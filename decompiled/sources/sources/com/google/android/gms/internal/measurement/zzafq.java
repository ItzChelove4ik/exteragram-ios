package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes4.dex */
abstract class zzafq {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzafz zzb = null;

    static {
        int r0 = zzacf.$r8$clinit;
        zzb = new zzagb();
    }

    @Deprecated
    public static int zzA(int r0, zzafc r1, zzafp r2) {
        int r02 = zzada.zzE(r0 << 3);
        return (r02 + r02) + ((zzacb) r1).zzcf(r2);
    }

    public static zzafz zzB() {
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

    public static void zzD(zzadg r0, Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    public static void zzE(zzafz r1, Object r2, Object r3) {
        zzadu r22 = (zzadu) r2;
        zzaga r12 = r22.zzc;
        zzaga r32 = ((zzadu) r3).zzc;
        if (zzaga.zza().equals(r32) == false) goto L5;
    L8:
        r22.zzc = r12;
        return;
    L5:
        if (zzaga.zza().equals(r12) == false) goto L7;
        r12 = zzaga.zzc(r12, r32);
        goto L8
    L7:
        r12.zzl(r32);
        goto L8
    }

    public static Object zzF(Object r6, int r7, List r8, zzadz r9, Object r10, zzafz r11) {
        if (r9 != null) goto L5;
        return r10;
    L5:
        if ((r8 instanceof RandomAccess) == false) goto L18;
        int r0 = r8.size();
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r0) goto L15;
        Integer r3 = (Integer) r8.get(r1);
        int r4 = r3.intValue();
        if (r9.zza(r4) == false) goto L13;
        if (r1 == r2) goto L12;
        r8.set(r2, r3);
    L12:
        r2 = r2 + 1;
    L14:
        r1 = r1 + 1;
        goto L7
    L13:
        r10 = zzG(r6, r7, r4, r10, r11);
        goto L14
    L15:
        if (r2 == r0) goto L17;
        r8.subList(r2, r0).clear();
    L17:
        return r10;
    L18:
        Iterator r82 = r8.iterator();
    L20:
        if (r82.hasNext() == false) goto L24;
        int r02 = ((Integer) r82.next()).intValue();
        if (r9.zza(r02) == true) goto L20;
        r10 = zzG(r6, r7, r02, r10, r11);
        r82.remove();
        goto L20
    L24:
        return r10;
    }

    public static Object zzG(Object r2, int r3, int r4, Object r5, zzafz r6) {
        if (r5 != null) goto L4;
        r5 = r6.zzh(r2);
    L4:
        r6.zza(r5, r3, r4);
        return r5;
    }

    public static void zza(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzC(r1, r2, r4);
        return;
    }

    public static void zzb(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzB(r1, r2, r4);
        return;
    }

    public static void zzc(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzy(r1, r2, r4);
        return;
    }

    public static void zzd(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzz(r1, r2, r4);
        return;
    }

    public static void zze(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzL(r1, r2, r4);
        return;
    }

    public static void zzf(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzA(r1, r2, r4);
        return;
    }

    public static void zzg(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzJ(r1, r2, r4);
        return;
    }

    public static void zzh(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzw(r1, r2, r4);
        return;
    }

    public static void zzi(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzH(r1, r2, r4);
        return;
    }

    public static void zzj(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzK(r1, r2, r4);
        return;
    }

    public static void zzk(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzx(r1, r2, r4);
        return;
    }

    public static void zzl(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzI(r1, r2, r4);
        return;
    }

    public static void zzm(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzD(r1, r2, r4);
        return;
    }

    public static void zzn(int r1, List r2, zzago r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzE(r1, r2, r4);
        return;
    }

    public static int zzo(List r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzaeq) == false) goto L11;
        zzaeq r52 = (zzaeq) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzada.zzF(r52.zzc(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzada.zzF(((Long) r5.get(r1)).longValue());
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
        if ((r5 instanceof zzaeq) == false) goto L11;
        zzaeq r52 = (zzaeq) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzada.zzF(r52.zzc(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzada.zzF(((Long) r5.get(r1)).longValue());
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
        if ((r8 instanceof zzaeq) == false) goto L11;
        zzaeq r82 = (zzaeq) r8;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        long r4 = r82.zzc(r1);
        r2 = r2 + zzada.zzF((r4 >> 63) ^ (r4 + r4));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        long r42 = ((Long) r8.get(r1)).longValue();
        r22 = r22 + zzada.zzF((r42 >> 63) ^ (r42 + r42));
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
        if ((r5 instanceof zzadv) == false) goto L11;
        zzadv r52 = (zzadv) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzada.zzF(r52.zzf(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzada.zzF(((Integer) r5.get(r1)).intValue());
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
        if ((r5 instanceof zzadv) == false) goto L11;
        zzadv r52 = (zzadv) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzada.zzF(r52.zzf(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzada.zzF(((Integer) r5.get(r1)).intValue());
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
        if ((r4 instanceof zzadv) == false) goto L11;
        zzadv r42 = (zzadv) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzada.zzE(r42.zzf(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzada.zzE(((Integer) r4.get(r1)).intValue());
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
        if ((r5 instanceof zzadv) == false) goto L11;
        zzadv r52 = (zzadv) r5;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        int r3 = r52.zzf(r1);
        r2 = r2 + zzada.zzE((r3 >> 31) ^ (r3 + r3));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        int r32 = ((Integer) r5.get(r1)).intValue();
        r22 = r22 + zzada.zzE((r32 >> 31) ^ (r32 + r32));
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
        return r12 * (zzada.zzE(r0 << 3) + 4);
    }

    public static int zzx(List r0) {
        return r0.size() * 8;
    }

    public static int zzy(int r0, List r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * (zzada.zzE(r0 << 3) + 8);
    }

    public static int zzz(int r0, Object r1, zzafp r2) {
        int r02 = zzada.zzE(r0 << 3);
        int r12 = ((zzacb) r1).zzcf(r2);
        return r02 + (zzada.zzE(r12) + r12);
    }
}
