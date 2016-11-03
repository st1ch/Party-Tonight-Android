package app.media.opp.partytonight.domain;


import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public interface SessionRepository {


    Observable<User> signUp(User user);

    Observable<User> logIn(User user);
}
