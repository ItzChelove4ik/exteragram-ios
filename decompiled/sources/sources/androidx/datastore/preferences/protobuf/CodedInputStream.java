package androidx.datastore.preferences.protobuf;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class CodedInputStream {
    private static volatile int defaultRecursionLimit = 100;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    /* renamed from: androidx.datastore.preferences.protobuf.CodedInputStream$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] r2, int r3, int r4, boolean r5) {
            super(null);
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = r2;
            this.limit = r4 + r3;
            this.pos = r3;
            this.startPos = r3;
            this.immutable = r5;
        }

        public /* synthetic */ ArrayDecoder(byte[] r1, int r2, int r3, boolean r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        private void recomputeBufferSizeAfterLimit() {
            int r0 = this.limit + this.bufferSizeAfterLimit;
            this.limit = r0;
            int r1 = r0 - this.startPos;
            int r2 = this.currentLimit;
            if (r1 <= r2) goto L6;
            int r12 = r1 - r2;
            this.bufferSizeAfterLimit = r12;
            this.limit = r0 - r12;
            return;
        L6:
            this.bufferSizeAfterLimit = 0;
        }

        private void skipRawVarint() {
            if ((this.limit - this.pos) < 10) goto L6;
            skipRawVarintFastPath();
            return;
        L6:
            skipRawVarintSlowPath();
        }

        private void skipRawVarintFastPath() {
            int r0 = 0;
        L4:
            if (r0 >= 10) goto L10;
            byte[] r1 = this.buffer;
            int r2 = this.pos;
            this.pos = r2 + 1;
            if (r1[r2] >= 0) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() {
            int r0 = 0;
        L4:
            if (r0 >= 10) goto L10;
            if (readRawByte() >= 0) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public void checkLastTagWas(int r1) {
            if (this.lastTag != r1) goto L6;
            return;
        L6:
            throw InvalidProtocolBufferException.invalidEndTag();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean isAtEnd() {
            if (this.pos != this.limit) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public void popLimit(int r1) {
            this.currentLimit = r1;
            recomputeBufferSizeAfterLimit();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int pushLimit(int r2) {
            if (r2 < 0) goto L14;
            int r22 = r2 + getTotalBytesRead();
            if (r22 < 0) goto L12;
            int r0 = this.currentLimit;
            if (r22 > r0) goto L10;
            this.currentLimit = r22;
            recomputeBufferSizeAfterLimit();
            return r0;
        L10:
            throw InvalidProtocolBufferException.truncatedMessage();
        L12:
            throw InvalidProtocolBufferException.parseFailure();
        L14:
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean readBool() {
            if (readRawVarint64() == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public ByteString readBytes() {
            int r0 = readRawVarint32();
            if (r0 <= 0) goto L14;
            int r1 = this.limit;
            int r2 = this.pos;
            if (r0 > (r1 - r2)) goto L14;
            if (this.immutable == true) goto L9;
        L11:
            ByteString r12 = ByteString.copyFrom(this.buffer, r2, r0);
        L12:
            this.pos += r0;
            return r12;
        L9:
            if (this.enableAliasing == false) goto L11;
            r12 = ByteString.wrap(this.buffer, r2, r0);
        L14:
            if (r0 != 0) goto L18;
            return ByteString.EMPTY;
        L18:
            return ByteString.wrap(readRawBytes(r0));
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        public byte readRawByte() {
            int r0 = this.pos;
            if (r0 == this.limit) goto L7;
            byte[] r1 = this.buffer;
            this.pos = r0 + 1;
            return r1[r0];
        L7:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int r3) {
            if (r3 <= 0) goto L7;
            int r0 = this.limit;
            int r1 = this.pos;
            if (r3 > (r0 - r1)) goto L7;
            int r32 = r3 + r1;
            this.pos = r32;
            return Arrays.copyOfRange(this.buffer, r1, r32);
        L7:
            if (r3 > 0) goto L14;
            if (r3 != 0) goto L12;
            return Internal.EMPTY_BYTE_ARRAY;
        L12:
            throw InvalidProtocolBufferException.negativeSize();
        L14:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readRawLittleEndian32() {
            int r0 = this.pos;
            if ((this.limit - r0) < 4) goto L7;
            byte[] r1 = this.buffer;
            this.pos = r0 + 4;
            return (((r1[r0] & UByte.MAX_VALUE) | ((r1[r0 + 1] & UByte.MAX_VALUE) << 8)) | ((r1[r0 + 2] & UByte.MAX_VALUE) << 16)) | ((r1[r0 + 3] & UByte.MAX_VALUE) << 24);
        L7:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() {
            int r0 = this.pos;
            if ((this.limit - r0) < 8) goto L7;
            byte[] r1 = this.buffer;
            this.pos = r0 + 8;
            return ((r1[r0 + 7] & 255) << 56) | (((((((r1[r0] & 255) | ((r1[r0 + 1] & 255) << 8)) | ((r1[r0 + 2] & 255) << 16)) | ((r1[r0 + 3] & 255) << 24)) | ((r1[r0 + 4] & 255) << 32)) | ((r1[r0 + 5] & 255) << 40)) | ((r1[r0 + 6] & 255) << 48));
        L7:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readRawVarint32() {
            int r0 = this.pos;
            int r1 = this.limit;
            if (r1 == r0) goto L36;
            byte[] r2 = this.buffer;
            int r3 = r0 + 1;
            byte r4 = r2[r0];
            if (r4 < 0) goto L10;
            this.pos = r3;
            return r4;
        L10:
            if ((r1 - r3) < 9) goto L36;
            int r12 = r0 + 2;
            int r32 = (r2[r3] << 7) ^ r4;
            if (r32 >= 0) goto L15;
            int r02 = r32 ^ (-128);
        L40:
            this.pos = r12;
            return r02;
        L15:
            int r42 = r0 + 3;
            int r13 = (r2[r12] << 14) ^ r32;
            if (r13 < 0) goto L19;
            r02 = r13 ^ 16256;
        L18:
            r12 = r42;
            goto L40
        L19:
            int r33 = r0 + 4;
            int r14 = r13 ^ (r2[r42] << 21);
            if (r14 >= 0) goto L23;
            r02 = (-2080896) ^ r14;
        L22:
            r12 = r33;
            goto L40
        L23:
            r42 = r0 + 5;
            byte r34 = r2[r33];
            int r15 = (r14 ^ (r34 << 28)) ^ 266354560;
            if (r34 >= 0) goto L38;
            r33 = r0 + 6;
            if (r2[r42] >= 0) goto L39;
            r42 = r0 + 7;
            if (r2[r33] >= 0) goto L38;
            r33 = r0 + 8;
            if (r2[r42] >= 0) goto L39;
            r42 = r0 + 9;
            if (r2[r33] >= 0) goto L38;
            int r03 = r0 + 10;
            if (r2[r42] < 0) goto L36;
            r12 = r03;
            r02 = r15;
        L39:
            r02 = r15;
        L38:
            r02 = r15;
        L36:
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() {
            int r0 = this.pos;
            int r1 = this.limit;
            if (r1 == r0) goto L40;
            byte[] r2 = this.buffer;
            int r3 = r0 + 1;
            byte r4 = r2[r0];
            if (r4 < 0) goto L10;
            this.pos = r3;
            return r4;
        L10:
            if ((r1 - r3) < 9) goto L40;
            int r12 = r0 + 2;
            int r32 = (r2[r3] << 7) ^ r4;
            if (r32 >= 0) goto L15;
            long r22 = r32 ^ (-128);
        L43:
            this.pos = r12;
            return r22;
        L15:
            int r42 = r0 + 3;
            int r13 = (r2[r12] << 14) ^ r32;
            if (r13 < 0) goto L18;
            r22 = r13 ^ 16256;
            r12 = r42;
            goto L43
        L18:
            int r33 = r0 + 4;
            int r14 = r13 ^ (r2[r42] << 21);
            if (r14 >= 0) goto L21;
            long r02 = (-2080896) ^ r14;
            r12 = r33;
            r22 = r02;
            goto L43
        L21:
            long r43 = r14;
            r12 = r0 + 5;
            long r34 = r43 ^ (r2[r33] << 28);
            if (r34 < 0) goto L25;
            long r5 = 266354560;
        L24:
            r22 = r34 ^ r5;
            goto L43
        L25:
            int r7 = r0 + 6;
            long r35 = r34 ^ (r2[r12] << 35);
            if (r35 >= 0) goto L29;
            long r03 = -34093383808L;
        L28:
            r22 = r35 ^ r03;
            r12 = r7;
            goto L43
        L29:
            r12 = r0 + 7;
            r34 = r35 ^ (r2[r7] << 42);
            if (r34 < 0) goto L32;
            r5 = 4363953127296L;
            goto L24
        L32:
            r7 = r0 + 8;
            r35 = r34 ^ (r2[r12] << 49);
            if (r35 >= 0) goto L35;
            r03 = -558586000294016L;
            goto L28
        L35:
            r12 = r0 + 9;
            long r36 = (r35 ^ (r2[r7] << 56)) ^ 71499008037633920L;
            if (r36 >= 0) goto L42;
            int r04 = r0 + 10;
            if (r2[r12] < 0) goto L40;
            r12 = r04;
        L42:
            r22 = r36;
        L40:
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() {
            long r0 = 0;
            int r2 = 0;
        L4:
            if (r2 >= 64) goto L10;
            r0 = r0 | ((r3 & ByteCompanionObject.MAX_VALUE) << r2);
            if ((readRawByte() & ByteCompanionObject.MIN_VALUE) == 0) goto L7;
            r2 = r2 + 7;
            goto L4
        L7:
            return r0;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public String readString() {
            int r0 = readRawVarint32();
            if (r0 <= 0) goto L8;
            int r1 = this.limit;
            int r2 = this.pos;
            if (r0 > (r1 - r2)) goto L8;
            String r12 = new String(this.buffer, r2, r0, Internal.UTF_8);
            this.pos += r0;
            return r12;
        L8:
            if (r0 != 0) goto L11;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            if (r0 >= 0) goto L15;
            throw InvalidProtocolBufferException.negativeSize();
        L15:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public String readStringRequireUtf8() {
            int r0 = readRawVarint32();
            if (r0 <= 0) goto L8;
            int r1 = this.limit;
            int r2 = this.pos;
            if (r0 > (r1 - r2)) goto L8;
            String r12 = Utf8.decodeUtf8(this.buffer, r2, r0);
            this.pos += r0;
            return r12;
        L8:
            if (r0 != 0) goto L11;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            if (r0 > 0) goto L15;
            throw InvalidProtocolBufferException.negativeSize();
        L15:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readTag() {
            if (isAtEnd() == false) goto L6;
            this.lastTag = 0;
            return 0;
        L6:
            int r0 = readRawVarint32();
            this.lastTag = r0;
            if (WireFormat.getTagFieldNumber(r0) == 0) goto L11;
            return this.lastTag;
        L11:
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean skipField(int r5) {
            int r0 = WireFormat.getTagWireType(r5);
            if (r0 == 0) goto L24;
            if (r0 != 1) goto L6;
            skipRawBytes(8);
            return true;
        L6:
            if (r0 != 2) goto L8;
            skipRawBytes(readRawVarint32());
            return true;
        L8:
            if (r0 == 3) goto L18;
            if (r0 != 4) goto L11;
            return false;
        L11:
            if (r0 != 5) goto L15;
            skipRawBytes(4);
            return true;
        L15:
            throw InvalidProtocolBufferException.invalidWireType();
        L18:
            skipMessage();
            checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(r5), 4));
            return true;
        L24:
            skipRawVarint();
            return true;
        }

        public void skipRawBytes(int r3) {
            if (r3 < 0) goto L7;
            int r0 = this.limit;
            int r1 = this.pos;
            if (r3 > (r0 - r1)) goto L7;
            this.pos = r1 + r3;
            return;
        L7:
            if (r3 >= 0) goto L11;
            throw InvalidProtocolBufferException.negativeSize();
        L11:
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public static final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private int totalBytesRetired;

        private StreamDecoder(InputStream r2, int r3) {
            super(null);
            this.currentLimit = Integer.MAX_VALUE;
            Internal.checkNotNull(r2, PluginsConstants.Settings.TYPE_INPUT);
            this.input = r2;
            this.buffer = new byte[r3];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        public /* synthetic */ StreamDecoder(InputStream r1, int r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        private static int available(InputStream r0) {
            return r0.available();
        L4:
            e = move-exception;
            e.setThrownFromInputStream();
            throw e;
        }

        private static int read(InputStream r0, byte[] r1, int r2, int r3) {
            return r0.read(r1, r2, r3);
        L4:
            e = move-exception;
            e.setThrownFromInputStream();
            throw e;
        }

        private ByteString readBytesSlowPath(int r5) {
            byte[] r0 = readRawBytesSlowPathOneChunk(r5);
            if (r0 != null) goto L5;
            int r02 = this.pos;
            int r1 = this.bufferSize;
            int r2 = r1 - r02;
            this.totalBytesRetired += r1;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> r3 = readRawBytesSlowPathRemainingChunks(r5 - r2);
            byte[] r52 = new byte[r5];
            System.arraycopy(this.buffer, r02, r52, 0, r2);
            Iterator<byte[]> r4 = r3.iterator();
        L8:
            if (r4.hasNext() == false) goto L11;
            byte[] r03 = r4.next();
            System.arraycopy(r03, 0, r52, r2, r03.length);
            r2 = r2 + r03.length;
            goto L8
        L11:
            return ByteString.wrap(r52);
        L5:
            return ByteString.copyFrom(r0);
        }

        private byte[] readRawBytesSlowPath(int r4, boolean r5) {
            byte[] r0 = readRawBytesSlowPathOneChunk(r4);
            if (r0 == null) goto L8;
            if (r5 == true) goto L6;
            return r0;
        L6:
            return (byte[]) r0.clone();
        L8:
            int r52 = this.pos;
            int r02 = this.bufferSize;
            int r1 = r02 - r52;
            this.totalBytesRetired += r02;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> r2 = readRawBytesSlowPathRemainingChunks(r4 - r1);
            byte[] r42 = new byte[r4];
            System.arraycopy(this.buffer, r52, r42, 0, r1);
            Iterator<byte[]> r3 = r2.iterator();
        L10:
            if (r3.hasNext() == false) goto L12;
            byte[] r53 = r3.next();
            System.arraycopy(r53, 0, r42, r1, r53.length);
            r1 = r1 + r53.length;
            goto L10
        L12:
            return r42;
        }

        private byte[] readRawBytesSlowPathOneChunk(int r6) {
            if (r6 == 0) goto L4;
            if (r6 < 0) goto L30;
            int r0 = this.totalBytesRetired;
            int r1 = this.pos;
            int r2 = (r0 + r1) + r6;
            if ((r2 - this.sizeLimit) > 0) goto L28;
            int r3 = this.currentLimit;
            if (r2 > r3) goto L25;
            int r02 = this.bufferSize - r1;
            int r12 = r6 - r02;
            if (r12 >= 4096) goto L13;
        L17:
            byte[] r13 = new byte[r6];
            System.arraycopy(this.buffer, this.pos, r13, 0, r02);
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
        L18:
            if (r02 >= r6) goto L24;
            int r22 = read(this.input, r13, r02, r6 - r02);
            if (r22 == (-1)) goto L23;
            this.totalBytesRetired += r22;
            r02 = r02 + r22;
            goto L18
        L23:
            throw InvalidProtocolBufferException.truncatedMessage();
        L24:
            return r13;
        L13:
            if (r12 <= available(this.input)) goto L17;
            return null;
        L25:
            skipRawBytes((r3 - r0) - r1);
            throw InvalidProtocolBufferException.truncatedMessage();
        L28:
            throw InvalidProtocolBufferException.sizeLimitExceeded();
        L30:
            throw InvalidProtocolBufferException.negativeSize();
        L4:
            return Internal.EMPTY_BYTE_ARRAY;
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int r7) {
            ArrayList r0 = new ArrayList();
        L3:
            if (r7 <= 0) goto L12;
            int r1 = Math.min(r7, 4096);
            byte[] r2 = new byte[r1];
            int r3 = 0;
        L5:
            if (r3 >= r1) goto L11;
            int r4 = this.input.read(r2, r3, r1 - r3);
            if (r4 == (-1)) goto L10;
            this.totalBytesRetired += r4;
            r3 = r3 + r4;
            goto L5
        L10:
            throw InvalidProtocolBufferException.truncatedMessage();
        L11:
            r7 = r7 - r1;
            r0.add(r2);
            goto L3
        L12:
            return r0;
        }

        private void recomputeBufferSizeAfterLimit() {
            int r0 = this.bufferSize + this.bufferSizeAfterLimit;
            this.bufferSize = r0;
            int r1 = this.totalBytesRetired + r0;
            int r2 = this.currentLimit;
            if (r1 <= r2) goto L6;
            int r12 = r1 - r2;
            this.bufferSizeAfterLimit = r12;
            this.bufferSize = r0 - r12;
            return;
        L6:
            this.bufferSizeAfterLimit = 0;
        }

        private void refillBuffer(int r3) {
            if (tryRefillBuffer(r3) == false) goto L5;
            return;
        L5:
            if (r3 <= ((this.sizeLimit - this.totalBytesRetired) - this.pos)) goto L9;
            throw InvalidProtocolBufferException.sizeLimitExceeded();
        L9:
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private static long skip(InputStream r0, long r1) {
            return r0.skip(r1);
        L4:
            e = move-exception;
            e.setThrownFromInputStream();
            throw e;
        }

        private void skipRawBytesSlowPath(int r8) {
            if (r8 < 0) goto L31;
            int r0 = this.totalBytesRetired;
            int r1 = this.pos;
            int r2 = (r0 + r1) + r8;
            int r3 = this.currentLimit;
            if (r2 > r3) goto L28;
            this.totalBytesRetired = r0 + r1;
            int r02 = this.bufferSize - r1;
            this.bufferSize = 0;
            this.pos = 0;
        L6:
            if (r02 >= r8) goto L20;
            long r32 = r8 - r02;
            long r12 = skip(this.input, r32);     // Catch: Throwable -> L17
            if (r12 < 0) goto L16;
            if (r12 > r32) goto L16;
            if (r12 == 0) goto L20;
            r02 = r02 + ((int) r12);     // Catch: Throwable -> L17
        L16:
            throw new IllegalStateException(this.input.getClass() + "#skip returned invalid result: " + r12 + "\nThe InputStream implementation is buggy.");     // Catch: Throwable -> L17
        L17:
            th = move-exception;
            this.totalBytesRetired += r02;
            recomputeBufferSizeAfterLimit();
            throw th;
        L20:
            this.totalBytesRetired += r02;
            recomputeBufferSizeAfterLimit();
            if (r02 >= r8) goto L39;
            int r03 = this.bufferSize;
            int r13 = r03 - this.pos;
            this.pos = r03;
            refillBuffer(1);
        L23:
            int r22 = r8 - r13;
            int r33 = this.bufferSize;
            if (r22 <= r33) goto L26;
            r13 = r13 + r33;
            this.pos = r33;
            refillBuffer(1);
            goto L23
        L26:
            this.pos = r22;
            return;
        L39:
            return;
        L28:
            skipRawBytes((r3 - r0) - r1);
            throw InvalidProtocolBufferException.truncatedMessage();
        L31:
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void skipRawVarint() {
            if ((this.bufferSize - this.pos) < 10) goto L6;
            skipRawVarintFastPath();
            return;
        L6:
            skipRawVarintSlowPath();
        }

        private void skipRawVarintFastPath() {
            int r0 = 0;
        L4:
            if (r0 >= 10) goto L10;
            byte[] r1 = this.buffer;
            int r2 = this.pos;
            this.pos = r2 + 1;
            if (r1[r2] >= 0) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() {
            int r0 = 0;
        L4:
            if (r0 >= 10) goto L10;
            if (readRawByte() >= 0) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private boolean tryRefillBuffer(int r8) {
            int r0 = this.pos;
            int r1 = r0 + r8;
            int r2 = this.bufferSize;
            if (r1 <= r2) goto L30;
            int r12 = this.sizeLimit;
            int r4 = this.totalBytesRetired;
            if (r8 <= ((r12 - r4) - r0)) goto L8;
            return false;
        L8:
            if (((r4 + r0) + r8) <= this.currentLimit) goto L10;
            return false;
        L10:
            if (r0 <= 0) goto L14;
            if (r2 <= r0) goto L13;
            byte[] r13 = this.buffer;
            System.arraycopy(r13, r0, r13, 0, r2 - r0);
        L13:
            this.totalBytesRetired += r0;
            this.bufferSize -= r0;
            this.pos = 0;
        L14:
            InputStream r02 = this.input;
            byte[] r14 = this.buffer;
            int r22 = this.bufferSize;
            int r03 = read(r02, r14, r22, Math.min(r14.length - r22, (this.sizeLimit - this.totalBytesRetired) - r22));
            if (r03 == 0) goto L29;
            if (r03 < (-1)) goto L29;
            if (r03 > this.buffer.length) goto L29;
            if (r03 <= 0) goto L27;
            this.bufferSize += r03;
            recomputeBufferSizeAfterLimit();
            if (this.bufferSize < r8) goto L26;
            return true;
        L26:
            return tryRefillBuffer(r8);
        L27:
            return false;
        L29:
            throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + r03 + "\nThe InputStream implementation is buggy.");
        L30:
            ExifInterface$$ExternalSyntheticBUOutline0.m("refillBuffer() called when ", r8, " bytes were already available in buffer");
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public void checkLastTagWas(int r1) {
            if (this.lastTag != r1) goto L6;
            return;
        L6:
            throw InvalidProtocolBufferException.invalidEndTag();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean isAtEnd() {
            if (this.pos == this.bufferSize) goto L5;
            return false;
        L5:
            if (tryRefillBuffer(1) == true) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public void popLimit(int r1) {
            this.currentLimit = r1;
            recomputeBufferSizeAfterLimit();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int pushLimit(int r3) {
            if (r3 < 0) goto L14;
            int r32 = r3 + (this.totalBytesRetired + this.pos);
            if (r32 < 0) goto L12;
            int r0 = this.currentLimit;
            if (r32 > r0) goto L10;
            this.currentLimit = r32;
            recomputeBufferSizeAfterLimit();
            return r0;
        L10:
            throw InvalidProtocolBufferException.truncatedMessage();
        L12:
            throw InvalidProtocolBufferException.parseFailure();
        L14:
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean readBool() {
            if (readRawVarint64() == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public ByteString readBytes() {
            int r0 = readRawVarint32();
            int r1 = this.bufferSize;
            int r2 = this.pos;
            if (r0 > (r1 - r2)) goto L7;
            if (r0 <= 0) goto L7;
            ByteString r12 = ByteString.copyFrom(this.buffer, r2, r0);
            this.pos += r0;
            return r12;
        L7:
            if (r0 == 0) goto L9;
            if (r0 < 0) goto L14;
            return readBytesSlowPath(r0);
        L14:
            throw InvalidProtocolBufferException.negativeSize();
        L9:
            return ByteString.EMPTY;
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        public byte readRawByte() {
            if (this.pos != this.bufferSize) goto L5;
            refillBuffer(1);
        L5:
            byte[] r0 = this.buffer;
            int r1 = this.pos;
            this.pos = r1 + 1;
            return r0[r1];
        }

        public int readRawLittleEndian32() {
            int r0 = this.pos;
            if ((this.bufferSize - r0) >= 4) goto L5;
            refillBuffer(4);
            r0 = this.pos;
        L5:
            byte[] r1 = this.buffer;
            this.pos = r0 + 4;
            return (((r1[r0] & UByte.MAX_VALUE) | ((r1[r0 + 1] & UByte.MAX_VALUE) << 8)) | ((r1[r0 + 2] & UByte.MAX_VALUE) << 16)) | ((r1[r0 + 3] & UByte.MAX_VALUE) << 24);
        }

        public long readRawLittleEndian64() {
            int r0 = this.pos;
            if ((this.bufferSize - r0) >= 8) goto L5;
            refillBuffer(8);
            r0 = this.pos;
        L5:
            byte[] r1 = this.buffer;
            this.pos = r0 + 8;
            return ((r1[r0 + 7] & 255) << 56) | (((((((r1[r0] & 255) | ((r1[r0 + 1] & 255) << 8)) | ((r1[r0 + 2] & 255) << 16)) | ((r1[r0 + 3] & 255) << 24)) | ((r1[r0 + 4] & 255) << 32)) | ((r1[r0 + 5] & 255) << 40)) | ((r1[r0 + 6] & 255) << 48));
        }

        public int readRawVarint32() {
            int r0 = this.pos;
            int r1 = this.bufferSize;
            if (r1 == r0) goto L36;
            byte[] r2 = this.buffer;
            int r3 = r0 + 1;
            byte r4 = r2[r0];
            if (r4 < 0) goto L10;
            this.pos = r3;
            return r4;
        L10:
            if ((r1 - r3) < 9) goto L36;
            int r12 = r0 + 2;
            int r32 = (r2[r3] << 7) ^ r4;
            if (r32 >= 0) goto L15;
            int r02 = r32 ^ (-128);
        L40:
            this.pos = r12;
            return r02;
        L15:
            int r42 = r0 + 3;
            int r13 = (r2[r12] << 14) ^ r32;
            if (r13 < 0) goto L19;
            r02 = r13 ^ 16256;
        L18:
            r12 = r42;
            goto L40
        L19:
            int r33 = r0 + 4;
            int r14 = r13 ^ (r2[r42] << 21);
            if (r14 >= 0) goto L23;
            r02 = (-2080896) ^ r14;
        L22:
            r12 = r33;
            goto L40
        L23:
            r42 = r0 + 5;
            byte r34 = r2[r33];
            int r15 = (r14 ^ (r34 << 28)) ^ 266354560;
            if (r34 >= 0) goto L38;
            r33 = r0 + 6;
            if (r2[r42] >= 0) goto L39;
            r42 = r0 + 7;
            if (r2[r33] >= 0) goto L38;
            r33 = r0 + 8;
            if (r2[r42] >= 0) goto L39;
            r42 = r0 + 9;
            if (r2[r33] >= 0) goto L38;
            int r03 = r0 + 10;
            if (r2[r42] < 0) goto L36;
            r12 = r03;
            r02 = r15;
        L39:
            r02 = r15;
        L38:
            r02 = r15;
        L36:
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() {
            int r0 = this.pos;
            int r1 = this.bufferSize;
            if (r1 == r0) goto L40;
            byte[] r2 = this.buffer;
            int r3 = r0 + 1;
            byte r4 = r2[r0];
            if (r4 < 0) goto L10;
            this.pos = r3;
            return r4;
        L10:
            if ((r1 - r3) < 9) goto L40;
            int r12 = r0 + 2;
            int r32 = (r2[r3] << 7) ^ r4;
            if (r32 >= 0) goto L15;
            long r22 = r32 ^ (-128);
        L43:
            this.pos = r12;
            return r22;
        L15:
            int r42 = r0 + 3;
            int r13 = (r2[r12] << 14) ^ r32;
            if (r13 < 0) goto L18;
            r22 = r13 ^ 16256;
            r12 = r42;
            goto L43
        L18:
            int r33 = r0 + 4;
            int r14 = r13 ^ (r2[r42] << 21);
            if (r14 >= 0) goto L21;
            long r02 = (-2080896) ^ r14;
            r12 = r33;
            r22 = r02;
            goto L43
        L21:
            long r43 = r14;
            r12 = r0 + 5;
            long r34 = r43 ^ (r2[r33] << 28);
            if (r34 < 0) goto L25;
            long r5 = 266354560;
        L24:
            r22 = r34 ^ r5;
            goto L43
        L25:
            int r7 = r0 + 6;
            long r35 = r34 ^ (r2[r12] << 35);
            if (r35 >= 0) goto L29;
            long r03 = -34093383808L;
        L28:
            r22 = r35 ^ r03;
            r12 = r7;
            goto L43
        L29:
            r12 = r0 + 7;
            r34 = r35 ^ (r2[r7] << 42);
            if (r34 < 0) goto L32;
            r5 = 4363953127296L;
            goto L24
        L32:
            r7 = r0 + 8;
            r35 = r34 ^ (r2[r12] << 49);
            if (r35 >= 0) goto L35;
            r03 = -558586000294016L;
            goto L28
        L35:
            r12 = r0 + 9;
            long r36 = (r35 ^ (r2[r7] << 56)) ^ 71499008037633920L;
            if (r36 >= 0) goto L42;
            int r04 = r0 + 10;
            if (r2[r12] < 0) goto L40;
            r12 = r04;
        L42:
            r22 = r36;
        L40:
            return readRawVarint64SlowPath();
        }

        public long readRawVarint64SlowPath() {
            long r0 = 0;
            int r2 = 0;
        L4:
            if (r2 >= 64) goto L10;
            r0 = r0 | ((r3 & ByteCompanionObject.MAX_VALUE) << r2);
            if ((readRawByte() & ByteCompanionObject.MIN_VALUE) == 0) goto L7;
            r2 = r2 + 7;
            goto L4
        L7:
            return r0;
        L10:
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public String readString() {
            int r0 = readRawVarint32();
            if (r0 <= 0) goto L8;
            int r1 = this.bufferSize;
            int r2 = this.pos;
            if (r0 > (r1 - r2)) goto L8;
            String r12 = new String(this.buffer, r2, r0, Internal.UTF_8);
            this.pos += r0;
            return r12;
        L8:
            if (r0 != 0) goto L11;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            if (r0 < 0) goto L19;
            if (r0 > this.bufferSize) goto L17;
            refillBuffer(r0);
            String r13 = new String(this.buffer, this.pos, r0, Internal.UTF_8);
            this.pos += r0;
            return r13;
        L17:
            return new String(readRawBytesSlowPath(r0, false), Internal.UTF_8);
        L19:
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public String readStringRequireUtf8() {
            int r0 = readRawVarint32();
            int r1 = this.pos;
            int r2 = this.bufferSize;
            if (r0 > (r2 - r1)) goto L6;
            if (r0 <= 0) goto L6;
            byte[] r22 = this.buffer;
            this.pos = r1 + r0;
        L15:
            return Utf8.decodeUtf8(r22, r1, r0);
        L6:
            if (r0 != 0) goto L9;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            if (r0 < 0) goto L17;
            r1 = 0;
            if (r0 > r2) goto L13;
            refillBuffer(r0);
            r22 = this.buffer;
            this.pos = r0;
            goto L15
        L13:
            r22 = readRawBytesSlowPath(r0, false);
            goto L15
        L17:
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readTag() {
            if (isAtEnd() == false) goto L6;
            this.lastTag = 0;
            return 0;
        L6:
            int r0 = readRawVarint32();
            this.lastTag = r0;
            if (WireFormat.getTagFieldNumber(r0) == 0) goto L11;
            return this.lastTag;
        L11:
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // androidx.datastore.preferences.protobuf.CodedInputStream
        public boolean skipField(int r5) {
            int r0 = WireFormat.getTagWireType(r5);
            if (r0 == 0) goto L24;
            if (r0 != 1) goto L6;
            skipRawBytes(8);
            return true;
        L6:
            if (r0 != 2) goto L8;
            skipRawBytes(readRawVarint32());
            return true;
        L8:
            if (r0 == 3) goto L18;
            if (r0 != 4) goto L11;
            return false;
        L11:
            if (r0 != 5) goto L15;
            skipRawBytes(4);
            return true;
        L15:
            throw InvalidProtocolBufferException.invalidWireType();
        L18:
            skipMessage();
            checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(r5), 4));
            return true;
        L24:
            skipRawVarint();
            return true;
        }

        public void skipRawBytes(int r3) {
            int r0 = this.bufferSize;
            int r1 = this.pos;
            if (r3 > (r0 - r1)) goto L7;
            if (r3 < 0) goto L7;
            this.pos = r1 + r3;
            return;
        L7:
            skipRawBytesSlowPath(r3);
        }
    }

    static {
    }

    private CodedInputStream() {
        this.recursionLimit = defaultRecursionLimit;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public /* synthetic */ CodedInputStream(AnonymousClass1 r1) {
        this();
    }

    public static int decodeZigZag32(int r1) {
        int r0 = r1 >>> 1;
        return (-(r1 & 1)) ^ r0;
    }

    public static long decodeZigZag64(long r4) {
        long r0 = r4 >>> 1;
        return (-(r4 & 1)) ^ r0;
    }

    public static CodedInputStream newInstance(InputStream r1) {
        return newInstance(r1, 4096);
    }

    public static CodedInputStream newInstance(InputStream r2, int r3) {
        if (r3 <= 0) goto L9;
        if (r2 != null) goto L8;
        return newInstance(Internal.EMPTY_BYTE_ARRAY);
    L8:
        return new StreamDecoder(r2, r3, null);
    L9:
        f$$ExternalSyntheticBUOutline1.m("bufferSize must be > 0");
        return null;
    }

    public static CodedInputStream newInstance(byte[] r2) {
        return newInstance(r2, 0, r2.length);
    }

    public static CodedInputStream newInstance(byte[] r1, int r2, int r3) {
        return newInstance(r1, r2, r3, false);
    }

    public static CodedInputStream newInstance(byte[] r6, int r7, int r8, boolean r9) {
        ArrayDecoder r0 = new ArrayDecoder(r6, r7, r8, r9, null);
        r0.pushLimit(r8);     // Catch: InvalidProtocolBufferException -> L5
        return r0;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public abstract void checkLastTagWas(int r1);

    public void checkRecursionLimit() {
        if (this.recursionDepth >= this.recursionLimit) goto L6;
        return;
    L6:
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd();

    public abstract void popLimit(int r1);

    public abstract int pushLimit(int r1);

    public abstract boolean readBool();

    public abstract ByteString readBytes();

    public abstract double readDouble();

    public abstract int readEnum();

    public abstract int readFixed32();

    public abstract long readFixed64();

    public abstract float readFloat();

    public abstract int readInt32();

    public abstract long readInt64();

    public abstract int readSFixed32();

    public abstract long readSFixed64();

    public abstract int readSInt32();

    public abstract long readSInt64();

    public abstract String readString();

    public abstract String readStringRequireUtf8();

    public abstract int readTag();

    public abstract int readUInt32();

    public abstract long readUInt64();

    public abstract boolean skipField(int r1);

    public void skipMessage() {
    L2:
        int r0 = readTag();
        if (r0 == 0) goto L7;
        checkRecursionLimit();
        this.recursionDepth++;
        this.recursionDepth--;
        if (skipField(r0) == true) goto L2;
        return;
    }
}
