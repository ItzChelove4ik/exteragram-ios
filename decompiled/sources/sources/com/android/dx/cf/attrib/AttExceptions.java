package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public final class AttExceptions extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "Exceptions";
    private final TypeList exceptions;

    public AttExceptions(TypeList r2) {
        super(ATTRIBUTE_NAME);
    L9:
        f$$ExternalSyntheticBUOutline2.m("exceptions == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.exceptions = r2;
        return;
    L8:
        throw new MutabilityException("exceptions.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return (this.exceptions.size() * 2) + 8;
    }

    public TypeList getExceptions() {
        return this.exceptions;
    }
}
