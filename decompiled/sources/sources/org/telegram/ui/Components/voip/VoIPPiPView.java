package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.CastStatusCodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.VoIPFragment;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;

/* loaded from: classes7.dex */
public class VoIPPiPView implements VoIPService.StateListener, IPipSourceDelegate, NotificationCenter.NotificationCenterDelegate {
    public static int bottomInset = 0;
    private static VoIPPiPView expandedInstance = null;
    private static VoIPPiPView instance = null;
    public static boolean switchingToPip = false;
    public static int topInset;
    int animationIndex;
    ValueAnimator animatorToCameraMini;
    ValueAnimator.AnimatorUpdateListener animatorToCameraMiniUpdater;
    boolean callingUserIsVideo;
    public final VoIPTextureView callingUserTextureView;
    ImageView closeIcon;
    Runnable collapseRunnable;
    private int currentAccount;
    boolean currentUserIsVideo;
    public final VoIPTextureView currentUserTextureView;
    ImageView enlargeIcon;
    ValueAnimator expandAnimator;
    public boolean expanded;
    private boolean expandedAnimationInProgress;
    private Runnable firstFrameCallback;
    FloatingView floatingView;
    AnimatorSet moveToBoundsAnimator;
    boolean moving;
    public final int parentHeight;
    public final int parentWidth;
    private PipSource pipSource;
    private VoIPTextureView pipTextureView;
    float[] point;
    float progressToCameraMini;
    private final RendererCommon.RendererEvents rendererEvents;
    long startTime;
    float startX;
    float startY;
    View topShadow;
    private ValueAnimator.AnimatorUpdateListener updateXlistener;
    private ValueAnimator.AnimatorUpdateListener updateYlistener;
    public WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    public FrameLayout windowView;
    private boolean windowViewSkipRender;
    public int xOffset;
    public int yOffset;

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ VoIPPiPView this$0;

