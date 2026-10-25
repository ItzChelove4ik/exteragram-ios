package com.sun.jna.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ReflectionUtils {
    private static Constructor CONSTRUCTOR_LOOKUP_CLASS;
    private static final Logger LOG = null;
    private static final Method METHOD_HANDLES_BIND_TO = null;
    private static final Method METHOD_HANDLES_INVOKE_WITH_ARGUMENTS = null;
    private static final Method METHOD_HANDLES_LOOKUP = null;
    private static final Method METHOD_HANDLES_LOOKUP_FIND_SPECIAL = null;
    private static final Method METHOD_HANDLES_LOOKUP_IN = null;
    private static final Method METHOD_HANDLES_LOOKUP_UNREFLECT_SPECIAL = null;
    private static final Method METHOD_HANDLES_PRIVATE_LOOKUP_IN = null;
    private static final Method METHOD_IS_DEFAULT = null;
    private static final Method METHOD_TYPE = null;

    static {
        LOG = Logger.getLogger(ReflectionUtils.class.getName());
        Class r0 = lookupClass("java.lang.invoke.MethodHandles");
        Class r1 = lookupClass("java.lang.invoke.MethodHandle");
        Class r2 = lookupClass("java.lang.invoke.MethodHandles$Lookup");
        Class r3 = lookupClass("java.lang.invoke.MethodType");
        METHOD_IS_DEFAULT = lookupMethod(Method.class, "isDefault", new Class[0]);
        METHOD_HANDLES_LOOKUP = lookupMethod(r0, "lookup", new Class[0]);
        METHOD_HANDLES_LOOKUP_IN = lookupMethod(r2, "in", new Class[]{Class.class});
        METHOD_HANDLES_LOOKUP_UNREFLECT_SPECIAL = lookupMethod(r2, "unreflectSpecial", new Class[]{Method.class, Class.class});
        METHOD_HANDLES_LOOKUP_FIND_SPECIAL = lookupMethod(r2, "findSpecial", new Class[]{Class.class, String.class, r3, Class.class});
        METHOD_HANDLES_BIND_TO = lookupMethod(r1, "bindTo", new Class[]{Object.class});
        METHOD_HANDLES_INVOKE_WITH_ARGUMENTS = lookupMethod(r1, "invokeWithArguments", new Class[]{Object[].class});
        METHOD_HANDLES_PRIVATE_LOOKUP_IN = lookupMethod(r0, "privateLookupIn", new Class[]{Class.class, r2});
        METHOD_TYPE = lookupMethod(r3, "methodType", new Class[]{Class.class, Class[].class});
    }

    private static Object createLookup() {
        return METHOD_HANDLES_LOOKUP.invoke(null, null);
    }

    private static Object createPrivateLookupIn(Class r2, Object r3) {
        return METHOD_HANDLES_PRIVATE_LOOKUP_IN.invoke(null, new Object[]{r2, r3});
    }

    private static Constructor getConstructorLookupClass() {
        if (CONSTRUCTOR_LOOKUP_CLASS != null) goto L6;
        CONSTRUCTOR_LOOKUP_CLASS = lookupDeclaredConstructor(lookupClass("java.lang.invoke.MethodHandles$Lookup"), new Class[]{Class.class});
    L6:
        return CONSTRUCTOR_LOOKUP_CLASS;
    }

    public static Object getMethodHandle(Method r2) {
        Object r0 = createLookup();
        return mhViaFindSpecial(createPrivateLookupIn(r2.getDeclaringClass(), r0), r2);
    L6:
        return mhViaUnreflectSpecial(getConstructorLookupClass().newInstance(new Object[]{r2.getDeclaringClass()}), r2);
    }

    public static Object invokeDefaultMethod(Object r1, Object r2, Object... r3) {
        Object r12 = METHOD_HANDLES_BIND_TO.invoke(r2, new Object[]{r1});
        return METHOD_HANDLES_INVOKE_WITH_ARGUMENTS.invoke(r12, new Object[]{r3});
    }

    public static boolean isDefault(Method r3) {
        Method r0 = METHOD_IS_DEFAULT;
        if (r0 != null) goto L26;
        return false;
    L26:
        return ((Boolean) r0.invoke(r3, null)).booleanValue();
    L12:
        e = e;
    L24:
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return false;
    L10:
        e = e;
    L8:
        e = move-exception;
        Throwable r32 = e.getCause();
        if ((r32 instanceof RuntimeException) == true) goto L23;
        if ((r32 instanceof Error) == true) goto L21;
        HttpUrl$$ExternalSyntheticBUOutline0.m(r32);
        return false;
    L21:
        throw ((Error) r32);
    L23:
        throw ((RuntimeException) r32);
    }

    private static Class lookupClass(String r5) {
        return Class.forName(r5);
    L4:
        e = move-exception;
        LOG.log(Level.FINE, "Failed to lookup class: " + r5, e);
        return null;
    }

    private static Constructor lookupDeclaredConstructor(Class r4, Class... r5) {
        if (r4 != null) goto L10;
        LOG.log(Level.FINE, "Failed to lookup method: <init>#{1}({2})", new Object[]{r4, Arrays.toString(r5)});
        return null;
    L10:
        Constructor r2 = r4.getDeclaredConstructor(r5);     // Catch: Exception -> L8
        r2.setAccessible(true);     // Catch: Exception -> L8
        return r2;
    L8:
        LOG.log(Level.FINE, "Failed to lookup method: <init>#{1}({2})", new Object[]{r4, Arrays.toString(r5)});
        return null;
    }

    private static Method lookupMethod(Class r4, String r5, Class... r6) {
        if (r4 != null) goto L10;
        LOG.log(Level.FINE, "Failed to lookup method: {0}#{1}({2})", new Object[]{r4, r5, Arrays.toString(r6)});
        return null;
    L10:
        return r4.getMethod(r5, r6);
    L8:
        LOG.log(Level.FINE, "Failed to lookup method: {0}#{1}({2})", new Object[]{r4, r5, Arrays.toString(r6)});
        return null;
    }

    private static Object mhViaFindSpecial(Object r6, Method r7) {
        return METHOD_HANDLES_LOOKUP_FIND_SPECIAL.invoke(r6, new Object[]{r7.getDeclaringClass(), r7.getName(), METHOD_TYPE.invoke(null, new Object[]{r7.getReturnType(), r7.getParameterTypes()}), r7.getDeclaringClass()});
    }

    private static Object mhViaUnreflectSpecial(Object r2, Method r3) {
        Object r22 = METHOD_HANDLES_LOOKUP_IN.invoke(r2, new Object[]{r3.getDeclaringClass()});
        return METHOD_HANDLES_LOOKUP_UNREFLECT_SPECIAL.invoke(r22, new Object[]{r3, r3.getDeclaringClass()});
    }
}
