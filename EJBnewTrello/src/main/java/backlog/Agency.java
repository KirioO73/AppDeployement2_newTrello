package backlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Agency implements Serializable {

    @OneToOne
    private Backlog backlog;
    @Id
    private String name;

    //Constructors
    public Agency() {
    }

    public Agency(String name) {
        this.name = name;
        this.backlog = new Backlog();
    }

    public Agency(Backlog backlog, String name) {
        this.backlog = backlog;
        this.name = name;
    }

    //Getters / Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agency)) return false;
        Agency agency = (Agency) o;
        return Objects.equals(name, agency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name);
    }
}
