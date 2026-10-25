package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: classes5.dex */
public abstract class MapView extends FrameLayout {
    private final zzai zza;

    public MapView(Context r3) {
        super(r3);
        this.zza = new zzai(this, r3, null);
        setClickable(true);
    }

    public void getMapAsync(OnMapReadyCallback r2) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        Preconditions.checkNotNull(r2, "callback must not be null.");
        this.zza.zza(r2);
    }

    public void onCreate(Bundle r3) {
        StrictMode.ThreadPolicy r0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(r0).permitAll().build());
        zzai r1 = this.zza;     // Catch: Throwable -> L7
        r1.onCreate(r3);     // Catch: Throwable -> L7
        if (r1.getDelegate() != null) goto L9;
        DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);     // Catch: Throwable -> L7
    L9:
        StrictMode.setThreadPolicy(r0);
        return;
    L7:
        th = move-exception;
        StrictMode.setThreadPolicy(r0);
        throw th;
    }

    public void onDestroy() {
        this.zza.onDestroy();
    }

    public void onEnterAmbient(Bundle r2) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        zzai r1 = this.zza;
        if (r1.getDelegate() == null) goto L6;
        ((zzah) r1.getDelegate()).zza(r2);
        return;
    }

    public void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzai r1 = this.zza;
        if (r1.getDelegate() == null) goto L6;
        ((zzah) r1.getDelegate()).zzb();
        return;
    }

    public void onLowMemory() {
        this.zza.onLowMemory();
    }

    public void onPause() {
        this.zza.onPause();
    }

    public void onResume() {
        this.zza.onResume();
    }

    public void onSaveInstanceState(Bundle r1) {
        this.zza.onSaveInstanceState(r1);
    }

    public void onStart() {
        this.zza.onStart();
    }

    public void onStop() {
        this.zza.onStop();
    }
}
