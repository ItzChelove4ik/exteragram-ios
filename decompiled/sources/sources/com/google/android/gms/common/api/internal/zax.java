package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zax implements zabv {
    private final Context zaa;
    private final zaaz zab;
    private final Looper zac;
    private final zabd zad;
    private final zabd zae;
    private final Map zaf;
    private final Set zag;
    private final Api.Client zah;
    private Bundle zai;
    private ConnectionResult zaj;
    private ConnectionResult zak;
    private boolean zal;
    private final Lock zam;
    private int zan;

    private zax(Context r15, zaaz r16, Lock r17, Looper r18, GoogleApiAvailabilityLight r19, Map r20, Map r21, ClientSettings r22, Api.AbstractClientBuilder r23, Api.Client r24, ArrayList r25, ArrayList r26, Map r27, Map r28) {
        this.zag = Collections.newSetFromMap(new WeakHashMap());
        this.zaj = null;
        this.zak = null;
        this.zal = false;
        this.zan = 0;
        this.zaa = r15;
        this.zab = r16;
        this.zam = r17;
        this.zac = r18;
        this.zah = r24;
        this.zad = new zabd(r15, r16, r17, r18, r19, r21, null, r28, null, r26, new zav(this, null));
        this.zae = new zabd(r15, r16, r17, r18, r19, r20, r22, r27, r23, r25, new zaw(this, null));
        ArrayMap r152 = new ArrayMap();
        Iterator r0 = r21.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r152.put((Api.AnyClientKey) r0.next(), this.zad);
        goto L4
    L6:
        Iterator r02 = r20.keySet().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        r152.put((Api.AnyClientKey) r02.next(), this.zae);
        goto L8
    L10:
        this.zaf = Collections.unmodifiableMap(r152);
    }

    private final void zaA() {
        Set r2 = this.zag;
        Iterator r0 = r2.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((SignInConnectionListener) r0.next()).onComplete();
        goto L4
    L6:
        r2.clear();
    }

    private final boolean zaB() {
        ConnectionResult r1 = this.zak;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getErrorCode() != 4) goto L10;
        return true;
    L10:
        return false;
    }

    private final boolean zaC(BaseImplementation$ApiMethodImpl r2) {
        zabd r22 = (zabd) this.zaf.get(r2.getClientKey());
        Preconditions.checkNotNull(r22, "GoogleApiClient is not configured to use the API required for this call.");
        return r22.equals(this.zae);
    }

    private final PendingIntent zaD() {
        Api.Client r0 = this.zah;
        if (r0 != null) goto L7;
        return null;
    L7:
        return com.google.android.gms.internal.base.zak.zaa(this.zaa, System.identityHashCode(this.zab), r0.getSignInIntent(), com.google.android.gms.internal.base.zak.zaa | 134217728);
    }

    private static boolean zaE(ConnectionResult r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.isSuccess() == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static zax zaa(Context r15, zaaz r16, Lock r17, Looper r18, GoogleApiAvailabilityLight r19, Map r20, ClientSettings r21, Map r22, Api.AbstractClientBuilder r23, ArrayList r24) {
        ArrayMap r6 = new ArrayMap();
        ArrayMap r7 = new ArrayMap();
        Iterator r1 = r20.entrySet().iterator();
        Api.Client r10 = null;
    L4:
        if (r1.hasNext() == false) goto L12;
        Map.Entry r3 = (Map.Entry) r1.next();
        Api.Client r5 = (Api.Client) r3.getValue();
        if (true != r5.providesSignIn()) goto L9;
        r10 = r5;
    L9:
        if (r5.requiresSignIn() == true) goto L10;
        r7.put((Api.AnyClientKey) r3.getKey(), r5);
        goto L4
    L10:
        r6.put((Api.AnyClientKey) r3.getKey(), r5);
        goto L4
    L12:
        Preconditions.checkState(!r6.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap r13 = new ArrayMap();
        ArrayMap r14 = new ArrayMap();
        Iterator r12 = r22.keySet().iterator();
    L14:
        if (r12.hasNext() == false) goto L23;
        Api r32 = (Api) r12.next();
        Api.AnyClientKey r4 = r32.zac();
        if (r6.containsKey(r4) == true) goto L17;
        if (r7.containsKey(r4) == false) goto L21;
        r14.put(r32, (Boolean) r22.get(r32));
        goto L14
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("Each API in the isOptionalMap must have a corresponding client in the clients map.");
        return null;
    L17:
        r13.put(r32, (Boolean) r22.get(r32));
        goto L14
    L23:
        ArrayList r11 = new ArrayList();
        ArrayList r122 = new ArrayList();
        int r0 = r24.size();
        int r110 = 0;
    L24:
        if (r110 >= r0) goto L35;
        zas r42 = (zas) r24.get(r110);
        Api r52 = r42.zaa;
        if (r13.containsKey(r52) == false) goto L29;
        r11.add(r42);
    L31:
        r110 = r110 + 1;
        goto L24
    L29:
        if (r14.containsKey(r52) == false) goto L32;
        r122.add(r42);
        goto L31
    L32:
        Segment$$ExternalSyntheticBUOutline1.m("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
        return null;
    L35:
        return new zax(r15, r16, r17, r18, r19, r6, r7, r21, r23, r10, r11, r122, r13, r14);
    }

    private final void zaz(ConnectionResult r3) {
        int r0 = this.zan;
        if (r0 != 1) goto L5;
    L8:
        zaA();
    L9:
        this.zan = 0;
        return;
    L5:
        if (r0 == 2) goto L7;
        Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        goto L9
    L7:
        this.zab.zab(r3);
        goto L8
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final BaseImplementation$ApiMethodImpl zab(BaseImplementation$ApiMethodImpl r4) {
        if (zaC(r4) == true) goto L5;
        this.zad.zab(r4);
        return r4;
    L5:
        if (zaB() == false) goto L8;
        r4.setFailedResult(new Status(4, null, zaD()));
        return r4;
    L8:
        this.zae.zab(r4);
        return r4;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final BaseImplementation$ApiMethodImpl zac(BaseImplementation$ApiMethodImpl r4) {
        if (zaC(r4) == false) goto L11;
        if (zaB() == false) goto L9;
        r4.setFailedResult(new Status(4, null, zaD()));
        return r4;
    L9:
        return this.zae.zac(r4);
    L11:
        return this.zad.zac(r4);
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zae() {
        this.zan = 2;
        this.zal = false;
        this.zak = null;
        this.zaj = null;
        this.zad.zae();
        this.zae.zae();
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zah() {
        this.zak = null;
        this.zaj = null;
        this.zan = 0;
        this.zad.zah();
        this.zae.zah();
        zaA();
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final boolean zai() {
        this.zam.lock();
        boolean r1 = false;
        if (this.zad.zai() == true) goto L6;
    L14:
        this.zam.unlock();
        return r1;
    L6:
        if (this.zae.zai() == false) goto L8;
    L11:
        r1 = true;
        goto L14
    L8:
        if (zaB() == true) goto L11;
        if (this.zan != 1) goto L14;
    L12:
        th = move-exception;
        this.zam.unlock();
        throw th;
    }

    public final boolean zaj() {
        this.zam.lock();
        if (this.zan != 2) goto L7;
        boolean r0 = true;
    L8:
        this.zam.unlock();
        return r0;
    L7:
        r0 = false;
    L10:
        th = move-exception;
        this.zam.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final boolean zak(SignInConnectionListener r4) {
        this.zam.lock();
        boolean r1 = false;
        if (zaj() == false) goto L6;
    L10:
        zabd r0 = this.zae;     // Catch: Throwable -> L8
        if (r0.zai() == true) goto L16;
        this.zag.add(r4);     // Catch: Throwable -> L8
        r1 = true;
        if (this.zan != 0) goto L15;
        this.zan = 1;     // Catch: Throwable -> L8
    L15:
        this.zak = null;     // Catch: Throwable -> L8
        r0.zae();     // Catch: Throwable -> L8
    L16:
        this.zam.unlock();
        return r1;
    L6:
        if (zai() == false) goto L16;
    L8:
        th = move-exception;
        this.zam.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zam() {
        this.zam.lock();
        boolean r0 = zaj();     // Catch: Throwable -> L6
        this.zae.zah();     // Catch: Throwable -> L6
        this.zak = new ConnectionResult(4);     // Catch: Throwable -> L6
        if (r0 == false) goto L8;
        new com.google.android.gms.internal.base.zao(this.zac).post(new zau(this));     // Catch: Throwable -> L6
    L9:
        this.zam.unlock();
        return;
    L8:
        zaA();     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        this.zam.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabv
    public final void zan(String r5, FileDescriptor r6, PrintWriter r7, String[] r8) {
        r7.append(r5).append("authClient").println(":");
        String r0 = String.valueOf(r5);
        this.zae.zan(r0.concat("  "), r6, r7, r8);
        r7.append(r5).append("anonClient").println(":");
        String r52 = String.valueOf(r5);
        this.zad.zan(r52.concat("  "), r6, r7, r8);
    }

    public final /* synthetic */ void zao() {
        if (zaE(this.zaj) == false) goto L27;
        if (zaE(this.zak) == false) goto L7;
    L17:
        int r0 = this.zan;
        if (r0 != 1) goto L20;
    L23:
        zaA();
    L24:
        this.zan = 0;
        return;
    L20:
        if (r0 == 2) goto L22;
        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
        goto L24
    L22:
        ((zaaz) Preconditions.checkNotNull(this.zab)).zaa(this.zai);
        goto L23
    L7:
        if (zaB() == true) goto L17;
        ConnectionResult r02 = this.zak;
        if (r02 != null) goto L12;
        return;
    L12:
        if (this.zan != 1) goto L15;
        zaA();
        return;
    L15:
        zaz(r02);
        this.zad.zah();
        return;
    L27:
        if (this.zaj != null) goto L29;
    L33:
        ConnectionResult r03 = this.zaj;
        if (r03 == null) goto L43;
        ConnectionResult r1 = this.zak;
        if (r1 == null) goto L44;
        zabd r2 = this.zae;
        zabd r3 = this.zad;
        if (r2.zaf >= r3.zaf) goto L40;
        r03 = r1;
    L40:
        zaz(r03);
        return;
    L44:
        return;
    L43:
        return;
    L29:
        if (zaE(this.zak) == false) goto L33;
        this.zae.zah();
        zaz((ConnectionResult) Preconditions.checkNotNull(this.zaj));
    }

    public final /* synthetic */ void zap(int r2, boolean r3) {
        this.zab.zac(r2, r3);
        this.zak = null;
        this.zaj = null;
    }

    public final /* synthetic */ void zaq(Bundle r2) {
        Bundle r0 = this.zai;
        if (r0 != null) goto L6;
        this.zai = r2;
        return;
    L6:
        if (r2 == null) goto L9;
        r0.putAll(r2);
        return;
    }

    public final /* synthetic */ zabd zar() {
        return this.zad;
    }

    public final /* synthetic */ zabd zas() {
        return this.zae;
    }

    public final /* synthetic */ void zat(ConnectionResult r1) {
        this.zaj = r1;
    }

    public final /* synthetic */ ConnectionResult zau() {
        return this.zak;
    }

    public final /* synthetic */ void zav(ConnectionResult r1) {
        this.zak = r1;
    }

    public final /* synthetic */ boolean zaw() {
        return this.zal;
    }

    public final /* synthetic */ void zax(boolean r1) {
        this.zal = r1;
    }

    public final /* synthetic */ Lock zay() {
        return this.zam;
    }
}
