package movimentacao;

import pagamentos.Pagamento;

public class Devolucao {
    private int id;
    private Aluguel aluguel;
    private Pagamento pagamento;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Devolucao{" +
                "id=" + id +
                ", aluguel=" + aluguel +
                ", pagamento=" + pagamento +
                '}';
    }

    public Devolucao() {
    }

    public Devolucao(int id, Aluguel aluguel, Pagamento pagamento) {
        this.id = id;
        this.aluguel = aluguel;
        this.pagamento = pagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

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
