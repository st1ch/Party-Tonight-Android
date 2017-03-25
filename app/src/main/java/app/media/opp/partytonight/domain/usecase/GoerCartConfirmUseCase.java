package app.media.opp.partytonight.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import okhttp3.ResponseBody;
import rx.Observable;

public class GoerCartConfirmUseCase extends UseCase<ResponseBody> {

    private SessionRepository repository;

    private List<Transaction> transactions;
    private List<Booking> bookings;

    @Inject
    public GoerCartConfirmUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    protected Observable<ResponseBody> getUseCaseObservable() {
        return repository.confirmPayments(bookings, transactions);
    }
}
