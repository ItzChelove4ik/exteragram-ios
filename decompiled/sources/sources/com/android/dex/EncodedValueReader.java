package com.android.dex;

import com.android.dex.util.ByteInput;
import com.android.dx.merge.DexMerger$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class EncodedValueReader {
    private int annotationType;
    private int arg;
    protected final ByteInput in;
    private int type;

    public EncodedValueReader(EncodedValue r1) {
        this(r1.asByteInput());
    }

    public EncodedValueReader(EncodedValue r1, int r2) {
        this(r1.asByteInput(), r2);
    }

    public EncodedValueReader(ByteInput r2) {
        this.type = -1;
        this.in = r2;
    }

    public EncodedValueReader(ByteInput r1, int r2) {
        this.in = r1;
        this.type = r2;
    }

    private void checkType(int r2) {
        if (peek() != r2) goto L5;
        return;
    L5:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Expected %x but was %x", new Object[]{Integer.valueOf(r2), Integer.valueOf(peek())});
    }

    public int getAnnotationType() {
        return this.annotationType;
    }

    public int peek() {
        if (this.type != (-1)) goto L6;
        byte r0 = this.in.readByte();
        this.type = r0 & 31;
        this.arg = (r0 & 224) >> 5;
    L6:
        return this.type;
    }

    public int readAnnotation() {
        checkType(29);
        this.type = -1;
        this.annotationType = Leb128.readUnsignedLeb128(this.in);
        return Leb128.readUnsignedLeb128(this.in);
    }

    public int readAnnotationName() {
        return Leb128.readUnsignedLeb128(this.in);
    }

    public int readArray() {
        checkType(28);
        this.type = -1;
        return Leb128.readUnsignedLeb128(this.in);
    }

    public boolean readBoolean() {
        checkType(31);
        this.type = -1;
        if (this.arg == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public byte readByte() {
        checkType(0);
        this.type = -1;
        return (byte) EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public char readChar() {
        checkType(3);
        this.type = -1;
        return (char) EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public double readDouble() {
        checkType(17);
        this.type = -1;
        return Double.longBitsToDouble(EncodedValueCodec.readUnsignedLong(this.in, this.arg, true));
    }

    public int readEnum() {
        checkType(27);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public int readField() {
        checkType(25);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public float readFloat() {
        checkType(16);
        this.type = -1;
        return Float.intBitsToFloat(EncodedValueCodec.readUnsignedInt(this.in, this.arg, true));
    }

    public int readInt() {
        checkType(4);
        this.type = -1;
        return EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public long readLong() {
        checkType(6);
        this.type = -1;
        return EncodedValueCodec.readSignedLong(this.in, this.arg);
    }

    public int readMethod() {
        checkType(26);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public int readMethodHandle() {
        checkType(22);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public int readMethodType() {
        checkType(21);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public void readNull() {
        checkType(30);
        this.type = -1;
    }

    public short readShort() {
        checkType(2);
        this.type = -1;
        return (short) EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public int readString() {
        checkType(23);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public int readType() {
        checkType(24);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, false);
    }

    public void skipValue() {
        int r0 = peek();
        if (r0 != 0) goto L5;
        readByte();
        return;
    L5:
        if (r0 != 6) goto L7;
        readLong();
        return;
    L7:
        if (r0 != 2) goto L9;
        readShort();
        return;
    L9:
        if (r0 != 3) goto L11;
        readChar();
        return;
    L11:
        if (r0 != 4) goto L13;
        readInt();
        return;
    L13:
        if (r0 != 16) goto L15;
        readFloat();
        return;
    L15:
        if (r0 == 17) goto L45;
        int r1 = 0;
        switch(r0) {
            case 21: goto L43;
            case 22: goto L41;
            case 23: goto L39;
            case 24: goto L37;
            case 25: goto L35;
            case 26: goto L33;
            case 27: goto L31;
            case 28: goto L27;
            case 29: goto L24;
            case 30: goto L22;
            case 31: goto L20;
            default: goto L18;
        };
    L18:
        DexMerger$$ExternalSyntheticBUOutline0.m("Unexpected type: ", Integer.toHexString(this.type));
        return;
    L20:
        readBoolean();
        return;
    L22:
        readNull();
        return;
    L24:
        int r02 = readAnnotation();
    L25:
        if (r1 >= r02) goto L30;
        readAnnotationName();
        skipValue();
        r1 = r1 + 1;
        goto L25
    L30:
        return;
    L27:
        int r03 = readArray();
    L28:
        if (r1 >= r03) goto L61;
        skipValue();
        r1 = r1 + 1;
        goto L28
    L61:
        return;
    L31:
        readEnum();
        return;
    L33:
        readMethod();
        return;
    L35:
        readField();
        return;
    L37:
        readType();
        return;
    L39:
        readString();
        return;
    L41:
        readMethodHandle();
        return;
    L43:
        readMethodType();
        return;
    L45:
        readDouble();
    }
}
