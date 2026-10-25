package com.google.android.gms.internal.location;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzcg extends GoogleApi implements FusedLocationProviderClient {
    static final Api.ClientKey zza = null;
    public static final Api zzb = null;
    private static final Object zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zzb = new Api("LocationServices.API", new zzbe(), r0);
        zzc = new Object();
    }

    public zzcg(Context r4) {
        super(r4, zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private final Task zzb(LocationRequest r3, ListenerHolder r4) {
        zzcf r0 = new zzcf(this, r4, zzbv.zza);
        zzbq r1 = new zzbq(r0, r3);
        return doRegisterEventListener(RegistrationMethods.builder().register(r1).unregister(r0).withHolder(r4).setMethodKey(2436).build());
    }

    @Override // com.google.android.gms.common.api.GoogleApi
    public final String getApiFallbackAttributionTag(Context r1) {
        return null;
    }

    @Override // com.google.android.gms.location.FusedLocationProviderClient
    public final Task<Location> getLastLocation() {
        return doRead(TaskApiCall.builder().run(zzbu.zza).setMethodKey(2414).build());
    }

    @Override // com.google.android.gms.location.FusedLocationProviderClient
    public final Task<Void> removeLocationUpdates(LocationCallback r2) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(r2, LocationCallback.class.getSimpleName()), 2418).continueWith(zzca.zza, zzbs.zza);
    }

    @Override // com.google.android.gms.location.FusedLocationProviderClient
    public final Task<Void> requestLocationUpdates(LocationRequest r2, LocationCallback r3, Looper r4) {
        if (r4 != null) goto L5;
        r4 = Looper.myLooper();
        Preconditions.checkNotNull(r4, "invalid null looper");
    L5:
        return zzb(r2, ListenerHolders.createListenerHolder(r3, r4, LocationCallback.class.getSimpleName()));
    }
}
