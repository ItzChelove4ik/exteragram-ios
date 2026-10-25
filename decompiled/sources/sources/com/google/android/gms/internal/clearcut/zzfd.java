package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
abstract class zzfd {
    private static final Logger logger = null;
    private static final Class<?> zzfb = null;
    private static final boolean zzfy = false;
    private static final Unsafe zzmh = null;
    private static final boolean zzpg = false;
    private static final boolean zzph = false;
    private static final zzd zzpi = null;
    private static final boolean zzpj = false;
    private static final long zzpk = 0;
    private static final long zzpl = 0;
    private static final long zzpm = 0;
    private static final long zzpn = 0;
    private static final long zzpo = 0;
    private static final long zzpp = 0;
    private static final long zzpq = 0;
    private static final long zzpr = 0;
    private static final long zzps = 0;
    private static final long zzpt = 0;
    private static final long zzpu = 0;
    private static final long zzpv = 0;
    private static final long zzpw = 0;
    private static final long zzpx = 0;
    private static final long zzpy = 0;
    private static final boolean zzpz = false;

    public static final class zza extends zzd {
        public zza(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(long r1, byte r3) {
            Memory.pokeByte((int) r1, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, double r4) {
            zza(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, float r4) {
            zza(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            if (zzfd.zzah() == false) goto L6;
            zzfd.zzd(r1, r2, r4);
            return;
        L6:
            zzfd.zze(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(byte[] r1, long r2, long r4, long r6) {
            Memory.pokeByteArray((int) r4, r1, (int) r2, (int) r6);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zze(Object r1, long r2, byte r4) {
            if (zzfd.zzah() == false) goto L6;
            zzfd.zzc(r1, r2, r4);
            return;
        L6:
            zzfd.zzd(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final boolean zzl(Object r1, long r2) {
            if (zzfd.zzah() == false) goto L7;
            return zzfd.zzv(r1, r2);
        L7:
            return zzfd.zzw(r1, r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final float zzm(Object r1, long r2) {
            return Float.intBitsToFloat(zzj(r1, r2));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final double zzn(Object r1, long r2) {
            return Double.longBitsToDouble(zzk(r1, r2));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final byte zzx(Object r1, long r2) {
            if (zzfd.zzah() == false) goto L7;
            return zzfd.zzt(r1, r2);
        L7:
            return zzfd.zzu(r1, r2);
        }
    }

    public static final class zzb extends zzd {
        public zzb(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(long r1, byte r3) {
            Memory.pokeByte(r1, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, double r4) {
            zza(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, float r4) {
            zza(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            if (zzfd.zzah() == false) goto L6;
            zzfd.zzd(r1, r2, r4);
            return;
        L6:
            zzfd.zze(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(byte[] r1, long r2, long r4, long r6) {
            Memory.pokeByteArray(r4, r1, (int) r2, (int) r6);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zze(Object r1, long r2, byte r4) {
            if (zzfd.zzah() == false) goto L6;
            zzfd.zzc(r1, r2, r4);
            return;
        L6:
            zzfd.zzd(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final boolean zzl(Object r1, long r2) {
            if (zzfd.zzah() == false) goto L7;
            return zzfd.zzv(r1, r2);
        L7:
            return zzfd.zzw(r1, r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final float zzm(Object r1, long r2) {
            return Float.intBitsToFloat(zzj(r1, r2));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final double zzn(Object r1, long r2) {
            return Double.longBitsToDouble(zzk(r1, r2));
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final byte zzx(Object r1, long r2) {
            if (zzfd.zzah() == false) goto L7;
            return zzfd.zzt(r1, r2);
        L7:
            return zzfd.zzu(r1, r2);
        }
    }

    public static final class zzc extends zzd {
        public zzc(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(long r1, byte r3) {
            this.zzqa.putByte(r1, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, double r4) {
            this.zzqa.putDouble(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, float r4) {
            this.zzqa.putFloat(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(Object r1, long r2, boolean r4) {
            this.zzqa.putBoolean(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zza(byte[] r10, long r11, long r13, long r15) {
            this.zzqa.copyMemory(r10, zzfd.zzej() + r11, null, r13, r15);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final void zze(Object r1, long r2, byte r4) {
            this.zzqa.putByte(r1, r2, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final boolean zzl(Object r1, long r2) {
            return this.zzqa.getBoolean(r1, r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final float zzm(Object r1, long r2) {
            return this.zzqa.getFloat(r1, r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final double zzn(Object r1, long r2) {
            return this.zzqa.getDouble(r1, r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzfd.zzd
        public final byte zzx(Object r1, long r2) {
            return this.zzqa.getByte(r1, r2);
        }
    }

    public static abstract class zzd {
        Unsafe zzqa;

        public zzd(Unsafe r1) {
            this.zzqa = r1;
        }

        public final long zza(Field r1) {
            return this.zzqa.objectFieldOffset(r1);
        }

        public abstract void zza(long r1, byte r3);

        public abstract void zza(Object r1, long r2, double r4);

        public abstract void zza(Object r1, long r2, float r4);

        public final void zza(Object r1, long r2, int r4) {
            this.zzqa.putInt(r1, r2, r4);
        }

        public final void zza(Object r1, long r2, long r4) {
            this.zzqa.putLong(r1, r2, r4);
        }

        public abstract void zza(Object r1, long r2, boolean r4);

        public abstract void zza(byte[] r1, long r2, long r4, long r6);

        public abstract void zze(Object r1, long r2, byte r4);

        public final int zzj(Object r1, long r2) {
            return this.zzqa.getInt(r1, r2);
        }

        public final long zzk(Object r1, long r2) {
            return this.zzqa.getLong(r1, r2);
        }

        public abstract boolean zzl(Object r1, long r2);

        public abstract float zzm(Object r1, long r2);

        public abstract double zzn(Object r1, long r2);

        public abstract byte zzx(Object r1, long r2);
    }

    static {
        logger = Logger.getLogger(zzfd.class.getName());
        Unsafe r0 = zzef();
        zzmh = r0;
        zzfb = zzaw.zzy();
        boolean r1 = zzi(Long.TYPE);
        zzpg = r1;
        boolean r2 = zzi(Integer.TYPE);
        zzph = r2;
        Field r3 = null;
        if (r0 != null) goto L6;
    L4:
        zzd r12 = null;
    L12:
        zzpi = r12;
        zzpj = zzeh();
        zzfy = zzeg();
        zzpk = zzg(byte[].class);
        zzpl = zzg(boolean[].class);
        zzpm = zzh(boolean[].class);
        zzpn = zzg(int[].class);
        zzpo = zzh(int[].class);
        zzpp = zzg(long[].class);
        zzpq = zzh(long[].class);
        zzpr = zzg(float[].class);
        zzps = zzh(float[].class);
        zzpt = zzg(double[].class);
        zzpu = zzh(double[].class);
        zzpv = zzg(Object[].class);
        zzpw = zzh(Object[].class);
        zzpx = zzb(zzei());
        Field r02 = zzb(String.class, "value");
        if (r02 != null) goto L15;
    L17:
        zzpy = zzb(r3);
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) goto L20;
        boolean r03 = true;
    L21:
        zzpz = r03;
        return;
    L20:
        r03 = false;
        goto L21
    L15:
        if (r02.getType() != char[].class) goto L17;
        r3 = r02;
        goto L17
    L6:
        if (zzaw.zzx() == false) goto L11;
        if (r1 == false) goto L9;
        r12 = new zzb(r0);
        goto L12
    L9:
        if (r2 == false) goto L4;
        r12 = new zza(r0);
        goto L12
    L11:
        r12 = new zzc(r0);
        goto L12
    }

    public static byte zza(byte[] r3, long r4) {
        return zzpi.zzx(r3, zzpk + r4);
    }

    public static long zza(Field r2) {
        return zzpi.zza(r2);
    }

    public static void zza(long r1, byte r3) {
        zzpi.zza(r1, r3);
    }

    private static void zza(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r2 = zzj(r4, r0);
        int r52 = ((~((int) r5)) & 3) << 3;
        int r22 = r2 & (~(255 << r52));
        zza(r4, r0, ((255 & r7) << r52) | r22);
    }

    public static void zza(Object r6, long r7, double r9) {
        zzpi.zza(r6, r7, r9);
    }

    public static void zza(Object r1, long r2, float r4) {
        zzpi.zza(r1, r2, r4);
    }

    public static void zza(Object r1, long r2, int r4) {
        zzpi.zza(r1, r2, r4);
    }

    public static void zza(Object r6, long r7, long r9) {
        zzpi.zza(r6, r7, r9);
    }

    public static void zza(Object r1, long r2, Object r4) {
        zzpi.zzqa.putObject(r1, r2, r4);
    }

    public static void zza(Object r1, long r2, boolean r4) {
        zzpi.zza(r1, r2, r4);
    }

    public static void zza(byte[] r3, long r4, byte r6) {
        zzpi.zze(r3, zzpk + r4, r6);
    }

    public static void zza(byte[] r8, long r9, long r11, long r13) {
        zzpi.zza(r8, r9, r11, r13);
    }

    public static /* synthetic */ boolean zzah() {
        return zzpz;
    }

    private static long zzb(Field r2) {
        if (r2 == null) goto L8;
        zzd r0 = zzpi;
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.zza(r2);
    L8:
        return -1;
    }

    public static long zzb(ByteBuffer r3) {
        return zzpi.zzk(r3, zzpx);
    }

    private static Field zzb(Class<?> r0, String r1) {
        Field r02 = r0.getDeclaredField(r1);     // Catch: Throwable -> L4
        r02.setAccessible(true);     // Catch: Throwable -> L4
        return r02;
    L4:
        return null;
    }

    private static void zzb(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r52 = (((int) r5) & 3) << 3;
        int r2 = zzj(r4, r0) & (~(255 << r52));
        zza(r4, r0, ((255 & r7) << r52) | r2);
    }

    private static void zzb(Object r0, long r1, boolean r3) {
        zza(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzc(Object r0, long r1, byte r3) {
        zza(r0, r1, r3);
    }

    private static void zzc(Object r0, long r1, boolean r3) {
        zzb(r0, r1, r3 ? 1 : 0);
    }

    public static /* synthetic */ void zzd(Object r0, long r1, byte r3) {
        zzb(r0, r1, r3);
    }

    public static /* synthetic */ void zzd(Object r0, long r1, boolean r3) {
        zzb(r0, r1, r3);
    }

    public static /* synthetic */ void zze(Object r0, long r1, boolean r3) {
        zzc(r0, r1, r3);
    }

    public static boolean zzed() {
        return zzfy;
    }

    public static boolean zzee() {
        return zzpj;
    }

    public static Unsafe zzef() {
        return (Unsafe) AccessController.doPrivileged(new zzfe());
    L4:
        return null;
    }

    private static boolean zzeg() {
        Unsafe r2 = zzmh;
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
        if (zzaw.zzx() == false) goto L8;
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
        Logger r1 = logger;
        Level r23 = Level.WARNING;
        String r0 = String.valueOf(th);
        StringBuilder r5 = new StringBuilder(r0.length() + 71);
        r5.append("platform method missing - proto runtime falling back to safer methods: ");
        r5.append(r0);
        r1.logp(r23, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", r5.toString());
        return false;
    }

    private static boolean zzeh() {
        Unsafe r3 = zzmh;
        if (r3 != null) goto L16;
        return false;
    L16:
        Class<?> r32 = r3.getClass();     // Catch: Throwable -> L13
        r32.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L13
        Class r5 = Long.TYPE;     // Catch: Throwable -> L13
        r32.getMethod("getLong", new Class[]{Object.class, r5});     // Catch: Throwable -> L13
        if (zzei() != null) goto L9;
        return false;
    L9:
        if (zzaw.zzx() == false) goto L11;
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
        Logger r1 = logger;
        Level r2 = Level.WARNING;
        String r0 = String.valueOf(th);
        StringBuilder r52 = new StringBuilder(r0.length() + 71);
        r52.append("platform method missing - proto runtime falling back to safer methods: ");
        r52.append(r0);
        r1.logp(r2, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", r52.toString());
        return false;
    }

    private static Field zzei() {
        if (zzaw.zzx() == false) goto L7;
        Field r0 = zzb(Buffer.class, "effectiveDirectAddress");
        if (r0 == null) goto L7;
        return r0;
    L7:
        Field r02 = zzb(Buffer.class, "address");
        if (r02 != null) goto L10;
        return null;
    L10:
        if (r02.getType() != Long.TYPE) goto L14;
        return r02;
    L14:
        return null;
    }

    public static /* synthetic */ long zzej() {
        return zzpk;
    }

    private static int zzg(Class<?> r1) {
        if (zzfy == true) goto L5;
        return -1;
    L5:
        return zzpi.zzqa.arrayBaseOffset(r1);
    }

    private static int zzh(Class<?> r1) {
        if (zzfy == true) goto L5;
        return -1;
    L5:
        return zzpi.zzqa.arrayIndexScale(r1);
    }

    private static boolean zzi(Class<?> r7) {
        if (zzaw.zzx() == true) goto L9;
        return false;
    L9:
        Class<?> r1 = zzfb;     // Catch: Throwable -> L8
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

    public static int zzj(Object r1, long r2) {
        return zzpi.zzj(r1, r2);
    }

    public static long zzk(Object r1, long r2) {
        return zzpi.zzk(r1, r2);
    }

    public static boolean zzl(Object r1, long r2) {
        return zzpi.zzl(r1, r2);
    }

    public static float zzm(Object r1, long r2) {
        return zzpi.zzm(r1, r2);
    }

    public static double zzn(Object r1, long r2) {
        return zzpi.zzn(r1, r2);
    }

    public static Object zzo(Object r1, long r2) {
        return zzpi.zzqa.getObject(r1, r2);
    }

    private static byte zzp(Object r2, long r3) {
        return (byte) (zzj(r2, (-4) & r3) >>> ((int) (((~r3) & 3) << 3)));
    }

    private static byte zzq(Object r2, long r3) {
        return (byte) (zzj(r2, (-4) & r3) >>> ((int) ((r3 & 3) << 3)));
    }

    private static boolean zzr(Object r0, long r1) {
        if (zzp(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzs(Object r0, long r1) {
        if (zzq(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ byte zzt(Object r0, long r1) {
        return zzp(r0, r1);
    }

    public static /* synthetic */ byte zzu(Object r0, long r1) {
        return zzq(r0, r1);
    }

    public static /* synthetic */ boolean zzv(Object r0, long r1) {
        return zzr(r0, r1);
    }

    public static /* synthetic */ boolean zzw(Object r0, long r1) {
        return zzs(r0, r1);
    }
}
