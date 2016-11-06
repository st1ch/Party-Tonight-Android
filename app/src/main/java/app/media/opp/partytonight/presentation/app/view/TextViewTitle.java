package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/31/16
 */

public class TextViewTitle extends TextView {

    private static final String FONT = "fonts/falkin.ttf";

    public TextViewTitle(Context context) {
        super(context);

        configureView(context);
    }

    public TextViewTitle(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public TextViewTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    public void configureView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));
    }
}
