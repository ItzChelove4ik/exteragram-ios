package com.google.protobuf;

import java.nio.Buffer;

/* loaded from: classes5.dex */
abstract class Java8Compatibility {
    public static void limit(Buffer r0, int r1) {
        r0.limit(r1);
    }

    public static void position(Buffer r0, int r1) {
        r0.position(r1);
    }
}
