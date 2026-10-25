package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class CstFloat extends CstLiteral32 {
    public static final CstFloat VALUE_0 = null;
    public static final CstFloat VALUE_1 = null;
    public static final CstFloat VALUE_2 = null;

    static {
        VALUE_0 = make(Float.floatToIntBits(0.0f));
        VALUE_1 = make(Float.floatToIntBits(1.0f));
        VALUE_2 = make(Float.floatToIntBits(2.0f));
    }

    private CstFloat(int r1) {
        super(r1);
    }

    public static CstFloat make(int r1) {
        return new CstFloat(r1);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.FLOAT;
    }

    public float getValue() {
        return Float.intBitsToFloat(getIntBits());
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Float.toString(Float.intBitsToFloat(getIntBits()));
    }

    public String toString() {
        int r2 = getIntBits();
        return "float{0x" + Hex.u4(r2) + " / " + Float.intBitsToFloat(r2) + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "float";
    }
}
