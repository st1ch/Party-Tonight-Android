package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

public class GoerSignInUseCase extends UseCase<User> {
    private SessionRepository repository;
    private User user;

    @Inject
    public GoerSignInUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<User> getUseCaseObservable() {
        return repository.goerLogIn(user);
    }

    public void setCredentials(User user) {
        this.user = user;
    }
}
