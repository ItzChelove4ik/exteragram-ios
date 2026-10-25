package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
class MetadataBackendRegistry implements BackendRegistry {
    private final BackendFactoryProvider backendFactoryProvider;
    private final Map<String, TransportBackend> backends;
    private final CreationContextFactory creationContextFactory;

    public static class BackendFactoryProvider {
        private final Context applicationContext;
        private Map<String, String> backendProviders;

        public BackendFactoryProvider(Context r2) {
            this.backendProviders = null;
            this.applicationContext = r2;
        }

        private Map<String, String> discover(Context r8) {
            Bundle r7 = getMetadata(r8);
            if (r7 != null) goto L6;
            Log.w("BackendRegistry", "Could not retrieve metadata, returning empty list of transport backends.");
            return Collections.EMPTY_MAP;
        L6:
            HashMap r82 = new HashMap();
            Iterator<String> r0 = r7.keySet().iterator();
        L8:
            if (r0.hasNext() == false) goto L20;
            String r1 = r0.next();
            Object r2 = r7.get(r1);
            if ((r2 instanceof String) == false) goto L8;
            if (r1.startsWith("backend:") == false) goto L8;
            String[] r22 = ((String) r2).split(",", -1);
            int r3 = r22.length;
            int r4 = 0;
        L14:
            if (r4 >= r3) goto L8;
            String r5 = r22[r4].trim();
            if (r5.isEmpty() == true) goto L19;
            r82.put(r5, r1.substring(8));
        L19:
            r4 = r4 + 1;
            goto L14
        L20:
            return r82;
        }

        private Map<String, String> getBackendProviders() {
            if (this.backendProviders != null) goto L6;
            this.backendProviders = discover(this.applicationContext);
        L6:
            return this.backendProviders;
        }

        private static Bundle getMetadata(Context r5) {
            PackageManager r2 = r5.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
            if (r2 != null) goto L7;
            Log.w("BackendRegistry", "Context has no PackageManager.");     // Catch: PackageManager.NameNotFoundException -> L13
            return null;
        L7:
            ServiceInfo r52 = r2.getServiceInfo(new ComponentName(r5, TransportBackendDiscovery.class), 128);     // Catch: PackageManager.NameNotFoundException -> L13
            if (r52 != null) goto L12;
            Log.w("BackendRegistry", "TransportBackendDiscovery has no service info.");     // Catch: PackageManager.NameNotFoundException -> L13
            return null;
        L12:
            return r52.metaData;
        L13:
            Log.w("BackendRegistry", "Application info not found.");
            return null;
        }

        public BackendFactory get(String r6) {
            String r5 = getBackendProviders().get(r6);
            if (r5 != null) goto L23;
            return null;
        L23:
            return (BackendFactory) Class.forName(r5).asSubclass(BackendFactory.class).getDeclaredConstructor(null).newInstance(null);
        L15:
            e = move-exception;
            Log.w("BackendRegistry", String.format("Class %s is not found.", new Object[]{r5}), e);
        L22:
            return null;
        L13:
            e = move-exception;
            Log.w("BackendRegistry", String.format("Could not instantiate %s.", new Object[]{r5}), e);
        L11:
            e = move-exception;
            Log.w("BackendRegistry", String.format("Could not instantiate %s.", new Object[]{r5}), e);
        L9:
            e = move-exception;
            Log.w("BackendRegistry", String.format("Could not instantiate %s", new Object[]{r5}), e);
        L7:
            e = move-exception;
            Log.w("BackendRegistry", String.format("Could not instantiate %s", new Object[]{r5}), e);
            goto L22
        }
    }

    public MetadataBackendRegistry(Context r2, CreationContextFactory r3) {
        this(new BackendFactoryProvider(r2), r3);
    }

    public MetadataBackendRegistry(BackendFactoryProvider r2, CreationContextFactory r3) {
        this.backends = new HashMap();
        this.backendFactoryProvider = r2;
        this.creationContextFactory = r3;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRegistry
    public synchronized TransportBackend get(String r3) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.backends.containsKey(r3) == false) goto L10;
        TransportBackend r32 = this.backends.get(r3);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r32;
    L10:
        BackendFactory r0 = this.backendFactoryProvider.get(r3);     // Catch: Throwable -> L8
        if (r0 != null) goto L15;
        monitor-exit(this);
        return null;
    L15:
        TransportBackend r02 = r0.create(this.creationContextFactory.create(r3));     // Catch: Throwable -> L8
        this.backends.put(r3, r02);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r02;
    }
}
