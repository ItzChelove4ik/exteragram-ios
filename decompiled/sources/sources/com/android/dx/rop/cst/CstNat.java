package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public final class CstNat extends Constant {
    public static final CstNat PRIMITIVE_TYPE_NAT = null;
    private final CstString descriptor;
    private final CstString name;

    static {
        PRIMITIVE_TYPE_NAT = new CstNat(new CstString("TYPE"), new CstString("Ljava/lang/Class;"));
    }

    public CstNat(CstString r2, CstString r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.name = r2;
        this.descriptor = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("name == null");
        throw null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r3) {
        CstNat r32 = (CstNat) r3;
        int r0 = this.name.compareTo2(r32.name);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.descriptor.compareTo2(r32.descriptor);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof CstNat) == true) goto L5;
        return false;
    L5:
        CstNat r42 = (CstNat) r4;
        if (this.name.equals(r42.name) == true) goto L8;
    L11:
        return false;
    L8:
        if (this.descriptor.equals(r42.descriptor) == false) goto L11;
        return true;
    }

    public CstString getDescriptor() {
        return this.descriptor;
    }

    public Type getFieldType() {
        return Type.intern(this.descriptor.getString());
    }

    public CstString getName() {
        return this.name;
    }

    public int hashCode() {
        int r0 = this.name.hashCode() * 31;
        return this.descriptor.hashCode() ^ r0;
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    public final boolean isClassInit() {
        return this.name.getString().equals("<clinit>");
    }

    public final boolean isInstanceInit() {
        return this.name.getString().equals("<init>");
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.name.toHuman() + ':' + this.descriptor.toHuman();
    }

    public String toString() {
        return "nat{" + toHuman() + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "nat";
    }
}
