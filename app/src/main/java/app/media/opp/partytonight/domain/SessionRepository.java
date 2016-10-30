package app.media.opp.partytonight.domain;


import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public interface SessionRepository {

    Observable<User> logIn(String email, String password);

    Observable<User> signUp(String name, String email, String phone, String password);
}
