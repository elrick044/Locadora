package model;

import movimentacao.Aluguel;

import java.util.List;

public class Cliente {
    private int clienteId;
    private String name;
    private String email;
    private String telefone;
    private Endereco endereco;

    public List<Aluguel> alugueis;

    public Cliente(int clienteId, String name, String email, String telefone, Endereco endereco) {
        this.clienteId = clienteId;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = "Cliente ID: " + clienteId + "\n"
                + "Nome: " + name + "\n"
                + "Email: " + email + "\n"
                + "Telefone: " + telefone + "\n"
                + "Endereço: " + endereco + "\n"
                + "Aluguéis:\n";
        if(!(alugueis == null)){
            for (Aluguel aluguel : alugueis) {
                result += "  - " + aluguel + "\n";
            }
        }
        return result;
    }
}
