package com.example.yasym.ez_eats.Yelp.Task;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.app.Service;
import android.os.IBinder;

/**
 * Created by Gary on 2/14/2016.
 */
public class CurrentLocation extends Service{

    final Context mobile;
    public CurrentLocation(Context mobil){
        this.mobile = mobil;
        getCurrentLocation();
    }
    boolean GPS = false;
    boolean Network = false;
    private Location loc;
    private double latitude;
    private double longitude;
    private LocationManager locManager;

    public Location getCurrentLocation(){
        try {
            locManager = (LocationManager) mobile.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            GPS = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            Network = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            // if there is no GPS and Network enabled
            if (!GPS && !Network) {
                // do nothing
            }
            else {
                // if Network is enabled
                if (Network) {
                    if (locManager != null) {
                        //checks if permission is granted to get NETWORK_PROVIDER
                        if(checkCallingPermission(LocationManager.NETWORK_PROVIDER) == PackageManager.PERMISSION_GRANTED ) {
                            loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (loc != null) {
                                latitude = loc.getLatitude();
                                longitude = loc.getLongitude();
                            }
                        }
                    }
                }
                // if GPS is enabled
                else if (GPS) {
                    if (locManager != null) {
                        //checks if permission is granted for GPS_PROVIDER
                        if(checkCallingPermission(LocationManager.GPS_PROVIDER) == PackageManager.PERMISSION_GRANTED) {
                            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (loc != null) {
                                latitude = loc.getLatitude();
                                longitude = loc.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return loc;
    }

    public Location getLoc(){
        return this.loc;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
}
