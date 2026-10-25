package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.zzcc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class zzai extends DeferredLifecycleHelper {
    protected OnDelegateCreatedListener zza;
    private final ViewGroup zzb;
    private final Context zzc;
    private final GoogleMapOptions zzd;
    private final List zze;

    public zzai(ViewGroup r2, Context r3, GoogleMapOptions r4) {
        this.zze = new ArrayList();
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener r1) {
        this.zza = r1;
        zzb();
    }

    public final void zza(OnMapReadyCallback r2) {
        if (getDelegate() == null) goto L6;
        ((zzah) getDelegate()).getMapAsync(r2);
        return;
    L6:
        this.zze.add(r2);
    }

    public final void zzb() {
        if (this.zza != null) goto L5;
        return;
    L5:
        if (getDelegate() != null) goto L23;
        Context r0 = this.zzc;     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        MapsInitializer.initialize(r0);     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        IMapViewDelegate r02 = zzcc.zza(r0, null).zzg(ObjectWrapper.wrap(r0), this.zzd);     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        if (r02 == null) goto L24;
        this.zza.onDelegateCreated(new zzah(this.zzb, r02));     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        List r03 = this.zze;     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        Iterator r1 = r03.iterator();     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
    L11:
        if (r1.hasNext() == false) goto L13;
        OnMapReadyCallback r2 = (OnMapReadyCallback) r1.next();     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        ((zzah) getDelegate()).getMapAsync(r2);     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        goto L11
    L13:
        r03.clear();     // Catch: RemoteException -> L15 GooglePlayServicesNotAvailableException -> L18
        return;
        goto L25
    L24:
        return;
    L15:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return;
    L25:
        return;
    }
}
