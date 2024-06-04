package com.company;

import DAOs.AluguelDAO;
import DAOs.ClienteDAO;
import DAOs.EnderecoDAO;
import DAOs.MidiaDAO;
import bd.DatabaseConnection;
import controller.MidiaController;
import factory.MediaFactory;
import model.*;
import model.state.Disponivel;
import model.state.Estado;
import movimentacao.Aluguel;
import view.ViewPrincipal;
import view.IView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        AluguelDAO aluguelDAO = new AluguelDAO();
        MidiaDAO midiaDAO = new MidiaDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();


        Endereco endereco = new Endereco(1, "joão XXIII", "Medianeira", "Parana", "85460-000");
        Cliente cliente = new Cliente(1, "Erick", "erick@gmail.com", "999", endereco);

        //enderecoDAO.inserirEndereco(endereco);
        //clienteDAO.inserirCliente(cliente);




        List<Genero> generos = new ArrayList<>();


        generos.add(Genero.REGGAE);
        generos.add(Genero.ROMANCE);

        Estado estado = new Disponivel();

        Midia midia = new Midia(1, "teste", generos, Categoria.FILME, estado);

        //midiaDAO.inserirMidia(midia);

        ArrayList<Midia> midias = (ArrayList<Midia>) midiaDAO.listarMidias();

        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDAO.listarClientes();

        ArrayList<Endereco> enderecos = (ArrayList<Endereco>) enderecoDAO.listarEnderecos();

        ArrayList<Aluguel> aluguels = (ArrayList<Aluguel>) aluguelDAO.listarAlugueis();

        Date data1 = new Date();
        Date data2 = new Date();

        Aluguel aluguel = new Aluguel(1, cliente, midias, data1, data2, 12.0);




        //aluguelDAO.inserirAluguel(aluguel);

        // Iterando sobre a lista de mídias e imprimindo os resultados
        /*for (Midia m : midias) {
            System.out.println(m);
        }*/


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
