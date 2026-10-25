package com.sun.jna;

/* loaded from: classes5.dex */
public interface ToNativeConverter {
    Class<?> nativeType();

    Object toNative(Object r1, ToNativeContext r2);
}
