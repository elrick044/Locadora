package model;

import model.state.Estado;

import java.util.List;

public class Midia {
    private int midiaId;
    private String titulo;
    private List<Genero> genero;
    private Categoria categoria;
    private Estado estado;

    public Midia() {
    }

    public Midia(int midiaId, String titulo, List<Genero> genero, Categoria categoria, Estado estado) {
        this.midiaId = midiaId;
        this.titulo = titulo;
        this.genero = genero;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(int midiaId) {
        this.midiaId = midiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Genero> getGenero() {
        return genero;
    }

    public void setGenero(List<Genero> genero) {
        this.genero = genero;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Midia{" +
                "midiaId=" + midiaId +
                ", titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", categoria=" + categoria +
                ", disponivel=" + estado +
                '}';
    }

    public void devolver(){
        estado.devolver(this);
    }

    public void alugar(){
        estado.alugar(this);
    }

    public void reservar(){
        estado.reservar(this);
    }

    public void cancelarReserva(){
        estado.cancelarReserva(this);
    }
}
