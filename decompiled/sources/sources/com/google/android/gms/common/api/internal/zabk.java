package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import androidx.collection.ArrayMap;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zabk implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zat {
    final /* synthetic */ GoogleApiManager zaa;
    private final Queue zab;
    private final Api.Client zac;
    private final ApiKey zad;
    private final zaaa zae;
    private final Set zaf;
    private final Map zag;
    private final int zah;
    private final zacm zai;
    private boolean zaj;
    private final List zak;
    private ConnectionResult zal;
    private int zam;

    public zabk(GoogleApiManager r4, GoogleApi r5) {
        Objects.requireNonNull(r4);
        this.zaa = r4;
        this.zab = new LinkedList();
        this.zaf = new HashSet();
        this.zag = new HashMap();
        this.zak = new ArrayList();
        this.zal = null;
        this.zam = 0;
        Api.Client r1 = r5.zaf(r4.zaF().getLooper(), this);
        this.zac = r1;
        this.zad = r5.getApiKey();
        this.zae = new zaaa();
        this.zah = r5.zab();
        if (r1.requiresSignIn() == false) goto L6;
        this.zai = r5.zac(r4.zaz(), r4.zaF());
        return;
    L6:
        this.zai = null;
    }

    private final void zaC() {
        zah();
        zaN(ConnectionResult.RESULT_SUCCESS);
        zaK();
        Iterator r0 = this.zag.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L15;
        RegisterListenerMethod r1 = ((zacd) r0.next()).zaa;
        if (zaO(r1.getRequiredFeatures()) != null) goto L7;
        r1.registerListener(this.zac, new TaskCompletionSource());     // Catch: RemoteException -> L10 RuntimeException -> L12 DeadObjectException -> L14
    L14:
        onConnectionSuspended(3);
        this.zac.disconnect("DeadObjectException thrown while calling register listener method.");
    L10:
        e = e;
    L13:
        Log.e("GoogleApiManager", "Failed to register listener on re-connection.", e);
        r0.remove();
    L12:
        e = e;
        goto L13
    L7:
        r0.remove();
    L15:
        zaF();
        zaL();
    }

    private final void zaD(int r6) {
        zah();
        this.zaj = true;
        String r0 = this.zac.getLastDisconnectMessage();
        this.zae.zae(r6, r0);
        ApiKey r62 = this.zad;
        GoogleApiManager r02 = this.zaa;
        Handler r1 = r02.zaF();
        r02.zaF().sendMessageDelayed(Message.obtain(r1, 9, r62), 5000);
        Handler r12 = r02.zaF();
        r02.zaF().sendMessageDelayed(Message.obtain(r12, 11, r62), 120000);
        r02.zaB().zac();
        Iterator r5 = this.zag.values().iterator();
    L4:
        if (r5.hasNext() == false) goto L6;
        ((zacd) r5.next()).zac.run();
        goto L4
    }

    private final boolean zaE(ConnectionResult r5) {
        Object r0 = GoogleApiManager.zay();
        monitor-enter(r0);
        GoogleApiManager r1 = this.zaa;     // Catch: Throwable -> L11
        if (r1.zaD() != null) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return false;
    L7:
        if (r1.zaE().contains(this.zad) == false) goto L13;
        r1.zaD().zaf(r5, this.zah);     // Catch: Throwable -> L11
        r1.zaA().zae(r1.zaz(), r5, true);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return true;
    L11:
        th = move-exception;
        throw th;
    }

    private final void zaF() {
        Queue r1 = this.zab;
        ArrayList r0 = new ArrayList(r1);
        int r2 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L11;
        zai r4 = (zai) r0.get(r3);
        if (this.zac.isConnected() == false) goto L16;
        if (zaG(r4) == false) goto L10;
        r1.remove(r4);
    L10:
        r3 = r3 + 1;
        goto L3
    L16:
        return;
    }

    private final boolean zaG(zai r12) {
        if ((r12 instanceof zac) == true) goto L6;
        zaH(r12);
        return true;
    L6:
        zac r0 = (zac) r12;
        Feature r2 = zaO(r0.zaa(this));
        if (r2 != null) goto L10;
        zaH(r12);
        return true;
    L10:
        String r122 = this.zac.getClass().getName();
        String r3 = r2.getName();
        long r4 = r2.getVersion();
        int r6 = r122.length();
        int r7 = String.valueOf(r3).length();
        StringBuilder r9 = new StringBuilder(((((r6 + 53) + r7) + 2) + String.valueOf(r4).length()) + 2);
        r9.append(r122);
        r9.append(" could not execute call because it requires feature (");
        r9.append(r3);
        r9.append(", ");
        r9.append(r4);
        r9.append(").");
        Log.w("GoogleApiManager", r9.toString());
        GoogleApiManager r123 = this.zaa;
        if (r123.zaG() == true) goto L13;
    L25:
        r0.zae(new UnsupportedApiCallException(r2));
        return true;
    L13:
        if (r0.zab(this) == false) goto L25;
        int r02 = r0.zac(this);
        zabl r42 = new zabl(this.zad, r2, null);
        List r1 = this.zak;
        int r62 = r1.indexOf(r42);
        if (r62 < 0) goto L17;
        zabl r11 = (zabl) r1.get(r62);
        r123.zaF().removeMessages(15, r11);
        Message r112 = Message.obtain(r123.zaF(), 15, r11);
        r123.zaF().sendMessageDelayed(r112, 5000);
        return false;
    L17:
        r1.add(r42);
        Message r13 = Message.obtain(r123.zaF(), 15, r42);
        r123.zaF().sendMessageDelayed(r13, 5000);
        Message r14 = Message.obtain(r123.zaF(), 16, r42);
        r123.zaF().sendMessageDelayed(r14, 120000);
        ConnectionResult r15 = new ConnectionResult(2, null, null, Integer.valueOf(r02));
        if (zaE(r15) == false) goto L20;
        String r113 = r2.getName();
        long r03 = r2.getVersion();
        int r124 = String.valueOf(r113).length();
        StringBuilder r5 = new StringBuilder((r124 + 61) + String.valueOf(r03).length());
        r5.append("A dialog should be displayed for missing feature: ");
        r5.append(r113);
        r5.append(", version: ");
        r5.append(r03);
        Log.w("GoogleApiManager", r5.toString());
        return false;
    L20:
        if (r123.zap(r15, this.zah) == false) goto L28;
        String r114 = r2.getName();
        long r04 = r2.getVersion();
        int r125 = String.valueOf(r114).length();
        StringBuilder r52 = new StringBuilder((r125 + 55) + String.valueOf(r04).length());
        r52.append("Notification displayed for missing feature: ");
        r52.append(r114);
        r52.append(", version: ");
        r52.append(r04);
        Log.w("GoogleApiManager", r52.toString());
        return false;
    L28:
        return false;
    }

    private final void zaH(zai r3) {
        r3.zaf(this.zae, zap());
        r3.zag(this);     // Catch: DeadObjectException -> L5
        return;
    L5:
        onConnectionSuspended(1);
        this.zac.disconnect("DeadObjectException thrown while running ApiCallRunner.");
    }

    private final void zaI(Status r4, Exception r5, boolean r6) {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        boolean r0 = true;
        if (r4 == null) goto L5;
        boolean r2 = false;
    L6:
        if (r5 == null) goto L8;
        r0 = false;
    L8:
        if (r2 == r0) goto L21;
        Iterator r3 = this.zab.iterator();
    L11:
        if (r3.hasNext() == false) goto L20;
        zai r02 = (zai) r3.next();
        if (r6 == false) goto L16;
        if (r02.zac != 2) goto L11;
    L16:
        if (r4 == null) goto L18;
        r02.zad(r4);
    L19:
        r3.remove();
        goto L11
    L18:
        r02.zae(r5);
        goto L19
    L20:
        return;
    L21:
        f$$ExternalSyntheticBUOutline1.m("Status XOR exception should be null");
        return;
    L5:
        r2 = true;
        goto L6
    }

    private final void zaJ(Status r3) {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        zaI(r3, null, false);
    }

    private final void zaK() {
        if (this.zaj == false) goto L6;
        GoogleApiManager r0 = this.zaa;
        ApiKey r1 = this.zad;
        r0.zaF().removeMessages(11, r1);
        r0.zaF().removeMessages(9, r1);
        this.zaj = false;
        return;
    }

    private final void zaL() {
        ApiKey r0 = this.zad;
        GoogleApiManager r4 = this.zaa;
        r4.zaF().removeMessages(12, r0);
        Handler r1 = r4.zaF();
        r4.zaF().sendMessageDelayed(r1.obtainMessage(12, r0), r4.zaw());
    }

    private final boolean zaM(boolean r3) {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        Api.Client r0 = this.zac;
        if (r0.isConnected() == true) goto L5;
        return false;
    L5:
        if (this.zag.isEmpty() == true) goto L7;
        return false;
    L7:
        if (this.zae.zac() == false) goto L10;
        if (r3 == false) goto L15;
        zaL();
        return false;
    L15:
        return false;
    L10:
        r0.disconnect("Timing out service connection.");
        return true;
    }

    private final void zaN(ConnectionResult r4) {
        Set r0 = this.zaf;
        Iterator r1 = r0.iterator();
        if (r1.hasNext() == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        if (com.google.android.gms.common.internal.Objects.equal(r4, ConnectionResult.RESULT_SUCCESS) == false) goto L8;
        this.zac.getEndpointPackageName();
    L8:
        throw null;
    L9:
        r0.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Feature zaO(Feature[] r9) {
        if (r9 != null) goto L5;
    L23:
        return null;
    L5:
        if (r9.length == 0) goto L23;
        Feature[] r8 = this.zac.getAvailableFeatures();
        int r1 = 0;
        if (r8 != null) goto L10;
        r8 = new Feature[0];
    L10:
        ArrayMap r2 = new ArrayMap(r8.length);
        int r3 = 0;
    L12:
        if (r3 >= r8.length) goto L14;
        Feature r4 = r8[r3];
        r2.put(r4.getName(), Long.valueOf(r4.getVersion()));
        r3 = r3 + 1;
        goto L12
    L14:
        int r82 = r9.length;
    L15:
        if (r1 >= r82) goto L23;
        Feature r32 = r9[r1];
        Long r42 = (Long) r2.get(r32.getName());
        if (r42 == null) goto L22;
        if (r42.longValue() < r32.getVersion()) goto L22;
        r1 = r1 + 1;
    L22:
        return r32;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle r3) {
        GoogleApiManager r32 = this.zaa;
        Handler r0 = r32.zaF();
        if (Looper.myLooper() != r0.getLooper()) goto L6;
        zaC();
        return;
    L6:
        zabg r02 = new zabg(this);
        r32.zaF().post(r02);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r2) {
        zac(r2, null);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int r4) {
        GoogleApiManager r0 = this.zaa;
        Handler r1 = r0.zaF();
        if (Looper.myLooper() != r1.getLooper()) goto L6;
        zaD(r4);
        return;
    L6:
        zabh r12 = new zabh(this, r4);
        r0.zaF().post(r12);
    }

    public final /* synthetic */ ApiKey zaA() {
        return this.zad;
    }

    public final /* synthetic */ boolean zaB() {
        return this.zaj;
    }

    @Override // com.google.android.gms.common.api.internal.zat
    public final void zaa(ConnectionResult r1, Api r2, boolean r3) {
        throw null;
    }

    public final void zab(ConnectionResult r7) {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        Api.Client r0 = this.zac;
        String r1 = r0.getClass().getName();
        String r2 = String.valueOf(r7);
        int r3 = r1.length();
        int r32 = r3 + 25;
        StringBuilder r5 = new StringBuilder(r32 + r2.length());
        r5.append("onSignInFailed for ");
        r5.append(r1);
        r5.append(" with ");
        r5.append(r2);
        r0.disconnect(r5.toString());
        zac(r7, null);
    }

    public final void zac(ConnectionResult r7, Exception r8) {
        GoogleApiManager r0 = this.zaa;
        Preconditions.checkHandlerThread(r0.zaF());
        zacm r1 = this.zai;
        if (r1 == null) goto L5;
        r1.zad();
    L5:
        zah();
        r0.zaB().zac();
        zaN(r7);
        if ((this.zac instanceof com.google.android.gms.common.internal.service.zau) == false) goto L11;
        if (r7.getErrorCode() == 24) goto L11;
        r0.zax(true);
        r0.zaF().sendMessageDelayed(r0.zaF().obtainMessage(19), 300000);
    L11:
        if (r7.getErrorCode() != 4) goto L15;
        zaJ(GoogleApiManager.zav());
        return;
    L15:
        if (r7.getErrorCode() != 25) goto L18;
        zaJ(GoogleApiManager.zau(this.zad, r7));
        return;
    L18:
        Queue r12 = this.zab;
        if (r12.isEmpty() == false) goto L23;
        this.zal = r7;
        return;
    L23:
        if (r8 == null) goto L26;
        Preconditions.checkHandlerThread(r0.zaF());
        zaI(null, r8, false);
        return;
    L26:
        boolean r82 = r0.zaG();
        ApiKey r4 = this.zad;
        if (r82 == false) goto L44;
        zaI(GoogleApiManager.zau(r4, r7), null, true);
        if (r12.isEmpty() == false) goto L32;
        return;
    L32:
        if (zaE(r7) == false) goto L34;
        return;
    L34:
        if (r0.zap(r7, this.zah) == false) goto L36;
        return;
    L36:
        if (r7.getErrorCode() != 18) goto L39;
        this.zaj = true;
    L39:
        if (this.zaj == false) goto L42;
        r0.zaF().sendMessageDelayed(Message.obtain(r0.zaF(), 9, r4), 5000);
        return;
    L42:
        zaJ(GoogleApiManager.zau(r4, r7));
        return;
    L44:
        zaJ(GoogleApiManager.zau(r4, r7));
    }

    public final void zad(zai r2) {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        if (this.zac.isConnected() == true) goto L5;
        this.zab.add(r2);
        ConnectionResult r22 = this.zal;
        if (r22 != null) goto L13;
    L16:
        zam();
        return;
    L13:
        if (r22.hasResolution() == false) goto L16;
        zac(this.zal, null);
        return;
    L5:
        if (zaG(r2) == false) goto L8;
        zaL();
        return;
    L8:
        this.zab.add(r2);
    }

    public final void zae() {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        zaJ(GoogleApiManager.zaa);
        this.zae.zad();
        int r1 = 0;
        ListenerHolder.ListenerKey[] r0 = (ListenerHolder.ListenerKey[]) this.zag.keySet().toArray(new ListenerHolder.ListenerKey[0]);
        int r2 = r0.length;
    L3:
        if (r1 >= r2) goto L5;
        zad(new zah(r0[r1], new TaskCompletionSource()));
        r1 = r1 + 1;
        goto L3
    L5:
        zaN(new ConnectionResult(4));
        Api.Client r02 = this.zac;
        if (r02.isConnected() == false) goto L10;
        r02.onUserSignOut(new zabj(this));
        return;
    }

    public final Api.Client zaf() {
        return this.zac;
    }

    public final Map zag() {
        return this.zag;
    }

    public final void zah() {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        this.zal = null;
    }

    public final void zaj() {
        Preconditions.checkHandlerThread(this.zaa.zaF());
        if (this.zaj == false) goto L6;
        zam();
        return;
    }

    public final void zak() {
        GoogleApiManager r0 = this.zaa;
        Preconditions.checkHandlerThread(r0.zaF());
        if (this.zaj == false) goto L10;
        zaK();
        Context r1 = r0.zaz();
        if (r0.zaA().isGooglePlayServicesAvailable(r1) != 18) goto L7;
        Status r02 = new Status(21, "Connection timed out waiting for Google Play services update to complete.");
    L8:
        zaJ(r02);
        this.zac.disconnect("Timing out connection while resuming.");
        return;
    L7:
        r02 = new Status(22, "API failed to connect while resuming due to an unknown error.");
        goto L8
    }

    public final boolean zal() {
        return zaM(true);
    }

    public final void zam() {
        GoogleApiManager r0 = this.zaa;
        Preconditions.checkHandlerThread(r0.zaF());
        Api.Client r3 = this.zac;
        if (r3.isConnected() == false) goto L5;
        return;
    L5:
        if (r3.isConnecting() == false) goto L26;
        return;
    L26:
        int r5 = r0.zaB().zaa(r0.zaz(), r3);     // Catch: IllegalStateException -> L12
        if (r5 == 0) goto L14;
        ConnectionResult r02 = new ConnectionResult(r5, null);     // Catch: IllegalStateException -> L12
        String r6 = this.zac.getClass().getName();     // Catch: IllegalStateException -> L12
        String r7 = r02.toString();     // Catch: IllegalStateException -> L12
        StringBuilder r9 = new StringBuilder((r6.length() + 35) + r7.length());     // Catch: IllegalStateException -> L12
        r9.append("The service for ");     // Catch: IllegalStateException -> L12
        r9.append(r6);     // Catch: IllegalStateException -> L12
        r9.append(" is not available: ");     // Catch: IllegalStateException -> L12
        r9.append(r7);     // Catch: IllegalStateException -> L12
        Log.w("GoogleApiManager", r9.toString());     // Catch: IllegalStateException -> L12
        zac(r02, null);     // Catch: IllegalStateException -> L12
        return;
    L14:
        zabn r2 = new zabn(r0, r3, this.zad);
        if (r3.requiresSignIn() == false) goto L24;
        ((zacm) Preconditions.checkNotNull(this.zai)).zac(r2);
    L24:
        r3.connect(r2);     // Catch: SecurityException -> L19
        return;
    L19:
        e = move-exception;
        zac(new ConnectionResult(10), e);
        return;
    L12:
        e = move-exception;
        zac(new ConnectionResult(10), e);
    }

    public final boolean zap() {
        return this.zac.requiresSignIn();
    }

    public final int zaq() {
        return this.zah;
    }

    public final int zar() {
        return this.zam;
    }

    public final void zas() {
        this.zam++;
    }

    public final /* synthetic */ void zat() {
        zaC();
    }

    public final /* synthetic */ void zau(int r1) {
        zaD(r1);
    }

    public final /* synthetic */ void zav(Status r1) {
        zaJ(r1);
    }

    public final /* synthetic */ boolean zaw(boolean r1) {
        return zaM(false);
    }

    public final /* synthetic */ void zax(zabl r2) {
        if (this.zak.contains(r2) == true) goto L6;
        return;
    L6:
        if (this.zaj == false) goto L8;
        return;
    L8:
        if (this.zac.isConnected() == true) goto L11;
        zam();
        return;
    L11:
        zaF();
    }

    public final /* synthetic */ void zay(zabl r6) {
        if (this.zak.remove(r6) == false) goto L17;
        GoogleApiManager r0 = this.zaa;
        r0.zaF().removeMessages(15, r6);
        r0.zaF().removeMessages(16, r6);
        Feature r62 = r6.zab();
        Queue r02 = this.zab;
        ArrayList r1 = new ArrayList(r02.size());
        Iterator r2 = r02.iterator();
    L6:
        if (r2.hasNext() == false) goto L14;
        zai r3 = (zai) r2.next();
        if ((r3 instanceof zac) == false) goto L6;
        Feature[] r4 = ((zac) r3).zaa(this);
        if (r4 == null) goto L6;
        if (ArrayUtils.contains(r4, r62) == false) goto L6;
        r1.add(r3);
        goto L6
    L14:
        int r5 = r1.size();
        int r22 = 0;
    L15:
        if (r22 >= r5) goto L28;
        zai r32 = (zai) r1.get(r22);
        r02.remove(r32);
        r32.zae(new UnsupportedApiCallException(r62));
        r22 = r22 + 1;
        goto L15
    L28:
        return;
    }

    public final /* synthetic */ Api.Client zaz() {
        return this.zac;
    }
}
