package movimentacao;

import pagamentos.Pagamento;

public class Devolucao {
    private Aluguel aluguel;
    private Pagamento pagamento;

    public void pagar() {
        pagamento.pagar();
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void devolver(){
        System.out.println("Midia(s): " + aluguel.getMidias() + "devolvida(s) com sucesso");
        pagar();
    }


}
