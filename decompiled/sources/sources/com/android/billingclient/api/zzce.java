package com.android.billingclient.api;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import androidx.core.util.Consumer;
import com.android.billingclient.api.BillingClient;
import com.google.android.gms.internal.play_billing.zzau;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzcz;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzib;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzil;
import com.google.android.gms.internal.play_billing.zzp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzce extends BillingClientImpl {
    private final Context zza;
    private volatile int zzb;
    private volatile zzau zzc;
    private volatile zzcc zzd;
    private volatile ScheduledExecutorService zze;

    public zzce(String r1, Context r2, zzch r3, ExecutorService r4, BillingClient.Builder r5) {
        super(null, r2, null, null, r5);
        this.zzb = 0;
        this.zza = r2;
    }

    public zzce(String r1, PendingPurchasesParams r2, Context r3, PurchasesUpdatedListener r4, zzb r5, zzch r6, ExecutorService r7, BillingClient.Builder r8) {
        super(null, r2, r3, r4, null, null, null, r8);
        this.zzb = 0;
        this.zza = r3;
    }

    public zzce(String r1, PendingPurchasesParams r2, Context r3, zzco r4, zzch r5, ExecutorService r6, BillingClient.Builder r7) {
        super(null, r2, r3, null, null, null, r7);
        this.zzb = 0;
        this.zza = r3;
    }

    private final synchronized void zzaB() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (zzat() == false) goto L11;
        zzc.zzm("BillingClientTesting", "Billing Override Service connection is valid. No need to re-initialize.");     // Catch: Throwable -> L8
        zzaG(26);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        if (this.zzb != 1) goto L16;
        zzc.zzn("BillingClientTesting", "Client is already in the process of connecting to Billing Override Service.");     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L16:
        if (this.zzb != 3) goto L20;
        zzc.zzn("BillingClientTesting", "Billing Override Service Client was already closed and can't be reused. Please create another instance.");     // Catch: Throwable -> L8
        zzaF(zzie.zzL, 26, zzcj.zza(-1, "Billing Override Service connection is disconnected."));     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L20:
        this.zzb = 1;     // Catch: Throwable -> L8
        zzc.zzm("BillingClientTesting", "Starting Billing Override Service setup.");     // Catch: Throwable -> L8
        this.zzd = new zzcc(this, null);     // Catch: Throwable -> L8
        Intent r0 = new Intent("com.google.android.apps.play.billingtestcompanion.BillingOverrideService.BIND");     // Catch: Throwable -> L8
        r0.setPackage("com.google.android.apps.play.billingtestcompanion");     // Catch: Throwable -> L8
        Context r3 = this.zza;     // Catch: Throwable -> L8
        List<ResolveInfo> r4 = r3.getPackageManager().queryIntentServices(r0, 0);     // Catch: Throwable -> L8
        zzie r6 = zzie.zza;     // Catch: Throwable -> L8
        if (r4 != null) goto L23;
    L36:
        r6 = zzie.zzO;     // Catch: Throwable -> L8
    L37:
        this.zzb = 0;     // Catch: Throwable -> L8
        zzc.zzm("BillingClientTesting", "Billing Override Service unavailable on device.");     // Catch: Throwable -> L8
        zzaF(r6, 26, zzcj.zza(2, "Billing Override Service unavailable on device."));     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L23:
        if (r4.isEmpty() == true) goto L36;
        ServiceInfo r42 = r4.get(0).serviceInfo;     // Catch: Throwable -> L8
        if (r42 == null) goto L37;
        String r62 = r42.packageName;     // Catch: Throwable -> L8
        String r43 = r42.name;     // Catch: Throwable -> L8
        if (Objects.equals(r62, "com.google.android.apps.play.billingtestcompanion") == false) goto L35;
        if (r43 == null) goto L35;
        ComponentName r7 = new ComponentName(r62, r43);     // Catch: Throwable -> L8
        Intent r44 = new Intent(r0);     // Catch: Throwable -> L8
        r44.setComponent(r7);     // Catch: Throwable -> L8
        if (r3.bindService(r44, this.zzd, 1) == false) goto L34;
        zzc.zzm("BillingClientTesting", "Billing Override Service was bonded successfully.");     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L34:
        r6 = zzie.zzM;     // Catch: Throwable -> L8
        zzc.zzn("BillingClientTesting", "Connection to Billing Override Service is blocked.");     // Catch: Throwable -> L8
    L35:
        r6 = zzie.zzM;     // Catch: Throwable -> L8
        zzc.zzn("BillingClientTesting", "The device doesn't have valid Play Billing Lab.");     // Catch: Throwable -> L8
        goto L37
    }

    private static final boolean zzaC(int r0) {
        if (r0 <= 0) goto L5;
        return true;
    L5:
        return false;
    }

    private final BillingResult zzaD(int r2, int r3) {
        BillingResult r32 = zzcj.zza(r3, "Billing override value was set by a license tester.");
        zzaF(zzie.zzaO, r2, r32);
        return r32;
    }

    private final zzcz zzaE(int r3) {
        if (zzat() == true) goto L7;
        zzc.zzn("BillingClientTesting", "Billing Override Service is not ready.");
        zzaF(zzie.zzaP, 28, zzcj.zza(-1, "Billing Override Service connection is disconnected."));
        return com.google.android.gms.internal.play_billing.zzcu.zza(0);
    L7:
        return com.google.android.gms.internal.play_billing.zzu.zza(new zzbv(this, r3));
    }

    private final void zzaF(zzie r3, int r4, BillingResult r5) {
        int r0 = zzcg.$r8$clinit;
        zzhx r32 = zzcg.zzb(r3, r4, r5, null, zzil.zza);
        Objects.requireNonNull(r32, "ApiFailure should not be null");
        zzj().zza(r32);
    }

    private final void zzaG(int r2) {
        int r0 = zzcg.$r8$clinit;
        zzib r22 = zzcg.zzc(r2, zzil.zza);
        Objects.requireNonNull(r22, "ApiSuccess should not be null");
        zzj().zzf(r22);
    }

    private final void zzaH(int r6, Consumer r7, Runnable r8) {
        com.google.android.gms.internal.play_billing.zzcu.zzc(com.google.android.gms.internal.play_billing.zzcu.zzb(zzaE(r6), 28500, TimeUnit.MILLISECONDS, zzaz()), new zzca(this, r6, r7, r8), zzF());
    }

    public static /* synthetic */ BillingResult zzal(zzce r0, Activity r1, BillingFlowParams r2) {
        return super.launchBillingFlow(r1, r2);
    }

    public static /* synthetic */ void zzan(zzce r0, ConsumeParams r1, ConsumeResponseListener r2) {
        super.consumeAsync(r1, r2);
    }

    public static /* synthetic */ void zzao(zzce r0, QueryProductDetailsParams r1, ProductDetailsResponseListener r2) {
        super.queryProductDetailsAsync(r1, r2);
    }

    public static /* synthetic */ void zzap(zzce r0, BillingResult r1) {
        super.zzm(r1);
    }

    public static /* bridge */ /* synthetic */ void zzaq(zzce r0, zzau r1) {
        r0.zzc = r1;
    }

    public static /* bridge */ /* synthetic */ void zzar(zzce r0, int r1) {
        r0.zzb = r1;
    }

    public static /* bridge */ /* synthetic */ boolean zzas(zzce r0, int r1) {
        return zzaC(r1);
    }

    public static /* bridge */ /* synthetic */ BillingResult zzau(zzce r0, int r1, int r2) {
        return r0.zzaD(r1, r2);
    }

    public static /* synthetic */ Object zzav(zzce r3, int r4, zzp r5) {
    L15:
        e = move-exception;
        r3.zzaF(zzie.zzaQ, 28, zzcj.zzF);
        zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", e);
        r5.zzb(0);
        return "billingOverrideService.getBillingOverride";
    L3:
        if (r3.zzc == null) goto L24;
        zzau r0 = r3.zzc;     // Catch: Exception -> L15
        String r1 = r3.zza.getPackageName();     // Catch: Exception -> L15
        if (r4 != 2) goto L7;
        String r42 = "LAUNCH_BILLING_FLOW";
    L22:
        r0.zza(r1, r42, new zzcb(r5));     // Catch: Exception -> L15
        return "billingOverrideService.getBillingOverride";
    L7:
        if (r4 != 3) goto L9;
        r42 = "ACKNOWLEDGE_PURCHASE";
        goto L22
    L9:
        if (r4 != 4) goto L11;
        r42 = "CONSUME_ASYNC";
        goto L22
    L11:
        if (r4 != 5) goto L13;
        r42 = "IS_FEATURE_SUPPORTED";
        goto L22
    L13:
        if (r4 == 6) goto L17;
        r42 = "QUERY_PRODUCT_DETAILS_ASYNC";
        goto L22
    L17:
        r42 = "START_CONNECTION";
        goto L22
    L24:
        throw null;     // Catch: Exception -> L15
    }

    public static /* bridge */ /* synthetic */ void zzaw(zzce r0, zzie r1, int r2, BillingResult r3) {
        r0.zzaF(r1, 28, r3);
    }

    public static /* bridge */ /* synthetic */ void zzax(zzce r0, int r1) {
        r0.zzaG(26);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int zzay(zzcz r7) {
        return ((Integer) r7.get(28500, TimeUnit.MILLISECONDS)).intValue();
    L7:
        e = move-exception;
        this.zzaF(zzie.zzaX, 28, zzcj.zzF);
        zzc.zzo("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", e);
        return 0;
    L5:
        e = move-exception;
        if ((e instanceof InterruptedException) == false) goto L12;
        Thread.currentThread().interrupt();
    L12:
        this.zzaF(zzie.zzaQ, 28, zzcj.zzF);
        zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", e);
        return 0;
    }

    private final synchronized ScheduledExecutorService zzaz() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.zze != null) goto L8;
        this.zze = Executors.newSingleThreadScheduledExecutor();     // Catch: Throwable -> L6
    L8:
        ScheduledExecutorService r0 = this.zze;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void consumeAsync(ConsumeParams r3, ConsumeResponseListener r4) {
        zzaH(4, new zzbt(r4, r3), new zzbu(this, r3, r4));
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final BillingResult launchBillingFlow(Activity r4, BillingFlowParams r5) {
        zzby r0 = new zzby(this);
        zzbz r1 = new zzbz(this, r4, r5);
        int r52 = zzay(zzaE(2));
        if (zzaC(r52) == false) goto L11;
        BillingResult r3 = zzaD(2, r52);
        r0.accept(r3);
        return r3;
    L11:
        return (BillingResult) r1.call();
    L8:
        e = move-exception;
        zzie r02 = zzie.zzaY;
        BillingResult r12 = zzcj.zzh;
        zzaF(r02, 2, r12);
        zzc.zzo("BillingClientTesting", "An internal error occurred.", e);
        return r12;
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void queryProductDetailsAsync(QueryProductDetailsParams r3, ProductDetailsResponseListener r4) {
        zzaH(7, new zzbr(r4), new zzbs(this, r3, r4));
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void startConnection(BillingClientStateListener r1) {
        zzaB();
        super.startConnection(r1);
    }

    public final synchronized boolean zzat() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.zzb == 2) goto L6;
    L14:
        monitor-exit(this);
        return false;
    L6:
        if (this.zzc == null) goto L14;
        if (this.zzd == null) goto L14;
        monitor-exit(this);
        return true;
    }
}
