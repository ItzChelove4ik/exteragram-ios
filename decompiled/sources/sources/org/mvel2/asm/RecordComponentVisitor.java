package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class RecordComponentVisitor {
    protected final int api;
    protected RecordComponentVisitor delegate;

    public RecordComponentVisitor(int r2) {
        this(r2, null);
    }

    public RecordComponentVisitor(int r3, RecordComponentVisitor r4) {
        if (r3 != 589824) goto L5;
    L18:
        if (r3 != 17432576) goto L20;
        Constants.checkAsmExperimental(this);
    L20:
        this.api = r3;
        this.delegate = r4;
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

    public RecordComponentVisitor getDelegate() {
        return this.delegate;
    }

    public AnnotationVisitor visitAnnotation(String r1, boolean r2) {
        RecordComponentVisitor r0 = this.delegate;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotation(r1, r2);
    }

    public void visitAttribute(Attribute r1) {
        RecordComponentVisitor r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.visitAttribute(r1);
        return;
    }

    public void visitEnd() {
        RecordComponentVisitor r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public AnnotationVisitor visitTypeAnnotation(int r1, TypePath r2, String r3, boolean r4) {
        RecordComponentVisitor r0 = this.delegate;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitTypeAnnotation(r1, r2, r3, r4);
    }
}
