package app.media.opp.partytonight.presentation.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/6/16
 */

public class PromoterLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView textViewTitle;
    @BindView(R.id.button_search)
    ImageButton buttonSearch;
    @BindView(R.id.editText_query)
    EditText editTextQuery;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_location);

        configureViews();
    }

    public void configureViews() {
        ButterKnife.bind(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
        configureSearchButton();

        editTextQuery.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onMapSearch();
                return true;
            }
            return false;
        });
    }

    public void configureSearchButton() {
        buttonSearch.setOnClickListener(v -> {
            if (editTextQuery.getVisibility() == View.GONE) {
                buttonSearch.setImageResource(R.mipmap.ic_close);

                editTextQuery.setVisibility(View.VISIBLE);
                textViewTitle.setVisibility(View.GONE);
            } else {
                editTextQuery.setText("");
            }
        });

        editTextQuery.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                buttonSearch.setImageResource(R.mipmap.ic_search);
                textViewTitle.setVisibility(View.VISIBLE);
                editTextQuery.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        if (editTextQuery.getVisibility() == View.VISIBLE) {
//
//        } else {
        super.onBackPressed();
//        }
    }


    public void onMapSearch() {
        EditText locationSearch = (EditText) findViewById(R.id.editText_query);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mMap == null) {
            mMap = googleMap;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.e("Permissions", "There is no permission!");
                return;
            }

            mMap.setMyLocationEnabled(true);
        }
    }
}