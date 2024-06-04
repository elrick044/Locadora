package model.state;

import model.Midia;

public class EmManutencao implements Estado{
    @Override
    public void alugar(Midia m) {
        System.out.println("O item está em manutenção e não pode ser alugado.");
    }

    @Override
    public void devolver(Midia m) {
        System.out.println("O item não está alugado.");
    }

    @Override
    public void reservar(Midia m) {
        System.out.println("O item está em manutenção e não pode ser reservado.");
    }

    @Override
    public void cancelarReserva(Midia m) {
        System.out.println("O item não está reservado.");
    }
}
