package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.internal.play_billing.zzbi;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzhv;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzhz;
import com.google.android.gms.internal.play_billing.zzib;
import com.google.android.gms.internal.play_billing.zzic;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzig;
import com.google.android.gms.internal.play_billing.zzij;
import com.google.android.gms.internal.play_billing.zzjm;
import com.google.android.gms.internal.play_billing.zzjo;
import com.google.android.gms.internal.play_billing.zzjs;
import com.google.android.gms.internal.play_billing.zzjt;
import com.google.android.gms.internal.play_billing.zzjv;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzbf implements ServiceConnection {
    final /* synthetic */ BillingClientImpl zza;
    private final BillingClientStateListener zzb;
    private final zzbi zzc;
    private final zzbi zzd;
    private final int zze;

    public /* synthetic */ zzbf(BillingClientImpl r1, BillingClientStateListener r2, int r3, zzbp r4) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzc = zzbi.zzc(BillingClientImpl.zzo(r1));
        this.zzd = zzbi.zzc(BillingClientImpl.zzo(r1));
        this.zzb = r2;
        this.zze = r3;
    }

    public static /* synthetic */ Object zza(zzbf r16) {
        BillingClientImpl r0 = r16.zza;
        Object r2 = BillingClientImpl.zzw(r0);
        monitor-enter(r2);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (BillingClientImpl.zza(r0) != 3) goto L11;
        monitor-exit(r2);     // Catch: Throwable -> L8
    L96:
        return null;
    L11:
        if (BillingClientImpl.zza(r0) != 1) goto L13;
        boolean r3 = true;
    L14:
        monitor-exit(r2);     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(null) == true) goto L18;
        Bundle r22 = new Bundle();
        r22.putString("accountName", null);
        zzc.zzc(r22, BillingClientImpl.zzx(r0), BillingClientImpl.zzy(r0), BillingClientImpl.zzp(r0).longValue());
    L19:
        zzie r8 = zzie.zza;
        Object r9 = BillingClientImpl.zzw(r0);
        monitor-enter(r9);
        com.google.android.gms.internal.play_billing.zzam r02 = BillingClientImpl.zzn(r0);     // Catch: Throwable -> L97
        monitor-exit(r9);     // Catch: Throwable -> L97
        BillingClientImpl r92 = r16.zza;
        if (r02 != null) goto L26;
        BillingClientImpl.zzad(r92, 0);
        int r03 = r16.zze;
        zzie r23 = zzie.zzbc;
        BillingResult r32 = zzcj.zzj;
        BillingClientImpl.zzab(r92, r23, r32, r03);
        r16.zzg(r32);
        goto L96
    L26:
        String r10 = BillingClientImpl.zzc(r92).getPackageName();
        int r11 = 25;
        int r13 = 3;
        int r12 = 25;
    L27:
        if (r12 < 3) goto L36;
        if (r22 == null) goto L102;
        r13 = r02.zzc(r12, r10, "subs", r22);     // Catch: Exception -> L30
    L33:
        if (r13 == 0) goto L34;
        r12 = r12 - 1;
        goto L27
    L34:
        zzc.zzm("BillingClient", "highestLevelSupportedForSubs: " + r12);     // Catch: Exception -> L30
    L38:
        if (r12 < 5) goto L40;
        boolean r6 = true;
    L41:
        BillingClientImpl.zzV(r92, r6);     // Catch: Exception -> L30
        if (r12 < 3) goto L44;
        boolean r62 = true;
    L45:
        BillingClientImpl.zzW(r92, r62);     // Catch: Exception -> L30
        if (r12 >= 3) goto L48;
        r8 = zzie.zzi;     // Catch: Exception -> L30
        zzc.zzm("BillingClient", "In-app billing API does not support subscription on this device.");     // Catch: Exception -> L30
    L48:
        if (r11 < 3) goto L56;
        if (r22 != null) goto L52;
        int r63 = r02.zzw(r11, r10, "inapp");     // Catch: Exception -> L30
    L51:
        r13 = r63;
        if (r13 == 0) goto L54;
        r11 = r11 - 1;
        goto L48
    L54:
        BillingClientImpl.zzT(r92, r11);     // Catch: Exception -> L30
        zzc.zzm("BillingClient", "mHighestLevelSupportedForInApp: " + BillingClientImpl.zzb(r92));     // Catch: Exception -> L30
        goto L56
    L52:
        r63 = r02.zzc(r11, r10, "inapp", r22);     // Catch: Exception -> L30
    L56:
        BillingClientImpl.zzac(r92, BillingClientImpl.zzb(r92));     // Catch: Exception -> L30
        if (BillingClientImpl.zzb(r92) >= 3) goto L59;
        r8 = zzie.zzJ;     // Catch: Exception -> L30
        zzc.zzn("BillingClient", "In-app billing API version 3 is not supported on this device.");     // Catch: Exception -> L30
    L59:
        BillingClientImpl.zzae(r92, r13);     // Catch: Exception -> L30
        if (r13 == 0) goto L105;
        BillingResult r04 = zzcj.zzb;
        r16.zzf(r04, r8, null, r3);
        r16.zzg(r04);
        goto L96
    L105:
        Long r05 = r16.zze(r3);     // Catch: Throwable -> L71
        if (r3 == false) goto L74;
        zzhz r24 = zzib.zzc();     // Catch: Throwable -> L71
        r24.zzo(6);     // Catch: Throwable -> L71
        zzjt r33 = zzjv.zzc();     // Catch: Throwable -> L71
        int r5 = r16.zze;     // Catch: Throwable -> L71
        if (r5 <= 0) goto L67;
        boolean r64 = true;
    L68:
        r33.zza(r64);     // Catch: Throwable -> L71
        r33.zzl(r5);     // Catch: Throwable -> L71
        if (r05 == null) goto L73;
        r33.zzm(r05.longValue());     // Catch: Throwable -> L71
    L73:
        BillingClientImpl r06 = r16.zza;     // Catch: Throwable -> L71
        r24.zzn(r33);     // Catch: Throwable -> L71
        BillingClientImpl.zzaa(r06, (zzib) r24.zze());     // Catch: Throwable -> L71
    L80:
        r16.zzg(zzcj.zzi);
        goto L96
    L67:
        r64 = false;
        goto L68
    L74:
        zzjm r25 = zzjo.zzc();     // Catch: Throwable -> L71
        zzic r34 = zzig.zzc();     // Catch: Throwable -> L71
        r34.zzo(0);     // Catch: Throwable -> L71
        r25.zza(r34);     // Catch: Throwable -> L71
        if (r05 == null) goto L77;
        r25.zzl(r05.longValue());     // Catch: Throwable -> L71
    L77:
        BillingClientImpl.zzi(r16.zza).zzj((zzjo) r25.zze());     // Catch: Throwable -> L71
    L71:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    L30:
        e = move-exception;
        zzc.zzo("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
        if ((e instanceof DeadObjectException) == false) goto L85;
        zzie r26 = zzie.zzaM;
    L92:
        if (r26.equals(zzie.zzP) == false) goto L94;
        String r52 = zzcg.zza(e);
    L95:
        BillingClientImpl.zzad(r16.zza, 0);
        r16.zzf(BillingClientImpl.zzl(e), r26, r52, r3);
        r16.zzg(BillingClientImpl.zzl(e));
        goto L96
    L94:
        r52 = null;
        goto L95
    L85:
        if ((e instanceof RemoteException) == false) goto L88;
        r26 = zzie.zzaL;
        goto L92
    L88:
        if ((e instanceof SecurityException) == false) goto L90;
        r26 = zzie.zzaN;
        goto L92
    L90:
        r26 = zzie.zzP;
        goto L92
    L44:
        r62 = false;
        goto L45
    L40:
        r6 = false;
        goto L41
    L102:
        r13 = r02.zzw(r12, r10, "subs");     // Catch: Exception -> L30
        goto L33
    L36:
        r12 = 0;
    L97:
        th = move-exception;
        throw th;
    L18:
        r22 = null;
        goto L19
    L13:
        r3 = false;
        goto L14
    }

    public static /* synthetic */ void zzb(zzbf r4) {
        BillingClientImpl r0 = r4.zza;
        BillingClientImpl.zzad(r0, 0);
        zzie r1 = zzie.zzx;
        BillingResult r2 = zzcj.zzk;
        BillingClientImpl.zzab(r0, r1, r2, r4.zze);
        r4.zzg(r2);
    }

    private final Long zze(boolean r2) {
        TimeUnit r0 = TimeUnit.MILLISECONDS;
        if (r2 == false) goto L8;
        zzbi r1 = this.zzc;
        if (r1.zzg() == false) goto L12;
        r1.zzf();
        return Long.valueOf(r1.zza(r0));
    L12:
        return null;
    L8:
        zzbi r12 = this.zzd;
        if (r12.zzg() == false) goto L14;
        r12.zzf();
        return Long.valueOf(r12.zza(r0));
    L14:
        return null;
    }

    private final void zzf(BillingResult r3, zzie r4, String r5, boolean r6) {
        zzic r0 = zzig.zzc();     // Catch: Throwable -> L21
        r0.zzo(r3.getResponseCode());     // Catch: Throwable -> L21
        r0.zzl(r3.getDebugMessage());     // Catch: Throwable -> L21
        r0.zzn(r4);     // Catch: Throwable -> L21
        if (r5 == null) goto L5;
        r0.zza(r5);     // Catch: Throwable -> L21
    L5:
        Long r32 = zze(r6);     // Catch: Throwable -> L21
        if (r6 == false) goto L16;
        zzjt r42 = zzjv.zzc();     // Catch: Throwable -> L21
        int r52 = this.zze;     // Catch: Throwable -> L21
        if (r52 <= 0) goto L10;
        boolean r62 = true;
    L11:
        r42.zza(r62);     // Catch: Throwable -> L21
        r42.zzl(r52);     // Catch: Throwable -> L21
        if (r32 == null) goto L14;
        r42.zzm(r32.longValue());     // Catch: Throwable -> L21
    L14:
        BillingClientImpl r2 = this.zza;     // Catch: Throwable -> L21
        zzhv r33 = zzhx.zzc();     // Catch: Throwable -> L21
        r33.zzl(r0);     // Catch: Throwable -> L21
        r33.zzp(6);     // Catch: Throwable -> L21
        r33.zzo(r42);     // Catch: Throwable -> L21
        BillingClientImpl.zzZ(r2, (zzhx) r33.zze());     // Catch: Throwable -> L21
        return;
    L10:
        r62 = false;
        goto L11
    L16:
        zzjm r43 = zzjo.zzc();     // Catch: Throwable -> L21
        r43.zza(r0);     // Catch: Throwable -> L21
        if (r32 == null) goto L19;
        r43.zzl(r32.longValue());     // Catch: Throwable -> L21
    L19:
        BillingClientImpl.zzi(this.zza).zzj((zzjo) r43.zze());     // Catch: Throwable -> L21
        return;
    L21:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
    }

    private final void zzg(BillingResult r4) {
        BillingClientImpl r0 = this.zza;
        Object r1 = BillingClientImpl.zzw(r0);
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (BillingClientImpl.zza(r0) != 3) goto L10;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L10:
        monitor-exit(r1);     // Catch: Throwable -> L8
        this.zzb.onBillingSetupFinished(r4);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception while calling onBillingSetupFinished.", th);
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName r6) {
        zzc.zzn("BillingClient", "Billing service died.");
        BillingClientImpl r0 = this.zza;     // Catch: Throwable -> L10
        if (BillingClientImpl.zzai(r0) == false) goto L12;
        zzch r02 = BillingClientImpl.zzi(r0);     // Catch: Throwable -> L10
        zzhv r1 = zzhx.zzc();     // Catch: Throwable -> L10
        r1.zzp(6);     // Catch: Throwable -> L10
        zzic r2 = zzig.zzc();     // Catch: Throwable -> L10
        r2.zzn(zzie.zzbf);     // Catch: Throwable -> L10
        r1.zzl(r2);     // Catch: Throwable -> L10
        zzjt r22 = zzjv.zzc();     // Catch: Throwable -> L10
        int r3 = this.zze;     // Catch: Throwable -> L10
        if (r3 <= 0) goto L8;
        boolean r4 = true;
    L9:
        r22.zza(r4);     // Catch: Throwable -> L10
        r22.zzl(r3);     // Catch: Throwable -> L10
        r1.zzo(r22);     // Catch: Throwable -> L10
        r02.zza((zzhx) r1.zze());     // Catch: Throwable -> L10
    L36:
    L15:
        BillingClientImpl r03 = this.zza;
        Object r12 = BillingClientImpl.zzw(r03);
        monitor-enter(r12);
    L29:
        th = move-exception;
        throw th;
    L18:
        if (BillingClientImpl.zza(r03) != 3) goto L20;
    L31:
        monitor-exit(r12);     // Catch: Throwable -> L29
        return;
    L20:
        if (BillingClientImpl.zza(r03) == 0) goto L31;
        BillingClientImpl.zzad(r03, 0);     // Catch: Throwable -> L29
        BillingClientImpl.zzaf(r03);     // Catch: Throwable -> L29
        monitor-exit(r12);     // Catch: Throwable -> L29
        this.zzb.onBillingServiceDisconnected();     // Catch: Throwable -> L26
        return;
    L26:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception while calling onBillingServiceDisconnected.", th);
        return;
    L8:
        r4 = false;
        goto L9
    L12:
        BillingClientImpl.zzi(r0).zzi(zzij.zzd());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
        goto L15
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r9, IBinder r10) {
        zzc.zzm("BillingClient", "Billing service connected.");
        BillingClientImpl r92 = this.zza;
        Object r1 = BillingClientImpl.zzw(r92);
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (BillingClientImpl.zza(r92) != 3) goto L10;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L10:
        BillingClientImpl.zzU(r92, com.google.android.gms.internal.play_billing.zzal.zzs(r10));     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        if (BillingClientImpl.zzG(new zzbd(this), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, new zzbe(this), BillingClientImpl.zzf(r92), r92.zzF()) != null) goto L19;
        int r102 = this.zze;
        BillingResult r0 = BillingClientImpl.zzk(r92);
        BillingClientImpl.zzab(r92, zzie.zzy, r0, r102);
        zzg(r0);
        return;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r6) {
        zzc.zzn("BillingClient", "Billing service disconnected.");
        BillingClientImpl r0 = this.zza;     // Catch: Throwable -> L10
        if (BillingClientImpl.zzai(r0) == false) goto L12;
        zzch r02 = BillingClientImpl.zzi(r0);     // Catch: Throwable -> L10
        zzhv r1 = zzhx.zzc();     // Catch: Throwable -> L10
        r1.zzp(6);     // Catch: Throwable -> L10
        zzic r2 = zzig.zzc();     // Catch: Throwable -> L10
        r2.zzn(zzie.zzbe);     // Catch: Throwable -> L10
        r1.zzl(r2);     // Catch: Throwable -> L10
        zzjt r22 = zzjv.zzc();     // Catch: Throwable -> L10
        int r3 = this.zze;     // Catch: Throwable -> L10
        if (r3 <= 0) goto L8;
        boolean r4 = true;
    L9:
        r22.zza(r4);     // Catch: Throwable -> L10
        r22.zzl(r3);     // Catch: Throwable -> L10
        r1.zzo(r22);     // Catch: Throwable -> L10
        r02.zza((zzhx) r1.zze());     // Catch: Throwable -> L10
    L33:
    L15:
        zzbi r03 = this.zzd;
        r03.zzd();
        r03.zze();
        BillingClientImpl r04 = this.zza;
        Object r12 = BillingClientImpl.zzw(r04);
        monitor-enter(r12);
    L21:
        th = move-exception;
        throw th;
    L18:
        if (BillingClientImpl.zza(r04) != 3) goto L23;
        monitor-exit(r12);     // Catch: Throwable -> L21
        return;
    L23:
        BillingClientImpl.zzad(r04, 0);     // Catch: Throwable -> L21
        monitor-exit(r12);     // Catch: Throwable -> L21
        this.zzb.onBillingServiceDisconnected();     // Catch: Throwable -> L27
        return;
    L27:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception while calling onBillingServiceDisconnected.", th);
        return;
    L8:
        r4 = false;
        goto L9
    L12:
        BillingClientImpl.zzi(r0).zzk(zzjs.zzd());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        zzc.zzo("BillingClient", "Unable to log.", th);
        goto L15
    }

    public final void zzc() {
        zzbi r0 = this.zzc;
        r0.zzd();
        r0.zze();
    }

    public final boolean zzd() {
        if (this.zze <= 0) goto L6;
        return true;
    L6:
        return false;
    }
}
