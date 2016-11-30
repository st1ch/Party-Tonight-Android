package app.media.opp.partytonight.presentation.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/4/16
 */

public final class ToolbarUtils {

    private static int RID_STANDARD_BACK_BUTTON = -1;

    // if want to reuse this class then change next line to the actual one
    private static int RID_STANDARD_TOOLBAR_TITLE = R.id.title;

    /**
     * Setting up the toolbar as a ActionBar of AppCompatActivity
     * <p>
     * Warning: If we use back button we still need to override onOptionsItemSelected method in activity
     * and handle clicks of android.R.id.home
     *
     * @param activity            target activity
     * @param toolbar             target toolbar
     * @param backButtonAvailable true if we need "back"/"home" button in toolbar
     */
    public static void configureToolbarAsActionBar(AppCompatActivity activity, Toolbar toolbar,
                                                   boolean backButtonAvailable) {
        activity.setSupportActionBar(toolbar);

        // get action bar
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar == null)
            return;

        ImageButton btnBack = (ImageButton) toolbar.findViewById(R.id.button_back);

        if (btnBack != null) {
            if (!backButtonAvailable) {
                btnBack.setVisibility(View.GONE);
            } else {
                btnBack.setOnClickListener(v -> {
                    activity.onBackPressed();
                });
            }
        }

        TextView title = (TextView) toolbar.findViewById(RID_STANDARD_TOOLBAR_TITLE);

        if (title != null) {

            // setting title
            title.setText(actionBar.getTitle());

            actionBar.setDisplayShowTitleEnabled(false);
        }

    }

    public static void configureToolbarAsActionBar(AppCompatActivity activity, Toolbar toolbar,
                                                   boolean backButtonAvailable, boolean cartButtonAvailable) {

        configureToolbarAsActionBar(activity, toolbar, backButtonAvailable);

        if (cartButtonAvailable) {
            ImageButton btnCart = (ImageButton) toolbar.findViewById(R.id.btnCart);

            btnCart.setVisibility(View.VISIBLE);
        }
    }


}
