package app.media.opp.partytonight.presentation.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
     * @param backButtonResId     resource of image which we want to use as overriding of default back icon
     */
    public static void configureToolbarAsActionBar(AppCompatActivity activity, Toolbar toolbar,
                                                   boolean backButtonAvailable, int backButtonResId) {
        activity.setSupportActionBar(toolbar);

        // get action bar
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar == null)
            return;

        if (backButtonAvailable) {

            // make "back" button available
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            if (backButtonResId != RID_STANDARD_BACK_BUTTON) {

                // setting icon to "back" button
                actionBar.setHomeAsUpIndicator(backButtonResId);
            }

        }

        TextView title = (TextView) toolbar.findViewById(RID_STANDARD_TOOLBAR_TITLE);

        if (title != null) {

            // setting title
            title.setText(actionBar.getTitle());

            actionBar.setDisplayShowTitleEnabled(false);
        }

    }

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
        configureToolbarAsActionBar(activity, toolbar, backButtonAvailable, RID_STANDARD_BACK_BUTTON);
    }

}
