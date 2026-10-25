package com.google.protobuf;

/* loaded from: classes5.dex */
final class NewInstanceSchemaLite implements NewInstanceSchema {
    public NewInstanceSchemaLite() {
    }

    @Override // com.google.protobuf.NewInstanceSchema
    public Object newInstance(Object r1) {
        return ((GeneratedMessageLite) r1).newMutableInstance();
    }
}
