package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class AnnotationVisitor {
    protected final int api;
    protected AnnotationVisitor av;

    public AnnotationVisitor(int r2) {
        this(r2, null);
    }

    public AnnotationVisitor(int r3, AnnotationVisitor r4) {
        if (r3 != 589824) goto L5;
    L18:
        if (r3 != 17432576) goto L20;
        Constants.checkAsmExperimental(this);
    L20:
        this.api = r3;
        this.av = r4;
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

    public AnnotationVisitor getDelegate() {
        return this.av;
    }

    public void visit(String r1, Object r2) {
        AnnotationVisitor r0 = this.av;
        if (r0 == null) goto L6;
        r0.visit(r1, r2);
        return;
    }

    public AnnotationVisitor visitAnnotation(String r1, String r2) {
        AnnotationVisitor r0 = this.av;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotation(r1, r2);
    }

    public AnnotationVisitor visitArray(String r1) {
        AnnotationVisitor r0 = this.av;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitArray(r1);
    }

    public void visitEnd() {
        AnnotationVisitor r0 = this.av;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public void visitEnum(String r1, String r2, String r3) {
        AnnotationVisitor r0 = this.av;
        if (r0 == null) goto L6;
        r0.visitEnum(r1, r2, r3);
        return;
    }
}
