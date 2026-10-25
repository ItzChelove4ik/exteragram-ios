package com.google.android.gms.maps;

import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.zzcb;

/* loaded from: classes5.dex */
final class zzah implements LifecycleDelegate {
    private final ViewGroup zza;
    private final IMapViewDelegate zzb;
    private View zzc;

    public zzah(ViewGroup r1, IMapViewDelegate r2) {
        this.zzb = (IMapViewDelegate) Preconditions.checkNotNull(r2);
        this.zza = (ViewGroup) Preconditions.checkNotNull(r1);
    }

    public final void getMapAsync(OnMapReadyCallback r3) {
        this.zzb.getMapAsync(new zzag(this, r3));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onCreate(Bundle r3) {
        Bundle r0 = new Bundle();     // Catch: RemoteException -> L4
        zzcb.zzb(r3, r0);     // Catch: RemoteException -> L4
        IMapViewDelegate r1 = this.zzb;     // Catch: RemoteException -> L4
        r1.onCreate(r0);     // Catch: RemoteException -> L4
        zzcb.zzb(r0, r3);     // Catch: RemoteException -> L4
        this.zzc = (View) ObjectWrapper.unwrap(r1.getView());     // Catch: RemoteException -> L4
        ViewGroup r32 = this.zza;     // Catch: RemoteException -> L4
        r32.removeAllViews();     // Catch: RemoteException -> L4
        r32.addView(this.zzc);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onDestroy() {
        this.zzb.onDestroy();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onLowMemory() {
        this.zzb.onLowMemory();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onPause() {
        this.zzb.onPause();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onResume() {
        this.zzb.onResume();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onSaveInstanceState(Bundle r2) {
        Bundle r0 = new Bundle();     // Catch: RemoteException -> L4
        zzcb.zzb(r2, r0);     // Catch: RemoteException -> L4
        this.zzb.onSaveInstanceState(r0);     // Catch: RemoteException -> L4
        zzcb.zzb(r0, r2);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onStart() {
        this.zzb.onStart();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // com.google.android.gms.dynamic.LifecycleDelegate
    public final void onStop() {
        this.zzb.onStop();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void zza(Bundle r2) {
        Bundle r0 = new Bundle();     // Catch: RemoteException -> L4
        zzcb.zzb(r2, r0);     // Catch: RemoteException -> L4
        this.zzb.onEnterAmbient(r0);     // Catch: RemoteException -> L4
        zzcb.zzb(r0, r2);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void zzb() {
        this.zzb.onExitAmbient();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }
}
