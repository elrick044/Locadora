package factory;

import model.Endereco;

public class EnderecoFactory {
    public static Endereco createEndereco(String rua, String cidade, String estado, String cep){
        return new Endereco(rua, cidade, estado, cep);
    }
}
