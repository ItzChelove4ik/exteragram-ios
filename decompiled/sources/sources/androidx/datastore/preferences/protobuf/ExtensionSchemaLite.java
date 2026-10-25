package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Map;

/* loaded from: classes.dex */
final class ExtensionSchemaLite extends ExtensionSchema<Object> {
    public ExtensionSchemaLite() {
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public int extensionNumber(Map.Entry<?, ?> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public Object findExtensionByNumber(ExtensionRegistryLite r1, MessageLite r2, int r3) {
        r1.findLiteExtensionByNumber(r2, r3);
        return null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public FieldSet<Object> getExtensions(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public FieldSet<Object> getMutableExtensions(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public boolean hasExtensions(MessageLite r1) {
        return false;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public void makeImmutable(Object r1) {
        getExtensions(r1).makeImmutable();
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public <UT, UB> UB parseExtension(Object r1, Reader r2, Object r3, ExtensionRegistryLite r4, FieldSet<Object> r5, UB r6, UnknownFieldSchema<UT, UB> r7) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public void parseLengthPrefixedMessageSetItem(Reader r1, Object r2, ExtensionRegistryLite r3, FieldSet<Object> r4) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public void parseMessageSetItem(ByteString r1, Object r2, ExtensionRegistryLite r3, FieldSet<Object> r4) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.ExtensionSchema
    public void serializeExtension(Writer r1, Map.Entry<?, ?> r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.getKey());
        throw null;
    }
}
