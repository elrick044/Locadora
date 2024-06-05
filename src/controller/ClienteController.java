package controller;

import DAOs.ClienteDAO;
import DAOs.EnderecoDAO;
import model.Cliente;
import model.Endereco;
import view.IView;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ClienteController {
    public List<Cliente> c;
    public IView v;
    public ClienteDAO cdao = new ClienteDAO();
    public EnderecoDAO edao = new EnderecoDAO();

    public ClienteController(List<Cliente> c, IView v) {
        this.c = c;
        this.v = v;
    }

    public void iniciar() {
        int opcao;
        while(true) {
            System.out.println();
            c = cdao.listarClientes();
            opcao = v.exibirMenu();
            switch (opcao) {
                case 1:
                    System.out.println();
                    v.listar(c);
                    break;
                case 2:
                    adicionar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    editar();
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

    public void adicionar() {
        Cliente aux = (Cliente) v.detalhar();

        aux.getEndereco().setId(edao.inserirEndereco(aux.getEndereco()));
        cdao.inserirCliente(aux);
    }

    public void editar(){
        Cliente select = cdao.buscarClientePorId(v.lerID(c));
        System.out.println("Estes são os dados atuais: \n" + select.toString() + "\n");

        Cliente aux = (Cliente) v.detalhar();
        aux.setClienteId(select.getClienteId());

        edao.atualizarEndereco(aux.getEndereco());
        cdao.atualizarCliente(aux);
    }

    public void excluir(){
        cdao.excluirCliente(v.lerID(c));
    }
}
