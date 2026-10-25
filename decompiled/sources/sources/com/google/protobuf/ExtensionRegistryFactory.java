package com.google.protobuf;

/* loaded from: classes5.dex */
abstract class ExtensionRegistryFactory {
    static final Class<?> EXTENSION_REGISTRY_CLASS = null;

    static {
        EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite r0 = invokeSubclassFactory("getEmptyRegistry");
        if (r0 == null) goto L6;
        return r0;
    L6:
        return ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String r2) {
        Class<?> r0 = EXTENSION_REGISTRY_CLASS;
        if (r0 != null) goto L8;
        return null;
    L8:
        return (ExtensionRegistryLite) r0.getDeclaredMethod(r2, null).invoke(null, null);
    L7:
        return null;
    }

    public static Class<?> reflectExtensionRegistry() {
        return Class.forName("com.google.protobuf.ExtensionRegistry");
    L4:
        return null;
    }
}
