package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException;
    private final CrashlyticsNativeComponent nativeComponent;
    private final SettingsProvider settingsProvider;

    public interface CrashListener {
        void onUncaughtException(SettingsProvider r1, Thread r2, Throwable r3);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener r1, SettingsProvider r2, Thread.UncaughtExceptionHandler r3, CrashlyticsNativeComponent r4) {
        this.crashListener = r1;
        this.settingsProvider = r2;
        this.defaultHandler = r3;
        this.isHandlingException = new AtomicBoolean(false);
        this.nativeComponent = r4;
    }

    private boolean shouldRecordUncaughtException(Thread r2, Throwable r3) {
        if (r2 != null) goto L6;
        Logger.getLogger().e("Crashlytics will not record uncaught exception; null thread");
        return false;
    L6:
        if (r3 != null) goto L10;
        Logger.getLogger().e("Crashlytics will not record uncaught exception; null throwable");
        return false;
    L10:
        if (this.nativeComponent.hasCrashDataForCurrentSession() == false) goto L13;
        Logger.getLogger().d("Crashlytics will not record uncaught exception; native crash exists for session.");
        return false;
    L13:
        return true;
    }

    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread r8, Throwable r9) {
        this.isHandlingException.set(true);
    L8:
        e = move-exception;
        Logger.getLogger().e("An error occurred in the uncaught exception handler", e);     // Catch: Throwable -> L6
        if (this.defaultHandler != null) goto L13;
    L14:
        Logger.getLogger().d("Completed exception processing, but no default exception handler.");
        System.exit(1);
    L15:
        this.isHandlingException.set(false);
        return;
    L13:
        Logger.getLogger().d("Completed exception processing. Invoking default exception handler.");
        this.defaultHandler.uncaughtException(r8, r9);
        goto L15
    L4:
        if (shouldRecordUncaughtException(r8, r9) == false) goto L10;
        this.crashListener.onUncaughtException(this.settingsProvider, r8, r9);     // Catch: Throwable -> L6 Exception -> L8
    L12:
        if (this.defaultHandler == null) goto L14;
    L10:
        Logger.getLogger().d("Uncaught exception will not be recorded by Crashlytics.");     // Catch: Throwable -> L6 Exception -> L8
    L6:
        th = move-exception;
        if (this.defaultHandler == null) goto L24;
        Logger.getLogger().d("Completed exception processing. Invoking default exception handler.");
        this.defaultHandler.uncaughtException(r8, r9);
    L25:
        this.isHandlingException.set(false);
        throw th;
    L24:
        Logger.getLogger().d("Completed exception processing, but no default exception handler.");
        System.exit(1);
        goto L25
    }
}
