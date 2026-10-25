package com.google.android.gms.common;

import android.content.Context;
import android.content.res.Resources;

/* loaded from: classes4.dex */
public abstract class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public static Context getRemoteContext(Context r0) {
        return GooglePlayServicesUtilLight.getRemoteContext(r0);
    }

    public static Resources getRemoteResource(Context r0) {
        return GooglePlayServicesUtilLight.getRemoteResource(r0);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context r0) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(r0);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context r0, int r1) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(r0, r1);
    }
}
