package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public final class Native implements Version {
    public static final int BOOL_SIZE = 0;
    static final int CB_HAS_INITIALIZER = 1;
    static final int CB_OPTION_DIRECT = 1;
    static final int CB_OPTION_IN_DLL = 2;
    private static final int CVT_ARRAY_BOOLEAN = 13;
    private static final int CVT_ARRAY_BYTE = 6;
    private static final int CVT_ARRAY_CHAR = 8;
    private static final int CVT_ARRAY_DOUBLE = 12;
    private static final int CVT_ARRAY_FLOAT = 11;
    private static final int CVT_ARRAY_INT = 9;
    private static final int CVT_ARRAY_LONG = 10;
    private static final int CVT_ARRAY_SHORT = 7;
    private static final int CVT_BOOLEAN = 14;
    private static final int CVT_BUFFER = 5;
    private static final int CVT_BYTE = 29;
    private static final int CVT_CALLBACK = 15;
    private static final int CVT_DEFAULT = 0;
    private static final int CVT_FLOAT = 16;
    private static final int CVT_INTEGER_TYPE = 21;
    private static final int CVT_JNIENV = 27;
    private static final int CVT_NATIVE_MAPPED = 17;
    private static final int CVT_NATIVE_MAPPED_STRING = 18;
    private static final int CVT_NATIVE_MAPPED_WSTRING = 19;
    private static final int CVT_OBJECT = 26;
    private static final int CVT_POINTER = 1;
    private static final int CVT_POINTER_TYPE = 22;
    private static final int CVT_SHORT = 28;
    private static final int CVT_STRING = 2;
    private static final int CVT_STRUCTURE = 3;
    private static final int CVT_STRUCTURE_BYVAL = 4;
    private static final int CVT_TYPE_MAPPER = 23;
    private static final int CVT_TYPE_MAPPER_STRING = 24;
    private static final int CVT_TYPE_MAPPER_WSTRING = 25;
    private static final int CVT_UNSUPPORTED = -1;
    private static final int CVT_WSTRING = 20;
    public static final boolean DEBUG_JNA_LOAD = false;
    private static final Level DEBUG_JNA_LOAD_LEVEL = null;
    public static final boolean DEBUG_LOAD = false;
    public static final Charset DEFAULT_CHARSET = null;
    public static final String DEFAULT_ENCODING = null;
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER = null;
    static final String JNA_TMPLIB_PREFIX = "jna";
    private static final Logger LOG = null;
    public static final int LONG_DOUBLE_SIZE = 0;
    public static final int LONG_SIZE = 0;
    static final int MAX_ALIGNMENT = 0;
    static final int MAX_PADDING = 0;
    public static final int POINTER_SIZE = 0;
    public static final int SIZE_T_SIZE = 0;
    private static final int TYPE_BOOL = 4;
    private static final int TYPE_LONG = 1;
    private static final int TYPE_LONG_DOUBLE = 5;
    private static final int TYPE_SIZE_T = 3;
    private static final int TYPE_VOIDP = 0;
    private static final int TYPE_WCHAR_T = 2;
    public static final int WCHAR_SIZE = 0;
    private static final String _OPTION_ENCLOSING_LIBRARY = "enclosing-library";
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    private static final Object finalizer = null;
    static String jnidispatchPath;
    private static final Map<Class<?>, Reference<?>> libraries = null;
    private static final ThreadLocal<Memory> nativeThreadTerminationFlag = null;
    private static final Map<Thread, Pointer> nativeThreads = null;
    private static final Map<Class<?>, long[]> registeredClasses = null;
    private static final Map<Class<?>, NativeLibrary> registeredLibraries = null;
    private static final Map<Class<?>, Map<String, Object>> typeOptions = null;

    /* renamed from: com.sun.jna.Native$1, reason: invalid class name */
    public class AnonymousClass1 implements Callback.UncaughtExceptionHandler {
        public AnonymousClass1() {
        }

        @Override // com.sun.jna.Callback.UncaughtExceptionHandler
        public void uncaughtException(Callback r4, Throwable r5) {
            Native.access$000().log(Level.WARNING, "JNA: Callback " + r4 + " threw the following exception", r5);
        }
    }

    /* renamed from: com.sun.jna.Native$2, reason: invalid class name */
    public class AnonymousClass2 {
        public AnonymousClass2() {
        }

        public void finalize() {
            Native.access$100();
            super.finalize();
        }
    }

    /* renamed from: com.sun.jna.Native$3, reason: invalid class name */
    public class AnonymousClass3 implements InvocationHandler {
        final /* synthetic */ Library.Handler val$handler;
        final /* synthetic */ Library val$library;

        public AnonymousClass3(Library.Handler r1, Library r2) {
            this.val$handler = r1;
            this.val$library = r2;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object r2, Method r3, Object[] r4) {
            NativeLibrary r22 = this.val$handler.getNativeLibrary();
            monitor-enter(r22);
            Object r1 = this.val$handler.invoke(this.val$library, r3, r4);     // Catch: Throwable -> L7
            monitor-exit(r22);     // Catch: Throwable -> L7
            return r1;
        L7:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: com.sun.jna.Native$4, reason: invalid class name */
    public class AnonymousClass4 implements PrivilegedAction<Method> {
        public AnonymousClass4() {
        }

        @Override // java.security.PrivilegedAction
        public /* bridge */ /* synthetic */ Method run() {
            return run2();
        }

        @Override // java.security.PrivilegedAction
        /* renamed from: run, reason: avoid collision after fix types in other method */
        public Method run2() {
            Method r2 = ClassLoader.class.getDeclaredMethod("findLibrary", new Class[]{String.class});     // Catch: Exception -> L4
            r2.setAccessible(true);     // Catch: Exception -> L4
            return r2;
        L4:
            return null;
        }
    }

    /* renamed from: com.sun.jna.Native$5, reason: invalid class name */
    public class AnonymousClass5 implements FilenameFilter {
        public AnonymousClass5() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File r1, String r2) {
            if (r2.endsWith(".x") == true) goto L5;
            return false;
        L5:
            if (r2.startsWith(Native.JNA_TMPLIB_PREFIX) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: com.sun.jna.Native$6, reason: invalid class name */
    public class AnonymousClass6 extends SecurityManager {
        public AnonymousClass6() {
        }

        @Override // java.lang.SecurityManager
        public Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    /* renamed from: com.sun.jna.Native$7, reason: invalid class name */
    public class AnonymousClass7 extends ThreadLocal<Memory> {
        public AnonymousClass7() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Memory initialValue() {
            Memory r2 = new Memory(4);
            r2.clear();
            return r2;
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ Memory initialValue() {
            return initialValue();
        }
    }

    public static class AWT {
        private AWT() {
        }

        public static long getComponentID(Object r4) {
            if (GraphicsEnvironment.isHeadless() == true) goto L24;
            Component r42 = (Component) r4;
            if (r42.isLightweight() == false) goto L7;
            f$$ExternalSyntheticBUOutline1.m("Component must be heavyweight");
            return 0;
        L7:
            if (r42.isDisplayable() == true) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("Component must be displayable");
            return 0;
        L9:
            if (Platform.isX11() == false) goto L18;
            if (System.getProperty("java.version").startsWith("1.4") == false) goto L18;
            if (r42.isVisible() == true) goto L18;
            Segment$$ExternalSyntheticBUOutline1.m("Component must be visible");
            return 0;
        L18:
            return Native.getWindowHandle0(r42);
        L24:
            throw new HeadlessException("No native windows when headless");
        }

        public static long getWindowID(Window r2) {
            return getComponentID(r2);
        }
    }

    public static class Buffers {
        private Buffers() {
        }

        public static boolean isBuffer(Class<?> r1) {
            return Buffer.class.isAssignableFrom(r1);
        }
    }

    public interface ffi_callback {
        void invoke(long r1, long r3, long r5);
    }

    static {
        LOG = Logger.getLogger(Native.class.getName());
        String r0 = System.getProperty("native.encoding");
        if (r0 != null) goto L55;
    L8:
        Charset r02 = null;
    L9:
        if (r02 != null) goto L11;
        r02 = Charset.defaultCharset();
    L11:
        DEFAULT_CHARSET = r02;
        DEFAULT_ENCODING = r02.name();
        DEBUG_LOAD = Boolean.getBoolean("jna.debug_load");
        boolean r03 = Boolean.getBoolean("jna.debug_load.jna");
        DEBUG_JNA_LOAD = r03;
        if (r03 == false) goto L14;
        Level r04 = Level.INFO;
    L15:
        DEBUG_JNA_LOAD_LEVEL = r04;
        jnidispatchPath = null;
        typeOptions = Collections.synchronizedMap(new WeakHashMap());
        libraries = Collections.synchronizedMap(new WeakHashMap());
        AnonymousClass1 r05 = new AnonymousClass1();
        DEFAULT_HANDLER = r05;
        callbackExceptionHandler = r05;
        loadNativeDispatchLibrary();
        if (isCompatibleVersion(Version.VERSION_NATIVE, getNativeVersion()) == true) goto L23;
        String r06 = System.lineSeparator();
        StringBuilder r2 = new StringBuilder();
        r2.append(r06);
        r2.append(r06);
        r2.append("There is an incompatible JNA native library installed on this system");
        r2.append(r06);
        r2.append("Expected: 7.0.4");
        r2.append(r06);
        r2.append("Found:    ");
        r2.append(getNativeVersion());
        r2.append(r06);
        if (jnidispatchPath == null) goto L20;
        String r3 = "(at " + jnidispatchPath + ")";
    L21:
        r2.append(r3);
        r2.append(".");
        r2.append(r06);
        r2.append("To resolve this issue you may do one of the following:");
        r2.append(r06);
        r2.append(" - remove or uninstall the offending library");
        r2.append(r06);
        r2.append(" - set the system property jna.nosys=true");
        r2.append(r06);
        r2.append(" - set jna.boot.library.path to include the path to the version of the ");
        r2.append(r06);
        r2.append("   jnidispatch library included with the JNA jar file you are using");
        r2.append(r06);
        throw new Error(r2.toString());
    L20:
        r3 = System.getProperty("java.library.path");
        goto L21
    L23:
        POINTER_SIZE = sizeof(0);
        int r1 = sizeof(1);
        LONG_SIZE = r1;
        WCHAR_SIZE = sizeof(2);
        SIZE_T_SIZE = sizeof(3);
        BOOL_SIZE = sizeof(4);
        LONG_DOUBLE_SIZE = sizeof(5);
        initIDs();
        if (Boolean.getBoolean("jna.protected") == false) goto L26;
        setProtected(true);
    L26:
        int r22 = 8;
        if (Platform.isSPARC() == false) goto L29;
    L46:
        r1 = 8;
    L47:
        MAX_ALIGNMENT = r1;
        if (Platform.isMac() == true) goto L50;
    L52:
        r22 = r1;
    L53:
        MAX_PADDING = r22;
        System.setProperty("jna.loaded", "true");
        finalizer = new AnonymousClass2();
        registeredClasses = new WeakHashMap();
        registeredLibraries = new WeakHashMap();
        nativeThreadTerminationFlag = new AnonymousClass7();
        nativeThreads = Collections.synchronizedMap(new WeakHashMap());
        return;
    L50:
        if (Platform.isPPC() == false) goto L52;
    L29:
        if (Platform.isWindows() == true) goto L46;
        if (Platform.isLinux() == false) goto L41;
        if (Platform.isARM() == true) goto L46;
        if (Platform.isPPC() == true) goto L46;
        if (Platform.isMIPS() == true) goto L46;
        if (Platform.isLoongArch() == true) goto L46;
    L41:
        if (Platform.isAIX() == true) goto L46;
        if (Platform.isAndroid() == false) goto L47;
        if (Platform.isIntel() == true) goto L47;
    L14:
        r04 = Level.FINE;
        goto L15
    L55:
        r02 = Charset.forName(r0);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        LOG.log(Level.WARNING, "Failed to get charset for native.encoding value : '" + r0 + "'", e);
        goto L8
    }

    private Native() {
    }

    private static native long _getDirectBufferPointer(Buffer r0);

    private static native long _getPointer(long r0);

    public static /* synthetic */ Logger access$000() {
        return LOG;
    }

    public static /* synthetic */ void access$100() {
        dispose();
    }

    private static Map<String, Object> cacheOptions(Class<?> r5, Map<String, ?> r6, Object r7) {
        HashMap r0 = new HashMap(r6);
        r0.put(_OPTION_ENCLOSING_LIBRARY, r5);
        typeOptions.put(r5, r0);
        if (r7 == null) goto L6;
        libraries.put(r5, new WeakReference(r7));
    L6:
        if (r5.isInterface() == false) goto L8;
    L16:
        return r0;
    L8:
        if (Library.class.isAssignableFrom(r5) == false) goto L16;
        Class<?>[] r52 = r5.getInterfaces();
        int r1 = r52.length;
        int r2 = 0;
    L10:
        if (r2 >= r1) goto L16;
        Class<?> r3 = r52[r2];
        if (Library.class.isAssignableFrom(r3) == true) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        cacheOptions(r3, r0, r7);
        return r0;
    }

    public static native void close(long r0);

    public static native synchronized long createNativeCallback(Callback r0, Method r1, Class<?>[] r2, Class<?> r3, int r4, int r5, String r6);

    public static boolean deleteLibrary(File r1) {
        if (r1.delete() == false) goto L6;
        return true;
    L6:
        markTemporaryFile(r1);
        return false;
    }

    public static void detach(boolean r2) {
        Thread r0 = Thread.currentThread();
        if (r2 == false) goto L6;
        nativeThreads.remove(r0);
        Memory r22 = nativeThreadTerminationFlag.get();
        setDetachState(true, 0);
        return;
    L6:
        Map<Thread, Pointer> r23 = nativeThreads;
        if (r23.containsKey(r0) == true) goto L10;
        Memory r1 = nativeThreadTerminationFlag.get();
        r23.put(r0, r1);
        setDetachState(false, r1.peer);
        return;
    }

    private static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        unregisterAll();
        jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }

    public static File extractFromResourcePath(String r1) {
        return extractFromResourcePath(r1, null);
    }

    public static File extractFromResourcePath(String r10, ClassLoader r11) {
        if (DEBUG_LOAD == false) goto L5;
    L10:
        Level r0 = Level.INFO;
    L11:
        if (r11 != null) goto L15;
        r11 = Thread.currentThread().getContextClassLoader();
        if (r11 != null) goto L15;
        r11 = Native.class.getClassLoader();
    L15:
        Logger r1 = LOG;
        r1.log(r0, "Looking in classpath from {0} for {1}", new Object[]{r11, r10});
        if (r10.startsWith("/") == false) goto L18;
        String r3 = r10;
    L20:
        if (r10.startsWith("/") == false) goto L22;
        String r4 = r10;
    L24:
        if (r4.startsWith("/") == false) goto L26;
        r4 = r4.substring(1);
    L26:
        URL r5 = r11.getResource(r4);
        if (r5 != null) goto L42;
        String r7 = Platform.RESOURCE_PREFIX;
        if (r4.startsWith(r7) == false) goto L36;
        if (r7.startsWith("darwin") == false) goto L33;
        r5 = r11.getResource("darwin/".concat(r4.substring(r7.length() + 1)));
    L33:
        if (r5 != null) goto L42;
        r5 = r11.getResource(r3);
        goto L42
    L36:
        if (r4.startsWith("com/sun/jna/" + r7 + "/") == false) goto L42;
        if (r7.startsWith("com/sun/jna/darwin") == false) goto L40;
        r5 = r11.getResource("com/sun/jna/darwin".concat(r4.substring(("com/sun/jna/" + r7).length() + 1)));
    L40:
        if (r5 != null) goto L42;
        r5 = r11.getResource(r3);
    L42:
        if (r5 != null) goto L48;
        String r102 = System.getProperty("java.class.path");
        if ((r11 instanceof URLClassLoader) == false) goto L47;
        r102 = Arrays.asList(((URLClassLoader) r11).getURLs()).toString();
    L47:
        throw new IOException("Native library (" + r4 + ") not found in resource path (" + r102 + ")");
    L48:
        r1.log(r0, "Found library resource at {0}", r5);
        FileOutputStream r2 = null;
        if (r5.getProtocol().toLowerCase().equals("file") == false) goto L59;
        File r103 = new File(new URI(r5.toString()));     // Catch: URISyntaxException -> L52
    L53:
        LOG.log(r0, "Looking in {0}", r103.getAbsolutePath());
        if (r103.exists() == true) goto L115;
        Native$$ExternalSyntheticBUOutline1.m("File URL ", r5, " could not be properly decoded");
        return null;
    L115:
        return r103;
    L52:
        r103 = new File(r5.getPath());
        goto L53
    L59:
        if (Boolean.getBoolean("jna.nounpack") == true) goto L95;
        InputStream r112 = r5.openStream();
        if (r112 != null) goto L111;
        Model$$ExternalSyntheticBUOutline0.m("Can't obtain InputStream for ".concat(r4));
        goto L95
    L111:
        File r32 = getTempDir();     // Catch: Throwable -> L65 IOException -> L67
        if (Platform.isWindows() == false) goto L69;
        String r52 = ".dll";
    L70:
        File r33 = File.createTempFile(JNA_TMPLIB_PREFIX, r52, r32);     // Catch: Throwable -> L65 IOException -> L67
        if (Boolean.getBoolean("jnidispatch.preserve") == true) goto L73;
        r33.deleteOnExit();     // Catch: Throwable -> L65 IOException -> L67
    L73:
        r1.log(r0, "Extracting library to {0}", r33.getAbsolutePath());     // Catch: Throwable -> L65 IOException -> L67
        FileOutputStream r02 = new FileOutputStream(r33);     // Catch: Throwable -> L65 IOException -> L67
        byte[] r22 = new byte[1024];     // Catch: Throwable -> L80 IOException -> L82
    L76:
        int r53 = r112.read(r22, 0, 1024);     // Catch: Throwable -> L80 IOException -> L82
        if (r53 <= 0) goto L104;
        r02.write(r22, 0, r53);     // Catch: Throwable -> L80 IOException -> L82
        goto L76
    L104:
        r112.close();     // Catch: IOException -> L96
    L100:
        r02.close();     // Catch: IOException -> L97
    L87:
        return r33;
    L82:
        e = move-exception;
        e = e;
    L89:
        throw new IOException("Failed to create temporary file for " + r10 + " library: " + e.getMessage());     // Catch: Throwable -> L65
    L80:
        th = th;
        r2 = r02;
    L106:
        r112.close();     // Catch: IOException -> L98
    L91:
        if (r2 == null) goto L116;
        r2.close();     // Catch: IOException -> L99
        throw th;
    L117:
        throw th;
    L116:
        throw th;
    L69:
        r52 = null;
    L67:
        e = e;
    L65:
        th = th;
    L95:
        return null;
    L22:
        r4 = Platform.RESOURCE_PREFIX + "/" + r3;
        goto L24
    L18:
        r3 = NativeLibrary.mapSharedLibraryName(r10);
        goto L20
    L5:
        if (DEBUG_JNA_LOAD == true) goto L7;
    L9:
        r0 = Level.FINE;
        goto L11
    L7:
        if (r10.contains("jnidispatch") == false) goto L9;
        goto L9
    }

    public static native void ffi_call(long r0, long r2, long r4, long r6);

    public static native void ffi_free_closure(long r0);

    public static native long ffi_prep_cif(int r0, int r1, long r2, long r4);

    public static native long ffi_prep_closure(long r0, ffi_callback r2);

    public static Class<?> findDirectMappedClass(Class<?> r5) {
        Method[] r0 = r5.getDeclaredMethods();
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        if ((r0[r3].getModifiers() & 256) != 0) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return r5;
    L8:
        int r02 = r5.getName().lastIndexOf("$");
        if (r02 != (-1)) goto L16;
    L13:
        Native$$ExternalSyntheticBUOutline0.m("Can't determine class with native methods from the current context (", r5, ")");
        return null;
    L16:
        return findDirectMappedClass(Class.forName(r5.getName().substring(0, r02), true, r5.getClassLoader()));
    }

    public static Class<?> findEnclosingLibraryClass(Class<?> r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Map<String, Object> r0 = typeOptions.get(r2);
        if (r0 == null) goto L11;
        Class<?> r02 = (Class) r0.get(_OPTION_ENCLOSING_LIBRARY);
        if (r02 == null) goto L12;
        return r02;
    L12:
        return r2;
    L11:
        if (Library.class.isAssignableFrom(r2) == true) goto L12;
        if (Callback.class.isAssignableFrom(r2) == false) goto L16;
        r2 = CallbackReference.findCallbackClass(r2);
    L16:
        Class<?> r03 = findEnclosingLibraryClass(r2.getDeclaringClass());
        if (r03 == null) goto L20;
        return r03;
    L20:
        return findEnclosingLibraryClass(r2.getSuperclass());
    }

    public static native long findSymbol(long r0, String r2);

    public static native void free(long r0);

    public static native synchronized void freeNativeCallback(long r0);

    private static NativeMapped fromNative(Class<?> r2, Object r3) {
        return (NativeMapped) NativeMappedConverter.getInstance(r2).fromNative(r3, new FromNativeContext(r2));
    }

    private static NativeMapped fromNative(Method r4, Object r5) {
        Class<?> r0 = r4.getReturnType();
        return (NativeMapped) NativeMappedConverter.getInstance(r0).fromNative(r5, new MethodResultContext(r0, null, null, r4));
    }

    private static Object fromNative(FromNativeConverter r3, Object r4, Method r5) {
        return r3.fromNative(r4, new MethodResultContext(r5.getReturnType(), null, null, r5));
    }

    private static native String getAPIChecksum();

    public static native byte getByte(Pointer r0, long r1, long r3);

    public static byte[] getBytes(String r1) {
        return getBytes(r1, getDefaultStringEncoding());
    }

    public static byte[] getBytes(String r0, String r1) {
        return getBytes(r0, getCharset(r1));
    }

    public static byte[] getBytes(String r0, Charset r1) {
        return r0.getBytes(r1);
    }

    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return callbackExceptionHandler;
    }

    public static Class<?> getCallingClass() {
        Class<?>[] r0 = new AnonymousClass6().getClassContext();
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        return null;
    L5:
        if (r0.length >= 4) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("This method must be called from the static initializer of a class");
        return null;
    L7:
        return r0[3];
    }

    public static native char getChar(Pointer r0, long r1, long r3);

    private static Charset getCharset(String r3) {
        if (r3 != null) goto L13;
    L9:
        Charset r32 = null;
    L10:
        if (r32 != null) goto L15;
        Logger r33 = LOG;
        Level r0 = Level.WARNING;
        Charset r1 = DEFAULT_CHARSET;
        r33.log(r0, "JNA Warning: Using fallback encoding {0}", r1);
        return r1;
    L15:
        return r32;
    L13:
        r32 = Charset.forName(r3);     // Catch: UnsupportedCharsetException -> L5 Throwable -> L7
    L7:
        e = move-exception;
        LOG.log(Level.WARNING, "JNA Warning: Encoding ''{0}'' is unsupported ({1})", new Object[]{r3, e.getMessage()});
        goto L9
    }

    public static long getComponentID(Component r2) {
        return AWT.getComponentID(r2);
    }

    public static Pointer getComponentPointer(Component r3) {
        return new Pointer(AWT.getComponentID(r3));
    }

    private static int getConversion(Class<?> r6, TypeMapper r7, boolean r8) {
        if (r6 != Void.class) goto L6;
        r6 = Void.TYPE;
    L6:
        if (r7 == null) goto L23;
        FromNativeConverter r2 = r7.getFromNativeConverter(r6);
        ToNativeConverter r72 = r7.getToNativeConverter(r6);
        if (r2 == null) goto L15;
        Class<?> r62 = r2.nativeType();
        if (r62 != String.class) goto L12;
        return 24;
    L12:
        if (r62 != WString.class) goto L14;
        return 25;
    L14:
        return 23;
    L15:
        if (r72 == null) goto L23;
        Class<?> r63 = r72.nativeType();
        if (r63 != String.class) goto L19;
        return 24;
    L19:
        if (r63 != WString.class) goto L21;
        return 25;
    L21:
        return 23;
    L23:
        if (Pointer.class.isAssignableFrom(r6) == false) goto L25;
        return 1;
    L25:
        if (String.class != r6) goto L29;
        return 2;
    L29:
        if (WString.class.isAssignableFrom(r6) == false) goto L33;
        return 20;
    L33:
        if (Platform.HAS_BUFFERS == false) goto L39;
        if (Buffers.isBuffer(r6) == false) goto L39;
        return 5;
    L39:
        if (Structure.class.isAssignableFrom(r6) == false) goto L47;
        if (Structure.ByValue.class.isAssignableFrom(r6) == false) goto L44;
        return 4;
    L44:
        return 3;
    L47:
        if (r6.isArray() == false) goto L77;
        char r73 = r6.getName().charAt(1);
        if (r73 != 'F') goto L51;
        return 11;
    L51:
        if (r73 != 'S') goto L53;
        return 7;
    L53:
        if (r73 != 'Z') goto L55;
        return 13;
    L55:
        if (r73 != 'I') goto L57;
        return 9;
    L57:
        if (r73 == 'J') goto L66;
        switch(r73) {
            case 66: goto L64;
            case 67: goto L62;
            case 68: goto L60;
            default: goto L77;
        };
    L60:
        return 12;
    L62:
        return 8;
    L64:
        return 6;
    L66:
        return 10;
    L77:
        if (r6.isPrimitive() == false) goto L85;
        if (r6 != Boolean.TYPE) goto L82;
        return 14;
    L82:
        return 0;
    L85:
        if (Callback.class.isAssignableFrom(r6) == false) goto L89;
        return 15;
    L89:
        if (IntegerType.class.isAssignableFrom(r6) == false) goto L93;
        return 21;
    L93:
        if (PointerType.class.isAssignableFrom(r6) == false) goto L97;
        return 22;
    L97:
        if (NativeMapped.class.isAssignableFrom(r6) == false) goto L108;
        Class<?> r64 = NativeMappedConverter.getInstance(r6).nativeType();
        if (r64 != String.class) goto L102;
        return 18;
    L102:
        if (r64 != WString.class) goto L105;
        return 19;
    L105:
        return 17;
    L108:
        if (JNIEnv.class != r6) goto L111;
        return 27;
    L111:
        if (r8 == false) goto L114;
        return 26;
    L114:
        return -1;
    }

    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", DEFAULT_ENCODING);
    }

    public static Pointer getDirectBufferPointer(Buffer r4) {
        long r0 = _getDirectBufferPointer(r4);
        if (r0 != 0) goto L7;
        return null;
    L7:
        return new Pointer(r0);
    }

    public static native ByteBuffer getDirectByteBuffer(Pointer r0, long r1, long r3, long r5);

    public static native double getDouble(Pointer r0, long r1, long r3);

    public static native float getFloat(Pointer r0, long r1, long r3);

    public static native int getInt(Pointer r0, long r1, long r3);

    public static native int getLastError();

    public static Map<String, Object> getLibraryOptions(Class<?> r6) {
        Map<Class<?>, Map<String, Object>> r0 = typeOptions;
        Map<String, Object> r1 = r0.get(r6);
        if (r1 == null) goto L5;
        return r1;
    L5:
        Class<?> r12 = findEnclosingLibraryClass(r6);
        if (r12 == null) goto L8;
        loadLibraryInstance(r12);
    L9:
        Map<String, Object> r2 = r0.get(r12);
        if (r2 == null) goto L36;
        r0.put(r6, r2);
        return r2;
    L36:
        Field r22 = r12.getField("OPTIONS");     // Catch: Exception -> L19 NoSuchFieldException -> L22
        r22.setAccessible(true);     // Catch: Exception -> L19 NoSuchFieldException -> L22
        Map r23 = (Map) r22.get(null);     // Catch: Exception -> L19 NoSuchFieldException -> L22
        if (r23 == null) goto L18;
    L23:
        HashMap r3 = new HashMap(r23);
        if (r3.containsKey(Library.OPTION_TYPE_MAPPER) == true) goto L27;
        r3.put(Library.OPTION_TYPE_MAPPER, lookupField(r12, "TYPE_MAPPER", TypeMapper.class));
    L27:
        if (r3.containsKey(Library.OPTION_STRUCTURE_ALIGNMENT) == true) goto L30;
        r3.put(Library.OPTION_STRUCTURE_ALIGNMENT, lookupField(r12, "STRUCTURE_ALIGNMENT", Integer.class));
    L30:
        if (r3.containsKey(Library.OPTION_STRING_ENCODING) == true) goto L32;
        r3.put(Library.OPTION_STRING_ENCODING, lookupField(r12, "STRING_ENCODING", String.class));
    L32:
        Map<String, Object> r02 = cacheOptions(r12, r3, null);
        if (r6 == r12) goto L35;
        typeOptions.put(r6, r02);
    L35:
        return r02;
    L18:
        throw new IllegalStateException("Null options field");     // Catch: Exception -> L19 NoSuchFieldException -> L22
    L22:
        r23 = Collections.EMPTY_MAP;
    L19:
        e = move-exception;
        Utils$$ExternalSyntheticBUOutline2.m("OPTIONS must be a public field of type java.util.Map (", e, "): ", r12);
        return null;
    L8:
        r12 = r6;
        goto L9
    }

    public static native long getLong(Pointer r0, long r1, long r3);

    public static NativeLibrary getNativeLibrary(Library r2) {
        if (r2 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("null passed to getNativeLibrary");
        return null;
    L5:
        if (Proxy.isProxyClass(r2.getClass()) == false) goto L12;
        InvocationHandler r22 = Proxy.getInvocationHandler(r2);
        if ((r22 instanceof Library.Handler) == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Object is not a properly initialized Library interface instance");
        return null;
    L9:
        return ((Library.Handler) r22).getNativeLibrary();
    L12:
        f$$ExternalSyntheticBUOutline1.m("library object passed to getNativeLibrary in not a proxy");
        return null;
    }

    public static NativeLibrary getNativeLibrary(Class<?> r4) {
        if (r4 == null) goto L16;
        Class<?> r1 = findDirectMappedClass(r4);
        Map<Class<?>, long[]> r2 = registeredClasses;
        monitor-enter(r2);
        NativeLibrary r12 = registeredLibraries.get(r1);     // Catch: Throwable -> L10
        if (r12 == null) goto L13;
        monitor-exit(r2);     // Catch: Throwable -> L10
        return r12;
    L13:
        throw new IllegalArgumentException("Class " + r4.getName() + " is not currently registered");     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L16:
        f$$ExternalSyntheticBUOutline1.m("null passed to getNativeLibrary");
        return null;
    }

    public static int getNativeSize(Class<?> r3) {
        if (NativeMapped.class.isAssignableFrom(r3) == false) goto L6;
        r3 = NativeMappedConverter.getInstance(r3).nativeType();
    L6:
        if (r3 != Boolean.TYPE) goto L8;
    L80:
        return 4;
    L8:
        if (r3 == Boolean.class) goto L80;
        if (r3 != Byte.TYPE) goto L13;
        return 1;
    L13:
        if (r3 != Byte.class) goto L16;
        return 1;
    L16:
        if (r3 != Short.TYPE) goto L18;
        return 2;
    L18:
        if (r3 != Short.class) goto L21;
        return 2;
    L21:
        if (r3 == Character.TYPE) goto L75;
        if (r3 == Character.class) goto L75;
        if (r3 != Integer.TYPE) goto L28;
    L73:
        return 4;
    L28:
        if (r3 == Integer.class) goto L73;
        if (r3 != Long.TYPE) goto L33;
    L72:
        return 8;
    L33:
        if (r3 == Long.class) goto L72;
        if (r3 != Float.TYPE) goto L38;
    L71:
        return 4;
    L38:
        if (r3 == Float.class) goto L71;
        if (r3 != Double.TYPE) goto L43;
    L70:
        return 8;
    L43:
        if (r3 == Double.class) goto L70;
        if (Structure.class.isAssignableFrom(r3) == false) goto L54;
        if (Structure.ByValue.class.isAssignableFrom(r3) == false) goto L52;
        return Structure.size(r3);
    L52:
        return POINTER_SIZE;
    L54:
        if (Pointer.class.isAssignableFrom(r3) == true) goto L69;
        if (Platform.HAS_BUFFERS == false) goto L60;
        if (Buffers.isBuffer(r3) == true) goto L69;
    L60:
        if (Callback.class.isAssignableFrom(r3) == true) goto L69;
        if (String.class == r3) goto L69;
        if (WString.class == r3) goto L69;
        Native$$ExternalSyntheticBUOutline2.m("Native size for type \"", r3.getName(), "\" is unknown");
        return 0;
    L69:
        return POINTER_SIZE;
    L75:
        return WCHAR_SIZE;
    }

    public static int getNativeSize(Class<?> r3, Object r4) {
        if (r3.isArray() == false) goto L11;
        int r0 = Array.getLength(r4);
        if (r0 <= 0) goto L8;
        Object r42 = Array.get(r4, 0);
        return r0 * getNativeSize(r3.getComponentType(), r42);
    L8:
        Native$$ExternalSyntheticBUOutline5.m("Arrays of length zero not allowed: ", r3);
        return 0;
    L11:
        if (Structure.class.isAssignableFrom(r3) == true) goto L13;
    L21:
        return getNativeSize(r3);
    L18:
        e = move-exception;
        Union$$ExternalSyntheticBUOutline0.m("The type \"", r3.getName(), "\" is not supported: ", e.getMessage());
        return 0;
    L13:
        if (Structure.ByReference.class.isAssignableFrom(r3) == true) goto L21;
        return Structure.size(r3, (Structure) r4);
    }

    private static native String getNativeVersion();

    public static Pointer getPointer(long r2) {
        long r22 = _getPointer(r2);
        if (r22 != 0) goto L7;
        return null;
    L7:
        return new Pointer(r22);
    }

    public static native short getShort(Pointer r0, long r1, long r3);

    public static String getSignature(Class<?> r3) {
        if (r3.isArray() == false) goto L7;
        return "[" + getSignature(r3.getComponentType());
    L7:
        if (r3.isPrimitive() == false) goto L45;
        if (r3 != Void.TYPE) goto L13;
        return "V";
    L13:
        if (r3 != Boolean.TYPE) goto L17;
        return "Z";
    L17:
        if (r3 != Byte.TYPE) goto L21;
        return "B";
    L21:
        if (r3 != Short.TYPE) goto L25;
        return "S";
    L25:
        if (r3 != Character.TYPE) goto L29;
        return "C";
    L29:
        if (r3 != Integer.TYPE) goto L33;
        return "I";
    L33:
        if (r3 != Long.TYPE) goto L37;
        return "J";
    L37:
        if (r3 != Float.TYPE) goto L41;
        return "F";
    L41:
        if (r3 != Double.TYPE) goto L45;
        return "D";
    L45:
        return "L" + replace(".", "/", r3.getName()) + ";";
    }

    public static String getString(Pointer r1, long r2) {
        return getString(r1, r2, getDefaultStringEncoding());
    }

    public static String getString(Pointer r2, long r3, String r5) {
        byte[] r22 = getStringBytes(r2, r2.peer, r3);
        if (r5 == null) goto L7;
        return new String(r22, r5);
    L7:
        return new String(r22);
    }

    public static native byte[] getStringBytes(Pointer r0, long r1, long r3);

    public static String getStringEncoding(Class<?> r1) {
        String r12 = (String) getLibraryOptions(r1).get(Library.OPTION_STRING_ENCODING);
        if (r12 == null) goto L6;
        return r12;
    L6:
        return getDefaultStringEncoding();
    }

    public static int getStructureAlignment(Class<?> r1) {
        Integer r12 = (Integer) getLibraryOptions(r1).get(Library.OPTION_STRUCTURE_ALIGNMENT);
        if (r12 != null) goto L7;
        return 0;
    L7:
        return r12.intValue();
    }

    public static File getTempDir() {
        String r0 = System.getProperty("jna.tmpdir");
        if (r0 == null) goto L5;
        File r1 = new File(r0);
        r1.mkdirs();
    L41:
        if (r1.exists() == true) goto L43;
        Native$$ExternalSyntheticBUOutline1.m("JNA temporary directory '", r1, "' does not exist");
        return null;
    L43:
        if (r1.canWrite() == false) goto L45;
        return r1;
    L45:
        Native$$ExternalSyntheticBUOutline1.m("JNA temporary directory '", r1, "' is not writable");
        return null;
    L5:
        r1 = new File(System.getProperty("java.io.tmpdir"));
        if (Platform.isMac() == false) goto L9;
        File r02 = new File(System.getProperty("user.home"), "Library/Caches/JNA/temp");
    L34:
        r02.mkdirs();
        if (r02.exists() == false) goto L41;
        if (r02.canWrite() == false) goto L41;
        r1 = r02;
        goto L41
    L9:
        if (Platform.isLinux() == false) goto L11;
    L26:
        String r03 = System.getenv("XDG_CACHE_HOME");
        if (r03 != null) goto L29;
    L32:
        File r2 = new File(System.getProperty("user.home"), ".cache");
    L33:
        r02 = new File(r2, "JNA/temp");
        goto L34
    L29:
        if (r03.trim().isEmpty() == true) goto L32;
        r2 = new File(r03);
        goto L33
    L11:
        if (Platform.isSolaris() == true) goto L26;
        if (Platform.isAIX() == true) goto L26;
        if (Platform.isDragonFlyBSD() == true) goto L26;
        if (Platform.isFreeBSD() == true) goto L26;
        if (Platform.isNetBSD() == true) goto L26;
        if (Platform.isOpenBSD() == true) goto L26;
        if (Platform.iskFreeBSD() == true) goto L26;
        r02 = new File(r1, "jna-" + System.getProperty("user.name").hashCode());
        goto L34
    }

    public static Pointer getTerminationFlag(Thread r1) {
        return nativeThreads.get(r1);
    }

    public static TypeMapper getTypeMapper(Class<?> r1) {
        return (TypeMapper) getLibraryOptions(r1).get(Library.OPTION_TYPE_MAPPER);
    }

    public static String getWebStartLibraryPath(String r3) {
        if (System.getProperty("javawebstart.version") != null) goto L11;
        return null;
    L11:
        ClassLoader r0 = Native.class.getClassLoader();     // Catch: Exception -> L10
        String r32 = (String) ((Method) AccessController.doPrivileged(new AnonymousClass4())).invoke(r0, new Object[]{r3});     // Catch: Exception -> L10
        if (r32 == null) goto L9;
        return new File(r32).getParent();
    L9:
        return null;
    }

    public static native String getWideString(Pointer r0, long r1, long r3);

    public static native long getWindowHandle0(Component r0);

    public static long getWindowID(Window r2) {
        return AWT.getWindowID(r2);
    }

    public static Pointer getWindowPointer(Window r3) {
        return new Pointer(AWT.getWindowID(r3));
    }

    public static native long indexOf(Pointer r0, long r1, long r3, byte r5);

    private static native void initIDs();

    public static native int initialize_ffi_type(long r0);

    public static native double invokeDouble(Function r0, long r1, int r3, Object[] r4);

    public static native float invokeFloat(Function r0, long r1, int r3, Object[] r4);

    public static native int invokeInt(Function r0, long r1, int r3, Object[] r4);

    public static native long invokeLong(Function r0, long r1, int r3, Object[] r4);

    public static native Object invokeObject(Function r0, long r1, int r3, Object[] r4);

    public static native long invokePointer(Function r0, long r1, int r3, Object[] r4);

    public static Structure invokeStructure(Function r10, long r11, int r13, Object[] r14, Structure r15) {
        invokeStructure(r10, r11, r13, r14, r15.getPointer().peer, r15.getTypeInfo().peer);
        return r15;
    }

    private static native void invokeStructure(Function r0, long r1, int r3, Object[] r4, long r5, long r7);

    public static native void invokeVoid(Function r0, long r1, int r3, Object[] r4);

    public static boolean isCompatibleVersion(String r4, String r5) {
        String[] r42 = r4.split("\\.");
        String[] r52 = r5.split("\\.");
        if (r42.length >= 3) goto L5;
    L13:
        return false;
    L5:
        if (r52.length < 3) goto L13;
        int r0 = Integer.parseInt(r42[0]);
        int r2 = Integer.parseInt(r52[0]);
        int r43 = Integer.parseInt(r42[1]);
        int r53 = Integer.parseInt(r52[1]);
        if (r0 == r2) goto L10;
        return false;
    L10:
        if (r43 <= r53) goto L12;
        return false;
    L12:
        return true;
    }

    public static native synchronized boolean isProtected();

    public static boolean isSupportedNativeType(Class<?> r2) {
        if (Structure.class.isAssignableFrom(r2) == false) goto L11;
        return true;
    L11:
        if (getNativeSize(r2) == 0) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isUnpacked(File r1) {
        return r1.getName().startsWith(JNA_TMPLIB_PREFIX);
    }

    public static <T extends Library> T load(Class<T> r1) {
        return (T) load(null, r1);
    }

    public static <T extends Library> T load(Class<T> r1, Map<String, ?> r2) {
        return (T) load(null, r1, r2);
    }

    public static <T extends Library> T load(String r1, Class<T> r2) {
        return (T) load(r1, r2, Collections.EMPTY_MAP);
    }

    public static <T extends Library> T load(String r6, Class<T> r7, Map<String, ?> r8) {
        if (Library.class.isAssignableFrom(r7) == false) goto L6;
        Library.Handler r0 = new Library.Handler(r6, r7, r8);
        Object r62 = Proxy.newProxyInstance(r7.getClassLoader(), new Class[]{r7}, r0);
        cacheOptions(r7, r8, r62);
        return r7.cast(r62);
    L6:
        Native$$ExternalSyntheticBUOutline4.m("Interface (", r7.getSimpleName(), ") of library=", r6, " does not extend ", Library.class.getSimpleName());
        return null;
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> r1) {
        return (T) loadLibrary(null, r1);
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> r1, Map<String, ?> r2) {
        return (T) loadLibrary(null, r1, r2);
    }

    @Deprecated
    public static <T> T loadLibrary(String r1, Class<T> r2) {
        return (T) loadLibrary(r1, r2, Collections.EMPTY_MAP);
    }

    @Deprecated
    public static <T> T loadLibrary(String r6, Class<T> r7, Map<String, ?> r8) {
        if (Library.class.isAssignableFrom(r7) == false) goto L6;
        Library.Handler r0 = new Library.Handler(r6, r7, r8);
        Object r62 = Proxy.newProxyInstance(r7.getClassLoader(), new Class[]{r7}, r0);
        cacheOptions(r7, r8, r62);
        return r7.cast(r62);
    L6:
        Native$$ExternalSyntheticBUOutline4.m("Interface (", r7.getSimpleName(), ") of library=", r6, " does not extend ", Library.class.getSimpleName());
        return null;
    }

    private static void loadLibraryInstance(Class<?> r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (libraries.containsKey(r4) == true) goto L26;
        Field[] r0 = r4.getFields();     // Catch: Exception -> L14
        int r1 = 0;
    L7:
        if (r1 >= r0.length) goto L27;
        Field r2 = r0[r1];     // Catch: Exception -> L14
        if (r2.getType() != r4) goto L16;
        if (Modifier.isStatic(r2.getModifiers()) == false) goto L16;
        r2.setAccessible(true);     // Catch: Exception -> L14
        libraries.put(r4, new WeakReference(r2.get(null)));     // Catch: Exception -> L14
        return;
    L16:
        r1 = r1 + 1;
        goto L7
    L27:
        return;
    L14:
        e = move-exception;
        Native$$ExternalSyntheticBUOutline3.m("Could not access instance of ", r4, " (", e, ")");
        return;
    }

    private static void loadNativeDispatchLibrary() {
        if (Boolean.getBoolean("jna.nounpack") == false) goto L49;
    L8:
        String r0 = System.getProperty("jna.boot.library.name", "jnidispatch");
        String r1 = System.getProperty("jna.boot.library.path");
        if (r1 == null) goto L30;
        StringTokenizer r2 = new StringTokenizer(r1, File.pathSeparator);
    L12:
        if (r2.hasMoreTokens() == false) goto L30;
        File r3 = new File(new File(r2.nextToken()), System.mapLibraryName(r0).replace(".dylib", ".jnilib"));
        String r12 = r3.getAbsolutePath();
        Logger r4 = LOG;
        Level r5 = DEBUG_JNA_LOAD_LEVEL;
        r4.log(r5, "Looking in {0}", r12);
        if (r3.exists() == false) goto L18;
        r4.log(r5, "Trying {0}", r12);     // Catch: UnsatisfiedLinkError -> L41
        System.setProperty("jnidispatch.path", r12);     // Catch: UnsatisfiedLinkError -> L41
        System.load(r12);     // Catch: UnsatisfiedLinkError -> L41
        jnidispatchPath = r12;     // Catch: UnsatisfiedLinkError -> L41
        r4.log(r5, "Found jnidispatch at {0}", r12);     // Catch: UnsatisfiedLinkError -> L41
        return;
    L18:
        if (Platform.isMac() == false) goto L12;
        String r32 = "dylib";
        String r52 = "jnilib";
        if (r12.endsWith("dylib") == true) goto L23;
        r52 = "dylib";
        r32 = "jnilib";
    L23:
        String r13 = r12.substring(0, r12.lastIndexOf(r32)).concat(r52);
        Logger r33 = LOG;
        Level r42 = DEBUG_JNA_LOAD_LEVEL;
        r33.log(r42, "Looking in {0}", r13);
        if (new File(r13).exists() == false) goto L12;
        r33.log(r42, "Trying {0}", r13);     // Catch: UnsatisfiedLinkError -> L27
        System.setProperty("jnidispatch.path", r13);     // Catch: UnsatisfiedLinkError -> L27
        System.load(r13);     // Catch: UnsatisfiedLinkError -> L27
        jnidispatchPath = r13;     // Catch: UnsatisfiedLinkError -> L27
        r33.log(r42, "Found jnidispatch at {0}", r13);     // Catch: UnsatisfiedLinkError -> L27
        return;
    L27:
        e = move-exception;
        LOG.log(Level.WARNING, "File found at " + r13 + " but not loadable: " + e.getMessage(), e);
    L30:
        if (Boolean.parseBoolean(System.getProperty("jna.nosys", "true")) == true) goto L32;
    L45:
        Logger r14 = LOG;     // Catch: UnsatisfiedLinkError -> L42
        Level r22 = DEBUG_JNA_LOAD_LEVEL;     // Catch: UnsatisfiedLinkError -> L42
        r14.log(r22, "Trying (via loadLibrary) {0}", r0);     // Catch: UnsatisfiedLinkError -> L42
        System.loadLibrary(r0);     // Catch: UnsatisfiedLinkError -> L42
        r14.log(r22, "Found jnidispatch on system path");     // Catch: UnsatisfiedLinkError -> L42
        return;
    L36:
        if (Boolean.getBoolean("jna.noclasspath") == true) goto L40;
        loadNativeDispatchLibraryFromClasspath();
        return;
    L40:
        throw new UnsatisfiedLinkError("Unable to locate JNA native support library");
    L32:
        if (Platform.isAndroid() == false) goto L36;
    L49:
        removeTemporaryFiles();     // Catch: IOException -> L6
    L6:
        e = move-exception;
        LOG.log(Level.WARNING, "JNA Warning: IOException removing temporary files", e);
        goto L8
    }

    private static void loadNativeDispatchLibraryFromClasspath() {
        String r1 = System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib");     // Catch: IOException -> L18
        if (Platform.isAIX() == false) goto L6;
        r1 = "libjnidispatch.a";
    L6:
        File r0 = extractFromResourcePath("/com/sun/jna/" + Platform.RESOURCE_PREFIX + "/" + r1, Native.class.getClassLoader());     // Catch: IOException -> L18
        if (r0 != null) goto L12;
        if (r0 != null) goto L12;
        throw new UnsatisfiedLinkError("Could not find JNA native support");     // Catch: IOException -> L18
    L12:
        Logger r12 = LOG;     // Catch: IOException -> L18
        Level r2 = DEBUG_JNA_LOAD_LEVEL;     // Catch: IOException -> L18
        r12.log(r2, "Trying {0}", r0.getAbsolutePath());     // Catch: IOException -> L18
        System.setProperty("jnidispatch.path", r0.getAbsolutePath());     // Catch: IOException -> L18
        System.load(r0.getAbsolutePath());     // Catch: IOException -> L18
        String r3 = r0.getAbsolutePath();     // Catch: IOException -> L18
        jnidispatchPath = r3;     // Catch: IOException -> L18
        r12.log(r2, "Found jnidispatch at {0}", r3);     // Catch: IOException -> L18
        if (isUnpacked(r0) == true) goto L15;
        return;
    L15:
        if (Boolean.getBoolean("jnidispatch.preserve") == true) goto L23;
        deleteLibrary(r0);     // Catch: IOException -> L18
        return;
    L23:
        return;
    L18:
        e = move-exception;
        throw new UnsatisfiedLinkError(e.getMessage());
    }

    private static Object lookupField(Class<?> r3, String r4, Class<?> r5) {
        Field r1 = r3.getField(r4);     // Catch: Exception -> L5 NoSuchFieldException -> L8
        r1.setAccessible(true);     // Catch: Exception -> L5 NoSuchFieldException -> L8
        return r1.get(null);
    L8:
        return null;
    L5:
        e = move-exception;
        throw new IllegalArgumentException(r4 + " must be a public field of type " + r5.getName() + " (" + e + "): " + r3);
    }

    public static void main(String[] r3) {
        Package r32 = Native.class.getPackage();
        String r0 = "Java Native Access (JNA)";
        if (r32 == null) goto L5;
        String r1 = r32.getSpecificationTitle();
    L6:
        if (r1 == null) goto L9;
        r0 = r1;
    L9:
        String r12 = Version.VERSION;
        if (r32 == null) goto L12;
        String r2 = r32.getSpecificationVersion();
    L13:
        if (r2 == null) goto L16;
        r12 = r2;
    L16:
        System.out.println(r0 + " API Version " + r12);
        String r02 = "5.18.1 (package information missing)";
        if (r32 == null) goto L19;
        String r33 = r32.getImplementationVersion();
    L20:
        if (r33 == null) goto L23;
        r02 = r33;
    L23:
        System.out.println("Version: ".concat(r02));
        System.out.println(" Native: " + getNativeVersion() + " (" + getAPIChecksum() + ")");
        PrintStream r34 = System.out;
        StringBuilder r03 = new StringBuilder(" Prefix: ");
        r03.append(Platform.RESOURCE_PREFIX);
        r34.println(r03.toString());
        return;
    L19:
        r33 = "5.18.1 (package information missing)";
        goto L20
    L12:
        r2 = Version.VERSION;
        goto L13
    L5:
        r1 = "Java Native Access (JNA)";
        goto L6
    }

    public static native long malloc(long r0);

    public static void markTemporaryFile(File r3) {
        new File(r3.getParentFile(), r3.getName() + ".x").createNewFile();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        e.printStackTrace();
    }

    private static Class<?> nativeType(Class<?> r0) {
        return NativeMappedConverter.getInstance(r0).nativeType();
    }

    public static long open(String r2) {
        return open(r2, -1);
    }

    public static native long open(String r0, int r1);

    public static native void read(Pointer r0, long r1, long r3, byte[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, char[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, double[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, float[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, int[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, long[] r5, int r6, int r7);

    public static native void read(Pointer r0, long r1, long r3, short[] r5, int r6, int r7);

    public static void register(NativeLibrary r1) {
        register(findDirectMappedClass(getCallingClass()), r1);
    }

    public static void register(Class<?> r29, NativeLibrary r30) {
        Class<?> r3 = r29;
        Method[] r1 = r3.getDeclaredMethods();
        ArrayList r2 = new ArrayList();
        Map<String, ?> r4 = r30.getOptions();
        TypeMapper r5 = (TypeMapper) r4.get(Library.OPTION_TYPE_MAPPER);
        boolean r6 = Boolean.TRUE.equals(r4.get(Library.OPTION_ALLOW_OBJECTS));
        cacheOptions(r3, r4, null);
        int r42 = r1.length;
        int r8 = 0;
    L3:
        if (r8 >= r42) goto L8;
        Method r9 = r1[r8];
        if ((r9.getModifiers() & 256) == 0) goto L7;
        r2.add(r9);
    L7:
        r8 = r8 + 1;
        goto L3
    L8:
        int r12 = r2.size();
        long[] r43 = new long[r12];
        int r82 = 0;
    L9:
        if (r82 >= r12) goto L85;
        Method r14 = (Method) r2.get(r82);
        Class<?> r10 = r14.getReturnType();
        Class<?>[] r11 = r14.getParameterTypes();
        long[] r122 = new long[r11.length];
        long[] r13 = new long[r11.length];
        int[] r15 = new int[r11.length];
        ToNativeConverter[] r7 = new ToNativeConverter[r11.length];
        int r92 = getConversion(r10, r5, r6);
        int r23 = r12;
        if (r92 == (-1)) goto L83;
        ArrayList r24 = r2;
        if (r92 == 3) goto L28;
        if (r92 == 4) goto L27;
        switch(r92) {
            case 17: goto L25;
            case 18: goto L25;
            case 19: goto L25;
            default: goto L16;
        };
    L16:
        switch(r92) {
            case 21: goto L25;
            case 22: goto L25;
            case 23: goto L20;
            case 24: goto L20;
            case 25: goto L20;
            case 26: goto L28;
            default: goto L17;
        };
    L17:
        long r22 = Structure.FFIType.get(r10).getPointer().peer;
    L18:
        long r25 = r22;
    L19:
        FromNativeConverter r20 = null;
    L29:
        long r27 = r22;
        String r16 = "(";
        int r26 = 0;
    L31:
        if (r26 >= r11.length) goto L67;
        Class<?> r32 = r11[r26];
        int r17 = r26;
        String r18 = r16 + getSignature(r32);
        int r28 = getConversion(r32, r5, r6);
        r15[r17] = r28;
        if (r28 == (-1)) goto L65;
        if (r28 != 17) goto L37;
    L50:
        r32 = NativeMappedConverter.getInstance(r32).nativeType();
    L51:
        if (r28 != 0) goto L53;
        long r210 = Structure.FFIType.get(r32).getPointer().peer;
        r122[r17] = r210;
        r13[r17] = r210;
    L64:
        r26 = r17 + 1;
        r16 = r18;
        goto L31
    L53:
        if (r28 == 4) goto L62;
        switch(r28) {
            case 17: goto L62;
            case 18: goto L62;
            case 19: goto L62;
            default: goto L55;
        };
    L55:
        switch(r28) {
            case 21: goto L62;
            case 22: goto L62;
            case 23: goto L58;
            case 24: goto L58;
            case 25: goto L58;
            default: goto L56;
        };
    L56:
        long r211 = Structure.FFIType.get(Pointer.class).getPointer().peer;
        r122[r17] = r211;
        r13[r17] = r211;
        goto L64
    L58:
        if (r32.isPrimitive() == true) goto L61;
        r32 = Pointer.class;
    L61:
        r13[r17] = Structure.FFIType.get(r32).getPointer().peer;
        r122[r17] = Structure.FFIType.get(r7[r17].nativeType()).getPointer().peer;
    L62:
        r122[r17] = Structure.FFIType.get(r32).getPointer().peer;
        r13[r17] = Structure.FFIType.get(Pointer.class).getPointer().peer;
        goto L64
    L37:
        if (r28 == 18) goto L50;
        if (r28 == 19) goto L50;
        if (r28 == 21) goto L50;
        if (r28 != 23) goto L46;
    L49:
        r7[r17] = r5.getToNativeConverter(r32);
        goto L51
    L46:
        if (r28 == 24) goto L49;
        if (r28 != 25) goto L51;
    L65:
        StringBuilder r19 = new StringBuilder();
        r19.append(r32);
        String r33 = r14.getName();
        r19.append(" is not a supported argument type (in method ");
        r19.append(r33);
        r19.append(" in ");
        r19.append(r29);
        r19.append(")");
        throw new IllegalArgumentException(r19.toString());
    L67:
        r3 = r29;
        String r110 = r16.concat(")") + getSignature(r10);
        Class<?>[] r212 = r14.getExceptionTypes();
        int r102 = 0;
    L69:
        if (r102 >= r212.length) goto L74;
        String r172 = r110;
        if (LastErrorException.class.isAssignableFrom(r212[r102]) == true) goto L72;
        r102 = r102 + 1;
        r110 = r172;
        goto L69
    L72:
        boolean r182 = true;
    L75:
        Function r111 = r30.getFunction(r14.getName(), r14);
        long[] r213 = r43;
        TypeMapper r192 = r5;
        String r52 = r172;
        boolean r214 = r6;
        int r262 = r82;
        r213[r262] = registerMethod(r3, r14.getName(), r52, r15, r13, r122, r92, r25, r27, r14, r111.peer, r111.getCallingConvention(), r182, r7, r20, r111.encoding);     // Catch: NoSuchMethodError -> L93
        r82 = r262 + 1;
        r43 = r213;
        r6 = r214;
        r12 = r23;
        r2 = r24;
        r5 = r192;
    L82:
        throw new UnsatisfiedLinkError("No method " + r14.getName() + " with signature " + r52 + " in " + r3);
    L80:
        r52 = r172;
        goto L82
    L74:
        r172 = r110;
        r182 = false;
        goto L75
    L20:
        FromNativeConverter r112 = r5.getFromNativeConverter(r10);
        if (r10.isPrimitive() == false) goto L23;
        Class<?> r215 = r10;
    L24:
        r25 = Structure.FFIType.get(r215).getPointer().peer;
        r22 = Structure.FFIType.get(r112.nativeType()).getPointer().peer;
        r20 = r112;
        goto L29
    L23:
        r215 = Pointer.class;
    L25:
        long r202 = Structure.FFIType.get(Pointer.class).getPointer().peer;
        long r113 = Structure.FFIType.get(NativeMappedConverter.getInstance(r10).nativeType()).getPointer().peer;
    L26:
        r22 = r113;
        r25 = r202;
        goto L19
    L27:
        r202 = Structure.FFIType.get(Pointer.class).getPointer().peer;
        r113 = Structure.FFIType.get(r10).getPointer().peer;
    L28:
        r22 = Structure.FFIType.get(Pointer.class).getPointer().peer;
        goto L18
    L83:
        StringBuilder r114 = new StringBuilder();
        r114.append(r10);
        String r44 = r14.getName();
        r114.append(" is not a supported return type (in method ");
        r114.append(r44);
        r114.append(" in ");
        r114.append(r3);
        r114.append(")");
        throw new IllegalArgumentException(r114.toString());
    L85:
        long[] r115 = r43;
        Map<Class<?>, long[]> r216 = registeredClasses;
        monitor-enter(r216);
        r216.put(r3, r115);     // Catch: Throwable -> L90
        registeredLibraries.put(r3, r30);     // Catch: Throwable -> L90
        monitor-exit(r216);     // Catch: Throwable -> L90
        return;
    L90:
        th = move-exception;
        throw th;
    }

    public static void register(Class<?> r2, String r3) {
        register(r2, NativeLibrary.getInstance(r3, Collections.singletonMap(Library.OPTION_CLASSLOADER, r2.getClassLoader())));
    }

    public static void register(String r1) {
        register(findDirectMappedClass(getCallingClass()), r1);
    }

    private static native long registerMethod(Class<?> r0, String r1, String r2, int[] r3, long[] r4, long[] r5, int r6, long r7, long r9, Method r11, long r12, int r14, boolean r15, ToNativeConverter[] r16, FromNativeConverter r17, String r18);

    public static boolean registered(Class<?> r1) {
        Map<Class<?>, long[]> r0 = registeredClasses;
        monitor-enter(r0);
        boolean r12 = r0.containsKey(r1);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    public static void removeTemporaryFiles() {
        File[] r0 = getTempDir().listFiles(new AnonymousClass5());
        int r2 = 0;
    L3:
        if (r0 == null) goto L12;
        if (r2 >= r0.length) goto L17;
        File r3 = r0[r2];
        File r5 = new File(r3.getParentFile(), r3.getName().substring(0, r4.length() - 2));
        if (r5.exists() == true) goto L9;
    L10:
        r3.delete();
    L11:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r5.delete() == false) goto L11;
    L17:
        return;
    }

    public static String replace(String r3, String r4, String r5) {
        StringBuilder r0 = new StringBuilder();
    L3:
        int r1 = r5.indexOf(r3);
        if (r1 == (-1)) goto L5;
        r0.append(r5.substring(0, r1));
        r0.append(r4);
        r5 = r5.substring(r1 + r3.length());
        goto L3
    L5:
        r0.append(r5);
        return r0.toString();
    }

    public static native void setByte(Pointer r0, long r1, long r3, byte r5);

    public static void setCallbackExceptionHandler(Callback.UncaughtExceptionHandler r0) {
        if (r0 != null) goto L4;
        r0 = DEFAULT_HANDLER;
    L4:
        callbackExceptionHandler = r0;
    }

    public static void setCallbackThreadInitializer(Callback r0, CallbackThreadInitializer r1) {
        CallbackReference.setCallbackThreadInitializer(r0, r1);
    }

    public static native void setChar(Pointer r0, long r1, long r3, char r5);

    private static native void setDetachState(boolean r0, long r1);

    public static native void setDouble(Pointer r0, long r1, long r3, double r5);

    public static native void setFloat(Pointer r0, long r1, long r3, float r5);

    public static native void setInt(Pointer r0, long r1, long r3, int r5);

    public static native void setLastError(int r0);

    public static native void setLong(Pointer r0, long r1, long r3, long r5);

    public static native void setMemory(Pointer r0, long r1, long r3, long r5, byte r7);

    public static native void setPointer(Pointer r0, long r1, long r3, long r5);

    public static native synchronized void setProtected(boolean r0);

    public static native void setShort(Pointer r0, long r1, long r3, short r5);

    public static native void setWideString(Pointer r0, long r1, long r3, String r5);

    private static native int sizeof(int r0);

    public static Library synchronizedLibrary(Library r4) {
        Class<?> r0 = r4.getClass();
        if (Proxy.isProxyClass(r0) == false) goto L10;
        InvocationHandler r1 = Proxy.getInvocationHandler(r4);
        if ((r1 instanceof Library.Handler) == false) goto L8;
        AnonymousClass3 r2 = new AnonymousClass3((Library.Handler) r1, r4);
        return (Library) Proxy.newProxyInstance(r0.getClassLoader(), r0.getInterfaces(), r2);
    L8:
        Native$$ExternalSyntheticBUOutline5.m("Unrecognized proxy handler: ", r1);
        return null;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Library must be a proxy class");
        return null;
    }

    public static byte[] toByteArray(String r1) {
        return toByteArray(r1, getDefaultStringEncoding());
    }

    public static byte[] toByteArray(String r0, String r1) {
        return toByteArray(r0, getCharset(r1));
    }

    public static byte[] toByteArray(String r2, Charset r3) {
        byte[] r22 = getBytes(r2, r3);
        byte[] r32 = new byte[r22.length + 1];
        System.arraycopy(r22, 0, r32, 0, r22.length);
        return r32;
    }

    public static char[] toCharArray(String r3) {
        char[] r32 = r3.toCharArray();
        char[] r0 = new char[r32.length + 1];
        System.arraycopy(r32, 0, r0, 0, r32.length);
        return r0;
    }

    private static Object toNative(ToNativeConverter r1, Object r2) {
        return r1.toNative(r2, new ToNativeContext());
    }

    public static String toString(byte[] r1) {
        return toString(r1, getDefaultStringEncoding());
    }

    public static String toString(byte[] r0, String r1) {
        return toString(r0, getCharset(r1));
    }

    public static String toString(byte[] r4, Charset r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (r4[r2] == 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        r0 = r2;
    L8:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return new String(r4, 0, r0, r5);
    }

    public static String toString(char[] r4) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (r4[r2] == 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        r0 = r2;
    L8:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return new String(r4, 0, r0);
    }

    public static List<String> toStringList(char[] r2) {
        return toStringList(r2, 0, r2.length);
    }

    public static List<String> toStringList(char[] r4, int r5, int r6) {
        ArrayList r0 = new ArrayList();
        int r62 = r6 + r5;
        int r1 = r5;
    L3:
        if (r5 >= r62) goto L11;
        if (r4[r5] != 0) goto L10;
        if (r1 == r5) goto L13;
        r0.add(new String(r4, r1, r5 - r1));
        r1 = r5 + 1;
    L13:
        return r0;
    L10:
        r5 = r5 + 1;
        goto L3
    L11:
        if (r1 >= r62) goto L13;
        r0.add(new String(r4, r1, r62 - r1));
        goto L13
    }

    public static void unregister() {
        unregister(findDirectMappedClass(getCallingClass()));
    }

    public static void unregister(Class<?> r2) {
        Map<Class<?>, long[]> r0 = registeredClasses;
        monitor-enter(r0);
        long[] r1 = r0.get(r2);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        unregister(r2, r1);     // Catch: Throwable -> L7
        r0.remove(r2);     // Catch: Throwable -> L7
        registeredLibraries.remove(r2);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static native void unregister(Class<?> r0, long[] r1);

    private static void unregisterAll() {
        Map<Class<?>, long[]> r0 = registeredClasses;
        monitor-enter(r0);
        Iterator<Map.Entry<Class<?>, long[]>> r1 = r0.entrySet().iterator();     // Catch: Throwable -> L8
    L6:
        if (r1.hasNext() == false) goto L10;
        Map.Entry<Class<?>, long[]> r2 = r1.next();     // Catch: Throwable -> L8
        unregister(r2.getKey(), r2.getValue());     // Catch: Throwable -> L8
        goto L6
    L10:
        registeredClasses.clear();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static native void write(Pointer r0, long r1, long r3, byte[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, char[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, double[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, float[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, int[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, long[] r5, int r6, int r7);

    public static native void write(Pointer r0, long r1, long r3, short[] r5, int r6, int r7);
}
