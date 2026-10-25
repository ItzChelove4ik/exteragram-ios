package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
abstract class zzle {
    private static final Class<?> zza = null;
    private static final zzlu<?, ?> zzb = null;
    private static final zzlu<?, ?> zzc = null;
    private static final zzlu<?, ?> zzd = null;

    static {
        zza = zzd();
        zzb = zza(false);
        zzc = zza(true);
        zzd = new zzlw();
    }

    public static int zza(int r0, Object r1, zzlc r2) {
        return zzii.zzb(r0, (zzkk) r1, r2);
    }

    public static int zza(int r4, List<?> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r42 = zzii.zze(r4) * r0;
        if ((r5 instanceof zzjv) == false) goto L16;
        zzjv r52 = (zzjv) r5;
    L8:
        if (r1 >= r0) goto L15;
        Object r2 = r52.zzb(r1);
        if ((r2 instanceof zzht) == false) goto L13;
        int r22 = zzii.zzb((zzht) r2);
    L12:
        r42 = r42 + r22;
        r1 = r1 + 1;
        goto L8
    L13:
        r22 = zzii.zzb((String) r2);
        goto L12
    L15:
        return r42;
    L16:
        if (r1 >= r0) goto L23;
        Object r23 = r5.get(r1);
        if ((r23 instanceof zzht) == false) goto L21;
        int r24 = zzii.zzb((zzht) r23);
    L20:
        r42 = r42 + r24;
        r1 = r1 + 1;
        goto L16
    L21:
        r24 = zzii.zzb((String) r23);
        goto L20
    L23:
        return r42;
    }

