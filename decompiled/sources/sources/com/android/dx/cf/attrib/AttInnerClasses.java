package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public final class AttInnerClasses extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "InnerClasses";
    private final InnerClassList innerClasses;

    public AttInnerClasses(InnerClassList r2) {
        super(ATTRIBUTE_NAME);
    L9:
        f$$ExternalSyntheticBUOutline2.m("innerClasses == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.innerClasses = r2;
        return;
    L8:
        throw new MutabilityException("innerClasses.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return (this.innerClasses.size() * 8) + 8;
    }

    public InnerClassList getInnerClasses() {
        return this.innerClasses;
    }
}
