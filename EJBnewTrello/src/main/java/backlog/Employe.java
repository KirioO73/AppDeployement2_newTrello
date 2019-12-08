package backlog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Employe implements Serializable {
    @Id @GeneratedValue
    private long id;

    private String name;
    @ManyToOne
    private Agency agency;

    //Constructors
    public Employe() {
    }

    public Employe(String name) {
        this.name = name;
    }

    public Employe(String name, Agency agency) {
        this.name = name;
        this.agency = agency;
    }


    //Getters / Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return id == employe.id &&
                name.equals(employe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "" + name  + ", from " + agency.getName();
    }
}
