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
 * on 11/21/16
 */

public class StatementItem extends LinearLayout {


    @BindView(R.id.ediEntity)
    EventDetailsItem ediEntity;

    private String key;
    private String value;

    public StatementItem(Context context) {
        super(context);

        configureView(context);
    }

    public StatementItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureAttributes(context, attrs);
        configureView(context);
    }

    public StatementItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureAttributes(context, attrs);
        configureView(context);
    }

    public void configureAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StatementItem, 0, 0);

        try {
            key = array.getString(R.styleable.StatementItem_key);
            value = array.getString(R.styleable.StatementItem_value);
        } finally {
            array.recycle();
        }
    }

    public void configureView(Context context) {
        inflate(context, R.layout.item_promoter_statement, this);

        ButterKnife.bind(this);

        ediEntity.setLabel(key);
        ediEntity.setAdditionalLabel(value);
    }
}
