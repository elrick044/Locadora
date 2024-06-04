package controller;

import Controller.AuthUser;
import Controller.StrongEmailValidator;
import Controller.StrongPasswordValidator;
import model.Cliente;
import view.IView;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        Object novoCliente = v.detalhar();
        String email = null;
        String senha = null;
        String nome = null;

        try{
            Method getEmailMethod = novoCliente.getClass().getMethod("getEmail",null);
            email = (String) getEmailMethod.invoke(novoCliente);

            Method getSenhaMethod = novoCliente.getClass().getMethod("getSenha",null);
            senha = (String) getEmailMethod.invoke(novoCliente);

            Method getNomeMethod = novoCliente.getClass().getMethod("getNome",null);
            nome = (String) getEmailMethod.invoke(novoCliente);

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


        AuthUser a = new AuthUser(new StrongEmailValidator(),new StrongPasswordValidator());
        if(a.autenticar(email, senha, nome)) c.add((Cliente) novoCliente);
    }

    public void excluir(){

    }

    public void editar(){

    }

    public void buscar(){

    }
}
