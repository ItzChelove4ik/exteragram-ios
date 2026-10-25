package com.android.dx.rop.cst;

/* loaded from: classes4.dex */
public final class CstInterfaceMethodRef extends CstBaseMethodRef {
    private CstMethodRef methodRef;

    public CstInterfaceMethodRef(CstType r1, CstNat r2) {
        super(r1, r2);
        this.methodRef = null;
    }

    public CstMethodRef toMethodRef() {
        if (this.methodRef != null) goto L6;
        this.methodRef = new CstMethodRef(getDefiningClass(), getNat());
    L6:
        return this.methodRef;
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "ifaceMethod";
    }
}
