package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
abstract class UnsafeUtil {
    private static final long BOOLEAN_ARRAY_BASE_OFFSET = 0;
    private static final long BOOLEAN_ARRAY_INDEX_SCALE = 0;
    private static final long BUFFER_ADDRESS_OFFSET = 0;
    private static final int BYTE_ARRAY_ALIGNMENT = 0;
    static final long BYTE_ARRAY_BASE_OFFSET = 0;
    private static final long DOUBLE_ARRAY_BASE_OFFSET = 0;
    private static final long DOUBLE_ARRAY_INDEX_SCALE = 0;
    private static final long FLOAT_ARRAY_BASE_OFFSET = 0;
    private static final long FLOAT_ARRAY_INDEX_SCALE = 0;
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = false;
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = false;
    private static final long INT_ARRAY_BASE_OFFSET = 0;
    private static final long INT_ARRAY_INDEX_SCALE = 0;
    private static final boolean IS_ANDROID_32 = false;
    private static final boolean IS_ANDROID_64 = false;
    static final boolean IS_BIG_ENDIAN = false;
    private static final long LONG_ARRAY_BASE_OFFSET = 0;
    private static final long LONG_ARRAY_INDEX_SCALE = 0;
    private static final MemoryAccessor MEMORY_ACCESSOR = null;
    private static final Class<?> MEMORY_CLASS = null;
    private static final long OBJECT_ARRAY_BASE_OFFSET = 0;
    private static final long OBJECT_ARRAY_INDEX_SCALE = 0;
    private static final Unsafe UNSAFE = null;

    /* renamed from: com.google.protobuf.UnsafeUtil$1, reason: invalid class name */
    public class AnonymousClass1 implements PrivilegedExceptionAction<Unsafe> {
        public AnonymousClass1() {
        }

        @Override // java.security.PrivilegedExceptionAction
        public /* bridge */ /* synthetic */ Unsafe run() {
            return run2();
        }

        @Override // java.security.PrivilegedExceptionAction
        /* renamed from: run, reason: avoid collision after fix types in other method */
        public Unsafe run2() {
            Field[] r0 = Unsafe.class.getDeclaredFields();
            int r1 = r0.length;
            int r2 = 0;
        L4:
            if (r2 >= r1) goto L10;
            Field r4 = r0[r2];
            r4.setAccessible(true);
            Object r3 = r4.get(null);
            if (Unsafe.class.isInstance(r3) == true) goto L8;
            r2 = r2 + 1;
            goto L4
        L8:
            return (Unsafe) Unsafe.class.cast(r3);
        L10:
            return null;
        }
    }

