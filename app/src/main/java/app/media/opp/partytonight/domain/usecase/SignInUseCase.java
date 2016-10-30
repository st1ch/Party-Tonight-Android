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
public class SignInUseCase extends UseCase<User> {
    private SessionRepository repository;
    private String email;
    private String password;

    @Inject
    public SignInUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }



    @Override
    protected Observable<User> getUseCaseObservable() {
        return repository.logIn(email, password);
    }

    public void setCredentials(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
