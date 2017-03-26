package app.media.opp.partytonight.presentation.views;

import java.util.List;

import app.media.opp.partytonight.domain.booking.BookedTable;

public interface IGoerTablesView extends IProgressView {

    void emptyResponse();

    void renderList(List<BookedTable> response);
}