    public static final class Android32MemoryAccessor extends MemoryAccessor {
        public Android32MemoryAccessor(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long r1, byte[] r3, long r4, long r6) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object r1, long r2) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L7;
            return UnsafeUtil.access$600(r1, r2);
        L7:
            return UnsafeUtil.access$700(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long r1) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object r1, long r2) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L7;
            return UnsafeUtil.access$200(r1, r2);
        L7:
            return UnsafeUtil.access$300(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object r1, long r2) {
            return Double.longBitsToDouble(getLong(r1, r2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object r1, long r2) {
            return Float.intBitsToFloat(getInt(r1, r2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long r1) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object r1, long r2, boolean r4) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L6;
            UnsafeUtil.access$800(r1, r2, r4);
            return;
        L6:
            UnsafeUtil.access$900(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object r1, long r2, byte r4) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L6;
            UnsafeUtil.access$400(r1, r2, r4);
            return;
        L6:
            UnsafeUtil.access$500(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object r1, long r2, double r4) {
            putLong(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object r1, long r2, float r4) {
            putInt(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }
    }

    public static final class Android64MemoryAccessor extends MemoryAccessor {
        public Android64MemoryAccessor(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long r1, byte[] r3, long r4, long r6) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object r1, long r2) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L7;
            return UnsafeUtil.access$600(r1, r2);
        L7:
            return UnsafeUtil.access$700(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long r1) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object r1, long r2) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L7;
            return UnsafeUtil.access$200(r1, r2);
        L7:
            return UnsafeUtil.access$300(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object r1, long r2) {
            return Double.longBitsToDouble(getLong(r1, r2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object r1, long r2) {
            return Float.intBitsToFloat(getInt(r1, r2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long r1) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object r1, long r2, boolean r4) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L6;
            UnsafeUtil.access$800(r1, r2, r4);
            return;
        L6:
            UnsafeUtil.access$900(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object r1, long r2, byte r4) {
            if (UnsafeUtil.IS_BIG_ENDIAN == false) goto L6;
            UnsafeUtil.access$400(r1, r2, r4);
            return;
        L6:
            UnsafeUtil.access$500(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object r1, long r2, double r4) {
            putLong(r1, r2, Double.doubleToLongBits(r4));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object r1, long r2, float r4) {
            putInt(r1, r2, Float.floatToIntBits(r4));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }
    }

    public static final class JvmMemoryAccessor extends MemoryAccessor {
        public JvmMemoryAccessor(Unsafe r1) {
            super(r1);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long r10, byte[] r12, long r13, long r15) {
            this.unsafe.copyMemory(null, r10, r12, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + r13, r15);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object r1, long r2) {
            return this.unsafe.getBoolean(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long r1) {
            return this.unsafe.getByte(r1);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object r1, long r2) {
            return this.unsafe.getByte(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object r1, long r2) {
            return this.unsafe.getDouble(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object r1, long r2) {
            return this.unsafe.getFloat(r1, r2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long r1) {
            return this.unsafe.getLong(r1);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object r1, long r2, boolean r4) {
            this.unsafe.putBoolean(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object r1, long r2, byte r4) {
            this.unsafe.putByte(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object r1, long r2, double r4) {
            this.unsafe.putDouble(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object r1, long r2, float r4) {
            this.unsafe.putFloat(r1, r2, r4);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeArrayOperations() {
            if (super.supportsUnsafeArrayOperations() == true) goto L11;
            return false;
        L11:
            Class<?> r5 = this.unsafe.getClass();     // Catch: Throwable -> L8
            Class r3 = Long.TYPE;     // Catch: Throwable -> L8
            r5.getMethod("getByte", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r5.getMethod("putByte", new Class[]{Object.class, r3, Byte.TYPE});     // Catch: Throwable -> L8
            r5.getMethod("getBoolean", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r5.getMethod("putBoolean", new Class[]{Object.class, r3, Boolean.TYPE});     // Catch: Throwable -> L8
            r5.getMethod("getFloat", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r5.getMethod("putFloat", new Class[]{Object.class, r3, Float.TYPE});     // Catch: Throwable -> L8
            r5.getMethod("getDouble", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r5.getMethod("putDouble", new Class[]{Object.class, r3, Double.TYPE});     // Catch: Throwable -> L8
            return true;
        L8:
            th = move-exception;
            UnsafeUtil.access$000(th);
            return false;
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            if (super.supportsUnsafeByteBufferOperations() == true) goto L11;
            return false;
        L11:
            Class<?> r6 = this.unsafe.getClass();     // Catch: Throwable -> L8
            Class r4 = Long.TYPE;     // Catch: Throwable -> L8
            r6.getMethod("getByte", new Class[]{r4});     // Catch: Throwable -> L8
            r6.getMethod("putByte", new Class[]{r4, Byte.TYPE});     // Catch: Throwable -> L8
            r6.getMethod("getInt", new Class[]{r4});     // Catch: Throwable -> L8
            r6.getMethod("putInt", new Class[]{r4, Integer.TYPE});     // Catch: Throwable -> L8
            r6.getMethod("getLong", new Class[]{r4});     // Catch: Throwable -> L8
            r6.getMethod("putLong", new Class[]{r4, r4});     // Catch: Throwable -> L8
            r6.getMethod("copyMemory", new Class[]{r4, r4, r4});     // Catch: Throwable -> L8
            r6.getMethod("copyMemory", new Class[]{Object.class, r4, Object.class, r4, r4});     // Catch: Throwable -> L8
            return true;
        L8:
            th = move-exception;
            UnsafeUtil.access$000(th);
            return false;
        }
    }

    public static abstract class MemoryAccessor {
        Unsafe unsafe;

        public MemoryAccessor(Unsafe r1) {
            this.unsafe = r1;
        }

        public final int arrayBaseOffset(Class<?> r1) {
            return this.unsafe.arrayBaseOffset(r1);
        }

        public final int arrayIndexScale(Class<?> r1) {
            return this.unsafe.arrayIndexScale(r1);
        }

        public abstract void copyMemory(long r1, byte[] r3, long r4, long r6);

        public abstract boolean getBoolean(Object r1, long r2);

        public abstract byte getByte(long r1);

        public abstract byte getByte(Object r1, long r2);

        public abstract double getDouble(Object r1, long r2);

        public abstract float getFloat(Object r1, long r2);

        public final int getInt(Object r1, long r2) {
            return this.unsafe.getInt(r1, r2);
        }

        public abstract long getLong(long r1);

        public final long getLong(Object r1, long r2) {
            return this.unsafe.getLong(r1, r2);
        }

        public final Object getObject(Object r1, long r2) {
            return this.unsafe.getObject(r1, r2);
        }

        public final long objectFieldOffset(Field r1) {
            return this.unsafe.objectFieldOffset(r1);
        }

        public abstract void putBoolean(Object r1, long r2, boolean r4);

        public abstract void putByte(Object r1, long r2, byte r4);

        public abstract void putDouble(Object r1, long r2, double r4);

        public abstract void putFloat(Object r1, long r2, float r4);

        public final void putInt(Object r1, long r2, int r4) {
            this.unsafe.putInt(r1, r2, r4);
        }

        public final void putLong(Object r1, long r2, long r4) {
            this.unsafe.putLong(r1, r2, r4);
        }

        public final void putObject(Object r1, long r2, Object r4) {
            this.unsafe.putObject(r1, r2, r4);
        }

        public boolean supportsUnsafeArrayOperations() {
            Unsafe r5 = this.unsafe;
            if (r5 != null) goto L11;
            return false;
        L11:
            Class<?> r52 = r5.getClass();     // Catch: Throwable -> L8
            r52.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L8
            r52.getMethod("arrayBaseOffset", new Class[]{Class.class});     // Catch: Throwable -> L8
            r52.getMethod("arrayIndexScale", new Class[]{Class.class});     // Catch: Throwable -> L8
            Class r3 = Long.TYPE;     // Catch: Throwable -> L8
            r52.getMethod("getInt", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r52.getMethod("putInt", new Class[]{Object.class, r3, Integer.TYPE});     // Catch: Throwable -> L8
            r52.getMethod("getLong", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r52.getMethod("putLong", new Class[]{Object.class, r3, r3});     // Catch: Throwable -> L8
            r52.getMethod("getObject", new Class[]{Object.class, r3});     // Catch: Throwable -> L8
            r52.getMethod("putObject", new Class[]{Object.class, r3, Object.class});     // Catch: Throwable -> L8
            return true;
        L8:
            th = move-exception;
            UnsafeUtil.access$000(th);
            return false;
        }

        public boolean supportsUnsafeByteBufferOperations() {
            Unsafe r4 = this.unsafe;
            if (r4 != null) goto L13;
            return false;
        L13:
            Class<?> r42 = r4.getClass();     // Catch: Throwable -> L10
            r42.getMethod("objectFieldOffset", new Class[]{Field.class});     // Catch: Throwable -> L10
            r42.getMethod("getLong", new Class[]{Object.class, Long.TYPE});     // Catch: Throwable -> L10
            if (UnsafeUtil.access$100() != null) goto L8;
            return false;
        L8:
            return true;
        L10:
            th = move-exception;
            UnsafeUtil.access$000(th);
            return false;
        }
    }

    static {
        UNSAFE = getUnsafe();
        MEMORY_CLASS = Android.getMemoryClass();
        IS_ANDROID_64 = determineAndroidSupportByAddressSize(Long.TYPE);
        IS_ANDROID_32 = determineAndroidSupportByAddressSize(Integer.TYPE);
        MEMORY_ACCESSOR = getMemoryAccessor();
        HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
        HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
        long r0 = arrayBaseOffset(byte[].class);
        BYTE_ARRAY_BASE_OFFSET = r0;
        BOOLEAN_ARRAY_BASE_OFFSET = arrayBaseOffset(boolean[].class);
        BOOLEAN_ARRAY_INDEX_SCALE = arrayIndexScale(boolean[].class);
        INT_ARRAY_BASE_OFFSET = arrayBaseOffset(int[].class);
        INT_ARRAY_INDEX_SCALE = arrayIndexScale(int[].class);
        LONG_ARRAY_BASE_OFFSET = arrayBaseOffset(long[].class);
        LONG_ARRAY_INDEX_SCALE = arrayIndexScale(long[].class);
        FLOAT_ARRAY_BASE_OFFSET = arrayBaseOffset(float[].class);
        FLOAT_ARRAY_INDEX_SCALE = arrayIndexScale(float[].class);
        DOUBLE_ARRAY_BASE_OFFSET = arrayBaseOffset(double[].class);
        DOUBLE_ARRAY_INDEX_SCALE = arrayIndexScale(double[].class);
        OBJECT_ARRAY_BASE_OFFSET = arrayBaseOffset(Object[].class);
        OBJECT_ARRAY_INDEX_SCALE = arrayIndexScale(Object[].class);
        BUFFER_ADDRESS_OFFSET = fieldOffset(bufferAddressField());
        BYTE_ARRAY_ALIGNMENT = (int) (r0 & 7);
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) goto L5;
        boolean r02 = true;
    L6:
        IS_BIG_ENDIAN = r02;
        return;
    L5:
        r02 = false;
        goto L6
    }

    public static /* synthetic */ void access$000(Throwable r0) {
        logMissingMethod(r0);
    }

    public static /* synthetic */ Field access$100() {
        return bufferAddressField();
    }

    public static /* synthetic */ byte access$200(Object r0, long r1) {
        return getByteBigEndian(r0, r1);
    }

    public static /* synthetic */ byte access$300(Object r0, long r1) {
        return getByteLittleEndian(r0, r1);
    }

    public static /* synthetic */ void access$400(Object r0, long r1, byte r3) {
        putByteBigEndian(r0, r1, r3);
    }

    public static /* synthetic */ void access$500(Object r0, long r1, byte r3) {
        putByteLittleEndian(r0, r1, r3);
    }

    public static /* synthetic */ boolean access$600(Object r0, long r1) {
        return getBooleanBigEndian(r0, r1);
    }

    public static /* synthetic */ boolean access$700(Object r0, long r1) {
        return getBooleanLittleEndian(r0, r1);
    }

    public static /* synthetic */ void access$800(Object r0, long r1, boolean r3) {
        putBooleanBigEndian(r0, r1, r3);
    }

    public static /* synthetic */ void access$900(Object r0, long r1, boolean r3) {
        putBooleanLittleEndian(r0, r1, r3);
    }

    public static long addressOffset(ByteBuffer r3) {
        return MEMORY_ACCESSOR.getLong(r3, BUFFER_ADDRESS_OFFSET);
    }

    public static <T> T allocateInstance(Class<T> r1) {
        return (T) UNSAFE.allocateInstance(r1);
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private static int arrayBaseOffset(Class<?> r1) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS == true) goto L5;
        return -1;
    L5:
        return MEMORY_ACCESSOR.arrayBaseOffset(r1);
    }

    private static int arrayIndexScale(Class<?> r1) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS == true) goto L5;
        return -1;
    L5:
        return MEMORY_ACCESSOR.arrayIndexScale(r1);
    }

    private static Field bufferAddressField() {
        if (Android.isOnAndroidDevice() == false) goto L7;
        Field r0 = field(Buffer.class, "effectiveDirectAddress");
        if (r0 == null) goto L7;
        return r0;
    L7:
        Field r02 = field(Buffer.class, "address");
        if (r02 != null) goto L10;
        return null;
    L10:
        if (r02.getType() != Long.TYPE) goto L14;
        return r02;
    L14:
        return null;
    }

    public static void copyMemory(long r8, byte[] r10, long r11, long r13) {
        MEMORY_ACCESSOR.copyMemory(r8, r10, r11, r13);
    }

    public static boolean determineAndroidSupportByAddressSize(Class<?> r7) {
        if (Android.isOnAndroidDevice() == true) goto L9;
        return false;
    L9:
        Class<?> r1 = MEMORY_CLASS;     // Catch: Throwable -> L8
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

    private static Field field(Class<?> r0, String r1) {
        return r0.getDeclaredField(r1);
    L4:
        return null;
    }

    private static long fieldOffset(Field r2) {
        if (r2 == null) goto L8;
        MemoryAccessor r0 = MEMORY_ACCESSOR;
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.objectFieldOffset(r2);
    L8:
        return -1;
    }

    public static boolean getBoolean(Object r1, long r2) {
        return MEMORY_ACCESSOR.getBoolean(r1, r2);
    }

    private static boolean getBooleanBigEndian(Object r0, long r1) {
        if (getByteBigEndian(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean getBooleanLittleEndian(Object r0, long r1) {
        if (getByteLittleEndian(r0, r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static byte getByte(long r1) {
        return MEMORY_ACCESSOR.getByte(r1);
    }

    public static byte getByte(byte[] r3, long r4) {
        return MEMORY_ACCESSOR.getByte(r3, BYTE_ARRAY_BASE_OFFSET + r4);
    }

    private static byte getByteBigEndian(Object r2, long r3) {
        return (byte) ((getInt(r2, (-4) & r3) >>> ((int) (((~r3) & 3) << 3))) & 255);
    }

    private static byte getByteLittleEndian(Object r2, long r3) {
        return (byte) ((getInt(r2, (-4) & r3) >>> ((int) ((r3 & 3) << 3))) & 255);
    }

    public static double getDouble(Object r1, long r2) {
        return MEMORY_ACCESSOR.getDouble(r1, r2);
    }

    public static float getFloat(Object r1, long r2) {
        return MEMORY_ACCESSOR.getFloat(r1, r2);
    }

    public static int getInt(Object r1, long r2) {
        return MEMORY_ACCESSOR.getInt(r1, r2);
    }

    public static long getLong(long r1) {
        return MEMORY_ACCESSOR.getLong(r1);
    }

    public static long getLong(Object r1, long r2) {
        return MEMORY_ACCESSOR.getLong(r1, r2);
    }

    private static MemoryAccessor getMemoryAccessor() {
        Unsafe r0 = UNSAFE;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (Android.isOnAndroidDevice() == false) goto L16;
        if (IS_ANDROID_64 == false) goto L12;
        return new Android64MemoryAccessor(r0);
    L12:
        if (IS_ANDROID_32 == true) goto L14;
        return null;
    L14:
        return new Android32MemoryAccessor(r0);
    L16:
        return new JvmMemoryAccessor(r0);
    }

    public static Object getObject(Object r1, long r2) {
        return MEMORY_ACCESSOR.getObject(r1, r2);
    }

    public static Unsafe getUnsafe() {
        return (Unsafe) AccessController.doPrivileged(new AnonymousClass1());
    L4:
        return null;
    }

    public static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    public static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    private static void logMissingMethod(Throwable r4) {
        Logger.getLogger(UnsafeUtil.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + r4);
    }

    public static void putBoolean(Object r1, long r2, boolean r4) {
        MEMORY_ACCESSOR.putBoolean(r1, r2, r4);
    }

    private static void putBooleanBigEndian(Object r0, long r1, boolean r3) {
        putByteBigEndian(r0, r1, r3 ? 1 : 0);
    }

    private static void putBooleanLittleEndian(Object r0, long r1, boolean r3) {
        putByteLittleEndian(r0, r1, r3 ? 1 : 0);
    }

    public static void putByte(byte[] r3, long r4, byte r6) {
        MEMORY_ACCESSOR.putByte(r3, BYTE_ARRAY_BASE_OFFSET + r4, r6);
    }

    private static void putByteBigEndian(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r2 = getInt(r4, r0);
        int r52 = ((~((int) r5)) & 3) << 3;
        int r22 = r2 & (~(255 << r52));
        putInt(r4, r0, ((255 & r7) << r52) | r22);
    }

    private static void putByteLittleEndian(Object r4, long r5, byte r7) {
        long r0 = (-4) & r5;
        int r52 = (((int) r5) & 3) << 3;
        int r2 = getInt(r4, r0) & (~(255 << r52));
        putInt(r4, r0, ((255 & r7) << r52) | r2);
    }

    public static void putDouble(Object r6, long r7, double r9) {
        MEMORY_ACCESSOR.putDouble(r6, r7, r9);
    }

    public static void putFloat(Object r1, long r2, float r4) {
        MEMORY_ACCESSOR.putFloat(r1, r2, r4);
    }

    public static void putInt(Object r1, long r2, int r4) {
        MEMORY_ACCESSOR.putInt(r1, r2, r4);
    }

    public static void putLong(Object r6, long r7, long r9) {
        MEMORY_ACCESSOR.putLong(r6, r7, r9);
    }

    public static void putObject(Object r1, long r2, Object r4) {
        MEMORY_ACCESSOR.putObject(r1, r2, r4);
    }

    private static boolean supportsUnsafeArrayOperations() {
        MemoryAccessor r0 = MEMORY_ACCESSOR;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.supportsUnsafeArrayOperations();
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        MemoryAccessor r0 = MEMORY_ACCESSOR;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.supportsUnsafeByteBufferOperations();
    }
}
