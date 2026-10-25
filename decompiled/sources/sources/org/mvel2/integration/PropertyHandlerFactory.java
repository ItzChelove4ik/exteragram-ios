package org.mvel2.integration;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class PropertyHandlerFactory {
    protected static PropertyHandler nullMethodHandler;
    protected static PropertyHandler nullPropertyHandler;
    protected static Map<Class, PropertyHandler> propertyHandlerClass;

    static {
        propertyHandlerClass = new HashMap();
    }

    public PropertyHandlerFactory() {
    }

    public static void disposeAll() {
        nullMethodHandler = null;
        nullPropertyHandler = null;
        propertyHandlerClass.clear();
    }

    public static PropertyHandler getNullMethodHandler() {
        return nullMethodHandler;
    }

    public static PropertyHandler getNullPropertyHandler() {
        return nullPropertyHandler;
    }

    public static PropertyHandler getPropertyHandler(Class r1) {
        return propertyHandlerClass.get(r1);
    }

    public static boolean hasNullMethodHandler() {
        if (nullMethodHandler == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean hasNullPropertyHandler() {
        if (nullPropertyHandler == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean hasPropertyHandler(Class r8) {
        if (r8 != null) goto L6;
        return false;
    L6:
        if (propertyHandlerClass.containsKey(r8) == true) goto L25;
        Class r1 = r8;
    L8:
        if (r8 != r1) goto L10;
    L13:
        Class<?>[] r3 = r1.getInterfaces();
        int r4 = r3.length;
        int r5 = 0;
    L14:
        if (r5 >= r4) goto L20;
        Class<?> r6 = r3[r5];
        if (propertyHandlerClass.containsKey(r6) == true) goto L17;
        r5 = r5 + 1;
        goto L14
    L17:
        Map<Class, PropertyHandler> r0 = propertyHandlerClass;
        r0.put(r8, r0.get(r6));
        return true;
    L20:
        r1 = r1.getSuperclass();
        if (r1 == null) goto L24;
        if (r1 != Object.class) goto L8;
    L24:
        return false;
    L10:
        if (propertyHandlerClass.containsKey(r1) == false) goto L13;
        Map<Class, PropertyHandler> r02 = propertyHandlerClass;
        r02.put(r8, r02.get(r1));
        return true;
    L25:
        return true;
    }

    public static void registerPropertyHandler(Class r1, PropertyHandler r2) {
        propertyHandlerClass.put(r1, r2);
    }

    public static void setNullMethodHandler(PropertyHandler r0) {
        nullMethodHandler = r0;
    }

    public static void setNullPropertyHandler(PropertyHandler r0) {
        nullPropertyHandler = r0;
    }

    public static void unregisterPropertyHandler(Class r1) {
        propertyHandlerClass.remove(r1);
    }
}
