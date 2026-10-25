package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class AppInitializer {
    private static volatile AppInitializer sInstance;
    private static final Object sLock = null;
    final Context mContext;
    final Set<Class<? extends Initializer<?>>> mDiscovered;
    final Map<Class<?>, Object> mInitialized;

    static {
        sLock = new Object();
    }

    public AppInitializer(Context r1) {
        this.mContext = r1.getApplicationContext();
        this.mDiscovered = new HashSet();
        this.mInitialized = new HashMap();
    }

    private <T> T doInitialize(Class<? extends Initializer<?>> r5, Set<Class<?>> r6) {
        if (Trace.isEnabled() == false) goto L6;
        Trace.beginSection(r5.getSimpleName());     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        Trace.endSection();
        throw th;
    L6:
        if (r6.contains(r5) == true) goto L28;
        if (this.mInitialized.containsKey(r5) == true) goto L24;
        r6.add(r5);     // Catch: Throwable -> L29
        Initializer<?> r0 = r5.getDeclaredConstructor(null).newInstance(null);     // Catch: Throwable -> L21
        List<Class<? extends Initializer<?>>> r1 = r0.dependencies();     // Catch: Throwable -> L21
        if (r1.isEmpty() == true) goto L19;
        Iterator<Class<? extends Initializer<?>>> r12 = r1.iterator();     // Catch: Throwable -> L21
    L15:
        if (r12.hasNext() == false) goto L19;
        Class<? extends Initializer<?>> r2 = r12.next();     // Catch: Throwable -> L21
        if (this.mInitialized.containsKey(r2) == true) goto L15;
        doInitialize(r2, r6);     // Catch: Throwable -> L21
    L19:
        T r02 = (T) r0.create(this.mContext);
        r6.remove(r5);     // Catch: Throwable -> L21
        this.mInitialized.put(r5, r02);     // Catch: Throwable -> L21
    L25:
        Trace.endSection();
        return r02;
    L21:
        th = move-exception;
        throw new StartupException(th);     // Catch: Throwable -> L29
    L24:
        r02 = (T) this.mInitialized.get(r5);
        goto L25
    L28:
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", new Object[]{r5.getName()}));     // Catch: Throwable -> L29
    }

    public static AppInitializer getInstance(Context r2) {
        if (sInstance != null) goto L16;
        Object r0 = sLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (sInstance != null) goto L11;
        sInstance = new AppInitializer(r2);     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return sInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void discoverAndInitialize(Bundle r6) {
        String r0 = this.mContext.getString(R$string.androidx_startup);
        if (r6 != null) goto L21;
        return;
    L21:
        HashSet r1 = new HashSet();     // Catch: ClassNotFoundException -> L17
        Iterator<String> r2 = r6.keySet().iterator();     // Catch: ClassNotFoundException -> L17
    L6:
        if (r2.hasNext() == false) goto L12;
        String r3 = r2.next();     // Catch: ClassNotFoundException -> L17
        if (r0.equals(r6.getString(r3, null)) == false) goto L6;
        Class<?> r32 = Class.forName(r3);     // Catch: ClassNotFoundException -> L17
        if (Initializer.class.isAssignableFrom(r32) == false) goto L6;
        this.mDiscovered.add(r32);     // Catch: ClassNotFoundException -> L17
        goto L6
    L12:
        Iterator<Class<? extends Initializer<?>>> r62 = this.mDiscovered.iterator();     // Catch: ClassNotFoundException -> L17
    L13:
        if (r62.hasNext() == false) goto L31;
        doInitialize(r62.next(), r1);     // Catch: ClassNotFoundException -> L17
        goto L13
    L31:
        return;
    L17:
        e = move-exception;
        throw new StartupException(e);
    }

    public void discoverAndInitialize(Class<? extends InitializationProvider> r3) {
        Trace.beginSection("Startup");     // Catch: PackageManager.NameNotFoundException -> L5 Throwable -> L8
        ComponentName r0 = new ComponentName(this.mContext, r3);     // Catch: PackageManager.NameNotFoundException -> L5 Throwable -> L8
        discoverAndInitialize(this.mContext.getPackageManager().getProviderInfo(r0, 128).metaData);     // Catch: PackageManager.NameNotFoundException -> L5 Throwable -> L8
        Trace.endSection();
        return;
    L8:
        th = move-exception;
        Trace.endSection();
        throw th;
    L5:
        e = move-exception;
        throw new StartupException(e);     // Catch: Throwable -> L8
    }

    public <T> T doInitialize(Class<? extends Initializer<?>> r3) {
        Object r0 = sLock;
        monitor-enter(r0);
        T r1 = (T) this.mInitialized.get(r3);
        if (r1 != null) goto L9;
        r1 = (T) doInitialize(r3, new HashSet());
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public <T> T initializeComponent(Class<? extends Initializer<T>> r1) {
        return (T) doInitialize(r1);
    }

    public boolean isEagerlyInitialized(Class<? extends Initializer<?>> r1) {
        return this.mDiscovered.contains(r1);
    }
}
