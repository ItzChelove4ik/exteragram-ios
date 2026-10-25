package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.Writers;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ClassDefItem extends IndexedItem {
    private final int accessFlags;
    private AnnotationsDirectoryItem annotationsDirectory;
    private final ClassDataItem classData;
    private TypeListItem interfaces;
    private final CstString sourceFile;
    private EncodedArrayItem staticValuesItem;
    private final CstType superclass;
    private final CstType thisClass;

    public ClassDefItem(CstType r2, int r3, CstType r4, TypeList r5, CstString r6) {
        if (r2 == null) goto L13;
        if (r5 == null) goto L11;
        this.thisClass = r2;
        this.accessFlags = r3;
        this.superclass = r4;
        if (r5.size() != 0) goto L8;
        TypeListItem r32 = null;
    L9:
        this.interfaces = r32;
        this.sourceFile = r6;
        this.classData = new ClassDataItem(r2);
        this.staticValuesItem = null;
        this.annotationsDirectory = new AnnotationsDirectoryItem();
        return;
    L8:
        r32 = new TypeListItem(r5);
        goto L9
    L11:
        f$$ExternalSyntheticBUOutline2.m("interfaces == null");
        throw null;
    L13:
        f$$ExternalSyntheticBUOutline2.m("thisClass == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r7) {
        TypeIdsSection r0 = r7.getTypeIds();
        MixedItemSection r1 = r7.getByteData();
        MixedItemSection r2 = r7.getWordData();
        MixedItemSection r3 = r7.getTypeLists();
        StringIdsSection r4 = r7.getStringIds();
        r0.intern(this.thisClass);
        if (this.classData.isEmpty() == true) goto L7;
        r7.getClassData().add(this.classData);
        CstArray r72 = this.classData.getStaticValuesConstant();
        if (r72 == null) goto L7;
        this.staticValuesItem = (EncodedArrayItem) r1.intern(new EncodedArrayItem(r72));
    L7:
        CstType r73 = this.superclass;
        if (r73 == null) goto L10;
        r0.intern(r73);
    L10:
        TypeListItem r74 = this.interfaces;
        if (r74 == null) goto L13;
        this.interfaces = (TypeListItem) r3.intern(r74);
    L13:
        CstString r75 = this.sourceFile;
        if (r75 == null) goto L17;
        r4.intern(r75);
    L17:
        if (this.annotationsDirectory.isEmpty() == true) goto L24;
        boolean r76 = this.annotationsDirectory.isInternable();
        AnnotationsDirectoryItem r02 = this.annotationsDirectory;
        if (r76 == false) goto L22;
        this.annotationsDirectory = (AnnotationsDirectoryItem) r2.intern(r02);
        return;
    L22:
        r2.add(r02);
        return;
    }

    public void addDirectMethod(EncodedMethod r1) {
        this.classData.addDirectMethod(r1);
    }

    public void addFieldAnnotations(CstFieldRef r1, Annotations r2, DexFile r3) {
        this.annotationsDirectory.addFieldAnnotations(r1, r2, r3);
    }

    public void addInstanceField(EncodedField r1) {
        this.classData.addInstanceField(r1);
    }

    public void addMethodAnnotations(CstMethodRef r1, Annotations r2, DexFile r3) {
        this.annotationsDirectory.addMethodAnnotations(r1, r2, r3);
    }

    public void addParameterAnnotations(CstMethodRef r1, AnnotationsList r2, DexFile r3) {
        this.annotationsDirectory.addParameterAnnotations(r1, r2, r3);
    }

    public void addStaticField(EncodedField r1, Constant r2) {
        this.classData.addStaticField(r1, r2);
    }

    public void addVirtualMethod(EncodedMethod r1) {
        this.classData.addVirtualMethod(r1);
    }

    public void debugPrint(Writer r5, boolean r6) {
        PrintWriter r0 = Writers.printWriterFor(r5);
        r0.println(ClassDefItem.class.getName().concat(" {"));
        r0.println("  accessFlags: " + Hex.u2(this.accessFlags));
        r0.println("  superclass: " + this.superclass);
        StringBuilder r1 = new StringBuilder("  interfaces: ");
        Object r2 = this.interfaces;
        String r3 = "<none>";
        if (r2 != null) goto L5;
        r2 = "<none>";
    L5:
        r1.append(r2);
        r0.println(r1.toString());
        StringBuilder r12 = new StringBuilder("  sourceFile: ");
        CstString r22 = this.sourceFile;
        if (r22 == null) goto L9;
        r3 = r22.toQuoted();
    L9:
        r12.append(r3);
        r0.println(r12.toString());
        this.classData.debugPrint(r5, r6);
        this.annotationsDirectory.debugPrint(r0);
        r0.println("}");
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public TypeList getInterfaces() {
        TypeListItem r0 = this.interfaces;
        if (r0 != null) goto L7;
        return StdTypeList.EMPTY;
    L7:
        return r0.getList();
    }

    public Annotations getMethodAnnotations(CstMethodRef r1) {
        return this.annotationsDirectory.getMethodAnnotations(r1);
    }

    public ArrayList<EncodedMethod> getMethods() {
        return this.classData.getMethods();
    }

    public AnnotationsList getParameterAnnotations(CstMethodRef r1) {
        return this.annotationsDirectory.getParameterAnnotations(r1);
    }

    public CstString getSourceFile() {
        return this.sourceFile;
    }

    public CstType getSuperclass() {
        return this.superclass;
    }

    public CstType getThisClass() {
        return this.thisClass;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_CLASS_DEF_ITEM;
    }

    public void setClassAnnotations(Annotations r1, DexFile r2) {
        this.annotationsDirectory.setClassAnnotations(r1, r2);
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 32;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r17, AnnotatedOutput r18) {
        boolean r2 = r18.annotates();
        TypeIdsSection r3 = r17.getTypeIds();
        int r4 = r3.indexOf(this.thisClass);
        CstType r5 = this.superclass;
        int r6 = -1;
        if (r5 != null) goto L5;
        int r32 = -1;
    L6:
        int r52 = OffsettedItem.getAbsoluteOffsetOr0(this.interfaces);
        if (this.annotationsDirectory.isEmpty() == false) goto L9;
        int r7 = 0;
    L11:
        if (this.sourceFile == null) goto L15;
        r6 = r17.getStringIds().indexOf(this.sourceFile);
    L15:
        if (this.classData.isEmpty() == false) goto L17;
        int r9 = 0;
    L18:
        int r10 = OffsettedItem.getAbsoluteOffsetOr0(this.staticValuesItem);
        if (r2 == false) goto L34;
        r18.annotate(0, indexString() + ' ' + this.thisClass.toHuman());
        StringBuilder r22 = new StringBuilder("  class_idx:           ");
        r22.append(Hex.u4(r4));
        r18.annotate(4, r22.toString());
        r18.annotate(4, "  access_flags:        " + AccessFlags.classString(this.accessFlags));
        StringBuilder r23 = new StringBuilder("  superclass_idx:      ");
        r23.append(Hex.u4(r32));
        r23.append(" // ");
        CstType r13 = this.superclass;
        String r14 = "<none>";
        if (r13 != null) goto L23;
        String r132 = "<none>";
    L24:
        r23.append(r132);
        r18.annotate(4, r23.toString());
        r18.annotate(4, "  interfaces_off:      " + Hex.u4(r52));
        if (r52 == 0) goto L29;
        TypeList r24 = this.interfaces.getList();
        int r133 = r24.size();
        int r15 = 0;
    L27:
        if (r15 >= r133) goto L29;
        r18.annotate(0, "    " + r24.getType(r15).toHuman());
        r15 = r15 + 1;
    L29:
        StringBuilder r25 = new StringBuilder("  source_file_idx:     ");
        r25.append(Hex.u4(r6));
        r25.append(" // ");
        CstString r8 = this.sourceFile;
        if (r8 == null) goto L33;
        r14 = r8.toHuman();
    L33:
        r25.append(r14);
        r18.annotate(4, r25.toString());
        r18.annotate(4, "  annotations_off:     " + Hex.u4(r7));
        r18.annotate(4, "  class_data_off:      " + Hex.u4(r9));
        r18.annotate(4, "  static_values_off:   " + Hex.u4(r10));
        goto L34
    L23:
        r132 = r13.toHuman();
    L34:
        r18.writeInt(r4);
        r18.writeInt(this.accessFlags);
        r18.writeInt(r32);
        r18.writeInt(r52);
        r18.writeInt(r6);
        r18.writeInt(r7);
        r18.writeInt(r9);
        r18.writeInt(r10);
        return;
    L17:
        r9 = this.classData.getAbsoluteOffset();
        goto L18
    L9:
        r7 = this.annotationsDirectory.getAbsoluteOffset();
        goto L11
    L5:
        r32 = r3.indexOf(r5);
        goto L6
    }
}
