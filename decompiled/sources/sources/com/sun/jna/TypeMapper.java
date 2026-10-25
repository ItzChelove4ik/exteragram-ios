package com.sun.jna;

/* loaded from: classes5.dex */
public interface TypeMapper {
    FromNativeConverter getFromNativeConverter(Class<?> r1);

    ToNativeConverter getToNativeConverter(Class<?> r1);
}
