package androidx.camera.core.impl.utils;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public abstract class ContextUtil {
    private static final Map<String, WeakReference<Context>> CACHED_CONTEXTS = null;
    private static final Object CACHE_LOCK = null;

    public static class Api30Impl {
        public static Context createAttributionContext(Context r0, String r1) {
            return r0.createAttributionContext(r1);
        }

        public static String getAttributionTag(Context r0) {
            return r0.getAttributionTag();
        }
    }

    public static class Api34Impl {
        public static Context createDeviceContext(Context r0, int r1) {
            return r0.createDeviceContext(r1);
        }

        public static int getDeviceId(Context r0) {
            return r0.getDeviceId();
        }
    }

    static {
        CACHE_LOCK = new Object();
        CACHED_CONTEXTS = new HashMap();
    }

    public static Application getApplication(Context r1) {
        Context r12 = r1.getApplicationContext();
    L4:
        if ((r12 instanceof ContextWrapper) == false) goto L10;
        if ((r12 instanceof Application) == true) goto L8;
        r12 = ((ContextWrapper) r12).getBaseContext();
        goto L4
    L8:
        return (Application) r12;
    L10:
        return null;
    }

    private static String getApplicationContextHashKey(Context r4) {
        int r0 = r4.getApplicationContext().hashCode();
        int r1 = getDeviceId(r4);
        if (Build.VERSION.SDK_INT < 30) goto L5;
        String r42 = Api30Impl.getAttributionTag(r4);
    L7:
        return String.format("%d-%d-%s", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1), r42});
    L5:
        r42 = null;
        goto L7
    }

    private static Context getCachedContext(String r2) {
        Map<String, WeakReference<Context>> r0 = CACHED_CONTEXTS;
        WeakReference<Context> r1 = r0.get(r2);
        if (r1 == null) goto L10;
        Context r12 = r1.get();
        if (r12 == null) goto L7;
        return r12;
    L7:
        r0.remove(r2);
        return null;
    L10:
        return null;
    }

    public static int getDefaultDeviceId() {
        return 0;
    }

    public static int getDeviceId(Context r2) {
        if (Build.VERSION.SDK_INT < 34) goto L7;
        return Api34Impl.getDeviceId(r2);
    L7:
        return getDefaultDeviceId();
    }

    public static Context getPersistentApplicationContext(Context r5) {
        Context r0 = r5.getApplicationContext();
        String r1 = getApplicationContextHashKey(r5);
        Object r2 = CACHE_LOCK;
        monitor-enter(r2);
        Context r3 = getCachedContext(r1);     // Catch: Throwable -> L8
        if (r3 == null) goto L10;
        monitor-exit(r2);     // Catch: Throwable -> L8
        return r3;
    L10:
        int r32 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L8
        if (r32 < 34) goto L14;
        r0 = Api34Impl.createDeviceContext(r0, Api34Impl.getDeviceId(r5));     // Catch: Throwable -> L8
    L14:
        if (r32 < 30) goto L18;
        String r52 = Api30Impl.getAttributionTag(r5);     // Catch: Throwable -> L8
        if (Objects.equals(r52, Api30Impl.getAttributionTag(r0)) == true) goto L18;
        r0 = Api30Impl.createAttributionContext(r0, r52);     // Catch: Throwable -> L8
    L18:
        CACHED_CONTEXTS.put(r1, new WeakReference(r0));     // Catch: Throwable -> L8
        monitor-exit(r2);     // Catch: Throwable -> L8
        return r0;
    L8:
        th = move-exception;
        throw th;
    }
}
