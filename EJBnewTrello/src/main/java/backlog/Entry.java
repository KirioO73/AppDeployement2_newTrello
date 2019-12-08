package backlog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Entry implements Serializable {

    @Id @GeneratedValue
    private long id;

    private String name;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private int priority;
    private int estimation;
    private String description;
    @OneToMany(cascade= CascadeType.ALL)
    private List<Comment> comments;

    //Constructors
    public Entry() {
        this.creationDate = Calendar.getInstance().getTime();
        this.comments = new ArrayList<Comment>();
    }

    public Entry(String name) {
        this.name = name;
        this.creationDate = Calendar.getInstance().getTime();
        this.comments = new ArrayList<Comment>();
        this.priority = 0;
        this.estimation = 0;
        this.description = "";
    }

    public Entry(String name, int priority, int estimation, String description) {
        this.name = name;
        this.priority = priority;
        this.estimation = estimation;
        this.description = description;
        this.creationDate = Calendar.getInstance().getTime();
        this.comments = new ArrayList<Comment>();
    }

    //Methods
    public void addComment(Comment comment){
        if (!comments.contains(comment))
            comments.add(0 , comment);
    }

    public void deleteComment( Comment comment){
        comments.remove(comment);
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return getId() == entry.getId() &&
                getName().equals(entry.getName()) &&
                getCreationDate().equals(entry.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreationDate(), getPriority(), getEstimation(), getDescription(), getComments());
    }


}
