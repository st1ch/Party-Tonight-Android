package app.media.opp.partytonight.presentation.presenters;

import android.util.Log;

import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by sebastian on 08.07.16.
 */
public class ProgressPresenter<V extends IProgressView> extends BasePresenter<V> implements
        BaseProgressSubscriber.ProgressSubscriberListener {


    public ProgressPresenter(Messages messages) {
        super(messages);
    }

    @Override
    public void onError(Throwable t) {
        V view = getView();
        if (view != null) {
            view.hideProgress();
            String error = getMessages().getError(t);
            view.showMessage(error);
        }
    }

    @Override
    public void onCompleted() {
        V view = getView();
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onStartLoading() {
        Log.e("progress", "onStartLoading");
        V view = getView();
        if (view != null) {
            view.showProgress();
        }
    }
}
