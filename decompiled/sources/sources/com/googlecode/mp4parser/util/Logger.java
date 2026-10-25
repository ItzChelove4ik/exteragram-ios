package com.googlecode.mp4parser.util;

/* loaded from: classes5.dex */
public abstract class Logger {
    public Logger() {
    }

    public static Logger getLogger(Class r2) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik") == false) goto L7;
        return new AndroidLogger(r2.getSimpleName());
    L7:
        return new JuliLogger(r2.getSimpleName());
    }

    public abstract void logDebug(String r1);
}
