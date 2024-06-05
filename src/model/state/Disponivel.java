package model.state;

import model.Midia;

public class Disponivel implements Estado {
    @Override
    public String nome() {
        return "Disponível";
    }

    @Override
    public void alugar(Midia m) {
        System.out.println("Item alugado com sucesso.");
        m.setEstado(new Alugado());
    }

    @Override
    public void devolver(Midia m) {
        System.out.println("O item já está disponível.");
    }

    @Override
    public void reservar(Midia m) {
        System.out.println("Item reservado com sucesso.");
        m.setEstado(new Reservado());
    }

    @Override
    public void cancelarReserva(Midia m) {
        System.out.println("O item não está reservado.");
    }

    @Override
    public String obterEstado() {
        return null;
    }
}
