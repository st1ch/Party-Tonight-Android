package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/1/16
 */

public class TextViewSignHint extends TextView {

    private static final String FONT = "fonts/aguda.ttf";

    public TextViewSignHint(Context context) {
        super(context);

        configureView(context);
    }

    public TextViewSignHint(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public TextViewSignHint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    public void configureView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));
    }
}
