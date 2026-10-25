package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Looper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.FilterShaders;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class FilterGLThread extends DispatchQueue {
    private final BlurringShader.BlurManager blurManager;
    private boolean blurred;
    private Bitmap currentBitmap;
    private final Runnable drawRunnable;
    private EGL10 egl10;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private FilterShaders filterShaders;
    private boolean filterTextureAvailable;
    private volatile boolean initied;
    private boolean isVideo;
    private long lastRenderCallTime;
    private int orientation;
    private int renderBufferHeight;
    private int renderBufferWidth;
    private boolean renderDataSet;
    private int simpleInputTexCoordHandle;
    private int simpleOESInputTexCoordHandle;
    private int simpleOESMatrixHandle;
    private int simpleOESPositionHandle;
    private int simpleOESShaderProgram;
    private int simpleOESSourceImageHandle;
    private int simplePositionHandle;
    private int simpleShaderProgram;
    private int simpleSourceImageHandle;
    private volatile int surfaceHeight;
    private SurfaceTexture surfaceTexture;
    private volatile int surfaceWidth;
    private FloatBuffer textureBuffer;
    private BlurringShader uiBlur;
    private boolean uiBlurEnabled;
    private boolean updateSurface;
    private FilterGLThreadVideoDelegate videoDelegate;
    private boolean videoFrameAvailable;
    private int videoHeight;
    private SurfaceTexture videoSurfaceTexture;
    private int[] videoTexture;
    private float[] videoTextureMatrix;
    private int videoWidth;

    public interface FilterGLThreadVideoDelegate {
        void onVideoSurfaceCreated(SurfaceTexture r1);
    }

    /* renamed from: $r8$lambda$0hLcxsf_g7MB-FbJ4DctXEHucGM, reason: not valid java name */
    public static /* synthetic */ void m10575$r8$lambda$0hLcxsf_g7MBFbJ4DctXEHucGM(FilterGLThread r0, boolean r1, boolean r2, boolean r3) {
        r0.lambda$requestRender$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$5YMXutPaSIAedWe4oz3DT-8wIyY, reason: not valid java name */
    public static /* synthetic */ void m10576$r8$lambda$5YMXutPaSIAedWe4oz3DT8wIyY(FilterGLThread r0) {
        r0.lambda$shutdown$8();
    }

    public static /* synthetic */ void $r8$lambda$7lH7ddH4aLdnSjhJjv8x3iu6fhk(FilterGLThread r0, SurfaceTexture r1) {
        r0.lambda$initGL$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$DTUQjrhxjY7p_lH5Ib1LCpOSq8w(FilterGLThread r0, StoryEntry.HDRInfo r1) {
        r0.lambda$updateHDRInfo$0(r1);
    }

    /* renamed from: $r8$lambda$JnsxJZq2-SMSvXVOnvEcP0rG1GU, reason: not valid java name */
    public static /* synthetic */ void m10577$r8$lambda$JnsxJZq2SMSvXVOnvEcP0rG1GU(FilterGLThread r0, int r1, int r2) {
        r0.lambda$setVideoSize$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OpWlwVwIDfGsXTfrcC0zTeEl1xg(FilterGLThread r0) {
        r0.lambda$initGL$3();
    }

    /* renamed from: $r8$lambda$WldJrnZoIw1FnwVYWD-jnPGfDkQ, reason: not valid java name */
    public static /* synthetic */ void m10578$r8$lambda$WldJrnZoIw1FnwVYWDjnPGfDkQ(FilterGLThread r0, Bitmap[] r1, CountDownLatch r2) {
        r0.lambda$getTexture$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_cwVOyiOJl0O3XqAykmHLL0UXwo(FilterGLThread r0, int r1, int r2) {
        r0.lambda$setSurfaceTextureSize$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gGwrX799bvX2q6TPVPBCxPpko50(FilterGLThread r0, int r1, int r2) {
        r0.lambda$updateUiBlurGradient$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gbzA7Lm8hndVE48c1PT9SiGmtL0(FilterGLThread r0, FilterShaders.FilterShadersDelegate r1) {
        r0.lambda$setFilterGLThreadDelegate$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$pZ4ThGPthsX7IFhRaePTiMILfe0(FilterGLThread r0) {
        r0.lambda$new$5();
    }

    public FilterGLThread(SurfaceTexture r4, Bitmap r5, int r6, boolean r7, StoryEntry.HDRInfo r8, boolean r9, BlurringShader.BlurManager r10, int r11, int r12) {
        super("PhotoFilterGLThread", false);
        this.videoTextureMatrix = new float[16];
        this.videoTexture = new int[1];
        this.drawRunnable = new FilterGLThread$$ExternalSyntheticLambda3(this);
        this.surfaceTexture = r4;
        this.surfaceWidth = r11;
        this.surfaceHeight = r12;
        this.currentBitmap = r5;
        this.orientation = r6;
        this.blurManager = r10;
        if (r10 == null) goto L5;
        boolean r42 = true;
    L6:
        this.uiBlurEnabled = r42;
        if (r42 == false) goto L9;
        BlurringShader r43 = new BlurringShader(this);
        this.uiBlur = r43;
        r43.setBlurManager(r10);
    L9:
        this.isVideo = false;
        FilterShaders r44 = new FilterShaders(false, r8);
        this.filterShaders = r44;
        r44.setScaleBitmap(r9);
        float[] r45 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        if (r7 == false) goto L12;
        r45[2] = 0.0f;
        r45[0] = 1.0f;
        r45[6] = 0.0f;
        r45[4] = 1.0f;
    L12:
        ByteBuffer r52 = ByteBuffer.allocateDirect(32);
        r52.order(ByteOrder.nativeOrder());
        FloatBuffer r53 = r52.asFloatBuffer();
        this.textureBuffer = r53;
        r53.put(r45);
        this.textureBuffer.position(0);
        start();
        return;
    L5:
        r42 = false;
        goto L6
    }

    public FilterGLThread(SurfaceTexture r4, FilterGLThreadVideoDelegate r5, StoryEntry.HDRInfo r6, BlurringShader.BlurManager r7, int r8, int r9) {
        boolean r1 = false;
        super("VideoFilterGLThread", false);
        this.videoTextureMatrix = new float[16];
        this.videoTexture = new int[1];
        this.drawRunnable = new FilterGLThread$$ExternalSyntheticLambda3(this);
        this.surfaceTexture = r4;
        this.surfaceWidth = r8;
        this.surfaceHeight = r9;
        this.videoDelegate = r5;
        this.blurManager = r7;
        if (r7 == null) goto L5;
        r1 = true;
    L5:
        this.uiBlurEnabled = r1;
        if (r1 == false) goto L8;
        BlurringShader r42 = new BlurringShader(this);
        this.uiBlur = r42;
        r42.setBlurManager(r7);
    L8:
        this.isVideo = true;
        this.filterShaders = new FilterShaders(true, r6);
        start();
    }

    private Bitmap getRenderBufferBitmap() {
        int r0 = this.renderBufferWidth;
        if (r0 == 0) goto L9;
        int r1 = this.renderBufferHeight;
        if (r1 == 0) goto L11;
        ByteBuffer r7 = ByteBuffer.allocateDirect((r0 * r1) * 4);
        GLES20.glReadPixels(0, 0, this.renderBufferWidth, this.renderBufferHeight, 6408, 5121, r7);
        Bitmap r8 = Bitmap.createBitmap(this.renderBufferWidth, this.renderBufferHeight, Bitmap.Config.ARGB_8888);
        r8.copyPixelsFromBuffer(r7);
        return r8;
    L11:
        return null;
    L9:
        return null;
    }

    private boolean initGL() {
        EGL10 r0 = (EGL10) EGLContext.getEGL();
        this.egl10 = r0;
        EGLDisplay r02 = r0.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.eglDisplay = r02;
        if (r02 != EGL10.EGL_NO_DISPLAY) goto L10;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L7:
        finish();
        return false;
    L10:
        if (this.egl10.eglInitialize(r02, new int[2]) == false) goto L12;
        int[] r8 = new int[1];
        EGLConfig[] r6 = new EGLConfig[1];
        if (this.egl10.eglChooseConfig(this.eglDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344}, r6, 1, r8) == true) goto L24;
        if (BuildVars.LOGS_ENABLED == false) goto L21;
        FileLog.e("eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L21:
        finish();
        return false;
    L24:
        if (r8[0] <= 0) goto L95;
        EGLConfig r3 = r6[0];
        int[] r1 = {12440, 2, 12344};
        BlurringShader.BlurManager r4 = this.blurManager;
        if (r4 == null) goto L28;
        EGLContext r42 = r4.getParentContext();
    L29:
        EGLContext r12 = this.egl10.eglCreateContext(this.eglDisplay, r3, r42, r1);
        this.eglContext = r12;
        if (r12 == null) goto L32;
        BlurringShader.BlurManager r43 = this.blurManager;
        if (r43 == null) goto L40;
        r43.acquiredContext(r12);
    L40:
        if (this.surfaceTexture == null) goto L92;
        EGLSurface r13 = this.egl10.eglCreateWindowSurface(this.eglDisplay, r3, this.surfaceTexture, null);
        this.eglSurface = r13;
        if (r13 == null) goto L88;
        if (r13 == EGL10.EGL_NO_SURFACE) goto L88;
        if (this.egl10.eglMakeCurrent(this.eglDisplay, r13, r13, this.eglContext) == false) goto L49;
        int r14 = FilterShaders.loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r32 = FilterShaders.loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;void main() {gl_FragColor = texture2D(sTexture, vTextureCoord);}");
        if (r14 == 0) goto L86;
        if (r32 == 0) goto L86;
        int r44 = GLES20.glCreateProgram();
        this.simpleShaderProgram = r44;
        GLES20.glAttachShader(r44, r14);
        GLES20.glAttachShader(this.simpleShaderProgram, r32);
        GLES20.glBindAttribLocation(this.simpleShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.simpleShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.simpleShaderProgram);
        int[] r15 = new int[1];
        GLES20.glGetProgramiv(this.simpleShaderProgram, 35714, r15, 0);
        int r16 = r15[0];
        int r5 = this.simpleShaderProgram;
        if (r16 != 0) goto L59;
        GLES20.glDeleteProgram(r5);
        this.simpleShaderProgram = 0;
    L61:
        if (setupVideoShader(null) == true) goto L63;
        return false;
    L63:
        Bitmap r17 = this.currentBitmap;
        if (r17 == null) goto L67;
        int r18 = r17.getWidth();
        int r33 = this.currentBitmap.getHeight();
    L66:
        int r11 = r18;
        int r122 = r33;
        if (this.videoDelegate == null) goto L72;
        GLES20.glGenTextures(1, this.videoTexture, 0);
        Matrix.setIdentityM(this.videoTextureMatrix, 0);
        SurfaceTexture r19 = new SurfaceTexture(this.videoTexture[0]);
        this.videoSurfaceTexture = r19;
        r19.setOnFrameAvailableListener(new FilterGLThread$$ExternalSyntheticLambda8(this));
        GLES20.glBindTexture(36197, this.videoTexture[0]);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        AndroidUtilities.runOnUIThread(new FilterGLThread$$ExternalSyntheticLambda9(this));
    L72:
        if (this.uiBlurEnabled == false) goto L79;
        BlurringShader r110 = this.uiBlur;
        if (r110 == null) goto L79;
        if (r110.setup(this.surfaceWidth / this.surfaceHeight, true, this.blurManager.padding) == true) goto L79;
        FileLog.e("Failed to create uiBlurFramebuffer");
        this.uiBlurEnabled = false;
        this.uiBlur = null;
    L79:
        if (this.filterShaders.create() == true) goto L82;
        finish();
        return false;
    L82:
        if (r11 == 0) goto L85;
        if (r122 == 0) goto L85;
        this.filterShaders.setRenderData(this.currentBitmap, this.orientation, this.videoTexture[0], r11, r122);
        this.renderDataSet = true;
        this.renderBufferWidth = this.filterShaders.getRenderBufferWidth();
        this.renderBufferHeight = this.filterShaders.getRenderBufferHeight();
    L85:
        return true;
    L67:
        r18 = this.videoWidth;
        r33 = this.videoHeight;
        goto L66
    L59:
        this.simplePositionHandle = GLES20.glGetAttribLocation(r5, "position");
        this.simpleInputTexCoordHandle = GLES20.glGetAttribLocation(this.simpleShaderProgram, "inputTexCoord");
        this.simpleSourceImageHandle = GLES20.glGetUniformLocation(this.simpleShaderProgram, "sourceImage");
    L86:
        return false;
    L49:
        if (BuildVars.LOGS_ENABLED == false) goto L51;
        FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L51:
        finish();
        return false;
    L88:
        if (BuildVars.LOGS_ENABLED == false) goto L90;
        FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L90:
        finish();
        return false;
    L92:
        finish();
        return false;
    L32:
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.e("eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L34:
        finish();
        return false;
    L28:
        r42 = EGL10.EGL_NO_CONTEXT;
        goto L29
    L95:
        if (BuildVars.LOGS_ENABLED == false) goto L97;
        FileLog.e("eglConfig not initialized");
    L97:
        finish();
        return false;
    L12:
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
    L14:
        finish();
        return false;
    }

    private /* synthetic */ void lambda$getTexture$7(Bitmap[] r6, CountDownLatch r7) {
        GLES20.glBindFramebuffer(36160, this.filterShaders.getRenderFrameBuffer());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.filterShaders.getRenderTexture(!this.blurred ? 1 : 0), 0);
        GLES20.glClear(0);
        r6[0] = getRenderBufferBitmap();
        r7.countDown();
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glClear(0);
    }

    private /* synthetic */ void lambda$initGL$2(SurfaceTexture r2) {
        requestRender(false, true, true);
    }

    private /* synthetic */ void lambda$initGL$3() {
        this.videoDelegate.onVideoSurfaceCreated(this.videoSurfaceTexture);
    }

    private /* synthetic */ void lambda$new$5() {
        if (this.initied == false) goto L44;
        makeCurrentContext();
        if (this.updateSurface == false) goto L9;
        this.videoSurfaceTexture.updateTexImage();
        this.videoSurfaceTexture.getTransformMatrix(this.videoTextureMatrix);
        setRenderData();
        this.updateSurface = false;
        this.filterShaders.onVideoFrameUpdate(this.videoTextureMatrix);
        this.videoFrameAvailable = true;
    L9:
        if (this.renderDataSet == true) goto L12;
        return;
    L12:
        if (this.isVideo == false) goto L24;
        if (this.filterShaders.drawOriginal() == false) goto L24;
        GLES20.glViewport(0, 0, this.surfaceWidth, this.surfaceHeight);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glUseProgram(this.simpleOESShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.videoTexture[0]);
        GLES20.glUniform1i(this.simpleOESSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.simpleOESInputTexCoordHandle);
        int r5 = this.simpleOESInputTexCoordHandle;
        FloatBuffer r0 = this.textureBuffer;
        if (r0 != null) goto L19;
        r0 = this.filterShaders.getTextureBuffer();
    L19:
        GLES20.glVertexAttribPointer(r5, 2, 5126, false, 8, r0);
        GLES20.glEnableVertexAttribArray(this.simpleOESPositionHandle);
        GLES20.glVertexAttribPointer(this.simpleOESPositionHandle, 2, 5126, false, 8, this.filterShaders.getVertexInvertBuffer());
        GLES20.glUniformMatrix4fv(this.simpleOESMatrixHandle, 1, false, this.videoTextureMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        this.egl10.eglSwapBuffers(this.eglDisplay, this.eglSurface);
        BlurringShader r02 = this.uiBlur;
        if (r02 == null) goto L41;
        r02.draw(this.videoTextureMatrix, this.videoTexture[0], this.videoWidth, this.videoHeight);
        return;
    L41:
        return;
    L24:
        if (this.videoDelegate != null) goto L26;
    L27:
        GLES20.glViewport(0, 0, this.renderBufferWidth, this.renderBufferHeight);
        this.filterShaders.drawSkinSmoothPass();
        this.filterShaders.drawEnhancePass();
        if (this.videoDelegate != null) goto L30;
        this.filterShaders.drawSharpenPass();
    L30:
        this.filterShaders.drawCustomParamsPass();
        this.blurred = this.filterShaders.drawBlurPass();
        this.filterTextureAvailable = true;
    L32:
        if (this.filterTextureAvailable == false) goto L42;
        GLES20.glViewport(0, 0, this.surfaceWidth, this.surfaceHeight);
        GLES20.glBindFramebuffer(36160, 0);
        int r03 = this.filterShaders.getRenderTexture(1 ^ (this.blurred ? 1 : 0));
        GLES20.glUseProgram(this.simpleShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, r03);
        GLES20.glUniform1i(this.simpleSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandle);
        int r52 = this.simpleInputTexCoordHandle;
        FloatBuffer r1 = this.textureBuffer;
        if (r1 != null) goto L37;
        r1 = this.filterShaders.getTextureBuffer();
    L37:
        GLES20.glVertexAttribPointer(r52, 2, 5126, false, 8, r1);
        GLES20.glEnableVertexAttribArray(this.simplePositionHandle);
        GLES20.glVertexAttribPointer(this.simplePositionHandle, 2, 5126, false, 8, this.filterShaders.getVertexBuffer());
        GLES20.glDrawArrays(5, 0, 4);
        this.egl10.eglSwapBuffers(this.eglDisplay, this.eglSurface);
        BlurringShader r12 = this.uiBlur;
        if (r12 == null) goto L43;
        r12.draw(null, r03, this.renderBufferWidth, this.renderBufferHeight);
        return;
    L43:
        return;
    L42:
        return;
    L26:
        if (this.videoFrameAvailable == false) goto L32;
    }

    private /* synthetic */ void lambda$requestRender$10(boolean r5, boolean r6, boolean r7) {
        if (r5 == false) goto L4;
        this.filterShaders.requestUpdateBlurTexture();
    L4:
        if (r6 == false) goto L6;
        this.updateSurface = true;
    L6:
        long r52 = System.currentTimeMillis();
        if (r7 == false) goto L9;
    L12:
        this.lastRenderCallTime = r52;
        this.drawRunnable.run();
        return;
    L9:
        if (Math.abs(this.lastRenderCallTime - r52) > 30) goto L12;
    }

    private /* synthetic */ void lambda$setFilterGLThreadDelegate$1(FilterShaders.FilterShadersDelegate r1) {
        this.filterShaders.setDelegate(r1);
    }

    private /* synthetic */ void lambda$setSurfaceTextureSize$9(int r1, int r2) {
        this.surfaceWidth = r1;
        this.surfaceHeight = r2;
    }

    private /* synthetic */ void lambda$setVideoSize$4(int r4, int r5) {
        if (this.videoWidth == r4) goto L5;
    L7:
        this.videoWidth = r4;
        this.videoHeight = r5;
        int r42 = SharedConfig.getDevicePerformanceClass();
        int r0 = 1920;
        if (r42 == 1) goto L13;
        if (r42 == 2) goto L11;
        r0 = 720;
        goto L13
    L11:
        Point r43 = AndroidUtilities.displaySize;
        r0 = Math.min(1920, Math.max(r43.x, r43.y));
    L13:
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L19;
        int r44 = this.videoWidth;
        if (r44 <= 1280) goto L17;
    L18:
        this.videoWidth = r44 / 2;
        this.videoHeight /= 2;
        goto L19
    L17:
        if (this.videoHeight > 1280) goto L18;
    L19:
        int r45 = this.videoWidth;
        if (r45 <= r0) goto L22;
    L23:
        int r52 = this.videoHeight;
        if (r45 <= r52) goto L26;
        this.videoHeight = (int) (r52 / (r0 / r45));
        this.videoWidth = r0;
    L27:
        this.renderDataSet = false;
        setRenderData();
        this.drawRunnable.run();
        return;
    L26:
        this.videoWidth = (int) (r45 / (r0 / r52));
        this.videoHeight = r0;
        goto L27
    L22:
        if (this.videoHeight <= r0) goto L27;
    L5:
        if (this.videoHeight != r5) goto L7;
    }

    private /* synthetic */ void lambda$shutdown$8() {
        finish();
        Looper r0 = Looper.myLooper();
        if (r0 == null) goto L6;
        r0.quit();
        return;
    }

    private /* synthetic */ void lambda$updateHDRInfo$0(StoryEntry.HDRInfo r1) {
        makeCurrentContext();
        setupVideoShader(r1);
        this.filterShaders.updateHDRInfo(r1);
    }

    private /* synthetic */ void lambda$updateUiBlurGradient$6(int r1, int r2) {
        this.uiBlur.updateGradient(r1, r2);
    }

    private void makeCurrentContext() {
        if (this.eglContext.equals(this.egl10.eglGetCurrentContext()) == true) goto L5;
    L6:
        EGL10 r0 = this.egl10;
        EGLDisplay r1 = this.eglDisplay;
        EGLSurface r2 = this.eglSurface;
        if (r0.eglMakeCurrent(r1, r2, r2, this.eglContext) == false) goto L9;
        return;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        return;
    L14:
        return;
    L5:
        if (this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == false) goto L6;
    }

    private void setRenderData() {
        if (this.renderDataSet == true) goto L11;
        int r5 = this.videoWidth;
        if (r5 <= 0) goto L12;
        int r6 = this.videoHeight;
        if (r6 <= 0) goto L13;
        this.filterShaders.setRenderData(this.currentBitmap, this.orientation, this.videoTexture[0], r5, r6);
        this.renderDataSet = true;
        this.renderBufferWidth = this.filterShaders.getRenderBufferWidth();
        this.renderBufferHeight = this.filterShaders.getRenderBufferHeight();
        return;
    L13:
        return;
    L12:
        return;
    }

    private boolean setupVideoShader(StoryEntry.HDRInfo r8) {
        if (r8 == null) goto L5;
        int r82 = r8.getHDRType();
    L7:
        if (r82 != 1) goto L10;
        String r2 = AndroidUtilities.readRes(R.raw.hdr2sdr_hlg);
    L14:
        if (r82 == 0) goto L16;
        int r83 = FilterShaders.loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        int r22 = FilterShaders.loadShader(35632, String.format(Locale.US, "%1$s\nvarying highp vec2 vTextureCoord;void main() {gl_FragColor = TEX(vTextureCoord);}", new Object[]{r2}));
    L17:
        if (r83 == 0) goto L27;
        if (r22 == 0) goto L27;
        int r3 = this.simpleOESShaderProgram;
        if (r3 == 0) goto L22;
        GLES20.glDeleteProgram(r3);
    L22:
        int r32 = GLES20.glCreateProgram();
        this.simpleOESShaderProgram = r32;
        GLES20.glAttachShader(r32, r83);
        GLES20.glAttachShader(this.simpleOESShaderProgram, r22);
        GLES20.glBindAttribLocation(this.simpleOESShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.simpleOESShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.simpleOESShaderProgram);
        int[] r84 = new int[1];
        GLES20.glGetProgramiv(this.simpleOESShaderProgram, 35714, r84, 0);
        int r85 = r84[0];
        int r4 = this.simpleOESShaderProgram;
        if (r85 != 0) goto L25;
        GLES20.glDeleteProgram(r4);
        this.simpleOESShaderProgram = 0;
    L26:
        return true;
    L25:
        this.simpleOESPositionHandle = GLES20.glGetAttribLocation(r4, "position");
        this.simpleOESInputTexCoordHandle = GLES20.glGetAttribLocation(this.simpleOESShaderProgram, "inputTexCoord");
        this.simpleOESSourceImageHandle = GLES20.glGetUniformLocation(this.simpleOESShaderProgram, "sourceImage");
        this.simpleOESMatrixHandle = GLES20.glGetUniformLocation(this.simpleOESShaderProgram, "videoMatrix");
    L27:
        return false;
    L16:
        r83 = FilterShaders.loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        r22 = FilterShaders.loadShader(35632, "#extension GL_OES_EGL_image_external : require\n" + "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;void main() {gl_FragColor = texture2D(sTexture, vTextureCoord);}".replace("sampler2D", "samplerExternalOES"));
        goto L17
    L10:
        if (r82 != 2) goto L12;
        r2 = AndroidUtilities.readRes(R.raw.hdr2sdr_pq);
        goto L14
    L12:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L5:
        r82 = 0;
        goto L7
    }

    public void finish() {
        this.currentBitmap = null;
        if (this.eglSurface == null) goto L5;
        EGL10 r1 = this.egl10;
        EGLDisplay r2 = this.eglDisplay;
        EGLSurface r3 = EGL10.EGL_NO_SURFACE;
        r1.eglMakeCurrent(r2, r3, r3, EGL10.EGL_NO_CONTEXT);
        this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
        this.eglSurface = null;
    L5:
        EGLContext r12 = this.eglContext;
        if (r12 == null) goto L11;
        BlurringShader.BlurManager r22 = this.blurManager;
        if (r22 == null) goto L10;
        r22.destroyedContext(r12);
    L10:
        this.egl10.eglDestroyContext(this.eglDisplay, this.eglContext);
        this.eglContext = null;
    L11:
        EGLDisplay r13 = this.eglDisplay;
        if (r13 == null) goto L14;
        this.egl10.eglTerminate(r13);
        this.eglDisplay = null;
    L14:
        SurfaceTexture r5 = this.surfaceTexture;
        if (r5 == null) goto L18;
        r5.release();
        return;
    }

    public Bitmap getTexture() {
        if (this.initied == true) goto L5;
        return null;
    L5:
        if (isAlive() == false) goto L20;
        CountDownLatch r0 = new CountDownLatch(1);
        Bitmap[] r1 = new Bitmap[1];
    L12:
        e = move-exception;
        FileLog.e(e);
    L15:
        return r1[0];
    L9:
        if (postRunnable(new FilterGLThread$$ExternalSyntheticLambda1(this, r1, r0)) == false) goto L15;
        r0.await();     // Catch: Exception -> L12
        goto L15
    L20:
        return null;
    }

    public Bitmap getUiBlurBitmap() {
        BlurringShader r0 = this.uiBlur;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getBitmap();
    }

    @Override // org.telegram.messenger.DispatchQueue
    public void onLooperPrepared() {
        this.initied = initGL();
    }

    public void requestRender(boolean r2) {
        requestRender(r2, false, false);
    }

    public void requestRender(boolean r2, boolean r3, boolean r4) {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda6(this, r2, r4, r3));
    }

    public void setFilterGLThreadDelegate(FilterShaders.FilterShadersDelegate r2) {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda7(this, r2));
    }

    public void setSurfaceTextureSize(int r2, int r3) {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda4(this, r2, r3));
    }

    public void setVideoSize(int r2, int r3) {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda0(this, r2, r3));
    }

    public void shutdown() {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda2(this));
    }

    public void updateHDRInfo(StoryEntry.HDRInfo r2) {
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda10(this, r2));
    }

    public void updateUiBlurGradient(int r2, int r3) {
        if (this.uiBlur != null) goto L5;
        return;
    L5:
        postRunnable(new FilterGLThread$$ExternalSyntheticLambda5(this, r2, r3));
    }

    public void updateUiBlurManager(BlurringShader.BlurManager r1) {
        BlurringShader r0 = this.uiBlur;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setBlurManager(r1);
    }

    public void updateUiBlurTransform(android.graphics.Matrix r2, int r3, int r4) {
        BlurringShader r0 = this.uiBlur;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.updateTransform(r2, r3, r4);
        requestRender(false);
    }
}
