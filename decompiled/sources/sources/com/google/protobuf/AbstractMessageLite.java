package com.google.protobuf;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int memoizedHashCode;

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {

        public static final class LimitedInputStream extends FilterInputStream implements InputStreamRetargetInterface {
            private int limit;

            public LimitedInputStream(InputStream r1, int r2) {
                super(r1);
                this.limit = r2;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() {
                if (this.limit > 0) goto L6;
                return -1;
            L6:
                int r0 = super.read();
                if (r0 < 0) goto L9;
                this.limit--;
            L9:
                return r0;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] r2, int r3, int r4) {
                int r0 = this.limit;
                if (r0 > 0) goto L6;
                return -1;
            L6:
                int r22 = super.read(r2, r3, Math.min(r4, r0));
                if (r22 < 0) goto L9;
                this.limit -= r22;
            L9:
                return r22;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long r3) {
                int r32 = (int) super.skip(Math.min(r3, this.limit));
                if (r32 < 0) goto L6;
                this.limit -= r32;
            L6:
                return r32;
            }

            @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
            public /* synthetic */ long transferTo(OutputStream r1) {
                return DesugarInputStream.transferTo(this, r1);
            }
        }

        public Builder() {
        }

        @Deprecated
        public static <T> void addAll(Iterable<T> r0, Collection<? super T> r1) {
            addAll(r0, (List) r1);
        }

        public static <T> void addAll(Iterable<T> r3, List<? super T> r4) {
            Internal.checkNotNull(r3);
            if ((r3 instanceof LazyStringList) == false) goto L20;
            List<?> r32 = ((LazyStringList) r3).getUnderlyingElements();
            LazyStringList r0 = (LazyStringList) r4;
            int r42 = r4.size();
            Iterator<?> r33 = r32.iterator();
        L6:
            if (r33.hasNext() == false) goto L18;
            Object r1 = r33.next();
            if (r1 == null) goto L9;
            if ((r1 instanceof ByteString) == true) goto L16;
            r0.add((String) r1);
            goto L6
        L16:
            r0.add((ByteString) r1);
            goto L6
        L9:
            String r34 = "Element at index " + (r0.size() - r42) + " is null.";
            int r12 = r0.size() - 1;
        L10:
            if (r12 < r42) goto L12;
            r0.remove(r12);
            r12 = r12 - 1;
            goto L10
        L12:
            f$$ExternalSyntheticBUOutline2.m(r34);
            return;
        L18:
            return;
        L20:
            if ((r3 instanceof PrimitiveNonBoxingCollection) == false) goto L23;
            r4.addAll((Collection) r3);
            return;
        L23:
            addAllCheckingNulls(r3, r4);
        }

        private static <T> void addAllCheckingNulls(Iterable<T> r3, List<? super T> r4) {
            if ((r4 instanceof ArrayList) == true) goto L5;
        L7:
            int r0 = r4.size();
            Iterator<T> r32 = r3.iterator();
        L9:
            if (r32.hasNext() == false) goto L18;
            T r1 = r32.next();
            if (r1 == null) goto L12;
            r4.add(r1);
            goto L9
        L12:
            String r33 = "Element at index " + (r4.size() - r0) + " is null.";
            int r12 = r4.size() - 1;
        L13:
            if (r12 < r0) goto L15;
            r4.remove(r12);
            r12 = r12 - 1;
            goto L13
        L15:
            f$$ExternalSyntheticBUOutline2.m(r33);
            return;
        L18:
            return;
        L5:
            if ((r3 instanceof Collection) == false) goto L7;
            ((ArrayList) r4).ensureCapacity(r4.size() + ((Collection) r3).size());
            goto L7
        }

        private String getReadingExceptionMessage(String r3) {
            return "Reading " + getClass().getName() + " from a " + r3 + " threw an IOException (should never happen).";
        }

        public static UninitializedMessageException newUninitializedMessageException(MessageLite r1) {
            return new UninitializedMessageException(r1);
        }

        public abstract BuilderType internalMergeFrom(MessageType r1);

        public boolean mergeDelimitedFrom(InputStream r2) {
            return mergeDelimitedFrom(r2, ExtensionRegistryLite.getEmptyRegistry());
        }

        public boolean mergeDelimitedFrom(InputStream r3, ExtensionRegistryLite r4) {
            int r0 = r3.read();
            if (r0 != (-1)) goto L6;
            return false;
        L6:
            mergeFrom(new LimitedInputStream(r3, CodedInputStream.readRawVarint32(r0, r3)), r4);
            return true;
        }

        public BuilderType mergeFrom(ByteString r2) {
            CodedInputStream r22 = r2.newCodedInput();     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            mergeFrom(r22);     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            r22.checkLastTagWas(0);     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            return this;
        L7:
            e = move-exception;
            throw e;
        L4:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m(getReadingExceptionMessage("ByteString"), e);
            return null;
        }

        public BuilderType mergeFrom(ByteString r1, ExtensionRegistryLite r2) {
            CodedInputStream r12 = r1.newCodedInput();     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            mergeFrom(r12, r2);     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            r12.checkLastTagWas(0);     // Catch: IOException -> L4 InvalidProtocolBufferException -> L7
            return this;
        L7:
            e = move-exception;
            throw e;
        L4:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m(getReadingExceptionMessage("ByteString"), e);
            return null;
        }

        public BuilderType mergeFrom(CodedInputStream r2) {
            return (BuilderType) mergeFrom(r2, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(CodedInputStream r1, ExtensionRegistryLite r2);

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(MessageLite r2) {
            if (getDefaultInstanceForType().getClass().isInstance(r2) == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("mergeFrom(MessageLite) can only merge messages of the same type.");
            return null;
        L5:
            return (BuilderType) internalMergeFrom((AbstractMessageLite) r2);
        }

        public BuilderType mergeFrom(InputStream r2) {
            CodedInputStream r22 = CodedInputStream.newInstance(r2);
            mergeFrom(r22);
            r22.checkLastTagWas(0);
            return this;
        }

        public BuilderType mergeFrom(InputStream r1, ExtensionRegistryLite r2) {
            CodedInputStream r12 = CodedInputStream.newInstance(r1);
            mergeFrom(r12, r2);
            r12.checkLastTagWas(0);
            return this;
        }

        public BuilderType mergeFrom(byte[] r3) {
            return (BuilderType) mergeFrom(r3, 0, r3.length);
        }

        public abstract BuilderType mergeFrom(byte[] r1, int r2, int r3);

        public abstract BuilderType mergeFrom(byte[] r1, int r2, int r3, ExtensionRegistryLite r4);

        public BuilderType mergeFrom(byte[] r3, ExtensionRegistryLite r4) {
            return (BuilderType) mergeFrom(r3, 0, r3.length, r4);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1991mergeFrom(ByteString r1) {
            return mergeFrom(r1);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1992mergeFrom(ByteString r1, ExtensionRegistryLite r2) {
            return mergeFrom(r1, r2);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1993mergeFrom(CodedInputStream r1) {
            return mergeFrom(r1);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite r1) {
            return mergeFrom(r1);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1994mergeFrom(InputStream r1) {
            return mergeFrom(r1);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1995mergeFrom(InputStream r1, ExtensionRegistryLite r2) {
            return mergeFrom(r1, r2);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1996mergeFrom(byte[] r1) {
            return mergeFrom(r1);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m1997mergeFrom(byte[] r1, ExtensionRegistryLite r2) {
            return mergeFrom(r1, r2);
        }
    }

    public AbstractMessageLite() {
        this.memoizedHashCode = 0;
    }

    @Deprecated
    public static <T> void addAll(Iterable<T> r0, Collection<? super T> r1) {
        Builder.addAll(r0, (List) r1);
    }

    public static <T> void addAll(Iterable<T> r0, List<? super T> r1) {
        Builder.addAll(r0, r1);
    }

    public static void checkByteStringIsUtf8(ByteString r0) {
        if (r0.isValidUtf8() == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Byte string is not UTF-8.");
    }

    private String getSerializingExceptionMessage(String r3) {
        return "Serializing " + getClass().getName() + " to a " + r3 + " threw an IOException (should never happen).";
    }

    public abstract int getSerializedSize(Schema r1);

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    public byte[] toByteArray() {
        byte[] r0 = new byte[getSerializedSize()];     // Catch: IOException -> L4
        CodedOutputStream r1 = CodedOutputStream.newInstance(r0);     // Catch: IOException -> L4
        writeTo(r1);     // Catch: IOException -> L4
        r1.checkNoSpaceLeft();     // Catch: IOException -> L4
        return r0;
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m(getSerializingExceptionMessage("byte array"), e);
        return null;
    }

    public ByteString toByteString() {
        ByteString.CodedBuilder r0 = ByteString.newCodedBuilder(getSerializedSize());     // Catch: IOException -> L4
        writeTo(r0.getCodedOutput());     // Catch: IOException -> L4
        return r0.build();
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m(this.getSerializingExceptionMessage("ByteString"), e);
        return null;
    }

    public void writeDelimitedTo(OutputStream r3) {
        int r0 = getSerializedSize();
        CodedOutputStream r32 = CodedOutputStream.newInstance(r3, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeUInt32SizeNoTag(r0) + r0));
        r32.writeUInt32NoTag(r0);
        writeTo(r32);
        r32.flush();
    }

    public void writeTo(OutputStream r2) {
        CodedOutputStream r22 = CodedOutputStream.newInstance(r2, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(r22);
        r22.flush();
    }
}
