package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class MethodVisitor {
    private static final String REQUIRES_ASM5 = "This feature requires ASM5";
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int r2) {
        this(r2, null);
    }

    public MethodVisitor(int r3, MethodVisitor r4) {
        if (r3 != 589824) goto L5;
    L18:
        if (r3 != 17432576) goto L20;
        Constants.checkAsmExperimental(this);
    L20:
        this.api = r3;
        this.mv = r4;
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

    public MethodVisitor getDelegate() {
        return this.mv;
    }

    public void visitAnnotableParameterCount(int r1, boolean r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitAnnotableParameterCount(r1, r2);
        return;
    }

    public AnnotationVisitor visitAnnotation(String r1, boolean r2) {
        MethodVisitor r0 = this.mv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotation(r1, r2);
    }

    public AnnotationVisitor visitAnnotationDefault() {
        MethodVisitor r0 = this.mv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotationDefault();
    }

    public void visitAttribute(Attribute r1) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitAttribute(r1);
        return;
    }

    public void visitCode() {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitCode();
        return;
    }

    public void visitEnd() {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public void visitFieldInsn(int r1, String r2, String r3, String r4) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitFieldInsn(r1, r2, r3, r4);
        return;
    }

    public void visitFrame(int r1, int r2, Object[] r3, int r4, Object[] r5) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitFrame(r1, r2, r3, r4, r5);
        return;
    }

    public void visitIincInsn(int r1, int r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitIincInsn(r1, r2);
        return;
    }

    public void visitInsn(int r1) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitInsn(r1);
        return;
    }

    public AnnotationVisitor visitInsnAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        if (this.api < 327680) goto L10;
        MethodVisitor r2 = this.mv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitInsnAnnotation(r3, r4, r5, r6);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
        return null;
    }

    public void visitIntInsn(int r1, int r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitIntInsn(r1, r2);
        return;
    }

    public void visitInvokeDynamicInsn(String r3, String r4, Handle r5, Object... r6) {
        if (this.api < 327680) goto L8;
        MethodVisitor r2 = this.mv;
        if (r2 == null) goto L10;
        r2.visitInvokeDynamicInsn(r3, r4, r5, r6);
        return;
    L10:
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
    }

    public void visitJumpInsn(int r1, Label r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitJumpInsn(r1, r2);
        return;
    }

    public void visitLabel(Label r1) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitLabel(r1);
        return;
    }

    public void visitLdcInsn(Object r3) {
        if (this.api >= 327680) goto L14;
        if ((r3 instanceof Handle) == false) goto L7;
    L11:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
        return;
    L7:
        if ((r3 instanceof Type) == false) goto L14;
        if (((Type) r3).getSort() == 11) goto L11;
    L14:
        if (this.api < 458752) goto L16;
    L20:
        MethodVisitor r2 = this.mv;
        if (r2 == null) goto L24;
        r2.visitLdcInsn(r3);
        return;
    L24:
        return;
    L16:
        if ((r3 instanceof ConstantDynamic) == false) goto L20;
        ByteString$$ExternalSyntheticBUOutline0.m("This feature requires ASM7");
    }

    public void visitLineNumber(int r1, Label r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitLineNumber(r1, r2);
        return;
    }

    public void visitLocalVariable(String r1, String r2, String r3, Label r4, Label r5, int r6) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitLocalVariable(r1, r2, r3, r4, r5, r6);
        return;
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int r3, TypePath r4, Label[] r5, Label[] r6, int[] r7, String r8, boolean r9) {
        if (this.api < 327680) goto L10;
        MethodVisitor r2 = this.mv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitLocalVariableAnnotation(r3, r4, r5, r6, r7, r8, r9);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
        return null;
    }

    public void visitLookupSwitchInsn(Label r1, int[] r2, Label[] r3) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitLookupSwitchInsn(r1, r2, r3);
        return;
    }

    public void visitMaxs(int r1, int r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitMaxs(r1, r2);
        return;
    }

    @Deprecated
    public void visitMethodInsn(int r10, String r11, String r12, String r13) {
        boolean r2 = false;
        if (this.api >= 327680) goto L5;
        int r0 = 256;
    L6:
        int r4 = r10 | r0;
        if (r10 != 185) goto L9;
        r2 = true;
    L9:
        visitMethodInsn(r4, r11, r12, r13, r2);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public void visitMethodInsn(int r7, String r8, String r9, String r10, boolean r11) {
        if (this.api < 327680) goto L5;
    L15:
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L19;
        r0.visitMethodInsn(r7 & (-257), r8, r9, r10, r11);
        return;
    L19:
        return;
    L5:
        if ((r7 & 256) != 0) goto L15;
        if (r7 != 185) goto L9;
        boolean r02 = true;
    L10:
        if (r11 != r02) goto L13;
        visitMethodInsn(r7, r8, r9, r10);
        return;
    L13:
        ByteString$$ExternalSyntheticBUOutline0.m("INVOKESPECIAL/STATIC on interfaces requires ASM5");
        return;
    L9:
        r02 = false;
        goto L10
    }

    public void visitMultiANewArrayInsn(String r1, int r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitMultiANewArrayInsn(r1, r2);
        return;
    }

    public void visitParameter(String r3, int r4) {
        if (this.api < 327680) goto L8;
        MethodVisitor r2 = this.mv;
        if (r2 == null) goto L10;
        r2.visitParameter(r3, r4);
        return;
    L10:
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
    }

    public AnnotationVisitor visitParameterAnnotation(int r1, String r2, boolean r3) {
        MethodVisitor r0 = this.mv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitParameterAnnotation(r1, r2, r3);
    }

    public void visitTableSwitchInsn(int r1, int r2, Label r3, Label... r4) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitTableSwitchInsn(r1, r2, r3, r4);
        return;
    }

    public AnnotationVisitor visitTryCatchAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        if (this.api < 327680) goto L10;
        MethodVisitor r2 = this.mv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitTryCatchAnnotation(r3, r4, r5, r6);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
        return null;
    }

    public void visitTryCatchBlock(Label r1, Label r2, Label r3, String r4) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitTryCatchBlock(r1, r2, r3, r4);
        return;
    }

    public AnnotationVisitor visitTypeAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        if (this.api < 327680) goto L10;
        MethodVisitor r2 = this.mv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitTypeAnnotation(r3, r4, r5, r6);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m(REQUIRES_ASM5);
        return null;
    }

    public void visitTypeInsn(int r1, String r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitTypeInsn(r1, r2);
        return;
    }

    public void visitVarInsn(int r1, int r2) {
        MethodVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitVarInsn(r1, r2);
        return;
    }
}
