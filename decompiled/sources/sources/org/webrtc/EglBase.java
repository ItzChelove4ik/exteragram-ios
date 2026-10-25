package org.webrtc;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGLContext;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;
import org.webrtc.EglBase10;
import org.webrtc.EglBase14;

/* loaded from: classes6.dex */
public interface EglBase {
    public static final int[] CONFIG_PIXEL_BUFFER = null;
    public static final int[] CONFIG_PIXEL_RGBA_BUFFER = null;
    public static final int[] CONFIG_PLAIN = null;
    public static final int[] CONFIG_RECORDABLE = null;
    public static final int[] CONFIG_RGBA = null;
    public static final int EGL_OPENGL_ES2_BIT = 4;
    public static final int EGL_OPENGL_ES3_BIT = 64;
    public static final int EGL_RECORDABLE_ANDROID = 12610;
    public static final Object lock = null;

    public static class ConfigBuilder {
        private boolean hasAlphaChannel;
        private boolean isRecordable;
        private int openGlesVersion;
        private boolean supportsPixelBuffer;

        public ConfigBuilder() {
            this.openGlesVersion = 2;
        }

        public int[] createConfigAttributes() {
            ArrayList r1 = new ArrayList();
            r1.add(12324);
            r1.add(8);
            r1.add(12323);
            r1.add(8);
            r1.add(12322);
            r1.add(8);
            if (this.hasAlphaChannel == false) goto L5;
            r1.add(12321);
            r1.add(8);
        L5:
            int r2 = this.openGlesVersion;
            if (r2 == 2) goto L8;
            if (r2 == 3) goto L8;
        L14:
            if (this.supportsPixelBuffer == false) goto L17;
            r1.add(12339);
            r1.add(1);
        L17:
            if (this.isRecordable == false) goto L19;
            r1.add(Integer.valueOf(EglBase.EGL_RECORDABLE_ANDROID));
            r1.add(1);
        L19:
            r1.add(12344);
            int[] r5 = new int[r1.size()];
            int r0 = 0;
        L21:
            if (r0 >= r1.size()) goto L23;
            r5[r0] = ((Integer) r1.get(r0)).intValue();
            r0 = r0 + 1;
            goto L21
        L23:
            return r5;
        L8:
            r1.add(12352);
            if (this.openGlesVersion != 3) goto L11;
            int r22 = 64;
        L12:
            r1.add(Integer.valueOf(r22));
            goto L14
        L11:
            r22 = 4;
            goto L12
        }

        public ConfigBuilder setHasAlphaChannel(boolean r1) {
            this.hasAlphaChannel = r1;
            return this;
        }

        public ConfigBuilder setIsRecordable(boolean r1) {
            this.isRecordable = r1;
            return this;
        }

        public ConfigBuilder setOpenGlesVersion(int r2) {
            if (r2 >= 1) goto L5;
        L8:
            CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("OpenGL ES version ", r2, " not supported");
            return null;
        L5:
            if (r2 > 3) goto L8;
            this.openGlesVersion = r2;
            return this;
        }

        public ConfigBuilder setSupportsPixelBuffer(boolean r1) {
            this.supportsPixelBuffer = r1;
            return this;
        }
    }

    public interface Context {
        public static final long NO_CONTEXT = 0;

        long getNativeEglContext();
    }

    static {
        lock = new Object();
        CONFIG_PLAIN = configBuilder().createConfigAttributes();
        CONFIG_RGBA = configBuilder().setHasAlphaChannel(true).createConfigAttributes();
        CONFIG_PIXEL_BUFFER = configBuilder().setSupportsPixelBuffer(true).createConfigAttributes();
        CONFIG_PIXEL_RGBA_BUFFER = configBuilder().setHasAlphaChannel(true).setSupportsPixelBuffer(true).createConfigAttributes();
        CONFIG_RECORDABLE = configBuilder().setIsRecordable(true).createConfigAttributes();
    }

    static ConfigBuilder configBuilder() {
        return new ConfigBuilder();
    }

    static EglBase create() {
        return create(null, CONFIG_PLAIN);
    }

    static EglBase create(Context r1) {
        return create(r1, CONFIG_PLAIN);
    }

    static EglBase create(Context r1, int[] r2) {
        if (r1 != null) goto L10;
        if (EglBase14Impl.isEGL14Supported() == false) goto L8;
        return createEgl14(r2);
    L8:
        return createEgl10(r2);
    L10:
        if ((r1 instanceof EglBase14.Context) == false) goto L14;
        return createEgl14((EglBase14.Context) r1, r2);
    L14:
        if ((r1 instanceof EglBase10.Context) == true) goto L16;
        f$$ExternalSyntheticBUOutline1.m("Unrecognized Context");
        return null;
    L16:
        return createEgl10((EglBase10.Context) r1, r2);
    }

    static EglBase10 createEgl10(EGLContext r1, int[] r2) {
        return new EglBase10Impl(r1, r2);
    }

    static EglBase10 createEgl10(EglBase10.Context r1, int[] r2) {
        if (r1 != null) goto L5;
        EGLContext r12 = null;
    L7:
        return new EglBase10Impl(r12, r2);
    L5:
        r12 = r1.getRawContext();
        goto L7
    }

    static EglBase10 createEgl10(int[] r2) {
        return new EglBase10Impl(null, r2);
    }

    static EglBase14 createEgl14(android.opengl.EGLContext r1, int[] r2) {
        return new EglBase14Impl(r1, r2);
    }

    static EglBase14 createEgl14(EglBase14.Context r1, int[] r2) {
        if (r1 != null) goto L5;
        android.opengl.EGLContext r12 = null;
    L7:
        return new EglBase14Impl(r12, r2);
    L5:
        r12 = r1.getRawContext();
        goto L7
    }

    static EglBase14 createEgl14(int[] r2) {
        return new EglBase14Impl(null, r2);
    }

    static int getOpenGlesVersionFromConfig(int[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= (r4.length - 1)) goto L17;
        if (r4[r0] == 12352) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        int r42 = r4[r0 + 1];
        if (r42 != 4) goto L10;
        return 2;
    L10:
        if (r42 == 64) goto L12;
        return 1;
    L12:
        return 3;
    L17:
        return 1;
    }

    void createBackgroundSurface(SurfaceTexture r1);

    void createDummyPbufferSurface();

    void createPbufferSurface(int r1, int r2);

    void createSurface(SurfaceTexture r1);

    void createSurface(Surface r1);

    void detachCurrent();

    Context getEglBaseContext();

    boolean hasBackgroundSurface();

    boolean hasSurface();

    void makeBackgroundCurrent();

    void makeCurrent();

    void release();

    void releaseSurface(boolean r1);

    int surfaceHeight();

    int surfaceWidth();

    void swapBuffers(long r1, boolean r3);

    void swapBuffers(boolean r1);
}
