package model.state;

import model.Midia;

public class Alugado implements Estado{
    @Override
    public String nome() {
        return "Alugado";
    }

    @Override
    public void alugar(Midia m) {
        System.out.println("O item já está alugado.");
    }

    @Override
    public void devolver(Midia m) {
        System.out.println("Item devolvido com sucesso.");
        m.setEstado(new Disponivel());
    }

    @Override
    public String obterEstado() {
        return "ALUGADO";
    }
}
