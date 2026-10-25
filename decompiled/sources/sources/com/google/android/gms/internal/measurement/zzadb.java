package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzadb implements zzago {
    private final zzada zza;

    private zzadb(zzada r1) {
        this.zza = r1;
        r1.zza = this;
    }

    public static zzadb zza(zzada r1) {
        Object r0 = r1.zza;
        if (r0 == null) goto L7;
        return (zzadb) r0;
    L7:
        return new zzadb(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzA(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzaeq) == false) goto L17;
        zzaeq r52 = (zzaeq) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzc(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzu(r52.zzc(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzg(r4, r52.zzc(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
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
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzu(((Long) r5.get(r2)).longValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzB(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzadm) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zzb(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzada r2 = this.zza;
        r2.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L13:
        if (r32 >= r4.size()) goto L15;
        ((Float) r4.get(r32)).getClass();
        r52 = r52 + 4;
        r32 = r32 + 1;
        goto L13
    L15:
        r2.zzr(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzs(Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L17
    L22:
        return;
    L20:
        if (r0 >= r4.size()) goto L26;
        this.zza.zze(r3, Float.floatToRawIntBits(((Float) r4.get(r0)).floatValue()));
        r0 = r0 + 1;
        goto L20
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzC(int r4, List r5, boolean r6) {
        if ((r5 instanceof zzadc) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == false) goto L8;
        this.zza.zzb(r4, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r6 == false) goto L20;
        zzada r3 = this.zza;
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
        r3.zzr(r62);
    L17:
        if (r0 >= r5.size()) goto L22;
        r3.zzu(Double.doubleToRawLongBits(((Double) r5.get(r0)).doubleValue()));
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzD(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzada.zzF(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzq(r52.zzf(r2));
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
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzada.zzF(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzq(((Integer) r5.get(r2)).intValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzE(int r3, List r4, boolean r5) {
        if ((r4 instanceof zzaci) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        if (r5 == false) goto L8;
        this.zza.zzb(r3, 2);
        throw null;
    L8:
        throw null;
    L9:
        int r0 = 0;
        if (r5 == false) goto L20;
        zzada r2 = this.zza;
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
        r2.zzr(r52);
    L17:
        if (r0 >= r4.size()) goto L22;
        r2.zzp(((Boolean) r4.get(r0)).booleanValue() ? 1 : 0);
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzF(int r6, List r7) {
        int r1 = 0;
        if ((r7 instanceof zzaen) == false) goto L13;
        zzaen r0 = (zzaen) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.zzc();
        boolean r3 = r2 instanceof String;
        zzada r4 = this.zza;
        if (r3 == false) goto L10;
        r4.zzi(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zzj(r6, (zzacr) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zza.zzi(r6, (String) r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzG(int r4, List r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zza.zzj(r4, (zzacr) r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzH(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzada.zzE(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzr(r52.zzf(r2));
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
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzada.zzE(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzr(((Integer) r5.get(r2)).intValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzI(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzf(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzs(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
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
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzs(((Integer) r5.get(r2)).intValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzJ(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzaeq) == false) goto L17;
        zzaeq r52 = (zzaeq) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzc(r42);
        r62 = r62 + 8;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzu(r52.zzc(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzg(r4, r52.zzc(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
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
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzu(((Long) r5.get(r2)).longValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzK(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        int r0 = r52.zzf(r42);
        r62 = r62 + zzada.zzE((r0 >> 31) ^ (r0 + r0));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        int r43 = r52.zzf(r2);
        r3.zzr((r43 >> 31) ^ (r43 + r43));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        zzada r63 = this.zza;
        int r02 = r52.zzf(r2);
        r63.zzd(r4, (r02 >> 31) ^ (r02 + r02));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r44 = 0;
        int r64 = 0;
    L20:
        if (r44 >= r5.size()) goto L22;
        int r03 = ((Integer) r5.get(r44)).intValue();
        r64 = r64 + zzada.zzE((r03 >> 31) ^ (r03 + r03));
        r44 = r44 + 1;
        goto L20
    L22:
        r32.zzr(r64);
    L24:
        if (r2 >= r5.size()) goto L37;
        int r45 = ((Integer) r5.get(r2)).intValue();
        r32.zzr((r45 >> 31) ^ (r45 + r45));
        r2 = r2 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r2 >= r5.size()) goto L38;
        zzada r65 = this.zza;
        int r04 = ((Integer) r5.get(r2)).intValue();
        r65.zzd(r4, (r04 >> 31) ^ (r04 + r04));
        r2 = r2 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzL(int r7, List r8, boolean r9) {
        int r3 = 0;
        if ((r8 instanceof zzaeq) == false) goto L17;
        zzaeq r82 = (zzaeq) r8;
        if (r9 == false) goto L15;
        zzada r6 = this.zza;
        r6.zzb(r7, 2);
        int r72 = 0;
        int r92 = 0;
    L8:
        if (r72 >= r82.size()) goto L10;
        long r0 = r82.zzc(r72);
        r92 = r92 + zzada.zzF((r0 >> 63) ^ (r0 + r0));
        r72 = r72 + 1;
        goto L8
    L10:
        r6.zzr(r92);
    L12:
        if (r3 >= r82.size()) goto L29;
        long r02 = r82.zzc(r3);
        r6.zzt((r02 >> 63) ^ (r02 + r02));
        r3 = r3 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r3 >= r82.size()) goto L36;
        zzada r93 = this.zza;
        long r03 = r82.zzc(r3);
        r93.zzf(r7, (r03 >> 63) ^ (r03 + r03));
        r3 = r3 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r9 == false) goto L27;
        zzada r62 = this.zza;
        r62.zzb(r7, 2);
        int r73 = 0;
        int r94 = 0;
    L20:
        if (r73 >= r8.size()) goto L22;
        long r04 = ((Long) r8.get(r73)).longValue();
        r94 = r94 + zzada.zzF((r04 >> 63) ^ (r04 + r04));
        r73 = r73 + 1;
        goto L20
    L22:
        r62.zzr(r94);
    L24:
        if (r3 >= r8.size()) goto L37;
        long r05 = ((Long) r8.get(r3)).longValue();
        r62.zzt((r05 >> 63) ^ (r05 + r05));
        r3 = r3 + 1;
        goto L24
    L37:
        return;
    L27:
        if (r3 >= r8.size()) goto L38;
        zzada r95 = this.zza;
        long r06 = ((Long) r8.get(r3)).longValue();
        r95.zzf(r7, (r06 >> 63) ^ (r06 + r06));
        r3 = r3 + 1;
        goto L27
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzM(int r5, zzaeu r6, Map r7) {
        Iterator r72 = r7.entrySet().iterator();
    L4:
        if (r72.hasNext() == false) goto L6;
        Map.Entry r0 = (Map.Entry) r72.next();
        zzada r1 = this.zza;
        r1.zzb(r5, 2);
        r1.zzr(zzaev.zzc(r6, r0.getKey(), r0.getValue()));
        zzaev.zzb(r1, r6, r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzb(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzc(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzd(int r1, long r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zze(int r1, float r2) {
        this.zza.zze(r1, Float.floatToRawIntBits(r2));
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzf(int r1, double r2) {
        this.zza.zzg(r1, Double.doubleToRawLongBits(r2));
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzg(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzh(int r1, long r2) {
        this.zza.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzi(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzj(int r1, long r2) {
        this.zza.zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzk(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzl(int r1, boolean r2) {
        this.zza.zzh(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzm(int r1, String r2) {
        this.zza.zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzn(int r1, zzacr r2) {
        this.zza.zzj(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzo(int r1, int r2) {
        this.zza.zzd(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzp(int r2, int r3) {
        int r0 = r3 + r3;
        zzada r1 = this.zza;
        r1.zzd(r2, (r3 >> 31) ^ r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzq(int r4, long r5) {
        long r0 = r5 + r5;
        zzada r3 = this.zza;
        r3.zzf(r4, (r5 >> 63) ^ r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzr(int r3, Object r4, zzafp r5) {
        zzada r0 = this.zza;
        zzacb r42 = (zzacb) r4;
        r0.zzb(r3, 2);
        r0.zzr(r42.zzcf(r5));
        r5.zzf(r42, this);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzs(int r3, Object r4, zzafp r5) {
        zzada r0 = this.zza;
        r0.zzb(r3, 3);
        r5.zzf((zzacb) r4, this);
        r0.zzb(r3, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    @Deprecated
    public final void zzt(int r2) {
        this.zza.zzb(r2, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    @Deprecated
    public final void zzu(int r2) {
        this.zza.zzb(r2, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzw(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzada.zzF(r52.zzf(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzq(r52.zzf(r2));
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
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzada.zzF(((Integer) r5.get(r43)).intValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzq(((Integer) r5.get(r2)).intValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzx(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzadv) == false) goto L17;
        zzadv r52 = (zzadv) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r52.zzf(r42);
        r62 = r62 + 4;
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzs(r52.zzf(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zze(r4, r52.zzf(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
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
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzs(((Integer) r5.get(r2)).intValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzy(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzaeq) == false) goto L17;
        zzaeq r52 = (zzaeq) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzada.zzF(r52.zzc(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzt(r52.zzc(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzf(r4, r52.zzc(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzada.zzF(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzt(((Long) r5.get(r2)).longValue());
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

    @Override // com.google.android.gms.internal.measurement.zzago
    public final void zzz(int r4, List r5, boolean r6) {
        int r2 = 0;
        if ((r5 instanceof zzaeq) == false) goto L17;
        zzaeq r52 = (zzaeq) r5;
        if (r6 == false) goto L15;
        zzada r3 = this.zza;
        r3.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L8:
        if (r42 >= r52.size()) goto L10;
        r62 = r62 + zzada.zzF(r52.zzc(r42));
        r42 = r42 + 1;
        goto L8
    L10:
        r3.zzr(r62);
    L12:
        if (r2 >= r52.size()) goto L29;
        r3.zzt(r52.zzc(r2));
        r2 = r2 + 1;
        goto L12
    L29:
        return;
    L15:
        if (r2 >= r52.size()) goto L36;
        this.zza.zzf(r4, r52.zzc(r2));
        r2 = r2 + 1;
        goto L15
    L36:
        return;
    L17:
        if (r6 == false) goto L27;
        zzada r32 = this.zza;
        r32.zzb(r4, 2);
        int r43 = 0;
        int r63 = 0;
    L20:
        if (r43 >= r5.size()) goto L22;
        r63 = r63 + zzada.zzF(((Long) r5.get(r43)).longValue());
        r43 = r43 + 1;
        goto L20
    L22:
        r32.zzr(r63);
    L24:
        if (r2 >= r5.size()) goto L37;
        r32.zzt(((Long) r5.get(r2)).longValue());
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
}
