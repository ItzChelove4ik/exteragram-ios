package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.internal.play_billing.zza;
import com.google.android.gms.internal.play_billing.zzaz;
import com.google.android.gms.internal.play_billing.zzbi;
import com.google.android.gms.internal.play_billing.zzbl;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzcz;
import com.google.android.gms.internal.play_billing.zzdj;
import com.google.android.gms.internal.play_billing.zzdk;
import com.google.android.gms.internal.play_billing.zzhv;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzhz;
import com.google.android.gms.internal.play_billing.zzib;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzil;
import com.google.android.gms.internal.play_billing.zziq;
import com.google.android.gms.internal.play_billing.zzis;
import com.google.android.gms.internal.play_billing.zzjt;
import com.google.android.gms.internal.play_billing.zzjv;
import com.google.android.gms.internal.play_billing.zzp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class BillingClientImpl extends BillingClient {
    private boolean zzA;
    private boolean zzB;
    private boolean zzC;
    private PendingPurchasesParams zzD;
    private boolean zzE;
    private boolean zzF;
    private volatile BillingClientStateListener zzG;
    private ExecutorService zzH;
    private final Long zzI;
    private zzbl zzJ;
    private final Object zza;
    private volatile int zzb;
    private final String zzc;
    private final String zzd;
    private final Handler zze;
    private volatile zzs zzf;
    private Context zzg;
    private zzch zzh;
    private volatile com.google.android.gms.internal.play_billing.zzam zzi;
    private volatile zzbf zzj;
    private boolean zzk;
    private boolean zzl;
    private int zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private boolean zzx;
    private boolean zzy;
    private boolean zzz;

    public BillingClientImpl(String r2, Context r3, zzch r4, ExecutorService r5, BillingClient.Builder r6) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        Long r42 = Long.valueOf(new Random().nextLong());
        this.zzI = r42;
        this.zzJ = zzaz.zza();
        this.zzc = "8.0.0";
        String r0 = zzaw();
        this.zzd = r0;
        this.zzg = r3.getApplicationContext();
        zziq r32 = zzis.zzc();
        r32.zzs("8.0.0");
        if (r0 == null) goto L5;
        r32.zzt(r0);
    L5:
        r32.zzq(this.zzg.getPackageName());
        r32.zzn(r42.longValue());
        r32.zzr(r6.zza);
        r32.zza(Build.VERSION.SDK_INT);
        r32.zzp(772604006);
        r32.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);     // Catch: Throwable -> L8
    L10:
        this.zzh = new zzcl(this.zzg, (zzis) r32.zze());
        this.zzg.getPackageName();
        this.zzE = r6.zza;
        return;
    L8:
        th = move-exception;
        zzc.zzo("BillingClient", "Error getting app version code.", th);
        goto L10
    }

    public BillingClientImpl(String r9, PendingPurchasesParams r10, Context r11, PurchasesUpdatedListener r12, zzb r13, zzch r14, ExecutorService r15, BillingClient.Builder r16) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        this.zzI = Long.valueOf(new Random().nextLong());
        this.zzJ = zzaz.zza();
        this.zzc = "8.0.0";
        this.zzd = zzaw();
        initialize(r11, r12, r10, null, "8.0.0", null, r16);
    }

    public BillingClientImpl(String r8, PendingPurchasesParams r9, Context r10, zzco r11, zzch r12, ExecutorService r13, BillingClient.Builder r14) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        Long r122 = Long.valueOf(new Random().nextLong());
        this.zzI = r122;
        this.zzJ = zzaz.zza();
        this.zzc = "8.0.0";
        String r0 = zzaw();
        this.zzd = r0;
        this.zzg = r10.getApplicationContext();
        zziq r102 = zzis.zzc();
        r102.zzs("8.0.0");
        if (r0 == null) goto L5;
        r102.zzt(r0);
    L5:
        r102.zzq(this.zzg.getPackageName());
        r102.zzn(r122.longValue());
        r102.zzr(r14.zza);
        r102.zza(Build.VERSION.SDK_INT);
        r102.zzp(772604006);
        r102.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);     // Catch: Throwable -> L8
    L10:
        this.zzh = new zzcl(this.zzg, (zzis) r102.zze());
        zzc.zzn("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzf = new zzs(this.zzg, null, null, null, null, this.zzh);
        this.zzD = r9;
        this.zzg.getPackageName();
        this.zzE = r14.zza;
        return;
    L8:
        th = move-exception;
        zzc.zzo("BillingClient", "Error getting app version code.", th);
        goto L10
    }

    private void initialize(Context r13, PurchasesUpdatedListener r14, PendingPurchasesParams r15, zzb r16, String r17, zzch r18, BillingClient.Builder r19) {
        this.zzg = r13.getApplicationContext();
        zziq r132 = zzis.zzc();
        r132.zzs(r17);
        String r0 = this.zzd;
        if (r0 == null) goto L5;
        r132.zzt(r0);
    L5:
        r132.zzq(this.zzg.getPackageName());
        r132.zzn(this.zzI.longValue());
        r132.zzr(r19.zza);
        r132.zza(Build.VERSION.SDK_INT);
        r132.zzp(772604006);
        boolean r4 = false;
        r132.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);     // Catch: Throwable -> L8
    L10:
        if (r18 == null) goto L12;
        this.zzh = r18;
    L13:
        if (r14 != null) goto L15;
        zzc.zzn("BillingClient", "Billing client should have a valid listener but the provided is null.");
    L15:
        this.zzf = new zzs(this.zzg, r14, null, r16, null, this.zzh);
        this.zzD = r15;
        if (r16 == null) goto L18;
        r4 = true;
    L18:
        this.zzF = r4;
        this.zzg.getPackageName();
        this.zzE = r19.zza;
        return;
    L12:
        this.zzh = new zzcl(this.zzg, (zzis) r132.zze());
    L8:
        th = move-exception;
        zzc.zzo("BillingClient", "Error getting app version code.", th);
        goto L10
    }

    public static Future zzG(Callable r2, long r3, Runnable r5, Handler r6, ExecutorService r7) {
        Future r22 = r7.submit(r2);     // Catch: Exception -> L5
        r6.postDelayed(new zzaj(r22, r5), (long) (r3 * 0.95d));
        return r22;
    L5:
        e = move-exception;
        zzc.zzo("BillingClient", "Async task throws exception!", e);
        return null;
    }

    public static /* synthetic */ void zzH(BillingClientImpl r3, ConsumeResponseListener r4, ConsumeParams r5) {
        zzie r0 = zzie.zzx;
        BillingResult r1 = zzcj.zzk;
        r3.zzbd(r0, 4, r1);
        r4.onConsumeResponse(r1, r5.getPurchaseToken());
    }

    public static /* synthetic */ void zzI(BillingClientImpl r3, PurchasesResponseListener r4) {
        zzie r0 = zzie.zzx;
        BillingResult r1 = zzcj.zzk;
        r3.zzbd(r0, 9, r1);
        r4.onQueryPurchasesResponse(r1, com.google.android.gms.internal.play_billing.zzbt.zzk());
    }

    public static /* synthetic */ void zzN(BillingClientImpl r3, ProductDetailsResponseListener r4) {
        zzie r0 = zzie.zzx;
        BillingResult r1 = zzcj.zzk;
        r3.zzbd(r0, 7, r1);
        r4.onProductDetailsResponse(r1, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing.zzbt.zzk(), com.google.android.gms.internal.play_billing.zzbt.zzk()));
    }

    public static /* synthetic */ void zzP(BillingClientImpl r1, BillingResult r2) {
        PurchasesUpdatedListener r0 = r1.zzf.zzd();
        zzs r12 = r1.zzf;
        if (r0 == null) goto L6;
        r12.zzd().onPurchasesUpdated(r2, null);
        return;
    L6:
        zzc.zzn("BillingClient", "No valid listener is set in BroadcastManager");
    }

    public static /* bridge */ /* synthetic */ void zzT(BillingClientImpl r0, int r1) {
        r0.zzm = r1;
    }

    public static /* bridge */ /* synthetic */ void zzU(BillingClientImpl r0, com.google.android.gms.internal.play_billing.zzam r1) {
        r0.zzi = r1;
    }

    public static /* bridge */ /* synthetic */ void zzV(BillingClientImpl r0, boolean r1) {
        r0.zzl = r1;
    }

    public static /* bridge */ /* synthetic */ void zzW(BillingClientImpl r0, boolean r1) {
        r0.zzk = r1;
    }

    public static /* bridge */ /* synthetic */ void zzZ(BillingClientImpl r0, zzhx r1) {
        r0.zzaO(r1);
    }

    public static /* bridge */ /* synthetic */ int zza(BillingClientImpl r0) {
        return r0.zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.billingclient.api.BillingClientImpl] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.billingclient.api.BillingClientImpl] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.StringBuilder] */
    private final void zzaD(ConsumeParams r10, ConsumeResponseListener r11) {
        String r4 = r10.getPurchaseToken();
        ?? r2 = new StringBuilder("Consuming purchase with token: ");     // Catch: Exception -> L16 DeadObjectException -> L18
        r2.append(r4);     // Catch: Exception -> L16 DeadObjectException -> L18
        zzc.zzm("BillingClient", r2.toString());     // Catch: Exception -> L16 DeadObjectException -> L18
        Object r102 = this.zza;     // Catch: Exception -> L16 DeadObjectException -> L18
        monitor-enter(r102);     // Catch: Exception -> L16 DeadObjectException -> L18
        com.google.android.gms.internal.play_billing.zzam r1 = this.zzi;     // Catch: Throwable -> L34
        monitor-exit(r102);     // Catch: Throwable -> L34
        if (r1 != null) goto L20;
    L10:
        zzaG(r11, r4, zzcj.zzj, zzie.zzbc, "Service has been reset to null.", null);     // Catch: Exception -> L12 DeadObjectException -> L14
        return;
    L20:
        boolean r9 = this.zzp;     // Catch: Exception -> L12 DeadObjectException -> L14
        Context r103 = this.zzg;
        if (r9 == false) goto L27;
        String r92 = r103.getPackageName();     // Catch: Exception -> L12 DeadObjectException -> L14
        boolean r104 = this.zzp;     // Catch: Exception -> L12 DeadObjectException -> L14
        String r112 = this.zzc;     // Catch: Exception -> L12 DeadObjectException -> L14
        String r5 = this.zzd;     // Catch: Exception -> L12 DeadObjectException -> L14
        long r6 = this.zzI.longValue();     // Catch: Exception -> L12 DeadObjectException -> L14
        Bundle r8 = new Bundle();     // Catch: Exception -> L12 DeadObjectException -> L14
        if (r104 == false) goto L26;
        zzc.zzc(r8, r112, r5, r6);     // Catch: Exception -> L12 DeadObjectException -> L14
    L26:
        Bundle r93 = r1.zze(9, r92, r4, r8);     // Catch: Exception -> L12 DeadObjectException -> L14
        int r105 = r93.getInt("RESPONSE_CODE");     // Catch: Exception -> L12 DeadObjectException -> L14
        String r94 = zzc.zzj(r93, "BillingClient");     // Catch: Exception -> L12 DeadObjectException -> L14
    L28:
        BillingResult r52 = zzcj.zza(r105, r94);     // Catch: Exception -> L12 DeadObjectException -> L14
        if (r105 != 0) goto L32;
        zzc.zzm("BillingClient", "Successfully consumed purchase.");     // Catch: Exception -> L12 DeadObjectException -> L14
        r11.onConsumeResponse(r52, r4);     // Catch: Exception -> L12 DeadObjectException -> L14
        return;
    L32:
        zzaG(r11, r4, r52, zzie.zzw, "Error consuming purchase with token. Response code: " + r105, null);     // Catch: Exception -> L12 DeadObjectException -> L14
        return;
    L27:
        r105 = r1.zza(3, r103.getPackageName(), r4);     // Catch: Exception -> L12 DeadObjectException -> L14
        r94 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L28
    L34:
        th = th;
    L47:
        throw th;     // Catch: Exception -> L12 DeadObjectException -> L14
    L39:
        th = th;
    L14:
        e = e;
    L43:
        r2.zzaG(r11, r4, zzcj.zzj, zzie.zzC, "Error consuming purchase!", e);
        return;
    L12:
        e = e;
    L41:
        r2.zzaG(r11, r4, zzcj.zzh, zzie.zzC, "Error consuming purchase!", e);
        return;
    L18:
        e = e;
        r2 = this;
    L16:
        e = e;
        r2 = this;
        goto L41
    }

    private final void zzaG(ConsumeResponseListener r2, String r3, BillingResult r4, zzie r5, String r6, Exception r7) {
        zzc.zzo("BillingClient", r6, r7);
        zzbf(r5, 4, r4, zzcg.zza(r7));
        r2.onConsumeResponse(r4, r3);
    }

    private final void zzaO(zzhx r2) {
        this.zzh.zzb(r2, this.zzm);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzaP(zzhx r7, long r8, boolean r10) {
        this.zzh.zze(r7, this.zzm, r8, r10);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzaQ(zzib r2) {
        this.zzh.zzg(r2, this.zzm);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzaR(zzie r4, BillingResult r5, int r6) {
        int r0 = zzcg.$r8$clinit;     // Catch: Throwable -> L8
        zzhv r42 = (zzhv) zzcg.zzb(r4, 6, r5, null, zzil.zza).zzm();     // Catch: Throwable -> L8
        zzjt r52 = zzjv.zzc();     // Catch: Throwable -> L8
        if (r6 <= 0) goto L5;
        boolean r02 = true;
    L6:
        r52.zza(r02);     // Catch: Throwable -> L8
        r52.zzl(r6);     // Catch: Throwable -> L8
        r42.zzo(r52);     // Catch: Throwable -> L8
        zzaO((zzhx) r42.zze());     // Catch: Throwable -> L8
        return;
    L5:
        r02 = false;
    L8:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzaS(int r7) {
        Object r1 = this.zza;
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb != 3) goto L10;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L10:
        zzc.zzm("BillingClient", "Setting clientState from " + zzaZ(this.zzb) + " to " + zzaZ(r7));     // Catch: Throwable -> L8
        this.zzb = r7;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
    }

    private final void zzaU(BillingClientStateListener r8, int r9) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (zzaY() == false) goto L12;
        BillingResult r7 = zzap(r9);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
    L65:
        if (r7 == null) goto L72;
        r8.onBillingSetupFinished(r7);
        return;
    L72:
        return;
    L12:
        if (this.zzb != 1) goto L17;
        zzc.zzn("BillingClient", "Client is already in the process of connecting to billing service.");     // Catch: Throwable -> L9
        zzie r1 = zzie.zzK;     // Catch: Throwable -> L9
        BillingResult r2 = zzcj.zzd;     // Catch: Throwable -> L9
        zzaR(r1, r2, r9);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
    L15:
        r7 = r2;
        goto L65
    L17:
        if (this.zzb != 3) goto L21;
        zzc.zzn("BillingClient", "Client was already closed and can't be reused. Please create another instance.");     // Catch: Throwable -> L9
        zzie r12 = zzie.zzL;     // Catch: Throwable -> L9
        r2 = zzcj.zzj;     // Catch: Throwable -> L9
        zzaR(r12, r2, r9);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        goto L15
    L21:
        zzaS(1);     // Catch: Throwable -> L9
        if (r9 != 0) goto L24;
        this.zzG = r8;     // Catch: Throwable -> L9
        r9 = 0;
    L24:
        zzaV();     // Catch: Throwable -> L9
        zzc.zzm("BillingClient", "Starting in-app billing setup.");     // Catch: Throwable -> L9
        this.zzj = new zzbf(this, r8, r9, null);     // Catch: Throwable -> L9
        this.zzj.zzc();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        Intent r02 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        r02.setPackage("com.android.vending");
        List<ResolveInfo> r3 = this.zzg.getPackageManager().queryIntentServices(r02, 0);
        if (r3 != null) goto L29;
    L63:
        zzie r32 = zzie.zzO;
    L64:
        zzaS(0);
        zzc.zzm("BillingClient", "Billing service unavailable on device.");
        BillingResult r03 = zzcj.zzb;
        zzaR(r32, r03, r9);
        r7 = r03;
        goto L65
    L29:
        if (r3.isEmpty() == true) goto L63;
        ServiceInfo r33 = r3.get(0).serviceInfo;
        if (r33 == null) goto L62;
        String r5 = r33.packageName;
        String r34 = r33.name;
        if (Objects.equals(r5, "com.android.vending") == false) goto L61;
        if (r34 == null) goto L61;
        ComponentName r6 = new ComponentName(r5, r34);
        Intent r35 = new Intent(r02);
        r35.setComponent(r6);
        r35.putExtra("playBillingLibraryVersion", this.zzc);
        Object r04 = this.zza;
        monitor-enter(r04);
    L42:
        th = move-exception;
        throw th;
    L38:
        if (this.zzb != 2) goto L45;
        r7 = zzap(r9);     // Catch: Throwable -> L42
        monitor-exit(r04);     // Catch: Throwable -> L42
        goto L65
    L45:
        if (this.zzb == 1) goto L49;
        zzc.zzn("BillingClient", "Client state no longer CONNECTING, returning service disconnected.");     // Catch: Throwable -> L42
        zzie r13 = zzie.zzba;     // Catch: Throwable -> L42
        r2 = zzcj.zzj;     // Catch: Throwable -> L42
        zzaR(r13, r2, r9);     // Catch: Throwable -> L42
        monitor-exit(r04);     // Catch: Throwable -> L42
        goto L15
    L49:
        zzbf r52 = this.zzj;     // Catch: Throwable -> L42
        monitor-exit(r04);     // Catch: Throwable -> L42
        if (r9 > 0) goto L53;
    L55:
        boolean r05 = this.zzg.bindService(r35, r52, 1);
    L56:
        if (r05 == false) goto L58;
        zzc.zzm("BillingClient", "Service was bonded successfully.");
        r7 = null;
        goto L65
    L58:
        r32 = zzie.zzM;
        zzc.zzn("BillingClient", "Connection to Billing service is blocked.");
        goto L64
    L53:
        if (Build.VERSION.SDK_INT < 29) goto L55;
        r05 = this.zzg.bindService(r35, 1, zzF(), r52);
    L61:
        r32 = zzie.zzN;
        zzc.zzn("BillingClient", "The device doesn't have valid Play Store.");
        goto L64
    L62:
        r32 = zzie.zzN;
        zzc.zzn("BillingClient", "The device doesn't have valid Play Store.");
        goto L64
    }

    private final void zzaV() {
        Object r0 = this.zza;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.zzj != null) goto L25;
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L25:
        this.zzg.unbindService(this.zzj);     // Catch: Throwable -> L12
        this.zzi = null;     // Catch: Throwable -> L10
        this.zzj = null;     // Catch: Throwable -> L10
        goto L18
    L12:
        th = move-exception;
        zzc.zzo("BillingClient", "There was an exception while unbinding service!", th);     // Catch: Throwable -> L15
        this.zzi = null;     // Catch: Throwable -> L10
        this.zzj = null;     // Catch: Throwable -> L10
        goto L18
    L15:
        th = move-exception;
        this.zzi = null;     // Catch: Throwable -> L10
        this.zzj = null;     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zzaW(long r6) {
    L10:
        e = move-exception;
        if ((e instanceof InterruptedException) == false) goto L17;
        Thread.currentThread().interrupt();
    L17:
        zzc.zzo("BillingClient", "Error during reconnection attempt: ", e);
    L19:
        return zzaY();
    L4:
        if (Build.VERSION.SDK_INT >= 29) goto L6;
        long r1 = 0;
    L7:
        BillingResult r12 = (BillingResult) zzar(1).get(r1, TimeUnit.MILLISECONDS);     // Catch: Exception -> L10
        if (r12.getResponseCode() != 0) goto L12;
        zzc.zzm("BillingClient", "Reconnection succeeded with result: " + r12.getResponseCode());     // Catch: Exception -> L10
    L20:
        goto L19
    L12:
        zzc.zzn("BillingClient", "Reconnection failed with result: " + r12.getResponseCode());     // Catch: Exception -> L10
        goto L20
    L6:
        r1 = 3000;
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zzaX(long r14) {
        TimeUnit r142 = TimeUnit.MILLISECONDS;
        zzbi r15 = zzbi.zzb(this.zzJ);
        int r0 = 1;
        long r3 = 30000;
    L4:
        if (r0 > 3) goto L34;
        long r32 = Math.max(0, r3);     // Catch: Exception -> L10
        if (r32 <= 0) goto L8;
        BillingResult r33 = (BillingResult) zzar(r0).get(r32, r142);     // Catch: Exception -> L10
        if (r33.getResponseCode() == 0) goto L14;
        zzc.zzn("BillingClient", "Reconnection failed with result: " + r33.getResponseCode());     // Catch: Exception -> L10
    L22:
        r3 = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS - r15.zza(r142);
        long r9 = ((long) Math.pow(2.0d, r0 - 1)) * 1000;
        if (r3 < r9) goto L24;
        if (r0 >= 3) goto L33;
        if (r9 <= 0) goto L33;
        Thread.sleep(r9);     // Catch: InterruptedException -> L31
        r3 = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS - r15.zza(r142);
    L31:
        e = move-exception;
        Thread.currentThread().interrupt();
        zzc.zzo("BillingClient", "Error sleeping during reconnection attempt: ", e);
    L33:
        r0 = r0 + 1;
        goto L4
    L24:
        zzc.zzn("BillingClient", "Reconnection failed due to timeout limit reached.");
        return zzaY();
    L14:
        zzc.zzm("BillingClient", "Reconnection succeeded with result: " + r33.getResponseCode());     // Catch: Exception -> L10
        return zzaY();
    L8:
        zzc.zzn("BillingClient", "No time remaining for reconnection attempt.");     // Catch: Exception -> L10
        return zzaY();
    L10:
        e = move-exception;
        if ((e instanceof InterruptedException) == false) goto L21;
        Thread.currentThread().interrupt();
    L21:
        zzc.zzo("BillingClient", "Error during reconnection attempt: ", e);
    L34:
        zzc.zzn("BillingClient", "Max retries reached.");
        return zzaY();
    }

    private final boolean zzaY() {
        Object r0 = this.zza;
        monitor-enter(r0);
        boolean r3 = false;
        if (this.zzb == 2) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r3;
    L7:
        if (this.zzi == null) goto L13;
        if (this.zzj == null) goto L13;
        r3 = true;
    L11:
        th = move-exception;
        throw th;
    }

    private static final String zzaZ(int r1) {
        if (r1 != 0) goto L4;
        return "DISCONNECTED";
    L4:
        if (r1 != 1) goto L6;
        return "CONNECTING";
    L6:
        if (r1 == 2) goto L9;
        return "CLOSED";
    L9:
        return "CONNECTED";
    }

    public static /* bridge */ /* synthetic */ void zzaa(BillingClientImpl r0, zzib r1) {
        r0.zzaQ(r1);
    }

    public static /* bridge */ /* synthetic */ void zzab(BillingClientImpl r0, zzie r1, BillingResult r2, int r3) {
        r0.zzaR(r1, r2, r3);
    }

    public static /* bridge */ /* synthetic */ void zzac(BillingClientImpl r3, int r4) {
        r3.zzm = r4;
        boolean r1 = false;
        if (r4 < 26) goto L5;
        boolean r0 = true;
    L6:
        r3.zzC = r0;
        if (r4 < 24) goto L9;
        boolean r02 = true;
    L10:
        r3.zzB = r02;
        if (r4 < 23) goto L13;
        boolean r03 = true;
    L14:
        r3.zzA = r03;
        if (r4 < 22) goto L17;
        boolean r04 = true;
    L18:
        r3.zzz = r04;
        if (r4 < 21) goto L21;
        boolean r05 = true;
    L22:
        r3.zzy = r05;
        if (r4 < 20) goto L25;
        boolean r06 = true;
    L26:
        r3.zzx = r06;
        if (r4 < 19) goto L29;
        boolean r07 = true;
    L30:
        r3.zzw = r07;
        if (r4 < 18) goto L33;
        boolean r08 = true;
    L34:
        r3.zzv = r08;
        if (r4 < 17) goto L37;
        boolean r09 = true;
    L38:
        r3.zzu = r09;
        if (r4 < 16) goto L41;
        boolean r010 = true;
    L42:
        r3.zzt = r010;
        if (r4 < 15) goto L45;
        boolean r011 = true;
    L46:
        r3.zzs = r011;
        if (r4 < 14) goto L49;
        boolean r012 = true;
    L50:
        r3.zzr = r012;
        if (r4 < 12) goto L53;
        boolean r013 = true;
    L54:
        r3.zzq = r013;
        if (r4 < 9) goto L57;
        boolean r014 = true;
    L58:
        r3.zzp = r014;
        if (r4 < 8) goto L61;
        boolean r015 = true;
    L62:
        r3.zzo = r015;
        if (r4 < 6) goto L65;
        r1 = true;
    L65:
        r3.zzn = r1;
        return;
    L61:
        r015 = false;
        goto L62
    L57:
        r014 = false;
        goto L58
    L53:
        r013 = false;
        goto L54
    L49:
        r012 = false;
        goto L50
    L45:
        r011 = false;
        goto L46
    L41:
        r010 = false;
        goto L42
    L37:
        r09 = false;
        goto L38
    L33:
        r08 = false;
        goto L34
    L29:
        r07 = false;
        goto L30
    L25:
        r06 = false;
        goto L26
    L21:
        r05 = false;
        goto L22
    L17:
        r04 = false;
        goto L18
    L13:
        r03 = false;
        goto L14
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public static /* bridge */ /* synthetic */ void zzad(BillingClientImpl r0, int r1) {
        r0.zzaS(0);
    }

    public static /* bridge */ /* synthetic */ void zzae(BillingClientImpl r2, int r3) {
        if (r3 != 0) goto L21;
        Object r32 = r2.zza;
        monitor-enter(r32);
    L9:
        th = move-exception;
        throw th;
    L6:
        if (r2.zzb != 3) goto L11;
        monitor-exit(r32);     // Catch: Throwable -> L9
        return;
    L11:
        r2.zzaS(2);     // Catch: Throwable -> L9
        if (r2.zzf == null) goto L14;
        zzs r0 = r2.zzf;     // Catch: Throwable -> L9
    L15:
        monitor-exit(r32);     // Catch: Throwable -> L9
        if (r0 == null) goto L24;
        r0.zzg(r2.zzy);
        return;
    L24:
        return;
    L14:
        r0 = null;
        goto L15
    L21:
        r2.zzaS(0);
    }

    public static /* bridge */ /* synthetic */ void zzaf(BillingClientImpl r0) {
        r0.zzaV();
    }

    public static /* bridge */ /* synthetic */ boolean zzah(BillingClientImpl r0, long r1) {
        return r0.zzaX(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
    }

    public static /* bridge */ /* synthetic */ boolean zzai(BillingClientImpl r2) {
        Object r0 = r2.zza;
        monitor-enter(r0);
        boolean r1 = true;
        if (r2.zzb == 1) goto L8;
        r1 = false;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L10:
        th = move-exception;
        throw th;
    }

    public static /* bridge */ /* synthetic */ zzcw zzaj(BillingClientImpl r0, String r1, boolean r2, int r3) {
        return r0.zzbb(r1, false, 9);
    }

    public static /* bridge */ /* synthetic */ void zzak(BillingClientImpl r0, zzie r1, int r2, BillingResult r3) {
        r0.zzbd(r1, 9, r3);
    }

    private final /* synthetic */ Bundle zzal(int r8, String r9, String r10, BillingFlowParams r11, Bundle r12) {
        Object r112 = this.zza;     // Catch: Exception -> L15 DeadObjectException -> L18
        monitor-enter(r112);     // Catch: Exception -> L15 DeadObjectException -> L18
        com.google.android.gms.internal.play_billing.zzam r0 = this.zzi;     // Catch: Throwable -> L11
        monitor-exit(r112);     // Catch: Throwable -> L11
        if (r0 != null) goto L10;
        return zzc.zzd(zzcj.zzj, zzie.zzbc);
    L10:
        return r0.zzg(r8, this.zzg.getPackageName(), r9, r10, null, r12);
    L11:
        th = move-exception;
        throw th;     // Catch: Exception -> L15 DeadObjectException -> L18
    L18:
        e = move-exception;
        return zzc.zze(zzcj.zzj, zzie.zze, zzcg.zza(e));
    L15:
        e = move-exception;
        return zzc.zze(zzcj.zzh, zzie.zze, zzcg.zza(e));
    }

    private final /* synthetic */ Bundle zzam(String r9, String r10) {
        Object r1 = this.zza;     // Catch: Exception -> L15 DeadObjectException -> L18
        monitor-enter(r1);     // Catch: Exception -> L15 DeadObjectException -> L18
        com.google.android.gms.internal.play_billing.zzam r2 = this.zzi;     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        if (r2 != null) goto L10;
        return zzc.zzd(zzcj.zzj, zzie.zzbc);
    L10:
        return r2.zzf(3, this.zzg.getPackageName(), r9, r10, null);
    L11:
        th = move-exception;
        throw th;     // Catch: Exception -> L15 DeadObjectException -> L18
    L18:
        e = move-exception;
        return zzc.zze(zzcj.zzj, zzie.zze, zzcg.zza(e));
    L15:
        e = move-exception;
        return zzc.zze(zzcj.zzh, zzie.zze, zzcg.zza(e));
    }

    private final Handler zzan() {
        if (Looper.myLooper() != null) goto L7;
        return this.zze;
    L7:
        return new Handler(Looper.myLooper());
    }

    private final zzbo zzao(BillingResult r2, zzie r3, String r4, Exception r5) {
        zzc.zzo("BillingClient", r4, r5);
        zzbf(r3, 7, r2, zzcg.zza(r5));
        return new zzbo(r2.getResponseCode(), r2.getDebugMessage(), new ArrayList(), new ArrayList());
    }

    private final BillingResult zzap(int r4) {
        zzc.zzm("BillingClient", "Service connection is valid. No need to re-initialize.");
        zzhz r0 = zzib.zzc();
        r0.zzo(6);
        zzjt r1 = zzjv.zzc();
        boolean r2 = true;
        r1.zzn(true);
        if (r4 > 0) goto L6;
        r2 = false;
    L6:
        r1.zza(r2);
        r1.zzl(r4);
        r0.zzn(r1);
        zzaQ((zzib) r0.zze());
        return zzcj.zzi;
    }

    private final BillingResult zzaq() {
        int r1 = 0;
        int[] r0 = {0, 3};
        Object r2 = this.zza;
        monitor-enter(r2);
    L5:
        if (r1 >= 2) goto L14;
        int r3 = r0[r1];     // Catch: Throwable -> L11
        if (this.zzb == r3) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        monitor-exit(r2);     // Catch: Throwable -> L11
        return zzcj.zzj;
    L11:
        th = move-exception;
        throw th;
    L14:
        monitor-exit(r2);     // Catch: Throwable -> L11
        return zzcj.zzh;
    }

    private final zzcz zzar(int r2) {
        if (this.zzE == true) goto L5;
    L9:
        zzc.zzm("BillingClient", "Already connected or not opted into auto reconnection.");
        return com.google.android.gms.internal.play_billing.zzcu.zza(zzcj.zzi);
    L5:
        if (zzaY() == true) goto L9;
        return com.google.android.gms.internal.play_billing.zzu.zza(new zzu(this, r2));
    }

    private final String zzav(QueryProductDetailsParams r2) {
        if (TextUtils.isEmpty(null) == true) goto L6;
        return null;
    L6:
        return this.zzg.getPackageName();
    }

    @SuppressLint({"PrivateApi"})
    private static String zzaw() {
        return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
    L5:
        return null;
    }

    public static /* bridge */ /* synthetic */ int zzb(BillingClientImpl r0) {
        return r0.zzm;
    }

    private final zzcw zzba(int r2, BillingResult r3, zzie r4, String r5, Exception r6) {
        zzbf(r4, 9, r3, zzcg.zza(r6));
        zzc.zzo("BillingClient", r5, r6);
        return new zzcw(r3, null);
    }

    private final zzcw zzbb(String r17, boolean r18, int r19) {
        BillingClientImpl r1 = this;
        zzc.zzm("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(r17)));
        ArrayList r0 = new ArrayList();
        boolean r2 = r1.zzp;
        boolean r3 = r1.zzw;
        boolean r4 = r1.zzD.isEnabledForOneTimeProducts();
        boolean r5 = r1.zzD.isEnabledForPrepaidPlans();
        long r6 = r1.zzI.longValue();
        Bundle r13 = new Bundle();
        zzc.zzc(r13, r1.zzc, r1.zzd, r6);
        if (r2 == false) goto L7;
        if (r4 == false) goto L7;
        r13.putBoolean("enablePendingPurchases", true);
    L7:
        if (r3 == false) goto L10;
        if (r5 == false) goto L10;
        r13.putBoolean("enablePendingPurchaseForSubscriptions", true);
    L10:
        String r12 = null;
    L94:
        Object r22 = r1.zza;     // Catch: Exception -> L18 DeadObjectException -> L20
        monitor-enter(r22);     // Catch: Exception -> L18 DeadObjectException -> L20
        com.google.android.gms.internal.play_billing.zzam r8 = r1.zzi;     // Catch: Throwable -> L83
        monitor-exit(r22);     // Catch: Throwable -> L83
        if (r8 == null) goto L17;
        if (r1.zzp == true) goto L26;
        Bundle r23 = r8.zzh(3, r1.zzg.getPackageName(), r17, r12);     // Catch: Exception -> L18 DeadObjectException -> L20
    L37:
        BillingResult r42 = zzcj.zzh;
        if (r23 != null) goto L41;
        zzc.zzn("BillingClient", String.format("%s got null owned items list", new Object[]{"getPurchase()"}));
        zzie r52 = zzie.zzab;
    L40:
        BillingResult r9 = r42;
    L61:
        if (r9 != zzcj.zzi) goto L63;
        ArrayList<String> r14 = r23.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList<String> r43 = r23.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> r7 = r23.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        int r82 = 0;
        boolean r92 = false;
    L66:
        if (r82 >= r43.size()) goto L76;
        String r10 = r43.get(r82);
        String r11 = r7.get(r82);
        zzc.zzm("BillingClient", "Sku is owned: ".concat(String.valueOf(r14.get(r82))));
        Purchase r122 = new Purchase(r10, r11);     // Catch: JSONException -> L73
        if (TextUtils.isEmpty(r122.getPurchaseToken()) == false) goto L72;
        zzc.zzn("BillingClient", "BUG: empty/null token!");
        r92 = true;
    L72:
        r0.add(r122);
        r82 = r82 + 1;
    L73:
        e = move-exception;
        return zzba(9, zzcj.zzh, zzie.zzY, "Got an exception trying to decode the purchase!", e);
    L76:
        r1 = this;
        if (r92 == false) goto L79;
        r1.zzbd(zzie.zzz, 9, r42);
    L79:
        r12 = r23.getString("INAPP_CONTINUATION_TOKEN");
        zzc.zzm("BillingClient", "Continuation token: ".concat(String.valueOf(r12)));
        if (TextUtils.isEmpty(r12) == false) goto L94;
        return new zzcw(zzcj.zzi, r0);
    L63:
        return r1.zzba(9, r9, r52, "Purchase bundle invalid", null);
    L41:
        int r83 = zzc.zzb(r23, "BillingClient");
        String r93 = zzc.zzj(r23, "BillingClient");
        BillingResult.Builder r102 = BillingResult.newBuilder();
        r102.setResponseCode(r83);
        r102.setDebugMessage(r93);
        r9 = r102.build();
        if (r83 == 0) goto L45;
        zzc.zzn("BillingClient", String.format("%s failed. Response code: %s", new Object[]{"getPurchase()", Integer.valueOf(r83)}));
        r52 = zzie.zzw;
        goto L61
    L45:
        if (r23.containsKey("INAPP_PURCHASE_ITEM_LIST") == true) goto L47;
    L59:
        zzc.zzn("BillingClient", String.format("Bundle returned from %s doesn't contain required fields.", new Object[]{"getPurchase()"}));
        r52 = zzie.zzac;
        goto L40
    L47:
        if (r23.containsKey("INAPP_PURCHASE_DATA_LIST") == false) goto L59;
        if (r23.containsKey("INAPP_DATA_SIGNATURE_LIST") == false) goto L59;
        ArrayList<String> r84 = r23.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList<String> r94 = r23.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> r103 = r23.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        if (r84 != null) goto L54;
        zzc.zzn("BillingClient", String.format("Bundle returned from %s contains null SKUs list.", new Object[]{"getPurchase()"}));
        r52 = zzie.zzad;
        goto L40
    L54:
        if (r94 != null) goto L56;
        zzc.zzn("BillingClient", String.format("Bundle returned from %s contains null purchases list.", new Object[]{"getPurchase()"}));
        r52 = zzie.zzae;
        goto L40
    L56:
        if (r103 != null) goto L58;
        zzc.zzn("BillingClient", String.format("Bundle returned from %s contains null signatures list.", new Object[]{"getPurchase()"}));
        r52 = zzie.zzaf;
        goto L40
    L58:
        r9 = zzcj.zzi;
        r52 = zzie.zza;
        goto L61
    L26:
        if (r1.zzC == false) goto L30;
        int r24 = 26;
    L28:
        int r95 = r24;
    L36:
        r23 = r8.zzi(r95, r1.zzg.getPackageName(), r17, r12, r13);     // Catch: Exception -> L18 DeadObjectException -> L20
        goto L37
    L30:
        if (r1.zzB == false) goto L33;
        r24 = 24;
        goto L28
    L33:
        if (r1.zzw == false) goto L35;
        r24 = 19;
        goto L28
    L35:
        r95 = 9;
        goto L36
    L17:
        return r1.zzba(9, zzcj.zzj, zzie.zzbc, "Service has been reset to null", null);
    L83:
        th = move-exception;
        throw th;     // Catch: Exception -> L18 DeadObjectException -> L20
    L20:
        e = move-exception;
        return zzba(9, zzcj.zzj, zzie.zzZ, "Got exception trying to get purchases try to reconnect", e);
    L18:
        e = move-exception;
        return r1.zzba(9, zzcj.zzh, zzie.zzZ, "Got exception trying to get purchases try to reconnect", e);
    }

    private void zzbd(zzie r3, int r4, BillingResult r5) {
        int r0 = zzcg.$r8$clinit;     // Catch: Throwable -> L4
        zzaO(zzcg.zzb(r3, r4, r5, null, zzil.zza));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzbe(zzie r5, int r6, BillingResult r7, long r8) {
        int r1 = zzcg.$r8$clinit;     // Catch: Throwable -> L9
        zzhx r52 = zzcg.zzb(r5, 2, r7, null, zzil.zza);     // Catch: Throwable -> L9
        this.zzh.zzc(r52, this.zzm, r8);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzbf(zzie r2, int r3, BillingResult r4, String r5) {
        int r0 = zzcg.$r8$clinit;     // Catch: Throwable -> L4
        zzaO(zzcg.zzb(r2, r3, r4, r5, zzil.zza));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzbg(zzie r3, int r4, BillingResult r5, long r6, boolean r8) {
        int r42 = zzcg.$r8$clinit;     // Catch: Throwable -> L4
        zzaP(zzcg.zzb(r3, 2, r5, null, zzil.zza), r6, r8);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzbh(zzie r2, int r3, BillingResult r4, String r5, long r6, boolean r8) {
        int r32 = zzcg.$r8$clinit;     // Catch: Throwable -> L4
        zzaP(zzcg.zzb(r2, 2, r4, r5, zzil.zza), r6, r8);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    public static /* bridge */ /* synthetic */ Context zzc(BillingClientImpl r0) {
        return r0.zzg;
    }

    public static /* synthetic */ Bundle zzd(BillingClientImpl r0, String r1, String r2) {
        return r0.zzam(r1, r2);
    }

    public static /* synthetic */ Bundle zze(BillingClientImpl r0, int r1, String r2, String r3, BillingFlowParams r4, Bundle r5) {
        return r0.zzal(r1, r2, r3, r4, r5);
    }

    public static /* bridge */ /* synthetic */ Handler zzf(BillingClientImpl r0) {
        return r0.zzan();
    }

    public static /* bridge */ /* synthetic */ BillingClientStateListener zzh(BillingClientImpl r0) {
        return r0.zzG;
    }

    public static /* bridge */ /* synthetic */ zzch zzi(BillingClientImpl r0) {
        return r0.zzh;
    }

    public static /* bridge */ /* synthetic */ BillingResult zzk(BillingClientImpl r0) {
        return r0.zzaq();
    }

    public static /* bridge */ /* synthetic */ BillingResult zzl(Exception r0) {
        if ((r0 instanceof DeadObjectException) == false) goto L7;
        return zzcj.zzj;
    L7:
        return zzcj.zzh;
    }

    public static /* bridge */ /* synthetic */ com.google.android.gms.internal.play_billing.zzam zzn(BillingClientImpl r0) {
        return r0.zzi;
    }

    public static /* bridge */ /* synthetic */ zzbl zzo(BillingClientImpl r0) {
        return r0.zzJ;
    }

    public static /* bridge */ /* synthetic */ Long zzp(BillingClientImpl r0) {
        return r0.zzI;
    }

    public static /* synthetic */ Object zzq(BillingClientImpl r1, int r2, zzp r3) {
        r1.zzaU(new zzbc(r1, r3), r2);
        return "reconnectIfNeeded";
    }

    public static /* synthetic */ Object zzr(BillingClientImpl r3, ConsumeResponseListener r4, ConsumeParams r5) {
        if (r3.zzaX(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) == true) goto L5;
        zzie r0 = zzie.zzb;
        BillingResult r1 = zzcj.zzj;
        r3.zzbd(r0, 4, r1);
        r4.onConsumeResponse(r1, r5.getPurchaseToken());
        return null;
    L5:
        r3.zzaD(r5, r4);
        return null;
    }

    public static /* synthetic */ Object zzs(BillingClientImpl r2, ProductDetailsResponseListener r3, QueryProductDetailsParams r4) {
        if (r2.zzaX(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) == true) goto L6;
        zzie r42 = zzie.zzb;
        BillingResult r0 = zzcj.zzj;
        r2.zzbd(r42, 7, r0);
        r3.onProductDetailsResponse(r0, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing.zzbt.zzk(), com.google.android.gms.internal.play_billing.zzbt.zzk()));
        return null;
    L6:
        if (r2.zzu == true) goto L8;
        zzc.zzn("BillingClient", "Querying product details is not supported.");
        zzie r43 = zzie.zzt;
        BillingResult r02 = zzcj.zzr;
        r2.zzbd(r43, 7, r02);
        r3.onProductDetailsResponse(r02, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing.zzbt.zzk(), com.google.android.gms.internal.play_billing.zzbt.zzk()));
        return null;
    L8:
        zzbo r22 = r2.zzg(r4);
        r3.onProductDetailsResponse(zzcj.zza(r22.zza(), r22.zzb()), new QueryProductDetailsResult(r22.zzc(), r22.zzd()));
        return null;
    }

    public static /* bridge */ /* synthetic */ Object zzw(BillingClientImpl r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ String zzx(BillingClientImpl r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ String zzy(BillingClientImpl r0) {
        return r0.zzd;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void consumeAsync(ConsumeParams r7, ConsumeResponseListener r8) {
        if (zzG(new zzak(this, r8, r7), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, new zzam(this, r8, r7), zzan(), zzF()) != null) goto L6;
        BillingResult r0 = zzaq();
        zzbd(zzie.zzy, 4, r0);
        r8.onConsumeResponse(r0, r7.getPurchaseToken());
        return;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final boolean isReady() {
        if (this.zzE == false) goto L7;
        return true;
    L7:
        return zzaY();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.billingclient.api.BillingClient
    public BillingResult launchBillingFlow(Activity r24, BillingFlowParams r25) {
        long r5 = new Random().nextLong();
        if (this.zzf != null) goto L5;
    L261:
        zzie r2 = zzie.zzl;
        BillingResult r4 = zzcj.zzE;
        zzbe(r2, 2, r4, r5);
        return r4;
    L5:
        if (this.zzf.zzd() == null) goto L261;
        if (zzaW(3000) == true) goto L10;
        zzie r22 = zzie.zzb;
        BillingResult r42 = zzcj.zzj;
        zzbe(r22, 2, r42, r5);
        zzm(r42);
        return r42;
    L10:
        Object r23 = this.zza;
        monitor-enter(r23);
    L15:
        th = move-exception;
        throw th;
    L13:
        if (this.zzj == null) goto L17;
        boolean r8 = this.zzj.zzd();     // Catch: Throwable -> L15
    L18:
        monitor-exit(r23);     // Catch: Throwable -> L15
        ArrayList r0 = r25.zzj();
        List r26 = r25.zzk();
        String r9 = null;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(com.google.android.gms.internal.play_billing.zzby.zza(r0, null));
        BillingFlowParams.ProductDetailsParams r43 = (BillingFlowParams.ProductDetailsParams) com.google.android.gms.internal.play_billing.zzby.zza(r26, null);
        String r10 = r43.zza().getProductId();
        String r11 = r43.zza().getProductType();
        if (r11.equals("subs") == false) goto L27;
        if (this.zzk == true) goto L27;
        zzc.zzn("BillingClient", "Current client doesn't support subscriptions.");
        zzie r27 = zzie.zzi;
        BillingResult r44 = zzcj.zzl;
        zzbg(r27, 2, r44, r5, r8);
        zzm(r44);
        return r44;
    L27:
        if (r25.zzt() == false) goto L34;
        if (this.zzn == true) goto L34;
        zzc.zzn("BillingClient", "Current client doesn't support extra params for buy intent.");
        zzie r28 = zzie.zzr;
        BillingResult r45 = zzcj.zzf;
        zzbg(r28, 2, r45, r5, r8);
        zzm(r45);
        return r45;
    L34:
        if (r0.size() <= 1) goto L41;
        if (this.zzt == true) goto L41;
        zzc.zzn("BillingClient", "Current client doesn't support multi-item purchases.");
        zzie r29 = zzie.zzs;
        BillingResult r46 = zzcj.zzp;
        zzbg(r29, 2, r46, r5, r8);
        zzm(r46);
        return r46;
    L41:
        if (r26.isEmpty() == true) goto L47;
        if (this.zzu == true) goto L47;
        zzc.zzn("BillingClient", "Current client doesn't support purchases with ProductDetails.");
        zzie r210 = zzie.zzt;
        BillingResult r47 = zzcj.zzr;
        zzbg(r210, 2, r47, r5, r8);
        zzm(r47);
        return r47;
    L47:
        BillingResult r48 = r25.zzd();
        if (r48 == zzcj.zzi) goto L52;
        zzbg(zzie.zzbd, 2, r48, r5, r8);
        zzm(r48);
        return r48;
    L52:
        if (this.zzn == false) goto L189;
        boolean r49 = this.zzp;
        boolean r14 = this.zzw;
        boolean r15 = this.zzD.isEnabledForOneTimeProducts();
        boolean r3 = this.zzD.isEnabledForPrepaidPlans();
        boolean r92 = this.zzF;
        String r13 = this.zzc;
        String r32 = this.zzd;
        boolean r20 = r8;
        long r82 = this.zzI.longValue();
        this.zzg.getPackageName();
        int r410 = zzc.zza;
        Bundle r411 = new Bundle();
        zzc.zzc(r411, r13, r32, r82);
        r411.putLong("billingClientTransactionId", r5);
        if (r25.zzb() == 0) goto L57;
        r411.putInt("prorationMode", r25.zzb());
    L57:
        if (TextUtils.isEmpty(r25.zze()) == true) goto L60;
        r411.putString("accountId", r25.zze());
    L60:
        if (TextUtils.isEmpty(r25.zzf()) == true) goto L63;
        r411.putString("obfuscatedProfileId", r25.zzf());
    L63:
        if (r25.zzs() == false) goto L66;
        r411.putBoolean("isOfferPersonalizedByDeveloper", true);
    L66:
        if (TextUtils.isEmpty(null) == true) goto L69;
        r411.putStringArrayList("skusToReplace", new ArrayList(Arrays.asList(new String[]{null})));
    L69:
        if (TextUtils.isEmpty(r25.zzh()) == true) goto L71;
        r411.putString("oldSkuPurchaseToken", r25.zzh());
    L71:
        r25.zzg();
        if (TextUtils.isEmpty(null) == true) goto L74;
        r25.zzg();
        String r83 = null;
        r411.putString("oldSkuPurchaseId", null);
    L76:
        if (TextUtils.isEmpty(r25.zzi()) == true) goto L79;
        r411.putString("originalExternalTransactionId", r25.zzi());
    L79:
        if (TextUtils.isEmpty(r83) == true) goto L81;
        r411.putString("paymentsPurchaseParams", r83);
    L81:
        if (r49 == false) goto L84;
        if (r15 == false) goto L84;
        boolean r84 = true;
        r411.putBoolean("enablePendingPurchases", true);
    L85:
        if (r14 == false) goto L88;
        if (r3 == false) goto L88;
        r411.putBoolean("enablePendingPurchaseForSubscriptions", r84);
    L88:
        if (r92 == false) goto L90;
        r411.putBoolean("enableAlternativeBilling", r84);
    L90:
        r25.zzc();
        r25.zza();
        ArrayList r33 = new ArrayList();
        Iterator r85 = r25.zzk().iterator();
    L92:
        if (r85.hasNext() == false) goto L95;
        BillingFlowParams.ProductDetailsParams r93 = (BillingFlowParams.ProductDetailsParams) r85.next();
        goto L92
    L95:
        if (r33.isEmpty() == true) goto L98;
        zzdj r86 = zzdk.zza();
        r86.zza(r33);
        r411.putByteArray("subscriptionProductReplacementParamsList", ((zzdk) r86.zze()).zzM());
    L98:
        if (r0.isEmpty() == true) goto L115;
        ArrayList<String> r34 = new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        Iterator r87 = r0.iterator();
        if (r87.hasNext() == false) goto L102;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r87.next());
        throw null;
    L102:
        if (r34.isEmpty() == true) goto L105;
        r411.putStringArrayList("skuDetailsTokens", r34);
    L105:
        if (r0.size() <= 1) goto L109;
        ArrayList<String> r35 = new ArrayList(r0.size() - 1);
        ArrayList<String> r88 = new ArrayList(r0.size() - 1);
        if (1 < r0.size()) goto L110;
        r411.putStringArrayList("additionalSkus", r35);
        r411.putStringArrayList("additionalSkuTypes", r88);
        goto L109
    L110:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.get(1));
        throw null;
    L109:
        long r18 = r5;
    L151:
        if (r411.containsKey("SKU_OFFER_ID_TOKEN_LIST") == true) goto L153;
    L154:
        r8 = r20;
        if (r43 != null) goto L159;
    L161:
        r9 = null;
        boolean r132 = false;
    L163:
        if (TextUtils.isEmpty(r9) == true) goto L165;
        r411.putString("accountName", r9);
    L165:
        Intent r02 = r24.getIntent();
        if (r02 != null) goto L169;
        zzc.zzn("BillingClient", "Activity's intent is null.");
    L175:
        if (this.zzu == false) goto L181;
        if (r26.isEmpty() == true) goto L181;
        int r03 = 17;
    L188:
        BillingFlowParams r52 = r25;
        Future r04 = zzG(new zzw(this, r03, r10, r11, r52, r411), 5000, null, this.zze, zzF());
        long r53 = r52;
    L278:
        if (r04 == null) goto L272;
        long r54 = r18;
        Bundle r211 = (Bundle) r04.get(5000, TimeUnit.MILLISECONDS);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        int r05 = zzc.zzb(r211, "BillingClient");     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        String r36 = zzc.zzj(r211, "BillingClient");     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        if (r05 == 0) goto L252;
        zzc.zzn("BillingClient", "Unable to buy item, Error response code: " + r05);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        BillingResult r412 = zzcj.zza(r05, r36);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        if (r211 != null) goto L221;
        zzie r06 = zzie.zza;     // Catch: Throwable -> L219
    L267:
    L231:
        if (r06 != zzie.zza) goto L233;
        r06 = zzie.zzw;     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
    L233:
        zzie r37 = r06;
        if (r211 == null) goto L276;
        r9 = r211.getString("ADDITIONAL_LOG_DETAILS");     // Catch: Throwable -> L238
    L238:
        th = move-exception;
        zzc.zzn("BillingClient", "Failed to get additional log details from bundle: ".concat(String.valueOf(th.getMessage())));     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
    L276:
        zzbh(r37, 2, r412, r9, r54, r8);     // Catch: Exception -> L246 CancellationException -> L248 TimeoutException -> L250
    L244:
        zzm(r412);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        return r412;
    L248:
        e = e;
    L249:
        long r55 = r54;
    L257:
        zzc.zzo("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
        zzie r212 = zzie.zzd;
        BillingResult r413 = zzcj.zzk;
        zzbh(r212, 2, r413, zzcg.zza(e), r55, r8);
        zzm(r413);
        return r413;
    L250:
        e = e;
    L246:
        e = e;
        long r56 = r54;
    L255:
        zzc.zzo("BillingClient", "Exception while launching billing flow. Try to reconnect", e);
        zzie r213 = zzie.zze;
        BillingResult r414 = zzcj.zzj;
        zzbh(r213, 2, r414, zzcg.zza(e), r56, r8);
        zzm(r414);
        return r414;
    L221:
        Object r07 = r211.get("LOG_REASON");     // Catch: Throwable -> L219
        if (r07 != null) goto L225;
        r06 = zzie.zza;     // Catch: Throwable -> L219
        goto L267
    L225:
        if ((r07 instanceof Integer) == false) goto L227;
        r06 = zzie.zzb(((Integer) r07).intValue());     // Catch: Throwable -> L219
        goto L267
    L227:
        zzc.zzn("BillingClient", "Unexpected type for bundle log reason: " + r07.getClass().getName());     // Catch: Throwable -> L219
        r06 = zzie.zza;     // Catch: Throwable -> L219
    L219:
        th = move-exception;
        zzc.zzn("BillingClient", "Failed to get log reason from bundle: ".concat(String.valueOf(th.getMessage())));     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        r06 = zzie.zza;     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        goto L231
    L252:
        Intent r08 = new Intent(r24, ProxyBillingActivity.class);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        r08.putExtra("BUY_INTENT", (PendingIntent) r211.getParcelable("BUY_INTENT"));     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        r08.putExtra("billingClientTransactionId", r54);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        r08.putExtra("wasServiceAutoReconnected", r8);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        r24.startActivity(r08);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        return zzcj.zzi;
    L272:
        zzie r214 = zzie.zzy;     // Catch: Exception -> L208 CancellationException -> L210 TimeoutException -> L212
        BillingResult r415 = zzcj.zzc;     // Catch: Exception -> L208 CancellationException -> L210 TimeoutException -> L212
        boolean r7 = r8;
        long r57 = r18;
        zzbg(r214, 2, r415, r57, r7);     // Catch: Exception -> L202 CancellationException -> L204 TimeoutException -> L206
        zzm(r415);     // Catch: Exception -> L196 CancellationException -> L198 TimeoutException -> L200
        return r415;
    L204:
        e = e;
    L205:
        r8 = r7;
        r55 = r57;
    L206:
        e = e;
    L202:
        e = e;
        r8 = r7;
        r56 = r57;
        goto L255
    L210:
        e = e;
    L211:
        r55 = r18;
    L212:
        e = e;
    L208:
        e = e;
        r56 = r18;
    L198:
        e = e;
        r55 = r53;
    L200:
        e = e;
        r55 = r53;
    L196:
        e = e;
        r56 = r53;
    L181:
        if (this.zzs == false) goto L185;
        if (r132 == false) goto L185;
        r03 = 15;
    L185:
        if (this.zzp == false) goto L187;
        r03 = 9;
        goto L188
    L187:
        r03 = 6;
        goto L188
    L169:
        if (TextUtils.isEmpty(r02.getStringExtra("PROXY_PACKAGE")) == true) goto L175;
        String r09 = r02.getStringExtra("PROXY_PACKAGE");
        r411.putString("proxyPackage", r09);
        r411.putString("proxyPackageVersion", this.zzg.getPackageManager().getPackageInfo(r09, 0).versionName);     // Catch: PackageManager.NameNotFoundException -> L173
    L173:
        r411.putString("proxyPackageVersion", "package not found");
        goto L175
    L159:
        if (TextUtils.isEmpty(r43.zza().zza()) == true) goto L161;
        r411.putString("skuPackageName", r43.zza().zza());
        r9 = null;
        r132 = true;
        goto L163
    L153:
        if (this.zzr == true) goto L154;
        zzie r215 = zzie.zzu;
        BillingResult r416 = zzcj.zzq;
        zzbg(r215, 2, r416, r18, r20);
        zzm(r416);
        return r416;
    L115:
        ArrayList<String> r010 = new ArrayList(r26.size() - 1);
        ArrayList<String> r38 = new ArrayList(r26.size() - 1);
        ArrayList<String> r89 = new ArrayList();
        ArrayList<String> r133 = new ArrayList();
        ArrayList<String> r142 = new ArrayList();
        ArrayList<Integer> r152 = new ArrayList();
        r18 = r5;
        int r94 = 0;
    L117:
        if (r94 >= r26.size()) goto L138;
        BillingFlowParams.ProductDetailsParams r58 = (BillingFlowParams.ProductDetailsParams) r26.get(r94);
        ProductDetails r6 = r58.zza();
        if (r6.zzb().isEmpty() == true) goto L121;
        BillingFlowParams.ProductDetailsParams r21 = r58;
        r89.add(r6.zzb());
    L122:
        r133.add(r21.zzb());
        String r59 = r6.zzc();
        if (r6.zzd() == null) goto L133;
        if (r6.zzd().isEmpty() == true) goto L133;
        Iterator r62 = r6.zzd().iterator();
    L128:
        if (r62.hasNext() == false) goto L133;
        ProductDetails.OneTimePurchaseOfferDetails r216 = (ProductDetails.OneTimePurchaseOfferDetails) r62.next();
        if (TextUtils.isEmpty(r216.zzb()) == true) goto L128;
        r59 = r216.zzb();
    L133:
        if (TextUtils.isEmpty(r59) == true) goto L135;
        r142.add(r59);
    L135:
        if (r94 <= 0) goto L137;
        r010.add(((BillingFlowParams.ProductDetailsParams) r26.get(r94)).zza().getProductId());
        r38.add(((BillingFlowParams.ProductDetailsParams) r26.get(r94)).zza().getProductType());
    L137:
        r94 = r94 + 1;
        goto L117
    L121:
        r21 = r58;
        goto L122
    L138:
        r411.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", r133);
        if (r152.isEmpty() == true) goto L142;
        r411.putIntegerArrayList("autoPayBalanceThresholdList", r152);
    L142:
        if (r89.isEmpty() == true) goto L145;
        r411.putStringArrayList("skuDetailsTokens", r89);
    L145:
        if (r142.isEmpty() == true) goto L148;
        r411.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", r142);
    L148:
        if (r010.isEmpty() == true) goto L151;
        r411.putStringArrayList("additionalSkus", r010);
        r411.putStringArrayList("additionalSkuTypes", r38);
    L84:
        r84 = true;
        goto L85
    L74:
        r83 = null;
        goto L76
    L189:
        r18 = r5;
        r04 = zzG(new zzx(this, r10, r11), 5000, null, this.zze, zzF());
        r53 = r5;
        goto L278
    L17:
        r8 = false;
        goto L18
    }

    @Override // com.android.billingclient.api.BillingClient
    public void queryProductDetailsAsync(QueryProductDetailsParams r7, ProductDetailsResponseListener r8) {
        if (zzG(new zzal(this, r8, r7), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, new zzap(this, r8), zzan(), zzF()) != null) goto L6;
        BillingResult r72 = zzaq();
        zzbd(zzie.zzy, 7, r72);
        r8.onProductDetailsResponse(r72, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing.zzbt.zzk(), com.google.android.gms.internal.play_billing.zzbt.zzk()));
        return;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchasesAsync(QueryPurchasesParams r7, PurchasesResponseListener r8) {
        if (zzG(new zzaw(this, r8, r7.zza(), false), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, new zzat(this, r8), zzan(), zzF()) != null) goto L6;
        BillingResult r72 = zzaq();
        zzbd(zzie.zzy, 9, r72);
        r8.onQueryPurchasesResponse(r72, com.google.android.gms.internal.play_billing.zzbt.zzk());
        return;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void startConnection(BillingClientStateListener r2) {
        zzaU(r2, 0);
    }

    public final synchronized ExecutorService zzF() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.zzH != null) goto L8;
        this.zzH = Executors.newFixedThreadPool(zzc.zza, new zzav(this));     // Catch: Throwable -> L6
    L8:
        ExecutorService r0 = this.zzH;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }

    public final void zzag(Runnable r3) {
        if (Looper.myLooper() != Looper.getMainLooper()) goto L6;
        r3.run();
        return;
    L6:
        this.zze.post(r3);
    }

    public final zzbo zzg(QueryProductDetailsParams r23) {
        ArrayList r0 = new ArrayList();
        ArrayList r2 = new ArrayList();
        String r6 = r23.zzb();
        com.google.android.gms.internal.play_billing.zzbt r9 = r23.zza();
        int r10 = r9.size();
        int r3 = 0;
    L3:
        if (r3 >= r10) goto L90;
        int r12 = r3 + 20;
        if (r12 <= r10) goto L7;
        int r4 = r10;
    L8:
        ArrayList r15 = new ArrayList(r9.subList(r3, r4));
        ArrayList<String> r32 = new ArrayList();
        int r42 = r15.size();
        int r5 = 0;
    L9:
        if (r5 >= r42) goto L11;
        r32.add(((QueryProductDetailsParams.Product) r15.get(r5)).zza());
        r5 = r5 + 1;
        goto L9
    L11:
        Bundle r7 = new Bundle();
        r7.putStringArrayList("ITEM_ID_LIST", r32);
        String r13 = this.zzc;
        r7.putString("playBillingLibraryVersion", r13);
        Object r33 = this.zza;     // Catch: Exception -> L21 DeadObjectException -> L23
        monitor-enter(r33);     // Catch: Exception -> L21 DeadObjectException -> L23
        com.google.android.gms.internal.play_billing.zzam r34 = this.zzi;     // Catch: Throwable -> L82
        monitor-exit(r33);     // Catch: Throwable -> L82
    L18:
        if (r34 == null) goto L20;
        if (this.zzw == true) goto L28;
    L30:
        boolean r16 = false;
    L31:
        zzav(r23);     // Catch: Exception -> L21 DeadObjectException -> L23
        zzav(r23);     // Catch: Exception -> L21 DeadObjectException -> L23
        zzav(r23);     // Catch: Exception -> L21 DeadObjectException -> L23
        zzav(r23);     // Catch: Exception -> L21 DeadObjectException -> L23
        zza r18 = zza.zza(r16, true, false, true, false, true);     // Catch: Exception -> L21 DeadObjectException -> L23
        if (true == this.zzx) goto L34;
        int r52 = 17;
    L35:
        Bundle r35 = r34.zzj(r52, this.zzg.getPackageName(), r6, r7, zzc.zzf(r13, this.zzd, r15, null, null, r18, this.zzI.longValue()));     // Catch: Exception -> L21 DeadObjectException -> L23
        if (r35 == null) goto L38;
        if (r35.containsKey("DETAILS_LIST") == false) goto L41;
        ArrayList<String> r43 = r35.getStringArrayList("DETAILS_LIST");
        if (r43 == null) goto L50;
        ArrayList r72 = new ArrayList();
        int r8 = r43.size();
        int r132 = 0;
    L52:
        if (r132 >= r8) goto L59;
        ProductDetails r11 = new ProductDetails(r43.get(r132));     // Catch: JSONException -> L56
        zzc.zzm("BillingClient", "Got product details: ".concat(r11.toString()));
        r72.add(r11);
        r132 = r132 + 1;
    L56:
        e = move-exception;
        return zzao(zzcj.zza(6, "Error trying to decode SkuDetails."), zzie.zzU, "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e);
    L59:
        ArrayList<String> r36 = r35.getStringArrayList("UNFETCHED_PRODUCT_LIST");
        new ArrayList();
        ArrayList r44 = new ArrayList();     // Catch: JSONException -> L65
        if (r36 == null) goto L67;
        int r53 = r36.size();     // Catch: JSONException -> L65
        int r82 = 0;
    L63:
        if (r82 >= r53) goto L79;
        String r112 = r36.get(r82);     // Catch: JSONException -> L65
        r82 = r82 + 1;     // Catch: JSONException -> L65
        UnfetchedProduct r133 = new UnfetchedProduct(r112);     // Catch: JSONException -> L65
        zzc.zzm("BillingClient", "Got unfetchedProduct: ".concat(r133.toString()));     // Catch: JSONException -> L65
        r44.add(r133);     // Catch: JSONException -> L65
    L79:
        r0.addAll(r72);
        r2.addAll(r44);
        r3 = r12;
        goto L3
    L67:
        int r37 = r15.size();     // Catch: JSONException -> L65
        int r54 = 0;
    L68:
        if (r54 >= r37) goto L79;
        Object r83 = r15.get(r54);     // Catch: JSONException -> L65
        int r55 = r54 + 1;     // Catch: JSONException -> L65
        QueryProductDetailsParams.Product r84 = (QueryProductDetailsParams.Product) r83;     // Catch: JSONException -> L65
        int r113 = r72.size();     // Catch: JSONException -> L65
        int r134 = 0;
    L70:
        if (r134 >= r113) goto L77;
        Object r14 = r72.get(r134);     // Catch: JSONException -> L65
        r134 = r134 + 1;     // Catch: JSONException -> L65
        ProductDetails r142 = (ProductDetails) r14;     // Catch: JSONException -> L65
        int r182 = r37;
        int r19 = r55;
        if (r84.zza().equals(r142.getProductId()) == false) goto L76;
        if (r84.zzb().equals(r142.getProductType()) == false) goto L76;
    L75:
        r37 = r182;
        r54 = r19;
    L76:
        r37 = r182;
        r55 = r19;
        goto L70
    L77:
        r182 = r37;
        r19 = r55;
        r44.add(new UnfetchedProduct(new JSONObject().put("productId", r84.zza()).put("type", r84.zzb()).put("statusCode", 0).toString()));     // Catch: JSONException -> L65
    L65:
        e = move-exception;
        return zzao(zzcj.zza(6, "Error trying to decode SkuDetails."), zzie.zzU, "Got a JSON exception trying to decode UnfetchedProduct. \n Exception: ", e);
    L50:
        return zzao(zzcj.zzB, zzie.zzT, "queryProductDetailsAsync got null response list", null);
    L41:
        int r02 = zzc.zzb(r35, "BillingClient");
        String r22 = zzc.zzj(r35, "BillingClient");
        if (r02 == 0) goto L46;
        return zzao(zzcj.zza(r02, r22), zzie.zzw, "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + r02, null);
    L46:
        return zzao(zzcj.zza(6, r22), zzie.zzS, "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.", null);
    L38:
        return zzao(zzcj.zzB, zzie.zzR, "queryProductDetailsAsync got empty product details response.", null);
    L34:
        r52 = 20;
        goto L35
    L28:
        if (this.zzD.isEnabledForPrepaidPlans() == false) goto L30;
        r16 = true;
        goto L31
    L20:
        return zzao(zzcj.zzj, zzie.zzbc, "Service has been reset to null.", null);
    L82:
        th = move-exception;
        throw th;     // Catch: Exception -> L21 DeadObjectException -> L23
    L23:
        e = move-exception;
        return zzao(zzcj.zzj, zzie.zzQ, "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
    L21:
        e = move-exception;
        return zzao(zzcj.zzh, zzie.zzQ, "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
    L7:
        r4 = r12;
        goto L8
    L90:
        return new zzbo(0, _UrlKt.FRAGMENT_ENCODE_SET, r0, r2);
    }

    public final zzch zzj() {
        return this.zzh;
    }

    public final BillingResult zzm(BillingResult r3) {
        if (Thread.interrupted() == false) goto L5;
        return r3;
    L5:
        this.zze.post(new zzac(this, r3));
        return r3;
    }
}
