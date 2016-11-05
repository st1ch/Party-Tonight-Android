package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/5/16
 */

public class ButtonCurvedGray extends Button {

    private static final int RID_BACKGROUND_DRAWABLE = R.drawable.button_curved_gray_transparent;
    private static final int RID_DIMEN_PADDING = R.dimen.edittext_curved_translucent_padding;
    private static final int RID_DIMEN_HEIGHT = R.dimen.edittext_curved_translucent_height;
    private static final int RID_COLOR_TEXT_COLOR = R.color.colorTextSignIn;

    private static final String FONT = "fonts/aguda_bold.ttf";

    public ButtonCurvedGray(Context context) {
        super(context);

        configureView(context);
    }

    public ButtonCurvedGray(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public ButtonCurvedGray(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        updateHeight(super.getContext());
    }

    public void updateHeight(Context context) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = (int) context.getResources().getDimension(RID_DIMEN_HEIGHT);
        setLayoutParams(lp);
    }

    public void configureView(Context context) {
        int padding = (int) context.getResources().getDimension(RID_DIMEN_PADDING);

        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));

        setBackground(ContextCompat.getDrawable(context, RID_BACKGROUND_DRAWABLE));

        setAllCaps(false);

        setTextColor(Color.WHITE);

        setPadding(padding, padding, padding, padding);

        setTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));

        setHintTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));
    }
}
