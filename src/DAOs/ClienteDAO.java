package DAOs;

import bd.DatabaseConnection;
import factory.DAOFactory;
import model.Cliente;
import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String INSERT_QUERY = "INSERT INTO Cliente (nome, email, telefone, enderecoId) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Cliente SET nome = ?, email = ?, telefone = ?, enderecoId = ? WHERE clienteId = ?";
    private static final String DELETE_QUERY = "DELETE FROM Cliente WHERE clienteId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Cliente";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Cliente WHERE clienteId = ?";
    private final EnderecoDAO enderecoDAO = DAOFactory.criarEnderecoDAO();//mudar para a criação com factory

    public void inserirCliente(Cliente cliente) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, String.valueOf(cliente.getEndereco().getId())); // Supondo que o endereço possa ser convertido para String

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int atualizarCliente(Cliente cliente) {
        int id = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, String.valueOf(cliente.getEndereco().getId())); // Supondo que o endereço possa ser convertido para String
            preparedStatement.setInt(5, cliente.getClienteId());

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

    public void excluirCliente(int clienteId) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, clienteId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        List<Integer> enderecoIds = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("clienteId"));
                cliente.setName(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                int enderecoId = resultSet.getInt("enderecoId");

                enderecoIds.add(enderecoId);  // Adiciona o ID do endereco à lista temporária

                cliente.setEndereco(null);  // Inicialmente define o endereço como null
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Busca os endereços após fechar o ResultSet
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        for (int i = 0; i < clientes.size(); i++) {
            int enderecoId = enderecoIds.get(i);
            clientes.get(i).setEndereco(enderecoDAO.buscarEnderecoPorId(enderecoId));
        }

        return clientes;
    }

    public Cliente buscarClientePorId(int clienteId) {
        Cliente cliente = null;
        int enderecoId = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, clienteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setClienteId(resultSet.getInt("clienteId"));
                    cliente.setName(resultSet.getString("nome"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                    enderecoId = resultSet.getInt("enderecoId");  // Extrai o ID do endereco enquanto o ResultSet está aberto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (cliente != null) {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            cliente.setEndereco(enderecoDAO.buscarEnderecoPorId(enderecoId));  // Busca o endereco após fechar o ResultSet
        }

        return cliente;
    }
}
