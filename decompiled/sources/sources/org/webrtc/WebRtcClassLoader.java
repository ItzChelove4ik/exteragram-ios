package org.webrtc;

/* loaded from: classes6.dex */
class WebRtcClassLoader {
    public WebRtcClassLoader() {
    }

    @CalledByNative
    public static Object getClassLoader() {
        ClassLoader r0 = WebRtcClassLoader.class.getClassLoader();
        if (r0 == null) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("Failed to get WebRTC class loader.");
        return null;
    }
}
