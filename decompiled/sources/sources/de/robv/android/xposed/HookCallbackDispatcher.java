package de.robv.android.xposed;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
final class HookCallbackDispatcher {
    private static final Object[] EMPTY_ARGS = null;

    static {
        EMPTY_ARGS = new Object[0];
    }

    private HookCallbackDispatcher() {
    }

    public static Object dispatch(XposedBridge.HookInfo r7, Object[] r8) {
        XC_MethodHook.MethodHookParam r0 = new XC_MethodHook.MethodHookParam();
        r0.method = r7.getMethod();
        if (r7.isStaticHook() == false) goto L5;
        r0.thisObject = null;
        r0.args = r8;
    L9:
        Object[] r82 = r7.getCallbacks().getSnapshot();
        int r1 = r82.length;
        if (r1 == 0) goto L52;
        int r5 = 0;
    L54:
        ((XC_MethodHook) r82[r5]).beforeHookedMethod(r0);     // Catch: Throwable -> L21
        if (r0.returnEarly == false) goto L24;
        ensureValidPrimitiveResult(r7, r0);     // Catch: Throwable -> L21
    L24:
        if (r0.returnEarly == true) goto L25;
    L27:
        r5 = r5 + 1;
        if (r5 < r1) goto L54;
    L30:
        if (r0.returnEarly == false) goto L50;
    L35:
        int r52 = r5 - 1;
    L36:
        Object r12 = r0.getResult();
        Throwable r2 = r0.getThrowable();
        ((XC_MethodHook) r82[r52]).afterHookedMethod(r0);     // Catch: Throwable -> L39
        ensureValidPrimitiveResult(r7, r0);     // Catch: Throwable -> L39
    L44:
        r52 = r52 - 1;
        if (r52 >= 0) goto L36;
        Object r83 = r0.getResultOrThrowable();
        if (r7.getReturnType() != null) goto L49;
        return r83;
    L49:
        return r7.getReturnType().cast(r83);
    L39:
        th = move-exception;
        XposedBridge.log(th);
        if (r2 != null) goto L43;
        r0.setResult(r12);
        goto L44
    L43:
        r0.setThrowable(r2);
        goto L44
    L50:
        r0.setResult(r7.awaitBackupMethod().invoke(r0.thisObject, r0.args));     // Catch: InvocationTargetException -> L33
    L33:
        e = move-exception;
        r0.setThrowable(e.getCause());
        goto L35
    L25:
        r5 = r5 + 1;
    L21:
        th = move-exception;
        XposedBridge.log(th);
        r0.setResult(null);
        r0.returnEarly = false;
        goto L27
    L52:
        return r7.awaitBackupMethod().invoke(r0.thisObject, r0.args);
    L13:
        e = move-exception;
        throw e.getCause();
    L5:
        r0.thisObject = r8[0];
        int r13 = r8.length - 1;
        if (r13 != 0) goto L8;
        r0.args = EMPTY_ARGS;
        goto L9
    L8:
        Object[] r53 = new Object[r13];
        r0.args = r53;
        System.arraycopy(r8, 1, r53, 0, r13);
        goto L9
    }

    private static void ensureValidPrimitiveResult(XposedBridge.HookInfo r4, XC_MethodHook.MethodHookParam r5) {
        Class<?> r42 = r4.getBoxedPrimitiveReturnType();
        Object r0 = r5.getResult();
        if (r42 != null) goto L5;
        return;
    L5:
        if (r5.hasThrowable() == false) goto L7;
        return;
    L7:
        if (r42.isInstance(r0) == true) goto L16;
        StringBuilder r2 = new StringBuilder("Hook returned ");
        if (r0 != null) goto L11;
        String r02 = "null";
    L12:
        r2.append(r02);
        r2.append(" for primitive method ");
        r2.append(r5.method);
        r2.append("; expected ");
        r2.append(r42.getName());
        throw new IllegalStateException(r2.toString());
    L11:
        r02 = r0.getClass().getName();
        goto L12
    }
}
