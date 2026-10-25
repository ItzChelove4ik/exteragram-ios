package org.lsposed.hiddenapibypass;

import java.lang.invoke.MethodType;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class Helper {
    static final Set<String> signaturePrefixes = null;

    /* loaded from: classes5.dex */
    public static class AccessibleObject {
        private boolean override;

        public AccessibleObject() {
        }
    }

    /* loaded from: classes5.dex */
    public static final class Class {
        private transient int accessFlags;
        private transient int classFlags;
        private transient ClassLoader classLoader;
        private transient int classSize;
        private transient int clinitThreadId;
        private transient java.lang.Class<?> componentType;
        private transient short copiedMethodsOffset;
        private transient Object dexCache;
        private transient int dexClassDefIndex;
        private volatile transient int dexTypeIndex;
        private transient Object extData;
        private transient long iFields;
        private transient Object[] ifTable;
        private transient long methods;
        private transient String name;
        private transient int numReferenceInstanceFields;
        private transient int numReferenceStaticFields;
        private transient int objectSize;
        private transient int objectSizeAllocFastPath;
        private transient int primitiveType;
        private transient int referenceInstanceOffsets;
        private transient long sFields;
        private transient int status;
        private transient java.lang.Class<?> superClass;
        private transient short virtualMethodsOffset;
        private transient Object vtable;

        public Class() {
        }
    }

    /* loaded from: classes5.dex */
    public static final class Executable extends AccessibleObject {
        private int accessFlags;
        private long artMethod;
        private Class declaringClass;
        private Class declaringClassOfOverriddenMethod;
        private Object[] parameters;

        public Executable() {
        }
    }

    public static class InvokeStub {
        private InvokeStub(Object... r1) {
            throw new IllegalStateException("Failed to new a instance");
        }

        private static Object invoke(Object... r1) {
            throw new IllegalStateException("Failed to invoke the method");
        }
    }

    /* loaded from: classes5.dex */
    public static class MethodHandle {
        protected final long artFieldOrMethod;
        private MethodHandle cachedSpreadInvoker;
        protected final int handleKind;
        private MethodType nominalType;
        private final MethodType type;

        public MethodHandle() {
            this.type = null;
            this.handleKind = 0;
            this.artFieldOrMethod = 0;
        }
    }

    public static class NeverCall {
        private static int s;
        private static int t;
        private int i;
        private int j;

        public NeverCall() {
        }

        private static void a() {
        }

        private static void b() {
        }
    }

    static {
        signaturePrefixes = new HashSet();
    }

    public static boolean checkArgsForInvokeMethod(java.lang.Class<?>[] r4, Object[] r5) {
        if (r4.length == r5.length) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r4.length) goto L56;
        if (r4[r0].isPrimitive() == false) goto L50;
        java.lang.Class<?> r1 = r4[r0];
        if (r1 != Integer.TYPE) goto L16;
        if ((r5[r0] instanceof Integer) == true) goto L16;
        return false;
    L16:
        if (r1 != Byte.TYPE) goto L21;
        if ((r5[r0] instanceof Byte) == true) goto L21;
        return false;
    L21:
        if (r1 != Character.TYPE) goto L26;
        if ((r5[r0] instanceof Character) == true) goto L26;
        return false;
    L26:
        if (r1 != Boolean.TYPE) goto L31;
        if ((r5[r0] instanceof Boolean) == true) goto L31;
        return false;
    L31:
        if (r1 != Double.TYPE) goto L36;
        if ((r5[r0] instanceof Double) == true) goto L36;
        return false;
    L36:
        if (r1 != Float.TYPE) goto L41;
        if ((r5[r0] instanceof Float) == true) goto L41;
        return false;
    L41:
        if (r1 != Long.TYPE) goto L46;
        if ((r5[r0] instanceof Long) == true) goto L46;
        return false;
    L46:
        if (r1 != Short.TYPE) goto L55;
        if ((r5[r0] instanceof Short) == true) goto L55;
        return false;
    L55:
        r0 = r0 + 1;
        goto L7
    L50:
        Object r12 = r5[r0];
        if (r12 == null) goto L55;
        if (r4[r0].isInstance(r12) == true) goto L55;
        return false;
    L56:
        return true;
    }
}
