package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zao;
import java.util.Objects;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes4.dex */
final class zad extends zao {
    final /* synthetic */ GoogleApiAvailability zaa;
    private final Context zab;

    public zad(GoogleApiAvailability r1, Context r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        if (Looper.myLooper() != null) goto L5;
        Looper r12 = Looper.getMainLooper();
    L6:
        super(r12);
        this.zab = r2.getApplicationContext();
        return;
    L5:
        r12 = Looper.myLooper();
        goto L6
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r3) {
        int r32 = r3.what;
        if (r32 == 1) goto L6;
        StringBuilder r0 = new StringBuilder(String.valueOf(r32).length() + 39);
        r0.append("Don't know how to handle this message: ");
        r0.append(r32);
        Log.w("GoogleApiAvailability", r0.toString());
        return;
    L6:
        GoogleApiAvailability r33 = this.zaa;
        Context r2 = this.zab;
        int r02 = r33.isGooglePlayServicesAvailable(r2);
        if (r33.isUserResolvableError(r02) == false) goto L10;
        r33.showErrorNotification(r2, r02);
        return;
    }
}
