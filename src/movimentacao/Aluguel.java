package movimentacao;

import model.Cliente;
import model.Midia;

import java.util.ArrayList;
import java.util.Date;

public class Aluguel {
    public Aluguel(int id, Cliente cliente, ArrayList<Midia> midias, Date dataAluguel, Date dataDevolucao, double preco) {
        this.id = id;
        this.cliente = cliente;
        this.midias = midias;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.preco = preco;
    }

    public Aluguel() {
    }

    private int id;
    private Cliente cliente;
    private ArrayList<Midia> midias;
    private Date dataAluguel;
    private Date dataDevolucao;
    private double preco;

    @Override
    public String toString() {
        return "Aluguel{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", midias=" + midias +
                ", dataAluguel=" + dataAluguel +
                ", dataDevolucao=" + dataDevolucao +
                ", preco=" + preco +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Midia> getMidias() {
        return midias;
    }

    public void setMidias(ArrayList<Midia> midias) {
        this.midias = midias;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
