package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.GestureDetectorFixDoubleTap;
import org.telegram.ui.Components.VideoForwardDrawable;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes7.dex */
public class PipVideoOverlay implements IPipSourceDelegate {
    private static final FloatPropertyCompat<PipVideoOverlay> PIP_X_PROPERTY = null;
    private static final FloatPropertyCompat<PipVideoOverlay> PIP_Y_PROPERTY = null;

    @SuppressLint({"StaticFieldLeak"})
    private static PipVideoOverlay instance;
    private Float aspectRatio;
    private float bufferProgress;
    private boolean canLongClick;
    private View consumingChild;
    private FrameLayout contentFrameLayout;
    private ViewGroup contentView;
    private ValueAnimator controlsAnimator;
    private FrameLayout controlsView;
    private final Runnable dismissControlsCallback;
    private GestureDetectorFixDoubleTap gestureDetector;
    private View innerView;
    private boolean isDismissing;
    private boolean isScrollDisallowed;
    private boolean isScrolling;
    private boolean isShowingControls;
    private boolean isVideoCompleted;
    private boolean isVisible;
    private boolean isWebView;
    private final Runnable longClickCallback;
    private float[] longClickStartPoint;
    private int mVideoHeight;
    private int mVideoWidth;
    private float maxScaleFactor;
    private float minScaleFactor;
    private boolean onSideToDismiss;
    private EmbedBottomSheet parentSheet;
    private PhotoViewer photoViewer;
    private PhotoViewerWebView photoViewerWebView;
    private PipConfig pipConfig;
    private int pipHeight;
    private PipSource pipSource;
    public TextureView pipTextureView;
    private int pipWidth;
    private float pipX;
    private SpringAnimation pipXSpring;
    private float pipY;
    private SpringAnimation pipYSpring;
    private View placeholderView;
    private ImageView playPauseButton;
    private boolean postedDismissControls;
    private final Runnable progressRunnable;
    private float scaleFactor;
    private ScaleGestureDetector scaleGestureDetector;
    private SeekSpeedDrawable seekSpeedDrawable;
    private final VideoForwardDrawable videoForwardDrawable;
    private float videoProgress;
    private VideoProgressView videoProgressView;
    private WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private boolean windowViewSkipRender;

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass1(PipVideoOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PipVideoOverlay.m11839$$Nest$fputcontrolsAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass2(PipVideoOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PipVideoOverlay.m11858$$Nest$monDismissedInternal(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$3, reason: invalid class name */
    public class AnonymousClass3 implements ScaleGestureDetector.OnScaleGestureListener {
        final /* synthetic */ PipVideoOverlay this$0;

        /* renamed from: org.telegram.ui.Components.PipVideoOverlay$3$1, reason: invalid class name */
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
                AnonymousClass3.m11863$$Nest$mupdateLayout(this.this$1);
                return;
            }
        }

        /* renamed from: $r8$lambda$uwo6lObkGqZXLyQe7VEBZ-5rbyQ, reason: not valid java name */
        public static /* synthetic */ void m11862$r8$lambda$uwo6lObkGqZXLyQe7VEBZ5rbyQ(AnonymousClass3 r0) {
            r0.lambda$onScale$0();
        }

        /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11863$$Nest$mupdateLayout(AnonymousClass3 r0) {
            r0.updateLayout();
        }

        public AnonymousClass3(PipVideoOverlay r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScale$0() {
            PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0).invalidate();
            PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).requestLayout();
        }

        private void updateLayout() {
            PipVideoOverlay r0 = this.this$0;
            WindowManager.LayoutParams r1 = PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(r0);
            int r2 = (int) (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0));
            r1.width = r2;
            PipVideoOverlay.m11847$$Nest$fputpipWidth(r0, r2);
            PipVideoOverlay r02 = this.this$0;
            WindowManager.LayoutParams r12 = PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(r02);
            int r22 = (int) (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0));
            r12.height = r22;
            PipVideoOverlay.m11846$$Nest$fputpipHeight(r02, r22);
            AndroidUtilities.updateViewLayout(PipVideoOverlay.m11835$$Nest$fgetwindowManager(this.this$0), PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0), PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0));     // Catch: IllegalArgumentException -> L5
            return;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector r6) {
            PipVideoOverlay r0 = this.this$0;
            PipVideoOverlay.m11851$$Nest$fputscaleFactor(r0, MathUtils.clamp(PipVideoOverlay.m11829$$Nest$fgetscaleFactor(r0) * r6.getScaleFactor(), PipVideoOverlay.m11818$$Nest$fgetminScaleFactor(this.this$0), PipVideoOverlay.m11817$$Nest$fgetmaxScaleFactor(this.this$0)));
            PipVideoOverlay.m11847$$Nest$fputpipWidth(this.this$0, (int) (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(r0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0)));
            PipVideoOverlay.m11846$$Nest$fputpipHeight(this.this$0, (int) (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(r0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0)));
            AndroidUtilities.runOnUIThread(new PipVideoOverlay$3$$ExternalSyntheticLambda0(this));
            float r02 = r6.getFocusX();
            int r1 = AndroidUtilities.displaySize.x;
            if (r02 < (r1 / 2.0f)) goto L5;
            float r03 = (r1 - PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L6:
            boolean r12 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).isRunning();
            PipVideoOverlay r4 = this.this$0;
            if (r12 == true) goto L9;
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r4).setStartValue(PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0)).getSpring().setFinalPosition(r03);
        L10:
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
            float r62 = MathUtils.clamp(r6.getFocusY() - (PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0) / 2.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f));
            boolean r04 = PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).isRunning();
            PipVideoOverlay r13 = this.this$0;
            if (r04 == true) goto L13;
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(r13).setStartValue(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(r62);
        L14:
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L13:
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(r13).getSpring().setFinalPosition(r62);
            goto L14
        L9:
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r4).getSpring().setFinalPosition(r03);
            goto L10
        L5:
            r03 = AndroidUtilities.dp(16.0f);
            goto L6
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector r4) {
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == false) goto L5;
            PipVideoOverlay.m11841$$Nest$fputisScrolling(this.this$0, false);
            PipVideoOverlay.m11837$$Nest$fputcanLongClick(this.this$0, false);
            PipVideoOverlay.m11852$$Nest$mcancelRewind(this.this$0);
            AndroidUtilities.cancelRunOnUIThread(PipVideoOverlay.m11816$$Nest$fgetlongClickCallback(this.this$0));
        L5:
            PipVideoOverlay.m11840$$Nest$fputisScrollDisallowed(this.this$0, true);
            PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0).width = (int) (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11817$$Nest$fgetmaxScaleFactor(this.this$0));
            PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0).height = (int) (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(this.this$0) * PipVideoOverlay.m11817$$Nest$fgetmaxScaleFactor(this.this$0));
            AndroidUtilities.updateViewLayout(PipVideoOverlay.m11835$$Nest$fgetwindowManager(this.this$0), PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0), PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0));
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector r4) {
            if (PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).isRunning() == false) goto L5;
        L9:
            ArrayList r42 = new ArrayList();
            AnonymousClass1 r0 = new AnonymousClass1(this, r42);
            boolean r1 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).isRunning();
            PipVideoOverlay r2 = this.this$0;
            if (r1 == true) goto L12;
            r42.add(PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r2));
        L13:
            boolean r12 = PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).isRunning();
            PipVideoOverlay r3 = this.this$0;
            if (r12 == true) goto L17;
            r42.add(PipVideoOverlay.m11827$$Nest$fgetpipYSpring(r3));
            return;
        L17:
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(r3).addEndListener(r0);
            return;
        L12:
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r2).addEndListener(r0);
            goto L13
        L5:
            if (PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L9;
            updateLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$4, reason: invalid class name */
    public class AnonymousClass4 extends GestureDetectorFixDoubleTap.OnGestureListener {
        private float startPipX;
        private float startPipY;
        final /* synthetic */ PipVideoOverlay this$0;
        final /* synthetic */ int val$touchSlop;

        public static /* synthetic */ void $r8$lambda$ExhUVhTPG9MlF8raig22SSGNowU(AnonymousClass4 r0, float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            r0.lambda$onScroll$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass4(PipVideoOverlay r1, int r2) {
            this.this$0 = r1;
            this.val$touchSlop = r2;
        }

        private /* synthetic */ void lambda$onScroll$0(float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            if (r3 == true) goto L9;
            SpringForce r22 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).getSpring();
            float r12 = r1 + (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) / 2.0f);
            int r32 = AndroidUtilities.displaySize.x;
            if (r12 < (r32 / 2.0f)) goto L6;
            float r0 = (r32 - PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L7:
            r22.setFinalPosition(r0);
            return;
        L6:
            r0 = AndroidUtilities.dp(16.0f);
            goto L7
        }

        @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.OnGestureListener
        public boolean hasDoubleTap(MotionEvent r6) {
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0) != null) goto L5;
        L25:
            return false;
        L5:
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayer() != null) goto L9;
            if (PipVideoOverlay.m11821$$Nest$fgetphotoViewerWebView(this.this$0) == null) goto L25;
        L9:
            if (PipVideoOverlay.m11810$$Nest$fgetisDismissing(this.this$0) == true) goto L25;
            if (PipVideoOverlay.m11814$$Nest$fgetisVideoCompleted(this.this$0) == true) goto L25;
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == true) goto L25;
            if (PipVideoOverlay.m11830$$Nest$fgetscaleGestureDetector(this.this$0).isInProgress() == true) goto L25;
            if (PipVideoOverlay.m11802$$Nest$fgetcanLongClick(this.this$0) == false) goto L25;
            long r1 = PipVideoOverlay.m11853$$Nest$mgetCurrentPosition(this.this$0);
            long r5 = PipVideoOverlay.m11854$$Nest$mgetDuration(this.this$0);
            if (r1 == (-9223372036854775807L)) goto L25;
            if (r5 < 15000) goto L25;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent r13) {
            boolean r1 = false;
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0) != null) goto L5;
        L56:
            return false;
        L5:
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayer() != null) goto L9;
            if (PipVideoOverlay.m11821$$Nest$fgetphotoViewerWebView(this.this$0) == null) goto L56;
        L9:
            if (PipVideoOverlay.m11810$$Nest$fgetisDismissing(this.this$0) == true) goto L56;
            if (PipVideoOverlay.m11814$$Nest$fgetisVideoCompleted(this.this$0) == true) goto L56;
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == true) goto L56;
            if (PipVideoOverlay.m11830$$Nest$fgetscaleGestureDetector(this.this$0).isInProgress() == true) goto L56;
            if (PipVideoOverlay.m11802$$Nest$fgetcanLongClick(this.this$0) == false) goto L56;
            PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayer();
            if (r13.getX() < ((PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0)) * 0.5f)) goto L22;
            boolean r132 = true;
        L23:
            long r2 = PipVideoOverlay.m11853$$Nest$mgetCurrentPosition(this.this$0);
            long r4 = PipVideoOverlay.m11854$$Nest$mgetDuration(this.this$0);
            if (r2 == (-9223372036854775807L)) goto L56;
            if (r4 < 15000) goto L56;
            long r6 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
            if (r132 == false) goto L31;
            long r8 = r2 + NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        L33:
            if (r2 == r8) goto L56;
            if (r8 <= r4) goto L38;
            r1 = true;
            r8 = r4;
        L45:
            if (r1 == false) goto L55;
            PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).setOneShootAnimation(true);
            PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).setLeftSide(!r132);
            PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).addTime(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            PipVideoOverlay.m11860$$Nest$mseekTo(this.this$0, r8);
            PipVideoOverlay r12 = this.this$0;
            if (r132 == true) goto L50;
            r6 = -10000;
        L50:
            PipVideoOverlay.m11859$$Nest$monUpdateRewindProgressUiInternal(r12, r6, r8 / r4, true);
            if (PipVideoOverlay.m11813$$Nest$fgetisShowingControls(this.this$0) == true) goto L55;
            PipVideoOverlay r133 = this.this$0;
            PipVideoOverlay.m11842$$Nest$fputisShowingControls(r133, true);
            PipVideoOverlay.m11861$$Nest$mtoggleControls(r133, true);
            if (PipVideoOverlay.m11828$$Nest$fgetpostedDismissControls(this.this$0) == true) goto L55;
            PipVideoOverlay.m11850$$Nest$fputpostedDismissControls(this.this$0, true);
            AndroidUtilities.runOnUIThread(PipVideoOverlay.m11808$$Nest$fgetdismissControlsCallback(this.this$0), 2500);
        L55:
            return true;
        L38:
            if (r8 < 0) goto L40;
            r1 = true;
            goto L45
        L40:
            if (r8 < (-9000)) goto L43;
            r1 = true;
        L43:
            r8 = 0;
            goto L45
        L31:
            r8 = r2 - NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
            goto L33
        L22:
            r132 = false;
            goto L23
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r5) {
            if (PipVideoOverlay.m11813$$Nest$fgetisShowingControls(this.this$0) == false) goto L12;
            int r0 = 1;
        L6:
            if (r0 >= PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).getChildCount()) goto L12;
            View r2 = PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).getChildAt(r0);
            if (r2.dispatchTouchEvent(r5) == true) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            PipVideoOverlay.m11838$$Nest$fputconsumingChild(this.this$0, r2);
            return true;
        L12:
            this.startPipX = PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0);
            this.startPipY = PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == true) goto L5;
            return false;
        L5:
            if (PipVideoOverlay.m11811$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            SpringForce r42 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).setStartVelocity(r6).setStartValue(PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0)).getSpring();
            float r52 = (PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0) + (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) / 2.0f)) + (r6 / 7.0f);
            int r0 = AndroidUtilities.displaySize.x;
            if (r52 < (r0 / 2.0f)) goto L9;
            float r53 = (r0 - PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L10:
            r42.setFinalPosition(r53);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).setStartVelocity(r6).setStartValue(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0) + (r7 / 10.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L9:
            r53 = AndroidUtilities.dp(16.0f);
            goto L10
        L14:
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r5, MotionEvent r6, float r7, float r8) {
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == true) goto L14;
            if (PipVideoOverlay.m11806$$Nest$fgetcontrolsAnimator(this.this$0) != null) goto L14;
            if (PipVideoOverlay.m11811$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            if (Math.abs(r7) < this.val$touchSlop) goto L11;
        L12:
            PipVideoOverlay.m11841$$Nest$fputisScrolling(this.this$0, true);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).cancel();
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).cancel();
            PipVideoOverlay.m11837$$Nest$fputcanLongClick(this.this$0, false);
            PipVideoOverlay.m11852$$Nest$mcancelRewind(this.this$0);
            AndroidUtilities.cancelRunOnUIThread(PipVideoOverlay.m11816$$Nest$fgetlongClickCallback(this.this$0));
            goto L14
        L11:
            if (Math.abs(r8) >= this.val$touchSlop) goto L12;
        L14:
            if (PipVideoOverlay.m11812$$Nest$fgetisScrolling(this.this$0) == false) goto L40;
            float r72 = PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0);
            float r82 = (this.startPipX + r6.getRawX()) - r5.getRawX();
            PipVideoOverlay.m11849$$Nest$fputpipY(this.this$0, (this.startPipY + r6.getRawY()) - r5.getRawY());
            if (r82 <= ((-PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0)) * 0.25f)) goto L32;
            if (r82 >= (AndroidUtilities.displaySize.x - (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) * 0.75f))) goto L32;
            boolean r52 = PipVideoOverlay.m11819$$Nest$fgetonSideToDismiss(this.this$0);
            PipVideoOverlay r62 = this.this$0;
            if (r52 == true) goto L23;
            boolean r53 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r62).isRunning();
            PipVideoOverlay r63 = this.this$0;
            if (r53 == false) goto L29;
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r63).getSpring().setFinalPosition(r82);
        L30:
            PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0).y = (int) PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0);
            PipConfig.m11868$$Nest$msetPipY(PipVideoOverlay.m11855$$Nest$mgetPipConfig(this.this$0), PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0));
            AndroidUtilities.updateViewLayout(PipVideoOverlay.m11835$$Nest$fgetwindowManager(this.this$0), PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0), PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0));
            goto L40
        L29:
            WindowManager.LayoutParams r54 = PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(r63);
            PipVideoOverlay.m11848$$Nest$fputpipX(this.this$0, r82);
            r54.x = (int) r82;
            PipConfig.m11867$$Nest$msetPipX(PipVideoOverlay.m11855$$Nest$mgetPipConfig(this.this$0), r82);
            goto L30
        L23:
            if (PipVideoOverlay.m11819$$Nest$fgetonSideToDismiss(r62) == false) goto L25;
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).addEndListener(new PipVideoOverlay$4$$ExternalSyntheticLambda0(this, r82));
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).setStartValue(r72).getSpring().setFinalPosition(r82);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
        L25:
            PipVideoOverlay.m11844$$Nest$fputonSideToDismiss(this.this$0, false);
        L32:
            if (PipVideoOverlay.m11819$$Nest$fgetonSideToDismiss(this.this$0) == true) goto L39;
            SpringForce r55 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).setStartValue(r72).getSpring();
            float r83 = r82 + (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) / 2.0f);
            int r64 = AndroidUtilities.displaySize.x;
            if (r83 < (r64 / 2.0f)) goto L37;
            int r73 = AndroidUtilities.dp(16.0f);
        L38:
            r55.setFinalPosition(r64 - r73);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
            goto L39
        L37:
            r64 = AndroidUtilities.dp(16.0f);
            r73 = PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0);
        L39:
            PipVideoOverlay.m11844$$Nest$fputonSideToDismiss(this.this$0, true);
        L40:
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent r4) {
            if (PipVideoOverlay.m11806$$Nest$fgetcontrolsAnimator(this.this$0) == null) goto L6;
            return true;
        L6:
            if (PipVideoOverlay.m11828$$Nest$fgetpostedDismissControls(this.this$0) == false) goto L8;
            AndroidUtilities.cancelRunOnUIThread(PipVideoOverlay.m11808$$Nest$fgetdismissControlsCallback(this.this$0));
            PipVideoOverlay.m11850$$Nest$fputpostedDismissControls(this.this$0, false);
        L8:
            PipVideoOverlay.m11842$$Nest$fputisShowingControls(this.this$0, !PipVideoOverlay.m11813$$Nest$fgetisShowingControls(r4));
            PipVideoOverlay r42 = this.this$0;
            PipVideoOverlay.m11861$$Nest$mtoggleControls(r42, PipVideoOverlay.m11813$$Nest$fgetisShowingControls(r42));
            if (PipVideoOverlay.m11813$$Nest$fgetisShowingControls(this.this$0) == true) goto L11;
        L13:
            return true;
        L11:
            if (PipVideoOverlay.m11828$$Nest$fgetpostedDismissControls(this.this$0) == true) goto L13;
            AndroidUtilities.runOnUIThread(PipVideoOverlay.m11808$$Nest$fgetdismissControlsCallback(this.this$0), 2500);
            PipVideoOverlay.m11850$$Nest$fputpostedDismissControls(this.this$0, true);
            goto L13
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r2) {
            if (hasDoubleTap(r2) == true) goto L7;
            return onSingleTapConfirmed(r2);
        L7:
            return super.onSingleTapUp(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass5(PipVideoOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r10) {
            int r0 = r10.getActionMasked();
            if (r0 != 0) goto L5;
        L6:
            int r4 = r10.getPointerCount();
            PipVideoOverlay r5 = this.this$0;
            if (r4 != 1) goto L9;
            PipVideoOverlay.m11837$$Nest$fputcanLongClick(r5, true);
            PipVideoOverlay.m11843$$Nest$fputlongClickStartPoint(this.this$0, new float[]{r10.getX(), r10.getY()});
            AndroidUtilities.runOnUIThread(PipVideoOverlay.m11816$$Nest$fgetlongClickCallback(this.this$0), 500);
        L11:
            if (r0 == 1) goto L23;
            if (r0 == 3) goto L23;
            if (r0 == 6) goto L23;
            if (r0 != 2) goto L25;
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0) == null) goto L25;
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayerRewinder() == null) goto L25;
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayerRewinder().rewinding == false) goto L25;
            PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).getVideoPlayerRewinder().setX(r10.getX());
        L25:
            if (PipVideoOverlay.m11803$$Nest$fgetconsumingChild(this.this$0) == null) goto L33;
            MotionEvent r1 = MotionEvent.obtain(r10);
            r1.offsetLocation(PipVideoOverlay.m11803$$Nest$fgetconsumingChild(this.this$0).getX(), PipVideoOverlay.m11803$$Nest$fgetconsumingChild(this.this$0).getY());
            boolean r6 = PipVideoOverlay.m11803$$Nest$fgetconsumingChild(this.this$0).dispatchTouchEvent(r10);
            r1.recycle();
            if (r0 == 1) goto L30;
            if (r0 == 3) goto L30;
            if (r0 == 6) goto L30;
        L31:
            if (r6 == false) goto L33;
            return true;
        L30:
            PipVideoOverlay.m11838$$Nest$fputconsumingChild(this.this$0, null);
        L33:
            MotionEvent r12 = MotionEvent.obtain(r10);
            r12.offsetLocation(r10.getRawX() - r10.getX(), r10.getRawY() - r10.getY());
            boolean r62 = PipVideoOverlay.m11830$$Nest$fgetscaleGestureDetector(this.this$0).onTouchEvent(r12);
            r12.recycle();
            if (PipVideoOverlay.m11830$$Nest$fgetscaleGestureDetector(this.this$0).isInProgress() == false) goto L36;
        L38:
            boolean r102 = false;
        L39:
            if (r0 == 1) goto L42;
            if (r0 == 3) goto L42;
            if (r0 == 6) goto L42;
        L55:
            if (r62 == true) goto L59;
            if (r102 == true) goto L59;
            return false;
        L59:
            return true;
        L42:
            PipVideoOverlay.m11841$$Nest$fputisScrolling(this.this$0, false);
            PipVideoOverlay.m11840$$Nest$fputisScrollDisallowed(this.this$0, false);
            boolean r02 = PipVideoOverlay.m11819$$Nest$fgetonSideToDismiss(this.this$0);
            PipVideoOverlay r13 = this.this$0;
            if (r02 == false) goto L46;
            PipVideoOverlay.m11844$$Nest$fputonSideToDismiss(r13, false);
            PipVideoOverlay.dismissAndDestroy();
            goto L55
        L46:
            if (PipVideoOverlay.m11825$$Nest$fgetpipXSpring(r13).isRunning() == true) goto L53;
            SpringForce r03 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).setStartValue(PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0)).getSpring();
            float r42 = PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0) + (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) / 2.0f);
            int r52 = AndroidUtilities.displaySize.x;
            if (r42 < (r52 / 2.0f)) goto L50;
            float r43 = (r52 - PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L51:
            r03.setFinalPosition(r43);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
            goto L53
        L50:
            r43 = AndroidUtilities.dp(16.0f);
        L53:
            if (PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L55;
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).setStartValue(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).start();
            goto L55
        L36:
            if (PipVideoOverlay.m11809$$Nest$fgetgestureDetector(this.this$0).onTouchEvent(r10) == false) goto L38;
            r102 = true;
        L23:
            PipVideoOverlay.m11837$$Nest$fputcanLongClick(this.this$0, false);
            PipVideoOverlay.m11852$$Nest$mcancelRewind(this.this$0);
            AndroidUtilities.cancelRunOnUIThread(PipVideoOverlay.m11816$$Nest$fgetlongClickCallback(this.this$0));
            goto L25
        L9:
            PipVideoOverlay.m11837$$Nest$fputcanLongClick(r5, false);
            PipVideoOverlay.m11852$$Nest$mcancelRewind(this.this$0);
            AndroidUtilities.cancelRunOnUIThread(PipVideoOverlay.m11816$$Nest$fgetlongClickCallback(this.this$0));
            goto L11
        L5:
            if (r0 != 5) goto L11;
            goto L6
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r7) {
            AndroidUtilities.checkDisplaySize(getContext(), r7);
            PipVideoOverlay.m11845$$Nest$fputpipConfig(this.this$0, null);
            AndroidUtilities.setPreferredMaxRefreshRate(PipVideoOverlay.m11835$$Nest$fgetwindowManager(this.this$0), PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0), PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0));
            if (PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) == (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0))) goto L5;
        L8:
            WindowManager.LayoutParams r72 = PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0);
            PipVideoOverlay r0 = this.this$0;
            int r1 = (int) (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(r0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0));
            PipVideoOverlay.m11847$$Nest$fputpipWidth(r0, r1);
            r72.width = r1;
            WindowManager.LayoutParams r73 = PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0);
            PipVideoOverlay r02 = this.this$0;
            int r12 = (int) (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(r02) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0));
            PipVideoOverlay.m11846$$Nest$fputpipHeight(r02, r12);
            r73.height = r12;
            AndroidUtilities.updateViewLayout(PipVideoOverlay.m11835$$Nest$fgetwindowManager(this.this$0), PipVideoOverlay.m11805$$Nest$fgetcontentView(this.this$0), PipVideoOverlay.m11834$$Nest$fgetwindowLayoutParams(this.this$0));
            SpringForce r74 = PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).setStartValue(PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0)).getSpring();
            float r03 = PipVideoOverlay.m11824$$Nest$fgetpipX(this.this$0) + ((PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0)) / 2.0f);
            int r13 = AndroidUtilities.displaySize.x;
            if (r03 < (r13 / 2.0f)) goto L11;
            float r04 = (r13 - (PipVideoOverlay.m11857$$Nest$mgetSuggestedWidth(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0))) - AndroidUtilities.dp(16.0f);
        L12:
            r74.setFinalPosition(r04);
            PipVideoOverlay.m11825$$Nest$fgetpipXSpring(this.this$0).start();
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).setStartValue(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(PipVideoOverlay.m11826$$Nest$fgetpipY(this.this$0), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0))) - AndroidUtilities.dp(16.0f)));
            PipVideoOverlay.m11827$$Nest$fgetpipYSpring(this.this$0).start();
            return;
        L11:
            r04 = AndroidUtilities.dp(16.0f);
            goto L12
        L5:
            if (PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0) != (PipVideoOverlay.m11856$$Nest$mgetSuggestedHeight(this.this$0) * PipVideoOverlay.m11829$$Nest$fgetscaleFactor(this.this$0))) goto L8;
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$6, reason: invalid class name */
    public class AnonymousClass6 extends PipVideoViewGroup {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass6(PipVideoOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r4) {
            if (PipVideoOverlay.m11836$$Nest$fgetwindowViewSkipRender(this.this$0) == false) goto L5;
            return;
        L5:
            r4.save();
            r4.scale(PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0) / PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).getWidth(), PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0) / PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).getHeight());
            super.draw(r4);
            r4.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).layout(0, 0, PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0), PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0));
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            setMeasuredDimension(View.MeasureSpec.getSize(r2), View.MeasureSpec.getSize(r3));
            PipVideoOverlay.m11804$$Nest$fgetcontentFrameLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(PipVideoOverlay.m11823$$Nest$fgetpipWidth(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(PipVideoOverlay.m11822$$Nest$fgetpipHeight(this.this$0), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$7, reason: invalid class name */
    public class AnonymousClass7 implements VideoForwardDrawable.VideoForwardDrawableDelegate {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass7(PipVideoOverlay r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoForwardDrawable.VideoForwardDrawableDelegate
        public void invalidate() {
            PipVideoOverlay.m11807$$Nest$fgetcontrolsView(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.VideoForwardDrawable.VideoForwardDrawableDelegate
        public void onAnimationEnd() {
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$8, reason: invalid class name */
    public class AnonymousClass8 extends FrameLayout {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass8(PipVideoOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            if (PipVideoOverlay.m11831$$Nest$fgetseekSpeedDrawable(this.this$0) != null) goto L5;
            return;
        L5:
            if (PipVideoOverlay.m11831$$Nest$fgetseekSpeedDrawable(this.this$0).isShown() == false) goto L9;
            PipVideoOverlay.m11831$$Nest$fgetseekSpeedDrawable(this.this$0).setBounds(getLeft(), getTop(), getRight(), getBottom());
            PipVideoOverlay.m11831$$Nest$fgetseekSpeedDrawable(this.this$0).draw(r6);
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            if (PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).isAnimating() == false) goto L6;
            PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).setBounds(getLeft(), getTop(), getRight(), getBottom());
            PipVideoOverlay.m11832$$Nest$fgetvideoForwardDrawable(this.this$0).draw(r6);
        L6:
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0) != null) goto L8;
            return;
        L8:
            if (PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).framesRewinder == null) goto L12;
            r6.save();
            r6.translate(getLeft(), getTop());
            PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).framesRewinder.draw(r6, getRight() - getLeft(), getBottom() - getTop());
            r6.restore();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PipVideoOverlay$9, reason: invalid class name */
    public class AnonymousClass9 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ PipVideoOverlay this$0;

        public AnonymousClass9(PipVideoOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
            PipVideoOverlay.m11820$$Nest$fgetphotoViewer(this.this$0).changedTextureView.setSurfaceTexture(r1);
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    public static final class PipConfig {
        private final SharedPreferences mPrefs;

        /* renamed from: -$$Nest$mgetPipX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11864$$Nest$mgetPipX(PipConfig r0) {
            return r0.getPipX();
        }

        /* renamed from: -$$Nest$mgetPipY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11865$$Nest$mgetPipY(PipConfig r0) {
            return r0.getPipY();
        }

        /* renamed from: -$$Nest$mgetScaleFactor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11866$$Nest$mgetScaleFactor(PipConfig r0) {
            return r0.getScaleFactor();
        }

        /* renamed from: -$$Nest$msetPipX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11867$$Nest$msetPipX(PipConfig r0, float r1) {
            r0.setPipX(r1);
        }

        /* renamed from: -$$Nest$msetPipY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11868$$Nest$msetPipY(PipConfig r0, float r1) {
            r0.setPipY(r1);
        }

        private PipConfig(int r4, int r5) {
            this.mPrefs = ApplicationLoader.applicationContext.getSharedPreferences("pip_layout_" + r4 + "_" + r5, 0);
        }

        public /* synthetic */ PipConfig(int r1, int r2, PipVideoOverlayIA r3) {
            this(r1, r2);
        }

        private float getPipX() {
            return this.mPrefs.getFloat("x", -1.0f);
        }

        private float getPipY() {
            return this.mPrefs.getFloat("y", -1.0f);
        }

        private float getScaleFactor() {
            return this.mPrefs.getFloat("scale_factor", 1.0f);
        }

        private void setPipX(float r2) {
            this.mPrefs.edit().putFloat("x", r2).apply();
        }

        private void setPipY(float r2) {
            this.mPrefs.edit().putFloat("y", r2).apply();
        }
    }

    public static class PipVideoViewGroup extends ViewGroup {
        public PipVideoViewGroup(Context r1) {
            super(r1);
        }
    }

    public final class VideoProgressView extends View {
        private final Paint bufferPaint;
        private final Paint progressPaint;
        final /* synthetic */ PipVideoOverlay this$0;

        public VideoProgressView(PipVideoOverlay r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            Paint r42 = new Paint();
            this.progressPaint = r42;
            Paint r52 = new Paint();
            this.bufferPaint = r52;
            r42.setColor(-1);
            Paint.Style r3 = Paint.Style.STROKE;
            r42.setStyle(r3);
            Paint.Cap r0 = Paint.Cap.ROUND;
            r42.setStrokeCap(r0);
            r42.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r52.setColor(r42.getColor());
            r52.setAlpha((int) (r42.getAlpha() * 0.3f));
            r52.setStyle(r3);
            r52.setStrokeCap(r0);
            r52.setStrokeWidth(AndroidUtilities.dp(2.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            super.onDraw(r11);
            if (PipVideoOverlay.m11815$$Nest$fgetisWebView(this.this$0) == true) goto L5;
        L9:
            int r0 = getWidth();
            int r1 = AndroidUtilities.dp(10.0f);
            float r02 = (r0 - r1) - r1;
            int r2 = ((int) (PipVideoOverlay.m11833$$Nest$fgetvideoProgress(this.this$0) * r02)) + r1;
            float r6 = getHeight() - AndroidUtilities.dp(8.0f);
            if (PipVideoOverlay.m11801$$Nest$fgetbufferProgress(this.this$0) == 0.0f) goto L12;
            float r5 = r1;
            Canvas r4 = r11;
            r4.drawLine(r5, r6, r5 + (r02 * PipVideoOverlay.m11801$$Nest$fgetbufferProgress(this.this$0)), r6, this.bufferPaint);
        L13:
            r4.drawLine(r1, r6, r2, r6, this.progressPaint);
            return;
        L12:
            r4 = r11;
            goto L13
        L5:
            if (PipVideoOverlay.m11821$$Nest$fgetphotoViewerWebView(this.this$0) != null) goto L7;
            return;
        L7:
            if (PipVideoOverlay.m11821$$Nest$fgetphotoViewerWebView(this.this$0).isControllable() == true) goto L9;
        }
    }

    public static /* synthetic */ void $r8$lambda$36PBe9XmKPe8wSQ8x0s02DWESI8(PipVideoOverlay r0) {
        r0.onDismissedInternal();
    }

    /* renamed from: $r8$lambda$8jGLxBMij-CVl1dVaFacfc2F4EU, reason: not valid java name */
    public static /* synthetic */ float m11796$r8$lambda$8jGLxBMijCVl1dVaFacfc2F4EU(PipVideoOverlay r0) {
        return r0.pipY;
    }

    /* renamed from: $r8$lambda$Eb-yuqjtS-DNsZXiM1cTKRWDsQY, reason: not valid java name */
    public static /* synthetic */ void m11797$r8$lambda$EbyuqjtSDNsZXiM1cTKRWDsQY(PipVideoOverlay r0, boolean r1, View r2) {
        r0.lambda$showInternal$10(r1, r2);
    }

    /* renamed from: $r8$lambda$Nb8EiEU2pF96vKkzt-8OKaK8dy0, reason: not valid java name */
    public static /* synthetic */ void m11798$r8$lambda$Nb8EiEU2pF96vKkzt8OKaK8dy0(PipVideoOverlay r0) {
        r0.lambda$new$5();
    }

    public static /* synthetic */ void $r8$lambda$O6TCDUBqPYhJigIzkyael9UJjyk(PipVideoOverlay r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$showInternal$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Op4bFPbfcvGm4BhCfu2KtvciLjk(PipVideoOverlay r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$SqeTIAx2Nt1QXh1ssC724WwzzGU(PipVideoOverlay r0, ValueAnimator r1) {
        r0.lambda$toggleControls$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$T73iSTdJWKrqKwG4fHFr4MqbIKA(PipVideoOverlay r2, float r3) {
        WindowManager.LayoutParams r0 = r2.windowLayoutParams;
        r2.pipY = r3;
        r0.y = (int) r3;
        AndroidUtilities.updateViewLayout(r2.windowManager, r2.contentView, r0);     // Catch: IllegalArgumentException -> L5
        return;
    L5:
        r2.pipYSpring.cancel();
    }

    public static /* synthetic */ void $r8$lambda$VozdQr6gdf9oTN1H0adHU7A3Pl8(PipVideoOverlay r2, float r3) {
        WindowManager.LayoutParams r0 = r2.windowLayoutParams;
        r2.pipX = r3;
        r0.x = (int) r3;
        AndroidUtilities.updateViewLayout(r2.windowManager, r2.contentView, r0);     // Catch: IllegalArgumentException -> L5
        return;
    L5:
        r2.pipXSpring.cancel();
    }

    public static /* synthetic */ void $r8$lambda$WuZVMb9RZW0wKhVlPCD2ilMsLC0(View r0) {
        dismissAndDestroy();
    }

    /* renamed from: $r8$lambda$ZBaA-fHpBa0ZLxpJGyx8xL7YA5M, reason: not valid java name */
    public static /* synthetic */ float m11799$r8$lambda$ZBaAfHpBa0ZLxpJGyx8xL7YA5M(PipVideoOverlay r0) {
        return r0.pipX;
    }

    public static /* synthetic */ void $r8$lambda$rZ5siaN0Dww2Vq0Lo0ZgHikIXL4(PipVideoOverlay r0, View r1) {
        r0.lambda$showInternal$11(r1);
    }

    /* renamed from: $r8$lambda$t2e3sTZ7iL_Weez62j-9OKWV3TA, reason: not valid java name */
    public static /* synthetic */ void m11800$r8$lambda$t2e3sTZ7iL_Weez62j9OKWV3TA(PipVideoOverlay r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$showInternal$7(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetbufferProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11801$$Nest$fgetbufferProgress(PipVideoOverlay r0) {
        return r0.bufferProgress;
    }

    /* renamed from: -$$Nest$fgetcanLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11802$$Nest$fgetcanLongClick(PipVideoOverlay r0) {
        return r0.canLongClick;
    }

    /* renamed from: -$$Nest$fgetconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11803$$Nest$fgetconsumingChild(PipVideoOverlay r0) {
        return r0.consumingChild;
    }

    /* renamed from: -$$Nest$fgetcontentFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11804$$Nest$fgetcontentFrameLayout(PipVideoOverlay r0) {
        return r0.contentFrameLayout;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m11805$$Nest$fgetcontentView(PipVideoOverlay r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcontrolsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11806$$Nest$fgetcontrolsAnimator(PipVideoOverlay r0) {
        return r0.controlsAnimator;
    }

    /* renamed from: -$$Nest$fgetcontrolsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11807$$Nest$fgetcontrolsView(PipVideoOverlay r0) {
        return r0.controlsView;
    }

    /* renamed from: -$$Nest$fgetdismissControlsCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11808$$Nest$fgetdismissControlsCallback(PipVideoOverlay r0) {
        return r0.dismissControlsCallback;
    }

    /* renamed from: -$$Nest$fgetgestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GestureDetectorFixDoubleTap m11809$$Nest$fgetgestureDetector(PipVideoOverlay r0) {
        return r0.gestureDetector;
    }

    /* renamed from: -$$Nest$fgetisDismissing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11810$$Nest$fgetisDismissing(PipVideoOverlay r0) {
        return r0.isDismissing;
    }

    /* renamed from: -$$Nest$fgetisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11811$$Nest$fgetisScrollDisallowed(PipVideoOverlay r0) {
        return r0.isScrollDisallowed;
    }

    /* renamed from: -$$Nest$fgetisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11812$$Nest$fgetisScrolling(PipVideoOverlay r0) {
        return r0.isScrolling;
    }

    /* renamed from: -$$Nest$fgetisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11813$$Nest$fgetisShowingControls(PipVideoOverlay r0) {
        return r0.isShowingControls;
    }

    /* renamed from: -$$Nest$fgetisVideoCompleted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11814$$Nest$fgetisVideoCompleted(PipVideoOverlay r0) {
        return r0.isVideoCompleted;
    }

    /* renamed from: -$$Nest$fgetisWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11815$$Nest$fgetisWebView(PipVideoOverlay r0) {
        return r0.isWebView;
    }

    /* renamed from: -$$Nest$fgetlongClickCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11816$$Nest$fgetlongClickCallback(PipVideoOverlay r0) {
        return r0.longClickCallback;
    }

    /* renamed from: -$$Nest$fgetmaxScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11817$$Nest$fgetmaxScaleFactor(PipVideoOverlay r0) {
        return r0.maxScaleFactor;
    }

    /* renamed from: -$$Nest$fgetminScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11818$$Nest$fgetminScaleFactor(PipVideoOverlay r0) {
        return r0.minScaleFactor;
    }

    /* renamed from: -$$Nest$fgetonSideToDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11819$$Nest$fgetonSideToDismiss(PipVideoOverlay r0) {
        return r0.onSideToDismiss;
    }

    /* renamed from: -$$Nest$fgetphotoViewer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer m11820$$Nest$fgetphotoViewer(PipVideoOverlay r0) {
        return r0.photoViewer;
    }

    /* renamed from: -$$Nest$fgetphotoViewerWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewerWebView m11821$$Nest$fgetphotoViewerWebView(PipVideoOverlay r0) {
        return r0.photoViewerWebView;
    }

    /* renamed from: -$$Nest$fgetpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11822$$Nest$fgetpipHeight(PipVideoOverlay r0) {
        return r0.pipHeight;
    }

    /* renamed from: -$$Nest$fgetpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11823$$Nest$fgetpipWidth(PipVideoOverlay r0) {
        return r0.pipWidth;
    }

    /* renamed from: -$$Nest$fgetpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11824$$Nest$fgetpipX(PipVideoOverlay r0) {
        return r0.pipX;
    }

    /* renamed from: -$$Nest$fgetpipXSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m11825$$Nest$fgetpipXSpring(PipVideoOverlay r0) {
        return r0.pipXSpring;
    }

    /* renamed from: -$$Nest$fgetpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11826$$Nest$fgetpipY(PipVideoOverlay r0) {
        return r0.pipY;
    }

    /* renamed from: -$$Nest$fgetpipYSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m11827$$Nest$fgetpipYSpring(PipVideoOverlay r0) {
        return r0.pipYSpring;
    }

    /* renamed from: -$$Nest$fgetpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11828$$Nest$fgetpostedDismissControls(PipVideoOverlay r0) {
        return r0.postedDismissControls;
    }

    /* renamed from: -$$Nest$fgetscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11829$$Nest$fgetscaleFactor(PipVideoOverlay r0) {
        return r0.scaleFactor;
    }

    /* renamed from: -$$Nest$fgetscaleGestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScaleGestureDetector m11830$$Nest$fgetscaleGestureDetector(PipVideoOverlay r0) {
        return r0.scaleGestureDetector;
    }

    /* renamed from: -$$Nest$fgetseekSpeedDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SeekSpeedDrawable m11831$$Nest$fgetseekSpeedDrawable(PipVideoOverlay r0) {
        return r0.seekSpeedDrawable;
    }

    /* renamed from: -$$Nest$fgetvideoForwardDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoForwardDrawable m11832$$Nest$fgetvideoForwardDrawable(PipVideoOverlay r0) {
        return r0.videoForwardDrawable;
    }

    /* renamed from: -$$Nest$fgetvideoProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11833$$Nest$fgetvideoProgress(PipVideoOverlay r0) {
        return r0.videoProgress;
    }

    /* renamed from: -$$Nest$fgetwindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m11834$$Nest$fgetwindowLayoutParams(PipVideoOverlay r0) {
        return r0.windowLayoutParams;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m11835$$Nest$fgetwindowManager(PipVideoOverlay r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fgetwindowViewSkipRender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11836$$Nest$fgetwindowViewSkipRender(PipVideoOverlay r0) {
        return r0.windowViewSkipRender;
    }

    /* renamed from: -$$Nest$fputcanLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11837$$Nest$fputcanLongClick(PipVideoOverlay r0, boolean r1) {
        r0.canLongClick = r1;
    }

    /* renamed from: -$$Nest$fputconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11838$$Nest$fputconsumingChild(PipVideoOverlay r0, View r1) {
        r0.consumingChild = r1;
    }

    /* renamed from: -$$Nest$fputcontrolsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11839$$Nest$fputcontrolsAnimator(PipVideoOverlay r0, ValueAnimator r1) {
        r0.controlsAnimator = r1;
    }

    /* renamed from: -$$Nest$fputisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11840$$Nest$fputisScrollDisallowed(PipVideoOverlay r0, boolean r1) {
        r0.isScrollDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11841$$Nest$fputisScrolling(PipVideoOverlay r0, boolean r1) {
        r0.isScrolling = r1;
    }

    /* renamed from: -$$Nest$fputisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11842$$Nest$fputisShowingControls(PipVideoOverlay r0, boolean r1) {
        r0.isShowingControls = r1;
    }

    /* renamed from: -$$Nest$fputlongClickStartPoint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11843$$Nest$fputlongClickStartPoint(PipVideoOverlay r0, float[] r1) {
        r0.longClickStartPoint = r1;
    }

    /* renamed from: -$$Nest$fputonSideToDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11844$$Nest$fputonSideToDismiss(PipVideoOverlay r0, boolean r1) {
        r0.onSideToDismiss = r1;
    }

    /* renamed from: -$$Nest$fputpipConfig, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11845$$Nest$fputpipConfig(PipVideoOverlay r0, PipConfig r1) {
        r0.pipConfig = r1;
    }

    /* renamed from: -$$Nest$fputpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11846$$Nest$fputpipHeight(PipVideoOverlay r0, int r1) {
        r0.pipHeight = r1;
    }

    /* renamed from: -$$Nest$fputpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11847$$Nest$fputpipWidth(PipVideoOverlay r0, int r1) {
        r0.pipWidth = r1;
    }

    /* renamed from: -$$Nest$fputpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11848$$Nest$fputpipX(PipVideoOverlay r0, float r1) {
        r0.pipX = r1;
    }

    /* renamed from: -$$Nest$fputpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11849$$Nest$fputpipY(PipVideoOverlay r0, float r1) {
        r0.pipY = r1;
    }

    /* renamed from: -$$Nest$fputpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11850$$Nest$fputpostedDismissControls(PipVideoOverlay r0, boolean r1) {
        r0.postedDismissControls = r1;
    }

    /* renamed from: -$$Nest$fputscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11851$$Nest$fputscaleFactor(PipVideoOverlay r0, float r1) {
        r0.scaleFactor = r1;
    }

    /* renamed from: -$$Nest$mcancelRewind, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11852$$Nest$mcancelRewind(PipVideoOverlay r0) {
        r0.cancelRewind();
    }

    /* renamed from: -$$Nest$mgetCurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11853$$Nest$mgetCurrentPosition(PipVideoOverlay r2) {
        return r2.getCurrentPosition();
    }

    /* renamed from: -$$Nest$mgetDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11854$$Nest$mgetDuration(PipVideoOverlay r2) {
        return r2.getDuration();
    }

    /* renamed from: -$$Nest$mgetPipConfig, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PipConfig m11855$$Nest$mgetPipConfig(PipVideoOverlay r0) {
        return r0.getPipConfig();
    }

    /* renamed from: -$$Nest$mgetSuggestedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11856$$Nest$mgetSuggestedHeight(PipVideoOverlay r0) {
        return r0.getSuggestedHeight();
    }

    /* renamed from: -$$Nest$mgetSuggestedWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11857$$Nest$mgetSuggestedWidth(PipVideoOverlay r0) {
        return r0.getSuggestedWidth();
    }

    /* renamed from: -$$Nest$monDismissedInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11858$$Nest$monDismissedInternal(PipVideoOverlay r0) {
        r0.onDismissedInternal();
    }

    /* renamed from: -$$Nest$monUpdateRewindProgressUiInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11859$$Nest$monUpdateRewindProgressUiInternal(PipVideoOverlay r0, long r1, float r3, boolean r4) {
        r0.onUpdateRewindProgressUiInternal(r1, r3, r4);
    }

    /* renamed from: -$$Nest$mseekTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11860$$Nest$mseekTo(PipVideoOverlay r0, long r1) {
        r0.seekTo(r1);
    }

    /* renamed from: -$$Nest$mtoggleControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11861$$Nest$mtoggleControls(PipVideoOverlay r0, boolean r1) {
        r0.toggleControls(r1);
    }

    static {
        PIP_X_PROPERTY = new SimpleFloatPropertyCompat("pipX", new PipVideoOverlay$$ExternalSyntheticLambda0(), new PipVideoOverlay$$ExternalSyntheticLambda1());
        PIP_Y_PROPERTY = new SimpleFloatPropertyCompat("pipY", new PipVideoOverlay$$ExternalSyntheticLambda2(), new PipVideoOverlay$$ExternalSyntheticLambda3());
        instance = new PipVideoOverlay();
    }

    public PipVideoOverlay() {
        this.minScaleFactor = 0.75f;
        this.maxScaleFactor = 1.4f;
        this.scaleFactor = 1.0f;
        this.videoForwardDrawable = new VideoForwardDrawable(false);
        this.progressRunnable = new PipVideoOverlay$$ExternalSyntheticLambda5(this);
        this.longClickStartPoint = new float[2];
        this.longClickCallback = new PipVideoOverlay$$ExternalSyntheticLambda6(this);
        this.dismissControlsCallback = new PipVideoOverlay$$ExternalSyntheticLambda7(this);
    }

    private void cancelRewind() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getVideoPlayerRewinder() != null) goto L8;
        return;
    L8:
        this.photoViewer.getVideoPlayerRewinder().cancelRewind();
    }

    public static void dismiss() {
        dismiss(false);
    }

    public static void dismiss(boolean r2) {
        instance.dismissInternal(r2, false);
    }

    public static void dismiss(boolean r1, boolean r2) {
        instance.dismissInternal(r1, r2);
    }

    public static void dismissAndDestroy() {
        PipVideoOverlay r0 = instance;
        EmbedBottomSheet r1 = r0.parentSheet;
        if (r1 == null) goto L5;
        r1.destroy();
    L8:
        dismiss();
        return;
    L5:
        PhotoViewer r02 = r0.photoViewer;
        if (r02 == null) goto L8;
        r02.destroyPhotoViewer();
        goto L8
    }

    private void dismissInternal(boolean r8, boolean r9) {
        if (this.isDismissing == false) goto L5;
        return;
    L5:
        this.isDismissing = true;
        ValueAnimator r1 = this.controlsAnimator;
        if (r1 == null) goto L9;
        r1.cancel();
    L9:
        if (this.postedDismissControls == false) goto L11;
        AndroidUtilities.cancelRunOnUIThread(this.dismissControlsCallback);
        this.postedDismissControls = false;
    L11:
        SpringAnimation r12 = this.pipXSpring;
        if (r12 == null) goto L14;
        r12.cancel();
        this.pipYSpring.cancel();
    L14:
        if (r8 == false) goto L16;
    L20:
        if (r9 == false) goto L23;
        onDismissedInternal();
        return;
    L23:
        AndroidUtilities.runOnUIThread(new PipVideoOverlay$$ExternalSyntheticLambda4(this), 100);
        return;
    L16:
        if (this.contentView == null) goto L20;
        AnimatorSet r82 = new AnimatorSet();
        r82.setDuration(250);
        r82.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.contentView, View.ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_X, new float[]{0.1f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_Y, new float[]{0.1f})});
        r82.addListener(new AnonymousClass2(this));
        r82.start();
    }

    private long getCurrentPosition() {
        if (this.photoViewerWebView != null) goto L5;
        VideoPlayer r2 = this.photoViewer.getVideoPlayer();
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getCurrentPosition();
    L5:
        return r0.getCurrentPosition();
    }

    private long getDuration() {
        if (this.photoViewerWebView != null) goto L5;
        VideoPlayer r2 = this.photoViewer.getVideoPlayer();
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getDuration();
    L5:
        return r0.getVideoDuration();
    }

    public static View getInnerView() {
        return instance.innerView;
    }

    private PipConfig getPipConfig() {
        if (this.pipConfig != null) goto L6;
        Point r1 = AndroidUtilities.displaySize;
        this.pipConfig = new PipConfig(r1.x, r1.y, null);
    L6:
        return this.pipConfig;
    }

    public static RectOld getPipRect(boolean r7, float r8) {
        RectOld r0 = new RectOld();
        float r1 = 1.0f / r8;
        PipVideoOverlay r82 = instance;
        if (r82.isVisible == false) goto L8;
        if (r7 == true) goto L8;
        r0.x = r82.pipX;
        r0.y = r82.pipY + AndroidUtilities.statusBarHeight;
        PipVideoOverlay r72 = instance;
        r0.width = r72.pipWidth;
        r0.height = r72.pipHeight;
        return r0;
    L8:
        float r73 = PipConfig.m11864$$Nest$mgetPipX(r82.getPipConfig());
        float r83 = PipConfig.m11865$$Nest$mgetPipY(instance.getPipConfig());
        float r2 = PipConfig.m11866$$Nest$mgetScaleFactor(instance.getPipConfig());
        r0.width = getSuggestedWidth(r1) * r2;
        r0.height = getSuggestedHeight(r1) * r2;
        if (r73 == (-1.0f)) goto L15;
        float r22 = r0.width;
        float r74 = r73 + (r22 / 2.0f);
        int r5 = AndroidUtilities.displaySize.x;
        if (r74 < (r5 / 2.0f)) goto L13;
        float r75 = (r5 - r22) - AndroidUtilities.dp(16.0f);
    L14:
        r0.x = r75;
    L17:
        if (r83 == (-1.0f)) goto L20;
        r0.y = MathUtils.clamp(r83, AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - AndroidUtilities.dp(16.0f)) - r0.height) + AndroidUtilities.statusBarHeight;
        return r0;
    L20:
        r0.y = AndroidUtilities.dp(16.0f) + AndroidUtilities.statusBarHeight;
        return r0;
    L13:
        r75 = AndroidUtilities.dp(16.0f);
        goto L14
    L15:
        r0.x = (AndroidUtilities.displaySize.x - r0.width) - AndroidUtilities.dp(16.0f);
        goto L17
    }

    public static PipSource getPipSource() {
        PipVideoOverlay r0 = instance;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.pipSource;
    }

    public static TextureView getPipTextureView() {
        PipVideoOverlay r0 = instance;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.pipTextureView;
    }

    private float getRatio() {
        if (this.aspectRatio != null) goto L10;
        this.aspectRatio = Float.valueOf(this.mVideoHeight / this.mVideoWidth);
        Point r0 = AndroidUtilities.displaySize;
        this.maxScaleFactor = (Math.min(r0.x, r0.y) - AndroidUtilities.dp(32.0f)) / getSuggestedWidth();
        VideoForwardDrawable r02 = this.videoForwardDrawable;
        if (this.aspectRatio.floatValue() >= 1.0f) goto L7;
        float r1 = 0.6f;
    L8:
        r02.setPlayScaleFactor(r1);
        goto L10
    L7:
        r1 = 0.45f;
    L10:
        return this.aspectRatio.floatValue();
    }

    private int getSuggestedHeight() {
        return getSuggestedHeight(getRatio());
    }

    private static int getSuggestedHeight(float r1) {
        return (int) (getSuggestedWidth(r1) * r1);
    }

    private int getSuggestedWidth() {
        return getSuggestedWidth(getRatio());
    }

    private static int getSuggestedWidth(float r1) {
        if (r1 < 1.0f) goto L7;
        Point r12 = AndroidUtilities.displaySize;
        float r13 = Math.min(r12.x, r12.y);
        float r0 = 0.35f;
    L6:
        return (int) (r13 * r0);
    L7:
        Point r14 = AndroidUtilities.displaySize;
        r13 = Math.min(r14.x, r14.y);
        r0 = 0.6f;
        goto L6
    }

    public static boolean isVisible() {
        return instance.isVisible;
    }

    private /* synthetic */ void lambda$new$4() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.photoViewerWebView == null) goto L9;
        this.videoProgress = r1.getCurrentPosition() / this.photoViewerWebView.getVideoDuration();
        this.bufferProgress = this.photoViewerWebView.getBufferedPosition();
    L12:
        this.videoProgressView.invalidate();
        AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
        return;
    L9:
        if (r0.getVideoPlayer() != null) goto L11;
        return;
    L11:
        float r1 = getDuration();
        this.videoProgress = r0.getCurrentPosition() / r1;
        this.bufferProgress = r0.getBufferedPosition() / r1;
        goto L12
    }

    private /* synthetic */ void lambda$new$5() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L5;
    L8:
        this.isShowingControls = false;
        toggleControls(false);
        this.postedDismissControls = false;
        return;
    L5:
        if (r0.getVideoPlayerRewinder().rewinding == false) goto L8;
        AndroidUtilities.runOnUIThread(this.dismissControlsCallback, 1500);
    }

    private /* synthetic */ void lambda$showInternal$10(boolean r5, View r6) {
        List<ActivityManager.RunningAppProcessInfo> r0 = ((ActivityManager) r6.getContext().getSystemService("activity")).getRunningAppProcesses();
        boolean r1 = true;
        if (r0 != null) goto L5;
    L10:
        if (r5 == true) goto L16;
        if (r1 == true) goto L13;
    L14:
        LaunchActivity.onResumeStaticCallback = new EmbedBottomSheet$$ExternalSyntheticLambda6(r6);
        Context r4 = ApplicationLoader.applicationContext;
        Intent r52 = new Intent(r4, LaunchActivity.class);
        r52.addFlags(268435456);
        r4.startActivity(r52);
        return;
    L13:
        if (LaunchActivity.isResumed == false) goto L14;
    L16:
        EmbedBottomSheet r53 = this.parentSheet;
        if (r53 == null) goto L20;
        r53.exitFromPip();
        return;
    L20:
        PhotoViewer r42 = this.photoViewer;
        if (r42 == null) goto L24;
        r42.exitFromPip();
        return;
    L24:
        return;
    L5:
        if (r0.isEmpty() == true) goto L10;
        if (r0.get(0).importance == 100) goto L10;
        r1 = false;
        goto L10
    }

    private /* synthetic */ void lambda$showInternal$11(View r2) {
        PhotoViewer r22 = this.photoViewer;
        if (r22 == null) goto L21;
        PhotoViewerWebView r0 = this.photoViewerWebView;
        if (r0 == null) goto L11;
        boolean r23 = r0.isPlaying();
        PhotoViewerWebView r1 = this.photoViewerWebView;
        if (r23 == false) goto L10;
        r1.pauseVideo();
    L19:
        updatePlayButton();
        return;
    L10:
        r1.playVideo();
        goto L19
    L11:
        VideoPlayer r24 = r22.getVideoPlayer();
        if (r24 != null) goto L14;
        return;
    L14:
        boolean r02 = r24.isPlaying();
        if (r02 == false) goto L17;
        r24.pause();
    L18:
        this.photoViewer.requestAudioFocus(!r02);
        goto L19
    L17:
        r24.play();
        goto L18
    }

    private /* synthetic */ void lambda$showInternal$7(DynamicAnimation r1, boolean r2, float r3, float r4) {
        PipConfig.m11867$$Nest$msetPipX(getPipConfig(), r3);
    }

    private /* synthetic */ void lambda$showInternal$8(DynamicAnimation r1, boolean r2, float r3, float r4) {
        PipConfig.m11868$$Nest$msetPipY(getPipConfig(), r3);
    }

    private /* synthetic */ void lambda$toggleControls$6(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.controlsView.setAlpha(r12);
    }

    private void onDismissedInternal() {
        ViewGroup r0 = this.contentView;     // Catch: Exception -> L15
        if (r0 == null) goto L7;
        if (r0.getParent() == null) goto L7;
        this.windowManager.removeViewImmediate(this.contentView);     // Catch: Exception -> L15
    L7:
        PhotoViewerWebView r02 = this.photoViewerWebView;
        if (r02 == null) goto L10;
        r02.showControls();
    L10:
        this.videoProgressView = null;
        this.innerView = null;
        this.photoViewer = null;
        PipSource r1 = this.pipSource;
        if (r1 == null) goto L13;
        r1.destroy();
        this.pipSource = null;
    L13:
        this.photoViewerWebView = null;
        this.parentSheet = null;
        this.consumingChild = null;
        this.isScrolling = false;
        this.isVisible = false;
        this.isDismissing = false;
        this.canLongClick = false;
        cancelRewind();
        AndroidUtilities.cancelRunOnUIThread(this.longClickCallback);
    }

    public static void onRewindCanceled() {
        instance.onRewindCanceledInternal();
    }

    private void onRewindCanceledInternal() {
        this.videoForwardDrawable.setShowing(false);
    }

    public static void onRewindStart(boolean r1) {
        instance.onRewindStartInternal(r1);
    }

    private void onRewindStartInternal(boolean r3) {
        this.videoForwardDrawable.setOneShootAnimation(false);
        this.videoForwardDrawable.setLeftSide(!r3);
        this.videoForwardDrawable.setShowing(true);
        VideoProgressView r32 = this.videoProgressView;
        if (r32 == null) goto L5;
        r32.invalidate();
    L5:
        FrameLayout r2 = this.controlsView;
        if (r2 == null) goto L9;
        r2.invalidate();
        return;
    }

    public static void onUpdateRewindProgressUi(long r1, float r3, boolean r4) {
        instance.onUpdateRewindProgressUiInternal(r1, r3, r4);
    }

    private void onUpdateRewindProgressUiInternal(long r3, float r5, boolean r6) {
        this.videoForwardDrawable.setTime(0);
        if (r6 == false) goto L11;
        this.videoProgress = r5;
        VideoProgressView r32 = this.videoProgressView;
        if (r32 == null) goto L7;
        r32.invalidate();
    L7:
        FrameLayout r2 = this.controlsView;
        if (r2 == null) goto L12;
        r2.invalidate();
        return;
    L12:
        return;
    }

    public static void onVideoCompleted() {
        instance.onVideoCompletedInternal();
    }

    private void onVideoCompletedInternal() {
        if (this.isVisible == false) goto L11;
        VideoProgressView r0 = this.videoProgressView;
        if (r0 == null) goto L13;
        this.isVideoCompleted = true;
        this.videoProgress = 0.0f;
        this.bufferProgress = 0.0f;
        r0.invalidate();
        updatePlayButtonInternal();
        AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
        if (this.isShowingControls == true) goto L12;
        toggleControls(true);
        AndroidUtilities.cancelRunOnUIThread(this.dismissControlsCallback);
        return;
    L12:
        return;
    L13:
        return;
    }

    private void seekTo(long r2) {
        PhotoViewerWebView r0 = this.photoViewerWebView;
        if (r0 == null) goto L6;
        r0.seekTo(r2);
        return;
    L6:
        VideoPlayer r1 = this.photoViewer.getVideoPlayer();
        if (r1 != null) goto L9;
        return;
    L9:
        r1.seekTo(r2);
    }

    public static void setBufferedProgress(float r2) {
        PipVideoOverlay r0 = instance;
        if (r2 <= r0.bufferProgress) goto L8;
        r0.bufferProgress = r2;
        VideoProgressView r22 = r0.videoProgressView;
        if (r22 == null) goto L9;
        r22.invalidate();
        return;
    L9:
        return;
    }

    public static void setParentSheet(EmbedBottomSheet r1) {
        instance.parentSheet = r1;
    }

    public static void setPhotoViewer(PhotoViewer r5) {
        instance.photoViewer = r5;
        VideoPlayer r0 = r5.getVideoPlayer();
        PipSource r1 = instance.pipSource;
        if (r1 == null) goto L5;
        r1.destroy();
        instance.pipSource = null;
    L5:
        if (r0 != null) goto L7;
    L9:
        instance.updatePlayButtonInternal();
        return;
    L7:
        if (PipUtils.checkPermissions(r5.getParentActivity()) != 1) goto L9;
        PipVideoOverlay r12 = instance;
        PipSource.Builder r52 = new PipSource.Builder(r5.getParentActivity(), instance).setTagPrefix("photo-viewer-pip-" + r0.playerId).setPriority(1).setCornerRadius(AndroidUtilities.dp(10.0f)).setContentView(instance.contentView).setPlaceholderView(instance.placeholderView);
        PipVideoOverlay r3 = instance;
        r12.pipSource = r52.setContentRatio(r3.mVideoWidth, r3.mVideoHeight).setPlayer(r0.player).setNeedMediaSession(true).build();
        goto L9
    }

    public static boolean show(boolean r6, Activity r7, View r8, int r9, int r10) {
        return show(r6, r7, r8, r9, r10, false);
    }

    public static boolean show(boolean r7, Activity r8, View r9, int r10, int r11, boolean r12) {
        return show(r7, r8, null, r9, r10, r11, r12);
    }

    public static boolean show(boolean r8, Activity r9, PhotoViewerWebView r10, View r11, int r12, int r13, boolean r14) {
        return instance.showInternal(r8, r9, r11, r10, r12, r13, r14);
    }

    private boolean showInternal(boolean r32, Activity r33, View r34, PhotoViewerWebView r35, int r36, int r37, boolean r38) {
        if (r32 == false) goto L5;
    L8:
        boolean r4 = true;
    L10:
        if (this.isVisible == false) goto L12;
        return false;
    L12:
        this.isVisible = true;
        this.mVideoWidth = r36;
        this.mVideoHeight = r37;
        this.aspectRatio = null;
        if (r35 != null) goto L15;
    L17:
        this.photoViewerWebView = null;
    L18:
        float r1 = PipConfig.m11864$$Nest$mgetPipX(getPipConfig());
        float r5 = PipConfig.m11865$$Nest$mgetPipY(getPipConfig());
        this.scaleFactor = PipConfig.m11866$$Nest$mgetScaleFactor(getPipConfig());
        this.pipWidth = (int) (getSuggestedWidth() * this.scaleFactor);
        this.pipHeight = (int) (getSuggestedHeight() * this.scaleFactor);
        this.isShowingControls = false;
        this.pipXSpring = new SpringAnimation(this, PIP_X_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f)).addEndListener(new PipVideoOverlay$$ExternalSyntheticLambda8(this));
        this.pipYSpring = new SpringAnimation(this, PIP_Y_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f)).addEndListener(new PipVideoOverlay$$ExternalSyntheticLambda9(this));
        if (r4 == false) goto L21;
        Context r6 = r33;
    L22:
        int r7 = ViewConfiguration.get(r6).getScaledTouchSlop();
        ScaleGestureDetector r8 = new ScaleGestureDetector(r6, new AnonymousClass3(this));
        this.scaleGestureDetector = r8;
        r8.setQuickScaleEnabled(false);
        this.scaleGestureDetector.setStylusScaleEnabled(false);
        this.gestureDetector = new GestureDetectorFixDoubleTap(r6, new AnonymousClass4(this, r7));
        this.contentFrameLayout = new AnonymousClass5(this, r6);
        AnonymousClass6 r72 = new AnonymousClass6(this, r6);
        this.contentView = r72;
        r72.addView(this.contentFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.contentFrameLayout.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(10.0f)));
        this.contentFrameLayout.setClipToOutline(true);
        this.contentFrameLayout.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        this.innerView = r34;
        if (r34.getParent() == null) goto L25;
        ((ViewGroup) this.innerView.getParent()).removeView(this.innerView);
    L25:
        this.contentFrameLayout.addView(this.innerView, LayoutHelper.createFrame(-1, -1.0f));
        View r73 = new View(r6);
        this.placeholderView = r73;
        this.contentFrameLayout.addView(r73, LayoutHelper.createFrame(-1, -1.0f));
        this.videoForwardDrawable.setDelegate(new AnonymousClass7(this));
        this.controlsView = new AnonymousClass8(this, r6);
        FrameLayout r82 = this.controlsView;
        Objects.requireNonNull(r82);
        this.seekSpeedDrawable = new SeekSpeedDrawable(new PipVideoOverlay$$ExternalSyntheticLambda10(r82), true, false);
        this.controlsView.setWillNotDraw(false);
        this.controlsView.setAlpha(0.0f);
        View r74 = new View(r6);
        r74.setBackgroundColor(1275068416);
        this.controlsView.addView(r74, LayoutHelper.createFrame(-1, -1.0f));
        int r75 = AndroidUtilities.dp(8.0f);
        ImageView r11 = new ImageView(r6);
        r11.setImageResource(R.drawable.pip_video_close);
        int r12 = Theme.key_voipgroup_actionBarItems;
        int r13 = Theme.getColor(r12);
        PorterDuff.Mode r14 = PorterDuff.Mode.MULTIPLY;
        r11.setColorFilter(r13, r14);
        int r132 = Theme.key_listSelector;
        r11.setBackground(Theme.createSelectorDrawable(Theme.getColor(r132)));
        r11.setPadding(r75, r75, r75, r75);
        r11.setOnClickListener(new PipVideoOverlay$$ExternalSyntheticLambda11());
        this.controlsView.addView(r11, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 4.0f, 0.0f));
        ImageView r3 = new ImageView(r6);
        r3.setImageResource(R.drawable.pip_video_expand);
        r3.setColorFilter(Theme.getColor(r12), r14);
        r3.setBackground(Theme.createSelectorDrawable(Theme.getColor(r132)));
        r3.setPadding(r75, r75, r75, r75);
        r3.setOnClickListener(new PipVideoOverlay$$ExternalSyntheticLambda12(this, r4));
        this.controlsView.addView(r3, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 48.0f, 0.0f));
        ImageView r39 = new ImageView(r6);
        this.playPauseButton = r39;
        r39.setColorFilter(Theme.getColor(r12), r14);
        this.playPauseButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(r132)));
        this.playPauseButton.setOnClickListener(new PipVideoOverlay$$ExternalSyntheticLambda13(this));
        View r310 = this.innerView;
        if ((r310 instanceof WebView) == false) goto L28;
    L31:
        boolean r311 = true;
    L32:
        this.isWebView = r311;
        ImageView r76 = this.playPauseButton;
        if (r311 == false) goto L39;
        PhotoViewerWebView r312 = this.photoViewerWebView;
        if (r312 != null) goto L37;
    L38:
        int r313 = 8;
    L40:
        r76.setVisibility(r313);
        this.controlsView.addView(this.playPauseButton, LayoutHelper.createFrame(38, 38, 17));
        VideoProgressView r314 = new VideoProgressView(this, r6);
        this.videoProgressView = r314;
        this.controlsView.addView(r314, LayoutHelper.createFrame(-1, -1.0f));
        this.contentFrameLayout.addView(this.controlsView, LayoutHelper.createFrame(-1, -1.0f));
        if (r4 == false) goto L43;
        Context r315 = r33;
    L44:
        this.windowManager = (WindowManager) r315.getSystemService("window");
        WindowManager.LayoutParams r316 = PipUtils.createWindowLayoutParams(r6, r4);
        this.windowLayoutParams = r316;
        int r42 = this.pipWidth;
        r316.width = r42;
        r316.height = this.pipHeight;
        if (r1 == (-1.0f)) goto L51;
        float r15 = r1 + (r42 / 2.0f);
        int r62 = AndroidUtilities.displaySize.x;
        if (r15 < (r62 / 2.0f)) goto L49;
        float r16 = (r62 - r42) - AndroidUtilities.dp(16.0f);
    L50:
        this.pipX = r16;
        r316.x = (int) r16;
    L52:
        WindowManager.LayoutParams r317 = this.windowLayoutParams;
        if (r5 == (-1.0f)) goto L55;
        float r17 = MathUtils.clamp(r5, AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - AndroidUtilities.dp(16.0f)) - this.pipHeight);
        this.pipY = r17;
        r317.y = (int) r17;
    L56:
        WindowManager.LayoutParams r18 = this.windowLayoutParams;
        r18.dimAmount = 0.0f;
        r18.flags = 520;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.contentView, r18);
        if (r38 == false) goto L59;
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
    L60:
        return true;
    L59:
        this.contentView.setAlpha(0.0f);
        this.contentView.setScaleX(0.1f);
        this.contentView.setScaleY(0.1f);
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
        AnimatorSet r19 = new AnimatorSet();
        r19.setDuration(250);
        r19.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r19.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.contentView, View.ALPHA, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_Y, new float[]{1.0f})});
        r19.start();
        goto L60
    L55:
        float r110 = AndroidUtilities.dp(16.0f);
        this.pipY = r110;
        r317.y = (int) r110;
        goto L56
    L49:
        r16 = AndroidUtilities.dp(16.0f);
        goto L50
    L51:
        float r111 = (AndroidUtilities.displaySize.x - r42) - AndroidUtilities.dp(16.0f);
        this.pipX = r111;
        r316.x = (int) r111;
        goto L52
    L43:
        r315 = ApplicationLoader.applicationContext;
        goto L44
    L37:
        if (r312.isControllable() == false) goto L38;
    L39:
        r313 = 0;
        goto L40
    L28:
        if ((r310 instanceof PhotoViewerWebView) == true) goto L31;
        r311 = false;
        goto L32
    L21:
        r6 = ApplicationLoader.applicationContext;
        goto L22
    L15:
        if (r35.isControllable() == false) goto L17;
        this.photoViewerWebView = r35;
        r35.hideControls();
        goto L18
    L5:
        if (PipUtils.checkPermissions(ApplicationLoader.applicationContext) == 1) goto L8;
        r4 = false;
        goto L10
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
        this.controlsAnimator = r42;
        r42.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.controlsAnimator.addUpdateListener(new PipVideoOverlay$$ExternalSyntheticLambda14(this));
        this.controlsAnimator.addListener(new AnonymousClass1(this));
        this.controlsAnimator.start();
        return;
    L5:
        r2 = 1.0f;
        goto L6
    }

    public static void updatePlayButton() {
        instance.updatePlayButtonInternal();
    }

    private void updatePlayButtonInternal() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.playPauseButton == null) goto L25;
        PhotoViewerWebView r1 = this.photoViewerWebView;
        if (r1 == null) goto L10;
        boolean r02 = r1.isPlaying();
    L14:
        AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
        if (r02 == true) goto L22;
        boolean r03 = this.isVideoCompleted;
        ImageView r2 = this.playPauseButton;
        if (r03 == false) goto L20;
        r2.setImageResource(R.drawable.pip_replay_large);
        return;
    L20:
        r2.setImageResource(R.drawable.pip_play_large);
        return;
    L22:
        this.playPauseButton.setImageResource(R.drawable.pip_pause_large);
        AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
        return;
    L10:
        VideoPlayer r04 = r0.getVideoPlayer();
        if (r04 == null) goto L26;
        r02 = r04.isPlaying();
        goto L14
    L26:
        return;
    }

    public void onLongClick() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVideoPlayer() != null) goto L9;
        if (this.photoViewerWebView != null) goto L9;
        return;
    L9:
        if (this.isDismissing == false) goto L11;
        return;
    L11:
        if (this.isVideoCompleted == false) goto L13;
        return;
    L13:
        if (this.isScrolling == false) goto L15;
        return;
    L15:
        if (this.scaleGestureDetector.isInProgress() == false) goto L17;
        return;
    L17:
        if (this.canLongClick == false) goto L47;
        VideoPlayer r2 = this.photoViewer.getVideoPlayer();
        if (this.longClickStartPoint[0] < ((getSuggestedWidth() * this.scaleFactor) * 0.5f)) goto L22;
        boolean r3 = true;
    L23:
        long r4 = getCurrentPosition();
        long r8 = getDuration();
        if (r4 != (-9223372036854775807L)) goto L26;
        return;
    L26:
        if (r8 < 15000) goto L48;
        PhotoViewerWebView r02 = this.photoViewerWebView;
        PhotoViewer r42 = this.photoViewer;
        if (r02 == null) goto L31;
        r42.getVideoPlayerRewinder().startRewind(this.photoViewerWebView, r3, this.longClickStartPoint[0], this.photoViewer.getCurrentVideoSpeed(), this.seekSpeedDrawable);
    L33:
        if (this.isShowingControls == true) goto L45;
        this.isShowingControls = true;
        toggleControls(true);
        if (this.postedDismissControls == true) goto L46;
        AndroidUtilities.runOnUIThread(this.dismissControlsCallback, 1500);
        this.postedDismissControls = true;
        return;
    L46:
        return;
    L45:
        return;
    L31:
        r42.getVideoPlayerRewinder().startRewind(r2, r3, this.longClickStartPoint[0], this.photoViewer.getCurrentVideoSpeed(), this.seekSpeedDrawable);
        goto L33
    L48:
        return;
    L22:
        r3 = false;
        goto L23
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public View pipCreatePictureInPictureView() {
        TextureView r0 = new TextureView(this.contentView.getContext());
        this.pipTextureView = r0;
        r0.setVisibility(4);
        this.pipTextureView.setOpaque(false);
        this.pipTextureView.setSurfaceTextureListener(new AnonymousClass9(this));
        return this.pipTextureView;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePictureInPictureViewBitmap() {
        TextureView r0 = this.pipTextureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.pipTextureView.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePrimaryWindowViewBitmap() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 == null) goto L11;
        TextureView r02 = r0.changedTextureView;
        if (r02 != null) goto L7;
        return null;
    L7:
        if (r02.isAvailable() == true) goto L10;
        return null;
    L10:
        return this.photoViewer.changedTextureView.getBitmap();
    L11:
        return null;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipHidePrimaryWindowView(Runnable r2) {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L5;
    L7:
        this.windowManager.removeView(this.contentView);
        this.windowViewSkipRender = true;
        this.contentView.invalidate();
        return;
    L5:
        if (r0.getVideoPlayer() == null) goto L7;
        this.photoViewer.pipFirstFrameCallback = r2;
        goto L7
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public boolean pipIsAvailable() {
        PhotoViewer r0 = this.photoViewer;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.pipIsAvailable() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipShowPrimaryWindowView(Runnable r4) {
        PipSource r0 = this.pipSource;
        if (r0 != null) goto L5;
    L7:
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
        this.windowViewSkipRender = false;
        this.contentView.invalidate();
        PhotoViewer r02 = this.photoViewer;
        if (r02 == null) goto L10;
        VideoPlayer r03 = r02.getVideoPlayer();
    L11:
        if (r03 != null) goto L13;
        return;
    L13:
        this.photoViewer.pipFirstFrameCallback = r4;
        return;
    L10:
        r03 = null;
        goto L11
    L5:
        if (r0.params.isValid() == false) goto L7;
        WindowManager.LayoutParams r04 = this.windowLayoutParams;
        int r1 = this.pipSource.params.getWidth();
        this.pipWidth = r1;
        r04.width = r1;
        WindowManager.LayoutParams r05 = this.windowLayoutParams;
        int r12 = this.pipSource.params.getHeight();
        this.pipHeight = r12;
        r05.height = r12;
        goto L7
    }
}
