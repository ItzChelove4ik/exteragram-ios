package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes.dex */
public final class ByteArray {
    private final byte[] bytes;
    private final int size;
    private final int start;

    /* loaded from: classes4.dex */
    public interface GetCursor {
        int getCursor();
    }

    /* loaded from: classes4.dex */
    public static class MyDataInputStream extends DataInputStream implements InputStreamRetargetInterface {
        private final MyInputStream wrapped;

        public MyDataInputStream(MyInputStream r1) {
            super(r1);
            this.wrapped = r1;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    /* loaded from: classes4.dex */
    public class MyInputStream extends InputStream implements InputStreamRetargetInterface {
        private int cursor;
        private int mark;
        final /* synthetic */ ByteArray this$0;

        public MyInputStream(ByteArray r1) {
            this.this$0 = r1;
            this.cursor = 0;
            this.mark = 0;
        }

        @Override // java.io.InputStream
        public int available() {
            return ByteArray.access$000(this.this$0) - this.cursor;
        }

        @Override // java.io.InputStream
        public void mark(int r1) {
            this.mark = this.cursor;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.cursor < ByteArray.access$000(this.this$0)) goto L6;
            return -1;
        L6:
            int r0 = ByteArray.access$100(this.this$0, this.cursor);
            this.cursor++;
            return r0;
        }

        @Override // java.io.InputStream
        public int read(byte[] r4, int r5, int r6) {
            if ((r5 + r6) <= r4.length) goto L5;
            r6 = r4.length - r5;
        L5:
            int r0 = ByteArray.access$000(this.this$0) - this.cursor;
            if (r6 <= r0) goto L8;
            r6 = r0;
        L8:
            System.arraycopy(ByteArray.access$200(this.this$0), this.cursor + ByteArray.access$300(this.this$0), r4, r5, r6);
            this.cursor += r6;
            return r6;
        }

        @Override // java.io.InputStream
        public void reset() {
            this.cursor = this.mark;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public ByteArray(byte[] r3) {
        this(r3, 0, r3.length);
    }

    public ByteArray(byte[] r3, int r4, int r5) {
        if (r3 == null) goto L16;
        if (r4 < 0) goto L14;
        if (r5 >= r4) goto L7;
        f$$ExternalSyntheticBUOutline1.m("end < start");
        throw null;
    L7:
        if (r5 > r3.length) goto L10;
        this.bytes = r3;
        this.start = r4;
        this.size = r5 - r4;
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("end > bytes.length");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline1.m("start < 0");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("bytes == null");
        throw null;
    }

    public static /* synthetic */ int access$000(ByteArray r0) {
        return r0.size;
    }

    public static /* synthetic */ int access$100(ByteArray r0, int r1) {
        return r0.getUnsignedByte0(r1);
    }

    public static /* synthetic */ byte[] access$200(ByteArray r0) {
        return r0.bytes;
    }

    public static /* synthetic */ int access$300(ByteArray r0) {
        return r0.start;
    }

    private void checkOffsets(int r4, int r5) {
        if (r4 < 0) goto L8;
        if (r5 < r4) goto L8;
        if (r5 > this.size) goto L8;
        return;
    L8:
        throw new IllegalArgumentException("bad range: " + r4 + ".." + r5 + "; actual size " + this.size);
    }

    private int getByte0(int r2) {
        return this.bytes[this.start + r2];
    }

    private int getUnsignedByte0(int r2) {
        return this.bytes[this.start + r2] & UByte.MAX_VALUE;
    }

    public int getByte(int r2) {
        checkOffsets(r2, r2 + 1);
        return getByte0(r2);
    }

    public void getBytes(byte[] r3, int r4) {
        int r0 = r3.length - r4;
        int r1 = this.size;
        if (r0 < r1) goto L6;
        System.arraycopy(this.bytes, this.start, r3, r4, r1);
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("(out.length - offset) < size()");
    }

    public int getInt(int r3) {
        checkOffsets(r3, r3 + 4);
        int r0 = ((getByte0(r3) << 24) | (getUnsignedByte0(r3 + 1) << 16)) | (getUnsignedByte0(r3 + 2) << 8);
        return getUnsignedByte0(r3 + 3) | r0;
    }

    public long getLong(int r4) {
        checkOffsets(r4, r4 + 8);
        int r0 = (((getByte0(r4) << 24) | (getUnsignedByte0(r4 + 1) << 16)) | (getUnsignedByte0(r4 + 2) << 8)) | getUnsignedByte0(r4 + 3);
        int r1 = ((getByte0(r4 + 4) << 24) | (getUnsignedByte0(r4 + 5) << 16)) | (getUnsignedByte0(r4 + 6) << 8);
        return ((getUnsignedByte0(r4 + 7) | r1) & 4294967295L) | (r0 << 32);
    }

    public int getShort(int r2) {
        checkOffsets(r2, r2 + 2);
        int r0 = getByte0(r2) << 8;
        return getUnsignedByte0(r2 + 1) | r0;
    }

    public int getUnsignedByte(int r2) {
        checkOffsets(r2, r2 + 1);
        return getUnsignedByte0(r2);
    }

    public int getUnsignedShort(int r2) {
        checkOffsets(r2, r2 + 2);
        int r0 = getUnsignedByte0(r2) << 8;
        return getUnsignedByte0(r2 + 1) | r0;
    }

    public MyDataInputStream makeDataInputStream() {
        return new MyDataInputStream(makeInputStream());
    }

    public MyInputStream makeInputStream() {
        return new MyInputStream(this);
    }

    public int size() {
        return this.size;
    }

    public ByteArray slice(int r1, int r2) {
        checkOffsets(r1, r2);
        return new ByteArray(Arrays.copyOfRange(this.bytes, r1, r2));
    }

    public int underlyingOffset(int r1) {
        return this.start + r1;
    }
}
