package libreria.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    private Boolean alta;

    public Autor() {
        alta = true;
    }

    public Autor(String nombre) {
        this.nombre = nombre;
        this.alta = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }
    
    
}
