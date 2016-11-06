package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GetEventsUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import app.media.opp.partytonight.presentation.views.IEventListView;

/**
 * Created by arkadii on 11/6/16.
 */
@UserScope
public class PromoterEventsPresenter extends ProgressPresenter<IEventListView> implements IEventListPresenter {

    private GetEventsUseCase getEventsUseCase;

    @Inject
    public PromoterEventsPresenter(Messages messages, GetEventsUseCase getEventsUseCase) {
        super(messages);
        this.getEventsUseCase = getEventsUseCase;
    }

    @Override
    public void onCreate(IEventListView view) {
        super.onCreate(view);
        getEventsUseCase.execute(getSubscriber());
    }


    @Override
    public void onRelease() {
        getEventsUseCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<List<Event>> getSubscriber() {
        return new BaseProgressSubscriber<List<Event>>(this) {
            @Override
            public void onNext(List<Event> response) {
                super.onNext(response);
                IEventListView view = getView();
                if (view != null) {
                    view.renderList(response);
                }
            }
        };
    }
}
