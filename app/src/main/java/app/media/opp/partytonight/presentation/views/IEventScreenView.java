package app.media.opp.partytonight.presentation.views;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Revenue;

/**
 * Created by arkadii on 12/4/16.
 */

public interface IEventScreenView extends IProgressView {
    void renderRevenue(Revenue revenue);
    Event getEvent();
    Revenue getRevenue();
}
