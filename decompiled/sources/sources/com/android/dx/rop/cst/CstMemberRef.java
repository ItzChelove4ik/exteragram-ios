package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public abstract class CstMemberRef extends TypedConstant {
    private final CstType definingClass;
    private final CstNat nat;

    public CstMemberRef(CstType r2, CstNat r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.definingClass = r2;
        this.nat = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("nat == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("definingClass == null");
        throw null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r3) {
        CstMemberRef r32 = (CstMemberRef) r3;
        int r0 = this.definingClass.compareTo2(r32.definingClass);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.nat.getName().compareTo2(r32.nat.getName());
    }

    public final boolean equals(Object r4) {
        if (r4 != null) goto L5;
    L13:
        return false;
    L5:
        if (getClass() != r4.getClass()) goto L13;
        CstMemberRef r42 = (CstMemberRef) r4;
        if (this.definingClass.equals(r42.definingClass) == false) goto L13;
        if (this.nat.equals(r42.nat) == false) goto L13;
        return true;
    }

    public final CstType getDefiningClass() {
        return this.definingClass;
    }

    public final CstNat getNat() {
        return this.nat;
    }

    public final int hashCode() {
        int r0 = this.definingClass.hashCode() * 31;
        return this.nat.hashCode() ^ r0;
    }

    @Override // com.android.dx.rop.cst.Constant
    public final boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public final String toHuman() {
        return this.definingClass.toHuman() + '.' + this.nat.toHuman();
    }

    public final String toString() {
        return typeName() + '{' + toHuman() + '}';
    }
}
