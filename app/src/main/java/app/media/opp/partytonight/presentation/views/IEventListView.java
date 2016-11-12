package app.media.opp.partytonight.presentation.views;

import java.util.List;

import app.media.opp.partytonight.domain.Event;

/**
 * Created by arkadii on 11/6/16.
 */
public interface IEventListView extends IProgressView {

    void renderList(List<Event> data);
}
