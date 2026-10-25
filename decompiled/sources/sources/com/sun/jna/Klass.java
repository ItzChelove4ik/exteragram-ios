package com.sun.jna;

/* loaded from: classes5.dex */
abstract class Klass {
    private Klass() {
    }

    public static <T> T newInstance(Class<T> r4) {
        return r4.getDeclaredConstructor(null).newInstance(null);
    L15:
        e = e;
    L24:
        throw new IllegalArgumentException("Can't create an instance of " + r4 + ", requires a public no-arg constructor: " + e, e);
    L13:
        e = e;
    L11:
        e = e;
    L9:
        e = e;
    L7:
        e = e;
    L5:
        e = move-exception;
        if ((e.getCause() instanceof RuntimeException) == false) goto L22;
        throw ((RuntimeException) e.getCause());
    L22:
        throw new IllegalArgumentException("Can't create an instance of " + r4 + ", requires a public no-arg constructor: " + e, e);
    }
}
