package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class FieldVisitor {
    protected final int api;
    protected FieldVisitor fv;

    public FieldVisitor(int r2) {
        this(r2, null);
    }

    public FieldVisitor(int r3, FieldVisitor r4) {
        if (r3 != 589824) goto L5;
    L18:
        if (r3 != 17432576) goto L20;
        Constants.checkAsmExperimental(this);
    L20:
        this.api = r3;
        this.fv = r4;
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

    public FieldVisitor getDelegate() {
        return this.fv;
    }

    public AnnotationVisitor visitAnnotation(String r1, boolean r2) {
        FieldVisitor r0 = this.fv;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.visitAnnotation(r1, r2);
    }

    public void visitAttribute(Attribute r1) {
        FieldVisitor r0 = this.fv;
        if (r0 == null) goto L6;
        r0.visitAttribute(r1);
        return;
    }

    public void visitEnd() {
        FieldVisitor r0 = this.fv;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public AnnotationVisitor visitTypeAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        if (this.api < 327680) goto L10;
        FieldVisitor r2 = this.fv;
        if (r2 != null) goto L7;
        return null;
    L7:
        return r2.visitTypeAnnotation(r3, r4, r5, r6);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m("This feature requires ASM5");
        return null;
    }
}
