package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.LineNumberList;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public final class AttLineNumberTable extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "LineNumberTable";
    private final LineNumberList lineNumbers;

    public AttLineNumberTable(LineNumberList r2) {
        super(ATTRIBUTE_NAME);
    L9:
        f$$ExternalSyntheticBUOutline2.m("lineNumbers == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.lineNumbers = r2;
        return;
    L8:
        throw new MutabilityException("lineNumbers.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return (this.lineNumbers.size() * 4) + 8;
    }

    public LineNumberList getLineNumbers() {
        return this.lineNumbers;
    }
}
