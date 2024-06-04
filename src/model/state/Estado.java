package model.state;

import model.Midia;

public interface Estado {
    void alugar(Midia m);
    void devolver(Midia m);
    void reservar(Midia m);
    void cancelarReserva(Midia m);

    String obterEstado();
}
