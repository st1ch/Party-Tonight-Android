package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by arkadii on 10/30/16.
 */
public class SignUpUseCase extends UseCase<User> {
    private SessionRepository repository;
    private User user;

    @Inject
    public SignUpUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<User> getUseCaseObservable() {
        return repository.signUp(user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
