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

    public void inserirDevolucao(Devolucao devolucao) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, devolucao.getAluguel().getId());
            preparedStatement.setString(2, devolucao.getPagamento().obterMetodo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(resultSet.getInt("id"));
                devolucao.getAluguel().setId(resultSet.getInt("aluguelId"));

                String pagamento = resultSet.getString("pagamento");

                switch (pagamento){
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
        return devolucoes;
    }

    public Devolucao buscarDevolucaoPorId(int id) {
        Devolucao devolucao = null;
        int idAluguel = 0;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    devolucao = new Devolucao();
                    devolucao.setId(resultSet.getInt("id"));

                    idAluguel = resultSet.getInt("aluguelId");
                    //devolucao.getAluguel().setId(resultSet.getInt("aluguelId"));

                    String pagamento = resultSet.getString("pagamento");

                    switch (pagamento){
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

        if(idAluguel > 0){
            devolucao.setAluguel(aluguelDAO.buscarAluguelPorId(idAluguel));
        }

        return devolucao;
    }
}