    public static int zza(int r3, List<?> r4, zzlc r5) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r32 = zzii.zze(r3) * r0;
    L6:
        if (r1 >= r0) goto L8;
        r32 = r32 + zzii.zza((zzkk) r4.get(r1), r5);
        r1 = r1 + 1;
        goto L6
    L8:
        return r32;
    }

    public static int zza(int r0, List<Long> r1, boolean r2) {
        if (r1.size() != 0) goto L7;
        return 0;
    L7:
        return zza(r1) + (r1.size() * zzii.zze(r0));
    }

    public static int zza(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzjy) == false) goto L12;
        zzjy r52 = (zzjy) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzii.zzd(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static zzlu<?, ?> zza() {
        return zzb;
    }

    private static zzlu<?, ?> zza(boolean r3) {
        Class<?> r1 = zze();     // Catch: Throwable -> L8
        if (r1 != null) goto L6;
        return null;
    L6:
        return (zzlu) r1.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(r3)});
    L8:
        return null;
    }

    public static <UT, UB> UB zza(int r2, int r3, UB r4, zzlu<UT, UB> r5) {
        if (r4 != null) goto L4;
        r4 = r5.zza();
    L4:
        r5.zza(r4, r2, r3);
        return r4;
    }

    public static <UT, UB> UB zza(int r6, List<Integer> r7, zzjg r8, UB r9, zzlu<UT, UB> r10) {
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
        if (r8.zza(r4) == false) goto L13;
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
        if (r8.zza(r02) == true) goto L20;
        r9 = (UB) zza(r6, r02, r9, r10);
        r72.remove();
        goto L20
    L24:
        return r9;
    }

    public static void zza(int r1, List<String> r2, zzmr r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2);
        return;
    }

    public static void zza(int r1, List<?> r2, zzmr r3, zzlc r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2, r4);
        return;
    }

    public static void zza(int r1, List<Double> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzg(r1, r2, r4);
        return;
    }

    public static <T, FT extends zziw<FT>> void zza(zziq<FT> r1, T r2, T r3) {
        zziu<T> r32 = r1.zza(r3);
        if (r32.zza.isEmpty() == true) goto L6;
        r1.zzb(r2).zza(r32);
        return;
    }

    public static <T> void zza(zzkh r1, T r2, T r3, long r4) {
        zzma.zza(r2, r4, r1.zza(zzma.zzf(r2, r4), zzma.zzf(r3, r4)));
    }

    public static <T, UT, UB> void zza(zzlu<UT, UB> r1, T r2, T r3) {
        r1.zza(r2, r1.zzc(r1.zzb(r2), r1.zzb(r3)));
    }

    public static void zza(Class<?> r1) {
        if (zzjb.class.isAssignableFrom(r1) == true) goto L11;
        Class<?> r0 = zza;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAssignableFrom(r1) == true) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        return;
    L13:
        return;
    }

    public static boolean zza(Object r0, Object r1) {
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

    public static int zzb(int r2, List<zzht> r3) {
        int r0 = r3.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r02 = r0 * zzii.zze(r2);
    L7:
        if (r1 >= r3.size()) goto L9;
        r02 = r02 + zzii.zzb(r3.get(r1));
        r1 = r1 + 1;
        goto L7
    L9:
        return r02;
    }

    public static int zzb(int r4, List<zzkk> r5, zzlc r6) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r2 = 0;
    L6:
        if (r1 >= r0) goto L8;
        r2 = r2 + zzii.zzc(r4, r5.get(r1), r6);
        r1 = r1 + 1;
        goto L6
    L8:
        return r2;
    }

    public static int zzb(int r0, List<Long> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzb(r1) + (r22 * zzii.zze(r0));
    }

    public static int zzb(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzjy) == false) goto L12;
        zzjy r52 = (zzjy) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzii.zze(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static zzlu<?, ?> zzb() {
        return zzc;
    }

    public static void zzb(int r1, List<zzht> r2, zzmr r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2);
        return;
    }

    public static void zzb(int r1, List<?> r2, zzmr r3, zzlc r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2, r4);
        return;
    }

    public static void zzb(int r1, List<Float> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzf(r1, r2, r4);
        return;
    }

    public static int zzc(int r0, List<Long> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzc(r1) + (r22 * zzii.zze(r0));
    }

    public static int zzc(List<Long> r5) {
        int r0 = r5.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r5 instanceof zzjy) == false) goto L12;
        zzjy r52 = (zzjy) r5;
        if (r0 > 0) goto L11;
        return 0;
    L11:
        throw null;
    L12:
        int r2 = 0;
    L13:
        if (r1 >= r0) goto L15;
        r2 = r2 + zzii.zzf(r5.get(r1).longValue());
        r1 = r1 + 1;
        goto L13
    L15:
        return r2;
    }

    public static zzlu<?, ?> zzc() {
        return zzd;
    }

    public static void zzc(int r1, List<Long> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzc(r1, r2, r4);
        return;
    }

    public static int zzd(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzd(r1) + (r22 * zzii.zze(r0));
    }

    public static int zzd(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzjd) == false) goto L11;
        zzjd r42 = (zzjd) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzii.zzk(r42.zzb(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzii.zzk(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    private static Class<?> zzd() {
        return Class.forName("com.google.protobuf.GeneratedMessage");
    L4:
        return null;
    }

    public static void zzd(int r1, List<Long> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzd(r1, r2, r4);
        return;
    }

    public static int zze(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zze(r1) + (r22 * zzii.zze(r0));
    }

    public static int zze(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzjd) == false) goto L11;
        zzjd r42 = (zzjd) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzii.zzf(r42.zzb(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzii.zzf(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    private static Class<?> zze() {
        return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
    L4:
        return null;
    }

    public static void zze(int r1, List<Long> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzn(r1, r2, r4);
        return;
    }

    public static int zzf(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzf(r1) + (r22 * zzii.zze(r0));
    }

    public static int zzf(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzjd) == false) goto L11;
        zzjd r42 = (zzjd) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzii.zzg(r42.zzb(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzii.zzg(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static void zzf(int r1, List<Long> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zze(r1, r2, r4);
        return;
    }

    public static int zzg(int r0, List<Integer> r1, boolean r2) {
        int r22 = r1.size();
        if (r22 != 0) goto L7;
        return 0;
    L7:
        return zzg(r1) + (r22 * zzii.zze(r0));
    }

    public static int zzg(List<Integer> r4) {
        int r0 = r4.size();
        int r1 = 0;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if ((r4 instanceof zzjd) == false) goto L11;
        zzjd r42 = (zzjd) r4;
        int r2 = 0;
    L8:
        if (r1 >= r0) goto L10;
        r2 = r2 + zzii.zzh(r42.zzb(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    L11:
        int r22 = 0;
    L12:
        if (r1 >= r0) goto L14;
        r22 = r22 + zzii.zzh(r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L12
    L14:
        return r22;
    }

    public static void zzg(int r1, List<Long> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzl(r1, r2, r4);
        return;
    }

    public static int zzh(int r0, List<?> r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L6;
        return 0;
    L6:
        return r12 * zzii.zzi(r0, 0);
    }

    public static int zzh(List<?> r0) {
        return r0.size() << 2;
    }

    public static void zzh(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zza(r1, r2, r4);
        return;
    }

    public static int zzi(int r2, List<?> r3, boolean r4) {
        int r32 = r3.size();
        if (r32 != 0) goto L7;
        return 0;
    L7:
        return r32 * zzii.zzg(r2, 0);
    }

    public static int zzi(List<?> r0) {
        return r0.size() << 3;
    }

    public static void zzi(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzj(r1, r2, r4);
        return;
    }

    public static int zzj(int r0, List<?> r1, boolean r2) {
        int r12 = r1.size();
        if (r12 != 0) goto L7;
        return 0;
    L7:
        return r12 * zzii.zzb(r0, true);
    }

    public static int zzj(List<?> r0) {
        return r0.size();
    }

    public static void zzj(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzm(r1, r2, r4);
        return;
    }

    public static void zzk(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzb(r1, r2, r4);
        return;
    }

    public static void zzl(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzk(r1, r2, r4);
        return;
    }

    public static void zzm(int r1, List<Integer> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzh(r1, r2, r4);
        return;
    }

    public static void zzn(int r1, List<Boolean> r2, zzmr r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L8;
        r3.zzi(r1, r2, r4);
        return;
    }
}
