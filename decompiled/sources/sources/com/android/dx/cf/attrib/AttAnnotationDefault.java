package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;

/* loaded from: classes4.dex */
public final class AttAnnotationDefault extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "AnnotationDefault";
    private final int byteLength;
    private final Constant value;

    public AttAnnotationDefault(Constant r2, int r3) {
        super(ATTRIBUTE_NAME);
        if (r2 == null) goto L6;
        this.value = r2;
        this.byteLength = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("value == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return this.byteLength + 6;
    }

    public Constant getValue() {
        return this.value;
    }
}
