package app.media.opp.partytonight.domain;

public interface Account {
    User user();

    void saveUser(User user, boolean isGoer);
    boolean isAuthorized();

    boolean isAuthorizedAsGoer();
    void logout();
}
