package DAOs;

import bd.DatabaseConnection;
import model.Categoria;
import model.Midia;
import model.state.Alugado;
import model.state.Disponivel;
import model.state.EmManutencao;
import model.state.Reservado;
import movimentacao.Aluguel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AluguelDAO {
    private static final String INSERT_QUERY = "INSERT INTO Aluguel (clienteId, dataAluguel, dataDevolucao, preco) VALUES (?, ?, ?, ?)";
    private static final String INSERT_QUERY_ALUGUEL_MIDIA = "INSERT INTO aluguel_midia (aluguelId, midiaId) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Aluguel SET clienteId = ?, dataAluguel = ?, dataDevolucao = ?, preco = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Aluguel WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Aluguel";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Aluguel WHERE id = ?";
    private static final String DELETE_ALUGUEL_MIDIA_QUERY = "DELETE FROM aluguel_midia WHERE aluguelId = ?";

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final MidiaDAO midiaDAO = new MidiaDAO();


    public void inserirAluguel(Aluguel aluguel) {
        int id = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, aluguel.getCliente().getClienteId());
            preparedStatement.setDate(2, new java.sql.Date(aluguel.getDataAluguel().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(aluguel.getDataDevolucao().getTime()));
            preparedStatement.setDouble(4, aluguel.getPreco());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("A criação do aluguel falhou, nenhum ID obtido.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (id > 0) {
            try (Connection connection = DatabaseConnection.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_ALUGUEL_MIDIA)) {
                for (int i = 0; i < aluguel.getMidias().size(); i++) {
                    System.out.println("" + id);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, aluguel.getMidias().get(i).getMidiaId());
                    preparedStatement.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Aluguel> listarAlugueis() {
        List<Aluguel> alugueis = new ArrayList<>();
        Map<Integer, List<Integer>> alugueisMidiasMap = new HashMap<>();
        List<Integer> clientesIds = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setId(resultSet.getInt("id"));
                aluguel.setDataAluguel(resultSet.getDate("dataAluguel"));
                aluguel.setDataDevolucao(resultSet.getDate("dataDevolucao"));
                aluguel.setPreco(resultSet.getDouble("preco"));

                int clienteId = resultSet.getInt("clienteId");
                clientesIds.add(clienteId);

                alugueis.add(aluguel);

                // Inicializa a lista de midias para o aluguel atual
                alugueisMidiasMap.put(aluguel.getId(), new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associando clientes aos alugueis
        ClienteDAO clienteDAO = new ClienteDAO();
        for (int i = 0; i < alugueis.size(); i++) {
            int clienteId = clientesIds.get(i);
            alugueis.get(i).setCliente(clienteDAO.buscarClientePorId(clienteId));
        }

        // Buscando e associando IDs das mídias aos alugueis
        String selectMidiasQuery = "SELECT aluguelId, midiaId FROM aluguel_midia";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectMidiasQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int aluguelId = resultSet.getInt("aluguelId");
                int midiaId = resultSet.getInt("midiaId");

                if (alugueisMidiasMap.containsKey(aluguelId)) {
                    alugueisMidiasMap.get(aluguelId).add(midiaId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associando midias aos alugueis
        MidiaDAO midiaDAO = new MidiaDAO();
        for (Aluguel aluguel : alugueis) {
            List<Integer> midiasIds = alugueisMidiasMap.get(aluguel.getId());
            ArrayList<Midia> midias = new ArrayList<>();
            for (int midiaId : midiasIds) {
                midias.add(midiaDAO.buscarMidiaPorId(midiaId));
            }
            aluguel.setMidias(midias);
        }
        return alugueis;
    }

    public Aluguel buscarAluguelPorId(int id) {
        Aluguel aluguel = new Aluguel();

        int clienteId = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    aluguel.setId(resultSet.getInt("id"));
                    aluguel.setDataAluguel(resultSet.getDate("dataAluguel"));
                    aluguel.setDataDevolucao(resultSet.getDate("dataDevolucao"));
                    aluguel.setPreco(resultSet.getDouble("preco"));

                    clienteId = resultSet.getInt("clienteId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associando clientes aos alugueis
        aluguel.setCliente(clienteDAO.buscarClientePorId(clienteId));


        // Buscando e associando IDs das mídias aos alugueis
        List<Integer> midiasIds = new ArrayList<>();

        String selectMidiasQuery = "SELECT midiaId FROM aluguel_midia WHERE aluguelId = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectMidiasQuery)) {
            preparedStatement.setInt(1, aluguel.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    midiasIds.add(resultSet.getInt("midiaId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associando midias aos alugueis
        ArrayList<Midia> midias = new ArrayList<>();
        for (int midiaId : midiasIds) {
            midias.add(midiaDAO.buscarMidiaPorId(midiaId));
        }
        aluguel.setMidias(midias);

        if (aluguel.getId() == 0) {
            return null;
        }
        return aluguel;
    }

    public void updateAluguel(Aluguel aluguel) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, aluguel.getCliente().getClienteId());
            preparedStatement.setDate(2, new java.sql.Date(aluguel.getDataAluguel().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(aluguel.getDataDevolucao().getTime()));
            preparedStatement.setDouble(4, aluguel.getPreco());
            preparedStatement.setInt(5, aluguel.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Atualizar a tabela de relacionamento aluguel_midia
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_ALUGUEL_MIDIA_QUERY)) {
            deleteStatement.setInt(1, aluguel.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_QUERY_ALUGUEL_MIDIA)) {
            for (Midia midia : aluguel.getMidias()) {
                insertStatement.setInt(1, aluguel.getId());
                insertStatement.setInt(2, midia.getMidiaId());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAluguel(int id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement deleteMidiaStatement = connection.prepareStatement(DELETE_ALUGUEL_MIDIA_QUERY);
             PreparedStatement deleteAluguelStatement = connection.prepareStatement(DELETE_QUERY)) {
            // Deletar associações na tabela aluguel_midia
            deleteMidiaStatement.setInt(1, id);
            deleteMidiaStatement.executeUpdate();

            // Deletar o aluguel na tabela Aluguel
            deleteAluguelStatement.setInt(1, id);
            deleteAluguelStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
