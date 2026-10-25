package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.ToHuman;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class ParameterAnnotationStruct implements ToHuman, Comparable<ParameterAnnotationStruct> {
    private final UniformListItem<AnnotationSetRefItem> annotationsItem;
    private final AnnotationsList annotationsList;
    private final CstMethodRef method;

    public ParameterAnnotationStruct(CstMethodRef r5, AnnotationsList r6, DexFile r7) {
        if (r5 == null) goto L12;
        if (r6 == null) goto L10;
        this.method = r5;
        this.annotationsList = r6;
        int r52 = r6.size();
        ArrayList r0 = new ArrayList(r52);
        int r1 = 0;
    L6:
        if (r1 >= r52) goto L8;
        r0.add(new AnnotationSetRefItem(new AnnotationSetItem(r6.get(r1), r7)));
        r1 = r1 + 1;
        goto L6
    L8:
        this.annotationsItem = new UniformListItem(ItemType.TYPE_ANNOTATION_SET_REF_LIST, r0);
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("annotationsList == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    public void addContents(DexFile r3) {
        MethodIdsSection r0 = r3.getMethodIds();
        MixedItemSection r32 = r3.getWordData();
        r0.intern(this.method);
        r32.add(this.annotationsItem);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(ParameterAnnotationStruct r1) {
        return this.method.compareTo2(r1.method);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ParameterAnnotationStruct r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof ParameterAnnotationStruct) == true) goto L7;
        return false;
    L7:
        return this.method.equals(((ParameterAnnotationStruct) r2).method);
    }

    public AnnotationsList getAnnotationsList() {
        return this.annotationsList;
    }

    public CstMethodRef getMethod() {
        return this.method;
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.method.toHuman());
        r0.append(": ");
        Iterator r4 = this.annotationsItem.getItems().iterator();
        boolean r1 = true;
    L4:
        if (r4.hasNext() == false) goto L11;
        AnnotationSetRefItem r2 = (AnnotationSetRefItem) r4.next();
        if (r1 == false) goto L8;
        r1 = false;
    L9:
        r0.append(r2.toHuman());
        goto L4
    L8:
        r0.append(", ");
        goto L9
    L11:
        return r0.toString();
    }

    public void writeTo(DexFile r4, AnnotatedOutput r5) {
        int r42 = r4.getMethodIds().indexOf(this.method);
        int r0 = this.annotationsItem.getAbsoluteOffset();
        if (r5.annotates() == false) goto L5;
        r5.annotate(0, "    " + this.method.toHuman());
        r5.annotate(4, "      method_idx:      " + Hex.u4(r42));
        r5.annotate(4, "      annotations_off: " + Hex.u4(r0));
    L5:
        r5.writeInt(r42);
        r5.writeInt(r0);
    }
}
