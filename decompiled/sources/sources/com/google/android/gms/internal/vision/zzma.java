package com.google.android.gms.internal.vision;

import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
abstract class zzma {
    static final boolean zza = false;
    private static final Unsafe zzb = null;
    private static final Class<?> zzc = null;
    private static final boolean zzd = false;
    private static final boolean zze = false;
    private static final zzd zzf = null;
    private static final boolean zzg = false;
    private static final boolean zzh = false;
    private static final long zzi = 0;
    private static final long zzj = 0;
    private static final long zzk = 0;
    private static final long zzl = 0;
    private static final long zzm = 0;
    private static final long zzn = 0;
    private static final long zzo = 0;
    private static final long zzp = 0;
    private static final long zzq = 0;
    private static final long zzr = 0;
    private static final long zzs = 0;
    private static final long zzt = 0;
    private static final long zzu = 0;
    private static final long zzv = 0;
    private static final int zzw = 0;

    public static final class zza extends zzd {
        public zza(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final byte zza(Object r1, long r2) {
            if (zzma.zza == false) goto L7;
            return zzma.zzg(r1, r2);
        L7:
            return zzma.zzh(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, byte r4) {
            if (zzma.zza == false) goto L6;
            zzma.zza(r1, r2, r4);
            return;
        L6:
            zzma.zzb(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, double r4) {
            zza(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, float r4) {
            zza(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            if (zzma.zza == false) goto L6;
            zzma.zzb(r1, r2, r4);
            return;
        L6:
            zzma.zzc(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final boolean zzb(Object r1, long r2) {
            if (zzma.zza == false) goto L7;
            return zzma.zzi(r1, r2);
        L7:
            return zzma.zzj(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final float zzc(Object r1, long r2) {
            return Float.intBitsToFloat(zze(r1, r2));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final double zzd(Object r1, long r2) {
            return Double.longBitsToDouble(zzf(r1, r2));
        }
    }

    public static final class zzb extends zzd {
        public zzb(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final byte zza(Object r1, long r2) {
            return this.zza.getByte(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, byte r4) {
            this.zza.putByte(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, double r4) {
            this.zza.putDouble(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, float r4) {
            this.zza.putFloat(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            this.zza.putBoolean(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final boolean zzb(Object r1, long r2) {
            return this.zza.getBoolean(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final float zzc(Object r1, long r2) {
            return this.zza.getFloat(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final double zzd(Object r1, long r2) {
            return this.zza.getDouble(r1, r2);
        }
    }

    public static final class zzc extends zzd {
        public zzc(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final byte zza(Object r1, long r2) {
            if (zzma.zza == false) goto L7;
            return zzma.zzg(r1, r2);
        L7:
            return zzma.zzh(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, byte r4) {
            if (zzma.zza == false) goto L6;
            zzma.zza(r1, r2, r4);
            return;
        L6:
            zzma.zzb(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, double r4) {
            zza(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, float r4) {
            zza(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            if (zzma.zza == false) goto L6;
            zzma.zzb(r1, r2, r4);
            return;
        L6:
            zzma.zzc(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final boolean zzb(Object r1, long r2) {
            if (zzma.zza == false) goto L7;
            return zzma.zzi(r1, r2);
        L7:
            return zzma.zzj(r1, r2);
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final float zzc(Object r1, long r2) {
            return Float.intBitsToFloat(zze(r1, r2));
        }

        @Override // com.google.android.gms.internal.vision.zzma.zzd
        public final double zzd(Object r1, long r2) {
            return Double.longBitsToDouble(zzf(r1, r2));
        }
    }

    public static abstract class zzd {
        Unsafe zza;

        public zzd(Unsafe r1) {
            this.zza = r1;
        }

        public abstract byte zza(Object r1, long r2);

        public abstract void zza(Object r1, long r2, byte r4);

        public abstract void zza(Object r1, long r2, double r4);

        public abstract void zza(Object r1, long r2, float r4);

        public final void zza(Object r1, long r2, int r4) {
            this.zza.putInt(r1, r2, r4);
        }

        public final void zza(Object r1, long r2, long r4) {
            this.zza.putLong(r1, r2, r4);
        }

        public abstract void zza(Object r1, long r2, boolean r4);

        public abstract boolean zzb(Object r1, long r2);

        public abstract float zzc(Object r1, long r2);

        public abstract double zzd(Object r1, long r2);

        public final int zze(Object r1, long r2) {
            return this.zza.getInt(r1, r2);
        }

        public final long zzf(Object r1, long r2) {
            return this.zza.getLong(r1, r2);
        }
    }

    static {
        Unsafe r0 = zzc();
        zzb = r0;
        zzc = zzhi.zzb();
        boolean r1 = zzd(Long.TYPE);
        zzd = r1;
        boolean r2 = zzd(Integer.TYPE);
        zze = r2;
        zzd r3 = null;
        if (r0 != null) goto L6;
    L12:
        zzf = r3;
        zzg = zze();
        zzh = zzd();
        long r02 = zzb(byte[].class);
        zzi = r02;
        zzj = zzb(boolean[].class);
        zzk = zzc(boolean[].class);
        zzl = zzb(int[].class);
        zzm = zzc(int[].class);
        zzn = zzb(long[].class);
        zzo = zzc(long[].class);
        zzp = zzb(float[].class);
        zzq = zzc(float[].class);
        zzr = zzb(double[].class);
        zzs = zzc(double[].class);
        zzt = zzb(Object[].class);
        zzu = zzc(Object[].class);
        Field r22 = zzf();
        if (r22 == null) goto L17;
        if (r3 == null) goto L17;
        long r23 = r3.zza.objectFieldOffset(r22);
    L18:
        zzv = r23;
        zzw = (int) (r02 & 7);
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) goto L21;
        boolean r03 = true;
    L22:
        zza = r03;
        return;
    L21:
        r03 = false;
    L17:
        r23 = -1;
        goto L18
    L6:
        if (zzhi.zza() == false) goto L11;
        if (r1 == false) goto L9;
        r3 = new zzc(r0);
        goto L12
    L9:
        if (r2 == false) goto L12;
        r3 = new zza(r0);
        goto L12
    L11:
        r3 = new zzb(r0);
        goto L12
    }

    public static byte zza(byte[] r3, long r4) {
        return zzf.zza(r3, zzi + r4);
    }

    public static int zza(Object r1, long r2) {
        return zzf.zze(r1, r2);
    }

    public static <T> T zza(Class<T> r1) {
        return (T) zzb.allocateInstance(r1);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private static Field zza(Class<?> r0, String r1) {
        return r0.getDeclaredField(r1);
    L4:
        return null;
    }

    public static /* synthetic */ void zza(Object r0, long r1, byte r3) {
        zzc(r0, r1, r3);
    }

    public static void zza(Object r6, long r7, double r9) {
        zzf.zza(r6, r7, r9);
    }

    public static void zza(Object r1, long r2, float r4) {
        zzf.zza(r1, r2, r4);
    }

    public static void zza(Object r1, long r2, int r4) {
        zzf.zza(r1, r2, r4);
    }

    public static void zza(Object r6, long r7, long r9) {
        zzf.zza(r6, r7, r9);
    }

    public static void zza(Object r1, long r2, Object r4) {
        zzf.zza.putObject(r1, r2, r4);
    }

    public static void zza(Object r1, long r2, boolean r4) {
        zzf.zza(r1, r2, r4);
    }

    public static void zza(byte[] r3, long r4, byte r6) {
        zzf.zza(r3, zzi + r4, r6);
    }

    public static boolean zza() {
        return zzh;
    }

    private static int zzb(Class<?> r1) {
        if (zzh == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayBaseOffset(r1);
    }

    public static long zzb(Object r1, long r2) {
        return zzf.zzf(r1, r2);
    }

    public static /* synthetic */ void zzb(Object r0, long r1, byte r3) {
        zzd(r0, r1, r3);
    }

    public static /* synthetic */ void zzb(Object r0, long r1, boolean r3) {
        zzd(r0, r1, r3);
    }

    public static boolean zzb() {
        return zzg;
    }

    private static int zzc(Class<?> r1) {
        if (zzh == true) goto L5;
        return -1;
    L5:
        return zzf.zza.arrayIndexScale(r1);
    }

    public static Unsafe zzc() {
        return (Unsafe) AccessController.doPrivileged(new zzmc());
    L4:
        return null;
    }

    private static void zzc(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r2 = zza(r4, r0);
        int r52 = ((~((int) r5)) & 3) << 3;
        int r22 = r2 & (~(255 << r52));
        zza(r4, r0, ((255 & r7) << r52) | r22);
    }

    public static /* synthetic */ void zzc(Object r0, long r1, boolean r3) {
        zze(r0, r1, r3);
    }

    public static boolean zzc(Object r1, long r2) {
        return zzf.zzb(r1, r2);
    }

    public static float zzd(Object r1, long r2) {
        return zzf.zzc(r1, r2);
    }

    private static void zzd(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r52 = (((int) r5) & 3) << 3;
        int r2 = zza(r4, r0) & (~(255 << r52));
        zza(r4, r0, ((255 & r7) << r52) | r2);
    }

    private static void zzd(Object r0, long r1, boolean r3) {
        zzc(r0, r1, r3 ? 1 : 0);
    }

    private static boolean zzd() {
        Unsafe r2 = zzb;
        if (r2 != null) goto L13;
        return false;
    L13:
        Class<?> r22 = r2.getClass();     // Catch: Throwable -> L10
        r22.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L10
        r22.getMethod("arrayBaseOffset", new Class[]{Class.class});     // Catch: Throwable -> L10
        r22.getMethod("arrayIndexScale", new Class[]{Class.class});     // Catch: Throwable -> L10
        Class r4 = Long.TYPE;     // Catch: Throwable -> L10
        r22.getMethod("getInt", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putInt", new Class[]{Object.class, r4, Integer.TYPE});     // Catch: Throwable -> L10
        r22.getMethod("getLong", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putLong", new Class[]{Object.class, r4, r4});     // Catch: Throwable -> L10
        r22.getMethod("getObject", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putObject", new Class[]{Object.class, r4, Object.class});     // Catch: Throwable -> L10
        if (zzhi.zza() == false) goto L8;
        return true;
    L8:
        r22.getMethod("getByte", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putByte", new Class[]{Object.class, r4, Byte.TYPE});     // Catch: Throwable -> L10
        r22.getMethod("getBoolean", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putBoolean", new Class[]{Object.class, r4, Boolean.TYPE});     // Catch: Throwable -> L10
        r22.getMethod("getFloat", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putFloat", new Class[]{Object.class, r4, Float.TYPE});     // Catch: Throwable -> L10
        r22.getMethod("getDouble", new Class[]{Object.class, r4});     // Catch: Throwable -> L10
        r22.getMethod("putDouble", new Class[]{Object.class, r4, Double.TYPE});     // Catch: Throwable -> L10
        return true;
    L10:
        th = move-exception;
        Logger r1 = Logger.getLogger(zzma.class.getName());
        Level r23 = Level.WARNING;
        String r0 = String.valueOf(th);
        StringBuilder r5 = new StringBuilder(r0.length() + 71);
        r5.append("platform method missing - proto runtime falling back to safer methods: ");
        r5.append(r0);
        r1.logp(r23, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", r5.toString());
        return false;
    }

    private static boolean zzd(Class<?> r7) {
        if (zzhi.zza() == true) goto L9;
        return false;
    L9:
        Class<?> r1 = zzc;     // Catch: Throwable -> L8
        Class r4 = Boolean.TYPE;     // Catch: Throwable -> L8
        r1.getMethod("peekLong", new Class[]{r7, r4});     // Catch: Throwable -> L8
        r1.getMethod("pokeLong", new Class[]{r7, Long.TYPE, r4});     // Catch: Throwable -> L8
        Class r5 = Integer.TYPE;     // Catch: Throwable -> L8
        r1.getMethod("pokeInt", new Class[]{r7, r5, r4});     // Catch: Throwable -> L8
        r1.getMethod("peekInt", new Class[]{r7, r4});     // Catch: Throwable -> L8
        r1.getMethod("pokeByte", new Class[]{r7, Byte.TYPE});     // Catch: Throwable -> L8
        r1.getMethod("peekByte", new Class[]{r7});     // Catch: Throwable -> L8
        r1.getMethod("pokeByteArray", new Class[]{r7, byte[].class, r5, r5});     // Catch: Throwable -> L8
        r1.getMethod("peekByteArray", new Class[]{r7, byte[].class, r5, r5});     // Catch: Throwable -> L8
        return true;
    L8:
        return false;
    }

    public static double zze(Object r1, long r2) {
        return zzf.zzd(r1, r2);
    }

    private static void zze(Object r0, long r1, boolean r3) {
        zzd(r0, r1, r3 ? 1 : 0);
    }

    private static boolean zze() {
        Unsafe r3 = zzb;
        if (r3 != null) goto L16;
        return false;
    L16:
        Class<?> r32 = r3.getClass();     // Catch: Throwable -> L13
        r32.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L13
        Class r5 = Long.TYPE;     // Catch: Throwable -> L13
        r32.getMethod("getLong", new Class[]{Object.class, r5});     // Catch: Throwable -> L13
        if (zzf() != null) goto L9;
        return false;
    L9:
        if (zzhi.zza() == false) goto L11;
        return true;
    L11:
        r32.getMethod("getByte", new Class[]{r5});     // Catch: Throwable -> L13
        r32.getMethod("putByte", new Class[]{r5, Byte.TYPE});     // Catch: Throwable -> L13
        r32.getMethod("getInt", new Class[]{r5});     // Catch: Throwable -> L13
        r32.getMethod("putInt", new Class[]{r5, Integer.TYPE});     // Catch: Throwable -> L13
        r32.getMethod("getLong", new Class[]{r5});     // Catch: Throwable -> L13
        r32.getMethod("putLong", new Class[]{r5, r5});     // Catch: Throwable -> L13
        r32.getMethod("copyMemory", new Class[]{r5, r5, r5});     // Catch: Throwable -> L13
        r32.getMethod("copyMemory", new Class[]{Object.class, r5, Object.class, r5, r5});     // Catch: Throwable -> L13
        return true;
    L13:
        th = move-exception;
        Logger r1 = Logger.getLogger(zzma.class.getName());
        Level r2 = Level.WARNING;
        String r0 = String.valueOf(th);
        StringBuilder r52 = new StringBuilder(r0.length() + 71);
        r52.append("platform method missing - proto runtime falling back to safer methods: ");
        r52.append(r0);
        r1.logp(r2, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", r52.toString());
        return false;
    }

    public static Object zzf(Object r1, long r2) {
        return zzf.zza.getObject(r1, r2);
    }

    private static Field zzf() {
        if (zzhi.zza() == false) goto L7;
        Field r0 = zza(Buffer.class, "effectiveDirectAddress");
        if (r0 == null) goto L7;
        return r0;
    L7:
        Field r02 = zza(Buffer.class, "address");
        if (r02 != null) goto L10;
        return null;
    L10:
        if (r02.getType() != Long.TYPE) goto L14;
        return r02;
    L14:
        return null;
    }

    public static /* synthetic */ byte zzg(Object r0, long r1) {
        return zzk(r0, r1);
    }

    public static /* synthetic */ byte zzh(Object r0, long r1) {
        return zzl(r0, r1);
    }

    public static /* synthetic */ boolean zzi(Object r0, long r1) {
        return zzm(r0, r1);
    }

    public static /* synthetic */ boolean zzj(Object r0, long r1) {
        return zzn(r0, r1);
    }

    private static byte zzk(Object r2, long r3) {
        return (byte) (zza(r2, (-4) & r3) >>> ((int) (((~r3) & 3) << 3)));
    }

    private static byte zzl(Object r2, long r3) {
        return (byte) (zza(r2, (-4) & r3) >>> ((int) ((r3 & 3) << 3)));
    }

    private static boolean zzm(Object r0, long r1) {
        if (zzk(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzn(Object r0, long r1) {
        if (zzl(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
