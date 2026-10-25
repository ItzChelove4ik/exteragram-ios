package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;
import kotlin.time.DurationKt;

/* loaded from: classes3.dex */
class TwilightManager {
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState;

    public static class TwilightState {
        boolean isNight;
        long nextUpdate;

        public TwilightState() {
        }
    }

    public TwilightManager(Context r2, LocationManager r3) {
        this.mTwilightState = new TwilightState();
        this.mContext = r2;
        this.mLocationManager = r3;
    }

    public static TwilightManager getInstance(Context r2) {
        if (sInstance != null) goto L6;
        Context r22 = r2.getApplicationContext();
        sInstance = new TwilightManager(r22, (LocationManager) r22.getSystemService("location"));
    L6:
        return sInstance;
    }

    @SuppressLint({"MissingPermission"})
    private Location getLastKnownLocation() {
        Location r1 = null;
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) goto L5;
        Location r0 = getLastKnownLocationForProvider("network");
    L7:
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0) goto L9;
        r1 = getLastKnownLocationForProvider("gps");
    L9:
        if (r1 == null) goto L15;
        if (r0 == null) goto L15;
        if (r1.getTime() <= r0.getTime()) goto L14;
        return r1;
    L14:
        return r0;
    L15:
        if (r1 == null) goto L17;
        return r1;
    L17:
        return r0;
    L5:
        r0 = null;
        goto L7
    }

    private Location getLastKnownLocationForProvider(String r2) {
    L6:
        e = move-exception;
        Log.d("TwilightManager", "Failed to get last known location", e);
        return null;
    L3:
        if (this.mLocationManager.isProviderEnabled(r2) == false) goto L12;
        return this.mLocationManager.getLastKnownLocation(r2);
    L12:
        return null;
    }

    private boolean isStateValid() {
        if (this.mTwilightState.nextUpdate <= System.currentTimeMillis()) goto L6;
        return true;
    L6:
        return false;
    }

    private void updateState(Location r18) {
        TwilightState r0 = this.mTwilightState;
        long r2 = System.currentTimeMillis();
        TwilightCalculator r4 = TwilightCalculator.getInstance();
        r4.calculateTwilight(r2 - DurationKt.MILLIS_IN_DAY, r18.getLatitude(), r18.getLongitude());
        r4.calculateTwilight(r2, r18.getLatitude(), r18.getLongitude());
        boolean r5 = true;
        if (r4.state != 1) goto L5;
    L4:
        boolean r1 = r5;
        long r13 = r4.sunrise;
        long r52 = r4.sunset;
        r4.calculateTwilight(r2 + DurationKt.MILLIS_IN_DAY, r18.getLatitude(), r18.getLongitude());
        long r42 = r4.sunrise;
        if (r13 != (-1)) goto L9;
    L18:
        long r132 = r2 + 43200000;
    L19:
        r0.isNight = r1;
        r0.nextUpdate = r132;
        return;
    L9:
        if (r52 == (-1)) goto L18;
        if (r2 <= r52) goto L15;
        r13 = r42;
    L17:
        r132 = r13 + 60000;
        goto L19
    L15:
        if (r2 <= r13) goto L17;
        r13 = r52;
        goto L17
    L5:
        r5 = false;
        goto L4
    }

    public boolean isNight() {
        TwilightState r0 = this.mTwilightState;
        if (isStateValid() == true) goto L5;
        Location r1 = getLastKnownLocation();
        if (r1 == null) goto L10;
        updateState(r1);
        return r0.isNight;
    L10:
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int r2 = Calendar.getInstance().get(11);
        if (r2 >= 6) goto L13;
        return true;
    L13:
        if (r2 >= 22) goto L19;
        return false;
    L19:
        return true;
    L5:
        return r0.isNight;
    }
}
