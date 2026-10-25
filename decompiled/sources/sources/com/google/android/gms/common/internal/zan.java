package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class zan implements Handler.Callback {
    final ArrayList zaa;
    private final zam zab;
    private final ArrayList zac;
    private final ArrayList zad;
    private volatile boolean zae;
    private final AtomicInteger zaf;
    private boolean zag;
    private final Handler zah;
    private final Object zai;

    public zan(Looper r3, zam r4) {
        this.zac = new ArrayList();
        this.zaa = new ArrayList();
        this.zad = new ArrayList();
        this.zae = false;
        this.zaf = new AtomicInteger(0);
        this.zag = false;
        this.zai = new Object();
        this.zab = r4;
        this.zah = new com.google.android.gms.internal.base.zao(r3, this);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message r4) {
        int r0 = r4.what;
        if (r0 != 1) goto L19;
        GoogleApiClient.ConnectionCallbacks r42 = (GoogleApiClient.ConnectionCallbacks) r4.obj;
        Object r2 = this.zai;
        monitor-enter(r2);
    L13:
        th = move-exception;
        throw th;
    L7:
        if (this.zae == true) goto L9;
    L15:
        monitor-exit(r2);     // Catch: Throwable -> L13
        return true;
    L9:
        if (this.zab.isConnected() == false) goto L15;
        if (this.zac.contains(r42) == false) goto L15;
        r42.onConnected(null);     // Catch: Throwable -> L13
        goto L15
    L19:
        StringBuilder r43 = new StringBuilder(String.valueOf(r0).length() + 34);
        r43.append("Don't know how to handle message: ");
        r43.append(r0);
        Log.wtf("GmsClientEvents", r43.toString(), new Exception());
        return false;
    }

    public final void zaa() {
        this.zae = false;
        this.zaf.incrementAndGet();
    }

    public final void zab() {
        this.zae = true;
    }

    public final void zac(Bundle r11) {
        Handler r0 = this.zah;
        Preconditions.checkHandlerThread(r0, "onConnectionSuccess must only be called on the Handler thread");
        Object r1 = this.zai;
        monitor-enter(r1);
        Preconditions.checkState(!this.zag);     // Catch: Throwable -> L16
        r0.removeMessages(1);     // Catch: Throwable -> L16
        this.zag = true;     // Catch: Throwable -> L16
        ArrayList r02 = this.zaa;     // Catch: Throwable -> L16
        Preconditions.checkState(r02.isEmpty());     // Catch: Throwable -> L16
        ArrayList r2 = new ArrayList(this.zac);     // Catch: Throwable -> L16
        AtomicInteger r3 = this.zaf;     // Catch: Throwable -> L16
        int r4 = r3.get();     // Catch: Throwable -> L16
        int r5 = r2.size();     // Catch: Throwable -> L16
        int r7 = 0;
    L5:
        if (r7 >= r5) goto L18;
        Object r8 = r2.get(r7);     // Catch: Throwable -> L16
        r7 = r7 + 1;     // Catch: Throwable -> L16
        GoogleApiClient.ConnectionCallbacks r82 = (GoogleApiClient.ConnectionCallbacks) r8;     // Catch: Throwable -> L16
        if (this.zae == false) goto L18;
        if (this.zab.isConnected() == false) goto L18;
        if (r3.get() != r4) goto L18;
        if (r02.contains(r82) == true) goto L5;
        r82.onConnected(r11);     // Catch: Throwable -> L16
    L18:
        r02.clear();     // Catch: Throwable -> L16
        this.zag = false;     // Catch: Throwable -> L16
        monitor-exit(r1);     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        throw th;
    }

    public final void zad(int r11) {
        Handler r0 = this.zah;
        Preconditions.checkHandlerThread(r0, "onUnintentionalDisconnection must only be called on the Handler thread");
        r0.removeMessages(1);
        Object r02 = this.zai;
        monitor-enter(r02);
        this.zag = true;     // Catch: Throwable -> L14
        ArrayList r2 = this.zac;     // Catch: Throwable -> L14
        ArrayList r1 = new ArrayList(r2);     // Catch: Throwable -> L14
        AtomicInteger r3 = this.zaf;     // Catch: Throwable -> L14
        int r4 = r3.get();     // Catch: Throwable -> L14
        int r5 = r1.size();     // Catch: Throwable -> L14
        int r7 = 0;
    L5:
        if (r7 >= r5) goto L16;
        Object r8 = r1.get(r7);     // Catch: Throwable -> L14
        r7 = r7 + 1;     // Catch: Throwable -> L14
        GoogleApiClient.ConnectionCallbacks r82 = (GoogleApiClient.ConnectionCallbacks) r8;     // Catch: Throwable -> L14
        if (this.zae == false) goto L16;
        if (r3.get() != r4) goto L16;
        if (r2.contains(r82) == false) goto L5;
        r82.onConnectionSuspended(r11);     // Catch: Throwable -> L14
    L16:
        this.zaa.clear();     // Catch: Throwable -> L14
        this.zag = false;     // Catch: Throwable -> L14
        monitor-exit(r02);     // Catch: Throwable -> L14
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public final void zae(ConnectionResult r10) {
        Handler r0 = this.zah;
        Preconditions.checkHandlerThread(r0, "onConnectionFailure must only be called on the Handler thread");
        r0.removeMessages(1);
        Object r02 = this.zai;
        monitor-enter(r02);
        ArrayList r2 = this.zad;     // Catch: Throwable -> L14
        ArrayList r1 = new ArrayList(r2);     // Catch: Throwable -> L14
        AtomicInteger r3 = this.zaf;     // Catch: Throwable -> L14
        int r4 = r3.get();     // Catch: Throwable -> L14
        int r5 = r1.size();     // Catch: Throwable -> L14
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L18;
        Object r7 = r1.get(r6);     // Catch: Throwable -> L14
        r6 = r6 + 1;     // Catch: Throwable -> L14
        GoogleApiClient.OnConnectionFailedListener r72 = (GoogleApiClient.OnConnectionFailedListener) r7;     // Catch: Throwable -> L14
        if (this.zae == false) goto L16;
        if (r3.get() != r4) goto L16;
        if (r2.contains(r72) == false) goto L5;
        r72.onConnectionFailed(r10);     // Catch: Throwable -> L14
    L16:
        monitor-exit(r02);     // Catch: Throwable -> L14
        return;
    L18:
        monitor-exit(r02);     // Catch: Throwable -> L14
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public final void zaf(GoogleApiClient.ConnectionCallbacks r8) {
        Preconditions.checkNotNull(r8);
        Object r0 = this.zai;
        monitor-enter(r0);
        ArrayList r3 = this.zac;     // Catch: Throwable -> L7
        if (r3.contains(r8) == false) goto L9;
        String r4 = String.valueOf(r8);     // Catch: Throwable -> L7
        StringBuilder r6 = new StringBuilder(r4.length() + 62);     // Catch: Throwable -> L7
        r6.append("registerConnectionCallbacks(): listener ");     // Catch: Throwable -> L7
        r6.append(r4);     // Catch: Throwable -> L7
        r6.append(" is already registered");     // Catch: Throwable -> L7
        Log.w("GmsClientEvents", r6.toString());     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        if (this.zab.isConnected() == false) goto L19;
        Handler r7 = this.zah;
        r7.sendMessage(r7.obtainMessage(1, r8));
        return;
    L19:
        return;
    L9:
        r3.add(r8);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public final void zai(GoogleApiClient.OnConnectionFailedListener r6) {
        Preconditions.checkNotNull(r6);
        Object r0 = this.zai;
        monitor-enter(r0);
        ArrayList r5 = this.zad;     // Catch: Throwable -> L7
        if (r5.contains(r6) == false) goto L9;
        String r62 = String.valueOf(r6);     // Catch: Throwable -> L7
        StringBuilder r4 = new StringBuilder(r62.length() + 67);     // Catch: Throwable -> L7
        r4.append("registerConnectionFailedListener(): listener ");     // Catch: Throwable -> L7
        r4.append(r62);     // Catch: Throwable -> L7
        r4.append(" is already registered");     // Catch: Throwable -> L7
        Log.w("GmsClientEvents", r4.toString());     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        r5.add(r6);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public final void zak(GoogleApiClient.OnConnectionFailedListener r6) {
        Preconditions.checkNotNull(r6);
        Object r0 = this.zai;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zad.remove(r6) == true) goto L9;
        String r62 = String.valueOf(r6);     // Catch: Throwable -> L7
        StringBuilder r4 = new StringBuilder(r62.length() + 57);     // Catch: Throwable -> L7
        r4.append("unregisterConnectionFailedListener(): listener ");     // Catch: Throwable -> L7
        r4.append(r62);     // Catch: Throwable -> L7
        r4.append(" not found");     // Catch: Throwable -> L7
        Log.w("GmsClientEvents", r4.toString());     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }
}
