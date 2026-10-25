package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    public ClassVisitor(int r2) {
        this(r2, null);
    }

    public ClassVisitor(int r3, ClassVisitor r4) {
        if (r3 != 589824) goto L5;
    L18:
        if (r3 != 17432576) goto L20;
        Constants.checkAsmExperimental(this);
    L20:
        this.api = r3;
        this.cv = r4;
        return;
    L5:
        if (r3 == 524288) goto L18;
        if (r3 == 458752) goto L18;
        if (r3 == 393216) goto L18;
        if (r3 == 327680) goto L18;
        if (r3 == 262144) goto L18;
        if (r3 == 17432576) goto L18;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unsupported api ", r3);
        throw null;
    }

    public ClassVisitor getDelegate() {
        return this.cv;
    }

    public void visit(int r8, int r9, String r10, String r11, String r12, String[] r13) {
        if (this.api < 524288) goto L5;
    L9:
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L13;
        r0.visit(r8, r9, r10, r11, r12, r13);
        return;
    L13:
        return;
    L5:
        if ((65536 & r9) == 0) goto L9;
        ByteString$$ExternalSyntheticBUOutline0.m("Records requires ASM8");
    }

    public AnnotationVisitor visitAnnotation(String r1, boolean r2) {
        ClassVisitor r0 = this.cv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotation(r1, r2);
    }

    public void visitAttribute(Attribute r1) {
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L6;
        r0.visitAttribute(r1);
        return;
    }

    public void visitEnd() {
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public FieldVisitor visitField(int r1, String r2, String r3, String r4, Object r5) {
        ClassVisitor r0 = this.cv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitField(r1, r2, r3, r4, r5);
    }

    public void visitInnerClass(String r1, String r2, String r3, int r4) {
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L6;
        r0.visitInnerClass(r1, r2, r3, r4);
        return;
    }

    public MethodVisitor visitMethod(int r1, String r2, String r3, String r4, String[] r5) {
        ClassVisitor r0 = this.cv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitMethod(r1, r2, r3, r4, r5);
    }

    public ModuleVisitor visitModule(String r3, int r4, String r5) {
        if (this.api < 393216) goto L10;
        ClassVisitor r2 = this.cv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitModule(r3, r4, r5);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m("Module requires ASM6");
        return null;
    }

    public void visitNestHost(String r3) {
        if (this.api < 458752) goto L8;
        ClassVisitor r2 = this.cv;
        if (r2 == null) goto L10;
        r2.visitNestHost(r3);
        return;
    L10:
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("NestHost requires ASM7");
    }

    public void visitNestMember(String r3) {
        if (this.api < 458752) goto L8;
        ClassVisitor r2 = this.cv;
        if (r2 == null) goto L10;
        r2.visitNestMember(r3);
        return;
    L10:
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("NestMember requires ASM7");
    }

    public void visitOuterClass(String r1, String r2, String r3) {
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L6;
        r0.visitOuterClass(r1, r2, r3);
        return;
    }

    public void visitPermittedSubclass(String r3) {
        if (this.api < 589824) goto L8;
        ClassVisitor r2 = this.cv;
        if (r2 == null) goto L10;
        r2.visitPermittedSubclass(r3);
        return;
    L10:
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("PermittedSubclasses requires ASM9");
    }

    public RecordComponentVisitor visitRecordComponent(String r3, String r4, String r5) {
        if (this.api < 524288) goto L10;
        ClassVisitor r2 = this.cv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitRecordComponent(r3, r4, r5);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m("Record requires ASM8");
        return null;
    }

    public void visitSource(String r1, String r2) {
        ClassVisitor r0 = this.cv;
        if (r0 == null) goto L6;
        r0.visitSource(r1, r2);
        return;
    }

    public AnnotationVisitor visitTypeAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        if (this.api < 327680) goto L10;
        ClassVisitor r2 = this.cv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitTypeAnnotation(r3, r4, r5, r6);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m("TypeAnnotation requires ASM5");
        return null;
    }
}
