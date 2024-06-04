package com.company;

import controller.MidiaController;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Disponivel;
import view.ViewPrincipal;
import view.IView;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        IView v = new ViewPrincipal();
        Midia m = new Midia(0, "Jorge", Arrays.asList(Genero.AVENTURA, Genero.ANIMACAO), Categoria.FILME, new Disponivel());

        MidiaController mc = new MidiaController(m, v);

        mc.alugar();
        mc.reservar();
        mc.devolver();
        mc.reservar();
        mc.cancelarReserva();




    }
}
