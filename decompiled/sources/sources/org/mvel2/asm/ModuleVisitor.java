package org.mvel2.asm;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ModuleVisitor {
    protected final int api;
    protected ModuleVisitor mv;

    public ModuleVisitor(int r2) {
        this(r2, null);
    }

    public ModuleVisitor(int r3, ModuleVisitor r4) {
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

    public ModuleVisitor getDelegate() {
        return this.mv;
    }

    public void visitEnd() {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitEnd();
        return;
    }

    public void visitExport(String r1, int r2, String... r3) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitExport(r1, r2, r3);
        return;
    }

    public void visitMainClass(String r1) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitMainClass(r1);
        return;
    }

    public void visitOpen(String r1, int r2, String... r3) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitOpen(r1, r2, r3);
        return;
    }

    public void visitPackage(String r1) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitPackage(r1);
        return;
    }

    public void visitProvide(String r1, String... r2) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitProvide(r1, r2);
        return;
    }

    public void visitRequire(String r1, int r2, String r3) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitRequire(r1, r2, r3);
        return;
    }

    public void visitUse(String r1) {
        ModuleVisitor r0 = this.mv;
        if (r0 == null) goto L6;
        r0.visitUse(r1);
        return;
    }
}
