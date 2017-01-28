package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by arkadii on 10/30/16.
 */
@UserScope
public class GetRevenueUseCase extends UseCase<Revenue> {
    private SessionRepository repository;
    private Event event;

    @Inject
    public GetRevenueUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<Revenue> getUseCaseObservable() {
        return repository.getEventRevenue(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
