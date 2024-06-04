package controller;

import model.Categoria;
import model.Genero;
import model.Midia;
import model.state.Estado;
import view.IView;

import java.util.List;

public class MidiaController {
    Midia m;
    IView v;

    public MidiaController(Midia m, IView v) {
        this.m = m;
        this.v = v;
    }

    public int getMidiaId() {
        return m.getMidiaId();
    }

    public void setMidiaId(int midiaId) {
        m.setMidiaId(midiaId);
    }

    public String getTitulo() {
        return m.getTitulo();
    }

    public void setTitulo(String titulo) {
        m.setTitulo(titulo);
    }

    public List<Genero> getGenero() {
        return m.getGenero();
    }

    public void setGenero(List<Genero> genero) {
        m.setGenero(genero);
    }

    public Categoria getCategoria() {
        return m.getCategoria();
    }

    public void setCategoria(Categoria categoria) {
        m.setCategoria(categoria);
    }

    public Estado getEstado() {
        return m.getEstado();
    }

    public void setEstado(Estado estado) {
        m.setEstado(estado);
    }

    public void devolver(){
        m.devolver();
    }

    public void alugar(){
        m.alugar();
    }

    public void reservar(){
        m.reservar();
    }

    public void cancelarReserva(){
        m.cancelarReserva();
    }
}
