package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes5.dex */
public class ExtensionRegistryLite {
    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = null;
    private static boolean doFullRuntimeInheritanceCheck = true;
    private static volatile ExtensionRegistryLite emptyRegistry;
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

    public static final class ObjectIntPair {
        private final int number;
        private final Object object;

        public ObjectIntPair(Object r1, int r2) {
            this.object = r1;
            this.number = r2;
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof ObjectIntPair) == true) goto L5;
            return false;
        L5:
            ObjectIntPair r42 = (ObjectIntPair) r4;
            if (this.object == r42.object) goto L8;
        L11:
            return false;
        L8:
            if (this.number != r42.number) goto L11;
            return true;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }
    }

    static {
        EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    }

    public ExtensionRegistryLite(boolean r1) {
        this.extensionsByNumber = Collections.EMPTY_MAP;
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        if (doFullRuntimeInheritanceCheck == false) goto L5;
        ExtensionRegistryLite r0 = emptyRegistry;
        if (r0 == null) goto L9;
        return r0;
    L9:
        monitor-enter(ExtensionRegistryLite.class);
        ExtensionRegistryLite r02 = emptyRegistry;     // Catch: Throwable -> L13
        if (r02 != null) goto L15;
        r02 = ExtensionRegistryFactory.createEmpty();     // Catch: Throwable -> L13
        emptyRegistry = r02;     // Catch: Throwable -> L13
    L15:
        monitor-exit(ExtensionRegistryLite.class);     // Catch: Throwable -> L13
        return r02;
    L13:
        th = move-exception;
        throw th;
    L5:
        return EMPTY_REGISTRY_LITE;
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType r2, int r3) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(r2, r3));
    }
}
