package com.google.protobuf;

/* loaded from: classes5.dex */
abstract class UnknownFieldSchema<T, B> {
    public UnknownFieldSchema() {
    }

    public abstract void addFixed32(B r1, int r2, int r3);

    public abstract void addFixed64(B r1, int r2, long r3);

    public abstract void addGroup(B r1, int r2, T r3);

    public abstract void addLengthDelimited(B r1, int r2, ByteString r3);

    public abstract void addVarint(B r1, int r2, long r3);

    public abstract B getBuilderFromMessage(Object r1);

    public abstract T getFromMessage(Object r1);

    public abstract int getSerializedSize(T r1);

    public abstract int getSerializedSizeAsMessageSet(T r1);

    public abstract void makeImmutable(Object r1);

    public abstract T merge(T r1, T r2);

    public final void mergeFrom(B r3, Reader r4) {
    L3:
        if (r4.getFieldNumber() == Integer.MAX_VALUE) goto L6;
        if (mergeOneFieldFrom(r3, r4) == true) goto L3;
        return;
    }

    public final boolean mergeOneFieldFrom(B r6, Reader r7) {
        int r0 = r7.getTag();
        int r1 = WireFormat.getTagFieldNumber(r0);
        int r02 = WireFormat.getTagWireType(r0);
        if (r02 == 0) goto L28;
        if (r02 != 1) goto L6;
        addFixed64(r6, r1, r7.readFixed64());
        return true;
    L6:
        if (r02 != 2) goto L8;
        addLengthDelimited(r6, r1, r7.readBytes());
        return true;
    L8:
        if (r02 == 3) goto L18;
        if (r02 != 4) goto L11;
        return false;
    L11:
        if (r02 != 5) goto L15;
        addFixed32(r6, r1, r7.readFixed32());
        return true;
    L15:
        throw InvalidProtocolBufferException.invalidWireType();
    L18:
        B r03 = newBuilder();
        int r3 = WireFormat.makeTag(r1, 4);
        mergeFrom(r03, r7);
        if (r3 != r7.getTag()) goto L23;
        addGroup(r6, r1, toImmutable(r03));
        return true;
    L23:
        throw InvalidProtocolBufferException.invalidEndTag();
    L28:
        addVarint(r6, r1, r7.readInt64());
        return true;
    }

    public abstract B newBuilder();

    public abstract void setBuilderToMessage(Object r1, B r2);

    public abstract void setToMessage(Object r1, T r2);

    public abstract boolean shouldDiscardUnknownFields(Reader r1);

    public abstract T toImmutable(B r1);

    public abstract void writeAsMessageSetTo(T r1, Writer r2);

    public abstract void writeTo(T r1, Writer r2);
}
