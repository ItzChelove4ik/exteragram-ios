package com.google.protobuf;

import com.google.protobuf.MessageLite;

/* loaded from: classes5.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = null;

    static {
        EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    }

    public AbstractParser() {
    }
}
