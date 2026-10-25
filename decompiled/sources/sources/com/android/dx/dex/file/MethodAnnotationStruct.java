package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.ToHuman;

/* loaded from: classes4.dex */
public final class MethodAnnotationStruct implements ToHuman, Comparable<MethodAnnotationStruct> {
    private AnnotationSetItem annotations;
    private final CstMethodRef method;

    public MethodAnnotationStruct(CstMethodRef r2, AnnotationSetItem r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.method = r2;
        this.annotations = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("annotations == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    public void addContents(DexFile r3) {
        MethodIdsSection r0 = r3.getMethodIds();
        MixedItemSection r32 = r3.getWordData();
        r0.intern(this.method);
        this.annotations = (AnnotationSetItem) r32.intern(this.annotations);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(MethodAnnotationStruct r1) {
        return this.method.compareTo2(r1.method);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(MethodAnnotationStruct r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof MethodAnnotationStruct) == true) goto L7;
        return false;
    L7:
        return this.method.equals(((MethodAnnotationStruct) r2).method);
    }

    public Annotations getAnnotations() {
        return this.annotations.getAnnotations();
    }

    public CstMethodRef getMethod() {
        return this.method;
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.method.toHuman() + ": " + this.annotations;
    }

    public void writeTo(DexFile r4, AnnotatedOutput r5) {
        int r42 = r4.getMethodIds().indexOf(this.method);
        int r0 = this.annotations.getAbsoluteOffset();
        if (r5.annotates() == false) goto L5;
        r5.annotate(0, "    " + this.method.toHuman());
        r5.annotate(4, "      method_idx:      " + Hex.u4(r42));
        r5.annotate(4, "      annotations_off: " + Hex.u4(r0));
    L5:
        r5.writeInt(r42);
        r5.writeInt(r0);
    }
}
