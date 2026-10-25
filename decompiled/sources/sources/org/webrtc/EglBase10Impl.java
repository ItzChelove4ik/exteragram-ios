package org.webrtc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.webrtc.EglBase;
import org.webrtc.EglBase10;

/* loaded from: classes6.dex */
class EglBase10Impl implements EglBase10 {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final String TAG = "EglBase10Impl";
    private final EGL10 egl;
    private EGLSurface eglBackgroundSurface;
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;

    public static class Context implements EglBase10.Context {
        private final EGL10 egl;
        private final EGLContext eglContext;
        private final EGLConfig eglContextConfig;

        public Context(EGL10 r1, EGLContext r2, EGLConfig r3) {
            this.egl = r1;
            this.eglContext = r2;
            this.eglContextConfig = r3;
        }

        @Override // org.webrtc.EglBase.Context
        public long getNativeEglContext() {
            EGLContext r1 = this.egl.eglGetCurrentContext();
            EGLDisplay r2 = this.egl.eglGetCurrentDisplay();
            EGLSurface r3 = this.egl.eglGetCurrentSurface(12377);
            EGLSurface r4 = this.egl.eglGetCurrentSurface(12378);
            if (r2 != EGL10.EGL_NO_DISPLAY) goto L5;
            r2 = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        L5:
            EGLSurface r5 = null;
        L13:
            th = move-exception;
            if (0 == 0) goto L21;
            this.egl.eglMakeCurrent(r2, r3, r4, r1);
            this.egl.eglDestroySurface(r2, null);
        L21:
            throw th;
        L7:
            if (r1 == this.eglContext) goto L15;
            r5 = this.egl.eglCreatePbufferSurface(r2, this.eglContextConfig, new int[]{12375, 1, 12374, 1, 12344});     // Catch: Throwable -> L13
            if (this.egl.eglMakeCurrent(r2, r5, r5, this.eglContext) == true) goto L15;
            throw new RuntimeException("Failed to make temporary EGL surface active: " + this.egl.eglGetError());     // Catch: Throwable -> L13
        L15:
            long r6 = EglBase10Impl.m22345$$Nest$smnativeGetCurrentNativeEGLContext();     // Catch: Throwable -> L13
            if (r5 == null) goto L18;
            this.egl.eglMakeCurrent(r2, r3, r4, r1);
            this.egl.eglDestroySurface(r2, r5);
        L18:
            return r6;
        }

        @Override // org.webrtc.EglBase10.Context
        public EGLContext getRawContext() {
            return this.eglContext;
        }
    }

    public class FakeSurfaceHolder implements SurfaceHolder {
        private final Surface surface;
        final /* synthetic */ EglBase10Impl this$0;

        public FakeSurfaceHolder(EglBase10Impl r1, Surface r2) {
            this.this$0 = r1;
            this.surface = r2;
        }

        @Override // android.view.SurfaceHolder
        public void addCallback(SurfaceHolder.Callback r1) {
        }

        @Override // android.view.SurfaceHolder
        public Surface getSurface() {
            return this.surface;
        }

