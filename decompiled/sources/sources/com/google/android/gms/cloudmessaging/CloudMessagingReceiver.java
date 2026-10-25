package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.lang.ref.SoftReference;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class CloudMessagingReceiver extends BroadcastReceiver {
    private static SoftReference zza;
    private static SoftReference zzb;

    public CloudMessagingReceiver() {
    }

    private final int zzb(Context r4, Intent r5) {
        PendingIntent r1 = (PendingIntent) r5.getParcelableExtra("pending_intent");
        if (r1 != null) goto L17;
    L7:
        Bundle r12 = r5.getExtras();
        if (r12 == null) goto L10;
        r12.remove("pending_intent");
    L12:
        if (Objects.equals(r5.getAction(), "com.google.firebase.messaging.NOTIFICATION_DISMISS") == false) goto L15;
        onNotificationDismissed(r4, r12);
        return -1;
    L15:
        Log.e("CloudMessagingReceiver", "Unknown notification action");
        return MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    L10:
        r12 = new Bundle();
        goto L12
    L17:
        r1.send();     // Catch: PendingIntent.CanceledException -> L6
    L6:
        Log.e("CloudMessagingReceiver", "Notification pending intent canceled");
        goto L7
    }

    public Executor getBroadcastExecutor() {
        monitor-enter(CloudMessagingReceiver.class);
        SoftReference r0 = zza;     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        ExecutorService r02 = (ExecutorService) r0.get();     // Catch: Throwable -> L7
    L10:
        if (r02 != null) goto L12;
        com.google.android.gms.internal.cloudmessaging.zzu.zza();     // Catch: Throwable -> L7
        r02 = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(new NamedThreadFactory("firebase-iid-executor")));     // Catch: Throwable -> L7
        zza = new SoftReference(r02);     // Catch: Throwable -> L7
    L12:
        monitor-exit(CloudMessagingReceiver.class);     // Catch: Throwable -> L7
        return r02;
    L9:
        r02 = null;
    L7:
        th = move-exception;
        throw th;
    }

    public abstract int onMessageReceive(Context r1, CloudMessage r2);

    public abstract void onNotificationDismissed(Context r1, Bundle r2);

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r8, Intent r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        boolean r4 = isOrderedBroadcast();
        BroadcastReceiver.PendingResult r5 = goAsync();
        getBroadcastExecutor().execute(new zzg(this, r9, r8, r4, r5));
    }

    public final /* synthetic */ void zza(Intent r18, Context r19, boolean r20, BroadcastReceiver.PendingResult r21) {
        Parcelable r4 = r18.getParcelableExtra("wrapped_intent");     // Catch: Throwable -> L6
        Executor r6 = null;
        if ((r4 instanceof Intent) == false) goto L8;
        Intent r42 = (Intent) r4;     // Catch: Throwable -> L6
    L9:
        if (r42 == null) goto L12;
        int r0 = zzb(r19, r42);     // Catch: Throwable -> L6
    L32:
        if (r20 == false) goto L35;
        if (r21 == null) goto L35;
        r21.setResultCode(r0);     // Catch: Throwable -> L6
    L35:
        if (r21 == null) goto L48;
        r21.finish();
        return;
    L48:
        return;
    L12:
        if (r18.getExtras() != null) goto L14;
        r0 = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L32
    L14:
        CloudMessage r5 = new CloudMessage(r18);     // Catch: Throwable -> L6
        CountDownLatch r1 = new CountDownLatch(1);     // Catch: Throwable -> L6
        monitor-enter(CloudMessagingReceiver.class);     // Catch: Throwable -> L6
        SoftReference r9 = zzb;     // Catch: Throwable -> L19
        if (r9 == null) goto L21;
        r6 = (Executor) r9.get();     // Catch: Throwable -> L19
    L21:
        if (r6 != null) goto L23;
        com.google.android.gms.internal.cloudmessaging.zzu.zza();     // Catch: Throwable -> L19
        ThreadPoolExecutor r92 = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("pscm-ack-executor"));     // Catch: Throwable -> L19
        r92.allowCoreThreadTimeOut(true);     // Catch: Throwable -> L19
        r6 = Executors.unconfigurableExecutorService(r92);     // Catch: Throwable -> L19
        zzb = new SoftReference(r6);     // Catch: Throwable -> L19
    L23:
        monitor-exit(CloudMessagingReceiver.class);     // Catch: Throwable -> L19
        r6.execute(new zze(r19, r5, r1));     // Catch: Throwable -> L6
        int r2 = onMessageReceive(r19, r5);     // Catch: Throwable -> L6
    L29:
        e = move-exception;
        Log.w("CloudMessagingReceiver", "Message ack failed: ".concat(e.toString()));     // Catch: Throwable -> L6
    L31:
        r0 = r2;
        goto L32
    L26:
        if (r1.await(1000, TimeUnit.MILLISECONDS) == true) goto L31;
        Log.w("CloudMessagingReceiver", "Message ack timed out");     // Catch: Throwable -> L6 InterruptedException -> L29
    L19:
        th = move-exception;
        throw th;     // Catch: Throwable -> L6
    L8:
        r42 = null;
    L6:
        th = move-exception;
        if (r21 == null) goto L42;
        r21.finish();
    L42:
        throw th;
    }
}
