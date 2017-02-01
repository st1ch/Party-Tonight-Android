package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.data.Statement;
import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

@UserScope
public class GetStatementUseCase extends UseCase<Statement> {
    private SessionRepository repository;
    private String partyName;

    @Inject
    public GetStatementUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<Statement> getUseCaseObservable() {
        return repository.getStatement(partyName);
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}