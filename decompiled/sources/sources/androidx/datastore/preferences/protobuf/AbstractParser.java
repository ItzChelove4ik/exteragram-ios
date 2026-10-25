package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.MessageLite;

/* loaded from: classes3.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = null;

    static {
        EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    }

    public AbstractParser() {
    }
}
