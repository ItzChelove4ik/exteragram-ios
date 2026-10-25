package org.chromium.support_lib_boundary.util;

import android.os.Build;
import c.a$$ExternalSyntheticBUOutline0;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/* loaded from: classes5.dex */
public abstract class BoundaryInterfaceReflectionUtil {

    public static class InvocationHandlerWithDelegateGetter implements InvocationHandler {
        private final Object mDelegate;

        public InvocationHandlerWithDelegateGetter(Object r1) {
            this.mDelegate = r1;
        }

        public boolean equals(Object r2) {
            if (r2 != null) goto L6;
            return false;
        L6:
            if ((r2 instanceof InvocationHandlerWithDelegateGetter) == false) goto L10;
            return this.mDelegate.equals(((InvocationHandlerWithDelegateGetter) r2).mDelegate);
        L10:
            return this.mDelegate.equals(r2);
        }

        public int hashCode() {
            return this.mDelegate.hashCode();
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object r1, Method r2, Object[] r3) {
            return BoundaryInterfaceReflectionUtil.dupeMethod(r2, this.mDelegate.getClass().getClassLoader()).invoke(this.mDelegate, r3);
        L8:
            e = move-exception;
            throw e.getTargetException();
        L5:
            e = move-exception;
            a$$ExternalSyntheticBUOutline0.m("Reflection failed for method ", r2, e);
            return null;
        }
    }

    static {
    }

    public static <T> T castToSuppLibClass(Class<T> r2, InvocationHandler r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        return r2.cast(Proxy.newProxyInstance(BoundaryInterfaceReflectionUtil.class.getClassLoader(), new Class[]{r2}, r3));
    }

    public static boolean containsFeature(Collection<String> r1, String r2) {
        if (r1.contains(r2) == false) goto L5;
        return true;
    L5:
        if (isDebuggable() == true) goto L7;
        return false;
    L7:
        if (r1.contains(r2 + ":dev") == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static InvocationHandler createInvocationHandlerFor(Object r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new InvocationHandlerWithDelegateGetter(r1);
    }

    public static Method dupeMethod(Method r2, ClassLoader r3) {
        Class<?> r32 = Class.forName(r2.getDeclaringClass().getName(), true, r3);
        Class<?>[] r0 = r2.getParameterTypes();
        return r32.getDeclaredMethod(r2.getName(), r0);
    }

    private static boolean isDebuggable() {
        String r0 = Build.TYPE;
        if ("eng".equals(r0) == false) goto L5;
        return true;
    L5:
        if ("userdebug".equals(r0) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
