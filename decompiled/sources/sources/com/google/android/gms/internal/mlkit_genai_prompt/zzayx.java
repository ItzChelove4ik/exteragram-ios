package com.google.android.gms.internal.mlkit_genai_prompt;

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
abstract class zzayx {
    static final boolean zza = false;
    private static final Unsafe zzb = null;
    private static final Class zzc = null;
    private static final boolean zzd = false;
    private static final zzayw zze = null;
    private static final boolean zzf = false;

    static {
        Unsafe r1 = zzn();
        zzb = r1;
        int r2 = zzavq.$r8$clinit;
        zzc = Memory.class;
        Class r22 = Long.TYPE;
        boolean r3 = zzo(r22);
        zzd = r3;
        Class r4 = Integer.TYPE;
        boolean r5 = zzo(r4);
        zzayw r6 = null;
        if (r1 == null) goto L9;
        if (r3 == false) goto L7;
        r6 = new zzayv(r1);
        goto L9
    L7:
        if (r5 == false) goto L9;
        r6 = new zzayu(r1);
    L9:
        zze = r6;
        if (r6 != null) goto L35;
    L16:
        zzayw r62 = zze;
        boolean r8 = true;
        if (r62 != null) goto L37;
    L18:
        boolean r0 = false;
    L24:
        zzf = r0;
        zzu(byte[].class);
        zzu(boolean[].class);
        zzv(boolean[].class);
        zzu(int[].class);
        zzv(int[].class);
        zzu(long[].class);
        zzv(long[].class);
        zzu(float[].class);
        zzv(float[].class);
        zzu(double[].class);
        zzv(double[].class);
        zzu(Object[].class);
        zzv(Object[].class);
        Field r02 = zzw();
        if (r02 == null) goto L30;
        zzayw r12 = zze;
        if (r12 == null) goto L30;
        r12.zza.objectFieldOffset(r02);
    L30:
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) goto L33;
        r8 = false;
    L33:
        zza = r8;
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
        zzt(th);
        goto L18
    L35:
        Class<?> r64 = r6.zza.getClass();     // Catch: Throwable -> L14
        r64.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L14
        r64.getMethod("getLong", new Class[]{Object.class, r22});     // Catch: Throwable -> L14
        zzw();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        zzt(th);
        goto L16
    }

    public static Object zza(Class r1) {
        return zzb.allocateInstance(r1);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzb(Object r1, long r2) {
        return zze.zza.getInt(r1, r2);
    }

    public static void zzc(Object r1, long r2, int r4) {
        zze.zza.putInt(r1, r2, r4);
    }

    public static long zzd(Object r1, long r2) {
        return zze.zza.getLong(r1, r2);
    }

    public static void zze(Object r7, long r8, long r10) {
        zze.zza.putLong(r7, r8, r10);
    }

    public static boolean zzf(Object r1, long r2) {
        return zze.zza(r1, r2);
    }

    public static void zzg(Object r1, long r2, boolean r4) {
        zze.zzb(r1, r2, r4);
    }

    public static float zzh(Object r1, long r2) {
        return zze.zzc(r1, r2);
    }

    public static void zzi(Object r1, long r2, float r4) {
        zze.zzd(r1, r2, r4);
    }

    public static double zzj(Object r1, long r2) {
        return zze.zze(r1, r2);
    }

    public static void zzk(Object r6, long r7, double r9) {
        zze.zzf(r6, r7, r9);
    }

    public static Object zzl(Object r1, long r2) {
        return zze.zza.getObject(r1, r2);
    }

    public static void zzm(Object r1, long r2, Object r4) {
        zze.zza.putObject(r1, r2, r4);
    }

    public static Unsafe zzn() {
        Unsafe r1 = (Unsafe) AccessController.doPrivileged(new zzayt());     // Catch: Throwable -> L5
    L6:
        if (r1 != null) goto L12;
        return null;
    L12:
        r1.arrayBaseOffset(byte[].class);     // Catch: Exception -> L10
        return r1;
    L10:
        Logger.getLogger(zzayx.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "getUnsafe", "As part of the planned removal, sun.misc.Unsafe is available in the current environment but configured to throw on use. Protobuf will continue without using it, but with slightly reduced performance. --sun-misc-unsafe-memory-access=allow is likely available to opt back in if desired. A later Protobuf version release will stop using sun.misc.Unsafe entirely.");
        return null;
    L5:
        r1 = null;
        goto L6
    }

    public static boolean zzo(Class r6) {
        int r1 = zzavq.$r8$clinit;
        Class r12 = zzc;     // Catch: Throwable -> L6
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

    public static /* synthetic */ boolean zzp(Object r3, long r4) {
        if (((byte) ((zze.zza.getInt(r3, (-4) & r4) >>> ((int) (((~r4) & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean zzq(Object r3, long r4) {
        if (((byte) ((zze.zza.getInt(r3, (-4) & r4) >>> ((int) ((r4 & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void zzr(Object r4, long r5, boolean r7) {
        Unsafe r0 = zze.zza;
        long r1 = (-4) & r5;
        int r3 = r0.getInt(r4, r1);
        int r52 = ((~((int) r5)) & 3) << 3;
        int r6 = (~(255 << r52)) & r3;
        r0.putInt(r4, r1, ((r7 ? 1 : 0) << r52) | r6);
    }

    public static /* synthetic */ void zzs(Object r4, long r5, boolean r7) {
        Unsafe r0 = zze.zza;
        long r1 = (-4) & r5;
        int r3 = r0.getInt(r4, r1);
        int r52 = (((int) r5) & 3) << 3;
        int r6 = (~(255 << r52)) & r3;
        r0.putInt(r4, r1, ((r7 ? 1 : 0) << r52) | r6);
    }

    public static /* synthetic */ void zzt(Throwable r4) {
        Logger.getLogger(zzayx.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r4.toString()));
    }

    private static int zzu(Class r1) {
        if (zzf == true) goto L5;
        return -1;
    L5:
        return zze.zza.arrayBaseOffset(r1);
    }

    private static int zzv(Class r1) {
        if (zzf == true) goto L5;
        return -1;
    L5:
        return zze.zza.arrayIndexScale(r1);
    }

    private static Field zzw() {
        int r0 = zzavq.$r8$clinit;
        Field r02 = zzx(Buffer.class, "effectiveDirectAddress");
        if (r02 != null) goto L11;
        Field r03 = zzx(Buffer.class, "address");
        if (r03 != null) goto L7;
    L9:
        return null;
    L7:
        if (r03.getType() != Long.TYPE) goto L9;
        return r03;
    L11:
        return r02;
    }

    private static Field zzx(Class r0, String r1) {
        return r0.getDeclaredField(r1);
    L4:
        return null;
    }
}
