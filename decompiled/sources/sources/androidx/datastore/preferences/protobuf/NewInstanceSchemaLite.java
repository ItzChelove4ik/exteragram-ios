package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
final class NewInstanceSchemaLite implements NewInstanceSchema {
    public NewInstanceSchemaLite() {
    }

    @Override // androidx.datastore.preferences.protobuf.NewInstanceSchema
    public Object newInstance(Object r1) {
        return ((GeneratedMessageLite) r1).newMutableInstance();
    }
}
