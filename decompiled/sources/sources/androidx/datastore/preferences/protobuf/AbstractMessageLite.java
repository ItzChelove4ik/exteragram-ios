package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.AbstractMessageLite;
import androidx.datastore.preferences.protobuf.AbstractMessageLite.Builder;
import androidx.datastore.preferences.protobuf.MessageLite;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int memoizedHashCode;

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {
        public Builder() {
        }

        public static <T> void addAll(Iterable<T> r3, List<? super T> r4) {
            Internal.checkNotNull(r3);
            if ((r3 instanceof LazyStringList) == false) goto L23;
            List<?> r32 = ((LazyStringList) r3).getUnderlyingElements();
            LazyStringList r0 = (LazyStringList) r4;
            int r42 = r4.size();
            Iterator<?> r33 = r32.iterator();
        L6:
            if (r33.hasNext() == false) goto L21;
            Object r1 = r33.next();
            if (r1 == null) goto L9;
            if ((r1 instanceof ByteString) == true) goto L16;
            if ((r1 instanceof byte[]) == true) goto L19;
            r0.add((String) r1);
            goto L6
        L19:
            r0.add(ByteString.copyFrom((byte[]) r1));
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
        L21:
            return;
        L23:
            if ((r3 instanceof PrimitiveNonBoxingCollection) == false) goto L26;
            r4.addAll((Collection) r3);
            return;
        L26:
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

        public static UninitializedMessageException newUninitializedMessageException(MessageLite r1) {
            return new UninitializedMessageException(r1);
        }
    }

    public AbstractMessageLite() {
        this.memoizedHashCode = 0;
    }

    public static <T> void addAll(Iterable<T> r0, List<? super T> r1) {
        Builder.addAll(r0, r1);
    }

    public abstract int getSerializedSize(Schema r1);

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    public void writeTo(OutputStream r2) {
        CodedOutputStream r22 = CodedOutputStream.newInstance(r2, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(r22);
        r22.flush();
    }
}
