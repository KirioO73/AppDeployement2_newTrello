package backlog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BacklogColumn implements Serializable {

    @Id @GeneratedValue
    private long id;

    private String name;

    @OneToOne
    private BacklogColumn next;
    @OneToOne
    private BacklogColumn prec;
    @OneToMany(cascade= CascadeType.ALL)
    private List<Entry> entries;

    //Constructors
    public BacklogColumn() {
        this.entries = new ArrayList<Entry>();
    }

    public BacklogColumn(String name) {
        this.name = name;
        this.entries = new ArrayList<Entry>();
        this.next = null;
        this.prec = null;
    }

    public BacklogColumn(String name, BacklogColumn prec, BacklogColumn next) {
        this.name = name;
        this.next = next;
        this.prec = prec;
        this.entries = new ArrayList<Entry>();
    }

    //Methods
    public void addEntry(Entry entry){
        if (!entries.contains(entry))
            entries.add(0 , entry);
    }

    public void deleteEntry( Entry entry){
        entries.remove(entry);
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

    public BacklogColumn getNext() {
        return next;
    }

    public void setNext(BacklogColumn next) {
        this.next = next;
    }

    public BacklogColumn getPrec() {
        return prec;
    }

    public void setPrec(BacklogColumn prec) {
        this.prec = prec;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BacklogColumn)) return false;
        BacklogColumn backlogColumn = (BacklogColumn) o;
        return id == backlogColumn.id &&
                name.equals(backlogColumn.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
