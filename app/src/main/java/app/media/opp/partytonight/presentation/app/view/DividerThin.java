package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import app.media.opp.partytonight.R;

public class DividerThin extends RelativeLayout {

    public DividerThin(Context context) {
        super(context);

        configureView(context);
    }

    public DividerThin(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public DividerThin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    private void configureView(Context context) {
        inflate(context, R.layout.divider_thin, this);
    }
}
