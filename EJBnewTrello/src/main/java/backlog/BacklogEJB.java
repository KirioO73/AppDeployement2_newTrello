package backlog;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless @Remote
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BacklogEJB implements BacklogInterface{

    @PersistenceContext
    private EntityManager em;

    public BacklogEJB() {
    }

    //Backlog
    @Override
    public Backlog createBacklog(Backlog backlog) {
        em.persist(backlog);
        return backlog;
    }

    @Override
    public Backlog findBacklog(long backlogId) {
        return em.find(Backlog.class, backlogId);
    }

    @Override
    public void deleteBacklog(long backlogId) {
        Backlog backlog = findBacklog(backlogId);
        em.remove(backlog);
    }

    //Agency
    @Override
    public Agency createAgency(Agency agency) {
        em.persist(agency);
        return agency;
    }

    @Override
    public Agency createAgency(String agencyName, Backlog backlog) {
        Agency agency = findAgency(agencyName);
        if (agency == null)
        {
            agency = new Agency( backlog, agencyName);
            return createAgency(agency);
        }
        else
            return agency;
    }

    @Override
    public Agency createAgency(String agencyName) {
        Backlog backlog = createBacklog(new Backlog());
        return createAgency(agencyName, backlog);
    }

    @Override
    public Agency findAgency(String agencyName) {
        return em.find(Agency.class, agencyName);
    }

    @Override
    public void deleteAgency(String agencyName) {
        Agency agency = findAgency(agencyName);
        em.remove(agency);
    }

    //Employe
    @Override
    public Employe createUser(Employe employe) {
        em.persist(employe);
        return employe;
    }

    @Override
    public Employe createUser(String userName) {
        Employe employe = new Employe(userName);
        return createUser(employe);
    }

    @Override
    public Employe createUser(String userName, Agency agency) {
        Employe employe = new Employe(userName, agency);
        return createUser(employe);
    }

    @Override
    public Employe changeUserAgency(long userId, Agency newAgency) {
        Employe employe = em.find(Employe.class, userId);
        employe.setAgency(newAgency);
        return employe;
    }

    @Override
    public Employe findUser(long userId) {
        return em.find(Employe.class, userId);
    }

    @Override
    public Employe findUserByNameAndAgency(String userName, String agencyName) {
        return null;
    }

    @Override
    public void deleteUser(long userId) {
        Employe employe = findUser( userId);
        em.remove(employe);
    }

    //Comment
    @Override
    public Comment createComment(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public Comment createComment(String content, Employe author) {
        Comment comment = new Comment(content, author);
        return createComment(comment);
    }

    @Override
    public Comment changeComment(long commentId, String newContent) {
        Comment comment = findComment(commentId);
        comment.setContent(newContent);
        return comment;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = findComment(commentId);
        em.remove(comment);
    }

    @Override
    public Comment findComment(long commentId) {
        return em.find(Comment.class, commentId);
    }

    //Entry
    @Override
    public Entry createEntry(Entry entry) {
        em.persist(entry);
        return entry;
    }

    @Override
    public Entry createEntry(String entryName) {
        Entry entry = new Entry(entryName);
        return createEntry(entry);
    }

    @Override
    public Entry createEntry(String entryName, int entryPriority, int entryEstimation, String entryDescription) {
        Entry entry = new Entry(entryName, entryPriority, entryEstimation, entryDescription);
        return createEntry(entry);
    }

    @Override
    public Entry changeEntryName(long entryId, String newEntryName) {
        Entry entry = findEntry(entryId);
        entry.setName(newEntryName);
        return entry;
    }

    @Override
    public Entry changeEntryPriority(long entryId, int newEntryPriority) {
        Entry entry = findEntry(entryId);
        entry.setPriority(newEntryPriority);
        return entry;
    }

    @Override
    public Entry changeEntryEstimation(long entryId, int newEntryEstimation) {
        Entry entry = findEntry(entryId);
        entry.setEstimation(newEntryEstimation);
        return entry;
    }

    @Override
    public Entry changeEntryDescription(long entryId, String newEntryDescription) {
        Entry entry = findEntry(entryId);
        entry.setDescription(newEntryDescription);
        return entry;
    }

    @Override
    public Entry findEntry(long entryId) {
        return em.find(Entry.class, entryId);
    }

    @Override
    public void validateEntryComments(Entry entry) {
        //TODO : Usefull ? Check utility
    }

    @Override
    public void deleteEntry(long entryId) {
        Entry entry = findEntry(entryId);
        em.remove(entry);
    }

    //BacklogColumn
    @Override
    public BacklogColumn createColumn(BacklogColumn backlogColumn) {
        em.persist(backlogColumn);
        return backlogColumn;
    }

    @Override
    public BacklogColumn createColumn(String columnName) {
        BacklogColumn backlogColumn = new BacklogColumn(columnName);
        return createColumn(backlogColumn);
    }

    @Override
    public BacklogColumn createColumn(String columnName, BacklogColumn backlogColumnPrec, BacklogColumn backlogColumnNext) {
        BacklogColumn backlogColumn = new BacklogColumn(columnName, backlogColumnPrec, backlogColumnNext);
        return createColumn(backlogColumn);
    }

    @Override
    public BacklogColumn changeColumn(long columnId, String newColumnName, BacklogColumn newBacklogColumnPrec, BacklogColumn newBacklogColumnNext) {
        BacklogColumn backlogColumn = findColumn(columnId);
        backlogColumn.setName(newColumnName);
        backlogColumn.setPrec(newBacklogColumnPrec);
        backlogColumn.setNext(newBacklogColumnNext);
        return backlogColumn;
    }

    @Override
    public BacklogColumn findColumn(long columnId) {
        return em.find(BacklogColumn.class, columnId);
    }

    @Override
    public void validateColumnEntries(BacklogColumn backlogColumn) {
        //TODO : Usefull ??  Check utility
    }

    @Override
    public void deleteColumn(long columnId) {
        BacklogColumn backlogColumn = findColumn(columnId);
        em.remove(backlogColumn);
    }
}
