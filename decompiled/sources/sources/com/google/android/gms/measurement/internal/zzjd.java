package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzaeh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzjd extends zzga {
    private final zzpg zza;
    private Boolean zzb;
    private String zzc;

    public zzjd(zzpg r1, String r2) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
        this.zzc = null;
    }

    private final void zzM(zzbh r1, zzr r2) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzF(r1, r2);
    }

    private final void zzN(zzr r2, boolean r3) {
        Preconditions.checkNotNull(r2);
        String r32 = r2.zza;
        Preconditions.checkNotEmpty(r32);
        zzO(r32, false);
        this.zza.zzt().zzC(r2.zzb);
    }

    private final void zzO(String r4, boolean r5) {
        if (TextUtils.isEmpty(r4) == true) goto L33;
        if (r5 == false) goto L22;
    L15:
        e = move-exception;
        this.zza.zzaW().zzb().zzb("Measurement Service called with invalid calling package. appId", zzgu.zzl(r4));
        throw e;
    L6:
        if (this.zzb != null) goto L19;
        boolean r0 = true;
        if ("com.google.android.gms".equals(this.zzc) == true) goto L17;
        zzpg r52 = this.zza;     // Catch: SecurityException -> L15
        if (UidVerifier.isGooglePlayServicesUid(r52.zzaZ(), Binder.getCallingUid()) == true) goto L17;
        if (GoogleSignatureVerifier.getInstance(r52.zzaZ()).isUidGoogleSigned(Binder.getCallingUid()) == true) goto L17;
        r0 = false;
    L17:
        this.zzb = Boolean.valueOf(r0);     // Catch: SecurityException -> L15
    L19:
        if (this.zzb.booleanValue() == false) goto L22;
        return;
    L22:
        if (this.zzc != null) goto L27;
        if (GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzaZ(), Binder.getCallingUid(), r4) == false) goto L27;
        this.zzc = r4;     // Catch: SecurityException -> L15
    L27:
        if (r4.equals(this.zzc) == false) goto L30;
        return;
    L30:
        throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{r4}));     // Catch: SecurityException -> L15
    L33:
        this.zza.zzaW().zzb().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzA(zzr r2) {
        zzN(r2, false);
        zzd(new zzif(this, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzB(zzr r3, zzoo r4, zzgh r5) {
        zzN(r3, false);
        String r32 = (String) Preconditions.checkNotNull(r3.zza);
        this.zza.zzaX().zzj(new zziz(this, r32, r4, r5));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzC(zzr r2, zzaf r3) {
        zzN(r2, false);
        zzd(new zzja(this, r2, r3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzD(zzr r8, Bundle r9, zzge r10) {
        zzN(r8, false);
        String r6 = (String) Preconditions.checkNotNull(r8.zza);
        this.zza.zzaX().zzj(new zziy(this, r8, r9, r10, r6));
    }

    public final void zzE(Runnable r2) {
        Preconditions.checkNotNull(r2);
        zzpg r1 = this.zza;
        if (r1.zzaX().zze() == false) goto L6;
        r2.run();
        return;
    L6:
        r1.zzaX().zzl(r2);
    }

    public final /* synthetic */ void zzF(zzr r1) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzv(r1);
    }

    public final /* synthetic */ void zzG(zzr r1) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzw(r1);
    }

    public final /* synthetic */ void zzH(zzr r2, Bundle r3, zzge r4, String r5) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r4.zze(r0.zzar(r2, r3));     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zza.zzaW().zzb().zzc("Failed to return trigger URIs for app", r5, e);
    }

    public final /* synthetic */ void zzI(String r11, zzoo r12, zzgh r13) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzaX().zzg();
        r0.zzu();
        List r122 = r0.zzj().zzC(r11, r12, ((Integer) zzfy.zzA.zzb(null)).intValue());
        ArrayList r1 = new ArrayList();
        Iterator r123 = r122.iterator();
    L4:
        if (r123.hasNext() == false) goto L27;
        zzpj r2 = (zzpj) r123.next();
        if (r0.zzO(r11, r2.zze()) == false) goto L7;
        int r4 = r2.zzi();
        if (r4 <= 0) goto L16;
        if (r4 > ((Integer) zzfy.zzy.zzb(null)).intValue()) goto L26;
        if (r0.zzba().currentTimeMillis() >= (r2.zzh() + Math.min(((Long) zzfy.zzw.zzb(null)).longValue() * (1 << (r4 - 1)), ((Long) zzfy.zzx.zzb(null)).longValue()))) goto L16;
    L26:
        r0.zzaW().zzk().zzd("[sgtm] batch skipped waiting for next retry. appId, rowId, lastUploadMillis", r11, Long.valueOf(r2.zzc()), Long.valueOf(r2.zzh()));
    L16:
        zzom r22 = r2.zzb();
        com.google.android.gms.internal.measurement.zzhz r42 = (com.google.android.gms.internal.measurement.zzhz) zzpk.zzw(com.google.android.gms.internal.measurement.zzib.zzi(), r22.zzb);     // Catch: zzaeh -> L25
        int r5 = 0;
    L18:
        if (r5 >= r42.zzb()) goto L21;
        com.google.android.gms.internal.measurement.zzic r6 = (com.google.android.gms.internal.measurement.zzic) r42.zzc(r5).zzco();     // Catch: zzaeh -> L25
        r6.zzs(r0.zzba().currentTimeMillis());     // Catch: zzaeh -> L25
        r42.zzd(r5, r6);     // Catch: zzaeh -> L25
        r5 = r5 + 1;     // Catch: zzaeh -> L25
        goto L18
    L21:
        r22.zzb = ((com.google.android.gms.internal.measurement.zzib) r42.zzbd()).zzcd();     // Catch: zzaeh -> L25
        if (Log.isLoggable(r0.zzaW().zzn(), 2) == false) goto L24;
        r22.zzg = r0.zzp().zzi((com.google.android.gms.internal.measurement.zzib) r42.zzbd());     // Catch: zzaeh -> L25
    L24:
        r1.add(r22);
    L25:
        r0.zzaW().zze().zzb("Failed to parse queued batch. appId", r11);
        goto L4
    L7:
        r0.zzaW().zzk().zzd("[sgtm] batch skipped due to destination in backoff. appId, rowId, url", r11, Long.valueOf(r2.zzc()), r2.zze());
        goto L4
    L27:
        zzoq r124 = new zzoq(r1);
        r13.zze(r124);     // Catch: RemoteException -> L30
        this.zza.zzaW().zzk().zzc("[sgtm] Sending queued upload batches to client. appId, count", r11, Integer.valueOf(r124.zza.size()));     // Catch: RemoteException -> L30
        return;
    L30:
        e = move-exception;
        this.zza.zzaW().zzb().zzc("[sgtm] Failed to return upload batches for app", r11, e);
    }

    public final /* synthetic */ void zzJ(zzr r1, zzaf r2) {
        zzpg r0 = this.zza;
        r0.zzaa();
        r0.zzas((String) Preconditions.checkNotNull(r1.zza), r2);
    }

    public final /* synthetic */ void zzK(Bundle r14, String r15, zzr r16) {
        boolean r0 = r14.isEmpty();
        zzpg r1 = this.zza;
        if (r0 == false) goto L10;
        zzaw r13 = r1.zzj();
        r13.zzg();
        r13.zzay();
        r13.zze().execSQL("delete from default_event_params where app_id=?", new String[]{r15});     // Catch: SQLiteException -> L7
        return;
    L7:
        e = move-exception;
        r13.zzu.zzaW().zzb().zzb("Error clearing default event params", e);
        return;
    L10:
        zzaw r12 = r1.zzj();
        r12.zzg();
        r12.zzay();
        zzbc r02 = new zzbc(r12.zzu, _UrlKt.FRAGMENT_ENCODE_SET, r15, "dep", 0, 0, 0, r14);
        byte[] r03 = r12.zzg.zzp().zzh(r02).zzcd();
        zzic r17 = r12.zzu;
        r17.zzaW().zzk().zzc("Saving default event parameters, appId, data size", r15, Integer.valueOf(r03.length));
        ContentValues r2 = new ContentValues();
        r2.put("app_id", r15);
        r2.put("parameters", r03);
    L15:
        e = move-exception;
        r12.zzu.zzaW().zzb().zzc("Error storing default event parameters. appId", zzgu.zzl(r15), e);
    L17:
        zzpg r132 = this.zza;
        zzaw r04 = r132.zzj();
        long r18 = r16.zzD;
        if (r04.zzW(r15, r18) == false) goto L25;
        r132.zzj().zzX(r15, Long.valueOf(r18), null, r14);
        return;
    L25:
        return;
    L12:
        if (r12.zze().insertWithOnConflict("default_event_params", null, r2, 5) != (-1)) goto L17;
        r17.zzaW().zzb().zzb("Failed to insert default event parameters (got -1). appId", zzgu.zzl(r15));     // Catch: SQLiteException -> L15
        goto L17
    }

    public final /* synthetic */ zzpg zzL() {
        return this.zza;
    }

    public final void zzb(zzbh r7, zzr r8) {
        zzpg r0 = this.zza;
        zzht r1 = r0.zzh();
        String r2 = r8.zza;
        if (TextUtils.isEmpty(r2) == false) goto L5;
        com.google.android.gms.internal.measurement.zzc r12 = null;
    L6:
        if (r12 != null) goto L29;
        this.zza.zzaW().zzk().zzb("EES not loaded for", r8.zza);
        zzM(r7, r8);
        return;
    L29:
        Map r02 = r0.zzp().zzz(r7.zzb.zzf(), true);     // Catch: com.google.android.gms.internal.measurement.zzd -> L24
        String r22 = r7.zza;     // Catch: com.google.android.gms.internal.measurement.zzd -> L24
        String r3 = zzjm.zza(r22);     // Catch: com.google.android.gms.internal.measurement.zzd -> L24
        if (r3 == null) goto L10;
        r22 = r3;
    L10:
        if (r12.zzb(new com.google.android.gms.internal.measurement.zzaa(r22, r7.zzd, r02)) == true) goto L14;
    L25:
        this.zza.zzaW().zzk().zzb("EES was not applied to event", r7.zza);
        zzM(r7, r8);
        return;
    L14:
        if (r12.zzc() == false) goto L16;
        zzpg r03 = this.zza;
        r03.zzaW().zzk().zzb("EES edited event", r7.zza);
        zzM(r03.zzp().zzA(r12.zze().zzc()), r8);
    L18:
        if (r12.zzd() == false) goto L23;
        Iterator r72 = r12.zze().zzf().iterator();
    L21:
        if (r72.hasNext() == false) goto L32;
        com.google.android.gms.internal.measurement.zzaa r04 = (com.google.android.gms.internal.measurement.zzaa) r72.next();
        zzpg r13 = this.zza;
        r13.zzaW().zzk().zzb("EES logging created event", r04.zzb());
        zzM(r13.zzp().zzA(r04), r8);
        goto L21
    L32:
        return;
    L23:
        return;
    L16:
        zzM(r7, r8);
    L24:
        this.zza.zzaW().zzb().zzc("EES error. appId, eventName", r8.zzb, r7.zza);
        goto L25
    L5:
        r12 = (com.google.android.gms.internal.measurement.zzc) r1.zze.get(r2);
        goto L6
    }

    public final zzbh zzc(zzbh r9, zzr r10) {
        if ("_cmp".equals(r9.zza) == false) goto L15;
        zzbf r2 = r9.zzb;
        if (r2 == null) goto L15;
        if (r2.zze() == 0) goto L15;
        String r102 = r2.zzd("_cis");
        if ("referrer broadcast".equals(r102) == false) goto L12;
    L13:
        this.zza.zzaW().zzi().zzb("Event has been filtered ", r9.toString());
        return new zzbh("_cmpx", r2, r9.zzc, r9.zzd, r9.zze);
    L12:
        if ("referrer API".equals(r102) == true) goto L13;
    L15:
        return r9;
    }

    public final void zzd(Runnable r2) {
        Preconditions.checkNotNull(r2);
        zzpg r1 = this.zza;
        if (r1.zzaX().zze() == false) goto L6;
        r2.run();
        return;
    L6:
        r1.zzaX().zzj(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zze(zzbh r2, zzr r3) {
        Preconditions.checkNotNull(r2);
        zzN(r3, false);
        zzd(new zzir(this, r2, r3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzf(zzpl r2, zzr r3) {
        Preconditions.checkNotNull(r2);
        zzN(r3, false);
        zzd(new zziu(this, r2, r3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzg(zzr r2) {
        zzN(r2, false);
        zzd(new zzie(this, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzh(zzbh r1, String r2, String r3) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotEmpty(r2);
        zzO(r2, true);
        zzd(new zzis(this, r1, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzi(zzr r2) {
        zzN(r2, false);
        zzd(new zzin(this, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzj(zzr r5, boolean r6) {
        zzN(r5, false);
        String r0 = r5.zza;
        Preconditions.checkNotNull(r0);
        List r02 = (List) this.zza.zzaX().zzh(new zzid(this, r0)).get();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        ArrayList r1 = new ArrayList(r02.size());     // Catch: ExecutionException -> L11 InterruptedException -> L13
        Iterator r03 = r02.iterator();     // Catch: ExecutionException -> L11 InterruptedException -> L13
    L4:
        if (r03.hasNext() == false) goto L17;
        zzpn r2 = (zzpn) r03.next();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        if (r6 == true) goto L15;
        if (zzpp.zzac(r2.zzc) == true) goto L4;
    L15:
        r1.add(new zzpl(r2));     // Catch: ExecutionException -> L11 InterruptedException -> L13
        goto L4
    L17:
        return r1;
    L13:
        e = e;
    L18:
        this.zza.zzaW().zzb().zzc("Failed to get user properties. appId", zzgu.zzl(r5.zza), e);
        return null;
    L11:
        e = e;
        goto L18
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final byte[] zzk(zzbh r10, String r11) {
        Preconditions.checkNotEmpty(r11);
        Preconditions.checkNotNull(r10);
        zzO(r11, true);
        zzpg r0 = this.zza;
        zzgs r1 = r0.zzaW().zzj();
        zzgn r2 = r0.zzs();
        String r3 = r10.zza;
        r1.zzb("Log and bundle. event", r2.zza(r3));
        long r12 = r0.zzba().nanoTime() / 1000000;
        byte[] r6 = (byte[]) r0.zzaX().zzi(new zzit(this, r10, r11)).get();     // Catch: ExecutionException -> L6 InterruptedException -> L8
        if (r6 != null) goto L10;
        r0.zzaW().zzb().zzb("Log and bundle returned null. appId", zzgu.zzl(r11));     // Catch: ExecutionException -> L6 InterruptedException -> L8
        r6 = new byte[0];     // Catch: ExecutionException -> L6 InterruptedException -> L8
    L10:
        long r7 = r0.zzba().nanoTime() / 1000000;     // Catch: ExecutionException -> L6 InterruptedException -> L8
        r0.zzaW().zzj().zzd("Log and bundle processed. event, size, time_ms", r0.zzs().zza(r3), Integer.valueOf(r6.length), Long.valueOf(r7 - r12));     // Catch: ExecutionException -> L6 InterruptedException -> L8
        return r6;
    L8:
        e = e;
    L12:
        zzpg r9 = this.zza;
        r9.zzaW().zzb().zzd("Failed to log and bundle. appId, event, error", zzgu.zzl(r11), r9.zzs().zza(r10.zza), e);
        return null;
    L6:
        e = e;
        goto L12
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzl(long r8, String r10, String r11, String r12) {
        zzd(new zzig(this, r11, r12, r10, r8));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final String zzm(zzr r2) {
        zzN(r2, false);
        return this.zza.zzaq(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzn(zzah r2, zzr r3) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r2.zzc);
        zzN(r3, false);
        zzah r0 = new zzah(r2);
        r0.zza = r3.zza;
        zzd(new zzih(this, r0, r3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzo(zzah r3) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r3.zzc);
        Preconditions.checkNotEmpty(r3.zza);
        zzO(r3.zza, true);
        zzd(new zzii(this, new zzah(r3)));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzp(String r4, String r5, boolean r6, zzr r7) {
        zzN(r7, false);
        String r0 = r7.zza;
        Preconditions.checkNotNull(r0);
        List r42 = (List) this.zza.zzaX().zzh(new zzij(this, r0, r4, r5)).get();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        ArrayList r52 = new ArrayList(r42.size());     // Catch: ExecutionException -> L11 InterruptedException -> L13
        Iterator r43 = r42.iterator();     // Catch: ExecutionException -> L11 InterruptedException -> L13
    L4:
        if (r43.hasNext() == false) goto L17;
        zzpn r02 = (zzpn) r43.next();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        if (r6 == true) goto L15;
        if (zzpp.zzac(r02.zzc) == true) goto L4;
    L15:
        r52.add(new zzpl(r02));     // Catch: ExecutionException -> L11 InterruptedException -> L13
        goto L4
    L17:
        return r52;
    L13:
        e = e;
    L18:
        this.zza.zzaW().zzb().zzc("Failed to query user properties. appId", zzgu.zzl(r7.zza), e);
        return Collections.EMPTY_LIST;
    L11:
        e = e;
        goto L18
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzq(String r3, String r4, String r5, boolean r6) {
        zzO(r3, true);
        List r42 = (List) this.zza.zzaX().zzh(new zzik(this, r3, r4, r5)).get();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        ArrayList r52 = new ArrayList(r42.size());     // Catch: ExecutionException -> L11 InterruptedException -> L13
        Iterator r43 = r42.iterator();     // Catch: ExecutionException -> L11 InterruptedException -> L13
    L4:
        if (r43.hasNext() == false) goto L17;
        zzpn r0 = (zzpn) r43.next();     // Catch: ExecutionException -> L11 InterruptedException -> L13
        if (r6 == true) goto L15;
        if (zzpp.zzac(r0.zzc) == true) goto L4;
    L15:
        r52.add(new zzpl(r0));     // Catch: ExecutionException -> L11 InterruptedException -> L13
        goto L4
    L17:
        return r52;
    L13:
        e = e;
    L18:
        this.zza.zzaW().zzb().zzc("Failed to get user properties as. appId", zzgu.zzl(r3), e);
        return Collections.EMPTY_LIST;
    L11:
        e = e;
        goto L18
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzr(String r3, String r4, zzr r5) {
        zzN(r5, false);
        String r52 = r5.zza;
        Preconditions.checkNotNull(r52);
        return (List) this.zza.zzaX().zzh(new zzil(this, r52, r3, r4)).get();
    L7:
        e = move-exception;
        this.zza.zzaW().zzb().zzb("Failed to get conditional user properties", e);
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzs(String r3, String r4, String r5) {
        zzO(r3, true);
        return (List) this.zza.zzaX().zzh(new zzim(this, r3, r4, r5)).get();
    L5:
        e = move-exception;
        this.zza.zzaW().zzb().zzb("Failed to get conditional user properties as", e);
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzt(zzr r3) {
        String r0 = r3.zza;
        Preconditions.checkNotEmpty(r0);
        zzO(r0, false);
        zzd(new zzio(this, r3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzu(Bundle r3, zzr r4) {
        zzN(r4, false);
        String r0 = r4.zza;
        Preconditions.checkNotNull(r0);
        zzd(new zzjb(this, r3, r0, r4));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzv(zzr r2) {
        Preconditions.checkNotEmpty(r2.zza);
        Preconditions.checkNotNull(r2.zzs);
        zzE(new zzip(this, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final zzao zzw(zzr r5) {
        zzN(r5, false);
        Preconditions.checkNotEmpty(r5.zza);
        Future r0 = this.zza.zzaX().zzi(new zziq(this, r5));
        return (zzao) r0.get(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    L5:
        e = move-exception;
        this.zza.zzaW().zzb().zzc("Failed to get consent. appId", zzgu.zzl(r5.zza), e);
        return new zzao(null);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzx(zzr r6, Bundle r7) {
        zzN(r6, false);
        Preconditions.checkNotNull(r6.zza);
        zzpg r0 = this.zza;
        if (r0.zzd().zzp(null, zzfy.zzaT) == false) goto L22;
        Future r72 = r0.zzaX().zzi(new zziv(this, r6, r7));
        return (List) r72.get(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    L7:
        e = move-exception;
        this.zza.zzaW().zzb().zzc("Failed to get trigger URIs. appId", zzgu.zzl(r6.zza), e);
        return Collections.EMPTY_LIST;
    L22:
        return (List) this.zza.zzaX().zzh(new zziw(this, r6, r7)).get();
    L19:
        e = move-exception;
        this.zza.zzaW().zzb().zzc("Failed to get trigger URIs. appId", zzgu.zzl(r6.zza), e);
        return Collections.EMPTY_LIST;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzy(zzr r2) {
        Preconditions.checkNotEmpty(r2.zza);
        Preconditions.checkNotNull(r2.zzs);
        zzE(new zzjc(this, r2));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzz(zzr r2) {
        Preconditions.checkNotEmpty(r2.zza);
        Preconditions.checkNotNull(r2.zzs);
        zzE(new zzix(this, r2));
    }
}
