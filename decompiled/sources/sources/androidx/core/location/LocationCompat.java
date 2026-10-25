package androidx.core.location;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;

/* loaded from: classes3.dex */
public abstract class LocationCompat {

    public static class Api26Impl {
        public static float getBearingAccuracyDegrees(Location r0) {
            return r0.getBearingAccuracyDegrees();
        }

        public static float getSpeedAccuracyMetersPerSecond(Location r0) {
            return r0.getSpeedAccuracyMetersPerSecond();
        }

        public static float getVerticalAccuracyMeters(Location r0) {
            return r0.getVerticalAccuracyMeters();
        }

        public static boolean hasBearingAccuracy(Location r0) {
            return r0.hasBearingAccuracy();
        }

        public static boolean hasSpeedAccuracy(Location r0) {
            return r0.hasSpeedAccuracy();
        }

        public static boolean hasVerticalAccuracy(Location r0) {
            return r0.hasVerticalAccuracy();
        }
    }

    public static class Api31Impl {
        public static boolean isMock(Location r0) {
            return r0.isMock();
        }
    }

    private static boolean containsExtra(Location r0, String r1) {
        Bundle r02 = r0.getExtras();
        if (r02 != null) goto L5;
        return false;
    L5:
        if (r02.containsKey(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static float getBearingAccuracyDegrees(Location r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        Bundle r22 = r2.getExtras();
        if (r22 != null) goto L10;
        return 0.0f;
    L10:
        return r22.getFloat("bearingAccuracy", 0.0f);
    L5:
        return Api26Impl.getBearingAccuracyDegrees(r2);
    }

    public static long getElapsedRealtimeMillis(Location r4) {
        return r4.getElapsedRealtimeNanos() / 1000000;
    }

    public static float getSpeedAccuracyMetersPerSecond(Location r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        Bundle r22 = r2.getExtras();
        if (r22 != null) goto L10;
        return 0.0f;
    L10:
        return r22.getFloat("speedAccuracy", 0.0f);
    L5:
        return Api26Impl.getSpeedAccuracyMetersPerSecond(r2);
    }

    public static float getVerticalAccuracyMeters(Location r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        Bundle r22 = r2.getExtras();
        if (r22 != null) goto L10;
        return 0.0f;
    L10:
        return r22.getFloat("verticalAccuracy", 0.0f);
    L5:
        return Api26Impl.getVerticalAccuracyMeters(r2);
    }

    public static boolean hasBearingAccuracy(Location r2) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return Api26Impl.hasBearingAccuracy(r2);
    L7:
        return containsExtra(r2, "bearingAccuracy");
    }

    public static boolean hasSpeedAccuracy(Location r2) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return Api26Impl.hasSpeedAccuracy(r2);
    L7:
        return containsExtra(r2, "speedAccuracy");
    }

    public static boolean hasVerticalAccuracy(Location r2) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return Api26Impl.hasVerticalAccuracy(r2);
    L7:
        return containsExtra(r2, "verticalAccuracy");
    }

    public static boolean isMock(Location r2) {
        if (Build.VERSION.SDK_INT < 31) goto L7;
        return Api31Impl.isMock(r2);
    L7:
        return r2.isFromMockProvider();
    }
}
