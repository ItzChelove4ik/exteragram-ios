package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.BootstrapMethodArgumentsList;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.type.Prototype;

/* loaded from: classes4.dex */
public final class CstCallSite extends CstArray {
    private CstCallSite(CstArray.List r1) {
        super(r1);
    }

    public static CstCallSite make(CstMethodHandle r3, CstNat r4, BootstrapMethodArgumentsList r5) {
        if (r3 == null) goto L13;
        if (r4 == null) goto L11;
        CstArray.List r0 = new CstArray.List(r5.size() + 3);
        int r1 = 0;
        r0.set(0, r3);
        r0.set(1, r4.getName());
        r0.set(2, new CstProtoRef(Prototype.fromDescriptor(r4.getDescriptor().getString())));
    L7:
        if (r1 >= r5.size()) goto L9;
        r0.set(r1 + 3, r5.get(r1));
        r1 = r1 + 1;
        goto L7
    L9:
        r0.setImmutable();
        return new CstCallSite(r0);
    L11:
        f$$ExternalSyntheticBUOutline2.m("nat == null");
        return null;
    L13:
        f$$ExternalSyntheticBUOutline2.m("bootstrapMethodHandle == null");
        return null;
    }

    @Override // com.android.dx.rop.cst.CstArray, com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return getList().compareTo2(((CstCallSite) r1).getList());
    }

    @Override // com.android.dx.rop.cst.CstArray
    public boolean equals(Object r2) {
        if ((r2 instanceof CstCallSite) == true) goto L5;
        return false;
    L5:
        return getList().equals(((CstCallSite) r2).getList());
    }

    @Override // com.android.dx.rop.cst.CstArray
    public int hashCode() {
        return getList().hashCode();
    }

    @Override // com.android.dx.rop.cst.CstArray, com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.rop.cst.CstArray, com.android.dx.util.ToHuman
    public String toHuman() {
        return getList().toHuman("{", ", ", "}");
    }

    @Override // com.android.dx.rop.cst.CstArray
    public String toString() {
        return getList().toString("call site{", ", ", "}");
    }

    @Override // com.android.dx.rop.cst.CstArray, com.android.dx.rop.cst.Constant
    public String typeName() {
        return "call site";
    }
}
