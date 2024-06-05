package controller;

import model.Cliente;
import view.IView;

import java.util.List;

public class ClienteController {
    public List<Cliente> c;
    public IView v;
    public ClienteController(List<Cliente> c, IView v) {
        this.c = c;
        this.v = v;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            opcao = v.exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println();
                    v.listar(c);
                    break;
                case 2:
                    cadastrar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    editar();
                    break;
                case 5:
                    buscar();
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
    public void cadastrar() {
        Cliente novoCliente = (Cliente) v.detalhar();
        c.add(novoCliente);
    }

    public void excluir(){
        int idProcurado = v.lerID();
        boolean achado = false;
        for (Cliente cliente : c) {
            if (cliente.getClienteId() == idProcurado) {
                c.remove(cliente);
                achado = true;
                break;

            }
        }
        if (!achado) {
            System.out.println("Não existe usuário com esse id");
        }
    }

    public void editar(){
        int idProcurado = v.lerID();
        boolean achado = false;
        for (Cliente cliente : c) {
            if (cliente.getClienteId() == idProcurado) {
                cliente = (Cliente) v.detalhar();
                achado = true;
                break;
            }
        }
        if (!achado) {
            System.out.println("Não existe usuário com esse id");
        }
    }

    public void buscar(){
        int idProcurado = v.lerID();
        boolean achado = false;
        for (Cliente cliente : c) {
            if (cliente.getClienteId() == idProcurado) {
                System.out.println(cliente);
                achado = true;
                break;
            }
        }
        if (!achado) {
            System.out.println("Não existe usuário com esse id");
        }
    }
}
