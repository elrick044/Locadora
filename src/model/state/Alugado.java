package model.state;

import model.Midia;

public class Alugado implements Estado{
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
    public void reservar(Midia m) {
        System.out.println("O item está alugado e não pode ser reservado.");
    }

    @Override
    public void cancelarReserva(Midia m) {
        System.out.println("O item não está reservado.");
    }
}
