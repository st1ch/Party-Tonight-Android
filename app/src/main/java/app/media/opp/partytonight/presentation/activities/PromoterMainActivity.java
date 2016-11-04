package app.media.opp.partytonight.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/4/16
 */

public class PromoterMainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_main);

        findViewById(R.id.bPromoterMyEvents).setOnClickListener(v -> {
            startActivity(new Intent(this, PromoterEventsActivity.class));
        });

        configureViews();
    }

    private void configureViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
