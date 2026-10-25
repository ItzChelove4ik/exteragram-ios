package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public abstract class AutoResolveHelper {
    static final long zza = 0;
    private static final long zzb = 0;

    static {
        zzb = 600000;
        zza = SystemClock.elapsedRealtime();
    }

    public static Status getStatusFromIntent(Intent r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return (Status) r1.getParcelableExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
    }

    public static void putStatusIntoIntent(Intent r1, Status r2) {
        if (r2 != null) goto L6;
        r1.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
        return;
    L6:
        r1.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", r2);
    }

    public static <TResult extends AutoResolvableResult> void resolveTask(Task<TResult> r4, Activity r5, int r6) {
        zzc r42 = zzc.zza(r4);
        FragmentTransaction r52 = r5.getFragmentManager().beginTransaction();
        int r0 = r42.zzc;
        Bundle r1 = new Bundle();
        r1.putInt("resolveCallId", r0);
        r1.putInt("requestCode", r6);
        r1.putLong("initializationElapsedRealtime", zza);
        zzd r62 = new zzd();
        r62.setArguments(r1);
        r52.add(r62, "com.google.android.gms.wallet.AutoResolveHelper" + r42.zzc).commit();
    }

    public static /* bridge */ /* synthetic */ long zza() {
        return zzb;
    }

    public static /* bridge */ /* synthetic */ void zzb(Activity r0, int r1, int r2, Intent r3) {
        zze(r0, r1, 0, r3);
    }

    public static /* bridge */ /* synthetic */ void zzc(Activity r0, int r1, Task r2) {
        zzf(r0, r1, r2);
    }

    public static void zzd(Status r1, Object r2, TaskCompletionSource r3) {
        if (r1.isSuccess() == false) goto L6;
        r3.setResult(r2);
        return;
    L6:
        r3.setException(ApiExceptionUtil.fromStatus(r1));
    }

    private static void zze(Activity r1, int r2, int r3, Intent r4) {
        PendingIntent r12 = r1.createPendingResult(r2, r4, TLObject.FLAG_30);
        if (r12 == null) goto L5;
        r12.send(r3);     // Catch: PendingIntent.CanceledException -> L10
        return;
    L10:
        e = move-exception;
        if (Log.isLoggable("AutoResolveHelper", 6) == false) goto L18;
        Log.e("AutoResolveHelper", "Exception sending pending result", e);
        return;
    L18:
        return;
    L5:
        if (Log.isLoggable("AutoResolveHelper", 5) == false) goto L17;
        Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
        return;
    }

    private static void zzf(Activity r5, int r6, Task r7) {
        if (r5.isFinishing() == true) goto L5;
        Exception r0 = r7.getException();
        if ((r0 instanceof ResolvableApiException) == true) goto L31;
        Intent r2 = new Intent();
        if (r7.isSuccessful() == false) goto L22;
        ((AutoResolvableResult) r7.getResult()).putIntoIntent(r2);
        int r72 = -1;
    L29:
        zze(r5, r6, r72, r2);
        return;
    L22:
        if ((r0 instanceof ApiException) == false) goto L26;
        ApiException r02 = (ApiException) r0;
        putStatusIntoIntent(r2, new Status(r02.getStatusCode(), r02.getMessage(), null));
    L24:
        r72 = 1;
        goto L29
    L26:
        if (Log.isLoggable("AutoResolveHelper", 6) == false) goto L28;
        Log.e("AutoResolveHelper", "Unexpected non API exception!", r0);
    L28:
        putStatusIntoIntent(r2, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
        goto L24
    L31:
        ((ResolvableApiException) r0).startResolutionForResult(r5, r6);     // Catch: IntentSender.SendIntentException -> L13
        return;
    L13:
        e = move-exception;
        if (Log.isLoggable("AutoResolveHelper", 6) == false) goto L34;
        Log.e("AutoResolveHelper", "Error starting pending intent!", e);
        return;
    L34:
        return;
    L5:
        if (Log.isLoggable("AutoResolveHelper", 3) == false) goto L33;
        Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
        return;
    }
}
