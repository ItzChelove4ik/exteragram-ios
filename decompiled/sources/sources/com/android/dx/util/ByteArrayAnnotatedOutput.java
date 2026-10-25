package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.Leb128;
import com.android.dex.util.ByteOutput;
import com.android.dex.util.ExceptionWithContext;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class ByteArrayAnnotatedOutput implements AnnotatedOutput, ByteOutput {
    private static final int DEFAULT_SIZE = 1000;
    private int annotationWidth;
    private ArrayList<Annotation> annotations;
    private int cursor;
    private byte[] data;
    private int hexCols;
    private final boolean stretchy;
    private boolean verbose;

    /* loaded from: classes4.dex */
    public static class Annotation {
        private int end;
        private final int start;
        private final String text;

        public Annotation(int r1, int r2, String r3) {
            this.start = r1;
            this.end = r2;
            this.text = r3;
        }

        public Annotation(int r2, String r3) {
            this(r2, Integer.MAX_VALUE, r3);
        }

        public int getEnd() {
            return this.end;
        }

        public int getStart() {
            return this.start;
        }

        public String getText() {
            return this.text;
        }

        public void setEnd(int r1) {
            this.end = r1;
        }

        public void setEndIfUnset(int r3) {
            if (this.end != Integer.MAX_VALUE) goto L6;
            this.end = r3;
            return;
        }
    }

    public ByteArrayAnnotatedOutput() {
        this(1000);
    }

    public ByteArrayAnnotatedOutput(int r2) {
        this(new byte[r2], true);
    }

    public ByteArrayAnnotatedOutput(byte[] r2) {
        this(r2, false);
    }

    private ByteArrayAnnotatedOutput(byte[] r2, boolean r3) {
        if (r2 == null) goto L6;
        this.stretchy = r3;
        this.data = r2;
        this.cursor = 0;
        this.verbose = false;
        this.annotations = null;
        this.annotationWidth = 0;
        this.hexCols = 0;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("data == null");
        throw null;
    }

    private void ensureCapacity(int r4) {
        byte[] r0 = this.data;
        if (r0.length >= r4) goto L6;
        byte[] r42 = new byte[(r4 * 2) + 1000];
        System.arraycopy(r0, 0, r42, 0, this.cursor);
        this.data = r42;
        return;
    }

    private static void throwBounds() {
        throw new IndexOutOfBoundsException("attempt to write past the end");
    }

    @Override // com.android.dx.util.Output
    public void alignTo(int r4) {
        int r0 = r4 - 1;
        if (r4 >= 0) goto L5;
    L15:
        f$$ExternalSyntheticBUOutline1.m("bogus alignment");
        return;
    L5:
        if ((r4 & r0) != 0) goto L15;
        int r42 = (this.cursor + r0) & (~r0);
        if (this.stretchy == false) goto L10;
        ensureCapacity(r42);
    L13:
        Arrays.fill(this.data, this.cursor, r42, (byte) 0);
        this.cursor = r42;
        return;
    L10:
        if (r42 <= this.data.length) goto L13;
        throwBounds();
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public void annotate(int r3, String r4) {
        if (this.annotations != null) goto L5;
        return;
    L5:
        endAnnotation();
        int r0 = this.annotations.size();
        if (r0 != 0) goto L8;
        int r02 = 0;
    L9:
        int r1 = this.cursor;
        if (r02 > r1) goto L12;
        r02 = r1;
    L12:
        this.annotations.add(new Annotation(r02, r3 + r02, r4));
        return;
    L8:
        r02 = this.annotations.get(r0 - 1).getEnd();
        goto L9
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public void annotate(String r3) {
        if (this.annotations != null) goto L5;
        return;
    L5:
        endAnnotation();
        this.annotations.add(new Annotation(this.cursor, r3));
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public boolean annotates() {
        if (this.annotations == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.util.Output
    public void assertCursor(int r4) {
        if (this.cursor != r4) goto L6;
        return;
    L6:
        throw new ExceptionWithContext("expected cursor " + r4 + "; actual value: " + this.cursor);
    }

    public void enableAnnotations(int r4, boolean r5) {
        if (this.annotations == null) goto L5;
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("cannot enable annotations");
        return;
    L5:
        if (this.cursor != 0) goto L18;
        if (r4 < 40) goto L16;
        int r0 = (((r4 - 7) / 15) + 1) & (-2);
        int r1 = 6;
        if (r0 >= 6) goto L11;
    L10:
        r0 = r1;
    L14:
        this.annotations = new ArrayList(1000);
        this.annotationWidth = r4;
        this.hexCols = r0;
        this.verbose = r5;
        return;
    L11:
        r1 = 10;
        if (r0 <= 10) goto L14;
    L16:
        f$$ExternalSyntheticBUOutline1.m("annotationWidth < 40");
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public void endAnnotation() {
        ArrayList<Annotation> r0 = this.annotations;
        if (r0 == null) goto L10;
        int r02 = r0.size();
        if (r02 == 0) goto L9;
        this.annotations.get(r02 - 1).setEndIfUnset(this.cursor);
        return;
    L9:
        return;
    }

    public void finishAnnotating() {
        endAnnotation();
        ArrayList<Annotation> r0 = this.annotations;
        if (r0 == null) goto L15;
        int r02 = r0.size();
    L5:
        if (r02 <= 0) goto L17;
        int r2 = r02 - 1;
        Annotation r1 = this.annotations.get(r2);
        if (r1.getStart() <= this.cursor) goto L9;
        this.annotations.remove(r2);
        r02 = r02 - 1;
        goto L5
    L9:
        int r03 = r1.getEnd();
        int r5 = this.cursor;
        if (r03 <= r5) goto L16;
        r1.setEnd(r5);
        return;
    L16:
        return;
    L17:
        return;
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public int getAnnotationWidth() {
        int r0 = this.hexCols;
        int r2 = this.annotationWidth;
        return r2 - (((r0 * 2) + 8) + (r0 / 2));
    }

    public byte[] getArray() {
        return this.data;
    }

    @Override // com.android.dx.util.Output
    public int getCursor() {
        return this.cursor;
    }

    @Override // com.android.dx.util.AnnotatedOutput
    public boolean isVerbose() {
        return this.verbose;
    }

    public byte[] toByteArray() {
        int r0 = this.cursor;
        byte[] r1 = new byte[r0];
        System.arraycopy(this.data, 0, r1, 0, r0);
        return r1;
    }

    @Override // com.android.dx.util.Output
    public void write(ByteArray r4) {
        int r0 = r4.size();
        int r1 = this.cursor;
        int r02 = r0 + r1;
        if (this.stretchy == false) goto L6;
        ensureCapacity(r02);
    L9:
        r4.getBytes(this.data, r1);
        this.cursor = r02;
        return;
    L6:
        if (r02 <= this.data.length) goto L9;
        throwBounds();
    }

    @Override // com.android.dx.util.Output
    public void write(byte[] r3) {
        write(r3, 0, r3.length);
    }

    @Override // com.android.dx.util.Output
    public void write(byte[] r8, int r9, int r10) {
        int r0 = this.cursor;
        int r6 = r0 + r10;
        int r1 = r9 + r10;
        if (((r9 | r10) | r6) >= 0) goto L5;
    L15:
        Hex$$ExternalSyntheticBUOutline0.m("bytes.length ", r8.length, "; ", r9, "..!", r6);
        return;
    L5:
        if (r1 > r8.length) goto L15;
        if (this.stretchy == false) goto L10;
        ensureCapacity(r6);
    L13:
        System.arraycopy(r8, r9, this.data, r0, r10);
        this.cursor = r6;
        return;
    L10:
        if (r6 <= this.data.length) goto L13;
        throwBounds();
    }

    public void writeAnnotationsTo(Writer r13) {
        TwoColumnOutput r2 = new TwoColumnOutput(r13, (this.annotationWidth - r0) - 1, getAnnotationWidth(), "|");
        Writer r132 = r2.getLeft();
        Writer r0 = r2.getRight();
        int r1 = this.annotations.size();
        int r3 = 0;
        int r5 = 0;
    L3:
        int r4 = this.cursor;
        if (r5 >= r4) goto L11;
        if (r3 >= r1) goto L11;
        Annotation r42 = this.annotations.get(r3);
        int r6 = r42.getStart();
        if (r5 >= r6) goto L9;
        String r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r11 = r6;
        r6 = r5;
    L10:
        r132.write(Hex.dump(this.data, r6, r11 - r6, r6, this.hexCols, 6));
        r0.write(r43);
        r2.flush();
        r5 = r11;
        goto L3
    L9:
        int r52 = r42.getEnd();
        r43 = r42.getText();
        r3 = r3 + 1;
        r11 = r52;
    L11:
        if (r5 >= r4) goto L13;
        r132.write(Hex.dump(this.data, r5, r4 - r5, r5, this.hexCols, 6));
    L13:
        if (r3 >= r1) goto L15;
        r0.write(this.annotations.get(r3).getText());
        r3 = r3 + 1;
        goto L13
    L15:
        r2.flush();
    }

    @Override // com.android.dx.util.Output, com.android.dex.util.ByteOutput
    public void writeByte(int r4) {
        int r0 = this.cursor;
        int r1 = r0 + 1;
        if (this.stretchy == false) goto L6;
        ensureCapacity(r1);
    L9:
        this.data[r0] = (byte) r4;
        this.cursor = r1;
        return;
    L6:
        if (r1 <= this.data.length) goto L9;
        throwBounds();
    }

    @Override // com.android.dx.util.Output
    public void writeInt(int r6) {
        int r0 = this.cursor;
        int r1 = r0 + 4;
        if (this.stretchy == false) goto L6;
        ensureCapacity(r1);
    L9:
        byte[] r2 = this.data;
        r2[r0] = (byte) r6;
        r2[r0 + 1] = (byte) (r6 >> 8);
        r2[r0 + 2] = (byte) (r6 >> 16);
        r2[r0 + 3] = (byte) (r6 >> 24);
        this.cursor = r1;
        return;
    L6:
        if (r1 <= this.data.length) goto L9;
        throwBounds();
    }

    @Override // com.android.dx.util.Output
    public void writeLong(long r7) {
        int r0 = this.cursor;
        int r1 = r0 + 8;
        if (this.stretchy == false) goto L6;
        ensureCapacity(r1);
    L9:
        int r2 = (int) r7;
        byte[] r3 = this.data;
        r3[r0] = (byte) r2;
        r3[r0 + 1] = (byte) (r2 >> 8);
        r3[r0 + 2] = (byte) (r2 >> 16);
        r3[r0 + 3] = (byte) (r2 >> 24);
        int r72 = (int) (r7 >> 32);
        r3[r0 + 4] = (byte) r72;
        r3[r0 + 5] = (byte) (r72 >> 8);
        r3[r0 + 6] = (byte) (r72 >> 16);
        r3[r0 + 7] = (byte) (r72 >> 24);
        this.cursor = r1;
        return;
    L6:
        if (r1 <= this.data.length) goto L9;
        throwBounds();
    }

    @Override // com.android.dx.util.Output
    public void writeShort(int r5) {
        int r0 = this.cursor;
        int r1 = r0 + 2;
        if (this.stretchy == false) goto L6;
        ensureCapacity(r1);
    L9:
        byte[] r2 = this.data;
        r2[r0] = (byte) r5;
        r2[r0 + 1] = (byte) (r5 >> 8);
        this.cursor = r1;
        return;
    L6:
        if (r1 <= this.data.length) goto L9;
        throwBounds();
    }

    @Override // com.android.dx.util.Output
    public int writeSleb128(int r2) {
        if (this.stretchy == false) goto L5;
        ensureCapacity(this.cursor + 5);
    L5:
        int r0 = this.cursor;
        Leb128.writeSignedLeb128(this, r2);
        return this.cursor - r0;
    }

    @Override // com.android.dx.util.Output
    public int writeUleb128(int r2) {
        if (this.stretchy == false) goto L5;
        ensureCapacity(this.cursor + 5);
    L5:
        int r0 = this.cursor;
        Leb128.writeUnsignedLeb128(this, r2);
        return this.cursor - r0;
    }

    @Override // com.android.dx.util.Output
    public void writeZeroes(int r4) {
        if (r4 < 0) goto L12;
        int r0 = this.cursor + r4;
        if (this.stretchy == false) goto L7;
        ensureCapacity(r0);
    L10:
        Arrays.fill(this.data, this.cursor, r0, (byte) 0);
        this.cursor = r0;
        return;
    L7:
        if (r0 <= this.data.length) goto L10;
        throwBounds();
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("count < 0");
    }
}
