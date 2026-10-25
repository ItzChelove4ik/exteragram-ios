package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzair;
import com.google.android.gms.internal.measurement.zzhv;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzit implements Callable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzjd zzc;

    public zzit(zzjd r1, zzbh r2, String r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzjd r2 = this.zzc;
        r2.zzL().zzaa();
        zzlp r22 = r2.zzL().zzn();
        r22.zzg();
        zzic r3 = r22.zzu;
        zzic.zzL();
        zzbh r4 = this.zza;
        Preconditions.checkNotNull(r4);
        String r6 = this.zzb;
        Preconditions.checkNotEmpty(r6);
        String r0 = r4.zza;
        if ("_iap".equals(r0) == false) goto L5;
    L8:
        zzpg r02 = r22.zzg;
        com.google.android.gms.internal.measurement.zzhz r5 = com.google.android.gms.internal.measurement.zzib.zzi();
        r02.zzj().zzb();
        zzh r8 = r02.zzj().zzu(r6);     // Catch: Throwable -> L14
        if (r8 != null) goto L17;
        r22.zzu.zzaW().zzj().zzb("Log and bundle not available. package_name", r6);     // Catch: Throwable -> L14
        byte[] r1 = new byte[0];     // Catch: Throwable -> L14
    L12:
        r02.zzj().zzd();
        return r1;
    L17:
        if (r8.zzD() == true) goto L19;
        r22.zzu.zzaW().zzj().zzb("Log and bundle disabled. package_name", r6);     // Catch: Throwable -> L14
        r1 = new byte[0];     // Catch: Throwable -> L14
        goto L12
    L19:
        com.google.android.gms.internal.measurement.zzic r10 = com.google.android.gms.internal.measurement.zzid.zzaE();     // Catch: Throwable -> L14
        r10.zza(1);     // Catch: Throwable -> L14
        r10.zzC("android");     // Catch: Throwable -> L14
        if (TextUtils.isEmpty(r8.zzc()) == true) goto L23;
        r10.zzL(r8.zzc());     // Catch: Throwable -> L14
    L23:
        if (TextUtils.isEmpty(r8.zzv()) == true) goto L26;
        r10.zzJ((String) Preconditions.checkNotNull(r8.zzv()));     // Catch: Throwable -> L14
    L26:
        if (TextUtils.isEmpty(r8.zzr()) == true) goto L29;
        r10.zzM((String) Preconditions.checkNotNull(r8.zzr()));     // Catch: Throwable -> L14
    L29:
        if (r8.zzt() == (-2147483648L)) goto L31;
        r10.zzaj((int) r8.zzt());     // Catch: Throwable -> L14
    L31:
        r10.zzN(r8.zzx());     // Catch: Throwable -> L14
        r10.zzar(r8.zzB());     // Catch: Throwable -> L14
        String r12 = r8.zzf();     // Catch: Throwable -> L14
        if (TextUtils.isEmpty(r12) == true) goto L34;
        r10.zzad(r12);     // Catch: Throwable -> L14
    L34:
        r10.zzay(r8.zzak());     // Catch: Throwable -> L14
        zzjl r122 = r22.zzg.zzB(r6);     // Catch: Throwable -> L14
        r10.zzY(r8.zzz());     // Catch: Throwable -> L14
        if (r3.zzB() == true) goto L37;
    L43:
        r10.zzat(r122.zzk());     // Catch: Throwable -> L14
        zzjk r32 = zzjk.zza;     // Catch: Throwable -> L14
        if (r122.zzo(r32) == true) goto L46;
    L66:
        zzic r03 = r22.zzu;     // Catch: Throwable -> L14
        r03.zzu().zzw();     // Catch: Throwable -> L14
        r10.zzF(Build.MODEL);     // Catch: Throwable -> L14
        r03.zzu().zzw();     // Catch: Throwable -> L14
        r10.zzE(Build.VERSION.RELEASE);     // Catch: Throwable -> L14
        r10.zzI((int) r03.zzu().zzb());     // Catch: Throwable -> L14
        r10.zzH(r03.zzu().zzc());     // Catch: Throwable -> L14
    L73:
        e = move-exception;
        r22.zzu.zzaW().zzj().zzb("app instance id encryption failed", e.getMessage());     // Catch: Throwable -> L14
        byte[] r7 = new byte[0];     // Catch: Throwable -> L14
        zzpg r04 = r22.zzg;
    L65:
        r04.zzj().zzd();
        return r7;
    L68:
        if (r122.zzo(zzjk.zzb) == false) goto L76;
        if (r8.zzd() == null) goto L76;
        r10.zzW(zzlp.zzc((String) Preconditions.checkNotNull(r8.zzd()), Long.toString(r4.zzd)));     // Catch: Throwable -> L14 SecurityException -> L73
    L76:
        if (TextUtils.isEmpty(r8.zzl()) == true) goto L78;
        r10.zzah((String) Preconditions.checkNotNull(r8.zzl()));     // Catch: Throwable -> L14
    L78:
        String r13 = r8.zzc();     // Catch: Throwable -> L14
        zzpg r05 = r22.zzg;     // Catch: Throwable -> L14
        List r33 = r05.zzj().zzn(r13);     // Catch: Throwable -> L14
        Iterator r123 = r33.iterator();     // Catch: Throwable -> L14
    L80:
        if (r123.hasNext() == false) goto L85;
        zzpn r14 = (zzpn) r123.next();     // Catch: Throwable -> L14
        if ("_lte".equals(r14.zzc) == false) goto L80;
    L87:
        if (r14 != null) goto L89;
    L90:
        zzpn r124 = new zzpn(r13, "auto", "_lte", r22.zzu.zzba().currentTimeMillis(), 0L);     // Catch: Throwable -> L14
        r33.add(r124);     // Catch: Throwable -> L14
        r05.zzj().zzl(r124);     // Catch: Throwable -> L14
    L91:
        com.google.android.gms.internal.measurement.zziu[] r72 = new com.google.android.gms.internal.measurement.zziu[r33.size()];     // Catch: Throwable -> L14
        int r125 = 0;
    L93:
        if (r125 >= r33.size()) goto L95;
        com.google.android.gms.internal.measurement.zzit r132 = com.google.android.gms.internal.measurement.zziu.zzm();     // Catch: Throwable -> L14
        r132.zzb(((zzpn) r33.get(r125)).zzc);     // Catch: Throwable -> L14
        r132.zza(((zzpn) r33.get(r125)).zzd);     // Catch: Throwable -> L14
        r05.zzp().zzc(r132, ((zzpn) r33.get(r125)).zze);     // Catch: Throwable -> L14
        r72[r125] = (com.google.android.gms.internal.measurement.zziu) r132.zzbd();     // Catch: Throwable -> L14
        r125 = r125 + 1;     // Catch: Throwable -> L14
        goto L93
    L95:
        r10.zzq(Arrays.asList(r72));     // Catch: Throwable -> L14
        zzpg r34 = r22.zzg;     // Catch: Throwable -> L14
        r34.zzI(r8, r10);     // Catch: Throwable -> L14
        r34.zzJ(r8, r10);     // Catch: Throwable -> L14
        zzgv r73 = zzgv.zza(r4);     // Catch: Throwable -> L14
        zzic r126 = r22.zzu;     // Catch: Throwable -> L14
        zzpp r133 = r126.zzk();     // Catch: Throwable -> L14
        Bundle r142 = r73.zze;     // Catch: Throwable -> L14
        r133.zzK(r142, r05.zzj().zzV(r6));     // Catch: Throwable -> L14
        r126.zzk().zzI(r73, r126.zzc().zzd(r6));     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzic r15 = r10;
        r142.putLong("_c", 1);     // Catch: Throwable -> L14
        r126.zzaW().zzj().zza("Marking in-app purchase as real-time");     // Catch: Throwable -> L14
        r142.putLong("_r", 1);     // Catch: Throwable -> L14
        String r11 = r4.zzc;     // Catch: Throwable -> L14
        r142.putString("_o", r11);     // Catch: Throwable -> L14
        if (r126.zzk().zzad(r15.zzK(), r8.zzay()) == false) goto L98;
        r126.zzk().zzO(r142, "_dbg", 1L);     // Catch: Throwable -> L14
        r126.zzk().zzO(r142, "_r", 1L);     // Catch: Throwable -> L14
    L98:
        zzaw r16 = r05.zzj();     // Catch: Throwable -> L14
        String r74 = r4.zza;     // Catch: Throwable -> L14
        zzbd r17 = r16.zzf(r6, r74);     // Catch: Throwable -> L14
        if (r17 != null) goto L102;
        zzic r25 = r126;
        com.google.android.gms.internal.measurement.zzhz r27 = r5;
        zzh r29 = r8;
        Bundle r30 = r142;
        zzpg r28 = r05;
        String r26 = r11;
        Object r24 = null;
        zzbd r52 = new zzbd(r6, r74, 0, 0, 0, r4.zzd, 0, null, null, null, null);     // Catch: Throwable -> L14
        r15 = r15;
        long r82 = 0;
    L101:
        zzbd r18 = r52;
        r28.zzj().zzh(r18);     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzic r9 = r15;
        zzic r62 = r22.zzu;     // Catch: Throwable -> L14
        long r102 = r4.zzd;     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzhz r42 = r27;
        zzh r182 = r29;
        zzbc r53 = new zzbc(r62, r26, r6, r74, r102, 0, r82, r30);     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzhr r83 = com.google.android.gms.internal.measurement.zzhs.zzp();     // Catch: Throwable -> L14
        r83.zzo(r53.zzd);     // Catch: Throwable -> L14
        r83.zzl(r53.zzb);     // Catch: Throwable -> L14
        r83.zzq(r53.zzf);     // Catch: Throwable -> L14
        zzbf r54 = r53.zzg;     // Catch: Throwable -> L14
        zzbe r92 = new zzbe(r54);     // Catch: Throwable -> L14
    L105:
        if (r92.hasNext() == false) goto L109;
        String r103 = r92.zza();     // Catch: Throwable -> L14
        zzhv r112 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L14
        r112.zzb(r103);     // Catch: Throwable -> L14
        Object r104 = r54.zza(r103);     // Catch: Throwable -> L14
        if (r104 == null) goto L105;
        r28.zzp().zzd(r112, r104);     // Catch: Throwable -> L14
        r83.zzg(r112);     // Catch: Throwable -> L14
        goto L105
    L109:
        r9.zzg(r83);     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzie r55 = com.google.android.gms.internal.measurement.zzig.zza();     // Catch: Throwable -> L14
        com.google.android.gms.internal.measurement.zzht r93 = com.google.android.gms.internal.measurement.zzhu.zza();     // Catch: Throwable -> L14
        r93.zzb(r18.zzc);     // Catch: Throwable -> L14
        r93.zza(r74);     // Catch: Throwable -> L14
        r55.zza(r93);     // Catch: Throwable -> L14
        r9.zzap(r55);     // Catch: Throwable -> L14
        r9.zzaf(r28.zzm().zzb(r182.zzc(), Collections.EMPTY_LIST, r9.zzk(), Long.valueOf(r83.zzn()), Long.valueOf(r83.zzn()), false));     // Catch: Throwable -> L14
        if (r83.zzm() == false) goto L112;
        r9.zzv(r83.zzn());     // Catch: Throwable -> L14
        r9.zzx(r83.zzn());     // Catch: Throwable -> L14
    L112:
        long r75 = r182.zzp();     // Catch: Throwable -> L14
        if (r75 == 0) goto L115;
        r9.zzA(r75);     // Catch: Throwable -> L14
    L115:
        long r94 = r182.zzn();     // Catch: Throwable -> L14
        if (r94 == 0) goto L118;
        r9.zzy(r94);     // Catch: Throwable -> L14
    L120:
        String r19 = r182.zzh();     // Catch: Throwable -> L14
        zzair.zza();     // Catch: Throwable -> L14
        if (r25.zzc().zzp(r6, zzfy.zzaM) == false) goto L124;
        if (r19 == null) goto L124;
        r9.zzau(r19);     // Catch: Throwable -> L14
    L124:
        r182.zzL();     // Catch: Throwable -> L14
        r9.zzZ((int) r182.zzG());     // Catch: Throwable -> L14
        r25.zzc().zzi();     // Catch: Throwable -> L14
        r9.zzO(161000);     // Catch: Throwable -> L14
        r9.zzs(r25.zzba().currentTimeMillis());     // Catch: Throwable -> L14
        r9.zzae(true);     // Catch: Throwable -> L14
        r34.zzS(r9.zzK(), r9);     // Catch: Throwable -> L14
        r42.zze(r9);     // Catch: Throwable -> L14
        r182.zzo(r9.zzu());     // Catch: Throwable -> L14
        r182.zzq(r9.zzw());     // Catch: Throwable -> L14
        r28.zzj().zzv(r182, false, false);     // Catch: Throwable -> L14
        r28.zzj().zzc();     // Catch: Throwable -> L14
        r28.zzj().zzd();
        return r28.zzp().zzv(((com.google.android.gms.internal.measurement.zzib) r42.zzbd()).zzcd());
    L128:
        e = move-exception;
        r22.zzu.zzaW().zzb().zzc("Data loss. Failed to bundle and serialize. appId", zzgu.zzl(r6), e);
        return r24;
    L118:
        if (r75 == 0) goto L120;
        r9.zzy(r75);     // Catch: Throwable -> L14
        goto L120
    L102:
        r28 = r05;
        r27 = r5;
        r29 = r8;
        r26 = r11;
        r25 = r126;
        r30 = r142;
        r24 = null;
        r82 = r17.zzf;     // Catch: Throwable -> L14
        r52 = r17.zza(r4.zzd);     // Catch: Throwable -> L14
        goto L101
    L89:
        if (r14.zze != null) goto L91;
    L85:
        r14 = null;
        goto L87
    L46:
        if (r8.zzac() == false) goto L66;
        zznn r06 = r02.zzq();     // Catch: Throwable -> L14
        if (r122.zzo(r32) == true) goto L50;
    L53:
        Pair r07 = new Pair(_UrlKt.FRAGMENT_ENCODE_SET, Boolean.FALSE);     // Catch: Throwable -> L14
    L55:
        if (r8.zzac() == false) goto L66;
        if (TextUtils.isEmpty((CharSequence) r07.first) == true) goto L66;
        r10.zzQ(zzlp.zzc((String) r07.first, Long.toString(r4.zzd)));     // Catch: Throwable -> L14 SecurityException -> L62
        Object r08 = r07.second;     // Catch: Throwable -> L14
        if (r08 == null) goto L66;
        r10.zzT(((Boolean) r08).booleanValue());     // Catch: Throwable -> L14
        goto L66
    L62:
        e = move-exception;
        r22.zzu.zzaW().zzj().zzb("Resettable device id encryption failed", e.getMessage());     // Catch: Throwable -> L14
        r7 = new byte[0];     // Catch: Throwable -> L14
        r04 = r22.zzg;
        goto L65
    L50:
        if (r8.zzac() == false) goto L53;
        r07 = r06.zzd(r8.zzc());     // Catch: Throwable -> L14
        goto L55
    L37:
        if (r22.zzu.zzc().zzC(r10.zzK()) == false) goto L43;
        if (r122.zzo(zzjk.zza) == false) goto L43;
        if (TextUtils.isEmpty(null) == true) goto L43;
        r10.zzam(null);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r22.zzg.zzj().zzd();
        throw th;
    L5:
        if ("_iapx".equals(r0) == true) goto L8;
        r22.zzu.zzaW().zzj().zzc("Generating a payload for this event is not available. package_name, event_name", r6, r0);
        return null;
    }
}
