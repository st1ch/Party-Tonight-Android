package app.media.opp.partytonight.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.booking.BookedTable;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

public class GoerGetTablesUseCase extends UseCase<List<BookedTable>> {

    private SessionRepository repository;
    private int idEvent;

    @Inject
    public GoerGetTablesUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }


    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    protected Observable<List<BookedTable>> getUseCaseObservable() {
        return repository.getFreeTables(idEvent);
    }
}