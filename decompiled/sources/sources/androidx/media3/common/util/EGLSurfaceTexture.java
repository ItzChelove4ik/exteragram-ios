package androidx.media3.common.util;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;

/* loaded from: classes3.dex */
public final class EGLSurfaceTexture implements SurfaceTexture.OnFrameAvailableListener, Runnable {
    private static final int[] EGL_CONFIG_ATTRIBUTES = null;
    private EGLContext context;
    private EGLDisplay display;
    private final Handler handler;
    private EGLSurface surface;
    private SurfaceTexture texture;
    private final int[] textureIdHolder;

    public interface TextureImageListener {
    }

    static {
        EGL_CONFIG_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    }

    public EGLSurfaceTexture(Handler r2) {
        this(r2, null);
    }

    public EGLSurfaceTexture(Handler r1, TextureImageListener r2) {
        this.handler = r1;
        this.textureIdHolder = new int[1];
    }

    private static EGLConfig chooseEGLConfig(EGLDisplay r9) {
        boolean r0 = true;
        EGLConfig[] r4 = new EGLConfig[1];
        int[] r7 = new int[1];
        boolean r92 = EGL14.eglChooseConfig(r9, EGL_CONFIG_ATTRIBUTES, 0, r4, 0, 1, r7, 0);
        if (r92 == true) goto L5;
    L9:
        r0 = false;
    L10:
        GlUtil.checkGlException(r0, Util.formatInvariant("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", new Object[]{Boolean.valueOf(r92), Integer.valueOf(r7[0]), r4[0]}));
        return r4[0];
    L5:
        if (r7[0] <= 0) goto L9;
        if (r4[0] == null) goto L9;
        goto L9
    }

    private static EGLContext createEGLContext(EGLDisplay r6, EGLConfig r7, int r8) {
        boolean r4 = true;
        if (r8 != 0) goto L5;
        int[] r82 = {12440, 2, 12344};
    L6:
        EGLContext r62 = EGL14.eglCreateContext(r6, r7, EGL14.EGL_NO_CONTEXT, r82, 0);
        if (r62 != null) goto L10;
        r4 = false;
    L10:
        GlUtil.checkGlException(r4, "eglCreateContext failed");
        return r62;
    L5:
        r82 = new int[]{12440, 2, 12992, 1, 12344};
        goto L6
    }

    private static EGLSurface createEGLSurface(EGLDisplay r9, EGLConfig r10, EGLContext r11, int r12) {
        boolean r0 = true;
        if (r12 != 1) goto L6;
        EGLSurface r102 = EGL14.EGL_NO_SURFACE;
    L14:
        GlUtil.checkGlException(EGL14.eglMakeCurrent(r9, r102, r102, r11), "eglMakeCurrent failed");
        return r102;
    L6:
        if (r12 != 2) goto L8;
        int[] r122 = {12375, 1, 12374, 1, 12992, 1, 12344};
    L9:
        r102 = EGL14.eglCreatePbufferSurface(r9, r10, r122, 0);
        if (r102 != null) goto L13;
        r0 = false;
    L13:
        GlUtil.checkGlException(r0, "eglCreatePbufferSurface failed");
        goto L14
    L8:
        r122 = new int[]{12375, 1, 12374, 1, 12344};
        goto L9
    }

    private void dispatchOnFrameAvailable() {
    }

    private static void generateTextureIds(int[] r2) {
        GLES20.glGenTextures(1, r2, 0);
        GlUtil.checkGlError();
    }

    private static EGLDisplay getDefaultDisplay() {
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        if (r1 == null) goto L5;
        boolean r3 = true;
    L6:
        GlUtil.checkGlException(r3, "eglGetDisplay failed");
        int[] r32 = new int[2];
        GlUtil.checkGlException(EGL14.eglInitialize(r1, r32, 0, r32, 1), "eglInitialize failed");
        return r1;
    L5:
        r3 = false;
        goto L6
    }

    public SurfaceTexture getSurfaceTexture() {
        return (SurfaceTexture) Assertions.checkNotNull(this.texture);
    }

    public void init(int r4) {
        EGLDisplay r0 = getDefaultDisplay();
        this.display = r0;
        EGLConfig r02 = chooseEGLConfig(r0);
        EGLContext r1 = createEGLContext(this.display, r02, r4);
        this.context = r1;
        this.surface = createEGLSurface(this.display, r02, r1, r4);
        generateTextureIds(this.textureIdHolder);
        SurfaceTexture r42 = new SurfaceTexture(this.textureIdHolder[0]);
        this.texture = r42;
        r42.setOnFrameAvailableListener(this);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture r1) {
        this.handler.post(this);
    }

    public void release() {
        this.handler.removeCallbacks(this);
        SurfaceTexture r1 = this.texture;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.release();     // Catch: Throwable -> L7
        GLES20.glDeleteTextures(1, this.textureIdHolder, 0);     // Catch: Throwable -> L7
    L9:
        EGLDisplay r12 = this.display;
        if (r12 != null) goto L12;
    L14:
        EGLSurface r13 = this.surface;
        if (r13 != null) goto L17;
    L19:
        EGLContext r14 = this.context;
        if (r14 == null) goto L22;
        EGL14.eglDestroyContext(this.display, r14);
    L22:
        EGL14.eglReleaseThread();
        EGLDisplay r15 = this.display;
        if (r15 != null) goto L25;
    L27:
        this.display = null;
        this.context = null;
        this.surface = null;
        this.texture = null;
        return;
    L25:
        if (r15.equals(EGL14.EGL_NO_DISPLAY) == true) goto L27;
        EGL14.eglTerminate(this.display);
        goto L27
    L17:
        if (r13.equals(EGL14.EGL_NO_SURFACE) == true) goto L19;
        EGL14.eglDestroySurface(this.display, this.surface);
        goto L19
    L12:
        if (r12.equals(EGL14.EGL_NO_DISPLAY) == true) goto L14;
        EGLDisplay r16 = this.display;
        EGLSurface r2 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r16, r2, r2, EGL14.EGL_NO_CONTEXT);
    L7:
        th = move-exception;
        EGLDisplay r22 = this.display;
        if (r22 != null) goto L32;
    L34:
        EGLSurface r23 = this.surface;
        if (r23 != null) goto L37;
    L39:
        EGLContext r24 = this.context;
        if (r24 == null) goto L42;
        EGL14.eglDestroyContext(this.display, r24);
    L42:
        EGL14.eglReleaseThread();
        EGLDisplay r25 = this.display;
        if (r25 != null) goto L45;
    L47:
        this.display = null;
        this.context = null;
        this.surface = null;
        this.texture = null;
        throw th;
    L45:
        if (r25.equals(EGL14.EGL_NO_DISPLAY) == true) goto L47;
        EGL14.eglTerminate(this.display);
        goto L47
    L37:
        if (r23.equals(EGL14.EGL_NO_SURFACE) == true) goto L39;
        EGL14.eglDestroySurface(this.display, this.surface);
        goto L39
    L32:
        if (r22.equals(EGL14.EGL_NO_DISPLAY) == true) goto L34;
        EGLDisplay r26 = this.display;
        EGLSurface r3 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r26, r3, r3, EGL14.EGL_NO_CONTEXT);
        goto L34
    }

    @Override // java.lang.Runnable
    public void run() {
        dispatchOnFrameAvailable();
        SurfaceTexture r0 = this.texture;
        if (r0 == null) goto L9;
        r0.updateTexImage();     // Catch: RuntimeException -> L6
        return;
    L10:
        return;
    }
}
