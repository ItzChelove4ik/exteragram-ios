package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.FieldSet;
import androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite;
import java.util.Map;

/* loaded from: classes.dex */
abstract class ExtensionSchema<T extends FieldSet.FieldDescriptorLite<T>> {
    public ExtensionSchema() {
    }

    public abstract int extensionNumber(Map.Entry<?, ?> r1);

    public abstract Object findExtensionByNumber(ExtensionRegistryLite r1, MessageLite r2, int r3);

    public abstract FieldSet<T> getExtensions(Object r1);

    public abstract FieldSet<T> getMutableExtensions(Object r1);

    public abstract boolean hasExtensions(MessageLite r1);

    public abstract void makeImmutable(Object r1);

    public abstract <UT, UB> UB parseExtension(Object r1, Reader r2, Object r3, ExtensionRegistryLite r4, FieldSet<T> r5, UB r6, UnknownFieldSchema<UT, UB> r7);

    public abstract void parseLengthPrefixedMessageSetItem(Reader r1, Object r2, ExtensionRegistryLite r3, FieldSet<T> r4);

    public abstract void parseMessageSetItem(ByteString r1, Object r2, ExtensionRegistryLite r3, FieldSet<T> r4);

    public abstract void serializeExtension(Writer r1, Map.Entry<?, ?> r2);
}
