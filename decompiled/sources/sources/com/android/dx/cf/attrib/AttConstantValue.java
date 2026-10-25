package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.TypedConstant;

/* loaded from: classes4.dex */
public final class AttConstantValue extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "ConstantValue";
    private final TypedConstant constantValue;

    public AttConstantValue(TypedConstant r2) {
        super(ATTRIBUTE_NAME);
        if ((r2 instanceof CstString) == false) goto L5;
    L17:
        this.constantValue = r2;
        return;
    L5:
        if ((r2 instanceof CstInteger) == true) goto L17;
        if ((r2 instanceof CstLong) == true) goto L17;
        if ((r2 instanceof CstFloat) == true) goto L17;
        if ((r2 instanceof CstDouble) == true) goto L17;
        if (r2 != null) goto L15;
        f$$ExternalSyntheticBUOutline2.m("constantValue == null");
        throw null;
    L15:
        f$$ExternalSyntheticBUOutline1.m("bad type for constantValue");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return 8;
    }

    public TypedConstant getConstantValue() {
        return this.constantValue;
    }
}
