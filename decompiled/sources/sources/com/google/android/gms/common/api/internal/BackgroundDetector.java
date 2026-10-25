package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zza = null;
    private final AtomicBoolean zzb;
    private final AtomicBoolean zzc;
    private final ArrayList zzd;
    private boolean zze;

    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean r1);
    }

    static {
        zza = new BackgroundDetector();
    }

    private BackgroundDetector() {
        this.zzb = new AtomicBoolean();
        this.zzc = new AtomicBoolean();
        this.zzd = new ArrayList();
        this.zze = false;
    }

    public static BackgroundDetector getInstance() {
        return zza;
    }

    public static void initialize(Application r2) {
        BackgroundDetector r0 = zza;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.zze == true) goto L9;
        r2.registerActivityLifecycleCallbacks(r0);     // Catch: Throwable -> L7
        r2.registerComponentCallbacks(r0);     // Catch: Throwable -> L7
        r0.zze = true;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    private final void zza(boolean r5) {
        BackgroundDetector r0 = zza;
        monitor-enter(r0);
        ArrayList r4 = this.zzd;     // Catch: Throwable -> L7
        int r1 = r4.size();     // Catch: Throwable -> L7
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L9;
        Object r3 = r4.get(r2);     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        ((BackgroundStateChangeListener) r3).onBackgroundStateChanged(r5);     // Catch: Throwable -> L7
        goto L5
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void addListener(BackgroundStateChangeListener r2) {
        BackgroundDetector r0 = zza;
        monitor-enter(r0);
        this.zzd.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isInBackground() {
        return this.zzb.get();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity r3, Bundle r4) {
        AtomicBoolean r32 = this.zzc;
        boolean r42 = this.zzb.compareAndSet(true, false);
        r32.set(true);
        if (r42 == false) goto L6;
        zza(false);
        return;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity r4) {
        AtomicBoolean r42 = this.zzc;
        boolean r0 = this.zzb.compareAndSet(true, false);
        r42.set(true);
        if (r0 == false) goto L6;
        zza(false);
        return;
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

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration r1) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int r3) {
        if (r3 == 20) goto L5;
        return;
    L5:
        if (this.zzb.compareAndSet(false, true) == false) goto L9;
        this.zzc.set(true);
        zza(true);
        return;
    }

    public boolean readCurrentStateIfPossible(boolean r3) {
        AtomicBoolean r0 = this.zzc;
        if (r0.get() == true) goto L13;
        if (ProcessUtils.zza() == true) goto L11;
        ActivityManager.RunningAppProcessInfo r32 = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(r32);
        if (r0.getAndSet(true) == true) goto L13;
        if (r32.importance <= 100) goto L13;
        this.zzb.set(true);
        goto L13
    L11:
        return r3;
    L13:
        return isInBackground();
    }
}
