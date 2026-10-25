package org.telegram.ui.Components.Premium.GLIcon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.Collections;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.EmuDetector;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public class GLIconTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    ArrayList<Integer> animationIndexes;
    int animationPointer;
    private final int animationsCount;
    AnimatorSet animatorSet;
    boolean attached;
    ValueAnimator backAnimation;
    private boolean dialogIsVisible;
    private EGLConfig eglConfig;
    GestureDetector gestureDetector;
    Runnable idleAnimation;
    private long idleDelay;
    public boolean isRunning;
    private EGL10 mEgl;
    private EGLContext mEglContext;
    private EGLDisplay mEglDisplay;
    private EGLSurface mEglSurface;
    private GL10 mGl;
    public GLIconRenderer mRenderer;
    private SurfaceTexture mSurface;
    private boolean paused;
    private volatile boolean ready;
    private volatile Runnable readyListener;
    private boolean rendererChanged;
    StarParticlesView starParticlesView;
    private int surfaceHeight;
    private int surfaceWidth;
    private int targetFps;
    private int targetFrameDurationMillis;
    private RenderThread thread;
    public boolean touched;
    int type;
    ValueAnimator.AnimatorUpdateListener xUpdater;
    ValueAnimator.AnimatorUpdateListener xUpdater2;
    ValueAnimator.AnimatorUpdateListener yUpdater;

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$1, reason: invalid class name */
    public class AnonymousClass1 implements GestureDetector.OnGestureListener {
        final /* synthetic */ GLIconTextureView this$0;

        /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$1$1, reason: invalid class name and collision with other inner class name */
        public class C00541 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00541(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                super.onAnimationEnd(r3);
                GLIconTextureView r2 = this.this$1.this$0;
                r2.mRenderer.angleX = 0.0f;
                r2.animatorSet = null;
                r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
            }
        }

        public static /* synthetic */ void $r8$lambda$8iEnGtZ737balql4CoG9S87r1r4(AnonymousClass1 r0, float r1, float r2) {
            r0.lambda$onSingleTapUp$0(r1, r2);
        }

        public AnonymousClass1(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSingleTapUp$0(float r13, float r14) {
            ValueAnimator r0 = this.this$0.backAnimation;
            if (r0 == null) goto L5;
            r0.removeAllListeners();
            this.this$0.backAnimation.cancel();
            this.this$0.backAnimation = null;
        L5:
            AnimatorSet r02 = this.this$0.animatorSet;
            if (r02 == null) goto L8;
            r02.removeAllListeners();
            this.this$0.animatorSet.cancel();
            this.this$0.animatorSet = null;
        L8:
            float r03 = Math.abs(this.this$0.mRenderer.angleX);
            GLIconTextureView r1 = this.this$0;
            if (r03 <= 10.0f) goto L12;
            r1.startBackAnimation();
            return;
        L12:
            AndroidUtilities.cancelRunOnUIThread(r1.idleAnimation);
            this.this$0.animatorSet = new AnimatorSet();
            ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.this$0.mRenderer.angleX, r13});
            r2.addUpdateListener(this.this$0.xUpdater);
            r2.setDuration(220);
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r2.setInterpolator(r6);
            ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{r13, 0.0f});
            r7.addUpdateListener(this.this$0.xUpdater);
            r7.setStartDelay(220);
            r7.setDuration(600);
            r7.setInterpolator(AndroidUtilities.overshootInterpolator);
            ValueAnimator r10 = ValueAnimator.ofFloat(new float[]{this.this$0.mRenderer.angleY, r14});
            r10.addUpdateListener(this.this$0.yUpdater);
            r10.setDuration(220);
            r10.setInterpolator(r6);
            ValueAnimator r132 = ValueAnimator.ofFloat(new float[]{r14, 0.0f});
            r132.addUpdateListener(this.this$0.yUpdater);
            r132.setStartDelay(220);
            r132.setDuration(600);
            r132.setInterpolator(AndroidUtilities.overshootInterpolator);
            this.this$0.animatorSet.playTogether(new Animator[]{r2, r7, r10, r132});
            this.this$0.animatorSet.addListener(new C00541(this));
            this.this$0.animatorSet.start();
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r2) {
            ValueAnimator r22 = this.this$0.backAnimation;
            if (r22 == null) goto L5;
            r22.removeAllListeners();
            this.this$0.backAnimation.cancel();
            this.this$0.backAnimation = null;
        L5:
            AnimatorSet r23 = this.this$0.animatorSet;
            if (r23 == null) goto L8;
            r23.removeAllListeners();
            this.this$0.animatorSet.cancel();
            this.this$0.animatorSet = null;
        L8:
            AndroidUtilities.cancelRunOnUIThread(this.this$0.idleAnimation);
            this.this$0.touched = true;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent r1) {
            this.this$0.onLongPress();
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            GLIconRenderer r0 = this.this$0.mRenderer;
            r0.angleX += r3 * 0.5f;
            r0.angleY += r4 * 0.05f;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent r1) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r5) {
            float r0 = this.this$0.getMeasuredWidth() / 2.0f;
            AndroidUtilities.runOnUIThread(new GLIconTextureView$1$$ExternalSyntheticLambda0(this, ((Utilities.random.nextInt(30) + 40) * (r0 - r5.getX())) / r0, ((Utilities.random.nextInt(30) + 40) * (r0 - r5.getY())) / r0), 16);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass2(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            AnimatorSet r0 = this.this$0.animatorSet;
            if (r0 != null) goto L5;
        L6:
            ValueAnimator r02 = this.this$0.backAnimation;
            if (r02 != null) goto L9;
        L12:
            this.this$0.startIdleAnimation();
            return;
        L9:
            if (r02.isRunning() == false) goto L12;
        L10:
            GLIconTextureView r2 = this.this$0;
            r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
            return;
        L5:
            if (r0.isRunning() == true) goto L10;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass3(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            GLIconTextureView r2 = this.this$0;
            r2.mRenderer.angleX = 0.0f;
            r2.animatorSet = null;
            r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass4(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            GLIconTextureView r2 = this.this$0;
            r2.mRenderer.angleX = 0.0f;
            r2.animatorSet = null;
            r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass5(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            GLIconTextureView r2 = this.this$0;
            r2.mRenderer.angleX = 0.0f;
            r2.animatorSet = null;
            r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass6(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            GLIconTextureView r2 = this.this$0;
            r2.mRenderer.angleX = 0.0f;
            r2.animatorSet = null;
            r2.scheduleIdleAnimation(GLIconTextureView.m11932$$Nest$fgetidleDelay(r2));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ GLIconTextureView this$0;

        public AnonymousClass7(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.startBackAnimation();
        }
    }

    public class RenderThread extends Thread {
        final /* synthetic */ GLIconTextureView this$0;

        private RenderThread(GLIconTextureView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ RenderThread(GLIconTextureView r1, GLIconTextureViewIA r2) {
            this(r1);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            EGLContext r0 = EGL10.EGL_NO_CONTEXT;
            EGLSurface r1 = EGL10.EGL_NO_SURFACE;
            GLIconTextureView r2 = this.this$0;
            r2.isRunning = true;
            GLIconTextureView.m11949$$Nest$minitGL(r2);     // Catch: Throwable -> L12 Exception -> L51
            GLIconTextureView.m11947$$Nest$mcheckGlError(this.this$0);     // Catch: Throwable -> L12 Exception -> L14
            long r6 = System.currentTimeMillis();     // Catch: Throwable -> L12 Exception -> L14
        L6:
            if (this.this$0.isRunning == false) goto L37;
        L7:
            GLIconTextureView r22 = this.this$0;     // Catch: Throwable -> L12 Exception -> L14
            if (r22.mRenderer != null) goto L17;
            Thread.sleep(100);     // Catch: InterruptedException -> L97 Throwable -> L12 Exception -> L14
            goto L7
        L17:
            if (GLIconTextureView.m11939$$Nest$fgetrendererChanged(r22) == false) goto L103;
            GLIconTextureView r23 = this.this$0;     // Catch: Throwable -> L12 Exception -> L14
            GLIconTextureView.m11950$$Nest$minitializeRenderer(r23, r23.mRenderer);     // Catch: Throwable -> L12 Exception -> L14
            GLIconTextureView.m11946$$Nest$fputrendererChanged(this.this$0, false);     // Catch: Throwable -> L12 Exception -> L14
        L103:
            if (GLIconTextureView.m11951$$Nest$mshouldSleep(this.this$0) == true) goto L105;
            long r11 = System.currentTimeMillis();     // Catch: Throwable -> L12 Exception -> L25
            GLIconTextureView.m11948$$Nest$mdrawSingleFrame(this.this$0, (r11 - r6) / 1000.0f);     // Catch: Throwable -> L12 Exception -> L25
            if (GLIconTextureView.m11937$$Nest$fgetready(this.this$0) == true) goto L27;
            GLIconTextureView.m11944$$Nest$fputready(this.this$0, true);     // Catch: Throwable -> L12 Exception -> L25
            AndroidUtilities.runOnUIThread(GLIconTextureView.m11938$$Nest$fgetreadyListener(this.this$0));     // Catch: Throwable -> L12 Exception -> L25
            GLIconTextureView.m11945$$Nest$fputreadyListener(this.this$0, null);     // Catch: Throwable -> L12 Exception -> L25
        L27:
            r6 = r11;
        L105:
            if (GLIconTextureView.m11951$$Nest$mshouldSleep(this.this$0) == true) goto L30;
            long r8 = System.currentTimeMillis();     // Catch: InterruptedException -> L96 Throwable -> L12 Exception -> L14
        L33:
            if ((r8 - r6) >= GLIconTextureView.m11940$$Nest$fgettargetFrameDurationMillis(this.this$0)) goto L6;
            r8 = System.currentTimeMillis();     // Catch: InterruptedException -> L96 Throwable -> L12 Exception -> L14
            goto L33
        L30:
            Thread.sleep(100);     // Catch: InterruptedException -> L96 Throwable -> L12 Exception -> L14
        L25:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L12 Exception -> L14
        L37:
            GLIconRenderer r24 = this.this$0.mRenderer;
            if (r24 == null) goto L41;
            r24.destroy();
        L41:
            if (GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0) != null) goto L43;
            return;
        L43:
            if (GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0) == null) goto L117;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglMakeCurrent(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), r1, r1, r0);
            if (GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0) == null) goto L48;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroySurface(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0));
            GLIconTextureView.m11943$$Nest$fputmEglSurface(this.this$0, null);
        L48:
            if (GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0) == null) goto L50;
        L49:
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroyContext(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0));
            GLIconTextureView.m11941$$Nest$fputmEglContext(this.this$0, null);
        L50:
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglTerminate(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0));
            GLIconTextureView.m11942$$Nest$fputmEglDisplay(this.this$0, null);
            return;
        L117:
            return;
        L51:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L12 Exception -> L14
            GLIconTextureView r25 = this.this$0;     // Catch: Throwable -> L12 Exception -> L14
            r25.isRunning = false;     // Catch: Throwable -> L12 Exception -> L14
            GLIconRenderer r26 = r25.mRenderer;
            if (r26 == null) goto L57;
            r26.destroy();
        L57:
            if (GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0) != null) goto L59;
            return;
        L59:
            if (GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0) == null) goto L120;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglMakeCurrent(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), r1, r1, r0);
            if (GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0) == null) goto L64;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroySurface(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0));
            GLIconTextureView.m11943$$Nest$fputmEglSurface(this.this$0, null);
        L64:
            if (GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0) == null) goto L50;
        L120:
            return;
        L14:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L12
            GLIconRenderer r27 = this.this$0.mRenderer;
            if (r27 == null) goto L71;
            r27.destroy();
        L71:
            if (GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0) != null) goto L73;
            return;
        L73:
            if (GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0) == null) goto L122;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglMakeCurrent(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), r1, r1, r0);
            if (GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0) == null) goto L78;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroySurface(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0));
            GLIconTextureView.m11943$$Nest$fputmEglSurface(this.this$0, null);
        L78:
            if (GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0) == null) goto L50;
        L122:
            return;
        L12:
            th = move-exception;
            GLIconRenderer r3 = this.this$0.mRenderer;
            if (r3 == null) goto L85;
            r3.destroy();
        L85:
            if (GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0) != null) goto L87;
        L95:
            throw th;
        L87:
            if (GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0) == null) goto L95;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglMakeCurrent(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), r1, r1, r0);
            if (GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0) == null) goto L92;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroySurface(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11936$$Nest$fgetmEglSurface(this.this$0));
            GLIconTextureView.m11943$$Nest$fputmEglSurface(this.this$0, null);
        L92:
            if (GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0) == null) goto L94;
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglDestroyContext(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0), GLIconTextureView.m11934$$Nest$fgetmEglContext(this.this$0));
            GLIconTextureView.m11941$$Nest$fputmEglContext(this.this$0, null);
        L94:
            GLIconTextureView.m11933$$Nest$fgetmEgl(this.this$0).eglTerminate(GLIconTextureView.m11935$$Nest$fgetmEglDisplay(this.this$0));
            GLIconTextureView.m11942$$Nest$fputmEglDisplay(this.this$0, null);
            goto L95
        }
    }

    public static /* synthetic */ void $r8$lambda$VmcKN6RlI4is8eDR3x1bPSscmqs(GLIconTextureView r0, ValueAnimator r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$Wk7A2gzc-gyETZqTsVAyWjpmz3U, reason: not valid java name */
    public static /* synthetic */ void m11930$r8$lambda$Wk7A2gzcgyETZqTsVAyWjpmz3U(GLIconTextureView r0, ValueAnimator r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$XhnZfm-QdMSQLLhFAbIJRNQSgW8, reason: not valid java name */
    public static /* synthetic */ void m11931$r8$lambda$XhnZfmQdMSQLLhFAbIJRNQSgW8(GLIconTextureView r0, ValueAnimator r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$YtP3UISqt7hWvqgF2DOuwo4vVdI(GLIconTextureView r0, float r1, float r2, float r3, ValueAnimator r4) {
        r0.lambda$startBackAnimation$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetidleDelay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11932$$Nest$fgetidleDelay(GLIconTextureView r2) {
        return r2.idleDelay;
    }

    /* renamed from: -$$Nest$fgetmEgl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EGL10 m11933$$Nest$fgetmEgl(GLIconTextureView r0) {
        return r0.mEgl;
    }

    /* renamed from: -$$Nest$fgetmEglContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EGLContext m11934$$Nest$fgetmEglContext(GLIconTextureView r0) {
        return r0.mEglContext;
    }

    /* renamed from: -$$Nest$fgetmEglDisplay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EGLDisplay m11935$$Nest$fgetmEglDisplay(GLIconTextureView r0) {
        return r0.mEglDisplay;
    }

    /* renamed from: -$$Nest$fgetmEglSurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EGLSurface m11936$$Nest$fgetmEglSurface(GLIconTextureView r0) {
        return r0.mEglSurface;
    }

    /* renamed from: -$$Nest$fgetready, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11937$$Nest$fgetready(GLIconTextureView r0) {
        return r0.ready;
    }

    /* renamed from: -$$Nest$fgetreadyListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11938$$Nest$fgetreadyListener(GLIconTextureView r0) {
        return r0.readyListener;
    }

    /* renamed from: -$$Nest$fgetrendererChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11939$$Nest$fgetrendererChanged(GLIconTextureView r0) {
        return r0.rendererChanged;
    }

    /* renamed from: -$$Nest$fgettargetFrameDurationMillis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11940$$Nest$fgettargetFrameDurationMillis(GLIconTextureView r0) {
        return r0.targetFrameDurationMillis;
    }

    /* renamed from: -$$Nest$fputmEglContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11941$$Nest$fputmEglContext(GLIconTextureView r0, EGLContext r1) {
        r0.mEglContext = r1;
    }

    /* renamed from: -$$Nest$fputmEglDisplay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11942$$Nest$fputmEglDisplay(GLIconTextureView r0, EGLDisplay r1) {
        r0.mEglDisplay = r1;
    }

    /* renamed from: -$$Nest$fputmEglSurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11943$$Nest$fputmEglSurface(GLIconTextureView r0, EGLSurface r1) {
        r0.mEglSurface = r1;
    }

    /* renamed from: -$$Nest$fputready, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11944$$Nest$fputready(GLIconTextureView r0, boolean r1) {
        r0.ready = r1;
    }

    /* renamed from: -$$Nest$fputreadyListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11945$$Nest$fputreadyListener(GLIconTextureView r0, Runnable r1) {
        r0.readyListener = r1;
    }

    /* renamed from: -$$Nest$fputrendererChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11946$$Nest$fputrendererChanged(GLIconTextureView r0, boolean r1) {
        r0.rendererChanged = r1;
    }

    /* renamed from: -$$Nest$mcheckGlError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11947$$Nest$mcheckGlError(GLIconTextureView r0) {
        r0.checkGlError();
    }

    /* renamed from: -$$Nest$mdrawSingleFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11948$$Nest$mdrawSingleFrame(GLIconTextureView r0, float r1) {
        r0.drawSingleFrame(r1);
    }

    /* renamed from: -$$Nest$minitGL, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11949$$Nest$minitGL(GLIconTextureView r0) {
        r0.initGL();
    }

    /* renamed from: -$$Nest$minitializeRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11950$$Nest$minitializeRenderer(GLIconTextureView r0, GLIconRenderer r1) {
        r0.initializeRenderer(r1);
    }

    /* renamed from: -$$Nest$mshouldSleep, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11951$$Nest$mshouldSleep(GLIconTextureView r0) {
        return r0.shouldSleep();
    }

    public GLIconTextureView(Context r2, int r3) {
        this(r2, r3, 0);
    }

    public GLIconTextureView(Context r5, int r6, int r7) {
        super(r5);
        int r0 = 0;
        this.isRunning = false;
        this.paused = true;
        this.rendererChanged = false;
        this.dialogIsVisible = false;
        this.animationIndexes = new ArrayList();
        this.animatorSet = new AnimatorSet();
        this.idleAnimation = new AnonymousClass2(this);
        this.xUpdater2 = new GLIconTextureView$$ExternalSyntheticLambda0(this);
        this.xUpdater = new GLIconTextureView$$ExternalSyntheticLambda1(this);
        this.yUpdater = new GLIconTextureView$$ExternalSyntheticLambda2(this);
        this.type = r7;
        if (r7 == 1) goto L9;
        if (r7 == 4) goto L9;
        if (r7 == 3) goto L9;
        int r3 = 5;
    L10:
        this.animationsCount = r3;
        if (r7 != 4) goto L13;
        long r2 = 0;
    L14:
        this.idleDelay = r2;
        setOpaque(false);
        setRenderer(new GLIconRenderer(r5, r6, r7));
        initialize(r5);
        GestureDetector r62 = new GestureDetector(r5, new AnonymousClass1(this));
        this.gestureDetector = r62;
        r62.setIsLongpressEnabled(true);
    L15:
        int r52 = this.animationsCount;
        ArrayList<Integer> r63 = this.animationIndexes;
        if (r0 >= r52) goto L18;
        r63.add(Integer.valueOf(r0));
        r0 = r0 + 1;
        goto L15
    L18:
        Collections.shuffle(r63);
        return;
    L13:
        r2 = 2000;
    L9:
        r3 = 1;
        goto L10
    }

    private void checkCurrent() {
        if (this.mEglContext.equals(this.mEgl.eglGetCurrentContext()) == true) goto L5;
    L8:
        checkEglError();
        EGL10 r0 = this.mEgl;
        EGLDisplay r1 = this.mEglDisplay;
        EGLSurface r2 = this.mEglSurface;
        if (r0.eglMakeCurrent(r1, r2, r2, this.mEglContext) == false) goto L12;
        checkEglError();
        return;
    L12:
        Instance$$ExternalSyntheticBUOutline0.m("eglMakeCurrent failed ", GLUtils.getEGLErrorString(this.mEgl.eglGetError()));
        return;
    L5:
        if (this.mEglSurface.equals(this.mEgl.eglGetCurrentSurface(12377)) == false) goto L8;
    }

    private void checkEglError() {
        if (this.mEgl.eglGetError() == 12288) goto L6;
        FileLog.e("cannot swap buffers!");
        return;
    }

    private void checkGlError() {
        int r2 = this.mGl.glGetError();
        if (r2 == 0) goto L6;
        FileLog.e("GL error = 0x" + Integer.toHexString(r2));
        return;
    }

    private synchronized void drawSingleFrame(float r3) {
        monitor-enter(this);
        checkCurrent();     // Catch: Throwable -> L6
        GLIconRenderer r0 = this.mRenderer;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        r0.setDeltaTime(r3);     // Catch: Throwable -> L6
        this.mRenderer.onDrawFrame(this.mGl);     // Catch: Throwable -> L6
    L8:
        checkGlError();     // Catch: Throwable -> L6
        this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    private void flipAnimation() {
        this.animatorSet = new AnimatorSet();
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleX, 180.0f});
        r2.addUpdateListener(this.xUpdater);
        r2.setDuration(600);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.DEFAULT;
        r2.setInterpolator(r6);
        ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{180.0f, 360.0f});
        r7.addUpdateListener(this.xUpdater);
        r7.setDuration(600);
        r7.setStartDelay(2000);
        r7.setInterpolator(r6);
        this.animatorSet.playTogether(new Animator[]{r2, r7});
        this.animatorSet.addListener(new AnonymousClass5(this));
        this.animatorSet.start();
    }

    private void initGL() {
        EGL10 r1 = (EGL10) EGLContext.getEGL();
        this.mEgl = r1;
        EGLDisplay r12 = r1.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.mEglDisplay = r12;
        EGL10 r2 = this.mEgl;
        if (r12 != EGL10.EGL_NO_DISPLAY) goto L5;
        Instance$$ExternalSyntheticBUOutline0.m("eglGetDisplay failed ", GLUtils.getEGLErrorString(r2.eglGetError()));
        return;
    L5:
        if (r2.eglInitialize(r12, new int[2]) == false) goto L38;
        int[] r9 = new int[1];
        EGLConfig[] r7 = new EGLConfig[1];
        if (EmuDetector.with(getContext()).detect() == false) goto L9;
        int[] r6 = {12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12344};
    L10:
        this.eglConfig = null;
        if (this.mEgl.eglChooseConfig(this.mEglDisplay, r6, r7, 1, r9) == true) goto L13;
        Buffer$$ExternalSyntheticBUOutline4.m("eglChooseConfig failed ", GLUtils.getEGLErrorString(this.mEgl.eglGetError()));
        return;
    L13:
        if (r9[0] <= 0) goto L15;
        this.eglConfig = r7[0];
    L15:
        EGLConfig r22 = this.eglConfig;
        if (r22 == null) goto L34;
        this.mEglContext = this.mEgl.eglCreateContext(this.mEglDisplay, r22, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        checkEglError();
        this.mEglSurface = this.mEgl.eglCreateWindowSurface(this.mEglDisplay, this.eglConfig, this.mSurface, null);
        checkEglError();
        EGLSurface r13 = this.mEglSurface;
        if (r13 != null) goto L20;
    L28:
        int r0 = this.mEgl.eglGetError();
        if (r0 != 12299) goto L32;
        FileLog.e("eglCreateWindowSurface returned EGL10.EGL_BAD_NATIVE_WINDOW");
        return;
    L32:
        Instance$$ExternalSyntheticBUOutline0.m("eglCreateWindowSurface failed ", GLUtils.getEGLErrorString(r0));
        return;
    L20:
        if (r13 == EGL10.EGL_NO_SURFACE) goto L28;
        if (this.mEgl.eglMakeCurrent(this.mEglDisplay, r13, r13, this.mEglContext) == false) goto L26;
        checkEglError();
        this.mGl = (GL10) this.mEglContext.getGL();
        checkEglError();
        return;
    L26:
        Instance$$ExternalSyntheticBUOutline0.m("eglMakeCurrent failed ", GLUtils.getEGLErrorString(this.mEgl.eglGetError()));
        return;
    L34:
        GlShader$$ExternalSyntheticBUOutline1.m("eglConfig not initialized");
        return;
    L9:
        r6 = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 0, 12338, 1, 12344};
        goto L10
    L38:
        Instance$$ExternalSyntheticBUOutline0.m("eglInitialize failed ", GLUtils.getEGLErrorString(this.mEgl.eglGetError()));
    }

    private void initialize(Context r1) {
        this.targetFps = (int) AndroidUtilities.screenRefreshRate;
        setSurfaceTextureListener(this);
    }

    private synchronized void initializeRenderer(GLIconRenderer r4) {
        monitor-enter(this);
        if (r4 != null) goto L12;
    L10:
        monitor-exit(this);
        return;
    L12:
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.isRunning == false) goto L10;
        r4.onSurfaceCreated(this.mGl, this.eglConfig);     // Catch: Throwable -> L7
        r4.onSurfaceChanged(this.mGl, this.surfaceWidth, this.surfaceHeight);     // Catch: Throwable -> L7
        goto L10
    }

    private /* synthetic */ void lambda$new$1(ValueAnimator r1) {
        GLIconRenderer r0 = this.mRenderer;
        r0.angleX2 = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$new$2(ValueAnimator r1) {
        GLIconRenderer r0 = this.mRenderer;
        r0.angleX = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$new$3(ValueAnimator r1) {
        GLIconRenderer r0 = this.mRenderer;
        r0.angleY = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$startBackAnimation$0(float r1, float r2, float r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        GLIconRenderer r0 = this.mRenderer;
        r0.angleX = r1 * r42;
        r0.angleX2 = r2 * r42;
        r0.angleY = r42 * r3;
    }

    private void pullAnimation() {
        int r0 = Math.abs(Utilities.random.nextInt() % 4);
        this.animatorSet = new AnimatorSet();
        int r2 = this.type;
        if (r2 != 4) goto L6;
        float r02 = this.mRenderer.angleX;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, 360.0f + r02});
        r03.addUpdateListener(this.xUpdater);
        r03.setDuration(12000);
        r03.setInterpolator(new LinearInterpolator());
        this.animatorSet.playTogether(new Animator[]{r03});
    L18:
        this.animatorSet.addListener(new AnonymousClass4(this));
        this.animatorSet.start();
        return;
    L6:
        if (r0 != 0) goto L10;
        if (r2 == 1) goto L10;
        if (r2 == 3) goto L10;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleY, 48.0f});
        r04.addUpdateListener(this.yUpdater);
        r04.setDuration(2300);
        r04.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        ValueAnimator r6 = ValueAnimator.ofFloat(new float[]{48.0f, 0.0f});
        r6.addUpdateListener(this.yUpdater);
        r6.setDuration(500);
        r6.setStartDelay(2300);
        r6.setInterpolator(AndroidUtilities.overshootInterpolator);
        this.animatorSet.playTogether(new Animator[]{r04, r6});
    L10:
        if (r2 == 1) goto L14;
        if (r2 == 3) goto L14;
        int r1 = 485;
    L15:
        if (r0 != 2) goto L17;
        r1 = -r1;
    L17:
        float r12 = r1;
        ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleY, r12});
        r05.addUpdateListener(this.xUpdater);
        r05.setDuration(3000);
        r05.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        ValueAnimator r13 = ValueAnimator.ofFloat(new float[]{r12, 0.0f});
        r13.addUpdateListener(this.xUpdater);
        r13.setDuration(1000);
        r13.setStartDelay(3000);
        r13.setInterpolator(AndroidUtilities.overshootInterpolator);
        this.animatorSet.playTogether(new Animator[]{r05, r13});
    L14:
        r1 = 360;
        goto L15
    }

    private boolean shouldSleep() {
        if (isPaused() == false) goto L5;
        return true;
    L5:
        if (this.mRenderer == null) goto L11;
        return false;
    L11:
        return true;
    }

    private void sleepAnimation() {
        this.animatorSet = new AnimatorSet();
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleX, 184.0f});
        r2.addUpdateListener(this.xUpdater);
        r2.setDuration(600);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT;
        r2.setInterpolator(r6);
        ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleY, 50.0f});
        r7.addUpdateListener(this.yUpdater);
        r7.setDuration(600);
        r7.setInterpolator(r6);
        ValueAnimator r4 = ValueAnimator.ofFloat(new float[]{180.0f, 0.0f});
        r4.addUpdateListener(this.xUpdater);
        r4.setDuration(800);
        r4.setStartDelay(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        r4.setInterpolator(AndroidUtilities.overshootInterpolator);
        ValueAnimator r10 = ValueAnimator.ofFloat(new float[]{60.0f, 0.0f});
        r10.addUpdateListener(this.yUpdater);
        r10.setDuration(800);
        r10.setStartDelay(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        r10.setInterpolator(AndroidUtilities.overshootInterpolator);
        ValueAnimator r5 = ValueAnimator.ofFloat(new float[]{0.0f, 2.0f, -3.0f, 2.0f, -1.0f, 2.0f, -3.0f, 2.0f, -1.0f, 0.0f});
        r5.addUpdateListener(this.xUpdater2);
        r5.setDuration(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        r5.setInterpolator(new LinearInterpolator());
        this.animatorSet.playTogether(new Animator[]{r2, r7, r4, r10, r5});
        this.animatorSet.addListener(new AnonymousClass6(this));
        this.animatorSet.start();
    }

    private void slowFlipAnimation() {
        this.animatorSet = new AnimatorSet();
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{this.mRenderer.angleX, 360.0f});
        r1.addUpdateListener(this.xUpdater);
        r1.setDuration(8000);
        r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.playTogether(new Animator[]{r1});
        this.animatorSet.addListener(new AnonymousClass3(this));
        this.animatorSet.start();
    }

    public void cancelAnimatons() {
        ValueAnimator r0 = this.backAnimation;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.backAnimation.cancel();
        this.backAnimation = null;
    L5:
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L9;
        r02.removeAllListeners();
        this.animatorSet.cancel();
        this.animatorSet = null;
        return;
    }

    public void cancelIdleAnimation() {
        AndroidUtilities.cancelRunOnUIThread(this.idleAnimation);
    }

    public synchronized boolean isPaused() {
        monitor-enter(this);
        boolean r0 = this.paused;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // android.view.TextureView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        this.rendererChanged = true;
        scheduleIdleAnimation(this.idleDelay);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimatons();
        GLIconRenderer r0 = this.mRenderer;
        if (r0 == null) goto L5;
        r0.angleX = 0.0f;
        r0.angleY = 0.0f;
        r0.angleX2 = 0.0f;
    L5:
        this.attached = false;
    }

    public void onLongPress() {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
        startThread(r1, r2, r3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
        this.ready = false;
        stopThread();
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        setDimensions(r2, r3);
        GLIconRenderer r12 = this.mRenderer;
        if (r12 == null) goto L6;
        r12.onSurfaceChanged(this.mGl, r2, r3);
        return;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture r1) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (r4.getAction() != 0) goto L6;
        getParent().requestDisallowInterceptTouchEvent(true);
    L11:
        return this.gestureDetector.onTouchEvent(r4);
    L6:
        if (r4.getAction() != 3) goto L8;
    L9:
        this.touched = false;
        startBackAnimation();
        getParent().requestDisallowInterceptTouchEvent(false);
        goto L11
    L8:
        if (r4.getAction() != 1) goto L11;
        goto L9
    }

    public void scheduleIdleAnimation(long r2) {
        AndroidUtilities.cancelRunOnUIThread(this.idleAnimation);
        if (this.dialogIsVisible == false) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(this.idleAnimation, r2);
    }

    public void setBackgroundBitmap(Bitmap r1) {
        this.mRenderer.setBackground(r1);
    }

    public void setDialogVisible(boolean r3) {
        this.dialogIsVisible = r3;
        if (r3 == false) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.idleAnimation);
        startBackAnimation();
        return;
    L6:
        scheduleIdleAnimation(this.idleDelay);
    }

    public void setDimensions(int r1, int r2) {
        this.surfaceWidth = r1;
        this.surfaceHeight = r2;
    }

    public synchronized void setPaused(boolean r1) {
        monitor-enter(this);
        this.paused = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void setRenderer(GLIconRenderer r1) {
        monitor-enter(this);
        this.mRenderer = r1;     // Catch: Throwable -> L6
        this.rendererChanged = true;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public void setStarParticlesView(StarParticlesView r1) {
        this.starParticlesView = r1;
    }

    public void startBackAnimation() {
        cancelAnimatons();
        GLIconRenderer r0 = this.mRenderer;
        float r1 = r0.angleX;
        float r2 = r0.angleY;
        float r02 = r0.angleX2;
        float r3 = r1 + r2;
        ValueAnimator r4 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.backAnimation = r4;
        r4.addUpdateListener(new GLIconTextureView$$ExternalSyntheticLambda3(this, r1, r02, r2));
        this.backAnimation.setDuration(600);
        this.backAnimation.setInterpolator(new OvershootInterpolator());
        this.backAnimation.start();
        StarParticlesView r03 = this.starParticlesView;
        if (r03 == null) goto L5;
        r03.flingParticles(Math.abs(r3));
    L5:
        scheduleIdleAnimation(this.idleDelay);
    }

    public void startEnterAnimation(int r2, long r3) {
        GLIconRenderer r22 = this.mRenderer;
        if (r22 == null) goto L6;
        r22.angleX = -180.0f;
        AndroidUtilities.runOnUIThread(new AnonymousClass7(this), r3);
        return;
    }

    public void startIdleAnimation() {
        if (this.attached == true) goto L5;
        return;
    L5:
        int r0 = this.animationIndexes.get(this.animationPointer).intValue();
        int r1 = this.animationPointer + 1;
        this.animationPointer = r1;
        if (r1 < this.animationIndexes.size()) goto L8;
        Collections.shuffle(this.animationIndexes);
        this.animationPointer = 0;
    L8:
        if (r0 != 0) goto L11;
        pullAnimation();
        return;
    L11:
        if (r0 != 1) goto L15;
        slowFlipAnimation();
        return;
    L15:
        if (r0 != 2) goto L18;
        sleepAnimation();
        return;
    L18:
        flipAnimation();
    }

    public void startThread(SurfaceTexture r3, int r4, int r5) {
        this.thread = new RenderThread(this, null);
        this.mSurface = r3;
        setDimensions(r4, r5);
        this.targetFrameDurationMillis = Math.max(0, ((int) ((1.0f / this.targetFps) * 1000.0f)) - 1);
        this.thread.start();
    }

    public void stopThread() {
        if (this.thread == null) goto L6;
        this.isRunning = false;
        this.thread = null;
        return;
    }

    public void whenReady(Runnable r2) {
        if (this.ready == false) goto L6;
        r2.run();
        return;
    L6:
        this.readyListener = r2;
    }
}
