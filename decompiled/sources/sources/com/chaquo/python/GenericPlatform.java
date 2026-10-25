package com.chaquo.python;

import com.chaquo.python.Python;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class GenericPlatform extends Python.Platform {
    private String mPath;

    public GenericPlatform() {
        this.mPath = System.getenv("PYTHONPATH");
        if (System.getProperty("java.vendor").toLowerCase().contains("android") == true) goto L6;
        System.loadLibrary("chaquopy_java");
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("Cannot use GenericPlatform on Android. Call Python.start(new AndroidPlatform(context)) before using Python, or use PyApplication to do this automatically.");
        throw null;
    }

    @Override // com.chaquo.python.Python.Platform
    public String getPath() {
        return this.mPath;
    }

    public GenericPlatform setPath(String r1) {
        this.mPath = r1;
        return this;
    }
}
