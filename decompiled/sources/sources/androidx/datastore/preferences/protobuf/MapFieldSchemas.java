package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
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
        if (Protobuf.assumeLiteRuntime == false) goto L8;
        return null;
    L8:
        return (MapFieldSchema) Class.forName("androidx.datastore.preferences.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L7:
        return null;
    }
}
