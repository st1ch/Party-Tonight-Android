package app.media.opp.partytonight.presentation.presenters;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.AddEventUseCase;
import app.media.opp.partytonight.domain.usecase.GetEventsUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by arkadii on 11/6/16.
 */
@Singleton
public class AddEventPresenter extends ProgressPresenter<IAddEventView> implements IAddEventPresenter {

    private AddEventUseCase addEventUseCase;
    private GetEventsUseCase getEventsUseCase;

    @Inject
    public AddEventPresenter(Messages messages, AddEventUseCase addEventUseCase, GetEventsUseCase getEventsUseCase) {
        super(messages);
        this.addEventUseCase = addEventUseCase;
        this.getEventsUseCase = getEventsUseCase;
    }

    @Override
    public void onAddButtonClick(Event event) {
        addEventUseCase.setEvent(event);
        addEventUseCase.execute(new BaseProgressSubscriber<Event>(this) {
            @Override
            public void onNext(Event response) {
                super.onNext(response);
                IAddEventView view = getView();
                getEventsUseCase.addToCache(response);
                if (view != null) {
                    view.navigateBack();
                }
            }
        });
    }
}
