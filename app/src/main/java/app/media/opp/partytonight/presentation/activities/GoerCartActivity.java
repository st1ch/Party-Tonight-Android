package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.media.opp.partytonight.R;
import butterknife.ButterKnife;

public class GoerCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_cart);
        ButterKnife.bind(this);

        configureViews();
    }

    private void configureViews() {

    }
}
