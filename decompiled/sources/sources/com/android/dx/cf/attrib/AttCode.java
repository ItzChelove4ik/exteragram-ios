package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.ByteCatchList;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public final class AttCode extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "Code";
    private final AttributeList attributes;
    private final ByteCatchList catches;
    private final BytecodeArray code;
    private final int maxLocals;
    private final int maxStack;

    public AttCode(int r3, int r4, BytecodeArray r5, ByteCatchList r6, AttributeList r7) {
        super(ATTRIBUTE_NAME);
        if (r3 < 0) goto L24;
        if (r4 < 0) goto L22;
        if (r5 != null) goto L28;
        f$$ExternalSyntheticBUOutline2.m("code == null");
        throw null;
    L28:
    L18:
        f$$ExternalSyntheticBUOutline2.m("catches == null");
        throw null;
    L7:
        if (r6.isMutable() == true) goto L17;
    L14:
        f$$ExternalSyntheticBUOutline2.m("attributes == null");
        throw null;
    L9:
        if (r7.isMutable() == true) goto L13;
        this.maxStack = r3;
        this.maxLocals = r4;
        this.code = r5;
        this.catches = r6;
        this.attributes = r7;
        return;
    L13:
        throw new MutabilityException("attributes.isMutable()");     // Catch: NullPointerException -> L14
    L17:
        throw new MutabilityException("catches.isMutable()");     // Catch: NullPointerException -> L18
    L22:
        f$$ExternalSyntheticBUOutline1.m("maxLocals < 0");
        throw null;
    L24:
        f$$ExternalSyntheticBUOutline1.m("maxStack < 0");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return ((this.code.byteLength() + 10) + this.catches.byteLength()) + this.attributes.byteLength();
    }

    public AttributeList getAttributes() {
        return this.attributes;
    }

    public ByteCatchList getCatches() {
        return this.catches;
    }

    public BytecodeArray getCode() {
        return this.code;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public int getMaxStack() {
        return this.maxStack;
    }
}
