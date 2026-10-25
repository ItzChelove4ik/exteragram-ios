package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class FcmBroadcastProcessor {
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock = null;
    private final Context context;
    private final Executor executor;

    /* renamed from: $r8$lambda$-7tuBEF0Y9HKY-iHL1KguBHpRmY, reason: not valid java name */
    public static /* synthetic */ Task m1956$r8$lambda$7tuBEF0Y9HKYiHL1KguBHpRmY(Context r2, Intent r3, boolean r4, Task r5) {
        if (PlatformVersion.isAtLeastO() == true) goto L5;
    L9:
        return r5;
    L5:
        if (((Integer) r5.getResult()).intValue() != 402) goto L9;
        return bindToMessagingService(r2, r3, r4).continueWith(new CredentialManager$$ExternalSyntheticLambda0(), new FcmBroadcastProcessor$$ExternalSyntheticLambda2());
    }

    /* renamed from: $r8$lambda$XfjDf4oN43yyK-JpMtYf4DccRgA, reason: not valid java name */
    public static /* synthetic */ Integer m1957$r8$lambda$XfjDf4oN43yyKJpMtYf4DccRgA(Context r1, Intent r2) {
        return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(r1, r2));
    }

    public static /* synthetic */ Integer $r8$lambda$cemJXu5yEt_ArhTsgQ0nOTd237E(Task r0) {
        return -1;
    }

    public static /* synthetic */ Integer $r8$lambda$gHDK62Zf5oWXQUZhN2QtKJuRFrs(Task r0) {
        return 403;
    }

    static {
        lock = new Object();
    }

    public FcmBroadcastProcessor(Context r1) {
        this.context = r1;
        this.executor = new CredentialManager$$ExternalSyntheticLambda0();
    }

    private static Task<Integer> bindToMessagingService(Context r2, Intent r3, boolean r4) {
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L5;
        Log.d("FirebaseMessaging", "Binding to service");
    L5:
        WithinAppServiceConnection r0 = getServiceConnection(r2, "com.google.firebase.MESSAGING_EVENT");
        if (r4 == false) goto L14;
        if (ServiceStarter.getInstance().hasWakeLockPermission(r2) == false) goto L10;
        WakeLockHolder.sendWakefulServiceIntent(r2, r0, r3);
    L12:
        return Tasks.forResult(-1);
    L10:
        r0.sendIntent(r3);
        goto L12
    L14:
        return r0.sendIntent(r3).continueWith(new CredentialManager$$ExternalSyntheticLambda0(), new FcmBroadcastProcessor$$ExternalSyntheticLambda3());
    }

    private static WithinAppServiceConnection getServiceConnection(Context r2, String r3) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (fcmServiceConn != null) goto L9;
        fcmServiceConn = new WithinAppServiceConnection(r2, r3);     // Catch: Throwable -> L7
    L9:
        WithinAppServiceConnection r22 = fcmServiceConn;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r22;
    }

    public Task<Integer> process(Intent r4) {
        String r1 = r4.getStringExtra("gcm.rawData64");
        if (r1 == null) goto L6;
        r4.putExtra("rawData", Base64.decode(r1, 0));
        r4.removeExtra("gcm.rawData64");
    L6:
        return startMessagingService(this.context, r4);
    }

    @SuppressLint({"InlinedApi"})
    public Task<Integer> startMessagingService(Context r6, Intent r7) {
        boolean r1 = false;
        if (PlatformVersion.isAtLeastO() == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if ((r7.getFlags() & 268435456) == 0) goto L11;
        r1 = true;
    L11:
        if (r0 == false) goto L16;
        if (r1 == true) goto L16;
        return bindToMessagingService(r6, r7, r1);
    L16:
        return Tasks.call(this.executor, new FcmBroadcastProcessor$$ExternalSyntheticLambda0(r6, r7)).continueWithTask(this.executor, new FcmBroadcastProcessor$$ExternalSyntheticLambda1(r6, r7, r1));
    L5:
        if (r6.getApplicationInfo().targetSdkVersion < 26) goto L7;
        r0 = true;
        goto L9
    }
}
