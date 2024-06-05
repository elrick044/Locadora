package DAOs;

import bd.DatabaseConnection;
import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private static final String INSERT_QUERY = "INSERT INTO Endereco (rua, cidade, estado, cep) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Endereco SET rua = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Endereco WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Endereco";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Endereco WHERE id = ?";

    public int inserirEndereco(Endereco endereco) {
        int id = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getCidade());
            preparedStatement.setString(3, endereco.getEstado());
            preparedStatement.setString(4, endereco.getCep());

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

    public void atualizarEndereco(Endereco endereco) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setString(2, endereco.getCidade());
            preparedStatement.setString(3, endereco.getEstado());
            preparedStatement.setString(4, endereco.getCep());
            preparedStatement.setInt(5, endereco.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEndereco(int id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Endereco> listarEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(resultSet.getInt("id"));
                endereco.setRua(resultSet.getString("rua"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setEstado(resultSet.getString("estado"));
                endereco.setCep(resultSet.getString("cep"));

                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    public Endereco buscarEnderecoPorId(int id) {
        Endereco endereco = null;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    endereco = new Endereco();
                    endereco.setId(resultSet.getInt("id"));
                    endereco.setRua(resultSet.getString("rua"));
                    endereco.setCidade(resultSet.getString("cidade"));
                    endereco.setEstado(resultSet.getString("estado"));
                    endereco.setCep(resultSet.getString("cep"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

}
