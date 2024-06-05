package com.company;

import DAOs.*;
import bd.DatabaseConnection;
import controller.ClienteController;
import controller.MidiaController;
import factory.MediaFactory;
import model.*;
import model.state.Disponivel;
import model.state.Estado;
import movimentacao.Aluguel;
import movimentacao.Devolucao;
import pagamentos.Pix;
import view.ViewCliente;
import view.ViewMain;
import view.ViewMidia;
import view.IView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args)  {
        IView v = new ViewMain();
        List<Midia> m = new ArrayList<Midia>();
        List<Cliente> c = new ArrayList<Cliente>();
        int op;

        while(true) {
            System.out.println();
            op = v.exibirMenu();

            switch (op) {
                case 1:
                    ClienteController cc = new ClienteController(c, new ViewCliente());
                    cc.iniciar();
                    break;
                case 2:
                    MidiaController mc = new MidiaController(m, new ViewMidia());
                    mc.iniciar();
                    break;
                case 3:
                    v.exibirMensagem("Saindo...");
                    break;
                default:
                    v.exibirMensagem("Opção inválida.");
            }

            if(op == 3){
                break;
            }
        }



        //MidiaController mc = new MidiaController(m, v);

//        MidiaController mc = new MidiaController(m, new ViewMidia());
//
//        mc.v.exibirMenu();
//
//        mc.alugar();
//        mc.reservar();
//        mc.devolver();
//        mc.reservar();
//        mc.cancelarReserva();
//
    }
}
