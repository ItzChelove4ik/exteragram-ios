package com.google.gson.internal;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class Primitives {
    public static boolean isPrimitive(Type r1) {
        if ((r1 instanceof Class) == true) goto L5;
        return false;
    L5:
        if (((Class) r1).isPrimitive() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static <T> Class<T> wrap(Class<T> r1) {
        if (r1 != Integer.TYPE) goto L7;
        return Integer.class;
    L7:
        if (r1 != Float.TYPE) goto L11;
        return Float.class;
    L11:
        if (r1 != Byte.TYPE) goto L15;
        return Byte.class;
    L15:
        if (r1 != Double.TYPE) goto L19;
        return Double.class;
    L19:
        if (r1 != Long.TYPE) goto L23;
        return Long.class;
    L23:
        if (r1 != Character.TYPE) goto L27;
        return Character.class;
    L27:
        if (r1 != Boolean.TYPE) goto L31;
        return Boolean.class;
    L31:
        if (r1 != Short.TYPE) goto L35;
        return Short.class;
    L35:
        if (r1 != Void.TYPE) goto L38;
        return Void.class;
    L38:
        return r1;
    }
}
