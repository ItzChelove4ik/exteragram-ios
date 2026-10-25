package org.telegram.messenger.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import kotlin.time.DurationKt;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.video.MP4Builder;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.messenger.video.Mp4Movie;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.LivePlayer;
import org.webrtc.EglBase;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

@SuppressLint({"NewApi"})
/* loaded from: classes5.dex */
public class CameraView extends FrameLayout implements TextureView.SurfaceTextureListener, CameraController.ICameraView, CameraController.ErrorCallback {
    private static final int MSG_AUDIOFRAME_AVAILABLE = 3;
    private static final int MSG_START_RECORDING = 0;
    private static final int MSG_STOP_RECORDING = 1;
    private static final int MSG_VIDEOFRAME_AVAILABLE = 2;
    private static final int audioSampleRate = 44100;
    public boolean WRITE_TO_FILE_IN_BACKGROUND;
    private Object blurRenderNode;
    private TextureView blurTextureView;
    public ImageView blurredStubView;
    Rect bounds;
    private File cameraFile;
    private float[][] cameraMatrix;
    private final CameraSessionWrapper[] cameraSession;
    private CameraSessionWrapper cameraSessionRecording;
    private final int[][] cameraTexture;
    protected CameraGLThread cameraThread;
    private int clipBottom;
    private int clipTop;
    private boolean closingDualCamera;
    private int cx;
    private int cy;
    private CameraViewDelegate delegate;
    protected boolean dual;
    private boolean dualCameraAppeared;
    private Matrix dualMatrix;
    boolean firstFrame2Rendered;
    boolean firstFrameRendered;
    public boolean fit;
    ValueAnimator flipAnimator;
    boolean flipHalfReached;
    boolean flipping;
    private int focusAreaSize;
    private float focusProgress;
    private int fpsLimit;
    CameraInfo[] info;
    private boolean initFirstCameraAfterSecond;
    private boolean inited;
    private boolean initialFrontface;
    private float innerAlpha;
    private Paint innerPaint;
    private DecelerateInterpolator interpolator;
    private final ArrayList<Runnable> invalidateListeners;
    private boolean isFrontface;
    public boolean isStory;
    private volatile float lastCrossfadeValue;
    private long lastDrawTime;
    private long lastDualSwitchTime;
    private int lastHeight;
    private volatile float lastShapeTo;
    private int lastWidth;
    private final Object layoutLock;
    private boolean lazy;
    private float[][] mMVPMatrix;
    private float[][] mSTMatrix;
    private Matrix matrix;
    private int measurementsCount;
    private boolean mirror;
    private float[][] moldSTMatrix;
    long nextFrameTimeNs;
    Runnable onRecordingFinishRunnable;
    private boolean optimizeForBarcode;
    private float outerAlpha;
    private Paint outerPaint;
    private Size[] pictureSize;
    private volatile float pixelDualH;
    private volatile float pixelDualW;
    private volatile float pixelH;
    private volatile float pixelW;
    private Size[] previewSize;
    File recordFile;
    public boolean recordHevc;
    private Object renderNode;
    private float scaleX;
    private float scaleY;
    private Integer shape;
    private volatile float shapeValue;
    private volatile int surfaceHeight;
    private volatile int surfaceWidth;
    private float takePictureProgress;
    private FloatBuffer textureBuffer;
    private boolean textureInited;
    private TextureView textureView;
    private ValueAnimator textureViewAnimator;
    private Drawable thumbDrawable;
    private long toggleDualUntil;
    public boolean toggledDualAsSave;
    private Matrix txform;
    private final Runnable updateRotationMatrix;
    private final boolean useCamera2;
    private boolean useMaxPreview;
    private FloatBuffer vertexBuffer;
    private VideoRecorder videoEncoder;
    private int videoHeight;
    private int videoWidth;

    /* renamed from: org.telegram.messenger.camera.CameraView$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ CameraView this$0;

        public AnonymousClass1(CameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            if (r42 >= 0.5f) goto L5;
            boolean r0 = false;
        L6:
            float r43 = r42 * 180.0f;
            CameraView.m5103$$Nest$fgettextureView(this.this$0).setRotationY(r43);
            this.this$0.blurredStubView.setRotationY(r43);
            if (r0 == false) goto L12;
            CameraView r3 = this.this$0;
            if (r3.flipHalfReached == true) goto L13;
            r3.flipHalfReached = true;
            return;
        L13:
            return;
        L12:
            return;
        L5:
            r42 = r42 - 1.0f;
            r0 = true;
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.camera.CameraView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ CameraView this$0;

        public AnonymousClass2(CameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            CameraView r32 = this.this$0;
            r32.flipAnimator = null;
            CameraView.m5103$$Nest$fgettextureView(r32).setTranslationY(0.0f);
            CameraView.m5103$$Nest$fgettextureView(this.this$0).setRotationX(0.0f);
            CameraView.m5103$$Nest$fgettextureView(this.this$0).setRotationY(0.0f);
            CameraView.m5103$$Nest$fgettextureView(this.this$0).setScaleX(1.0f);
            CameraView.m5103$$Nest$fgettextureView(this.this$0).setScaleY(1.0f);
            this.this$0.blurredStubView.setRotationY(0.0f);
            CameraView r2 = this.this$0;
            if (r2.flipHalfReached == true) goto L5;
            r2.flipHalfReached = true;
        L5:
            r2.invalidate();
        }
    }

    /* renamed from: org.telegram.messenger.camera.CameraView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ CameraView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass3(CameraView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            TextureView r22 = CameraView.m5103$$Nest$fgettextureView(this.this$0);
            if (this.val$show == false) goto L5;
            float r0 = 1.0f;
        L6:
            r22.setAlpha(r0);
            CameraView.m5115$$Nest$fputtextureViewAnimator(this.this$0, null);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.camera.CameraView$4, reason: invalid class name */
    public class AnonymousClass4 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ CameraView this$0;

