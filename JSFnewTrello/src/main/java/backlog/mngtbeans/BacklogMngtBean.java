package backlog.mngtbeans;

import backlog.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class BacklogMngtBean implements Serializable {

    @EJB
    private BacklogInterface backlogEJB;

    private String name;
    private String agencyName;
    private Employe employe;
    private Agency agency;

    private Backlog backlog;
    private int columnsNb;

    private String newColumnName;
    private String newEntryName;


    public BacklogMngtBean() {
        columnsNb = 0;
        backlog = null;
        employe = null;
        this.name = "";
        this.agencyName = "";
    }

    //Getters / Setters
    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
        if (backlog == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("backlog NULL"));
            this.columnsNb = 0;
        }
        else{
            this.columnsNb = this.backlog.getBacklogColumns().size();
        }
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Agency getAgency() {
        return agency;
    }

    public String getNewColumnName() {
        return newColumnName;
    }

    public void setNewColumnName(String newColumnName) {
        this.newColumnName = newColumnName;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public void setAgency(String agencyName) {
        setAgency(backlogEJB.findAgency(agencyName));
    }

    public int getColumnsNb() {
        return columnsNb;
    }

    public void setColumnsNb(int columnsNb) {
        this.columnsNb = columnsNb;
    }

    public String getNewEntryName() {
        return newEntryName;
    }

    public void setNewEntryName(String newEntryName) {
        this.newEntryName = newEntryName;
    }

    //Fonctionnal
    public void newColumn(){
        if(!newColumnName.equals("")){
            backlog.addColumn( backlogEJB.createColumn(this.newColumnName));
            columnsNb ++;
        }
        newColumnName = "";
    }

    public void addNewEntryToColumn(long columnId){
        if(!newEntryName.equals("")){
            BacklogColumn column = backlogEJB.findColumn(columnId);
            column.addEntry(backlogEJB.createEntry(this.newEntryName));
        }
        newEntryName = "";
    }

    public void connectUser(){
        try {
            Agency agency = backlogEJB.createAgency(agencyName);
            Employe employe = backlogEJB.createUser(name, agency );
            setAgency(agency);
            setEmploye(employe);
            if (agency.getBacklog() == null){
                this.backlog = backlogEJB.createBacklog(new Backlog());
                agency.setBacklog(this.backlog);
            }
            setBacklog(agency.getBacklog());
        }
        catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erreur lors de la connection de l'utilisateur. ", e.getLocalizedMessage()) );
        }
    }


}

