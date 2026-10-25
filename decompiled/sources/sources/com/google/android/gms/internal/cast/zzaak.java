package com.google.android.gms.internal.cast;

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
abstract class zzaak {
    static final long zza = 0;
    static final boolean zzb = false;
    private static final Unsafe zzc = null;
    private static final Class zzd = null;
    private static final boolean zze = false;
    private static final zzaaj zzf = null;
    private static final boolean zzg = false;
    private static final boolean zzh = false;

    static {
        Unsafe r1 = zzq();
        zzc = r1;
        int r2 = zzxb.$r8$clinit;
        zzd = Memory.class;
        Class r22 = Long.TYPE;
        boolean r3 = zzr(r22);
        zze = r3;
        Class r4 = Integer.TYPE;
        boolean r5 = zzr(r4);
        zzaaj r6 = null;
        if (r1 == null) goto L9;
        if (r3 == false) goto L7;
        r6 = new zzaai(r1);
        goto L9
    L7:
        if (r5 == false) goto L9;
        r6 = new zzaah(r1);
    L9:
        zzf = r6;
        boolean r7 = true;
        if (r6 != null) goto L38;
    L11:
        boolean r62 = false;
    L19:
        zzg = r62;
        zzaaj r63 = zzf;
        if (r63 != null) goto L40;
    L21:
        boolean r0 = false;
    L27:
        zzh = r0;
        zza = zzz(byte[].class);
        zzz(boolean[].class);
        zzA(boolean[].class);
        zzz(int[].class);
        zzA(int[].class);
        zzz(long[].class);
        zzA(long[].class);
        zzz(float[].class);
        zzA(float[].class);
        zzz(double[].class);
        zzA(double[].class);
        zzz(Object[].class);
        zzA(Object[].class);
        Field r02 = zzB();
        if (r02 == null) goto L33;
        zzaaj r12 = zzf;
        if (r12 == null) goto L33;
        r12.zza.objectFieldOffset(r02);
    L33:
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) goto L36;
        r7 = false;
    L36:
        zzb = r7;
        return;
    L40:
        Class<?> r64 = r63.zza.getClass();     // Catch: Throwable -> L25
        r64.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L25
        r64.getMethod("arrayBaseOffset", new Class[]{Class.class});     // Catch: Throwable -> L25
        r64.getMethod("arrayIndexScale", new Class[]{Class.class});     // Catch: Throwable -> L25
        r64.getMethod("getInt", new Class[]{Object.class, r22});     // Catch: Throwable -> L25
        r64.getMethod("putInt", new Class[]{Object.class, r22, r4});     // Catch: Throwable -> L25
        r64.getMethod("getLong", new Class[]{Object.class, r22});     // Catch: Throwable -> L25
        r64.getMethod("putLong", new Class[]{Object.class, r22, r22});     // Catch: Throwable -> L25
        r64.getMethod("getObject", new Class[]{Object.class, r22});     // Catch: Throwable -> L25
        r64.getMethod("putObject", new Class[]{Object.class, r22, Object.class});     // Catch: Throwable -> L25
        r0 = true;
    L25:
        th = move-exception;
        zzy(th);
        goto L21
    L38:
        Class<?> r65 = r6.zza.getClass();     // Catch: Throwable -> L17
        r65.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L17
        r65.getMethod("getLong", new Class[]{Object.class, r22});     // Catch: Throwable -> L17
        if (zzB() == null) goto L11;
        r62 = true;
    L17:
        th = move-exception;
        zzy(th);
        goto L11
    }

    private static int zzA(Class r1) {
        if (zzh == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayIndexScale(r1);
    }

    private static Field zzB() {
        int r0 = zzxb.$r8$clinit;
        Field r02 = zzC(Buffer.class, "effectiveDirectAddress");
        if (r02 != null) goto L11;
        Field r03 = zzC(Buffer.class, "address");
        if (r03 != null) goto L7;
    L9:
        return null;
    L7:
        if (r03.getType() != Long.TYPE) goto L9;
        return r03;
    L11:
        return r02;
    }

    private static Field zzC(Class r0, String r1) {
        return r0.getDeclaredField(r1);
    L4:
        return null;
    }

    private static void zzD(Object r5, long r6, byte r8) {
        Unsafe r0 = zzf.zza;
        long r1 = (-4) & r6;
        int r3 = r0.getInt(r5, r1);
        int r62 = ((~((int) r6)) & 3) << 3;
        int r32 = r3 & (~(255 << r62));
        r0.putInt(r5, r1, ((255 & r8) << r62) | r32);
    }

    private static void zzE(Object r5, long r6, byte r8) {
        Unsafe r0 = zzf.zza;
        long r1 = (-4) & r6;
        int r62 = (((int) r6) & 3) << 3;
        int r3 = r0.getInt(r5, r1) & (~(255 << r62));
        r0.putInt(r5, r1, ((255 & r8) << r62) | r3);
    }

    public static boolean zza() {
        return zzh;
    }

    public static boolean zzb() {
        return zzg;
    }

    public static Object zzc(Class r1) {
        return zzc.allocateInstance(r1);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzd(Object r1, long r2) {
        return zzf.zza.getInt(r1, r2);
    }

    public static void zze(Object r1, long r2, int r4) {
        zzf.zza.putInt(r1, r2, r4);
    }

    public static long zzf(Object r1, long r2) {
        return zzf.zza.getLong(r1, r2);
    }

    public static void zzg(Object r7, long r8, long r10) {
        zzf.zza.putLong(r7, r8, r10);
    }

    public static boolean zzh(Object r1, long r2) {
        return zzf.zzb(r1, r2);
    }

    public static void zzi(Object r1, long r2, boolean r4) {
        zzf.zzc(r1, r2, r4);
    }

    public static float zzj(Object r1, long r2) {
        return zzf.zzd(r1, r2);
    }

    public static void zzk(Object r1, long r2, float r4) {
        zzf.zze(r1, r2, r4);
    }

    public static double zzl(Object r1, long r2) {
        return zzf.zzf(r1, r2);
    }

    public static void zzm(Object r6, long r7, double r9) {
        zzf.zzg(r6, r7, r9);
    }

    public static Object zzn(Object r1, long r2) {
        return zzf.zza.getObject(r1, r2);
    }

    public static void zzo(Object r1, long r2, Object r4) {
        zzf.zza.putObject(r1, r2, r4);
    }

    public static void zzp(byte[] r3, long r4, byte r6) {
        zzf.zza(r3, zza + r4, r6);
    }

    public static Unsafe zzq() {
        Unsafe r1 = (Unsafe) AccessController.doPrivileged(new zzaag());     // Catch: Throwable -> L5
    L6:
        if (r1 != null) goto L12;
        return null;
    L12:
        r1.arrayBaseOffset(byte[].class);     // Catch: Exception -> L10
        return r1;
    L10:
        Logger.getLogger(zzaak.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "getUnsafe", "As part of the planned removal, sun.misc.Unsafe is available in the current environment but configured to throw on use. Protobuf will continue without using it, but with slightly reduced performance. --sun-misc-unsafe-memory-access=allow is likely available to opt back in if desired. A later Protobuf version release will stop using sun.misc.Unsafe entirely.");
        return null;
    L5:
        r1 = null;
        goto L6
    }

    public static boolean zzr(Class r6) {
        int r1 = zzxb.$r8$clinit;
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

    public static /* synthetic */ void zzs(Object r0, long r1, byte r3) {
        zzD(r0, r1, r3);
    }

    public static /* synthetic */ void zzt(Object r0, long r1, byte r3) {
        zzE(r0, r1, r3);
    }

    public static /* synthetic */ boolean zzu(Object r3, long r4) {
        if (((byte) ((zzf.zza.getInt(r3, (-4) & r4) >>> ((int) (((~r4) & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean zzv(Object r3, long r4) {
        if (((byte) ((zzf.zza.getInt(r3, (-4) & r4) >>> ((int) ((r4 & 3) << 3))) & 255)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void zzw(Object r0, long r1, boolean r3) {
        zzD(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzx(Object r0, long r1, boolean r3) {
        zzE(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzy(Throwable r4) {
        Logger.getLogger(zzaak.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r4.toString()));
    }

    private static int zzz(Class r1) {
        if (zzh == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayBaseOffset(r1);
    }
}
