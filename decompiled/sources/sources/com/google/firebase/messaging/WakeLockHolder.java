package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.stats.WakeLock;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
abstract class WakeLockHolder {
    static final long WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = 0;
    private static final Object syncObject = null;
    private static WakeLock wakeLock;

    public static /* synthetic */ void $r8$lambda$SPUnmvKIM1U5EXlg0L3EuoHoay4(Intent r0, Task r1) {
        completeWakefulIntent(r0);
    }

    static {
        WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = 60000;
        syncObject = new Object();
    }

    private static void checkAndInitWakeLock(Context r3) {
        if (wakeLock != null) goto L6;
        WakeLock r0 = new WakeLock(r3, 1, "wake:com.google.firebase.iid.WakeLockHolder");
        wakeLock = r0;
        r0.setReferenceCounted(true);
        return;
    }

    public static void completeWakefulIntent(Intent r2) {
        Object r0 = syncObject;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (wakeLock != null) goto L7;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L7:
        if (isWakefulIntent(r2) == false) goto L11;
        setAsWakefulIntent(r2, false);     // Catch: Throwable -> L9
        wakeLock.release();     // Catch: Throwable -> L9
        goto L11
    }

    public static boolean isWakefulIntent(Intent r2) {
        return r2.getBooleanExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", false);
    }

    @SuppressLint({"TaskMainThread"})
    public static void sendWakefulServiceIntent(Context r3, WithinAppServiceConnection r4, Intent r5) {
        Object r0 = syncObject;
        monitor-enter(r0);
        checkAndInitWakeLock(r3);     // Catch: Throwable -> L7
        boolean r32 = isWakefulIntent(r5);     // Catch: Throwable -> L7
        setAsWakefulIntent(r5, true);     // Catch: Throwable -> L7
        if (r32 == true) goto L9;
        wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);     // Catch: Throwable -> L7
    L9:
        r4.sendIntent(r5).addOnCompleteListener(new WakeLockHolder$$ExternalSyntheticLambda0(r5));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static void setAsWakefulIntent(Intent r1, boolean r2) {
        r1.putExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", r2);
    }

    public static ComponentName startWakefulService(Context r3, Intent r4) {
        Object r0 = syncObject;
        monitor-enter(r0);
        checkAndInitWakeLock(r3);     // Catch: Throwable -> L9
        boolean r1 = isWakefulIntent(r4);     // Catch: Throwable -> L9
        setAsWakefulIntent(r4, true);     // Catch: Throwable -> L9
        ComponentName r32 = r3.startService(r4);     // Catch: Throwable -> L9
        if (r32 == null) goto L7;
        if (r1 == true) goto L13;
        wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r32;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }
}
