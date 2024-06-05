package factory;

import model.Cliente;
import model.Endereco;

public class ClienteFactory {
    public static Cliente createCliente(String name, String email, String telefone, Endereco endereco, String senha){
        return new Cliente(name, email, telefone, endereco, senha);
    }
}
