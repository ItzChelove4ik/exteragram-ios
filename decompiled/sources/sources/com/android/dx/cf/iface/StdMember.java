package com.android.dx.cf.iface;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;

/* loaded from: classes4.dex */
public abstract class StdMember implements Member {
    private final int accessFlags;
    private final AttributeList attributes;
    private final CstType definingClass;
    private final CstNat nat;

    public StdMember(CstType r2, int r3, CstNat r4, AttributeList r5) {
        if (r2 == null) goto L12;
        if (r4 == null) goto L10;
        if (r5 == null) goto L8;
        this.definingClass = r2;
        this.accessFlags = r3;
        this.nat = r4;
        this.attributes = r5;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("attributes == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("nat == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("definingClass == null");
        throw null;
    }

    @Override // com.android.dx.cf.iface.Member
    public final int getAccessFlags() {
        return this.accessFlags;
    }

    @Override // com.android.dx.cf.iface.Member, com.android.dx.cf.iface.HasAttribute
    public final AttributeList getAttributes() {
        return this.attributes;
    }

    @Override // com.android.dx.cf.iface.Member
    public final CstType getDefiningClass() {
        return this.definingClass;
    }

    @Override // com.android.dx.cf.iface.Member
    public final CstString getDescriptor() {
        return this.nat.getDescriptor();
    }

    @Override // com.android.dx.cf.iface.Member
    public final CstString getName() {
        return this.nat.getName();
    }

    @Override // com.android.dx.cf.iface.Member
    public final CstNat getNat() {
        return this.nat;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(getClass().getName());
        r0.append('{');
        r0.append(this.nat.toHuman());
        r0.append('}');
        return r0.toString();
    }
}
