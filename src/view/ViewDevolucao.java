package view;

import DAOs.AluguelDAO;
import factory.DevolucaoFactory;
import model.Cliente;
import movimentacao.Aluguel;
import movimentacao.Devolucao;
import pagamentos.*;

import java.util.List;
import java.util.Scanner;

public class ViewDevolucao implements IView<Devolucao>{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Devolução ===");
        System.out.println("1. Listar");
        System.out.println("2. Efetuar devolução");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public void listar(List<Devolucao> devolucao) {
        for (Devolucao d : devolucao) {
            System.out.println(d.toString());
        }
    }

    @Override
    public Devolucao detalhar() {
        AluguelDAO adao = new AluguelDAO();
        List<Aluguel> a = adao.listarAlugueis();

        System.out.println("\nEscolha um aluguel: ");
        for (Aluguel al : a) {
            System.out.println(al.toString());
        }
        System.out.print("Digite o número do aluguel: ");
        int idAl = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Aluguel auxA = adao.buscarAluguelPorId(idAl);

        Pagamento p = null;
        System.out.println("\nEscolha um Pagamento: ");
        System.out.println("=== Pagamento ===");
        System.out.println("1. Dinheiro");
        System.out.println("2. Pix");
        System.out.println("3. Cartão de Crédito");
        System.out.println("4. Cartão de Débito");
        int idP = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        switch(idP){
            case 1:
                p = new Dinheiro();
                break;
            case 2:
                p = new Pix();
                break;
            case 3:
                p = new CartaoDeCredito();
                break;
            case 4:
                p = new CartaoDeDebito();
        }

        return DevolucaoFactory.createDevolucao(auxA, p);
    }

    @Override
    public int lerID(List<Devolucao> o) {
        System.out.print("Digite o ID da devolução: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
