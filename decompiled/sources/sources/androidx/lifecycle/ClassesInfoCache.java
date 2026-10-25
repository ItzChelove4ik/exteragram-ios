package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

@Deprecated
/* loaded from: classes3.dex */
final class ClassesInfoCache {
    static ClassesInfoCache sInstance;
    private final Map<Class<?>, CallbackInfo> mCallbackMap;
    private final Map<Class<?>, Boolean> mHasLifecycleMethods;

    @Deprecated
    public static class CallbackInfo {
        final Map<Lifecycle.Event, List<MethodReference>> mEventToHandlers;
        final Map<MethodReference, Lifecycle.Event> mHandlerToEvent;

        public CallbackInfo(Map<MethodReference, Lifecycle.Event> r5) {
            this.mHandlerToEvent = r5;
            this.mEventToHandlers = new HashMap();
            Iterator<Map.Entry<MethodReference, Lifecycle.Event>> r52 = r5.entrySet().iterator();
        L4:
            if (r52.hasNext() == false) goto L9;
            Map.Entry<MethodReference, Lifecycle.Event> r0 = r52.next();
            Lifecycle.Event r1 = r0.getValue();
            List<MethodReference> r2 = this.mEventToHandlers.get(r1);
            if (r2 != null) goto L8;
            r2 = new ArrayList();
            this.mEventToHandlers.put(r1, r2);
        L8:
            r2.add(r0.getKey());
            goto L4
        }

        private static void invokeMethodsForEvent(List<MethodReference> r2, LifecycleOwner r3, Lifecycle.Event r4, Object r5) {
            if (r2 == null) goto L6;
            int r0 = r2.size() - 1;
        L4:
            if (r0 < 0) goto L8;
            r2.get(r0).invokeCallback(r3, r4, r5);
            r0 = r0 - 1;
            goto L4
        L8:
            return;
        }

        public void invokeCallbacks(LifecycleOwner r2, Lifecycle.Event r3, Object r4) {
            invokeMethodsForEvent(this.mEventToHandlers.get(r3), r2, r3, r4);
            invokeMethodsForEvent(this.mEventToHandlers.get(Lifecycle.Event.ON_ANY), r2, r3, r4);
        }
    }

    @Deprecated
    public static final class MethodReference {
        final int mCallType;
        final Method mMethod;

