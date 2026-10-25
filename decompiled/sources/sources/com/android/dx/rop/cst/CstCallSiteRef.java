package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public class CstCallSiteRef extends Constant {
    private final int id;
    private final CstInvokeDynamic invokeDynamic;

    public CstCallSiteRef(CstInvokeDynamic r1, int r2) {
        if (r1 == null) goto L6;
        this.invokeDynamic = r1;
        this.id = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("invokeDynamic == null");
        throw null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r3) {
        CstCallSiteRef r32 = (CstCallSiteRef) r3;
        int r0 = this.invokeDynamic.compareTo2(r32.invokeDynamic);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return Integer.compare(this.id, r32.id);
    }

    public CstCallSite getCallSite() {
        return this.invokeDynamic.getCallSite();
    }

    public Prototype getPrototype() {
        return this.invokeDynamic.getPrototype();
    }

    public Type getReturnType() {
        return this.invokeDynamic.getReturnType();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return getCallSite().toHuman();
    }

    public String toString() {
        return getCallSite().toString();
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "CallSiteRef";
    }
}
