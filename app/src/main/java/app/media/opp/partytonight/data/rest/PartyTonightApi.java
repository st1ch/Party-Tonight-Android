package app.media.opp.partytonight.data.rest;

import app.media.opp.partytonight.domain.User;
import rx.Observable;

/**
 * Created by Arkadiy on 01.06.2016.
 */
public interface PartyTonightApi {

    Observable<User> logIn(String email, String password);

    Observable<User> signUp(String name, String email, String phone, String password);
}

