package model;

import movimentacao.Aluguel;

import java.util.List;

public class Cliente {
    public int clienteId;
    public String name;
    public String email;
    public String telefone;
    public Endereco endereco;

    public List<Aluguel> alugueis;

    public Cliente() {
    }

    public Cliente(String name, String email, String telefone, Endereco endereco) {
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Cliente(int clienteId, String name, String email, String telefone, Endereco endereco, List<Aluguel> alugueis) {
        this.clienteId = clienteId;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.alugueis = alugueis;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco=" + endereco +
                ", alugueis=" + alugueis +
                '}';
    }
}
