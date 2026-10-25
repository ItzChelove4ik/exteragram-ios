package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.util.AnnotatedOutput;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class OffsettedItem extends Item implements Comparable<OffsettedItem> {
    private Section addedTo;
    private final int alignment;
    private int offset;
    private int writeSize;

    public OffsettedItem(int r2, int r3) {
        Section.validateAlignment(r2);
        if (r3 < (-1)) goto L6;
        this.alignment = r2;
        this.writeSize = r3;
        this.addedTo = null;
        this.offset = -1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("writeSize < -1");
        throw null;
    }

    public static int getAbsoluteOffsetOr0(OffsettedItem r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.getAbsoluteOffset();
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public final int compareTo2(OffsettedItem r3) {
        if (this != r3) goto L5;
        return 0;
    L5:
        ItemType r0 = itemType();
        ItemType r1 = r3.itemType();
        if (r0 == r1) goto L10;
        return r0.compareTo(r1);
    L10:
        return compareTo0(r3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(OffsettedItem r1) {
        return compareTo2(r1);
    }

    public int compareTo0(OffsettedItem r1) {
        throw new UnsupportedOperationException("unsupported");
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L5;
        return true;
    L5:
        OffsettedItem r52 = (OffsettedItem) r5;
        if (itemType() == r52.itemType()) goto L9;
        return false;
    L9:
        if (compareTo0(r52) != 0) goto L11;
        return true;
    L11:
        return false;
    }

    public final int getAbsoluteOffset() {
        int r0 = this.offset;
        if (r0 >= 0) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("offset not yet known");
        return 0;
    L5:
        return this.addedTo.getAbsoluteOffset(r0);
    }

    public final int getAlignment() {
        return this.alignment;
    }

    public final int getRelativeOffset() {
        int r0 = this.offset;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("offset not yet known");
        return 0;
    }

    public final String offsetString() {
        return "[" + Integer.toHexString(getAbsoluteOffset()) + ']';
    }

    public final int place(Section r3, int r4) {
        if (r3 == null) goto L13;
        if (r4 >= 0) goto L6;
        f$$ExternalSyntheticBUOutline1.m("offset < 0");
        return 0;
    L6:
        if (this.addedTo != null) goto L9;
        int r0 = this.alignment - 1;
        int r42 = (r4 + r0) & (~r0);
        this.addedTo = r3;
        this.offset = r42;
        place0(r3, r42);
        return r42;
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("already written");
        return 0;
    L13:
        f$$ExternalSyntheticBUOutline2.m("addedTo == null");
        return 0;
    }

    public void place0(Section r1, int r2) {
    }

    public final void setWriteSize(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("writeSize < 0");
        return;
    L4:
        if (this.writeSize >= 0) goto L7;
        this.writeSize = r2;
        return;
    L7:
        ByteString$$ExternalSyntheticBUOutline0.m("writeSize already set");
    }

    public abstract String toHuman();

    @Override // com.android.dx.dex.file.Item
    public final int writeSize() {
        int r0 = this.writeSize;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("writeSize is unknown");
        return 0;
    }

    @Override // com.android.dx.dex.file.Item
    public final void writeTo(DexFile r2, AnnotatedOutput r3) {
        r3.alignTo(this.alignment);
    L8:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while writing " + this);
    L4:
        if (this.writeSize < 0) goto L11;
        r3.assertCursor(getAbsoluteOffset());     // Catch: RuntimeException -> L8
        writeTo0(r2, r3);
        return;
    L11:
        throw new UnsupportedOperationException("writeSize is unknown");     // Catch: RuntimeException -> L8
    }

    public abstract void writeTo0(DexFile r1, AnnotatedOutput r2);
}
