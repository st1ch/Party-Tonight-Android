package app.media.opp.partytonight.presentation.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.adapters.PlacesAutoCompleteAdapter;
import app.media.opp.partytonight.presentation.app.view.TouchableMapFragment;
import app.media.opp.partytonight.presentation.utils.MapUtils;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/6/16
 */

public class PromoterLocationActivity extends AppCompatActivity implements OnMapReadyCallback,
        LocationSource.OnLocationChangedListener,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    protected GoogleApiClient mGoogleApiClient;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView textViewTitle;
    @BindView(R.id.button_search)
    ImageButton buttonSearch;
    @BindView(R.id.editText_query)
    AutoCompleteTextView editTextQuery;
    @BindView(R.id.ivCross)
    ImageView imageViewCross;
    @BindView(R.id.ivMarker)
    ImageView imageViewMarker;
    @BindView(R.id.tvCoordinates)
    TextView tvCoordinates;

    private GoogleMap map;
    private LocationRequest mLocationRequest;

    private double latitude = -1;
    private double longitude = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_location);

        checkLocationPermission();
        buildGoogleApiClient();

        configureData();
        configureViews();
    }

    @OnClick(R.id.bConfirmLocation)
    public void confirmLocation() {
        Intent returnIntent = new Intent();

        returnIntent.putExtra(CreateEventActivity.INTENT_ADDRESS_LINE, MapUtils.getAddressLine(this, latitude, longitude));
        returnIntent.putExtra(CreateEventActivity.INTENT_LONGITUDE, longitude);
        returnIntent.putExtra(CreateEventActivity.INTENT_LATITUDE, latitude);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }

    public void configureData() {
        Intent intent = getIntent();

        longitude = intent.getDoubleExtra(CreateEventActivity.INTENT_LONGITUDE, longitude);
        latitude = intent.getDoubleExtra(CreateEventActivity.INTENT_LATITUDE, latitude);
    }

    public void configureViews() {
        ButterKnife.bind(this);

        TouchableMapFragment mapFragment = (TouchableMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.setOnTouchListener(v -> {
            switch (v.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    imageViewCross.setVisibility(View.VISIBLE);
                    imageViewMarker.setVisibility(View.INVISIBLE);

                    break;
                case MotionEvent.ACTION_UP:

                    imageViewCross.setVisibility(View.INVISIBLE);
                    imageViewMarker.setVisibility(View.VISIBLE);

                    break;
            }
        });

        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
        configureSearchButton();

        findViewById(R.id.mapRoot).setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    imageViewCross.setVisibility(View.VISIBLE);
                    imageViewMarker.setVisibility(View.GONE);
                    break;
                case MotionEvent.ACTION_UP:
                    imageViewCross.setVisibility(View.GONE);
                    imageViewMarker.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return false;
        });

        editTextQuery.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.item_suggestion_item));

        editTextQuery.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                onMapSearch();
                return true;
            }
            return false;
        });

//        latitude = getIntent().getDoubleExtra(CreateEventActivity.INTENT_LATITUDE, 0);
//        longitude = getIntent().getDoubleExtra(CreateEventActivity.INTENT_LONGITUDE, 0);
//
//        tvCoordinates.setText(StringUtils.cutDouble(latitude, 5) + " " + StringUtils.cutDouble(longitude, 5));
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
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setOnCameraMoveListener(() -> {
            latitude = map.getCameraPosition().target.latitude;
            longitude = map.getCameraPosition().target.longitude;

            tvCoordinates.setText(StringUtils.cutDouble(latitude, 5) + " " +
                    StringUtils.cutDouble(longitude, 5));
        });

        if (longitude == -1 || latitude == -1) {
            centerMapOnMyLocation();
        } else {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))
                    .zoom(17)
                    .tilt(40)
                    .build();
            map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        editTextQuery.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.item_suggestion_item, latitude, longitude));

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();

        Log.e("client", mGoogleApiClient != null ? "not null" : "null");

    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void centerMapOnMyLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();

//            editTextQuery.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.item_suggestion_item, latitude, longitude));


            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }

    @Override
    public void onLocationChanged(Location location) {
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        //Place current location marker
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = map.addMarker(markerOptions);
//
//        //move map camera
//        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        map.animateCamera(CameraUpdateFactory.zoomTo(11));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        map.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}