        public AnonymousClass4(CameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
            CameraGLThread r0 = this.this$0.cameraThread;
            if (r0 == null) goto L6;
            r0.setBlurSurfaceTexture(r1);
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
            CameraGLThread r0 = this.this$0.cameraThread;
            if (r0 == null) goto L7;
            r0.setBlurSurfaceTexture(null);
            return false;
        L7:
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    /* renamed from: org.telegram.messenger.camera.CameraView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ CameraView this$0;

        public AnonymousClass5(CameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.blurredStubView.setVisibility(8);
        }
    }

    public class CameraGLThread extends DispatchQueue {
        private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
        private static final int EGL_OPENGL_ES2_BIT = 4;
        private final int BLUR_CAMERA1;
        private final int DO_BLUR_TEXTURE;
        private final int DO_DUAL_END;
        private final int DO_DUAL_FLIP;
        private final int DO_DUAL_MOVE;
        private final int DO_DUAL_START;
        private final int DO_DUAL_TOGGLE_SHAPE;
        private final int DO_REINIT_MESSAGE;
        private final int DO_RENDER_MESSAGE;
        private final int DO_SETSESSION_MESSAGE;
        private final int DO_SHUTDOWN_MESSAGE;
        private final int DO_START_RECORDING;
        private final int DO_STOP_RECORDING;
        private int alphaHandle;
        final int[] array;
        private int blurCameraMatrixHandle;
        private int blurHandle;
        private boolean blurInited;
        private int blurPixelHandle;
        private int blurPositionHandle;
        private SurfaceTexture blurSurfaceTexture;
        private int blurTextureHandle;
        private int blurTextureMatrixHandle;
        private int blurVertexMatrixHandle;
        private final AnimatedFloat camera1Appear;
        private boolean camera1Appeared;
        private long camera1AppearedUntil;
        private final int[] cameraId;
        private int cameraMatrixHandle;
        private final SurfaceTexture[] cameraSurface;
        private final AnimatedFloat crossfade;
        private int crossfadeHandle;
        private boolean crossfading;
        private final CameraSessionWrapper[] currentSession;
        private int drawBlurProgram;
        private int drawProgram;
        private final AnimatedFloat dualAppear;
        private boolean dualAppeared;
        private int dualHandle;
        private EGL10 egl10;
        private EGLContext eglBlurContext;
        private EGLSurface eglBlurSurface;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private boolean ignoreCamera1Upd;
        private boolean initDual;
        private Matrix initDualMatrix;
        private boolean initDualReverse;
        private boolean initied;
        private float[] m3x3;
        private boolean needRecord;
        private int oppositeCameraMatrixHandle;
        private long pausedTime;
        private int pixelHandle;
        private int positionHandle;
        private boolean recording;
        private int roundRadiusHandle;
        private int scaleHandle;
        private final AnimatedFloat shape;
        private int shapeFromHandle;
        private int shapeHandle;
        private float shapeTo;
        private int shapeToHandle;
        private SurfaceTexture surfaceTexture;
        private int textureHandle;
        private int textureMatrixHandle;
        final /* synthetic */ CameraView this$0;
        private final Object updateTex1;
        private final Object updateTex2;
        private final Object updateTexBoth;
        private int vertexMatrixHandle;
        private final float[] verticesData;

        /* renamed from: $r8$lambda$9dTKet-dqSQJll5gSTbXPPaIe5Q, reason: not valid java name */
        public static /* synthetic */ void m5124$r8$lambda$9dTKetdqSQJll5gSTbXPPaIe5Q(CameraGLThread r0) {
            r0.lambda$new$2();
        }

        /* renamed from: $r8$lambda$CSR2Ka_H1J-K6HbI2E_yACrqhE8, reason: not valid java name */
        public static /* synthetic */ void m5125$r8$lambda$CSR2Ka_H1JK6HbI2E_yACrqhE8(CameraGLThread r0) {
            r0.lambda$new$3();
        }

        public static /* synthetic */ void $r8$lambda$SwjKBgMmKm1_673l0_PQf94d7U8(CameraGLThread r0, SurfaceTexture r1) {
            r0.updTex(r1);
        }

        public static /* synthetic */ void $r8$lambda$ZBzVn9cT9o7RNWgYe_cV6CwTIH0(CameraGLThread r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$bVU_wiGJI08eTz9biQCSnWLFyic(CameraGLThread r0) {
            r0.lambda$onDraw$5();
        }

        /* renamed from: $r8$lambda$gmYL8-yOn33Use9vKR1qNpjo0zY, reason: not valid java name */
        public static /* synthetic */ void m5126$r8$lambda$gmYL8yOn33Use9vKR1qNpjo0zY(CameraGLThread r0) {
            r0.lambda$new$0();
        }

        /* renamed from: $r8$lambda$mAnjiEBmCDuyLFx-l1Km5V57Ylc, reason: not valid java name */
        public static /* synthetic */ void m5127$r8$lambda$mAnjiEBmCDuyLFxl1Km5V57Ylc(CameraGLThread r0) {
            r0.lambda$onDraw$4();
        }

        /* renamed from: -$$Nest$fgetcurrentSession, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CameraSessionWrapper[] m5128$$Nest$fgetcurrentSession(CameraGLThread r0) {
            return r0.currentSession;
        }

        public CameraGLThread(CameraView r13, SurfaceTexture r14) {
            this.this$0 = r13;
            super("CameraGLThread");
            this.currentSession = new CameraSessionWrapper[2];
            this.cameraSurface = new SurfaceTexture[2];
            this.DO_RENDER_MESSAGE = 0;
            this.DO_SHUTDOWN_MESSAGE = 1;
            this.DO_REINIT_MESSAGE = 2;
            this.DO_SETSESSION_MESSAGE = 3;
            this.DO_START_RECORDING = 4;
            this.DO_STOP_RECORDING = 5;
            this.DO_DUAL_START = 6;
            this.DO_DUAL_MOVE = 7;
            this.DO_DUAL_FLIP = 8;
            this.DO_DUAL_TOGGLE_SHAPE = 9;
            this.DO_DUAL_END = 10;
            this.BLUR_CAMERA1 = 11;
            this.DO_BLUR_TEXTURE = 12;
            this.cameraId = new int[]{-1, -1};
            this.verticesData = new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f};
            CameraView$CameraGLThread$$ExternalSyntheticLambda2 r3 = new CameraView$CameraGLThread$$ExternalSyntheticLambda2(this);
            CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.crossfade = new AnimatedFloat(r3, 560, r11);
            this.camera1Appear = new AnimatedFloat(1.0f, new CameraView$CameraGLThread$$ExternalSyntheticLambda3(this), 0, 420, r11);
            this.dualAppear = new AnimatedFloat(new CameraView$CameraGLThread$$ExternalSyntheticLambda4(this), 340, r11);
            this.shape = new AnimatedFloat(new CameraView$CameraGLThread$$ExternalSyntheticLambda5(this), 340, r11);
            this.shapeTo = MessagesController.getGlobalMainSettings().getInt("dualshape", 0);
            this.array = new int[1];
            this.updateTex1 = new Object();
            this.updateTex2 = new Object();
            this.updateTexBoth = new Object();
            this.surfaceTexture = r14;
            this.initDual = r13.dual;
            this.initDualReverse = !CameraView.m5085$$Nest$fgetisFrontface(r13);
            this.initDualMatrix = CameraView.m5083$$Nest$fgetdualMatrix(r13);
        }

        private void applyDualMatrix(Matrix r3) {
            getValues(r3, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[1]);
        }

        private void getValues(Matrix r7, float[] r8) {
            if (this.m3x3 != null) goto L5;
            this.m3x3 = new float[9];
        L5:
            r7.getValues(this.m3x3);
            float[] r6 = this.m3x3;
            r8[0] = r6[0];
            r8[1] = r6[3];
            r8[2] = 0.0f;
            r8[3] = r6[6];
            r8[4] = r6[1];
            r8[5] = r6[4];
            r8[6] = 0.0f;
            r8[7] = r6[7];
            r8[8] = 0.0f;
            r8[9] = 0.0f;
            r8[10] = 1.0f;
            r8[11] = 0.0f;
            r8[12] = r6[2];
            r8[13] = r6[5];
            r8[14] = 0.0f;
            r8[15] = r6[8];
        }

        private boolean initBlurGL() {
            if (this.initied == true) goto L5;
            return false;
        L5:
            EGLContext r0 = this.egl10.eglCreateContext(this.eglDisplay, this.eglConfig, this.eglContext, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            this.eglBlurContext = r0;
            if (r0 != null) goto L8;
        L39:
            this.eglBlurContext = null;
            if (BuildVars.LOGS_ENABLED == false) goto L42;
            FileLog.e("eglCreateContext (blur) failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L42:
            return false;
        L8:
            if (r0 == EGL10.EGL_NO_CONTEXT) goto L39;
            SurfaceTexture r02 = this.blurSurfaceTexture;
            if (r02 == null) goto L37;
            EGLSurface r03 = this.egl10.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, r02, null);
            this.eglBlurSurface = r03;
            if (r03 == null) goto L33;
            if (r03 == EGL10.EGL_NO_SURFACE) goto L33;
            if (this.egl10.eglMakeCurrent(this.eglDisplay, r03, r03, this.eglBlurContext) == false) goto L20;
            int r04 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35633, AndroidUtilities.readRes(R.raw.camera_blur_vert));
            int r2 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35632, AndroidUtilities.readRes(R.raw.camera_blur_frag));
            if (r04 == 0) goto L31;
            if (r2 == 0) goto L31;
            int r4 = GLES20.glCreateProgram();
            this.drawBlurProgram = r4;
            GLES20.glAttachShader(r4, r04);
            GLES20.glAttachShader(this.drawBlurProgram, r2);
            GLES20.glLinkProgram(this.drawBlurProgram);
            int[] r05 = new int[1];
            GLES20.glGetProgramiv(this.drawBlurProgram, 35714, r05, 0);
            int r06 = r05[0];
            int r22 = this.drawBlurProgram;
            if (r06 != 0) goto L30;
            GLES20.glDeleteProgram(r22);
            this.drawBlurProgram = 0;
            goto L31
        L30:
            this.blurPositionHandle = GLES20.glGetAttribLocation(r22, "aPosition");
            this.blurTextureHandle = GLES20.glGetAttribLocation(this.drawBlurProgram, "aTextureCoord");
            this.blurVertexMatrixHandle = GLES20.glGetUniformLocation(this.drawBlurProgram, "uMVPMatrix");
            this.blurTextureMatrixHandle = GLES20.glGetUniformLocation(this.drawBlurProgram, "uSTMatrix");
            this.blurCameraMatrixHandle = GLES20.glGetUniformLocation(this.drawBlurProgram, "cameraMatrix");
            this.blurPixelHandle = GLES20.glGetUniformLocation(this.drawBlurProgram, "pixelWH");
        L31:
            return true;
        L20:
            if (BuildVars.LOGS_ENABLED == false) goto L22;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L22:
            finishBlur();
            EGL10 r07 = this.egl10;
            EGLDisplay r23 = this.eglDisplay;
            EGLSurface r3 = this.eglSurface;
            r07.eglMakeCurrent(r23, r3, r3, this.eglContext);
            return false;
        L33:
            if (BuildVars.LOGS_ENABLED == false) goto L35;
            FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L35:
            finishBlur();
            return false;
        L37:
            finishBlur();
            return false;
        }

        private boolean initGL() {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("CameraView start init gl");
        L5:
            EGL10 r0 = (EGL10) EGLContext.getEGL();
            this.egl10 = r0;
            EGLDisplay r02 = r0.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.eglDisplay = r02;
            if (r02 != EGL10.EGL_NO_DISPLAY) goto L13;
            if (BuildVars.LOGS_ENABLED == false) goto L10;
            FileLog.e("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L10:
            this.eglDisplay = null;
            finish();
            return false;
        L13:
            if (this.egl10.eglInitialize(r02, new int[2]) == false) goto L15;
            int[] r9 = new int[1];
            EGLConfig[] r7 = new EGLConfig[1];
            if (this.egl10.eglChooseConfig(this.eglDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 0, 12325, 0, 12326, 0, 12344}, r7, 1, r9) == true) goto L27;
            if (BuildVars.LOGS_ENABLED == false) goto L24;
            FileLog.e("eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L24:
            finish();
            return false;
        L27:
            if (r9[0] <= 0) goto L93;
            EGLConfig r4 = r7[0];
            this.eglConfig = r4;
            int[] r5 = {EGL_CONTEXT_CLIENT_VERSION, 2, 12344};
            EGL10 r6 = this.egl10;
            EGLDisplay r72 = this.eglDisplay;
            EGLContext r8 = EGL10.EGL_NO_CONTEXT;
            EGLContext r42 = r6.eglCreateContext(r72, r4, r8, r5);
            this.eglContext = r42;
            if (r42 == null) goto L87;
            if (r42 == r8) goto L87;
            SurfaceTexture r43 = this.surfaceTexture;
            if (r43 == null) goto L85;
            EGLSurface r2 = this.egl10.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, r43, null);
            this.eglSurface = r2;
            if (r2 == null) goto L81;
            if (r2 == EGL10.EGL_NO_SURFACE) goto L81;
            if (this.egl10.eglMakeCurrent(this.eglDisplay, r2, r2, this.eglContext) == false) goto L42;
            android.opengl.Matrix.setIdentityM(CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[0], 0);
            int r22 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35633, AndroidUtilities.readRes(R.raw.camera_vert));
            int r44 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35632, AndroidUtilities.readRes(R.raw.camera_frag));
            if (r22 == 0) goto L76;
            if (r44 == 0) goto L76;
            int r52 = GLES20.glCreateProgram();
            this.drawProgram = r52;
            GLES20.glAttachShader(r52, r22);
            GLES20.glAttachShader(this.drawProgram, r44);
            GLES20.glLinkProgram(this.drawProgram);
            int[] r23 = new int[1];
            GLES20.glGetProgramiv(this.drawProgram, 35714, r23, 0);
            if (r23[0] == 0) goto L52;
            this.positionHandle = GLES20.glGetAttribLocation(this.drawProgram, "aPosition");
            this.textureHandle = GLES20.glGetAttribLocation(this.drawProgram, "aTextureCoord");
            this.vertexMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uMVPMatrix");
            this.textureMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uSTMatrix");
            this.cameraMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "cameraMatrix");
            this.oppositeCameraMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "oppositeCameraMatrix");
            this.roundRadiusHandle = GLES20.glGetUniformLocation(this.drawProgram, "roundRadius");
            this.pixelHandle = GLES20.glGetUniformLocation(this.drawProgram, "pixelWH");
            this.dualHandle = GLES20.glGetUniformLocation(this.drawProgram, "dual");
            this.scaleHandle = GLES20.glGetUniformLocation(this.drawProgram, "scale");
            this.blurHandle = GLES20.glGetUniformLocation(this.drawProgram, "blur");
            this.alphaHandle = GLES20.glGetUniformLocation(this.drawProgram, "alpha");
            this.crossfadeHandle = GLES20.glGetUniformLocation(this.drawProgram, "crossfade");
            this.shapeFromHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeFrom");
            this.shapeToHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeTo");
            this.shapeHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeT");
        L56:
            GLES20.glGenTextures(1, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0], 0);
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0][0]);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            GLES20.glEnable(3042);
            GLES20.glBlendFuncSeparate(770, 771, 1, 771);
            android.opengl.Matrix.setIdentityM(CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[0], 0);
            if (BuildVars.LOGS_ENABLED == false) goto L59;
            FileLog.e("gl initied");
        L59:
            updateScale(0);
            float r10 = (1.0f / CameraView.m5097$$Nest$fgetscaleX(this.this$0)) / 2.0f;
            float r11 = (1.0f / CameraView.m5098$$Nest$fgetscaleY(this.this$0)) / 2.0f;
            float r13 = 0.5f - r10;
            float r14 = 0.5f - r11;
            float r102 = r10 + 0.5f;
            float r112 = r11 + 0.5f;
            CameraView.m5116$$Nest$fputvertexBuffer(this.this$0, ByteBuffer.allocateDirect(this.verticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer());
            CameraView.m5104$$Nest$fgetvertexBuffer(this.this$0).put(this.verticesData).position(0);
            CameraView.m5114$$Nest$fputtextureBuffer(this.this$0, ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer());
            CameraView.m5102$$Nest$fgettextureBuffer(this.this$0).put(new float[]{r13, r14, r102, r14, r13, r112, r102, r112}).position(0);
            this.cameraSurface[0] = new SurfaceTexture(CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0][0]);
            this.cameraSurface[0].setOnFrameAvailableListener(new CameraView$CameraGLThread$$ExternalSyntheticLambda6(this));
            if (this.initDual == false) goto L63;
            GLES20.glGenTextures(1, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1], 0);
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1][0]);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            this.cameraSurface[1] = new SurfaceTexture(CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1][0]);
            this.cameraSurface[1].setOnFrameAvailableListener(new CameraView$CameraGLThread$$ExternalSyntheticLambda6(this));
        L63:
            if (this.initDual == false) goto L68;
            boolean r1 = this.initDualReverse;
            CameraView r24 = this.this$0;
            if (r1 == false) goto L67;
            CameraView.m5121$$Nest$mcreateCamera(r24, this.cameraSurface[1], 1);
            CameraView.m5121$$Nest$mcreateCamera(this.this$0, this.cameraSurface[0], 0);
        L69:
            Matrix r12 = new Matrix();
            r12.reset();
            getValues(r12, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[0]);
            Matrix r25 = this.initDualMatrix;
            CameraView r3 = this.this$0;
            if (r25 == null) goto L72;
            getValues(r25, CameraView.m5080$$Nest$fgetcameraMatrix(r3)[1]);
        L73:
            CameraView.m5110$$Nest$fputlastShapeTo(this.this$0, this.shapeTo);
            return true;
        L72:
            getValues(r12, CameraView.m5080$$Nest$fgetcameraMatrix(r3)[1]);
            goto L73
        L67:
            CameraView.m5121$$Nest$mcreateCamera(r24, this.cameraSurface[0], 0);
            CameraView.m5121$$Nest$mcreateCamera(this.this$0, this.cameraSurface[1], 1);
            goto L69
        L68:
            CameraView.m5121$$Nest$mcreateCamera(this.this$0, this.cameraSurface[0], 0);
            goto L69
        L52:
            if (BuildVars.LOGS_ENABLED == false) goto L54;
            FileLog.e("failed link shader");
        L54:
            GLES20.glDeleteProgram(this.drawProgram);
            this.drawProgram = 0;
        L76:
            if (BuildVars.LOGS_ENABLED == false) goto L78;
            FileLog.e("failed creating shader");
        L78:
            finish();
            return false;
        L42:
            if (BuildVars.LOGS_ENABLED == false) goto L44;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L44:
            finish();
            return false;
        L81:
            if (BuildVars.LOGS_ENABLED == false) goto L83;
            FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L83:
            finish();
            return false;
        L85:
            finish();
            return false;
        L87:
            this.eglContext = null;
            if (BuildVars.LOGS_ENABLED == false) goto L90;
            FileLog.e("eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L90:
            finish();
            return false;
        L93:
            if (BuildVars.LOGS_ENABLED == false) goto L95;
            FileLog.e("eglConfig not initialized");
        L95:
            finish();
            return false;
        L15:
            if (BuildVars.LOGS_ENABLED == false) goto L17;
            FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L17:
            finish();
            return false;
        }

        private /* synthetic */ void lambda$new$0() {
            requestRender(false, false);
        }

        private /* synthetic */ void lambda$new$1() {
            requestRender(false, false);
        }

        private /* synthetic */ void lambda$new$2() {
            requestRender(false, false);
        }

        private /* synthetic */ void lambda$new$3() {
            requestRender(false, false);
        }

        private /* synthetic */ void lambda$onDraw$4() {
            CameraView.m5123$$Nest$monFirstFrameRendered(this.this$0, 0);
        }

        private /* synthetic */ void lambda$onDraw$5() {
            CameraView.m5123$$Nest$monFirstFrameRendered(this.this$0, 1);
        }

        private void onDraw(int r28, int r29, boolean r30, boolean r31) {
            int r2 = r28;
            if (this.initied == true) goto L6;
            return;
        L6:
            if (this.eglContext.equals(this.egl10.eglGetCurrentContext()) == true) goto L8;
        L9:
            EGL10 r0 = this.egl10;
            EGLDisplay r4 = this.eglDisplay;
            EGLSurface r5 = this.eglSurface;
            if (r0.eglMakeCurrent(r4, r5, r5, this.eglContext) == false) goto L12;
        L15:
            Object r42 = CameraView.m5088$$Nest$fgetlayoutLock(this.this$0);
            monitor-enter(r42);
            boolean r52 = this.this$0.dual;     // Catch: Throwable -> L191
            boolean r6 = this.camera1Appeared;     // Catch: Throwable -> L191
            monitor-exit(r42);     // Catch: Throwable -> L191
            boolean r43 = true;
            if (r30 == true) goto L22;
            if (r31 == true) goto L22;
        L24:
            boolean r7 = r30;
            boolean r8 = r31;
        L25:
            boolean r9 = false;
            if (r7 == true) goto L194;
        L34:
            if (r8 == true) goto L196;
        L42:
            Object r10 = CameraView.m5088$$Nest$fgetlayoutLock(this.this$0);
            monitor-enter(r10);
        L188:
            th = move-exception;
            throw th;
        L45:
            if (CameraView.m5084$$Nest$fgetfpsLimit(this.this$0) <= 0) goto L51;
            long r11 = System.nanoTime();     // Catch: Throwable -> L188
            CameraView r02 = this.this$0;     // Catch: Throwable -> L188
            long r13 = r02.nextFrameTimeNs;     // Catch: Throwable -> L188
            if (r11 >= r13) goto L50;
            r43 = false;
            goto L51
        L50:
            r02.nextFrameTimeNs = r13 + (1000000000 / CameraView.m5084$$Nest$fgetfpsLimit(r02));     // Catch: Throwable -> L188
            CameraView r03 = this.this$0;     // Catch: Throwable -> L188
            r03.nextFrameTimeNs = Math.max(r03.nextFrameTimeNs, r11);     // Catch: Throwable -> L188
            r43 = true;
        L51:
            monitor-exit(r10);     // Catch: Throwable -> L188
            CameraSessionWrapper r04 = this.currentSession[0];
            if (r04 != null) goto L55;
            return;
        L55:
            if (r04.getCameraId() == r2) goto L58;
            return;
        L58:
            if (this.recording == true) goto L60;
        L64:
            if (r43 == false) goto L211;
            this.egl10.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, this.array);
            int[] r05 = this.array;
            int r3 = r05[0];
            this.egl10.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, r05);
            GLES20.glViewport(0, 0, r3, this.array[0]);
            if (r52 == false) goto L69;
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GLES20.glClear(16384);
        L69:
            CameraView.m5113$$Nest$fputshapeValue(this.this$0, this.shape.set(this.shapeTo));
            CameraView r44 = this.this$0;
            float r102 = this.crossfade.set(0.0f);
            CameraView.m5109$$Nest$fputlastCrossfadeValue(r44, r102);
            AnimatedFloat r45 = this.dualAppear;
            if (this.dualAppeared == false) goto L72;
            float r112 = 1.0f;
        L73:
            float r46 = r45.set(r112);
            float r113 = 1.0f - this.camera1Appear.set(this.camera1Appeared);
            if (r102 > 0.0f) goto L76;
            this.crossfading = false;
        L76:
            int r12 = -1;
            int r132 = -1;
            int r14 = -1;
        L78:
            if (r132 >= 2) goto L154;
            if (r132 == r12) goto L81;
        L83:
            if (r132 >= 0) goto L85;
            int r15 = 1;
        L87:
            if (this.cameraSurface[r15] == null) goto L100;
            if (r15 == 0) goto L94;
            CameraSessionWrapper r06 = this.currentSession[r15];
            if (r06 == null) goto L100;
            if (r06.isInitiated() == false) goto L100;
        L94:
            if (r15 != 0) goto L97;
            if (r2 >= 0) goto L97;
            if (r52 == false) goto L100;
        L97:
            boolean r20 = r9;
            if (r15 != 1) goto L101;
            if (r29 < 0) goto L100;
        L101:
            if (r15 != 0) goto L103;
            if (r7 == false) goto L103;
        L105:
            this.cameraSurface[r15].getTransformMatrix(CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[r15]);
        L106:
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r15][r20 ? 1 : 0]);
            if (r14 != r12) goto L109;
            r14 = CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r15][r20 ? 1 : 0];
        L109:
            GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, CameraView.m5104$$Nest$fgetvertexBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.positionHandle);
            GLES20.glVertexAttribPointer(this.textureHandle, 2, 5126, false, 8, CameraView.m5102$$Nest$fgettextureBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.textureHandle);
            GLES20.glUniformMatrix4fv(this.cameraMatrixHandle, 1, r20, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[r15], r20 ? 1 : 0);
            GLES20.glUniformMatrix4fv(this.oppositeCameraMatrixHandle, 1, r20, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[1 - r15], r20 ? 1 : 0);
            GLES20.glUniformMatrix4fv(this.textureMatrixHandle, 1, r20, CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[r15], r20 ? 1 : 0);
            GLES20.glUniformMatrix4fv(this.vertexMatrixHandle, 1, r20, CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[r15], r20 ? 1 : 0);
            Size r07 = CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[r15];
            if (r07 == null) goto L121;
            CameraSessionWrapper r32 = this.currentSession[r15];
            if (r32 == null) goto L121;
            int r33 = r32.getWorldAngle();
            if (r33 != 90) goto L116;
        L119:
            int r34 = r07.getWidth();
            int r08 = r07.getHeight();
        L120:
            GLES20.glUniform2f(this.pixelHandle, r34, r08);
        L125:
            int r09 = this.dualHandle;
            if (r15 != 0) goto L131;
            if (r52 == false) goto L129;
            float r35 = 1.0f;
        L130:
            GLES20.glUniform1f(r09, r35);
            float r36 = 1.0f;
        L132:
            int r010 = this.blurHandle;
            if (r15 != 0) goto L135;
            float r92 = r113;
        L136:
            GLES20.glUniform1f(r010, r92);
            int r011 = this.alphaHandle;
            if (r15 != 1) goto L147;
            GLES20.glUniform1f(r011, r36);
            if (r132 >= 0) goto L142;
            GLES20.glUniform1f(this.roundRadiusHandle, 0.0f);
            GLES20.glUniform1f(this.scaleHandle, r36);
            GLES20.glUniform1f(this.shapeFromHandle, 2.0f);
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            GLES20.glUniform1f(this.shapeHandle, 0.0f);
            GLES20.glUniform1f(this.crossfadeHandle, r36);
            int r012 = 4;
            int r22 = 5;
            boolean r93 = false;
        L152:
            GLES20.glDrawArrays(r22, 0, r012);
            GLES20.glDisableVertexAttribArray(this.positionHandle);
            GLES20.glDisableVertexAttribArray(this.textureHandle);
            GLES20.glBindTexture(36197, 0);
            GLES20.glUseProgram(0);
        L153:
            r132 = r132 + 1;
            r2 = r28;
            r9 = false;
            r12 = -1;
            goto L78
        L142:
            boolean r013 = this.crossfading;
            int r37 = this.roundRadiusHandle;
            if (r013 == true) goto L146;
            GLES20.glUniform1f(r37, AndroidUtilities.dp(16.0f));
            GLES20.glUniform1f(this.scaleHandle, r46);
            GLES20.glUniform1f(this.shapeFromHandle, (float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeToHandle, (float) Math.ceil(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeHandle, CameraView.m5099$$Nest$fgetshapeValue(this.this$0) - ((float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0))));
            r93 = false;
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
        L145:
            r012 = 4;
            r22 = 5;
            r36 = 1.0f;
            goto L152
        L146:
            GLES20.glUniform1f(r37, AndroidUtilities.dp(16.0f));
            GLES20.glUniform1f(this.scaleHandle, 1.0f - r102);
            GLES20.glUniform1f(this.shapeFromHandle, (float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeToHandle, (float) Math.ceil(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeHandle, CameraView.m5099$$Nest$fgetshapeValue(this.this$0) - ((float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0))));
            GLES20.glUniform1f(this.shapeHandle, r102);
            r93 = false;
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
            goto L145
        L147:
            GLES20.glUniform1f(r011, r36);
            boolean r014 = this.crossfading;
            int r23 = this.roundRadiusHandle;
            if (r014 == false) goto L151;
            GLES20.glUniform1f(r23, AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), r102));
            GLES20.glUniform1f(this.scaleHandle, r36);
            GLES20.glUniform1f(this.shapeFromHandle, this.shapeTo);
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            r93 = false;
            GLES20.glUniform1f(this.shapeHandle, Utilities.clamp(r36 - r102, r36, 0.0f));
            GLES20.glUniform1f(this.crossfadeHandle, r102);
        L150:
            r012 = 4;
            r22 = 5;
            goto L152
        L151:
            r93 = false;
            GLES20.glUniform1f(r23, 0.0f);
            GLES20.glUniform1f(this.scaleHandle, r36);
            GLES20.glUniform1f(this.shapeFromHandle, 2.0f);
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            GLES20.glUniform1f(this.shapeHandle, 0.0f);
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
            goto L150
        L135:
            r92 = 0.0f;
            goto L136
        L129:
            r35 = 0.0f;
            goto L130
        L131:
            r36 = 1.0f;
            GLES20.glUniform1f(r09, 1.0f);
            goto L132
        L116:
            if (r33 == 270) goto L119;
            r34 = r07.getHeight();
            r08 = r07.getWidth();
        L121:
            int r015 = this.pixelHandle;
            if (r15 != 0) goto L124;
            GLES20.glUniform2f(r015, CameraView.m5095$$Nest$fgetpixelW(this.this$0), CameraView.m5094$$Nest$fgetpixelH(this.this$0));
            goto L125
        L124:
            GLES20.glUniform2f(r015, CameraView.m5093$$Nest$fgetpixelDualW(this.this$0), CameraView.m5092$$Nest$fgetpixelDualH(this.this$0));
        L103:
            if (r15 != 1) goto L106;
            if (r8 == false) goto L106;
        L100:
            r36 = 1.0f;
            r93 = false;
            goto L153
        L85:
            r15 = r132;
            goto L87
        L81:
            if (this.crossfading == true) goto L83;
        L154:
            this.egl10.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            if (this.blurSurfaceTexture != null) goto L157;
        L170:
            Object r24 = CameraView.m5088$$Nest$fgetlayoutLock(this.this$0);
            monitor-enter(r24);
            CameraView r016 = this.this$0;     // Catch: Throwable -> L176
            if (r016.firstFrameRendered == true) goto L178;
            if (r6 == false) goto L178;
            r016.firstFrameRendered = true;     // Catch: Throwable -> L176
            AndroidUtilities.runOnUIThread(new CameraView$CameraGLThread$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L176
        L178:
            CameraView r017 = this.this$0;     // Catch: Throwable -> L176
            if (r017.firstFrame2Rendered == false) goto L181;
        L183:
            monitor-exit(r24);     // Catch: Throwable -> L176
            return;
        L181:
            if (this.dualAppeared == false) goto L183;
            r017.firstFrame2Rendered = true;     // Catch: Throwable -> L176
            AndroidUtilities.runOnUIThread(new CameraView$CameraGLThread$$ExternalSyntheticLambda1(this));     // Catch: Throwable -> L176
        L176:
            th = move-exception;
            throw th;
        L157:
            if (this.blurInited == false) goto L170;
            if (this.eglBlurContext.equals(this.egl10.eglGetCurrentContext()) == true) goto L161;
        L162:
            EGL10 r018 = this.egl10;
            EGLDisplay r25 = this.eglDisplay;
            EGLSurface r38 = this.eglBlurSurface;
            if (r018.eglMakeCurrent(r25, r38, r38, this.eglBlurContext) == false) goto L165;
        L168:
            if (this.cameraSurface[0] == null) goto L170;
            GLES20.glUseProgram(this.drawBlurProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0][0]);
            GLES20.glVertexAttribPointer(this.blurPositionHandle, 3, 5126, false, 12, CameraView.m5104$$Nest$fgetvertexBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.blurPositionHandle);
            GLES20.glVertexAttribPointer(this.blurTextureHandle, 2, 5126, false, 8, CameraView.m5102$$Nest$fgettextureBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.blurTextureHandle);
            GLES20.glUniformMatrix4fv(this.blurCameraMatrixHandle, 1, false, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[0], 0);
            GLES20.glUniformMatrix4fv(this.blurTextureMatrixHandle, 1, false, CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[0], 0);
            GLES20.glUniformMatrix4fv(this.blurVertexMatrixHandle, 1, false, CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[0], 0);
            GLES20.glUniform2f(this.blurPixelHandle, CameraView.m5095$$Nest$fgetpixelW(this.this$0), CameraView.m5094$$Nest$fgetpixelH(this.this$0));
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.blurPositionHandle);
            GLES20.glDisableVertexAttribArray(this.blurTextureHandle);
            this.egl10.eglSwapBuffers(this.eglDisplay, this.eglBlurSurface);
            goto L170
        L165:
            if (BuildVars.LOGS_ENABLED == false) goto L170;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            goto L170
        L161:
            if (this.eglBlurSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == true) goto L168;
        L72:
            r112 = 0.0f;
            goto L73
        L211:
            return;
        L60:
            if (CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0) == null) goto L64;
            if (r7 == true) goto L63;
            if (r8 == false) goto L64;
        L63:
            CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0).frameAvailable(this.cameraSurface[0], Integer.valueOf(r2), System.nanoTime());
            goto L64
        L196:
            SurfaceTexture r019 = this.cameraSurface[1];     // Catch: Throwable -> L40
            if (r019 == null) goto L42;
            if (r29 < 0) goto L42;
            r019.updateTexImage();     // Catch: Throwable -> L40
        L40:
            th = move-exception;
            FileLog.e(th);
            goto L42
        L194:
            SurfaceTexture r020 = this.cameraSurface[0];     // Catch: Throwable -> L32
            if (r020 == null) goto L34;
            if (r2 < 0) goto L34;
            r020.updateTexImage();     // Catch: Throwable -> L32
        L32:
            th = move-exception;
            FileLog.e(th);
        L22:
            if (r6 == false) goto L24;
            r7 = true;
            r8 = true;
        L191:
            th = move-exception;
            throw th;
        L12:
            if (BuildVars.LOGS_ENABLED == false) goto L187;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            return;
        L187:
            return;
        L8:
            if (this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == true) goto L15;
            goto L9
        }

        private void updTex(SurfaceTexture r9) {
            SurfaceTexture[] r0 = this.cameraSurface;
            if (r9 != r0[0]) goto L12;
            if (this.ignoreCamera1Upd == false) goto L7;
        L9:
            requestRender(true, false);
            return;
        L7:
            if (System.currentTimeMillis() <= this.camera1AppearedUntil) goto L9;
            this.camera1Appeared = true;
            goto L9
        L12:
            if (r9 == r0[1]) goto L14;
            return;
        L14:
            if (this.dualAppeared == true) goto L23;
            Object r92 = CameraView.m5088$$Nest$fgetlayoutLock(this.this$0);
            monitor-enter(r92);
            CameraView.m5108$$Nest$fputdualCameraAppeared(this.this$0, true);     // Catch: Throwable -> L20
            CameraView.m5120$$Nest$maddToDualWait(this.this$0, 1200);     // Catch: Throwable -> L20
            monitor-exit(r92);     // Catch: Throwable -> L20
        L20:
            th = move-exception;
            throw th;
        L23:
            this.dualAppeared = true;
            requestRender(false, true);
        }

        private void updateScale(int r4) {
            if (CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[r4] == null) goto L13;
            int r0 = CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[r4].getWidth();
            float r1 = CameraView.m5101$$Nest$fgetsurfaceWidth(this.this$0) / Math.min(r0, r4);
            int r02 = (int) (r0 * r1);
            int r42 = (int) (CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[r4].getHeight() * r1);
            if (r02 != r42) goto L7;
            CameraView.m5111$$Nest$fputscaleX(this.this$0, 1.0f);
            CameraView.m5112$$Nest$fputscaleY(this.this$0, 1.0f);
        L11:
            FileLog.d("CameraView camera scaleX = " + CameraView.m5097$$Nest$fgetscaleX(this.this$0) + " scaleY = " + CameraView.m5098$$Nest$fgetscaleY(this.this$0));
            return;
        L7:
            CameraView r2 = this.this$0;
            if (r02 <= r42) goto L10;
            CameraView.m5111$$Nest$fputscaleX(r2, r42 / CameraView.m5101$$Nest$fgetsurfaceWidth(r2));
            CameraView.m5112$$Nest$fputscaleY(this.this$0, 1.0f);
            goto L11
        L10:
            CameraView.m5111$$Nest$fputscaleX(r2, 1.0f);
            CameraView.m5112$$Nest$fputscaleY(this.this$0, r02 / CameraView.m5100$$Nest$fgetsurfaceHeight(r4));
            goto L11
        }

        public void finish() {
            if (this.cameraSurface == null) goto L11;
            int r0 = 0;
        L5:
            SurfaceTexture[] r2 = this.cameraSurface;
            if (r0 >= r2.length) goto L11;
            SurfaceTexture r22 = r2[r0];
            if (r22 == null) goto L10;
            r22.setOnFrameAvailableListener(null);
            this.cameraSurface[r0].release();
            this.cameraSurface[r0] = null;
        L10:
            r0 = r0 + 1;
        L11:
            finishBlur();
            if (this.eglSurface == null) goto L14;
            EGL10 r02 = this.egl10;
            EGLDisplay r23 = this.eglDisplay;
            EGLSurface r3 = EGL10.EGL_NO_SURFACE;
            r02.eglMakeCurrent(r23, r3, r3, EGL10.EGL_NO_CONTEXT);
            this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = null;
        L14:
            EGLContext r03 = this.eglContext;
            if (r03 == null) goto L17;
            this.egl10.eglDestroyContext(this.eglDisplay, r03);
            this.eglContext = null;
        L17:
            EGLDisplay r04 = this.eglDisplay;
            if (r04 == null) goto L24;
            this.egl10.eglTerminate(r04);
            this.eglDisplay = null;
            return;
        }

        public void finishBlur() {
            if (this.eglBlurSurface == null) goto L5;
            EGL10 r0 = this.egl10;
            EGLDisplay r2 = this.eglDisplay;
            EGLSurface r3 = EGL10.EGL_NO_SURFACE;
            r0.eglMakeCurrent(r2, r3, r3, EGL10.EGL_NO_CONTEXT);
            this.egl10.eglDestroySurface(this.eglDisplay, this.eglBlurSurface);
            this.eglBlurSurface = null;
        L5:
            EGLContext r02 = this.eglBlurContext;
            if (r02 == null) goto L8;
            this.egl10.eglDestroyContext(this.eglDisplay, r02);
            this.eglBlurContext = null;
        L8:
            this.blurInited = false;
        }

        @Override // org.telegram.messenger.DispatchQueue
        public void handleMessage(Message r13) {
            int r0 = r13.what;
            boolean r5 = true;
            switch(r0) {
                case 0: goto L101;
                case 1: goto L94;
                case 2: goto L61;
                case 3: goto L47;
                case 4: goto L43;
                case 5: goto L38;
                case 6: goto L61;
                case 7: goto L35;
                case 8: goto L33;
                case 9: goto L31;
                case 10: goto L17;
                case 11: goto L15;
                case 12: goto L6;
                default: goto L122;
            };
        L15:
            this.camera1Appeared = false;
            this.ignoreCamera1Upd = true;
            this.camera1AppearedUntil = System.currentTimeMillis() + 60;
            requestRender(false, false);
            return;
        L17:
            EGL10 r132 = this.egl10;
            EGLDisplay r02 = this.eglDisplay;
            EGLSurface r1 = this.eglSurface;
            if (r132.eglMakeCurrent(r02, r1, r1, this.eglContext) == false) goto L20;
            SurfaceTexture r133 = this.cameraSurface[1];
            if (r133 == null) goto L27;
            r133.getTransformMatrix(CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[1]);
            this.cameraSurface[1].setOnFrameAvailableListener(null);
            this.cameraSurface[1].release();
            this.cameraSurface[1] = null;
        L27:
            if (CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1][0] == 0) goto L29;
            GLES20.glDeleteTextures(1, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1], 0);
            CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1][0] = 0;
        L29:
            this.currentSession[1] = null;
            this.cameraId[1] = -1;
            requestRender(false, false);
            return;
        L20:
            if (BuildVars.LOGS_ENABLED == false) goto L117;
            FileLog.d("CameraView eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            return;
        L117:
            return;
        L31:
            float r134 = this.shapeTo + 1.0f;
            this.shapeTo = r134;
            CameraView.m5110$$Nest$fputlastShapeTo(this.this$0, r134);
            requestRender(false, false);
            return;
        L33:
            int[] r135 = this.cameraId;
            int r03 = r135[0];
            r135[0] = r135[1];
            r135[1] = r03;
            CameraSessionWrapper[] r136 = this.currentSession;
            CameraSessionWrapper r04 = r136[0];
            r136[0] = r136[1];
            r136[1] = r04;
            int[] r137 = CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0];
            CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[0] = CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1];
            CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1] = r137;
            SurfaceTexture[] r138 = this.cameraSurface;
            SurfaceTexture r05 = r138[0];
            r138[0] = r138[1];
            r138[1] = r05;
            float[] r139 = CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[0];
            CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[0] = CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[1];
            CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[1] = r139;
            float[] r1310 = CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[0];
            CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[0] = CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[1];
            CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[1] = r1310;
            float[] r1311 = CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[0];
            CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[0] = CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[1];
            CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[1] = r1311;
            this.crossfading = true;
            CameraView.m5109$$Nest$fputlastCrossfadeValue(this.this$0, 1.0f);
            this.crossfade.set(1.0f, true);
            requestRender(true, true);
            return;
        L35:
            applyDualMatrix((Matrix) r13.obj);
            requestRender(false, false);
            return;
        L47:
            int r06 = r13.arg1;
            CameraSessionWrapper r1312 = (CameraSessionWrapper) r13.obj;
            if (r1312 == null) goto L124;
            CameraSessionWrapper[] r12 = this.currentSession;
            if (r12[r06] == r1312) goto L53;
            r12[r06] = r1312;
            this.cameraId[r06] = r1312.getCameraId();
        L53:
            int r1313 = this.currentSession[r06].getWorldAngle();
            if (BuildVars.LOGS_ENABLED == false) goto L56;
            FileLog.d("CameraView set gl renderer session " + r06 + " angle=" + r1313);
        L56:
            android.opengl.Matrix.setIdentityM(CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[r06], 0);
            if (r1313 == 0) goto L118;
            android.opengl.Matrix.rotateM(CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[r06], 0, r1313, 0.0f, 0.0f, 1.0f);
            return;
        L118:
            return;
        L124:
            return;
        L94:
            finishBlur();
            finish();
            if (this.recording == false) goto L97;
            CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0).stopRecording(r13.arg1);
        L97:
            Looper r122 = Looper.myLooper();
            if (r122 == null) goto L121;
            r122.quit();
            return;
        L121:
            return;
        L101:
            int r07 = r13.arg1;
            int r14 = r13.arg2;
            Object r1314 = r13.obj;
            Object r2 = this.updateTexBoth;
            if (r1314 != r2) goto L104;
        L107:
            boolean r3 = true;
        L108:
            if (r1314 != r2) goto L110;
        L113:
            onDraw(r07, r14, r3, r5);
            return;
        L110:
            if (r1314 == this.updateTex2) goto L113;
            r5 = false;
            goto L113
        L104:
            if (r1314 == this.updateTex1) goto L107;
            r3 = false;
            goto L108
        L122:
            return;
        L6:
            if (this.blurSurfaceTexture == r13.obj) goto L8;
            finishBlur();
            this.blurSurfaceTexture = null;
        L8:
            Object r1315 = r13.obj;
            if (r1315 != null) goto L11;
        L13:
            requestRender(false, false);
            return;
        L11:
            if (this.blurSurfaceTexture == r1315) goto L13;
            this.blurSurfaceTexture = (SurfaceTexture) r1315;
            this.blurInited = initBlurGL();
            goto L13
        L38:
            if (CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0) == null) goto L40;
            CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0).stopRecording(0);
            CameraView.m5117$$Nest$fputvideoEncoder(this.this$0, null);
        L40:
            this.recording = false;
            return;
        L43:
            if (this.initied == false) goto L123;
            CameraView r08 = this.this$0;
            r08.recordFile = (File) r13.obj;
            CameraView.m5117$$Nest$fputvideoEncoder(r08, new VideoRecorder(r08, null));
            this.recording = true;
            CameraView.m5105$$Nest$fgetvideoEncoder(this.this$0).startRecording(this.this$0.recordFile, EGL14.eglGetCurrentContext());
            return;
        L123:
            return;
        L61:
            if (r0 != 2) goto L63;
            int r09 = 0;
        L64:
            EGL10 r8 = this.egl10;
            EGLDisplay r9 = this.eglDisplay;
            EGLSurface r10 = this.eglSurface;
            if (r8.eglMakeCurrent(r9, r10, r10, this.eglContext) == false) goto L67;
            SurfaceTexture r82 = this.cameraSurface[r09];
            if (r82 == null) goto L74;
            r82.getTransformMatrix(CameraView.m5091$$Nest$fgetmoldSTMatrix(this.this$0)[r09]);
            this.cameraSurface[r09].setOnFrameAvailableListener(null);
            this.cameraSurface[r09].release();
            this.cameraSurface[r09] = null;
        L74:
            if (CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r09][0] != 0) goto L76;
            GLES20.glGenTextures(1, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r09], 0);
        L76:
            this.cameraId[r09] = r13.arg1;
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r09][0]);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            if (r09 != 1) goto L79;
            applyDualMatrix((Matrix) r13.obj);
        L79:
            this.cameraSurface[r09] = new SurfaceTexture(CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r09][0]);
            this.cameraSurface[r09].setOnFrameAvailableListener(new CameraView$CameraGLThread$$ExternalSyntheticLambda6(this));
            if (this.ignoreCamera1Upd == false) goto L82;
            this.camera1Appeared = false;
            this.camera1AppearedUntil = System.currentTimeMillis() + 60;
            this.ignoreCamera1Upd = false;
        L82:
            CameraView.m5121$$Nest$mcreateCamera(this.this$0, this.cameraSurface[r09], r09);
            updateScale(r09);
            float r1316 = (1.0f / CameraView.m5097$$Nest$fgetscaleX(this.this$0)) / 2.0f;
            float r32 = (1.0f / CameraView.m5098$$Nest$fgetscaleY(this.this$0)) / 2.0f;
            float r22 = 0.5f - r1316;
            float r4 = 0.5f - r32;
            float r1317 = r1316 + 0.5f;
            float r33 = r32 + 0.5f;
            CameraView.m5114$$Nest$fputtextureBuffer(this.this$0, ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer());
            CameraView.m5102$$Nest$fgettextureBuffer(this.this$0).put(new float[]{r22, r4, r1317, r4, r22, r33, r1317, r33}).position(0);
            if (r09 != 1) goto L120;
            this.dualAppeared = false;
            Object r1318 = CameraView.m5088$$Nest$fgetlayoutLock(this.this$0);
            monitor-enter(r1318);
            CameraView.m5108$$Nest$fputdualCameraAppeared(this.this$0, false);     // Catch: Throwable -> L90
            this.this$0.firstFrame2Rendered = false;     // Catch: Throwable -> L90
            monitor-exit(r1318);     // Catch: Throwable -> L90
            this.dualAppear.set(0.0f, true);
            return;
        L90:
            th = move-exception;
            throw th;
        L120:
            return;
        L67:
            if (BuildVars.LOGS_ENABLED == false) goto L119;
            FileLog.d("CameraView eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            return;
        L119:
            return;
        L63:
            r09 = 1;
            goto L64
        }

        public void pause(long r3) {
            this.pausedTime = System.currentTimeMillis() + r3;
        }

        public void reinitForNewCamera() {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(2, Integer.valueOf(this.this$0.info[0].cameraId)), 0);
            return;
        }

        public void requestRender(boolean r6, boolean r7) {
            if (this.pausedTime > 0) goto L5;
        L7:
            if (r6 == true) goto L12;
            if (r7 == true) goto L12;
            if (this.recording == false) goto L12;
            return;
        L12:
            Handler r0 = getHandler();
            if (r0 != null) goto L15;
            return;
        L15:
            if (r6 == true) goto L18;
            if (r7 == true) goto L18;
        L21:
            if (r6 == false) goto L23;
        L25:
            if (r7 == false) goto L27;
        L29:
            r0.removeMessages(0);
            int[] r3 = this.cameraId;
            int r4 = r3[0];
            int r2 = r3[1];
            if (r6 == false) goto L33;
            if (r7 == false) goto L33;
            Object r62 = this.updateTexBoth;
        L36:
            sendMessage(r0.obtainMessage(0, r4, r2, r62), 0);
            return;
        L33:
            if (r6 == false) goto L35;
            r62 = this.updateTex1;
            goto L36
        L35:
            r62 = this.updateTex2;
            goto L36
        L27:
            if (r0.hasMessages(0, this.updateTex2) == false) goto L29;
            r7 = true;
            goto L29
        L23:
            if (r0.hasMessages(0, this.updateTex1) == false) goto L25;
            r6 = true;
        L18:
            if (r0.hasMessages(0, this.updateTexBoth) == false) goto L21;
            return;
        L5:
            if (System.currentTimeMillis() >= this.pausedTime) goto L7;
        }

        @Override // org.telegram.messenger.DispatchQueue, java.lang.Thread, java.lang.Runnable
        public void run() {
            this.initied = initGL();
            if (this.blurSurfaceTexture == null) goto L5;
            this.blurInited = initBlurGL();
        L5:
            super.run();
        }

        public void setBlurSurfaceTexture(SurfaceTexture r3) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(12, r3), 0);
            return;
        L6:
            this.blurSurfaceTexture = r3;
        }

        public void setCurrentSession(CameraSessionWrapper r4, int r5) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(3, r5, 0, r4), 0);
            return;
        }

        public void shutdown(int r4) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(1, r4, 0), 0);
            return;
        }

        public boolean startRecording(File r3) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(4, r3), 0);
            return false;
        L6:
            return true;
        }

        public void stopRecording() {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(5), 0);
            return;
        }
    }

    public interface CameraViewDelegate {
        void onCameraInit();
    }

    public static class EncoderHandler extends Handler {
        private WeakReference<VideoRecorder> mWeakEncoder;

        public EncoderHandler(VideoRecorder r2) {
            this.mWeakEncoder = new WeakReference(r2);
        }

        public void exit() {
            Looper.myLooper().quit();
        }

        @Override // android.os.Handler
        public void handleMessage(Message r7) {
            int r0 = r7.what;
            VideoRecorder r6 = this.mWeakEncoder.get();
            if (r6 == null) goto L33;
            if (r0 != 0) goto L7;
        L25:
            e = move-exception;
            FileLog.e(e);
            VideoRecorder.m5136$$Nest$mhandleStopRecording(r6, 0);
            Looper.myLooper().quit();
            return;
        L23:
            if (BuildVars.LOGS_ENABLED == false) goto L27;
            FileLog.e("start encoder");     // Catch: Exception -> L25
        L27:
            VideoRecorder.m5138$$Nest$mprepareEncoder(r6);     // Catch: Exception -> L25
            return;
        L7:
            if (r0 == 1) goto L18;
            if (r0 != 2) goto L11;
            VideoRecorder.m5137$$Nest$mhandleVideoFrameAvailable(r6, (r7.arg1 << 32) | (r7.arg2 & 4294967295L), (Integer) r7.obj);
            return;
        L11:
            if (r0 == 3) goto L13;
            return;
        L13:
            VideoRecorder.m5135$$Nest$mhandleAudioFrameAvailable(r6, (InstantCameraView.AudioBufferInfo) r7.obj);
            return;
        L18:
            if (BuildVars.LOGS_ENABLED == false) goto L20;
            FileLog.e("stop encoder");
        L20:
            VideoRecorder.m5136$$Nest$mhandleStopRecording(r6, r7.arg1);
            return;
        }
    }

    public class VideoRecorder implements Runnable {
        private static final String AUDIO_MIME_TYPE = "audio/mp4a-latm";
        private static final int FRAME_RATE = 30;
        private static final int IFRAME_INTERVAL = 1;
        private static final String VIDEO_MIME_TYPE = "video/hevc";
        private int alphaHandle;
        private MediaCodec.BufferInfo audioBufferInfo;
        private MediaCodec audioEncoder;
        private long audioFirst;
        private AudioRecord audioRecorder;
        private long audioStartTime;
        private boolean audioStopedByTime;
        private int audioTrackIndex;
        private boolean blendEnabled;
        private int blurHandle;
        private ArrayBlockingQueue<InstantCameraView.AudioBufferInfo> buffers;
        private ArrayList<InstantCameraView.AudioBufferInfo> buffersToWrite;
        private int cameraMatrixHandle;
        private int crossfadeHandle;
        private long currentTimestamp;
        private long desyncTime;
        private int drawProgram;
        private int dualHandle;
        private android.opengl.EGLConfig eglConfig;
        private android.opengl.EGLContext eglContext;
        private android.opengl.EGLDisplay eglDisplay;
        private android.opengl.EGLSurface eglSurface;
        private File fileToWrite;
        DispatchQueue fileWriteQueue;
        private boolean firstEncode;
        private volatile EncoderHandler handler;
        private ArrayList<Bitmap> keyframeThumbs;
        private Integer lastCameraId;
        private long lastCommitedFrameTime;
        private long lastTimestamp;
        private MP4Builder mediaMuxer;
        private int oppositeCameraMatrixHandle;
        private String outputMimeType;
        private int pixelHandle;
        private int positionHandle;
        private int prependHeaderSize;
        private boolean ready;
        private Runnable recorderRunnable;
        private int roundRadiusHandle;
        private volatile boolean running;
        private int scaleHandle;
        private volatile int sendWhenDone;
        private int shapeFromHandle;
        private int shapeHandle;
        private int shapeToHandle;
        private android.opengl.EGLContext sharedEglContext;
        private boolean skippedFirst;
        private long skippedTime;
        private Surface surface;
        private final Object sync;
        private FloatBuffer textureBuffer;
        private int textureHandle;
        private int textureMatrixHandle;
        final /* synthetic */ CameraView this$0;
        private int vertexMatrixHandle;
        private int videoBitrate;
        private MediaCodec.BufferInfo videoBufferInfo;
        private boolean videoConvertFirstWrite;
        private MediaCodec videoEncoder;
        private File videoFile;
        private long videoFirst;
        private long videoLast;
        private int videoTrackIndex;
        private boolean writingToDifferentFile;
        private int zeroTimeStamps;

        /* renamed from: org.telegram.messenger.camera.CameraView$VideoRecorder$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ VideoRecorder this$1;

            public static /* synthetic */ void $r8$lambda$ZC4T3pgyLG0rggLfZTKExb2mNXs(AnonymousClass1 r0, double r1) {
                r0.lambda$run$0(r1);
            }

            public AnonymousClass1(VideoRecorder r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0(double r1) {
                this.this$1.this$0.receivedAmplitude(r1);
            }

            @Override // java.lang.Runnable
            public void run() {
                long r2 = -1;
                long r5 = -1;
                boolean r0 = false;
            L3:
                boolean r7 = true;
                if (r0 == false) goto L6;
            L65:
                VideoRecorder.m5130$$Nest$fgetaudioRecorder(this.this$1).release();     // Catch: Exception -> L58
            L60:
                VideoRecorder.m5132$$Nest$fgethandler(this.this$1).sendMessage(VideoRecorder.m5132$$Nest$fgethandler(this.this$1).obtainMessage(1, VideoRecorder.m5134$$Nest$fgetsendWhenDone(this.this$1), 0));
                return;
            L58:
                e = move-exception;
                FileLog.e(e);
                goto L60
            L6:
                if (VideoRecorder.m5133$$Nest$fgetrunning(this.this$1) == true) goto L16;
                if (VideoRecorder.m5130$$Nest$fgetaudioRecorder(this.this$1).getRecordingState() == 1) goto L16;
                VideoRecorder.m5130$$Nest$fgetaudioRecorder(this.this$1).stop();     // Catch: Exception -> L11
            L13:
                if (VideoRecorder.m5134$$Nest$fgetsendWhenDone(this.this$1) != 0) goto L16;
            L11:
                r0 = true;
            L16:
                if (VideoRecorder.m5131$$Nest$fgetbuffers(this.this$1).isEmpty() == false) goto L18;
                InstantCameraView.AudioBufferInfo r8 = new InstantCameraView.AudioBufferInfo();
            L19:
                r8.lastWroteBuffer = 0;
                r8.results = 10;
                int r10 = 0;
            L20:
                if (r10 >= 10) goto L39;
                if (r5 != r2) goto L24;
                r5 = System.nanoTime() / 1000;
            L24:
                ByteBuffer r11 = r8.buffer[r10];
                r11.rewind();
                int r12 = VideoRecorder.m5130$$Nest$fgetaudioRecorder(this.this$1).read(r11, 2048);
                if (r12 > 0) goto L27;
            L33:
                if (r12 <= 0) goto L34;
                r8.offset[r10] = r5;
                r8.read[r10] = r12;
                r5 = r5 + (((r12 * DurationKt.NANOS_IN_MILLIS) / CameraView.audioSampleRate) / 2);
                r10 = r10 + 1;
                r2 = -1;
                goto L20
            L34:
                r8.results = r10;
                if (VideoRecorder.m5133$$Nest$fgetrunning(this.this$1) == true) goto L39;
                r8.last = true;
                goto L39
            L27:
                if ((r10 % 2) != 0) goto L33;
                r11.limit(r12);
                double r13 = 0.0d;
                int r15 = 0;
            L30:
                if (r15 >= (r12 / 2)) goto L32;
                short r22 = r11.getShort();
                r13 = r13 + (r22 * r22);
                r15 = r15 + 1;
                goto L30
            L32:
                AndroidUtilities.runOnUIThread(new CameraView$VideoRecorder$1$$ExternalSyntheticLambda0(this, Math.sqrt((r13 / r12) / 2.0d)));
                r11.position(0);
            L39:
                if (r8.results >= 0) goto L49;
                if (r8.last == true) goto L49;
                if (VideoRecorder.m5133$$Nest$fgetrunning(this.this$1) == true) goto L63;
            L45:
                r0 = r7;
            L55:
                r2 = -1;
                goto L3
            L63:
                VideoRecorder.m5131$$Nest$fgetbuffers(this.this$1).put(r8);     // Catch: Exception -> L62
            L49:
                if (VideoRecorder.m5133$$Nest$fgetrunning(this.this$1) == false) goto L51;
            L53:
                r7 = r0;
            L54:
                VideoRecorder.m5132$$Nest$fgethandler(this.this$1).sendMessage(VideoRecorder.m5132$$Nest$fgethandler(this.this$1).obtainMessage(3, r8));
                goto L45
            L51:
                if (r8.results >= 10) goto L53;
            L18:
                r8 = (InstantCameraView.AudioBufferInfo) VideoRecorder.m5131$$Nest$fgetbuffers(this.this$1).poll();
                goto L19
            }
        }

        public static /* synthetic */ void $r8$lambda$HOWOAGjFZqPOuX3H8VY0n2oHNiY(VideoRecorder r0) {
            r0.lambda$handleStopRecording$1();
        }

        public static /* synthetic */ void $r8$lambda$QZNHJK5UPSRb3o1Nc6MnaBR4YME(VideoRecorder r0, ByteBuffer r1, MediaCodec.BufferInfo r2) {
            r0.lambda$drainEncoder$2(r1, r2);
        }

        /* renamed from: $r8$lambda$jrZYQ7Xx-d3N9y1y9UZkg8ZElrM, reason: not valid java name */
        public static /* synthetic */ void m5129$r8$lambda$jrZYQ7Xxd3N9y1y9UZkg8ZElrM(VideoRecorder r0, ByteBuffer r1, MediaCodec.BufferInfo r2) {
            r0.lambda$drainEncoder$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$qW8FVxIanQD28yfq5GVor7XZNuw(VideoRecorder r0, CountDownLatch r1) {
            r0.lambda$handleStopRecording$0(r1);
        }

        /* renamed from: -$$Nest$fgetaudioRecorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AudioRecord m5130$$Nest$fgetaudioRecorder(VideoRecorder r0) {
            return r0.audioRecorder;
        }

        /* renamed from: -$$Nest$fgetbuffers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayBlockingQueue m5131$$Nest$fgetbuffers(VideoRecorder r0) {
            return r0.buffers;
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EncoderHandler m5132$$Nest$fgethandler(VideoRecorder r0) {
            return r0.handler;
        }

        /* renamed from: -$$Nest$fgetrunning, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5133$$Nest$fgetrunning(VideoRecorder r0) {
            return r0.running;
        }

        /* renamed from: -$$Nest$fgetsendWhenDone, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5134$$Nest$fgetsendWhenDone(VideoRecorder r0) {
            return r0.sendWhenDone;
        }

        /* renamed from: -$$Nest$mhandleAudioFrameAvailable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5135$$Nest$mhandleAudioFrameAvailable(VideoRecorder r0, InstantCameraView.AudioBufferInfo r1) {
            r0.handleAudioFrameAvailable(r1);
        }

        /* renamed from: -$$Nest$mhandleStopRecording, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5136$$Nest$mhandleStopRecording(VideoRecorder r0, int r1) {
            r0.handleStopRecording(r1);
        }

        /* renamed from: -$$Nest$mhandleVideoFrameAvailable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5137$$Nest$mhandleVideoFrameAvailable(VideoRecorder r0, long r1, Integer r3) {
            r0.handleVideoFrameAvailable(r1, r3);
        }

        /* renamed from: -$$Nest$mprepareEncoder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5138$$Nest$mprepareEncoder(VideoRecorder r0) {
            r0.prepareEncoder();
        }

        private VideoRecorder(CameraView r5) {
            this.this$0 = r5;
            this.videoConvertFirstWrite = true;
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;
            this.eglContext = EGL14.EGL_NO_CONTEXT;
            this.eglSurface = EGL14.EGL_NO_SURFACE;
            this.buffersToWrite = new ArrayList();
            this.videoTrackIndex = -5;
            this.audioTrackIndex = -5;
            this.audioStartTime = -1;
            this.currentTimestamp = 0;
            this.lastTimestamp = -1;
            this.sync = new Object();
            this.videoFirst = -1;
            this.audioFirst = -1;
            this.lastCameraId = 0;
            this.buffers = new ArrayBlockingQueue(10);
            this.keyframeThumbs = new ArrayList();
            this.recorderRunnable = new AnonymousClass1(this);
        }

        public /* synthetic */ VideoRecorder(CameraView r1, CameraViewIA r2) {
            this(r1);
        }

        private void handleAudioFrameAvailable(InstantCameraView.AudioBufferInfo r21) {
            if (this.audioStopedByTime == true) goto L115;
            InstantCameraView.AudioBufferInfo r2 = r21;
            this.buffersToWrite.add(r2);
            if (this.audioFirst != (-1)) goto L38;
            if (this.videoFirst == (-1)) goto L10;
        L13:
            int r0 = 0;
        L15:
            if (r0 >= r2.results) goto L32;
            if (r0 == 0) goto L18;
        L22:
            long r7 = r2.offset[r0];
            if (r7 >= this.videoFirst) goto L24;
            if (BuildVars.LOGS_ENABLED == false) goto L30;
            FileLog.d("CameraView ignore first audio frame at " + r0 + " timestamp = " + r2.offset[r0]);
        L30:
            r0 = r0 + 1;
            goto L15
        L24:
            r2.lastWroteBuffer = r0;
            this.audioFirst = r7;
            if (BuildVars.LOGS_ENABLED == false) goto L38;
            FileLog.d("CameraView found first audio frame at " + r0 + " timestamp = " + r2.offset[r0]);
            goto L38
        L18:
            if (Math.abs(this.videoFirst - r2.offset[r0]) <= 10000000) goto L22;
            long r72 = this.videoFirst;
            long r9 = r2.offset[r0];
            this.desyncTime = r72 - r9;
            this.audioFirst = r9;
            if (BuildVars.LOGS_ENABLED == false) goto L38;
            FileLog.d("CameraView detected desync between audio and video " + this.desyncTime);
            goto L38
        L32:
            if (BuildVars.LOGS_ENABLED == false) goto L34;
            FileLog.d("CameraView first audio frame not found, removing buffers " + r2.results);
        L34:
            this.buffersToWrite.remove(r2);
            if (this.buffersToWrite.isEmpty() == true) goto L116;
            r2 = this.buffersToWrite.get(0);
            goto L13
        L116:
            return;
        L10:
            if (BuildVars.LOGS_ENABLED == false) goto L114;
            FileLog.d("CameraView video record not yet started");
            return;
        L114:
            return;
        L38:
            if (this.audioStartTime != (-1)) goto L41;
            this.audioStartTime = r2.offset[r2.lastWroteBuffer];
        L41:
            if (this.buffersToWrite.size() <= 1) goto L93;
            r2 = this.buffersToWrite.get(0);
        L93:
            drainEncoder(false);     // Catch: Exception -> L45
        L47:
            boolean r02 = false;
        L48:
            if (r2 == null) goto L117;
            long r6 = 0;
            int r92 = this.audioEncoder.dequeueInputBuffer(0);     // Catch: Throwable -> L90
            if (r92 < 0) goto L48;
            ByteBuffer r5 = this.audioEncoder.getInputBuffer(r92);     // Catch: Throwable -> L90
            long[] r8 = r2.offset;     // Catch: Throwable -> L90
            int r10 = r2.lastWroteBuffer;     // Catch: Throwable -> L90
            long r11 = r8[r10];     // Catch: Throwable -> L90
        L52:
            int r82 = r2.results;     // Catch: Throwable -> L90
            if (r10 > r82) goto L80;
            if (r10 < r82) goto L56;
            long r16 = r6;
        L71:
            if (r10 < (r2.results - 1)) goto L79;
            this.buffersToWrite.remove(r2);     // Catch: Throwable -> L90
            if (this.running == false) goto L76;
            this.buffers.put(r2);     // Catch: Throwable -> L90
        L76:
            if (this.buffersToWrite.isEmpty() == true) goto L78;
            r2 = this.buffersToWrite.get(0);     // Catch: Throwable -> L90
            goto L79
        L78:
            r02 = r2.last;     // Catch: Throwable -> L90
        L63:
            r2 = null;
        L81:
            MediaCodec r83 = this.audioEncoder;     // Catch: Throwable -> L90
            int r52 = r5.position();     // Catch: Throwable -> L90
            if (r11 != r16) goto L84;
            long r12 = r16;
        L85:
            if (r02 == false) goto L87;
            int r14 = 4;
        L88:
            r83.queueInputBuffer(r92, 0, r52, r12, r14);     // Catch: Throwable -> L90
            goto L48
        L87:
            r14 = 0;
            goto L88
        L84:
            r12 = r11 - this.audioStartTime;     // Catch: Throwable -> L90
        L79:
            r10 = r10 + 1;     // Catch: Throwable -> L90
            r6 = r16;
            goto L52
        L56:
            if (this.running == true) goto L64;
            r16 = r6;
            if (r2.offset[r10] >= (this.videoLast - this.desyncTime)) goto L60;
        L66:
            if (r5.remaining() < r2.read[r10]) goto L67;
            r5.put(r2.buffer[r10]);     // Catch: Throwable -> L90
            goto L71
        L67:
            r2.lastWroteBuffer = r10;     // Catch: Throwable -> L90
            goto L63
        L60:
            if (BuildVars.LOGS_ENABLED == false) goto L62;
            FileLog.d("CameraView stop audio encoding because of stoped video recording at " + r2.offset[r10] + " last video " + this.videoLast);     // Catch: Throwable -> L90
        L62:
            this.audioStopedByTime = true;     // Catch: Throwable -> L90
            this.buffersToWrite.clear();     // Catch: Throwable -> L90
            r02 = true;
            goto L63
        L64:
            r16 = r6;
            goto L66
        L80:
            r16 = r6;
        L90:
            th = move-exception;
            FileLog.e(th);
            return;
        L117:
            return;
        L45:
            e = move-exception;
            FileLog.e(e);
            goto L47
        }

        private void handleStopRecording(int r4) {
            if (this.running == false) goto L45;
            this.sendWhenDone = r4;
            this.running = false;
            return;
        L45:
            drainEncoder(true);     // Catch: Exception -> L9
        L11:
            MediaCodec r0 = this.videoEncoder;
            if (r0 != null) goto L53;
        L17:
            MediaCodec r02 = this.audioEncoder;
            if (r02 != null) goto L51;
        L23:
            CountDownLatch r03 = new CountDownLatch(1);
            this.fileWriteQueue.postRunnable(new CameraView$VideoRecorder$$ExternalSyntheticLambda2(this, r03));
            r03.await();     // Catch: InterruptedException -> L26
        L29:
            if (this.writingToDifferentFile == true) goto L31;
        L37:
            EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
            Surface r42 = this.surface;
            if (r42 == null) goto L40;
            r42.release();
            this.surface = null;
        L40:
            android.opengl.EGLDisplay r43 = this.eglDisplay;
            if (r43 == EGL14.EGL_NO_DISPLAY) goto L43;
            android.opengl.EGLSurface r04 = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(r43, r04, r04, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay);
        L43:
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;
            this.eglContext = EGL14.EGL_NO_CONTEXT;
            this.eglConfig = null;
            this.handler.exit();
            AndroidUtilities.runOnUIThread(new CameraView$VideoRecorder$$ExternalSyntheticLambda3(this));
            return;
        L31:
            if (this.fileToWrite.renameTo(this.videoFile) == true) goto L37;
            FileLog.e("unable to rename file, try move file");
            AndroidUtilities.copyFile(this.fileToWrite, this.videoFile);     // Catch: IOException -> L35
            this.fileToWrite.delete();     // Catch: IOException -> L35
        L35:
            e = move-exception;
            FileLog.e(e);
            FileLog.e("unable to move file");
        L26:
            e = move-exception;
            e.printStackTrace();
            goto L29
        L51:
            r02.stop();     // Catch: Exception -> L21
            this.audioEncoder.release();     // Catch: Exception -> L21
            this.audioEncoder = null;     // Catch: Exception -> L21
        L21:
            e = move-exception;
            FileLog.e(e);
            goto L23
        L53:
            r0.stop();     // Catch: Exception -> L15
            this.videoEncoder.release();     // Catch: Exception -> L15
            this.videoEncoder = null;     // Catch: Exception -> L15
        L15:
            e = move-exception;
            FileLog.e(e);
        L9:
            e = move-exception;
            FileLog.e(e);
            goto L11
        }

        private void handleVideoFrameAvailable(long r19, Integer r21) {
            drainEncoder(false);     // Catch: Exception -> L5
        L7:
            long r6 = System.currentTimeMillis();
            if (this.lastCameraId.equals(r21) == true) goto L10;
            this.lastTimestamp = -1;
            this.lastCameraId = r21;
        L10:
            long r10 = this.lastTimestamp;
            if (r10 != (-1)) goto L15;
            this.lastTimestamp = r19;
            long r12 = 0;
            if (this.currentTimestamp == 0) goto L16;
            r12 = 1000000 * (r6 - this.lastCommitedFrameTime);
        L16:
            this.lastCommitedFrameTime = r6;
            if (this.skippedFirst == true) goto L22;
            long r62 = this.skippedTime + r12;
            this.skippedTime = r62;
            if (r62 >= 200000000) goto L21;
            return;
        L21:
            this.skippedFirst = true;
        L22:
            this.currentTimestamp += r12;
            if (this.videoFirst != (-1)) goto L27;
            this.videoFirst = r19 / 1000;
            if (BuildVars.LOGS_ENABLED == false) goto L27;
            FileLog.d("CameraView first video frame was at " + this.videoFirst);
        L27:
            this.videoLast = r19;
            if (CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[1][0] != 0) goto L30;
        L32:
            boolean r0 = this.this$0.dual;
            if (r0 == false) goto L35;
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GLES20.glClear(16384);
        L35:
            float r63 = CameraView.m5086$$Nest$fgetlastCrossfadeValue(this.this$0);
            if (r63 <= 0.0f) goto L38;
            boolean r7 = true;
        L39:
            int r9 = -1;
        L41:
            if (r9 >= 2) goto L89;
            if (r9 != (-1)) goto L45;
            if (r7 == true) goto L45;
        L88:
            r9 = r9 + 1;
        L45:
            if (r9 >= 0) goto L47;
            int r102 = 1;
        L49:
            if (CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r102][0] == 0) goto L88;
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, CameraView.m5104$$Nest$fgetvertexBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.positionHandle);
            GLES20.glVertexAttribPointer(this.textureHandle, 2, 5126, false, 8, this.textureBuffer);
            GLES20.glEnableVertexAttribArray(this.textureHandle);
            GLES20.glUniformMatrix4fv(this.vertexMatrixHandle, 1, false, CameraView.m5089$$Nest$fgetmMVPMatrix(this.this$0)[r102], 0);
            GLES20.glUniformMatrix4fv(this.cameraMatrixHandle, 1, false, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[r102], 0);
            GLES20.glUniformMatrix4fv(this.oppositeCameraMatrixHandle, 1, false, CameraView.m5080$$Nest$fgetcameraMatrix(this.this$0)[1 - r102], 0);
            GLES20.glActiveTexture(33984);
            GLES20.glUniformMatrix4fv(this.textureMatrixHandle, 1, false, CameraView.m5090$$Nest$fgetmSTMatrix(this.this$0)[r102], 0);
            GLES20.glUniform1f(this.blurHandle, 0.0f);
            Size r11 = CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[r102];
            if (r11 != null) goto L54;
        L63:
            int r112 = this.pixelHandle;
            if (r102 != 0) goto L66;
            GLES20.glUniform2f(r112, CameraView.m5095$$Nest$fgetpixelW(this.this$0), CameraView.m5094$$Nest$fgetpixelH(this.this$0));
        L67:
            int r113 = this.dualHandle;
            if (r102 != 0) goto L73;
            if (r0 == false) goto L71;
            float r122 = 1.0f;
        L72:
            GLES20.glUniform1f(r113, r122);
        L74:
            int r114 = this.alphaHandle;
            if (r102 != 1) goto L83;
            GLES20.glUniform1f(r114, 1.0f);
            if (r9 >= 0) goto L79;
            GLES20.glUniform1f(this.roundRadiusHandle, 0.0f);
            GLES20.glUniform1f(this.scaleHandle, 1.0f);
            GLES20.glUniform1f(this.shapeFromHandle, 2.0f);
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            GLES20.glUniform1f(this.shapeHandle, 0.0f);
            GLES20.glUniform1f(this.crossfadeHandle, 1.0f);
        L87:
            GLES20.glBindTexture(36197, CameraView.m5082$$Nest$fgetcameraTexture(this.this$0)[r102][0]);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.positionHandle);
            GLES20.glDisableVertexAttribArray(this.textureHandle);
            GLES20.glBindTexture(36197, 0);
            GLES20.glUseProgram(0);
            goto L88
        L79:
            int r115 = this.roundRadiusHandle;
            if (r7 == true) goto L82;
            GLES20.glUniform1f(r115, AndroidUtilities.dp(16.0f));
            GLES20.glUniform1f(this.scaleHandle, 1.0f);
            GLES20.glUniform1f(this.shapeFromHandle, (float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeToHandle, (float) Math.ceil(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeHandle, CameraView.m5099$$Nest$fgetshapeValue(this.this$0) - ((float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0))));
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
            goto L87
        L82:
            GLES20.glUniform1f(r115, AndroidUtilities.dp(16.0f));
            GLES20.glUniform1f(this.scaleHandle, 1.0f - r63);
            GLES20.glUniform1f(this.shapeFromHandle, (float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeToHandle, (float) Math.ceil(CameraView.m5099$$Nest$fgetshapeValue(this.this$0)));
            GLES20.glUniform1f(this.shapeHandle, CameraView.m5099$$Nest$fgetshapeValue(this.this$0) - ((float) Math.floor(CameraView.m5099$$Nest$fgetshapeValue(this.this$0))));
            GLES20.glUniform1f(this.shapeHandle, r63);
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
            goto L87
        L83:
            GLES20.glUniform1f(r114, 1.0f);
            int r116 = this.roundRadiusHandle;
            if (r7 == false) goto L86;
            GLES20.glUniform1f(r116, AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), r63));
            GLES20.glUniform1f(this.scaleHandle, 1.0f);
            GLES20.glUniform1f(this.shapeFromHandle, CameraView.m5087$$Nest$fgetlastShapeTo(this.this$0));
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            GLES20.glUniform1f(this.shapeHandle, Utilities.clamp(1.0f - r63, 1.0f, 0.0f));
            GLES20.glUniform1f(this.crossfadeHandle, r63);
            goto L87
        L86:
            GLES20.glUniform1f(r116, 0.0f);
            GLES20.glUniform1f(this.scaleHandle, 1.0f);
            GLES20.glUniform1f(this.shapeFromHandle, 2.0f);
            GLES20.glUniform1f(this.shapeToHandle, 2.0f);
            GLES20.glUniform1f(this.shapeHandle, 0.0f);
            GLES20.glUniform1f(this.crossfadeHandle, 0.0f);
            goto L87
        L71:
            r122 = 0.0f;
            goto L72
        L73:
            GLES20.glUniform1f(r113, 1.0f);
            goto L74
        L66:
            GLES20.glUniform2f(r112, CameraView.m5093$$Nest$fgetpixelDualW(this.this$0), CameraView.m5092$$Nest$fgetpixelDualH(this.this$0));
            goto L67
        L54:
            if (CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[r102] == null) goto L63;
            int r123 = CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[r102].getWorldAngle();
            if (r123 != 90) goto L58;
        L61:
            int r124 = r11.getWidth();
            int r117 = r11.getHeight();
        L62:
            GLES20.glUniform2f(this.pixelHandle, r124, r117);
            goto L67
        L58:
            if (r123 == 270) goto L61;
            r124 = r11.getHeight();
            r117 = r11.getWidth();
            goto L62
        L47:
            r102 = r9;
            goto L49
        L89:
            EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, this.currentTimestamp);
            EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            return;
        L38:
            r7 = false;
            goto L39
        L30:
            if (this.blendEnabled == true) goto L32;
            GLES20.glEnable(3042);
            this.blendEnabled = true;
            goto L32
        L15:
            r12 = r19 - r10;
            this.lastTimestamp = r19;
        L5:
            e = move-exception;
            FileLog.e(e);
            goto L7
        }

        private /* synthetic */ void lambda$drainEncoder$2(ByteBuffer r3, MediaCodec.BufferInfo r4) {
            this.mediaMuxer.writeSampleData(this.videoTrackIndex, r3, r4, true);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$drainEncoder$3(ByteBuffer r3, MediaCodec.BufferInfo r4) {
            this.mediaMuxer.writeSampleData(this.audioTrackIndex, r3, r4, false);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$handleStopRecording$0(CountDownLatch r1) {
            this.mediaMuxer.finishMovie();     // Catch: Exception -> L4
        L6:
            r1.countDown();
            return;
        L4:
            e = move-exception;
            e.printStackTrace();
            goto L6
        }

        private /* synthetic */ void lambda$handleStopRecording$1() {
            if (CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[0] == null) goto L6;
            CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[0].stopVideoRecording();
        L6:
            if (CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[1] == null) goto L8;
            CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[1].stopVideoRecording();
        L8:
            this.this$0.onRecordingFinishRunnable.run();
        }

        private void prepareEncoder() {
            int r3 = AudioRecord.getMinBufferSize(CameraView.audioSampleRate, 16, 2);     // Catch: Exception -> L96
            if (r3 > 0) goto L6;
            r3 = 3584;
        L6:
            int r7 = 49152;
            if (49152 >= r3) goto L9;
            r7 = ((r3 / 2048) + 1) * 4096;     // Catch: Exception -> L96
        L9:
            int r12 = r7;
            int r72 = 0;
        L11:
            if (r72 >= 3) goto L13;
            this.buffers.add(new InstantCameraView.AudioBufferInfo());     // Catch: Exception -> L96
            r72 = r72 + 1;     // Catch: Exception -> L96
            goto L11
        L13:
            AudioRecord r73 = new AudioRecord(0, CameraView.audioSampleRate, 16, 2, r12);     // Catch: Exception -> L96
            this.audioRecorder = r73;     // Catch: Exception -> L96
            r73.startRecording();     // Catch: Exception -> L96
            if (BuildVars.LOGS_ENABLED == false) goto L16;
            FileLog.d("CameraView initied audio record with channels " + this.audioRecorder.getChannelCount() + " sample rate = " + this.audioRecorder.getSampleRate() + " bufferSize = " + r12);     // Catch: Exception -> L96
        L16:
            Thread r74 = new Thread(this.recorderRunnable);     // Catch: Exception -> L96
            r74.setPriority(10);     // Catch: Exception -> L96
            r74.start();     // Catch: Exception -> L96
            this.audioBufferInfo = new MediaCodec.BufferInfo();     // Catch: Exception -> L96
            this.videoBufferInfo = new MediaCodec.BufferInfo();     // Catch: Exception -> L96
            MediaFormat r75 = new MediaFormat();     // Catch: Exception -> L96
            r75.setString("mime", "audio/mp4a-latm");     // Catch: Exception -> L96
            r75.setInteger("sample-rate", CameraView.audioSampleRate);     // Catch: Exception -> L96
            r75.setInteger("channel-count", 1);     // Catch: Exception -> L96
            r75.setInteger("bitrate", 32000);     // Catch: Exception -> L96
            r75.setInteger("max-input-size", 20480);     // Catch: Exception -> L96
            MediaCodec r0 = MediaCodec.createEncoderByType("audio/mp4a-latm");     // Catch: Exception -> L96
            this.audioEncoder = r0;     // Catch: Exception -> L96
            r0.configure(r75, null, null, 1);     // Catch: Exception -> L96
            this.audioEncoder.start();     // Catch: Exception -> L96
            boolean r02 = this.this$0.recordHevc;     // Catch: Exception -> L96
            if (r02 == false) goto L20;
            String r9 = VIDEO_MIME_TYPE;
        L21:
            this.outputMimeType = r9;     // Catch: Exception -> L96
            if (r02 == false) goto L28;
            String r03 = SharedConfig.findGoodHevcEncoder();     // Catch: Throwable -> L26
            if (r03 == null) goto L30;
            this.videoEncoder = MediaCodec.createByCodecName(r03);     // Catch: Throwable -> L26
        L30:
            if (this.outputMimeType.equals(VIDEO_MIME_TYPE) == false) goto L39;
            MediaCodec r04 = this.videoEncoder;     // Catch: Throwable -> L26
            if (r04 == null) goto L39;
            if (r04.getCodecInfo().isHardwareAccelerated() == true) goto L39;
            FileLog.e("hevc encoder isn't hardware accelerated");     // Catch: Throwable -> L26
            this.videoEncoder.release();     // Catch: Throwable -> L26
            this.videoEncoder = null;     // Catch: Throwable -> L26
        L39:
            if (this.videoEncoder == null) goto L41;
        L43:
            this.firstEncode = true;     // Catch: Exception -> L96
            MediaFormat r05 = MediaFormat.createVideoFormat(this.outputMimeType, CameraView.m5107$$Nest$fgetvideoWidth(this.this$0), CameraView.m5106$$Nest$fgetvideoHeight(this.this$0));     // Catch: Exception -> L96
            r05.setInteger("color-format", 2130708361);     // Catch: Exception -> L96
            r05.setInteger("bitrate", this.videoBitrate);     // Catch: Exception -> L96
            r05.setInteger("frame-rate", 30);     // Catch: Exception -> L96
            r05.setInteger("i-frame-interval", 1);     // Catch: Exception -> L96
            this.videoEncoder.configure(r05, null, null, 1);     // Catch: Exception -> L96
            this.surface = this.videoEncoder.createInputSurface();     // Catch: Exception -> L96
            this.videoEncoder.start();     // Catch: Exception -> L96
            boolean r06 = ImageLoader.isSdCardPath(this.videoFile);     // Catch: Exception -> L96
            this.fileToWrite = this.videoFile;     // Catch: Exception -> L96
            if (r06 == true) goto L99;
        L53:
            Mp4Movie r07 = new Mp4Movie();     // Catch: Exception -> L96
            r07.setCacheFile(this.fileToWrite);     // Catch: Exception -> L96
            r07.setRotation(0);     // Catch: Exception -> L96
            r07.setSize(CameraView.m5107$$Nest$fgetvideoWidth(this.this$0), CameraView.m5106$$Nest$fgetvideoHeight(this.this$0));     // Catch: Exception -> L96
            MP4Builder r08 = new MP4Builder().createMovie(r07, false, false);     // Catch: Exception -> L96
            this.mediaMuxer = r08;     // Catch: Exception -> L96
            r08.setAllowSyncFiles(false);     // Catch: Exception -> L96
            if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) goto L94;
            android.opengl.EGLDisplay r09 = EGL14.eglGetDisplay(0);
            this.eglDisplay = r09;
            if (r09 == EGL14.EGL_NO_DISPLAY) goto L92;
            int[] r2 = new int[2];
            if (EGL14.eglInitialize(r09, r2, 0, r2, 1) == true) goto L61;
            this.eglDisplay = null;
            GlShader$$ExternalSyntheticBUOutline1.m("unable to initialize EGL14");
            return;
        L61:
            if (this.eglContext != EGL14.EGL_NO_CONTEXT) goto L67;
            android.opengl.EGLConfig[] r22 = new android.opengl.EGLConfig[1];
            if (EGL14.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, EglBase.EGL_RECORDABLE_ANDROID, 1, 12344}, 0, r22, 0, 1, new int[1], 0) == false) goto L65;
            this.eglContext = EGL14.eglCreateContext(this.eglDisplay, r22[0], this.sharedEglContext, new int[]{12440, 2, 12344}, 0);
            this.eglConfig = r22[0];
            goto L67
        L65:
            GlShader$$ExternalSyntheticBUOutline1.m("Unable to find a suitable EGLConfig");
            return;
        L67:
            EGL14.eglQueryContext(this.eglDisplay, this.eglContext, 12440, new int[1], 0);
            if (this.eglSurface != EGL14.EGL_NO_SURFACE) goto L88;
            android.opengl.EGLSurface r010 = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surface, new int[]{12344}, 0);
            this.eglSurface = r010;
            if (r010 != null) goto L72;
            GlShader$$ExternalSyntheticBUOutline1.m("surface was null");
            return;
        L72:
            if (EGL14.eglMakeCurrent(this.eglDisplay, r010, r010, this.eglContext) == false) goto L74;
            GLES20.glBlendFunc(770, 771);
            float r011 = (1.0f / CameraView.m5097$$Nest$fgetscaleX(this.this$0)) / 2.0f;
            float r23 = (1.0f / CameraView.m5098$$Nest$fgetscaleY(this.this$0)) / 2.0f;
            float r76 = 0.5f - r011;
            float r8 = 0.5f - r23;
            float r012 = r011 + 0.5f;
            float r24 = r23 + 0.5f;
            float[] r4 = {r76, r8, r012, r8, r76, r24, r012, r24};
            FloatBuffer r013 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.textureBuffer = r013;
            r013.put(r4).position(0);
            int r014 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35633, AndroidUtilities.readRes(R.raw.camera_vert));
            int r25 = CameraView.m5122$$Nest$mloadShader(this.this$0, 35632, AndroidUtilities.readRes(R.raw.camera_frag));
            if (r014 == 0) goto L106;
            if (r25 == 0) goto L107;
            int r42 = GLES20.glCreateProgram();
            this.drawProgram = r42;
            GLES20.glAttachShader(r42, r014);
            GLES20.glAttachShader(this.drawProgram, r25);
            GLES20.glLinkProgram(this.drawProgram);
            int[] r015 = new int[1];
            GLES20.glGetProgramiv(this.drawProgram, 35714, r015, 0);
            int r016 = r015[0];
            int r26 = this.drawProgram;
            if (r016 != 0) goto L84;
            GLES20.glDeleteProgram(r26);
            this.drawProgram = 0;
            return;
        L84:
            this.positionHandle = GLES20.glGetAttribLocation(r26, "aPosition");
            this.textureHandle = GLES20.glGetAttribLocation(this.drawProgram, "aTextureCoord");
            this.vertexMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uMVPMatrix");
            this.textureMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uSTMatrix");
            this.cameraMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "cameraMatrix");
            this.oppositeCameraMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "oppositeCameraMatrix");
            this.roundRadiusHandle = GLES20.glGetUniformLocation(this.drawProgram, "roundRadius");
            this.pixelHandle = GLES20.glGetUniformLocation(this.drawProgram, "pixelWH");
            this.dualHandle = GLES20.glGetUniformLocation(this.drawProgram, "dual");
            this.scaleHandle = GLES20.glGetUniformLocation(this.drawProgram, "scale");
            this.blurHandle = GLES20.glGetUniformLocation(this.drawProgram, "blur");
            this.alphaHandle = GLES20.glGetUniformLocation(this.drawProgram, "alpha");
            this.crossfadeHandle = GLES20.glGetUniformLocation(this.drawProgram, "crossfade");
            this.shapeFromHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeFrom");
            this.shapeToHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeTo");
            this.shapeHandle = GLES20.glGetUniformLocation(this.drawProgram, "shapeT");
            return;
        L107:
            return;
        L106:
            return;
        L74:
            if (BuildVars.LOGS_ENABLED == false) goto L76;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        L76:
            GlShader$$ExternalSyntheticBUOutline1.m("eglMakeCurrent failed");
            return;
        L88:
            Segment$$ExternalSyntheticBUOutline1.m("surface already created");
            return;
        L92:
            GlShader$$ExternalSyntheticBUOutline1.m("unable to get EGL14 display");
            return;
        L94:
            GlShader$$ExternalSyntheticBUOutline1.m("EGL already set up");
            return;
        L99:
            File r017 = new File(ApplicationLoader.getFilesDirFixed(), "camera_tmp.mp4");     // Catch: Throwable -> L48
            this.fileToWrite = r017;     // Catch: Throwable -> L48
            if (r017.exists() == false) goto L50;
            this.fileToWrite.delete();     // Catch: Throwable -> L48
        L50:
            this.writingToDifferentFile = true;     // Catch: Throwable -> L48
        L48:
            th = move-exception;
            FileLog.e(th);     // Catch: Exception -> L96
            this.fileToWrite = this.videoFile;     // Catch: Exception -> L96
            this.writingToDifferentFile = false;     // Catch: Exception -> L96
            goto L53
        L41:
            if (this.outputMimeType.equals(VIDEO_MIME_TYPE) == false) goto L43;
            this.outputMimeType = MediaController.VIDEO_MIME_TYPE;     // Catch: Exception -> L96
            this.videoEncoder = MediaCodec.createEncoderByType(MediaController.VIDEO_MIME_TYPE);     // Catch: Exception -> L96
            goto L43
        L28:
            this.outputMimeType = MediaController.VIDEO_MIME_TYPE;     // Catch: Throwable -> L26
            this.videoEncoder = MediaCodec.createEncoderByType(MediaController.VIDEO_MIME_TYPE);     // Catch: Throwable -> L26
        L26:
            th = move-exception;
            FileLog.e("can't get hevc encoder");     // Catch: Exception -> L96
            FileLog.e(th);     // Catch: Exception -> L96
            goto L39
        L20:
            r9 = MediaController.VIDEO_MIME_TYPE;
        L96:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        }

        public void drainEncoder(boolean r18) {
            if (r18 == false) goto L5;
            this.videoEncoder.signalEndOfInputStream();
        L5:
            int r1 = this.videoEncoder.dequeueOutputBuffer(this.videoBufferInfo, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            byte r8 = 1;
            int r9 = 0;
            if (r1 == (-1)) goto L7;
            if (r1 == (-3)) goto L5;
            if (r1 == (-2)) goto L13;
            if (r1 < 0) goto L5;
            ByteBuffer r12 = this.videoEncoder.getOutputBuffer(r1);
            if (r12 == null) goto L108;
            MediaCodec.BufferInfo r13 = this.videoBufferInfo;
            int r14 = r13.size;
            if (r14 <= 1) goto L73;
            int r15 = r13.flags;
            if ((r15 & 2) != 0) goto L48;
            int r10 = this.prependHeaderSize;
            if (r10 == 0) goto L40;
            if ((r15 & 1) == 0) goto L40;
            r13.offset += r10;
            r13.size = r14 - r10;
        L40:
            if (this.firstEncode == true) goto L42;
        L44:
            MediaCodec.BufferInfo r102 = new MediaCodec.BufferInfo();
            MediaCodec.BufferInfo r11 = this.videoBufferInfo;
            r102.size = r11.size;
            r102.offset = r11.offset;
            r102.flags = r11.flags;
            r102.presentationTimeUs = r11.presentationTimeUs;
            ByteBuffer r112 = AndroidUtilities.cloneByteBuffer(r12);
            if (r112 == null) goto L73;
            this.fileWriteQueue.postRunnable(new CameraView$VideoRecorder$$ExternalSyntheticLambda0(this, r112, r102));
            goto L73
        L42:
            if ((r15 & 1) == 0) goto L44;
            MediaCodecVideoConvertor.cutOfNalData(this.outputMimeType, r12, r13);
            this.firstEncode = false;
            goto L44
        L48:
            if (this.videoTrackIndex != (-5)) goto L73;
            if (this.outputMimeType.equals(VIDEO_MIME_TYPE) == true) goto L71;
            MediaCodec.BufferInfo r132 = this.videoBufferInfo;
            int r142 = r132.size;
            byte[] r152 = new byte[r142];
            r12.limit(r132.offset + r142);
            r12.position(this.videoBufferInfo.offset);
            r12.get(r152);
            int r122 = this.videoBufferInfo.size - 1;
        L52:
            if (r122 < 0) goto L65;
            if (r122 <= 3) goto L65;
            if (r152[r122] != r8) goto L64;
            if (r152[r122 - 1] != 0) goto L64;
            if (r152[r122 - 2] != 0) goto L64;
            int r133 = r122 - 3;
            if (r152[r133] != 0) goto L64;
            ByteBuffer r123 = ByteBuffer.allocate(r133);
            ByteBuffer r143 = ByteBuffer.allocate(this.videoBufferInfo.size - r133);
            r123.put(r152, 0, r133).position(0);
            r143.put(r152, r133, this.videoBufferInfo.size - r133).position(0);
        L66:
            MediaFormat r82 = MediaFormat.createVideoFormat(MediaController.VIDEO_MIME_TYPE, CameraView.m5107$$Nest$fgetvideoWidth(this.this$0), CameraView.m5106$$Nest$fgetvideoHeight(this.this$0));
            if (r123 == null) goto L70;
            if (r143 == null) goto L70;
            r82.setByteBuffer("csd-0", r123);
            r82.setByteBuffer("csd-1", r143);
        L70:
            this.videoTrackIndex = this.mediaMuxer.addTrack(r82, false);
        L64:
            r122 = r122 - 1;
            r8 = 1;
        L65:
            r123 = null;
            r143 = null;
            goto L66
        L71:
            GlShader$$ExternalSyntheticBUOutline1.m("need fix parsing csd data");
            return;
        L73:
            this.videoEncoder.releaseOutputBuffer(r1, false);
            if ((this.videoBufferInfo.flags & 4) == 0) goto L5;
        L75:
            int r16 = this.audioEncoder.dequeueOutputBuffer(this.audioBufferInfo, 0);
            if (r16 == (-1)) goto L77;
            if (r16 == (-3)) goto L75;
            if (r16 == (-2)) goto L86;
            if (r16 < 0) goto L75;
            ByteBuffer r83 = this.audioEncoder.getOutputBuffer(r16);
            if (r83 == null) goto L106;
            MediaCodec.BufferInfo r113 = this.audioBufferInfo;
            if ((r113.flags & 2) == 0) goto L98;
            r113.size = 0;
        L98:
            if (r113.size == 0) goto L102;
            MediaCodec.BufferInfo r114 = new MediaCodec.BufferInfo();
            MediaCodec.BufferInfo r124 = this.audioBufferInfo;
            r114.size = r124.size;
            r114.offset = r124.offset;
            r114.flags = r124.flags;
            r114.presentationTimeUs = r124.presentationTimeUs;
            ByteBuffer r84 = AndroidUtilities.cloneByteBuffer(r83);
            if (r84 == null) goto L102;
            this.fileWriteQueue.postRunnable(new CameraView$VideoRecorder$$ExternalSyntheticLambda1(this, r84, r114));
        L102:
            this.audioEncoder.releaseOutputBuffer(r16, false);
            if ((this.audioBufferInfo.flags & 4) == 0) goto L75;
            return;
        L106:
            throw new RuntimeException("encoderOutputBuffer " + r16 + " was null");
        L86:
            MediaFormat r17 = this.audioEncoder.getOutputFormat();
            if (this.audioTrackIndex != (-5)) goto L75;
            this.audioTrackIndex = this.mediaMuxer.addTrack(r17, true);
            goto L75
        L77:
            if (r18 == false) goto L156;
            if (this.running == true) goto L75;
            if (this.sendWhenDone != 0) goto L75;
            return;
        L156:
            return;
        L108:
            throw new RuntimeException("encoderOutputBuffer " + r1 + " was null");
        L13:
            MediaFormat r19 = this.videoEncoder.getOutputFormat();
            if (this.videoTrackIndex != (-5)) goto L5;
            this.videoTrackIndex = this.mediaMuxer.addTrack(r19, false);
            if (r19.containsKey("prepend-sps-pps-to-idr-frames") == false) goto L5;
            if (r19.getInteger("prepend-sps-pps-to-idr-frames") != 1) goto L5;
            ByteBuffer r2 = r19.getByteBuffer("csd-0");
            ByteBuffer r110 = r19.getByteBuffer("csd-1");
            if (r2 != null) goto L22;
            int r22 = 0;
        L23:
            if (r110 == null) goto L26;
            r9 = r110.limit();
        L26:
            this.prependHeaderSize = r22 + r9;
            goto L5
        L22:
            r22 = r2.limit();
            goto L23
        L7:
            if (r18 == true) goto L5;
            goto L75
        }

        public void finalize() {
            DispatchQueue r0 = this.fileWriteQueue;
            if (r0 == null) goto L15;
            r0.recycle();
            this.fileWriteQueue = null;
        L15:
            android.opengl.EGLDisplay r02 = this.eglDisplay;     // Catch: Throwable -> L9
            if (r02 == EGL14.EGL_NO_DISPLAY) goto L11;
            android.opengl.EGLSurface r2 = EGL14.EGL_NO_SURFACE;     // Catch: Throwable -> L9
            EGL14.eglMakeCurrent(r02, r2, r2, EGL14.EGL_NO_CONTEXT);     // Catch: Throwable -> L9
            EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);     // Catch: Throwable -> L9
            EGL14.eglReleaseThread();     // Catch: Throwable -> L9
            EGL14.eglTerminate(this.eglDisplay);     // Catch: Throwable -> L9
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;     // Catch: Throwable -> L9
            this.eglContext = EGL14.EGL_NO_CONTEXT;     // Catch: Throwable -> L9
            this.eglConfig = null;     // Catch: Throwable -> L9
        L11:
            super.finalize();
            return;
        L9:
            th = move-exception;
            super.finalize();
            throw th;
        }

        public void frameAvailable(SurfaceTexture r5, Integer r6, long r7) {
            Object r0 = this.sync;
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.ready == true) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            monitor-exit(r0);     // Catch: Throwable -> L8
            long r02 = r5.getTimestamp();
            if (r02 != 0) goto L19;
            int r52 = this.zeroTimeStamps + 1;
            this.zeroTimeStamps = r52;
            if (r52 > 1) goto L16;
            return;
        L16:
            if (BuildVars.LOGS_ENABLED == false) goto L20;
            FileLog.d("CameraView fix timestamp enabled");
        L20:
            this.handler.sendMessage(this.handler.obtainMessage(2, (int) (r7 >> 32), (int) r7, r6));
            return;
        L19:
            this.zeroTimeStamps = 0;
            r7 = r02;
            goto L20
        }

        public Surface getInputSurface() {
            return this.surface;
        }

        @Override // java.lang.Runnable
        public void run() {
            Looper.prepare();
            Object r0 = this.sync;
            monitor-enter(r0);
            this.handler = new EncoderHandler(this);     // Catch: Throwable -> L15
            this.ready = true;     // Catch: Throwable -> L15
            this.sync.notify();     // Catch: Throwable -> L15
            monitor-exit(r0);     // Catch: Throwable -> L15
            Looper.loop();
            Object r1 = this.sync;
            monitor-enter(r1);
            this.ready = false;     // Catch: Throwable -> L12
            monitor-exit(r1);     // Catch: Throwable -> L12
            return;
        L12:
            th = move-exception;
            throw th;
        L15:
            th = move-exception;
            throw th;
        }

        public void startRecording(File r5, android.opengl.EGLContext r6) {
            String r0 = Build.DEVICE;
            Size r02 = CameraView.m5096$$Nest$fgetpreviewSize(this.this$0)[0];
            if (Math.min(r02.mHeight, r02.mWidth) < 720) goto L5;
            int r2 = 3500000;
        L6:
            this.videoFile = r5;
            if (CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[0].getWorldAngle() != 90) goto L9;
        L12:
            CameraView.m5119$$Nest$fputvideoWidth(this.this$0, r02.getWidth());
            CameraView.m5118$$Nest$fputvideoHeight(this.this$0, r02.getHeight());
        L13:
            this.videoBitrate = r2;
            this.sharedEglContext = r6;
            Object r52 = this.sync;
            monitor-enter(r52);
        L19:
            th = move-exception;
            throw th;
        L16:
            if (this.running == false) goto L21;
            monitor-exit(r52);     // Catch: Throwable -> L19
            return;
        L21:
            this.running = true;     // Catch: Throwable -> L19
            Thread r62 = new Thread(this, "TextureMovieEncoder");     // Catch: Throwable -> L19
            r62.setPriority(10);     // Catch: Throwable -> L19
            r62.start();     // Catch: Throwable -> L19
        L23:
            if (this.ready == true) goto L26;
            this.sync.wait();     // Catch: Throwable -> L19 InterruptedException -> L31
            goto L23
        L26:
            monitor-exit(r52);     // Catch: Throwable -> L19
            DispatchQueue r53 = new DispatchQueue("VR_FileWriteQueue");
            this.fileWriteQueue = r53;
            r53.setPriority(10);
            this.keyframeThumbs.clear();
            this.handler.sendMessage(this.handler.obtainMessage(0));
            return;
        L9:
            if (CameraView.m5081$$Nest$fgetcameraSession(this.this$0)[0].getWorldAngle() == 270) goto L12;
            CameraView.m5119$$Nest$fputvideoWidth(this.this$0, r02.getHeight());
            CameraView.m5118$$Nest$fputvideoHeight(this.this$0, r02.getWidth());
            goto L13
        L5:
            r2 = 1800000;
            goto L6
        }

        public void stopRecording(int r4) {
            this.handler.sendMessage(this.handler.obtainMessage(1, r4, 0));
        }
    }

    /* renamed from: $r8$lambda$D20pbOcvaZdtKbpmzt-Fs1LB9B4, reason: not valid java name */
    public static /* synthetic */ void m5078$r8$lambda$D20pbOcvaZdtKbpmztFs1LB9B4(CameraView r0, CameraGLThread r1, int r2) {
        r0.lambda$createCamera$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$G4zvoZYuTy9VIpCruMwApip5FZM(CameraView r0) {
        r0.onSurfaceTextureUpdatedInternal();
    }

    public static /* synthetic */ void $r8$lambda$Ltfzy5M2S43_6kUmo837eRc9tGk(CameraView r0, CameraGLThread r1) {
        r0.lambda$createCamera$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$M3V2Z9xJNqwCEZuBNzniFbIxdo8(CameraView r0) {
        r0.lambda$new$7();
    }

    public static /* synthetic */ void $r8$lambda$N63xQ3ILmVPzqdgMJPJWsoRq5Mw(CameraView r0, ValueAnimator r1) {
        r0.lambda$showTexture$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZK08APGPCzpI2eTQaRCJYfAl_xY(CameraView r0, int r1, CameraSession r2, CameraGLThread r3) {
        r0.lambda$createCamera$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$fC-vtSvjb2AkjXtPZm8_oCHFPk8, reason: not valid java name */
    public static /* synthetic */ void m5079$r8$lambda$fCvtSvjb2AkjXtPZm8_oCHFPk8(CameraView r0, Handler r1) {
        r0.lambda$enableDualInternal$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$fx261qFEd0fNzFj3vyVzP32aUnA(CameraView r0) {
        r0.lambda$enableDualInternal$0();
    }

    public static /* synthetic */ void $r8$lambda$ixBo8kpB8LZEfmg6ySViuEsQKis(CameraView r0) {
        r0.lambda$resetCamera$4();
    }

    public static /* synthetic */ void $r8$lambda$j2uRam2ZCB3JQGBEssUe8LupsWY(CameraView r0, CameraGLThread r1) {
        r0.lambda$createCamera$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$lf3Yacm0KlslNVSgLsoemZT7djI(CameraView r0, int r1, SurfaceTexture r2) {
        r0.lambda$createCamera$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oNZIvs9I7pL1MjRtSMsUq32rqhw(CameraView r0) {
        r0.lambda$toggleDual$2();
    }

    public static /* synthetic */ void $r8$lambda$pIXlGYn8QukqELebGtYZimBOOC4(CameraView r0, int r1, CameraGLThread r2) {
        r0.lambda$createCamera$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pMfEbgWw_AwObDq7pxlGLj0QFPo(CameraView r0) {
        r0.lambda$onSurfaceTextureDestroyed$5();
    }

    public static /* synthetic */ void $r8$lambda$wKUaVWFJFdea_Mug_HkzaO6edaE(CameraView r0) {
        r0.lambda$switchCamera$3();
    }

    /* renamed from: -$$Nest$fgetcameraMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[][] m5080$$Nest$fgetcameraMatrix(CameraView r0) {
        return r0.cameraMatrix;
    }

    /* renamed from: -$$Nest$fgetcameraSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSessionWrapper[] m5081$$Nest$fgetcameraSession(CameraView r0) {
        return r0.cameraSession;
    }

    /* renamed from: -$$Nest$fgetcameraTexture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[][] m5082$$Nest$fgetcameraTexture(CameraView r0) {
        return r0.cameraTexture;
    }

    /* renamed from: -$$Nest$fgetdualMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m5083$$Nest$fgetdualMatrix(CameraView r0) {
        return r0.dualMatrix;
    }

    /* renamed from: -$$Nest$fgetfpsLimit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5084$$Nest$fgetfpsLimit(CameraView r0) {
        return r0.fpsLimit;
    }

    /* renamed from: -$$Nest$fgetisFrontface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5085$$Nest$fgetisFrontface(CameraView r0) {
        return r0.isFrontface;
    }

    /* renamed from: -$$Nest$fgetlastCrossfadeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5086$$Nest$fgetlastCrossfadeValue(CameraView r0) {
        return r0.lastCrossfadeValue;
    }

    /* renamed from: -$$Nest$fgetlastShapeTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5087$$Nest$fgetlastShapeTo(CameraView r0) {
        return r0.lastShapeTo;
    }

    /* renamed from: -$$Nest$fgetlayoutLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m5088$$Nest$fgetlayoutLock(CameraView r0) {
        return r0.layoutLock;
    }

    /* renamed from: -$$Nest$fgetmMVPMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[][] m5089$$Nest$fgetmMVPMatrix(CameraView r0) {
        return r0.mMVPMatrix;
    }

    /* renamed from: -$$Nest$fgetmSTMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[][] m5090$$Nest$fgetmSTMatrix(CameraView r0) {
        return r0.mSTMatrix;
    }

    /* renamed from: -$$Nest$fgetmoldSTMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[][] m5091$$Nest$fgetmoldSTMatrix(CameraView r0) {
        return r0.moldSTMatrix;
    }

    /* renamed from: -$$Nest$fgetpixelDualH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5092$$Nest$fgetpixelDualH(CameraView r0) {
        return r0.pixelDualH;
    }

    /* renamed from: -$$Nest$fgetpixelDualW, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5093$$Nest$fgetpixelDualW(CameraView r0) {
        return r0.pixelDualW;
    }

    /* renamed from: -$$Nest$fgetpixelH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5094$$Nest$fgetpixelH(CameraView r0) {
        return r0.pixelH;
    }

    /* renamed from: -$$Nest$fgetpixelW, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5095$$Nest$fgetpixelW(CameraView r0) {
        return r0.pixelW;
    }

    /* renamed from: -$$Nest$fgetpreviewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Size[] m5096$$Nest$fgetpreviewSize(CameraView r0) {
        return r0.previewSize;
    }

    /* renamed from: -$$Nest$fgetscaleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5097$$Nest$fgetscaleX(CameraView r0) {
        return r0.scaleX;
    }

    /* renamed from: -$$Nest$fgetscaleY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5098$$Nest$fgetscaleY(CameraView r0) {
        return r0.scaleY;
    }

    /* renamed from: -$$Nest$fgetshapeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5099$$Nest$fgetshapeValue(CameraView r0) {
        return r0.shapeValue;
    }

    /* renamed from: -$$Nest$fgetsurfaceHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5100$$Nest$fgetsurfaceHeight(CameraView r0) {
        return r0.surfaceHeight;
    }

    /* renamed from: -$$Nest$fgetsurfaceWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5101$$Nest$fgetsurfaceWidth(CameraView r0) {
        return r0.surfaceWidth;
    }

    /* renamed from: -$$Nest$fgettextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatBuffer m5102$$Nest$fgettextureBuffer(CameraView r0) {
        return r0.textureBuffer;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m5103$$Nest$fgettextureView(CameraView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetvertexBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatBuffer m5104$$Nest$fgetvertexBuffer(CameraView r0) {
        return r0.vertexBuffer;
    }

    /* renamed from: -$$Nest$fgetvideoEncoder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoRecorder m5105$$Nest$fgetvideoEncoder(CameraView r0) {
        return r0.videoEncoder;
    }

    /* renamed from: -$$Nest$fgetvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5106$$Nest$fgetvideoHeight(CameraView r0) {
        return r0.videoHeight;
    }

    /* renamed from: -$$Nest$fgetvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5107$$Nest$fgetvideoWidth(CameraView r0) {
        return r0.videoWidth;
    }

    /* renamed from: -$$Nest$fputdualCameraAppeared, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5108$$Nest$fputdualCameraAppeared(CameraView r0, boolean r1) {
        r0.dualCameraAppeared = r1;
    }

    /* renamed from: -$$Nest$fputlastCrossfadeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5109$$Nest$fputlastCrossfadeValue(CameraView r0, float r1) {
        r0.lastCrossfadeValue = r1;
    }

    /* renamed from: -$$Nest$fputlastShapeTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5110$$Nest$fputlastShapeTo(CameraView r0, float r1) {
        r0.lastShapeTo = r1;
    }

    /* renamed from: -$$Nest$fputscaleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5111$$Nest$fputscaleX(CameraView r0, float r1) {
        r0.scaleX = r1;
    }

    /* renamed from: -$$Nest$fputscaleY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5112$$Nest$fputscaleY(CameraView r0, float r1) {
        r0.scaleY = r1;
    }

    /* renamed from: -$$Nest$fputshapeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5113$$Nest$fputshapeValue(CameraView r0, float r1) {
        r0.shapeValue = r1;
    }

    /* renamed from: -$$Nest$fputtextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5114$$Nest$fputtextureBuffer(CameraView r0, FloatBuffer r1) {
        r0.textureBuffer = r1;
    }

    /* renamed from: -$$Nest$fputtextureViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5115$$Nest$fputtextureViewAnimator(CameraView r0, ValueAnimator r1) {
        r0.textureViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputvertexBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5116$$Nest$fputvertexBuffer(CameraView r0, FloatBuffer r1) {
        r0.vertexBuffer = r1;
    }

    /* renamed from: -$$Nest$fputvideoEncoder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5117$$Nest$fputvideoEncoder(CameraView r0, VideoRecorder r1) {
        r0.videoEncoder = r1;
    }

    /* renamed from: -$$Nest$fputvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5118$$Nest$fputvideoHeight(CameraView r0, int r1) {
        r0.videoHeight = r1;
    }

    /* renamed from: -$$Nest$fputvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5119$$Nest$fputvideoWidth(CameraView r0, int r1) {
        r0.videoWidth = r1;
    }

    /* renamed from: -$$Nest$maddToDualWait, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5120$$Nest$maddToDualWait(CameraView r0, long r1) {
        r0.addToDualWait(r1);
    }

    /* renamed from: -$$Nest$mcreateCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5121$$Nest$mcreateCamera(CameraView r0, SurfaceTexture r1, int r2) {
        r0.createCamera(r1, r2);
    }

    /* renamed from: -$$Nest$mloadShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5122$$Nest$mloadShader(CameraView r0, int r1, String r2) {
        return r0.loadShader(r1, r2);
    }

    /* renamed from: -$$Nest$monFirstFrameRendered, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5123$$Nest$monFirstFrameRendered(CameraView r0, int r1) {
        r0.onFirstFrameRendered(r1);
    }

    public CameraView(Context r2, boolean r3) {
        this(r2, r3, false);
    }

    public CameraView(Context r8, boolean r9, boolean r10) {
        super(r8, null);
        this.WRITE_TO_FILE_IN_BACKGROUND = false;
        this.previewSize = new Size[2];
        this.pictureSize = new Size[2];
        this.info = new CameraInfo[2];
        this.txform = new Matrix();
        this.matrix = new Matrix();
        this.useCamera2 = false;
        this.cameraSession = new CameraSessionWrapper[2];
        this.focusProgress = 1.0f;
        this.outerPaint = new Paint(1);
        this.innerPaint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.layoutLock = new Object();
        Class r6 = Float.TYPE;
        this.mMVPMatrix = (float[][]) Array.newInstance(r6, new int[]{2, 16});
        this.mSTMatrix = (float[][]) Array.newInstance(r6, new int[]{2, 16});
        this.moldSTMatrix = (float[][]) Array.newInstance(r6, new int[]{2, 16});
        this.cameraMatrix = (float[][]) Array.newInstance(r6, new int[]{2, 16});
        this.lastCrossfadeValue = 0.0f;
        this.flipping = false;
        this.fpsLimit = -1;
        this.dualMatrix = new Matrix();
        this.textureInited = false;
        this.bounds = new Rect();
        this.measurementsCount = 0;
        this.lastWidth = -1;
        this.lastHeight = -1;
        this.updateRotationMatrix = new CameraView$$ExternalSyntheticLambda14(this);
        this.takePictureProgress = 1.0f;
        this.invalidateListeners = new ArrayList();
        this.cameraTexture = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 1});
        CameraController.getInstance().addOnErrorListener(this);
        this.isFrontface = r9;
        this.initialFrontface = r9;
        this.textureView = new TextureView(r8);
        this.lazy = r10;
        if (r10 == true) goto L5;
        initTexture();
    L5:
        setWillNotDraw(!r10);
        ImageView r92 = new ImageView(r8);
        this.blurredStubView = r92;
        addView(r92, LayoutHelper.createFrame(-1, -1, 17));
        this.blurredStubView.setVisibility(8);
        this.focusAreaSize = AndroidUtilities.dp(96.0f);
        this.outerPaint.setColor(-1);
        this.outerPaint.setStyle(Paint.Style.STROKE);
        this.outerPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.innerPaint.setColor(Integer.MAX_VALUE);
    }

    private void addToDualWait(long r6) {
        long r0 = System.currentTimeMillis();
        long r2 = this.toggleDualUntil;
        if (r2 >= r0) goto L6;
        this.toggleDualUntil = r0 + r6;
        return;
    L6:
        this.toggleDualUntil = r2 + r6;
    }

    private Rect calculateTapArea(float r4, float r5, float r6) {
        int r62 = Float.valueOf(this.focusAreaSize * r6).intValue();
        int r0 = r62 / 2;
        RectF r02 = new RectF(clamp(((int) r4) - r0, 0, getWidth() - r62), clamp(((int) r5) - r0, 0, getHeight() - r62), r4 + r62, r5 + r62);
        this.matrix.mapRect(r02);
        return new Rect(Math.round(r02.left), Math.round(r02.top), Math.round(r02.right), Math.round(r02.bottom));
    }

    private void checkPreviewMatrix() {
        if (this.previewSize[0] == null) goto L18;
        TextureView r0 = this.textureView;
        if (r0 == null) goto L20;
        int r02 = r0.getWidth();
        int r2 = this.textureView.getHeight();
        Matrix r3 = new Matrix();
        if (this.cameraSession[0] == null) goto L10;
        r3.postRotate(r1.getDisplayOrientation());
    L10:
        float r03 = r02;
        float r22 = r2;
        r3.postScale(r03 / 2000.0f, r22 / 2000.0f);
        r3.postTranslate(r03 / 2.0f, r22 / 2.0f);
        r3.invert(this.matrix);
        CameraGLThread r04 = this.cameraThread;
        if (r04 != null) goto L13;
        return;
    L13:
        if (r04.isReady() == true) goto L16;
        this.updateRotationMatrix.run();
        return;
    L16:
        this.cameraThread.postRunnable(this.updateRotationMatrix);
        return;
    L20:
        return;
    }

    private int clamp(int r1, int r2, int r3) {
        if (r1 <= r3) goto L4;
        return r3;
    L4:
        if (r1 >= r2) goto L6;
        return r2;
    L6:
        return r1;
    }

    private void createCamera(SurfaceTexture r2, int r3) {
        AndroidUtilities.runOnUIThread(new CameraView$$ExternalSyntheticLambda12(this, r3, r2));
    }

    private void enableDualInternal() {
        CameraSessionWrapper r0 = this.cameraSession[1];
        if (r0 == null) goto L13;
        if (this.closingDualCamera == false) goto L7;
        return;
    L7:
        this.closingDualCamera = true;
        r0.destroy(false, null, new CameraView$$ExternalSyntheticLambda7(this));
        CameraSessionWrapper r02 = this.cameraSessionRecording;
        CameraSessionWrapper[] r3 = this.cameraSession;
        if (r02 != r3[1]) goto L10;
        this.cameraSessionRecording = null;
    L10:
        r3[1] = null;
        addToDualWait(400);
        return;
    L13:
        if (this.isFrontface == false) goto L15;
    L25:
        updateCameraInfoSize(1);
        Handler r03 = this.cameraThread.getHandler();
        if (r03 == null) goto L28;
        CameraGLThread r2 = this.cameraThread;
        Objects.requireNonNull(r2);
        r2.sendMessage(r03.obtainMessage(6, this.info[1].cameraId, 0, this.dualMatrix), 0);
    L28:
        addToDualWait(800);
        return;
    L15:
        if ("samsung".equalsIgnoreCase(Build.MANUFACTURER) == false) goto L25;
        if (this.toggledDualAsSave == true) goto L25;
        if (this.cameraSession[0] == null) goto L25;
        Handler r04 = this.cameraThread.getHandler();
        if (r04 == null) goto L23;
        CameraGLThread r1 = this.cameraThread;
        Objects.requireNonNull(r1);
        r1.sendMessage(r04.obtainMessage(11), 0);
    L23:
        this.cameraSession[0].destroy(false, null, new CameraView$$ExternalSyntheticLambda8(this, r04));
        this.cameraSession[0] = null;
    }

    public static boolean isCameraAllowed() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L8;
        if (r0.hasVideoCapturer() == false) goto L8;
        return false;
    L8:
        if (LivePlayer.recording == null) goto L10;
        return false;
    L10:
        return true;
    }

    private /* synthetic */ void lambda$createCamera$10(CameraGLThread r3) {
        updateCameraInfoSize(0);
        r3.reinitForNewCamera();
        addToDualWait(350);
    }

    private /* synthetic */ void lambda$createCamera$11(int r3, CameraSession r4, CameraGLThread r5) {
        if (this.cameraSession[r3] == null) goto L9;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("CameraView camera initied " + r3);
    L7:
        r4.setInitied();
        requestLayout();
    L9:
        if (this.dual == true) goto L11;
        return;
    L11:
        if (r3 == 1) goto L13;
        return;
    L13:
        if (this.initFirstCameraAfterSecond == false) goto L18;
        this.initFirstCameraAfterSecond = false;
        AndroidUtilities.runOnUIThread(new CameraView$$ExternalSyntheticLambda1(this, r5));
        return;
    }

    private /* synthetic */ void lambda$createCamera$12(CameraGLThread r1, int r2) {
        r1.setCurrentSession(this.cameraSession[r2], r2);
    }

    private /* synthetic */ void lambda$createCamera$13(int r10, SurfaceTexture r11) {
        CameraGLThread r0 = this.cameraThread;
        if (r0 != null) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        StringBuilder r1 = new StringBuilder("CameraView create camera");
        if (this.useCamera2 == false) goto L10;
        String r2 = "2";
    L11:
        r1.append(r2);
        r1.append(" session ");
        r1.append(r10);
        FileLog.d(r1.toString());
        goto L13
    L10:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if (this.useCamera2 == false) goto L26;
        boolean r12 = this.isFrontface;
        if (r10 == 0) goto L20;
        if (r12 == true) goto L19;
        r12 = true;
        goto L20
    L19:
        r12 = false;
    L20:
        Camera2Session r13 = Camera2Session.create(r12, this.surfaceWidth, this.surfaceHeight);
        if (r13 == null) goto L34;
        this.cameraSession[r10] = CameraSessionWrapper.of(r13);
        this.previewSize[r10] = new Size(r13.getPreviewWidth(), r13.getPreviewHeight());
        r0.setCurrentSession(this.cameraSession[r10], r10);
        r13.whenDone(new CameraView$$ExternalSyntheticLambda9(this, r10, r0));
        r13.open(r11);
        return;
    L34:
        return;
    L26:
        if (this.previewSize[r10] != null) goto L28;
        updateCameraInfoSize(r10);
    L28:
        Size r14 = this.previewSize[r10];
        if (r14 != null) goto L31;
        return;
    L31:
        r11.setDefaultBufferSize(r14.getWidth(), this.previewSize[r10].getHeight());
        CameraSession r3 = new CameraSession(this.info[r10], this.previewSize[r10], this.pictureSize[r10], 256, false);
        r3.setCurrentFlashMode("off");
        this.cameraSession[r10] = CameraSessionWrapper.of(r3);
        r0.setCurrentSession(this.cameraSession[r10], r10);
        requestLayout();
        CameraController.getInstance().open(r3, r11, new CameraView$$ExternalSyntheticLambda10(this, r10, r3, r0), new CameraView$$ExternalSyntheticLambda11(this, r0, r10));
    }

    private /* synthetic */ void lambda$createCamera$8(CameraGLThread r3) {
        updateCameraInfoSize(0);
        r3.reinitForNewCamera();
        addToDualWait(350);
    }

    private /* synthetic */ void lambda$createCamera$9(int r2, CameraGLThread r3) {
        requestLayout();
        if (this.dual == true) goto L5;
        return;
    L5:
        if (r2 == 1) goto L7;
        return;
    L7:
        if (this.initFirstCameraAfterSecond == false) goto L12;
        this.initFirstCameraAfterSecond = false;
        AndroidUtilities.runOnUIThread(new CameraView$$ExternalSyntheticLambda2(this, r3));
        return;
    }

    private /* synthetic */ void lambda$enableDualInternal$0() {
        this.closingDualCamera = false;
        enableDualInternal();
    }

    private /* synthetic */ void lambda$enableDualInternal$1(Handler r6) {
        this.initFirstCameraAfterSecond = true;
        updateCameraInfoSize(1);
        if (r6 == null) goto L5;
        CameraGLThread r1 = this.cameraThread;
        Objects.requireNonNull(r1);
        r1.sendMessage(r6.obtainMessage(6, this.info[1].cameraId, 0, this.dualMatrix), 0);
    L5:
        addToDualWait(1200);
    }

    private /* synthetic */ void lambda$new$7() {
        CameraGLThread r0 = this.cameraThread;
        if (r0 == null) goto L13;
        int r2 = 0;
    L6:
        if (r2 >= 2) goto L18;
        if (CameraGLThread.m5128$$Nest$fgetcurrentSession(r0)[r2] == null) goto L12;
        int r3 = CameraGLThread.m5128$$Nest$fgetcurrentSession(r0)[r2].getWorldAngle();
        android.opengl.Matrix.setIdentityM(this.mMVPMatrix[r2], 0);
        if (r3 == 0) goto L12;
        android.opengl.Matrix.rotateM(this.mMVPMatrix[r2], 0, r3, 0.0f, 0.0f, 1.0f);
    L12:
        r2 = r2 + 1;
        goto L6
    L18:
        return;
    }

    private /* synthetic */ void lambda$onSurfaceTextureDestroyed$5() {
        this.cameraThread = null;
    }

    private /* synthetic */ void lambda$resetCamera$4() {
        this.inited = false;
        Object r1 = this.layoutLock;
        monitor-enter(r1);
        this.firstFrameRendered = false;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        updateCameraInfoSize(0);
        this.cameraThread.reinitForNewCamera();
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$showTexture$6(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.textureView.setAlpha(r12);
    }

    private /* synthetic */ void lambda$switchCamera$3() {
        this.inited = false;
        Object r1 = this.layoutLock;
        monitor-enter(r1);
        this.firstFrameRendered = false;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        updateCameraInfoSize(0);
        this.cameraThread.reinitForNewCamera();
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$toggleDual$2() {
        this.closingDualCamera = false;
        this.dualCameraAppeared = false;
        addToDualWait(400);
        Handler r1 = this.cameraThread.getHandler();
        if (r1 == null) goto L6;
        CameraGLThread r3 = this.cameraThread;
        Objects.requireNonNull(r3);
        r3.sendMessage(r1.obtainMessage(10), 0);
        return;
    }

    private int loadShader(int r2, String r3) {
        int r1 = GLES20.glCreateShader(r2);
        GLES20.glShaderSource(r1, r3);
        GLES20.glCompileShader(r1);
        int[] r22 = new int[1];
        GLES20.glGetShaderiv(r1, 35713, r22, 0);
        if (r22[0] == 0) goto L5;
        return r1;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e(GLES20.glGetShaderInfoLog(r1));
    L7:
        GLES20.glDeleteShader(r1);
        return 0;
    }

    private void onFirstFrameRendered(int r3) {
        if (r3 != 0) goto L7;
        this.flipping = false;
        if (this.blurredStubView.getVisibility() != 0) goto L9;
        this.blurredStubView.animate().alpha(0.0f).setListener(new AnonymousClass5(this)).setDuration(120).start();
        return;
    L9:
        return;
    L7:
        onDualCameraSuccess();
    }

    private void onSurfaceTextureUpdatedInternal() {
        if (this.inited == true) goto L14;
        CameraSessionWrapper r0 = this.cameraSession[0];
        if (r0 == null) goto L14;
        if (r0.isInitiated() == false) goto L14;
        CameraViewDelegate r02 = this.delegate;
        if (r02 == null) goto L11;
        r02.onCameraInit();
    L11:
        this.inited = true;
        if (this.lazy == false) goto L14;
        this.textureView.setAlpha(0.0f);
        showTexture(true, true);
    L14:
        Object r03 = this.renderNode;
        if (r03 != null) goto L17;
        return;
    L17:
        if (BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r03).hasDisplayList() == true) goto L21;
        invalidate();
        return;
    }

    private void updateCameraInfoSize(int r10) {
        ArrayList<CameraInfo> r0 = CameraController.getInstance().getCameras();
        if (r0 == null) goto L52;
        int r2 = 0;
    L7:
        if (r2 >= r0.size()) goto L19;
        CameraInfo r3 = r0.get(r2);
        if (r3.frontCamera == 0) goto L11;
        boolean r5 = true;
    L12:
        boolean r6 = this.isFrontface;
        if (r10 != 1) goto L15;
        r6 = !r6;
    L15:
        if (r5 == r6) goto L16;
        r2 = r2 + 1;
        goto L7
    L16:
        this.info[r10] = r3;
        goto L19
    L11:
        r5 = false;
    L19:
        if (this.info[r10] != null) goto L21;
        return;
    L21:
        Point r02 = AndroidUtilities.displaySize;
        float r03 = Math.max(r02.x, r02.y);
        Point r22 = AndroidUtilities.displaySize;
        float r04 = r03 / Math.min(r22.x, r22.y);
        int r32 = 720;
        if (square() == false) goto L24;
        Size r05 = new Size(1, 1);
        int r4 = 720;
        int r62 = 720;
        int r7 = 720;
    L45:
        this.previewSize[r10] = CameraController.chooseOptimalSize(this.info[r10].getPreviewSizes(), r32, r7, r05, this.isStory);
        this.pictureSize[r10] = CameraController.chooseOptimalSize(this.info[r10].getPictureSizes(), r62, r4, r05, false);
        if (BuildVars.LOGS_ENABLED == false) goto L48;
        FileLog.d("camera preview " + this.previewSize[0]);
    L48:
        requestLayout();
        return;
    L24:
        r62 = 1280;
        if (this.initialFrontface == false) goto L28;
        r05 = new Size(16, 9);
        r4 = 720;
        r7 = 720;
    L27:
        r32 = 1280;
        goto L45
    L28:
        int r23 = 1920;
        r7 = 960;
        if (Math.abs(r04 - 1.3333334f) >= 0.1f) goto L35;
        r05 = new Size(4, 3);
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L33;
        r32 = 1280;
        r4 = 960;
        goto L45
    L33:
        r4 = 1440;
    L34:
        r32 = 1280;
        r62 = r23;
        goto L45
    L35:
        r05 = new Size(16, 9);
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L38;
        r4 = 960;
        r7 = 720;
        goto L27
    L38:
        boolean r42 = this.isStory;
        if (r42 == false) goto L41;
        r23 = 1280;
    L41:
        if (r42 == false) goto L43;
        r4 = 720;
    L44:
        r7 = 720;
        goto L34
    L43:
        r4 = 1080;
        goto L44
    }

    public void destroy(boolean r4, Runnable r5) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L9;
        CameraSessionWrapper r1 = this.cameraSession[r0];
        if (r1 == null) goto L8;
        r1.destroy(r4, r5, null);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        CameraController.getInstance().removeOnErrorListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.flipAnimator == null) goto L5;
        r5.drawColor(-16777216);
    L5:
        super.dispatchDraw(r5);
        float r0 = this.takePictureProgress;
        if (r0 == 1.0f) goto L13;
        float r02 = r0 + 0.064f;
        this.takePictureProgress = r02;
        if (r02 <= 1.0f) goto L10;
        this.takePictureProgress = 1.0f;
    L11:
        r5.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) ((1.0f - this.takePictureProgress) * 150.0f)));
        return;
    L10:
        invalidate();
        goto L11
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r9, View r10, long r11) {
        if (r10 == this.textureView) goto L5;
    L9:
        Canvas r0 = r9;
    L10:
        boolean r112 = super.drawChild(r0, r10, r11);
        if (r10 != this.textureView) goto L20;
        if (r9.isHardwareAccelerated() == false) goto L20;
        Object r102 = this.renderNode;
        if (r102 == null) goto L20;
        RenderNode r103 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r102);
        r103.endRecording();
        r9.drawRenderNode(r103);
        Object r12 = this.blurRenderNode;
        if (r12 == null) goto L20;
        RenderNode r122 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r12);
        r122.setPosition(0, 0, getWidth(), getHeight());
        r122.beginRecording().drawRenderNode(r103);
        r122.endRecording();
    L20:
        if (this.focusProgress == 1.0f) goto L22;
    L25:
        int r104 = AndroidUtilities.dp(30.0f);
        long r1 = System.currentTimeMillis();
        long r3 = r1 - this.lastDrawTime;
        if (r3 >= 0) goto L28;
    L29:
        r3 = 17;
    L30:
        this.lastDrawTime = r1;
        this.outerPaint.setAlpha((int) (this.interpolator.getInterpolation(this.outerAlpha) * 255.0f));
        this.innerPaint.setAlpha((int) (this.interpolator.getInterpolation(this.innerAlpha) * 127.0f));
        float r13 = this.interpolator.getInterpolation(this.focusProgress);
        float r105 = r104;
        r9.drawCircle(this.cx, this.cy, ((1.0f - r13) * r105) + r105, this.outerPaint);
        r9.drawCircle(this.cx, this.cy, r105 * r13, this.innerPaint);
        float r92 = this.focusProgress;
        if (r92 >= 1.0f) goto L37;
        float r93 = r92 + (r3 / 200.0f);
        this.focusProgress = r93;
        if (r93 <= 1.0f) goto L35;
        this.focusProgress = 1.0f;
    L35:
        invalidate();
        return r112;
    L37:
        float r94 = this.innerAlpha;
        if (r94 == 0.0f) goto L44;
        float r95 = r94 - (r3 / 150.0f);
        this.innerAlpha = r95;
        if (r95 >= 0.0f) goto L42;
        this.innerAlpha = 0.0f;
    L42:
        invalidate();
        return r112;
    L44:
        float r96 = this.outerAlpha;
        if (r96 == 0.0f) goto L50;
        float r97 = r96 - (r3 / 150.0f);
        this.outerAlpha = r97;
        if (r97 >= 0.0f) goto L49;
        this.outerAlpha = 0.0f;
    L49:
        invalidate();
    L50:
        return r112;
    L28:
        if (r3 <= 17) goto L30;
    L22:
        if (this.innerAlpha != 0.0f) goto L25;
        if (this.outerAlpha == 0.0f) goto L50;
    L5:
        if (r9.isHardwareAccelerated() == false) goto L9;
        Object r02 = this.renderNode;
        if (r02 == null) goto L9;
        RenderNode r03 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r02);
        r03.setPosition(0, 0, getWidth(), getHeight());
        r0 = r03.beginRecording();
        goto L10
    }

    public void dualToggleShape() {
        if (this.flipping == false) goto L5;
        return;
    L5:
        if (this.dual == false) goto L16;
        Handler r0 = this.cameraThread.getHandler();
        if (this.shape != null) goto L10;
        this.shape = Integer.valueOf(MessagesController.getGlobalMainSettings().getInt("dualshape", 0));
    L10:
        this.shape = Integer.valueOf(this.shape.intValue() + 1);
        MessagesController.getGlobalMainSettings().edit().putInt("dualshape", this.shape.intValue()).apply();
        if (r0 == null) goto L15;
        Objects.requireNonNull(this.cameraThread);
        r0.sendMessage(r0.obtainMessage(9));
        return;
    L15:
        return;
    }

    public void focusToPoint(int r2, int r3) {
        focusToPoint(r2, r3, true);
    }

    public void focusToPoint(int r4, int r5, int r6, int r7, int r8, boolean r9) {
        float r52 = r5;
        float r62 = r6;
        Rect r1 = calculateTapArea(r52, r62, 1.0f);
        Rect r53 = calculateTapArea(r52, r62, 1.5f);
        CameraSessionWrapper r42 = this.cameraSession[r4];
        if (r42 == null) goto L5;
        r42.focusToRect(r1, r53);
    L5:
        if (r9 == false) goto L8;
        this.focusProgress = 0.0f;
        this.innerAlpha = 1.0f;
        this.outerAlpha = 1.0f;
        this.cx = r7;
        this.cy = r8;
        this.lastDrawTime = System.currentTimeMillis();
        invalidate();
        return;
    }

    public void focusToPoint(int r8, int r9, boolean r10) {
        focusToPoint(0, r8, r9, r8, r9, r10);
    }

    public Object getBlurRenderNode() {
        if (this.renderNode != null) goto L8;
        if (Build.VERSION.SDK_INT < 31) goto L8;
        this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("CameraViewRenderNode");
        RenderNode r0 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("CameraViewRenderNodeBlur");
        this.blurRenderNode = r0;
        BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r0);
        r0.setRenderEffect(RenderEffect.createBlurEffect(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f), CameraView$$ExternalSyntheticApiModelOutline0.m()));
    L8:
        return this.blurRenderNode;
    }

    public CameraSessionWrapper getCameraSession() {
        return getCameraSession(0);
    }

    public CameraSessionWrapper getCameraSession(int r1) {
        return this.cameraSession[r1];
    }

    public Object getCameraSessionObject() {
        CameraSessionWrapper r1 = this.cameraSession[0];
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getObject();
    }

    public CameraSessionWrapper getCameraSessionRecording() {
        return this.cameraSessionRecording;
    }

    public Matrix getDualPosition() {
        return this.dualMatrix;
    }

    public int getDualShape() {
        if (this.shape != null) goto L6;
        this.shape = Integer.valueOf(MessagesController.getGlobalMainSettings().getInt("dualshape", 0));
    L6:
        return this.shape.intValue();
    }

    @Override // android.view.View
    public Matrix getMatrix() {
        return this.txform;
    }

    public Size getPreviewSize() {
        return this.previewSize[0];
    }

    public float getTextureHeight(float r4, float r5) {
        if (this.previewSize[0] == null) goto L16;
        CameraSessionWrapper r0 = this.cameraSession[0];
        if (r0 == null) goto L16;
        if (r0.getWorldAngle() != 90) goto L10;
    L13:
        int r02 = this.previewSize[0].getWidth();
        int r3 = this.previewSize[0].getHeight();
    L14:
        float r32 = r3;
        return (int) (Math.max(r4 / r02, r5 / r32) * r32);
    L10:
        if (this.cameraSession[0].getWorldAngle() == 270) goto L13;
        r02 = this.previewSize[0].getHeight();
        r3 = this.previewSize[0].getWidth();
    L16:
        return r5;
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public boolean hasFrontFaceCamera() {
        ArrayList<CameraInfo> r3 = CameraController.getInstance().getCameras();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L10;
        if (r3.get(r1).frontCamera != 0) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public void initTexture() {
        if (this.textureInited == false) goto L5;
        return;
    L5:
        this.textureView.setSurfaceTextureListener(this);
        addView(this.textureView, 0, LayoutHelper.createFrame(-1, -1, 17));
        this.textureInited = true;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        ArrayList<Runnable> r3 = this.invalidateListeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Runnable r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.run();
        goto L3
    }

    @Override // android.view.View
    public void invalidate(int r1, int r2, int r3, int r4) {
        super.invalidate(r1, r2, r3, r4);
        ArrayList<Runnable> r0 = this.invalidateListeners;
        int r12 = r0.size();
        int r22 = 0;
    L3:
        if (r22 >= r12) goto L5;
        Runnable r32 = r0.get(r22);
        r22 = r22 + 1;
        r32.run();
        goto L3
    }

    @Override // android.view.View
    public void invalidate(Rect r3) {
        super.invalidate(r3);
        ArrayList<Runnable> r2 = this.invalidateListeners;
        int r32 = r2.size();
        int r0 = 0;
    L3:
        if (r0 >= r32) goto L5;
        Runnable r1 = r2.get(r0);
        r0 = r0 + 1;
        r1.run();
        goto L3
    }

    public boolean isDual() {
        return this.dual;
    }

    public boolean isFrontface() {
        return this.isFrontface;
    }

    public boolean isInited() {
        return this.inited;
    }

    public void listenDraw(Runnable r1) {
        this.invalidateListeners.add(r1);
    }

    public TextureView makeBlurTextureView() {
        if (this.blurTextureView != null) goto L6;
        TextureView r0 = new TextureView(getContext());
        this.blurTextureView = r0;
        r0.setSurfaceTextureListener(new AnonymousClass4(this));
    L6:
        return this.blurTextureView;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.measurementsCount = 0;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.thumbDrawable == null) goto L5;
        this.bounds.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        int r0 = this.thumbDrawable.getIntrinsicWidth();
        float r02 = r0;
        float r1 = this.thumbDrawable.getIntrinsicHeight();
        float r3 = 1.0f / Math.min(r02 / Math.max(1, this.bounds.width()), r1 / Math.max(1, this.bounds.height()));
        float r03 = (r02 * r3) / 2.0f;
        float r12 = (r1 * r3) / 2.0f;
        this.thumbDrawable.setBounds((int) (this.bounds.centerX() - r03), (int) (this.bounds.centerY() - r12), (int) (this.bounds.centerX() + r03), (int) (this.bounds.centerY() + r12));
        this.thumbDrawable.draw(r8);
    L5:
        super.onDraw(r8);
    }

    public void onDualCameraSuccess() {
    }

    @Override // org.telegram.messenger.camera.CameraController.ErrorCallback
    public void onError(int r1, Camera r2, CameraSessionWrapper r3) {
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkPreviewMatrix();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r0 = View.MeasureSpec.getSize(r8);
        int r1 = View.MeasureSpec.getSize(r9);
        if (this.previewSize[0] == null) goto L25;
        CameraSessionWrapper r2 = this.cameraSession[0];
        if (r2 == null) goto L25;
        if (this.lastWidth != r0) goto L11;
        if (this.lastHeight != r1) goto L11;
    L13:
        this.measurementsCount++;
        if (this.cameraSession[0].getWorldAngle() != 90) goto L16;
    L19:
        int r22 = this.previewSize[0].getWidth();
        int r3 = this.previewSize[0].getHeight();
    L21:
        if (this.fit == false) goto L23;
        float r4 = Math.min(r0 / r22, r1 / r3);
    L24:
        ViewGroup.LayoutParams r5 = this.blurredStubView.getLayoutParams();
        int r23 = (int) (r22 * r4);
        this.textureView.getLayoutParams().width = r23;
        r5.width = r23;
        ViewGroup.LayoutParams r24 = this.blurredStubView.getLayoutParams();
        int r32 = (int) (r4 * r3);
        this.textureView.getLayoutParams().height = r32;
        r24.height = r32;
        goto L25
    L23:
        r4 = Math.max(r0 / r22, r1 / r3);
        goto L24
    L16:
        if (this.cameraSession[0].getWorldAngle() == 270) goto L19;
        r22 = this.previewSize[0].getHeight();
        r3 = this.previewSize[0].getWidth();
    L11:
        if (this.measurementsCount <= 1) goto L13;
        r2.updateRotation();
    L25:
        super.onMeasure(r8, r9);
        checkPreviewMatrix();
        this.lastWidth = r0;
        this.lastHeight = r1;
        this.pixelW = getMeasuredWidth();
        this.pixelH = getMeasuredHeight();
        this.pixelDualW = getMeasuredWidth();
        this.pixelDualH = getMeasuredHeight();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture r2, int r3, int r4) {
        updateCameraInfoSize(0);
        if (this.dual == false) goto L5;
        updateCameraInfoSize(1);
    L5:
        this.surfaceHeight = r4;
        this.surfaceWidth = r3;
        if (this.cameraThread != null) goto L16;
        if (r2 != null) goto L9;
        return;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("CameraView start create thread");
    L11:
        CameraGLThread r32 = new CameraGLThread(this, r2);
        this.cameraThread = r32;
        TextureView r22 = this.blurTextureView;
        if (r22 == null) goto L14;
        r32.setBlurSurfaceTexture(r22.getSurfaceTexture());
    L14:
        checkPreviewMatrix();
        return;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture r4) {
        CameraGLThread r42 = this.cameraThread;
        if (r42 == null) goto L5;
        r42.shutdown(0);
        this.cameraThread.postRunnable(new CameraView$$ExternalSyntheticLambda5(this));
    L5:
        CameraSessionWrapper r43 = this.cameraSession[0];
        if (r43 == null) goto L8;
        r43.destroy(true, null, null);
    L8:
        CameraSessionWrapper r3 = this.cameraSession[1];
        if (r3 == null) goto L11;
        r3.destroy(true, null, null);
    L11:
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        this.surfaceHeight = r3;
        this.surfaceWidth = r2;
        checkPreviewMatrix();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture r2) {
        if (this.inited == true) goto L8;
        CameraSessionWrapper r22 = this.cameraSession[0];
        if (r22 == null) goto L8;
        if (r22.isInitiated() == false) goto L8;
    L14:
        if (Looper.myLooper() == Looper.getMainLooper()) goto L17;
        AndroidUtilities.runOnUIThread(new CameraView$$ExternalSyntheticLambda6(this));
        return;
    L17:
        onSurfaceTextureUpdatedInternal();
        return;
    L8:
        Object r23 = this.renderNode;
        if (r23 != null) goto L11;
        return;
    L11:
        if (BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r23).hasDisplayList() == false) goto L14;
    }

    public void pauseAsTakingPicture() {
        CameraGLThread r2 = this.cameraThread;
        if (r2 == null) goto L6;
        r2.pause(600);
        return;
    }

    public void receivedAmplitude(double r1) {
    }

    public void resetCamera() {
        CameraSessionWrapper r0 = this.cameraSession[0];
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.cameraSessionRecording != r0) goto L7;
        this.cameraSessionRecording = null;
    L7:
        Handler r02 = this.cameraThread.getHandler();
        if (r02 == null) goto L10;
        CameraGLThread r2 = this.cameraThread;
        Objects.requireNonNull(r2);
        r2.sendMessage(r02.obtainMessage(11), 0);
    L10:
        this.cameraSession[0].destroy(false, null, new CameraView$$ExternalSyntheticLambda13(this));
        this.cameraSession[0] = null;
    }

    public void runHaptic() {
        long[] r1 = {0, 1};
        if (Build.VERSION.SDK_INT < 26) goto L10;
        Vibrator r4 = (Vibrator) getContext().getSystemService("vibrator");
        VibrationEffect r0 = VibrationEffect.createWaveform(r1, -1);
        r4.cancel();
        r4.vibrate(r0);
        return;
    L10:
        performHapticFeedback(3, 2);     // Catch: Exception -> L9
        return;
    }

    public void setClipBottom(int r1) {
        this.clipBottom = r1;
    }

    public void setClipTop(int r1) {
        this.clipTop = r1;
    }

    public void setDelegate(CameraViewDelegate r1) {
        this.delegate = r1;
    }

    public void setFpsLimit(int r1) {
        this.fpsLimit = r1;
    }

    public void setMirror(boolean r1) {
        this.mirror = r1;
    }

    public void setOptimizeForBarcode(boolean r1) {
        this.optimizeForBarcode = r1;
        CameraSessionWrapper r0 = this.cameraSession[0];
        if (r0 == null) goto L6;
        r0.setOptimizeForBarcode(true);
        return;
    }

    public void setRecordFile(File r1) {
        this.recordFile = r1;
    }

    public void setThumbDrawable(Drawable r3) {
        Drawable r0 = this.thumbDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.thumbDrawable = r3;
        if (r3 == null) goto L9;
        r3.setCallback(this);
    L9:
        if (this.firstFrameRendered == true) goto L12;
        this.blurredStubView.animate().setListener(null).cancel();
        this.blurredStubView.setBackground(this.thumbDrawable);
        this.blurredStubView.setAlpha(1.0f);
        this.blurredStubView.setVisibility(0);
        return;
    }

    public void setUseMaxPreview(boolean r1) {
        this.useMaxPreview = r1;
    }

    public void setZoom(float r2) {
        CameraSessionWrapper r1 = this.cameraSession[0];
        if (r1 == null) goto L6;
        r1.setZoom(r2);
        return;
    }

    public void showTexture(boolean r4, boolean r5) {
        if (this.textureView != null) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.textureViewAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.textureViewAnimator = null;
    L8:
        TextureView r02 = this.textureView;
        float r1 = 0.0f;
        if (r5 == false) goto L15;
        float r52 = r02.getAlpha();
        if (r4 == false) goto L13;
        r1 = 1.0f;
    L13:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r1});
        this.textureViewAnimator = r53;
        r53.addUpdateListener(new CameraView$$ExternalSyntheticLambda3(this));
        this.textureViewAnimator.addListener(new AnonymousClass3(this, r4));
        this.textureViewAnimator.start();
        return;
    L15:
        if (r4 == false) goto L17;
        r1 = 1.0f;
    L17:
        r02.setAlpha(r1);
    }

    public boolean square() {
        return false;
    }

    @Override // org.telegram.messenger.camera.CameraController.ICameraView
    public boolean startRecording(File r3, Runnable r4) {
        this.cameraSessionRecording = this.cameraSession[0];
        this.cameraThread.startRecording(r3);
        this.onRecordingFinishRunnable = r4;
        return true;
    }

    public void startSwitchingAnimation() {
        ValueAnimator r0 = this.flipAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.blurredStubView.animate().setListener(null).cancel();
        if (this.firstFrameRendered == false) goto L10;
        Bitmap r02 = this.textureView.getBitmap(100, 100);
        if (r02 == null) goto L10;
        Utilities.blurBitmap(r02, 3);
        this.blurredStubView.setBackground(new BitmapDrawable(r02));
    L10:
        this.blurredStubView.setAlpha(1.0f);
        this.blurredStubView.setVisibility(0);
        this.flipHalfReached = false;
        this.flipping = true;
        this.flipAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.textureView.setCameraDistance(r0.getMeasuredHeight() * 4.0f);
        this.blurredStubView.setCameraDistance(r0.getMeasuredHeight() * 4.0f);
        this.flipAnimator.addUpdateListener(new AnonymousClass1(this));
        this.flipAnimator.addListener(new AnonymousClass2(this));
        this.flipAnimator.setDuration(500);
        this.flipAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.flipAnimator.start();
        invalidate();
    }

    public void startTakePictureAnimation(boolean r2) {
        this.takePictureProgress = 0.0f;
        invalidate();
        if (r2 == false) goto L6;
        runHaptic();
        return;
    }

    @Override // org.telegram.messenger.camera.CameraController.ICameraView
    public void stopRecording() {
        this.cameraThread.stopRecording();
    }

    public void switchCamera() {
        if (this.flipping == false) goto L5;
        return;
    L5:
        if (System.currentTimeMillis() >= this.toggleDualUntil) goto L10;
        if (this.dualCameraAppeared == true) goto L10;
        return;
    L10:
        if (this.dual == true) goto L12;
        startSwitchingAnimation();
        CameraSessionWrapper r0 = this.cameraSession[0];
        if (r0 != null) goto L23;
    L26:
        this.isFrontface = !this.isFrontface;
        return;
    L23:
        if (this.cameraSessionRecording != r0) goto L25;
        this.cameraSessionRecording = null;
    L25:
        r0.destroy(false, null, new CameraView$$ExternalSyntheticLambda4(this));
        this.cameraSession[0] = null;
        goto L26
    L12:
        if (this.dualCameraAppeared == true) goto L14;
        return;
    L14:
        if ((System.currentTimeMillis() - this.lastDualSwitchTime) < 420) goto L32;
        this.lastDualSwitchTime = System.currentTimeMillis();
        CameraInfo[] r02 = this.info;
        CameraInfo r3 = r02[0];
        r02[0] = r02[1];
        r02[1] = r3;
        Size[] r03 = this.previewSize;
        Size r32 = r03[0];
        r03[0] = r03[1];
        r03[1] = r32;
        Size[] r04 = this.pictureSize;
        Size r33 = r04[0];
        r04[0] = r04[1];
        r04[1] = r33;
        CameraSessionWrapper[] r05 = this.cameraSession;
        CameraSessionWrapper r34 = r05[0];
        r05[0] = r05[1];
        r05[1] = r34;
        this.isFrontface = !this.isFrontface;
        Handler r06 = this.cameraThread.getHandler();
        if (r06 == null) goto L30;
        Objects.requireNonNull(this.cameraThread);
        r06.sendMessage(r06.obtainMessage(8));
        return;
    L30:
        return;
    }

    public void toggleDual() {
        toggleDual(false);
    }

    public void toggleDual(boolean r5) {
        if (r5 == false) goto L4;
    L14:
        addToDualWait(200);
        boolean r52 = this.dual;
        this.dual = !r52;
        CameraSessionWrapper[] r0 = this.cameraSession;
        if (r52 == true) goto L20;
        CameraSessionWrapper r53 = r0[0];
        if (r53 == null) goto L19;
        r53.setCurrentFlashMode("off");
    L19:
        enableDualInternal();
    L37:
        this.toggledDualAsSave = false;
        return;
    L20:
        CameraSessionWrapper r02 = r0[1];
        if (r02 != null) goto L23;
    L39:
        this.dual = true ^ this.dual;
        return;
    L23:
        if (r02.isInitiated() == false) goto L39;
        CameraSessionWrapper r03 = this.cameraSession[1];
        if (r03 == null) goto L31;
        this.closingDualCamera = true;
        if (this.cameraSessionRecording != r03) goto L30;
        this.cameraSessionRecording = null;
    L30:
        r03.destroy(false, null, new CameraView$$ExternalSyntheticLambda15(this));
        this.cameraSession[1] = null;
        this.previewSize[1] = null;
        this.pictureSize[1] = null;
        this.info[1] = null;
    L33:
        if (this.closingDualCamera == true) goto L37;
        Handler r54 = this.cameraThread.getHandler();
        if (r54 == null) goto L37;
        CameraGLThread r04 = this.cameraThread;
        Objects.requireNonNull(r04);
        r04.sendMessage(r54.obtainMessage(10), 0);
        goto L37
    L31:
        this.dualCameraAppeared = false;
        goto L33
    L4:
        if (this.flipping == false) goto L6;
        return;
    L6:
        if (this.closingDualCamera == false) goto L8;
        return;
    L8:
        if (System.currentTimeMillis() < this.toggleDualUntil) goto L12;
        if (this.dual == this.dualCameraAppeared) goto L14;
    L12:
        if (this.dual == true) goto L14;
    }

    public void unlistenDraw(Runnable r1) {
        this.invalidateListeners.remove(r1);
    }

    public void updateDualPosition() {
        CameraGLThread r0 = this.cameraThread;
        if (r0 == null) goto L10;
        Handler r02 = r0.getHandler();
        if (r02 == null) goto L9;
        CameraGLThread r1 = this.cameraThread;
        Objects.requireNonNull(r1);
        r1.sendMessage(r02.obtainMessage(7, this.dualMatrix), 0);
        return;
    L9:
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.thumbDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
