package backlog;

public interface BacklogInterface {

    //Backlog
    Backlog createBacklog(Backlog backlog);

    Backlog findBacklog( long backlogId);

    void deleteBacklog( long backlogId);

    //Agency
    Agency createAgency(Agency agency);

    Agency createAgency(String agencyName);

    Agency createAgency(String agencyName, Backlog backlog);

    Agency findAgency (String agencyName);

    void deleteAgency (String agencyName);

    //Employe
    Employe createUser (Employe employe);

    Employe createUser (String userName);

    Employe createUser (String userName, Agency agency);

    Employe changeUserAgency (long userId, Agency newAgency );

    Employe findUser (long userId);

    Employe findUserByNameAndAgency(String userName, String agencyName);

    void deleteUser (long userId);

    //Comment
    Comment createComment ( Comment comment);

    Comment createComment ( String content, Employe author);

    Comment changeComment ( long commentId, String newContent);

    Comment findComment (long commentId);

    void deleteComment (long commentId);

    //Entry
    Entry createEntry (Entry entry);

    Entry createEntry (String entryName);

    Entry createEntry (String entryName,  int entryPriority, int entryEstimation, String entryDescription);

    Entry changeEntryName (long entryId, String newEntryName);

    Entry changeEntryPriority (long entryId, int newEntryPriority);

    Entry changeEntryEstimation (long entryId, int newEntryEstimation);

    Entry changeEntryDescription ( long entryId, String newEntryDescription);

    Entry findEntry (long entryId);

    void validateEntryComments ( Entry entry); //??

    void deleteEntry (long entryId);

    //BacklogColumn

    BacklogColumn createColumn (BacklogColumn backlogColumn);

    BacklogColumn createColumn (String columnName );

    BacklogColumn createColumn (String columnName, BacklogColumn backlogColumnPrec, BacklogColumn backlogColumnNext);

    BacklogColumn changeColumn (long columnId, String newColumnName, BacklogColumn newBacklogColumnPrec, BacklogColumn newBacklogColumnNext);

    BacklogColumn findColumn (long columnId);

    void validateColumnEntries (BacklogColumn backlogColumn); //??

    void deleteColumn ( long columnId);

}
