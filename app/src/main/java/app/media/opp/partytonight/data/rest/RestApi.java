package app.media.opp.partytonight.data.rest;

import android.content.Context;
import android.util.Base64;

import java.io.File;
import java.util.Collections;
import java.util.List;

import app.media.opp.partytonight.data.EventEntity;
import app.media.opp.partytonight.data.FileEntity;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.presentation.utils.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public class RestApi {

    private final PartyTonightApi api;
    private Account account;
    private Context c;


    public RestApi(Context c, PartyTonightApi api, Account account) {
        this.c = c;
        this.api = api;
        this.account = account;
    }

    public Observable<TokenEntity> signUp(UserEntity userEntity) {
        return api.makerSignUp(userEntity).flatMap(response -> logIn(userEntity));
    }

    public Observable<TokenEntity> logIn(UserEntity userEntity) {
        String credentials = userEntity.getEmail() + ":" + userEntity.getPassword();
        String authorizationHeader = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        return api.logIn(authorizationHeader);
    }


    public Observable<EventEntity> createEvent(EventEntity event) {
        return api.createEvent(account.user().getToken(), event).map(responseBody -> event);
    }

    public Observable<List<EventEntity>> getEvents() {
        return api.getEvents(account.user().getToken());
    }

    public Call<FileEntity> uploadFile(String localFile) {
        File file = new File(localFile);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return api.uploadFile(account.user().getToken(), part);
    }

    public Observable<Revenue> getEventRevenue(String partyName, String token) {
        return api.getEventRevenue(partyName, token);
    }
}
