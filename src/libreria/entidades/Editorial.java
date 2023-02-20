package libreria.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Editorial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Basic
    private String nombre;
    private Boolean alta;

    public Editorial() {
        alta = true;
    }

    public Editorial(String nombre) {
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
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }
    
    
}
