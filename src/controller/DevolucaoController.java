package controller;

import DAOs.DevolucaoDAO;
import factory.DAOFactory;
import movimentacao.Devolucao;
import view.IView;
import view.ViewMidia;

import java.util.List;

public class DevolucaoController {
    public List<Devolucao> d;
    public IView v;
    public DevolucaoDAO ddao = DAOFactory.criarDevolucaoDAO();

    public DevolucaoController(List<Devolucao> d, IView v) {
        this.d = d;
        this.v = v;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            d = ddao.listarDevolucoes();
            opcao = v.exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println();
                    v.listar(d);
                    break;
                case 2:
                    devolver();
                    break;
                case 3:
                    v.exibirMensagem("Saindo...");
                    break;
                default:
                    v.exibirMensagem("Opção inválida.");
            }

            if(opcao == 3){
                break;
            }
        }
    }

    public void devolver(){
        Devolucao aux = (Devolucao) v.detalhar();

        aux.pagar();

        MidiaController midiaController = new MidiaController(aux.getAluguel().getMidias(), new ViewMidia());

        for (int i = 0; i < aux.getAluguel().getMidias().size(); i++) {
            midiaController.devolver(aux.getAluguel().getMidias().get(i).getMidiaId());
        }

        ddao.inserirDevolucao(aux);
    }

}
