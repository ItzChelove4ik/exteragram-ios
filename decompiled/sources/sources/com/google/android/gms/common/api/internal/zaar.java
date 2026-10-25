package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* loaded from: classes4.dex */
public final class zaar implements zaba {
    private final zabd zaa;
    private final Lock zab;
    private final Context zac;
    private final GoogleApiAvailabilityLight zad;
    private ConnectionResult zae;
    private int zaf;
    private int zag;
    private int zah;
    private final Bundle zai;
    private final Set zaj;
    private com.google.android.gms.signin.zae zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private IAccountAccessor zao;
    private boolean zap;
    private boolean zaq;
    private final ClientSettings zar;
    private final Map zas;
    private final Api.AbstractClientBuilder zat;
    private final ArrayList zau;

    public zaar(zabd r2, ClientSettings r3, Map r4, GoogleApiAvailabilityLight r5, Api.AbstractClientBuilder r6, Lock r7, Context r8) {
        this.zag = 0;
        this.zai = new Bundle();
        this.zaj = new HashSet();
        this.zau = new ArrayList();
        this.zaa = r2;
        this.zar = r3;
        this.zas = r4;
        this.zad = r5;
        this.zat = r6;
        this.zab = r7;
        this.zac = r8;
    }

    private final void zaA() {
        if (this.zah == 0) goto L6;
        return;
    L6:
        if (this.zam == true) goto L8;
    L9:
        ArrayList r0 = new ArrayList();
        this.zag = 1;
        zabd r1 = this.zaa;
        Map r2 = r1.zaa;
        this.zah = r2.size();
        Iterator r3 = r2.keySet().iterator();
    L11:
        if (r3.hasNext() == false) goto L19;
        Api.AnyClientKey r4 = (Api.AnyClientKey) r3.next();
        if (r1.zab.containsKey(r4) == true) goto L15;
        r0.add((Api.Client) r2.get(r4));
        goto L11
    L15:
        if (zaz() == false) goto L11;
        zaB();
        goto L11
    L19:
        if (r0.isEmpty() == true) goto L31;
        this.zau.add(zabe.zaa().submit(new zaam(this, r0)));
        return;
    L31:
        return;
    L8:
        if (this.zan == true) goto L9;
    }

    private final void zaB() {
        zabd r0 = this.zaa;
        r0.zap();
        zabe.zaa().execute(new zaah(this));
        com.google.android.gms.signin.zae r1 = this.zak;
        if (r1 != null) goto L5;
    L8:
        Iterator r12 = r0.zab.keySet().iterator();
    L10:
        if (r12.hasNext() == false) goto L12;
        Api.AnyClientKey r2 = (Api.AnyClientKey) r12.next();
        ((Api.Client) Preconditions.checkNotNull((Api.Client) r0.zaa.get(r2))).disconnect();
        goto L10
    L12:
        Bundle r4 = this.zai;
        if (true != r4.isEmpty()) goto L15;
        r4 = null;
    L15:
        r0.zah.zaa(r4);
        return;
    L5:
        if (this.zap == false) goto L7;
        r1.zab((IAccountAccessor) Preconditions.checkNotNull(this.zao), this.zaq);
    L7:
        zaG(false);
        goto L8
    }

    private final void zaC(ConnectionResult r3, Api r4, boolean r5) {
        int r0 = r4.zaa().getPriority();
        if (r5 == false) goto L10;
        if (r3.hasResolution() == true) goto L10;
        if (this.zad.getErrorResolutionIntent(r3.getErrorCode()) != null) goto L10;
    L14:
        zabd r2 = this.zaa;
        Api.AnyClientKey r42 = r4.zac();
        r2.zab.put(r42, r3);
        return;
    L10:
        if (this.zae != null) goto L12;
    L13:
        this.zae = r3;
        this.zaf = r0;
        goto L14
    L12:
        if (r0 >= this.zaf) goto L14;
        goto L13
    }

    private final void zaD() {
        this.zam = false;
        zabd r0 = this.zaa;
        zaaz r1 = r0.zag;
        r1.zad = Collections.EMPTY_SET;
        Iterator r6 = this.zaj.iterator();
    L4:
        if (r6.hasNext() == false) goto L8;
        Api.AnyClientKey r12 = (Api.AnyClientKey) r6.next();
        Map r2 = r0.zab;
        if (r2.containsKey(r12) == true) goto L4;
        r2.put(r12, new ConnectionResult(17, null));
        goto L4
    }

