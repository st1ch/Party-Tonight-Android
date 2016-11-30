package app.media.opp.partytonight.domain;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public interface SessionRepository {


    Observable<User> signUp(User user);

    Observable<User> logIn(User user);

    Observable<Event> createEvent(Event event);

    Observable<List<Event>> getEvents();

    Observable<String> getPostalAddress(LatLng latLng);
}
