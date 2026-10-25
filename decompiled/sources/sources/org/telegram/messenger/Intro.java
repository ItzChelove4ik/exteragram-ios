package org.telegram.messenger;

import android.graphics.Color;

/* loaded from: classes5.dex */
public class Intro {
    public Intro() {
    }

    public static native void onDrawFrame(int r0);

    public static native void onSurfaceChanged(int r0, int r1, float r2, int r3);

    public static native void onSurfaceCreated();

    private static native void setBackgroundColor(float r0, float r1, float r2, float r3);

    public static void setBackgroundColor(int r4) {
        setBackgroundColor(Color.red(r4) / 255.0f, Color.green(r4) / 255.0f, Color.blue(r4) / 255.0f, Color.alpha(r4) / 255.0f);
    }

    public static native void setDate(float r0);

    public static native void setFastTextures(int r0, int r1, int r2, int r3);

    public static native void setFreeTextures(int r0, int r1);

    public static native void setIcTextures(int r0, int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8);

    public static native void setPage(int r0);

    public static native void setPowerfulTextures(int r0, int r1, int r2, int r3);

    public static native void setPrivateTextures(int r0, int r1);

    public static native void setScrollOffset(float r0);

    public static native void setTelegramTextures(int r0, int r1, int r2);
}
