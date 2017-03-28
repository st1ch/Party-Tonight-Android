package app.media.opp.partytonight.presentation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.booking.BookedTicket;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.activities.GoerCartActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class GoerRVSPFragment extends BlurDialogFragment {

    public static final String EVENT = "event";
    private Event event;

    public static GoerRVSPFragment newInstance() {
        return new GoerRVSPFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_goer_rsvp, null);

        ButterKnife.bind(this, root);

        builder.setView(root);

        Bundle bundle = this.getArguments();
        event = (Event) bundle.getSerializable("event");

        return builder.create();
    }

    @OnClick({R.id.btnNo})
    public void onClickNo() {
        finish();
        onDismiss(getDialog());
    }

    @OnClick({R.id.btnYes})
    public void onClickYes() {
        GoerCartActivity.putToCart(event.getIdEvent(), new Booking(event.getIdEvent(),
                new BookedTicket("", Integer.parseInt(event.getTicketPrice().get(0).getPrice()))));
        finish();
        onDismiss(getDialog());
    }

    private void finish() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}
