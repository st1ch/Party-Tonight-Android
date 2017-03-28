package app.media.opp.partytonight.domain.usecase;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Review;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import okhttp3.ResponseBody;
import rx.Observable;

public class GoerEventReviewUseCase extends UseCase<ResponseBody> {

    private SessionRepository repository;
    private Review review;

    @Inject
    public GoerEventReviewUseCase(SubscribeOn subscribeOn, ObserveOn observeOn, SessionRepository repository) {
        super(subscribeOn, observeOn);
        this.repository = repository;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    protected Observable<ResponseBody> getUseCaseObservable() {
        return repository.postReview(review);
    }
}
