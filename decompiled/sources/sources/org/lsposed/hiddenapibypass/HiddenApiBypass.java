package org.lsposed.hiddenapibypass;

import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import dalvik.system.VMRuntime;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import org.lsposed.hiddenapibypass.Helper;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
public abstract class HiddenApiBypass {
    private static final long artFieldBias = 0;
    private static final long artFieldSize = 0;
    private static final long artMethodBias = 0;
    private static final long artMethodSize = 0;
    private static final long artOffset = 0;
    private static final long classOffset = 0;
    private static final long iFieldOffset = 0;
    private static final long methodOffset = 0;
    private static final long methodsOffset = 0;
    private static final long sFieldOffset = 0;
    private static final Unsafe unsafe = null;

    static {
        Unsafe r1 = (Unsafe) Unsafe.class.getDeclaredMethod("getUnsafe", null).invoke(null, null);     // Catch: ReflectiveOperationException -> L9
        unsafe = r1;     // Catch: ReflectiveOperationException -> L9
        CoreOjClassLoader r2 = new CoreOjClassLoader();     // Catch: ReflectiveOperationException -> L9
        Class<?> r4 = r2.loadClass(CoreOjClassLoader$$ExternalSyntheticApiModelOutline0.m().getName());     // Catch: ReflectiveOperationException -> L9
        Class<?> r5 = r2.loadClass(CoreOjClassLoader$$ExternalSyntheticApiModelOutline1.m().getName());     // Catch: ReflectiveOperationException -> L9
        Class<?> r22 = r2.loadClass(Class.class.getName());     // Catch: ReflectiveOperationException -> L9
        methodOffset = r1.objectFieldOffset(r4.getDeclaredField("artMethod"));     // Catch: ReflectiveOperationException -> L9
        classOffset = r1.objectFieldOffset(r4.getDeclaredField("declaringClass"));     // Catch: ReflectiveOperationException -> L9
        artOffset = r1.objectFieldOffset(r5.getDeclaredField("artFieldOrMethod"));     // Catch: ReflectiveOperationException -> L9
        long r42 = r1.objectFieldOffset(r22.getDeclaredField("fields"));     // Catch: NoSuchFieldException -> L6 ReflectiveOperationException -> L9
        long r6 = r42;
    L7:
        iFieldOffset = r42;     // Catch: ReflectiveOperationException -> L9
        sFieldOffset = r6;     // Catch: ReflectiveOperationException -> L9
        Unsafe r12 = unsafe;     // Catch: ReflectiveOperationException -> L9
        long r62 = r12.objectFieldOffset(r22.getDeclaredField("methods"));     // Catch: ReflectiveOperationException -> L9
        methodsOffset = r62;     // Catch: ReflectiveOperationException -> L9
        Method r23 = Helper.NeverCall.class.getDeclaredMethod("a", null);     // Catch: ReflectiveOperationException -> L9
        Method r3 = Helper.NeverCall.class.getDeclaredMethod("b", null);     // Catch: ReflectiveOperationException -> L9
        r23.setAccessible(true);     // Catch: ReflectiveOperationException -> L9
        r3.setAccessible(true);     // Catch: ReflectiveOperationException -> L9
        MethodHandle r24 = MethodHandles.lookup().unreflect(r23);     // Catch: ReflectiveOperationException -> L9
        MethodHandle r32 = MethodHandles.lookup().unreflect(r3);     // Catch: ReflectiveOperationException -> L9
        long r9 = artOffset;     // Catch: ReflectiveOperationException -> L9
        long r11 = r12.getLong(r24, r9);     // Catch: ReflectiveOperationException -> L9
        long r25 = r12.getLong(r32, r9);     // Catch: ReflectiveOperationException -> L9
        long r63 = r12.getLong(Helper.NeverCall.class, r62);     // Catch: ReflectiveOperationException -> L9
        long r26 = r25 - r11;     // Catch: ReflectiveOperationException -> L9
        artMethodSize = r26;     // Catch: ReflectiveOperationException -> L9
        artMethodBias = (r11 - r63) - r26;     // Catch: ReflectiveOperationException -> L9
        Field r27 = Helper.NeverCall.class.getDeclaredField("i");     // Catch: ReflectiveOperationException -> L9
        Field r33 = Helper.NeverCall.class.getDeclaredField("j");     // Catch: ReflectiveOperationException -> L9
        r27.setAccessible(true);     // Catch: ReflectiveOperationException -> L9
        r33.setAccessible(true);     // Catch: ReflectiveOperationException -> L9
        MethodHandle r28 = MethodHandles.lookup().unreflectGetter(r27);     // Catch: ReflectiveOperationException -> L9
        MethodHandle r34 = MethodHandles.lookup().unreflectGetter(r33);     // Catch: ReflectiveOperationException -> L9
        long r64 = r12.getLong(r28, r9);     // Catch: ReflectiveOperationException -> L9
        long r29 = r12.getLong(r34, r9);     // Catch: ReflectiveOperationException -> L9
        long r0 = r12.getLong(Helper.NeverCall.class, r42);     // Catch: ReflectiveOperationException -> L9
        artFieldSize = r29 - r64;     // Catch: ReflectiveOperationException -> L9
        artFieldBias = r64 - r0;     // Catch: ReflectiveOperationException -> L9
        return;
    L6:
        Unsafe r13 = unsafe;     // Catch: ReflectiveOperationException -> L9
        r42 = r13.objectFieldOffset(r22.getDeclaredField("iFields"));     // Catch: ReflectiveOperationException -> L9
        r6 = r13.objectFieldOffset(r22.getDeclaredField("sFields"));     // Catch: ReflectiveOperationException -> L9
    L9:
        e = move-exception;
        Log.e("HiddenApiBypass", "Initialize error", e);
        throw new ExceptionInInitializerError(e);
    }

