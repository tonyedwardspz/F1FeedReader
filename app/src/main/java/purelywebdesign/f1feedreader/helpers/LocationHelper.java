package purelywebdesign.f1feedreader.helpers;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Anthony on 25/03/2015.
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


    private static GoogleApiClient mGoogleApiClient;
    static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 1001;
    private Context calledContext;
    private View rootView;
    private Double userLat;
    private Double userLong;
    Location userLocation;

    public LocationHelper(Context context, View rootView){
        this.calledContext = context;
        this.rootView = rootView;

        if (checkPlayServices()){
            buildGoogleApiClient();
            mGoogleApiClient.connect();
        }
    }

    /**
     * Calculates and displays the distance betwen the user and the race, as the crow flies, in miles
     * @param latitude of the race
     * @param longitude of the race
     * @param locationText TextView to display results
     */
    public void calculateUserDistance(String latitude, String longitude, TextView locationText){
        Log.d("on", "calculate distance");
        Double raceLat = Utilities.parseDouble(latitude);
        Double raceLong = Utilities.parseDouble(longitude);

        Location race = new Location("Race");
        race.setLatitude(raceLat);
        race.setLongitude(raceLong);

        Float distance = userLocation.distanceTo(race);
        String distanceMiles = String.valueOf(Math.round(distance / 1609));
        locationText.setText(distanceMiles);

    }

    protected synchronized void buildGoogleApiClient() {
        Log.d("on", "build");
        mGoogleApiClient = new GoogleApiClient.Builder(calledContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("on", "connected");
        userLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("on", "connectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("on", "connection Failed");
    }

    /**
     * Check to see if play services are available on the users device. As this is a 'Bonus' feature,
     * available on if play services work, I do not want to inform user, but this is where to do it
     * @return the whether play services are available or not
     */
   public boolean checkPlayServices() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(calledContext);
        if (status != ConnectionResult.SUCCESS) {
            Log.d("Device", "not supported");
            return false;
        }
        return true;
    }
}
