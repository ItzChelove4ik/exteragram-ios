package org.telegram.ui.Components.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.TextureView;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Paint.Painting;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class RenderView extends TextureView {
    private Bitmap bitmap;
    private Bitmap blurBitmap;
    private Brush brush;
    private int color;
    private RenderViewDelegate delegate;
    private boolean firstDrawSent;
    private Input input;
    private CanvasInternal internal;
    private Painting painting;
    private DispatchQueue queue;
    private ShapeInput shapeInput;
    private boolean shuttingDown;
    private boolean transformedBitmap;
    private UndoStore undoStore;
    private float weight;

    /* renamed from: org.telegram.ui.Components.Paint.RenderView$1, reason: invalid class name */
    public class AnonymousClass1 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ RenderView this$0;
        final /* synthetic */ BlurringShader.BlurManager val$blurManager;

        public static /* synthetic */ void $r8$lambda$22kHXaXcNHQ3uWry0kkDMJCQfak(AnonymousClass1 r0) {
            r0.lambda$onSurfaceTextureSizeChanged$1();
        }

        public static /* synthetic */ void $r8$lambda$OgIPSzhmyI3NV57Eknhd4dzW124(AnonymousClass1 r0) {
            r0.lambda$onSurfaceTextureAvailable$0();
        }

        public static /* synthetic */ void $r8$lambda$S6Te03NouASZiwX7Ub_DC0oqcBo(AnonymousClass1 r0) {
            r0.lambda$onSurfaceTextureDestroyed$2();
        }

        public AnonymousClass1(RenderView r1, BlurringShader.BlurManager r2) {
            this.this$0 = r1;
            this.val$blurManager = r2;
        }

        private /* synthetic */ void lambda$onSurfaceTextureAvailable$0() {
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) == null) goto L6;
            RenderView.m11385$$Nest$fgetinternal(this.this$0).requestRender();
            return;
        }

        private /* synthetic */ void lambda$onSurfaceTextureDestroyed$2() {
            RenderView.m11385$$Nest$fgetinternal(this.this$0).shutdown();
            RenderView.m11393$$Nest$fputinternal(this.this$0, null);
        }

        private /* synthetic */ void lambda$onSurfaceTextureSizeChanged$1() {
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) == null) goto L6;
            RenderView.m11385$$Nest$fgetinternal(this.this$0).requestRender();
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r5, int r6, int r7) {
            if (r5 != null) goto L4;
            return;
        L4:
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) != null) goto L12;
            RenderView.m11393$$Nest$fputinternal(this.this$0, new CanvasInternal(this.this$0, r5, this.val$blurManager));
            RenderView.m11385$$Nest$fgetinternal(this.this$0).setBufferSize(r6, r7);
            RenderView.m11395$$Nest$mupdateTransform(this.this$0);
            this.this$0.post(new RenderView$1$$ExternalSyntheticLambda1(this));
            if (RenderView.m11386$$Nest$fgetpainting(this.this$0).isPaused() == false) goto L11;
            RenderView.m11386$$Nest$fgetpainting(this.this$0).onResume();
            return;
        L11:
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r3) {
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) != null) goto L6;
            return true;
        L6:
            if (RenderView.m11388$$Nest$fgetshuttingDown(this.this$0) == true) goto L8;
            RenderView.m11386$$Nest$fgetpainting(this.this$0).onPause(new RenderView$1$$ExternalSyntheticLambda2(this));
        L8:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) != null) goto L5;
            return;
        L5:
            RenderView.m11385$$Nest$fgetinternal(this.this$0).setBufferSize(r2, r3);
            RenderView.m11395$$Nest$mupdateTransform(this.this$0);
            RenderView.m11385$$Nest$fgetinternal(this.this$0).requestRender();
            RenderView.m11385$$Nest$fgetinternal(this.this$0).postRunnable(new RenderView$1$$ExternalSyntheticLambda0(this));
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.RenderView$2, reason: invalid class name */
    public class AnonymousClass2 implements Painting.PaintingDelegate {
        final /* synthetic */ RenderView this$0;

        public AnonymousClass2(RenderView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Painting.PaintingDelegate
        public void contentChanged() {
            if (RenderView.m11385$$Nest$fgetinternal(this.this$0) == null) goto L6;
            RenderView.m11385$$Nest$fgetinternal(this.this$0).scheduleRedraw();
            return;
        }

        @Override // org.telegram.ui.Components.Paint.Painting.PaintingDelegate
        public DispatchQueue requestDispatchQueue() {
            return RenderView.m11387$$Nest$fgetqueue(this.this$0);
        }

        @Override // org.telegram.ui.Components.Paint.Painting.PaintingDelegate
        public UndoStore requestUndoStore() {
            return RenderView.m11389$$Nest$fgetundoStore(this.this$0);
        }
    }

    public class CanvasInternal extends DispatchQueue {
        private final BlurringShader.BlurManager blurManager;
        private int bufferHeight;
        private int bufferWidth;
        private Runnable drawRunnable;
        private EGL10 egl10;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private boolean initialized;
        private volatile boolean ready;
        public Runnable safeRequestRender;
        private Runnable scheduledRunnable;
        private SurfaceTexture surfaceTexture;
        final /* synthetic */ RenderView this$0;

        /* renamed from: org.telegram.ui.Components.Paint.RenderView$CanvasInternal$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ CanvasInternal this$1;

            public static /* synthetic */ void $r8$lambda$XJtKAWuLIgeHKvupzTkGhoqKryk(AnonymousClass1 r0) {
                r0.lambda$run$0();
            }

            public AnonymousClass1(CanvasInternal r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0() {
                RenderView.m11383$$Nest$fgetdelegate(this.this$1.this$0).onFirstDraw();
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CanvasInternal.m11403$$Nest$fgetinitialized(this.this$1) == true) goto L5;
                return;
            L5:
                if (RenderView.m11388$$Nest$fgetshuttingDown(this.this$1.this$0) == true) goto L16;
                CanvasInternal.m11406$$Nest$msetCurrentContext(this.this$1);
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glViewport(0, 0, CanvasInternal.m11399$$Nest$fgetbufferWidth(this.this$1), CanvasInternal.m11398$$Nest$fgetbufferHeight(this.this$1));
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                GLES20.glClear(16384);
                RenderView.m11386$$Nest$fgetpainting(this.this$1.this$0).render();
                GLES20.glBlendFunc(1, 771);
                CanvasInternal.m11400$$Nest$fgetegl10(this.this$1).eglSwapBuffers(CanvasInternal.m11401$$Nest$fgeteglDisplay(this.this$1), CanvasInternal.m11402$$Nest$fgeteglSurface(this.this$1));
                if (RenderView.m11384$$Nest$fgetfirstDrawSent(this.this$1.this$0) == true) goto L11;
                RenderView.m11392$$Nest$fputfirstDrawSent(this.this$1.this$0, true);
                AndroidUtilities.runOnUIThread(new RenderView$CanvasInternal$1$$ExternalSyntheticLambda0(this));
            L11:
                if (CanvasInternal.m11404$$Nest$fgetready(this.this$1) == true) goto L15;
                CanvasInternal.m11405$$Nest$fputready(this.this$1, true);
                return;
            L15:
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$OSNQXgqVWiQqaOxmqk50NcKfeTg(CanvasInternal r0, boolean r1, boolean r2, Bitmap[] r3, CountDownLatch r4) {
            r0.lambda$getTexture$3(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$Oq-AJq6qalRs2Xy6gg25dVWgesA, reason: not valid java name */
        public static /* synthetic */ void m11396$r8$lambda$OqAJq6qalRs2Xy6gg25dVWgesA(CanvasInternal r0) {
            r0.lambda$scheduleRedraw$1();
        }

        public static /* synthetic */ void $r8$lambda$kSzBU6AzjozreioKJ20dl5YEweg(CanvasInternal r0) {
            r0.lambda$shutdown$2();
        }

        /* renamed from: $r8$lambda$l0-xJ4fKPY7Br4x-T2QEijlOu_8, reason: not valid java name */
        public static /* synthetic */ void m11397$r8$lambda$l0xJ4fKPY7Br4xT2QEijlOu_8(CanvasInternal r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fgetbufferHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11398$$Nest$fgetbufferHeight(CanvasInternal r0) {
            return r0.bufferHeight;
        }

        /* renamed from: -$$Nest$fgetbufferWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11399$$Nest$fgetbufferWidth(CanvasInternal r0) {
            return r0.bufferWidth;
        }

        /* renamed from: -$$Nest$fgetegl10, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGL10 m11400$$Nest$fgetegl10(CanvasInternal r0) {
            return r0.egl10;
        }

        /* renamed from: -$$Nest$fgeteglDisplay, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGLDisplay m11401$$Nest$fgeteglDisplay(CanvasInternal r0) {
            return r0.eglDisplay;
        }

        /* renamed from: -$$Nest$fgeteglSurface, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EGLSurface m11402$$Nest$fgeteglSurface(CanvasInternal r0) {
            return r0.eglSurface;
        }

        /* renamed from: -$$Nest$fgetinitialized, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11403$$Nest$fgetinitialized(CanvasInternal r0) {
            return r0.initialized;
        }

        /* renamed from: -$$Nest$fgetready, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11404$$Nest$fgetready(CanvasInternal r0) {
            return r0.ready;
        }

        /* renamed from: -$$Nest$fputready, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11405$$Nest$fputready(CanvasInternal r0, boolean r1) {
            r0.ready = r1;
        }

        /* renamed from: -$$Nest$msetCurrentContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11406$$Nest$msetCurrentContext(CanvasInternal r0) {
            return r0.setCurrentContext();
        }

        public CanvasInternal(RenderView r1, SurfaceTexture r2, BlurringShader.BlurManager r3) {
            this.this$0 = r1;
            super("CanvasInternal");
            this.drawRunnable = new AnonymousClass1(this);
            this.safeRequestRender = new RenderView$CanvasInternal$$ExternalSyntheticLambda1(this);
            this.blurManager = r3;
            this.surfaceTexture = r2;
        }

        private void checkBitmap() {
            Size r0 = RenderView.m11386$$Nest$fgetpainting(this.this$0).getSize();
            if (RenderView.m11381$$Nest$fgetbitmap(this.this$0).getWidth() == r0.width) goto L5;
        L6:
            Bitmap r1 = Bitmap.createBitmap((int) r0.width, (int) r0.height, Bitmap.Config.ARGB_8888);
            new Canvas(r1).drawBitmap(RenderView.m11381$$Nest$fgetbitmap(this.this$0), null, new RectF(0.0f, 0.0f, r0.width, r0.height), null);
            RenderView.m11390$$Nest$fputbitmap(this.this$0, r1);
            RenderView.m11394$$Nest$fputtransformedBitmap(this.this$0, true);
        L8:
            if (RenderView.m11382$$Nest$fgetblurBitmap(this.this$0) != null) goto L10;
            return;
        L10:
            if (RenderView.m11382$$Nest$fgetblurBitmap(this.this$0).getWidth() == r0.width) goto L12;
        L13:
            Bitmap r12 = Bitmap.createBitmap((int) r0.width, (int) r0.height, Bitmap.Config.ARGB_8888);
            new Canvas(r12).drawBitmap(RenderView.m11382$$Nest$fgetblurBitmap(this.this$0), null, new RectF(0.0f, 0.0f, r0.width, r0.height), null);
            RenderView.m11391$$Nest$fputblurBitmap(this.this$0, r12);
            RenderView.m11394$$Nest$fputtransformedBitmap(this.this$0, true);
            return;
        L12:
            if (RenderView.m11382$$Nest$fgetblurBitmap(this.this$0).getHeight() != r0.height) goto L13;
            return;
        L5:
            if (RenderView.m11381$$Nest$fgetbitmap(this.this$0).getHeight() == r0.height) goto L8;
            goto L6
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
            if (r8[0] <= 0) goto L63;
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
            this.blurManager.attach(this.safeRequestRender);
        L40:
            if (this.surfaceTexture == null) goto L60;
            EGLSurface r13 = this.egl10.eglCreateWindowSurface(this.eglDisplay, r3, this.surfaceTexture, null);
            this.eglSurface = r13;
            if (r13 == null) goto L56;
            if (r13 == EGL10.EGL_NO_SURFACE) goto L56;
            if (this.egl10.eglMakeCurrent(this.eglDisplay, r13, r13, this.eglContext) == false) goto L49;
            GLES20.glEnable(3042);
            GLES20.glDisable(3024);
            GLES20.glDisable(2960);
            GLES20.glDisable(2929);
            RenderView.m11386$$Nest$fgetpainting(this.this$0).setupShaders();
            checkBitmap();
            RenderView.m11386$$Nest$fgetpainting(this.this$0).setBitmap(RenderView.m11381$$Nest$fgetbitmap(this.this$0), RenderView.m11382$$Nest$fgetblurBitmap(this.this$0));
            Utils.HasGLError();
            return true;
        L49:
            if (BuildVars.LOGS_ENABLED == false) goto L51;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L51:
            finish();
            return false;
        L56:
            if (BuildVars.LOGS_ENABLED == false) goto L58;
            FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L58:
            finish();
            return false;
        L60:
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
        L63:
            if (BuildVars.LOGS_ENABLED == false) goto L65;
            FileLog.e("eglConfig not initialized");
        L65:
            finish();
            return false;
        L12:
            if (BuildVars.LOGS_ENABLED == false) goto L14;
            FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L14:
            finish();
            return false;
        }

        private /* synthetic */ void lambda$getTexture$3(boolean r5, boolean r6, Bitmap[] r7, CountDownLatch r8) {
            Painting.PaintingData r52 = RenderView.m11386$$Nest$fgetpainting(this.this$0).getPaintingData(new RectF(0.0f, 0.0f, RenderView.m11386$$Nest$fgetpainting(this.this$0).getSize().width, RenderView.m11386$$Nest$fgetpainting(this.this$0).getSize().height), false, r5, r6);
            if (r52 == null) goto L5;
            r7[0] = r52.bitmap;
        L5:
            r8.countDown();
        }

        private /* synthetic */ void lambda$new$0() {
            Runnable r0 = this.scheduledRunnable;
            if (r0 == null) goto L5;
            cancelRunnable(r0);
            this.scheduledRunnable = null;
        L5:
            cancelRunnable(this.drawRunnable);
            postRunnable(this.drawRunnable);
        }

        private /* synthetic */ void lambda$scheduleRedraw$1() {
            this.scheduledRunnable = null;
            this.drawRunnable.run();
        }

        private /* synthetic */ void lambda$shutdown$2() {
            finish();
            Looper r0 = Looper.myLooper();
            if (r0 == null) goto L6;
            r0.quit();
            return;
        }

        private boolean setCurrentContext() {
            if (this.initialized == true) goto L6;
            return false;
        L6:
            if (this.eglContext.equals(this.egl10.eglGetCurrentContext()) == true) goto L8;
        L9:
            EGL10 r0 = this.egl10;
            EGLDisplay r2 = this.eglDisplay;
            EGLSurface r3 = this.eglSurface;
            if (r0.eglMakeCurrent(r2, r3, r3, this.eglContext) == true) goto L14;
            return false;
        L14:
            return true;
        L8:
            if (this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == false) goto L9;
            return true;
        }

        public void finish() {
            if (this.eglSurface == null) goto L5;
            EGL10 r0 = this.egl10;
            EGLDisplay r2 = this.eglDisplay;
            EGLSurface r3 = EGL10.EGL_NO_SURFACE;
            r0.eglMakeCurrent(r2, r3, r3, EGL10.EGL_NO_CONTEXT);
            this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = null;
        L5:
            EGLContext r02 = this.eglContext;
            if (r02 == null) goto L11;
            BlurringShader.BlurManager r22 = this.blurManager;
            if (r22 == null) goto L10;
            r22.destroyedContext(r02);
        L10:
            this.egl10.eglDestroyContext(this.eglDisplay, this.eglContext);
            this.eglContext = null;
        L11:
            EGLDisplay r03 = this.eglDisplay;
            if (r03 == null) goto L14;
            this.egl10.eglTerminate(r03);
            this.eglDisplay = null;
        L14:
            BlurringShader.BlurManager r04 = this.blurManager;
            if (r04 == null) goto L18;
            r04.detach(this.safeRequestRender);
            return;
        }

        public Bitmap getTexture(boolean r7, boolean r8) {
            if (this.initialized == true) goto L6;
            return null;
        L6:
            CountDownLatch r5 = new CountDownLatch(1);
            Bitmap[] r4 = new Bitmap[1];
            postRunnable(new RenderView$CanvasInternal$$ExternalSyntheticLambda0(this, r7, r8, r4, r5));     // Catch: Exception -> L9
            r5.await();     // Catch: Exception -> L9
        L12:
            return r4[0];
        L9:
            e = move-exception;
            FileLog.e(e);
            goto L12
        }

        public void requestRender() {
            postRunnable(this.drawRunnable);
        }

        @Override // org.telegram.messenger.DispatchQueue, java.lang.Thread, java.lang.Runnable
        public void run() {
            if (RenderView.m11381$$Nest$fgetbitmap(this.this$0) != null) goto L5;
            return;
        L5:
            if (RenderView.m11381$$Nest$fgetbitmap(this.this$0).isRecycled() == true) goto L10;
            this.initialized = initGL();
            super.run();
            return;
        }

        public void scheduleRedraw() {
            Runnable r0 = this.scheduledRunnable;
            if (r0 == null) goto L5;
            cancelRunnable(r0);
            this.scheduledRunnable = null;
        L5:
            RenderView$CanvasInternal$$ExternalSyntheticLambda3 r02 = new RenderView$CanvasInternal$$ExternalSyntheticLambda3(this);
            this.scheduledRunnable = r02;
            postRunnable(r02, 1);
        }

        public void setBufferSize(int r1, int r2) {
            this.bufferWidth = r1;
            this.bufferHeight = r2;
        }

        public void shutdown() {
            postRunnable(new RenderView$CanvasInternal$$ExternalSyntheticLambda2(this));
        }
    }

    public interface RenderViewDelegate {
        default void invalidateInputView() {
        }

        void onBeganDrawing();

        void onFinishedDrawing(boolean r1);

        void onFirstDraw();

        void resetBrush();

        boolean shouldDraw();
    }

    public static /* synthetic */ void $r8$lambda$B7UI3vOCsWsWZSIGUNUeLz7KBSQ(RenderView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$G-mtX5nhyxxeoz1pIvwoxDjYQ2U, reason: not valid java name */
    public static /* synthetic */ void m11378$r8$lambda$GmtX5nhyxxeoz1pIvwoxDjYQ2U(RenderView r0) {
        r0.lambda$shutdown$1();
    }

    /* renamed from: $r8$lambda$jeEEC-riUa3-RTh0iKb7Lq21t-c, reason: not valid java name */
    public static /* synthetic */ void m11379$r8$lambda$jeEECriUa3RTh0iKb7Lq21tc(RenderView r0, Runnable r1) {
        r0.lambda$performInContext$3(r1);
    }

    /* renamed from: $r8$lambda$uz2DZK53-JRcMhGGb97rw02acoo, reason: not valid java name */
    public static /* synthetic */ void m11380$r8$lambda$uz2DZK53JRcMhGGb97rw02acoo(RenderView r0) {
        r0.lambda$clearAll$2();
    }

    /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m11381$$Nest$fgetbitmap(RenderView r0) {
        return r0.bitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m11382$$Nest$fgetblurBitmap(RenderView r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderViewDelegate m11383$$Nest$fgetdelegate(RenderView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfirstDrawSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11384$$Nest$fgetfirstDrawSent(RenderView r0) {
        return r0.firstDrawSent;
    }

    /* renamed from: -$$Nest$fgetinternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CanvasInternal m11385$$Nest$fgetinternal(RenderView r0) {
        return r0.internal;
    }

    /* renamed from: -$$Nest$fgetpainting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Painting m11386$$Nest$fgetpainting(RenderView r0) {
        return r0.painting;
    }

    /* renamed from: -$$Nest$fgetqueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m11387$$Nest$fgetqueue(RenderView r0) {
        return r0.queue;
    }

    /* renamed from: -$$Nest$fgetshuttingDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11388$$Nest$fgetshuttingDown(RenderView r0) {
        return r0.shuttingDown;
    }

    /* renamed from: -$$Nest$fgetundoStore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoStore m11389$$Nest$fgetundoStore(RenderView r0) {
        return r0.undoStore;
    }

    /* renamed from: -$$Nest$fputbitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11390$$Nest$fputbitmap(RenderView r0, Bitmap r1) {
        r0.bitmap = r1;
    }

    /* renamed from: -$$Nest$fputblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11391$$Nest$fputblurBitmap(RenderView r0, Bitmap r1) {
        r0.blurBitmap = r1;
    }

    /* renamed from: -$$Nest$fputfirstDrawSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11392$$Nest$fputfirstDrawSent(RenderView r0, boolean r1) {
        r0.firstDrawSent = r1;
    }

    /* renamed from: -$$Nest$fputinternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11393$$Nest$fputinternal(RenderView r0, CanvasInternal r1) {
        r0.internal = r1;
    }

    /* renamed from: -$$Nest$fputtransformedBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11394$$Nest$fputtransformedBitmap(RenderView r0, boolean r1) {
        r0.transformedBitmap = r1;
    }

    /* renamed from: -$$Nest$mupdateTransform, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11395$$Nest$mupdateTransform(RenderView r0) {
        r0.updateTransform();
    }

    public RenderView(Context r1, Painting r2, Bitmap r3, Bitmap r4, BlurringShader.BlurManager r5) {
        super(r1);
        setOpaque(false);
        this.bitmap = r3;
        this.blurBitmap = r4;
        this.painting = r2;
        r2.setRenderView(this);
        setSurfaceTextureListener(new AnonymousClass1(this, r5));
        this.input = new Input(this);
        this.shapeInput = new ShapeInput(this, new RenderView$$ExternalSyntheticLambda0(this));
        this.painting.setDelegate(new AnonymousClass2(this));
    }

    private /* synthetic */ void lambda$clearAll$2() {
        this.painting.setBrush(this.brush);
    }

    private /* synthetic */ void lambda$new$0() {
        RenderViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.invalidateInputView();
        return;
    }

    private /* synthetic */ void lambda$performInContext$3(Runnable r2) {
        CanvasInternal r0 = this.internal;
        if (r0 != null) goto L5;
        return;
    L5:
        if (CanvasInternal.m11403$$Nest$fgetinitialized(r0) == false) goto L10;
        CanvasInternal.m11406$$Nest$msetCurrentContext(this.internal);
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$shutdown$1() {
        this.painting.cleanResources(this.transformedBitmap);
        this.internal.shutdown();
        this.internal = null;
    }

    private void updateTransform() {
        if (this.internal != null) goto L5;
        return;
    L5:
        Matrix r0 = new Matrix();
        float r2 = 1.0f;
        if (this.painting == null) goto L8;
        float r1 = getWidth() / this.painting.getSize().width;
    L10:
        if (r1 <= 0.0f) goto L13;
        r2 = r1;
    L13:
        Size r12 = getPainting().getSize();
        r0.preTranslate(getWidth() / 2.0f, getHeight() / 2.0f);
        r0.preScale(r2, -r2);
        r0.preTranslate((-r12.width) / 2.0f, (-r12.height) / 2.0f);
        if ((this.brush instanceof Brush.Shape) == false) goto L16;
        this.shapeInput.setMatrix(r0);
    L17:
        this.painting.setRenderProjection(GLMatrix.MultiplyMat4f(GLMatrix.LoadOrtho(0.0f, CanvasInternal.m11399$$Nest$fgetbufferWidth(this.internal), 0.0f, CanvasInternal.m11398$$Nest$fgetbufferHeight(this.internal), -1.0f, 1.0f), GLMatrix.LoadGraphicsMatrix(r0)));
        return;
    L16:
        this.input.setMatrix(r0);
        goto L17
    L8:
        r1 = 1.0f;
        goto L10
    }

    public float brushWeightForSize(float r3) {
        float r2 = this.painting.getSize().width;
        return (0.00390625f * r2) + ((r2 * 0.043945312f) * r3);
    }

    public void clearAll() {
        this.input.clear(new RenderView$$ExternalSyntheticLambda2(this));
    }

    public void clearShape() {
        ShapeInput r0 = this.shapeInput;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public Brush getCurrentBrush() {
        return this.brush;
    }

    public int getCurrentColor() {
        return this.color;
    }

    public float getCurrentWeight() {
        return this.weight;
    }

    public Painting getPainting() {
        return this.painting;
    }

    public Bitmap getResultBitmap(boolean r2, boolean r3) {
        if ((this.brush instanceof Brush.Shape) == false) goto L5;
        this.shapeInput.stop();
    L5:
        CanvasInternal r1 = this.internal;
        if (r1 != null) goto L8;
        return null;
    L8:
        return r1.getTexture(r2, r3);
    }

    public UndoStore getUndoStore() {
        return this.undoStore;
    }

    public void onBeganDrawing() {
        RenderViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onBeganDrawing();
        return;
    }

    public void onDrawForInput(Canvas r2) {
        if ((this.brush instanceof Brush.Shape) == false) goto L6;
        this.shapeInput.dispatchDraw(r2);
        return;
    }

    public void onFinishedDrawing(boolean r1) {
        RenderViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onFinishedDrawing(r1);
        return;
    }

    public boolean onTouch(MotionEvent r3) {
        if (r3.getPointerCount() <= 1) goto L6;
        return false;
    L6:
        CanvasInternal r0 = this.internal;
        if (r0 != null) goto L9;
    L17:
        return true;
    L9:
        if (CanvasInternal.m11403$$Nest$fgetinitialized(r0) == false) goto L17;
        if (CanvasInternal.m11404$$Nest$fgetready(this.internal) == false) goto L17;
        if ((this.brush instanceof Brush.Shape) == false) goto L16;
        this.shapeInput.process(r3, getScaleX());
        goto L17
    L16:
        this.input.process(r3, getScaleX());
        goto L17
    }

    public void performInContext(Runnable r3) {
        CanvasInternal r0 = this.internal;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.postRunnable(new RenderView$$ExternalSyntheticLambda3(this, r3));
    }

    public void redraw() {
        CanvasInternal r0 = this.internal;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.requestRender();
    }

    public void resetBrush() {
        RenderViewDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.resetBrush();
    L5:
        this.input.ignoreOnce();
    }

    public void selectBrush(Brush r1) {
    }

    public void setBrush(Brush r2) {
        if ((this.brush instanceof Brush.Shape) == false) goto L5;
        this.shapeInput.stop();
    L5:
        this.brush = r2;
        updateTransform();
        this.painting.setBrush(this.brush);
        Brush r22 = this.brush;
        if ((r22 instanceof Brush.Shape) == false) goto L9;
        this.shapeInput.start(((Brush.Shape) r22).getShapeShaderType());
        return;
    }

    public void setBrushSize(float r1) {
        this.weight = brushWeightForSize(r1);
        if ((this.brush instanceof Brush.Shape) == false) goto L6;
        this.shapeInput.onWeightChange();
        return;
    }

    public void setColor(int r1) {
        this.color = r1;
        if ((this.brush instanceof Brush.Shape) == false) goto L6;
        this.shapeInput.onColorChange();
        return;
    }

    public void setDelegate(RenderViewDelegate r1) {
        this.delegate = r1;
    }

    public void setQueue(DispatchQueue r1) {
        this.queue = r1;
    }

    public void setUndoStore(UndoStore r1) {
        this.undoStore = r1;
    }

    public boolean shouldDraw() {
        RenderViewDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.shouldDraw() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void shutdown() {
        this.shuttingDown = true;
        if (this.internal == null) goto L5;
        performInContext(new RenderView$$ExternalSyntheticLambda1(this));
    L5:
        setVisibility(8);
    }
}
