package app.media.opp.partytonight.domain.usecase;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by arkadii on 10/30/16.
 */
@Singleton
public class GetEventsUseCase extends UseCase<List<Event>> {
    private SessionRepository repository;
    @Nullable
    private List<Event> events;

    @Inject
    public GetEventsUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Event>> getUseCaseObservable() {
        return repository.getEvents().doOnNext(events -> this.events = events);
    }

    @Nullable
    public List<Event> getEvents() {
        return events;
    }

    public void addToCache(Event response) {
        if (events != null) {
            int i = 0;
            while (i < events.size() && events.get(i).getTime() < response.getTime()) {
                i++;
            }
            events.add(i, response);
        }
    }
}
