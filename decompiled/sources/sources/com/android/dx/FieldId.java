package com.android.dx;

import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;

/* loaded from: classes.dex */
public final class FieldId<D, V> {
    final CstFieldRef constant;
    final TypeId<D> declaringType;
    final String name;
    final CstNat nat;
    final TypeId<V> type;

    public FieldId(TypeId<D> r3, TypeId<V> r4, String r5) {
        if (r3 == null) goto L9;
        if (r4 == null) goto L9;
        if (r5 == null) goto L9;
        this.declaringType = r3;
        this.type = r4;
        this.name = r5;
        CstNat r0 = new CstNat(new CstString(r5), new CstString(r4.name));
        this.nat = r0;
        this.constant = new CstFieldRef(r3.constant, r0);
        return;
    L9:
        throw null;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof FieldId) == false) goto L10;
        FieldId r32 = (FieldId) r3;
        if (r32.declaringType.equals(this.declaringType) == true) goto L7;
        return false;
    L7:
        if (r32.name.equals(this.name) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public TypeId<D> getDeclaringType() {
        return this.declaringType;
    }

    public String getName() {
        return this.name;
    }

    public TypeId<V> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.declaringType.hashCode() + (this.name.hashCode() * 37);
    }

    public String toString() {
        return this.declaringType + "." + this.name;
    }
}
