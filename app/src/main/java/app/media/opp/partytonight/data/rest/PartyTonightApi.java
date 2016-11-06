package app.media.opp.partytonight.data.rest;

import java.util.List;

import app.media.opp.partytonight.EventEntity;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.User;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Arkadiy on 01.06.2016.
 */
public interface PartyTonightApi {


    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @POST("maker/signup")
    Observable<ResponseBody> makerSignUp(@Body UserEntity userEntity);

    @GET("signin")
    Observable<TokenEntity> logIn(@Header("Authorization") String authorization);

    @POST("maker/event/create")
    Observable<Object> createEvent(@Body EventEntity eventEntity);


    Observable<List<EventEntity>> getEvents();
}

