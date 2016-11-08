package app.media.opp.partytonight.presentation.app.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/7/16
 */

public class TouchableMapFragment extends SupportMapFragment {

    private View mOriginalView;
    private TouchableMapWrapper mMapWrapperLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mOriginalView = super.onCreateView(inflater, container, savedInstanceState);

        mMapWrapperLayout = new TouchableMapWrapper(getActivity());
        mMapWrapperLayout.addView(mOriginalView);

        return mMapWrapperLayout;
    }

    @Override
    public View getView() {
        return mOriginalView;
    }

    public void setOnTouchListener(TouchableMapWrapper.OnTouchListener onTouchListener) {
        mMapWrapperLayout.setOnTouchListener(onTouchListener);
    }
}
