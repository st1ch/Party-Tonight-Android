package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public class Divider extends RelativeLayout {

    public Divider(Context context) {
        super(context);

        configureView(context);
    }

    public Divider(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public Divider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    private void configureView(Context context) {
        inflate(context, R.layout.divider_horizontal, this);
    }
}
