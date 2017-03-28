package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Review;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerEventReviewUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerReviewView;
import okhttp3.ResponseBody;

@UserScope
public class GoerReviewPresenter extends ProgressPresenter<IGoerReviewView> implements IGoerReviewPresenter {

    private GoerEventReviewUseCase useCase;

    @Inject
    public GoerReviewPresenter(Messages messages, GoerEventReviewUseCase useCase) {
        super(messages);
        this.useCase = useCase;
    }

    @Override
    public void sendReview(Review review) {
        useCase.setReview(review);
        useCase.execute(getSubscriber());
    }

    @NonNull
    private BaseProgressSubscriber<ResponseBody> getSubscriber() {
        return new BaseProgressSubscriber<ResponseBody>(this) {
            @Override
            public void onNext(ResponseBody response) {
                super.onNext(response);
                IGoerReviewView view = getView();

                view.finish();
            }
        };
    }
}
