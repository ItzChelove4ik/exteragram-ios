package com.google.common.hash;

import java.nio.Buffer;

/* loaded from: classes5.dex */
abstract class Java8Compatibility {
    public static void flip(Buffer r0) {
        r0.flip();
    }

    public static void position(Buffer r0, int r1) {
        r0.position(r1);
    }
}
