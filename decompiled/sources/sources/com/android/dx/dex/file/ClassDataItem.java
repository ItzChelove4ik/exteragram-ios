package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.Zeroes;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import com.android.dx.util.Writers;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ClassDataItem extends OffsettedItem {
    private final ArrayList<EncodedMethod> directMethods;
    private byte[] encodedForm;
    private final ArrayList<EncodedField> instanceFields;
    private final ArrayList<EncodedField> staticFields;
    private final HashMap<EncodedField, Constant> staticValues;
    private CstArray staticValuesConstant;
    private final CstType thisClass;
    private final ArrayList<EncodedMethod> virtualMethods;

    public ClassDataItem(CstType r4) {
        super(1, -1);
        if (r4 == null) goto L6;
        this.thisClass = r4;
        this.staticFields = new ArrayList(20);
        this.staticValues = new HashMap(40);
        this.instanceFields = new ArrayList(20);
        this.directMethods = new ArrayList(20);
        this.virtualMethods = new ArrayList(20);
        this.staticValuesConstant = null;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("thisClass == null");
        throw null;
    }

    private static void encodeList(DexFile r4, AnnotatedOutput r5, String r6, ArrayList<? extends EncodedMember> r7) {
        int r0 = r7.size();
        if (r0 == 0) goto L11;
        int r2 = 0;
        if (r5.annotates() == false) goto L8;
        r5.annotate(0, "  " + r6 + ":");
    L8:
        int r62 = 0;
    L9:
        if (r2 >= r0) goto L13;
        r62 = r7.get(r2).encode(r4, r5, r62, r2);
        r2 = r2 + 1;
        goto L9
    L13:
        return;
    }

    private void encodeOutput(DexFile r7, AnnotatedOutput r8) {
        boolean r0 = r8.annotates();
        if (r0 == false) goto L5;
        r8.annotate(0, offsetString() + " class data for " + this.thisClass.toHuman());
    L5:
        encodeSize(r7, r8, "static_fields", this.staticFields.size());
        encodeSize(r7, r8, "instance_fields", this.instanceFields.size());
        encodeSize(r7, r8, "direct_methods", this.directMethods.size());
        encodeSize(r7, r8, "virtual_methods", this.virtualMethods.size());
        encodeList(r7, r8, "static_fields", this.staticFields);
        encodeList(r7, r8, "instance_fields", this.instanceFields);
        encodeList(r7, r8, "direct_methods", this.directMethods);
        encodeList(r7, r8, "virtual_methods", this.virtualMethods);
        if (r0 == false) goto L9;
        r8.endAnnotation();
        return;
    }

    private static void encodeSize(DexFile r0, AnnotatedOutput r1, String r2, int r3) {
        if (r1.annotates() == false) goto L5;
        r1.annotate(String.format("  %-21s %08x", new Object[]{r2 + "_size:", Integer.valueOf(r3)}));
    L5:
        r1.writeUleb128(r3);
    }

    private CstArray makeStaticValuesConstant() {
        Collections.sort(this.staticFields);
        int r0 = this.staticFields.size();
    L3:
        if (r0 <= 0) goto L12;
        Constant r1 = this.staticValues.get(this.staticFields.get(r0 - 1));
        if ((r1 instanceof CstLiteralBits) == true) goto L7;
        if (r1 != null) goto L12;
    L11:
        r0 = r0 - 1;
        goto L3
    L7:
        if (((CstLiteralBits) r1).getLongBits() == 0) goto L11;
    L12:
        if (r0 != 0) goto L15;
        return null;
    L15:
        CstArray.List r12 = new CstArray.List(r0);
        int r2 = 0;
    L16:
        if (r2 >= r0) goto L21;
        EncodedField r3 = this.staticFields.get(r2);
        Constant r4 = this.staticValues.get(r3);
        if (r4 != null) goto L20;
        r4 = Zeroes.zeroFor(r3.getRef().getType());
    L20:
        r12.set(r2, r4);
        r2 = r2 + 1;
        goto L16
    L21:
        r12.setImmutable();
        return new CstArray(r12);
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r6) {
        int r1 = 0;
        if (this.staticFields.isEmpty() == true) goto L8;
        getStaticValuesConstant();
        ArrayList<EncodedField> r0 = this.staticFields;
        int r2 = r0.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L8;
        EncodedField r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.addContents(r6);
    L8:
        if (this.instanceFields.isEmpty() == true) goto L13;
        Collections.sort(this.instanceFields);
        ArrayList<EncodedField> r02 = this.instanceFields;
        int r22 = r02.size();
        int r32 = 0;
    L10:
        if (r32 >= r22) goto L13;
        EncodedField r42 = r02.get(r32);
        r32 = r32 + 1;
        r42.addContents(r6);
    L13:
        if (this.directMethods.isEmpty() == true) goto L18;
        Collections.sort(this.directMethods);
        ArrayList<EncodedMethod> r03 = this.directMethods;
        int r23 = r03.size();
        int r33 = 0;
    L15:
        if (r33 >= r23) goto L18;
        EncodedMethod r43 = r03.get(r33);
        r33 = r33 + 1;
        r43.addContents(r6);
    L18:
        if (this.virtualMethods.isEmpty() == true) goto L22;
        Collections.sort(this.virtualMethods);
        ArrayList<EncodedMethod> r5 = this.virtualMethods;
        int r04 = r5.size();
    L20:
        if (r1 >= r04) goto L27;
        EncodedMethod r24 = r5.get(r1);
        r1 = r1 + 1;
        r24.addContents(r6);
        goto L20
    L27:
        return;
    }

    public void addDirectMethod(EncodedMethod r1) {
        if (r1 == null) goto L5;
        this.directMethods.add(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("method == null");
    }

    public void addInstanceField(EncodedField r1) {
        if (r1 == null) goto L5;
        this.instanceFields.add(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("field == null");
    }

    public void addStaticField(EncodedField r2, Constant r3) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("field == null");
        return;
    L4:
        if (this.staticValuesConstant != null) goto L7;
        this.staticFields.add(r2);
        this.staticValues.put(r2, r3);
        return;
    L7:
        ByteString$$ExternalSyntheticBUOutline0.m("static fields already sorted");
    }

    public void addVirtualMethod(EncodedMethod r1) {
        if (r1 == null) goto L5;
        this.virtualMethods.add(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("method == null");
    }

    public void debugPrint(Writer r7, boolean r8) {
        PrintWriter r72 = Writers.printWriterFor(r7);
        int r0 = this.staticFields.size();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L6;
        r72.println("  sfields[" + r2 + "]: " + this.staticFields.get(r2));
        r2 = r2 + 1;
        goto L4
    L6:
        int r02 = this.instanceFields.size();
        int r22 = 0;
    L7:
        if (r22 >= r02) goto L9;
        r72.println("  ifields[" + r22 + "]: " + this.instanceFields.get(r22));
        r22 = r22 + 1;
        goto L7
    L9:
        int r03 = this.directMethods.size();
        int r23 = 0;
    L11:
        if (r23 >= r03) goto L13;
        r72.println("  dmeths[" + r23 + "]:");
        this.directMethods.get(r23).debugPrint(r72, r8);
        r23 = r23 + 1;
        goto L11
    L13:
        int r04 = this.virtualMethods.size();
    L14:
        if (r1 >= r04) goto L16;
        r72.println("  vmeths[" + r1 + "]:");
        this.virtualMethods.get(r1).debugPrint(r72, r8);
        r1 = r1 + 1;
        goto L14
    }

    public ArrayList<EncodedMethod> getMethods() {
        ArrayList<EncodedMethod> r1 = new ArrayList(this.directMethods.size() + this.virtualMethods.size());
        r1.addAll(this.directMethods);
        r1.addAll(this.virtualMethods);
        return r1;
    }

    public CstArray getStaticValuesConstant() {
        if (this.staticValuesConstant != null) goto L8;
        if (this.staticFields.size() == 0) goto L8;
        this.staticValuesConstant = makeStaticValuesConstant();
    L8:
        return this.staticValuesConstant;
    }

    public boolean isEmpty() {
        if (this.staticFields.isEmpty() == true) goto L5;
        return false;
    L5:
        if (this.instanceFields.isEmpty() == true) goto L7;
        return false;
    L7:
        if (this.directMethods.isEmpty() == true) goto L9;
        return false;
    L9:
        if (this.virtualMethods.isEmpty() == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_CLASS_DATA_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r1, int r2) {
        ByteArrayAnnotatedOutput r22 = new ByteArrayAnnotatedOutput();
        encodeOutput(r1.getFile(), r22);
        byte[] r12 = r22.toByteArray();
        this.encodedForm = r12;
        setWriteSize(r12.length);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return toString();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r2, AnnotatedOutput r3) {
        if (r3.annotates() == false) goto L6;
        encodeOutput(r2, r3);
        return;
    L6:
        r3.write(this.encodedForm);
    }
}
