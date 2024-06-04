package view;

import model.Midia;

import java.util.List;
import java.util.Scanner;

public interface IView<T> {
     int exibirMenu();
     void listar(List<T> o);
     T detalhar();
     int lerID();
     void exibirMensagem(String mensagem);
}
