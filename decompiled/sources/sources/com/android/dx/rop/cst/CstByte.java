package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class CstByte extends CstLiteral32 {
    public static final CstByte VALUE_0 = null;

    static {
        VALUE_0 = make((byte) 0);
    }

    private CstByte(byte r1) {
        super(r1);
    }

    public static CstByte make(byte r1) {
        return new CstByte(r1);
    }

    public static CstByte make(int r1) {
        byte r0 = (byte) r1;
        if (r0 == r1) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("bogus byte value: ", r1);
        return null;
    L5:
        return make(r0);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.BYTE;
    }

    public byte getValue() {
        return (byte) getIntBits();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int r2 = getIntBits();
        return "byte{0x" + Hex.u1(r2) + " / " + r2 + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "byte";
    }
}
