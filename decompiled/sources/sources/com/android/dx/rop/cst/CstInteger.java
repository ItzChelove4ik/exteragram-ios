package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class CstInteger extends CstLiteral32 {
    public static final CstInteger VALUE_0 = null;
    public static final CstInteger VALUE_1 = null;
    public static final CstInteger VALUE_2 = null;
    public static final CstInteger VALUE_3 = null;
    public static final CstInteger VALUE_4 = null;
    public static final CstInteger VALUE_5 = null;
    public static final CstInteger VALUE_M1 = null;
    private static final CstInteger[] cache = null;

    static {
        cache = new CstInteger[511];
        VALUE_M1 = make(-1);
        VALUE_0 = make(0);
        VALUE_1 = make(1);
        VALUE_2 = make(2);
        VALUE_3 = make(3);
        VALUE_4 = make(4);
        VALUE_5 = make(5);
    }

    private CstInteger(int r1) {
        super(r1);
    }

    public static CstInteger make(int r4) {
        CstInteger[] r1 = cache;
        int r0 = (Integer.MAX_VALUE & r4) % r1.length;
        CstInteger r2 = r1[r0];
        if (r2 != null) goto L5;
    L7:
        CstInteger r22 = new CstInteger(r4);
        r1[r0] = r22;
        return r22;
    L5:
        if (r2.getValue() != r4) goto L7;
        return r2;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.INT;
    }

    public int getValue() {
        return getIntBits();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int r2 = getIntBits();
        return "int{0x" + Hex.u4(r2) + " / " + r2 + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "int";
    }
}
