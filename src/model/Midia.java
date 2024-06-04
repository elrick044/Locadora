package model;

import java.util.List;

public class Midia {
    private int midiaId;
    private String titulo;
    private Genero genero;
    private List<Categoria> categoria;
    private boolean disponivel = true;

    public Midia() {
    }

    public Midia(int midiaId, String titulo, Genero genero, List<Categoria> categoria, boolean disponivel) {
        this.midiaId = midiaId;
        this.titulo = titulo;
        this.genero = genero;
        this.categoria = categoria;
        this.disponivel = disponivel;
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Midia{" +
                "midiaId=" + midiaId +
                ", titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", categoria=" + categoria +
                ", disponivel=" + disponivel +
                '}';
    }
}
