package com.android.dx.rop.cst;

/* loaded from: classes4.dex */
public abstract class CstLiteral64 extends CstLiteralBits {
    private final long bits;

    public CstLiteral64(long r1) {
        this.bits = r1;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r4) {
        long r0 = ((CstLiteral64) r4).bits;
        long r3 = this.bits;
        if (r3 >= r0) goto L7;
        return -1;
    L7:
        if (r3 <= r0) goto L10;
        return 1;
    L10:
        return 0;
    }

    public final boolean equals(Object r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (getClass() == r3.getClass()) goto L6;
        return false;
    L6:
        if (this.bits != ((CstLiteral64) r3).bits) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final boolean fitsInInt() {
        if (((int) r0) != this.bits) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final int getIntBits() {
        return (int) this.bits;
    }

    @Override // com.android.dx.rop.cst.CstLiteralBits
    public final long getLongBits() {
        return this.bits;
    }

    public final int hashCode() {
        long r0 = this.bits;
        return ((int) r0) ^ ((int) (r0 >> 32));
    }

    @Override // com.android.dx.rop.cst.Constant
    public final boolean isCategory2() {
        return true;
    }
}
