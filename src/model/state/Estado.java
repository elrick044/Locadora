package model.state;

import model.Midia;

public interface Estado {
    String nome();
    void alugar(Midia m);
    void devolver(Midia m);

    String obterEstado();
}
