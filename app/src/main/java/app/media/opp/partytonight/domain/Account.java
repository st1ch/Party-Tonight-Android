package app.media.opp.partytonight.domain;


/**
 * Created by sebastian on 16.06.16.
 */
public interface Account {
    User user();
    void saveUser(User user);
    boolean isAuthorized();
    void logout();
}
