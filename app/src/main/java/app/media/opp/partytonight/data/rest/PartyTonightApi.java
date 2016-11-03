package app.media.opp.partytonight.data.rest;

import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.User;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Arkadiy on 01.06.2016.
 */
public interface PartyTonightApi {


    @POST("maker/signup")
    Observable<Response> signUp(@Body UserEntity userEntity);

    @GET("signin")
    Observable<TokenEntity> logIn(@Header("Authorization") String authorization);
}

