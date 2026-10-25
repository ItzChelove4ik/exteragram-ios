package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public abstract class BaseParameterAnnotations extends BaseAttribute {
    private final int byteLength;
    private final AnnotationsList parameterAnnotations;

    public BaseParameterAnnotations(String r1, AnnotationsList r2, int r3) {
        super(r1);
    L9:
        f$$ExternalSyntheticBUOutline2.m("parameterAnnotations == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.parameterAnnotations = r2;
        this.byteLength = r3;
        return;
    L8:
        throw new MutabilityException("parameterAnnotations.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public final int byteLength() {
        return this.byteLength + 6;
    }

    public final AnnotationsList getParameterAnnotations() {
        return this.parameterAnnotations;
    }
}
