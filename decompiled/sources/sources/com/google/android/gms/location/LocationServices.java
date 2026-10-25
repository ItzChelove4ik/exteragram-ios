package com.google.android.gms.location;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.internal.location.zzcg;
import com.google.android.gms.internal.location.zzcr;
import com.google.android.gms.internal.location.zzcy;
import com.google.android.gms.internal.location.zzdb;

/* loaded from: classes5.dex */
public abstract class LocationServices {

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = null;

    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = null;

    @Deprecated
    public static final GeofencingApi GeofencingApi = null;

    @Deprecated
    public static final SettingsApi SettingsApi = null;

    static {
        API = zzcg.zzb;
        FusedLocationApi = new zzbd();
        GeofencingApi = new zzcr();
        SettingsApi = new zzcy();
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context r1) {
        return new zzcg(r1);
    }

    public static SettingsClient getSettingsClient(Context r1) {
        return new zzdb(r1);
    }
}
