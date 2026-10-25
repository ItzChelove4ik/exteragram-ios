package com.google.android.recaptcha.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* loaded from: classes5.dex */
public final class zzas implements Application.ActivityLifecycleCallbacks {
    private final zzav zza;
    private zzbd zzb;
    private zzat zzc;

    public zzas(zzav r1) {
        this.zza = r1;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity r4, Bundle r5) {
        Window r0 = r4.getWindow();
        String r1 = r4.getClass().getSimpleName();
        zzav r2 = this.zza;
        zzbd r52 = new zzbd(r0, r1, r2);
        this.zzb = r52;
        r52.zza();
        zzat r53 = new zzat(r2);
        this.zzc = r53;
        if ((r4 instanceof FragmentActivity) == false) goto L5;
        FragmentActivity r42 = (FragmentActivity) r4;
    L6:
        if (r42 == null) goto L11;
        FragmentManager r3 = r42.getSupportFragmentManager();
        if (r3 == null) goto L12;
        r3.registerFragmentLifecycleCallbacks(r53, true);
        return;
    L12:
        return;
    L11:
        return;
    L5:
        r42 = null;
        goto L6
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity r2) {
        zzat r1 = this.zzc;
        if (r1 != null) goto L5;
        return;
    L5:
        if ((r2 instanceof FragmentActivity) == false) goto L7;
        FragmentActivity r22 = (FragmentActivity) r2;
    L8:
        if (r22 == null) goto L14;
        FragmentManager r23 = r22.getSupportFragmentManager();
        if (r23 == null) goto L15;
        r23.unregisterFragmentLifecycleCallbacks(r1);
        return;
    L15:
        return;
    L14:
        return;
    L7:
        r22 = null;
        goto L8
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity r1, Bundle r2) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity r1) {
    }
}
