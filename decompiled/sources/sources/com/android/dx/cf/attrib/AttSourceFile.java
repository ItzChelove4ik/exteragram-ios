package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstString;

/* loaded from: classes4.dex */
public final class AttSourceFile extends BaseAttribute {
    public static final String ATTRIBUTE_NAME = "SourceFile";
    private final CstString sourceFile;

    public AttSourceFile(CstString r2) {
        super(ATTRIBUTE_NAME);
        if (r2 == null) goto L6;
        this.sourceFile = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("sourceFile == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return 8;
    }

    public CstString getSourceFile() {
        return this.sourceFile;
    }
}
