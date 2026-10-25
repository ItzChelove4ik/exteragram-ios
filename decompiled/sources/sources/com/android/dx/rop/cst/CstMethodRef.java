package com.android.dx.rop.cst;

/* loaded from: classes.dex */
public final class CstMethodRef extends CstBaseMethodRef {
    public CstMethodRef(CstType r1, CstNat r2) {
        super(r1, r2);
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "method";
    }
}
