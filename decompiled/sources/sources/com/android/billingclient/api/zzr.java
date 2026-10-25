package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzeu;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzil;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzr extends BroadcastReceiver {
    final /* synthetic */ zzs zza;
    private boolean zzb;
    private final boolean zzc;

    public zzr(zzs r1, boolean r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzc = r2;
    }

    private final void zzd(Bundle r3, BillingResult r4, int r5, zzil r6, long r7, boolean r9) {
        byte[] r1 = r3.getByteArray("FAILURE_LOGGING_PAYLOAD");     // Catch: Throwable -> L10
        zzs r2 = this.zza;
        if (r1 == null) goto L8;
        zzs.zzb(r2).zzd(zzhx.zzA(r3.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzeu.zza()), r7, r9);     // Catch: Throwable -> L10
        return;
    L8:
        zzs.zzb(r2).zzd(zzcg.zzb(zzie.zzw, r5, r4, null, r6), r7, r9);     // Catch: Throwable -> L10
        return;
    L10:
        zzc.zzn("BillingBroadcastManager", "Failed parsing Api failure.");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r11, Intent r12) {
        String r112 = r12.getAction();
        int r0 = r112.hashCode();
        if (r0 == (-1484087650)) goto L17;
        if (r0 == (-337612916)) goto L14;
        if (r0 == 345207161) goto L10;
    L19:
        zzil r113 = zzil.zza;
    L12:
        zzil r4 = r113;
        zzil r114 = zzil.zzc;
        if (r4.equals(r114) == false) goto L23;
    L24:
        int r02 = 2;
        int r3 = 2;
    L30:
        Bundle r1 = r12.getExtras();
        if (r1 != null) goto L37;
        zzc.zzn("BillingBroadcastManager", "Bundle is null.");
        zzs r10 = this.zza;
        zzch r115 = zzs.zzb(r10);
        zzie r122 = zzie.zzk;
        BillingResult r03 = zzcj.zzh;
        r115.zza(zzcg.zzb(r122, r3, r03, null, r4));
        if (zzs.zzc(r10) == null) goto L70;
        zzs.zzc(r10).onPurchasesUpdated(r03, null);
        return;
    L70:
        return;
    L37:
        if (r3 != r02) goto L50;
        int r04 = zzc.zza;
        BillingResult.Builder r05 = BillingResult.newBuilder();
        r05.setResponseCode(zzc.zzb(r12.getExtras(), "BillingBroadcastManager"));
        Bundle r5 = r12.getExtras();
        if (r5 != null) goto L42;
        zzc.zzn("BillingBroadcastManager", "Unexpected null bundle received!");
    L41:
        int r52 = 0;
    L49:
        r05.setOnPurchasesUpdatedSubResponseCode(r52);
        r05.setDebugMessage(zzc.zzj(r12.getExtras(), "BillingBroadcastManager"));
        BillingResult r123 = r05.build();
    L51:
        long r53 = r1.getLong("billingClientTransactionId", 0);
        boolean r7 = r1.getBoolean("wasServiceAutoReconnected", false);
        if (r4.equals(zzil.zzb) == false) goto L54;
    L55:
        BillingResult r2 = r123;
        List<Purchase> r102 = zzc.zzl(r1);
        if (r2.getResponseCode() != 0) goto L67;
        zzs.zzb(this.zza).zzh(zzcg.zzc(r3, r4), r53, r7);
    L68:
        zzs.zzc(this.zza).onPurchasesUpdated(r2, r102);
        return;
    L67:
        zzd(r1, r2, r3, r4, r53, r7);
        goto L68
    L54:
        if (r4.equals(r114) == true) goto L55;
        if (r4.equals(zzil.zzd) == true) goto L59;
        return;
    L59:
        if (r123.getResponseCode() == 0) goto L62;
        BillingResult r22 = r123;
        zzd(r1, r22, r3, r4, r53, r7);
        zzs.zzc(this.zza).onPurchasesUpdated(r22, com.google.android.gms.internal.play_billing.zzbt.zzk());
        return;
    L62:
        zzs r103 = this.zza;
        zzs.zza(r103);
        zzs.zze(r103);
        zzc.zzn("BillingBroadcastManager", "AlternativeBillingListener and UserChoiceBillingListener is null.");
        zzs r104 = this.zza;
        zzch r116 = zzs.zzb(r104);
        zzie r124 = zzie.zzay;
        BillingResult r06 = zzcj.zzh;
        r116.zzd(zzcg.zzb(r124, r3, r06, null, r4), r53, r7);
        zzs.zzc(r104).onPurchasesUpdated(r06, com.google.android.gms.internal.play_billing.zzbt.zzk());
        return;
    L42:
        Object r54 = r5.get("SUB_RESPONSE_CODE");
        if (r54 != null) goto L46;
        zzc.zzm("BillingBroadcastManager", "getLaunchBillingFlowSubResponseCodeFromBundle() got null response code, assuming OK");
        goto L41
    L46:
        if ((r54 instanceof Integer) == false) goto L48;
        r52 = ((Integer) r54).intValue();
        goto L49
    L48:
        zzc.zzn("BillingBroadcastManager", "Unexpected type for bundle sub response code: ".concat(r54.getClass().getName()));
        goto L41
    L50:
        r123 = zzc.zzh(r12, "BillingBroadcastManager");
        goto L51
    L23:
        if (r4.equals(zzil.zzd) == true) goto L24;
        if (r4.equals(zzil.zzb) == false) goto L29;
        int r07 = 32;
    L28:
        r3 = r07;
        r02 = 2;
        goto L30
    L29:
        r07 = 1;
        goto L28
    L10:
        if (r112.equals("com.android.vending.billing.ALTERNATIVE_BILLING") == false) goto L19;
        r113 = zzil.zzd;
        goto L12
    L14:
        if (r112.equals("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED") == false) goto L19;
        r113 = zzil.zzc;
        goto L12
    L17:
        if (r112.equals("com.android.vending.billing.PURCHASES_UPDATED") == false) goto L19;
        r113 = zzil.zzb;
        goto L12
    }

    public final synchronized void zza(Context r4, IntentFilter r5) {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (this.zzb == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (Build.VERSION.SDK_INT >= 33) goto L10;
        r4.registerReceiver(this, r5);     // Catch: Throwable -> L14
    L17:
        this.zzb = true;     // Catch: Throwable -> L14
        monitor-exit(this);
        return;
    L10:
        if (true == this.zzc) goto L12;
        int r0 = 4;
    L13:
        r4.registerReceiver(this, r5, r0);     // Catch: Throwable -> L14
        goto L17
    L12:
        r0 = 2;
        goto L13
    }

    public final synchronized void zzb(Context r8, IntentFilter r9, String r10) {
        monitor-enter(this);
    L16:
        th = th;
    L17:
        Throwable r7 = th;
        monitor-exit(this);     // Catch: Throwable -> L16
        throw r7;
    L18:
        th = th;
        goto L17
    L4:
        if (this.zzb == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (Build.VERSION.SDK_INT < 33) goto L20;
        if (true == this.zzc) goto L13;
        int r102 = 4;
    L14:
        zzr r1 = this;
        r8.registerReceiver(r1, r9, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null, r102);     // Catch: Throwable -> L16
    L21:
        r1.zzb = true;     // Catch: Throwable -> L16
        monitor-exit(r1);
        return;
    L13:
        r102 = 2;
        goto L14
    L20:
        r1 = this;
        r8.registerReceiver(r1, r9, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null);     // Catch: Throwable -> L16
        goto L21
    }
}
