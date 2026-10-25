package org.mvel2.asm;

import com.android.dx.cf.attrib.AttConstantValue;
import org.mvel2.asm.Attribute;

/* loaded from: classes5.dex */
final class FieldWriter extends FieldVisitor {
    private final int accessFlags;
    private int constantValueIndex;
    private final int descriptorIndex;
    private Attribute firstAttribute;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private final int nameIndex;
    private int signatureIndex;
    private final SymbolTable symbolTable;

    public FieldWriter(SymbolTable r2, int r3, String r4, String r5, String r6, Object r7) {
        super(Opcodes.ASM9);
        this.symbolTable = r2;
        this.accessFlags = r3;
        this.nameIndex = r2.addConstantUtf8(r4);
        this.descriptorIndex = r2.addConstantUtf8(r5);
        if (r6 == null) goto L5;
        this.signatureIndex = r2.addConstantUtf8(r6);
    L5:
        if (r7 == null) goto L8;
        this.constantValueIndex = r2.addConstant(r7).index;
        return;
    }

    public final void collectAttributePrototypes(Attribute.Set r1) {
        r1.addAttributes(this.firstAttribute);
    }

    public int computeFieldInfoSize() {
        if (this.constantValueIndex == 0) goto L5;
        this.symbolTable.addConstantUtf8(AttConstantValue.ATTRIBUTE_NAME);
        int r0 = 16;
    L6:
        int r02 = (r0 + Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex)) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        Attribute r1 = this.firstAttribute;
        if (r1 != null) goto L9;
        return r02;
    L9:
        return r02 + r1.computeAttributesSize(this.symbolTable);
    L5:
        r0 = 8;
        goto L6
    }

    public void putFieldInfo(ByteVector r10) {
        int r2 = 0;
        if (this.symbolTable.getMajorVersion() >= 49) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        int r1 = 4096;
    L9:
        r10.putShort((~r1) & this.accessFlags).putShort(this.nameIndex).putShort(this.descriptorIndex);
        if (this.constantValueIndex == 0) goto L12;
        r2 = 1;
    L12:
        int r12 = this.accessFlags;
        if ((r12 & 4096) == 0) goto L17;
        if (r0 == false) goto L17;
        r2 = r2 + 1;
    L17:
        if (this.signatureIndex == 0) goto L20;
        r2 = r2 + 1;
    L20:
        if ((131072 & r12) == 0) goto L23;
        r2 = r2 + 1;
    L23:
        if (this.lastRuntimeVisibleAnnotation == null) goto L26;
        r2 = r2 + 1;
    L26:
        if (this.lastRuntimeInvisibleAnnotation == null) goto L29;
        r2 = r2 + 1;
    L29:
        if (this.lastRuntimeVisibleTypeAnnotation == null) goto L32;
        r2 = r2 + 1;
    L32:
        if (this.lastRuntimeInvisibleTypeAnnotation == null) goto L34;
        r2 = r2 + 1;
    L34:
        Attribute r02 = this.firstAttribute;
        if (r02 == null) goto L37;
        r2 = r2 + r02.getAttributeCount();
    L37:
        r10.putShort(r2);
        if (this.constantValueIndex == 0) goto L40;
        r10.putShort(this.symbolTable.addConstantUtf8(AttConstantValue.ATTRIBUTE_NAME)).putInt(2).putShort(this.constantValueIndex);
    L40:
        Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, r10);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, r10);
        Attribute r102 = this.firstAttribute;
        if (r102 == null) goto L44;
        r102.putAttributes(this.symbolTable, r10);
        return;
    L44:
        return;
    L8:
        r1 = 0;
        goto L9
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.mvel2.asm.FieldVisitor
    public AnnotationVisitor visitAnnotation(String r2, boolean r3) {
        SymbolTable r0 = this.symbolTable;
        if (r3 == false) goto L6;
        AnnotationWriter r22 = AnnotationWriter.create(r0, r2, this.lastRuntimeVisibleAnnotation);
        this.lastRuntimeVisibleAnnotation = r22;
        return r22;
    L6:
        AnnotationWriter r23 = AnnotationWriter.create(r0, r2, this.lastRuntimeInvisibleAnnotation);
        this.lastRuntimeInvisibleAnnotation = r23;
        return r23;
    }

    @Override // org.mvel2.asm.FieldVisitor
    public void visitAttribute(Attribute r2) {
        r2.nextAttribute = this.firstAttribute;
        this.firstAttribute = r2;
    }

    @Override // org.mvel2.asm.FieldVisitor
    public void visitEnd() {
    }

    @Override // org.mvel2.asm.FieldVisitor
    public AnnotationVisitor visitTypeAnnotation(int r2, TypePath r3, String r4, boolean r5) {
        SymbolTable r0 = this.symbolTable;
        if (r5 == false) goto L6;
        AnnotationWriter r22 = AnnotationWriter.create(r0, r2, r3, r4, this.lastRuntimeVisibleTypeAnnotation);
        this.lastRuntimeVisibleTypeAnnotation = r22;
        return r22;
    L6:
        AnnotationWriter r23 = AnnotationWriter.create(r0, r2, r3, r4, this.lastRuntimeInvisibleTypeAnnotation);
        this.lastRuntimeInvisibleTypeAnnotation = r23;
        return r23;
    }
}
