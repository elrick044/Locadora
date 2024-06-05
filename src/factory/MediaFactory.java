package factory;

import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;

import java.util.List;

public class MediaFactory {
    public static Midia createMedia(String titulo, Genero genero, Categoria categoria, Estado estado){
        return new Midia(titulo, genero, categoria, estado);
    }
}
