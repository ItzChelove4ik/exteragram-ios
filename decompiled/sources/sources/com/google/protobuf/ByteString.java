package com.google.protobuf;

import com.android.dex.Dex$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.fido.zzgu$$ExternalSyntheticBUOutline0;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ByteString implements Iterable<Byte>, Serializable {
    public static final ByteString EMPTY = null;
    private static final Comparator<ByteString> UNSIGNED_LEXICOGRAPHICAL_COMPARATOR = null;
    private static final ByteArrayCopier byteArrayCopier = null;
    private int hash;

    /* renamed from: com.google.protobuf.ByteString$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractByteIterator {
        private final int limit;
        private int position;
        final /* synthetic */ ByteString this$0;

        public AnonymousClass1(ByteString r2) {
            this.this$0 = r2;
            this.position = 0;
            this.limit = r2.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.position >= this.limit) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            int r0 = this.position;
            if (r0 >= this.limit) goto L6;
            this.position = r0 + 1;
            return this.this$0.internalByteAt(r0);
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return 0;
        }
    }

    /* renamed from: com.google.protobuf.ByteString$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<ByteString> {
        public AnonymousClass2() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.protobuf.ByteString$ByteIterator, java.util.Iterator] */
        /* JADX WARN: Type inference failed for: r3v1, types: [com.google.protobuf.ByteString$ByteIterator, java.util.Iterator] */
        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ByteString r4, ByteString r5) {
            ?? r3 = r4.iterator();
            ?? r0 = r5.iterator();
        L4:
            if (r3.hasNext() == false) goto L11;
            if (r0.hasNext() == false) goto L11;
            int r1 = Integer.valueOf(ByteString.access$200(r3.nextByte())).compareTo(Integer.valueOf(ByteString.access$200(r0.nextByte())));
            if (r1 == 0) goto L4;
            return r1;
        L11:
            return Integer.valueOf(r4.size()).compareTo(Integer.valueOf(r5.size()));
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ByteString r1, ByteString r2) {
            return compare2(r1, r2);
        }
    }

    public static abstract class AbstractByteIterator implements ByteIterator {
        public AbstractByteIterator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public final Byte next() {
            return Byte.valueOf(nextByte());
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Byte next() {
            return next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static final class ArraysByteArrayCopier implements ByteArrayCopier {
        private ArraysByteArrayCopier() {
        }

        public /* synthetic */ ArraysByteArrayCopier(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] r1, int r2, int r3) {
            return Arrays.copyOfRange(r1, r2, r3 + r2);
        }
    }

    public static final class BoundedByteString extends LiteralByteString {
        private final int bytesLength;
        private final int bytesOffset;

        public BoundedByteString(byte[] r2, int r3, int r4) {
            super(r2);
            ByteString.checkRange(r3, r3 + r4, r2.length);
            this.bytesOffset = r3;
            this.bytesLength = r4;
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public byte byteAt(int r2) {
            ByteString.checkIndex(r2, size());
            return this.bytes[this.bytesOffset + r2];
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public void copyToInternal(byte[] r2, int r3, int r4, int r5) {
            System.arraycopy(this.bytes, getOffsetIntoBytes() + r3, r2, r4, r5);
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString
        public int getOffsetIntoBytes() {
            return this.bytesOffset;
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public byte internalByteAt(int r2) {
            return this.bytes[this.bytesOffset + r2];
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public int size() {
            return this.bytesLength;
        }
    }

    public interface ByteArrayCopier {
        byte[] copyFrom(byte[] r1, int r2, int r3);
    }

    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    public static final class CodedBuilder {
        private final byte[] buffer;
        private final CodedOutputStream output;

        private CodedBuilder(int r1) {
            byte[] r12 = new byte[r1];
            this.buffer = r12;
            this.output = CodedOutputStream.newInstance(r12);
        }

        public /* synthetic */ CodedBuilder(int r1, AnonymousClass1 r2) {
            this(r1);
        }

        public ByteString build() {
            this.output.checkNoSpaceLeft();
            return new LiteralByteString(this.buffer);
        }

        public CodedOutputStream getCodedOutput() {
            return this.output;
        }
    }

    public static abstract class LeafByteString extends ByteString {
        public LeafByteString() {
        }
    }

    public static class LiteralByteString extends LeafByteString {
        protected final byte[] bytes;

        public LiteralByteString(byte[] r1) {
            r1.getClass();
            this.bytes = r1;
        }

        @Override // com.google.protobuf.ByteString
        public final ByteBuffer asReadOnlyByteBuffer() {
            return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
        }

        @Override // com.google.protobuf.ByteString
        public byte byteAt(int r1) {
            return this.bytes[r1];
        }

        @Override // com.google.protobuf.ByteString
        public void copyToInternal(byte[] r1, int r2, int r3, int r4) {
            System.arraycopy(this.bytes, r2, r1, r3, r4);
        }

        @Override // com.google.protobuf.ByteString
        public final boolean equals(Object r5) {
            if (r5 != this) goto L6;
            return true;
        L6:
            if ((r5 instanceof ByteString) == true) goto L9;
            return false;
        L9:
            if (size() == ((ByteString) r5).size()) goto L12;
            return false;
        L12:
            if (size() != 0) goto L15;
            return true;
        L15:
            if ((r5 instanceof LiteralByteString) == false) goto L24;
            LiteralByteString r52 = (LiteralByteString) r5;
            int r0 = peekCachedHashCode();
            int r1 = r52.peekCachedHashCode();
            if (r0 == 0) goto L22;
            if (r1 == 0) goto L22;
            if (r0 == r1) goto L22;
            return false;
        L22:
            return equalsRange(r52, 0, size());
        L24:
            return r5.equals(this);
        }

        public final boolean equalsRange(ByteString r5, int r6, int r7) {
            if (r7 > r5.size()) goto L20;
            int r0 = r6 + r7;
            if (r0 > r5.size()) goto L19;
            if ((r5 instanceof LiteralByteString) == false) goto L17;
            LiteralByteString r52 = (LiteralByteString) r5;
            byte[] r02 = this.bytes;
            byte[] r2 = r52.bytes;
            int r3 = getOffsetIntoBytes() + r7;
            int r4 = getOffsetIntoBytes();
            int r53 = r52.getOffsetIntoBytes() + r6;
        L9:
            if (r4 >= r3) goto L14;
            if (r02[r4] != r2[r53]) goto L12;
            r4 = r4 + 1;
            r53 = r53 + 1;
            goto L9
        L12:
            return false;
        L14:
            return true;
        L17:
            return r5.substring(r6, r0).equals(substring(0, r7));
        L19:
            throw new IllegalArgumentException("Ran off end of other: " + r6 + ", " + r7 + ", " + r5.size());
        L20:
            zzgu$$ExternalSyntheticBUOutline0.m(r7, size());
            return false;
        }

        public int getOffsetIntoBytes() {
            return 0;
        }

        @Override // com.google.protobuf.ByteString
        public byte internalByteAt(int r1) {
            return this.bytes[r1];
        }

        @Override // com.google.protobuf.ByteString
        public final boolean isValidUtf8() {
            int r0 = getOffsetIntoBytes();
            return Utf8.isValidUtf8(this.bytes, r0, size() + r0);
        }

        @Override // com.google.protobuf.ByteString
        public final CodedInputStream newCodedInput() {
            return CodedInputStream.newInstance(this.bytes, getOffsetIntoBytes(), size(), true);
        }

        @Override // com.google.protobuf.ByteString
        public final int partialHash(int r2, int r3, int r4) {
            return Internal.partialHash(r2, this.bytes, getOffsetIntoBytes() + r3, r4);
        }

        @Override // com.google.protobuf.ByteString
        public int size() {
            return this.bytes.length;
        }

        @Override // com.google.protobuf.ByteString
        public final ByteString substring(int r3, int r4) {
            int r42 = ByteString.checkRange(r3, r4, size());
            if (r42 != 0) goto L7;
            return ByteString.EMPTY;
        L7:
            return new BoundedByteString(this.bytes, getOffsetIntoBytes() + r3, r42);
        }

        @Override // com.google.protobuf.ByteString
        public final String toStringInternal(Charset r4) {
            return new String(this.bytes, getOffsetIntoBytes(), size(), r4);
        }

        @Override // com.google.protobuf.ByteString
        public final void writeTo(ByteOutput r3) {
            r3.writeLazy(this.bytes, getOffsetIntoBytes(), size());
        }
    }

    public static final class SystemByteArrayCopier implements ByteArrayCopier {
        private SystemByteArrayCopier() {
        }

        public /* synthetic */ SystemByteArrayCopier(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] r2, int r3, int r4) {
            byte[] r1 = new byte[r4];
            System.arraycopy(r2, r3, r1, 0, r4);
            return r1;
        }
    }

    static {
        EMPTY = new LiteralByteString(Internal.EMPTY_BYTE_ARRAY);
        if (Android.isOnAndroidDevice() == false) goto L5;
        ByteArrayCopier r0 = new SystemByteArrayCopier(null);
    L6:
        byteArrayCopier = r0;
        UNSIGNED_LEXICOGRAPHICAL_COMPARATOR = new AnonymousClass2();
        return;
    L5:
        r0 = new ArraysByteArrayCopier(null);
        goto L6
    }

    public ByteString() {
        this.hash = 0;
    }

    public static /* synthetic */ int access$200(byte r0) {
        return toInt(r0);
    }

    public static void checkIndex(int r1, int r2) {
        if (((r2 - (r1 + 1)) | r1) >= 0) goto L9;
        if (r1 >= 0) goto L7;
        ByteString$$ExternalSyntheticBUOutline0.m(r1);
        return;
    L7:
        ByteString$$ExternalSyntheticBUOutline1.m(r1, r2);
        return;
    }

    public static int checkRange(int r3, int r4, int r5) {
        int r0 = r4 - r3;
        if ((((r3 | r4) | r0) | (r5 - r4)) >= 0) goto L11;
        if (r3 < 0) goto L10;
        if (r4 >= r3) goto L9;
        Dex$$ExternalSyntheticBUOutline0.m("Beginning index larger than ending index: ", r3, ", ", r4);
        return 0;
    L9:
        Dex$$ExternalSyntheticBUOutline0.m("End index: ", r4, " >= ", r5);
        return 0;
    L10:
        ByteString$$ExternalSyntheticBUOutline2.m("Beginning index: ", r3, " < 0");
        return 0;
    L11:
        return r0;
    }

    public static ByteString copyFrom(String r1, Charset r2) {
        return new LiteralByteString(r1.getBytes(r2));
    }

    public static ByteString copyFrom(byte[] r2) {
        return copyFrom(r2, 0, r2.length);
    }

    public static ByteString copyFrom(byte[] r2, int r3, int r4) {
        checkRange(r3, r3 + r4, r2.length);
        return new LiteralByteString(byteArrayCopier.copyFrom(r2, r3, r4));
    }

    public static ByteString copyFromUtf8(String r2) {
        return new LiteralByteString(r2.getBytes(Internal.UTF_8));
    }

    public static CodedBuilder newCodedBuilder(int r2) {
        return new CodedBuilder(r2, null);
    }

    private static int toInt(byte r0) {
        return r0 & UByte.MAX_VALUE;
    }

    private String truncateAndEscapeForDisplay() {
        if (size() > 50) goto L7;
        return TextFormatEscaper.escapeBytes(this);
    L7:
        return TextFormatEscaper.escapeBytes(substring(0, 47)) + "...";
    }

    public static ByteString wrap(ByteBuffer r3) {
        if (r3.hasArray() == false) goto L7;
        int r0 = r3.arrayOffset();
        return wrap(r3.array(), r0 + r3.position(), r3.remaining());
    L7:
        return new NioByteString(r3);
    }

    public static ByteString wrap(byte[] r1) {
        return new LiteralByteString(r1);
    }

    public static ByteString wrap(byte[] r1, int r2, int r3) {
        return new BoundedByteString(r1, r2, r3);
    }

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract byte byteAt(int r1);

    public abstract void copyToInternal(byte[] r1, int r2, int r3, int r4);

    public abstract boolean equals(Object r1);

    public final int hashCode() {
        int r0 = this.hash;
        if (r0 != 0) goto L8;
        int r02 = size();
        r0 = partialHash(r02, 0, r02);
        if (r0 != 0) goto L7;
        r0 = 1;
    L7:
        this.hash = r0;
    L8:
        return r0;
    }

    public abstract byte internalByteAt(int r1);

    public abstract boolean isValidUtf8();

    @Override // java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ Iterator<Byte> iterator2() {
        return iterator();
    }

    public abstract CodedInputStream newCodedInput();

    public abstract int partialHash(int r1, int r2, int r3);

    public final int peekCachedHashCode() {
        return this.hash;
    }

    public abstract int size();

    public abstract ByteString substring(int r1, int r2);

    public final byte[] toByteArray() {
        int r0 = size();
        if (r0 == 0) goto L5;
        byte[] r1 = new byte[r0];
        copyToInternal(r1, 0, 0, r0);
        return r1;
    L5:
        return Internal.EMPTY_BYTE_ARRAY;
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()), truncateAndEscapeForDisplay()});
    }

    public final String toString(Charset r2) {
        if (size() != 0) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return toStringInternal(r2);
    }

    public abstract String toStringInternal(Charset r1);

    public final String toStringUtf8() {
        return toString(Internal.UTF_8);
    }

    public abstract void writeTo(ByteOutput r1);
}
