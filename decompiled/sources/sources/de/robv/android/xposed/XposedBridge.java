package de.robv.android.xposed;

import a.a;
import a.c;
import android.util.Log;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import c.d;
import c.e;
import c.f;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.sun.jna.Callback;
import de.robv.android.xposed.XC_MethodHook;
import dev.exterahook.runtime.bridge.HookBridgeProvider;
import dev.exterahook.runtime.bridge.JniBridgeBindings;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;
import org.lsposed.hiddenapibypass.HiddenApiBypass;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class XposedBridge {
    private static final Object[] EMPTY_ARRAY = null;
    private static final String TAG = "exteraHook-XposedBridge";
    private static final Method callbackMethod = null;
    private static final a hookBridge = null;
    private static final d hookRegistry = null;

    public static final class CopyOnWriteSortedSet<E> {
        private volatile transient Object[] elements;

        public CopyOnWriteSortedSet() {
            this.elements = XposedBridge.m2009$$Nest$sfgetEMPTY_ARRAY();
        }

        private int indexOf(Object[] r2, Object r3) {
            int r1 = 0;
        L4:
            if (r1 >= r2.length) goto L9;
            if (r3.equals(r2[r1]) == true) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return r1;
        L9:
            return -1;
        }

        private int insertionIndex(Object[] r4, E r5) {
            int r3 = r4.length;
            int r0 = 0;
            int r1 = 0;
        L3:
            if (r1 >= r3) goto L9;
            if (r5.equals(r4[r1]) == true) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return -1;
        L9:
            Comparable r52 = (Comparable) r5;
        L11:
            if (r0 >= r4.length) goto L17;
            if (r52.compareTo(r4[r0]) < 0) goto L14;
            r0 = r0 + 1;
            goto L11
        L14:
            return r0;
        L17:
            return r4.length;
        }

        public synchronized boolean add(E r6) {
            monitor-enter(this);
            Object[] r0 = this.elements;     // Catch: Throwable -> L11
            int r1 = insertionIndex(r0, r6);     // Catch: Throwable -> L11
            if (r1 >= 0) goto L8;
            monitor-exit(this);
            return false;
        L8:
            Object[] r3 = new Object[r0.length + 1];     // Catch: Throwable -> L11
            System.arraycopy(r0, 0, r3, 0, r1);     // Catch: Throwable -> L11
            r3[r1] = r6;     // Catch: Throwable -> L11
            System.arraycopy(r0, r1, r3, r1 + 1, r0.length - r1);     // Catch: Throwable -> L11
            this.elements = r3;     // Catch: Throwable -> L11
            monitor-exit(this);
            return true;
        L11:
            th = move-exception;
            throw th;
        }

        public Object[] getSnapshot() {
            return this.elements;
        }

        public synchronized boolean remove(E r6) {
            monitor-enter(this);
            Object[] r0 = this.elements;     // Catch: Throwable -> L11
            int r62 = indexOf(r0, r6);     // Catch: Throwable -> L11
            if (r62 != (-1)) goto L8;
            monitor-exit(this);
            return false;
        L8:
            Object[] r1 = new Object[r0.length - 1];     // Catch: Throwable -> L11
            System.arraycopy(r0, 0, r1, 0, r62);     // Catch: Throwable -> L11
            System.arraycopy(r0, r62 + 1, r1, r62, (r0.length - r62) - 1);     // Catch: Throwable -> L11
            this.elements = r1;     // Catch: Throwable -> L11
            monitor-exit(this);
            return true;
        L11:
            th = move-exception;
            throw th;
        }

        public int size() {
            return this.elements.length;
        }
    }

    public static class HookInfo {
        private static final long BACKUP_PUBLISH_TIMEOUT_MS = 1000;
        private volatile Method backup;
        private final Object backupLock;
        private final Class<?> boxedPrimitiveReturnType;
        final CopyOnWriteSortedSet<XC_MethodHook> callbacks;
        private volatile boolean installFailed;
        private volatile Thread installingThread;
        private final boolean isStatic;
        private final Member method;
        private final Class<?> returnType;

        public HookInfo(Member r3) {
            this.backupLock = new Object();
            this.installingThread = Thread.currentThread();
            this.callbacks = new CopyOnWriteSortedSet();
            this.method = r3;
            this.isStatic = Modifier.isStatic(r3.getModifiers());
            if ((r3 instanceof Method) == false) goto L9;
            Class<?> r32 = ((Method) r3).getReturnType();
            if (r32.isPrimitive() == true) goto L8;
            this.returnType = r32;
            this.boxedPrimitiveReturnType = null;
            return;
        L8:
            this.boxedPrimitiveReturnType = boxPrimitiveType(r32);
        L10:
            this.returnType = null;
            return;
        L9:
            this.boxedPrimitiveReturnType = null;
            goto L10
        }

        private Method awaitBackupMethodSlowPath() {
            boolean r1 = false;
            Object r2 = this.backupLock;     // Catch: Throwable -> L39
            monitor-enter(r2);     // Catch: Throwable -> L39
        L16:
            th = move-exception;
            throw th;     // Catch: Throwable -> L39
        L6:
            if (Thread.currentThread() == this.installingThread) goto L36;
            long r3 = System.nanoTime() + 1000000000;     // Catch: Throwable -> L16
            long r5 = 1000;
        L9:
            if (this.backup != null) goto L20;
            if (this.installFailed == true) goto L20;
            if (r5 <= 0) goto L20;
            this.backupLock.wait(r5);     // Catch: Throwable -> L16 InterruptedException -> L18
        L19:
            r5 = (r3 - System.nanoTime()) / 1000000;     // Catch: Throwable -> L16
        L18:
            r1 = true;
        L20:
            Method r0 = this.backup;     // Catch: Throwable -> L16
            if (r0 == null) goto L26;
            monitor-exit(r2);     // Catch: Throwable -> L16
            if (r1 == false) goto L25;
            Thread.currentThread().interrupt();
        L25:
            return r0;
        L26:
            monitor-exit(r2);     // Catch: Throwable -> L16
            if (r1 == false) goto L29;
            Thread.currentThread().interrupt();
        L29:
            boolean r02 = this.installFailed;
            Member r9 = this.method;
            if (r02 == false) goto L33;
            LiveData$$ExternalSyntheticBUOutline0.m("Hooking ", r9, " failed, the original method is unavailable");
            return null;
        L33:
            LiveData$$ExternalSyntheticBUOutline0.m("Backup method for ", r9, " was not published within 1000ms");
            return null;
        L36:
            throw new IllegalStateException("Backup method for " + this.method + " is not published yet, the hook is still being installed on this thread");     // Catch: Throwable -> L16
        L39:
            th = move-exception;
            if (0 == 0) goto L42;
            Thread.currentThread().interrupt();
        L42:
            throw th;
        }

        private static Class<?> boxPrimitiveType(Class<?> r3) {
            if (r3 != Void.TYPE) goto L7;
            return null;
        L7:
            if (r3 != Boolean.TYPE) goto L11;
            return Boolean.class;
        L11:
            if (r3 != Byte.TYPE) goto L15;
            return Byte.class;
        L15:
            if (r3 != Character.TYPE) goto L19;
            return Character.class;
        L19:
            if (r3 != Short.TYPE) goto L23;
            return Short.class;
        L23:
            if (r3 != Integer.TYPE) goto L27;
            return Integer.class;
        L27:
            if (r3 != Long.TYPE) goto L31;
            return Long.class;
        L31:
            if (r3 != Float.TYPE) goto L35;
            return Float.class;
        L35:
            if (r3 != Double.TYPE) goto L39;
            return Double.class;
        L39:
            throw new AssertionError("Unknown primitive type: " + r3);
        }

        public Method awaitBackupMethod() {
            Method r0 = this.backup;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return awaitBackupMethodSlowPath();
        }

        public Object callback(Object[] r1) {
            return HookCallbackDispatcher.dispatch(this, r1);
        }

        public Member getBackup() {
            return this.backup;
        }

        public Class<?> getBoxedPrimitiveReturnType() {
            return this.boxedPrimitiveReturnType;
        }

        public CopyOnWriteSortedSet<XC_MethodHook> getCallbacks() {
            return this.callbacks;
        }

        public Member getMethod() {
            return this.method;
        }

        public Class<?> getReturnType() {
            return this.returnType;
        }

        public boolean isStaticHook() {
            return this.isStatic;
        }

        public void markInstallFailed() {
            Object r0 = this.backupLock;
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.backup == null) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            this.installFailed = true;     // Catch: Throwable -> L8
            this.installingThread = null;     // Catch: Throwable -> L8
            this.backupLock.notifyAll();     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
        }

        public void setBackup(Member r2) {
            Method r22 = (Method) r2;
            if (r22.isAccessible() == true) goto L5;
            r22.setAccessible(true);
        L5:
            Object r0 = this.backupLock;
            monitor-enter(r0);
            this.backup = r22;     // Catch: Throwable -> L10
            this.installingThread = null;     // Catch: Throwable -> L10
            this.backupLock.notifyAll();     // Catch: Throwable -> L10
            monitor-exit(r0);     // Catch: Throwable -> L10
            return;
        L10:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: $r8$lambda$-10kFl-2JjBfEwc-KLdb607Wxxs, reason: not valid java name */
    public static Member m2008$r8$lambda$10kFl2JjBfEwcKLdb607Wxxs(Object r2, Member r3) {
        a r0 = bridge();
        Method r1 = callbackMethod;
        c r02 = (c) r0;
        r02.getClass();
        r02.a();
        return JniBridgeBindings.hook0(r2, r3, r1);
    }

    /* renamed from: -$$Nest$sfgetEMPTY_ARRAY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object[] m2009$$Nest$sfgetEMPTY_ARRAY() {
        return EMPTY_ARRAY;
    }

    static {
        EMPTY_ARRAY = new Object[0];
        callbackMethod = HookInfo.class.getMethod(Callback.METHOD_NAME, new Class[]{Object[].class});
        hookRegistry = new d();
        hookBridge = HookBridgeProvider.createDefault();
        return;
    L6:
        th = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Failed to initialize callback bridge", th);
    }

    public XposedBridge() {
    }

    public static <T> T allocateInstance(Class<T> r1) {
        if (r1 == null) goto L5;
        c r0 = (c) bridge();
        r0.getClass();
        r0.a();
        return (T) JniBridgeBindings.allocateInstance0(r1);
    L5:
        f$$ExternalSyntheticBUOutline2.m("clazz");
        return null;
    }

    private static a bridge() {
        return hookBridge;
    }

    public static boolean deoptimizeMethod(Member r1) {
        f.a(r1);
        c r0 = (c) bridge();
        r0.getClass();
        r0.a();
        return JniBridgeBindings.deoptimize0(r1);
    }

    public static boolean disableHiddenApiRestrictions() {
        b.a r0 = ((c) bridge()).f8b;
        boolean r2 = true;
        if (r0.f11b == false) goto L5;
        return true;
    L5:
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L7:
        if (r0.f11b == false) goto L24;
    L19:
        monitor-exit(r0);
        return r2;
    L24:
        if (HiddenApiBypass.addHiddenApiExemptions(new String[]{_UrlKt.FRAGMENT_ENCODE_SET}) == false) goto L12;
        boolean r1 = true;
    L13:
        if (r1 == false) goto L18;
        r0.f11b = true;     // Catch: Throwable -> L16
    L18:
        r2 = r1;
    L12:
        r0.f10a.f12a.a();     // Catch: Throwable -> L16
        r1 = JniBridgeBindings.disableHiddenApiRestrictions();     // Catch: Throwable -> L16
        goto L13
    }

    public static boolean disableProfileSaver() {
        ((c) bridge()).a();
        return JniBridgeBindings.disableProfileSaver0();
    }

    public static Set<XC_MethodHook.Unhook> hookAllConstructors(Class<?> r4, XC_MethodHook r5) {
        HashSet r0 = new HashSet();
        Constructor<?>[] r42 = r4.getDeclaredConstructors();     // Catch: Throwable -> L7 RuntimeException -> L9
        int r1 = r42.length;     // Catch: Throwable -> L7 RuntimeException -> L9
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L11;
        r0.add(hookMethod(r42[r2], r5));     // Catch: Throwable -> L7 RuntimeException -> L9
        r2 = r2 + 1;
        goto L4
    L11:
        return r0;
    L7:
        e = move-exception;
        rollbackHooks(r0, e);
        throw e;
    }

    public static Set<XC_MethodHook.Unhook> hookAllMethods(Class<?> r5, String r6, XC_MethodHook r7) {
        HashSet r0 = new HashSet();
        Method[] r52 = r5.getDeclaredMethods();     // Catch: Throwable -> L9 RuntimeException -> L11
        int r1 = r52.length;     // Catch: Throwable -> L9 RuntimeException -> L11
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L14;
        Method r3 = r52[r2];     // Catch: Throwable -> L9 RuntimeException -> L11
        if (r3.getName().equals(r6) == false) goto L13;
        r0.add(hookMethod(r3, r7));     // Catch: Throwable -> L9 RuntimeException -> L11
    L13:
        r2 = r2 + 1;
        goto L4
    L14:
        return r0;
    L9:
        e = move-exception;
        rollbackHooks(r0, e);
        throw e;
    }

    public static XC_MethodHook.Unhook hookMethod(Member r4, XC_MethodHook r5) {
        f.a(r4);
        if (r5 == null) goto L17;
        d r0 = hookRegistry;
        XposedBridge$$ExternalSyntheticLambda0 r1 = new XposedBridge$$ExternalSyntheticLambda0();
        r0.getClass();
        HashMap r2 = r0.f13a;
        monitor-enter(r2);
        HookInfo r3 = (HookInfo) r0.f13a.get(r4);     // Catch: Throwable -> L9
        if (r3 == null) goto L11;
        r3.getCallbacks().add(r5);     // Catch: Throwable -> L9
    L12:
        monitor-exit(r2);
        return new XC_MethodHook.Unhook(r5, r4);
    L11:
        r0.a(r4, r5, r1);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    L17:
        f$$ExternalSyntheticBUOutline2.m("callback must not be null");
        return null;
    }

    public static <S, T extends S> boolean invokeConstructor(T r2, Constructor<S> r3, Object... r4) {
        if (r2 == null) goto L16;
        if (r3 != null) goto L6;
        f$$ExternalSyntheticBUOutline2.m("constructor");
        return false;
    L6:
        if (r3.isVarArgs() == false) goto L8;
        f$$ExternalSyntheticBUOutline1.m("varargs parameters are not supported");
        return false;
    L8:
        if (r4.length != 0) goto L10;
        r4 = null;
    L10:
        c r0 = (c) bridge();
        r0.getClass();
        r0.a();
        return JniBridgeBindings.invokeConstructor0(r2, r3, r4);
    L16:
        f$$ExternalSyntheticBUOutline2.m("instance");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object invokeOriginalMethod(Member r2, Object r3, Object[] r4) {
        if (r4 != null) goto L4;
        r4 = EMPTY_ARRAY;
    L4:
        d r0 = hookRegistry;
        r0.getClass();
        HashMap r1 = r0.f13a;
        monitor-enter(r1);
        HookInfo r02 = (HookInfo) r0.f13a.get(r2);     // Catch: Throwable -> L25
        monitor-exit(r1);
        if (r02 != null) goto L10;
        f.a(r2);     // Catch: InstantiationException -> L23
        if (r4 != null) goto L14;
        r4 = e.f14a;     // Catch: InstantiationException -> L23
    L14:
        AccessibleObject r03 = (AccessibleObject) r2;     // Catch: InstantiationException -> L23
        if (r03.isAccessible() == true) goto L18;
        r03.setAccessible(true);     // Catch: InstantiationException -> L23
    L18:
        if ((r2 instanceof Method) == false) goto L22;
        return ((Method) r2).invoke(r3, r4);
    L22:
        return ((Constructor) r2).newInstance(r4);
    L23:
        f$$ExternalSyntheticBUOutline1.m("The class this Constructor belongs to is abstract and cannot be instantiated");
        return null;
    L10:
        return r02.awaitBackupMethod().invoke(r3, r4);
    L25:
        th = move-exception;
        throw th;
    }

    public static boolean isHooked(Member r2) {
        d r0 = hookRegistry;
        r0.getClass();
        HashMap r1 = r0.f13a;
        monitor-enter(r1);
        boolean r22 = r0.f13a.containsKey(r2);     // Catch: Throwable -> L7
        monitor-exit(r1);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    private static boolean isHooked0(Member r1) {
        c r0 = (c) bridge();
        r0.getClass();
        r0.a();
        return JniBridgeBindings.isHooked0(r1);
    }

    public static void log(Throwable r2) {
        Log.e(TAG, "Uncaught Exception", r2);
    }

    public static boolean makeClassInheritable(Class<?> r1) {
        if (r1 == null) goto L5;
        c r0 = (c) bridge();
        r0.getClass();
        r0.a();
        return JniBridgeBindings.makeClassInheritable0(r1);
    L5:
        f$$ExternalSyntheticBUOutline2.m("class must not be null");
        return false;
    }

    private static void rollbackHooks(Set<XC_MethodHook.Unhook> r1, Throwable r2) {
        Iterator<XC_MethodHook.Unhook> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L11;
        r12.next().unhook();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        if (th == r2) goto L4;
        r2.addSuppressed(th);
        goto L4
    }

    @Deprecated
    public static void unhookMethod(Member r5, XC_MethodHook r6) {
        d r0 = hookRegistry;
        a r1 = bridge();
        Objects.requireNonNull(r1);
        XposedBridge$$ExternalSyntheticLambda1 r2 = new XposedBridge$$ExternalSyntheticLambda1(r1);
        r0.getClass();
        HashMap r12 = r0.f13a;
        monitor-enter(r12);
        HookInfo r3 = (HookInfo) r0.f13a.get(r5);     // Catch: Throwable -> L11
        if (r3 != null) goto L8;
        monitor-exit(r12);
        return;
    L8:
        r3.getCallbacks().remove(r6);     // Catch: Throwable -> L11
        if (r3.getCallbacks().size() != 0) goto L13;
        r0.f13a.remove(r5);     // Catch: Throwable -> L11
        r2.a(r5);     // Catch: Throwable -> L11
    L13:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r12);
        return;
    L11:
        th = move-exception;
        throw th;
    }
}
