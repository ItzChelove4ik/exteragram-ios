package org.mvel2.asm;

import org.mvel2.asm.Attribute;

/* loaded from: classes5.dex */
final class RecordComponentWriter extends RecordComponentVisitor {
    private final int descriptorIndex;
    private Attribute firstAttribute;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private final int nameIndex;
    private int signatureIndex;
    private final SymbolTable symbolTable;

    public RecordComponentWriter(SymbolTable r2, String r3, String r4, String r5) {
        super(Opcodes.ASM9);
        this.symbolTable = r2;
        this.nameIndex = r2.addConstantUtf8(r3);
        this.descriptorIndex = r2.addConstantUtf8(r4);
        if (r5 == null) goto L6;
        this.signatureIndex = r2.addConstantUtf8(r5);
        return;
    }

    public final void collectAttributePrototypes(Attribute.Set r1) {
        r1.addAttributes(this.firstAttribute);
    }

    public int computeRecordComponentInfoSize() {
        int r0 = (Attribute.computeAttributesSize(this.symbolTable, 0, this.signatureIndex) + 6) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        Attribute r1 = this.firstAttribute;
        if (r1 != null) goto L5;
        return r0;
    L5:
        return r0 + r1.computeAttributesSize(this.symbolTable);
    }

    public void putRecordComponentInfo(ByteVector r10) {
        r10.putShort(this.nameIndex).putShort(this.descriptorIndex);
        if (this.signatureIndex == 0) goto L5;
        int r0 = 1;
    L7:
        if (this.lastRuntimeVisibleAnnotation == null) goto L10;
        r0 = r0 + 1;
    L10:
        if (this.lastRuntimeInvisibleAnnotation == null) goto L13;
        r0 = r0 + 1;
    L13:
        if (this.lastRuntimeVisibleTypeAnnotation == null) goto L16;
        r0 = r0 + 1;
    L16:
        if (this.lastRuntimeInvisibleTypeAnnotation == null) goto L18;
        r0 = r0 + 1;
    L18:
        Attribute r2 = this.firstAttribute;
        if (r2 == null) goto L21;
        r0 = r0 + r2.getAttributeCount();
    L21:
        r10.putShort(r0);
        Attribute.putAttributes(this.symbolTable, 0, this.signatureIndex, r10);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, r10);
        Attribute r102 = this.firstAttribute;
        if (r102 == null) goto L25;
        r102.putAttributes(this.symbolTable, r10);
        return;
    L25:
        return;
    L5:
        r0 = 0;
        goto L7
    }

    @Override // org.mvel2.asm.RecordComponentVisitor
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

    @Override // org.mvel2.asm.RecordComponentVisitor
    public void visitAttribute(Attribute r2) {
        r2.nextAttribute = this.firstAttribute;
        this.firstAttribute = r2;
    }

    @Override // org.mvel2.asm.RecordComponentVisitor
    public void visitEnd() {
    }

    @Override // org.mvel2.asm.RecordComponentVisitor
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
