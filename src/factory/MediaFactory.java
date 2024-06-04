package factory;

import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;

import java.util.List;

public class MediaFactory {
    public static Midia createMedia(int id, String titulo, List<Genero> genero, Categoria categoria, Estado estado){
        return new Midia(id, titulo, genero, categoria, estado);
        //arrumei
    }
}
