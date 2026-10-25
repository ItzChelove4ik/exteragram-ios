package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class CstChar extends CstLiteral32 {
    public static final CstChar VALUE_0 = null;

    static {
        VALUE_0 = make(0);
    }

    private CstChar(char r1) {
        super(r1);
    }

    public static CstChar make(char r1) {
        return new CstChar(r1);
    }

    public static CstChar make(int r1) {
        char r0 = (char) r1;
        if (r0 == r1) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("bogus char value: ", r1);
        return null;
    L5:
        return make(r0);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.CHAR;
    }

    public char getValue() {
        return (char) getIntBits();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int r2 = getIntBits();
        return "char{0x" + Hex.u2(r2) + " / " + r2 + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "char";
    }
}
