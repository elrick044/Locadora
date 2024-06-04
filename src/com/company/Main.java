package com.company;

import bd.DatabaseConnection;
import controller.MidiaController;
import factory.MediaFactory;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Disponivel;
import view.ViewPrincipal;
import view.IView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;


public class Main {

    public static void main(String[] args)  {

        String sql = "INSERT INTO teste (nome) VALUES (?)";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Erico");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
