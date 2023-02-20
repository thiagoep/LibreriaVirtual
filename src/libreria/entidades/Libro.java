package libreria.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long isbn;
    
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    @OneToOne
    private Autor autor;
    
    @OneToOne
    private Editorial Editorial;

    public Libro() {
        alta = true;
        ejemplaresPrestados = 0;
    }

    public Libro(String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial Editorial) {
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = 0;
        this.ejemplaresRestantes = ejemplares;
        this.alta = true;
        this.autor = autor;
        this.Editorial = Editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return Editorial;
    }

    public void setEditorial(Editorial Editorial) {
        this.Editorial = Editorial;
    }

    public Long getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes=" + ejemplaresRestantes + ", alta=" + alta + ", autor=" + autor + ", Editorial=" + Editorial + '}';
    }
    
}
