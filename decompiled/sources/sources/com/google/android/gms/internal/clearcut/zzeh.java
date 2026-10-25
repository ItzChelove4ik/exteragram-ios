package com.google.android.gms.internal.clearcut;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes4.dex */
abstract class zzeh {
    private static final Class<?> zzoh = null;
    private static final zzex<?, ?> zzoi = null;
    private static final zzex<?, ?> zzoj = null;
    private static final zzex<?, ?> zzok = null;

    static {
        zzoh = zzdp();
        zzoi = zzd(false);
        zzoj = zzd(true);
        zzok = new zzez();
    }

    public static int zza(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzdc) == false) goto L12;
        zzdc r52 = (zzdc) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zze(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    private static <UT, UB> UB zza(int r2, int r3, UB r4, zzex<UT, UB> r5) {
        if (r4 != null) goto L4;
        r4 = r5.zzdz();
    L4:
        r5.zza(r4, r2, r3);
        return r4;
    }

    public static <UT, UB> UB zza(int r6, List<Integer> r7, zzck<?> r8, UB r9, zzex<UT, UB> r10) {
        if (r8 != null) goto L5;
        return r9;
    L5:
        if ((r7 instanceof RandomAccess) == false) goto L18;
        int r0 = r7.size();
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r0) goto L15;
        Integer r3 = r7.get(r1);
        int r4 = r3.intValue();
        if (r8.zzb(r4) == null) goto L13;
        if (r1 == r2) goto L12;
        r7.set(r2, r3);
    L12:
        r2 = r2 + 1;
    L14:
        r1 = r1 + 1;
        goto L7
    L13:
        r9 = (UB) zza(r6, r4, r9, r10);
        goto L14
    L15:
        if (r2 == r0) goto L17;
        r7.subList(r2, r0).clear();
    L17:
        return r9;
    L18:
        Iterator<Integer> r72 = r7.iterator();
    L20:
        if (r72.hasNext() == false) goto L24;
        int r02 = r72.next().intValue();
        if (r8.zzb(r02) != null) goto L20;
        r9 = (UB) zza(r6, r02, r9, r10);
        r72.remove();
        goto L20
    L24:
        return r9;
    }

    public static void zza(int r1, List<String> r2, zzfr r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2);
        return;
    }

    public static void zza(int r1, List<?> r2, zzfr r3, zzef r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2, r4);
        return;
    }

    public static void zza(int r1, List<Double> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzg(r1, r2, r4);
        return;
    }

    public static <T, FT> void zza(zzbu<FT> r1, T r2, T r3) {
        zzby<FT> r32 = r1.zza(r3);
        if (r32.isEmpty() == true) goto L6;
        r1.zzb(r2).zza(r32);
        return;
    }

    public static <T> void zza(zzdj r1, T r2, T r3, long r4) {
        zzfd.zza(r2, r4, r1.zzb(zzfd.zzo(r2, r4), zzfd.zzo(r3, r4)));
    }

    public static <T, UT, UB> void zza(zzex<UT, UB> r1, T r2, T r3) {
        r1.zze(r2, r1.zzg(r1.zzq(r2), r1.zzq(r3)));
    }

    public static int zzb(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzdc) == false) goto L12;
        zzdc r52 = (zzdc) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzf(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static void zzb(int r1, List<zzbb> r2, zzfr r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2);
        return;
    }

    public static void zzb(int r1, List<?> r2, zzfr r3, zzef r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2, r4);
        return;
    }

    public static void zzb(int r1, List<Float> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzf(r1, r2, r4);
        return;
    }

    public static int zzc(int r0, Object r1, zzef r2) {
        return zzbn.zzb(r0, (zzdo) r1, r2);
    }

    public static int zzc(int r4, List<?> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r42 = zzbn.zzr(r4) * r0;
        if ((r5 instanceof zzcx) == false) goto L16;
        zzcx r52 = (zzcx) r5;
    L8:
        if (r1 >= r0) goto L15;
        Object r2 = r52.getRaw(r1);
        if ((r2 instanceof zzbb) == false) goto L13;
        int r22 = zzbn.zzb((zzbb) r2);
    L12:
        r42 = r42 + r22;
        r1 = r1 + 1;
        goto L8
    L13:
        r22 = zzbn.zzh((String) r2);
        goto L12
    L15:
        return r42;
    L16:
        if (r1 >= r0) goto L23;
        Object r23 = r5.get(r1);
        if ((r23 instanceof zzbb) == false) goto L21;
        int r24 = zzbn.zzb((zzbb) r23);
    L20:
        r42 = r42 + r24;
        r1 = r1 + 1;
        goto L16
    L21:
        r24 = zzbn.zzh((String) r23);
        goto L20
    L23:
        return r42;
    }

    public static int zzc(int r3, List<?> r4, zzef r5) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r32 = zzbn.zzr(r3) * r0;
    L6:
        if (r1 >= r0) goto L8;
        r32 = r32 + zzbn.zzb((zzdo) r4.get(r1), r5);
        r1 = r1 + 1;
        goto L6
    L8:
        return r32;
    }

    public static int zzc(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzdc) == false) goto L12;
        zzdc r52 = (zzdc) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzg(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static void zzc(int r1, List<Long> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzc(r1, r2, r4);
        return;
    }

    public static boolean zzc(int r10, int r11, int r12) {
        if (r11 >= 40) goto L5;
        return true;
    L5:
        long r2 = r11 - r10;
        long r102 = r12;
        if ((r2 + 10) > (((2 * r102) + 3) + ((r102 + 3) * 3))) goto L8;
        return true;
    L8:
        return false;
    }

    public static int zzd(int r2, List<zzbb> r3) {
        int r0 = r3.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r02 = r0 * zzbn.zzr(r2);
    L7:
        if (r1 >= r3.size()) goto L9;
        r02 = r02 + zzbn.zzb(r3.get(r1));
        r1 = r1 + 1;
        goto L7
    L9:
        return r02;
    }

    public static int zzd(int r4, List<zzdo> r5, zzef r6) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r2 = 0;
    L6:
        if (r1 >= r0) goto L8;
        r2 = r2 + zzbn.zzc(r4, r5.get(r1), r6);
        r1 = r1 + 1;
        goto L6
    L8:
        return r2;
    }

    public static int zzd(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzch) == false) goto L12;
        zzch r42 = (zzch) r4;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzx(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    private static zzex<?, ?> zzd(boolean r3) {
        Class<?> r1 = zzdq();     // Catch: Throwable -> L8
        if (r1 != null) goto L6;
        return null;
    L6:
        return (zzex) r1.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(r3)});
    L8:
        return null;
    }

    public static void zzd(int r1, List<Long> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzd(r1, r2, r4);
        return;
    }

    public static boolean zzd(Object r0, Object r1) {
        if (r0 == r1) goto L9;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.equals(r1) == true) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    public static zzex<?, ?> zzdm() {
        return zzoi;
    }

    public static zzex<?, ?> zzdn() {
        return zzoj;
    }

    public static zzex<?, ?> zzdo() {
        return zzok;
    }

    private static Class<?> zzdp() {
        return Class.forName("com.google.protobuf.GeneratedMessage");
    L4:
        return null;
    }

    private static Class<?> zzdq() {
        return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
    L4:
        return null;
    }

    public static int zze(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzch) == false) goto L12;
        zzch r42 = (zzch) r4;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzs(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static void zze(int r1, List<Long> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzn(r1, r2, r4);
        return;
    }

    public static int zzf(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzch) == false) goto L12;
        zzch r42 = (zzch) r4;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzt(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static void zzf(int r1, List<Long> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zze(r1, r2, r4);
        return;
    }

    public static void zzf(Class<?> r1) {
        if (zzcg.class.isAssignableFrom(r1) == true) goto L11;
        Class<?> r0 = zzoh;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAssignableFrom(r1) == true) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        return;
    L13:
        return;
    }

    public static int zzg(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzch) == false) goto L12;
        zzch r42 = (zzch) r4;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzbn.zzu(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static void zzg(int r1, List<Long> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzl(r1, r2, r4);
        return;
    }

    public static int zzh(List<?> r0) {
        return r0.size() << 2;
    }

    public static void zzh(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2, r4);
        return;
    }

    public static int zzi(List<?> r0) {
        return r0.size() << 3;
    }

    public static void zzi(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzj(r1, r2, r4);
        return;
    }

    public static int zzj(List<?> r0) {
        return r0.size();
    }

    public static void zzj(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzm(r1, r2, r4);
        return;
    }

    public static void zzk(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2, r4);
        return;
    }

    public static void zzl(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzk(r1, r2, r4);
        return;
    }

    public static void zzm(int r1, List<Integer> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzh(r1, r2, r4);
        return;
    }

    public static void zzn(int r1, List<Boolean> r2, zzfr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzi(r1, r2, r4);
        return;
    }

    public static int zzo(int r0, List<Long> r1, boolean r2) {
        if (r1.size() != 0) goto L7;
        return 0;
    L7:
        return zza(r1) + (r1.size() * zzbn.zzr(r0));
    }

    public static int zzp(int r0, List<Long> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzb(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzq(int r0, List<Long> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzc(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzr(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzd(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzs(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zze(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzt(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzf(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzu(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzg(r1) + (r22 * zzbn.zzr(r0));
    }

    public static int zzv(int r0, List<?> r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L6;
        return 0;
    L6:
        return r12 * zzbn.zzj(r0, 0);
    }

    public static int zzw(int r2, List<?> r3, boolean r4) {
        int r32 = r3.size();
        if (r32 != 0) goto L7;
        return 0;
    L7:
        return r32 * zzbn.zzg(r2, 0);
    }

    public static int zzx(int r0, List<?> r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * zzbn.zzc(r0, true);
    }
}
