package view;

import model.Cliente;
import model.Endereco;
import model.Midia;

import java.util.List;
import java.util.Scanner;

public class ViewCliente implements IView<Cliente> {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Cliente ===");
        System.out.println("1. Listar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Excluir");
        System.out.println("4. Editar");
        System.out.println("5. Buscar");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public void listar(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    @Override
    public Cliente detalhar() {
        System.out.print("Digite nome: ");
        String name = scanner.nextLine();

        System.out.print("Digite email: ");
        String email = scanner.nextLine();

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

        System.out.println("Digite senha:");
        String senha = scanner.nextLine();

        Endereco endereco = new Endereco(rua, cidade, estado, cep);

        return new Cliente(name, email, telefone, endereco, senha);
    }

    @Override
    public int lerID() {
        System.out.print("Digite o ID da cliente: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
