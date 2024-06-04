package factory;

import model.Categoria;
import model.Genero;
import model.Midia;

import java.util.List;

public class MediaFactory {
    public Midia createMedia(int id, String titulo, Genero genero, List<Categoria> categoria, boolean disponivel){
        return new Midia(id, titulo, genero, categoria, disponivel);
    }
}
