package DAOs;

import bd.DatabaseConnection;
import model.Categoria;
import model.Midia;
import model.state.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MidiaDAO {
    private static final String INSERT_QUERY = "INSERT INTO Midia (titulo, categoria, estado, genero) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Midia SET titulo = ?, categoria = ?, estado = ? WHERE midiaId = ?";
    private static final String DELETE_QUERY = "DELETE FROM Midia WHERE midiaId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Midia";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Midia WHERE midiaId = ?";

    public void inserirMidia(Midia midia) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, midia.getTitulo());
            preparedStatement.setString(2, midia.getCategoria().name());
            preparedStatement.setString(3, midia.getEstado().obterEstado());
            preparedStatement.setString(4, midia.getGenero().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarMidia(Midia midia) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, midia.getTitulo());
            preparedStatement.setString(2, midia.getCategoria().name());
            preparedStatement.setString(3, midia.getEstado().obterEstado());


            preparedStatement.setInt(4, midia.getMidiaId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirMidia(int midiaId) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, midiaId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Midia> listarMidias() {
        List<Midia> midias = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Midia midia = new Midia();
                midia.setMidiaId(resultSet.getInt("midiaId"));
                midia.setTitulo(resultSet.getString("titulo"));
                midia.setCategoria(Categoria.valueOf(resultSet.getString("categoria")));

                String estado = resultSet.getString("estado");

                switch (estado) {
                    case "DISPONIVEL" -> midia.setEstado(new Disponivel());
                    case "RESERVADO" -> midia.setEstado(new Reservado());
                    case "ALUGADO" -> midia.setEstado(new Alugado());
                    case "MANUTENCAO" -> midia.setEstado(new EmManutencao());
                }


                midias.add(midia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return midias;
    }

    public Midia buscarMidiaPorId(int midiaId) {
        Midia midia = null;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, midiaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    midia = new Midia();
                    midia.setMidiaId(resultSet.getInt("midiaId"));
                    midia.setTitulo(resultSet.getString("titulo"));
                    midia.setCategoria(Categoria.valueOf(resultSet.getString("categoria")));
                    String estado = resultSet.getString("estado");

                    switch (estado) {
                        case "DISPONIVEL" -> midia.setEstado(new Disponivel());
                        case "RESERVADO" -> midia.setEstado(new Reservado());
                        case "ALUGADO" -> midia.setEstado(new Alugado());
                        case "MANUTENCAO" -> midia.setEstado(new EmManutencao());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return midia;
    }
}
