package app.media.opp.partytonight.data.rest;

import java.util.List;

import app.media.opp.partytonight.data.EventEntity;
import app.media.opp.partytonight.data.FileEntity;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.Revenue;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Arkadiy on 01.06.2016.
 */
public interface PartyTonightApi {


    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @POST("maker/signup")
    Observable<ResponseBody> makerSignUp(@Body UserEntity userEntity);

    @POST("goer/signup")
    Observable<ResponseBody> goerSignUp(@Body UserEntity userEntity);

    @GET("signin")
    Observable<TokenEntity> logIn(@Header("Authorization") String authorization);

    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @POST("maker/event/create")
    Observable<ResponseBody> createEvent(@Header("x-auth-token") String token, @Body EventEntity eventEntity);

    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @GET("maker/event/get")
    Observable<List<EventEntity>> getEvents(@Header("x-auth-token") String token);

    @Multipart
    @POST("maker/event/image")
    Call<FileEntity> uploadFile(@Header("x-auth-token") String token, @Part MultipartBody.Part part);

    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @GET("maker/event/revenue")
    Observable<Revenue> getEventRevenue(@Header("partyName") String partyName, @Header("x-auth-token") String token);
}

