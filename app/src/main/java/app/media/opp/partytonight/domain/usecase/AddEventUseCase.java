package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by arkadii on 10/30/16.
 */
public class AddEventUseCase extends UseCase<Event> {
    private SessionRepository repository;
    private Event event;

    @Inject
    public AddEventUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<Event> getUseCaseObservable() {
        return repository.createEvent(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
