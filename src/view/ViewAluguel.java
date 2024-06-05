package view;

import DAOs.ClienteDAO;
import DAOs.MidiaDAO;
import model.Cliente;
import model.Genero;
import model.Midia;
import movimentacao.Aluguel;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewAluguel implements IView<Aluguel>{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Aluguéis ===");
        System.out.println("1. Listar");
        System.out.println("2. Efetuar aluguel");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public void listar(List<Aluguel> aluguel) {
        for (Aluguel a : aluguel) {
            System.out.println(a.toString());
        }
    }

    @Override
    public Aluguel detalhar() {
        ClienteDAO cdao = new ClienteDAO();
        MidiaDAO mdao = new MidiaDAO();
        List<Cliente> c = cdao.listarClientes();
        List<Midia> m = mdao.listarMidias();

        System.out.println("\nEscolha um cliente: ");
        for (Cliente cli : c) {
            System.out.println(cli.toString());
        }
        System.out.print("Digite o número do cliente: ");
        int idCli = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Cliente auxC = cdao.buscarClientePorId(idCli);

        System.out.println("\nEscolha suas mídias: ");
        for (Midia mid : m) {
            System.out.println(mid.toString());
        }

        System.out.print("Digite o número de mídias: ");
        int numMidias = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        List<Midia> alugados = new ArrayList<>();

        for (int i = 0; i < numMidias; i++) {
            System.out.print("Digite o ID da mídia " + (i + 1) + ": ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            alugados.add(mdao.buscarMidiaPorId(id));
        }

        System.out.print("Digite a data de aluguel (dd/MM/yyyy): ");
        String dataAluguelStr = scanner.nextLine();
        System.out.print("Digite a data de devolução (dd/MM/yyyy): ");
        String dataDevolucaoStr = scanner.nextLine();

        System.out.print("Digite o preço do aluguel: ");
        double preco = scanner.nextDouble();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAluguel = null, dataDevolucao = null;
        try {
            dataAluguel = dateFormat.parse(dataAluguelStr);
            dataDevolucao = dateFormat.parse(dataDevolucaoStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Aluguel(auxC, (ArrayList<Midia>) alugados, dataAluguel, dataDevolucao, preco);
    }

    @Override
    public int lerID(List<Aluguel> o) {
        System.out.print("Digite o ID do aluguel: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
