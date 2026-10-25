package com.android.dx.rop.cst;

/* loaded from: classes.dex */
public abstract class CstLiteralBits extends TypedConstant {
    public CstLiteralBits() {
    }

    public boolean fitsIn16Bits() {
        if (fitsInInt() == true) goto L5;
        return false;
    L5:
        int r2 = getIntBits();
        if (((short) r2) != r2) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean fitsIn8Bits() {
        if (fitsInInt() == true) goto L5;
        return false;
    L5:
        int r2 = getIntBits();
        if (((byte) r2) != r2) goto L9;
        return true;
    L9:
        return false;
    }

    public abstract boolean fitsInInt();

    public abstract int getIntBits();

    public abstract long getLongBits();
}
