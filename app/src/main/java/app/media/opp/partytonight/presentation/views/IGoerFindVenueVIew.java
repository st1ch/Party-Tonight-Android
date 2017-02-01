package app.media.opp.partytonight.presentation.views;

import java.util.List;

import app.media.opp.partytonight.domain.Event;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public interface IGoerFindVenueVIew extends IProgressView {

    void renderList(List<Event> response);
}
