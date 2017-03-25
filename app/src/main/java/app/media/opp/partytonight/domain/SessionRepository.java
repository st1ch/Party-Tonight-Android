package app.media.opp.partytonight.domain;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import app.media.opp.partytonight.data.Statement;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public interface SessionRepository {


    Observable<User> signUp(User user);

    Observable<User> goerSignUp(User user);

    Observable<User> logIn(User user);

    Observable<User> goerLogIn(User user);

    Observable<Event> createEvent(Event event);

    Observable<List<Event>> getEvents();

    Observable<String> getPostalAddress(LatLng latLng);

    Observable<Statement> getStatement(String partyName);

    Observable<Revenue> getEventRevenue(Event event);

    Observable<List<Event>> getEventsByZipCode(String zipCode);

    Observable<List<Transaction>> getTransactions(List<Booking> order);

    Observable<ResponseBody> confirmPayments(List<Booking> bookings, List<Transaction> transactions);
}
