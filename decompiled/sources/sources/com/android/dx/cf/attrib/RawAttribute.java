package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.util.ByteArray;

/* loaded from: classes4.dex */
public final class RawAttribute extends BaseAttribute {
    private final ByteArray data;
    private final ConstantPool pool;

    public RawAttribute(String r1, ByteArray r2, int r3, int r4, ConstantPool r5) {
        this(r1, r2.slice(r3, r4 + r3), r5);
    }

    public RawAttribute(String r1, ByteArray r2, ConstantPool r3) {
        super(r1);
        if (r2 == null) goto L6;
        this.data = r2;
        this.pool = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("data == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Attribute
    public int byteLength() {
        return this.data.size() + 6;
    }

    public ByteArray getData() {
        return this.data;
    }

    public ConstantPool getPool() {
        return this.pool;
    }
}
