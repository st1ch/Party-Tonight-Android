package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.AddEventUseCase;
import app.media.opp.partytonight.domain.usecase.GetEventsUseCase;
import app.media.opp.partytonight.domain.usecase.GetZipCodeUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by arkadii on 11/6/16.
 */
@UserScope
public class AddEventPresenter extends ProgressPresenter<IAddEventView> implements IAddEventPresenter {

    private AddEventUseCase addEventUseCase;
    private GetZipCodeUseCase getZipCodeUseCase;

    @Inject
    public AddEventPresenter(Messages messages, AddEventUseCase addEventUseCase, GetZipCodeUseCase getZipCodeUseCase) {
        super(messages);
        this.addEventUseCase = addEventUseCase;
        this.getZipCodeUseCase = getZipCodeUseCase;
    }

    @Override
    public void onCreate(IAddEventView view) {
        super.onCreate(view);
        if (addEventUseCase.isWorking()) {
            addEventUseCase.execute(getSubscriber());
        }
        if (getZipCodeUseCase.isWorking()) {
            getZipCodeUseCase.execute(getZipCodeSubscriber());
        }
    }

    @Override
    public void onAddButtonClick(Event event) {
        addEventUseCase.setEvent(event);
        addEventUseCase.execute(getSubscriber());
    }

    @Override
    public void onLocationDefined(LatLng latLng) {
        getZipCodeUseCase.setLatLng(latLng);
        getZipCodeUseCase.execute(getZipCodeSubscriber());
    }

    @NonNull
    private BaseProgressSubscriber<String> getZipCodeSubscriber() {
        return new BaseProgressSubscriber<String>(this) {
            @Override
            public void onNext(String response) {
                super.onNext(response);
                IAddEventView view = getView();
                if (view != null) {
                    view.saveZipCode(response);
                }
            }
        };
    }

    @Override
    public void onRelease() {
        addEventUseCase.unsubscribe();
        getZipCodeUseCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<Event> getSubscriber() {
        return new BaseProgressSubscriber<Event>(this) {
            @Override
            public void onNext(Event response) {
                super.onNext(response);
                IAddEventView view = getView();
                if (view != null) {
                    view.navigateBack();
                }
            }
        };
    }
}
