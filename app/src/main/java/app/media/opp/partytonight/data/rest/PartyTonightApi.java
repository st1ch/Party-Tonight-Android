package app.media.opp.partytonight.data.rest;

import java.util.List;

import app.media.opp.partytonight.data.EventEntity;
import app.media.opp.partytonight.data.FileEntity;
import app.media.opp.partytonight.data.Statement;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.domain.Transaction;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @POST("dancer/signup")
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
    Observable<Revenue> getEventRevenue(@Header("party_name") String partyName, @Header("x-auth-token") String token);

    @Headers({"Content-Type: application/json", "Content-Length: 0"})
    @GET("dancer/event/get")
    Observable<List<EventEntity>> getEventsByZipCode(@Header("zip_code") String zipCode, @Header("x-auth-token") String token);

    @GET("maker/event/total")
    Observable<Statement> getStatement(@Header("x-auth-token") String token, @Header("party_name") String partyName);

    @POST("dancer/event/get_transactions")
    Observable<List<Transaction>> getTransactions(@Header("x-auth-token") String token, @Body List<Booking> order);

    @FormUrlEncoded
    @POST("dancer/event/confirm_payments")
    Observable<ResponseBody> confirmPayments(@Header("x-auth-token") String token, @Field("bookings[]") Booking[] bookings, @Field("transactions[]") Transaction[] transactions);
}

