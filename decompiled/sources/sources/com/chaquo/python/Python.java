package com.chaquo.python;

import java.util.Objects;

/* loaded from: classes.dex */
public class Python {
    private static boolean failed;
    private static Python instance;
    private static Platform platform;

    public static class Platform {
        public Platform() {
        }

        public String getPath() {
            return null;
        }

        public void onStart(Python r1) {
        }
    }

    static {
        instance = new Python();
    }

    private Python() {
    }

    public static synchronized Python getInstance() {
        monitor-enter(Python.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (isStarted() == true) goto L9;
        start(new GenericPlatform());     // Catch: Throwable -> L7
    L9:
        Python r1 = instance;     // Catch: Throwable -> L7
        monitor-exit(Python.class);
        return r1;
    }

    private native long getModuleNative(String r1);

    public static synchronized Platform getPlatform() {
        monitor-enter(Python.class);
        Platform r1 = platform;     // Catch: Throwable -> L7
        monitor-exit(Python.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized boolean isStarted() {
        monitor-enter(Python.class);
        if (platform == null) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(Python.class);
        return r1;
    L7:
        r1 = false;
    L10:
        th = move-exception;
        throw th;
    }

    public static synchronized void start(Platform r2) {
        monitor-enter(Python.class);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (isStarted() == true) goto L20;
        if (failed == true) goto L18;
        startNative(r2, r2.getPath());     // Catch: Throwable -> L11
        r2.onStart(instance);     // Catch: Throwable -> L11
        platform = r2;     // Catch: Throwable -> L11
        monitor-exit(Python.class);
        return;
    L11:
        th = move-exception;
        failed = true;     // Catch: Throwable -> L15
        throw th;     // Catch: Throwable -> L15
    L18:
        throw new IllegalStateException("Python startup previously failed, and cannot be retried");     // Catch: Throwable -> L15
    L20:
        throw new IllegalStateException("Python already started");     // Catch: Throwable -> L15
    }

    private static native void startNative(Platform r0, String r1);

    public PyObject getBuiltins() {
        return getModule("builtins");
    }

    public PyObject getModule(String r1) {
        PyObject r0 = PyObject.getInstance(getModuleNative(r1));
        Objects.requireNonNull(r0);
        return r0;
    }
}
