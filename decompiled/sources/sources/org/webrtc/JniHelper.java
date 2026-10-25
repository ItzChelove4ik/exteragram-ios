package org.webrtc;

import java.util.Map;

/* loaded from: classes6.dex */
class JniHelper {
    public JniHelper() {
    }

    @CalledByNative
    public static Object getKey(Map.Entry r0) {
        return r0.getKey();
    }

    @CalledByNative
    public static byte[] getStringBytes(String r1) {
        return r1.getBytes("ISO-8859-1");
    L4:
        GlShader$$ExternalSyntheticBUOutline1.m("ISO-8859-1 is unsupported");
        return null;
    }

    @CalledByNative
    public static Object getStringClass() {
        return String.class;
    }

    @CalledByNative
    public static Object getValue(Map.Entry r0) {
        return r0.getValue();
    }
}
