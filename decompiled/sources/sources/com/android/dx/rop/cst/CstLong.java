package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class CstLong extends CstLiteral64 {
    public static final CstLong VALUE_0 = null;
    public static final CstLong VALUE_1 = null;

    static {
        VALUE_0 = make(0);
        VALUE_1 = make(1);
    }

    private CstLong(long r1) {
        super(r1);
    }

    public static CstLong make(long r1) {
        return new CstLong(r1);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.LONG;
    }

    public long getValue() {
        return getLongBits();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Long.toString(getLongBits());
    }

    public String toString() {
        long r0 = getLongBits();
        return "long{0x" + Hex.u8(r0) + " / " + r0 + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "long";
    }
}
