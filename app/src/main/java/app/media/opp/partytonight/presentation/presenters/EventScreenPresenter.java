package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GetRevenueUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IEventScreenView;

/**
 * Created by arkadii on 12/4/16.
 */
@UserScope
public class EventScreenPresenter extends ProgressPresenter<IEventScreenView> {
    private GetRevenueUseCase getRevenueUseCase;

    @Inject
    public EventScreenPresenter(Messages messages, GetRevenueUseCase getRevenueUseCase) {
        super(messages);
        this.getRevenueUseCase = getRevenueUseCase;
    }

    @Override
    public void onCreate(IEventScreenView view) {
        super.onCreate(view);
        if (view.getRevenue() != null) {
            view.renderRevenue(view.getRevenue());
        } else {
            getRevenueUseCase.setEvent(view.getEvent());
            getRevenueUseCase.execute(getSubscriber());
        }
    }

    @Override
    public void onRelease() {
        getRevenueUseCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<Revenue> getSubscriber() {
        return new BaseProgressSubscriber<Revenue>(this) {
            @Override
            public void onNext(Revenue response) {
                super.onNext(response);
                IEventScreenView screenView = getView();
                if (screenView != null) {
                    screenView.renderRevenue(response);
                }
            }
        };
    }
}
