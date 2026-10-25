package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.iface.Attribute;

/* loaded from: classes4.dex */
public abstract class BaseAttribute implements Attribute {
    private final String name;

    public BaseAttribute(String r1) {
        if (r1 == null) goto L6;
        this.name = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("name == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public String getName() {
        return this.name;
    }
}
