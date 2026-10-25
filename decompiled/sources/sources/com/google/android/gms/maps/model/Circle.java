package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.zzah$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class Circle {
    private final com.google.android.gms.internal.maps.zzn zza;

    public Circle(com.google.android.gms.internal.maps.zzn r1) {
        this.zza = (com.google.android.gms.internal.maps.zzn) Preconditions.checkNotNull(r1);
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof Circle) == true) goto L10;
        return false;
    L10:
        return this.zza.zzy(((Circle) r3).zza);
    L7:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return false;
    }

    public double getRadius() {
        return this.zza.zzd();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return 0.0d;
    }

    public final int hashCode() {
        return this.zza.zzi();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return 0;
    }

    public void remove() {
        this.zza.zzn();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setCenter(LatLng r2) {
        Preconditions.checkNotNull(r2, "center must not be null.");     // Catch: RemoteException -> L4
        this.zza.zzo(r2);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setFillColor(int r1) {
        this.zza.zzq(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setRadius(double r1) {
        this.zza.zzr(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setStrokeColor(int r1) {
        this.zza.zzs(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }
}
