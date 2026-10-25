package com.android.dx.rop.cst;

import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public final class CstProtoRef extends TypedConstant {
    private final Prototype prototype;

    public CstProtoRef(Prototype r1) {
        this.prototype = r1;
    }

    public static CstProtoRef make(CstString r1) {
        return new CstProtoRef(Prototype.fromDescriptor(r1.getString()));
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return this.prototype.compareTo2(((CstProtoRef) r1).getPrototype());
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof CstProtoRef) == true) goto L7;
        return false;
    L7:
        return getPrototype().equals(((CstProtoRef) r2).getPrototype());
    }

    public Prototype getPrototype() {
        return this.prototype;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.METHOD_TYPE;
    }

    public int hashCode() {
        return this.prototype.hashCode();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.prototype.getDescriptor();
    }

    public final String toString() {
        return typeName() + "{" + toHuman() + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "proto";
    }
}
