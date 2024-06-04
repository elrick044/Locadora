package model.state;

import model.Midia;

public class Reservado implements Estado{
    @Override
    public String nome() {
        return "Reservado";
    }

    @Override
    public void alugar(Midia m) {
        System.out.println("O item está reservado e não pode ser alugado.");
    }

    @Override
    public void devolver(Midia m) {
        System.out.println("O item não está alugado.");
    }

    @Override
    public void reservar(Midia m) {
        System.out.println("O item já está reservado.");
    }

    @Override
    public void cancelarReserva(Midia m) {
        System.out.println("Reserva cancelada com sucesso.");
        m.setEstado(new Disponivel());
    }

    @Override
    public String obterEstado() {
        return "RESERVADO";
    }
}
