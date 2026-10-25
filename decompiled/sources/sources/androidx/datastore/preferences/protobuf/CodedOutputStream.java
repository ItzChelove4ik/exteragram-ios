package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Utf8;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class CodedOutputStream extends ByteOutput {
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = false;
    private static final Logger logger = null;
    private boolean serializationDeterministic;
    CodedOutputStreamWriter wrapper;

    /* renamed from: androidx.datastore.preferences.protobuf.CodedOutputStream$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        public AbstractBufferedEncoder(int r2) {
            super(null);
            if (r2 < 0) goto L6;
            byte[] r22 = new byte[Math.max(r2, 20)];
            this.buffer = r22;
            this.limit = r22.length;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("bufferSize must be >= 0");
            throw null;
        }

        public final void buffer(byte r4) {
            byte[] r0 = this.buffer;
            int r1 = this.position;
            this.position = r1 + 1;
            r0[r1] = r4;
            this.totalBytesWritten++;
        }

        public final void bufferFixed32NoTag(int r6) {
            byte[] r0 = this.buffer;
            int r1 = this.position;
            int r2 = r1 + 1;
            this.position = r2;
            r0[r1] = (byte) (r6 & 255);
            int r3 = r1 + 2;
            this.position = r3;
            r0[r2] = (byte) ((r6 >> 8) & 255);
            int r22 = r1 + 3;
            this.position = r22;
            r0[r3] = (byte) ((r6 >> 16) & 255);
            this.position = r1 + 4;
            r0[r22] = (byte) ((r6 >> 24) & 255);
            this.totalBytesWritten += 4;
        }

        public final void bufferFixed64NoTag(long r10) {
            byte[] r0 = this.buffer;
            int r1 = this.position;
            int r2 = r1 + 1;
            this.position = r2;
            r0[r1] = (byte) (r10 & 255);
            int r5 = r1 + 2;
            this.position = r5;
            r0[r2] = (byte) ((r10 >> 8) & 255);
            int r22 = r1 + 3;
            this.position = r22;
            r0[r5] = (byte) ((r10 >> 16) & 255);
            int r52 = r1 + 4;
            this.position = r52;
            r0[r22] = (byte) (255 & (r10 >> 24));
            int r23 = r1 + 5;
            this.position = r23;
            r0[r52] = (byte) (((int) (r10 >> 32)) & 255);
            int r3 = r1 + 6;
            this.position = r3;
            r0[r23] = (byte) (((int) (r10 >> 40)) & 255);
            int r24 = r1 + 7;
            this.position = r24;
            r0[r3] = (byte) (((int) (r10 >> 48)) & 255);
            this.position = r1 + 8;
            r0[r24] = (byte) (((int) (r10 >> 56)) & 255);
            this.totalBytesWritten += 8;
        }

        public final void bufferInt32NoTag(int r3) {
            if (r3 < 0) goto L5;
            bufferUInt32NoTag(r3);
            return;
        L5:
            bufferUInt64NoTag(r3);
        }

        public final void bufferTag(int r1, int r2) {
            bufferUInt32NoTag(WireFormat.makeTag(r1, r2));
        }

        public final void bufferUInt32NoTag(int r7) {
            if (CodedOutputStream.access$100() == false) goto L10;
            long r0 = this.position;
        L5:
            int r2 = r7 & (-128);
            byte[] r3 = this.buffer;
            if (r2 == 0) goto L7;
            int r22 = this.position;
            this.position = r22 + 1;
            UnsafeUtil.putByte(r3, r22, (byte) ((r7 | 128) & 255));
            r7 = r7 >>> 7;
            goto L5
        L7:
            int r23 = this.position;
            this.position = r23 + 1;
            UnsafeUtil.putByte(r3, r23, (byte) r7);
            this.totalBytesWritten += (int) (this.position - r0);
            return;
        L10:
            int r02 = r7 & (-128);
            byte[] r1 = this.buffer;
            if (r02 == 0) goto L12;
            int r03 = this.position;
            this.position = r03 + 1;
            r1[r03] = (byte) ((r7 | 128) & 255);
            this.totalBytesWritten++;
            r7 = r7 >>> 7;
            goto L10
        L12:
            int r04 = this.position;
            this.position = r04 + 1;
            r1[r04] = (byte) r7;
            this.totalBytesWritten++;
        }

        public final void bufferUInt64NoTag(long r12) {
            if (CodedOutputStream.access$100() == false) goto L10;
            long r6 = this.position;
        L5:
            long r8 = r12 & (-128);
            byte[] r82 = this.buffer;
            if (r8 == 0) goto L7;
            int r0 = this.position;
            this.position = r0 + 1;
            UnsafeUtil.putByte(r82, r0, (byte) ((((int) r12) | 128) & 255));
            r12 = r12 >>> 7;
            goto L5
        L7:
            int r02 = this.position;
            this.position = r02 + 1;
            UnsafeUtil.putByte(r82, r02, (byte) r12);
            this.totalBytesWritten += (int) (this.position - r6);
            return;
        L10:
            long r62 = r12 & (-128);
            byte[] r63 = this.buffer;
            if (r62 == 0) goto L12;
            int r03 = this.position;
            this.position = r03 + 1;
            r63[r03] = (byte) ((((int) r12) | 128) & 255);
            this.totalBytesWritten++;
            r12 = r12 >>> 7;
            goto L10
        L12:
            int r04 = this.position;
            this.position = r04 + 1;
            r63[r04] = (byte) r12;
            this.totalBytesWritten++;
        }
    }

    /* loaded from: classes3.dex */
    public static class OutOfSpaceException extends IOException {
        public OutOfSpaceException(Throwable r2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", r2);
        }
    }

    public static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        public OutputStreamEncoder(OutputStream r1, int r2) {
            super(r2);
            if (r1 == null) goto L6;
            this.out = r1;
            return;
        L6:
            f$$ExternalSyntheticBUOutline2.m("out");
            throw null;
        }

        private void doFlush() {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        private void flushIfNotAvailable(int r3) {
            if ((this.limit - this.position) >= r3) goto L6;
            doFlush();
            return;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void flush() {
            if (this.position <= 0) goto L6;
            doFlush();
            return;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void write(byte r3) {
            if (this.position != this.limit) goto L5;
            doFlush();
        L5:
            buffer(r3);
        }

        public void write(byte[] r5, int r6, int r7) {
            int r0 = this.limit;
            int r1 = this.position;
            int r2 = r0 - r1;
            byte[] r3 = this.buffer;
            if (r2 < r7) goto L6;
            System.arraycopy(r5, r6, r3, r1, r7);
            this.position += r7;
            this.totalBytesWritten += r7;
            return;
        L6:
            int r02 = r0 - r1;
            System.arraycopy(r5, r6, r3, r1, r02);
            int r62 = r6 + r02;
            int r72 = r7 - r02;
            this.position = this.limit;
            this.totalBytesWritten += r02;
            doFlush();
            if (r72 > this.limit) goto L9;
            System.arraycopy(r5, r62, this.buffer, 0, r72);
            this.position = r72;
        L10:
            this.totalBytesWritten += r72;
            return;
        L9:
            this.out.write(r5, r62, r72);
            goto L10
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeBool(int r2, boolean r3) {
            flushIfNotAvailable(11);
            bufferTag(r2, 0);
            buffer(r3 ? 1 : 0);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(byte[] r1, int r2, int r3) {
            writeUInt32NoTag(r3);
            write(r1, r2, r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeBytes(int r2, ByteString r3) {
            writeTag(r2, 2);
            writeBytesNoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeBytesNoTag(ByteString r2) {
            writeUInt32NoTag(r2.size());
            r2.writeTo(this);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeFixed32(int r2, int r3) {
            flushIfNotAvailable(14);
            bufferTag(r2, 5);
            bufferFixed32NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int r2) {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(r2);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeFixed64(int r2, long r3) {
            flushIfNotAvailable(18);
            bufferTag(r2, 1);
            bufferFixed64NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long r2) {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(r2);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeInt32(int r2, int r3) {
            flushIfNotAvailable(20);
            bufferTag(r2, 0);
            bufferInt32NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeInt32NoTag(int r3) {
            if (r3 < 0) goto L5;
            writeUInt32NoTag(r3);
            return;
        L5:
            writeUInt64NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream, androidx.datastore.preferences.protobuf.ByteOutput
        public void writeLazy(byte[] r1, int r2, int r3) {
            write(r1, r2, r3);
        }

        public void writeMessage(int r2, MessageLite r3) {
            writeTag(r2, 2);
            writeMessageNoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeMessage(int r2, MessageLite r3, Schema r4) {
            writeTag(r2, 2);
            writeMessageNoTag(r3, r4);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeMessageNoTag(MessageLite r2) {
            writeUInt32NoTag(r2.getSerializedSize());
            r2.writeTo(this);
        }

        public void writeMessageNoTag(MessageLite r2, Schema r3) {
            writeUInt32NoTag(((AbstractMessageLite) r2).getSerializedSize(r3));
            r3.writeTo(r2, this.wrapper);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeMessageSetExtension(int r4, MessageLite r5) {
            writeTag(1, 3);
            writeUInt32(2, r4);
            writeMessage(3, r5);
            writeTag(1, 4);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(int r4, ByteString r5) {
            writeTag(1, 3);
            writeUInt32(2, r4);
            writeBytes(3, r5);
            writeTag(1, 4);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeString(int r2, String r3) {
            writeTag(r2, 2);
            writeStringNoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeStringNoTag(String r6) {
            int r0 = r6.length() * 3;     // Catch: Utf8.UnpairedSurrogateException -> L6
            int r1 = CodedOutputStream.computeUInt32SizeNoTag(r0);     // Catch: Utf8.UnpairedSurrogateException -> L6
            int r2 = r1 + r0;     // Catch: Utf8.UnpairedSurrogateException -> L6
            int r3 = this.limit;     // Catch: Utf8.UnpairedSurrogateException -> L6
            if (r2 <= r3) goto L9;
            byte[] r12 = new byte[r0];     // Catch: Utf8.UnpairedSurrogateException -> L6
            int r02 = Utf8.encode(r6, r12, 0, r0);     // Catch: Utf8.UnpairedSurrogateException -> L6
            writeUInt32NoTag(r02);     // Catch: Utf8.UnpairedSurrogateException -> L6
            writeLazy(r12, 0, r02);     // Catch: Utf8.UnpairedSurrogateException -> L6
            return;
        L9:
            if (r2 <= (r3 - this.position)) goto L11;
            doFlush();     // Catch: Utf8.UnpairedSurrogateException -> L6
        L11:
            int r03 = CodedOutputStream.computeUInt32SizeNoTag(r6.length());     // Catch: Utf8.UnpairedSurrogateException -> L6
            int r22 = this.position;     // Catch: Utf8.UnpairedSurrogateException -> L6
            if (r03 != r1) goto L19;
            int r13 = r22 + r03;
            this.position = r13;     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            int r14 = Utf8.encode(r6, this.buffer, r13, this.limit - r13);     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            this.position = r22;     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            int r32 = (r14 - r22) - r03;     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            bufferUInt32NoTag(r32);     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            this.position = r14;     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
        L20:
            this.totalBytesWritten += r32;
            return;
        L19:
            r32 = Utf8.encodedLength(r6);     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            bufferUInt32NoTag(r32);     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
            this.position = Utf8.encode(r6, this.buffer, this.position, r32);     // Catch: ArrayIndexOutOfBoundsException -> L15 Utf8.UnpairedSurrogateException -> L17
        L17:
            e = move-exception;
            this.totalBytesWritten -= this.position - r22;
            this.position = r22;     // Catch: Utf8.UnpairedSurrogateException -> L6
            throw e;     // Catch: Utf8.UnpairedSurrogateException -> L6
        L15:
            e = move-exception;
            throw new OutOfSpaceException(e);     // Catch: Utf8.UnpairedSurrogateException -> L6
        L6:
            e = move-exception;
            inefficientWriteStringNoTag(r6, e);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeTag(int r1, int r2) {
            writeUInt32NoTag(WireFormat.makeTag(r1, r2));
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeUInt32(int r2, int r3) {
            flushIfNotAvailable(20);
            bufferTag(r2, 0);
            bufferUInt32NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int r2) {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(r2);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeUInt64(int r2, long r3) {
            flushIfNotAvailable(20);
            bufferTag(r2, 0);
            bufferUInt64NoTag(r3);
        }

        @Override // androidx.datastore.preferences.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long r2) {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(r2);
        }
    }

    static {
        logger = Logger.getLogger(CodedOutputStream.class.getName());
        HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();
    }

    private CodedOutputStream() {
    }

    public /* synthetic */ CodedOutputStream(AnonymousClass1 r1) {
        this();
    }

    public static /* synthetic */ boolean access$100() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    public static int computeBoolSize(int r0, boolean r1) {
        return computeTagSize(r0) + computeBoolSizeNoTag(r1);
    }

    public static int computeBoolSizeNoTag(boolean r0) {
        return 1;
    }

    public static int computeByteArraySizeNoTag(byte[] r0) {
        return computeLengthDelimitedFieldSize(r0.length);
    }

    public static int computeBytesSize(int r0, ByteString r1) {
        return computeTagSize(r0) + computeBytesSizeNoTag(r1);
    }

    public static int computeBytesSizeNoTag(ByteString r0) {
        return computeLengthDelimitedFieldSize(r0.size());
    }

    public static int computeDoubleSize(int r0, double r1) {
        return computeTagSize(r0) + computeDoubleSizeNoTag(r1);
    }

    public static int computeDoubleSizeNoTag(double r0) {
        return 8;
    }

    public static int computeEnumSize(int r0, int r1) {
        return computeTagSize(r0) + computeEnumSizeNoTag(r1);
    }

    public static int computeEnumSizeNoTag(int r0) {
        return computeInt32SizeNoTag(r0);
    }

    public static int computeFixed32Size(int r0, int r1) {
        return computeTagSize(r0) + computeFixed32SizeNoTag(r1);
    }

    public static int computeFixed32SizeNoTag(int r0) {
        return 4;
    }

    public static int computeFixed64Size(int r0, long r1) {
        return computeTagSize(r0) + computeFixed64SizeNoTag(r1);
    }

    public static int computeFixed64SizeNoTag(long r0) {
        return 8;
    }

    public static int computeFloatSize(int r0, float r1) {
        return computeTagSize(r0) + computeFloatSizeNoTag(r1);
    }

    public static int computeFloatSizeNoTag(float r0) {
        return 4;
    }

    @Deprecated
    public static int computeGroupSize(int r0, MessageLite r1, Schema r2) {
        return (computeTagSize(r0) * 2) + computeGroupSizeNoTag(r1, r2);
    }

    @Deprecated
    public static int computeGroupSizeNoTag(MessageLite r0) {
        return r0.getSerializedSize();
    }

    @Deprecated
    public static int computeGroupSizeNoTag(MessageLite r0, Schema r1) {
        return ((AbstractMessageLite) r0).getSerializedSize(r1);
    }

    public static int computeInt32Size(int r0, int r1) {
        return computeTagSize(r0) + computeInt32SizeNoTag(r1);
    }

    public static int computeInt32SizeNoTag(int r2) {
        return computeUInt64SizeNoTag(r2);
    }

    public static int computeInt64Size(int r0, long r1) {
        return computeTagSize(r0) + computeInt64SizeNoTag(r1);
    }

    public static int computeInt64SizeNoTag(long r0) {
        return computeUInt64SizeNoTag(r0);
    }

    public static int computeLengthDelimitedFieldSize(int r1) {
        return computeUInt32SizeNoTag(r1) + r1;
    }

    public static int computeMessageSize(int r0, MessageLite r1, Schema r2) {
        return computeTagSize(r0) + computeMessageSizeNoTag(r1, r2);
    }

    public static int computeMessageSizeNoTag(MessageLite r0) {
        return computeLengthDelimitedFieldSize(r0.getSerializedSize());
    }

    public static int computeMessageSizeNoTag(MessageLite r0, Schema r1) {
        return computeLengthDelimitedFieldSize(((AbstractMessageLite) r0).getSerializedSize(r1));
    }

    public static int computePreferredBufferSize(int r1) {
        if (r1 <= 4096) goto L5;
        return 4096;
    L5:
        return r1;
    }

    public static int computeRawMessageSetExtensionSize(int r2, ByteString r3) {
        return ((computeTagSize(1) * 2) + computeUInt32Size(2, r2)) + computeBytesSize(3, r3);
    }

    public static int computeSFixed32Size(int r0, int r1) {
        return computeTagSize(r0) + computeSFixed32SizeNoTag(r1);
    }

    public static int computeSFixed32SizeNoTag(int r0) {
        return 4;
    }

    public static int computeSFixed64Size(int r0, long r1) {
        return computeTagSize(r0) + computeSFixed64SizeNoTag(r1);
    }

    public static int computeSFixed64SizeNoTag(long r0) {
        return 8;
    }

    public static int computeSInt32Size(int r0, int r1) {
        return computeTagSize(r0) + computeSInt32SizeNoTag(r1);
    }

    public static int computeSInt32SizeNoTag(int r0) {
        return computeUInt32SizeNoTag(encodeZigZag32(r0));
    }

    public static int computeSInt64Size(int r0, long r1) {
        return computeTagSize(r0) + computeSInt64SizeNoTag(r1);
    }

    public static int computeSInt64SizeNoTag(long r0) {
        return computeUInt64SizeNoTag(encodeZigZag64(r0));
    }

    public static int computeStringSize(int r0, String r1) {
        return computeTagSize(r0) + computeStringSizeNoTag(r1);
    }

    public static int computeStringSizeNoTag(String r1) {
        int r12 = Utf8.encodedLength(r1);     // Catch: Utf8.UnpairedSurrogateException -> L4
    L6:
        return computeLengthDelimitedFieldSize(r12);
    L4:
        r12 = r1.getBytes(Internal.UTF_8).length;
        goto L6
    }

    public static int computeTagSize(int r1) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(r1, 0));
    }

    public static int computeUInt32Size(int r0, int r1) {
        return computeTagSize(r0) + computeUInt32SizeNoTag(r1);
    }

    public static int computeUInt32SizeNoTag(int r0) {
        return (352 - (Integer.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int computeUInt64Size(int r0, long r1) {
        return computeTagSize(r0) + computeUInt64SizeNoTag(r1);
    }

    public static int computeUInt64SizeNoTag(long r0) {
        return (640 - (Long.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int encodeZigZag32(int r1) {
        int r0 = r1 << 1;
        return (r1 >> 31) ^ r0;
    }

    public static long encodeZigZag64(long r3) {
        long r0 = r3 << 1;
        return (r3 >> 63) ^ r0;
    }

    public static CodedOutputStream newInstance(OutputStream r1, int r2) {
        return new OutputStreamEncoder(r1, r2);
    }

    public abstract void flush();

    public final void inefficientWriteStringNoTag(String r4, Utf8.UnpairedSurrogateException r5) {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", r5);
        byte[] r42 = r4.getBytes(Internal.UTF_8);
        writeUInt32NoTag(r42.length);     // Catch: IndexOutOfBoundsException -> L5
        writeLazy(r42, 0, r42.length);     // Catch: IndexOutOfBoundsException -> L5
        return;
    L5:
        e = move-exception;
        throw new OutOfSpaceException(e);
    }

    public boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    public abstract void write(byte r1);

    public abstract void writeBool(int r1, boolean r2);

    public final void writeBoolNoTag(boolean r1) {
        write(r1 ? 1 : 0);
    }

    public final void writeByteArrayNoTag(byte[] r3) {
        writeByteArrayNoTag(r3, 0, r3.length);
    }

    public abstract void writeByteArrayNoTag(byte[] r1, int r2, int r3);

    public abstract void writeBytes(int r1, ByteString r2);

    public abstract void writeBytesNoTag(ByteString r1);

    public final void writeDouble(int r1, double r2) {
        writeFixed64(r1, Double.doubleToRawLongBits(r2));
    }

    public final void writeDoubleNoTag(double r1) {
        writeFixed64NoTag(Double.doubleToRawLongBits(r1));
    }

    public final void writeEnum(int r1, int r2) {
        writeInt32(r1, r2);
    }

    public final void writeEnumNoTag(int r1) {
        writeInt32NoTag(r1);
    }

    public abstract void writeFixed32(int r1, int r2);

    public abstract void writeFixed32NoTag(int r1);

    public abstract void writeFixed64(int r1, long r2);

    public abstract void writeFixed64NoTag(long r1);

    public final void writeFloat(int r1, float r2) {
        writeFixed32(r1, Float.floatToRawIntBits(r2));
    }

    public final void writeFloatNoTag(float r1) {
        writeFixed32NoTag(Float.floatToRawIntBits(r1));
    }

    @Deprecated
    public final void writeGroup(int r2, MessageLite r3) {
        writeTag(r2, 3);
        writeGroupNoTag(r3);
        writeTag(r2, 4);
    }

    @Deprecated
    public final void writeGroup(int r2, MessageLite r3, Schema r4) {
        writeTag(r2, 3);
        writeGroupNoTag(r3, r4);
        writeTag(r2, 4);
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite r1) {
        r1.writeTo(this);
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite r1, Schema r2) {
        r2.writeTo(r1, this.wrapper);
    }

    public abstract void writeInt32(int r1, int r2);

    public abstract void writeInt32NoTag(int r1);

    public final void writeInt64(int r1, long r2) {
        writeUInt64(r1, r2);
    }

    public final void writeInt64NoTag(long r1) {
        writeUInt64NoTag(r1);
    }

    @Override // androidx.datastore.preferences.protobuf.ByteOutput
    public abstract void writeLazy(byte[] r1, int r2, int r3);

    public abstract void writeMessage(int r1, MessageLite r2, Schema r3);

    public abstract void writeMessageNoTag(MessageLite r1);

    public abstract void writeMessageSetExtension(int r1, MessageLite r2);

    public abstract void writeRawMessageSetExtension(int r1, ByteString r2);

    public final void writeSFixed32(int r1, int r2) {
        writeFixed32(r1, r2);
    }

    public final void writeSFixed32NoTag(int r1) {
        writeFixed32NoTag(r1);
    }

    public final void writeSFixed64(int r1, long r2) {
        writeFixed64(r1, r2);
    }

    public final void writeSFixed64NoTag(long r1) {
        writeFixed64NoTag(r1);
    }

    public final void writeSInt32(int r1, int r2) {
        writeUInt32(r1, encodeZigZag32(r2));
    }

    public final void writeSInt32NoTag(int r1) {
        writeUInt32NoTag(encodeZigZag32(r1));
    }

    public final void writeSInt64(int r1, long r2) {
        writeUInt64(r1, encodeZigZag64(r2));
    }

    public final void writeSInt64NoTag(long r1) {
        writeUInt64NoTag(encodeZigZag64(r1));
    }

    public abstract void writeString(int r1, String r2);

    public abstract void writeStringNoTag(String r1);

    public abstract void writeTag(int r1, int r2);

    public abstract void writeUInt32(int r1, int r2);

    public abstract void writeUInt32NoTag(int r1);

    public abstract void writeUInt64(int r1, long r2);

    public abstract void writeUInt64NoTag(long r1);
}
