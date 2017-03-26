package app.media.opp.partytonight.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import rx.Observable;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/26/17
 */

public class GoerCartValidateUseCase extends UseCase<List<Booking>> {

    private SessionRepository repository;

    private List<Booking> bookings;

    @Inject
    public GoerCartValidateUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    protected Observable<List<Booking>> getUseCaseObservable() {
        return repository.validateBookings(bookings);
    }
}
