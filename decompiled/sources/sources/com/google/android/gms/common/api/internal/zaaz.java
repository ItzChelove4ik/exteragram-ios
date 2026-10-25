package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zaaz extends GoogleApiClient implements zabu {
    final Queue zaa;
    zabs zab;
    final Map zac;
    Set zad;
    final ClientSettings zae;
    final Map zaf;
    final Api.AbstractClientBuilder zag;
    Set zah;
    final zacu zai;
    private final Lock zaj;
    private final com.google.android.gms.common.internal.zan zak;
    private zabv zal;
    private final int zam;
    private final Context zan;
    private final Looper zao;
    private volatile boolean zap;
    private long zaq;
    private long zar;
    private final zaax zas;
    private final GoogleApiAvailability zat;
    private final ListenerHolders zau;
    private final ArrayList zav;
    private Integer zaw;
    private final com.google.android.gms.common.internal.zam zax;

    public zaaz(Context r4, Lock r5, Looper r6, ClientSettings r7, GoogleApiAvailability r8, Api.AbstractClientBuilder r9, Map r10, List r11, List r12, Map r13, int r14, int r15, ArrayList r16) {
        this.zal = null;
        this.zaa = new LinkedList();
        if (true == ClientLibraryUtils.isPackageSide()) goto L5;
        long r1 = 120000;
    L6:
        this.zaq = r1;
        this.zar = 5000;
        this.zad = new HashSet();
        this.zau = new ListenerHolders();
        this.zaw = null;
        this.zah = null;
        zaat r0 = new zaat(this);
        this.zax = r0;
        this.zan = r4;
        this.zaj = r5;
        this.zak = new com.google.android.gms.common.internal.zan(r6, r0);
        this.zao = r6;
        this.zas = new zaax(this, r6);
        this.zat = r8;
        this.zam = r14;
        if (r14 < 0) goto L9;
        this.zaw = Integer.valueOf(r15);
    L9:
        this.zaf = r10;
        this.zac = r13;
        this.zav = r16;
        this.zai = new zacu();
        Iterator r42 = r11.iterator();
    L11:
        if (r42.hasNext() == false) goto L13;
        GoogleApiClient.ConnectionCallbacks r52 = (GoogleApiClient.ConnectionCallbacks) r42.next();
        this.zak.zaf(r52);
        goto L11
    L13:
        Iterator r43 = r12.iterator();
    L15:
        if (r43.hasNext() == false) goto L17;
        GoogleApiClient.OnConnectionFailedListener r53 = (GoogleApiClient.OnConnectionFailedListener) r43.next();
        this.zak.zai(r53);
        goto L15
    L17:
        this.zae = r7;
        this.zag = r9;
        return;
    L5:
        r1 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        goto L6
    }

    public static int zaf(Iterable r4, boolean r5) {
        Iterator r42 = r4.iterator();
        boolean r0 = false;
        boolean r1 = false;
    L4:
        if (r42.hasNext() == false) goto L6;
        Api.Client r2 = (Api.Client) r42.next();
        r0 = r0 | r2.requiresSignIn();
        r1 = r1 | r2.providesSignIn();
        goto L4
    L6:
        if (r0 == false) goto L13;
        if (r1 == false) goto L11;
        if (r5 == false) goto L16;
        return 2;
    L16:
        return 1;
    L11:
        return 1;
    L13:
        return 3;
    }

    public static String zag(int r1) {
        if (r1 != 1) goto L5;
        return "SIGN_IN_MODE_REQUIRED";
    L5:
        if (r1 != 2) goto L7;
        return "SIGN_IN_MODE_OPTIONAL";
    L7:
        if (r1 == 3) goto L10;
        return "UNKNOWN";
    L10:
        return "SIGN_IN_MODE_NONE";
    }

    private final void zam(int r13) {
        Integer r0 = this.zaw;
        if (r0 != null) goto L6;
        this.zaw = Integer.valueOf(r13);
    L8:
        if (this.zal == null) goto L10;
        return;
    L10:
        Map r5 = this.zac;
        Iterator r132 = r5.values().iterator();
        boolean r02 = false;
        boolean r1 = false;
    L12:
        if (r132.hasNext() == false) goto L14;
        Api.Client r2 = (Api.Client) r132.next();
        r02 = r02 | r2.requiresSignIn();
        r1 = r1 | r2.providesSignIn();
        goto L12
    L14:
        int r133 = this.zaw.intValue();
        if (r133 != 1) goto L17;
        zaaz r22 = this;
        if (r02 == false) goto L29;
        if (r1 == true) goto L27;
    L25:
        r22.zal = new zabd(r22.zan, r22, r22.zaj, r22.zao, r22.zat, r5, r22.zae, r22.zaf, r22.zag, r22.zav, r22);
        return;
    L27:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
        return;
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
        return;
    L17:
        if (r133 == 2) goto L19;
    L18:
        r22 = this;
        goto L25
    L19:
        if (r02 == false) goto L18;
        this.zal = zax.zaa(this.zan, this, this.zaj, this.zao, this.zat, r5, this.zae, this.zaf, this.zag, this.zav);
        return;
    L6:
        if (r0.intValue() == r13) goto L8;
        String r12 = zag(this.zaw.intValue());
        String r134 = zag(r13);
        zaaz$$ExternalSyntheticBUOutline0.m((r134.length() + 51) + r12.length(), "Cannot use sign-in mode: ", r134, ". Mode was already set to ", r12);
    }

    private final void zan() {
        this.zak.zab();
        ((zabv) Preconditions.checkNotNull(this.zal)).zae();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        Lock r0 = this.zaj;
        r0.lock();
        int r1 = this.zam;     // Catch: Throwable -> L10
        Integer r2 = this.zaw;
        int r3 = 2;
        boolean r4 = false;
        if (r1 < 0) goto L12;
        if (r2 == null) goto L8;
        boolean r12 = true;
    L9:
        Preconditions.checkState(r12, "Sign-in mode should have been set explicitly by auto-manage.");     // Catch: Throwable -> L10
    L16:
        int r13 = ((Integer) Preconditions.checkNotNull(this.zaw)).intValue();     // Catch: Throwable -> L10
        r0.lock();     // Catch: Throwable -> L10
        if (r13 == 3) goto L23;
        if (r13 == 1) goto L23;
        if (r13 != 2) goto L22;
    L21:
        r4 = true;
    L36:
        StringBuilder r14 = new StringBuilder(String.valueOf(r3).length() + 22);     // Catch: Throwable -> L28
        r14.append("Illegal sign-in mode: ");     // Catch: Throwable -> L28
        r14.append(r3);     // Catch: Throwable -> L28
        Preconditions.checkArgument(r4, r14.toString());     // Catch: Throwable -> L28
        zam(r3);     // Catch: Throwable -> L28
        zan();     // Catch: Throwable -> L28
        Lock r02 = this.zaj;     // Catch: Throwable -> L10
        r02.unlock();     // Catch: Throwable -> L10
        r02.unlock();
        return;
    L28:
        th = move-exception;
        this.zaj.unlock();     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    L22:
        r3 = r13;
    L23:
        r3 = r13;
        goto L21
    L8:
        r12 = false;
        goto L9
    L12:
        if (r2 != null) goto L15;
        this.zaw = Integer.valueOf(zaf(this.zac.values(), false));     // Catch: Throwable -> L10
        goto L16
    L15:
        if (r2.intValue() != 2) goto L16;
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        Lock r0 = this.zaj;
        r0.lock();
        this.zai.zab();     // Catch: Throwable -> L6
        zabv r1 = this.zal;     // Catch: Throwable -> L6
        if (r1 == null) goto L8;
        r1.zah();     // Catch: Throwable -> L6
    L8:
        this.zau.zab();     // Catch: Throwable -> L6
        Queue r12 = this.zaa;     // Catch: Throwable -> L6
        Iterator r2 = r12.iterator();     // Catch: Throwable -> L6
    L10:
        if (r2.hasNext() == false) goto L12;
        BaseImplementation$ApiMethodImpl r3 = (BaseImplementation$ApiMethodImpl) r2.next();     // Catch: Throwable -> L6
        r3.zan(null);     // Catch: Throwable -> L6
        r3.cancel();     // Catch: Throwable -> L6
        goto L10
    L12:
        r12.clear();     // Catch: Throwable -> L6
        if (this.zal == null) goto L15;
        zad();     // Catch: Throwable -> L6
        this.zak.zaa();     // Catch: Throwable -> L6
    L15:
        r0.unlock();
        return;
    L6:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String r3, FileDescriptor r4, PrintWriter r5, String[] r6) {
        r5.append(r3).append("mContext=").println(this.zan);
        r5.append(r3).append("mResuming=").print(this.zap);
        Queue r0 = this.zaa;
        r5.append(" mWorkQueue.size()=").print(r0.size());
        Set r02 = this.zai.zab;
        r5.append(" mUnconsumedApiCalls.size()=").println(r02.size());
        zabv r2 = this.zal;
        if (r2 == null) goto L6;
        r2.zan(r3, r4, r5, r6);
        return;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation$ApiMethodImpl<R, A>> T enqueue(T r5) {
        Api<?> r0 = r5.getApi();
        boolean r1 = this.zac.containsKey(r5.getClientKey());
        if (r0 == null) goto L5;
        String r02 = r0.zad();
    L6:
        StringBuilder r3 = new StringBuilder(String.valueOf(r02).length() + 65);
        r3.append("GoogleApiClient is not configured to use ");
        r3.append(r02);
        r3.append(" required for this call.");
        Preconditions.checkArgument(r1, r3.toString());
        Lock r03 = this.zaj;
        r03.lock();
        zabv r12 = this.zal;     // Catch: Throwable -> L10
        if (r12 != null) goto L12;
        this.zaa.add(r5);     // Catch: Throwable -> L10
    L13:
        r03.unlock();
        return r5;
    L12:
        r5 = (T) r12.zab(r5);
    L10:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    L5:
        r02 = "the API";
        goto L6
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T r5) {
        Map r0 = this.zac;
        Api<?> r1 = r5.getApi();
        boolean r02 = r0.containsKey(r5.getClientKey());
        if (r1 == null) goto L5;
        String r12 = r1.zad();
    L6:
        StringBuilder r3 = new StringBuilder(String.valueOf(r12).length() + 65);
        r3.append("GoogleApiClient is not configured to use ");
        r3.append(r12);
        r3.append(" required for this call.");
        Preconditions.checkArgument(r02, r3.toString());
        Lock r03 = this.zaj;
        r03.lock();
        zabv r13 = this.zal;     // Catch: Throwable -> L16
        if (r13 == null) goto L24;
        if (this.zap == false) goto L20;
        Queue r14 = this.zaa;     // Catch: Throwable -> L16
        r14.add(r5);     // Catch: Throwable -> L16
    L13:
        if (r14.isEmpty() == true) goto L18;
        BaseImplementation$ApiMethodImpl r2 = (BaseImplementation$ApiMethodImpl) r14.remove();     // Catch: Throwable -> L16
        this.zai.zaa(r2);     // Catch: Throwable -> L16
        r2.setFailedResult(Status.RESULT_INTERNAL_ERROR);     // Catch: Throwable -> L16
        goto L13
    L18:
        r03.unlock();
        return r5;
    L20:
        T r52 = (T) r13.zac(r5);
        this.zaj.unlock();
        return r52;
    L24:
        throw new IllegalStateException("GoogleApiClient is not connected yet.");     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    L5:
        r12 = "the API";
        goto L6
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <C extends Api.Client> C getClient(Api.AnyClientKey<C> r1) {
        C r0 = (C) this.zac.get(r1);
        Preconditions.checkNotNull(r0, "Appropriate Api was not requested.");
        return r0;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zao;
    }

    public final boolean isConnected() {
        zabv r0 = this.zal;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.zai() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener r1) {
        zabv r0 = this.zal;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.zak(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zabv r0 = this.zal;
        if (r0 == null) goto L6;
        r0.zam();
        return;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener r1) {
        this.zak.zai(r1);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener r1) {
        this.zak.zak(r1);
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zaa(Bundle r3) {
    L2:
        Queue r0 = this.zaa;
        if (r0.isEmpty() == true) goto L5;
        execute((BaseImplementation$ApiMethodImpl) r0.remove());
        goto L2
    L5:
        this.zak.zac(r3);
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zab(ConnectionResult r4) {
        if (this.zat.isPlayServicesPossiblyUpdating(this.zan, r4.getErrorCode()) == true) goto L6;
        zad();
    L6:
        if (this.zap == true) goto L9;
        com.google.android.gms.common.internal.zan r3 = this.zak;
        r3.zae(r4);
        r3.zaa();
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zac(int r6, boolean r7) {
        if (r6 != 1) goto L14;
        if (r7 == false) goto L6;
    L7:
        r6 = 1;
        goto L14
    L6:
        if (this.zap == true) goto L7;
        this.zap = true;
        if (this.zab == null) goto L11;
    L13:
        zaax r62 = this.zas;
        r62.sendMessageDelayed(r62.obtainMessage(1), this.zaq);
        r62.sendMessageDelayed(r62.obtainMessage(2), this.zar);
        goto L7
    L11:
        if (ClientLibraryUtils.isPackageSide() == true) goto L13;
        this.zab = this.zat.zag(this.zan.getApplicationContext(), new zaay(this));     // Catch: SecurityException -> L21
    L14:
        int r1 = 0;
        BasePendingResult[] r72 = (BasePendingResult[]) this.zai.zab.toArray(new BasePendingResult[0]);
        int r2 = r72.length;
    L15:
        if (r1 >= r2) goto L17;
        r72[r1].forceFailureUnlessReady(zacu.zaa);
        r1 = r1 + 1;
        goto L15
    L17:
        com.google.android.gms.common.internal.zan r73 = this.zak;
        r73.zad(r6);
        r73.zaa();
        if (r6 != 2) goto L25;
        zan();
        return;
    }

    public final boolean zad() {
        if (this.zap == true) goto L5;
        return false;
    L5:
        this.zap = false;
        zaax r0 = this.zas;
        r0.removeMessages(2);
        r0.removeMessages(1);
        zabs r02 = this.zab;
        if (r02 == null) goto L8;
        r02.zab();
        this.zab = null;
    L8:
        return true;
    }

    public final String zae() {
        StringWriter r0 = new StringWriter();
        dump(_UrlKt.FRAGMENT_ENCODE_SET, null, new PrintWriter(r0), null);
        return r0.toString();
    }

    public final /* synthetic */ void zai() {
        this.zaj.lock();
    L7:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    L4:
        if (this.zap == false) goto L9;
        zan();     // Catch: Throwable -> L7
    L9:
        this.zaj.unlock();
    }

    public final /* synthetic */ void zaj() {
        this.zaj.lock();
    L7:
        th = move-exception;
        this.zaj.unlock();
        throw th;
    L4:
        if (zad() == false) goto L9;
        zan();     // Catch: Throwable -> L7
    L9:
        this.zaj.unlock();
    }
}
