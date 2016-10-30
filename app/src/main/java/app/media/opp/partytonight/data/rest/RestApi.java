package app.media.opp.partytonight.data.rest;

import android.content.Context;

import app.media.opp.partytonight.domain.User;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public class RestApi {

    private final PartyTonightApi api;
    private Context c;

    public RestApi(Context c, PartyTonightApi api) {
        this.c = c;
        this.api = api;
    }

    public Observable<User> logIn(String email, String password) {
        return api.logIn(email, password);
    }

    public Observable<User> signUp(String name, String email, String phone, String password) {
        return api.signUp(name, email, phone, password);
    }
}