        public MethodReference(int r1, Method r2) {
            this.mCallType = r1;
            this.mMethod = r2;
            r2.setAccessible(true);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof MethodReference) == true) goto L8;
            return false;
        L8:
            MethodReference r52 = (MethodReference) r5;
            if (this.mCallType == r52.mCallType) goto L11;
        L13:
            return false;
        L11:
            if (this.mMethod.getName().equals(r52.mMethod.getName()) == false) goto L13;
            return true;
        }

        public int hashCode() {
            return (this.mCallType * 31) + this.mMethod.getName().hashCode();
        }

        public void invokeCallback(LifecycleOwner r3, Lifecycle.Event r4, Object r5) {
            int r0 = this.mCallType;     // Catch: IllegalAccessException -> L15 InvocationTargetException -> L18
            if (r0 != 0) goto L5;
            this.mMethod.invoke(r5, null);     // Catch: IllegalAccessException -> L15 InvocationTargetException -> L18
            return;
        L5:
            if (r0 != 1) goto L7;
            this.mMethod.invoke(r5, new Object[]{r3});     // Catch: IllegalAccessException -> L15 InvocationTargetException -> L18
            return;
        L7:
            if (r0 == 2) goto L9;
            return;
        L9:
            this.mMethod.invoke(r5, new Object[]{r3, r4});     // Catch: IllegalAccessException -> L15 InvocationTargetException -> L18
            return;
        L15:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            return;
        L18:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Failed to call observer method", e.getCause());
        }
    }

    static {
        sInstance = new ClassesInfoCache();
    }

    public ClassesInfoCache() {
        this.mCallbackMap = new HashMap();
        this.mHasLifecycleMethods = new HashMap();
    }

    private CallbackInfo createInfo(Class<?> r13, Method[] r14) {
        Class<? super Object> r0 = r13.getSuperclass();
        HashMap r1 = new HashMap();
        if (r0 == null) goto L7;
        CallbackInfo r02 = getInfo(r0);
        if (r02 == null) goto L7;
        r1.putAll(r02.mHandlerToEvent);
    L7:
        Class<?>[] r03 = r13.getInterfaces();
        int r2 = r03.length;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L14;
        Iterator<Map.Entry<MethodReference, Lifecycle.Event>> r5 = getInfo(r03[r4]).mHandlerToEvent.entrySet().iterator();
    L11:
        if (r5.hasNext() == false) goto L13;
        Map.Entry<MethodReference, Lifecycle.Event> r6 = r5.next();
        verifyAndPutHandler(r1, r6.getKey(), r6.getValue(), r13);
        goto L11
    L13:
        r4 = r4 + 1;
        goto L8
    L14:
        if (r14 != null) goto L17;
        r14 = getDeclaredMethods(r13);
    L17:
        int r04 = r14.length;
        int r22 = 0;
        boolean r42 = false;
    L18:
        if (r22 >= r04) goto L47;
        Method r52 = r14[r22];
        OnLifecycleEvent r62 = (OnLifecycleEvent) r52.getAnnotation(OnLifecycleEvent.class);
        if (r62 == null) goto L44;
        Class<?>[] r43 = r52.getParameterTypes();
        if (r43.length > 0) goto L25;
        int r7 = 0;
    L30:
        Lifecycle.Event r63 = r62.value();
        if (r43.length <= 1) goto L42;
        if (Lifecycle.Event.class.isAssignableFrom(r43[1]) == false) goto L39;
        if (r63 != Lifecycle.Event.ON_ANY) goto L37;
        r7 = 2;
        goto L42
    L37:
        f$$ExternalSyntheticBUOutline1.m("Second arg is supported only for ON_ANY value");
        return null;
    L39:
        f$$ExternalSyntheticBUOutline1.m("invalid parameter type. second arg must be an event");
        return null;
    L42:
        if (r43.length > 2) goto L45;
        verifyAndPutHandler(r1, new MethodReference(r7, r52), r63, r13);
        r42 = true;
        goto L44
    L45:
        f$$ExternalSyntheticBUOutline1.m("cannot have more than 2 params");
        return null;
    L25:
        if (LifecycleOwner.class.isAssignableFrom(r43[0]) == false) goto L27;
        r7 = 1;
        goto L30
    L27:
        f$$ExternalSyntheticBUOutline1.m("invalid parameter type. Must be one and instanceof LifecycleOwner");
        return null;
    L44:
        r22 = r22 + 1;
        goto L18
    L47:
        CallbackInfo r142 = new CallbackInfo(r1);
        this.mCallbackMap.put(r13, r142);
        this.mHasLifecycleMethods.put(r13, Boolean.valueOf(r42));
        return r142;
    }

    private Method[] getDeclaredMethods(Class<?> r2) {
        return r2.getDeclaredMethods();
    L4:
        e = move-exception;
        throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
    }

    private void verifyAndPutHandler(Map<MethodReference, Lifecycle.Event> r3, MethodReference r4, Lifecycle.Event r5, Class<?> r6) {
        Lifecycle.Event r2 = r3.get(r4);
        if (r2 == null) goto L8;
        if (r5 == r2) goto L8;
        throw new IllegalArgumentException("Method " + r4.mMethod.getName() + " in " + r6.getName() + " already declared with different @OnLifecycleEvent value: previous value " + String.valueOf(r2) + ", new value " + String.valueOf(r5));
    L8:
        if (r2 != null) goto L11;
        r3.put(r4, r5);
        return;
    }

    public CallbackInfo getInfo(Class<?> r2) {
        CallbackInfo r0 = this.mCallbackMap.get(r2);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return createInfo(r2, null);
    }

    public boolean hasLifecycleMethods(Class<?> r7) {
        Boolean r0 = this.mHasLifecycleMethods.get(r7);
        if (r0 != null) goto L5;
        Method[] r02 = getDeclaredMethods(r7);
        int r1 = r02.length;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L13;
        if (((OnLifecycleEvent) r02[r3].getAnnotation(OnLifecycleEvent.class)) != null) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        createInfo(r7, r02);
        return true;
    L13:
        this.mHasLifecycleMethods.put(r7, Boolean.FALSE);
        return false;
    L5:
        return r0.booleanValue();
    }
}
