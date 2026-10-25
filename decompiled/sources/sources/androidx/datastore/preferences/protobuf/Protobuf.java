package androidx.datastore.preferences.protobuf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
final class Protobuf {
    private static final Protobuf INSTANCE = null;
    static boolean assumeLiteRuntime;
    private final ConcurrentMap<Class<?>, Schema<?>> schemaCache;
    private final SchemaFactory schemaFactory;

    static {
        INSTANCE = new Protobuf();
        assumeLiteRuntime = false;
    }

    private Protobuf() {
        this.schemaCache = new ConcurrentHashMap();
        this.schemaFactory = new ManifestSchemaFactory();
    }

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public Schema<?> registerSchema(Class<?> r2, Schema<?> r3) {
        Internal.checkNotNull(r2, "messageType");
        Internal.checkNotNull(r3, "schema");
        return this.schemaCache.putIfAbsent(r2, r3);
    }

    public <T> Schema<T> schemaFor(Class<T> r2) {
        Internal.checkNotNull(r2, "messageType");
        Schema<T> r0 = (Schema) this.schemaCache.get(r2);
        if (r0 != null) goto L7;
        r0 = this.schemaFactory.createSchema(r2);
        Schema<T> r1 = (Schema<T>) registerSchema(r2, r0);
        if (r1 == null) goto L7;
        return r1;
    L7:
        return r0;
    }

    public <T> Schema<T> schemaFor(T r1) {
        return schemaFor(r1.getClass());
    }
}
