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
    private String name;
    private String email;
    private String phone;
    private String password;

    @Inject
    public SignUpUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }



    @Override
    protected Observable<User> getUseCaseObservable() {
        return repository.signUp(name, email, phone, password);
    }

    public void setCredentials(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
