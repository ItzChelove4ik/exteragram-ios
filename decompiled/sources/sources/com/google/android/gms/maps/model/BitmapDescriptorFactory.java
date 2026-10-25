package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.maps.zzah$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class BitmapDescriptorFactory {
    private static zzk zza;

    public static BitmapDescriptor fromBitmap(Bitmap r2) {
        Preconditions.checkNotNull(r2, "image must not be null");
        return new BitmapDescriptor(zzb().zzg(r2));
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static BitmapDescriptor fromResource(int r2) {
        return new BitmapDescriptor(zzb().zzk(r2));
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static void zza(zzk r1) {
        if (zza == null) goto L5;
        return;
    L5:
        zza = (zzk) Preconditions.checkNotNull(r1, "delegate must not be null");
    }

    private static zzk zzb() {
        return (zzk) Preconditions.checkNotNull(zza, "IBitmapDescriptorFactory is not initialized");
    }
}
