package controller;

import DAOs.MidiaDAO;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;
import view.IView;

import java.util.List;

public class MidiaController {
    public List<Midia> m;
    public IView v;
    public MidiaDAO mdao = new MidiaDAO();

    public MidiaController(List<Midia> midias, IView view) {
        this.m = midias;
        this.v = view;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            m = mdao.listarMidias();
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
                    adicionar();
                    break;
                case 7:
                    editar();
                    break;
                case 8:
                    remover();
                    break;
                case 9:
                    v.exibirMensagem("Saindo...");
                    break;
                default:
                    v.exibirMensagem("Opção inválida.");
            }

            if(opcao == 9){
                break;
            }
        }
    }

    public void devolver(){
        Midia aux = mdao.buscarMidiaPorId(v.lerID(m));
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).devolver();
            atualizar(m.get(pos));
        }
    }

    public void alugar(){
        Midia aux = mdao.buscarMidiaPorId(v.lerID(m));
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).alugar();
            atualizar(m.get(pos));
        }
    }

    public void reservar(){
        Midia aux = mdao.buscarMidiaPorId(v.lerID(m));
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).reservar();
            atualizar(m.get(pos));
        }
    }

    public void cancelarReserva(){
        Midia aux = mdao.buscarMidiaPorId(v.lerID(m));
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).cancelarReserva();
            atualizar(m.get(pos));
        }
    }

    public void adicionar(){
        mdao.inserirMidia((Midia) v.detalhar());
    }

    public void editar() {
        Midia select = mdao.buscarMidiaPorId(v.lerID(m));
        System.out.println("Estes são os dados atuais: \n" + select.toString() + "\n");

        Midia aux = (Midia) v.detalhar();
        aux.setMidiaId(select.getMidiaId());

        mdao.atualizarMidia(aux);
    }

    public void atualizar(Midia md){
        mdao.atualizarMidia(md);
    }

    public void remover(){
        mdao.excluirMidia(v.lerID(m));
    }

    public static int procurarPosicaoPorId(List<Midia> midias, int id) {
        for (int i = 0; i < midias.size(); i++) {
            if (midias.get(i).getMidiaId() == id) {
                return i;
            }
        }
        return -1; // Retorna -1 se o ID não for encontrado
    }
}
