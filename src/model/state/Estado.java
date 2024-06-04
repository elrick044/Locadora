package model.state;

import model.Midia;

public interface Estado {
    String nome();
    void alugar(Midia m);
    void devolver(Midia m);
    void reservar(Midia m);
    void cancelarReserva(Midia m);

    String obterEstado();
}