    private final boolean zaE(ConnectionResult r1) {
        if (this.zal == true) goto L5;
        return false;
    L5:
        if (r1.hasResolution() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private final void zaF(ConnectionResult r2) {
        zaH();
        zaG(!r2.hasResolution());
        zabd r1 = this.zaa;
        r1.zaq(r2);
        r1.zah.zab(r2);
    }

    private final void zaG(boolean r3) {
        com.google.android.gms.signin.zae r0 = this.zak;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isConnected() == false) goto L8;
        if (r3 == false) goto L8;
        r0.zac();
    L8:
        r0.disconnect();
        ClientSettings r32 = (ClientSettings) Preconditions.checkNotNull(this.zar);
        this.zao = null;
    }

    private final void zaH() {
        ArrayList r4 = this.zau;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        ((Future) r4.get(r1)).cancel(true);
        r1 = r1 + 1;
        goto L3
    L5:
        r4.clear();
    }

    private final boolean zaI(int r6) {
        if (this.zag == r6) goto L6;
        Log.w("GACConnecting", this.zaa.zag.zae());
        Log.w("GACConnecting", "Unexpected callback in ".concat(toString()));
        int r0 = this.zah;
        StringBuilder r3 = new StringBuilder(String.valueOf(r0).length() + 22);
        r3.append("mRemainingConnections=");
        r3.append(r0);
        Log.w("GACConnecting", r3.toString());
        String r02 = zaJ(this.zag);
        String r62 = zaJ(r6);
        StringBuilder r32 = new StringBuilder((r02.length() + 70) + r62.length());
        r32.append("GoogleApiClient connecting is in step ");
        r32.append(r02);
        r32.append(" but received callback for step ");
        r32.append(r62);
        Log.e("GACConnecting", r32.toString(), new Exception());
        zaF(new ConnectionResult(8, null));
        return false;
    L6:
        return true;
    }

    private static final String zaJ(int r0) {
        if (r0 == 0) goto L5;
        return "STEP_GETTING_REMOTE_SERVICE";
    L5:
        return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    private final boolean zaz() {
        int r0 = this.zah - 1;
        this.zah = r0;
        if (r0 <= 0) goto L5;
        return false;
    L5:
        if (r0 >= 0) goto L8;
        Log.w("GACConnecting", this.zaa.zag.zae());
        Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
        zaF(new ConnectionResult(8, null));
        return false;
    L8:
        ConnectionResult r02 = this.zae;
        if (r02 == null) goto L12;
        this.zaa.zaf = this.zaf;
        zaF(r02);
        return false;
    L12:
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaa() {
        zabd r0 = this.zaa;
        r0.zab.clear();
        this.zam = false;
        this.zae = null;
        this.zag = 0;
        this.zal = true;
        this.zan = false;
        this.zap = false;
        HashMap r4 = new HashMap();
        Map r5 = this.zas;
        Iterator r6 = r5.keySet().iterator();
        boolean r7 = false;
    L4:
        if (r6.hasNext() == false) goto L16;
        Api r8 = (Api) r6.next();
        Api.Client r9 = (Api.Client) Preconditions.checkNotNull((Api.Client) r0.zaa.get(r8.zac()));
        if (r8.zaa().getPriority() != 1) goto L8;
        boolean r10 = true;
    L9:
        r7 = r7 | r10;
        boolean r102 = ((Boolean) r5.get(r8)).booleanValue();
        if (r9.requiresSignIn() == false) goto L15;
        this.zam = true;
        if (r102 == false) goto L14;
        this.zaj.add(r8.zac());
        goto L15
    L14:
        this.zal = false;
    L15:
        r4.put(r9, new zaai(this, r8, r102));
        goto L4
    L8:
        r10 = false;
        goto L9
    L16:
        if (r7 == false) goto L19;
        this.zam = false;
    L19:
        if (this.zam == false) goto L21;
        ClientSettings r82 = this.zar;
        Preconditions.checkNotNull(r82);
        Api.AbstractClientBuilder r52 = this.zat;
        Preconditions.checkNotNull(r52);
        zaaz r1 = r0.zag;
        r82.zae(Integer.valueOf(System.identityHashCode(r1)));
        zaap r103 = new zaap(this, null);
        this.zak = (com.google.android.gms.signin.zae) r52.buildClient(this.zac, r1.getLooper(), r82, r82.zac(), r103, r103);
    L21:
        this.zah = r0.zaa.size();
        this.zau.add(zabe.zaa().submit(new zaal(this, r4)));
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zab(BaseImplementation$ApiMethodImpl r1) {
        this.zaa.zag.zaa.add(r1);
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zac(BaseImplementation$ApiMethodImpl r1) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final boolean zad() {
        zaH();
        zaG(true);
        this.zaa.zaq(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zae() {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaf(Bundle r2) {
        if (zaI(1) == false) goto L12;
        if (r2 == null) goto L8;
        this.zai.putAll(r2);
    L8:
        if (zaz() == false) goto L11;
        zaB();
        return;
    L11:
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zag(ConnectionResult r2, Api r3, boolean r4) {
        if (zaI(1) == false) goto L10;
        zaC(r2, r3, r4);
        if (zaz() == false) goto L9;
        zaB();
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zah(int r3) {
        zaF(new ConnectionResult(8, null));
    }

    public final /* synthetic */ boolean zai() {
        return zaz();
    }

    public final /* synthetic */ void zaj(com.google.android.gms.signin.internal.zak r5) {
        if (zaI(0) == true) goto L5;
        return;
    L5:
        ConnectionResult r0 = r5.zaa();
        if (r0.isSuccess() == false) goto L14;
        com.google.android.gms.common.internal.zay r52 = (com.google.android.gms.common.internal.zay) Preconditions.checkNotNull(r5.zab());
        ConnectionResult r02 = r52.zab();
        if (r02.isSuccess() == true) goto L11;
        String r53 = String.valueOf(r02);
        Exception r1 = new Exception();
        Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: ".concat(r53), r1);
        zaF(r02);
        return;
    L11:
        this.zan = true;
        this.zao = (IAccountAccessor) Preconditions.checkNotNull(r52.zaa());
        this.zap = r52.zac();
        this.zaq = r52.zad();
        zaA();
        return;
    L14:
        if (zaE(r0) == false) goto L17;
        zaD();
        zaA();
        return;
    L17:
        zaF(r0);
    }

    public final /* synthetic */ void zak() {
        zaA();
    }

    public final /* synthetic */ void zal(ConnectionResult r1, Api r2, boolean r3) {
        zaC(r1, r2, r3);
    }

    public final /* synthetic */ void zam() {
        zaD();
    }

    public final /* synthetic */ boolean zan(ConnectionResult r1) {
        return zaE(r1);
    }

    public final /* synthetic */ void zao(ConnectionResult r1) {
        zaF(r1);
    }

    public final /* synthetic */ Set zap() {
        ClientSettings r0 = this.zar;
        if (r0 == null) goto L5;
        HashSet r1 = new HashSet(r0.getRequiredScopes());
        Map r02 = r0.zaa();
        Iterator r2 = r02.keySet().iterator();
    L8:
        if (r2.hasNext() == false) goto L14;
        Api r3 = (Api) r2.next();
        zabd r4 = this.zaa;
        Api.AnyClientKey r5 = r3.zac();
        if (r4.zab.containsKey(r5) == true) goto L8;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.get(r3));
        throw null;
    L14:
        return r1;
    L5:
        return Collections.EMPTY_SET;
    }

    public final /* synthetic */ boolean zaq(int r1) {
        return zaI(0);
    }

    public final /* synthetic */ zabd zar() {
        return this.zaa;
    }

    public final /* synthetic */ Lock zas() {
        return this.zab;
    }

    public final /* synthetic */ Context zat() {
        return this.zac;
    }

    public final /* synthetic */ GoogleApiAvailabilityLight zau() {
        return this.zad;
    }

    public final /* synthetic */ com.google.android.gms.signin.zae zav() {
        return this.zak;
    }

    public final /* synthetic */ boolean zaw() {
        return this.zam;
    }

    public final /* synthetic */ IAccountAccessor zax() {
        return this.zao;
    }

    public final /* synthetic */ ClientSettings zay() {
        return this.zar;
    }
}
