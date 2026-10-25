package org.webrtc;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLException;
import android.os.Build;
import android.view.Surface;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;

/* loaded from: classes6.dex */
class EglBase14Impl implements EglBase14 {
    private static final int CURRENT_SDK_VERSION = 0;
    private static final int EGLExt_SDK_VERSION = 18;
    private static final String TAG = "EglBase14Impl";
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private EGLSurface eglSurfaceBackground;

    public static class Context implements EglBase14.Context {
        private final EGLContext egl14Context;

        public Context(EGLContext r1) {
            this.egl14Context = r1;
        }

        @Override // org.webrtc.EglBase.Context
        public long getNativeEglContext() {
            int r0 = EglBase14Impl.m22346$$Nest$sfgetCURRENT_SDK_VERSION();
            EGLContext r2 = this.egl14Context;
            if (r0 < 21) goto L7;
            return r2.getNativeHandle();
        L7:
            return r2.getHandle();
        }

        @Override // org.webrtc.EglBase14.Context
        public EGLContext getRawContext() {
            return this.egl14Context;
        }
    }

    /* renamed from: -$$Nest$sfgetCURRENT_SDK_VERSION, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22346$$Nest$sfgetCURRENT_SDK_VERSION() {
        return CURRENT_SDK_VERSION;
    }

    static {
        CURRENT_SDK_VERSION = Build.VERSION.SDK_INT;
    }

    public EglBase14Impl(EGLContext r3, int[] r4) {
        EGLSurface r0 = EGL14.EGL_NO_SURFACE;
        this.eglSurface = r0;
        this.eglSurfaceBackground = r0;
        EGLDisplay r02 = getEglDisplay();
        this.eglDisplay = r02;
        this.eglConfig = getEglConfig(r02, r4);
        int r42 = EglBase.getOpenGlesVersionFromConfig(r4);
        Logging.d(TAG, "Using OpenGL ES version " + r42);
        this.eglContext = createEglContext(r3, this.eglDisplay, this.eglConfig, r42);
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) goto L5;
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("This object has been released");
        return;
    L5:
        if (this.eglContext == EGL14.EGL_NO_CONTEXT) goto L9;
        if (this.eglConfig == null) goto L9;
    }

    private static EGLContext createEglContext(EGLContext r2, EGLDisplay r3, EGLConfig r4, int r5) {
        if (r2 != null) goto L4;
    L8:
        int[] r52 = {12440, r5, 12344};
        if (r2 != null) goto L11;
        r2 = EGL14.EGL_NO_CONTEXT;
    L11:
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        EGLContext r22 = EGL14.eglCreateContext(r3, r4, r2, r52, 0);     // Catch: Throwable -> L21
        monitor-exit(r0);     // Catch: Throwable -> L21
        if (r22 == EGL14.EGL_NO_CONTEXT) goto L20;
        return r22;
    L20:
        throw new GLException(EGL14.eglGetError(), "Failed to create EGL context: 0x" + Integer.toHexString(EGL14.eglGetError()));
    L21:
        th = move-exception;
        throw th;
    L4:
        if (r2 != EGL14.EGL_NO_CONTEXT) goto L8;
        GlShader$$ExternalSyntheticBUOutline1.m("Invalid sharedContext");
        return null;
    }

    private void createSurfaceInternal(Object r6, boolean r7) {
        if ((r6 instanceof Surface) == false) goto L5;
    L9:
        checkIsNotReleased();
        if (r7 == false) goto L21;
        if (this.eglSurfaceBackground != EGL14.EGL_NO_SURFACE) goto L18;
        EGLDisplay r2 = this.eglDisplay;
        EGLConfig r3 = this.eglConfig;
        EGLSurface r62 = EGL14.eglCreateWindowSurface(r2, r3, r6, new int[]{12344}, 0);
        this.eglSurfaceBackground = r62;
        if (r62 != EGL14.EGL_NO_SURFACE) goto L29;
        Instance$$ExternalSyntheticBUOutline0.m("Failed to create window surface: 0x", Integer.toHexString(EGL14.eglGetError()));
        return;
    L29:
        return;
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
        return;
    L21:
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) goto L27;
        EGLDisplay r22 = this.eglDisplay;
        EGLConfig r32 = this.eglConfig;
        EGLSurface r63 = EGL14.eglCreateWindowSurface(r22, r32, r6, new int[]{12344}, 0);
        this.eglSurface = r63;
        if (r63 == EGL14.EGL_NO_SURFACE) goto L25;
        return;
    L25:
        Instance$$ExternalSyntheticBUOutline0.m("Failed to create window surface: 0x", Integer.toHexString(EGL14.eglGetError()));
        return;
    L27:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
        return;
    L5:
        if ((r6 instanceof SurfaceTexture) == true) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Input must be either a Surface or SurfaceTexture");
    }

    private static EGLConfig getEglConfig(EGLDisplay r8, int[] r9) {
        EGLConfig[] r3 = new EGLConfig[1];
        int[] r6 = new int[1];
        if (EGL14.eglChooseConfig(r8, r9, 0, r3, 0, 1, r6, 0) == false) goto L14;
        if (r6[0] <= 0) goto L11;
        EGLConfig r82 = r3[0];
        if (r82 == null) goto L9;
        return r82;
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("eglChooseConfig returned null");
        return null;
    L11:
        GlShader$$ExternalSyntheticBUOutline1.m("Unable to find any matching EGL config");
        return null;
    L14:
        throw new GLException(EGL14.eglGetError(), "eglChooseConfig failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
    }

    private static EGLDisplay getEglDisplay() {
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        if (r1 == EGL14.EGL_NO_DISPLAY) goto L10;
        int[] r2 = new int[2];
        if (EGL14.eglInitialize(r1, r2, 0, r2, 1) == false) goto L8;
        return r1;
    L8:
        throw new GLException(EGL14.eglGetError(), "Unable to initialize EGL14: 0x" + Integer.toHexString(EGL14.eglGetError()));
    L10:
        throw new GLException(EGL14.eglGetError(), "Unable to get EGL14 display: 0x" + Integer.toHexString(EGL14.eglGetError()));
    }

    public static boolean isEGL14Supported() {
        StringBuilder r0 = new StringBuilder("SDK version: ");
        int r1 = CURRENT_SDK_VERSION;
        r0.append(r1);
        r0.append(". isEGL14Supported: ");
        if (r1 < 18) goto L5;
        boolean r5 = true;
    L6:
        r0.append(r5);
        Logging.d(TAG, r0.toString());
        if (r1 < 18) goto L9;
        return true;
    L9:
        return false;
    L5:
        r5 = false;
        goto L6
    }

    @Override // org.webrtc.EglBase
    public void createBackgroundSurface(SurfaceTexture r2) {
        createSurfaceInternal(r2, true);
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int r5, int r6) {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) goto L9;
        EGLSurface r0 = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, r5, 12374, r6, 12344}, 0);
        this.eglSurface = r0;
        if (r0 == EGL14.EGL_NO_SURFACE) goto L8;
        return;
    L8:
        throw new RuntimeException("Failed to create pixel buffer surface with size " + r5 + "x" + r6 + ": 0x" + Integer.toHexString(EGL14.eglGetError()));
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture r2) {
        createSurfaceInternal(r2, false);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface r2) {
        createSurfaceInternal(r2, false);
    }

    @Override // org.webrtc.EglBase
    public void detachCurrent() {
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGLDisplay r4 = this.eglDisplay;     // Catch: Throwable -> L8
        EGLSurface r2 = EGL14.EGL_NO_SURFACE;     // Catch: Throwable -> L8
        if (EGL14.eglMakeCurrent(r4, r2, r2, EGL14.EGL_NO_CONTEXT) == false) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L11:
        throw new RuntimeException("eglDetachCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.EglBase
    public /* bridge */ /* synthetic */ EglBase.Context getEglBaseContext() {
        return getEglBaseContext();
    }

