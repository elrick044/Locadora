package factory;

import model.Cliente;
import model.Midia;
import movimentacao.Aluguel;
import movimentacao.Devolucao;
import pagamentos.Pagamento;

import java.util.ArrayList;
import java.util.Date;

public class DevolucaoFactory {
    public static Devolucao createDevolucao(Aluguel aluguel, Pagamento pagamento){
        return new Devolucao(aluguel, pagamento);
    }
}
