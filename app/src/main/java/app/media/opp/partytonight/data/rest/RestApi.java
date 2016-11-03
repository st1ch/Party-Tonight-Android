package app.media.opp.partytonight.data.rest;

import android.content.Context;
import android.util.Base64;

import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
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

    public Observable<TokenEntity> signUp(UserEntity userEntity) {
        return api.signUp(userEntity).flatMap(response -> {
            if (response.code() == 201) {
                return logIn(userEntity);
            }
            throw new RuntimeException("Sign up is not successful");
        });
    }

    public Observable<TokenEntity> logIn(UserEntity userEntity) {
        String credentials = userEntity.getEmail() + userEntity.getPassword();
        String authorizationHeader = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        return api.logIn(authorizationHeader);
    }
}
