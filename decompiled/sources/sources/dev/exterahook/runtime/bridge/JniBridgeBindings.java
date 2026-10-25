package dev.exterahook.runtime.bridge;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class JniBridgeBindings {
    private JniBridgeBindings() {
    }

    public static native Object allocateInstance0(Class<?> r0);

    public static native boolean deoptimize0(Member r0);

    public static boolean disableHiddenApiRestrictions() {
        return disableHiddenApiRestrictions0();
    }

    public static native boolean disableHiddenApiRestrictions0();

    public static native boolean disableProfileSaver0();

    public static native Method hook0(Object r0, Member r1, Method r2);

    public static native boolean initialize0();

    public static native boolean invokeConstructor0(Object r0, Constructor<?> r1, Object[] r2);

    public static native boolean isHooked0(Member r0);

    public static native boolean makeClassInheritable0(Class<?> r0);

    public static native boolean unhook0(Member r0);
}
