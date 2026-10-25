package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public abstract class zzcb {
    public static Parcelable zza(Bundle r2, String r3) {
        ClassLoader r0 = zzd();
        r2.setClassLoader(r0);
        Bundle r22 = r2.getBundle("map_state");
        if (r22 != null) goto L6;
        return null;
    L6:
        r22.setClassLoader(r0);
        return r22.getParcelable(r3);
    }

    public static void zzb(Bundle r2, Bundle r3) {
        if (r2 == null) goto L21;
        if (r3 == null) goto L23;
        Parcelable r1 = zza(r2, "MapOptions");
        if (r1 == null) goto L8;
        zzc(r3, "MapOptions", r1);
    L8:
        Parcelable r12 = zza(r2, "StreetViewPanoramaOptions");
        if (r12 == null) goto L11;
        zzc(r3, "StreetViewPanoramaOptions", r12);
    L11:
        Parcelable r13 = zza(r2, "camera");
        if (r13 == null) goto L15;
        zzc(r3, "camera", r13);
    L15:
        if (r2.containsKey("position") == false) goto L18;
        r3.putString("position", r2.getString("position"));
    L18:
        if (r2.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT") == false) goto L22;
        r3.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", r2.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
        return;
    L22:
        return;
    L23:
        return;
    }

    public static void zzc(Bundle r3, String r4, Parcelable r5) {
        ClassLoader r0 = zzd();
        r3.setClassLoader(r0);
        Bundle r2 = r3.getBundle("map_state");
        if (r2 != null) goto L5;
        r2 = new Bundle();
    L5:
        r2.setClassLoader(r0);
        r2.putParcelable(r4, r5);
        r3.putBundle("map_state", r2);
    }

    private static ClassLoader zzd() {
        return (ClassLoader) Preconditions.checkNotNull(zzcb.class.getClassLoader());
    }
}
