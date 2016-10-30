package app.media.opp.partytonight.presentation.presenters;


import android.support.annotation.Nullable;

import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IView;

/**
 * Created by sebastian on 14.06.16.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {
    @Nullable
    private V view;
    private Messages messages;

    public BasePresenter(Messages messages) {
        this.messages = messages;
    }

    @Nullable
    public V getView() {
        return view;
    }

    @Override
    public void onCreate(V view) {
        this.view = view;
    }

    @Override
    public void onRelease() {
        view = null;
    }

    public Messages getMessages() {
        return messages;
    }

    protected void showMessage(int messageId) {
        V view = getView();
        if (view != null) {
            view.showMessage(messages.convertError(messageId));
        }
    }

    protected void showMessage(String message) {
        V view = getView();
        if (view != null) {
            view.showMessage(message);
        }
    }
}
