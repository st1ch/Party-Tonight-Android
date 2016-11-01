package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/31/16
 */

public class ButtonCurvedTranslucent extends Button {

    private static final int RID_BACKGROUND_DRAWABLE = R.drawable.button_curved_transparent;
    private static final String TYPEFACE_LOCATION = "fonts/aguda.ttf";

    public ButtonCurvedTranslucent(Context context) {
        super(context);

        configureView(context);
    }

    public ButtonCurvedTranslucent(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public ButtonCurvedTranslucent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    private void configureView(Context context) {
        setBackground(ContextCompat.getDrawable(context, RID_BACKGROUND_DRAWABLE));

        setAllCaps(false);

        setTypeface(Typeface.createFromAsset(context.getAssets(), TYPEFACE_LOCATION));
    }
}
