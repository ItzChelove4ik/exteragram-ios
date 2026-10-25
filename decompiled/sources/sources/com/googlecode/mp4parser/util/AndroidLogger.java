package com.googlecode.mp4parser.util;

import android.util.Log;

/* loaded from: classes5.dex */
public class AndroidLogger extends Logger {
    String name;

    public AndroidLogger(String r1) {
        this.name = r1;
    }

    @Override // com.googlecode.mp4parser.util.Logger
    public void logDebug(String r2) {
        Log.d("isoparser", String.valueOf(this.name) + ":" + r2);
    }
}
