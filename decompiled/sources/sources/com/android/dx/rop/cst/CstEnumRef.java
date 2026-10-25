package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public final class CstEnumRef extends CstMemberRef {
    private CstFieldRef fieldRef;

    public CstEnumRef(CstNat r3) {
        super(new CstType(r3.getFieldType()), r3);
        this.fieldRef = null;
    }

    public CstFieldRef getFieldRef() {
        if (this.fieldRef != null) goto L6;
        this.fieldRef = new CstFieldRef(getDefiningClass(), getNat());
    L6:
        return this.fieldRef;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return getDefiningClass().getClassType();
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "enum";
    }
}
