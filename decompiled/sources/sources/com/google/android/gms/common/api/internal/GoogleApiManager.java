package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import androidx.collection.ArraySet;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class GoogleApiManager implements Handler.Callback {
    public static final Status zaa = null;
    private static final Status zab = null;
    private static final Object zae = null;
    private static GoogleApiManager zah;
    private static volatile boolean zai;
    private long zac;
    private boolean zad;
    private TelemetryData zaf;
    private TelemetryLoggingClient zag;
    private final Context zaj;
    private final GoogleApiAvailability zak;
    private final com.google.android.gms.common.internal.zao zal;
    private final AtomicInteger zam;
    private final AtomicInteger zan;
    private final Map zao;
    private zaab zap;
    private final Set zaq;
    private final Set zar;
    private final Handler zas;
    private volatile boolean zat;

    static {
        zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
        zab = new Status(4, "The user must be signed in to make this API call.");
        zae = new Object();
        zai = false;
    }

    private GoogleApiManager(Context r6, Looper r7, GoogleApiAvailability r8) {
        this.zac = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        this.zad = false;
        this.zam = new AtomicInteger(1);
        this.zan = new AtomicInteger(0);
        this.zao = new ConcurrentHashMap(5, 0.75f, 1);
        this.zap = null;
        this.zaq = new ArraySet();
        this.zar = new ArraySet();
        this.zat = true;
        this.zaj = r6;
        com.google.android.gms.internal.base.zao r1 = new com.google.android.gms.internal.base.zao(r7, this);
        this.zas = r1;
        this.zak = r8;
        this.zal = new com.google.android.gms.common.internal.zao(r8);
        if (DeviceProperties.isAuto(r6) == false) goto L5;
        this.zat = false;
    L5:
        r1.sendMessage(r1.obtainMessage(6));
    }

    public static void reportSignOut() {
        Object r0 = zae;
        monitor-enter(r0);
        GoogleApiManager r1 = zah;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.zan.incrementAndGet();     // Catch: Throwable -> L7
        Handler r12 = r1.zas;     // Catch: Throwable -> L7
        r12.sendMessageAtFrontOfQueue(r12.obtainMessage(10));     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final zabk zaH(GoogleApi r4) {
        Map r0 = this.zao;
        ApiKey r1 = r4.getApiKey();
        zabk r2 = (zabk) r0.get(r1);
        if (r2 != null) goto L6;
        r2 = new zabk(this, r4);
        r0.put(r1, r2);
    L6:
        if (r2.zap() == false) goto L8;
        this.zar.add(r1);
    L8:
        r2.zam();
        return r2;
    }

    private final void zaI(TaskCompletionSource r1, int r2, GoogleApi r3) {
        if (r2 == 0) goto L7;
        zaby r22 = zaby.zaa(this, r2, r3.getApiKey());
        if (r22 == null) goto L8;
        Task r12 = r1.getTask();
        Handler r0 = this.zas;
        Objects.requireNonNull(r0);
        r12.addOnCompleteListener(new zabp(r0), r22);
        return;
    L8:
        return;
    }

    private static Status zaJ(ApiKey r5, ConnectionResult r6) {
        String r52 = r5.zaa();
        String r1 = String.valueOf(r6);
        int r2 = String.valueOf(r52).length();
        int r22 = r2 + 63;
        StringBuilder r4 = new StringBuilder(r22 + r1.length());
        r4.append("API: ");
        r4.append(r52);
        r4.append(" is not available on this device. Connection failed with: ");
        r4.append(r1);
        return new Status(r6, r4.toString());
    }

    private final void zaK() {
        TelemetryData r0 = this.zaf;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.zaa() <= 0) goto L7;
    L8:
        zaL().log(r0);
    L9:
        this.zaf = null;
        return;
    L7:
        if (zam() == false) goto L9;
        goto L8
    }

    private final TelemetryLoggingClient zaL() {
        if (this.zag != null) goto L6;
        this.zag = TelemetryLogging.getClient(this.zaj);
    L6:
        return this.zag;
    }

    public static GoogleApiManager zaa(Context r4) {
        Object r0 = zae;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (zah != null) goto L11;
        Looper r1 = GmsClientSupervisor.getOrStartHandlerThread().getLooper();     // Catch: Throwable -> L9
        zah = new GoogleApiManager(r4.getApplicationContext(), r1, GoogleApiAvailability.getInstance());     // Catch: Throwable -> L9
        if (zai == false) goto L11;
        Handler r42 = zah.zas;     // Catch: Throwable -> L9
        Objects.requireNonNull(r42);     // Catch: Throwable -> L9
        GmsClient.zag(new zabo(r42));     // Catch: Throwable -> L9
    L11:
        GoogleApiManager r43 = zah;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r43;
    }

    public static /* synthetic */ Status zau(ApiKey r0, ConnectionResult r1) {
        return zaJ(r0, r1);
    }

    public static /* synthetic */ Status zav() {
        return zab;
    }

    public static /* synthetic */ Object zay() {
        return zae;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message r9) {
        int r0 = r9.what;
        zabk r1 = null;
        long r2 = 300000;
        switch(r0) {
            case 1: goto L84;
            case 2: goto L81;
            case 3: goto L77;
            case 4: goto L68;
            case 5: goto L56;
            case 6: goto L51;
            case 7: goto L50;
            case 8: goto L68;
            case 9: goto L47;
            case 10: goto L40;
            case 11: goto L37;
            case 12: goto L34;
            case 13: goto L68;
            case 14: goto L30;
            case 15: goto L27;
            case 16: goto L24;
            case 17: goto L23;
            case 18: goto L7;
            case 19: goto L6;
            default: goto L4;
        };
    L4:
        StringBuilder r92 = new StringBuilder(String.valueOf(r0).length() + 20);
        r92.append("Unknown message id: ");
        r92.append(r0);
        Log.w("GoogleApiManager", r92.toString());
        return false;
    L6:
        this.zad = false;
    L91:
        return true;
    L7:
        zabz r93 = (zabz) r9.obj;
        long r02 = r93.zac;
        if (r02 != 0) goto L10;
        TelemetryData r03 = new TelemetryData(r93.zab, Arrays.asList(new MethodInvocation[]{r93.zaa}));
        zaL().log(r03);
        goto L91
    L10:
        TelemetryData r22 = this.zaf;
        if (r22 == null) goto L21;
        List r3 = r22.zab();
        if (r22.zaa() != r93.zab) goto L19;
        if (r3 != null) goto L16;
    L18:
        this.zaf.zac(r93.zaa);
        goto L21
    L16:
        if (r3.size() < r93.zad) goto L18;
    L19:
        this.zas.removeMessages(17);
        zaK();
    L21:
        if (this.zaf != null) goto L91;
        ArrayList r23 = new ArrayList();
        r23.add(r93.zaa);
        this.zaf = new TelemetryData(r93.zab, r23);
        Handler r8 = this.zas;
        r8.sendMessageDelayed(r8.obtainMessage(17), r02);
        goto L91
    L23:
        zaK();
        goto L91
    L24:
        zabl r94 = (zabl) r9.obj;
        Map r82 = this.zao;
        if (r82.containsKey(r94.zaa()) == false) goto L91;
        ((zabk) r82.get(r94.zaa())).zay(r94);
        goto L91
    L27:
        zabl r95 = (zabl) r9.obj;
        Map r83 = this.zao;
        if (r83.containsKey(r95.zaa()) == false) goto L91;
        ((zabk) r83.get(r95.zaa())).zax(r95);
        goto L91
    L30:
        zaac r96 = (zaac) r9.obj;
        ApiKey r04 = r96.zaa();
        Map r84 = this.zao;
        if (r84.containsKey(r04) == true) goto L33;
        r96.zab().setResult(Boolean.FALSE);
        goto L91
    L33:
        boolean r85 = ((zabk) r84.get(r04)).zaw(false);
        r96.zab().setResult(Boolean.valueOf(r85));
        goto L91
    L34:
        Map r86 = this.zao;
        if (r86.containsKey(r9.obj) == false) goto L91;
        ((zabk) r86.get(r9.obj)).zal();
        goto L91
    L37:
        Map r87 = this.zao;
        if (r87.containsKey(r9.obj) == false) goto L91;
        ((zabk) r87.get(r9.obj)).zak();
        goto L91
    L40:
        Set r97 = this.zar;
        Iterator r05 = r97.iterator();
    L42:
        if (r05.hasNext() == false) goto L46;
        ApiKey r12 = (ApiKey) r05.next();
        zabk r13 = (zabk) this.zao.remove(r12);
        if (r13 == null) goto L42;
        r13.zae();
        goto L42
    L46:
        r97.clear();
        goto L91
    L47:
        Map r88 = this.zao;
        if (r88.containsKey(r9.obj) == false) goto L91;
        ((zabk) r88.get(r9.obj)).zaj();
        goto L91
    L50:
        zaH((GoogleApi) r9.obj);
        goto L91
    L51:
        Context r98 = this.zaj;
        if ((r98.getApplicationContext() instanceof Application) == false) goto L91;
        BackgroundDetector.initialize((Application) r98.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zabf(this));
        if (BackgroundDetector.getInstance().readCurrentStateIfPossible(true) == true) goto L91;
        this.zac = 300000;
        goto L91
    L56:
        int r06 = r9.arg1;
        ConnectionResult r99 = (ConnectionResult) r9.obj;
        Iterator r24 = this.zao.values().iterator();
    L58:
        if (r24.hasNext() == false) goto L62;
        zabk r32 = (zabk) r24.next();
        if (r32.zaq() != r06) goto L58;
        r1 = r32;
    L62:
        if (r1 != null) goto L64;
        StringBuilder r910 = new StringBuilder(String.valueOf(r06).length() + 65);
        r910.append("Could not find API instance ");
        r910.append(r06);
        r910.append(" while trying to fail enqueued calls.");
        Log.wtf("GoogleApiManager", r910.toString(), new Exception());
        goto L91
    L64:
        if (r99.getErrorCode() != 13) goto L66;
        String r89 = this.zak.getErrorString(r99.getErrorCode());
        String r911 = r99.getErrorMessage();
        int r25 = String.valueOf(r89).length();
        StringBuilder r4 = new StringBuilder((r25 + 69) + String.valueOf(r911).length());
        r4.append("Error resolution was canceled by the user, original error message: ");
        r4.append(r89);
        r4.append(": ");
        r4.append(r911);
        r1.zav(new Status(17, r4.toString()));
        goto L91
    L66:
        r1.zav(zaJ(r1.zaA(), r99));
        goto L91
    L68:
        zacc r912 = (zacc) r9.obj;
        Map r07 = this.zao;
        GoogleApi r14 = r912.zac;
        zabk r08 = (zabk) r07.get(r14.getApiKey());
        if (r08 != null) goto L72;
        r08 = zaH(r14);
    L72:
        if (r08.zap() == true) goto L74;
    L76:
        r08.zad(r912.zaa);
        goto L91
    L74:
        if (this.zan.get() == r912.zab) goto L76;
        r912.zaa.zad(zaa);
        r08.zae();
        goto L91
    L77:
        Iterator r810 = this.zao.values().iterator();
    L79:
        if (r810.hasNext() == false) goto L91;
        zabk r913 = (zabk) r810.next();
        r913.zah();
        r913.zam();
        goto L79
    L81:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9.obj);
        throw null;
    L84:
        if (true != ((Boolean) r9.obj).booleanValue()) goto L87;
        r2 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L87:
        this.zac = r2;
        Handler r914 = this.zas;
        r914.removeMessages(12);
        Iterator r15 = this.zao.keySet().iterator();
    L89:
        if (r15.hasNext() == false) goto L91;
        r914.sendMessageDelayed(r914.obtainMessage(12, (ApiKey) r15.next()), this.zac);
        goto L89
    }

    public final /* synthetic */ GoogleApiAvailability zaA() {
        return this.zak;
    }

    public final /* synthetic */ com.google.android.gms.common.internal.zao zaB() {
        return this.zal;
    }

    public final /* synthetic */ Map zaC() {
        return this.zao;
    }

    public final /* synthetic */ zaab zaD() {
        return this.zap;
    }

    public final /* synthetic */ Set zaE() {
        return this.zaq;
    }

    public final /* synthetic */ Handler zaF() {
        return this.zas;
    }

    public final /* synthetic */ boolean zaG() {
        return this.zat;
    }

    public final int zac() {
        return this.zam.getAndIncrement();
    }

    public final void zad(GoogleApi r2) {
        Handler r1 = this.zas;
        r1.sendMessage(r1.obtainMessage(7, r2));
    }

    public final void zae(zaab r3) {
        Object r0 = zae;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zap == r3) goto L9;
        this.zap = r3;     // Catch: Throwable -> L7
        this.zaq.clear();     // Catch: Throwable -> L7
    L9:
        this.zaq.addAll(r3.zab());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public final void zaf(zaab r3) {
        Object r0 = zae;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zap != r3) goto L9;
        this.zap = null;     // Catch: Throwable -> L7
        this.zaq.clear();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public final zabk zag(ApiKey r1) {
        return (zabk) this.zao.get(r1);
    }

    public final void zai() {
        Handler r1 = this.zas;
        r1.sendMessage(r1.obtainMessage(3));
    }

    public final Task zaj(GoogleApi r2) {
        zaac r0 = new zaac(r2.getApiKey());
        Handler r1 = this.zas;
        r1.sendMessage(r1.obtainMessage(14, r0));
        return r0.zab().getTask();
    }

    public final void zak(GoogleApi r2, int r3, BaseImplementation$ApiMethodImpl r4) {
        zacc r42 = new zacc(new zae(r3, r4), this.zan.get(), r2);
        Handler r1 = this.zas;
        r1.sendMessage(r1.obtainMessage(4, r42));
    }

    public final void zal(GoogleApi r2, int r3, TaskApiCall r4, TaskCompletionSource r5, StatusExceptionMapper r6) {
        zaI(r5, r4.zab(), r2);
        zacc r42 = new zacc(new zag(r3, r4, r5, r6), this.zan.get(), r2);
        Handler r1 = this.zas;
        r1.sendMessage(r1.obtainMessage(4, r42));
    }

    public final boolean zam() {
        if (this.zad == false) goto L5;
        return false;
    L5:
        RootTelemetryConfiguration r0 = RootTelemetryConfigManager.getInstance().getConfig();
        if (r0 != null) goto L8;
    L11:
        int r3 = this.zal.zab(this.zaj, 203400000);
        if (r3 == (-1)) goto L16;
        if (r3 == 0) goto L18;
        return false;
    L18:
        return true;
    L16:
        return true;
    L8:
        if (r0.getMethodInvocationTelemetryEnabled() == true) goto L11;
        return false;
    }

    public final Task zan(GoogleApi r4, RegisterListenerMethod r5, UnregisterListenerMethod r6, Runnable r7) {
        TaskCompletionSource r0 = new TaskCompletionSource();
        zaI(r0, r5.zab(), r4);
        zacc r62 = new zacc(new zaf(new zacd(r5, r6, r7), r0), this.zan.get(), r4);
        Handler r3 = this.zas;
        r3.sendMessage(r3.obtainMessage(8, r62));
        return r0.getTask();
    }

    public final Task zao(GoogleApi r3, ListenerHolder.ListenerKey r4, int r5) {
        TaskCompletionSource r0 = new TaskCompletionSource();
        zaI(r0, r5, r3);
        zacc r1 = new zacc(new zah(r4, r0), this.zan.get(), r3);
        Handler r2 = this.zas;
        r2.sendMessage(r2.obtainMessage(13, r1));
        return r0.getTask();
    }

    public final boolean zap(ConnectionResult r2, int r3) {
        return this.zak.zad(this.zaj, r2, r3);
    }

    public final void zaq(ConnectionResult r3, int r4) {
        if (zap(r3, r4) == true) goto L6;
        Handler r2 = this.zas;
        r2.sendMessage(r2.obtainMessage(5, r4, 0, r3));
        return;
    }

    public final void zar(MethodInvocation r7, int r8, long r9, int r11) {
        zabz r0 = new zabz(r7, r8, r9, r11);
        Handler r6 = this.zas;
        r6.sendMessage(r6.obtainMessage(18, r0));
    }

    public final /* synthetic */ long zaw() {
        return this.zac;
    }

    public final /* synthetic */ void zax(boolean r1) {
        this.zad = true;
    }

    public final /* synthetic */ Context zaz() {
        return this.zaj;
    }
}
