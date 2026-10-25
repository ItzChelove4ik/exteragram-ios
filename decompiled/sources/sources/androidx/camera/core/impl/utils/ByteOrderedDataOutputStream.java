package androidx.camera.core.impl.utils;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
class ByteOrderedDataOutputStream extends FilterOutputStream {
    private ByteOrder mByteOrder;
    final OutputStream mOutputStream;

    public ByteOrderedDataOutputStream(OutputStream r1, ByteOrder r2) {
        super(r1);
        this.mOutputStream = r1;
        this.mByteOrder = r2;
    }

    public void setByteOrder(ByteOrder r1) {
        this.mByteOrder = r1;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r1) {
        this.mOutputStream.write(r1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] r1, int r2, int r3) {
        this.mOutputStream.write(r1, r2, r3);
    }

    public void writeByte(int r1) {
        this.mOutputStream.write(r1);
    }

    public void writeInt(int r3) {
        ByteOrder r0 = this.mByteOrder;
        if (r0 != ByteOrder.LITTLE_ENDIAN) goto L7;
        this.mOutputStream.write(r3 & 255);
        this.mOutputStream.write((r3 >>> 8) & 255);
        this.mOutputStream.write((r3 >>> 16) & 255);
        this.mOutputStream.write((r3 >>> 24) & 255);
        return;
    L7:
        if (r0 != ByteOrder.BIG_ENDIAN) goto L10;
        this.mOutputStream.write((r3 >>> 24) & 255);
        this.mOutputStream.write((r3 >>> 16) & 255);
        this.mOutputStream.write((r3 >>> 8) & 255);
        this.mOutputStream.write(r3 & 255);
        return;
    }

    public void writeShort(short r3) {
        ByteOrder r0 = this.mByteOrder;
        if (r0 != ByteOrder.LITTLE_ENDIAN) goto L7;
        this.mOutputStream.write(r3 & 255);
        this.mOutputStream.write((r3 >>> 8) & 255);
        return;
    L7:
        if (r0 != ByteOrder.BIG_ENDIAN) goto L10;
        this.mOutputStream.write((r3 >>> 8) & 255);
        this.mOutputStream.write(r3 & 255);
        return;
    }

    public void writeUnsignedInt(long r1) {
        writeInt((int) r1);
    }

    public void writeUnsignedShort(int r1) {
        writeShort((short) r1);
    }
}
