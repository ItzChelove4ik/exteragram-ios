package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotation;

/* loaded from: classes4.dex */
public final class CstAnnotation extends Constant {
    private final Annotation annotation;

    public CstAnnotation(Annotation r1) {
        if (r1 == null) goto L6;
        r1.throwIfMutable();
        this.annotation = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("annotation == null");
        throw null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return this.annotation.compareTo2(((CstAnnotation) r1).annotation);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof CstAnnotation) == true) goto L7;
        return false;
    L7:
        return this.annotation.equals(((CstAnnotation) r2).annotation);
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public int hashCode() {
        return this.annotation.hashCode();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.annotation.toString();
    }

    public String toString() {
        return this.annotation.toString();
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "annotation";
    }
}
