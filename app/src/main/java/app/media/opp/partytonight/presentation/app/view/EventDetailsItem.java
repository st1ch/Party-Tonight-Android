package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/15/16
 */

public class EventDetailsItem extends LinearLayout {

    private final static int DEFAULT_SRC = R.mipmap.ic_launcher;
    private final static String DEFAULT_TITLE = "Hello World";

    @BindView(R.id.src)
    ImageView ivSrc;
    @BindView(R.id.title)
    TextViewAguda tvTitle;
    @BindView(R.id.additionalTitle)
    TextViewAguda tvAdditional;
    @BindView(R.id.icon_forward)
    ImageView ivForwardIcon;

    private Drawable mImage = null;
    private String mLabel = DEFAULT_TITLE;
    private String mAdditionalLabel = "";
    private int mTextColor;
    private float mTextSize;

    public EventDetailsItem(Context context) {
        super(context);
    }

    public EventDetailsItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        handleAttributes(context, attrs);
        configureView(context);
    }

    public EventDetailsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleAttributes(context, attrs);
        configureView(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EventDetailsItem, 0, 0);

        try {

            mImage = array.getDrawable(R.styleable.EventDetailsItem_src);
            mLabel = array.getString(R.styleable.EventDetailsItem_label);
            mAdditionalLabel = array.getString(R.styleable.EventDetailsItem_additionalLabel);
            mTextColor = array.getColor(R.styleable.EventDetailsItem_textColor, Color.WHITE);
            mTextSize = array.getDimension(R.styleable.EventDetailsItem_textSize,
                    getResources().getDimension(R.dimen.textview_default_text_size));

        } finally {
            array.recycle();
        }
    }

    private void configureView(Context context) {
        inflate(context, R.layout.item_event_details, this);
        ButterKnife.bind(this);

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        tvAdditional.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

        tvTitle.setTextColor(mTextColor);
        tvAdditional.setTextColor(mTextColor);

        if (mImage != null) {
            ivSrc.setImageDrawable(mImage);
        } else {
            ivSrc.setImageResource(DEFAULT_SRC);
        }
        if (isClickable()) {
            ivForwardIcon.setVisibility(View.VISIBLE);

            setOnTouchListener((v, motionEvent) -> {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        setBackgroundColor(ContextCompat.getColor(context, R.color.colorButtonGraySemiTransparentPressed));

                        break;

                    case MotionEvent.ACTION_UP:

                        setBackgroundColor(ContextCompat.getColor(context, R.color.colorButtonGraySemiTransparent));

                        break;

                    default:
                        break;
                }

                // TODO: 11/16/16  do we need return true?
                return true;
            });
        }

        tvTitle.setText(mLabel);

        if (mAdditionalLabel != null) {
            tvAdditional.setVisibility(View.VISIBLE);
            tvAdditional.setText(mAdditionalLabel);
        }
    }
}
