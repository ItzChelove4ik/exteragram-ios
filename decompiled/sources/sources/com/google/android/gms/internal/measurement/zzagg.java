package com.google.android.gms.internal.measurement;

import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
abstract class zzagg {
    static final long zza = 0;
    static final boolean zzb = false;
    private static final Unsafe zzc = null;
    private static final Class zzd = null;
    private static final boolean zze = false;
    private static final zzagf zzf = null;
    private static final boolean zzg = false;

    static {
        Unsafe r1 = zzp();
        zzc = r1;
        int r2 = zzacf.$r8$clinit;
        zzd = Memory.class;
        Class r22 = Long.TYPE;
        boolean r3 = zzq(r22);
        zze = r3;
        Class r4 = Integer.TYPE;
        boolean r5 = zzq(r4);
        zzagf r6 = null;
        if (r1 == null) goto L9;
        if (r3 == false) goto L7;
        r6 = new zzage(r1);
        goto L9
    L7:
        if (r5 == false) goto L9;
        r6 = new zzagd(r1);
    L9:
        zzf = r6;
        if (r6 != null) goto L35;
    L16:
        zzagf r62 = zzf;
        boolean r8 = true;
        if (r62 != null) goto L37;
    L18:
        boolean r0 = false;
    L24:
        zzg = r0;
        zza = zzy(byte[].class);
        zzy(boolean[].class);
        zzz(boolean[].class);
        zzy(int[].class);
        zzz(int[].class);
        zzy(long[].class);
        zzz(long[].class);
        zzy(float[].class);
        zzz(float[].class);
        zzy(double[].class);
        zzz(double[].class);
        zzy(Object[].class);
        zzz(Object[].class);
        Field r02 = zzA();
        if (r02 == null) goto L30;
        zzagf r12 = zzf;
        if (r12 == null) goto L30;
        r12.zza.objectFieldOffset(r02);
    L30:
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) goto L33;
        r8 = false;
    L33:
        zzb = r8;
        return;
    L37:
        Class<?> r63 = r62.zza.getClass();     // Catch: Throwable -> L22
        r63.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L22
        r63.getMethod("arrayBaseOffset", new Class[]{Class.class});     // Catch: Throwable -> L22
        r63.getMethod("arrayIndexScale", new Class[]{Class.class});     // Catch: Throwable -> L22
        r63.getMethod("getInt", new Class[]{Object.class, r22});     // Catch: Throwable -> L22
        r63.getMethod("putInt", new Class[]{Object.class, r22, r4});     // Catch: Throwable -> L22
        r63.getMethod("getLong", new Class[]{Object.class, r22});     // Catch: Throwable -> L22
        r63.getMethod("putLong", new Class[]{Object.class, r22, r22});     // Catch: Throwable -> L22
        r63.getMethod("getObject", new Class[]{Object.class, r22});     // Catch: Throwable -> L22
        r63.getMethod("putObject", new Class[]{Object.class, r22, Object.class});     // Catch: Throwable -> L22
        r0 = true;
    L22:
        th = move-exception;
        zzx(th);
        goto L18
    L35:
        Class<?> r64 = r6.zza.getClass();     // Catch: Throwable -> L14
        r64.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L14
        r64.getMethod("getLong", new Class[]{Object.class, r22});     // Catch: Throwable -> L14
        zzA();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        zzx(th);
        goto L16
    }

    private static Field zzA() {
        int r0 = zzacf.$r8$clinit;
        Field r02 = zzB(Buffer.class, "effectiveDirectAddress");
        if (r02 != null) goto L11;
        Field r03 = zzB(Buffer.class, "address");
        if (r03 != null) goto L7;
    L9:
        return null;
    L7:
        if (r03.getType() != Long.TYPE) goto L9;
        return r03;
    L11:
        return r02;
    }

    private static Field zzB(Class r0, String r1) {
        return r0.getDeclaredField(r1);
    L4:
        return null;
    }

    private static void zzC(Object r5, long r6, byte r8) {
        Unsafe r0 = zzf.zza;
        long r1 = (-4) & r6;
        int r3 = r0.getInt(r5, r1);
        int r62 = ((~((int) r6)) & 3) << 3;
        int r32 = r3 & (~(255 << r62));
        r0.putInt(r5, r1, ((255 & r8) << r62) | r32);
    }

    private static void zzD(Object r5, long r6, byte r8) {
        Unsafe r0 = zzf.zza;
        long r1 = (-4) & r6;
        int r62 = (((int) r6) & 3) << 3;
        int r3 = r0.getInt(r5, r1) & (~(255 << r62));
        r0.putInt(r5, r1, ((255 & r8) << r62) | r3);
    }

    public static boolean zza() {
        return zzg;
    }

    public static Object zzb(Class r1) {
        return zzc.allocateInstance(r1);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzc(Object r1, long r2) {
        return zzf.zza.getInt(r1, r2);
    }

    public static void zzd(Object r1, long r2, int r4) {
        zzf.zza.putInt(r1, r2, r4);
    }

    public static long zze(Object r1, long r2) {
        return zzf.zza.getLong(r1, r2);
    }

    public static void zzf(Object r7, long r8, long r10) {
        zzf.zza.putLong(r7, r8, r10);
    }

    public static boolean zzg(Object r1, long r2) {
        return zzf.zzb(r1, r2);
    }

    public static void zzh(Object r1, long r2, boolean r4) {
        zzf.zzc(r1, r2, r4);
    }

    public static float zzi(Object r1, long r2) {
        return zzf.zzd(r1, r2);
    }

    public static void zzj(Object r1, long r2, float r4) {
        zzf.zze(r1, r2, r4);
    }

    public static double zzk(Object r1, long r2) {
        return zzf.zzf(r1, r2);
    }

    public static void zzl(Object r6, long r7, double r9) {
        zzf.zzg(r6, r7, r9);
    }

    public static Object zzm(Object r1, long r2) {
        return zzf.zza.getObject(r1, r2);
    }

    public static void zzn(Object r1, long r2, Object r4) {
        zzf.zza.putObject(r1, r2, r4);
    }

    public static void zzo(byte[] r3, long r4, byte r6) {
        zzf.zza(r3, zza + r4, r6);
    }

    public static Unsafe zzp() {
        Unsafe r1 = (Unsafe) AccessController.doPrivileged(new zzagc());     // Catch: Throwable -> L5
    L6:
        if (r1 != null) goto L12;
        return null;
    L12:
        r1.arrayBaseOffset(byte[].class);     // Catch: Exception -> L10
        return r1;
    L10:
        Logger.getLogger(zzagg.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "getUnsafe", "As part of the planned removal, sun.misc.Unsafe is available in the current environment but configured to throw on use. Protobuf will continue without using it, but with slightly reduced performance. --sun-misc-unsafe-memory-access=allow is likely available to opt back in if desired. A later Protobuf version release will stop using sun.misc.Unsafe entirely.");
        return null;
    L5:
        r1 = null;
        goto L6
    }

    public static boolean zzq(Class r6) {
        int r1 = zzacf.$r8$clinit;
        Class r12 = zzd;     // Catch: Throwable -> L6
        Class r3 = Boolean.TYPE;     // Catch: Throwable -> L6
        r12.getMethod("peekLong", new Class[]{r6, r3});     // Catch: Throwable -> L6
        r12.getMethod("pokeLong", new Class[]{r6, Long.TYPE, r3});     // Catch: Throwable -> L6
        Class r4 = Integer.TYPE;     // Catch: Throwable -> L6
        r12.getMethod("pokeInt", new Class[]{r6, r4, r3});     // Catch: Throwable -> L6
        r12.getMethod("peekInt", new Class[]{r6, r3});     // Catch: Throwable -> L6
        r12.getMethod("pokeByte", new Class[]{r6, Byte.TYPE});     // Catch: Throwable -> L6
        r12.getMethod("peekByte", new Class[]{r6});     // Catch: Throwable -> L6
        r12.getMethod("pokeByteArray", new Class[]{r6, byte[].class, r4, r4});     // Catch: Throwable -> L6
        r12.getMethod("peekByteArray", new Class[]{r6, byte[].class, r4, r4});     // Catch: Throwable -> L6
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void zzr(Object r0, long r1, byte r3) {
        zzC(r0, r1, r3);
    }

    public static /* synthetic */ void zzs(Object r0, long r1, byte r3) {
        zzD(r0, r1, r3);
    }

    public static /* synthetic */ boolean zzt(Object r3, long r4) {
        if (((byte) ((zzf.zza.getInt(r3, (-4) & r4) >>> ((int) (((~r4) & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean zzu(Object r3, long r4) {
        if (((byte) ((zzf.zza.getInt(r3, (-4) & r4) >>> ((int) ((r4 & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void zzv(Object r0, long r1, boolean r3) {
        zzC(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzw(Object r0, long r1, boolean r3) {
        zzD(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzx(Throwable r4) {
        Logger.getLogger(zzagg.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r4.toString()));
    }

    private static int zzy(Class r1) {
        if (zzg == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayBaseOffset(r1);
    }

    private static int zzz(Class r1) {
        if (zzg == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayIndexScale(r1);
    }
}
