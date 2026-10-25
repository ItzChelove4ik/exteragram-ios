package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public abstract class zzky {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static UserManager zzb;
    private static volatile boolean zzc;

    static {
        zzc = !zza();
    }

    public static boolean zza() {
        return true;
    }

    public static boolean zzb(Context r1) {
        if (zza() == true) goto L5;
        return false;
    L5:
        if (zzi(r1) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean zzc(Context r1) {
        if (zza() == true) goto L5;
        return true;
    L5:
        if (zzi(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static ListenableFuture zzd(Context r1, Callable r2, Executor r3) {
        return zze(r1, new zzkx(r2), r3);
    }

    @SuppressLint({"UnprotectedReceiver"})
    public static ListenableFuture zze(Context r6, AsyncCallable r7, Executor r8) {
        if (zzc(r6) == true) goto L5;
        SettableFuture r3 = SettableFuture.create();
        AtomicBoolean r1 = new AtomicBoolean();
        zzkv r0 = new zzkv(r1, r6, r3, r7, r8);
        r6.registerReceiver(r0, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        if (zzc(r6) == true) goto L9;
    L12:
        r3.addListener(new zzkw(r3, r1, r6, r0), MoreExecutors.directExecutor());
        return r3;
    L9:
        if (r1.compareAndSet(false, true) == false) goto L12;
        zzh(r6, r0);
        r3.setFuture(Futures.submitAsync(r7, r8));
        return r3;
    L5:
        return Futures.submitAsync(r7, r8);
    }

    public static /* synthetic */ void zzf(SettableFuture r0, AtomicBoolean r1, Context r2, BroadcastReceiver r3) {
        zzj(r0, r1, r2, r3);
    }

    public static /* synthetic */ void zzg(Context r0, BroadcastReceiver r1) {
        zzh(r0, r1);
    }

    private static void zzh(Context r1, BroadcastReceiver r2) {
        r1.unregisterReceiver(r2);     // Catch: IllegalArgumentException -> L4
        return;
    L4:
        e = move-exception;
        Log.w("DirectBootUtils", "Failed to unregister receiver", e);
    }

    @TargetApi(24)
    private static boolean zzi(Context r7) {
        if (zzc == false) goto L6;
        return true;
    L6:
        monitor-enter(zzky.class);
    L11:
        th = move-exception;
        throw th;
    L8:
        if (zzc == false) goto L13;
        monitor-exit(zzky.class);     // Catch: Throwable -> L11
        return true;
    L13:
        int r2 = 1;
    L14:
        boolean r5 = false;
        if (r2 > 2) goto L29;
        if (zzb != null) goto L19;
        zzb = (UserManager) r7.getSystemService(UserManager.class);     // Catch: Throwable -> L11
    L19:
        UserManager r3 = zzb;     // Catch: Throwable -> L11
        if (r3 == null) goto L21;
    L27:
        e = move-exception;
        Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);     // Catch: Throwable -> L11
        zzb = null;     // Catch: Throwable -> L11
        r2 = r2 + 1;     // Catch: Throwable -> L11
        goto L14
    L23:
        if (r3.isUserUnlocked() == true) goto L26;
    L25:
        if (r3.isUserRunning(Process.myUserHandle()) == true) goto L29;
    L26:
        r5 = true;
        goto L29
    L21:
        r5 = true;
    L31:
        if (r5 == false) goto L33;
        zzc = true;     // Catch: Throwable -> L11
    L33:
        monitor-exit(zzky.class);     // Catch: Throwable -> L11
        return r5;
    L29:
        if (r5 == false) goto L31;
        zzb = null;     // Catch: Throwable -> L11
        goto L31
    }

    private static /* synthetic */ void zzj(SettableFuture r1, AtomicBoolean r2, Context r3, BroadcastReceiver r4) {
        if (r1.isCancelled() == true) goto L5;
        return;
    L5:
        if (r2.compareAndSet(false, true) == false) goto L9;
        zzh(r3, r4);
        return;
    }
}
