package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class AnnotationsDirectoryItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ELEMENT_SIZE = 8;
    private static final int HEADER_SIZE = 16;
    private AnnotationSetItem classAnnotations;
    private ArrayList<FieldAnnotationStruct> fieldAnnotations;
    private ArrayList<MethodAnnotationStruct> methodAnnotations;
    private ArrayList<ParameterAnnotationStruct> parameterAnnotations;

    public AnnotationsDirectoryItem() {
        super(4, -1);
        this.classAnnotations = null;
        this.fieldAnnotations = null;
        this.methodAnnotations = null;
        this.parameterAnnotations = null;
    }

    private static int listSize(ArrayList<?> r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.size();
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r6) {
        MixedItemSection r0 = r6.getWordData();
        AnnotationSetItem r1 = this.classAnnotations;
        if (r1 == null) goto L5;
        this.classAnnotations = (AnnotationSetItem) r0.intern(r1);
    L5:
        ArrayList<FieldAnnotationStruct> r02 = this.fieldAnnotations;
        int r12 = 0;
        if (r02 == null) goto L10;
        int r2 = r02.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L10;
        FieldAnnotationStruct r4 = r02.get(r3);
        r3 = r3 + 1;
        r4.addContents(r6);
    L10:
        ArrayList<MethodAnnotationStruct> r03 = this.methodAnnotations;
        if (r03 == null) goto L15;
        int r22 = r03.size();
        int r32 = 0;
    L13:
        if (r32 >= r22) goto L15;
        MethodAnnotationStruct r42 = r03.get(r32);
        r32 = r32 + 1;
        r42.addContents(r6);
    L15:
        ArrayList<ParameterAnnotationStruct> r5 = this.parameterAnnotations;
        if (r5 == null) goto L20;
        int r04 = r5.size();
    L18:
        if (r12 >= r04) goto L24;
        ParameterAnnotationStruct r23 = r5.get(r12);
        r12 = r12 + 1;
        r23.addContents(r6);
        goto L18
    L24:
        return;
    }

    public void addFieldAnnotations(CstFieldRef r3, Annotations r4, DexFile r5) {
        if (this.fieldAnnotations != null) goto L5;
        this.fieldAnnotations = new ArrayList();
    L5:
        this.fieldAnnotations.add(new FieldAnnotationStruct(r3, new AnnotationSetItem(r4, r5)));
    }

    public void addMethodAnnotations(CstMethodRef r3, Annotations r4, DexFile r5) {
        if (this.methodAnnotations != null) goto L5;
        this.methodAnnotations = new ArrayList();
    L5:
        this.methodAnnotations.add(new MethodAnnotationStruct(r3, new AnnotationSetItem(r4, r5)));
    }

    public void addParameterAnnotations(CstMethodRef r2, AnnotationsList r3, DexFile r4) {
        if (this.parameterAnnotations != null) goto L5;
        this.parameterAnnotations = new ArrayList();
    L5:
        this.parameterAnnotations.add(new ParameterAnnotationStruct(r2, r3, r4));
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r2) {
        if (isInternable() == true) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("uninternable instance");
        return 0;
    L5:
        return this.classAnnotations.compareTo2(((AnnotationsDirectoryItem) r2).classAnnotations);
    }

    public void debugPrint(PrintWriter r8) {
        if (this.classAnnotations == null) goto L5;
        r8.println("  class annotations: " + this.classAnnotations);
    L5:
        int r2 = 0;
        if (this.fieldAnnotations == null) goto L11;
        r8.println("  field annotations:");
        ArrayList<FieldAnnotationStruct> r0 = this.fieldAnnotations;
        int r3 = r0.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L11;
        FieldAnnotationStruct r5 = r0.get(r4);
        r4 = r4 + 1;
        r8.println("    " + r5.toHuman());
    L11:
        if (this.methodAnnotations == null) goto L16;
        r8.println("  method annotations:");
        ArrayList<MethodAnnotationStruct> r02 = this.methodAnnotations;
        int r32 = r02.size();
        int r42 = 0;
    L13:
        if (r42 >= r32) goto L16;
        MethodAnnotationStruct r52 = r02.get(r42);
        r42 = r42 + 1;
        r8.println("    " + r52.toHuman());
    L16:
        if (this.parameterAnnotations == null) goto L20;
        r8.println("  parameter annotations:");
        ArrayList<ParameterAnnotationStruct> r7 = this.parameterAnnotations;
        int r03 = r7.size();
    L18:
        if (r2 >= r03) goto L24;
        ParameterAnnotationStruct r33 = r7.get(r2);
        r2 = r2 + 1;
        r8.println("    " + r33.toHuman());
        goto L18
    L24:
        return;
    }

    public Annotations getMethodAnnotations(CstMethodRef r6) {
        ArrayList<MethodAnnotationStruct> r5 = this.methodAnnotations;
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        MethodAnnotationStruct r3 = r5.get(r2);
        r2 = r2 + 1;
        MethodAnnotationStruct r32 = r3;
        if (r32.getMethod().equals(r6) == false) goto L6;
        return r32.getAnnotations();
    L11:
        return null;
    }

    public AnnotationsList getParameterAnnotations(CstMethodRef r6) {
        ArrayList<ParameterAnnotationStruct> r5 = this.parameterAnnotations;
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        ParameterAnnotationStruct r3 = r5.get(r2);
        r2 = r2 + 1;
        ParameterAnnotationStruct r32 = r3;
        if (r32.getMethod().equals(r6) == false) goto L6;
        return r32.getAnnotationsList();
    L11:
        return null;
    }

    public int hashCode() {
        AnnotationSetItem r0 = this.classAnnotations;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.hashCode();
    }

    public boolean isEmpty() {
        if (this.classAnnotations == null) goto L5;
        return false;
    L5:
        if (this.fieldAnnotations == null) goto L7;
        return false;
    L7:
        if (this.methodAnnotations == null) goto L9;
        return false;
    L9:
        if (this.parameterAnnotations != null) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean isInternable() {
        if (this.classAnnotations != null) goto L5;
        return false;
    L5:
        if (this.fieldAnnotations == null) goto L7;
        return false;
    L7:
        if (this.methodAnnotations == null) goto L9;
        return false;
    L9:
        if (this.parameterAnnotations != null) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATIONS_DIRECTORY_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r1, int r2) {
        setWriteSize((((listSize(this.fieldAnnotations) + listSize(this.methodAnnotations)) + listSize(this.parameterAnnotations)) * 8) + 16);
    }

    public void setClassAnnotations(Annotations r2, DexFile r3) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("annotations == null");
        return;
    L4:
        if (this.classAnnotations != null) goto L7;
        this.classAnnotations = new AnnotationSetItem(r2, r3);
        return;
    L7:
        ByteString$$ExternalSyntheticBUOutline0.m("class annotations already set");
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r10, AnnotatedOutput r11) {
        boolean r0 = r11.annotates();
        int r1 = OffsettedItem.getAbsoluteOffsetOr0(this.classAnnotations);
        int r2 = listSize(this.fieldAnnotations);
        int r3 = listSize(this.methodAnnotations);
        int r4 = listSize(this.parameterAnnotations);
        int r5 = 0;
        if (r0 == false) goto L5;
        r11.annotate(0, offsetString() + " annotations directory");
        StringBuilder r6 = new StringBuilder("  class_annotations_off: ");
        r6.append(Hex.u4(r1));
        r11.annotate(4, r6.toString());
        r11.annotate(4, "  fields_size:           " + Hex.u4(r2));
        r11.annotate(4, "  methods_size:          " + Hex.u4(r3));
        r11.annotate(4, "  parameters_size:       " + Hex.u4(r4));
    L5:
        r11.writeInt(r1);
        r11.writeInt(r2);
        r11.writeInt(r3);
        r11.writeInt(r4);
        if (r2 == 0) goto L13;
        Collections.sort(this.fieldAnnotations);
        if (r0 == false) goto L10;
        r11.annotate(0, "  fields:");
    L10:
        ArrayList<FieldAnnotationStruct> r12 = this.fieldAnnotations;
        int r22 = r12.size();
        int r62 = 0;
    L11:
        if (r62 >= r22) goto L13;
        FieldAnnotationStruct r7 = r12.get(r62);
        r62 = r62 + 1;
        r7.writeTo(r10, r11);
    L13:
        if (r3 == 0) goto L20;
        Collections.sort(this.methodAnnotations);
        if (r0 == false) goto L17;
        r11.annotate(0, "  methods:");
    L17:
        ArrayList<MethodAnnotationStruct> r13 = this.methodAnnotations;
        int r23 = r13.size();
        int r32 = 0;
    L18:
        if (r32 >= r23) goto L20;
        MethodAnnotationStruct r63 = r13.get(r32);
        r32 = r32 + 1;
        r63.writeTo(r10, r11);
    L20:
        if (r4 == 0) goto L27;
        Collections.sort(this.parameterAnnotations);
        if (r0 == false) goto L24;
        r11.annotate(0, "  parameters:");
    L24:
        ArrayList<ParameterAnnotationStruct> r9 = this.parameterAnnotations;
        int r02 = r9.size();
    L25:
        if (r5 >= r02) goto L31;
        ParameterAnnotationStruct r14 = r9.get(r5);
        r5 = r5 + 1;
        r14.writeTo(r10, r11);
        goto L25
    L31:
        return;
    }
}
