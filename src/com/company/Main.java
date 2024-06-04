package com.company;

import DAOs.MidiaDAO;
import bd.DatabaseConnection;
import controller.MidiaController;
import factory.MediaFactory;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Disponivel;
import model.state.Estado;
import view.ViewPrincipal;
import view.IView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args)  {

        MidiaDAO midiaDAO = new MidiaDAO();

        /*
        List<Genero> generos = new ArrayList<>();


        generos.add(Genero.REGGAE);
        generos.add(Genero.ROMANCE);

        Estado estado = new Disponivel();

        Midia midia = new Midia(1, "teste", generos, Categoria.FILME, estado);

         */
        List<Midia> midias = midiaDAO.listarMidias();



        // Iterando sobre a lista de mídias e imprimindo os resultados
        for (Midia m : midias) {
            System.out.println(m);
        }


        /*
        IView v = new ViewPrincipal();
        Midia m = MediaFactory.createMedia(0, "Jorge", Arrays.asList(Genero.AVENTURA, Genero.ANIMACAO), Categoria.FILME, new Disponivel());

        MidiaController mc = new MidiaController(m, v);

        mc.alugar();
        mc.reservar();
        mc.devolver();
        mc.reservar();
        mc.cancelarReserva();

         */

    }
}
