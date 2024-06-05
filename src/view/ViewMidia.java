package view;

import factory.MediaFactory;
import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Disponivel;

import java.util.ArrayList;
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
        System.out.println("6. Adicionar mídia");
        System.out.println("7. Editar mídia");
        System.out.println("8. Remover mídia");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    @Override
    public int lerID(List<Midia> m) {
        System.out.println("\nEscolha uma mídia: ");
        for (Midia md : m) {
            System.out.println(md.toString() + "\n");
        }
        System.out.print("Digite o ID da mídia: ");
        return scanner.nextInt();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void listar(List<Midia> midias) {
        for (int i = 0; i < midias.size(); i++) {
            System.out.println(midias.get(i).toString());
            if (i < midias.size() - 1) {
                System.out.println();
            }
        }
    }

    @Override
    public Midia detalhar() {
        scanner.nextLine();
        System.out.print("Digite o título da mídia: ");
        String titulo = scanner.nextLine();

        System.out.println("\nEscolha um gênero: ");
        for (Genero g : Genero.values()) {
            System.out.println(g.ordinal() + " - " + g);
        }
        System.out.print("Digite o número do gênero: ");
        int generoIndex = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Genero genero = Genero.values()[generoIndex];

        System.out.println("\nEscolha uma categoria: ");
        for (Categoria c : Categoria.values()) {
            System.out.println(c.ordinal() + " - " + c);
        }
        System.out.print("Digite o número da categoria: ");
        int categoriaIndex = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Categoria categoria = Categoria.values()[categoriaIndex];

        return MediaFactory.createMedia(titulo, genero, categoria, new Disponivel());
    }
}
