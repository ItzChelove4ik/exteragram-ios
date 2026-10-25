package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class zznl extends zzg {
    private final zznf zza;
    private zzgb zzb;
    private volatile Boolean zzc;
    private final zzaz zzd;
    private ScheduledExecutorService zze;
    private final zzog zzf;
    private final List zzg;
    private final zzaz zzh;

    public zznl(zzic r3) {
        super(r3);
        this.zzg = new ArrayList();
        this.zzf = new zzog(r3.zzba());
        this.zza = new zznf(this);
        this.zzd = new zzmm(this, r3);
        this.zzh = new zzmq(this, r3);
    }

    private final boolean zzad() {
        this.zzu.zzaV();
        return true;
    }

    private final void zzae() {
        zzg();
        this.zzf.zza();
        this.zzu.zzc();
        long r0 = ((Long) zzfy.zzY.zzb(null)).longValue();
        this.zzd.zzb(r0);
    }

    private final void zzaf(Runnable r7) {
        zzg();
        if (zzh() == false) goto L6;
        r7.run();
        return;
    L6:
        List r0 = this.zzg;
        long r1 = r0.size();
        zzic r3 = this.zzu;
        r3.zzc();
        if (r1 < 1000) goto L10;
        r3.zzaW().zzb().zza("Discarding data. Max runnable queue size reached");
        return;
    L10:
        r0.add(r7);
        this.zzh.zzb(60000);
        zzI();
    }

    private final void zzag() {
        zzg();
        zzgs r0 = this.zzu.zzaW().zzk();
        List r1 = this.zzg;
        r0.zzb("Processing queued up service tasks", Integer.valueOf(r1.size()));
        Iterator r02 = r1.iterator();
    L4:
        if (r02.hasNext() == false) goto L10;
        ((Runnable) r02.next()).run();     // Catch: RuntimeException -> L8
    L8:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Task exception while flushing queue", e);
        goto L4
    L10:
        this.zzg.clear();
        this.zzh.zzd();
    }

    private final zzr zzah(boolean r5) {
        zzic r0 = this.zzu;
        r0.zzaV();
        zzgi r4 = this.zzu.zzv();
        String r1 = null;
        if (r5 == false) goto L14;
        zzic r52 = r0.zzaW().zzu;
        if (r52.zzd().zzb == null) goto L14;
        Pair r53 = r52.zzd().zzb.zzb();
        if (r53 == null) goto L14;
        if (r53 == zzhh.zza) goto L14;
        String r02 = String.valueOf(r53.second);
        String r54 = (String) r53.first;
        int r12 = r02.length();
        StringBuilder r3 = new StringBuilder((r12 + 1) + String.valueOf(r54).length());
        r3.append(r02);
        r3.append(":");
        r3.append(r54);
        r1 = r3.toString();
    L14:
        return r4.zzh(r1);
    }

    public final void zzA(zzpl r4) {
        zzg();
        zzb();
        zzad();
        boolean r0 = this.zzu.zzm().zzj(r4);
        zzaf(new zzmg(this, zzah(true), r0, r4));
    }

    public final void zzB() {
        zzg();
        zzb();
        zzr r0 = zzah(false);
        zzad();
        this.zzu.zzm().zzh();
        zzaf(new zzmh(this, r0));
    }

    public final void zzC(AtomicReference r3) {
        zzg();
        zzb();
        zzaf(new zzmi(this, r3, zzah(false)));
    }

    public final void zzD(com.google.android.gms.internal.measurement.zzcs r3) {
        zzg();
        zzb();
        zzaf(new zzmj(this, zzah(false), r3));
    }

    public final void zzE() {
        zzg();
        zzb();
        zzr r1 = zzah(true);
        zzad();
        this.zzu.zzc().zzp(null, zzfy.zzaW);
        this.zzu.zzm().zzn();
        zzaf(new zzmk(this, r1, true));
    }

    public final void zzF() {
        zzg();
        zzb();
        zzaf(new zzml(this, zzah(true)));
    }

    public final void zzG(zzlu r2) {
        zzg();
        zzb();
        zzaf(new zzmn(this, r2));
    }

    public final void zzH(Bundle r8) {
        zzg();
        zzb();
        zzbf r5 = new zzbf(r8);
        zzad();
        if (this.zzu.zzc().zzp(null, zzfy.zzaW) == true) goto L5;
    L7:
        boolean r4 = false;
    L8:
        zzaf(new zzmo(this, true, zzah(false), r4, r5, r8));
        return;
    L5:
        if (this.zzu.zzm().zzl(r5) == false) goto L7;
        r4 = true;
        goto L8
    }

    public final void zzI() {
        zzg();
        zzb();
        if (zzh() == false) goto L6;
        return;
    L6:
        if (zzK() == true) goto L17;
        zzic r0 = this.zzu;
        if (r0.zzc().zzE() == true) goto L19;
        r0.zzaV();
        List<ResolveInfo> r1 = r0.zzaZ().getPackageManager().queryIntentServices(new Intent().setClassName(r0.zzaZ(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        if (r1 != null) goto L12;
    L15:
        r0.zzaW().zzb().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        return;
    L12:
        if (r1.isEmpty() == true) goto L15;
        Intent r12 = new Intent("com.google.android.gms.measurement.START");
        Context r3 = r0.zzaZ();
        r0.zzaV();
        r12.setComponent(new ComponentName(r3, "com.google.android.gms.measurement.AppMeasurementService"));
        this.zza.zza(r12);
        return;
    L19:
        return;
    L17:
        this.zza.zzc();
    }

    public final Boolean zzJ() {
        return this.zzc;
    }

    public final boolean zzK() {
        zzg();
        zzb();
        if (this.zzc != null) goto L50;
        zzg();
        zzb();
        zzic r0 = this.zzu;
        zzhh r1 = r0.zzd();
        r1.zzg();
        boolean r4 = false;
        if (r1.zzd().contains("use_service") == true) goto L7;
        Boolean r12 = null;
    L8:
        boolean r2 = true;
        if (r12 != null) goto L11;
    L13:
        r0.zzaV();
        if (this.zzu.zzv().zzo() != 1) goto L16;
    L15:
        r4 = true;
    L41:
        if (r4 == false) goto L43;
    L45:
        if (r2 == false) goto L47;
        zzhh r02 = r0.zzd();
        r02.zzg();
        SharedPreferences.Editor r03 = r02.zzd().edit();
        r03.putBoolean("use_service", r4);
        r03.apply();
    L47:
        r2 = r4;
    L48:
        this.zzc = Boolean.valueOf(r2);
        goto L50
    L43:
        if (r0.zzc().zzE() == false) goto L45;
        r0.zzaW().zzb().zza("No way to upload. Consider using the full version of Analytics");
        goto L47
    L16:
        r0.zzaW().zzk().zza("Checking service availability");
        int r5 = r0.zzk().zzal(12451000);
        if (r5 == 0) goto L40;
        if (r5 != 1) goto L20;
        r0.zzaW().zzk().zza("Service missing");
        goto L41
    L20:
        if (r5 != 2) goto L22;
        r0.zzaW().zzj().zza("Service container out of date");
        if (r0.zzk().zzak() < 17443) goto L41;
        if (r12 == null) goto L38;
        r2 = false;
    L38:
        r4 = r2;
        r2 = false;
        goto L41
    L22:
        if (r5 != 3) goto L24;
        r0.zzaW().zze().zza("Service disabled");
    L28:
        r2 = false;
        goto L41
    L24:
        if (r5 != 9) goto L26;
        r0.zzaW().zze().zza("Service invalid");
        goto L28
    L26:
        if (r5 == 18) goto L29;
        r0.zzaW().zze().zzb("Unexpected service status", Integer.valueOf(r5));
        goto L28
    L29:
        r0.zzaW().zze().zza("Service updating");
        goto L15
    L40:
        r0.zzaW().zzk().zza("Service available");
        goto L15
    L11:
        if (r12.booleanValue() == false) goto L13;
    L7:
        r12 = Boolean.valueOf(r1.zzd().getBoolean("use_service", false));
    L50:
        return this.zzc.booleanValue();
    }

    public final void zzL(zzgb r1) {
        zzg();
        Preconditions.checkNotNull(r1);
        this.zzb = r1;
        zzae();
        zzag();
    }

    public final void zzM() {
        zzg();
        zzb();
        zznf r0 = this.zza;
        r0.zzb();
        ConnectionTracker.getInstance().unbindService(this.zzu.zzaZ(), r0);     // Catch: Throwable -> L6
    L4:
        this.zzb = null;
    }

    public final void zzN(com.google.android.gms.internal.measurement.zzcs r4, zzbh r5, String r6) {
        zzg();
        zzb();
        zzic r0 = this.zzu;
        if (r0.zzk().zzal(12451000) == 0) goto L6;
        r0.zzaW().zze().zza("Not bundling data. Service unavailable or out of date");
        r0.zzk().zzar(r4, new byte[0]);
        return;
    L6:
        zzaf(new zzmp(this, r5, r6, r4));
    }

    public final boolean zzO() {
        zzg();
        zzb();
        if (zzK() == true) goto L5;
        return true;
    L5:
        if (this.zzu.zzk().zzak() < ((Integer) zzfy.zzaJ.zzb(null)).intValue()) goto L7;
        return true;
    L7:
        return false;
    }

    public final boolean zzP() {
        zzg();
        zzb();
        if (zzK() == true) goto L5;
        return true;
    L5:
        if (this.zzu.zzk().zzak() < 241200) goto L7;
        return true;
    L7:
        return false;
    }

    public final /* synthetic */ void zzQ() {
        zzgb r0 = this.zzb;
        if (r0 != null) goto L12;
        this.zzu.zzaW().zzb().zza("Failed to send storage consent settings to service");
        return;
    L12:
        zzr r1 = zzah(false);     // Catch: RemoteException -> L9
        Preconditions.checkNotNull(r1);     // Catch: RemoteException -> L9
        r0.zzy(r1);     // Catch: RemoteException -> L9
        zzae();     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to send storage consent settings to the service", e);
    }

    public final /* synthetic */ void zzR() {
        zzgb r0 = this.zzb;
        if (r0 != null) goto L12;
        this.zzu.zzaW().zzb().zza("Failed to send Dma consent settings to service");
        return;
    L12:
        zzr r1 = zzah(false);     // Catch: RemoteException -> L9
        Preconditions.checkNotNull(r1);     // Catch: RemoteException -> L9
        r0.zzz(r1);     // Catch: RemoteException -> L9
        zzae();     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to send Dma consent settings to the service", e);
    }

    public final /* synthetic */ void zzS(AtomicReference r3, zzr r4, Bundle r5) {
        monitor-enter(r3);
        zzgb r0 = this.zzb;     // Catch: Throwable -> L8 RemoteException -> L10
        if (r0 != null) goto L12;
        this.zzu.zzaW().zzb().zza("Failed to request trigger URIs; not connected to service");     // Catch: Throwable -> L8 RemoteException -> L10
        monitor-exit(r3);     // Catch: Throwable -> L8
        return;
    L12:
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L8 RemoteException -> L10
        r0.zzD(r4, r5, new zzme(this, r3));     // Catch: Throwable -> L8 RemoteException -> L10
        zzae();     // Catch: Throwable -> L8 RemoteException -> L10
    L15:
        monitor-exit(r3);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to request trigger URIs; remote exception", e);     // Catch: Throwable -> L8
        r3.notifyAll();     // Catch: Throwable -> L8
        goto L15
    }

    public final /* synthetic */ void zzT(AtomicReference r3, zzr r4, zzoo r5) {
        monitor-enter(r3);
        zzgb r0 = this.zzb;     // Catch: Throwable -> L8 RemoteException -> L10
        if (r0 != null) goto L12;
        this.zzu.zzaW().zzb().zza("[sgtm] Failed to get upload batches; not connected to service");     // Catch: Throwable -> L8 RemoteException -> L10
        monitor-exit(r3);     // Catch: Throwable -> L8
        return;
    L12:
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L8 RemoteException -> L10
        r0.zzB(r4, r5, new zzmf(this, r3));     // Catch: Throwable -> L8 RemoteException -> L10
        zzae();     // Catch: Throwable -> L8 RemoteException -> L10
    L15:
        monitor-exit(r3);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    L10:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("[sgtm] Failed to get upload batches; remote exception", e);     // Catch: Throwable -> L8
        r3.notifyAll();     // Catch: Throwable -> L8
        goto L15
    }

    public final /* synthetic */ void zzU(zzr r3, zzaf r4) {
        zzgb r0 = this.zzb;
        if (r0 != null) goto L11;
        this.zzu.zzaW().zzb().zza("[sgtm] Discarding data. Failed to update batch upload status.");
        return;
    L11:
        r0.zzC(r3, r4);     // Catch: RemoteException -> L8
        zzae();     // Catch: RemoteException -> L8
        return;
    L8:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("[sgtm] Failed to update batch upload status, rowId, exception", Long.valueOf(r4.zza), e);
    }

    public final /* synthetic */ void zzV() {
        zzae();
    }

    public final /* synthetic */ void zzW(ComponentName r3) {
        zzg();
        if (this.zzb == null) goto L6;
        this.zzb = null;
        this.zzu.zzaW().zzk().zzb("Disconnected from device MeasurementService", r3);
        zzg();
        zzI();
        return;
    }

    public final /* synthetic */ void zzX() {
        zzag();
    }

    public final /* synthetic */ zznf zzY() {
        return this.zza;
    }

    public final /* synthetic */ zzgb zzZ() {
        return this.zzb;
    }

    public final /* synthetic */ void zzaa(zzgb r1) {
        this.zzb = null;
    }

    public final /* synthetic */ ScheduledExecutorService zzab() {
        return this.zze;
    }

    public final /* synthetic */ void zzac(ScheduledExecutorService r1) {
        this.zze = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return false;
    }

    public final boolean zzh() {
        zzg();
        zzb();
        if (this.zzb == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzi() {
        zzg();
        zzb();
        zzaf(new zzmr(this, zzah(true)));
    }

    public final void zzj(boolean r2) {
        zzg();
        zzb();
        if (zzO() == false) goto L6;
        zzaf(new zzms(this, zzah(false)));
        return;
    }

    public final void zzk(boolean r1) {
        zzg();
        zzb();
        zzaf(new zznk(this));
    }

    public final void zzl() {
        zzg();
        zzb();
        zzaf(new zzng(this));
    }

    public final void zzm(zzgb r60, AbstractSafeParcelable r61, zzr r62) {
        AbstractSafeParcelable r3 = r61;
        zzg();
        zzb();
        zzad();
        zzic r4 = this.zzu;
        r4.zzc();
        int r6 = 100;
        zzr r0 = r62;
        int r8 = 100;
        int r7 = 0;
    L4:
        if (r7 >= 1001) goto L60;
        if (r8 != r6) goto L86;
        zzic r82 = this.zzu;
        ArrayList r9 = new ArrayList();
        List r83 = r82.zzm().zzm(r6);
        if (r83 == null) goto L9;
        r9.addAll(r83);
        r8 = r83.size();
    L10:
        if (r3 == null) goto L13;
        if (r8 >= r6) goto L13;
        r9.add(new zzgk(r3, r0.zzc, r0.zzj));
    L13:
        int r10 = r9.size();
        int r11 = 0;
    L14:
        if (r11 >= r10) goto L59;
        zzgk r12 = (zzgk) r9.get(r11);
        AbstractSafeParcelable r13 = r12.zza;
        zzal r14 = r4.zzc();
        zzfx r15 = zzfy.zzaW;
        if (r14.zzp(null, r15) == false) goto L20;
        String r142 = r12.zzb;
        if (TextUtils.isEmpty(r142) == true) goto L20;
        int r622 = r7;
        zzic r58 = r4;
        zzr r32 = new zzr(r0.zza, r0.zzb, r142, r12.zzc, r0.zzd, r0.zze, r0.zzf, r0.zzg, r0.zzh, r0.zzi, r0.zzk, r0.zzl, r0.zzm, r0.zzn, r0.zzo, r0.zzp, r0.zzq, r0.zzr, r0.zzs, r0.zzt, r0.zzu, r0.zzv, r0.zzw, r0.zzx, r0.zzy, r0.zzz, r0.zzA, r0.zzB, r0.zzC, r0.zzD, r0.zzE, r0.zzF);
    L22:
        if ((r13 instanceof zzbh) == false) goto L38;
        zzic r02 = this.zzu;     // Catch: RemoteException -> L32
        long r19 = r02.zzba().currentTimeMillis();     // Catch: RemoteException -> L32
        long r63 = r02.zzba().elapsedRealtime();     // Catch: RemoteException -> L30
        r60.zze((zzbh) r13, r32);     // Catch: RemoteException -> L28
        r58.zzaW().zzk().zza("Logging telemetry for logEvent from database");     // Catch: RemoteException -> L28
        zzgq.zza(r02).zzb(36301, 0, r19, r02.zzba().currentTimeMillis(), (int) (r02.zzba().elapsedRealtime() - r63));     // Catch: RemoteException -> L28
    L58:
        r11 = r11 + 1;
        r7 = r622;
        r0 = r32;
        r4 = r58;
    L28:
        e = e;
    L34:
        this.zzu.zzaW().zzb().zzb("Failed to send event to the service", e);
        if (r19 == 0) goto L58;
        zzic r03 = this.zzu;
        zzgq.zza(r03).zzb(36301, 13, r19, r03.zzba().currentTimeMillis(), (int) (r03.zzba().elapsedRealtime() - r63));
    L30:
        e = e;
        r63 = 0;
    L32:
        e = e;
        r63 = 0;
        r19 = 0;
        goto L34
    L38:
        if ((r13 instanceof zzpl) == false) goto L44;
        r60.zzf((zzpl) r13, r32);     // Catch: RemoteException -> L41
    L41:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to send user property to the service", e);
        goto L58
    L44:
        if ((r13 instanceof zzah) == true) goto L63;
        zzic r04 = this.zzu;
        if (r04.zzc().zzp(null, r15) == true) goto L52;
    L57:
        r04.zzaW().zzb().zza("Discarding data. Unrecognized parcel type.");
        goto L58
    L52:
        if ((r13 instanceof zzbf) == false) goto L57;
        r60.zzu(((zzbf) r13).zzf(), r32);     // Catch: RemoteException -> L55
    L55:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to send default event parameters to the service", e);
        goto L58
    L63:
        r60.zzn((zzah) r13, r32);     // Catch: RemoteException -> L47
    L47:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to send conditional user property to the service", e);
    L20:
        r58 = r4;
        r622 = r7;
        r32 = r0;
        goto L22
    L59:
        r7 = r7 + 1;
        r3 = r61;
        r6 = 100;
        goto L4
    L9:
        r8 = 0;
        goto L10
    L86:
        return;
    }

    public final void zzn(zzbh r9, String r10) {
        Preconditions.checkNotNull(r9);
        zzg();
        zzb();
        zzad();
        boolean r5 = this.zzu.zzm().zzi(r9);
        zzaf(new zzmt(this, true, zzah(true), r5, r9, r10));
    }

    public final void zzp(zzah r9) {
        Preconditions.checkNotNull(r9);
        zzg();
        zzb();
        this.zzu.zzaV();
        boolean r5 = this.zzu.zzm().zzk(r9);
        zzah r6 = new zzah(r9);
        zzaf(new zzmu(this, true, zzah(true), r5, r6, r9));
    }

    public final void zzq(AtomicReference r8, String r9, String r10, String r11) {
        zzg();
        zzb();
        zzaf(new zzmv(this, r8, null, r10, r11, zzah(false)));
    }

    public final void zzs(com.google.android.gms.internal.measurement.zzcs r8, String r9, String r10) {
        zzg();
        zzb();
        zzaf(new zzmw(this, r9, r10, zzah(false), r8));
    }

    public final void zzt(AtomicReference r9, String r10, String r11, String r12, boolean r13) {
        zzg();
        zzb();
        zzaf(new zzmx(this, r9, null, r11, r12, zzah(false), r13));
    }

    public final void zzu(com.google.android.gms.internal.measurement.zzcs r9, String r10, String r11, boolean r12) {
        zzg();
        zzb();
        zzaf(new zzmc(this, r10, r11, zzah(false), r12, r9));
    }

    public final void zzw(AtomicReference r3, Bundle r4) {
        zzg();
        zzb();
        zzaf(new zznh(this, r3, zzah(false), r4));
    }

    public final void zzx(AtomicReference r3, zzoo r4) {
        zzg();
        zzb();
        zzaf(new zzni(this, r3, zzah(false), r4));
    }

    public final void zzy(zzaf r3) {
        zzg();
        zzb();
        zzr r0 = zzah(true);
        Preconditions.checkNotNull(r0);
        zzaf(new zznj(this, r0, r3));
    }

    public final zzao zzz() {
        zzg();
        zzb();
        zzgb r0 = this.zzb;
        if (r0 != null) goto L6;
        zzI();
        this.zzu.zzaW().zzj().zza("Failed to get consents; not connected to service yet.");
        return null;
    L6:
        zzr r2 = zzah(false);
        Preconditions.checkNotNull(r2);
        zzao r02 = r0.zzw(r2);     // Catch: RemoteException -> L9
        zzae();     // Catch: RemoteException -> L9
        return r02;
    L9:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to get consents; remote exception", e);
        return null;
    }
}
