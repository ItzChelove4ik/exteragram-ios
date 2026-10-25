package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public abstract class BaseAnnotations extends BaseAttribute {
    private final Annotations annotations;
    private final int byteLength;

    public BaseAnnotations(String r1, Annotations r2, int r3) {
        super(r1);
    L9:
        f$$ExternalSyntheticBUOutline2.m("annotations == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.annotations = r2;
        this.byteLength = r3;
        return;
    L8:
        throw new MutabilityException("annotations.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public final int byteLength() {
        return this.byteLength + 6;
    }

    public final Annotations getAnnotations() {
        return this.annotations;
    }
}
