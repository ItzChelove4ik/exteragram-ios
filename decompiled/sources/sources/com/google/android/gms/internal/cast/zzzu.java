package com.google.android.gms.internal.cast;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.List;

/* loaded from: classes4.dex */
abstract class zzzu {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzaad zzb = null;

    static {
        int r0 = zzxb.$r8$clinit;
        zzb = new zzaaf();
    }

    @Deprecated
    public static int zzA(int r0, zzzi r1, zzzs r2) {
        int r02 = zzxp.zzv(r0 << 3);
        return (r02 + r02) + ((zzwz) r1).zzt(r2);
    }

    public static zzaad zzB() {
        return zzb;
    }

    public static boolean zzC(Object r2, Object r3) {
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

    public static void zzD(zzxs r0, Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    public static void zzE(zzaad r1, Object r2, Object r3) {
        zzyd r22 = (zzyd) r2;
        zzaae r12 = r22.zzc;
        zzaae r32 = ((zzyd) r3).zzc;
        if (zzaae.zza().equals(r32) == false) goto L5;
    L8:
        r22.zzc = r12;
        return;
    L5:
        if (zzaae.zza().equals(r12) == false) goto L7;
        r12 = zzaae.zzb(r12, r32);
        goto L8
    L7:
        r12.zzh(r32);
        goto L8
    }

    public static void zza(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzA(r1, r2, r4);
        return;
    }

    public static void zzb(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzz(r1, r2, r4);
        return;
    }

    public static void zzc(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzw(r1, r2, r4);
        return;
    }

    public static void zzd(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzx(r1, r2, r4);
        return;
    }

    public static void zze(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzJ(r1, r2, r4);
        return;
    }

    public static void zzf(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzy(r1, r2, r4);
        return;
    }

    public static void zzg(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzH(r1, r2, r4);
        return;
    }

    public static void zzh(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzu(r1, r2, r4);
        return;
    }

    public static void zzi(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzF(r1, r2, r4);
        return;
    }

    public static void zzj(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzI(r1, r2, r4);
        return;
    }

    public static void zzk(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzv(r1, r2, r4);
        return;
    }

    public static void zzl(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzG(r1, r2, r4);
        return;
    }

    public static void zzm(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzB(r1, r2, r4);
        return;
    }

    public static void zzn(int r1, List r2, zzaar r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzC(r1, r2, r4);
        return;
    }

    public static int zzo(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzyx) == false) goto L11;
        zzyx r52 = (zzyx) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzxp.zzw(r52.zze(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzxp.zzw(((Long) r5.get(r1)).longValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzp(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzyx) == false) goto L11;
        zzyx r52 = (zzyx) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzxp.zzw(r52.zze(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzxp.zzw(((Long) r5.get(r1)).longValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzq(List r8) {
        int r0 = r8.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r8 instanceof zzyx) == false) goto L11;
        zzyx r82 = (zzyx) r8;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        long r4 = r82.zze(r1);
        r2 = r2 + zzxp.zzw((r4 >> 63) ^ (r4 + r4));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        long r42 = ((Long) r8.get(r1)).longValue();
        r22 = r22 + zzxp.zzw((r42 >> 63) ^ (r42 + r42));
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzr(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzye) == false) goto L11;
        zzye r52 = (zzye) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzxp.zzw(r52.zzg(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzxp.zzw(((Integer) r5.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzs(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzye) == false) goto L11;
        zzye r52 = (zzye) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzxp.zzw(r52.zzg(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzxp.zzw(((Integer) r5.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzt(List r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzye) == false) goto L11;
        zzye r42 = (zzye) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzxp.zzv(r42.zzg(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzxp.zzv(((Integer) r4.get(r1)).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzu(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzye) == false) goto L11;
        zzye r52 = (zzye) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        int r3 = r52.zzg(r1);
        r2 = r2 + zzxp.zzv((r3 >> 31) ^ (r3 + r3));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        int r32 = ((Integer) r5.get(r1)).intValue();
        r22 = r22 + zzxp.zzv((r32 >> 31) ^ (r32 + r32));
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static int zzv(List r0) {
        return r0.size() * 4;
    }

    public static int zzw(int r0, List r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * (zzxp.zzv(r0 << 3) + 4);
    }

    public static int zzx(List r0) {
        return r0.size() * 8;
    }

    public static int zzy(int r0, List r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * (zzxp.zzv(r0 << 3) + 8);
    }

    public static int zzz(int r0, Object r1, zzzs r2) {
        int r02 = zzxp.zzv(r0 << 3);
        int r12 = ((zzwz) r1).zzt(r2);
        return r02 + (zzxp.zzv(r12) + r12);
    }
}
