package controller;

import DAOs.AluguelDAO;
import factory.DAOFactory;
import movimentacao.Aluguel;
import view.IView;
import view.ViewMidia;

import java.util.List;

public class AluguelController {
    public List<Aluguel> a;
    public IView v;
    public AluguelDAO adao = DAOFactory.criarAluguelDAO();

    public AluguelController(List<Aluguel> a, IView v) {
        this.a = a;
        this.v = v;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            a = adao.listarAlugueis();
            opcao = v.exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println();
                    v.listar(a);
                    break;
                case 2:
                    alugar();
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

    public void alugar(){
        Aluguel aux = (Aluguel) v.detalhar();
        MidiaController midiaController = new MidiaController(aux.getMidias(), new ViewMidia());

        for (int i = 0; i < aux.getMidias().size(); i++) {
            midiaController.alugar(aux.getMidias().get(i).getMidiaId());
        }

        adao.inserirAluguel(aux);
    }
}
