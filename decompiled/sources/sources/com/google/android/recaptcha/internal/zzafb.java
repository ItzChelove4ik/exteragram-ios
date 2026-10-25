package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes5.dex */
final class zzafb {
    private final zzafa zza;

    private zzafb(zzafa r1) {
        this.zza = r1;
        r1.zza = this;
    }

    public static zzafb zza(zzafa r1) {
        Object r0 = r1.zza;
        if (r0 == null) goto L7;
        return (zzafb) r0;
    L7:
        return new zzafb(r1);
    }

    public final void zzA(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzagq) == false) goto L17;
        zzagq r52 = (zzagq) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
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
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Long) r5.get(r43)).getClass();
        r63 = r63 + 8;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
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

    public final void zzB(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzafo) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zza(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzafa r2 = this.zza;
        r2.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Float) r4.get(r32)).getClass();
        r52 = r52 + 4;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzn(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzo(Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zzd(r3, Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L20
    }

    public final void zzC(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzafc) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zza(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        ((Double) r5.get(r42)).getClass();
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzn(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzq(Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzf(r4, Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L20
    }

    public final void zzD(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzafa.zzv(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzm(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzb(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzafa.zzv(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzm(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzb(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    public final void zzE(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzaei) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zza(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzafa r2 = this.zza;
        r2.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Boolean) r4.get(r32)).getClass();
        r52 = r52 + 1;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzn(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzl(((Boolean) r4.get(r0)).booleanValue() ? 1 : 0);
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zzg(r3, ((Boolean) r4.get(r0)).booleanValue());
        r0 = r0 + 1;
        goto L20
    }

    public final void zzF(int r6, List r7) {
        int r1 = 0;
        if ((r7 instanceof zzagn) == false) goto L13;
        zzagn r0 = (zzagn) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.zzc();
        boolean r3 = r2 instanceof String;
        zzafa r4 = this.zza;
        if (r3 == false) goto L10;
        r4.zzh(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zzi(r6, (zzaer) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zza.zzh(r6, (String) r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    public final void zzG(int r4, List r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zza.zzi(r4, (zzaer) r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    public final void zzH(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzafa.zzu(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzn(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzc(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzafa.zzu(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
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

    public final void zzI(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzf(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzo(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzd(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
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

    public final void zzJ(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzagq) == false) goto L17;
        zzagq r52 = (zzagq) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
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
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Long) r5.get(r43)).getClass();
        r63 = r63 + 8;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
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

    public final void zzK(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        int r0 = r52.zzf(r42);
        r62 = r62 + zzafa.zzu((r0 >> 31) ^ (r0 + r0));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        int r43 = r52.zzf(r2);
        r3.zzn((r43 >> 31) ^ (r43 + r43));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        zzafa r63 = this.zza;
        int r02 = r52.zzf(r2);
        r63.zzc(r4, (r02 >> 31) ^ (r02 + r02));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r44 = 0;
        int r64 = 0;
    L20:
        if (r44 >= r5.size()) goto L22;
        int r03 = ((Integer) r5.get(r44)).intValue();
        r64 = r64 + zzafa.zzu((r03 >> 31) ^ (r03 + r03));
        r44 = r44 + 1;
        goto L20
    L22:
        r32.zzn(r64);
    L24:
        if (r2 >= r5.size()) goto L37;
        int r45 = ((Integer) r5.get(r2)).intValue();
        r32.zzn((r45 >> 31) ^ (r45 + r45));
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        zzafa r65 = this.zza;
        int r04 = ((Integer) r5.get(r2)).intValue();
        r65.zzc(r4, (r04 >> 31) ^ (r04 + r04));
        r2 = r2 + 1;
        goto L27
    }

    public final void zzL(int r7, List r8, boolean r9) {
        int r3 = 0;
        if ((r8 instanceof zzagq) == false) goto L17;
        zzagq r82 = (zzagq) r8;
        if (r9 == false) goto L15;
        zzafa r6 = this.zza;
        r6.zza(r7, 2);
        int r72 = 0;
        int r92 = 0;
    L8:
        if (r72 >= r82.size()) goto L10;
        long r0 = r82.zze(r72);
        r92 = r92 + zzafa.zzv((r0 >> 63) ^ (r0 + r0));
        r72 = r72 + 1;
        goto L8
    L10:
        r6.zzn(r92);
    L12:
        if (r3 >= r82.size()) goto L29;
        long r02 = r82.zze(r3);
        r6.zzp((r02 >> 63) ^ (r02 + r02));
        r3 = r3 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r3 >= r82.size()) goto L36;
        zzafa r93 = this.zza;
        long r03 = r82.zze(r3);
        r93.zze(r7, (r03 >> 63) ^ (r03 + r03));
        r3 = r3 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r9 == false) goto L27;
        zzafa r62 = this.zza;
        r62.zza(r7, 2);
        int r73 = 0;
        int r94 = 0;
    L20:
        if (r73 >= r8.size()) goto L22;
        long r04 = ((Long) r8.get(r73)).longValue();
        r94 = r94 + zzafa.zzv((r04 >> 63) ^ (r04 + r04));
        r73 = r73 + 1;
        goto L20
    L22:
        r62.zzn(r94);
    L24:
        if (r3 >= r8.size()) goto L37;
        long r05 = ((Long) r8.get(r3)).longValue();
        r62.zzp((r05 >> 63) ^ (r05 + r05));
        r3 = r3 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r3 >= r8.size()) goto L38;
        zzafa r95 = this.zza;
        long r06 = ((Long) r8.get(r3)).longValue();
        r95.zze(r7, (r06 >> 63) ^ (r06 + r06));
        r3 = r3 + 1;
        goto L27
    }

    public final void zzb(int r1, int r2) {
        this.zza.zzd(r1, r2);
    }

    public final void zzc(int r1, long r2) {
        this.zza.zze(r1, r2);
    }

    public final void zzd(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    public final void zze(int r1, float r2) {
        this.zza.zzd(r1, Float.floatToRawIntBits(r2));
    }

    public final void zzf(int r1, double r2) {
        this.zza.zzf(r1, Double.doubleToRawLongBits(r2));
    }

    public final void zzg(int r1, int r2) {
        this.zza.zzb(r1, r2);
    }

    public final void zzh(int r1, long r2) {
        this.zza.zze(r1, r2);
    }

    public final void zzi(int r1, int r2) {
        this.zza.zzb(r1, r2);
    }

    public final void zzj(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    public final void zzk(int r1, int r2) {
        this.zza.zzd(r1, r2);
    }

    public final void zzl(int r1, boolean r2) {
        this.zza.zzg(r1, r2);
    }

    public final void zzm(int r1, String r2) {
        this.zza.zzh(r1, r2);
    }

    public final void zzn(int r1, zzaer r2) {
        this.zza.zzi(r1, r2);
    }

    public final void zzo(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    public final void zzp(int r2, int r3) {
        int r0 = r3 + r3;
        zzafa r1 = this.zza;
        r1.zzc(r2, (r3 >> 31) ^ r0);
    }

    public final void zzq(int r4, long r5) {
        long r0 = r5 + r5;
        zzafa r3 = this.zza;
        r3.zze(r4, (r5 >> 63) ^ r0);
    }

    public final void zzr(int r3, Object r4, zzahn r5) {
        zzafa r0 = this.zza;
        zzaec r42 = (zzaec) r4;
        r0.zza(r3, 2);
        r0.zzn(r42.zzx(r5));
        r5.zzf(r42, this);
    }

    public final void zzs(int r3, Object r4, zzahn r5) {
        zzafa r0 = this.zza;
        r0.zza(r3, 3);
        r5.zzf((zzaec) r4, this);
        r0.zza(r3, 4);
    }

    @Deprecated
    public final void zzt(int r2) {
        this.zza.zza(r2, 3);
    }

    @Deprecated
    public final void zzu(int r2) {
        this.zza.zza(r2, 4);
    }

    public final void zzw(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzafa.zzv(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzm(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzb(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzafa.zzv(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzm(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzb(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    public final void zzx(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzafw) == false) goto L17;
        zzafw r52 = (zzafw) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzf(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzo(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzd(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
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

    public final void zzy(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzagq) == false) goto L17;
        zzagq r52 = (zzagq) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzafa.zzv(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzp(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzafa.zzv(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzp(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zze(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }

    public final void zzz(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzagq) == false) goto L17;
        zzagq r52 = (zzagq) r5;
        if (r6 == false) goto L15;
        zzafa r3 = this.zza;
        r3.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzafa.zzv(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzn(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzp(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzafa r32 = this.zza;
        r32.zza(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzafa.zzv(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzn(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzp(((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zze(r4, ((Long) r5.get(r2)).longValue());
        r2 = r2 + 1;
        goto L27
    }
}
