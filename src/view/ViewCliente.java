package view;

import Controller.*;
import DAOs.EnderecoDAO;
import factory.ClienteFactory;
import factory.EnderecoFactory;
import model.Cliente;
import model.Endereco;
import model.Midia;

import java.util.List;
import java.util.Scanner;

public class ViewCliente implements IView<Cliente> {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Cliente ===");
        System.out.println("1. Listar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Excluir");
        System.out.println("4. Editar");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public void listar(List<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).toString());
            if (i < clientes.size() - 1) {
                System.out.println();
            }
        }
    }

    @Override
    public Cliente detalhar() {
        String email = null;

        EmailValidator emailV = new StrongEmailValidator();
        //PasswordValidator passV = new StrongPasswordValidator();

        scanner.nextLine();
        System.out.print("Digite nome: ");
        String name = scanner.nextLine();

       do{
            System.out.print("Digite email: ");
            email = scanner.nextLine();
            if(!emailV.isValid(email)) System.out.println("Email inválido");
       }while(!emailV.isValid(email));


        System.out.print("Digite telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite rua: ");
        String rua = scanner.nextLine();

        System.out.print("Digite cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Digite estado: ");
        String estado = scanner.nextLine();

        System.out.print("Digite CEP: ");
        String cep = scanner.nextLine();

        Endereco endereco = EnderecoFactory.createEndereco(rua, cidade, estado, cep);

        return ClienteFactory.createCliente(name, email, telefone, endereco);
    }

    @Override
    public int lerID(List<Cliente> clientes) {
        System.out.println("\nEscolha um cliente: ");
        for (Cliente c : clientes) {
            System.out.println(c.toString() + "\n");
        }
        System.out.print("Digite o ID da cliente: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
