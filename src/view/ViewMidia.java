package view;

import model.Midia;

import java.util.List;
import java.util.Scanner;

public class ViewMidia implements IView<Midia>{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int exibirMenu() {
        System.out.println("=== Mídias ===");
        System.out.println("1. Listar mídias");
        System.out.println("2. Alugar mídia");
        System.out.println("3. Devolver mídia");
        System.out.println("4. Reservar mídia");
        System.out.println("5. Cancelar reserva");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public int lerID() {
        System.out.print("Digite o ID da mídia: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void listar(List<Midia> midias) {
        for (Midia midia : midias) {
            System.out.println(midia.toString());
        }
    }

    @Override
    public Midia detalhar() {
        return null;
    }
}
