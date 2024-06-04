package controller;

import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;
import view.IView;

import java.util.List;

public class MidiaController {
    public List<Midia> m;
    public IView v;

    public MidiaController(List<Midia> midias, IView view) {
        this.m = midias;
        this.v = view;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            opcao = v.exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println();
                    v.listar(m);
                    break;
                case 2:
                    alugar();
                    break;
                case 3:
                    devolver();
                    break;
                case 4:
                    reservar();
                    break;
                case 5:
                    cancelarReserva();
                    break;
                case 6:
                    v.exibirMensagem("Saindo...");
                    break;
                default:
                    v.exibirMensagem("Opção inválida.");
            }

            if(opcao == 6){
                break;
            }
        }
    }

    public void devolver(){
        //m.devolver();
    }

    public void alugar(){
       // m.alugar();
    }

    public void reservar(){
      //  m.reservar();
    }

    public void cancelarReserva(){
        //m.cancelarReserva();
    }
}
