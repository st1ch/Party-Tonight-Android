package app.media.opp.partytonight.domain.usecase;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by arkadii on 10/30/16.
 */
@UserScope
public class GetZipCodeUseCase extends UseCase<String> {

    private SessionRepository repository;
    private LatLng latLng;

    @Inject
    public GetZipCodeUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected Observable<String> getUseCaseObservable() {
        return repository.getPostalAddress(latLng);
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
