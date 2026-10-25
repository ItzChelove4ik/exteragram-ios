package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.ToHuman;

/* loaded from: classes4.dex */
public final class FieldAnnotationStruct implements ToHuman, Comparable<FieldAnnotationStruct> {
    private AnnotationSetItem annotations;
    private final CstFieldRef field;

    public FieldAnnotationStruct(CstFieldRef r2, AnnotationSetItem r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.field = r2;
        this.annotations = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("annotations == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("field == null");
        throw null;
    }

    public void addContents(DexFile r3) {
        FieldIdsSection r0 = r3.getFieldIds();
        MixedItemSection r32 = r3.getWordData();
        r0.intern(this.field);
        this.annotations = (AnnotationSetItem) r32.intern(this.annotations);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(FieldAnnotationStruct r1) {
        return this.field.compareTo2(r1.field);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(FieldAnnotationStruct r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof FieldAnnotationStruct) == true) goto L7;
        return false;
    L7:
        return this.field.equals(((FieldAnnotationStruct) r2).field);
    }

    public Annotations getAnnotations() {
        return this.annotations.getAnnotations();
    }

    public CstFieldRef getField() {
        return this.field;
    }

    public int hashCode() {
        return this.field.hashCode();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.field.toHuman() + ": " + this.annotations;
    }

    public void writeTo(DexFile r4, AnnotatedOutput r5) {
        int r42 = r4.getFieldIds().indexOf(this.field);
        int r0 = this.annotations.getAbsoluteOffset();
        if (r5.annotates() == false) goto L5;
        r5.annotate(0, "    " + this.field.toHuman());
        r5.annotate(4, "      field_idx:       " + Hex.u4(r42));
        r5.annotate(4, "      annotations_off: " + Hex.u4(r0));
    L5:
        r5.writeInt(r42);
        r5.writeInt(r0);
    }
}
