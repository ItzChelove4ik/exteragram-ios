package com.chaquo.python;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class PyInvocationHandler implements InvocationHandler {
    private PyObject dict;
    private PyObject type;

    public PyInvocationHandler(PyObject r1) {
        this.type = r1;
    }

    private static native Object invokeNative(Object r0, String r1, Object[] r2, Class<?> r3);

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object r5, Method r6, Object[] r7) {
        String r0 = r6.getName();
        r0.getClass();
        char r3 = 65535;
        switch(r0.hashCode()) {
            case -983358751: goto L14;
            case 1251498965: goto L10;
            case 1251991385: goto L6;
            default: goto L17;
        };
    L17:
        switch(r3) {
            case 0: goto L24;
            case 1: goto L23;
            case 2: goto L21;
            default: goto L19;
        };
    L24:
        this.dict = (PyObject) r7[0];
        return null;
    L19:
        return invokeNative(r5, r0, r7, r6.getReturnType());
    L21:
        return this.type;
    L23:
        return this.dict;
    L6:
        if (r0.equals("_chaquopyGetType") == false) goto L17;
        r3 = 2;
        goto L17
    L10:
        if (r0.equals("_chaquopyGetDict") == false) goto L17;
        r3 = 1;
        goto L17
    L14:
        if (r0.equals("_chaquopySetDict") == false) goto L17;
        r3 = 0;
        goto L17
    }
}
