package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class CstInvokeDynamic extends Constant {
    private final int bootstrapMethodIndex;
    private CstCallSite callSite;
    private CstType declaringClass;
    private final CstNat nat;
    private final Prototype prototype;
    private final List<CstCallSiteRef> references;

    private CstInvokeDynamic(int r1, CstNat r2) {
        this.bootstrapMethodIndex = r1;
        this.nat = r2;
        this.prototype = Prototype.fromDescriptor(r2.getDescriptor().toHuman());
        this.references = new ArrayList();
    }

    public static CstInvokeDynamic make(int r1, CstNat r2) {
        return new CstInvokeDynamic(r1, r2);
    }

    public CstCallSiteRef addReference() {
        CstCallSiteRef r0 = new CstCallSiteRef(this, this.references.size());
        this.references.add(r0);
        return r0;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r3) {
        CstInvokeDynamic r32 = (CstInvokeDynamic) r3;
        int r0 = Integer.compare(this.bootstrapMethodIndex, r32.getBootstrapMethodIndex());
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = this.nat.compareTo2(r32.getNat());
        if (r02 == 0) goto L8;
        return r02;
    L8:
        int r03 = this.declaringClass.compareTo2(r32.getDeclaringClass());
        if (r03 == 0) goto L12;
        return r03;
    L12:
        return this.callSite.compareTo2(r32.getCallSite());
    }

    public int getBootstrapMethodIndex() {
        return this.bootstrapMethodIndex;
    }

    public CstCallSite getCallSite() {
        return this.callSite;
    }

    public CstType getDeclaringClass() {
        return this.declaringClass;
    }

    public CstNat getNat() {
        return this.nat;
    }

    public Prototype getPrototype() {
        return this.prototype;
    }

    public List<CstCallSiteRef> getReferences() {
        return this.references;
    }

    public Type getReturnType() {
        return this.prototype.getReturnType();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    public void setCallSite(CstCallSite r2) {
        if (this.callSite != null) goto L9;
        if (r2 == null) goto L7;
        this.callSite = r2;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("callSite == null");
        return;
    L9:
        f$$ExternalSyntheticBUOutline1.m("already added call site");
    }

    public void setDeclaringClass(CstType r2) {
        if (this.declaringClass != null) goto L9;
        if (r2 == null) goto L7;
        this.declaringClass = r2;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("declaringClass == null");
        return;
    L9:
        f$$ExternalSyntheticBUOutline1.m("already added declaring class");
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        CstType r0 = this.declaringClass;
        if (r0 == null) goto L5;
        String r02 = r0.toHuman();
    L7:
        return "InvokeDynamic(" + r02 + ":" + this.bootstrapMethodIndex + ", " + this.nat.toHuman() + ")";
    L5:
        r02 = "Unknown";
        goto L7
    }

    public String toString() {
        return toHuman();
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "InvokeDynamic";
    }
}
