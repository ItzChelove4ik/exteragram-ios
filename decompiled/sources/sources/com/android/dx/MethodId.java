package com.android.dx;

import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Prototype;
import java.util.List;

/* loaded from: classes.dex */
public final class MethodId<D, R> {
    final CstMethodRef constant;
    final TypeId<D> declaringType;
    final String name;
    final CstNat nat;
    final TypeList parameters;
    final TypeId<R> returnType;

    public MethodId(TypeId<D> r2, TypeId<R> r3, String r4, TypeList r5) {
        if (r2 == null) goto L10;
        if (r3 == null) goto L10;
        if (r4 == null) goto L10;
        if (r5 == null) goto L10;
        this.declaringType = r2;
        this.returnType = r3;
        this.name = r4;
        this.parameters = r5;
        CstNat r32 = new CstNat(new CstString(r4), new CstString(descriptor(false)));
        this.nat = r32;
        this.constant = new CstMethodRef(r2.constant, r32);
        return;
    L10:
        throw null;
    }

    public String descriptor(boolean r5) {
        StringBuilder r0 = new StringBuilder("(");
        if (r5 == false) goto L5;
        r0.append(this.declaringType.name);
    L5:
        TypeId<?>[] r52 = this.parameters.types;
        int r1 = r52.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        r0.append(r52[r2].name);
        r2 = r2 + 1;
        goto L6
    L8:
        r0.append(")");
        r0.append(this.returnType.name);
        return r0.toString();
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof MethodId) == false) goto L14;
        MethodId r32 = (MethodId) r3;
        if (r32.declaringType.equals(this.declaringType) == true) goto L7;
        return false;
    L7:
        if (r32.name.equals(this.name) == true) goto L9;
        return false;
    L9:
        if (r32.parameters.equals(this.parameters) == true) goto L11;
        return false;
    L11:
        if (r32.returnType.equals(this.returnType) == false) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    public TypeId<D> getDeclaringType() {
        return this.declaringType;
    }

    public String getName() {
        return this.name;
    }

    public List<TypeId<?>> getParameters() {
        return this.parameters.asList();
    }

    public TypeId<R> getReturnType() {
        return this.returnType;
    }

    public int hashCode() {
        return ((((((527 + this.declaringType.hashCode()) * 31) + this.name.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.returnType.hashCode();
    }

    public boolean isConstructor() {
        return this.name.equals("<init>");
    }

    public boolean isStaticInitializer() {
        return this.name.equals("<clinit>");
    }

    public Prototype prototype(boolean r1) {
        return Prototype.intern(descriptor(r1));
    }

    public String toString() {
        return this.declaringType + "." + this.name + "(" + this.parameters + ")";
    }
}
