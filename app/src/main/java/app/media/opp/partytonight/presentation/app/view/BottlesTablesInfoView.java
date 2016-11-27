package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/16/16
 */

public class BottlesTablesInfoView extends LinearLayout {

    @BindView(R.id.tvLabel)
    TextViewAguda tvLabel;
    @BindView(R.id.containerAvailable)
    EventDetailsItem containerAvailable;
    @BindView(R.id.containerPurchased)
    EventDetailsItem containerPurchased;


    private String mLabel;
    private int mCounterAvailable;
    private int mCounterPurchased;

    public BottlesTablesInfoView(Context context) {
        super(context);

        configureView(context);
    }

    public BottlesTablesInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        handleAttributes(context, attrs);
        configureView(context);
    }

    public BottlesTablesInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleAttributes(context, attrs);
        configureView(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottlesTablesInfoView, 0, 0);

        try {
            mLabel = array.getString(R.styleable.BottlesTablesInfoView_itemLabel);
            mCounterAvailable = array.getInt(R.styleable.BottlesTablesInfoView_available, 0);
            mCounterPurchased = array.getInt(R.styleable.BottlesTablesInfoView_purchased, 0);
        } finally {
            array.recycle();
        }
    }

    private void actualizeView() {
        tvLabel.setText(mLabel);

        containerAvailable.tvAdditional.setText(String.valueOf(mCounterAvailable));
        containerPurchased.tvAdditional.setText(String.valueOf(mCounterPurchased));
    }

    private void setLabel(String label) {
        mLabel = label;
        tvLabel.setText(label);
    }

    private void setAvailableAmount(int amount) {
        mCounterAvailable = amount;

        containerAvailable.tvAdditional.setText(String.valueOf(amount));
    }

    private void setPurchasedAmount(int amount) {
        mCounterPurchased = amount;

        containerPurchased.tvAdditional.setText(String.valueOf(amount));
    }

    private void configureView(Context context) {
        inflate(context, R.layout.item_details_item, this);

        ButterKnife.bind(this);

        actualizeView();
    }
}
