package com.sun.jna;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public interface InvocationMapper {
    InvocationHandler getInvocationHandler(NativeLibrary r1, Method r2);
}
