package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerCartUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerCartView;

@UserScope
public class GoerCartPresenter extends ProgressPresenter<IGoerCartView> implements IGoerCartPresenter {
    private GoerCartUseCase useCase;

    @Inject
    public GoerCartPresenter(Messages messages, GoerCartUseCase useCase) {
        super(messages);
        this.useCase = useCase;
    }

    @Override
    public void onCreate(IGoerCartView view) {
        super.onCreate(view);
        if (useCase.isWorking()) {
            useCase.execute(getSubscriber());
        }
    }

    @Override
    public void onOrderSent(List<Booking> order) {
        useCase.setOrder(order);
        useCase.execute(getSubscriber());
    }

    @Override
    public void onRelease() {
        useCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<List<Transaction>> getSubscriber() {
        return new BaseProgressSubscriber<List<Transaction>>(this) {

            @Override
            public void onNext(List<Transaction> response) {
                super.onNext(response);
                IGoerCartView view = getView();

                // handle transactions here
                Log.i("response is ", " " + response.size());
            }
        };
    }
}
