package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.media.opp.partytonight.data.Statement;
import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GetStatementUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IStatementTotal;

@UserScope
public class StatementPresenter extends ProgressPresenter<IStatementTotal> {
    private GetStatementUseCase getStatementUseCase;

    @Inject
    public StatementPresenter(Messages messages, GetStatementUseCase getStatementUseCase) {
        super(messages);
        this.getStatementUseCase = getStatementUseCase;
    }

    @Override
    public void onRelease() {
        getStatementUseCase.unsubscribe();
        super.onRelease();
    }

    @Override
    public void onCreate(IStatementTotal view) {
        super.onCreate(view);

        getStatementUseCase.setPartyName(view.getPartyName());
        getStatementUseCase.execute(getSubscriber());
    }

    @NonNull
    private BaseProgressSubscriber<Statement> getSubscriber() {
        return new BaseProgressSubscriber<Statement>(this) {
            @Override
            public void onNext(Statement response) {
                super.onNext(response);

                IStatementTotal screenView = getView();
                if (screenView != null) {
                    screenView.showStatement(response);
                }
            }
        };
    }
}
