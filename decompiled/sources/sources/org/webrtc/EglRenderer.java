package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.FileLog;
import org.webrtc.EglBase;
import org.webrtc.GlGenericDrawer;
import org.webrtc.GlUtil;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public class EglRenderer implements VideoSink {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final String TAG = "EglRenderer";
    private final GlTextureFrameBuffer bitmapTextureFramebuffer;
    private final Matrix drawMatrix;
    private RendererCommon.GlDrawer drawer;
    private EglBase eglBase;
    private final EglSurfaceCreation eglSurfaceBackgroundCreationRunnable;
    private final EglSurfaceCreation eglSurfaceCreationRunnable;
    private volatile ErrorCallback errorCallback;
    public boolean firstFrameRendered;
    private final Object fpsReductionLock;
    private final VideoFrameDrawer frameDrawer;
    private final ArrayList<FrameListenerAndParams> frameListeners;
    private final Object frameLock;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private final Object handlerLock;
    private float layoutAspectRatio;
    private final Object layoutLock;
    private long minRenderPeriodNs;
    private boolean mirrorHorizontally;
    private boolean mirrorVertically;
    protected final String name;
    private long nextFrameTimeNs;
    private VideoFrame pendingFrame;
    private long renderSwapBufferTimeNs;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private int rotation;
    private boolean usePresentationTimeStamp;

    /* renamed from: org.webrtc.EglRenderer$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ EglRenderer this$0;

        public AnonymousClass1(EglRenderer r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object r0 = EglRenderer.m22352$$Nest$fgethandlerLock(this.this$0);
            monitor-enter(r0);
            EglRenderer.m22353$$Nest$fputrenderThreadHandler(this.this$0, null);     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    public class EglSurfaceCreation implements Runnable {
        private final boolean background;
        private Object surface;
        final /* synthetic */ EglRenderer this$0;

        public EglSurfaceCreation(EglRenderer r1, boolean r2) {
            this.this$0 = r1;
            this.background = r2;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            monitor-enter(this);
        L14:
            th = move-exception;
            throw th;
        L5:
            if (this.surface != null) goto L7;
        L37:
            monitor-exit(this);
            return;
        L7:
            if (EglRenderer.m22351$$Nest$fgeteglBase(this.this$0) == null) goto L37;
            boolean r1 = this.background;     // Catch: Throwable -> L14
            EglRenderer r2 = this.this$0;
            if (r1 == false) goto L17;
            if (EglRenderer.m22351$$Nest$fgeteglBase(r2).hasBackgroundSurface() == true) goto L37;
        L18:
            Object r12 = this.surface;     // Catch: Throwable -> L14
            if ((r12 instanceof Surface) == false) goto L22;
            EglRenderer.m22351$$Nest$fgeteglBase(this.this$0).createSurface((Surface) this.surface);     // Catch: Throwable -> L14
        L28:
            boolean r0 = this.background;     // Catch: Throwable -> L14
            EglRenderer r13 = this.this$0;
            if (r0 == true) goto L32;
            EglRenderer.m22351$$Nest$fgeteglBase(r13).makeCurrent();     // Catch: Throwable -> L14
            GLES20.glPixelStorei(3317, 1);     // Catch: Throwable -> L14
            goto L37
        L32:
            EglRenderer.m22351$$Nest$fgeteglBase(r13).makeBackgroundCurrent();     // Catch: Throwable -> L14
            GLES20.glPixelStorei(3317, 1);     // Catch: Throwable -> L14
            if (EglRenderer.m22351$$Nest$fgeteglBase(this.this$0).hasSurface() == false) goto L37;
            EglRenderer.m22351$$Nest$fgeteglBase(this.this$0).makeCurrent();     // Catch: Throwable -> L14
            goto L37
        L22:
            if ((r12 instanceof SurfaceTexture) == false) goto L36;
            boolean r02 = this.background;     // Catch: Throwable -> L14
            EglRenderer r14 = this.this$0;
            if (r02 == false) goto L27;
            EglRenderer.m22351$$Nest$fgeteglBase(r14).createBackgroundSurface((SurfaceTexture) this.surface);     // Catch: Throwable -> L14
            goto L28
        L27:
            EglRenderer.m22351$$Nest$fgeteglBase(r14).createSurface((SurfaceTexture) this.surface);     // Catch: Throwable -> L14
            goto L28
        L36:
            throw new IllegalStateException("Invalid surface: " + this.surface);     // Catch: Throwable -> L14
        L17:
            if (EglRenderer.m22351$$Nest$fgeteglBase(r2).hasSurface() == true) goto L37;
            goto L18
        }

        public synchronized void setSurface(Object r1) {
            monitor-enter(this);
            this.surface = r1;     // Catch: Throwable -> L6
            monitor-exit(this);
            return;
        L6:
            th = move-exception;
            throw th;
        }
    }

    public interface ErrorCallback {
        void onGlOutOfMemory();
    }

    public interface FrameListener {
        void onFrame(Bitmap r1);
    }

    public static class FrameListenerAndParams {
        public final boolean applyFpsReduction;
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener r1, float r2, RendererCommon.GlDrawer r3, boolean r4) {
            this.listener = r1;
            this.scale = r2;
            this.drawer = r3;
            this.applyFpsReduction = r4;
        }
    }

    public static class HandlerWithExceptionCallback extends Handler {
        private final Runnable exceptionCallback;

        public HandlerWithExceptionCallback(Looper r1, Runnable r2) {
            super(r1);
            this.exceptionCallback = r2;
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message r3) {
            super.dispatchMessage(r3);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            Logging.e(EglRenderer.TAG, "Exception on EglRenderer thread", e);
            this.exceptionCallback.run();
            throw e;
        }
    }

    /* renamed from: $r8$lambda$6PYfgv7vnZUWWy1DQ9BQ-XmOTJk, reason: not valid java name */
    public static /* synthetic */ void m22347$r8$lambda$6PYfgv7vnZUWWy1DQ9BQXmOTJk(EglRenderer r0, CountDownLatch r1) {
        r0.lambda$release$1(r1);
    }

    /* renamed from: $r8$lambda$6zQKi9G9nLejSMEVmPicerC1-ZA, reason: not valid java name */
    public static /* synthetic */ void m22348$r8$lambda$6zQKi9G9nLejSMEVmPicerC1ZA(EglRenderer r0, EglBase.Context r1, int[] r2) {
        r0.lambda$init$0(r1, r2);
    }

    /* renamed from: $r8$lambda$8932XXZbKVA3JH5mn-B48aUTtcA, reason: not valid java name */
    public static /* synthetic */ void m22349$r8$lambda$8932XXZbKVA3JH5mnB48aUTtcA(EglRenderer r0, boolean r1, Runnable r2) {
        r0.lambda$releaseEglSurface$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8ptpXprPQ1TTclYjTSMzvC_VkXA(EglRenderer r0, CountDownLatch r1, FrameListener r2) {
        r0.lambda$removeFrameListener$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MGK9OG9g3ZveuXxv0Iy_fAM_8Co(EglRenderer r0, Looper r1) {
        r0.lambda$release$2(r1);
    }

    /* renamed from: $r8$lambda$OR4UU85Fqg-AKkRX2QkUk-Q6Mkw, reason: not valid java name */
    public static /* synthetic */ void m22350$r8$lambda$OR4UU85FqgAKkRX2QkUkQ6Mkw(EglRenderer r0, float r1, float r2, float r3, float r4) {
        r0.lambda$clearImage$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$fnPy0w6bq9SUMTUHvo_IKgYYuj0(EglRenderer r0, GlGenericDrawer.TextureCallback r1) {
        r0.lambda$getTexture$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$mq7kxdV_YKwOjSHqancIuN31nls(EglRenderer r0) {
        r0.renderFrameOnRenderThread();
    }

    public static /* synthetic */ void $r8$lambda$p4pBsZz9unlJCHmOkWZ3krSu6ho(EglRenderer r0, RendererCommon.GlDrawer r1, FrameListener r2, float r3, boolean r4) {
        r0.lambda$addFrameListener$3(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgeteglBase, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EglBase m22351$$Nest$fgeteglBase(EglRenderer r0) {
        return r0.eglBase;
    }

    /* renamed from: -$$Nest$fgethandlerLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m22352$$Nest$fgethandlerLock(EglRenderer r0) {
        return r0.handlerLock;
    }

    /* renamed from: -$$Nest$fputrenderThreadHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22353$$Nest$fputrenderThreadHandler(EglRenderer r0, Handler r1) {
        r0.renderThreadHandler = r1;
    }

    public EglRenderer(String r2) {
        this(r2, new VideoFrameDrawer());
    }

    public EglRenderer(String r3, VideoFrameDrawer r4) {
        this.handlerLock = new Object();
        this.frameListeners = new ArrayList();
        this.fpsReductionLock = new Object();
        this.drawMatrix = new Matrix();
        this.frameLock = new Object();
        this.layoutLock = new Object();
        this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
        this.eglSurfaceCreationRunnable = new EglSurfaceCreation(this, false);
        this.eglSurfaceBackgroundCreationRunnable = new EglSurfaceCreation(this, true);
        this.name = r3;
        this.frameDrawer = r4;
    }

    private void clearSurfaceOnRenderThread(float r4, float r5, float r6, float r7) {
        EglBase r0 = this.eglBase;
        if (r0 != null) goto L5;
    L7:
        EglBase r02 = this.eglBase;
        if (r02 != null) goto L10;
        return;
    L10:
        if (r02.hasBackgroundSurface() == false) goto L14;
        this.eglBase.makeBackgroundCurrent();
        logD("clearSurface in background");
        GLES20.glClearColor(r4, r5, r6, r7);
        GLES20.glClear(16384);
        this.eglBase.swapBuffers(true);
        this.eglBase.makeCurrent();
        return;
    L14:
        return;
    L5:
        if (r0.hasSurface() == false) goto L7;
        logD("clearSurface");
        GLES20.glClearColor(r4, r5, r6, r7);
        GLES20.glClear(16384);
        this.eglBase.swapBuffers(false);
        goto L7
    }

    private void createEglSurfaceInternal(Object r1, boolean r2) {
        if (r2 == false) goto L15;
        this.eglSurfaceBackgroundCreationRunnable.setSurface(r1);
        Object r22 = this.handlerLock;
        monitor-enter(r22);
        Handler r12 = this.renderThreadHandler;     // Catch: Throwable -> L8
        if (r12 == null) goto L10;
        r12.post(this.eglSurfaceBackgroundCreationRunnable);     // Catch: Throwable -> L8
    L11:
        monitor-exit(r22);     // Catch: Throwable -> L8
        return;
    L10:
        FileLog.d("can't create background surface. render thread is null");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    L15:
        this.eglSurfaceCreationRunnable.setSurface(r1);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    private /* synthetic */ void lambda$addFrameListener$3(RendererCommon.GlDrawer r2, FrameListener r3, float r4, boolean r5) {
        if (r2 != null) goto L4;
        r2 = this.drawer;
    L4:
        this.frameListeners.add(new FrameListenerAndParams(r3, r4, r2, r5));
    }

    private /* synthetic */ void lambda$clearImage$6(float r1, float r2, float r3, float r4) {
        clearSurfaceOnRenderThread(r1, r2, r3, r4);
    }

    private /* synthetic */ void lambda$getTexture$7(GlGenericDrawer.TextureCallback r3) {
        this.frameDrawer.getRenderBufferBitmap(this.drawer, this.rotation, r3);
    }

    private /* synthetic */ void lambda$init$0(EglBase.Context r2, int[] r3) {
        if (r2 != null) goto L5;
        logD("EglBase10.create context");
        this.eglBase = EglBase.createEgl10(r3);
        return;
    L5:
        logD("EglBase.create shared context");
        this.eglBase = EglBase.create(r2, r3);
    }

    private /* synthetic */ void lambda$release$1(CountDownLatch r3) {
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        GLES20.glUseProgram(0);     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        RendererCommon.GlDrawer r02 = this.drawer;
        if (r02 == null) goto L10;
        r02.release();
        this.drawer = null;
    L10:
        this.frameDrawer.release();
        this.bitmapTextureFramebuffer.release();
        if (this.eglBase == null) goto L13;
        logD("eglBase detach and release.");
        this.eglBase.detachCurrent();
        this.eglBase.release();
        this.eglBase = null;
    L13:
        this.frameListeners.clear();
        r3.countDown();
        return;
    L15:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$release$2(Looper r2) {
        logD("Quitting render thread.");
        r2.quit();
    }

    private /* synthetic */ void lambda$releaseEglSurface$5(boolean r2, Runnable r3) {
        EglBase r0 = this.eglBase;
        if (r0 == null) goto L5;
        r0.detachCurrent();
        this.eglBase.releaseSurface(r2);
    L5:
        if (r3 == null) goto L8;
        r3.run();
        return;
    }

    private /* synthetic */ void lambda$removeFrameListener$4(CountDownLatch r1, FrameListener r2) {
        r1.countDown();
        Iterator<FrameListenerAndParams> r0 = this.frameListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        if (r0.next().listener != r2) goto L4;
        r0.remove();
        goto L4
    }

    private void logD(String r2) {
        Logging.d(TAG, this.name + r2);
    }

    private void logE(String r2, Throwable r3) {
        Logging.e(TAG, this.name + r2, r3);
    }

    private void logW(String r2) {
        Logging.w(TAG, this.name + r2);
    }

    private void notifyCallbacks(VideoFrame r17, boolean r18) {
        if (this.frameListeners.isEmpty() == true) goto L27;
        this.drawMatrix.reset();
        this.drawMatrix.preTranslate(0.5f, 0.5f);
        this.drawMatrix.preRotate(this.rotation);
        Matrix r1 = this.drawMatrix;
        if (this.mirrorHorizontally == false) goto L8;
        float r2 = -1.0f;
    L10:
        if (this.mirrorVertically == false) goto L12;
        float r5 = -1.0f;
    L13:
        r1.preScale(r2, r5);
        this.drawMatrix.preScale(1.0f, -1.0f);
        this.drawMatrix.preTranslate(-0.5f, -0.5f);
        Iterator<FrameListenerAndParams> r12 = this.frameListeners.iterator();
    L15:
        if (r12.hasNext() == false) goto L38;
        FrameListenerAndParams r22 = r12.next();
        if (r18 == true) goto L21;
        if (r22.applyFpsReduction == true) goto L15;
    L21:
        r12.remove();
        int r6 = (int) (r22.scale * r17.getRotatedWidth());
        int r7 = (int) (r22.scale * r17.getRotatedHeight());
        if (r6 == 0) goto L26;
        if (r7 == 0) goto L26;
        this.bitmapTextureFramebuffer.setSize(r6, r7);
        GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        this.frameDrawer.drawFrame(r17, r22.drawer, this.drawMatrix, 0, 0, r6, r7, false, false);
        ByteBuffer r3 = ByteBuffer.allocateDirect((r6 * r7) * 4);
        GLES20.glViewport(0, 0, r6, r7);
        GLES20.glReadPixels(0, 0, r6, r7, 6408, 5121, r3);
        GLES20.glBindFramebuffer(36160, 0);
        GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
        Bitmap r4 = Bitmap.createBitmap(r6, r7, Bitmap.Config.ARGB_8888);
        r4.copyPixelsFromBuffer(r3);
        r22.listener.onFrame(r4);
    L26:
        r22.listener.onFrame(null);
        goto L15
    L38:
        return;
    L12:
        r5 = 1.0f;
        goto L13
    L8:
        r2 = 1.0f;
        goto L10
    }

    private void postToRenderThread(Runnable r2) {
        Object r0 = this.handlerLock;
        monitor-enter(r0);
        Handler r1 = this.renderThreadHandler;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.post(r2);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void renderFrameOnRenderThread() {
        Object r1 = this.frameLock;
        monitor-enter(r1);
        VideoFrame r3 = this.pendingFrame;     // Catch: Throwable -> L8
        if (r3 != null) goto L10;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L10:
        this.pendingFrame = null;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        EglBase r0 = this.eglBase;
        if (r0 != null) goto L15;
    L103:
        logD("Dropping frame - No surface");
        r3.release();
        return;
    L15:
        if (r0.hasSurface() == false) goto L103;
        Object r12 = this.fpsReductionLock;
        monitor-enter(r12);
        long r4 = this.minRenderPeriodNs;     // Catch: Throwable -> L99
        if (r4 != LongCompanionObject.MAX_VALUE) goto L23;
    L21:
        boolean r02 = false;
    L29:
        monitor-exit(r12);     // Catch: Throwable -> L99
        System.nanoTime();
        if (Math.abs(this.rotation) != 90) goto L33;
    L36:
        boolean r10 = true;
    L37:
        if (r10 == false) goto L39;
        int r13 = r3.getRotatedHeight();
    L40:
        float r14 = r13;
        if (r10 == false) goto L43;
        int r2 = r3.getRotatedWidth();
    L44:
        float r15 = r14 / r2;
        Object r22 = this.layoutLock;
        monitor-enter(r22);
        float r42 = this.layoutAspectRatio;     // Catch: Throwable -> L95
        if (r42 != 0.0f) goto L50;
        r42 = r15;
    L50:
        monitor-exit(r22);     // Catch: Throwable -> L95
        float r5 = 1.0f;
        if (r15 <= r42) goto L54;
        float r43 = r42 / r15;
        float r16 = 1.0f;
    L55:
        this.drawMatrix.reset();
        this.drawMatrix.preTranslate(0.5f, 0.5f);
        this.drawMatrix.preRotate(this.rotation);
        Matrix r23 = this.drawMatrix;
        if (this.mirrorHorizontally == false) goto L58;
        float r6 = -1.0f;
    L60:
        if (this.mirrorVertically == false) goto L62;
        r5 = -1.0f;
    L62:
        r23.preScale(r6, r5);
        this.drawMatrix.preScale(r43, r16);
        this.drawMatrix.preTranslate(-0.5f, -0.5f);
        if (r02 == true) goto L109;
    L84:
        notifyCallbacks(r3, r02);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        r3.release();
        return;
    L72:
        e = move-exception;
        logE("Error while drawing frame", e);     // Catch: Throwable -> L70
        ErrorCallback r03 = this.errorCallback;     // Catch: Throwable -> L70
        if (r03 == null) goto L90;
        r03.onGlOutOfMemory();     // Catch: Throwable -> L70
    L90:
        this.drawer.release();     // Catch: Throwable -> L70
        this.frameDrawer.release();     // Catch: Throwable -> L70
        this.bitmapTextureFramebuffer.release();     // Catch: Throwable -> L70
        r3.release();
        return;
    L70:
        th = move-exception;
        r3.release();
        throw th;
    L109:
        this.frameDrawer.drawFrame(r3, this.drawer, this.drawMatrix, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight(), r10, false);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        if (this.eglBase.hasBackgroundSurface() == false) goto L76;
        this.eglBase.makeBackgroundCurrent();     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        this.frameDrawer.drawFrame(r3, this.drawer, this.drawMatrix, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight(), r10, true);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        boolean r17 = this.usePresentationTimeStamp;     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        EglBase r24 = this.eglBase;
        if (r17 == false) goto L74;
        r24.swapBuffers(r3.getTimestampNs(), true);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
    L75:
        this.eglBase.makeCurrent();     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        goto L76
    L74:
        r24.swapBuffers(true);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
    L76:
        System.nanoTime();     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        boolean r18 = this.usePresentationTimeStamp;     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        EglBase r25 = this.eglBase;
        if (r18 == false) goto L80;
        r25.swapBuffers(r3.getTimestampNs(), false);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
    L82:
        if (this.firstFrameRendered == true) goto L84;
        this.firstFrameRendered = true;     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        onFirstFrameRendered();     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        goto L84
    L80:
        r25.swapBuffers(false);     // Catch: Throwable -> L70 GlUtil.GlOutOfMemoryException -> L72
        goto L82
    L58:
        r6 = 1.0f;
        goto L60
    L54:
        r16 = r15 / r42;
        r43 = 1.0f;
    L95:
        th = move-exception;
        throw th;
    L43:
        r2 = r3.getRotatedHeight();
        goto L44
    L39:
        r13 = r3.getRotatedWidth();
        goto L40
    L33:
        if (Math.abs(this.rotation) == 270) goto L36;
        r10 = false;
        goto L37
    L23:
        if (r4 > 0) goto L25;
    L24:
        r02 = true;
        goto L29
    L25:
        long r44 = System.nanoTime();     // Catch: Throwable -> L99
        long r62 = this.nextFrameTimeNs;     // Catch: Throwable -> L99
        if (r44 < r62) goto L21;
        long r63 = r62 + this.minRenderPeriodNs;     // Catch: Throwable -> L99
        this.nextFrameTimeNs = r63;     // Catch: Throwable -> L99
        this.nextFrameTimeNs = Math.max(r63, r44);     // Catch: Throwable -> L99
    L99:
        th = move-exception;
        throw th;
    L8:
        th = move-exception;
        throw th;
    }

    public void addFrameListener(FrameListener r3, float r4) {
        addFrameListener(r3, r4, null, false);
    }

    public void addFrameListener(FrameListener r2, float r3, RendererCommon.GlDrawer r4) {
        addFrameListener(r2, r3, r4, false);
    }

    public void addFrameListener(FrameListener r7, float r8, RendererCommon.GlDrawer r9, boolean r10) {
        postToRenderThread(new EglRenderer$$ExternalSyntheticLambda0(this, r9, r7, r8, r10));
    }

    public void clearImage() {
        clearImage(0.0f, 0.0f, 0.0f, 0.0f);
        this.firstFrameRendered = false;
    }

    public void clearImage(float r9, float r10, float r11, float r12) {
        Object r1 = this.handlerLock;
        monitor-enter(r1);
        Handler r0 = this.renderThreadHandler;     // Catch: Throwable -> L8
        if (r0 != null) goto L10;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L10:
        r0.postAtFrontOfQueue(new EglRenderer$$ExternalSyntheticLambda1(this, r9, r10, r11, r12));     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void createBackgroundSurface(SurfaceTexture r2) {
        createEglSurfaceInternal(r2, true);
    }

    public void createEglSurface(SurfaceTexture r2) {
        createEglSurfaceInternal(r2, false);
    }

    public void createEglSurface(Surface r2) {
        createEglSurfaceInternal(r2, false);
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void getTexture(GlGenericDrawer.TextureCallback r4) {
        Object r0 = this.handlerLock;
        monitor-enter(r0);
        Handler r1 = this.renderThreadHandler;     // Catch: Throwable -> L8 Exception -> L10
        if (r1 == null) goto L12;
        r1.post(new EglRenderer$$ExternalSyntheticLambda2(this, r4));     // Catch: Throwable -> L8 Exception -> L10
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        goto L12
    }

    public void init(EglBase.Context r2, int[] r3, RendererCommon.GlDrawer r4) {
        init(r2, r3, r4, false);
    }

    public void init(EglBase.Context r3, int[] r4, RendererCommon.GlDrawer r5, boolean r6) {
        Object r0 = this.handlerLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.renderThreadHandler != null) goto L12;
        logD("Initializing EglRenderer");     // Catch: Throwable -> L9
        this.drawer = r5;     // Catch: Throwable -> L9
        this.usePresentationTimeStamp = r6;     // Catch: Throwable -> L9
        this.firstFrameRendered = false;     // Catch: Throwable -> L9
        HandlerThread r52 = new HandlerThread(this.name + TAG);     // Catch: Throwable -> L9
        r52.start();     // Catch: Throwable -> L9
        HandlerWithExceptionCallback r62 = new HandlerWithExceptionCallback(r52.getLooper(), new AnonymousClass1(this));     // Catch: Throwable -> L9
        this.renderThreadHandler = r62;     // Catch: Throwable -> L9
        r62.post(new EglRenderer$$ExternalSyntheticLambda6(this, r3, r4));     // Catch: Throwable -> L9
        this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L12:
        throw new IllegalStateException(this.name + "Already initialized");     // Catch: Throwable -> L9
    }

    public void onFirstFrameRendered() {
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r4) {
        Object r0 = this.handlerLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.renderThreadHandler != null) goto L11;
        logD("Dropping frame - Not initialized or already released.");     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        Object r1 = this.frameLock;     // Catch: Throwable -> L9
        monitor-enter(r1);     // Catch: Throwable -> L9
        VideoFrame r2 = this.pendingFrame;     // Catch: Throwable -> L16
        if (r2 == null) goto L18;
        r2.release();     // Catch: Throwable -> L16
    L18:
        this.pendingFrame = r4;     // Catch: Throwable -> L16
        r4.retain();     // Catch: Throwable -> L16
        this.renderThreadHandler.post(new EglRenderer$$ExternalSyntheticLambda5(this));     // Catch: Throwable -> L16
        monitor-exit(r1);     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L16:
        th = move-exception;
        throw th;     // Catch: Throwable -> L9
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void printStackTrace() {
        Object r0 = this.handlerLock;
        monitor-enter(r0);
        Handler r1 = this.renderThreadHandler;     // Catch: Throwable -> L14
        if (r1 != null) goto L7;
        Thread r12 = null;
    L8:
        if (r12 == null) goto L16;
        StackTraceElement[] r13 = r12.getStackTrace();     // Catch: Throwable -> L14
        if (r13.length <= 0) goto L16;
        logW("EglRenderer stack trace:");     // Catch: Throwable -> L14
        int r2 = r13.length;     // Catch: Throwable -> L14
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L16;
        logW(r13[r3].toString());     // Catch: Throwable -> L14
        r3 = r3 + 1;     // Catch: Throwable -> L14
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L14
        return;
    L7:
        r12 = r1.getLooper().getThread();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public void release() {
        logD("Releasing.");
        CountDownLatch r0 = new CountDownLatch(1);
        Object r1 = this.handlerLock;
        monitor-enter(r1);
        Handler r2 = this.renderThreadHandler;     // Catch: Throwable -> L9
        if (r2 != null) goto L11;
        logD("Already released");     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        r2.postAtFrontOfQueue(new EglRenderer$$ExternalSyntheticLambda3(this, r0));     // Catch: Throwable -> L9
        Looper r22 = this.renderThreadHandler.getLooper();     // Catch: Throwable -> L9
        this.renderThreadHandler.post(new EglRenderer$$ExternalSyntheticLambda4(this, r22));     // Catch: Throwable -> L9
        this.renderThreadHandler = null;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        ThreadUtils.awaitUninterruptibly(r0);
        Object r02 = this.frameLock;
        monitor-enter(r02);
        VideoFrame r12 = this.pendingFrame;     // Catch: Throwable -> L18
        if (r12 == null) goto L20;
        r12.release();     // Catch: Throwable -> L18
        this.pendingFrame = null;     // Catch: Throwable -> L18
    L20:
        monitor-exit(r02);     // Catch: Throwable -> L18
        logD("Releasing done.");
        return;
    L18:
        th = move-exception;
        throw th;
    L9:
        th = move-exception;
        throw th;
    }

    public void releaseEglSurface(Runnable r4, boolean r5) {
        this.eglSurfaceCreationRunnable.setSurface(null);
        Object r0 = this.handlerLock;
        monitor-enter(r0);
        Handler r1 = this.renderThreadHandler;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.removeCallbacks(this.eglSurfaceCreationRunnable);     // Catch: Throwable -> L9
        this.renderThreadHandler.postAtFrontOfQueue(new EglRenderer$$ExternalSyntheticLambda8(this, r5, r4));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r4 == null) goto L18;
        r4.run();
        return;
    L18:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public void removeFrameListener(FrameListener r5) {
        CountDownLatch r0 = new CountDownLatch(1);
        Object r1 = this.handlerLock;
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.renderThreadHandler != null) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L11:
        if (Thread.currentThread() == this.renderThreadHandler.getLooper().getThread()) goto L17;
        postToRenderThread(new EglRenderer$$ExternalSyntheticLambda7(this, r0, r5));     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        ThreadUtils.awaitUninterruptibly(r0);
        return;
    L17:
        throw new RuntimeException("removeFrameListener must not be called on the render thread.");     // Catch: Throwable -> L8
    }

    public void setErrorCallback(ErrorCallback r1) {
        this.errorCallback = r1;
    }

    public void setFpsReduction(float r6) {
        logD("setFpsReduction: " + r6);
        Object r0 = this.fpsReductionLock;
        monitor-enter(r0);
        long r1 = this.minRenderPeriodNs;     // Catch: Throwable -> L7
        if (r6 > 0.0f) goto L9;
        this.minRenderPeriodNs = LongCompanionObject.MAX_VALUE;     // Catch: Throwable -> L7
    L11:
        if (this.minRenderPeriodNs == r1) goto L13;
        this.nextFrameTimeNs = System.nanoTime();     // Catch: Throwable -> L7
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        this.minRenderPeriodNs = (long) (1.0E9f / r6);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public void setLayoutAspectRatio(float r2) {
        if (this.layoutAspectRatio == r2) goto L12;
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.layoutAspectRatio = r2;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public void setMirror(boolean r3) {
        logD("setMirrorHorizontally: " + r3);
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.mirrorHorizontally = r3;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setMirrorVertically(boolean r3) {
        logD("setMirrorVertically: " + r3);
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.mirrorVertically = r3;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setRotation(int r2) {
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.rotation = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
