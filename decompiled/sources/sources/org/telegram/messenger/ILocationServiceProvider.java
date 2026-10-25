package org.telegram.messenger;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.core.util.Consumer;

@Keep
/* loaded from: classes.dex */
public interface ILocationServiceProvider {
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 1;
    public static final int PRIORITY_HIGH_ACCURACY = 0;
    public static final int PRIORITY_LOW_POWER = 2;
    public static final int PRIORITY_NO_POWER = 3;
    public static final int STATUS_RESOLUTION_REQUIRED = 1;
    public static final int STATUS_SETTINGS_CHANGE_UNAVAILABLE = 2;
    public static final int STATUS_SUCCESS = 0;

    public interface IAPIConnectionCallbacks {
        void onConnected(Bundle r1);

        void onConnectionSuspended(int r1);
    }

    public interface IAPIOnConnectionFailedListener {
        void onConnectionFailed();
    }

    public interface ILocationListener {
        void onLocationChanged(Location r1);
    }

    public interface ILocationRequest {
        void setFastestInterval(long r1);

        void setInterval(long r1);

        void setPriority(int r1);
    }

    public interface IMapApiClient {
        void connect();

        void disconnect();
    }

    void checkLocationSettings(ILocationRequest r1, Consumer<Integer> r2);

    boolean checkServices();

    void getLastLocation(Consumer<Location> r1);

    void init(Context r1);

    ILocationRequest onCreateLocationRequest();

    IMapApiClient onCreateLocationServicesAPI(Context r1, IAPIConnectionCallbacks r2, IAPIOnConnectionFailedListener r3);

    void removeLocationUpdates(ILocationListener r1);

    void requestLocationUpdates(ILocationRequest r1, ILocationListener r2);
}
