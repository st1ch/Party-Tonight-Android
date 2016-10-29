package app.media.opp.partytonight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LaunchScreenActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        findViewById(R.id.bGetStarted).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, WelcomeScreenActivity.class));
        finish();
    }
}
