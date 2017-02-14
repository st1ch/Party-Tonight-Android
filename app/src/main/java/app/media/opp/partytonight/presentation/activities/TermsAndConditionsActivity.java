package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.os.Bundle;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.fragments.ContactUsFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TermsAndConditionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms_and_conditions);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bContactUs)
    public void contactUs() {
        ContactUsFragment fragment = ContactUsFragment.newInstance();
        fragment.show(getFragmentManager(), "contact us");
    }
}
