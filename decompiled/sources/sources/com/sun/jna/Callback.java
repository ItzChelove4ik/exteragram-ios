package com.sun.jna;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public interface Callback {
    public static final List<String> FORBIDDEN_NAMES = null;
    public static final String METHOD_NAME = "callback";

    public interface UncaughtExceptionHandler {
        void uncaughtException(Callback r1, Throwable r2);
    }

    static {
        FORBIDDEN_NAMES = Collections.unmodifiableList(Arrays.asList(new String[]{"hashCode", "equals", "toString"}));
    }
}
