package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/1/16
 */

public class ButtonTransparent extends Button {

    private static final String TYPEFACE_LOCATION = "fonts/aguda_bold.ttf";

    public ButtonTransparent(Context context) {
        super(context);

        configureView(context);
    }

    public ButtonTransparent(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public ButtonTransparent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }


    private void configureView(Context context) {
        setBackground(null);

        setAllCaps(false);

        setTypeface(Typeface.createFromAsset(context.getAssets(), TYPEFACE_LOCATION));
    }
}
