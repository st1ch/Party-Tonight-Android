package app.media.opp.partytonight.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

@UserScope
public class GoerFindVenueUseCase extends UseCase<List<Event>> {

    private SessionRepository repository;
    private String zipCode;

    @Inject
    public GoerFindVenueUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Event>> getUseCaseObservable() {
        return repository.getEventsByZipCode(zipCode);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
