package app.media.opp.partytonight.presentation.views;

import app.media.opp.partytonight.data.Statement;

public interface IStatementTotal extends IProgressView {

    void showStatement(Statement statement);

    String getPartyName();
}
