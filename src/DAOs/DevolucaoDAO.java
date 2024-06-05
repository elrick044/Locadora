package DAOs;

import bd.DatabaseConnection;
import factory.DAOFactory;
import movimentacao.Devolucao;
import pagamentos.CartaoDeCredito;
import pagamentos.CartaoDeDebito;
import pagamentos.Dinheiro;
import pagamentos.Pix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucaoDAO {
    private static final String INSERT_QUERY = "INSERT INTO Devolucao (aluguelId, pagamento) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Devolucao SET aluguelId = ?, pagamento = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Devolucao WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Devolucao";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Devolucao WHERE id = ?";

    private final AluguelDAO aluguelDAO = DAOFactory.criarAluguelDAO();

    public int inserirDevolucao(Devolucao devolucao) {
        int id =0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, devolucao.getAluguel().getId());
            preparedStatement.setString(2, devolucao.getPagamento().obterMetodo());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1); // Get the first generated key
                } else {
                    throw new SQLException("A criação do endereço falhou, nenhum ID obtido.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizarDevolucao(Devolucao devolucao) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, devolucao.getAluguel().getId());
            preparedStatement.setString(2, devolucao.getPagamento().obterMetodo());
            preparedStatement.setInt(3, devolucao.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirDevolucao(int id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Devolucao> listarDevolucoes() {
        List<Devolucao> devolucoes = new ArrayList<>();
        AluguelDAO aluguelDAO = new AluguelDAO(); // Instância do DAO de Aluguel
        List<Integer> aluguelIds = new ArrayList<>(); // Lista para armazenar os IDs dos aluguéis

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(resultSet.getInt("id"));

                int aluguelId = resultSet.getInt("aluguelId");
                aluguelIds.add(aluguelId); // Adicionar o ID do aluguel à lista

                String pagamento = resultSet.getString("pagamento");

                switch (pagamento) {
                    case "PIX" -> devolucao.setPagamento(new Pix());
                    case "DINHEIRO" -> devolucao.setPagamento(new Dinheiro());
                    case "DEBITO" -> devolucao.setPagamento(new CartaoDeDebito());
                    case "CREDITO" -> devolucao.setPagamento(new CartaoDeCredito());
                }

                devolucoes.add(devolucao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associar os aluguéis após o loop principal
        for (int i = 0; i < devolucoes.size(); i++) {
            Devolucao devolucao = devolucoes.get(i);
            int aluguelId = aluguelIds.get(i);
            devolucao.setAluguel(aluguelDAO.buscarAluguelPorId(aluguelId)); // Buscar e associar o aluguel pelo ID
        }

        return devolucoes;
    }

    public Devolucao buscarDevolucaoPorId(int id) {
        Devolucao devolucao = null;
        int idAluguel = 0;
        AluguelDAO aluguelDAO = new AluguelDAO(); // Instância do DAO de Aluguel

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    devolucao = new Devolucao();
                    devolucao.setId(resultSet.getInt("id"));

                    idAluguel = resultSet.getInt("aluguelId"); // Salvar o ID do aluguel

                    String pagamento = resultSet.getString("pagamento");

                    switch (pagamento) {
                        case "PIX" -> devolucao.setPagamento(new Pix());
                        case "DINHEIRO" -> devolucao.setPagamento(new Dinheiro());
                        case "DEBITO" -> devolucao.setPagamento(new CartaoDeDebito());
                        case "CREDITO" -> devolucao.setPagamento(new CartaoDeCredito());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Associar o aluguel após fechar o ResultSet
        if (idAluguel > 0) {
            devolucao.setAluguel(aluguelDAO.buscarAluguelPorId(idAluguel));
        }

        return devolucao;
    }

}
