package factory;

import model.Cliente;
import model.Endereco;
import model.Midia;
import movimentacao.Aluguel;

import java.util.ArrayList;
import java.util.Date;

public class AluguelFactory {
    public static Aluguel createAluguel(Cliente cliente, ArrayList<Midia> midias, Date dataAluguel, Date dataDevolucao, double preco){
        return new Aluguel(cliente, midias, dataAluguel, dataDevolucao, preco);
    }
}
