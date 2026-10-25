package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes5.dex */
final class zzeq implements zzhu {
    private final zzep zza;

    private zzeq(zzep r2) {
        byte[] r0 = zzfo.zzb;
        this.zza = r2;
        r2.zza = this;
    }

    public static zzeq zza(zzep r1) {
        zzeq r0 = r1.zza;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new zzeq(r1);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzA(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzga) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzt(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        ((Long) r5.get(r42)).getClass();
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzv(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzj(((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzi(r4, ((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzB(int r2, int r3) {
        int r0 = r3 + r3;
        zzep r1 = this.zza;
        r1.zzu(r2, (r3 >> 31) ^ r0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzC(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        int r0 = r52.zze(r42);
        r62 = r62 + zzep.zzC((r0 >> 31) ^ (r0 + r0));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        int r43 = r52.zze(r2);
        r3.zzv((r43 >> 31) ^ (r43 + r43));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        zzep r63 = this.zza;
        int r02 = r52.zze(r2);
        r63.zzu(r4, (r02 >> 31) ^ (r02 + r02));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r44 = 0;
        int r64 = 0;
    L20:
        if (r44 >= r5.size()) goto L22;
        int r03 = ((Integer) r5.get(r44)).intValue();
        r64 = r64 + zzep.zzC((r03 >> 31) ^ (r03 + r03));
        r44 = r44 + 1;
        goto L20
    L22:
        r32.zzv(r64);
    L24:
        if (r2 >= r5.size()) goto L37;
        int r45 = ((Integer) r5.get(r2)).intValue();
        r32.zzv((r45 >> 31) ^ (r45 + r45));
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        zzep r65 = this.zza;
        int r04 = ((Integer) r5.get(r2)).intValue();
        r65.zzu(r4, (r04 >> 31) ^ (r04 + r04));
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzD(int r4, long r5) {
        long r0 = r5 + r5;
        zzep r3 = this.zza;
        r3.zzw(r4, (r5 >> 63) ^ r0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzE(int r8, List r9, boolean r10) {
        if ((r9 instanceof zzga) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9);
        if (r10 == false) goto L8;
        this.zza.zzt(r8, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r2 = 0;
        if (r10 == false) goto L20;
        zzep r7 = this.zza;
        r7.zzt(r8, 2);
        int r82 = 0;
        int r102 = 0;
    L13:
        if (r82 >= r9.size()) goto L15;
        long r3 = ((Long) r9.get(r82)).longValue();
        r102 = r102 + zzep.zzD((r3 >> 63) ^ (r3 + r3));
        r82 = r82 + 1;
        goto L13
    L15:
        r7.zzv(r102);
    L17:
        if (r2 >= r9.size()) goto L22;
        long r32 = ((Long) r9.get(r2)).longValue();
        r7.zzx((r32 >> 63) ^ (r32 + r32));
        r2 = r2 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r2 >= r9.size()) goto L26;
        zzep r103 = this.zza;
        long r33 = ((Long) r9.get(r2)).longValue();
        r103.zzw(r8, (r33 >> 63) ^ (r33 + r33));
        r2 = r2 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    @Deprecated
    public final void zzF(int r2) {
        this.zza.zzt(r2, 3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzG(int r1, String r2) {
        this.zza.zzr(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzH(int r6, List r7) {
        int r1 = 0;
        if ((r7 instanceof zzfx) == false) goto L13;
        zzfx r0 = (zzfx) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.zza();
        boolean r3 = r2 instanceof String;
        zzep r4 = this.zza;
        if (r3 == false) goto L10;
        r4.zzr(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zze(r6, (zzei) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zza.zzr(r6, (String) r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzI(int r1, int r2) {
        this.zza.zzu(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzJ(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzep.zzC(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzv(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzu(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzep.zzC(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzv(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzv(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzu(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzK(int r1, long r2) {
        this.zza.zzw(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzL(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzga) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzt(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        r62 = r62 + zzep.zzD(((Long) r5.get(r42)).longValue());
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzv(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzx(((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzw(r4, ((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzb(int r1, boolean r2) {
        this.zza.zzd(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzc(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzdy) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zzt(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzep r2 = this.zza;
        r2.zzt(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Boolean) r4.get(r32)).getClass();
        r52 = r52 + 1;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzv(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzb(((Boolean) r4.get(r0)).booleanValue() ? 1 : 0);
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zzd(r3, ((Boolean) r4.get(r0)).booleanValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzd(int r1, zzei r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zze(int r4, List r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zza.zze(r4, (zzei) r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzf(int r1, double r2) {
        this.zza.zzi(r1, Double.doubleToRawLongBits(r2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzg(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzer) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzt(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        ((Double) r5.get(r42)).getClass();
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzv(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzj(Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzi(r4, Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    @Deprecated
    public final void zzh(int r2) {
        this.zza.zzt(r2, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzi(int r1, int r2) {
        this.zza.zzk(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzj(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzep.zzD(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzl(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzk(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzep.zzD(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzv(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzl(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzk(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzk(int r1, int r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzl(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzh(r52.zze(r2));
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
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzv(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzh(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzg(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzm(int r1, long r2) {
        this.zza.zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzn(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzga) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzt(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        ((Long) r5.get(r42)).getClass();
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzv(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzj(((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzi(r4, ((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzo(int r1, float r2) {
        this.zza.zzg(r1, Float.floatToRawIntBits(r2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzp(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzfb) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zzt(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzep r2 = this.zza;
        r2.zzt(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Float) r4.get(r32)).getClass();
        r52 = r52 + 4;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzv(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzh(Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zzg(r3, Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzq(int r2, Object r3, zzgv r4) {
        zzep r1 = this.zza;
        r1.zzt(r2, 3);
        r4.zzi((zzgl) r3, r1.zza);
        r1.zzt(r2, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzr(int r1, int r2) {
        this.zza.zzk(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzs(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzep.zzD(r52.zze(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzl(r52.zze(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzk(r4, r52.zze(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzep.zzD(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzv(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzl(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzk(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzt(int r1, long r2) {
        this.zza.zzw(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzu(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzga) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzt(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L13:
        if (r42 >= r5.size()) goto L15;
        r62 = r62 + zzep.zzD(((Long) r5.get(r42)).longValue());
        r42 = r42 + 1;
        goto L13
    L15:
        r3.zzv(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzx(((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r5.size()) goto L26;
        this.zza.zzw(r4, ((Long) r5.get(r0)).longValue());
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzv(int r1, Object r2, zzgv r3) {
        zzep r0 = this.zza;
        r0.zzn(r1, (zzgl) r2, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzx(int r1, int r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzy(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzfj) == false) goto L17;
        zzfj r52 = (zzfj) r5;
        if (r6 == false) goto L15;
        zzep r3 = this.zza;
        r3.zzt(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zze(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzv(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzh(r52.zze(r2));
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
        zzep r32 = this.zza;
        r32.zzt(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        ((Integer) r5.get(r43)).getClass();
        r63 = r63 + 4;
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzv(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzh(((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        this.zza.zzg(r4, ((Integer) r5.get(r2)).intValue());
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.play_billing.zzhu
    public final void zzz(int r1, long r2) {
        this.zza.zzi(r1, r2);
    }
}
