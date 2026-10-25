package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzey implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzez zza;

    public zzey(zzez r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity r2, Bundle r3) {
        zzer r0 = new zzer(this, r3, r2);
        this.zza.zzM(r0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity r2) {
        zzex r0 = new zzex(this, r2);
        this.zza.zzM(r0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity r2) {
        zzeu r0 = new zzeu(this, r2);
        this.zza.zzM(r0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity r2) {
        zzet r0 = new zzet(this, r2);
        this.zza.zzM(r0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity r3, Bundle r4) {
        zzcm r0 = new zzcm();
        zzew r1 = new zzew(this, r3, r0);
        this.zza.zzM(r1);
        Bundle r2 = r0.zze(50);
        if (r2 == null) goto L6;
        r4.putAll(r2);
        return;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity r2) {
        zzes r0 = new zzes(this, r2);
        this.zza.zzM(r0);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity r2) {
        zzev r0 = new zzev(this, r2);
        this.zza.zzM(r0);
    }
}
