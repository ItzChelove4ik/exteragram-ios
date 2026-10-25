package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzaif;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class zzic implements zzjg {
    private static volatile zzic zzc;
    private long zzA;
    private volatile Boolean zzB;
    private volatile boolean zzC;
    private int zzD;
    private int zzE;
    private final AtomicInteger zzF;
    final long zza;
    final long zzb;
    private final Context zzd;
    private final boolean zze;
    private final zzae zzf;
    private final zzal zzg;
    private final zzhh zzh;
    private final zzgu zzi;
    private final zzhz zzj;
    private final zzoc zzk;
    private final zzpp zzl;
    private final zzgn zzm;
    private final Clock zzn;
    private final zzmb zzo;
    private final zzlj zzp;
    private final zzd zzq;
    private final zzlo zzr;
    private final String zzs;
    private zzgl zzt;
    private zznl zzu;
    private zzbb zzv;
    private zzgi zzw;
    private zzlq zzx;
    private boolean zzy;
    private Boolean zzz;

    public zzic(zzjs r11) {
        boolean r0 = false;
        this.zzy = false;
        this.zzF = new AtomicInteger(0);
        Preconditions.checkNotNull(r11);
        Context r1 = r11.zza;
        zzae r2 = new zzae(r1);
        this.zzf = r2;
        zzfr.zza = r2;
        this.zzd = r1;
        this.zze = r11.zze;
        this.zzB = r11.zzb;
        this.zzs = r11.zzh;
        this.zzC = true;
        com.google.android.gms.internal.measurement.zzlw.zza(r1);
        Clock r3 = DefaultClock.getInstance();
        this.zzn = r3;
        com.google.android.gms.internal.measurement.zzjx.zza(r1).zza("com.google.android.gms.measurement#".concat(String.valueOf(r1.getPackageName())), 0, new String[0], null);
        com.google.android.gms.internal.measurement.zzlk.zza(r1);
        Long r4 = r11.zzf;
        if (r4 == null) goto L5;
        long r42 = r4.longValue();
    L6:
        this.zza = r42;
        Long r43 = r11.zzg;
        if (r43 == null) goto L9;
        long r32 = r43.longValue();
    L10:
        this.zzb = r32;
        this.zzg = new zzal(this);
        zzhh r33 = new zzhh(this);
        r33.zzx();
        this.zzh = r33;
        zzgu r34 = new zzgu(this);
        r34.zzx();
        this.zzi = r34;
        zzpp r44 = new zzpp(this);
        r44.zzx();
        this.zzl = r44;
        this.zzm = new zzgn(new zzjr(r11, this));
        this.zzq = new zzd(this);
        zzmb r45 = new zzmb(this);
        r45.zzc();
        this.zzo = r45;
        zzlj r46 = new zzlj(this);
        r46.zzc();
        this.zzp = r46;
        zzoc r5 = new zzoc(this);
        r5.zzc();
        this.zzk = r5;
        zzlo r52 = new zzlo(this);
        r52.zzx();
        this.zzr = r52;
        zzhz r53 = new zzhz(this);
        r53.zzx();
        this.zzj = r53;
        com.google.android.gms.internal.measurement.zzdb r6 = r11.zzd;
        if (r6 != null) goto L13;
    L15:
        r0 = true;
    L17:
        if ((r1.getApplicationContext() instanceof Application) == false) goto L25;
        zzO(r46);
        if ((r46.zzu.zzd.getApplicationContext() instanceof Application) == false) goto L26;
        Application r12 = (Application) r46.zzu.zzd.getApplicationContext();
        if (r46.zza != null) goto L23;
        r46.zza = new zzky(r46);
    L23:
        if (r0 == false) goto L26;
        r12.unregisterActivityLifecycleCallbacks(r46.zza);
        r12.registerActivityLifecycleCallbacks(r46.zza);
        zzgu r02 = r46.zzu.zzi;
        zzP(r02);
        r02.zzk().zza("Registered activity lifecycle callback");
    L26:
        r53.zzj(new zzia(this, r11));
        return;
    L25:
        zzP(r34);
        r34.zze().zza("Application context is not an Application");
        goto L26
    L13:
        if (r6.zzb == 0) goto L15;
    L9:
        r32 = r3.elapsedRealtime();
        goto L10
    L5:
        r42 = r3.currentTimeMillis();
        goto L6
    }

    public static final void zzL() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzM(zzf r0) {
        if (r0 == null) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m("Component not created");
    }

    private static final void zzN(zzje r0) {
        if (r0 == null) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m("Component not created");
    }

    private static final void zzO(zzg r1) {
        if (r1 != null) goto L4;
        Segment$$ExternalSyntheticBUOutline1.m("Component not created");
        return;
    L4:
        if (r1.zza() == false) goto L6;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Component not initialized: ".concat(String.valueOf(r1.getClass())));
    }

    private static final void zzP(zzjf r1) {
        if (r1 != null) goto L4;
        Segment$$ExternalSyntheticBUOutline1.m("Component not created");
        return;
    L4:
        if (r1.zzv() == false) goto L6;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Component not initialized: ".concat(String.valueOf(r1.getClass())));
    }

    public static zzic zzy(Context r8, com.google.android.gms.internal.measurement.zzdb r9, Long r10, Long r11) {
        if (r9 == null) goto L4;
        Bundle r6 = r9.zzd;
        boolean r5 = r9.zzc;
        long r3 = r9.zzb;
        r9 = new com.google.android.gms.internal.measurement.zzdb(r9.zza, r3, r5, r6, null);
    L4:
        Preconditions.checkNotNull(r8);
        Preconditions.checkNotNull(r8.getApplicationContext());
        if (zzc == null) goto L7;
        if (r9 == null) goto L23;
        Bundle r82 = r9.zzd;
        if (r82 == null) goto L23;
        if (r82.containsKey("dataCollectionDefaultEnabled") == false) goto L23;
        Preconditions.checkNotNull(zzc);
        zzic r92 = zzc;
        r92.zzB = Boolean.valueOf(r82.getBoolean("dataCollectionDefaultEnabled"));
    L23:
        Preconditions.checkNotNull(zzc);
        return zzc;
    L7:
        monitor-enter(zzic.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (zzc != null) goto L13;
        zzc = new zzic(new zzjs(r8, r9, r10, r11));     // Catch: Throwable -> L11
    L13:
        monitor-exit(zzic.class);     // Catch: Throwable -> L11
        goto L23
    }

    public final boolean zzA() {
        if (this.zzB != null) goto L5;
        return false;
    L5:
        if (this.zzB.booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzB() {
        if (zzC() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzC() {
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        zzal r1 = this.zzg;
        if (r1.zzt() == true) goto L30;
        zzP(r0);
        r0.zzg();
        if (this.zzC == false) goto L28;
        zzhh r02 = this.zzh;
        zzN(r02);
        Boolean r03 = r02.zzi();
        if (r03 != null) goto L9;
        zzae r04 = r1.zzu.zzf;
        Boolean r05 = r1.zzr("firebase_analytics_collection_enabled");
        if (r05 == null) goto L21;
        if (r05.booleanValue() == false) goto L18;
        return 0;
    L18:
        return 4;
    L21:
        if (this.zzB != null) goto L23;
        return 0;
    L23:
        if (this.zzB.booleanValue() == false) goto L25;
        return 0;
    L25:
        return 7;
    L9:
        if (r03.booleanValue() == false) goto L11;
        return 0;
    L11:
        return 3;
    L28:
        return 8;
    L30:
        return 1;
    }

    public final void zzD(boolean r2) {
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        this.zzC = r2;
    }

    public final boolean zzE() {
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        return this.zzC;
    }

    public final void zzF() {
        this.zzD++;
    }

    public final void zzG() {
        this.zzF.incrementAndGet();
    }

    public final boolean zzH() {
        boolean r1 = false;
        if (this.zzy == false) goto L30;
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        Boolean r02 = this.zzz;
        if (r02 != null) goto L7;
    L12:
        this.zzA = this.zzn.elapsedRealtime();
        zzpp r03 = this.zzl;
        zzN(r03);
        if (r03.zzab("android.permission.INTERNET") == false) goto L25;
        zzN(r03);
        if (r03.zzab("android.permission.ACCESS_NETWORK_STATE") == false) goto L25;
        Context r2 = this.zzd;
        if (Wrappers.packageManager(r2).isCallerInstantApp() == false) goto L19;
    L24:
        r1 = true;
        goto L25
    L19:
        if (this.zzg.zzE() == true) goto L24;
        if (zzpp.zzax(r2) == false) goto L25;
        if (zzpp.zzS(r2, false) == true) goto L24;
    L25:
        Boolean r12 = Boolean.valueOf(r1);
        this.zzz = r12;
        if (r12.booleanValue() == false) goto L29;
        zzN(r03);
        this.zzz = Boolean.valueOf(r03.zzC(zzv().zzk()));
    L29:
        return this.zzz.booleanValue();
    L7:
        if (this.zzA == 0) goto L12;
        if (r02.booleanValue() == true) goto L29;
        if (Math.abs(this.zzn.elapsedRealtime() - this.zzA) <= 1000) goto L29;
    L30:
        Segment$$ExternalSyntheticBUOutline1.m("AppMeasurement is not initialized");
        return false;
    }

    public final boolean zzI() {
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        zzlo r02 = this.zzr;
        zzP(r02);
        zzP(r02);
        String r4 = zzv().zzj();
        boolean r10 = false;
        if (this.zzg.zzu() == false) goto L48;
        zzhh r1 = this.zzh;
        zzN(r1);
        Pair r12 = r1.zzb(r4);
        if (((Boolean) r12.second).booleanValue() == false) goto L7;
    L46:
        zzgu r13 = this.zzi;
        zzP(r13);
        r13.zzk().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
        return false;
    L7:
        if (TextUtils.isEmpty((CharSequence) r12.first) == true) goto L46;
        zzP(r02);
        r02.zzw();
        ConnectivityManager r03 = (ConnectivityManager) r02.zzu.zzd.getSystemService("connectivity");
        Bundle r2 = null;
        if (r03 != null) goto L51;
    L13:
        NetworkInfo r04 = null;
    L14:
        if (r04 != null) goto L16;
    L44:
        zzgu r132 = this.zzi;
        zzP(r132);
        r132.zze().zza("Network is not available for Deferred Deep Link request. Skipping");
        return false;
    L16:
        if (r04.isConnected() == false) goto L44;
        StringBuilder r05 = new StringBuilder();
        zznl r3 = zzt();
        r3.zzg();
        r3.zzb();
        if (r3.zzK() == false) goto L22;
        zzpp r32 = r3.zzu.zzl;
        zzN(r32);
        if (r32.zzak() >= 234200) goto L22;
    L40:
        zzpp r22 = this.zzl;
        zzN(r22);
        zzv().zzu.zzg.zzi();
        String r6 = (String) r12.first;
        zzN(this.zzh);
        URL r06 = r22.zzaw(161000, r4, r6, r1.zzp.zza() - 1, r05.toString());
        if (r06 == null) goto L43;
        zzlo r33 = this.zzr;
        zzP(r33);
        zzib r8 = new zzib(this);
        r33.zzw();
        Preconditions.checkNotNull(r06);
        Preconditions.checkNotNull(r8);
        zzhz r133 = r33.zzu.zzj;
        zzP(r133);
        r133.zzm(new zzln(r33, r4, r06, null, null, r8));
    L43:
        return false;
    L22:
        zzlj r34 = this.zzp;
        zzO(r34);
        zzic r5 = r34.zzu;
        r34.zzg();
        zzao r35 = r5.zzt().zzz();
        if (r35 == null) goto L26;
        r2 = r35.zza;
    L26:
        if (r2 != null) goto L36;
        int r07 = this.zzE;
        this.zzE = r07 + 1;
        if (r07 >= 10) goto L30;
        r10 = true;
    L30:
        zzgu r23 = this.zzi;
        zzP(r23);
        zzgs r24 = r23.zzj();
        StringBuilder r36 = new StringBuilder(69);
        r36.append("Failed to retrieve DMA consent from the service, ");
        if (r07 >= 10) goto L33;
        String r08 = "Retrying.";
    L34:
        r36.append(r08);
        r36.append(" retryCount");
        r24.zzb(r36.toString(), Integer.valueOf(this.zzE));
        return r10;
    L33:
        r08 = "Skipping.";
        goto L34
    L36:
        zzjl r62 = zzjl.zze(r2, 100);
        r05.append("&gcs=");
        r05.append(r62.zzk());
        zzba r52 = zzba.zzh(r2, 100);
        r05.append("&dma=");
        r05.append(!Objects.equals(r52.zzj(), Boolean.FALSE) ? 1 : 0);
        if (TextUtils.isEmpty(r52.zzk()) == true) goto L39;
        r05.append("&dma_cps=");
        r05.append(r52.zzk());
    L39:
        int r25 = !Objects.equals(zzba.zzi(r2), Boolean.TRUE) ? 1 : 0;
        r05.append("&npa=");
        r05.append(r25);
        zzgu r26 = this.zzi;
        zzP(r26);
        r26.zzk().zzb("Consent query parameters to Bow", r05);
        goto L40
    L51:
        r04 = r03.getActiveNetworkInfo();     // Catch: SecurityException -> L50
    L48:
        zzgu r134 = this.zzi;
        zzP(r134);
        r134.zzk().zza("ADID collection is disabled from Manifest. Skipping");
        return false;
    }

    public final /* synthetic */ void zzJ(String r18, int r19, Throwable r20, byte[] r21, Map r22) {
        if (r19 != 200) goto L5;
    L10:
        int r10 = r19;
    L11:
        if (r20 != null) goto L56;
        zzhh r0 = this.zzh;
        zzN(r0);
        r0.zzo.zzb(true);
        if (r21 != null) goto L15;
    L54:
        zzgu r02 = this.zzi;
        zzP(r02);
        r02.zzj().zza("Deferred Deep Link response empty.");
        return;
    L15:
        if (r21.length == 0) goto L54;
        JSONObject r3 = new JSONObject(new String(r21));     // Catch: JSONException -> L22
        String r03 = r3.optString("deeplink", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: JSONException -> L22
        if (TextUtils.isEmpty(r03) == false) goto L24;
        zzgu r04 = this.zzi;     // Catch: JSONException -> L22
        zzP(r04);     // Catch: JSONException -> L22
        r04.zzj().zza("Deferred Deep Link is empty.");     // Catch: JSONException -> L22
        return;
    L24:
        String r102 = r3.optString("gclid", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: JSONException -> L22
        String r11 = r3.optString("gbraid", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: JSONException -> L22
        String r9 = r3.optString("gad_source", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: JSONException -> L22
        double r12 = r3.optDouble("timestamp", 0.0d);     // Catch: JSONException -> L22
        Bundle r32 = new Bundle();     // Catch: JSONException -> L22
        zzpp r14 = this.zzl;     // Catch: JSONException -> L22
        zzN(r14);     // Catch: JSONException -> L22
        zzic r15 = r14.zzu;     // Catch: JSONException -> L22
        if (TextUtils.isEmpty(r03) == true) goto L50;
        Context r152 = r15.zzd;     // Catch: JSONException -> L22
        List<ResolveInfo> r2 = r152.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(r03)), 0);     // Catch: JSONException -> L22
        if (r2 == null) goto L50;
        if (r2.isEmpty() == true) goto L50;
        if (TextUtils.isEmpty(r11) == true) goto L35;
        r32.putString("gbraid", r11);     // Catch: JSONException -> L22
    L35:
        if (TextUtils.isEmpty(r9) == true) goto L37;
        r32.putString("gad_source", r9);     // Catch: JSONException -> L22
    L37:
        r32.putString("gclid", r102);     // Catch: JSONException -> L22
        r32.putString("_cis", "ddp");     // Catch: JSONException -> L22
        this.zzp.zzE("auto", "_cmp", r32);     // Catch: JSONException -> L22
        zzN(r14);     // Catch: JSONException -> L22
        if (TextUtils.isEmpty(r03) == true) goto L61;
        SharedPreferences.Editor r23 = r152.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();     // Catch: JSONException -> L22 RuntimeException -> L47
        r23.putString("deeplink", r03);     // Catch: JSONException -> L22 RuntimeException -> L47
        r23.putLong("timestamp", Double.doubleToRawLongBits(r12));     // Catch: JSONException -> L22 RuntimeException -> L47
        if (r23.commit() == false) goto L62;
        Intent r05 = new Intent("android.google.analytics.action.DEEPLINK_ACTION");     // Catch: JSONException -> L22
        Context r24 = r14.zzu.zzd;     // Catch: JSONException -> L22
        if (Build.VERSION.SDK_INT >= 34) goto L45;
        r24.sendBroadcast(r05);     // Catch: JSONException -> L22
        return;
    L45:
        r24.sendBroadcast(r05, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());     // Catch: JSONException -> L22
        return;
    L62:
        return;
    L47:
        e = move-exception;
        zzgu r25 = r14.zzu.zzi;     // Catch: JSONException -> L22
        zzP(r25);     // Catch: JSONException -> L22
        r25.zzb().zzb("Failed to persist Deferred Deep Link. exception", e);     // Catch: JSONException -> L22
        return;
    L61:
        return;
    L50:
        zzgu r26 = this.zzi;     // Catch: JSONException -> L22
        zzP(r26);     // Catch: JSONException -> L22
        r26.zze().zzd("Deferred Deep Link validation failed. gclid, gbraid, deep link", r102, r11, r03);     // Catch: JSONException -> L22
        return;
    L22:
        e = move-exception;
        zzgu r1 = this.zzi;
        zzP(r1);
        r1.zzb().zzb("Failed to parse the Deferred Deep Link response. exception", e);
        return;
    L56:
        zzgu r06 = this.zzi;
        zzP(r06);
        r06.zze().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(r10), r20);
        return;
    L5:
        if (r19 == 204) goto L10;
        r10 = 304;
        if (r19 == 304) goto L11;
        r10 = r19;
        goto L56
    }

    public final /* synthetic */ void zzK(zzjs r12) {
        zzhz r0 = this.zzj;
        zzP(r0);
        r0.zzg();
        zzal r02 = this.zzg;
        r02.zzb();
        zzbb r1 = new zzbb(this);
        r1.zzx();
        this.zzv = r1;
        com.google.android.gms.internal.measurement.zzdb r13 = r12.zzd;
        if (r13 != null) goto L6;
        long r2 = 0;
    L5:
        long r8 = r2;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r13 == null) goto L13;
        Bundle r14 = r13.zzd;
        if (r14 == null) goto L13;
        r22 = r14.getString("runtime_google_app_id", _UrlKt.FRAGMENT_ENCODE_SET);
    L13:
        long r6 = r12.zzc;
        zzgi r4 = new zzgi(this, r6, r8, r22);
        r4.zzc();
        this.zzw = r4;
        zzgl r11 = new zzgl(this);
        r11.zzc();
        this.zzt = r11;
        zznl r112 = new zznl(this);
        r112.zzc();
        this.zzu = r112;
        zzpp r113 = this.zzl;
        r113.zzy();
        this.zzh.zzy();
        this.zzw.zzd();
        zzlq r122 = new zzlq(this);
        r122.zzc();
        this.zzx = r122;
        r122.zzd();
        zzgu r123 = this.zzi;
        zzP(r123);
        zzgs r15 = r123.zzi();
        r02.zzi();
        r15.zzb("App measurement initialized, version", 161000L);
        zzP(r123);
        r123.zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String r16 = r4.zzj();
        zzN(r113);
        if (r113.zzad(r16, r02.zzz()) == false) goto L16;
        zzP(r123);
        r123.zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
    L17:
        zzP(r123);
        r123.zzj().zza("Debug-level message logging enabled");
        int r114 = this.zzD;
        AtomicInteger r03 = this.zzF;
        if (r114 == r03.get()) goto L20;
        zzP(r123);
        r123.zzb().zzc("Not all components initialized", Integer.valueOf(this.zzD), Integer.valueOf(r03.get()));
    L20:
        this.zzy = true;
        return;
    L16:
        zzP(r123);
        r123.zzi().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(String.valueOf(r16)));
        goto L17
    L6:
        r2 = r13.zza;
        goto L5
    }

    public final void zza(com.google.android.gms.internal.measurement.zzdb r13) {
        zzhz r132 = this.zzj;
        zzP(r132);
        r132.zzg();
        com.google.android.gms.internal.measurement.zzin r133 = zzx().zzj();
        com.google.android.gms.internal.measurement.zzin r0 = com.google.android.gms.internal.measurement.zzin.zzb;
        zzaif.zza();
        zzfx r1 = zzfy.zzaP;
        zzal r2 = this.zzg;
        boolean r12 = r2.zzp(null, r1);
        if (r133 != r0) goto L5;
        boolean r134 = true;
    L6:
        if (r12 == false) goto L9;
        zzpp r02 = this.zzl;
        zzN(r02);
        if (r02.zzU() == false) goto L9;
    L11:
        zzpp r03 = this.zzl;
        zzN(r03);
        r03.zzg();
        IntentFilter r14 = new IntentFilter();
        r14.addAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
        r14.addAction("com.google.android.gms.measurement.BATCHES_AVAILABLE");
        zzw r6 = new zzw(r03.zzu);
        zzic r04 = r03.zzu;
        ContextCompat.registerReceiver(r04.zzd, r6, r14, 2);
        zzgu r05 = r04.zzi;
        zzP(r05);
        r05.zzj().zza("Registered app receiver");
        if (r134 == false) goto L14;
        zzx().zzh(((Long) zzfy.zzB.zzb(null)).longValue());
    L14:
        zzhh r135 = this.zzh;
        zzN(r135);
        zzjl r06 = r135.zzl();
        int r15 = r06.zzb();
        zzji r62 = r2.zzw("google_analytics_default_allow_ad_storage", false);
        zzji r7 = r2.zzw("google_analytics_default_allow_analytics_storage", false);
        zzji r8 = zzji.zza;
        if (r62 != r8) goto L17;
        if (r7 != r8) goto L17;
    L21:
        if (TextUtils.isEmpty(zzv().zzk()) == true) goto L29;
        if (r15 == 0) goto L30;
        if (r15 == 30) goto L30;
        if (r15 == 10) goto L30;
        if (r15 != 40) goto L29;
    L30:
        zzlj r16 = this.zzp;
        zzO(r16);
        r16.zzs(new zzjl(null, null, -10), false);
    L29:
        zzjl r17 = null;
    L31:
        if (r17 == null) goto L33;
        zzlj r07 = this.zzp;
        zzO(r07);
        r07.zzs(r17, true);
        r06 = r17;
    L33:
        zzlj r18 = this.zzp;
        zzO(r18);
        r18.zzz(r06);
        zzN(r135);
        int r08 = r135.zzj().zzb();
        zzji r63 = r2.zzw("google_analytics_default_allow_ad_personalization_signals", true);
        if (r63 == r8) goto L36;
        zzgu r72 = this.zzi;
        zzP(r72);
        r72.zzk().zzb("Default ad personalization consent from Manifest", r63);
    L36:
        zzji r64 = r2.zzw("google_analytics_default_allow_ad_user_data", true);
        if (r64 == r8) goto L42;
        if (zzjl.zzu(-10, r08) == false) goto L42;
        zzO(r18);
        r18.zzq(zzba.zza(r64, -10), true);
    L46:
        Boolean r09 = r2.zzr("google_analytics_tcf_data_enabled");
        if (r09 != null) goto L49;
    L50:
        zzgu r010 = this.zzi;
        zzP(r010);
        r010.zzj().zza("TCF client enabled.");
        zzO(r18);
        r18.zzD();
        zzO(r18);
        r18.zzC();
    L51:
        zzN(r135);
        zzhe r011 = r135.zzc;
        if (r011.zza() != 0) goto L54;
        zzgu r22 = this.zzi;
        zzP(r22);
        long r65 = this.zza;
        r22.zzk().zzb("Persisting first open", Long.valueOf(r65));
        zzN(r135);
        r011.zzb(r65);
    L54:
        zzO(r18);
        r18.zzb.zzc();
        if (zzH() == true) goto L76;
        if (zzB() == false) goto L103;
        zzpp r136 = this.zzl;
        zzN(r136);
        if (r136.zzab("android.permission.INTERNET") == true) goto L61;
        zzgu r012 = this.zzi;
        zzP(r012);
        r012.zzb().zza("App is missing INTERNET permission");
    L61:
        zzN(r136);
        if (r136.zzab("android.permission.ACCESS_NETWORK_STATE") == true) goto L64;
        zzgu r137 = this.zzi;
        zzP(r137);
        r137.zzb().zza("App is missing ACCESS_NETWORK_STATE permission");
    L64:
        Context r138 = this.zzd;
        if (Wrappers.packageManager(r138).isCallerInstantApp() == false) goto L67;
    L74:
        zzgu r139 = this.zzi;
        zzP(r139);
        r139.zzb().zza("Uploading is not possible. App measurement disabled");
        goto L103
    L67:
        if (this.zzg.zzE() == true) goto L74;
        if (zzpp.zzax(r138) == true) goto L72;
        zzgu r013 = this.zzi;
        zzP(r013);
        r013.zzb().zza("AppMeasurementReceiver not registered/enabled");
    L72:
        if (zzpp.zzS(r138, false) == true) goto L74;
        zzgu r1310 = this.zzi;
        zzP(r1310);
        r1310.zzb().zza("AppMeasurementService not registered/enabled");
    L103:
        zzaif.zza();
        if (this.zzg.zzp(null, zzfy.zzaP) == false) goto L111;
        zzpp r1311 = this.zzl;
        zzN(r1311);
        if (r1311.zzU() == false) goto L111;
        long r014 = ((Integer) zzfy.zzaw.zzb(null)).intValue();
        long r015 = Math.max(500, ((r014 * 1000) + new Random().nextInt(5000)) - this.zzn.elapsedRealtime());
        if (r015 <= 500) goto L110;
        zzgu r1312 = this.zzi;
        zzP(r1312);
        r1312.zzk().zzb("Waiting to fetch trigger URIs until some time after boot. Delay in millis", Long.valueOf(r015));
    L110:
        zzlj r1313 = this.zzp;
        zzO(r1313);
        r1313.zzu(r015);
    L111:
        zzhh r122 = this.zzh;
        zzN(r122);
        r122.zzj.zzb(true);
        return;
    L76:
        if (TextUtils.isEmpty(zzv().zzk()) == true) goto L84;
        zzpp r23 = this.zzl;
        zzN(r23);
        String r4 = zzv().zzk();
        zzN(r135);
        r135.zzg();
        if (r23.zzD(r4, r135.zzd().getString("gmp_app_id", null)) == false) goto L83;
        zzgu r24 = this.zzi;
        zzP(r24);
        r24.zzi().zza("Rechecking which service to use due to a GMP App Id change");
        zzN(r135);
        r135.zzg();
        Boolean r25 = r135.zzi();
        SharedPreferences.Editor r42 = r135.zzd().edit();
        r42.clear();
        r42.apply();
        if (r25 == null) goto L82;
        r135.zzh(r25);
    L82:
        zzm().zzh();
        this.zzu.zzM();
        this.zzu.zzI();
        zzN(r135);
        r011.zzb(this.zza);
        zzN(r135);
        r135.zze.zzb(null);
    L83:
        zzN(r135);
        String r016 = zzv().zzk();
        r135.zzg();
        SharedPreferences.Editor r26 = r135.zzd().edit();
        r26.putString("gmp_app_id", r016);
        r26.apply();
    L84:
        zzN(r135);
        if (r135.zzl().zzo(zzjk.zzb) == true) goto L87;
        zzN(r135);
        r135.zze.zzb(null);
    L87:
        zzO(r18);
        zzN(r135);
        r18.zzQ(r135.zze.zza());
        zzpp r1314 = this.zzl;
        zzN(r1314);
        r1314.zzu.zzd.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");     // Catch: ClassNotFoundException -> L90
    L94:
        if (TextUtils.isEmpty(zzv().zzk()) == true) goto L103;
        boolean r1315 = zzB();
        zzhh r017 = this.zzh;
        zzN(r017);
        if (r017.zzn() == false) goto L98;
    L100:
        if (r1315 == false) goto L102;
        zzlj r1316 = this.zzp;
        zzO(r1316);
        r1316.zzT();
    L102:
        zzoc r1317 = this.zzk;
        zzO(r1317);
        r1317.zza.zza();
        zzt().zzC(new AtomicReference());
        zznl r1318 = zzt();
        zzN(r017);
        r1318.zzH(r017.zzt.zza());
        goto L103
    L98:
        if (this.zzg.zzt() == true) goto L100;
        zzN(r017);
        r017.zzm(!r1315);
    L90:
        zzhh r1319 = this.zzh;
        zzN(r1319);
        zzhg r018 = r1319.zzq;
        if (TextUtils.isEmpty(r018.zza()) == true) goto L94;
        zzgu r19 = this.zzi;
        zzP(r19);
        r19.zze().zza("Remote config removed with active feature rollouts");
        zzN(r1319);
        r018.zzb(null);
        goto L94
    L49:
        if (r09.booleanValue() == false) goto L51;
    L42:
        if (TextUtils.isEmpty(zzv().zzk()) == true) goto L46;
        if (r08 == 0) goto L45;
        if (r08 != 30) goto L46;
    L45:
        zzO(r18);
        r18.zzq(new zzba(null, -10, null, null), true);
    L17:
        zzN(r135);
        if (r135.zzk(-10) == false) goto L21;
        r17 = zzjl.zza(r62, r7, -10);
    L9:
        if (r134 == false) goto L14;
        r134 = true;
        goto L11
    L5:
        r134 = false;
        goto L6
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzae zzaV() {
        return this.zzf;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzgu zzaW() {
        zzgu r0 = this.zzi;
        zzP(r0);
        return r0;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzhz zzaX() {
        zzhz r0 = this.zzj;
        zzP(r0);
        return r0;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final Context zzaZ() {
        return this.zzd;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final Clock zzba() {
        return this.zzn;
    }

    @Pure
    public final zzal zzc() {
        return this.zzg;
    }

    @Pure
    public final zzhh zzd() {
        zzhh r0 = this.zzh;
        zzN(r0);
        return r0;
    }

    public final zzgu zzf() {
        zzgu r1 = this.zzi;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.zzv() == false) goto L9;
        return r1;
    L9:
        return null;
    }

    @Pure
    public final zzoc zzh() {
        zzoc r0 = this.zzk;
        zzO(r0);
        return r0;
    }

    @SideEffectFree
    public final zzhz zzi() {
        return this.zzj;
    }

    @Pure
    public final zzlj zzj() {
        zzlj r0 = this.zzp;
        zzO(r0);
        return r0;
    }

    @Pure
    public final zzpp zzk() {
        zzpp r0 = this.zzl;
        zzN(r0);
        return r0;
    }

    @Pure
    public final zzgn zzl() {
        return this.zzm;
    }

    @Pure
    public final zzgl zzm() {
        zzO(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzlo zzn() {
        zzlo r0 = this.zzr;
        zzP(r0);
        return r0;
    }

    @Pure
    public final boolean zzp() {
        return this.zze;
    }

    @Pure
    public final String zzq() {
        return this.zzs;
    }

    @Pure
    public final zzmb zzs() {
        zzmb r0 = this.zzo;
        zzO(r0);
        return r0;
    }

    @Pure
    public final zznl zzt() {
        zzO(this.zzu);
        return this.zzu;
    }

    @Pure
    public final zzbb zzu() {
        zzP(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzgi zzv() {
        zzO(this.zzw);
        return this.zzw;
    }

    @Pure
    public final zzd zzw() {
        zzd r0 = this.zzq;
        zzM(r0);
        return r0;
    }

    @Pure
    public final zzlq zzx() {
        zzM(this.zzx);
        return this.zzx;
    }

    public final void zzz(boolean r1) {
        this.zzB = Boolean.valueOf(r1);
    }
}
