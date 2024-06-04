package view;

import model.Midia;

import java.util.List;
import java.util.Scanner;

public class ViewMain implements IView<Object>{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Videolocadora ===");
        System.out.println("1. Clientes");
        System.out.println("2. Mídias");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public void listar(List<Object> midias) {
        return;
    }

    @Override
    public Object detalhar() {
        return null;
    }

    @Override
    public int lerID() {
        return 0;
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
