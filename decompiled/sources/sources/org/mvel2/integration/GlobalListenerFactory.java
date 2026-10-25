package org.mvel2.integration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class GlobalListenerFactory {
    private static List<Listener> propertyGetListeners;
    private static List<Listener> propertySetListeners;

    public GlobalListenerFactory() {
    }

    public static void disposeAll() {
        propertyGetListeners = null;
        propertySetListeners = null;
    }

    public static boolean hasGetListeners() {
        List<Listener> r0 = propertyGetListeners;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean hasSetListeners() {
        List<Listener> r0 = propertySetListeners;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static void notifyGetListeners(Object r3, String r4, VariableResolverFactory r5) {
        List<Listener> r0 = propertyGetListeners;
        if (r0 == null) goto L8;
        Iterator<Listener> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L10;
        r02.next().onEvent(r3, r4, r5, null);
        goto L6
    L10:
        return;
    }

    public static void notifySetListeners(Object r2, String r3, VariableResolverFactory r4, Object r5) {
        List<Listener> r0 = propertySetListeners;
        if (r0 == null) goto L8;
        Iterator<Listener> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L10;
        r02.next().onEvent(r2, r3, r4, r5);
        goto L6
    L10:
        return;
    }

    public static boolean registerGetListener(Listener r1) {
        if (propertyGetListeners != null) goto L6;
        propertyGetListeners = new LinkedList();
    L6:
        return propertyGetListeners.add(r1);
    }

    public static boolean registerSetListener(Listener r1) {
        if (propertySetListeners != null) goto L6;
        propertySetListeners = new LinkedList();
    L6:
        return propertySetListeners.add(r1);
    }
}
