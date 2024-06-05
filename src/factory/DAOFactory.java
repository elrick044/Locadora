package factory;

import DAOs.*;

public class DAOFactory{
    public static AluguelDAO criarAluguelDAO() {
        return new AluguelDAO();
    }

    public static ClienteDAO criarClienteDAO() {
        return new ClienteDAO();
    }

    public static DevolucaoDAO criarDevolucaoDAO() {
        return new DevolucaoDAO();
    }

    public static EnderecoDAO criarEnderecoDAO() {
        return new EnderecoDAO();
    }

    public static MidiaDAO criarMidiaDAO() {
        return new MidiaDAO();
    }
}
