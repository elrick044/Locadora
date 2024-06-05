package controller;

import DAOs.AluguelDAO;
import DAOs.MidiaDAO;
import factory.DAOFactory;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;
import movimentacao.Aluguel;
import view.IView;

import java.util.List;

public class MidiaController {
    public List<Midia> m;
    public IView v;
    public MidiaDAO mdao = DAOFactory.criarMidiaDAO();
    public AluguelDAO adao = DAOFactory.criarAluguelDAO();

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
                    adicionar();
                    break;
                case 3:
                    editar();
                    break;
                case 4:
                    remover();
                    break;
                case 5:
                    v.exibirMensagem("Saindo...");
                    break;
                default:
                    v.exibirMensagem("Opção inválida.");
            }

            if(opcao == 5){
                break;
            }
        }
    }

    public void devolver(int id){
        Midia aux = mdao.buscarMidiaPorId(id);
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).devolver();
            atualizar(m.get(pos));
        }
    }

    public void alugar(int id){
        Midia aux = mdao.buscarMidiaPorId(id);
        int pos = procurarPosicaoPorId(m, aux.getMidiaId());

        if(pos == -1){
            System.out.println("Erro");
        }else {
            m.get(pos).alugar();
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
