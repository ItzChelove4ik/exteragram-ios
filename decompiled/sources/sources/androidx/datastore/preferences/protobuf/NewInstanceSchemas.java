package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
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
        if (Protobuf.assumeLiteRuntime == false) goto L8;
        return null;
    L8:
        return (NewInstanceSchema) Class.forName("androidx.datastore.preferences.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L7:
        return null;
    }
}