        @Override // android.view.SurfaceHolder
        public Rect getSurfaceFrame() {
            return null;
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return false;
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas() {
            return null;
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas(Rect r1) {
            return null;
        }

        @Override // android.view.SurfaceHolder
        public void removeCallback(SurfaceHolder.Callback r1) {
        }

        @Override // android.view.SurfaceHolder
        public void setFixedSize(int r1, int r2) {
        }

        @Override // android.view.SurfaceHolder
        public void setFormat(int r1) {
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean r1) {
        }

        @Override // android.view.SurfaceHolder
        public void setSizeFromLayout() {
        }

        @Override // android.view.SurfaceHolder
        @Deprecated
        public void setType(int r1) {
        }

        @Override // android.view.SurfaceHolder
        public void unlockCanvasAndPost(Canvas r1) {
        }
    }

    /* renamed from: -$$Nest$smnativeGetCurrentNativeEGLContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22345$$Nest$smnativeGetCurrentNativeEGLContext() {
        return nativeGetCurrentNativeEGLContext();
    }

    public EglBase10Impl(EGLContext r3, int[] r4) {
        EGLSurface r0 = EGL10.EGL_NO_SURFACE;
        this.eglSurface = r0;
        this.eglBackgroundSurface = r0;
        EGL10 r02 = (EGL10) EGLContext.getEGL();
        this.egl = r02;
        EGLDisplay r1 = getEglDisplay();
        this.eglDisplay = r1;
        this.eglConfig = getEglConfig(r02, r1, r4);
        int r42 = EglBase.getOpenGlesVersionFromConfig(r4);
        Logging.d(TAG, "Using OpenGL ES version " + r42);
        this.eglContext = createEglContext(r3, this.eglDisplay, this.eglConfig, r42);
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay != EGL10.EGL_NO_DISPLAY) goto L5;
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("This object has been released");
        return;
    L5:
        if (this.eglContext == EGL10.EGL_NO_CONTEXT) goto L9;
        if (this.eglConfig == null) goto L9;
    }

    private EGLContext createEglContext(EGLContext r4, EGLDisplay r5, EGLConfig r6, int r7) {
        if (r4 != null) goto L5;
    L9:
        int[] r72 = {EGL_CONTEXT_CLIENT_VERSION, r7, 12344};
        if (r4 != null) goto L12;
        r4 = EGL10.EGL_NO_CONTEXT;
    L12:
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGLContext r42 = this.egl.eglCreateContext(r5, r6, r4, r72);     // Catch: Throwable -> L21
        monitor-exit(r1);     // Catch: Throwable -> L21
        if (r42 == EGL10.EGL_NO_CONTEXT) goto L19;
        return r42;
    L19:
        Instance$$ExternalSyntheticBUOutline0.m("Failed to create EGL context: 0x", Integer.toHexString(this.egl.eglGetError()));
        return null;
    L21:
        th = move-exception;
        throw th;
    L5:
        if (r4 != EGL10.EGL_NO_CONTEXT) goto L9;
        GlShader$$ExternalSyntheticBUOutline1.m("Invalid sharedContext");
        return null;
    }

    private void createSurfaceInternal(Object r6, boolean r7) {
        if ((r6 instanceof SurfaceHolder) == false) goto L5;
    L9:
        checkIsNotReleased();
        EGLSurface r3 = EGL10.EGL_NO_SURFACE;
        if (r7 == false) goto L21;
        if (this.eglBackgroundSurface != r3) goto L18;
        EGL10 r1 = this.egl;
        EGLDisplay r2 = this.eglDisplay;
        EGLConfig r4 = this.eglConfig;
        EGLSurface r62 = r1.eglCreateWindowSurface(r2, r4, r6, new int[]{12344});
        this.eglBackgroundSurface = r62;
        if (r62 != r3) goto L29;
        Instance$$ExternalSyntheticBUOutline0.m("Failed to create window surface: 0x", Integer.toHexString(this.egl.eglGetError()));
        return;
    L29:
        return;
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
        return;
    L21:
        if (this.eglSurface != r3) goto L27;
        EGL10 r12 = this.egl;
        EGLDisplay r22 = this.eglDisplay;
        EGLConfig r42 = this.eglConfig;
        EGLSurface r63 = r12.eglCreateWindowSurface(r22, r42, r6, new int[]{12344});
        this.eglSurface = r63;
        if (r63 == r3) goto L25;
        return;
    L25:
        Instance$$ExternalSyntheticBUOutline0.m("Failed to create window surface: 0x", Integer.toHexString(this.egl.eglGetError()));
        return;
    L27:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
        return;
    L5:
        if ((r6 instanceof SurfaceTexture) == true) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Input must be either a SurfaceHolder or SurfaceTexture");
    }

    private static EGLConfig getEglConfig(EGL10 r6, EGLDisplay r7, int[] r8) {
        EGLConfig[] r3 = new EGLConfig[1];
        int[] r5 = new int[1];
        if (r6.eglChooseConfig(r7, r8, r3, 1, r5) == true) goto L5;
        Instance$$ExternalSyntheticBUOutline0.m("eglChooseConfig failed: 0x", Integer.toHexString(r6.eglGetError()));
        return null;
    L5:
        if (r5[0] <= 0) goto L11;
        EGLConfig r62 = r3[0];
        if (r62 == null) goto L9;
        return r62;
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("eglChooseConfig returned null");
        return null;
    L11:
        GlShader$$ExternalSyntheticBUOutline1.m("Unable to find any matching EGL config");
        return null;
    }

    private EGLDisplay getEglDisplay() {
        EGLDisplay r0 = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        EGL10 r1 = this.egl;
        if (r0 != EGL10.EGL_NO_DISPLAY) goto L5;
        Instance$$ExternalSyntheticBUOutline0.m("Unable to get EGL10 display: 0x", Integer.toHexString(r1.eglGetError()));
        return null;
    L5:
        if (r1.eglInitialize(r0, new int[2]) == false) goto L7;
        return r0;
    L7:
        Instance$$ExternalSyntheticBUOutline0.m("Unable to initialize EGL10: 0x", Integer.toHexString(this.egl.eglGetError()));
        return null;
    }

    private static native long nativeGetCurrentNativeEGLContext();

    @Override // org.webrtc.EglBase
    public void createBackgroundSurface(SurfaceTexture r2) {
        createSurfaceInternal(r2, true);
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int r6, int r7) {
        checkIsNotReleased();
        EGLSurface r0 = this.eglSurface;
        EGLSurface r1 = EGL10.EGL_NO_SURFACE;
        if (r0 != r1) goto L9;
        EGLSurface r02 = this.egl.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, r6, 12374, r7, 12344});
        this.eglSurface = r02;
        if (r02 == r1) goto L8;
        return;
    L8:
        throw new RuntimeException("Failed to create pixel buffer surface with size " + r6 + "x" + r7 + ": 0x" + Integer.toHexString(this.egl.eglGetError()));
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("Already has an EGLSurface");
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture r2) {
        createSurfaceInternal(r2, false);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface r2) {
        createSurfaceInternal(new FakeSurfaceHolder(this, r2), false);
    }

    @Override // org.webrtc.EglBase
    public void detachCurrent() {
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGL10 r2 = this.egl;     // Catch: Throwable -> L8
        EGLDisplay r3 = this.eglDisplay;     // Catch: Throwable -> L8
        EGLSurface r4 = EGL10.EGL_NO_SURFACE;     // Catch: Throwable -> L8
        if (r2.eglMakeCurrent(r3, r4, r4, EGL10.EGL_NO_CONTEXT) == false) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L11:
        throw new RuntimeException("eglDetachCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.EglBase
    public EglBase.Context getEglBaseContext() {
        return new Context(this.egl, this.eglContext, this.eglConfig);
    }

    @Override // org.webrtc.EglBase
    public boolean hasBackgroundSurface() {
        if (this.eglBackgroundSurface == EGL10.EGL_NO_SURFACE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.webrtc.EglBase
    public void makeBackgroundCurrent() {
        checkIsNotReleased();
        if (this.eglBackgroundSurface == EGL10.EGL_NO_SURFACE) goto L16;
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGL10 r2 = this.egl;     // Catch: Throwable -> L10
        EGLDisplay r3 = this.eglDisplay;     // Catch: Throwable -> L10
        EGLSurface r4 = this.eglBackgroundSurface;     // Catch: Throwable -> L10
        if (r2.eglMakeCurrent(r3, r4, r4, this.eglContext) == false) goto L13;
        monitor-exit(r1);     // Catch: Throwable -> L10
        return;
    L13:
        throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't make current");
    }

    @Override // org.webrtc.EglBase
    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) goto L16;
        Object r1 = EglBase.lock;
        monitor-enter(r1);
        EGL10 r2 = this.egl;     // Catch: Throwable -> L10
        EGLDisplay r3 = this.eglDisplay;     // Catch: Throwable -> L10
        EGLSurface r4 = this.eglSurface;     // Catch: Throwable -> L10
        if (r2.eglMakeCurrent(r3, r4, r4, this.eglContext) == false) goto L13;
        monitor-exit(r1);     // Catch: Throwable -> L10
        return;
    L13:
        throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));     // Catch: Throwable -> L10
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
        this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
        this.egl.eglTerminate(this.eglDisplay);
        this.eglContext = EGL10.EGL_NO_CONTEXT;
        this.eglDisplay = EGL10.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface(boolean r4) {
        EGLSurface r0 = EGL10.EGL_NO_SURFACE;
        if (r4 == false) goto L8;
        EGLSurface r42 = this.eglBackgroundSurface;
        if (r42 == r0) goto L12;
        this.egl.eglDestroySurface(this.eglDisplay, r42);
        this.eglBackgroundSurface = r0;
        return;
    L12:
        return;
    L8:
        EGLSurface r43 = this.eglSurface;
        if (r43 == r0) goto L13;
        this.egl.eglDestroySurface(this.eglDisplay, r43);
        this.eglSurface = r0;
        return;
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] r0 = new int[1];
        this.egl.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, r0);
        return r0[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] r0 = new int[1];
        this.egl.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, r0);
        return r0[0];
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(long r1, boolean r3) {
        swapBuffers(r3);
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(boolean r3) {
        if (r3 == false) goto L4;
        EGLSurface r32 = this.eglBackgroundSurface;
    L5:
        checkIsNotReleased();
        if (r32 == EGL10.EGL_NO_SURFACE) goto L15;
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        this.egl.eglSwapBuffers(this.eglDisplay, r32);     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    L15:
        GlShader$$ExternalSyntheticBUOutline1.m("No EGLSurface - can't swap buffers");
        return;
    L4:
        r32 = this.eglSurface;
        goto L5
    }
}
