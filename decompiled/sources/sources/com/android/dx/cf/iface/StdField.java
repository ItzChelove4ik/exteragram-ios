package com.android.dx.cf.iface;

import com.android.dx.cf.attrib.AttConstantValue;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.TypedConstant;

/* loaded from: classes4.dex */
public final class StdField extends StdMember implements Field {
    public StdField(CstType r1, int r2, CstNat r3, AttributeList r4) {
        super(r1, r2, r3, r4);
    }

    @Override // com.android.dx.cf.iface.Field
    public TypedConstant getConstantValue() {
        AttConstantValue r1 = (AttConstantValue) getAttributes().findFirst(AttConstantValue.ATTRIBUTE_NAME);
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getConstantValue();
    }
}
