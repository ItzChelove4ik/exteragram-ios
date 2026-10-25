package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes5.dex */
public abstract class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zza;

    public static CameraUpdate newLatLng(LatLng r2) {
        Preconditions.checkNotNull(r2, "latLng must not be null");
        return new CameraUpdate(zzb().newLatLng(r2));
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds r2, int r3) {
        Preconditions.checkNotNull(r2, "bounds must not be null");
        return new CameraUpdate(zzb().newLatLngBounds(r2, r3));
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static CameraUpdate newLatLngZoom(LatLng r2, float r3) {
        Preconditions.checkNotNull(r2, "latLng must not be null");
        return new CameraUpdate(zzb().newLatLngZoom(r2, r3));
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static void zza(ICameraUpdateFactoryDelegate r0) {
        zza = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(r0);
    }

    private static ICameraUpdateFactoryDelegate zzb() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(zza, "CameraUpdateFactory is not initialized");
    }
}
