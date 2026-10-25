package com.sun.jna;

import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes5.dex */
public abstract class IntegerType extends Number implements NativeMapped {
    private static final long serialVersionUID = 1;
    private Number number;
    private int size;
    private boolean unsigned;
    private long value;

    public IntegerType(int r4) {
        this(r4, 0, false);
    }

    public IntegerType(int r2, long r3) {
        this(r2, r3, false);
    }

    public IntegerType(int r1, long r2, boolean r4) {
        this.size = r1;
        this.unsigned = r4;
        setValue(r2);
    }

    public IntegerType(int r3, boolean r4) {
        this(r3, 0, r4);
    }

    public static final int compare(long r0, long r2) {
        if (r0 != r2) goto L6;
        return 0;
    L6:
        if (r0 >= r2) goto L9;
        return -1;
    L9:
        return 1;
    }

    public static int compare(IntegerType r2, long r3) {
        if (r2 != null) goto L6;
        return 1;
    L6:
        return compare(r2.longValue(), r3);
    }

    public static <T extends IntegerType> int compare(T r2, T r3) {
        if (r2 != r3) goto L5;
        return 0;
    L5:
        if (r2 != null) goto L8;
        return 1;
    L8:
        if (r3 != null) goto L12;
        return -1;
    L12:
        return compare(r2.longValue(), r3.longValue());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.number.doubleValue();
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof IntegerType) == true) goto L5;
        return false;
    L5:
        if (this.number.equals(((IntegerType) r2).number) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.number.floatValue();
    }

    @Override // com.sun.jna.NativeMapped
    public Object fromNative(Object r1, FromNativeContext r2) {
        if (r1 != null) goto L4;
        long r12 = 0;
    L5:
        IntegerType r0 = (IntegerType) Klass.newInstance(getClass());
        r0.setValue(r12);
        return r0;
    L4:
        r12 = ((Number) r1).longValue();
        goto L5
    }

    public int hashCode() {
        return this.number.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // com.sun.jna.NativeMapped
    public Class<?> nativeType() {
        return this.number.getClass();
    }

    public void setValue(long r8) {
        this.value = r8;
        int r0 = this.size;
        if (r0 == 1) goto L21;
        if (r0 == 2) goto L17;
        if (r0 == 4) goto L13;
        if (r0 != 8) goto L10;
        this.number = Long.valueOf(r8);
        long r3 = r8;
    L24:
        int r02 = this.size;
        if (r02 >= 8) goto L36;
        long r03 = ~((1 << (r02 * 8)) - 1);
        if (r8 < 0) goto L29;
    L30:
        if (r8 >= 0) goto L32;
        return;
    L32:
        if ((r03 & r8) != 0) goto L35;
        return;
    L35:
        throw new IllegalArgumentException("Argument value 0x" + Long.toHexString(r8) + " exceeds native capacity (" + this.size + " bytes) mask=0x" + Long.toHexString(r03));
    L29:
        if (r3 != r8) goto L35;
    L36:
        return;
    L10:
        IntegerType$$ExternalSyntheticBUOutline0.m("Unsupported size: ", this.size);
        return;
    L13:
        if (this.unsigned == false) goto L15;
        this.value = 4294967295L & r8;
    L15:
        int r04 = (int) r8;
        r3 = r04;
        this.number = Integer.valueOf(r04);
        goto L24
    L17:
        if (this.unsigned == false) goto L19;
        this.value = WebSocketProtocol.PAYLOAD_SHORT_MAX & r8;
    L19:
        short r05 = (short) r8;
        r3 = r05;
        this.number = Short.valueOf(r05);
        goto L24
    L21:
        if (this.unsigned == false) goto L23;
        this.value = 255 & r8;
    L23:
        byte r06 = (byte) r8;
        r3 = r06;
        this.number = Byte.valueOf(r06);
        goto L24
    }

    @Override // com.sun.jna.NativeMapped
    public Object toNative() {
        return this.number;
    }

    public String toString() {
        return this.number.toString();
    }
}
