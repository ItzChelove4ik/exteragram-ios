package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
final class NioByteString extends ByteString.LeafByteString {
    private final ByteBuffer buffer;

    public NioByteString(ByteBuffer r2) {
        Internal.checkNotNull(r2, "buffer");
        this.buffer = r2.slice().order(ByteOrder.nativeOrder());
    }

    private ByteBuffer slice(int r3, int r4) {
        if (r3 >= this.buffer.position()) goto L5;
    L9:
        FieldSet$$ExternalSyntheticBUOutline0.m("Invalid indices [%d, %d]", new Object[]{Integer.valueOf(r3), Integer.valueOf(r4)});
        return null;
    L5:
        if (r4 > this.buffer.limit()) goto L9;
        if (r3 > r4) goto L9;
        ByteBuffer r0 = this.buffer.slice();
        Java8Compatibility.position(r0, r3 - this.buffer.position());
        Java8Compatibility.limit(r0, r4 - this.buffer.position());
        return r0;
    }

    @Override // com.google.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return this.buffer.asReadOnlyBuffer();
    }

    @Override // com.google.protobuf.ByteString
    public byte byteAt(int r1) {
        return this.buffer.get(r1);
    L7:
        e = move-exception;
        throw e;
    L4:
        e = move-exception;
        throw new ArrayIndexOutOfBoundsException(e.getMessage());
    }

    @Override // com.google.protobuf.ByteString
    public void copyToInternal(byte[] r1, int r2, int r3, int r4) {
        ByteBuffer r0 = this.buffer.slice();
        Java8Compatibility.position(r0, r2);
        r0.get(r1, r3, r4);
    }

    @Override // com.google.protobuf.ByteString
    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof ByteString) == true) goto L8;
        return false;
    L8:
        ByteString r1 = (ByteString) r6;
        if (size() == r1.size()) goto L12;
        return false;
    L12:
        if (size() != 0) goto L14;
        return true;
    L14:
        boolean r0 = r6 instanceof NioByteString;
        ByteBuffer r5 = this.buffer;
        if (r0 == false) goto L19;
        return r5.equals(((NioByteString) r6).buffer);
    L19:
        return r5.equals(r1.asReadOnlyByteBuffer());
    }

    @Override // com.google.protobuf.ByteString
    public byte internalByteAt(int r1) {
        return byteAt(r1);
    }

    @Override // com.google.protobuf.ByteString
    public boolean isValidUtf8() {
        return Utf8.isValidUtf8(this.buffer);
    }

    @Override // com.google.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this.buffer, true);
    }

    @Override // com.google.protobuf.ByteString
    public int partialHash(int r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r3 = (r3 * 31) + this.buffer.get(r0);
        r0 = r0 + 1;
        goto L4
    L6:
        return r3;
    }

    @Override // com.google.protobuf.ByteString
    public int size() {
        return this.buffer.remaining();
    }

    @Override // com.google.protobuf.ByteString
    public ByteString substring(int r1, int r2) {
        return new NioByteString(slice(r1, r2));
    L7:
        e = move-exception;
        throw e;
    L4:
        e = move-exception;
        throw new ArrayIndexOutOfBoundsException(e.getMessage());
    }

    @Override // com.google.protobuf.ByteString
    public String toStringInternal(Charset r4) {
        if (this.buffer.hasArray() == false) goto L5;
        byte[] r0 = this.buffer.array();
        int r1 = this.buffer.arrayOffset() + this.buffer.position();
        int r3 = this.buffer.remaining();
    L7:
        return new String(r0, r1, r3, r4);
    L5:
        r0 = toByteArray();
        r3 = r0.length;
        r1 = 0;
        goto L7
    }

    @Override // com.google.protobuf.ByteString
    public void writeTo(ByteOutput r1) {
        r1.writeLazy(this.buffer.slice());
    }
}
