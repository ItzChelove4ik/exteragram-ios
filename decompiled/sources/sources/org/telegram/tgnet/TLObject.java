package org.telegram.tgnet;

import me.vkryl.core.BitwiseUtils;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public class TLObject {
    public static final int FLAG_0 = 1;
    public static final int FLAG_1 = 2;
    public static final int FLAG_10 = 1024;
    public static final int FLAG_11 = 2048;
    public static final int FLAG_12 = 4096;
    public static final int FLAG_13 = 8192;
    public static final int FLAG_14 = 16384;
    public static final int FLAG_15 = 32768;
    public static final int FLAG_16 = 65536;
    public static final int FLAG_17 = 131072;
    public static final int FLAG_18 = 262144;
    public static final int FLAG_19 = 524288;
    public static final int FLAG_2 = 4;
    public static final int FLAG_20 = 1048576;
    public static final int FLAG_21 = 2097152;
    public static final int FLAG_22 = 4194304;
    public static final int FLAG_23 = 8388608;
    public static final int FLAG_24 = 16777216;
    public static final int FLAG_25 = 33554432;
    public static final int FLAG_26 = 67108864;
    public static final int FLAG_27 = 134217728;
    public static final int FLAG_28 = 268435456;
    public static final int FLAG_29 = 536870912;
    public static final int FLAG_3 = 8;
    public static final int FLAG_30 = 1073741824;
    public static final int FLAG_31 = Integer.MIN_VALUE;
    public static final int FLAG_4 = 16;
    public static final int FLAG_5 = 32;
    public static final int FLAG_6 = 64;
    public static final int FLAG_7 = 128;
    public static final int FLAG_8 = 256;
    public static final int FLAG_9 = 512;
    private static final ThreadLocal<NativeByteBuffer> sizeCalculator = null;
    public boolean disableFree;
    public int networkType;

    /* renamed from: org.telegram.tgnet.TLObject$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<NativeByteBuffer> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ NativeByteBuffer initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public NativeByteBuffer initialValue2() {
            return new NativeByteBuffer(true);
        }
    }

    static {
        sizeCalculator = new AnonymousClass1();
    }

    public TLObject() {
        this.disableFree = false;
    }

    public static <T extends TLObject> T TLdeserialize(Class<T> r0, T r1, InputSerializedData r2, int r3, boolean r4) {
        if (r1 != null) goto L5;
        TLParseException.doThrowOrLog(r2, r0.getName(), r3, r4);
        return null;
    L5:
        r1.readParams(r2, r4);
        return r1;
    }

    public static <T extends TLObject> T deepCopy(T r2, Vector.TLDeserializer<T> r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        SerializedData r0 = new SerializedData(r2.getObjectSize());
        r2.serializeToStream(r0);
        SerializedData r22 = new SerializedData(r0.toByteArray());
        return (T) r3.deserialize(r22, r22.readInt32(false), false);
    }

    public static boolean hasFlag(int r0, int r1) {
        return BitwiseUtils.hasFlag(r0, r1);
    }

    public static int setFlag(int r0, int r1, boolean r2) {
        return BitwiseUtils.setFlag(r0, r1, r2);
    }

    public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
        return null;
    }

    public void freeResources() {
    }

    public int getObjectSize() {
        ThreadLocal<NativeByteBuffer> r0 = sizeCalculator;
        NativeByteBuffer r1 = r0.get();
        r1.rewind();
        serializeToStream(r0.get());
        return r1.length();
    }

    public void readParams(InputSerializedData r1, boolean r2) {
    }

    public void serializeToStream(OutputSerializedData r1) {
    }
}
