package com.google.firebase.components;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.inject.Provider;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class ComponentDiscovery<T> {
    private final T context;
    private final RegistrarNameRetriever<T> retriever;

    /* renamed from: com.google.firebase.components.ComponentDiscovery$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class MetadataRegistrarNameRetriever implements RegistrarNameRetriever<Context> {
        private final Class<? extends Service> discoveryService;

        private MetadataRegistrarNameRetriever(Class<? extends Service> r1) {
            this.discoveryService = r1;
        }

        public /* synthetic */ MetadataRegistrarNameRetriever(Class r1, AnonymousClass1 r2) {
            this(r1);
        }

        private Bundle getMetadata(Context r6) {
            PackageManager r2 = r6.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
            if (r2 != null) goto L7;
            Log.w("ComponentDiscovery", "Context has no PackageManager.");     // Catch: PackageManager.NameNotFoundException -> L13
            return null;
        L7:
            ServiceInfo r62 = r2.getServiceInfo(new ComponentName(r6, this.discoveryService), 128);     // Catch: PackageManager.NameNotFoundException -> L13
            if (r62 != null) goto L12;
            Log.w("ComponentDiscovery", this.discoveryService + " has no service info.");     // Catch: PackageManager.NameNotFoundException -> L13
            return null;
        L12:
            return r62.metaData;
        L13:
            Log.w("ComponentDiscovery", "Application info not found.");
            return null;
        }

        /* renamed from: retrieve, reason: avoid collision after fix types in other method */
        public List<String> retrieve2(Context r5) {
            Bundle r4 = getMetadata(r5);
            if (r4 != null) goto L6;
            Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
            return Collections.EMPTY_LIST;
        L6:
            ArrayList r52 = new ArrayList();
            Iterator<String> r0 = r4.keySet().iterator();
        L8:
            if (r0.hasNext() == false) goto L14;
            String r1 = r0.next();
            if ("com.google.firebase.components.ComponentRegistrar".equals(r4.get(r1)) == false) goto L8;
            if (r1.startsWith("com.google.firebase.components:") == false) goto L8;
            r52.add(r1.substring(31));
            goto L8
        L14:
            return r52;
        }

        @Override // com.google.firebase.components.ComponentDiscovery.RegistrarNameRetriever
        public /* bridge */ /* synthetic */ List retrieve(Context r1) {
            return retrieve2(r1);
        }
    }

    public interface RegistrarNameRetriever<T> {
        List<String> retrieve(T r1);
    }

    /* renamed from: $r8$lambda$Gc4-uboerSWhT4Qj_o2kYkHFf50, reason: not valid java name */
    public static /* synthetic */ ComponentRegistrar m1919$r8$lambda$Gc4uboerSWhT4Qj_o2kYkHFf50(String r0) {
        return instantiate(r0);
    }

    public ComponentDiscovery(T r1, RegistrarNameRetriever<T> r2) {
        this.context = r1;
        this.retriever = r2;
    }

    public static ComponentDiscovery<Context> forContext(Context r3, Class<? extends Service> r4) {
        return new ComponentDiscovery(r3, new MetadataRegistrarNameRetriever(r4, null));
    }

    private static ComponentRegistrar instantiate(String r6) {
        Class<?> r3 = Class.forName(r6);     // Catch: InvocationTargetException -> L7 NoSuchMethodException -> L9 InstantiationException -> L11 IllegalAccessException -> L13 ClassNotFoundException -> L25
        if (ComponentRegistrar.class.isAssignableFrom(r3) == false) goto L16;
        return (ComponentRegistrar) r3.getDeclaredConstructor(null).newInstance(null);
    L16:
        throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", new Object[]{r6, "com.google.firebase.components.ComponentRegistrar"}));     // Catch: InvocationTargetException -> L7 NoSuchMethodException -> L9 InstantiationException -> L11 IllegalAccessException -> L13 ClassNotFoundException -> L25
    L25:
        Log.w("ComponentDiscovery", String.format("Class %s is not an found.", new Object[]{r6}));
        return null;
    L13:
        e = move-exception;
        throw new InvalidRegistrarException(String.format("Could not instantiate %s.", new Object[]{r6}), e);
    L11:
        e = move-exception;
        throw new InvalidRegistrarException(String.format("Could not instantiate %s.", new Object[]{r6}), e);
    L9:
        e = move-exception;
        throw new InvalidRegistrarException(String.format("Could not instantiate %s", new Object[]{r6}), e);
    L7:
        e = move-exception;
        throw new InvalidRegistrarException(String.format("Could not instantiate %s", new Object[]{r6}), e);
    }

    public List<Provider<ComponentRegistrar>> discoverLazy() {
        ArrayList r0 = new ArrayList();
        Iterator<String> r3 = this.retriever.retrieve(this.context).iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        r0.add(new ComponentDiscovery$$ExternalSyntheticLambda0(r3.next()));
        goto L4
    L6:
        return r0;
    }
}
