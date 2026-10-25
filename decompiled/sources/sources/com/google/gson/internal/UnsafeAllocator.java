package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public abstract class UnsafeAllocator {
    public static final UnsafeAllocator INSTANCE = null;

    /* renamed from: com.google.gson.internal.UnsafeAllocator$1, reason: invalid class name */
    public class AnonymousClass1 extends UnsafeAllocator {
        final /* synthetic */ Method val$allocateInstance;
        final /* synthetic */ Object val$unsafe;

        public AnonymousClass1(Method r1, Object r2) {
            this.val$allocateInstance = r1;
            this.val$unsafe = r2;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> r2) {
            UnsafeAllocator.access$000(r2);
            return (T) this.val$allocateInstance.invoke(this.val$unsafe, new Object[]{r2});
        }
    }

    /* renamed from: com.google.gson.internal.UnsafeAllocator$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 extends UnsafeAllocator {
        final /* synthetic */ int val$constructorId;
        final /* synthetic */ Method val$newInstance;

        public AnonymousClass2(Method r1, int r2) {
            this.val$newInstance = r1;
            this.val$constructorId = r2;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> r2) {
            UnsafeAllocator.access$000(r2);
            return (T) this.val$newInstance.invoke(null, new Object[]{r2, Integer.valueOf(this.val$constructorId)});
        }
    }

    /* renamed from: com.google.gson.internal.UnsafeAllocator$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 extends UnsafeAllocator {
        final /* synthetic */ Method val$newInstance;

        public AnonymousClass3(Method r1) {
            this.val$newInstance = r1;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> r2) {
            UnsafeAllocator.access$000(r2);
            return (T) this.val$newInstance.invoke(null, new Object[]{r2, Object.class});
        }
    }

    /* renamed from: com.google.gson.internal.UnsafeAllocator$4, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 extends UnsafeAllocator {
        public AnonymousClass4() {
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> r3) {
            throw new UnsupportedOperationException("Cannot allocate " + r3 + ". Usage of JDK sun.misc.Unsafe is enabled, but it could not be used. Make sure your runtime is configured correctly.");
        }
    }

    static {
        INSTANCE = create();
    }

    public UnsafeAllocator() {
    }

    public static /* synthetic */ void access$000(Class r0) {
        assertInstantiable(r0);
    }

    private static void assertInstantiable(Class<?> r1) {
        String r12 = ConstructorConstructor.checkInstantiable(r1);
        if (r12 != null) goto L5;
        return;
    L5:
        Buffer$$ExternalSyntheticBUOutline2.m("UnsafeAllocator is used for non-instantiable type: ".concat(r12));
    }

    private static UnsafeAllocator create() {
        Class<?> r5 = Class.forName("sun.misc.Unsafe");     // Catch: Exception -> L5
        Field r6 = r5.getDeclaredField("theUnsafe");     // Catch: Exception -> L5
        r6.setAccessible(true);     // Catch: Exception -> L5
        Object r62 = r6.get(null);     // Catch: Exception -> L5
        return new AnonymousClass1(r5.getMethod("allocateInstance", new Class[]{Class.class}), r62);
    L5:
        Method r52 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});     // Catch: Exception -> L7
        r52.setAccessible(true);     // Catch: Exception -> L7
        int r3 = ((Integer) r52.invoke(null, new Object[]{Object.class})).intValue();     // Catch: Exception -> L7
        Method r1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});     // Catch: Exception -> L7
        r1.setAccessible(true);     // Catch: Exception -> L7
        return new AnonymousClass2(r1, r3);
    L7:
        Method r0 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});     // Catch: Exception -> L9
        r0.setAccessible(true);     // Catch: Exception -> L9
        return new AnonymousClass3(r0);
    L10:
        return new AnonymousClass4();
    }

    public abstract <T> T newInstance(Class<T> r1);
}
