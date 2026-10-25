package org.telegram.ui.Components.spoilers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class SpoilerEffect2 {
    private static HashMap<Integer, SpoilerEffect2> instance;
    private final double MAX_DELTA;
    public final int MAX_FPS;
    private final double MIN_DELTA;
    private final Runnable checkDestroy;
    public boolean destroyed;
    private int height;
    private final ArrayList<View> holders;
    private int holdersIndex;
    private final HashMap<View, Integer> holdersToIndex;
    private final TextureView textureView;
    private final ViewGroup textureViewContainer;
    private SpoilerThread thread;
    public final int type;
    private int width;

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect2$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends FrameLayout {
        public AnonymousClass1(Context r1) {
            super(r1);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect2$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends TextureView {
        final /* synthetic */ SpoilerEffect2 this$0;

        public AnonymousClass2(SpoilerEffect2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(SpoilerEffect2.m13795$$Nest$fgetwidth(this.this$0), SpoilerEffect2.m13793$$Nest$fgetheight(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilerEffect2$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ SpoilerEffect2 this$0;

        public AnonymousClass3(SpoilerEffect2 r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r8, int r9, int r10) {
            if (SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0) != null) goto L6;
            SpoilerEffect2 r0 = this.this$0;
            SpoilerEffect2 r2 = this.this$0;
            SpoilerEffect2.m13796$$Nest$fputthread(r0, new SpoilerThread(r2, r8, r9, r10, new SpoilerEffect2$3$$ExternalSyntheticLambda0(r2)));
            SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0).start();
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
            if (SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0) == null) goto L7;
            SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0).halt();
            SpoilerEffect2.m13796$$Nest$fputthread(this.this$0, null);
            return true;
        L7:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0) == null) goto L6;
            SpoilerEffect2.m13794$$Nest$fgetthread(this.this$0).updateSize(r2, r3);
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    /* loaded from: classes7.dex */
    public class SpoilerThread extends Thread {
        private int currentBuffer;
        private int deltaTimeHandle;
        private int drawProgram;
        private EGL10 egl;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private int height;
        private final Runnable invalidate;
        private int particlesCount;
        private int[] particlesData;
        private volatile boolean paused;
        private final float radius;
        private int radiusHandle;
        private boolean reset;
        private int resetHandle;
        private boolean resize;
        private final Object resizeLock;
        private volatile boolean running;
        private int seedHandle;
        private int sizeHandle;
        private final SurfaceTexture surfaceTexture;
        private float t;
        final /* synthetic */ SpoilerEffect2 this$0;
        private int timeHandle;
        private final float timeScale;
        private int width;

        public SpoilerThread(SpoilerEffect2 r3, SurfaceTexture r4, int r5, int r6, Runnable r7) {
            this.this$0 = r3;
            this.running = true;
            this.paused = false;
            this.resizeLock = new Object();
            this.radius = AndroidUtilities.dpf2(1.2f);
            this.reset = true;
            this.currentBuffer = 0;
            this.timeScale = 0.65f;
            this.invalidate = r7;
            this.surfaceTexture = r4;
            this.width = r5;
            this.height = r6;
            this.particlesCount = particlesCount();
            setName("SpoilerThread");
        }

        private void checkGlErrors() {
        L2:
            int r2 = GLES20.glGetError();
            if (r2 == 0) goto L5;
            FileLog.e("spoiler gles error " + r2);
            goto L2
        }

        private void checkResize() {
            Object r0 = this.resizeLock;
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (this.resize == false) goto L12;
            GLES20.glUniform2f(this.sizeHandle, this.width, this.height);     // Catch: Throwable -> L9
            GLES20.glViewport(0, 0, this.width, this.height);     // Catch: Throwable -> L9
            int r1 = particlesCount();     // Catch: Throwable -> L9
            if (r1 <= this.particlesCount) goto L11;
            this.reset = true;     // Catch: Throwable -> L9
            genParticlesData();     // Catch: Throwable -> L9
        L11:
            this.particlesCount = r1;     // Catch: Throwable -> L9
            this.resize = false;     // Catch: Throwable -> L9
        L12:
            monitor-exit(r0);     // Catch: Throwable -> L9
        }

        private void die() {
            int[] r0 = this.particlesData;
            if (r0 != null) goto L41;
        L10:
            int r02 = this.drawProgram;
            if (r02 != 0) goto L45;
        L17:
            EGL10 r03 = this.egl;
            if (r03 == null) goto L47;
            EGLDisplay r1 = this.eglDisplay;     // Catch: Exception -> L21
            EGLSurface r2 = EGL10.EGL_NO_SURFACE;     // Catch: Exception -> L21
            r03.eglMakeCurrent(r1, r2, r2, EGL10.EGL_NO_CONTEXT);     // Catch: Exception -> L21
        L39:
            this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);     // Catch: Exception -> L25
        L43:
            this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);     // Catch: Exception -> L29
        L29:
            e = move-exception;
            FileLog.e(e);
        L25:
            e = move-exception;
            FileLog.e(e);
        L21:
            e = move-exception;
            FileLog.e(e);
        L47:
            this.surfaceTexture.release();     // Catch: Exception -> L33
        L35:
            checkGlErrors();
            return;
        L33:
            e = move-exception;
            FileLog.e(e);
            goto L35
        L45:
            GLES20.glDeleteProgram(r02);     // Catch: Exception -> L14
        L16:
            this.drawProgram = 0;
        L14:
            e = move-exception;
            FileLog.e(e);
            goto L16
        L41:
            GLES20.glDeleteBuffers(2, r0, 0);     // Catch: Exception -> L7
        L9:
            this.particlesData = null;
        L7:
            e = move-exception;
            FileLog.e(e);
            goto L9
        }

        private void drawFrame(float r22) {
            if (this.eglContext.equals(this.egl.eglGetCurrentContext()) == true) goto L5;
        L6:
            EGL10 r1 = this.egl;
            EGLDisplay r3 = this.eglDisplay;
            EGLSurface r4 = this.eglSurface;
            if (r1.eglMakeCurrent(r3, r4, r4, this.eglContext) == true) goto L10;
            this.running = false;
            return;
        L10:
            float r32 = 0.65f * r22;
            float r12 = this.t + r32;
            this.t = r12;
            if (r12 <= 1000.0f) goto L13;
            this.t = 0.0f;
        L13:
            GLES20.glClear(16384);
            GLES20.glBindBuffer(34962, this.particlesData[this.currentBuffer]);
            GLES20.glVertexAttribPointer(0, 2, 5126, false, 24, 0);
            GLES20.glEnableVertexAttribArray(0);
            GLES20.glVertexAttribPointer(1, 2, 5126, false, 24, 8);
            GLES20.glEnableVertexAttribArray(1);
            GLES20.glVertexAttribPointer(2, 1, 5126, false, 24, 16);
            GLES20.glEnableVertexAttribArray(2);
            GLES20.glVertexAttribPointer(3, 1, 5126, false, 24, 20);
            GLES20.glEnableVertexAttribArray(3);
            GLES30.glBindBufferBase(35982, 0, this.particlesData[1 - this.currentBuffer]);
            GLES20.glVertexAttribPointer(0, 2, 5126, false, 24, 0);
            GLES20.glEnableVertexAttribArray(0);
            GLES20.glVertexAttribPointer(1, 2, 5126, false, 24, 8);
            GLES20.glEnableVertexAttribArray(1);
            GLES20.glVertexAttribPointer(2, 1, 5126, false, 24, 16);
            GLES20.glEnableVertexAttribArray(2);
            GLES20.glVertexAttribPointer(3, 1, 5126, false, 24, 20);
            GLES20.glEnableVertexAttribArray(3);
            GLES20.glUniform1f(this.timeHandle, this.t);
            GLES20.glUniform1f(this.deltaTimeHandle, r32);
            GLES30.glBeginTransformFeedback(0);
            GLES20.glDrawArrays(0, 0, this.particlesCount);
            GLES30.glEndTransformFeedback();
            if (this.reset == false) goto L16;
            this.reset = false;
            GLES20.glUniform1f(this.resetHandle, 0.0f);
        L16:
            this.currentBuffer = 1 - this.currentBuffer;
            this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            checkGlErrors();
            return;
        L5:
            if (this.eglSurface.equals(this.egl.eglGetCurrentSurface(12377)) == true) goto L10;
            goto L6
        }

        private void genParticlesData() {
            int[] r0 = this.particlesData;
            int r1 = 0;
            if (r0 == null) goto L5;
            GLES20.glDeleteBuffers(2, r0, 0);
        L5:
            int[] r02 = new int[2];
            this.particlesData = r02;
            GLES20.glGenBuffers(2, r02, 0);
        L6:
            if (r1 >= 2) goto L8;
            GLES20.glBindBuffer(34962, this.particlesData[r1]);
            GLES20.glBufferData(34962, this.particlesCount * 24, null, 35048);
            r1 = r1 + 1;
            goto L6
        L8:
            checkGlErrors();
        }

        private void init() {
            EGL10 r0 = (EGL10) EGLContext.getEGL();
            this.egl = r0;
            EGLDisplay r02 = r0.eglGetDisplay(0);
            this.eglDisplay = r02;
            if (r02 != EGL10.EGL_NO_DISPLAY) goto L7;
            this.running = false;
            return;
        L7:
            if (this.egl.eglInitialize(r02, new int[2]) == true) goto L10;
            this.running = false;
            return;
        L10:
            EGLConfig[] r5 = new EGLConfig[1];
            if (this.egl.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 64, 12344}, r5, 1, new int[1]) == true) goto L14;
            this.running = false;
            return;
        L14:
            EGLConfig r2 = r5[0];
            this.eglConfig = r2;
            EGLContext r22 = this.egl.eglCreateContext(this.eglDisplay, r2, EGL10.EGL_NO_CONTEXT, new int[]{12440, 3, 12344});
            this.eglContext = r22;
            if (r22 != null) goto L18;
            this.running = false;
            return;
        L18:
            EGLSurface r23 = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surfaceTexture, null);
            this.eglSurface = r23;
            if (r23 != null) goto L23;
            this.running = false;
            return;
        L23:
            if (this.egl.eglMakeCurrent(this.eglDisplay, r23, r23, this.eglContext) == true) goto L26;
            this.running = false;
            return;
        L26:
            genParticlesData();
            int r24 = GLES20.glCreateShader(35633);
            int r3 = GLES20.glCreateShader(35632);
            if (r24 == 0) goto L51;
            if (r3 == 0) goto L51;
            GLES20.glShaderSource(r24, AndroidUtilities.readRes(R.raw.spoiler_vertex));
            GLES20.glCompileShader(r24);
            int[] r03 = new int[1];
            GLES20.glGetShaderiv(r24, 35713, r03, 0);
            if (r03[0] != 0) goto L34;
            FileLog.e("SpoilerEffect2, compile vertex shader error: " + GLES20.glGetShaderInfoLog(r24));
            GLES20.glDeleteShader(r24);
            this.running = false;
            return;
        L34:
            GLES20.glShaderSource(r3, AndroidUtilities.readRes(R.raw.spoiler_fragment));
            GLES20.glCompileShader(r3);
            GLES20.glGetShaderiv(r3, 35713, r03, 0);
            if (r03[0] != 0) goto L38;
            FileLog.e("SpoilerEffect2, compile fragment shader error: " + GLES20.glGetShaderInfoLog(r3));
            GLES20.glDeleteShader(r3);
            this.running = false;
            return;
        L38:
            int r4 = GLES20.glCreateProgram();
            this.drawProgram = r4;
            if (r4 != 0) goto L42;
            this.running = false;
            return;
        L42:
            GLES20.glAttachShader(r4, r24);
            GLES20.glAttachShader(this.drawProgram, r3);
            GLES30.glTransformFeedbackVaryings(this.drawProgram, new String[]{"outPosition", "outVelocity", "outTime", "outDuration"}, 35980);
            GLES20.glLinkProgram(this.drawProgram);
            GLES20.glGetProgramiv(this.drawProgram, 35714, r03, 0);
            int r04 = r03[0];
            int r25 = this.drawProgram;
            if (r04 != 0) goto L46;
            FileLog.e("SpoilerEffect2, link draw program error: " + GLES20.glGetProgramInfoLog(r25));
            this.running = false;
            return;
        L46:
            this.resetHandle = GLES20.glGetUniformLocation(r25, "reset");
            this.timeHandle = GLES20.glGetUniformLocation(this.drawProgram, "time");
            this.deltaTimeHandle = GLES20.glGetUniformLocation(this.drawProgram, "deltaTime");
            this.sizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "size");
            this.radiusHandle = GLES20.glGetUniformLocation(this.drawProgram, "r");
            this.seedHandle = GLES20.glGetUniformLocation(this.drawProgram, "seed");
            GLES20.glViewport(0, 0, this.width, this.height);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(770, 771);
            float r05 = 0.0f;
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glUniform2f(this.sizeHandle, this.width, this.height);
            int r1 = this.resetHandle;
            if (this.reset == false) goto L49;
            r05 = 1.0f;
        L49:
            GLES20.glUniform1f(r1, r05);
            GLES20.glUniform1f(this.radiusHandle, this.radius);
            GLES20.glUniform1f(this.seedHandle, Utilities.fastRandom.nextInt(256) / 256.0f);
            return;
        L51:
            this.running = false;
        }

        private int particlesCount() {
            return (int) Utilities.clamp(((this.width * this.height) / 250000.0f) * 1000.0f, 10000.0f, 500.0f);
        }

        public void halt() {
            this.running = false;
        }

        public void pause(boolean r1) {
            this.paused = r1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            init();
            long r0 = System.nanoTime();
        L4:
            if (this.running == false) goto L19;
            long r2 = System.nanoTime();
            double r02 = (r2 - r0) / 1.0E9d;
            double r6 = SpoilerEffect2.m13792$$Nest$fgetMIN_DELTA(this.this$0);
            SpoilerEffect2 r7 = this.this$0;
            if (r02 >= r6) goto L11;
            double r62 = SpoilerEffect2.m13792$$Nest$fgetMIN_DELTA(r7) - r02;
            long r8 = (long) (r62 * 1000.0d);
            Thread.sleep(r8, (int) ((r62 - (r8 / 1000.0d)) * 1.0E9d));     // Catch: Exception -> L21
        L9:
            r02 = SpoilerEffect2.m13792$$Nest$fgetMIN_DELTA(this.this$0);
        L14:
            if (this.paused == false) goto L18;
            Thread.sleep(1000);     // Catch: Exception -> L22
            goto L14
        L18:
            checkResize();
            drawFrame((float) r02);
            AndroidUtilities.cancelRunOnUIThread(this.invalidate);
            AndroidUtilities.runOnUIThread(this.invalidate);
            r0 = r2;
            goto L4
        L11:
            if (r02 <= SpoilerEffect2.m13791$$Nest$fgetMAX_DELTA(r7)) goto L14;
            r02 = SpoilerEffect2.m13791$$Nest$fgetMAX_DELTA(this.this$0);
            goto L14
        L19:
            die();
        }

        public void updateSize(int r3, int r4) {
            Object r0 = this.resizeLock;
            monitor-enter(r0);
            this.resize = true;     // Catch: Throwable -> L8
            this.width = r3;     // Catch: Throwable -> L8
            this.height = r4;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            throw th;
        }
    }

    public static /* synthetic */ void $r8$lambda$W0ygnwTbZYEue2FpSY5CvKTMZb8(SpoilerEffect2 r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetMAX_DELTA, reason: not valid java name */
    public static /* bridge */ /* synthetic */ double m13791$$Nest$fgetMAX_DELTA(SpoilerEffect2 r2) {
        return r2.MAX_DELTA;
    }

    /* renamed from: -$$Nest$fgetMIN_DELTA, reason: not valid java name */
    public static /* bridge */ /* synthetic */ double m13792$$Nest$fgetMIN_DELTA(SpoilerEffect2 r2) {
        return r2.MIN_DELTA;
    }

    /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13793$$Nest$fgetheight(SpoilerEffect2 r0) {
        return r0.height;
    }

    /* renamed from: -$$Nest$fgetthread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerThread m13794$$Nest$fgetthread(SpoilerEffect2 r0) {
        return r0.thread;
    }

    /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13795$$Nest$fgetwidth(SpoilerEffect2 r0) {
        return r0.width;
    }

    /* renamed from: -$$Nest$fputthread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13796$$Nest$fputthread(SpoilerEffect2 r0, SpoilerThread r1) {
        r0.thread = r1;
    }

    private SpoilerEffect2(int r7, ViewGroup r8, int r9, int r10) {
        this.holders = new ArrayList();
        this.holdersToIndex = new HashMap();
        this.holdersIndex = 0;
        this.checkDestroy = new SpoilerEffect2$$ExternalSyntheticLambda0(this);
        int r1 = (int) AndroidUtilities.screenRefreshRate;
        this.MAX_FPS = r1;
        double r2 = 1.0d / r1;
        this.MIN_DELTA = r2;
        this.MAX_DELTA = r2 * 4.0d;
        this.type = r7;
        this.width = r9;
        this.height = r10;
        this.textureViewContainer = r8;
        AnonymousClass2 r72 = new AnonymousClass2(this, r8.getContext());
        this.textureView = r72;
        r72.setSurfaceTextureListener(new AnonymousClass3(this));
        r72.setOpaque(false);
        r8.addView(r72);
    }

    private void destroy() {
        this.destroyed = true;
        instance = null;
        SpoilerThread r1 = this.thread;
        if (r1 == null) goto L5;
        r1.halt();
        this.thread = null;
    L5:
        this.textureViewContainer.removeView(this.textureView);
        if ((this.textureViewContainer.getParent() instanceof ViewGroup) == false) goto L9;
        ((ViewGroup) this.textureViewContainer.getParent()).removeView(this.textureViewContainer);
        return;
    }

    public static SpoilerEffect2 getInstance(int r1, View r2) {
        return getInstance(r1, r2, getRootView(r2));
    }

    public static SpoilerEffect2 getInstance(int r4, View r5, ViewGroup r6) {
        if (r5 != null) goto L5;
    L18:
        return null;
    L5:
        if (supports() == false) goto L18;
        if (instance != null) goto L10;
        instance = new HashMap();
    L10:
        SpoilerEffect2 r1 = instance.get(Integer.valueOf(r4));
        if (r1 != null) goto L16;
        int r12 = getSize();
        if (r6 != null) goto L15;
        return null;
    L15:
        HashMap<Integer, SpoilerEffect2> r0 = instance;
        Integer r2 = Integer.valueOf(r4);
        SpoilerEffect2 r3 = new SpoilerEffect2(r4, makeTextureViewContainer(r6), r12, r12);
        r0.put(r2, r3);
        r1 = r3;
    L16:
        r1.attach(r5);
        return r1;
    }

    public static SpoilerEffect2 getInstance(View r1) {
        return getInstance(0, r1);
    }

    private static ViewGroup getRootView(View r2) {
        Activity r22 = AndroidUtilities.findActivity(r2.getContext());
        if (r22 != null) goto L5;
        return null;
    L5:
        View r23 = r22.findViewById(android.R.id.content).getRootView();
        if ((r23 instanceof ViewGroup) == true) goto L9;
        return null;
    L9:
        return (ViewGroup) r23;
    }

    private static int getSize() {
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 != 1) goto L5;
        Point r02 = AndroidUtilities.displaySize;
        return Math.min(900, (int) (((r02.x + r02.y) / 2.0f) * 0.8f));
    L5:
        if (r0 == 2) goto L8;
        Point r03 = AndroidUtilities.displaySize;
        return Math.min(720, (int) (((r03.x + r03.y) / 2.0f) * 0.7f));
    L8:
        Point r04 = AndroidUtilities.displaySize;
        return Math.min(1280, (int) (((r04.x + r04.y) / 2.0f) * 1.0f));
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.holders.isEmpty() == false) goto L6;
        destroy();
        return;
    }

    private static FrameLayout makeTextureViewContainer(ViewGroup r2) {
        AnonymousClass1 r0 = new AnonymousClass1(r2.getContext());
        r2.addView(r0);
        return r0;
    }

    public static void pause(int r3, boolean r4) {
        HashMap<Integer, SpoilerEffect2> r0 = instance;
        if (r0 == null) goto L13;
        Iterator<SpoilerEffect2> r02 = r0.values().iterator();
    L7:
        if (r02.hasNext() == false) goto L21;
        SpoilerEffect2 r1 = r02.next();
        if (r1.type != r3) goto L7;
        SpoilerThread r12 = r1.thread;
        if (r12 == null) goto L7;
        r12.pause(r4);
        goto L7
    L21:
        return;
    }

    public static void pause(boolean r2) {
        HashMap<Integer, SpoilerEffect2> r0 = instance;
        if (r0 == null) goto L11;
        Iterator<SpoilerEffect2> r02 = r0.values().iterator();
    L7:
        if (r02.hasNext() == false) goto L17;
        SpoilerThread r1 = r02.next().thread;
        if (r1 == null) goto L7;
        r1.pause(r2);
        goto L7
    L17:
        return;
    }

    public static boolean supports() {
        return true;
    }

    public void attach(View r4) {
        if (this.destroyed == false) goto L6;
        return;
    L6:
        if (this.holders.contains(r4) == true) goto L9;
        this.holders.add(r4);
        HashMap<View, Integer> r0 = this.holdersToIndex;
        int r1 = this.holdersIndex;
        this.holdersIndex = r1 + 1;
        r0.put(r4, Integer.valueOf(r1));
        return;
    }

    public void detach(View r3) {
        this.holders.remove(r3);
        this.holdersToIndex.remove(r3);
        if (this.destroyed == true) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.checkDestroy);
        AndroidUtilities.runOnUIThread(this.checkDestroy, 30);
        return;
    }

    public void draw(Canvas r7, View r8, int r9, int r10) {
        draw(r7, r8, r9, r10, 1.0f);
    }

    public void draw(Canvas r8, View r9, int r10, int r11, float r12) {
        draw(r8, r9, r10, r11, r12, false);
    }

    public void draw(Canvas r6, View r7, int r8, int r9, float r10, boolean r11) {
        if (r6 == null) goto L28;
        if (r7 == null) goto L29;
        r6.save();
        int r0 = this.width;
        int r1 = this.height;
        Integer r72 = this.holdersToIndex.get(r7);
        if (r72 != null) goto L8;
        r72 = 0;
    L8:
        if (r8 > r0) goto L10;
        if (r9 > r1) goto L10;
    L12:
        if ((r72.intValue() % 4) != 1) goto L15;
        r6.rotate(180.0f, r0 / 2.0f, r1 / 2.0f);
    L15:
        if ((r72.intValue() % 4) != 2) goto L18;
        r6.scale(-1.0f, 1.0f, r0 / 2.0f, r1 / 2.0f);
    L18:
        if ((r72.intValue() % 4) != 3) goto L20;
        r6.scale(1.0f, -1.0f, r0 / 2.0f, r1 / 2.0f);
    L20:
        TextureView r73 = this.textureView;
        if (r11 == false) goto L25;
        Bitmap r5 = r73.getBitmap();
        if (r5 == null) goto L26;
        Paint r74 = new Paint(7);
        r74.setColor(-1);
        r6.drawBitmap(r5, 0.0f, 0.0f, r74);
        r5.recycle();
    L26:
        r6.restore();
        return;
    L25:
        r73.setAlpha(r10);
        this.textureView.draw(r6);
    L10:
        float r82 = Math.max(r8 / r0, r9 / r1);
        r6.scale(r82, r82);
        goto L12
    L29:
        return;
    }

    public int getAttachIndex(View r1) {
        Integer r0 = this.holdersToIndex.get(r1);
        if (r0 != null) goto L6;
        r0 = 0;
    L6:
        return r0.intValue();
    }

    public void invalidate() {
        int r0 = 0;
    L4:
        if (r0 >= this.holders.size()) goto L6;
        this.holders.get(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    public void reassignAttach(View r1, int r2) {
        this.holdersToIndex.put(r1, Integer.valueOf(r2));
    }
}
