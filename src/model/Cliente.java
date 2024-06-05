package model;

import movimentacao.Aluguel;

import java.util.List;

public class Cliente {
    private int clienteId;
    private String name;
    private String email;
    private String telefone;
    private Endereco endereco;
    private String senha;

    public List<Aluguel> alugueis;

    public Cliente() {
    }



    public Cliente(String name, String email, String telefone, Endereco endereco, String senha) {
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Cliente(int clienteId, String name, String email, String telefone, Endereco endereco, List<Aluguel> alugueis, String senha) {
        this.clienteId = clienteId;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.alugueis = alugueis;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco=" + endereco +
                ", senha='" + senha + '\'' +
                ", alugueis=" + alugueis +
                '}';
    }
}
