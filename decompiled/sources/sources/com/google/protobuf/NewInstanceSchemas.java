package com.google.protobuf;

/* loaded from: classes5.dex */
abstract class NewInstanceSchemas {
    private static final NewInstanceSchema FULL_SCHEMA = null;
    private static final NewInstanceSchema LITE_SCHEMA = null;

    static {
        FULL_SCHEMA = loadSchemaForFullRuntime();
        LITE_SCHEMA = new NewInstanceSchemaLite();
    }

    public static NewInstanceSchema full() {
        return FULL_SCHEMA;
    }

    public static NewInstanceSchema lite() {
        return LITE_SCHEMA;
    }

    private static NewInstanceSchema loadSchemaForFullRuntime() {
        return (NewInstanceSchema) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
