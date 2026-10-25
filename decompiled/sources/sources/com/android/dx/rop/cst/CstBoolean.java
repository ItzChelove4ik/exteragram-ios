package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class CstBoolean extends CstLiteral32 {
    public static final CstBoolean VALUE_FALSE = null;
    public static final CstBoolean VALUE_TRUE = null;

    static {
        VALUE_FALSE = new CstBoolean(false);
        VALUE_TRUE = new CstBoolean(true);
    }

    private CstBoolean(boolean r1) {
        super(r1 ? 1 : 0);
    }

    public static CstBoolean make(int r1) {
        if (r1 != 0) goto L6;
        return VALUE_FALSE;
    L6:
        if (r1 == 1) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("bogus value: ", r1);
        return null;
    L8:
        return VALUE_TRUE;
    }

    public static CstBoolean make(boolean r0) {
        if (r0 == false) goto L6;
        return VALUE_TRUE;
    L6:
        return VALUE_FALSE;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.BOOLEAN;
    }

    public boolean getValue() {
        if (getIntBits() != 0) goto L6;
        return false;
    L6:
        return true;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        if (getValue() == false) goto L6;
        return "true";
    L6:
        return "false";
    }

    public String toString() {
        if (getValue() == false) goto L6;
        return "boolean{true}";
    L6:
        return "boolean{false}";
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "boolean";
    }
}
