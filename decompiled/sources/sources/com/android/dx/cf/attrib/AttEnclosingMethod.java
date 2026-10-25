package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;

/* loaded from: classes4.dex */
public final class AttEnclosingMethod extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "EnclosingMethod";
    private final CstNat method;
    private final CstType type;

    public AttEnclosingMethod(CstType r2, CstNat r3) {
        super(ATTRIBUTE_NAME);
        if (r2 == null) goto L6;
        this.type = r2;
        this.method = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return 10;
    }

    public CstType getEnclosingClass() {
        return this.type;
    }

    public CstNat getMethod() {
        return this.method;
    }
}
