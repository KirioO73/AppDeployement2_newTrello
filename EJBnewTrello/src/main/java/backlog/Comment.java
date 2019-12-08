package backlog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comment implements Serializable {

    @Id @GeneratedValue
    private long id;

    private String content;

    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @ManyToOne
    private Employe creator;

    //Constructors
    public Comment() {
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Comment(String content, Employe creator) {
        this.creationDate = Calendar.getInstance().getTime();
        this.content = content;
        this.creator = creator;
    }


    //Getters / Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date dateOfCreation) {
        this.creationDate = dateOfCreation;
    }

    public Employe getCreator() {
        return creator;
    }

    public void setCreator(Employe creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                content.equals(comment.content) &&
                creationDate.equals(comment.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, creationDate);
    }
}
