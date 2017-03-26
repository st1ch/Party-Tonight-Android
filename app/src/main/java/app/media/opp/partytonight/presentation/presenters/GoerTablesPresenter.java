package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.booking.BookedTable;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerGetTablesUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerTablesView;

@UserScope
public class GoerTablesPresenter extends ProgressPresenter<IGoerTablesView> implements IGoerTablesPresenter {

    private GoerGetTablesUseCase useCase;

    @Inject
    public GoerTablesPresenter(Messages messages, GoerGetTablesUseCase useCase) {
        super(messages);
        this.useCase = useCase;
    }

    @Override
    public void getFreeTables(int eventId) {
        useCase.setIdEvent(eventId);
        useCase.execute(getSubscriber());
    }


    @NonNull
    private BaseProgressSubscriber<List<BookedTable>> getSubscriber() {
        return new BaseProgressSubscriber<List<BookedTable>>(this) {
            @Override
            public void onNext(List<BookedTable> response) {
                super.onNext(response);
                IGoerTablesView view = getView();

                if (view != null) {
                    if (response != null && response.isEmpty()) {
                        view.emptyResponse();
                    } else {
                        view.renderList(response);
                    }
                }
            }
        };
    }

    public HashMap<String, ArrayList<BookedTable>> compileList(List<BookedTable> response) {
        HashMap<String, ArrayList<BookedTable>> result = new HashMap<>();

        for (int i = 0; i < response.size(); i++) {
            String type = response.get(i).getType();

            result.put(type, new ArrayList<>());
            result.get(type).add(response.get(i));

            for (int j = i; j < response.size(); j++, i++) {
                if (response.get(j).getType().equals(type)) {
                    result.get(type).add(response.get(j));
                } else break;
            }

        }

        return result;
    }
}
