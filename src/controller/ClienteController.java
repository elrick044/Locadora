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

        try{
            Method getEmailMethod = novoCliente.getClass().getMethod("getEmail",null);
            String email = (String) getEmailMethod.invoke(novoCliente);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


        AuthUser a = new AuthUser(new StrongEmailValidator());
        a.autenticar();
        c.add((Cliente) novoCliente);
    }

    public void excluir(){

    }

    public void editar(){

    }

    public void buscar(){

    }
}
