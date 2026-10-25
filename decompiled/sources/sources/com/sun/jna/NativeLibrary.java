package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline2;
import com.sun.jna.internal.Cleaner;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class NativeLibrary implements Closeable {
    private static final Level DEBUG_LOAD_LEVEL = null;
    private static final int DEFAULT_OPEN_OPTIONS = -1;
    private static final Logger LOG = null;
    private static final SymbolProvider NATIVE_SYMBOL_PROVIDER = null;
    private static Method addSuppressedMethod;
    private static final Map<String, Reference<NativeLibrary>> libraries = null;
    private static final LinkedHashSet<String> librarySearchPath = null;
    private static final Map<String, List<String>> searchPaths = null;
    private final int callFlags;
    private final Cleaner.Cleanable cleanable;
    private final String encoding;
    private final Map<String, Function> functions;
    private volatile long handle;
    private final String libraryName;
    private final String libraryPath;
    private final Map<String, ?> options;
    private final SymbolProvider symbolProvider;

    /* renamed from: com.sun.jna.NativeLibrary$1, reason: invalid class name */
    public class AnonymousClass1 implements SymbolProvider {
        public AnonymousClass1() {
        }

        @Override // com.sun.jna.SymbolProvider
        public long getSymbolAddress(long r1, String r3, SymbolProvider r4) {
            return Native.findSymbol(r1, r3);
        }
    }

    /* renamed from: com.sun.jna.NativeLibrary$2, reason: invalid class name */
    public class AnonymousClass2 extends Function {
        final /* synthetic */ NativeLibrary this$0;

        public AnonymousClass2(NativeLibrary r1, NativeLibrary r2, String r3, int r4, String r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // com.sun.jna.Function
        public Object invoke(Method r1, Class<?>[] r2, Class<?> r3, Object[] r4, Map<String, ?> r5) {
            return Integer.valueOf(Native.getLastError());
        }

        @Override // com.sun.jna.Function
        public Object invoke(Object[] r1, Class<?> r2, boolean r3, int r4) {
            return Integer.valueOf(Native.getLastError());
        }
    }

    /* renamed from: com.sun.jna.NativeLibrary$3, reason: invalid class name */
    public class AnonymousClass3 implements FilenameFilter {
        final /* synthetic */ String val$libName;

        public AnonymousClass3(String r1) {
            this.val$libName = r1;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File r4, String r5) {
            if (r5.startsWith("lib" + this.val$libName + ".so") == true) goto L9;
            if (r5.startsWith(this.val$libName + ".so") == true) goto L7;
            return false;
        L7:
            if (this.val$libName.startsWith("lib") == true) goto L9;
            return false;
        L9:
            if (NativeLibrary.access$000(r5) == false) goto L15;
            return true;
        L15:
            return false;
        }
    }

    public static final class NativeLibraryDisposer implements Runnable {
        private long handle;

        public NativeLibraryDisposer(long r1) {
            this.handle = r1;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            monitor-enter(this);
            long r0 = this.handle;     // Catch: Throwable -> L8
            if (r0 != 0) goto L17;
        L13:
            monitor-exit(this);
            return;
        L17:
            Native.close(r0);     // Catch: Throwable -> L10
            this.handle = 0;     // Catch: Throwable -> L8
            goto L13
        L10:
            th = move-exception;
            this.handle = 0;     // Catch: Throwable -> L8
            throw th;     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            throw th;
        }
    }

    static {
        LOG = Logger.getLogger(NativeLibrary.class.getName());
        if (Native.DEBUG_LOAD == false) goto L5;
        Level r2 = Level.INFO;
    L6:
        DEBUG_LOAD_LEVEL = r2;
        NATIVE_SYMBOL_PROVIDER = new AnonymousClass1();
        libraries = new HashMap();
        searchPaths = new ConcurrentHashMap();
        librarySearchPath = new LinkedHashSet();
        if (Native.POINTER_SIZE == 0) goto L66;
        addSuppressedMethod = null;
        addSuppressedMethod = Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});     // Catch: SecurityException -> L11 NoSuchMethodException -> L67
    L11:
        e = move-exception;
        Logger.getLogger(NativeLibrary.class.getName()).log(Level.SEVERE, "Failed to initialize 'addSuppressed' method", e);
    L13:
        String r0 = Native.getWebStartLibraryPath("jnidispatch");
        if (r0 == null) goto L17;
        librarySearchPath.add(r0);
    L17:
        if (System.getProperty("jna.platform.library.path") == null) goto L19;
    L63:
        librarySearchPath.addAll(initPaths("jna.platform.library.path"));
        return;
    L19:
        if (Platform.isWindows() == true) goto L63;
        if (Platform.isLinux() == false) goto L23;
    L30:
        StringBuilder r1 = new StringBuilder();
        if (Platform.isSolaris() == false) goto L33;
        String r3 = "/";
    L34:
        r1.append(r3);
        r1.append(Native.POINTER_SIZE * 8);
        String r12 = r1.toString();
    L35:
        int r7 = 0;
        String[] r4 = {"/usr/lib".concat(r12), "/lib".concat(r12), "/usr/lib", "/lib"};
        if (Platform.isLinux() == false) goto L38;
    L41:
        String r42 = getMultiArchPath();
        r4 = new String[]{"/usr/lib/" + r42, "/lib/" + r42, "/usr/lib".concat(r12), "/lib".concat(r12), "/usr/lib", "/lib"};
    L43:
        if (Platform.isLinux() == false) goto L51;
        ArrayList<String> r13 = getLinuxLdPaths();
        int r32 = r4.length - 1;
    L45:
        if (r32 < 0) goto L50;
        int r5 = r13.indexOf(r4[r32]);
        if (r5 == (-1)) goto L49;
        r13.remove(r5);
    L49:
        r13.add(0, r4[r32]);
        r32 = r32 - 1;
        goto L45
    L50:
        r4 = (String[]) r13.toArray(new String[0]);
    L51:
        String r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r33 = r14;
    L53:
        if (r7 >= r4.length) goto L61;
        File r52 = new File(r4[r7]);
        if (r52.exists() == false) goto L59;
        if (r52.isDirectory() == false) goto L59;
        r14 = r14 + r33 + r4[r7];
        r33 = File.pathSeparator;
    L59:
        r7 = r7 + 1;
        goto L53
    L61:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r14) == true) goto L63;
        System.setProperty("jna.platform.library.path", r14);
        goto L63
    L38:
        if (Platform.iskFreeBSD() == true) goto L41;
        if (Platform.isGNU() == false) goto L43;
    L33:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L34
    L23:
        if (Platform.isSolaris() == true) goto L30;
        if (Platform.isFreeBSD() == true) goto L30;
        if (Platform.iskFreeBSD() == true) goto L30;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L35
    L66:
        throw new Error("Native library not initialized");
    L5:
        r2 = Level.FINE;
        goto L6
    }

    private NativeLibrary(String r9, String r10, long r11, Map<String, ?> r13) {
        HashMap r1 = new HashMap();
        this.functions = r1;
        String r92 = getLibraryName(r9);
        this.libraryName = r92;
        this.libraryPath = r10;
        this.handle = r11;
        this.cleanable = Cleaner.getCleaner().register(this, new NativeLibraryDisposer(r11));
        Object r102 = r13.get(Library.OPTION_CALLING_CONVENTION);
        if ((r102 instanceof Number) == false) goto L5;
        int r103 = ((Number) r102).intValue();
    L6:
        this.callFlags = r103;
        this.options = r13;
        SymbolProvider r112 = (SymbolProvider) r13.get(Library.OPTION_SYMBOL_PROVIDER);
        if (r112 != null) goto L9;
        this.symbolProvider = NATIVE_SYMBOL_PROVIDER;
    L10:
        String r113 = (String) r13.get(Library.OPTION_STRING_ENCODING);
        if (r113 != null) goto L13;
        r113 = Native.getDefaultStringEncoding();
    L13:
        String r7 = r113;
        this.encoding = r7;
        if (Platform.isWindows() == true) goto L16;
        return;
    L16:
        if ("kernel32".equals(r92.toLowerCase()) == false) goto L28;
        monitor-enter(r1);
        AnonymousClass2 r2 = new AnonymousClass2(this, this, "GetLastError", 63, r7);     // Catch: Throwable -> L21
        r1.put(functionKey("GetLastError", r103, r7), r2);     // Catch: Throwable -> L21
        monitor-exit(r1);     // Catch: Throwable -> L21
        return;
    L21:
        th = move-exception;
        throw th;
    L28:
        return;
    L9:
        this.symbolProvider = r112;
        goto L10
    L5:
        r103 = 0;
        goto L6
    }

    public static /* synthetic */ boolean access$000(String r0) {
        return isVersionedName(r0);
    }

    public static final void addSearchPath(String r2, String r3) {
        Map<String, List<String>> r0 = searchPaths;
        List<String> r1 = r0.get(r2);
        if (r1 != null) goto L5;
        r1 = Collections.synchronizedList(new ArrayList());
        r0.put(r2, r1);
    L5:
        r1.add(r3);
    }

    private static void addSuppressedReflected(Throwable r1, Throwable r2) {
        Method r0 = addSuppressedMethod;
        if (r0 != null) goto L10;
        return;
    L10:
        r0.invoke(r1, new Object[]{r2});     // Catch: Throwable -> L7
        return;
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Failed to call addSuppressedMethod", e);
    }

    public static void disposeAll() {
        Map<String, Reference<NativeLibrary>> r0 = libraries;
        monitor-enter(r0);
        LinkedHashSet r1 = new LinkedHashSet(r0.values());     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        Iterator r02 = r1.iterator();
    L8:
        if (r02.hasNext() == false) goto L12;
        NativeLibrary r12 = (NativeLibrary) ((Reference) r02.next()).get();
        if (r12 == null) goto L8;
        r12.close();
        goto L8
    L12:
        return;
    L13:
        th = move-exception;
        throw th;
    }

    private static String findLibraryPath(String r4, Collection<String> r5) {
        if (new File(r4).isAbsolute() == false) goto L5;
        return r4;
    L5:
        String r42 = mapSharedLibraryName(r4);
        Iterator<String> r52 = r5.iterator();
    L7:
        if (r52.hasNext() == false) goto L29;
        String r0 = r52.next();
        File r1 = new File(r0, r42);
        if (r1.exists() == true) goto L11;
        if (Platform.isMac() == false) goto L7;
        if (r42.endsWith(".dylib") == false) goto L7;
        File r2 = new File(r0, r42.substring(0, r42.lastIndexOf(".dylib")).concat(".jnilib"));
        if (r2.exists() == false) goto L7;
        return r2.getAbsolutePath();
    L11:
        return r1.getAbsolutePath();
    L29:
        return r42;
    }

    private static String functionKey(String r1, int r2, String r3) {
        return r1 + "|" + r2 + "|" + r3;
    }

    public static final NativeLibrary getInstance(String r1) {
        return getInstance(r1, Collections.EMPTY_MAP);
    }

    public static final NativeLibrary getInstance(String r1, ClassLoader r2) {
        return getInstance(r1, Collections.singletonMap(Library.OPTION_CLASSLOADER, r2));
    }

    public static final NativeLibrary getInstance(String r6, Map<String, ?> r7) {
        HashMap r5 = new HashMap(r7);
        if (r5.get(Library.OPTION_CALLING_CONVENTION) != null) goto L6;
        r5.put(Library.OPTION_CALLING_CONVENTION, 0);
    L6:
        if (Platform.isLinux() == true) goto L12;
        if (Platform.isFreeBSD() == true) goto L12;
        if (Platform.isAIX() == true) goto L12;
    L14:
        Map<String, Reference<NativeLibrary>> r72 = libraries;
        monitor-enter(r72);
        Reference<NativeLibrary> r1 = r72.get(r6 + r5);     // Catch: Throwable -> L19
        if (r1 == null) goto L21;
        NativeLibrary r12 = r1.get();     // Catch: Throwable -> L19
    L22:
        if (r12 != null) goto L29;
        if (r6 != null) goto L25;
        r12 = new NativeLibrary("<process>", null, Native.open(null, openFlags(r5)), r5);     // Catch: Throwable -> L19
    L26:
        WeakReference r62 = new WeakReference(r12);     // Catch: Throwable -> L19
        r72.put(r12.getName() + r5, r62);     // Catch: Throwable -> L19
        File r0 = r12.getFile();     // Catch: Throwable -> L19
        if (r0 == null) goto L29;
        r72.put(r0.getAbsolutePath() + r5, r62);     // Catch: Throwable -> L19
        r72.put(r0.getName() + r5, r62);     // Catch: Throwable -> L19
        goto L29
    L25:
        r12 = loadLibrary(r6, r5);     // Catch: Throwable -> L19
    L29:
        monitor-exit(r72);     // Catch: Throwable -> L19
        return r12;
    L21:
        r12 = null;
    L19:
        th = move-exception;
        throw th;
    L12:
        if (Platform.C_LIBRARY_NAME.equals(r6) == false) goto L14;
        r6 = null;
        goto L14
    }

    private String getLibraryName(String r4) {
        String r0 = mapSharedLibraryName("---");
        int r3 = r0.indexOf("---");
        if (r3 > 0) goto L5;
    L7:
        int r32 = r4.indexOf(r0.substring(r3 + 3));
        if (r32 != (-1)) goto L10;
        return r4;
    L10:
        return r4.substring(0, r32);
    L5:
        if (r4.startsWith(r0.substring(0, r3)) == false) goto L7;
        r4 = r4.substring(r3);
        goto L7
    }

    private static ArrayList<String> getLinuxLdPaths() {
        ArrayList<String> r0 = new ArrayList();
        BufferedReader r1 = null;
        Process r2 = Runtime.getRuntime().exec("/sbin/ldconfig -p");     // Catch: Throwable -> L23 Exception -> L25
        BufferedReader r3 = new BufferedReader(new InputStreamReader(r2.getInputStream()));     // Catch: Throwable -> L21 Exception -> L40
    L56:
        String r12 = r3.readLine();     // Catch: Throwable -> L15 Exception -> L17
        if (r12 == null) goto L50;
        int r4 = r12.indexOf(" => ");     // Catch: Throwable -> L15 Exception -> L17
        int r5 = r12.lastIndexOf(47);     // Catch: Throwable -> L15 Exception -> L17
        if (r4 == (-1)) goto L56;
        if (r5 == (-1)) goto L56;
        if (r4 >= r5) goto L56;
        String r13 = r12.substring(r4 + 4, r5);     // Catch: Throwable -> L15 Exception -> L17
        if (r0.contains(r13) == true) goto L56;
        r0.add(r13);     // Catch: Throwable -> L15 Exception -> L17
        goto L56
    L50:
        r3.close();     // Catch: IOException -> L36
    L46:
        r2.waitFor();     // Catch: InterruptedException -> L37
    L35:
        return r0;
    L17:
        r1 = r3;
    L15:
        th = th;
        r1 = r3;
    L26:
        if (r1 != null) goto L48;
    L28:
        if (r2 == null) goto L68;
        r2.waitFor();     // Catch: InterruptedException -> L39
        throw th;
    L69:
        throw th;
    L68:
        throw th;
    L48:
        r1.close();     // Catch: IOException -> L38
    L21:
        th = th;
    L31:
        if (r1 != null) goto L42;
    L33:
        if (r2 == null) goto L35;
    L42:
        r1.close();     // Catch: IOException -> L41
        goto L33
    L25:
        r2 = null;
    L23:
        th = th;
        r2 = null;
        goto L26
    }

    private static String getMultiArchPath() {
        String r0 = Platform.ARCH;
        if (Platform.iskFreeBSD() == false) goto L6;
        String r1 = "-kfreebsd";
    L9:
        String r3 = "-gnu";
        if (Platform.isIntel() == false) goto L16;
        if (Platform.is64Bit() == false) goto L14;
        r0 = "x86_64";
    L28:
        return r0 + r1 + r3;
    L14:
        r0 = "i386";
        goto L28
    L16:
        if (Platform.isPPC() == false) goto L22;
        if (Platform.is64Bit() == false) goto L20;
        r0 = "powerpc64";
        goto L28
    L20:
        r0 = "powerpc";
        goto L28
    L22:
        if (Platform.isARM() == false) goto L25;
        r0 = "arm";
        r3 = "-gnueabi";
        goto L28
    L25:
        if (r0.equals("mips64el") == false) goto L28;
        r3 = "-gnuabi64";
        goto L28
    L6:
        if (Platform.isGNU() == false) goto L8;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    L8:
        r1 = "-linux";
        goto L9
    }

    public static final synchronized NativeLibrary getProcess() {
        monitor-enter(NativeLibrary.class);
        NativeLibrary r1 = getInstance(null);     // Catch: Throwable -> L8
        monitor-exit(NativeLibrary.class);
        return r1;
    L8:
        th = move-exception;
        throw th;
    }

    public static final synchronized NativeLibrary getProcess(Map<String, ?> r2) {
        monitor-enter(NativeLibrary.class);
        NativeLibrary r22 = getInstance(null, r2);     // Catch: Throwable -> L8
        monitor-exit(NativeLibrary.class);
        return r22;
    L8:
        th = move-exception;
        throw th;
    }

    private static List<String> initPaths(String r4) {
        String r42 = System.getProperty(r4, _UrlKt.FRAGMENT_ENCODE_SET);
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r42) == true) goto L5;
        StringTokenizer r1 = new StringTokenizer(r42, File.pathSeparator);
        ArrayList r43 = new ArrayList();
    L8:
        if (r1.hasMoreTokens() == false) goto L12;
        String r2 = r1.nextToken();
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r2) == true) goto L8;
        r43.add(r2);
        goto L8
    L12:
        return r43;
    L5:
        return Collections.EMPTY_LIST;
    }

    private static boolean isVersionedName(String r4) {
        if (r4.startsWith("lib") == false) goto L18;
        int r0 = r4.lastIndexOf(".so.");
        if (r0 == (-1)) goto L18;
        int r02 = r0 + 4;
        if (r02 >= r4.length()) goto L18;
    L9:
        if (r02 >= r4.length()) goto L16;
        char r2 = r4.charAt(r02);
        if (Character.isDigit(r2) == true) goto L15;
        if (r2 == '.') goto L15;
        return false;
    L15:
        r02 = r02 + 1;
        goto L9
    L16:
        return true;
    L18:
        return false;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    private static NativeLibrary loadLibrary(String r18, Map<String, ?> r19) {
        Logger r5 = LOG;
        Level r6 = DEBUG_LOAD_LEVEL;
        r5.log(r6, "Looking for library '" + r18 + "'");
        ArrayList r7 = new ArrayList();
        boolean r8 = new File(r18).isAbsolute();
        LinkedHashSet r9 = new LinkedHashSet();
        int r10 = openFlags(r19);
        List<String> r11 = searchPaths.get(r18);
        if (r11 == null) goto L11;
        monitor-enter(r11);
        r9.addAll(r11);     // Catch: Throwable -> L8
        monitor-exit(r11);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    L11:
        String r112 = Native.getWebStartLibraryPath(r18);
        if (r112 == null) goto L14;
        r5.log(r6, "Adding web start path ".concat(r112));
        r9.add(r112);
    L14:
        r5.log(r6, "Adding paths from jna.library.path: " + System.getProperty("jna.library.path"));
        r9.addAll(initPaths("jna.library.path"));
        String r113 = findLibraryPath(r18, r9);
        long r12 = 0;
        r5.log(r6, "Trying " + r113);     // Catch: UnsatisfiedLinkError -> L17
        long r52 = Native.open(r113, r10);     // Catch: UnsatisfiedLinkError -> L17
    L20:
        if (r52 == 0) goto L120;
    L23:
        Map<String, ?> r2 = r19;
    L101:
        long r3 = r52;
        LOG.log(DEBUG_LOAD_LEVEL, "Found library '" + r18 + "' at " + r113);
        return new NativeLibrary(r18, r113, r3, r2);
    L120:
        r113 = findLibraryPath(r18, r9);     // Catch: UnsatisfiedLinkError -> L26
        LOG.log(DEBUG_LOAD_LEVEL, "Trying " + r113);     // Catch: UnsatisfiedLinkError -> L26
        r52 = Native.open(r113, r10);     // Catch: UnsatisfiedLinkError -> L26
        if (r52 != 0) goto L23;
        throw new UnsatisfiedLinkError("Failed to load library '" + r18 + "'");     // Catch: UnsatisfiedLinkError -> L26
    L26:
        e = move-exception;
        Logger r32 = LOG;
        Level r4 = DEBUG_LOAD_LEVEL;
        r32.log(r4, "Loading failed with message: " + e.getMessage());
        r7.add(e);
        int r14 = 0;
        if (Platform.isAndroid() == false) goto L34;
        r32.log(r4, "Preload (via System.loadLibrary) " + r18);     // Catch: UnsatisfiedLinkError -> L31
        System.loadLibrary(r18);     // Catch: UnsatisfiedLinkError -> L31
        r52 = Native.open(r113, r10);     // Catch: UnsatisfiedLinkError -> L31
    L30:
        long r16 = r12;
    L72:
        if (r52 == r16) goto L74;
        r2 = r19;
    L93:
        if (r52 != r16) goto L101;
        StringBuilder r0 = new StringBuilder("Unable to load library '");
        r0.append(r18);
        r0.append("':");
        int r1 = r7.size();
        int r22 = 0;
    L95:
        if (r22 >= r1) goto L97;
        Object r33 = r7.get(r22);
        r22 = r22 + 1;
        r0.append("\n");
        r0.append(((Throwable) r33).getMessage());
        goto L95
    L97:
        UnsatisfiedLinkError r13 = new UnsatisfiedLinkError(r0.toString());
        int r02 = r7.size();
    L98:
        if (r14 >= r02) goto L100;
        Object r23 = r7.get(r14);
        r14 = r14 + 1;
        addSuppressedReflected(r13, (Throwable) r23);
        goto L98
    L100:
        throw r13;
    L74:
        r2 = r19;
        File r34 = Native.extractFromResourcePath(r18, (ClassLoader) r2.get(Library.OPTION_CLASSLOADER));     // Catch: IOException -> L81
        if (r34 == null) goto L93;
        r52 = Native.open(r34.getAbsolutePath(), r10);     // Catch: Throwable -> L83
        r113 = r34.getAbsolutePath();     // Catch: Throwable -> L83
    L79:
        if (Native.isUnpacked(r34) == false) goto L93;
        Native.deleteLibrary(r34);     // Catch: IOException -> L81
    L83:
        th = move-exception;
        if (Native.isUnpacked(r34) == false) goto L87;
        Native.deleteLibrary(r34);     // Catch: IOException -> L81
    L87:
        throw th;     // Catch: IOException -> L81
    L81:
        e = move-exception;
        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
        r7.add(e);
    L31:
        e = move-exception;
        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
        r7.add(e);
        goto L30
    L34:
        if (Platform.isLinux() == false) goto L36;
    L37:
        r16 = 0;
        r32.log(r4, "Looking for version variants");
        r113 = matchLibrary(r18, r9);
        if (r113 == null) goto L72;
        r32.log(r4, "Trying ".concat(r113));
        r52 = Native.open(r113, r10);     // Catch: UnsatisfiedLinkError -> L69
    L69:
        e = move-exception;
        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
        r7.add(e);
        goto L72
    L36:
        if (Platform.isFreeBSD() == true) goto L37;
        if (Platform.isMac() == true) goto L41;
    L54:
        r16 = 0;
        if (Platform.isWindows() == false) goto L72;
        if (r8 == true) goto L72;
        r32.log(r4, "Looking for lib- prefix");
        r113 = findLibraryPath("lib" + r18, r9);
        if (r113 == null) goto L72;
        r32.log(r4, "Trying ".concat(r113));
        r52 = Native.open(r113, r10);     // Catch: UnsatisfiedLinkError -> L62
    L62:
        e = move-exception;
        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
        r7.add(e);
        goto L72
    L41:
        if (r18.endsWith(".dylib") == true) goto L54;
        String[] r24 = matchFramework(r18);
        int r35 = r24.length;
        int r42 = 0;
    L43:
        if (r42 >= r35) goto L30;
        String r03 = r24[r42];
        Logger r82 = LOG;     // Catch: UnsatisfiedLinkError -> L51
        Level r92 = DEBUG_LOAD_LEVEL;     // Catch: UnsatisfiedLinkError -> L51
        StringBuilder r15 = new StringBuilder();     // Catch: UnsatisfiedLinkError -> L51
        r16 = r12;
        r15.append("Trying ");     // Catch: UnsatisfiedLinkError -> L49
        r15.append(r03);     // Catch: UnsatisfiedLinkError -> L49
        r82.log(r92, r15.toString());     // Catch: UnsatisfiedLinkError -> L49
        r52 = Native.open(r03, r10);     // Catch: UnsatisfiedLinkError -> L49
    L49:
        e = e;
    L53:
        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
        r7.add(e);
        r42 = r42 + 1;
        r12 = r16;
    L51:
        e = e;
        r16 = r12;
    L17:
        e = move-exception;
        Logger r53 = LOG;
        Level r62 = DEBUG_LOAD_LEVEL;
        r53.log(r62, "Loading failed with message: " + e.getMessage());
        StringBuilder r142 = new StringBuilder("Adding system paths: ");
        LinkedHashSet<String> r152 = librarySearchPath;
        r142.append(r152);
        r53.log(r62, r142.toString());
        r7.add(e);
        r9.addAll(r152);
        r52 = 0;
        goto L20
    }

    public static String mapSharedLibraryName(String r4) {
        if (Platform.isMac() == false) goto L16;
        if (r4.startsWith("lib") == true) goto L7;
    L11:
        String r42 = System.mapLibraryName(r4);
        if (r42.endsWith(".jnilib") == true) goto L14;
        return r42;
    L14:
        return r42.substring(0, r42.lastIndexOf(".jnilib")).concat(".dylib");
    L7:
        if (r4.endsWith(".dylib") == false) goto L9;
    L10:
        return r4;
    L9:
        if (r4.endsWith(".jnilib") == false) goto L11;
    L16:
        if (Platform.isLinux() == true) goto L41;
        if (Platform.isFreeBSD() == true) goto L41;
        if (Platform.isAIX() == false) goto L32;
        if (isVersionedName(r4) == false) goto L25;
        return r4;
    L25:
        if (r4.endsWith(".so") == false) goto L27;
        return r4;
    L27:
        if (r4.startsWith("lib") == false) goto L29;
        return r4;
    L29:
        if (r4.endsWith(".a") == true) goto L58;
    L45:
        String r43 = System.mapLibraryName(r4);
        if (Platform.isAIX() == true) goto L48;
        return r43;
    L48:
        if (r43.endsWith(".so") == true) goto L50;
        return r43;
    L50:
        return r43.replaceAll(".so$", ".a");
    L58:
        return r4;
    L32:
        if (Platform.isWindows() == false) goto L45;
        if (r4.endsWith(".drv") == false) goto L36;
    L39:
        return r4;
    L36:
        if (r4.endsWith(".dll") == true) goto L39;
        if (r4.endsWith(".ocx") == false) goto L45;
    L41:
        if (isVersionedName(r4) == false) goto L43;
        return r4;
    L43:
        if (r4.endsWith(".so") == false) goto L45;
        return r4;
    }

    public static String[] matchFramework(String r8) {
        LinkedHashSet r0 = new LinkedHashSet();
        File r1 = new File(r8);
        if (r1.isAbsolute() == true) goto L5;
        String[] r12 = {System.getProperty("user.home"), _UrlKt.FRAGMENT_ENCODE_SET, "/System"};
        if (r8.contains(".framework") == true) goto L19;
        r8 = r8 + ".framework/" + r8;
    L19:
        int r2 = 0;
    L21:
        if (r2 >= 3) goto L28;
        File r5 = new File(r12[r2] + "/Library/Frameworks/" + r8);
        if (r5.exists() == true) goto L25;
        r0.add(r5.getAbsolutePath());
        r2 = r2 + 1;
        goto L21
    L25:
        return new String[]{r5.getAbsolutePath()};
    L28:
        return (String[]) r0.toArray(new String[0]);
    L5:
        if (r8.contains(".framework") == true) goto L7;
        File r82 = new File(new File(r1.getParentFile(), r1.getName() + ".framework"), r1.getName());
        if (r82.exists() == true) goto L14;
        r0.add(r82.getAbsolutePath());
        goto L28
    L14:
        return new String[]{r82.getAbsolutePath()};
    L7:
        if (r1.exists() == true) goto L9;
        r0.add(r1.getAbsolutePath());
        goto L28
    L9:
        return new String[]{r1.getAbsolutePath()};
    }

    public static String matchLibrary(String r6, Collection<String> r7) {
        File r0 = new File(r6);
        if (r0.isAbsolute() == false) goto L5;
        r7 = Arrays.asList(new String[]{r0.getParent()});
    L5:
        AnonymousClass3 r02 = new AnonymousClass3(r6);
        LinkedList r62 = new LinkedList();
        Iterator<String> r72 = r7.iterator();
    L7:
        if (r72.hasNext() == false) goto L13;
        File[] r1 = new File(r72.next()).listFiles(r02);
        if (r1 == null) goto L7;
        if (r1.length <= 0) goto L7;
        r62.addAll(Arrays.asList(r1));
        goto L7
    L13:
        Iterator r63 = r62.iterator();
        double r03 = -1.0d;
        String r73 = null;
    L15:
        if (r63.hasNext() == false) goto L19;
        String r2 = ((File) r63.next()).getAbsolutePath();
        double r3 = parseVersion(r2.substring(r2.lastIndexOf(".so.") + 4));
        if (r3 <= r03) goto L15;
        r73 = r2;
        r03 = r3;
        goto L15
    L19:
        return r73;
    }

    private static int openFlags(Map<String, ?> r1) {
        Object r12 = r1.get(Library.OPTION_OPEN_FLAGS);
        if ((r12 instanceof Number) == true) goto L5;
        return -1;
    L5:
        return ((Number) r12).intValue();
    }

    public static double parseVersion(String r12) {
        int r1 = r12.indexOf(".");
        double r4 = 1.0d;
        double r6 = 0.0d;
    L3:
        if (r12 == null) goto L11;
        if (r1 == (-1)) goto L7;
        String r8 = r12.substring(0, r1);
        String r122 = r12.substring(r1 + 1);
        r1 = r122.indexOf(".");
        String r82 = r122;
        r12 = r8;
    L12:
        r6 = r6 + (Integer.parseInt(r12) / r4);
        r4 = r4 * 100.0d;
        r12 = r82;
    L10:
        return 0.0d;
    L7:
        r82 = null;
        goto L12
    L11:
        return r6;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        HashSet r0 = new HashSet();
        Map<String, Reference<NativeLibrary>> r1 = libraries;
        monitor-enter(r1);
        Iterator<Map.Entry<String, Reference<NativeLibrary>>> r2 = r1.entrySet().iterator();     // Catch: Throwable -> L10
    L6:
        if (r2.hasNext() == false) goto L12;
        Map.Entry<String, Reference<NativeLibrary>> r3 = r2.next();     // Catch: Throwable -> L10
        if (r3.getValue().get() != this) goto L6;
        r0.add(r3.getKey());     // Catch: Throwable -> L10
        goto L6
    L12:
        Iterator r02 = r0.iterator();     // Catch: Throwable -> L10
    L14:
        if (r02.hasNext() == false) goto L16;
        String r22 = (String) r02.next();     // Catch: Throwable -> L10
        libraries.remove(r22);     // Catch: Throwable -> L10
        goto L14
    L16:
        monitor-exit(r1);     // Catch: Throwable -> L10
        monitor-enter(this);
    L21:
        th = move-exception;
        throw th;
    L19:
        if (this.handle == 0) goto L23;
        this.handle = 0;     // Catch: Throwable -> L21
        this.cleanable.clean();     // Catch: Throwable -> L21
    L23:
        monitor-exit(this);     // Catch: Throwable -> L21
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public File getFile() {
        if (this.libraryPath != null) goto L7;
        return null;
    L7:
        return new File(this.libraryPath);
    }

    public Function getFunction(String r2) {
        return getFunction(r2, this.callFlags);
    }

    public Function getFunction(String r2, int r3) {
        return getFunction(r2, r3, this.encoding);
    }

    public Function getFunction(String r4, int r5, String r6) {
        if (r4 == null) goto L14;
        Map<String, Function> r0 = this.functions;
        monitor-enter(r0);
        String r1 = functionKey(r4, r5, r6);     // Catch: Throwable -> L8
        Function r2 = this.functions.get(r1);     // Catch: Throwable -> L8
        if (r2 != null) goto L10;
        r2 = new Function(this, r4, r5, r6);     // Catch: Throwable -> L8
        this.functions.put(r1, r2);     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r2;
    L8:
        th = move-exception;
        throw th;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Function name may not be null");
        return null;
    }

    public Function getFunction(String r5, Method r6) {
        FunctionMapper r0 = (FunctionMapper) this.options.get(Library.OPTION_FUNCTION_MAPPER);
        if (r0 == null) goto L5;
        r5 = r0.getFunctionName(this, r6);
    L5:
        String r02 = System.getProperty("jna.profiler.prefix", "$$YJP$$");
        if (r5.startsWith(r02) == false) goto L8;
        r5 = r5.substring(r02.length());
    L8:
        int r03 = this.callFlags;
        Class<?>[] r62 = r6.getExceptionTypes();
        int r1 = 0;
    L10:
        if (r1 >= r62.length) goto L16;
        if (LastErrorException.class.isAssignableFrom(r62[r1]) == false) goto L14;
        r03 = r03 | 64;
    L14:
        r1 = r1 + 1;
        goto L10
    L16:
        return getFunction(r5, r03);
    }

    public Pointer getGlobalVariableAddress(String r4) {
        return new Pointer(getSymbolAddress(r4));
    L4:
        e = move-exception;
        throw new UnsatisfiedLinkError("Error looking up '" + r4 + "': " + e.getMessage());
    }

    public String getName() {
        return this.libraryName;
    }

    public Map<String, ?> getOptions() {
        return this.options;
    }

    public long getSymbolAddress(String r5) {
        if (this.handle == 0) goto L7;
        return this.symbolProvider.getSymbolAddress(this.handle, r5, NATIVE_SYMBOL_PROVIDER);
    L7:
        throw new UnsatisfiedLinkError("Library has been unloaded");
    }

    public String toString() {
        return "Native Library <" + this.libraryPath + "@" + this.handle + ">";
    }
}