    @Override // org.webrtc.EglBase
    public Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    @Override // org.webrtc.EglBase
    public boolean hasBackgroundSurface() {
        if (this.eglSurfaceBackground == EGL14.EGL_NO_SURFACE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.webrtc.EglBase
    public void makeBackgroundCurrent() {
        checkIsNotReleased();
        if (this.eglSurfaceBackground == EGL14.EGL_NO_SURFACE) goto L16;
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGLDisplay r2 = this.eglDisplay;     // Catch: Throwable -> L10
        EGLSurface r3 = this.eglSurfaceBackground;     // Catch: Throwable -> L10
        if (EGL14.eglMakeCurrent(r2, r3, r3, this.eglContext) == false) goto L13;
        monitor-exit(r1);     // Catch: Throwable -> L10
        return;
    L13:
        throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't make current");
    }

    @Override // org.webrtc.EglBase
    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) goto L16;
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGLDisplay r2 = this.eglDisplay;     // Catch: Throwable -> L10
        EGLSurface r3 = this.eglSurface;     // Catch: Throwable -> L10
        if (EGL14.eglMakeCurrent(r2, r3, r3, this.eglContext) == false) goto L13;
        monitor-exit(r1);     // Catch: Throwable -> L10
        return;
    L13:
        throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't make current");
    }

    @Override // org.webrtc.EglBase
    public void release() {
        checkIsNotReleased();
        releaseSurface(false);
        releaseSurface(true);
        detachCurrent();
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface(boolean r2) {
        if (r2 == false) goto L7;
        EGLSurface r22 = this.eglSurfaceBackground;
        if (r22 == EGL14.EGL_NO_SURFACE) goto L11;
        EGL14.eglDestroySurface(this.eglDisplay, r22);
        this.eglSurfaceBackground = EGL14.EGL_NO_SURFACE;
        return;
    L11:
        return;
    L7:
        EGLSurface r23 = this.eglSurface;
        if (r23 == EGL14.EGL_NO_SURFACE) goto L12;
        EGL14.eglDestroySurface(this.eglDisplay, r23);
        this.eglSurface = EGL14.EGL_NO_SURFACE;
        return;
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] r0 = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, r0, 0);
        return r0[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] r0 = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, r0, 0);
        return r0[0];
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(long r3, boolean r5) {
        checkIsNotReleased();
        if (r5 == false) goto L5;
        EGLSurface r52 = this.eglSurfaceBackground;
    L7:
        if (r52 == EGL14.EGL_NO_SURFACE) goto L16;
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, r52, r3);     // Catch: Throwable -> L13
        EGL14.eglSwapBuffers(this.eglDisplay, r52);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        throw th;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't swap buffers");
        return;
    L5:
        r52 = this.eglSurface;
        goto L7
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(boolean r2) {
        checkIsNotReleased();
        if (r2 == false) goto L5;
        EGLSurface r22 = this.eglSurfaceBackground;
    L7:
        if (r22 == EGL14.EGL_NO_SURFACE) goto L16;
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        EGL14.eglSwapBuffers(this.eglDisplay, r22);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        throw th;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't swap buffers");
        return;
    L5:
        r22 = this.eglSurface;
        goto L7
    }
}
