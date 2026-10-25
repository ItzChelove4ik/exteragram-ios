package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
abstract class ListFieldSchemas {
    private static final ListFieldSchema FULL_SCHEMA = null;
    private static final ListFieldSchema LITE_SCHEMA = null;

    static {
        FULL_SCHEMA = loadSchemaForFullRuntime();
        LITE_SCHEMA = new ListFieldSchemaLite();
    }

    public static ListFieldSchema full() {
        return FULL_SCHEMA;
    }

    public static ListFieldSchema lite() {
        return LITE_SCHEMA;
    }

    private static ListFieldSchema loadSchemaForFullRuntime() {
        if (Protobuf.assumeLiteRuntime == false) goto L8;
        return null;
    L8:
        return (ListFieldSchema) Class.forName("androidx.datastore.preferences.protobuf.ListFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L7:
        return null;
    }
}
