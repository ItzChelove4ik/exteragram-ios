package com.google.android.gms.maps.model;

import android.os.RemoteException;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.maps.zzah$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class Marker {
    protected final zzaj zza;

    public Marker(zzaj r1) {
        this.zza = (zzaj) Preconditions.checkNotNull(r1);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Marker) == true) goto L10;
        return false;
    L10:
        return this.zza.zzE(((Marker) r3).zza);
    L7:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return false;
    }

    public LatLng getPosition() {
        return this.zza.zzj();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public Object getTag() {
        return ObjectWrapper.unwrap(this.zza.zzi());
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public int hashCode() {
        return this.zza.zzg();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return 0;
    }

    public void remove() {
        this.zza.zzo();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setIcon(BitmapDescriptor r1) {
        if (r1 != null) goto L5;
        this.zza.zzt(null);     // Catch: RemoteException -> L7
        return;
    L5:
        IObjectWrapper r12 = r1.zza();     // Catch: RemoteException -> L7
        this.zza.zzt(r12);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setPosition(LatLng r1) {
        if (r1 != null) goto L10;
        f$$ExternalSyntheticBUOutline1.m("latlng cannot be null - a position is required.");
        return;
    L10:
        this.zza.zzw(r1);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setRotation(float r1) {
        this.zza.zzx(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setTag(Object r1) {
        this.zza.zzz(ObjectWrapper.wrap(r1));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }
}
