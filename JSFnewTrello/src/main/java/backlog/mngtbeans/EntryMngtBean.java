package backlog.mngtbeans;

import backlog.BacklogInterface;
import backlog.Comment;
import backlog.Employe;
import backlog.Entry;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class EntryMngtBean implements Serializable {

    @EJB
    private BacklogInterface backlogEJB;

    @ManagedProperty("#{param.employe}")
    private Employe employe;

    @ManagedProperty("#{param.entry}")
    private
    Entry entry;

    private String newCommentText;

    public EntryMngtBean() {
        newCommentText = "";
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getNewCommentText() {
        return newCommentText;
    }

    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    public void addComments(){
        Comment comment = backlogEJB.createComment(newCommentText, employe);
        entry.addComment(comment);
        newCommentText = "";
    }

    public void deleteEntry(){
        backlogEJB.deleteEntry(this.entry.getId());
    }


}
