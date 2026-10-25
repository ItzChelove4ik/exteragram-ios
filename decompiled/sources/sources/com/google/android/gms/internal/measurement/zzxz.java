package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.logging.Level;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzxz implements zzyi, zzzd {
    private static final String zza = null;
    private final Level zzb;
    private final long zzc;
    private zzxy zzd;
    private zzyc zze;
    private zzyq zzf;
    private zzaaf zzg;
    private Object[] zzh;

    static {
        zza = new String();
    }

    public zzxz(Level r3, boolean r4) {
        long r0 = zzaad.zzk();
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzg = null;
        this.zzh = null;
        zzabr.zza(r3, "level");
        this.zzb = r3;
        this.zzc = r0;
    }

    public abstract zzabn zza();

    public boolean zzb(zzyd r7) {
        zzxy r0 = this.zzd;
        if (r0 == null) goto L15;
        if (r7 == null) goto L9;
        zzyq r72 = zzyq.zzc(zzyq.zzc(zzxn.zza(r0, r7, this.zzc), zzxk.zza(this.zzd, r7)), zzyt.zza(this.zzd, r7));
        this.zzf = r72;
        if (r72 != zzyq.zzc) goto L9;
        return false;
    L9:
        zzxy r73 = this.zzd;
        zzyl r02 = zzxx.zzi;
        zzyv r74 = (zzyv) r73.zzd(r02);
        if (r74 == null) goto L15;
        zzxy r2 = this.zzd;
        if (r2 == null) goto L14;
        r2.zzf(r02);
    L14:
        zzzj r22 = zzl();
        zzyl r3 = zzxx.zza;
        zzm(r3, new zzyg((Throwable) r22.zzd(r3), r74, zzabq.zzb(zzxz.class, r74.zza(), 1)));
    L15:
        return true;
    }

    public abstract zzxi zzc();

    public abstract zzyi zzd();

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final Level zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final long zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final zzyc zzg() {
        zzyc r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("cannot request log site information prior to postProcess()");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final zzaaf zzh() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final Object[] zzi() {
        if (this.zzg == null) goto L5;
        boolean r0 = true;
    L6:
        zzabr.zzc(r0, "cannot get arguments unless a template context exists");
        Object[] r2 = this.zzh;
        if (r2 == null) goto L9;
        return r2;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("cannot get arguments before calling log()");
        return null;
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final Object zzj() {
        if (this.zzg != null) goto L5;
        boolean r0 = true;
    L6:
        zzabr.zzc(r0, "cannot get literal argument if a template context exists");
        Object[] r3 = this.zzh;
        if (r3 != null) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("cannot get literal argument before calling log()");
        return null;
    L9:
        return r3[0];
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final boolean zzk() {
        zzxy r2 = this.zzd;
        if (r2 != null) goto L5;
        return false;
    L5:
        if (Boolean.TRUE.equals(r2.zzd(zzxx.zzg)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzzd
    public final zzzj zzl() {
        zzxy r0 = this.zzd;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return zzzj.zzg();
    }

    public final void zzm(zzyl r2, Object r3) {
        if (this.zzd != null) goto L5;
        this.zzd = new zzxy();
    L5:
        this.zzd.zze(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzyi
    public final zzyi zzn(String r7, String r8, int r9, String r10) {
        zzyc r72 = zzyc.zza;
        zzyb r0 = new zzyb("com/google/android/libraries/phenotype/client/Phlogger", "logInternal", 44, "Phlogger.java", null);
        if (this.zze != null) goto L6;
        this.zze = r0;
    L6:
        return zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzyi
    public final zzyi zzo(Throwable r3) {
        zzyl r0 = zzxx.zza;
        zzabr.zza(r0, "metadata key");
        if (r3 == null) goto L6;
        zzm(r0, r3);
    L6:
        return zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzyi
    public final void zzp(String r9, Object[] r10) {
        boolean r1 = true;
        if (this.zze != null) goto L5;
        this.zze = zzaad.zzb().zzb(zzxz.class, 1);
    L5:
        zzyd r0 = this.zze;
        int r3 = 0;
        if (r0 == zzyc.zza) goto L17;
        zzxy r2 = this.zzd;
        if (r2 != null) goto L10;
    L18:
        boolean r22 = zzb(r0);
        zzyq r4 = this.zzf;
        if (r4 == null) goto L30;
        int r02 = zzyp.zza(r4, r0, this.zzd);
        if (r22 == false) goto L26;
        if (r02 <= 0) goto L26;
        zzxy r42 = this.zzd;
        if (r42 == null) goto L26;
        r42.zze(zzxx.zze, Integer.valueOf(r02));
    L26:
        if (r02 >= 0) goto L29;
        r1 = false;
    L29:
        r22 = r22 & r1;
    L30:
        if (r22 == false) goto L50;
        Object[] r102 = Arrays.copyOf(r10, r10.length);
        this.zzh = r102;
    L33:
        if (r3 >= r102.length) goto L36;
        Object r03 = r102[r3];
        r3 = r3 + 1;
        goto L33
    L36:
        if (r9 == zza) goto L38;
        this.zzg = new zzaaf(zza(), r9);
    L38:
        zzabe r92 = zzaad.zzi();
        if (r92.zzc() == true) goto L44;
        zzzj r103 = zzl();
        zzyl r04 = zzxx.zzh;
        zzabe r104 = (zzabe) r103.zzd(r04);
        if (r104 == null) goto L43;
        r92 = r92.zzd(r104);
    L43:
        zzm(r04, r92);
    L44:
        zzc().zzc(this);
        return;
    L50:
        return;
    L10:
        if (r2.zza() <= 0) goto L18;
        zzabr.zza(r0, "logSiteKey");
        int r43 = r2.zza();
        int r5 = 0;
    L12:
        if (r5 >= r43) goto L18;
        if (zzxx.zzf.equals(r2.zzb(r5)) == false) goto L16;
        r0 = zzyu.zza(r0, r2.zzc(r5));
    L16:
        r5 = r5 + 1;
        goto L12
    L17:
        r0 = null;
        goto L18
    }
}
