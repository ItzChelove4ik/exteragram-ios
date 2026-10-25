package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes4.dex */
public final class zabd implements zabv, zat {
    final Map zaa;
    final Map zab;
    final ClientSettings zac;
    final Map zad;
    final Api.AbstractClientBuilder zae;
    int zaf;
    final zaaz zag;
    final zabu zah;
    private final Lock zai;
    private final Condition zaj;
    private final Context zak;
    private final GoogleApiAvailabilityLight zal;
    private final zabc zam;
    private volatile zaba zan;
    private ConnectionResult zao;

    public zabd(Context r2, zaaz r3, Lock r4, Looper r5, GoogleApiAvailabilityLight r6, Map r7, ClientSettings r8, Map r9, Api.AbstractClientBuilder r10, ArrayList r11, zabu r12) {
        this.zab = new HashMap();
        this.zao = null;
        this.zak = r2;
        this.zai = r4;
        this.zal = r6;
        this.zaa = r7;
        this.zac = r8;
        this.zad = r9;
        this.zae = r10;
        this.zag = r3;
        this.zah = r12;
        int r22 = r11.size();
        int r32 = 0;
    L3:
        if (r32 >= r22) goto L5;
        ((zas) r11.get(r32)).zaa(this);
        r32 = r32 + 1;
        goto L3
    L5:
        this.zam = new zabc(this, r5);
        this.zaj = r4.newCondition();
        this.zan = new zaas(this);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle r2) {
        this.zai.lock();
        this.zan.zaf(r2);     // Catch: Throwable -> L6
        this.zai.unlock();
        return;
    L6:
        th = move-exception;
        this.zai.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int r2) {
        this.zai.lock();
        this.zan.zah(r2);     // Catch: Throwable -> L6
        this.zai.unlock();
        return;
    L6:
        th = move-exception;
        this.zai.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zat
    public final void zaa(ConnectionResult r2, Api r3, boolean r4) {
        this.zai.lock();
        this.zan.zag(r2, r3, r4);     // Catch: Throwable -> L6
        this.zai.unlock();
        return;
    L6:
        th = move-exception;
        this.zai.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final BaseImplementation$ApiMethodImpl zab(BaseImplementation$ApiMethodImpl r1) {
        r1.zak();
        this.zan.zab(r1);
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final BaseImplementation$ApiMethodImpl zac(BaseImplementation$ApiMethodImpl r1) {
        r1.zak();
        return this.zan.zac(r1);
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zae() {
        this.zan.zae();
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zah() {
        if (this.zan.zad() == false) goto L6;
        this.zab.clear();
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final boolean zai() {
        return this.zan instanceof zaag;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final boolean zak(SignInConnectionListener r1) {
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zam() {
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zan(String r6, FileDescriptor r7, PrintWriter r8, String[] r9) {
        r8.append(r6).append("mState=").println(this.zan);
        Iterator r0 = this.zad.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        String r1 = String.valueOf(r6);
        Api r2 = (Api) r0.next();
        r8.append(r6).append(r2.zad()).println(":");
        ((Api.Client) Preconditions.checkNotNull((Api.Client) this.zaa.get(r2.zac()))).dump(r1.concat("  "), r7, r8, r9);
        goto L4
    }

    public final void zao() {
        Lock r6 = this.zai;
        r6.lock();
        zabd r1 = this;
        r1.zan = new zaar(r1, this.zac, this.zad, this.zal, this.zae, r6, this.zak);     // Catch: Throwable -> L8
        r1.zan.zaa();     // Catch: Throwable -> L8
        r1.zaj.signalAll();     // Catch: Throwable -> L8
        r1.zai.unlock();
        return;
    L8:
        th = th;
    L9:
        Throwable r8 = th;
        r1.zai.unlock();
        throw r8;
    L10:
        th = th;
        r1 = this;
        goto L9
    }

    public final void zap() {
        this.zai.lock();
        this.zag.zad();     // Catch: Throwable -> L6
        this.zan = new zaag(this);     // Catch: Throwable -> L6
        this.zan.zaa();     // Catch: Throwable -> L6
        this.zaj.signalAll();     // Catch: Throwable -> L6
        this.zai.unlock();
        return;
    L6:
        th = move-exception;
        this.zai.unlock();
        throw th;
    }

    public final void zaq(ConnectionResult r2) {
        this.zai.lock();
        this.zao = r2;     // Catch: Throwable -> L6
        this.zan = new zaas(this);     // Catch: Throwable -> L6
        this.zan.zaa();     // Catch: Throwable -> L6
        this.zaj.signalAll();     // Catch: Throwable -> L6
        this.zai.unlock();
        return;
    L6:
        th = move-exception;
        this.zai.unlock();
        throw th;
    }

    public final void zar(zabb r2) {
        zabc r1 = this.zam;
        r1.sendMessage(r1.obtainMessage(1, r2));
    }

    public final void zas(RuntimeException r2) {
        zabc r1 = this.zam;
        r1.sendMessage(r1.obtainMessage(2, r2));
    }

    public final /* synthetic */ Lock zat() {
        return this.zai;
    }

    public final /* synthetic */ zaba zau() {
        return this.zan;
    }
}
