package app.media.opp.partytonight.data.rest;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import app.media.opp.partytonight.data.EventEntity;
import app.media.opp.partytonight.data.FileEntity;
import app.media.opp.partytonight.data.Statement;
import app.media.opp.partytonight.data.Ticket;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.domain.Review;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.booking.BookedTable;
import app.media.opp.partytonight.domain.booking.Booking;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

public class RestApi {

    private final PartyTonightApi api;
    private Account account;
    private Context c;


    public RestApi(Context c, PartyTonightApi api, Account account) {
        this.c = c;
        this.api = api;
        this.account = account;
    }

    public Observable<ResponseBody> signUp(UserEntity userEntity) {
        return api.makerSignUp(userEntity);
    }

    public Observable<ResponseBody> goerSignUp(UserEntity userEntity) {
        return api.goerSignUp(userEntity);
    }


    public Observable<TokenEntity> logIn(UserEntity userEntity) {
        String credentials = userEntity.getEmail() + ":" + userEntity.getPassword();
        String authorizationHeader = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        return api.logIn(authorizationHeader);
    }


    public Observable<EventEntity> createEvent(EventEntity event) {
        for (Ticket t : event.getTickets()) {
            t.setAvailable(event.getClubCapacity());
        }
        
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

    public Observable<List<EventEntity>> getEventsByZipCode(String zipCode) {
        return api.getEventsByZipCode(zipCode, account.user().getToken());
    }

    public Observable<Statement> getStatement(String partyName) {
        return api.getStatement(account.user().getToken(), partyName);
    }

    public Observable<List<Booking>> validateBookings(List<Booking> order) {
        return api.validateBookings(account.user().getToken(), order);
    }

    public Observable<List<Transaction>> getTransactions(List<Booking> order) {
        return api.getTransactions(account.user().getToken(), order);
    }

    public Observable<ResponseBody> confirmPayments(List<Booking> bookings, List<Transaction> transactions) {
        Gson gson = new Gson();

        String bookingsJson = gson.toJson(bookings.toArray(new Booking[bookings.size()]));
        String transactionsJson = gson.toJson(transactions.toArray(new Transaction[transactions.size()]));

        return api.confirmPayments(account.user().getToken(), bookingsJson, transactionsJson);
    }

    public Observable<List<BookedTable>> getFreeTables(int idEvent) {
        return api.getFreeTables(account.user().getToken(), idEvent);
    }

    public Observable<ResponseBody> postReview(Review review) {
        return api.postReview(account.user().getToken(), review);
    }
}
