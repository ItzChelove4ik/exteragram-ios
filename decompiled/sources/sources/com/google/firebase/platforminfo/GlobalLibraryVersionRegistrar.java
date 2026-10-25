package com.google.firebase.platforminfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class GlobalLibraryVersionRegistrar {
    private static volatile GlobalLibraryVersionRegistrar INSTANCE;
    private final Set<LibraryVersion> infos;

    public GlobalLibraryVersionRegistrar() {
        this.infos = new HashSet();
    }

    public static GlobalLibraryVersionRegistrar getInstance() {
        GlobalLibraryVersionRegistrar r0 = INSTANCE;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(GlobalLibraryVersionRegistrar.class);
        GlobalLibraryVersionRegistrar r02 = INSTANCE;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new GlobalLibraryVersionRegistrar();     // Catch: Throwable -> L9
        INSTANCE = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(GlobalLibraryVersionRegistrar.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public Set<LibraryVersion> getRegisteredVersions() {
        Set<LibraryVersion> r0 = this.infos;
        monitor-enter(r0);
        Set<LibraryVersion> r1 = Collections.unmodifiableSet(this.infos);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