    public static boolean addHiddenApiExemptions(String... r1) {
        Set<String> r0 = Helper.signaturePrefixes;
        r0.addAll(Arrays.asList(r1));
        String[] r12 = new String[r0.size()];
        r0.toArray(r12);
        return setHiddenApiExemptions(r12);
    }

    public static Object invoke(Class<?> r10, Object r11, String r12, Object... r13) {
        if (r11 != null) goto L4;
    L8:
        Method r4 = Helper.InvokeStub.class.getDeclaredMethod("invoke", new Class[]{Object[].class});
        r4.setAccessible(true);
        Unsafe r0 = unsafe;
        long r1 = r0.getLong(r10, methodsOffset);
        if (r1 == 0) goto L22;
        int r102 = r0.getInt(r1);
        int r02 = 0;
    L11:
        if (r02 >= r102) goto L20;
        unsafe.putLong(r4, methodOffset, artMethodBias + ((r02 * artMethodSize) + r1));
        if (r12.equals(r4.getName()) == false) goto L18;
        if (Helper.checkArgsForInvokeMethod(r4.getParameterTypes(), r13) == false) goto L18;
        return r4.invoke(r11, r13);
    L18:
        r02 = r02 + 1;
        goto L11
    L20:
        throw new NoSuchMethodException("Cannot find matching method");
    L22:
        throw new NoSuchMethodException("Cannot find matching method");
    L4:
        if (r10.isInstance(r11) == true) goto L8;
        f$$ExternalSyntheticBUOutline1.m("this object is not an instance of the given class");
        return null;
    }

    public static boolean setHiddenApiExemptions(String... r6) {
        invoke(VMRuntime.class, invoke(VMRuntime.class, null, "getRuntime", new Object[0]), "setHiddenApiExemptions", new Object[]{r6});     // Catch: ReflectiveOperationException -> L6
        return true;
    L6:
        e = move-exception;
        Log.w("HiddenApiBypass", "setHiddenApiExemptions", e);
        return false;
    }
}
