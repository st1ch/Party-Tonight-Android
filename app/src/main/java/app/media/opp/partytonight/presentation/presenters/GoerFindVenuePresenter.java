package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerFindVenueUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerFindVenueView;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public class GoerFindVenuePresenter extends ProgressPresenter<IGoerFindVenueView> implements IGoerFindVenuePresenter {

    private GoerFindVenueUseCase findVenueUseCase;

    @Inject
    public GoerFindVenuePresenter(Messages messages, GoerFindVenueUseCase findVenueUseCase) {
        super(messages);
        this.findVenueUseCase = findVenueUseCase;
    }

    @Override
    public void onCreate(IGoerFindVenueView view) {
        super.onCreate(view);
    }

    @Override
    public void onFindButtonClick(String zipCode) {
        findVenueUseCase.setZipCode(zipCode);
        findVenueUseCase.execute(getSubscriber());
    }

    @NonNull
    private BaseProgressSubscriber<List<Event>> getSubscriber() {
        return new BaseProgressSubscriber<List<Event>>(this) {
            @Override
            public void onNext(List<Event> response) {
                super.onNext(response);
                IGoerFindVenueView view = getView();

                if (view != null) {
                    view.renderList(response);
                }
            }
        };
    }
}
