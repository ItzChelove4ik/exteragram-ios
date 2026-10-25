package com.google.protobuf;

/* loaded from: classes5.dex */
public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite build();

        MessageLite buildPartial();

        Builder mergeFrom(CodedInputStream r1, ExtensionRegistryLite r2);

        Builder mergeFrom(MessageLite r1);
    }

    int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();

    void writeTo(CodedOutputStream r1);
}
