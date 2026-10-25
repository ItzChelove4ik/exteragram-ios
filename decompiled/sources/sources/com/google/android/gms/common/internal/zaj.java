package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: classes4.dex */
public abstract class zaj implements DialogInterface.OnClickListener {
    public zaj() {
    }

    public static zaj zab(Activity r1, Intent r2, int r3) {
        return new zag(r2, r1, r3);
    }

    public static zaj zad(LifecycleFragment r1, Intent r2, int r3) {
        return new zai(r2, r1, 2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r4, int r5) {
        zaa();     // Catch: Throwable -> L4 ActivityNotFoundException -> L6
    L11:
        r4.dismiss();
        return;
    L6:
        e = move-exception;
        String r52 = "Failed to start resolution intent.";
        if (true != Build.FINGERPRINT.contains("generic")) goto L10;
        r52 = "Failed to start resolution intent. This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.";
    L10:
        Log.e("DialogRedirect", r52, e);     // Catch: Throwable -> L4
    L4:
        th = move-exception;
        r4.dismiss();
        throw th;
    }

    public abstract void zaa();
}
