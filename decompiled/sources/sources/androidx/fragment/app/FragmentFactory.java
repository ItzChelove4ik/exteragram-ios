package androidx.fragment.app;

import androidx.collection.SimpleArrayMap;

/* loaded from: classes.dex */
public abstract class FragmentFactory {
    private static final SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> sClassCacheMap = null;

    static {
        sClassCacheMap = new SimpleArrayMap();
    }

    public FragmentFactory() {
    }

    public static boolean isFragmentClass(ClassLoader r0, String r1) {
        return Fragment.class.isAssignableFrom(loadClass(r0, r1));
    L4:
        return false;
    }

    private static Class<?> loadClass(ClassLoader r2, String r3) {
        SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> r0 = sClassCacheMap;
        SimpleArrayMap<String, Class<?>> r1 = r0.get(r2);
        if (r1 != null) goto L5;
        r1 = new SimpleArrayMap();
        r0.put(r2, r1);
    L5:
        Class<?> r02 = r1.get(r3);
        if (r02 != null) goto L9;
        Class<?> r22 = Class.forName(r3, false, r2);
        r1.put(r3, r22);
        return r22;
    L9:
        return r02;
    }

    public static Class<? extends Fragment> loadFragmentClass(ClassLoader r1, String r2) {
        return loadClass(r1, r2);
    L4:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r2, ": make sure class is a valid subclass of Fragment", e);
        return null;
    L8:
        e = move-exception;
        Fragment$$ExternalSyntheticBUOutline1.m(r2, ": make sure class name exists", e);
        return null;
    }

    public abstract Fragment instantiate(ClassLoader r1, String r2);
}
