package org.chromium.net;

import android.content.Context;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.chromium.net.CronetEngine;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public abstract class CronetProvider {
    private static final String TAG = "CronetProvider";
    protected final Context mContext;

    public static final class ProviderInfo {
        public CronetLogger.CronetSource logSource;
        public CronetProvider provider;

        public ProviderInfo() {
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof ProviderInfo) == true) goto L5;
            return false;
        L5:
            if (this.provider.equals(((ProviderInfo) r2).provider) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            return this.provider.hashCode();
        }
    }

    static {
    }

    public CronetProvider(Context r1) {
        if (r1 == null) goto L6;
        this.mContext = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Context must not be null");
        throw null;
    }

    private static boolean addCronetProviderFromResourceFile(Context r4, CronetLogger.CronetSource r5, Set<ProviderInfo> r6) {
        int r0 = r4.getResources().getIdentifier("CronetProviderClassName", "string", r4.getPackageName());
        boolean r1 = false;
        if (r0 != 0) goto L5;
        return false;
    L5:
        String r02 = r4.getString(r0);
        if (r02 != null) goto L8;
    L19:
        return r1;
    L8:
        if (r02.equals("com.google.android.gms.net.PlayServicesCronetProvider") == true) goto L19;
        if (r02.equals("com.google.android.gms.net.GmsCoreCronetProvider") == true) goto L19;
        if (r02.equals("org.chromium.net.impl.JavaCronetProvider") == true) goto L19;
        if (r02.equals("org.chromium.net.impl.NativeCronetProvider") == true) goto L19;
        r1 = true;
        if (addCronetProviderImplByClassName(r4, r02, r5, r6, true) == true) goto L19;
        Log.e(TAG, "Unable to instantiate Cronet implementation class " + r02 + " that is listed as in the app string resource file under CronetProviderClassName key");
        goto L19
    }

    private static boolean addCronetProviderImplByClassName(Context r2, String r3, CronetLogger.CronetSource r4, Set<ProviderInfo> r5, boolean r6) {
        Constructor r0 = r2.getClassLoader().loadClass(r3).asSubclass(CronetProvider.class).getConstructor(new Class[]{Context.class});     // Catch: ClassNotFoundException -> L6 IllegalAccessException -> L8 NoSuchMethodException -> L10 InvocationTargetException -> L12 InstantiationException -> L14
        ProviderInfo r1 = new ProviderInfo();     // Catch: ClassNotFoundException -> L6 IllegalAccessException -> L8 NoSuchMethodException -> L10 InvocationTargetException -> L12 InstantiationException -> L14
        r1.provider = (CronetProvider) r0.newInstance(new Object[]{r2});     // Catch: ClassNotFoundException -> L6 IllegalAccessException -> L8 NoSuchMethodException -> L10 InvocationTargetException -> L12 InstantiationException -> L14
        r1.logSource = r4;     // Catch: ClassNotFoundException -> L6 IllegalAccessException -> L8 NoSuchMethodException -> L10 InvocationTargetException -> L12 InstantiationException -> L14
        r5.add(r1);     // Catch: ClassNotFoundException -> L6 IllegalAccessException -> L8 NoSuchMethodException -> L10 InvocationTargetException -> L12 InstantiationException -> L14
        return true;
    L6:
        e = move-exception;
        logReflectiveOperationException(r3, r6, e);
        return false;
    L8:
        e = move-exception;
        logReflectiveOperationException(r3, r6, e);
        return false;
    L14:
        e = move-exception;
        logReflectiveOperationException(r3, r6, e);
        return false;
    L10:
        e = move-exception;
        logReflectiveOperationException(r3, r6, e);
        return false;
    L12:
        e = move-exception;
        logReflectiveOperationException(r3, r6, e);
        return false;
    }

    public static List<ProviderInfo> getAllProviderInfos(Context r4) {
        LinkedHashSet r0 = new LinkedHashSet();
        addCronetProviderFromResourceFile(r4, CronetLogger.CronetSource.CRONET_SOURCE_UNSPECIFIED, r0);
        CronetLogger.CronetSource r1 = CronetLogger.CronetSource.CRONET_SOURCE_PLAY_SERVICES;
        addCronetProviderImplByClassName(r4, "com.google.android.gms.net.PlayServicesCronetProvider", r1, r0, false);
        addCronetProviderImplByClassName(r4, "com.google.android.gms.net.GmsCoreCronetProvider", r1, r0, false);
        addCronetProviderImplByClassName(r4, "org.chromium.net.impl.NativeCronetProvider", CronetLogger.CronetSource.CRONET_SOURCE_STATICALLY_LINKED, r0, false);
        addCronetProviderImplByClassName(r4, "org.chromium.net.impl.JavaCronetProvider", CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK, r0, false);
        return Collections.unmodifiableList(new ArrayList(r0));
    }

    private static void logReflectiveOperationException(String r2, boolean r3, Exception r4) {
        if (r3 == false) goto L5;
        Log.e(TAG, "Unable to load provider class: " + r2, r4);
        return;
    L5:
        String r32 = TAG;
        if (Log.isLoggable(r32, 3) == false) goto L9;
        Log.d(r32, "Tried to load " + r2 + " provider class but it wasn't included in the app classpath");
        return;
    }

    public abstract CronetEngine.Builder createBuilder();

    public abstract String getName();

    public abstract String getVersion();

    public abstract boolean isEnabled();

    public String toString() {
        return "[class=" + getClass().getName() + ", name=" + getName() + ", version=" + getVersion() + ", enabled=" + isEnabled() + "]";
    }
}
