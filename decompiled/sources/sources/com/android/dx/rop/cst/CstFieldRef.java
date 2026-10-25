package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public final class CstFieldRef extends CstMemberRef {
    public CstFieldRef(CstType r1, CstNat r2) {
        super(r1, r2);
    }

    public static CstFieldRef forPrimitiveType(Type r2) {
        return new CstFieldRef(CstType.forBoxedPrimitiveType(r2), CstNat.PRIMITIVE_TYPE_NAT);
    }

    @Override // com.android.dx.rop.cst.CstMemberRef, com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r2) {
        int r0 = super.compareTo0(r2);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return getNat().getDescriptor().compareTo2(((CstFieldRef) r2).getNat().getDescriptor());
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return getNat().getFieldType();
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "field";
    }
}
