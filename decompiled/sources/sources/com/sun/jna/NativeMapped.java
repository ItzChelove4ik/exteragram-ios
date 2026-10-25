package com.sun.jna;

/* loaded from: classes5.dex */
public interface NativeMapped {
    Object fromNative(Object r1, FromNativeContext r2);

    Class<?> nativeType();

    Object toNative();
}
