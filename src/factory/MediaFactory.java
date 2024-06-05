package factory;

import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;

public class MediaFactory {
    public static Midia createMidia(String titulo, Genero genero, Categoria categoria, Estado estado){
        return new Midia(titulo, genero, categoria, estado);
    }
}
