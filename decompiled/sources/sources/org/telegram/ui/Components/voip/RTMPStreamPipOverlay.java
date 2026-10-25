package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.GestureDetectorCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;
import org.telegram.ui.LaunchActivity;
import org.webrtc.RendererCommon;

/* loaded from: classes3.dex */
public class RTMPStreamPipOverlay implements NotificationCenter.NotificationCenterDelegate, IPipSourceDelegate {
    private static final FloatPropertyCompat<RTMPStreamPipOverlay> PIP_X_PROPERTY = null;
    private static final FloatPropertyCompat<RTMPStreamPipOverlay> PIP_Y_PROPERTY = null;

    @SuppressLint({"StaticFieldLeak"})
    private static RTMPStreamPipOverlay instance;
    private AccountInstance accountInstance;
    private Float aspectRatio;
    private BackupImageView avatarImageView;
    private TLRPC.GroupCallParticipant boundParticipant;
    private boolean boundPresentation;
    private CellFlickerDrawable cellFlickerDrawable;
    private View consumingChild;
    private FrameLayout contentFrameLayout;
    private ViewGroup contentView;
    private FrameLayout controlsView;
    private Runnable dismissControlsCallback;
    private Runnable firstFrameCallback;
    private boolean firstFrameRendered;
    private View flickerView;
    private GestureDetectorCompat gestureDetector;
    private boolean isScrollDisallowed;
    private boolean isScrolling;
    private boolean isShowingControls;
    private boolean isVisible;
    private float maxScaleFactor;
    private float minScaleFactor;
    private int pipHeight;
    private PipSource pipSource;
    private VoIPTextureView pipTextureView;
    private int pipWidth;
    private float pipX;
    private SpringAnimation pipXSpring;
    private float pipY;
    private SpringAnimation pipYSpring;
    private boolean placeholderShown;
    private boolean postedDismissControls;
    private ValueAnimator scaleAnimator;
    private float scaleFactor;
    private ScaleGestureDetector scaleGestureDetector;
    private VoIPTextureView textureView;
    private WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private boolean windowViewSkipRender;

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass1(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            RTMPStreamPipOverlay.m13934$$Nest$fputscaleAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass10(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1, boolean r2) {
            if (RTMPStreamPipOverlay.m13906$$Nest$fgetpipSource(this.this$0) == null) goto L6;
            RTMPStreamPipOverlay.m13906$$Nest$fgetpipSource(this.this$0).invalidatePosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 implements RendererCommon.RendererEvents {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass11(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            if (RTMPStreamPipOverlay.m13898$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L6;
            RTMPStreamPipOverlay.m13898$$Nest$fgetfirstFrameCallback(this.this$0).run();
            RTMPStreamPipOverlay.m13923$$Nest$fputfirstFrameCallback(this.this$0, null);
            return;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass2(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            RTMPStreamPipOverlay.m13918$$Nest$fgetwindowManager(this.this$0).removeViewImmediate(RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0));
            RTMPStreamPipOverlay.m13916$$Nest$fgettextureView(this.this$0).renderer.release();
            RTMPStreamPipOverlay.m13921$$Nest$fputboundParticipant(this.this$0, null);
            RTMPStreamPipOverlay.m13932$$Nest$fputplaceholderShown(this.this$0, true);
            RTMPStreamPipOverlay.m13924$$Nest$fputfirstFrameRendered(this.this$0, false);
            RTMPStreamPipOverlay.m13922$$Nest$fputconsumingChild(this.this$0, null);
            RTMPStreamPipOverlay.m13926$$Nest$fputisScrolling(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements ScaleGestureDetector.OnScaleGestureListener {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$3$1, reason: invalid class name */
        public class AnonymousClass1 implements DynamicAnimation.OnAnimationEndListener {
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ List val$springs;

            public AnonymousClass1(AnonymousClass3 r1, List r2) {
                this.this$1 = r1;
                this.val$springs = r2;
            }

            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation r1, boolean r2, float r3, float r4) {
                r1.removeEndListener(this);
                this.val$springs.add((SpringAnimation) r1);
                if (this.val$springs.size() != 2) goto L6;
                AnonymousClass3.m13941$$Nest$mupdateLayout(this.this$1);
                return;
            }
        }

        /* renamed from: $r8$lambda$B694T_cv2uMOgLg0-WRxDxdCmCg, reason: not valid java name */
        public static /* synthetic */ void m13940$r8$lambda$B694T_cv2uMOgLg0WRxDxdCmCg(AnonymousClass3 r0) {
            r0.lambda$onScale$0();
        }

        /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13941$$Nest$mupdateLayout(AnonymousClass3 r0) {
            r0.updateLayout();
        }

        public AnonymousClass3(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScale$0() {
            RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).invalidate();
            if (RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).isInLayout() == false) goto L5;
            return;
        L5:
            RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).requestLayout();
            RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0).requestLayout();
            RTMPStreamPipOverlay.m13916$$Nest$fgettextureView(this.this$0).requestLayout();
        }

        private void updateLayout() {
            RTMPStreamPipOverlay r0 = this.this$0;
            WindowManager.LayoutParams r1 = RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(r0);
            int r2 = (int) (RTMPStreamPipOverlay.m13938$$Nest$mgetSuggestedWidth(this.this$0) * RTMPStreamPipOverlay.m13914$$Nest$fgetscaleFactor(this.this$0));
            r1.width = r2;
            RTMPStreamPipOverlay.m13929$$Nest$fputpipWidth(r0, r2);
            RTMPStreamPipOverlay r02 = this.this$0;
            WindowManager.LayoutParams r12 = RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(r02);
            int r22 = (int) (RTMPStreamPipOverlay.m13937$$Nest$mgetSuggestedHeight(this.this$0) * RTMPStreamPipOverlay.m13914$$Nest$fgetscaleFactor(this.this$0));
            r12.height = r22;
            RTMPStreamPipOverlay.m13928$$Nest$fputpipHeight(r02, r22);
            AndroidUtilities.updateViewLayout(RTMPStreamPipOverlay.m13918$$Nest$fgetwindowManager(this.this$0), RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0), RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0));
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector r6) {
            RTMPStreamPipOverlay r0 = this.this$0;
            RTMPStreamPipOverlay.m13935$$Nest$fputscaleFactor(r0, MathUtils.clamp(RTMPStreamPipOverlay.m13914$$Nest$fgetscaleFactor(r0) * r6.getScaleFactor(), RTMPStreamPipOverlay.m13904$$Nest$fgetminScaleFactor(this.this$0), RTMPStreamPipOverlay.m13903$$Nest$fgetmaxScaleFactor(this.this$0)));
            RTMPStreamPipOverlay.m13929$$Nest$fputpipWidth(this.this$0, (int) (RTMPStreamPipOverlay.m13938$$Nest$mgetSuggestedWidth(r0) * RTMPStreamPipOverlay.m13914$$Nest$fgetscaleFactor(this.this$0)));
            RTMPStreamPipOverlay.m13928$$Nest$fputpipHeight(this.this$0, (int) (RTMPStreamPipOverlay.m13937$$Nest$mgetSuggestedHeight(r0) * RTMPStreamPipOverlay.m13914$$Nest$fgetscaleFactor(this.this$0)));
            AndroidUtilities.runOnUIThread(new RTMPStreamPipOverlay$3$$ExternalSyntheticLambda0(this));
            SpringForce r02 = RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).setStartValue(RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0)).getSpring();
            float r1 = r6.getFocusX();
            int r2 = AndroidUtilities.displaySize.x;
            if (r1 < (r2 / 2.0f)) goto L5;
            float r12 = (r2 - RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L6:
            r02.setFinalPosition(r12);
            if (RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).isRunning() == true) goto L9;
            RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).start();
        L9:
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).setStartValue(RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(r6.getFocusY() - (RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0) / 2.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            if (RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L14;
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L14:
            return true;
        L5:
            r12 = AndroidUtilities.dp(16.0f);
            goto L6
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector r4) {
            if (RTMPStreamPipOverlay.m13901$$Nest$fgetisScrolling(this.this$0) == false) goto L5;
            RTMPStreamPipOverlay.m13926$$Nest$fputisScrolling(this.this$0, false);
        L5:
            RTMPStreamPipOverlay.m13925$$Nest$fputisScrollDisallowed(this.this$0, true);
            RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0).width = (int) (RTMPStreamPipOverlay.m13938$$Nest$mgetSuggestedWidth(this.this$0) * RTMPStreamPipOverlay.m13903$$Nest$fgetmaxScaleFactor(this.this$0));
            RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0).height = (int) (RTMPStreamPipOverlay.m13937$$Nest$mgetSuggestedHeight(this.this$0) * RTMPStreamPipOverlay.m13903$$Nest$fgetmaxScaleFactor(this.this$0));
            AndroidUtilities.updateViewLayout(RTMPStreamPipOverlay.m13918$$Nest$fgetwindowManager(this.this$0), RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0), RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0));
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector r4) {
            if (RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).isRunning() == false) goto L5;
        L9:
            ArrayList r42 = new ArrayList();
            AnonymousClass1 r0 = new AnonymousClass1(this, r42);
            boolean r1 = RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).isRunning();
            RTMPStreamPipOverlay r2 = this.this$0;
            if (r1 == true) goto L12;
            r42.add(RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(r2));
        L13:
            boolean r12 = RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).isRunning();
            RTMPStreamPipOverlay r3 = this.this$0;
            if (r12 == true) goto L17;
            r42.add(RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(r3));
            return;
        L17:
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(r3).addEndListener(r0);
            return;
        L12:
            RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(r2).addEndListener(r0);
            goto L13
        L5:
            if (RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L9;
            updateLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends GestureDetector.SimpleOnGestureListener {
        private float startPipX;
        private float startPipY;
        final /* synthetic */ RTMPStreamPipOverlay this$0;
        final /* synthetic */ int val$touchSlop;

        public AnonymousClass4(RTMPStreamPipOverlay r1, int r2) {
            this.this$0 = r1;
            this.val$touchSlop = r2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r5) {
            if (RTMPStreamPipOverlay.m13902$$Nest$fgetisShowingControls(this.this$0) == false) goto L12;
            int r0 = 1;
        L6:
            if (r0 >= RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).getChildCount()) goto L12;
            View r2 = RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).getChildAt(r0);
            if (r2.dispatchTouchEvent(r5) == true) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            RTMPStreamPipOverlay.m13922$$Nest$fputconsumingChild(this.this$0, r2);
            return true;
        L12:
            this.startPipX = RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0);
            this.startPipY = RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            if (RTMPStreamPipOverlay.m13901$$Nest$fgetisScrolling(this.this$0) == true) goto L5;
            return false;
        L5:
            if (RTMPStreamPipOverlay.m13900$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            SpringForce r42 = RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).setStartVelocity(r6).setStartValue(RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0)).getSpring();
            float r52 = (RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0) + (RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0) / 2.0f)) + (r6 / 7.0f);
            int r0 = AndroidUtilities.displaySize.x;
            if (r52 < (r0 / 2.0f)) goto L9;
            float r53 = (r0 - RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L10:
            r42.setFinalPosition(r53);
            RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).start();
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).setStartVelocity(r6).setStartValue(RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0) + (r7 / 10.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L9:
            r53 = AndroidUtilities.dp(16.0f);
            goto L10
        L14:
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            if (RTMPStreamPipOverlay.m13901$$Nest$fgetisScrolling(this.this$0) == true) goto L14;
            if (RTMPStreamPipOverlay.m13913$$Nest$fgetscaleAnimator(this.this$0) != null) goto L14;
            if (RTMPStreamPipOverlay.m13900$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            if (Math.abs(r6) < this.val$touchSlop) goto L11;
        L12:
            RTMPStreamPipOverlay.m13926$$Nest$fputisScrolling(this.this$0, true);
            RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).cancel();
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).cancel();
            goto L14
        L11:
            if (Math.abs(r7) >= this.val$touchSlop) goto L12;
        L14:
            if (RTMPStreamPipOverlay.m13901$$Nest$fgetisScrolling(this.this$0) == false) goto L16;
            WindowManager.LayoutParams r62 = RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0);
            RTMPStreamPipOverlay r72 = this.this$0;
            float r0 = (this.startPipX + r5.getRawX()) - r4.getRawX();
            RTMPStreamPipOverlay.m13930$$Nest$fputpipX(r72, r0);
            r62.x = (int) r0;
            WindowManager.LayoutParams r63 = RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0);
            RTMPStreamPipOverlay r73 = this.this$0;
            float r02 = (this.startPipY + r5.getRawY()) - r4.getRawY();
            RTMPStreamPipOverlay.m13931$$Nest$fputpipY(r73, r02);
            r63.y = (int) r02;
            AndroidUtilities.updateViewLayout(RTMPStreamPipOverlay.m13918$$Nest$fgetwindowManager(this.this$0), RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0), RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0));
        L16:
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r4) {
            if (RTMPStreamPipOverlay.m13913$$Nest$fgetscaleAnimator(this.this$0) == null) goto L6;
            return true;
        L6:
            if (RTMPStreamPipOverlay.m13912$$Nest$fgetpostedDismissControls(this.this$0) == false) goto L8;
            AndroidUtilities.cancelRunOnUIThread(RTMPStreamPipOverlay.m13897$$Nest$fgetdismissControlsCallback(this.this$0));
            RTMPStreamPipOverlay.m13933$$Nest$fputpostedDismissControls(this.this$0, false);
        L8:
            RTMPStreamPipOverlay.m13927$$Nest$fputisShowingControls(this.this$0, !RTMPStreamPipOverlay.m13902$$Nest$fgetisShowingControls(r4));
            RTMPStreamPipOverlay r42 = this.this$0;
            RTMPStreamPipOverlay.m13939$$Nest$mtoggleControls(r42, RTMPStreamPipOverlay.m13902$$Nest$fgetisShowingControls(r42));
            if (RTMPStreamPipOverlay.m13902$$Nest$fgetisShowingControls(this.this$0) == true) goto L11;
        L13:
            return true;
        L11:
            if (RTMPStreamPipOverlay.m13912$$Nest$fgetpostedDismissControls(this.this$0) == true) goto L13;
            AndroidUtilities.runOnUIThread(RTMPStreamPipOverlay.m13897$$Nest$fgetdismissControlsCallback(this.this$0), 2500);
            RTMPStreamPipOverlay.m13933$$Nest$fputpostedDismissControls(this.this$0, true);
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends FrameLayout {
        private Path path;
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass5(RTMPStreamPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r10) {
            int r0 = r10.getAction();
            if (RTMPStreamPipOverlay.m13894$$Nest$fgetconsumingChild(this.this$0) == null) goto L10;
            MotionEvent r1 = MotionEvent.obtain(r10);
            r1.offsetLocation(RTMPStreamPipOverlay.m13894$$Nest$fgetconsumingChild(this.this$0).getX(), RTMPStreamPipOverlay.m13894$$Nest$fgetconsumingChild(this.this$0).getY());
            boolean r4 = RTMPStreamPipOverlay.m13894$$Nest$fgetconsumingChild(this.this$0).dispatchTouchEvent(r10);
            r1.recycle();
            if (r0 == 1) goto L7;
            if (r0 == 3) goto L7;
        L8:
            if (r4 == false) goto L10;
            return true;
        L7:
            RTMPStreamPipOverlay.m13922$$Nest$fputconsumingChild(this.this$0, null);
        L10:
            MotionEvent r12 = MotionEvent.obtain(r10);
            r12.offsetLocation(r10.getRawX() - r10.getX(), r10.getRawY() - r10.getY());
            boolean r42 = RTMPStreamPipOverlay.m13915$$Nest$fgetscaleGestureDetector(this.this$0).onTouchEvent(r12);
            r12.recycle();
            if (RTMPStreamPipOverlay.m13915$$Nest$fgetscaleGestureDetector(this.this$0).isInProgress() == false) goto L13;
        L15:
            boolean r102 = false;
        L16:
            if (r0 == 1) goto L18;
            if (r0 == 3) goto L18;
        L28:
            if (r42 == true) goto L32;
            if (r102 == true) goto L32;
            return false;
        L32:
            return true;
        L18:
            RTMPStreamPipOverlay.m13926$$Nest$fputisScrolling(this.this$0, false);
            RTMPStreamPipOverlay.m13925$$Nest$fputisScrollDisallowed(this.this$0, false);
            if (RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).isRunning() == true) goto L26;
            SpringForce r02 = RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).setStartValue(RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0)).getSpring();
            float r2 = RTMPStreamPipOverlay.m13908$$Nest$fgetpipX(this.this$0) + (RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0) / 2.0f);
            int r6 = AndroidUtilities.displaySize.x;
            if (r2 < (r6 / 2.0f)) goto L23;
            float r22 = (r6 - RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L24:
            r02.setFinalPosition(r22);
            RTMPStreamPipOverlay.m13909$$Nest$fgetpipXSpring(this.this$0).start();
            goto L26
        L23:
            r22 = AndroidUtilities.dp(16.0f);
        L26:
            if (RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L28;
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).setStartValue(RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(RTMPStreamPipOverlay.m13910$$Nest$fgetpipY(this.this$0), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            RTMPStreamPipOverlay.m13911$$Nest$fgetpipYSpring(this.this$0).start();
            goto L28
        L13:
            if (RTMPStreamPipOverlay.m13899$$Nest$fgetgestureDetector(this.this$0).onTouchEvent(r10) == false) goto L15;
            r102 = true;
            goto L16
        }

        @Override // android.view.View
        public void draw(Canvas r1) {
            super.draw(r1);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r3) {
            AndroidUtilities.checkDisplaySize(getContext(), r3);
            AndroidUtilities.setPreferredMaxRefreshRate(RTMPStreamPipOverlay.m13918$$Nest$fgetwindowManager(this.this$0), RTMPStreamPipOverlay.m13896$$Nest$fgetcontentView(this.this$0), RTMPStreamPipOverlay.m13917$$Nest$fgetwindowLayoutParams(this.this$0));
            RTMPStreamPipOverlay.m13936$$Nest$mbindTextureView(this.this$0);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            this.path.rewind();
            RectF r32 = AndroidUtilities.rectTmp;
            r32.set(0.0f, 0.0f, r1, r2);
            this.path.addRoundRect(r32, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends ViewGroup {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass6(RTMPStreamPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            if (RTMPStreamPipOverlay.m13919$$Nest$fgetwindowViewSkipRender(this.this$0) == false) goto L5;
            return;
        L5:
            super.draw(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            if (RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).getParent() != this) goto L6;
            RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).layout(0, 0, RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0), RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0));
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            setMeasuredDimension(View.MeasureSpec.getSize(r2), View.MeasureSpec.getSize(r3));
            if (RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).getParent() != this) goto L6;
            RTMPStreamPipOverlay.m13895$$Nest$fgetcontentFrameLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(RTMPStreamPipOverlay.m13907$$Nest$fgetpipWidth(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(RTMPStreamPipOverlay.m13905$$Nest$fgetpipHeight(this.this$0), TLObject.FLAG_30));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends ViewOutlineProvider {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass7(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dp(10.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 implements RendererCommon.RendererEvents {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        /* renamed from: $r8$lambda$6P1nv_CfYwje4KlMfo3v-R_Wuwg, reason: not valid java name */
        public static /* synthetic */ void m13942$r8$lambda$6P1nv_CfYwje4KlMfo3vR_Wuwg(AnonymousClass8 r0, int r1, int r2) {
            r0.lambda$onFrameResolutionChanged$1(r1, r2);
        }

        /* renamed from: $r8$lambda$P6k2K6CsMDdVrIFFmg-GEp6EKpw, reason: not valid java name */
        public static /* synthetic */ void m13943$r8$lambda$P6k2K6CsMDdVrIFFmgGEp6EKpw(AnonymousClass8 r0) {
            r0.lambda$onFirstFrameRendered$0();
        }

        public AnonymousClass8(RTMPStreamPipOverlay r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onFirstFrameRendered$0() {
            RTMPStreamPipOverlay.m13936$$Nest$mbindTextureView(this.this$0);
        }

        private /* synthetic */ void lambda$onFrameResolutionChanged$1(int r2, int r3) {
            if (RTMPStreamPipOverlay.m13906$$Nest$fgetpipSource(this.this$0) == null) goto L5;
            RTMPStreamPipOverlay.m13906$$Nest$fgetpipSource(this.this$0).setContentRatio(r2, r3);
        L5:
            RTMPStreamPipOverlay.m13936$$Nest$mbindTextureView(this.this$0);
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            RTMPStreamPipOverlay.m13924$$Nest$fputfirstFrameRendered(this.this$0, true);
            if (RTMPStreamPipOverlay.m13898$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L5;
            RTMPStreamPipOverlay.m13898$$Nest$fgetfirstFrameCallback(this.this$0).run();
            RTMPStreamPipOverlay.m13923$$Nest$fputfirstFrameCallback(this.this$0, null);
        L5:
            AndroidUtilities.runOnUIThread(new RTMPStreamPipOverlay$8$$ExternalSyntheticLambda0(this));
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r3, int r4, int r5) {
            int r52 = (r5 / 90) % 2;
            RTMPStreamPipOverlay r0 = this.this$0;
            if (r52 != 0) goto L5;
            RTMPStreamPipOverlay.m13920$$Nest$fputaspectRatio(r0, Float.valueOf(r4 / r3));
        L6:
            AndroidUtilities.runOnUIThread(new RTMPStreamPipOverlay$8$$ExternalSyntheticLambda1(this, r3, r4));
            return;
        L5:
            RTMPStreamPipOverlay.m13920$$Nest$fputaspectRatio(r0, Float.valueOf(r3 / r4));
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.RTMPStreamPipOverlay$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends View {
        final /* synthetic */ RTMPStreamPipOverlay this$0;

        public AnonymousClass9(RTMPStreamPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            if (getAlpha() != 0.0f) goto L5;
            return;
        L5:
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getWidth(), getHeight());
            RTMPStreamPipOverlay.m13893$$Nest$fgetcellFlickerDrawable(this.this$0).draw(r5, r0, AndroidUtilities.dp(10.0f), null);
            invalidate();
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            RTMPStreamPipOverlay.m13893$$Nest$fgetcellFlickerDrawable(this.this$0).setParentWidth(r1);
        }
    }

    /* renamed from: $r8$lambda$3rL7lGcqh4hmab5qShhnR2-74jU, reason: not valid java name */
    public static /* synthetic */ void m13891$r8$lambda$3rL7lGcqh4hmab5qShhnR274jU(Context r2, View r3) {
        if (VoIPService.getSharedInstance() == null) goto L9;
        Intent r32 = new Intent(r2, LaunchActivity.class).setAction("voip_chat");
        r32.putExtra("currentAccount", VoIPService.getSharedInstance().getAccount());
        if ((r2 instanceof Activity) == true) goto L7;
        r32.addFlags(268435456);
    L7:
        r2.startActivity(r32);
        dismiss();
        return;
    }

    public static /* synthetic */ void $r8$lambda$9P8m7WDr0ec6iH3CeyyeBds0yXY(View r0) {
        dismiss();
    }

    public static /* synthetic */ float $r8$lambda$Cx8zoFRxTt5hJ6rk23t8_UwHZLU(RTMPStreamPipOverlay r0) {
        return r0.pipY;
    }

    public static /* synthetic */ void $r8$lambda$In8DnH9qlxu1RfCGK3MOT39sFBU(RTMPStreamPipOverlay r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$J8xlabDgeyn1OMANQLJLGJAACpo(RTMPStreamPipOverlay r0, ValueAnimator r1) {
        r0.lambda$toggleControls$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$SCkwudA6M2DrlnqBYPkdXPJuITY(RTMPStreamPipOverlay r1, float r2) {
        WindowManager.LayoutParams r0 = r1.windowLayoutParams;
        r1.pipX = r2;
        r0.x = (int) r2;
        AndroidUtilities.updateViewLayout(r1.windowManager, r1.contentView, r0);
    }

    /* renamed from: $r8$lambda$_Tk57sOrJpPZZ-CDoVxB9cVvUIA, reason: not valid java name */
    public static /* synthetic */ void m13892$r8$lambda$_Tk57sOrJpPZZCDoVxB9cVvUIA(RTMPStreamPipOverlay r1, float r2) {
        WindowManager.LayoutParams r0 = r1.windowLayoutParams;
        r1.pipY = r2;
        r0.y = (int) r2;
        AndroidUtilities.updateViewLayout(r1.windowManager, r1.contentView, r0);
    }

    public static /* synthetic */ float $r8$lambda$dsXqnsaGk1E1TVy5L2H2GokKLtg(RTMPStreamPipOverlay r0) {
        return r0.pipX;
    }

    public static /* synthetic */ void $r8$lambda$garEwqkCqBKn2Mf4aYABmurJ16s() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
    }

    /* renamed from: -$$Nest$fgetcellFlickerDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CellFlickerDrawable m13893$$Nest$fgetcellFlickerDrawable(RTMPStreamPipOverlay r0) {
        return r0.cellFlickerDrawable;
    }

    /* renamed from: -$$Nest$fgetconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m13894$$Nest$fgetconsumingChild(RTMPStreamPipOverlay r0) {
        return r0.consumingChild;
    }

    /* renamed from: -$$Nest$fgetcontentFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13895$$Nest$fgetcontentFrameLayout(RTMPStreamPipOverlay r0) {
        return r0.contentFrameLayout;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m13896$$Nest$fgetcontentView(RTMPStreamPipOverlay r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetdismissControlsCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13897$$Nest$fgetdismissControlsCallback(RTMPStreamPipOverlay r0) {
        return r0.dismissControlsCallback;
    }

    /* renamed from: -$$Nest$fgetfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13898$$Nest$fgetfirstFrameCallback(RTMPStreamPipOverlay r0) {
        return r0.firstFrameCallback;
    }

    /* renamed from: -$$Nest$fgetgestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GestureDetectorCompat m13899$$Nest$fgetgestureDetector(RTMPStreamPipOverlay r0) {
        return r0.gestureDetector;
    }

    /* renamed from: -$$Nest$fgetisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13900$$Nest$fgetisScrollDisallowed(RTMPStreamPipOverlay r0) {
        return r0.isScrollDisallowed;
    }

    /* renamed from: -$$Nest$fgetisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13901$$Nest$fgetisScrolling(RTMPStreamPipOverlay r0) {
        return r0.isScrolling;
    }

    /* renamed from: -$$Nest$fgetisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13902$$Nest$fgetisShowingControls(RTMPStreamPipOverlay r0) {
        return r0.isShowingControls;
    }

    /* renamed from: -$$Nest$fgetmaxScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13903$$Nest$fgetmaxScaleFactor(RTMPStreamPipOverlay r0) {
        return r0.maxScaleFactor;
    }

    /* renamed from: -$$Nest$fgetminScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13904$$Nest$fgetminScaleFactor(RTMPStreamPipOverlay r0) {
        return r0.minScaleFactor;
    }

    /* renamed from: -$$Nest$fgetpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13905$$Nest$fgetpipHeight(RTMPStreamPipOverlay r0) {
        return r0.pipHeight;
    }

    /* renamed from: -$$Nest$fgetpipSource, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PipSource m13906$$Nest$fgetpipSource(RTMPStreamPipOverlay r0) {
        return r0.pipSource;
    }

    /* renamed from: -$$Nest$fgetpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13907$$Nest$fgetpipWidth(RTMPStreamPipOverlay r0) {
        return r0.pipWidth;
    }

    /* renamed from: -$$Nest$fgetpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13908$$Nest$fgetpipX(RTMPStreamPipOverlay r0) {
        return r0.pipX;
    }

    /* renamed from: -$$Nest$fgetpipXSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m13909$$Nest$fgetpipXSpring(RTMPStreamPipOverlay r0) {
        return r0.pipXSpring;
    }

    /* renamed from: -$$Nest$fgetpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13910$$Nest$fgetpipY(RTMPStreamPipOverlay r0) {
        return r0.pipY;
    }

    /* renamed from: -$$Nest$fgetpipYSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m13911$$Nest$fgetpipYSpring(RTMPStreamPipOverlay r0) {
        return r0.pipYSpring;
    }

    /* renamed from: -$$Nest$fgetpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13912$$Nest$fgetpostedDismissControls(RTMPStreamPipOverlay r0) {
        return r0.postedDismissControls;
    }

    /* renamed from: -$$Nest$fgetscaleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13913$$Nest$fgetscaleAnimator(RTMPStreamPipOverlay r0) {
        return r0.scaleAnimator;
    }

    /* renamed from: -$$Nest$fgetscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13914$$Nest$fgetscaleFactor(RTMPStreamPipOverlay r0) {
        return r0.scaleFactor;
    }

    /* renamed from: -$$Nest$fgetscaleGestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScaleGestureDetector m13915$$Nest$fgetscaleGestureDetector(RTMPStreamPipOverlay r0) {
        return r0.scaleGestureDetector;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPTextureView m13916$$Nest$fgettextureView(RTMPStreamPipOverlay r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetwindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m13917$$Nest$fgetwindowLayoutParams(RTMPStreamPipOverlay r0) {
        return r0.windowLayoutParams;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m13918$$Nest$fgetwindowManager(RTMPStreamPipOverlay r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fgetwindowViewSkipRender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13919$$Nest$fgetwindowViewSkipRender(RTMPStreamPipOverlay r0) {
        return r0.windowViewSkipRender;
    }

    /* renamed from: -$$Nest$fputaspectRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13920$$Nest$fputaspectRatio(RTMPStreamPipOverlay r0, Float r1) {
        r0.aspectRatio = r1;
    }

    /* renamed from: -$$Nest$fputboundParticipant, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13921$$Nest$fputboundParticipant(RTMPStreamPipOverlay r0, TLRPC.GroupCallParticipant r1) {
        r0.boundParticipant = r1;
    }

    /* renamed from: -$$Nest$fputconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13922$$Nest$fputconsumingChild(RTMPStreamPipOverlay r0, View r1) {
        r0.consumingChild = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13923$$Nest$fputfirstFrameCallback(RTMPStreamPipOverlay r0, Runnable r1) {
        r0.firstFrameCallback = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameRendered, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13924$$Nest$fputfirstFrameRendered(RTMPStreamPipOverlay r0, boolean r1) {
        r0.firstFrameRendered = r1;
    }

    /* renamed from: -$$Nest$fputisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13925$$Nest$fputisScrollDisallowed(RTMPStreamPipOverlay r0, boolean r1) {
        r0.isScrollDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13926$$Nest$fputisScrolling(RTMPStreamPipOverlay r0, boolean r1) {
        r0.isScrolling = r1;
    }

    /* renamed from: -$$Nest$fputisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13927$$Nest$fputisShowingControls(RTMPStreamPipOverlay r0, boolean r1) {
        r0.isShowingControls = r1;
    }

    /* renamed from: -$$Nest$fputpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13928$$Nest$fputpipHeight(RTMPStreamPipOverlay r0, int r1) {
        r0.pipHeight = r1;
    }

    /* renamed from: -$$Nest$fputpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13929$$Nest$fputpipWidth(RTMPStreamPipOverlay r0, int r1) {
        r0.pipWidth = r1;
    }

    /* renamed from: -$$Nest$fputpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13930$$Nest$fputpipX(RTMPStreamPipOverlay r0, float r1) {
        r0.pipX = r1;
    }

    /* renamed from: -$$Nest$fputpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13931$$Nest$fputpipY(RTMPStreamPipOverlay r0, float r1) {
        r0.pipY = r1;
    }

    /* renamed from: -$$Nest$fputplaceholderShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13932$$Nest$fputplaceholderShown(RTMPStreamPipOverlay r0, boolean r1) {
        r0.placeholderShown = r1;
    }

    /* renamed from: -$$Nest$fputpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13933$$Nest$fputpostedDismissControls(RTMPStreamPipOverlay r0, boolean r1) {
        r0.postedDismissControls = r1;
    }

    /* renamed from: -$$Nest$fputscaleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13934$$Nest$fputscaleAnimator(RTMPStreamPipOverlay r0, ValueAnimator r1) {
        r0.scaleAnimator = r1;
    }

    /* renamed from: -$$Nest$fputscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13935$$Nest$fputscaleFactor(RTMPStreamPipOverlay r0, float r1) {
        r0.scaleFactor = r1;
    }

    /* renamed from: -$$Nest$mbindTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13936$$Nest$mbindTextureView(RTMPStreamPipOverlay r0) {
        r0.bindTextureView();
    }

    /* renamed from: -$$Nest$mgetSuggestedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13937$$Nest$mgetSuggestedHeight(RTMPStreamPipOverlay r0) {
        return r0.getSuggestedHeight();
    }

    /* renamed from: -$$Nest$mgetSuggestedWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13938$$Nest$mgetSuggestedWidth(RTMPStreamPipOverlay r0) {
        return r0.getSuggestedWidth();
    }

    /* renamed from: -$$Nest$mtoggleControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13939$$Nest$mtoggleControls(RTMPStreamPipOverlay r0, boolean r1) {
        r0.toggleControls(r1);
    }

    static {
        PIP_X_PROPERTY = new SimpleFloatPropertyCompat("pipX", new RTMPStreamPipOverlay$$ExternalSyntheticLambda0(), new RTMPStreamPipOverlay$$ExternalSyntheticLambda1());
        PIP_Y_PROPERTY = new SimpleFloatPropertyCompat("pipY", new RTMPStreamPipOverlay$$ExternalSyntheticLambda2(), new RTMPStreamPipOverlay$$ExternalSyntheticLambda3());
        instance = new RTMPStreamPipOverlay();
    }

    public RTMPStreamPipOverlay() {
        this.minScaleFactor = 0.6f;
        this.maxScaleFactor = 1.4f;
        this.cellFlickerDrawable = new CellFlickerDrawable();
        this.placeholderShown = true;
        this.scaleFactor = 1.0f;
        this.dismissControlsCallback = new RTMPStreamPipOverlay$$ExternalSyntheticLambda5(this);
    }

    private void bindTextureView() {
        bindTextureView(false);
    }

    private void bindTextureView(boolean r21) {
        boolean r3 = true;
        if (VoIPService.getSharedInstance() == null) goto L43;
        if (VoIPService.getSharedInstance().groupCall == null) goto L43;
        if (VoIPService.getSharedInstance().groupCall.visibleVideoParticipants.isEmpty() == true) goto L43;
        TLRPC.GroupCallParticipant r1 = VoIPService.getSharedInstance().groupCall.visibleVideoParticipants.get(0).participant;
        if (r21 == true) goto L15;
        TLRPC.GroupCallParticipant r5 = this.boundParticipant;
        if (r5 == null) goto L15;
        if (MessageObject.getPeerId(r5.peer) != MessageObject.getPeerId(r1.peer)) goto L15;
    L49:
        if (this.firstFrameRendered == false) goto L66;
        TLRPC.GroupCallParticipant r12 = this.boundParticipant;
        if (r12 == null) goto L66;
        TLRPC.TL_groupCallParticipantVideo r2 = r12.video;
        if (r2 == null) goto L55;
    L56:
        if (r2 != null) goto L58;
    L59:
        TLRPC.TL_groupCallParticipantVideo r13 = r12.presentation;
        if (r13 != null) goto L62;
    L64:
        r3 = false;
        goto L66
    L62:
        if (r13.paused == false) goto L64;
    L58:
        if (r2.paused == true) goto L66;
    L55:
        if (r12.presentation != null) goto L56;
    L66:
        if (this.placeholderShown == r3) goto L81;
        this.flickerView.animate().cancel();
        ViewPropertyAnimator r14 = this.flickerView.animate();
        float r22 = 0.0f;
        if (r3 == false) goto L70;
        float r52 = 1.0f;
    L71:
        ViewPropertyAnimator r15 = r14.alpha(r52).setDuration(150);
        CubicBezierInterpolator r7 = CubicBezierInterpolator.DEFAULT;
        r15.setInterpolator(r7).start();
        this.avatarImageView.animate().cancel();
        ViewPropertyAnimator r16 = this.avatarImageView.animate();
        if (r3 == false) goto L74;
        float r8 = 1.0f;
    L75:
        r16.alpha(r8).setDuration(150).setInterpolator(r7).start();
        this.textureView.animate().cancel();
        ViewPropertyAnimator r17 = this.textureView.animate();
        if (r3 == true) goto L79;
        r22 = 1.0f;
    L79:
        r17.alpha(r22).setDuration(150).setInterpolator(r7).start();
        this.placeholderShown = r3;
        goto L81
    L74:
        r8 = 0.0f;
        goto L75
    L70:
        r52 = 0.0f;
    L81:
        if (this.pipWidth == (getSuggestedWidth() * this.scaleFactor)) goto L83;
    L86:
        WindowManager.LayoutParams r18 = this.windowLayoutParams;
        int r23 = (int) (getSuggestedWidth() * this.scaleFactor);
        this.pipWidth = r23;
        r18.width = r23;
        WindowManager.LayoutParams r19 = this.windowLayoutParams;
        int r24 = (int) (getSuggestedHeight() * this.scaleFactor);
        this.pipHeight = r24;
        r19.height = r24;
        AndroidUtilities.updateViewLayout(this.windowManager, this.contentView, this.windowLayoutParams);
        SpringForce r110 = this.pipXSpring.setStartValue(this.pipX).getSpring();
        float r25 = this.pipX + ((getSuggestedWidth() * this.scaleFactor) / 2.0f);
        int r32 = AndroidUtilities.displaySize.x;
        if (r25 < (r32 / 2.0f)) goto L89;
        float r26 = (r32 - (getSuggestedWidth() * this.scaleFactor)) - AndroidUtilities.dp(16.0f);
    L90:
        r110.setFinalPosition(r26);
        this.pipXSpring.start();
        this.pipYSpring.setStartValue(this.pipY).getSpring().setFinalPosition(MathUtils.clamp(this.pipY, AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - (getSuggestedHeight() * this.scaleFactor)) - AndroidUtilities.dp(16.0f)));
        this.pipYSpring.start();
        return;
    L89:
        r26 = AndroidUtilities.dp(16.0f);
        goto L90
    L83:
        if (this.pipHeight != (getSuggestedHeight() * this.scaleFactor)) goto L86;
        return;
    L15:
        if (this.boundParticipant == null) goto L17;
        VoIPService.getSharedInstance().removeRemoteSink(this.boundParticipant, this.boundPresentation);
    L17:
        VoIPTextureView r53 = this.pipTextureView;
        if (r53 != null) goto L22;
        r53 = this.textureView;
    L22:
        if (r1.presentation == null) goto L24;
        boolean r6 = true;
    L25:
        this.boundPresentation = r6;
        if (r1.self == false) goto L28;
        VoIPService.getSharedInstance().setSinks(r53.renderer, this.boundPresentation, null);
    L29:
        AccountInstance r27 = VoIPService.getSharedInstance().groupCall.currentAccount;
        MessagesController r54 = r27.getMessagesController();
        long r62 = MessageObject.getPeerId(r1.peer);
        if (r62 <= 0) goto L36;
        TLRPC.User r55 = r54.getUser(Long.valueOf(r62));
        ImageLocation r142 = ImageLocation.getForUser(r27.getCurrentAccount(), r55, 1);
        if (r55 == null) goto L34;
        int r28 = AvatarDrawable.getColorForId(r55.id);
    L35:
        this.avatarImageView.getImageReceiver().setImage(r142, "50_50_b", new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r28, -16777216, 0.2f), ColorUtils.blendARGB(r28, -16777216, 0.4f)}), null, r55, 0);
    L41:
        this.boundParticipant = r1;
        goto L49
    L34:
        r28 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L35
    L36:
        TLRPC.Chat r56 = r54.getChat(Long.valueOf(-r62));
        ImageLocation r143 = ImageLocation.getForChat(r27.getCurrentAccount(), r56, 1);
        if (r56 == null) goto L39;
        int r29 = AvatarDrawable.getColorForId(r56.id);
    L40:
        this.avatarImageView.getImageReceiver().setImage(r143, "50_50_b", new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r29, -16777216, 0.2f), ColorUtils.blendARGB(r29, -16777216, 0.4f)}), null, r56, 0);
        goto L41
    L39:
        r29 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L40
    L28:
        VoIPService.getSharedInstance().addRemoteSink(r1, this.boundPresentation, r53.renderer, null);
        goto L29
    L24:
        r6 = false;
    L43:
        if (this.boundParticipant == null) goto L49;
        if (VoIPService.getSharedInstance() == null) goto L47;
        VoIPService.getSharedInstance().removeRemoteSink(this.boundParticipant, false);
    L47:
        this.boundParticipant = null;
        goto L49
    }

    public static void dismiss() {
        instance.dismissInternal();
    }

    private void dismissInternal() {
        if (this.isVisible == false) goto L16;
        this.isVisible = false;
        AndroidUtilities.runOnUIThread(new RTMPStreamPipOverlay$$ExternalSyntheticLambda4(), 100);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.groupCallUpdated);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.applyGroupCallVisibleParticipants);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didEndCall);
        ValueAnimator r1 = this.scaleAnimator;
        if (r1 == null) goto L9;
        r1.cancel();
    L9:
        if (this.postedDismissControls == false) goto L11;
        AndroidUtilities.cancelRunOnUIThread(this.dismissControlsCallback);
        this.postedDismissControls = false;
    L11:
        AnimatorSet r12 = new AnimatorSet();
        r12.setDuration(250);
        r12.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r12.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.contentView, View.ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_X, new float[]{0.1f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_Y, new float[]{0.1f})});
        r12.addListener(new AnonymousClass2(this));
        r12.start();
        PipSource r0 = this.pipSource;
        if (r0 == null) goto L15;
        r0.destroy();
        this.pipSource = null;
        return;
    L15:
        return;
    }

    private float getRatio() {
        if (this.aspectRatio != null) goto L14;
        if (VoIPService.getSharedInstance() != null) goto L7;
    L11:
        float r1 = 0.5625f;
    L12:
        this.aspectRatio = Float.valueOf(r1);
        Point r0 = AndroidUtilities.displaySize;
        this.maxScaleFactor = (Math.min(r0.x, r0.y) - AndroidUtilities.dp(32.0f)) / getSuggestedWidth();
        goto L14
    L7:
        if (VoIPService.getSharedInstance().groupCall.visibleVideoParticipants.isEmpty() == true) goto L11;
        float r02 = VoIPService.getSharedInstance().groupCall.visibleVideoParticipants.get(0).aspectRatio;
        if (r02 == 0.0f) goto L11;
        r1 = 1.0f / r02;
    L14:
        return this.aspectRatio.floatValue();
    }

    private int getSuggestedHeight() {
        return (int) (getSuggestedWidth() * getRatio());
    }

    private int getSuggestedWidth() {
        if (getRatio() < 1.0f) goto L7;
        Point r1 = AndroidUtilities.displaySize;
        float r12 = Math.min(r1.x, r1.y);
        float r0 = 0.35f;
    L6:
        return (int) (r12 * r0);
    L7:
        Point r13 = AndroidUtilities.displaySize;
        r12 = Math.min(r13.x, r13.y);
        r0 = 0.6f;
        goto L6
    }

    public static boolean isVisible() {
        return instance.isVisible;
    }

    private /* synthetic */ void lambda$new$4() {
        this.isShowingControls = false;
        toggleControls(false);
        this.postedDismissControls = false;
    }

    private /* synthetic */ void lambda$toggleControls$5(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.controlsView.setAlpha(r12);
    }

    public static void show(Activity r1) {
        instance.showInternal(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.content.Context] */
    private void showInternal(Activity r30) {
        if (VoIPService.getSharedInstance() != null) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance().groupCall != null) goto L7;
        return;
    L7:
        if (this.isVisible == true) goto L25;
        this.isVisible = true;
        AccountInstance r3 = VoIPService.getSharedInstance().groupCall.currentAccount;
        this.accountInstance = r3;
        r3.getNotificationCenter().addObserver(this, NotificationCenter.groupCallUpdated);
        this.accountInstance.getNotificationCenter().addObserver(this, NotificationCenter.applyGroupCallVisibleParticipants);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didEndCall);
        this.pipWidth = getSuggestedWidth();
        this.pipHeight = getSuggestedHeight();
        this.scaleFactor = 1.0f;
        this.isShowingControls = false;
        this.pipXSpring = new SpringAnimation(this, PIP_X_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f));
        this.pipYSpring = new SpringAnimation(this, PIP_Y_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f));
        if (r30 == null) goto L12;
        Activity r7 = r30;
    L13:
        int r5 = ViewConfiguration.get(r7).getScaledTouchSlop();
        ScaleGestureDetector r6 = new ScaleGestureDetector(r7, new AnonymousClass3(this));
        this.scaleGestureDetector = r6;
        r6.setQuickScaleEnabled(false);
        this.scaleGestureDetector.setStylusScaleEnabled(false);
        this.gestureDetector = new GestureDetectorCompat(r7, new AnonymousClass4(this, r5));
        this.contentFrameLayout = new AnonymousClass5(this, r7);
        AnonymousClass6 r52 = new AnonymousClass6(this, r7);
        this.contentView = r52;
        r52.addView(this.contentFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.contentFrameLayout.setOutlineProvider(new AnonymousClass7(this));
        this.contentFrameLayout.setClipToOutline(true);
        this.contentFrameLayout.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        BackupImageView r53 = new BackupImageView(r7);
        this.avatarImageView = r53;
        this.contentFrameLayout.addView(r53, LayoutHelper.createFrame(-1, -1.0f));
        VoIPTextureView r62 = new VoIPTextureView(r7, false, false, false, false);
        this.textureView = r62;
        r62.setAlpha(0.0f);
        this.textureView.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);
        VoIPTextureView r63 = this.textureView;
        r63.scaleType = VoIPTextureView.SCALE_TYPE_FILL;
        r63.renderer.setRotateTextureWithScreen(true);
        this.textureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass8(this));
        this.contentFrameLayout.addView(this.textureView, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass9 r64 = new AnonymousClass9(this, r7);
        this.flickerView = r64;
        this.contentFrameLayout.addView(r64, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r65 = new FrameLayout(r7);
        this.controlsView = r65;
        r65.setAlpha(0.0f);
        View r66 = new View(r7);
        GradientDrawable r8 = new GradientDrawable();
        r8.setColors(new int[]{1140850688, 0});
        r8.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        r66.setBackground(r8);
        this.controlsView.addView(r66, LayoutHelper.createFrame(-1, -1.0f));
        int r67 = AndroidUtilities.dp(8.0f);
        ImageView r82 = new ImageView(r7);
        r82.setImageResource(R.drawable.pip_video_close);
        int r9 = Theme.key_voipgroup_actionBarItems;
        r82.setColorFilter(Theme.getColor(r9));
        int r10 = Theme.key_listSelector;
        r82.setBackground(Theme.createSelectorDrawable(Theme.getColor(r10)));
        r82.setPadding(r67, r67, r67, r67);
        r82.setOnClickListener(new RTMPStreamPipOverlay$$ExternalSyntheticLambda6());
        this.controlsView.addView(r82, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 4.0f, 0.0f));
        ImageView r32 = new ImageView(r7);
        r32.setImageResource(R.drawable.pip_video_expand);
        r32.setColorFilter(Theme.getColor(r9));
        r32.setBackground(Theme.createSelectorDrawable(Theme.getColor(r10)));
        r32.setPadding(r67, r67, r67, r67);
        r32.setOnClickListener(new RTMPStreamPipOverlay$$ExternalSyntheticLambda7(r7));
        this.controlsView.addView(r32, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 48.0f, 0.0f));
        this.contentFrameLayout.addView(this.controlsView, LayoutHelper.createFrame(-1, -1.0f));
        this.windowManager = (WindowManager) r7.getSystemService("window");
        WindowManager.LayoutParams r33 = PipUtils.createWindowLayoutParams(r7, false);
        this.windowLayoutParams = r33;
        int r68 = this.pipWidth;
        r33.width = r68;
        r33.height = this.pipHeight;
        float r72 = (AndroidUtilities.displaySize.x - r68) - AndroidUtilities.dp(16.0f);
        this.pipX = r72;
        r33.x = (int) r72;
        WindowManager.LayoutParams r34 = this.windowLayoutParams;
        float r69 = (AndroidUtilities.displaySize.y - this.pipHeight) - AndroidUtilities.dp(16.0f);
        this.pipY = r69;
        r34.y = (int) r69;
        WindowManager.LayoutParams r35 = this.windowLayoutParams;
        r35.dimAmount = 0.0f;
        r35.flags = 520;
        this.contentView.setAlpha(0.0f);
        this.contentView.setScaleX(0.1f);
        this.contentView.setScaleY(0.1f);
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.contentView, this.windowLayoutParams);
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
        AnimatorSet r36 = new AnimatorSet();
        r36.setDuration(250);
        r36.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r36.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.contentView, View.ALPHA, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_Y, new float[]{1.0f})});
        r36.addListener(new AnonymousClass10(this));
        r36.start();
        bindTextureView();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
        PipSource r37 = this.pipSource;
        if (r37 == null) goto L16;
        r37.destroy();
        this.pipSource = null;
    L16:
        if (r30 != null) goto L18;
        return;
    L18:
        if (PipUtils.checkPermissions(r30) != 1) goto L24;
        this.pipSource = new PipSource.Builder(r30, this).setTagPrefix("pip-rtmp-video").setPriority(1).setCornerRadius(AndroidUtilities.dp(10.0f)).setContentView(this.contentView).setPlaceholderView(this.textureView.getPlaceholderView()).build();
        return;
    L24:
        return;
    L12:
        r7 = ApplicationLoader.applicationContext;
        goto L13
    }

    private void toggleControls(boolean r4) {
        float r0 = 1.0f;
        if (r4 == false) goto L5;
        float r2 = 0.0f;
    L6:
        if (r4 == true) goto L9;
        r0 = 0.0f;
    L9:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r2, r0}).setDuration(200);
        this.scaleAnimator = r42;
        r42.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.scaleAnimator.addUpdateListener(new RTMPStreamPipOverlay$$ExternalSyntheticLambda8(this));
        this.scaleAnimator.addListener(new AnonymousClass1(this));
        this.scaleAnimator.start();
        return;
    L5:
        r2 = 1.0f;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.didEndCall) goto L7;
        dismiss();
        return;
    L7:
        if (r1 != NotificationCenter.groupCallUpdated) goto L10;
        bindTextureView();
        return;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public View pipCreatePictureInPictureView() {
        VoIPTextureView r0 = new VoIPTextureView(this.textureView.getContext(), false, false, false, false);
        this.pipTextureView = r0;
        r0.renderer.setOpaque(false);
        this.pipTextureView.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);
        VoIPTextureView r02 = this.pipTextureView;
        r02.scaleType = VoIPTextureView.SCALE_TYPE_FILL;
        r02.renderer.setRotateTextureWithScreen(true);
        this.pipTextureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass11(this));
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
        VoIPTextureView r0 = this.textureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.renderer.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.textureView.renderer.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipHidePrimaryWindowView(Runnable r2) {
        this.firstFrameCallback = r2;
        VoIPTextureView r22 = this.textureView;
        if (r22 == null) goto L5;
        r22.renderer.clearFirstFrame();
    L5:
        bindTextureView(true);
        this.windowViewSkipRender = true;
        this.windowManager.removeView(this.contentView);
        this.contentView.invalidate();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipShowPrimaryWindowView(Runnable r3) {
        this.firstFrameCallback = r3;
        PipSource r32 = this.pipSource;
        if (r32 != null) goto L5;
    L7:
        this.windowViewSkipRender = false;
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
        this.contentView.invalidate();
        VoIPTextureView r33 = this.pipTextureView;
        if (r33 == null) goto L10;
        r33.renderer.release();
        this.pipTextureView = null;
    L10:
        bindTextureView(true);
        return;
    L5:
        if (r32.params.isValid() == false) goto L7;
        WindowManager.LayoutParams r34 = this.windowLayoutParams;
        int r0 = this.pipSource.params.getWidth();
        this.pipWidth = r0;
        r34.width = r0;
        WindowManager.LayoutParams r35 = this.windowLayoutParams;
        int r02 = this.pipSource.params.getHeight();
        this.pipHeight = r02;
        r35.height = r02;
        goto L7
    }
}
