package com.google.firebase.crashlytics.internal;

import android.util.Log;

/* loaded from: classes.dex */
public class Logger {
    static final Logger DEFAULT_LOGGER = null;
    private int logLevel;
    private final String tag;

    static {
        DEFAULT_LOGGER = new Logger("FirebaseCrashlytics");
    }

    public Logger(String r1) {
        this.tag = r1;
        this.logLevel = 4;
    }

    private boolean canLog(int r2) {
        if (this.logLevel > r2) goto L5;
        return true;
    L5:
        if (Log.isLoggable(this.tag, r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    public void d(String r2) {
        d(r2, null);
    }

    public void d(String r2, Throwable r3) {
        if (canLog(3) == false) goto L6;
        Log.d(this.tag, r2, r3);
        return;
    }

    public void e(String r2) {
        e(r2, null);
    }

    public void e(String r2, Throwable r3) {
        if (canLog(6) == false) goto L6;
        Log.e(this.tag, r2, r3);
        return;
    }

    public void i(String r2) {
        i(r2, null);
    }

    public void i(String r2, Throwable r3) {
        if (canLog(4) == false) goto L6;
        Log.i(this.tag, r2, r3);
        return;
    }

    public void v(String r2) {
        v(r2, null);
    }

    public void v(String r2, Throwable r3) {
        if (canLog(2) == false) goto L6;
        Log.v(this.tag, r2, r3);
        return;
    }

    public void w(String r2) {
        w(r2, null);
    }

    public void w(String r2, Throwable r3) {
        if (canLog(5) == false) goto L6;
        Log.w(this.tag, r2, r3);
        return;
    }
}