        public AnonymousClass1(VoIPPiPView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VoIPPiPView.m13979$$Nest$sfgetinstance() == null) goto L6;
            FloatingView.m13984$$Nest$mexpand(VoIPPiPView.m13979$$Nest$sfgetinstance().floatingView, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ VoIPPiPView this$0;

        public AnonymousClass2(VoIPPiPView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            VoIPPiPView r0 = this.this$0;
            int r33 = (int) r32;
            r0.windowLayoutParams.x = r33;
            WindowManager r34 = VoIPPiPView.m13973$$Nest$fgetwindowManager(r0);
            VoIPPiPView r2 = this.this$0;
            AndroidUtilities.updateViewLayout(r34, r2.windowView, r2.windowLayoutParams);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$3, reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ VoIPPiPView this$0;

        public AnonymousClass3(VoIPPiPView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            VoIPPiPView r0 = this.this$0;
            int r33 = (int) r32;
            r0.windowLayoutParams.y = r33;
            WindowManager r34 = VoIPPiPView.m13973$$Nest$fgetwindowManager(r0);
            VoIPPiPView r2 = this.this$0;
            AndroidUtilities.updateViewLayout(r34, r2.windowView, r2.windowLayoutParams);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ VoIPPiPView this$0;
        final /* synthetic */ Drawable val$outerDrawable;

        public AnonymousClass4(VoIPPiPView r1, Context r2, Drawable r3) {
            this.this$0 = r1;
            this.val$outerDrawable = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            r7.save();
            r7.scale(this.this$0.floatingView.getScaleX(), this.this$0.floatingView.getScaleY(), this.this$0.floatingView.getLeft() + this.this$0.floatingView.getPivotX(), this.this$0.floatingView.getTop() + this.this$0.floatingView.getPivotY());
            this.val$outerDrawable.setBounds(this.this$0.floatingView.getLeft() - AndroidUtilities.dp(2.0f), this.this$0.floatingView.getTop() - AndroidUtilities.dp(2.0f), this.this$0.floatingView.getRight() + AndroidUtilities.dp(2.0f), this.this$0.floatingView.getBottom() + AndroidUtilities.dp(2.0f));
            this.val$outerDrawable.draw(r7);
            r7.restore();
            super.onDraw(r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$5, reason: invalid class name */
    public class AnonymousClass5 implements RendererCommon.RendererEvents {
        final /* synthetic */ VoIPPiPView this$0;

        public AnonymousClass5(VoIPPiPView r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            if (VoIPPiPView.m13969$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L6;
            VoIPPiPView.m13969$$Nest$fgetfirstFrameCallback(this.this$0).run();
            VoIPPiPView.m13975$$Nest$fputfirstFrameCallback(this.this$0, null);
            return;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$6, reason: invalid class name */
    public class AnonymousClass6 implements RendererCommon.RendererEvents {
        final /* synthetic */ VoIPPiPView this$0;

        public AnonymousClass6(VoIPPiPView r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            if (VoIPPiPView.m13969$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L6;
            VoIPPiPView.m13969$$Nest$fgetfirstFrameCallback(this.this$0).run();
            VoIPPiPView.m13975$$Nest$fputfirstFrameCallback(this.this$0, null);
            return;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    public class FloatingView extends FrameLayout {
        float bottomPadding;
        float leftPadding;
        float rightPadding;
        final /* synthetic */ VoIPPiPView this$0;
        float topPadding;
        float touchSlop;

        /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$FloatingView$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ FloatingView this$1;
            final /* synthetic */ VoIPPiPView val$this$0;

            public AnonymousClass1(FloatingView r1, VoIPPiPView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r7, Outline r8) {
                r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), (1.0f / r7.getScaleX()) * AndroidUtilities.dp(4.0f));
            }
        }

        /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$FloatingView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ FloatingView this$1;

            public AnonymousClass2(FloatingView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                VoIPPiPView.m13974$$Nest$fputexpandedAnimationInProgress(this.this$1.this$0, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.voip.VoIPPiPView$FloatingView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ FloatingView this$1;
            final /* synthetic */ boolean val$expanded;

            public static /* synthetic */ void $r8$lambda$eHMot_YV21uLqHBK_RwnMyBiONo(AnonymousClass3 r0, boolean r1) {
                r0.lambda$onAnimationEnd$0(r1);
            }

            public AnonymousClass3(FloatingView r1, boolean r2) {
                this.this$1 = r1;
                this.val$expanded = r2;
            }

            private /* synthetic */ void lambda$onAnimationEnd$0(boolean r3) {
                if (VoIPPiPView.m13979$$Nest$sfgetinstance() != null) goto L5;
                return;
            L5:
                if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() == null) goto L13;
                VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().windowView.setAlpha(0.0f);
                VoIPPiPView.m13977$$Nest$mfinishInternal(VoIPPiPView.m13978$$Nest$sfgetexpandedInstance());
                VoIPPiPView.m13974$$Nest$fputexpandedAnimationInProgress(this.this$1.this$0, false);
                if (r3 == false) goto L12;
                AndroidUtilities.runOnUIThread(this.this$1.this$0.collapseRunnable, 3000);
                return;
            L12:
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() != null) goto L5;
                return;
            L5:
                FloatingView.m13986$$Nest$mswapRender(this.this$1, VoIPPiPView.m13978$$Nest$sfgetexpandedInstance(), VoIPPiPView.m13979$$Nest$sfgetinstance());
                VoIPPiPView.m13979$$Nest$sfgetinstance().windowView.setAlpha(1.0f);
                AndroidUtilities.setPreferredMaxRefreshRate(VoIPPiPView.m13973$$Nest$fgetwindowManager(this.this$1.this$0), VoIPPiPView.m13979$$Nest$sfgetinstance().windowView, VoIPPiPView.m13979$$Nest$sfgetinstance().windowLayoutParams);
                VoIPPiPView.m13973$$Nest$fgetwindowManager(this.this$1.this$0).addView(VoIPPiPView.m13979$$Nest$sfgetinstance().windowView, VoIPPiPView.m13979$$Nest$sfgetinstance().windowLayoutParams);
                AndroidUtilities.runOnUIThread(new VoIPPiPView$FloatingView$3$$ExternalSyntheticLambda0(this, this.val$expanded), 64);
            }
        }

        public static /* synthetic */ void $r8$lambda$7dfjbpbBWrgfKFnTHKeR9LPUdeg(FloatingView r0, float r1, VoIPPiPView r2) {
            r0.lambda$expand$1(r1, r2);
        }

        /* renamed from: $r8$lambda$IozzDMM2-wo9rwKfitkKti6NbPU, reason: not valid java name */
        public static /* synthetic */ void m13982$r8$lambda$IozzDMM2wo9rwKfitkKti6NbPU(float r1, ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            float r0 = (1.0f - r22) + (r1 * r22);
            if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() == null) goto L6;
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.setScaleX(r0);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.setScaleY(r0);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.invalidate();
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.invalidateOutline();
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().windowView.invalidate();
            return;
        }

        /* renamed from: $r8$lambda$nn-UQfHzfHnDL_MoShkmrtDfByk, reason: not valid java name */
        public static /* synthetic */ void m13983$r8$lambda$nnUQfHzfHnDL_MoShkmrtDfByk(float r1, float r2, VoIPPiPView r3, ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            float r12 = (r1 * (1.0f - r42)) + (r2 * r42);
            r3.floatingView.setScaleX(r12);
            r3.floatingView.setScaleY(r12);
            r3.floatingView.invalidate();
            r3.windowView.invalidate();
            r3.floatingView.invalidateOutline();
        }

        /* renamed from: -$$Nest$mexpand, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13984$$Nest$mexpand(FloatingView r0, boolean r1) {
            r0.expand(r1);
        }

        /* renamed from: -$$Nest$mgetRelativePosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13985$$Nest$mgetRelativePosition(FloatingView r0, float[] r1) {
            r0.getRelativePosition(r1);
        }

        /* renamed from: -$$Nest$mswapRender, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13986$$Nest$mswapRender(FloatingView r0, VoIPPiPView r1, VoIPPiPView r2) {
            r0.swapRender(r1, r2);
        }

        public FloatingView(VoIPPiPView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.touchSlop = ViewConfiguration.get(r2).getScaledTouchSlop();
            setOutlineProvider(new AnonymousClass1(this, r1));
            setClipToOutline(true);
        }

        private void expand(boolean r13) {
            AndroidUtilities.cancelRunOnUIThread(this.this$0.collapseRunnable);
            if (VoIPPiPView.m13979$$Nest$sfgetinstance() != null) goto L5;
            return;
        L5:
            if (VoIPPiPView.m13968$$Nest$fgetexpandedAnimationInProgress(this.this$0) == false) goto L7;
            return;
        L7:
            if (VoIPPiPView.m13979$$Nest$sfgetinstance().expanded == r13) goto L20;
            VoIPPiPView.m13979$$Nest$sfgetinstance().expanded = r13;
            VoIPPiPView r0 = this.this$0;
            int r1 = r0.parentWidth;
            int r4 = r0.xOffset;
            float r2 = (r1 * 0.25f) + (r4 * 2);
            int r5 = r0.parentHeight;
            int r3 = r0.yOffset;
            float r6 = (r5 * 0.25f) + (r3 * 2);
            float r12 = (r1 * 0.4f) + (r4 * 2);
            float r42 = (r5 * 0.4f) + (r3 * 2);
            VoIPPiPView.m13974$$Nest$fputexpandedAnimationInProgress(r0, true);
            if (r13 == false) goto L14;
            Context r132 = VoIPPiPView.m13979$$Nest$sfgetinstance().windowView.getContext();
            VoIPPiPView r8 = this.this$0;
            WindowManager.LayoutParams r133 = VoIPPiPView.m13981$$Nest$smcreateWindowLayoutParams(r132, r8.parentWidth, r8.parentHeight, 0.4f);
            Context r9 = getContext();
            VoIPPiPView r10 = this.this$0;
            VoIPPiPView r82 = new VoIPPiPView(r9, r10.parentWidth, r10.parentHeight, true);
            getRelativePosition(this.this$0.point);
            VoIPPiPView r92 = this.this$0;
            float[] r102 = r92.point;
            float r52 = r102[0];
            float r32 = r102[1];
            WindowManager.LayoutParams r103 = r92.windowLayoutParams;
            r133.x = (int) (r103.x - ((r12 - r2) * r52));
            r133.y = (int) (r103.y - ((r42 - r6) * r32));
            AndroidUtilities.setPreferredMaxRefreshRate(VoIPPiPView.m13973$$Nest$fgetwindowManager(r92), r82.windowView, r133);
            VoIPPiPView.m13973$$Nest$fgetwindowManager(this.this$0).addView(r82.windowView, r133);
            r82.windowView.setAlpha(1.0f);
            r82.windowLayoutParams = r133;
            VoIPPiPView.m13976$$Nest$fputwindowManager(r82, VoIPPiPView.m13973$$Nest$fgetwindowManager(this.this$0));
            VoIPPiPView.m13980$$Nest$sfputexpandedInstance(r82);
            swapRender(VoIPPiPView.m13979$$Nest$sfgetinstance(), VoIPPiPView.m13978$$Nest$sfgetexpandedInstance());
            float r134 = this.this$0.floatingView.getScaleX() * 0.625f;
            r82.floatingView.setPivotX((r52 * this.this$0.parentWidth) * 0.4f);
            r82.floatingView.setPivotY((r32 * this.this$0.parentHeight) * 0.4f);
            r82.floatingView.setScaleX(r134);
            r82.floatingView.setScaleY(r134);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().topShadow.setAlpha(0.0f);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().closeIcon.setAlpha(0.0f);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().enlargeIcon.setAlpha(0.0f);
            AndroidUtilities.runOnUIThread(new VoIPPiPView$FloatingView$$ExternalSyntheticLambda0(this, r134, r82), 64);
            return;
        L14:
            if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() == null) goto L21;
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.getRelativePosition(this.this$0.point);
            float[] r93 = this.this$0.point;
            float r104 = r93[0];
            float r33 = r93[1];
            VoIPPiPView.m13979$$Nest$sfgetinstance().windowLayoutParams.x = (int) (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().windowLayoutParams.x + ((r12 - r2) * r104));
            VoIPPiPView.m13979$$Nest$sfgetinstance().windowLayoutParams.y = (int) (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().windowLayoutParams.y + ((r42 - r6) * r33));
            float r14 = this.this$0.floatingView.getScaleX() * 0.625f;
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.setPivotX((r104 * this.this$0.parentWidth) * 0.4f);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().floatingView.setPivotY((r33 * this.this$0.parentHeight) * 0.4f);
            showUi(false);
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r02.addUpdateListener(new VoIPPiPView$FloatingView$$ExternalSyntheticLambda1(r14));
            r02.setDuration(300).setInterpolator(CubicBezierInterpolator.DEFAULT);
            r02.addListener(new AnonymousClass3(this, r13));
            r02.start();
            this.this$0.expandAnimator = r02;
            return;
        L21:
            return;
        }

        private void getRelativePosition(float[] r5) {
            Point r0 = AndroidUtilities.displaySize;
            float r1 = r0.x;
            float r02 = r0.y;
            VoIPPiPView r2 = this.this$0;
            float r22 = r2.windowLayoutParams.x + r2.floatingView.getLeft();
            float r3 = this.leftPadding;
            r5[0] = (r22 - r3) / (((r1 - r3) - this.rightPadding) - this.this$0.floatingView.getMeasuredWidth());
            VoIPPiPView r23 = this.this$0;
            float r24 = r23.windowLayoutParams.y + r23.floatingView.getTop();
            float r32 = this.topPadding;
            r5[1] = (r24 - r32) / (((r02 - r32) - this.bottomPadding) - this.this$0.floatingView.getMeasuredHeight());
            r5[0] = Math.min(1.0f, Math.max(0.0f, r5[0]));
            r5[1] = Math.min(1.0f, Math.max(0.0f, r5[1]));
        }

        private /* synthetic */ void lambda$expand$1(float r4, VoIPPiPView r5) {
            if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() != null) goto L5;
            return;
        L5:
            this.this$0.windowView.setAlpha(0.0f);
            VoIPPiPView.m13973$$Nest$fgetwindowManager(this.this$0).removeView(this.this$0.windowView);     // Catch: Throwable -> L8
        L10:
            animate().cancel();
            showUi(true);
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r0.addUpdateListener(new VoIPPiPView$FloatingView$$ExternalSyntheticLambda2(r4, 1.0f, r5));
            r0.addListener(new AnonymousClass2(this));
            r0.setDuration(300).setInterpolator(CubicBezierInterpolator.DEFAULT);
            r0.start();
            this.this$0.expandAnimator = r0;
            return;
        L8:
            th = move-exception;
            FileLog.e(th);
            goto L10
        }

        private void showUi(boolean r7) {
            if (VoIPPiPView.m13978$$Nest$sfgetexpandedInstance() != null) goto L5;
            return;
        L5:
            float r6 = 0.0f;
            if (r7 == false) goto L8;
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().topShadow.setAlpha(0.0f);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().closeIcon.setAlpha(0.0f);
            VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().enlargeIcon.setAlpha(0.0f);
        L8:
            ViewPropertyAnimator r0 = VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().topShadow.animate();
            if (r7 == false) goto L11;
            float r2 = 1.0f;
        L12:
            ViewPropertyAnimator r02 = r0.alpha(r2).setDuration(300);
            CubicBezierInterpolator r4 = CubicBezierInterpolator.DEFAULT;
            r02.setInterpolator(r4).start();
            ViewPropertyAnimator r03 = VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().closeIcon.animate();
            if (r7 == false) goto L15;
            float r5 = 1.0f;
        L16:
            r03.alpha(r5).setDuration(300).setInterpolator(r4).start();
            ViewPropertyAnimator r04 = VoIPPiPView.m13978$$Nest$sfgetexpandedInstance().enlargeIcon.animate();
            if (r7 == false) goto L19;
            r6 = 1.0f;
        L19:
            r04.alpha(r6).setDuration(300).setInterpolator(r4).start();
            return;
        L15:
            r5 = 0.0f;
            goto L16
        L11:
            r2 = 0.0f;
            goto L12
        }

        private void swapRender(VoIPPiPView r3, VoIPPiPView r4) {
            r4.currentUserTextureView.setStub(r3.currentUserTextureView);
            r4.callingUserTextureView.setStub(r3.callingUserTextureView);
            r3.currentUserTextureView.renderer.release();
            r3.callingUserTextureView.renderer.release();
            EglBase r32 = VideoCapturerDevice.eglBase;
            if (r32 == null) goto L10;
            r4.currentUserTextureView.renderer.init(r32.getEglBaseContext(), null);
            r4.callingUserTextureView.renderer.init(VideoCapturerDevice.eglBase.getEglBaseContext(), VoIPPiPView.m13970$$Nest$fgetrendererEvents(this.this$0));
            if (VoIPService.getSharedInstance() == null) goto L9;
            VoIPService.getSharedInstance().setSinks(r4.currentUserTextureView.renderer, r4.callingUserTextureView.renderer);
            return;
        L9:
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            this.this$0.currentUserTextureView.setPivotX(r0.callingUserTextureView.getMeasuredWidth());
            this.this$0.currentUserTextureView.setPivotY(r0.callingUserTextureView.getMeasuredHeight());
            this.this$0.currentUserTextureView.setTranslationX(((-AndroidUtilities.dp(4.0f)) * (1.0f / getScaleX())) * this.this$0.progressToCameraMini);
            this.this$0.currentUserTextureView.setTranslationY(((-AndroidUtilities.dp(4.0f)) * (1.0f / getScaleY())) * this.this$0.progressToCameraMini);
            this.this$0.currentUserTextureView.setRoundCorners((AndroidUtilities.dp(8.0f) * (1.0f / getScaleY())) * this.this$0.progressToCameraMini);
            VoIPPiPView r0 = this.this$0;
            r0.currentUserTextureView.setScaleX(((1.0f - r0.progressToCameraMini) * 0.6f) + 0.4f);
            VoIPPiPView r02 = this.this$0;
            r02.currentUserTextureView.setScaleY(((1.0f - r02.progressToCameraMini) * 0.6f) + 0.4f);
            VoIPPiPView r03 = this.this$0;
            r03.currentUserTextureView.setAlpha(Math.min(1.0f, 1.0f - r03.progressToCameraMini));
            super.dispatchDraw(r6);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            this.leftPadding = AndroidUtilities.dp(16.0f);
            this.rightPadding = AndroidUtilities.dp(16.0f);
            this.topPadding = AndroidUtilities.dp(60.0f);
            this.bottomPadding = AndroidUtilities.dp(16.0f);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r15) {
            if (VoIPPiPView.m13968$$Nest$fgetexpandedAnimationInProgress(this.this$0) == false) goto L5;
        L66:
            return false;
        L5:
            if (VoIPPiPView.switchingToPip == true) goto L66;
            if (VoIPPiPView.m13979$$Nest$sfgetinstance() == null) goto L66;
            AndroidUtilities.cancelRunOnUIThread(this.this$0.collapseRunnable);
            float r0 = r15.getRawX();
            float r2 = r15.getRawY();
            ViewParent r3 = getParent();
            int r4 = r15.getAction();
            if (r4 != 0) goto L12;
            VoIPPiPView r152 = this.this$0;
            r152.startX = r0;
            r152.startY = r2;
            r152.startTime = System.currentTimeMillis();
            AnimatorSet r14 = this.this$0.moveToBoundsAnimator;
            if (r14 == null) goto L65;
            r14.cancel();
        L65:
            return true;
        L12:
            if (r4 == 1) goto L27;
            if (r4 != 2) goto L15;
            VoIPPiPView r153 = this.this$0;
            float r1 = r0 - r153.startX;
            float r42 = r2 - r153.startY;
            if (r153.moving == true) goto L24;
            float r154 = (r1 * r1) + (r42 * r42);
            float r6 = this.touchSlop;
            if (r154 <= (r6 * r6)) goto L24;
            if (r3 == null) goto L23;
            r3.requestDisallowInterceptTouchEvent(true);
        L23:
            VoIPPiPView r155 = this.this$0;
            r155.moving = true;
            r155.startX = r0;
            r155.startY = r2;
            r1 = 0.0f;
            r42 = 0.0f;
        L24:
            VoIPPiPView r156 = this.this$0;
            if (r156.moving == false) goto L65;
            WindowManager.LayoutParams r32 = r156.windowLayoutParams;
            r32.x = (int) (r32.x + r1);
            r32.y = (int) (r32.y + r42);
            r156.startX = r0;
            r156.startY = r2;
            WindowManager r157 = VoIPPiPView.m13973$$Nest$fgetwindowManager(r156);
            VoIPPiPView r142 = this.this$0;
            AndroidUtilities.updateViewLayout(r157, r142.windowView, r142.windowLayoutParams);
            goto L65
        L15:
            if (r4 != 3) goto L65;
        L27:
            AnimatorSet r02 = this.this$0.moveToBoundsAnimator;
            if (r02 == null) goto L31;
            r02.cancel();
        L31:
            if (r15.getAction() == 1) goto L33;
        L45:
            if (r3 == null) goto L59;
            r3.requestDisallowInterceptTouchEvent(false);
            Point r158 = AndroidUtilities.displaySize;
            int r03 = r158.x;
            int r159 = r158.y + VoIPPiPView.topInset;
            float r22 = this.topPadding;
            float r33 = this.bottomPadding;
            VoIPPiPView r43 = this.this$0;
            float r44 = r43.windowLayoutParams.x + r43.floatingView.getLeft();
            float r9 = this.this$0.floatingView.getMeasuredWidth() + r44;
            VoIPPiPView r10 = this.this$0;
            float r102 = r10.windowLayoutParams.y + r10.floatingView.getTop();
            float r11 = this.this$0.floatingView.getMeasuredHeight() + r102;
            this.this$0.moveToBoundsAnimator = new AnimatorSet();
            float r12 = this.leftPadding;
            if (r44 >= r12) goto L50;
            VoIPPiPView r04 = this.this$0;
            ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{r04.windowLayoutParams.x, r12 - r04.floatingView.getLeft()});
            r05.addUpdateListener(VoIPPiPView.m13971$$Nest$fgetupdateXlistener(this.this$0));
            this.this$0.moveToBoundsAnimator.playTogether(new Animator[]{r05});
        L53:
            if (r102 >= r22) goto L56;
            VoIPPiPView r1510 = this.this$0;
            ValueAnimator r1511 = ValueAnimator.ofFloat(new float[]{r1510.windowLayoutParams.y, r22 - r1510.floatingView.getTop()});
            r1511.addUpdateListener(VoIPPiPView.m13972$$Nest$fgetupdateYlistener(this.this$0));
            this.this$0.moveToBoundsAnimator.playTogether(new Animator[]{r1511});
        L58:
            this.this$0.moveToBoundsAnimator.setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.this$0.moveToBoundsAnimator.start();
            goto L59
        L56:
            if (r11 <= (r159 - r33)) goto L58;
            VoIPPiPView r06 = this.this$0;
            ValueAnimator r1512 = ValueAnimator.ofFloat(new float[]{r06.windowLayoutParams.y, (r159 - r06.floatingView.getMeasuredHeight()) - r33});
            r1512.addUpdateListener(VoIPPiPView.m13972$$Nest$fgetupdateYlistener(this.this$0));
            this.this$0.moveToBoundsAnimator.playTogether(new Animator[]{r1512});
            goto L58
        L50:
            if (r9 <= (r03 - this.rightPadding)) goto L53;
            VoIPPiPView r45 = this.this$0;
            ValueAnimator r07 = ValueAnimator.ofFloat(new float[]{r45.windowLayoutParams.x, (r03 - r45.floatingView.getRight()) - this.rightPadding});
            r07.addUpdateListener(VoIPPiPView.m13971$$Nest$fgetupdateXlistener(this.this$0));
            this.this$0.moveToBoundsAnimator.playTogether(new Animator[]{r07});
        L59:
            this.this$0.moving = false;
            if (VoIPPiPView.m13979$$Nest$sfgetinstance().expanded == false) goto L65;
            AndroidUtilities.runOnUIThread(this.this$0.collapseRunnable, 3000);
            goto L65
        L33:
            if (this.this$0.moving == true) goto L45;
            if ((System.currentTimeMillis() - this.this$0.startTime) >= 150) goto L45;
            Context r1513 = getContext();
            boolean r08 = r1513 instanceof LaunchActivity;
            if (r08 == true) goto L39;
        L41:
            if (r08 == false) goto L43;
            Intent r09 = new Intent(r1513, LaunchActivity.class);
            r09.setAction("voip");
            r1513.startActivity(r09);
        L43:
            this.this$0.moving = false;
            return false;
        L39:
            if (ApplicationLoader.mainInterfacePaused == true) goto L41;
            VoIPFragment.show((Activity) r1513, VoIPPiPView.m13967$$Nest$fgetcurrentAccount(this.this$0));
            goto L43
        }
    }

    /* renamed from: $r8$lambda$7mvZOeuwv-B8o8X80RDNGTwdx2s, reason: not valid java name */
    public static /* synthetic */ void m13965$r8$lambda$7mvZOeuwvB8o8X80RDNGTwdx2s(VoIPPiPView r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$7q1t5oZ8DhKBVav098VJkBjQryM(View r0) {
        VoIPService r02 = VoIPService.getSharedInstance();
        if (r02 == null) goto L6;
        r02.hangUp();
        return;
    L6:
        finish();
    }

    /* renamed from: $r8$lambda$X4LmUIjMAOz1wbAJC00NTD-HFcM, reason: not valid java name */
    public static /* synthetic */ void m13966$r8$lambda$X4LmUIjMAOz1wbAJC00NTDHFcM(VoIPPiPView r0, Context r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13967$$Nest$fgetcurrentAccount(VoIPPiPView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetexpandedAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13968$$Nest$fgetexpandedAnimationInProgress(VoIPPiPView r0) {
        return r0.expandedAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13969$$Nest$fgetfirstFrameCallback(VoIPPiPView r0) {
        return r0.firstFrameCallback;
    }

    /* renamed from: -$$Nest$fgetrendererEvents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RendererCommon.RendererEvents m13970$$Nest$fgetrendererEvents(VoIPPiPView r0) {
        return r0.rendererEvents;
    }

    /* renamed from: -$$Nest$fgetupdateXlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m13971$$Nest$fgetupdateXlistener(VoIPPiPView r0) {
        return r0.updateXlistener;
    }

    /* renamed from: -$$Nest$fgetupdateYlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m13972$$Nest$fgetupdateYlistener(VoIPPiPView r0) {
        return r0.updateYlistener;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m13973$$Nest$fgetwindowManager(VoIPPiPView r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fputexpandedAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13974$$Nest$fputexpandedAnimationInProgress(VoIPPiPView r0, boolean r1) {
        r0.expandedAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13975$$Nest$fputfirstFrameCallback(VoIPPiPView r0, Runnable r1) {
        r0.firstFrameCallback = r1;
    }

    /* renamed from: -$$Nest$fputwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13976$$Nest$fputwindowManager(VoIPPiPView r0, WindowManager r1) {
        r0.windowManager = r1;
    }

    /* renamed from: -$$Nest$mfinishInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13977$$Nest$mfinishInternal(VoIPPiPView r0) {
        r0.finishInternal();
    }

    /* renamed from: -$$Nest$sfgetexpandedInstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPPiPView m13978$$Nest$sfgetexpandedInstance() {
        return expandedInstance;
    }

    /* renamed from: -$$Nest$sfgetinstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPPiPView m13979$$Nest$sfgetinstance() {
        return instance;
    }

    /* renamed from: -$$Nest$sfputexpandedInstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13980$$Nest$sfputexpandedInstance(VoIPPiPView r0) {
        expandedInstance = r0;
    }

    /* renamed from: -$$Nest$smcreateWindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m13981$$Nest$smcreateWindowLayoutParams(Context r0, int r1, int r2, float r3) {
        return createWindowLayoutParams(r0, r1, r2, r3);
    }

    static {
    }

    public VoIPPiPView(Context r8, int r9, int r10, boolean r11) {
        this.animatorToCameraMiniUpdater = new VoIPPiPView$$ExternalSyntheticLambda0(this);
        this.point = new float[2];
        this.animationIndex = -1;
        this.collapseRunnable = new AnonymousClass1(this);
        this.updateXlistener = new AnonymousClass2(this);
        this.updateYlistener = new AnonymousClass3(this);
        this.rendererEvents = new AnonymousClass6(this);
        this.parentWidth = r9;
        this.parentHeight = r10;
        float r102 = r10 * 0.4f;
        this.yOffset = ((int) ((r102 * 1.05f) - r102)) / 2;
        float r92 = r9 * 0.4f;
        this.xOffset = ((int) ((1.05f * r92) - r92)) / 2;
        AnonymousClass4 r103 = new AnonymousClass4(this, r8, ContextCompat.getDrawable(r8, R.drawable.calls_pip_outershadow));
        this.windowView = r103;
        r103.setWillNotDraw(false);
        FrameLayout r104 = this.windowView;
        int r0 = this.xOffset;
        int r2 = this.yOffset;
        r104.setPadding(r0, r2, r0, r2);
        this.floatingView = new FloatingView(this, r8);
        VoIPTextureView r105 = new VoIPTextureView(r8, false, true);
        this.callingUserTextureView = r105;
        r105.scaleType = VoIPTextureView.SCALE_TYPE_NONE;
        VoIPTextureView r22 = new VoIPTextureView(r8, false, true);
        this.currentUserTextureView = r22;
        r22.renderer.setMirror(true);
        this.floatingView.addView(r105);
        this.floatingView.addView(r22);
        this.floatingView.setBackgroundColor(-7829368);
        this.windowView.addView(this.floatingView);
        this.windowView.setClipChildren(false);
        this.windowView.setClipToPadding(false);
        if (r11 == false) goto L5;
        View r106 = new View(r8);
        this.topShadow = r106;
        r106.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ColorUtils.setAlphaComponent(-16777216, 76), 0}));
        this.floatingView.addView(this.topShadow, -1, AndroidUtilities.dp(60.0f));
        ImageView r93 = new ImageView(r8);
        this.closeIcon = r93;
        r93.setImageResource(R.drawable.pip_close);
        this.closeIcon.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.closeIcon.setContentDescription(LocaleController.getString(R.string.Close));
        this.floatingView.addView(this.closeIcon, LayoutHelper.createFrame(40, 40.0f, 53, 4.0f, 4.0f, 4.0f, 0.0f));
        ImageView r94 = new ImageView(r8);
        this.enlargeIcon = r94;
        r94.setImageResource(R.drawable.pip_enlarge);
        this.enlargeIcon.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.enlargeIcon.setContentDescription(LocaleController.getString(R.string.Open));
        this.floatingView.addView(this.enlargeIcon, LayoutHelper.createFrame(40, 40.0f, 51, 4.0f, 4.0f, 4.0f, 0.0f));
        this.closeIcon.setOnClickListener(new VoIPPiPView$$ExternalSyntheticLambda1());
        this.enlargeIcon.setOnClickListener(new VoIPPiPView$$ExternalSyntheticLambda2(this, r8));
    L5:
        VoIPService r82 = VoIPService.getSharedInstance();
        if (r82 == null) goto L8;
        r82.registerStateListener(this);
    L8:
        updateViewState();
    }

    private static WindowManager.LayoutParams createWindowLayoutParams(Context r5, int r6, int r7, float r8) {
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams();
        float r72 = r7;
        float r2 = r72 * 0.4f;
        float r62 = r6;
        float r1 = 0.4f * r62;
        r0.height = (int) ((r72 * r8) + ((((int) ((r2 * 1.05f) - r2)) / 2) * 2));
        r0.width = (int) ((r62 * r8) + ((((int) ((1.05f * r1) - r1)) / 2) * 2));
        r0.gravity = 51;
        r0.format = -3;
        if (AndroidUtilities.checkInlinePermissions(r5) == true) goto L5;
        r0.type = 99;
    L9:
        r0.flags = 16778120;
        return r0;
    L5:
        if (Build.VERSION.SDK_INT < 26) goto L7;
        r0.type = 2038;
        goto L9
    L7:
        r0.type = CastStatusCodes.NOT_ALLOWED;
        goto L9
    }

    public static void finish() {
        if (switchingToPip == false) goto L5;
        return;
    L5:
        VoIPPiPView r0 = expandedInstance;
        if (r0 == null) goto L8;
        r0.finishInternal();
    L8:
        VoIPPiPView r02 = instance;
        if (r02 == null) goto L11;
        r02.finishInternal();
    L11:
        expandedInstance = null;
        instance = null;
    }

    private void finishInternal() {
        this.currentUserTextureView.renderer.release();
        this.callingUserTextureView.renderer.release();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L5;
        r0.unregisterStateListener(this);
    L5:
        this.windowView.setVisibility(8);
        if (this.windowView.getParent() == null) goto L12;
        FloatingView.m13985$$Nest$mgetRelativePosition(this.floatingView, this.point);
        float r02 = Math.min(1.0f, Math.max(0.0f, this.point[0]));
        float r2 = Math.min(1.0f, Math.max(0.0f, this.point[1]));
        ApplicationLoader.applicationContext.getSharedPreferences("voippipconfig", 0).edit().putFloat("relativeX", r02).putFloat("relativeY", r2).apply();
        this.windowManager.removeView(this.windowView);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        FileLog.e(th);
    L12:
        PipSource r03 = this.pipSource;
        if (r03 == null) goto L15;
        r03.destroy();
        this.pipSource = null;
    L15:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didEndCall);
    }

    public static VoIPPiPView getInstance() {
        VoIPPiPView r0 = expandedInstance;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return instance;
    }

    public static boolean isExpanding() {
        return instance.expanded;
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r1) {
        this.progressToCameraMini = ((Float) r1.getAnimatedValue()).floatValue();
        this.floatingView.invalidate();
    }

    private /* synthetic */ void lambda$new$2(Context r2, View r3) {
        boolean r32 = r2 instanceof LaunchActivity;
        if (r32 == true) goto L5;
    L8:
        if (r32 == false) goto L11;
        Intent r1 = new Intent(r2, LaunchActivity.class);
        r1.setAction("voip");
        r2.startActivity(r1);
        return;
    L11:
        return;
    L5:
        if (ApplicationLoader.mainInterfacePaused == true) goto L8;
        VoIPFragment.show((Activity) r2, this.currentAccount);
    }

    public static void prepareForTransition() {
        if (expandedInstance == null) goto L6;
        instance.expandAnimator.cancel();
        return;
    }

    private void setRelativePosition(float r10, float r11) {
        Point r0 = AndroidUtilities.displaySize;
        float r1 = r0.x;
        float r02 = r0.y;
        float r3 = AndroidUtilities.dp(16.0f);
        float r4 = AndroidUtilities.dp(16.0f);
        float r5 = AndroidUtilities.dp(60.0f);
        float r2 = AndroidUtilities.dp(16.0f);
        float r6 = this.parentWidth * 0.25f;
        float r8 = this.parentHeight * 0.25f;
        if (this.floatingView.getMeasuredWidth() == 0) goto L7;
        r6 = this.floatingView.getMeasuredWidth();
    L7:
        if (this.floatingView.getMeasuredWidth() == 0) goto L10;
        r8 = this.floatingView.getMeasuredHeight();
    L10:
        WindowManager.LayoutParams r7 = this.windowLayoutParams;
        r7.x = (int) ((r10 * (((r1 - r3) - r4) - r6)) - (this.xOffset - r3));
        r7.y = (int) ((r11 * (((r02 - r5) - r2) - r8)) - (this.yOffset - r5));
        AndroidUtilities.updateViewLayout(this.windowManager, this.windowView, r7);
    }

    public static void show(Activity r4, int r5, int r6, int r7, int r8) {
        if (instance == null) goto L5;
        return;
    L5:
        if (VideoCapturerDevice.eglBase == null) goto L32;
        WindowManager.LayoutParams r0 = createWindowLayoutParams(r4, r6, r7, 0.25f);
        instance = new VoIPPiPView(r4, r6, r7, false);
        if (AndroidUtilities.checkInlinePermissions(r4) == false) goto L10;
        WindowManager r62 = (WindowManager) ApplicationLoader.applicationContext.getSystemService("window");
    L11:
        VoIPPiPView r72 = instance;
        r72.currentAccount = r5;
        r72.windowManager = r62;
        r72.windowLayoutParams = r0;
        SharedPreferences r52 = ApplicationLoader.applicationContext.getSharedPreferences("voippipconfig", 0);
        float r73 = r52.getFloat("relativeX", 1.0f);
        float r53 = r52.getFloat("relativeY", 0.0f);
        instance.setRelativePosition(r73, r53);
        NotificationCenter.getGlobalInstance().addObserver(instance, NotificationCenter.didEndCall);
        r62.addView(instance.windowView, r0);
        instance.currentUserTextureView.renderer.init(VideoCapturerDevice.eglBase.getEglBaseContext(), null);
        instance.callingUserTextureView.renderer.init(VideoCapturerDevice.eglBase.getEglBaseContext(), instance.rendererEvents);
        if (r8 != 0) goto L16;
        instance.windowView.setScaleX(0.5f);
        instance.windowView.setScaleY(0.5f);
        instance.windowView.setAlpha(0.0f);
        instance.windowView.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).start();
        if (VoIPService.getSharedInstance() == null) goto L20;
        VoIPService r63 = VoIPService.getSharedInstance();
        VoIPPiPView r74 = instance;
        r63.setSinks(r74.currentUserTextureView.renderer, r74.callingUserTextureView.renderer);
    L20:
        VoIPService r64 = VoIPService.getSharedInstance();
        if (r64 != null) goto L23;
        return;
    L23:
        if (r64.getRemoteVideoState() == 2) goto L25;
        return;
    L25:
        if (PipUtils.checkPermissions(r4) != 1) goto L31;
        VoIPPiPView r65 = instance;
        r65.pipSource = new PipSource.Builder(r4, r65).setTagPrefix("voip-pip").setPriority(1).setContentView(instance.callingUserTextureView.renderer).setPlaceholderView(instance.callingUserTextureView.getPlaceholderView()).build();
        return;
    L31:
        return;
    L16:
        if (r8 != 1) goto L20;
        instance.windowView.setAlpha(0.0f);
        if (VoIPService.getSharedInstance() == null) goto L20;
        VoIPService r66 = VoIPService.getSharedInstance();
        VoIPPiPView r75 = instance;
        r66.setBackgroundSinks(r75.currentUserTextureView.renderer, r75.callingUserTextureView.renderer);
        goto L20
    L10:
        r62 = (WindowManager) r4.getSystemService("window");
        goto L11
    }

    private void updateViewState() {
        if (this.floatingView.getMeasuredWidth() == 0) goto L5;
        boolean r0 = true;
    L6:
        boolean r3 = this.callingUserIsVideo;
        VoIPService r4 = VoIPService.getSharedInstance();
        float r6 = 1.0f;
        if (r4 != null) goto L9;
    L20:
        boolean r42 = this.callingUserIsVideo;
        if (r0 == true) goto L27;
        if (r42 == true) goto L25;
        r6 = 0.0f;
    L25:
        this.progressToCameraMini = r6;
        return;
    L27:
        if (r3 == r42) goto L37;
        ValueAnimator r02 = this.animatorToCameraMini;
        if (r02 == null) goto L31;
        r02.cancel();
    L31:
        float r03 = this.progressToCameraMini;
        if (this.callingUserIsVideo == true) goto L35;
        r6 = 0.0f;
    L35:
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{r03, r6});
        this.animatorToCameraMini = r04;
        r04.addUpdateListener(this.animatorToCameraMiniUpdater);
        this.animatorToCameraMini.setDuration(300).setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorToCameraMini.start();
        return;
    L37:
        return;
    L9:
        if (r4.getRemoteVideoState() != 2) goto L11;
        boolean r7 = true;
    L12:
        this.callingUserIsVideo = r7;
        if (r4.getVideoState(false) != 2) goto L15;
    L18:
        boolean r72 = true;
    L19:
        this.currentUserIsVideo = r72;
        this.currentUserTextureView.renderer.setMirror(r4.isFrontFaceCamera());
        this.currentUserTextureView.setIsScreencast(r4.isScreencast());
        this.currentUserTextureView.setScreenshareMiniProgress(1.0f, false);
        goto L20
    L15:
        if (r4.getVideoState(false) == 1) goto L18;
        r72 = false;
        goto L19
    L11:
        r7 = false;
        goto L12
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.didEndCall) goto L6;
        finish();
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraSwitch(boolean r1) {
        updateViewState();
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onMediaStateUpdated(int r2, int r3) {
        VoIPService r22 = VoIPService.getSharedInstance();
        if (r22 != null) goto L5;
    L13:
        PipSource r23 = this.pipSource;
        if (r23 == null) goto L16;
        r23.destroy();
        this.pipSource = null;
    L16:
        updateViewState();
        return;
    L5:
        if (r22.getRemoteVideoState() != 2) goto L13;
        Context r24 = instance.windowView.getContext();
        if (this.pipSource != null) goto L16;
        if (PipUtils.checkPermissions(r24) != 1) goto L16;
        if ((r24 instanceof Activity) == false) goto L16;
        this.pipSource = new PipSource.Builder((Activity) r24, this).setTagPrefix("voip-pip").setPriority(1).setContentView(this.callingUserTextureView.renderer).setPlaceholderView(this.callingUserTextureView.getPlaceholderView()).build();
        goto L16
    }

    public void onPause() {
        if (this.windowLayoutParams.type != 99) goto L8;
        VoIPService r0 = VoIPService.getSharedInstance();
        if (this.currentUserIsVideo == false) goto L9;
        r0.setVideoState(false, 1);
        return;
    L9:
        return;
    }

    public void onResume() {
        VoIPService r3 = VoIPService.getSharedInstance();
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.getVideoState(false) != 1) goto L9;
        r3.setVideoState(false, 2);
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onScreenOnChange(boolean r4) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r4 == false) goto L8;
    L11:
        if (r4 == true) goto L13;
        return;
    L13:
        if (r0.getVideoState(false) != 1) goto L17;
        r0.setVideoState(false, 2);
        return;
    L17:
        return;
    L8:
        if (this.currentUserIsVideo == false) goto L11;
        r0.setVideoState(false, 1);
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onSignalBarsCountChanged(int r1) {
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r4) {
        if (r4 != 11) goto L5;
    L10:
        AndroidUtilities.runOnUIThread(new VoIPPiPView$$ExternalSyntheticLambda3(), 200);
    L11:
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L16;
        finish();
        return;
    L16:
        if (r4 == 3) goto L18;
    L21:
        updateViewState();
        return;
    L18:
        if (r0.isVideoAvailable() == true) goto L21;
        finish();
        return;
    L5:
        if (r4 == 17) goto L10;
        if (r4 == 4) goto L10;
        if (r4 != 10) goto L11;
        goto L10
    }

    public void onTransitionEnd() {
        if (VoIPService.getSharedInstance() == null) goto L6;
        VoIPService.getSharedInstance().swapSinks();
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onVideoAvailableChange(boolean r1) {
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public View pipCreatePictureInPictureView() {
        VoIPTextureView r0 = new VoIPTextureView(this.callingUserTextureView.getContext(), false, true, false, false);
        this.pipTextureView = r0;
        r0.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        this.pipTextureView.renderer.setEnableHardwareScaler(true);
        this.pipTextureView.renderer.setRotateTextureWithScreen(true);
        VoIPTextureView r02 = this.pipTextureView;
        r02.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        r02.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass5(this));
        View r03 = this.pipTextureView.backgroundView;
        if (r03 == null) goto L6;
        r03.setVisibility(8);
    L6:
        return this.pipTextureView;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePictureInPictureViewBitmap() {
        VoIPTextureView r0 = this.pipTextureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.renderer.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.pipTextureView.renderer.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePrimaryWindowViewBitmap() {
        VoIPTextureView r0 = this.callingUserTextureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.renderer.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.callingUserTextureView.renderer.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipHidePrimaryWindowView(Runnable r3) {
        this.firstFrameCallback = r3;
        VoIPTextureView r32 = this.callingUserTextureView;
        if (r32 == null) goto L5;
        r32.renderer.clearFirstFrame();
    L5:
        VoIPService r33 = VoIPService.getSharedInstance();
        if (r33 == null) goto L8;
        r33.setSinks(this.currentUserTextureView.renderer, this.pipTextureView.renderer);
    L8:
        this.windowViewSkipRender = true;
        this.windowManager.removeView(this.windowView);
        this.windowView.invalidate();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipShowPrimaryWindowView(Runnable r3) {
        this.firstFrameCallback = r3;
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        VoIPTextureView r32 = this.pipTextureView;
        if (r32 == null) goto L5;
        r32.renderer.release();
        this.pipTextureView = null;
    L5:
        this.windowViewSkipRender = false;
        this.windowView.invalidate();
        VoIPService r33 = VoIPService.getSharedInstance();
        if (r33 == null) goto L9;
        r33.setSinks(this.currentUserTextureView.renderer, this.callingUserTextureView.renderer);
        return;
    }
}
