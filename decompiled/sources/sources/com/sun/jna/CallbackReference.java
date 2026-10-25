package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.internal.Cleaner;
import com.sun.jna.win32.DLLCallback;
import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class CallbackReference extends WeakReference<Callback> implements Closeable {
    private static final Class<?> DLL_CALLBACK_CLASS = null;
    private static final Method PROXY_CALLBACK_METHOD = null;
    private static final Map<Long, Reference<CallbackReference>> allocatedMemory = null;
    static final Map<Object, Object> allocations = null;
    static final Map<Callback, CallbackReference> callbackMap = null;
    static final Map<Callback, CallbackReference> directCallbackMap = null;
    private static final Map<Callback, CallbackThreadInitializer> initializers = null;
    static final Map<Pointer, Reference<Callback>[]> pointerCallbackMap = null;
    int callingConvention;
    Pointer cbstruct;
    Cleaner.Cleanable cleanable;
    Method method;
    CallbackProxy proxy;
    Pointer trampoline;

    public static class AttachOptions extends Structure {
        public static final List<String> FIELDS = null;
        public boolean daemon;
        public boolean detach;
        public String name;

        static {
            FIELDS = Structure.createFieldsOrder(new String[]{"daemon", "detach", "name"});
        }

        public AttachOptions() {
            setStringEncoding("utf8");
        }

        @Override // com.sun.jna.Structure
        public List<String> getFieldOrder() {
            return FIELDS;
        }
    }

    public static final class CallbackReferenceDisposer implements Runnable {
        private Pointer cbstruct;

        public CallbackReferenceDisposer(Pointer r1) {
            this.cbstruct = r1;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            monitor-enter(this);
            Pointer r0 = this.cbstruct;     // Catch: Throwable -> L8
            if (r0 != null) goto L18;
        L13:
            monitor-exit(this);
            return;
        L18:
            Native.freeNativeCallback(r0.peer);     // Catch: Throwable -> L10
            CallbackReference.access$200().remove(Long.valueOf(this.cbstruct.peer));     // Catch: Throwable -> L8
            this.cbstruct.peer = 0;     // Catch: Throwable -> L8
            this.cbstruct = null;     // Catch: Throwable -> L8
            goto L13
        L10:
            th = move-exception;
            CallbackReference.access$200().remove(Long.valueOf(this.cbstruct.peer));     // Catch: Throwable -> L8
            this.cbstruct.peer = 0;     // Catch: Throwable -> L8
            this.cbstruct = null;     // Catch: Throwable -> L8
            throw th;     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            throw th;
        }
    }

    public class DefaultCallbackProxy implements CallbackProxy {
        private final Method callbackMethod;
        private final String encoding;
        private final FromNativeConverter[] fromNative;
        final /* synthetic */ CallbackReference this$0;
        private ToNativeConverter toNative;

        public DefaultCallbackProxy(CallbackReference r5, Method r6, TypeMapper r7, String r8) {
            this.this$0 = r5;
            this.callbackMethod = r6;
            this.encoding = r8;
            Class<?>[] r52 = r6.getParameterTypes();
            Class<?> r82 = r6.getReturnType();
            this.fromNative = new FromNativeConverter[r52.length];
            if (NativeMapped.class.isAssignableFrom(r82) == false) goto L5;
            this.toNative = NativeMappedConverter.getInstance(r82);
        L7:
            int r83 = 0;
        L9:
            if (r83 >= this.fromNative.length) goto L17;
            if (NativeMapped.class.isAssignableFrom(r52[r83]) == false) goto L13;
            this.fromNative[r83] = new NativeMappedConverter(r52[r83]);
        L15:
            r83 = r83 + 1;
            goto L9
        L13:
            if (r7 == null) goto L15;
            this.fromNative[r83] = r7.getFromNativeConverter(r52[r83]);
            goto L15
        L17:
            if (r6.isAccessible() == false) goto L24;
            return;
        L24:
            r6.setAccessible(true);     // Catch: SecurityException -> L21
            return;
        L21:
            Native$$ExternalSyntheticBUOutline5.m("Callback method is inaccessible, make sure the interface is public: ", r6);
            throw null;
        L5:
            if (r7 == null) goto L7;
            this.toNative = r7.getToNativeConverter(r82);
            goto L7
        }

        private Object convertArgument(Object r7, Class<?> r8) {
            if ((r7 instanceof Pointer) == false) goto L33;
            if (r8 != String.class) goto L9;
            return ((Pointer) r7).getString(0, this.encoding);
        L9:
            if (r8 != WString.class) goto L13;
            return new WString(((Pointer) r7).getWideString(0));
        L13:
            if (r8 != String[].class) goto L17;
            return ((Pointer) r7).getStringArray(0, this.encoding);
        L17:
            if (r8 != WString[].class) goto L21;
            return ((Pointer) r7).getWideStringArray(0);
        L21:
            if (Callback.class.isAssignableFrom(r8) == false) goto L25;
            return CallbackReference.getCallback(r8, (Pointer) r7);
        L25:
            if (Structure.class.isAssignableFrom(r8) == true) goto L27;
        L44:
            return r7;
        L27:
            if (Structure.ByValue.class.isAssignableFrom(r8) == false) goto L30;
            Structure r6 = Structure.newInstance(r8);
            int r5 = r6.size();
            byte[] r3 = new byte[r5];
            ((Pointer) r7).read(0, r3, 0, r5);
            r6.getPointer().write(0, r3, 0, r5);
            r6.read();
            return r6;
        L30:
            Structure r62 = Structure.newInstance(r8, (Pointer) r7);
            r62.conditionalAutoRead();
            return r62;
        L33:
            if (Boolean.TYPE == r8) goto L37;
            if (Boolean.class != r8) goto L44;
        L37:
            if ((r7 instanceof Number) == false) goto L44;
            if (((Number) r7).intValue() == 0) goto L41;
            boolean r63 = true;
        L43:
            return Function.valueOf(r63);
        L41:
            r63 = false;
            goto L43
        }

        private Object convertResult(Object r4) {
            ToNativeConverter r0 = this.toNative;
            if (r0 == null) goto L5;
            r4 = r0.toNative(r4, new CallbackResultContext(this.callbackMethod));
        L5:
            if (r4 != null) goto L8;
            return null;
        L8:
            Class<?> r02 = r4.getClass();
            if (Structure.class.isAssignableFrom(r02) == false) goto L16;
            if (Structure.ByValue.class.isAssignableFrom(r02) == false) goto L14;
        L33:
            return r4;
        L14:
            return ((Structure) r4).getPointer();
        L16:
            if (r02 == Boolean.TYPE) goto L45;
            if (r02 == Boolean.class) goto L45;
            if (r02 == String.class) goto L39;
            if (r02 == WString.class) goto L39;
            if (r02 != String[].class) goto L27;
        L34:
            if (r02 != String[].class) goto L36;
            StringArray r03 = new StringArray((String[]) r4, this.encoding);
        L37:
            CallbackReference.allocations.put(r4, r03);
            return r03;
        L36:
            r03 = new StringArray((WString[]) r4);
            goto L37
        L27:
            if (r02 == WString[].class) goto L34;
            if (Callback.class.isAssignableFrom(r02) == false) goto L33;
            return CallbackReference.getFunctionPointer((Callback) r4);
        L39:
            if (r02 != WString.class) goto L41;
            boolean r3 = true;
        L43:
            return CallbackReference.access$100(r4, r3);
        L41:
            r3 = false;
        L45:
            if (Boolean.TRUE.equals(r4) == false) goto L49;
            return Function.INTEGER_TRUE;
        L49:
            return Function.INTEGER_FALSE;
        }

        private Object invokeCallback(Object[] r10) {
            Class<?>[] r0 = this.callbackMethod.getParameterTypes();
            int r1 = r10.length;
            Object[] r2 = new Object[r1];
            int r3 = 0;
            int r4 = 0;
        L4:
            if (r4 >= r10.length) goto L10;
            Class<?> r5 = r0[r4];
            Object r6 = r10[r4];
            if (this.fromNative[r4] == null) goto L8;
            r2[r4] = this.fromNative[r4].fromNative(r6, new CallbackParameterContext(r5, this.callbackMethod, r10, r4));
        L9:
            r4 = r4 + 1;
            goto L4
        L8:
            r2[r4] = convertArgument(r6, r5);
            goto L9
        L10:
            Callback r102 = getCallback();
            if (r102 != null) goto L31;
        L22:
            Object r9 = null;
        L23:
            if (r3 >= r1) goto L30;
            Object r103 = r2[r3];
            if ((r103 instanceof Structure) == false) goto L29;
            if ((r103 instanceof Structure.ByValue) == true) goto L29;
            ((Structure) r103).autoWrite();
        L29:
            r3 = r3 + 1;
            goto L23
        L30:
            return r9;
        L31:
            r9 = convertResult(this.callbackMethod.invoke(r102, r2));     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 IllegalArgumentException -> L18
        L16:
            e = e;
        L21:
            Native.getCallbackExceptionHandler().uncaughtException(r102, e);
        L18:
            e = e;
        L14:
            e = move-exception;
            Native.getCallbackExceptionHandler().uncaughtException(r102, e.getTargetException());
            goto L22
        }

        @Override // com.sun.jna.CallbackProxy
        public Object callback(Object[] r2) {
            return invokeCallback(r2);
        L4:
            th = move-exception;
            Native.getCallbackExceptionHandler().uncaughtException(this.getCallback(), th);
            return null;
        }

        public Callback getCallback() {
            return CallbackReference.access$000(this.this$0);
        }

        @Override // com.sun.jna.CallbackProxy
        public Class<?>[] getParameterTypes() {
            return this.callbackMethod.getParameterTypes();
        }

        @Override // com.sun.jna.CallbackProxy
        public Class<?> getReturnType() {
            return this.callbackMethod.getReturnType();
        }
    }

    public static class NativeFunctionHandler implements InvocationHandler {
        private final Function function;
        private final Map<String, ?> options;

        public NativeFunctionHandler(Pointer r3, int r4, Map<String, ?> r5) {
            this.options = r5;
            this.function = new Function(r3, r4, (String) r5.get(Library.OPTION_STRING_ENCODING));
        }

        public Pointer getPointer() {
            return this.function;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object r1, Method r2, Object[] r3) {
            if (Library.Handler.OBJECT_TOSTRING.equals(r2) == false) goto L7;
            return ("Proxy interface to " + this.function) + " (" + CallbackReference.findCallbackClass(((Method) this.options.get("invoking-method")).getDeclaringClass()).getName() + ")";
        L7:
            if (Library.Handler.OBJECT_HASHCODE.equals(r2) == false) goto L11;
            return Integer.valueOf(hashCode());
        L11:
            if (Library.Handler.OBJECT_EQUALS.equals(r2) == false) goto L24;
            boolean r12 = false;
            Object r22 = r3[0];
            if (r22 == null) goto L22;
            if (Proxy.isProxyClass(r22.getClass()) == false) goto L22;
            if (Proxy.getInvocationHandler(r22) != this) goto L20;
            r12 = true;
        L20:
            return Function.valueOf(r12);
        L22:
            return Boolean.FALSE;
        L24:
            if (Function.isVarArgs(r2) == false) goto L27;
            r3 = Function.concatenateVarArgs(r3);
        L27:
            return this.function.invoke(r2.getReturnType(), r3, this.options);
        }
    }

    static {
        callbackMap = new WeakHashMap();
        directCallbackMap = new WeakHashMap();
        pointerCallbackMap = new WeakHashMap();
        allocations = Collections.synchronizedMap(new WeakHashMap());
        allocatedMemory = new ConcurrentHashMap();
        PROXY_CALLBACK_METHOD = CallbackProxy.class.getMethod(Callback.METHOD_NAME, new Class[]{Object[].class});     // Catch: Exception -> L14
        if (Platform.isWindows() == true) goto L16;
        DLL_CALLBACK_CLASS = null;
    L12:
        initializers = new WeakHashMap();
        return;
    L16:
        DLL_CALLBACK_CLASS = DLLCallback.class;     // Catch: ClassNotFoundException -> L8
    L8:
        e = move-exception;
        throw new Error("Error loading DLLCallback class", e);
    L15:
        throw new Error("Error looking up CallbackProxy.callback() method");
    }

    private CallbackReference(Callback r11, int r12, boolean r13) {
        super(r11);
        TypeMapper r0 = Native.getTypeMapper(r11.getClass());
        this.callingConvention = r12;
        boolean r1 = Platform.isPPC();
        int r2 = 0;
        if (r13 == false) goto L22;
        Method r3 = getCallbackMethod(r11);
        Class<?>[] r4 = r3.getParameterTypes();
        int r5 = 0;
    L6:
        if (r5 >= r4.length) goto L18;
        if (r1 == false) goto L13;
        Class<?> r6 = r4[r5];
        if (r6 == Float.TYPE) goto L12;
        if (r6 != Double.TYPE) goto L13;
    L12:
        r13 = false;
    L13:
        if (r0 == null) goto L17;
        if (r0.getFromNativeConverter(r4[r5]) != null) goto L12;
    L17:
        r5 = r5 + 1;
    L18:
        if (r0 == null) goto L22;
        if (r0.getToNativeConverter(r3.getReturnType()) == null) goto L22;
        r13 = false;
    L22:
        String r9 = Native.getStringEncoding(r11.getClass());
        Pointer r14 = null;
        if (r13 == false) goto L33;
        Method r132 = getCallbackMethod(r11);
        this.method = r132;
        Class<?>[] r52 = r132.getParameterTypes();
        Class<?> r62 = this.method.getReturnType();
        Class<?> r133 = DLL_CALLBACK_CLASS;
        if (r133 != null) goto L27;
    L30:
        int r134 = 1;
    L31:
        long r112 = Native.createNativeCallback(r11, this.method, r52, r62, r12, r134, r9);
    L65:
        if (r112 == 0) goto L67;
        r14 = new Pointer(r112);
    L67:
        this.cbstruct = r14;
        if (r112 == 0) goto L84;
        allocatedMemory.put(Long.valueOf(r112), new WeakReference(this));
        this.cleanable = Cleaner.getCleaner().register(this, new CallbackReferenceDisposer(this.cbstruct));
        return;
    L84:
        return;
    L27:
        if (r133.isInstance(r11) == false) goto L30;
        r134 = 3;
        goto L31
    L33:
        if ((r11 instanceof CallbackProxy) == false) goto L35;
        this.proxy = (CallbackProxy) r11;
    L36:
        Class<?>[] r53 = this.proxy.getParameterTypes();
        Class<?> r113 = this.proxy.getReturnType();
        if (r0 == null) goto L48;
        int r122 = 0;
    L40:
        if (r122 >= r53.length) goto L45;
        FromNativeConverter r135 = r0.getFromNativeConverter(r53[r122]);
        if (r135 == null) goto L44;
        r53[r122] = r135.nativeType();
    L44:
        r122 = r122 + 1;
        goto L40
    L45:
        ToNativeConverter r123 = r0.getToNativeConverter(r113);
        if (r123 == null) goto L48;
        r113 = r123.nativeType();
    L48:
        int r124 = 0;
    L50:
        if (r124 >= r53.length) goto L56;
        Class<?> r136 = getNativeType(r53[r124]);
        r53[r124] = r136;
        if (isAllowableNativeType(r136) == false) goto L54;
        r124 = r124 + 1;
        goto L50
    L54:
        Native$$ExternalSyntheticBUOutline2.m("Callback argument ", r53[r124], " requires custom type conversion");
        throw null;
    L56:
        Class<?> r63 = getNativeType(r113);
        if (isAllowableNativeType(r63) == false) goto L71;
        Class<?> r114 = DLL_CALLBACK_CLASS;
        if (r114 != null) goto L61;
    L63:
        r112 = Native.createNativeCallback(this.proxy, PROXY_CALLBACK_METHOD, r53, r63, r12, r2, r9);
        goto L65
    L61:
        if (r114.isInstance(r11) == false) goto L63;
        r2 = 2;
        goto L63
    L71:
        Native$$ExternalSyntheticBUOutline2.m("Callback return type ", r63, " requires custom type conversion");
        throw null;
    L35:
        this.proxy = new DefaultCallbackProxy(this, getCallbackMethod(r11), r0, r9);
        goto L36
    }

    public static /* synthetic */ Callback access$000(CallbackReference r0) {
        return r0.getCallback();
    }

    public static /* synthetic */ Pointer access$100(Object r0, boolean r1) {
        return getNativeString(r0, r1);
    }

    public static /* synthetic */ Map access$200() {
        return allocatedMemory;
    }

    private static Reference<Callback>[] addCallbackToArray(Callback r5, Reference<Callback>[] r6) {
        int r0 = 0;
        int r1 = 1;
        if (r6 == null) goto L12;
        int r2 = 0;
    L6:
        if (r2 >= r6.length) goto L12;
        if (r6[r2].get() != null) goto L10;
        r6[r2] = null;
    L11:
        r2 = r2 + 1;
        goto L6
    L10:
        r1 = r1 + 1;
    L12:
        Reference<Callback>[] r12 = new Reference[r1];
        if (r6 == null) goto L22;
        int r22 = 0;
    L16:
        if (r0 >= r6.length) goto L21;
        Reference<Callback> r3 = r6[r0];
        if (r3 == null) goto L20;
        r12[r22] = r3;
        r22 = r22 + 1;
    L20:
        r0 = r0 + 1;
        goto L16
    L21:
        r0 = r22;
    L22:
        r12[r0] = new WeakReference(r5);
        return r12;
    }

    private static Method checkMethod(Method r2) {
        if (r2.getParameterTypes().length > 256) goto L6;
        return r2;
    L6:
        throw new UnsupportedOperationException("Method signature exceeds the maximum parameter count: " + r2);
    }

    private static Callback createCallback(Class<?> r4, Pointer r5) {
        if (AltCallingConvention.class.isAssignableFrom(r4) == false) goto L5;
        int r0 = 63;
    L6:
        HashMap r1 = new HashMap(Native.getLibraryOptions(r4));
        r1.put("invoking-method", getCallbackMethod(r4));
        NativeFunctionHandler r2 = new NativeFunctionHandler(r5, r0, r1);
        return (Callback) Proxy.newProxyInstance(r4.getClassLoader(), new Class[]{r4}, r2);
    L5:
        r0 = 0;
        goto L6
    }

    public static void disposeAll() {
        Iterator r0 = new LinkedList(allocatedMemory.values()).iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        CallbackReference r1 = (CallbackReference) ((Reference) r0.next()).get();
        if (r1 == null) goto L4;
        r1.close();
        goto L4
    }

    public static Class<?> findCallbackClass(Class<?> r4) {
        if (Callback.class.isAssignableFrom(r4) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m(r4.getName().concat(" is not derived from com.sun.jna.Callback"));
        return null;
    L5:
        if (r4.isInterface() == true) goto L26;
        Class<?>[] r1 = r4.getInterfaces();
        int r2 = 0;
    L9:
        if (r2 >= r1.length) goto L16;
        if (Callback.class.isAssignableFrom(r1[r2]) == true) goto L22;
        r2 = r2 + 1;
        goto L9
    L22:
        getCallbackMethod(r1[r2]);     // Catch: IllegalArgumentException -> L21
        return r1[r2];
    L16:
        if (Callback.class.isAssignableFrom(r4.getSuperclass()) == true) goto L18;
        return r4;
    L18:
        return findCallbackClass(r4.getSuperclass());
    L26:
        return r4;
    }

    private Callback getCallback() {
        return get();
    }

    public static Callback getCallback(Class<?> r1, Pointer r2) {
        return getCallback(r1, r2, false);
    }

    private static Callback getCallback(Class<?> r3, Pointer r4, boolean r5) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (r3.isInterface() == false) goto L23;
        if (r5 == false) goto L9;
        Map<Callback, CallbackReference> r52 = directCallbackMap;
    L10:
        Map<Pointer, Reference<Callback>[]> r1 = pointerCallbackMap;
        monitor-enter(r1);
        Reference<Callback>[] r0 = r1.get(r4);     // Catch: Throwable -> L16
        Callback r2 = getTypeAssignableCallback(r3, r0);     // Catch: Throwable -> L16
        if (r2 == null) goto L18;
        monitor-exit(r1);     // Catch: Throwable -> L16
        return r2;
    L18:
        Callback r32 = createCallback(r3, r4);     // Catch: Throwable -> L16
        r1.put(r4, addCallbackToArray(r32, r0));     // Catch: Throwable -> L16
        r52.remove(r32);     // Catch: Throwable -> L16
        monitor-exit(r1);     // Catch: Throwable -> L16
        return r32;
    L16:
        th = move-exception;
        throw th;
    L9:
        r52 = callbackMap;
        goto L10
    L23:
        f$$ExternalSyntheticBUOutline1.m("Callback type must be an interface");
        return null;
    }

    private static Method getCallbackMethod(Callback r0) {
        return getCallbackMethod(findCallbackClass(r0.getClass()));
    }

    private static Method getCallbackMethod(Class<?> r4) {
        Method[] r0 = r4.getDeclaredMethods();
        Method[] r42 = r4.getMethods();
        HashSet r1 = new HashSet(Arrays.asList(r0));
        r1.retainAll(Arrays.asList(r42));
        Iterator r43 = r1.iterator();
    L4:
        if (r43.hasNext() == false) goto L8;
        Method r02 = (Method) r43.next();
        if (Callback.FORBIDDEN_NAMES.contains(r02.getName()) == false) goto L4;
        r43.remove();
        goto L4
    L8:
        int r44 = 0;
        Method[] r03 = (Method[]) r1.toArray(new Method[0]);
        if (r03.length != 1) goto L13;
        return checkMethod(r03[0]);
    L13:
        if (r44 >= r03.length) goto L19;
        Method r12 = r03[r44];
        if (Callback.METHOD_NAME.equals(r12.getName()) == true) goto L17;
        r44 = r44 + 1;
        goto L13
    L17:
        return checkMethod(r12);
    L19:
        f$$ExternalSyntheticBUOutline1.m("Callback must implement a single public method, or one public method named 'callback'");
        return null;
    }

    public static Pointer getFunctionPointer(Callback r1) {
        return getFunctionPointer(r1, false);
    }

    private static Pointer getFunctionPointer(Callback r6, boolean r7) {
        if (r6 != null) goto L5;
        return null;
    L5:
        Pointer r1 = getNativeFunctionPointer(r6);
        if (r1 == null) goto L8;
        return r1;
    L8:
        Map<String, Object> r12 = Native.getLibraryOptions(r6.getClass());
        if ((r6 instanceof AltCallingConvention) == false) goto L11;
        int r13 = 63;
    L16:
        if (r7 == false) goto L18;
        Map<Callback, CallbackReference> r2 = directCallbackMap;
    L19:
        Map<Pointer, Reference<Callback>[]> r3 = pointerCallbackMap;
        monitor-enter(r3);
        CallbackReference r4 = r2.get(r6);     // Catch: Throwable -> L26
        if (r4 != null) goto L24;
    L28:
        r4 = new CallbackReference(r6, r13, r7);     // Catch: Throwable -> L26
        r2.put(r6, r4);     // Catch: Throwable -> L26
        r3.put(r4.getTrampoline(), addCallbackToArray(r6, null));     // Catch: Throwable -> L26
        if (initializers.containsKey(r6) == false) goto L31;
        r4.setCallbackOptions(1);     // Catch: Throwable -> L26
    L31:
        Pointer r62 = r4.getTrampoline();     // Catch: Throwable -> L26
        monitor-exit(r3);     // Catch: Throwable -> L26
        return r62;
    L24:
        if (r4.cbstruct != null) goto L31;
    L26:
        th = move-exception;
        throw th;
    L18:
        r2 = callbackMap;
        goto L19
    L11:
        if (r12 != null) goto L13;
    L15:
        r13 = 0;
        goto L16
    L13:
        if (r12.containsKey(Library.OPTION_CALLING_CONVENTION) == false) goto L15;
        r13 = ((Integer) r12.get(Library.OPTION_CALLING_CONVENTION)).intValue();
        goto L16
    }

    private static Pointer getNativeFunctionPointer(Callback r1) {
        if (Proxy.isProxyClass(r1.getClass()) == false) goto L8;
        InvocationHandler r12 = Proxy.getInvocationHandler(r1);
        if ((r12 instanceof NativeFunctionHandler) == true) goto L7;
        return null;
    L7:
        return ((NativeFunctionHandler) r12).getPointer();
    L8:
        return null;
    }

    private static Pointer getNativeString(Object r2, boolean r3) {
        if (r2 == null) goto L5;
        NativeString r0 = new NativeString(r2.toString(), r3);
        allocations.put(r2, r0);
        return r0.getPointer();
    L5:
        return null;
    }

    private Class<?> getNativeType(Class<?> r2) {
        if (Structure.class.isAssignableFrom(r2) == false) goto L8;
        Structure.validate(r2);
        if (Structure.ByValue.class.isAssignableFrom(r2) == true) goto L22;
        return Pointer.class;
    L22:
        return r2;
    L8:
        if (NativeMapped.class.isAssignableFrom(r2) == false) goto L12;
        return NativeMappedConverter.getInstance(r2).nativeType();
    L12:
        if (r2 != String.class) goto L14;
    L23:
        return Pointer.class;
    L14:
        if (r2 == WString.class) goto L23;
        if (r2 == String[].class) goto L23;
        if (r2 == WString[].class) goto L23;
        if (Callback.class.isAssignableFrom(r2) == false) goto L22;
        goto L22
    }

    private static Callback getTypeAssignableCallback(Class<?> r3, Reference<Callback>[] r4) {
        if (r4 == null) goto L12;
        int r0 = 0;
    L5:
        if (r0 >= r4.length) goto L18;
        Callback r1 = r4[r0].get();
        if (r1 == null) goto L11;
        if (r3.isAssignableFrom(r1.getClass()) == false) goto L11;
        return r1;
    L11:
        r0 = r0 + 1;
        goto L5
    L18:
        return null;
    L12:
        return null;
    }

    private static ThreadGroup initializeThread(Callback r3, AttachOptions r4) {
        if ((r3 instanceof DefaultCallbackProxy) == false) goto L5;
        r3 = ((DefaultCallbackProxy) r3).getCallback();
    L5:
        Map<Callback, CallbackThreadInitializer> r0 = initializers;
        monitor-enter(r0);
        CallbackThreadInitializer r1 = r0.get(r3);     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r1 == null) goto L12;
        ThreadGroup r02 = r1.getThreadGroup(r3);
        r4.name = r1.getName(r3);
        r4.daemon = r1.isDaemon(r3);
        r4.detach = r1.detach(r3);
        r4.write();
        return r02;
    L12:
        return null;
    L14:
        th = move-exception;
        throw th;
    }

    private static boolean isAllowableNativeType(Class<?> r1) {
        if (r1 != Void.TYPE) goto L5;
        return true;
    L5:
        if (r1 != Void.class) goto L7;
        return true;
    L7:
        if (r1 != Boolean.TYPE) goto L9;
        return true;
    L9:
        if (r1 != Boolean.class) goto L11;
        return true;
    L11:
        if (r1 != Byte.TYPE) goto L13;
        return true;
    L13:
        if (r1 != Byte.class) goto L15;
        return true;
    L15:
        if (r1 != Short.TYPE) goto L17;
        return true;
    L17:
        if (r1 != Short.class) goto L19;
        return true;
    L19:
        if (r1 != Character.TYPE) goto L21;
        return true;
    L21:
        if (r1 != Character.class) goto L23;
        return true;
    L23:
        if (r1 != Integer.TYPE) goto L25;
        return true;
    L25:
        if (r1 != Integer.class) goto L27;
        return true;
    L27:
        if (r1 != Long.TYPE) goto L29;
        return true;
    L29:
        if (r1 != Long.class) goto L31;
        return true;
    L31:
        if (r1 != Float.TYPE) goto L33;
        return true;
    L33:
        if (r1 != Float.class) goto L35;
        return true;
    L35:
        if (r1 != Double.TYPE) goto L37;
        return true;
    L37:
        if (r1 != Double.class) goto L39;
        return true;
    L39:
        if (Structure.ByValue.class.isAssignableFrom(r1) == false) goto L43;
        if (Structure.class.isAssignableFrom(r1) == false) goto L43;
        return true;
    L43:
        if (Pointer.class.isAssignableFrom(r1) == true) goto L67;
        return false;
    L67:
        return true;
    }

    private void setCallbackOptions(int r3) {
        this.cbstruct.setInt(Native.POINTER_SIZE, r3);
    }

    public static CallbackThreadInitializer setCallbackThreadInitializer(Callback r1, CallbackThreadInitializer r2) {
        Map<Callback, CallbackThreadInitializer> r0 = initializers;
        monitor-enter(r0);
        if (r2 == null) goto L10;
        CallbackThreadInitializer r12 = r0.put(r1, r2);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r12;
    L10:
        CallbackThreadInitializer r13 = r0.remove(r1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r13;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Cleaner.Cleanable r0 = this.cleanable;
        if (r0 == null) goto L5;
        r0.clean();
    L5:
        this.cbstruct = null;
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public Pointer getTrampoline() {
        if (this.trampoline != null) goto L6;
        this.trampoline = this.cbstruct.getPointer(0);
    L6:
        return this.trampoline;
    }
}
