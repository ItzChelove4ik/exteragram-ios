package com.google.protobuf;

/* loaded from: classes5.dex */
abstract class MapFieldSchemas {
    private static final MapFieldSchema FULL_SCHEMA = null;
    private static final MapFieldSchema LITE_SCHEMA = null;

    static {
        FULL_SCHEMA = loadSchemaForFullRuntime();
        LITE_SCHEMA = new MapFieldSchemaLite();
    }

    public static MapFieldSchema full() {
        return FULL_SCHEMA;
    }

    public static MapFieldSchema lite() {
        return LITE_SCHEMA;
    }

    private static MapFieldSchema loadSchemaForFullRuntime() {
        return (MapFieldSchema) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
