package com.sun.jna;

/* loaded from: classes5.dex */
public interface CallbackProxy extends Callback {
    Object callback(Object[] r1);

    Class<?>[] getParameterTypes();

    Class<?> getReturnType();
}
