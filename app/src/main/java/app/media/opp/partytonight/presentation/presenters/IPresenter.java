package app.media.opp.partytonight.presentation.presenters;


import app.media.opp.partytonight.presentation.views.IView;

/**
 * Created by sebastian on 07.07.16.
 */
public interface IPresenter<V extends IView> {
    void onCreate(V view);
    void onRelease();
}
