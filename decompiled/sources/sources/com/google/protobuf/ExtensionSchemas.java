package com.google.protobuf;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class ExtensionSchemas {
    private static final ExtensionSchema<?> FULL_SCHEMA = null;
    private static final ExtensionSchema<?> LITE_SCHEMA = null;

    static {
        LITE_SCHEMA = new ExtensionSchemaLite();
        FULL_SCHEMA = loadSchemaForFullRuntime();
    }

    public static ExtensionSchema<?> full() {
        ExtensionSchema<?> r0 = FULL_SCHEMA;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Protobuf runtime is not correctly loaded.");
        return null;
    }

    public static ExtensionSchema<?> lite() {
        return LITE_SCHEMA;
    }

    private static ExtensionSchema<?> loadSchemaForFullRuntime() {
        return (ExtensionSchema) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
