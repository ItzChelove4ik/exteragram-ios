package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class CstShort extends CstLiteral32 {
    public static final CstShort VALUE_0 = null;

    static {
        VALUE_0 = make(0);
    }

    private CstShort(short r1) {
        super(r1);
    }

    public static CstShort make(int r1) {
        short r0 = (short) r1;
        if (r0 == r1) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("bogus short value: ", r1);
        return null;
    L5:
        return make(r0);
    }

    public static CstShort make(short r1) {
        return new CstShort(r1);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.SHORT;
    }

    public short getValue() {
        return (short) getIntBits();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int r2 = getIntBits();
        return "short{0x" + Hex.u2(r2) + " / " + r2 + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "short";
    }
}
