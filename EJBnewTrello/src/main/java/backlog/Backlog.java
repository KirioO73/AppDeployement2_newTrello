package backlog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL)
    private List<BacklogColumn> backlogColumns;

    public Backlog() {
        backlogColumns = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BacklogColumn> getBacklogColumns() {
        return backlogColumns;
    }

    public void setBacklogColumns(List<BacklogColumn> backlogColumns) {
        this.backlogColumns = backlogColumns;
    }

    public void addColumn( BacklogColumn column){
        backlogColumns.add(column);
    }
}
