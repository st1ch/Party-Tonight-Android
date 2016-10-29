package app.media.opp.partytonight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreenActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        findViewById(R.id.bPromoter).setOnClickListener(this);
        findViewById(R.id.bPartyGoer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPartyGoer:
                break;
            case R.id.bPromoter:
                startActivity(new Intent(this, PromoterSignInActivity.class));
                break;
        }

    }
}
