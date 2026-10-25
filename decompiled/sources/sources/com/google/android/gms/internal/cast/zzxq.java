package com.google.android.gms.internal.cast;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes4.dex */
final class zzxq implements zzaar {
    private final zzxp zza;

    private zzxq(zzxp r2) {
        byte[] r0 = zzym.zzb;
        this.zza = r2;
        r2.zza = this;
    }

    public static zzxq zza(zzxp r1) {
        Object r0 = r1.zza;
        if (r0 == null) goto L7;
        return (zzxq) r0;
    L7:
        return new zzxq(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzA(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzxr) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzb(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        ((Double) r5.get(r42)).getClass();
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzo(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzr(Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzg(r4, Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzB(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzxp.zzw(r52.zzg(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzn(r52.zzg(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzc(r4, r52.zzg(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzxp.zzw(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzn(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzc(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzC(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzxc) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zzb(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzxp r2 = this.zza;
        r2.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Boolean) r4.get(r32)).getClass();
        r52 = r52 + 1;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzo(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzm(((Boolean) r4.get(r0)).booleanValue() ? 1 : 0);
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zzh(r3, ((Boolean) r4.get(r0)).booleanValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzD(int r6, List r7) {
        int r1 = 0;
        if ((r7 instanceof zzyu) == false) goto L13;
        zzyu r0 = (zzyu) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.zza();
        boolean r3 = r2 instanceof String;
        zzxp r4 = this.zza;
        if (r3 == false) goto L10;
        r4.zzi(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zzj(r6, (zzxk) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zza.zzi(r6, (String) r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzE(int r4, List r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zza.zzj(r4, (zzxk) r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzF(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzxp.zzv(r52.zzg(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzo(r52.zzg(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzd(r4, r52.zzg(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzxp.zzv(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzo(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzd(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzG(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzg(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzp(r52.zzg(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zzg(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzp(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zze(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzH(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzyx) == false) goto L17;
        zzyx r52 = (zzyx) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzr(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzg(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Long) r5.get(r43)).getClass();
        r63 = r63 + 8;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzr(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzg(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzI(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        int r0 = r52.zzg(r42);
        r62 = r62 + zzxp.zzv((r0 >> 31) ^ (r0 + r0));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        int r43 = r52.zzg(r2);
        r3.zzo((r43 >> 31) ^ (r43 + r43));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        zzxp r63 = this.zza;
        int r02 = r52.zzg(r2);
        r63.zzd(r4, (r02 >> 31) ^ (r02 + r02));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r44 = 0;
        int r64 = 0;
    L20:
        if (r44 >= r5.size()) goto L22;
        int r03 = ((Integer) r5.get(r44)).intValue();
        r64 = r64 + zzxp.zzv((r03 >> 31) ^ (r03 + r03));
        r44 = r44 + 1;
        goto L20
    L22:
        r32.zzo(r64);
    L24:
        if (r2 >= r5.size()) goto L37;
        int r45 = ((Integer) r5.get(r2)).intValue();
        r32.zzo((r45 >> 31) ^ (r45 + r45));
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        zzxp r65 = this.zza;
        int r04 = ((Integer) r5.get(r2)).intValue();
        r65.zzd(r4, (r04 >> 31) ^ (r04 + r04));
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzJ(int r7, List r8, boolean r9) {
        int r3 = 0;
        if ((r8 instanceof zzyx) == false) goto L17;
        zzyx r82 = (zzyx) r8;
        if (r9 == false) goto L15;
        zzxp r6 = this.zza;
        r6.zzb(r7, 2);
        int r72 = 0;
        int r92 = 0;
    L8:
        if (r72 >= r82.size()) goto L10;
        long r0 = r82.zze(r72);
        r92 = r92 + zzxp.zzw((r0 >> 63) ^ (r0 + r0));
        r72 = r72 + 1;
        goto L8
    L10:
        r6.zzo(r92);
    L12:
        if (r3 >= r82.size()) goto L29;
        long r02 = r82.zze(r3);
        r6.zzq((r02 >> 63) ^ (r02 + r02));
        r3 = r3 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r3 >= r82.size()) goto L36;
        zzxp r93 = this.zza;
        long r03 = r82.zze(r3);
        r93.zzf(r7, (r03 >> 63) ^ (r03 + r03));
        r3 = r3 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r9 == false) goto L27;
        zzxp r62 = this.zza;
        r62.zzb(r7, 2);
        int r73 = 0;
        int r94 = 0;
    L20:
        if (r73 >= r8.size()) goto L22;
        long r04 = ((Long) r8.get(r73)).longValue();
        r94 = r94 + zzxp.zzw((r04 >> 63) ^ (r04 + r04));
        r73 = r73 + 1;
        goto L20
    L22:
        r62.zzo(r94);
    L24:
        if (r3 >= r8.size()) goto L37;
        long r05 = ((Long) r8.get(r3)).longValue();
        r62.zzq((r05 >> 63) ^ (r05 + r05));
        r3 = r3 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r3 >= r8.size()) goto L38;
        zzxp r95 = this.zza;
        long r06 = ((Long) r8.get(r3)).longValue();
        r95.zzf(r7, (r06 >> 63) ^ (r06 + r06));
        r3 = r3 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzb(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzc(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzd(int r1, long r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zze(int r1, float r2) {
        this.zza.zze(r1, Float.floatToRawIntBits(r2));
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzf(int r1, double r2) {
        this.zza.zzg(r1, Double.doubleToRawLongBits(r2));
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzg(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzh(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzi(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzj(int r1, long r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzk(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzl(int r1, boolean r2) {
        this.zza.zzh(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzm(int r1, String r2) {
        this.zza.zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzn(int r1, zzxk r2) {
        this.zza.zzj(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzo(int r1, int r2) {
        this.zza.zzd(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzp(int r2, int r3) {
        int r0 = r3 + r3;
        zzxp r1 = this.zza;
        r1.zzd(r2, (r3 >> 31) ^ r0);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzq(int r4, long r5) {
        long r0 = r5 + r5;
        zzxp r3 = this.zza;
        r3.zzf(r4, (r5 >> 63) ^ r0);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzr(int r3, Object r4, zzzs r5) {
        zzxp r0 = this.zza;
        zzwz r42 = (zzwz) r4;
        r0.zzb(r3, 2);
        r0.zzo(r42.zzt(r5));
        r5.zzf(r42, this);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzs(int r3, Object r4, zzzs r5) {
        zzxp r0 = this.zza;
        r0.zzb(r3, 3);
        r5.zzf((zzwz) r4, this);
        r0.zzb(r3, 4);
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzu(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzxp.zzw(r52.zzg(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzn(r52.zzg(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzc(r4, r52.zzg(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzxp.zzw(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzn(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzc(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzv(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzye) == false) goto L17;
        zzye r52 = (zzye) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzg(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzp(r52.zzg(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zzg(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzp(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zze(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzw(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzyx) == false) goto L17;
        zzyx r52 = (zzyx) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzxp.zzw(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzq(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzf(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzxp.zzw(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzq(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzf(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzx(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzyx) == false) goto L17;
        zzyx r52 = (zzyx) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzxp.zzw(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzq(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzf(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzxp.zzw(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzq(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzf(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzy(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzyx) == false) goto L17;
        zzyx r52 = (zzyx) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzr(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzg(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Long) r5.get(r43)).getClass();
        r63 = r63 + 8;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzr(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzg(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.cast.zzaar
    public final void zzz(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzxy) == false) goto L17;
        zzxy r52 = (zzxy) r5;
        if (r6 == false) goto L15;
        zzxp r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzg(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzo(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzp(Float.floatToRawIntBits(r52.zzg(r2)));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, Float.floatToRawIntBits(r52.zzg(r2)));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzxp r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Float) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzo(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzp(Float.floatToRawIntBits(((Float) r5.get(r2)).floatValue()));
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zze(r4, Float.floatToRawIntBits(((Float) r5.get(r2)).floatValue()));
        r2 = r2 + 1;
        goto L27
    }
}
