package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.AnnotatedOutput;
import java.util.Collection;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class Section {
    private final int alignment;
    private final DexFile file;
    private int fileOffset;
    private final String name;
    private boolean prepared;

    public Section(String r1, DexFile r2, int r3) {
        if (r2 == null) goto L6;
        validateAlignment(r3);
        this.name = r1;
        this.file = r2;
        this.alignment = r3;
        this.fileOffset = -1;
        this.prepared = false;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("file == null");
        throw null;
    }

    public static void validateAlignment(int r1) {
        if (r1 > 0) goto L4;
    L6:
        f$$ExternalSyntheticBUOutline1.m("invalid alignment");
        return;
    L4:
        if ((r1 & (r1 - 1)) != 0) goto L6;
    }

    public final void align(AnnotatedOutput r1) {
        r1.alignTo(this.alignment);
    }

    public abstract int getAbsoluteItemOffset(Item r1);

    public final int getAbsoluteOffset(int r1) {
        if (r1 < 0) goto L10;
        int r0 = this.fileOffset;
        if (r0 >= 0) goto L6;
        GlShader$$ExternalSyntheticBUOutline1.m("fileOffset not yet set");
        return 0;
    L6:
        return r0 + r1;
    L10:
        f$$ExternalSyntheticBUOutline1.m("relative < 0");
        return 0;
    }

    public final int getAlignment() {
        return this.alignment;
    }

    public final DexFile getFile() {
        return this.file;
    }

    public final int getFileOffset() {
        int r0 = this.fileOffset;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("fileOffset not set");
        return 0;
    }

    public final String getName() {
        return this.name;
    }

    public abstract Collection<? extends Item> items();

    public final void prepare() {
        throwIfPrepared();
        prepare0();
        this.prepared = true;
    }

    public abstract void prepare0();

    public final int setFileOffset(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("fileOffset < 0");
        return 0;
    L4:
        if (this.fileOffset >= 0) goto L7;
        int r0 = this.alignment - 1;
        int r22 = (r2 + r0) & (~r0);
        this.fileOffset = r22;
        return r22;
    L7:
        GlShader$$ExternalSyntheticBUOutline1.m("fileOffset already set");
        return 0;
    }

    public final void throwIfNotPrepared() {
        if (this.prepared == false) goto L5;
        return;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("not prepared");
    }

    public final void throwIfPrepared() {
        if (this.prepared == true) goto L5;
        return;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("already prepared");
    }

    public abstract int writeSize();

    public final void writeTo(AnnotatedOutput r5) {
        throwIfNotPrepared();
        align(r5);
        int r0 = r5.getCursor();
        int r1 = this.fileOffset;
        if (r1 >= 0) goto L5;
        this.fileOffset = r0;
    L7:
        if (r5.annotates() == true) goto L9;
    L13:
        writeTo0(r5);
        return;
    L9:
        if (this.name == null) goto L11;
        r5.annotate(0, "\n" + this.name + ":");
        goto L13
    L11:
        if (r0 == 0) goto L13;
        r5.annotate(0, "\n");
        goto L13
    L5:
        if (r1 == r0) goto L7;
        StringBuilder r12 = new StringBuilder("alignment mismatch: for ");
        r12.append(this);
        int r4 = this.fileOffset;
        r12.append(", at ");
        r12.append(r0);
        r12.append(", but expected ");
        r12.append(r4);
        throw new RuntimeException(r12.toString());
    }

    public abstract void writeTo0(AnnotatedOutput r1);
}
