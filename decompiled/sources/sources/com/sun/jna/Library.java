package com.sun.jna;

import com.sun.jna.internal.ReflectionUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public interface Library {
    public static final String OPTION_ALLOW_OBJECTS = "allow-objects";
    public static final String OPTION_CALLING_CONVENTION = "calling-convention";
    public static final String OPTION_CLASSLOADER = "classloader";
    public static final String OPTION_FUNCTION_MAPPER = "function-mapper";
    public static final String OPTION_INVOCATION_MAPPER = "invocation-mapper";
    public static final String OPTION_OPEN_FLAGS = "open-flags";
    public static final String OPTION_STRING_ENCODING = "string-encoding";
    public static final String OPTION_STRUCTURE_ALIGNMENT = "structure-alignment";
    public static final String OPTION_SYMBOL_PROVIDER = "symbol-provider";
    public static final String OPTION_TYPE_MAPPER = "type-mapper";

    public static class Handler implements InvocationHandler {
        static final Method OBJECT_EQUALS = null;
        static final Method OBJECT_HASHCODE = null;
        static final Method OBJECT_TOSTRING = null;
        private final Map<Method, FunctionInfo> functions;
        private final Class<?> interfaceClass;
        private final InvocationMapper invocationMapper;
        private final NativeLibrary nativeLibrary;
        private final Map<String, Object> options;

        public static final class FunctionInfo {
            final Function function;
            final InvocationHandler handler;
            final boolean isVarArgs;
            final Object methodHandle;
            final Map<String, ?> options;
            final Class<?>[] parameterTypes;

            public FunctionInfo(Object r3) {
                this.handler = null;
                this.function = null;
                this.isVarArgs = false;
                this.options = null;
                this.parameterTypes = null;
                this.methodHandle = r3;
            }

            public FunctionInfo(InvocationHandler r1, Function r2, Class<?>[] r3, boolean r4, Map<String, ?> r5) {
                this.handler = r1;
                this.function = r2;
                this.isVarArgs = r4;
                this.options = r5;
                this.parameterTypes = r3;
                this.methodHandle = null;
            }
        }

        static {
            OBJECT_TOSTRING = Object.class.getMethod("toString", null);     // Catch: Exception -> L5
            OBJECT_HASHCODE = Object.class.getMethod("hashCode", null);     // Catch: Exception -> L5
            OBJECT_EQUALS = Object.class.getMethod("equals", new Class[]{Object.class});     // Catch: Exception -> L5
            return;
        L6:
            throw new Error("Error retrieving Object.toString() method");
        }

        public Handler(String r4, Class<?> r5, Map<String, ?> r6) {
            this.functions = new WeakHashMap();
            if (r4 == null) goto L10;
            if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r4.trim()) == false) goto L10;
            Native$$ExternalSyntheticBUOutline0.m("Invalid library name \"", r4, "\"");
            throw null;
        L10:
            if (r5.isInterface() == false) goto L23;
            this.interfaceClass = r5;
            HashMap r0 = new HashMap(r6);
            this.options = r0;
            if (AltCallingConvention.class.isAssignableFrom(r5) == false) goto L14;
            int r62 = 63;
        L16:
            if (r0.get(Library.OPTION_CALLING_CONVENTION) != null) goto L19;
            r0.put(Library.OPTION_CALLING_CONVENTION, Integer.valueOf(r62));
        L19:
            if (r0.get(Library.OPTION_CLASSLOADER) != null) goto L21;
            r0.put(Library.OPTION_CLASSLOADER, r5.getClassLoader());
        L21:
            this.nativeLibrary = NativeLibrary.getInstance(r4, r0);
            this.invocationMapper = (InvocationMapper) r0.get(Library.OPTION_INVOCATION_MAPPER);
            return;
        L14:
            r62 = 0;
            goto L16
        L23:
            Library$Handler$$ExternalSyntheticBUOutline0.m(r4, " does not implement an interface: ", r5.getName());
            throw null;
        }

        public Class<?> getInterfaceClass() {
            return this.interfaceClass;
        }

        public String getLibraryName() {
            return this.nativeLibrary.getName();
        }

        public NativeLibrary getNativeLibrary() {
            return this.nativeLibrary;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object r9, Method r10, Object[] r11) {
            if (OBJECT_TOSTRING.equals(r10) == false) goto L7;
            return "Proxy interface to " + this.nativeLibrary;
        L7:
            if (OBJECT_HASHCODE.equals(r10) == false) goto L11;
            return Integer.valueOf(hashCode());
        L11:
            if (OBJECT_EQUALS.equals(r10) == false) goto L23;
            boolean r92 = false;
            Object r102 = r11[0];
            if (r102 == null) goto L22;
            if (Proxy.isProxyClass(r102.getClass()) == false) goto L22;
            if (Proxy.getInvocationHandler(r102) != this) goto L20;
            r92 = true;
        L20:
            return Function.valueOf(r92);
        L22:
            return Boolean.FALSE;
        L23:
            FunctionInfo r0 = this.functions.get(r10);
            if (r0 != null) goto L47;
            Map<Method, FunctionInfo> r1 = this.functions;
            monitor-enter(r1);
            r0 = this.functions.get(r10);     // Catch: Throwable -> L34
            if (r0 == null) goto L30;
        L43:
            monitor-exit(r1);     // Catch: Throwable -> L34
            goto L47
        L30:
            if (ReflectionUtils.isDefault(r10) == true) goto L41;
            boolean r6 = Function.isVarArgs(r10);     // Catch: Throwable -> L34
            InvocationMapper r02 = this.invocationMapper;     // Catch: Throwable -> L34
            if (r02 == null) goto L36;
            InvocationHandler r3 = r02.getInvocationHandler(this.nativeLibrary, r10);     // Catch: Throwable -> L34
        L37:
            if (r3 != null) goto L39;
            Function r2 = this.nativeLibrary.getFunction(r10.getName(), r10);     // Catch: Throwable -> L34
            Class<?>[] r03 = r10.getParameterTypes();     // Catch: Throwable -> L34
            HashMap r4 = new HashMap(this.options);     // Catch: Throwable -> L34
            r4.put("invoking-method", r10);     // Catch: Throwable -> L34
            Class<?>[] r5 = r03;
            HashMap r7 = r4;
            Function r42 = r2;
        L40:
            FunctionInfo r22 = new FunctionInfo(r3, r42, r5, r6, r7);     // Catch: Throwable -> L34
        L42:
            this.functions.put(r10, r22);     // Catch: Throwable -> L34
            r0 = r22;
            goto L43
        L39:
            r42 = null;
            r5 = null;
            r7 = null;
            goto L40
        L36:
            r3 = null;
            goto L37
        L41:
            r22 = new FunctionInfo(ReflectionUtils.getMethodHandle(r10));     // Catch: Throwable -> L34
        L34:
            th = move-exception;
            throw th;
        L47:
            Object r8 = r0.methodHandle;
            if (r8 == null) goto L52;
            return ReflectionUtils.invokeDefaultMethod(r9, r8, r11);
        L52:
            if (r0.isVarArgs == false) goto L54;
            r11 = Function.concatenateVarArgs(r11);
        L54:
            Object[] r52 = r11;
            InvocationHandler r82 = r0.handler;
            if (r82 == null) goto L59;
            return r82.invoke(r9, r10, r52);
        L59:
            return r0.function.invoke(r10, r0.parameterTypes, r10.getReturnType(), r52, r0.options);
        }
    }
}
