package app.media.opp.partytonight.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

public class GoerCartUseCase extends UseCase<List<Transaction>> {

    private SessionRepository repository;
    private List<Booking> order;

    @Inject
    public GoerCartUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    public void setOrder(List<Booking> order) {
        this.order = order;
    }

    @Override
    protected Observable<List<Transaction>> getUseCaseObservable() {
        return repository.getTransactions(order);
    }
}
