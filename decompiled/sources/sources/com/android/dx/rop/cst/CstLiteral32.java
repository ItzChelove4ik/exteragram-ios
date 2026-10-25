package com.android.dx.rop.cst;

/* loaded from: classes.dex */
public abstract class CstLiteral32 extends CstLiteralBits {
    private final int bits;

    public CstLiteral32(int r1) {
        this.bits = r1;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        int r12 = ((CstLiteral32) r1).bits;
        int r0 = this.bits;
        if (r0 >= r12) goto L6;
        return -1;
    L6:
        if (r0 <= r12) goto L9;
        return 1;
    L9:
        return 0;
    }

    public final boolean equals(Object r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (getClass() == r3.getClass()) goto L6;
        return false;
    L6:
        if (this.bits != ((CstLiteral32) r3).bits) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final boolean fitsInInt() {
        return true;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final int getIntBits() {
        return this.bits;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final long getLongBits() {
        return this.bits;
    }

    public final int hashCode() {
        return this.bits;
    }

    @Override // com.android.dx.rop.cst.Constant
    public final boolean isCategory2() {
        return false;
    }
}
