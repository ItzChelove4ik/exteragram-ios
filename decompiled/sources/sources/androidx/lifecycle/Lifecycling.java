package androidx.lifecycle;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u00112\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u0014\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u00152\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u00110\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR2\u0010 \u001a \u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\b0\u001f0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u001e¨\u0006!"}, d2 = {"Landroidx/lifecycle/Lifecycling;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "object", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleEventObserver", "(Ljava/lang/Object;)Landroidx/lifecycle/LifecycleEventObserver;", "Ljava/lang/reflect/Constructor;", "Landroidx/lifecycle/GeneratedAdapter;", "constructor", "createGeneratedAdapter", "(Ljava/lang/reflect/Constructor;Ljava/lang/Object;)Landroidx/lifecycle/GeneratedAdapter;", "Ljava/lang/Class;", "klass", "generatedConstructor", "(Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", _UrlKt.FRAGMENT_ENCODE_SET, "getObserverConstructorType", "(Ljava/lang/Class;)I", "resolveObserverCallbackType", _UrlKt.FRAGMENT_ENCODE_SET, "isLifecycleParent", "(Ljava/lang/Class;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "className", "getAdapterName", "(Ljava/lang/String;)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "callbackCache", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "classToAdapters", "lifecycle-common"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Lifecycling {
    public static final Lifecycling INSTANCE = null;
    private static final Map<Class<?>, Integer> callbackCache = null;
    private static final Map<Class<?>, List<Constructor<? extends GeneratedAdapter>>> classToAdapters = null;

    static {
        INSTANCE = new Lifecycling();
        callbackCache = new HashMap();
        classToAdapters = new HashMap();
    }

    private Lifecycling() {
    }

    private final GeneratedAdapter createGeneratedAdapter(Constructor<? extends GeneratedAdapter> r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.newInstance(new Object[]{r2}));     // Catch: InvocationTargetException -> L4 InstantiationException -> L8 IllegalAccessException -> L10
        return null;
    L10:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L8:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private final Constructor<? extends GeneratedAdapter> generatedConstructor(Class<?> r5) {
        Package r0 = r5.getPackage();     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
        String r1 = r5.getCanonicalName();     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
        if (r0 == null) goto L8;
        String r02 = r0.getName();     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
    L10:
        if (r02.length() == 0) goto L13;
        r1 = r1.substring(r02.length() + 1);     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
    L13:
        String r12 = getAdapterName(r1);     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
        if (r02.length() == 0) goto L17;
        r12 = r02 + "." + r12;     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
    L17:
        Constructor r52 = Class.forName(r12).getDeclaredConstructor(new Class[]{r5});     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
        if (r52.isAccessible() == true) goto L20;
        r52.setAccessible(true);     // Catch: NoSuchMethodException -> L6 ClassNotFoundException -> L23
    L20:
        return r52;
    L8:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    L22:
        return null;
    }

    @JvmStatic
    public static final String getAdapterName(String r6) {
        return StringsKt.replace$default(r6, ".", "_", false, 4, null) + "_LifecycleAdapter";
    }

    private final int getObserverConstructorType(Class<?> r3) {
        Map<Class<?>, Integer> r0 = callbackCache;
        Integer r1 = r0.get(r3);
        if (r1 != null) goto L5;
        int r2 = resolveObserverCallbackType(r3);
        r0.put(r3, Integer.valueOf(r2));
        return r2;
    L5:
        return r1.intValue();
    }

    private final boolean isLifecycleParent(Class<?> r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (LifecycleObserver.class.isAssignableFrom(r1) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @JvmStatic
    public static final LifecycleEventObserver lifecycleEventObserver(Object r7) {
        boolean r0 = r7 instanceof LifecycleEventObserver;
        boolean r1 = r7 instanceof DefaultLifecycleObserver;
        if (r0 == false) goto L8;
        if (r1 == false) goto L8;
        return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) r7, (LifecycleEventObserver) r7);
    L8:
        if (r1 == true) goto L10;
        if (r0 == true) goto L13;
        Class<?> r02 = r7.getClass();
        Lifecycling r12 = INSTANCE;
        if (r12.getObserverConstructorType(r02) != 2) goto L26;
        List<Constructor<? extends GeneratedAdapter>> r03 = classToAdapters.get(r02);
        int r4 = 0;
        if (r03.size() != 1) goto L20;
        r12.createGeneratedAdapter(r03.get(0), r7);
        return new SingleGeneratedAdapterObserver(null);
    L20:
        int r13 = r03.size();
        GeneratedAdapter[] r3 = new GeneratedAdapter[r13];
    L21:
        if (r4 >= r13) goto L24;
        INSTANCE.createGeneratedAdapter(r03.get(r4), r7);
        r3[r4] = null;
        r4 = r4 + 1;
        goto L21
    L24:
        return new CompositeGeneratedAdaptersObserver(r3);
    L26:
        return new ReflectiveGenericLifecycleObserver(r7);
    L13:
        return (LifecycleEventObserver) r7;
    L10:
        return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) r7, null);
    }

    private final int resolveObserverCallbackType(Class<?> r9) {
        if (r9.getCanonicalName() != null) goto L5;
        return 1;
    L5:
        Constructor<? extends GeneratedAdapter> r0 = generatedConstructor(r9);
        if (r0 == null) goto L10;
        classToAdapters.put(r9, CollectionsKt.listOf(r0));
        return 2;
    L10:
        if (ClassesInfoCache.sInstance.hasLifecycleMethods(r9) == false) goto L12;
        return 1;
    L12:
        Class<? super Object> r02 = r9.getSuperclass();
        if (isLifecycleParent(r02) == true) goto L15;
        ArrayList r3 = null;
    L19:
        Class<?>[] r03 = r9.getInterfaces();
        int r4 = r03.length;
        int r5 = 0;
    L20:
        if (r5 >= r4) goto L31;
        Class<?> r6 = r03[r5];
        if (isLifecycleParent(r6) == false) goto L30;
        if (getObserverConstructorType(r6) == 1) goto L26;
        if (r3 != null) goto L29;
        r3 = new ArrayList();
    L29:
        r3.addAll(classToAdapters.get(r6));
        goto L30
    L26:
        return 1;
    L30:
        r5 = r5 + 1;
        goto L20
    L31:
        if (r3 == null) goto L34;
        classToAdapters.put(r9, r3);
        return 2;
    L34:
        return 1;
    L15:
        if (getObserverConstructorType(r02) != 1) goto L17;
        return 1;
    L17:
        r3 = new ArrayList(classToAdapters.get(r02));
        goto L19
    }
}
