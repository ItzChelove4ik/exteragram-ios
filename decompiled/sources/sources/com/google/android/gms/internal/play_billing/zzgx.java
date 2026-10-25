package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes.dex */
abstract class zzgx {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzhh zzb = null;

    static {
        int r0 = zzgs.$r8$clinit;
        zzb = new zzhj();
    }

    public static void zzA(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzC(r1, r2, r4);
        return;
    }

    public static void zzB(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzE(r1, r2, r4);
        return;
    }

    public static void zzC(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzJ(r1, r2, r4);
        return;
    }

    public static void zzD(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzL(r1, r2, r4);
        return;
    }

    public static boolean zzE(Object r2, Object r3) {
        if (r2 != r3) goto L5;
        return true;
    L5:
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.equals(r3) == false) goto L9;
        return true;
    }

    public static int zza(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzfj) == false) goto L11;
        zzfj r52 = (zzfj) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzep.zzD(r52.zze(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzep.zzD(((Integer) r5.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzb(int r0, List r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * (zzep.zzC(r0 << 3) + 4);
    }

    public static int zzc(List r0) {
        return r0.size() * 4;
    }

    public static int zzd(int r0, List r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * (zzep.zzC(r0 << 3) + 8);
    }

    public static int zze(List r0) {
        return r0.size() * 8;
    }

    public static int zzf(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzfj) == false) goto L11;
        zzfj r52 = (zzfj) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzep.zzD(r52.zze(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzep.zzD(((Integer) r5.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzg(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzga) == false) goto L12;
        zzga r52 = (zzga) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzep.zzD(((Long) r5.get(r1)).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static int zzh(int r0, Object r1, zzgv r2) {
        return zzep.zzC(r0 << 3) + zzep.zzA((zzgl) r1, r2);
    }

    public static int zzi(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzfj) == false) goto L11;
        zzfj r52 = (zzfj) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        int r3 = r52.zze(r1);
        r2 = r2 + zzep.zzC((r3 >> 31) ^ (r3 + r3));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        int r32 = ((Integer) r5.get(r1)).intValue();
        r22 = r22 + zzep.zzC((r32 >> 31) ^ (r32 + r32));
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzj(List r8) {
        int r0 = r8.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r8 instanceof zzga) == false) goto L12;
        zzga r82 = (zzga) r8;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        long r3 = ((Long) r8.get(r1)).longValue();
        r2 = r2 + zzep.zzD((r3 >> 63) ^ (r3 + r3));
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static int zzk(List r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzfj) == false) goto L11;
        zzfj r42 = (zzfj) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzep.zzC(r42.zze(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzep.zzC(((Integer) r4.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzl(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzga) == false) goto L12;
        zzga r52 = (zzga) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzep.zzD(((Long) r5.get(r1)).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static zzhh zzm() {
        return zzb;
    }

    public static Object zzn(Object r2, int r3, int r4, Object r5, zzhh r6) {
        if (r5 != null) goto L4;
        r5 = r6.zza(r2);
    L4:
        ((zzhi) r5).zzj(r3 << 3, Long.valueOf(r4));
        return r5;
    }

    public static void zzo(zzev r0, Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    public static void zzp(zzhh r1, Object r2, Object r3) {
        zzfi r22 = (zzfi) r2;
        zzhi r12 = r22.zzc;
        zzhi r32 = ((zzfi) r3).zzc;
        if (zzhi.zzc().equals(r32) == false) goto L5;
    L8:
        r22.zzc = r12;
        return;
    L5:
        if (zzhi.zzc().equals(r12) == false) goto L7;
        r12 = zzhi.zze(r12, r32);
        goto L8
    L7:
        r12.zzd(r32);
        goto L8
    }

    public static void zzq(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzc(r1, r2, r4);
        return;
    }

    public static void zzr(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzg(r1, r2, r4);
        return;
    }

    public static void zzs(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzj(r1, r2, r4);
        return;
    }

    public static void zzt(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzl(r1, r2, r4);
        return;
    }

    public static void zzu(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzn(r1, r2, r4);
        return;
    }

    public static void zzv(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzp(r1, r2, r4);
        return;
    }

    public static void zzw(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzs(r1, r2, r4);
        return;
    }

    public static void zzx(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzu(r1, r2, r4);
        return;
    }

    public static void zzy(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzy(r1, r2, r4);
        return;
    }

    public static void zzz(int r1, List r2, zzhu r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzA(r1, r2, r4);
        return;
    }
}
