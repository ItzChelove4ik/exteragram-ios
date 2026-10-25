package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class CstDouble extends CstLiteral64 {
    public static final CstDouble VALUE_0 = null;
    public static final CstDouble VALUE_1 = null;

    static {
        VALUE_0 = new CstDouble(Double.doubleToLongBits(0.0d));
        VALUE_1 = new CstDouble(Double.doubleToLongBits(1.0d));
    }

    private CstDouble(long r1) {
        super(r1);
    }

    public static CstDouble make(long r1) {
        return new CstDouble(r1);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.DOUBLE;
    }

    public double getValue() {
        return Double.longBitsToDouble(getLongBits());
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Double.toString(Double.longBitsToDouble(getLongBits()));
    }

    public String toString() {
        long r0 = getLongBits();
        return "double{0x" + Hex.u8(r0) + " / " + Double.longBitsToDouble(r0) + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "double";
    }
}
