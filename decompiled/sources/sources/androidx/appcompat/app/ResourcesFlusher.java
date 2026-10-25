package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
abstract class ResourcesFlusher {
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class<?> sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    public static void flush(Resources r2) {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        return;
    L5:
        flushNougats(r2);
    }

    private static void flushNougats(Resources r5) {
        if (sResourcesImplFieldFetched == false) goto L41;
    L9:
        Field r0 = sResourcesImplField;
        if (r0 == null) goto L44;
        Object r3 = null;
        Object r52 = r0.get(r5);     // Catch: IllegalAccessException -> L15
    L17:
        if (r52 != null) goto L20;
        return;
    L20:
        if (sDrawableCacheFieldFetched == false) goto L35;
    L26:
        Field r02 = sDrawableCacheField;
        if (r02 != null) goto L39;
    L32:
        if (r3 == null) goto L43;
        flushThemedResourcesCache(r3);
        return;
    L43:
        return;
    L39:
        r3 = r02.get(r52);     // Catch: IllegalAccessException -> L30
    L30:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e);
        goto L32
    L35:
        Field r03 = r52.getClass().getDeclaredField("mDrawableCache");     // Catch: NoSuchFieldException -> L23
        sDrawableCacheField = r03;     // Catch: NoSuchFieldException -> L23
        r03.setAccessible(true);     // Catch: NoSuchFieldException -> L23
    L25:
        sDrawableCacheFieldFetched = true;
    L23:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e);
    L15:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e);
        r52 = null;
        goto L17
    L44:
        return;
    L41:
        Field r04 = Resources.class.getDeclaredField("mResourcesImpl");     // Catch: NoSuchFieldException -> L6
        sResourcesImplField = r04;     // Catch: NoSuchFieldException -> L6
        r04.setAccessible(true);     // Catch: NoSuchFieldException -> L6
    L8:
        sResourcesImplFieldFetched = true;
    L6:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e);
        goto L8
    }

    private static void flushThemedResourcesCache(Object r4) {
        if (sThemedResourceCacheClazzFetched == false) goto L33;
    L9:
        Class<?> r0 = sThemedResourceCacheClazz;
        if (r0 != null) goto L13;
        return;
    L13:
        if (sThemedResourceCache_mUnthemedEntriesFieldFetched == false) goto L29;
    L19:
        Field r02 = sThemedResourceCache_mUnthemedEntriesField;
        if (r02 == null) goto L37;
        LongSparseArray r42 = (LongSparseArray) r02.get(r4);     // Catch: IllegalAccessException -> L24
    L26:
        if (r42 == null) goto L35;
        r42.clear();
        return;
    L35:
        return;
    L24:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e);
        r42 = null;
        goto L26
    L37:
        return;
    L29:
        Field r03 = r0.getDeclaredField("mUnthemedEntries");     // Catch: NoSuchFieldException -> L16
        sThemedResourceCache_mUnthemedEntriesField = r03;     // Catch: NoSuchFieldException -> L16
        r03.setAccessible(true);     // Catch: NoSuchFieldException -> L16
    L18:
        sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
    L16:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e);
        goto L18
    L33:
        sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");     // Catch: ClassNotFoundException -> L6
    L8:
        sThemedResourceCacheClazzFetched = true;
    L6:
        e = move-exception;
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e);
        goto L8
    }
}
