package app.media.opp.partytonight.data.repositories;


import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.media.opp.partytonight.data.EventEntity;
import app.media.opp.partytonight.data.AbstractMapperFactory;
import app.media.opp.partytonight.data.Mapper;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
import app.media.opp.partytonight.data.rest.RestApi;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.presentation.utils.MapUtils;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public class SessionDataRepository implements SessionRepository {

    private final AbstractMapperFactory abstractMapperFactory;
    private final Account account;
    private MapUtils mapUtils;
    private RestApi restApi;

    public SessionDataRepository(RestApi restApi, Account account, AbstractMapperFactory abstractMapperFactory, MapUtils mapUtils) {
        this.restApi = restApi;
        this.abstractMapperFactory = abstractMapperFactory;
        this.account = account;
        this.mapUtils = mapUtils;
    }


    @Override
    public Observable<User> signUp(User user) {
        Mapper<User, UserEntity> userEntityMapper = abstractMapperFactory.getUserEntityMapper();
        return restApi.signUp(userEntityMapper.transform(user)).map(tokenEntity -> saveUser(user, tokenEntity));
    }

    @Override
    public Observable<User> logIn(User user) {
        Mapper<User, UserEntity> userEntityMapper = abstractMapperFactory.getUserEntityMapper();
        return restApi.logIn(userEntityMapper.transform(user)).map(tokenEntity -> saveUser(user, tokenEntity));
    }

    @Override
    public Observable<Event> createEvent(Event event) {
        Mapper<Event, EventEntity> eventMapper = abstractMapperFactory.getEventEntityMapper();
        return restApi.createEvent(eventMapper.transform(event)).map(o -> event);

    }

    @Override
    public Observable<List<Event>> getEvents() {
        Mapper<EventEntity, Event> eventMapper = abstractMapperFactory.getEventMapper();
        return restApi.getEvents().map(eventEntities -> {
            List<Event> events = new ArrayList<>();
            for (EventEntity eventEntity : eventEntities) {
                events.add(eventMapper.transform(eventEntity));
            }
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return (int) (o1.getTime() - o2.getTime());
                }
            });
            return events;
        });
    }

    @Override
    public Observable<String> getPostalAddress(LatLng latLng) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        String postalCode = mapUtils.getPostalCode(latLng.latitude, latLng.longitude);
                        Log.e("repository", "postal code : " + postalCode);
                        subscriber.onNext(postalCode);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private User saveUser(User user, TokenEntity tokenEntity) {
        user.setToken(tokenEntity.getToken());
        account.saveUser(user);
        return user;
    }
}
