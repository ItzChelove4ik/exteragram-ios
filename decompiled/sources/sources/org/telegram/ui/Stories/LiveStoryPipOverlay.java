package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import androidx.core.math.MathUtils;
import androidx.core.view.GestureDetectorCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.LivePlayerView;

/* loaded from: classes7.dex */
public class LiveStoryPipOverlay implements NotificationCenter.NotificationCenterDelegate, IPipSourceDelegate {
    private static final FloatPropertyCompat<LiveStoryPipOverlay> PIP_X_PROPERTY = null;
    private static final FloatPropertyCompat<LiveStoryPipOverlay> PIP_Y_PROPERTY = null;

    @SuppressLint({"StaticFieldLeak"})
    private static LiveStoryPipOverlay instance;
    private Float aspectRatio;
    private BackupImageView avatarImageView;
    private View consumingChild;
    private FrameLayout contentFrameLayout;
    private ViewGroup contentView;
    private FrameLayout controlsView;
    private int currentAccount;
    private Runnable dismissControlsCallback;
    private Runnable firstFrameCallback;
    private boolean firstFrameRendered;
    private View flickerView;
    private GestureDetectorCompat gestureDetector;
    private boolean isScrollDisallowed;
    private boolean isScrolling;
    private boolean isShowingControls;
    private boolean isVisible;
    private LivePlayer livePlayer;
    private float maxScaleFactor;
    private float minScaleFactor;
    private int pipHeight;
    private PipSource pipSource;
    private LivePlayerView pipTextureView;
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
    private LivePlayerView textureView;
    private WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private boolean windowViewSkipRender;

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass1(LiveStoryPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            LiveStoryPipOverlay.m19412$$Nest$fputscaleAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ LiveStoryPipOverlay this$0;
        final /* synthetic */ boolean val$destroyPlayer;

        public AnonymousClass2(LiveStoryPipOverlay r1, boolean r2) {
            this.this$0 = r1;
            this.val$destroyPlayer = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            LiveStoryPipOverlay.m19398$$Nest$fgetwindowManager(this.this$0).removeViewImmediate(LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0));
            LiveStoryPipOverlay.m19396$$Nest$fgettextureView(this.this$0).release();
            if (this.val$destroyPlayer == true) goto L5;
        L9:
            LiveStoryPipOverlay.m19405$$Nest$fputlivePlayer(this.this$0, null);
            LiveStoryPipOverlay.m19410$$Nest$fputplaceholderShown(this.this$0, true);
            LiveStoryPipOverlay.m19401$$Nest$fputfirstFrameRendered(this.this$0, false);
            LiveStoryPipOverlay.m19400$$Nest$fputconsumingChild(this.this$0, null);
            LiveStoryPipOverlay.m19403$$Nest$fputisScrolling(this.this$0, false);
            return;
        L5:
            if (LiveStoryPipOverlay.m19382$$Nest$fgetlivePlayer(this.this$0) == null) goto L9;
            if (LiveStoryPipOverlay.m19382$$Nest$fgetlivePlayer(this.this$0) == LivePlayer.recording) goto L9;
            LiveStoryPipOverlay.m19382$$Nest$fgetlivePlayer(this.this$0).destroy();
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$3, reason: invalid class name */
    public class AnonymousClass3 implements ScaleGestureDetector.OnScaleGestureListener {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$3$1, reason: invalid class name */
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
                AnonymousClass3.m19418$$Nest$mupdateLayout(this.this$1);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$Z76vYHtxVavacG4nAgDKO5AGXOQ(AnonymousClass3 r0) {
            r0.lambda$onScale$0();
        }

        /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19418$$Nest$mupdateLayout(AnonymousClass3 r0) {
            r0.updateLayout();
        }

        public AnonymousClass3(LiveStoryPipOverlay r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScale$0() {
            LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).invalidate();
            if (LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).isInLayout() == false) goto L5;
            return;
        L5:
            LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).requestLayout();
            LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0).requestLayout();
            LiveStoryPipOverlay.m19396$$Nest$fgettextureView(this.this$0).requestLayout();
        }

        private void updateLayout() {
            LiveStoryPipOverlay r0 = this.this$0;
            WindowManager.LayoutParams r1 = LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(r0);
            int r2 = (int) (LiveStoryPipOverlay.m19416$$Nest$mgetSuggestedWidth(this.this$0) * LiveStoryPipOverlay.m19394$$Nest$fgetscaleFactor(this.this$0));
            r1.width = r2;
            LiveStoryPipOverlay.m19407$$Nest$fputpipWidth(r0, r2);
            LiveStoryPipOverlay r02 = this.this$0;
            WindowManager.LayoutParams r12 = LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(r02);
            int r22 = (int) (LiveStoryPipOverlay.m19415$$Nest$mgetSuggestedHeight(this.this$0) * LiveStoryPipOverlay.m19394$$Nest$fgetscaleFactor(this.this$0));
            r12.height = r22;
            LiveStoryPipOverlay.m19406$$Nest$fputpipHeight(r02, r22);
            AndroidUtilities.updateViewLayout(LiveStoryPipOverlay.m19398$$Nest$fgetwindowManager(this.this$0), LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0), LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0));
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector r6) {
            LiveStoryPipOverlay r0 = this.this$0;
            LiveStoryPipOverlay.m19413$$Nest$fputscaleFactor(r0, MathUtils.clamp(LiveStoryPipOverlay.m19394$$Nest$fgetscaleFactor(r0) * r6.getScaleFactor(), LiveStoryPipOverlay.m19384$$Nest$fgetminScaleFactor(this.this$0), LiveStoryPipOverlay.m19383$$Nest$fgetmaxScaleFactor(this.this$0)));
            LiveStoryPipOverlay.m19407$$Nest$fputpipWidth(this.this$0, (int) (LiveStoryPipOverlay.m19416$$Nest$mgetSuggestedWidth(r0) * LiveStoryPipOverlay.m19394$$Nest$fgetscaleFactor(this.this$0)));
            LiveStoryPipOverlay.m19406$$Nest$fputpipHeight(this.this$0, (int) (LiveStoryPipOverlay.m19415$$Nest$mgetSuggestedHeight(r0) * LiveStoryPipOverlay.m19394$$Nest$fgetscaleFactor(this.this$0)));
            AndroidUtilities.runOnUIThread(new LiveStoryPipOverlay$3$$ExternalSyntheticLambda0(this));
            SpringForce r02 = LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).setStartValue(LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0)).getSpring();
            float r1 = r6.getFocusX();
            int r2 = AndroidUtilities.displaySize.x;
            if (r1 < (r2 / 2.0f)) goto L5;
            float r12 = (r2 - LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L6:
            r02.setFinalPosition(r12);
            if (LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).isRunning() == true) goto L9;
            LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).start();
        L9:
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).setStartValue(LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(r6.getFocusY() - (LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0) / 2.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            if (LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L14;
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L14:
            return true;
        L5:
            r12 = AndroidUtilities.dp(16.0f);
            goto L6
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector r4) {
            if (LiveStoryPipOverlay.m19380$$Nest$fgetisScrolling(this.this$0) == false) goto L5;
            LiveStoryPipOverlay.m19403$$Nest$fputisScrolling(this.this$0, false);
        L5:
            LiveStoryPipOverlay.m19402$$Nest$fputisScrollDisallowed(this.this$0, true);
            LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0).width = (int) (LiveStoryPipOverlay.m19416$$Nest$mgetSuggestedWidth(this.this$0) * LiveStoryPipOverlay.m19383$$Nest$fgetmaxScaleFactor(this.this$0));
            LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0).height = (int) (LiveStoryPipOverlay.m19415$$Nest$mgetSuggestedHeight(this.this$0) * LiveStoryPipOverlay.m19383$$Nest$fgetmaxScaleFactor(this.this$0));
            AndroidUtilities.updateViewLayout(LiveStoryPipOverlay.m19398$$Nest$fgetwindowManager(this.this$0), LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0), LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0));
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector r4) {
            if (LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).isRunning() == false) goto L5;
        L9:
            ArrayList r42 = new ArrayList();
            AnonymousClass1 r0 = new AnonymousClass1(this, r42);
            boolean r1 = LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).isRunning();
            LiveStoryPipOverlay r2 = this.this$0;
            if (r1 == true) goto L12;
            r42.add(LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(r2));
        L13:
            boolean r12 = LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).isRunning();
            LiveStoryPipOverlay r3 = this.this$0;
            if (r12 == true) goto L17;
            r42.add(LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(r3));
            return;
        L17:
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(r3).addEndListener(r0);
            return;
        L12:
            LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(r2).addEndListener(r0);
            goto L13
        L5:
            if (LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L9;
            updateLayout();
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$4, reason: invalid class name */
    public class AnonymousClass4 extends GestureDetector.SimpleOnGestureListener {
        private float startPipX;
        private float startPipY;
        final /* synthetic */ LiveStoryPipOverlay this$0;
        final /* synthetic */ int val$touchSlop;

        public AnonymousClass4(LiveStoryPipOverlay r1, int r2) {
            this.this$0 = r1;
            this.val$touchSlop = r2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r5) {
            if (LiveStoryPipOverlay.m19381$$Nest$fgetisShowingControls(this.this$0) == false) goto L12;
            int r0 = 1;
        L6:
            if (r0 >= LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).getChildCount()) goto L12;
            View r2 = LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).getChildAt(r0);
            if (r2.dispatchTouchEvent(r5) == true) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            LiveStoryPipOverlay.m19400$$Nest$fputconsumingChild(this.this$0, r2);
            return true;
        L12:
            this.startPipX = LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0);
            this.startPipY = LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            if (LiveStoryPipOverlay.m19380$$Nest$fgetisScrolling(this.this$0) == true) goto L5;
            return false;
        L5:
            if (LiveStoryPipOverlay.m19379$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            SpringForce r42 = LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).setStartVelocity(r6).setStartValue(LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0)).getSpring();
            float r52 = (LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0) + (LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0) / 2.0f)) + (r6 / 7.0f);
            int r0 = AndroidUtilities.displaySize.x;
            if (r52 < (r0 / 2.0f)) goto L9;
            float r53 = (r0 - LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L10:
            r42.setFinalPosition(r53);
            LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).start();
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).setStartVelocity(r6).setStartValue(LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0) + (r7 / 10.0f), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).start();
            return true;
        L9:
            r53 = AndroidUtilities.dp(16.0f);
            goto L10
        L14:
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            if (LiveStoryPipOverlay.m19380$$Nest$fgetisScrolling(this.this$0) == true) goto L14;
            if (LiveStoryPipOverlay.m19393$$Nest$fgetscaleAnimator(this.this$0) != null) goto L14;
            if (LiveStoryPipOverlay.m19379$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L14;
            if (Math.abs(r6) < this.val$touchSlop) goto L11;
        L12:
            LiveStoryPipOverlay.m19403$$Nest$fputisScrolling(this.this$0, true);
            LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).cancel();
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).cancel();
            goto L14
        L11:
            if (Math.abs(r7) >= this.val$touchSlop) goto L12;
        L14:
            if (LiveStoryPipOverlay.m19380$$Nest$fgetisScrolling(this.this$0) == false) goto L16;
            WindowManager.LayoutParams r62 = LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0);
            LiveStoryPipOverlay r72 = this.this$0;
            float r0 = (this.startPipX + r5.getRawX()) - r4.getRawX();
            LiveStoryPipOverlay.m19408$$Nest$fputpipX(r72, r0);
            r62.x = (int) r0;
            WindowManager.LayoutParams r63 = LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0);
            LiveStoryPipOverlay r73 = this.this$0;
            float r02 = (this.startPipY + r5.getRawY()) - r4.getRawY();
            LiveStoryPipOverlay.m19409$$Nest$fputpipY(r73, r02);
            r63.y = (int) r02;
            AndroidUtilities.updateViewLayout(LiveStoryPipOverlay.m19398$$Nest$fgetwindowManager(this.this$0), LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0), LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0));
        L16:
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r4) {
            if (LiveStoryPipOverlay.m19393$$Nest$fgetscaleAnimator(this.this$0) == null) goto L6;
            return true;
        L6:
            if (LiveStoryPipOverlay.m19392$$Nest$fgetpostedDismissControls(this.this$0) == false) goto L8;
            AndroidUtilities.cancelRunOnUIThread(LiveStoryPipOverlay.m19377$$Nest$fgetdismissControlsCallback(this.this$0));
            LiveStoryPipOverlay.m19411$$Nest$fputpostedDismissControls(this.this$0, false);
        L8:
            LiveStoryPipOverlay.m19404$$Nest$fputisShowingControls(this.this$0, !LiveStoryPipOverlay.m19381$$Nest$fgetisShowingControls(r4));
            LiveStoryPipOverlay r42 = this.this$0;
            LiveStoryPipOverlay.m19417$$Nest$mtoggleControls(r42, LiveStoryPipOverlay.m19381$$Nest$fgetisShowingControls(r42));
            if (LiveStoryPipOverlay.m19381$$Nest$fgetisShowingControls(this.this$0) == true) goto L11;
        L13:
            return true;
        L11:
            if (LiveStoryPipOverlay.m19392$$Nest$fgetpostedDismissControls(this.this$0) == true) goto L13;
            AndroidUtilities.runOnUIThread(LiveStoryPipOverlay.m19377$$Nest$fgetdismissControlsCallback(this.this$0), 2500);
            LiveStoryPipOverlay.m19411$$Nest$fputpostedDismissControls(this.this$0, true);
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        private Path path;
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass5(LiveStoryPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r10) {
            int r0 = r10.getAction();
            if (LiveStoryPipOverlay.m19374$$Nest$fgetconsumingChild(this.this$0) == null) goto L10;
            MotionEvent r1 = MotionEvent.obtain(r10);
            r1.offsetLocation(LiveStoryPipOverlay.m19374$$Nest$fgetconsumingChild(this.this$0).getX(), LiveStoryPipOverlay.m19374$$Nest$fgetconsumingChild(this.this$0).getY());
            boolean r4 = LiveStoryPipOverlay.m19374$$Nest$fgetconsumingChild(this.this$0).dispatchTouchEvent(r10);
            r1.recycle();
            if (r0 == 1) goto L7;
            if (r0 == 3) goto L7;
        L8:
            if (r4 == false) goto L10;
            return true;
        L7:
            LiveStoryPipOverlay.m19400$$Nest$fputconsumingChild(this.this$0, null);
        L10:
            MotionEvent r12 = MotionEvent.obtain(r10);
            r12.offsetLocation(r10.getRawX() - r10.getX(), r10.getRawY() - r10.getY());
            boolean r42 = LiveStoryPipOverlay.m19395$$Nest$fgetscaleGestureDetector(this.this$0).onTouchEvent(r12);
            r12.recycle();
            if (LiveStoryPipOverlay.m19395$$Nest$fgetscaleGestureDetector(this.this$0).isInProgress() == false) goto L13;
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
            LiveStoryPipOverlay.m19403$$Nest$fputisScrolling(this.this$0, false);
            LiveStoryPipOverlay.m19402$$Nest$fputisScrollDisallowed(this.this$0, false);
            if (LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).isRunning() == true) goto L26;
            SpringForce r02 = LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).setStartValue(LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0)).getSpring();
            float r2 = LiveStoryPipOverlay.m19388$$Nest$fgetpipX(this.this$0) + (LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0) / 2.0f);
            int r6 = AndroidUtilities.displaySize.x;
            if (r2 < (r6 / 2.0f)) goto L23;
            float r22 = (r6 - LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0)) - AndroidUtilities.dp(16.0f);
        L24:
            r02.setFinalPosition(r22);
            LiveStoryPipOverlay.m19389$$Nest$fgetpipXSpring(this.this$0).start();
            goto L26
        L23:
            r22 = AndroidUtilities.dp(16.0f);
        L26:
            if (LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).isRunning() == true) goto L28;
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).setStartValue(LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0)).getSpring().setFinalPosition(MathUtils.clamp(LiveStoryPipOverlay.m19390$$Nest$fgetpipY(this.this$0), AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0)) - AndroidUtilities.dp(16.0f)));
            LiveStoryPipOverlay.m19391$$Nest$fgetpipYSpring(this.this$0).start();
            goto L28
        L13:
            if (LiveStoryPipOverlay.m19378$$Nest$fgetgestureDetector(this.this$0).onTouchEvent(r10) == false) goto L15;
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
            AndroidUtilities.setPreferredMaxRefreshRate(LiveStoryPipOverlay.m19398$$Nest$fgetwindowManager(this.this$0), LiveStoryPipOverlay.m19376$$Nest$fgetcontentView(this.this$0), LiveStoryPipOverlay.m19397$$Nest$fgetwindowLayoutParams(this.this$0));
            LiveStoryPipOverlay.m19414$$Nest$mbindTextureView(this.this$0);
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

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$6, reason: invalid class name */
    public class AnonymousClass6 extends ViewGroup {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass6(LiveStoryPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            if (LiveStoryPipOverlay.m19399$$Nest$fgetwindowViewSkipRender(this.this$0) == false) goto L5;
            return;
        L5:
            super.draw(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            if (LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).getParent() != this) goto L6;
            LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).layout(0, 0, LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0), LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0));
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            setMeasuredDimension(View.MeasureSpec.getSize(r2), View.MeasureSpec.getSize(r3));
            if (LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).getParent() != this) goto L6;
            LiveStoryPipOverlay.m19375$$Nest$fgetcontentFrameLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(LiveStoryPipOverlay.m19387$$Nest$fgetpipWidth(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(LiveStoryPipOverlay.m19385$$Nest$fgetpipHeight(this.this$0), TLObject.FLAG_30));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$7, reason: invalid class name */
    public class AnonymousClass7 extends ViewOutlineProvider {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass7(LiveStoryPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dp(10.0f));
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$8, reason: invalid class name */
    public class AnonymousClass8 extends View {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass8(LiveStoryPipOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            if (getAlpha() != 0.0f) goto L5;
            return;
        L5:
            AndroidUtilities.rectTmp.set(0.0f, 0.0f, getWidth(), getHeight());
            invalidate();
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveStoryPipOverlay$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ LiveStoryPipOverlay this$0;

        public AnonymousClass9(LiveStoryPipOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1, boolean r2) {
            if (LiveStoryPipOverlay.m19386$$Nest$fgetpipSource(this.this$0) == null) goto L6;
            LiveStoryPipOverlay.m19386$$Nest$fgetpipSource(this.this$0).invalidatePosition();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$BeLbMy9PT9RMFco8Tcgj4xFCxUo(LiveStoryPipOverlay r1, float r2) {
        WindowManager.LayoutParams r0 = r1.windowLayoutParams;
        r1.pipX = r2;
        r0.x = (int) r2;
        AndroidUtilities.updateViewLayout(r1.windowManager, r1.contentView, r0);
    }

    /* renamed from: $r8$lambda$Eu-x2q0G9H0gsXKjw85Zo2pTD20, reason: not valid java name */
    public static /* synthetic */ float m19372$r8$lambda$Eux2q0G9H0gsXKjw85Zo2pTD20(LiveStoryPipOverlay r0) {
        return r0.pipX;
    }

    public static /* synthetic */ float $r8$lambda$KqMKLICZbdzncndGxhkD5LVete4(LiveStoryPipOverlay r0) {
        return r0.pipY;
    }

    public static /* synthetic */ void $r8$lambda$PBVFdkmNe5S17gwEYRqgBe9i65k(LivePlayer r4, Context r5, View r6) {
        if (r4 == null) goto L20;
        int r62 = r4.currentAccount;
        if (r62 == UserConfig.selectedAccount) goto L10;
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L21;
        r0.switchToAccount(r62, true);
        goto L10
    L21:
        return;
    L10:
        BaseFragment r63 = LaunchActivity.getSafeLastFragment();
        if (r63 == null) goto L22;
        TL_stories.StoryItem r02 = MessagesController.getInstance(r4.currentAccount).getStoriesController().findStory(r4.dialogId, r4.storyId);
        if (r02 != null) goto L16;
        r02 = r4.storyItem;
    L16:
        if (r02 != null) goto L18;
        return;
    L18:
        r63.getOrCreateStoryViewer().open(r4.currentAccount, r5, r02, null);
        AndroidUtilities.runOnUIThread(new LiveStoryPipOverlay$$ExternalSyntheticLambda9(), 200);
        return;
    L22:
        return;
    }

    public static /* synthetic */ void $r8$lambda$PZhxBqvKYwtuAVSchaLBc6aUhE8() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
    }

    /* renamed from: $r8$lambda$RtxvIMOP4pyBZ-hC7EFpjUQ_GaU, reason: not valid java name */
    public static /* synthetic */ void m19373$r8$lambda$RtxvIMOP4pyBZhC7EFpjUQ_GaU(LiveStoryPipOverlay r1, float r2) {
        WindowManager.LayoutParams r0 = r1.windowLayoutParams;
        r1.pipY = r2;
        r0.y = (int) r2;
        AndroidUtilities.updateViewLayout(r1.windowManager, r1.contentView, r0);
    }

    public static /* synthetic */ void $r8$lambda$aMqt7dAj4F5TxCBJvYJGPtHImOU(LiveStoryPipOverlay r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$vnlmWRxwUd6wG3PMh_4UyUXp3wE(View r0) {
        dismiss();
    }

    public static /* synthetic */ void $r8$lambda$zcgVX0OGwtCf0EFynzF8ZiuEi1k(LiveStoryPipOverlay r0, ValueAnimator r1) {
        r0.lambda$toggleControls$5(r1);
    }

    /* renamed from: -$$Nest$fgetconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m19374$$Nest$fgetconsumingChild(LiveStoryPipOverlay r0) {
        return r0.consumingChild;
    }

    /* renamed from: -$$Nest$fgetcontentFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19375$$Nest$fgetcontentFrameLayout(LiveStoryPipOverlay r0) {
        return r0.contentFrameLayout;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m19376$$Nest$fgetcontentView(LiveStoryPipOverlay r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetdismissControlsCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19377$$Nest$fgetdismissControlsCallback(LiveStoryPipOverlay r0) {
        return r0.dismissControlsCallback;
    }

    /* renamed from: -$$Nest$fgetgestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GestureDetectorCompat m19378$$Nest$fgetgestureDetector(LiveStoryPipOverlay r0) {
        return r0.gestureDetector;
    }

    /* renamed from: -$$Nest$fgetisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19379$$Nest$fgetisScrollDisallowed(LiveStoryPipOverlay r0) {
        return r0.isScrollDisallowed;
    }

    /* renamed from: -$$Nest$fgetisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19380$$Nest$fgetisScrolling(LiveStoryPipOverlay r0) {
        return r0.isScrolling;
    }

    /* renamed from: -$$Nest$fgetisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19381$$Nest$fgetisShowingControls(LiveStoryPipOverlay r0) {
        return r0.isShowingControls;
    }

    /* renamed from: -$$Nest$fgetlivePlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LivePlayer m19382$$Nest$fgetlivePlayer(LiveStoryPipOverlay r0) {
        return r0.livePlayer;
    }

    /* renamed from: -$$Nest$fgetmaxScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19383$$Nest$fgetmaxScaleFactor(LiveStoryPipOverlay r0) {
        return r0.maxScaleFactor;
    }

    /* renamed from: -$$Nest$fgetminScaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19384$$Nest$fgetminScaleFactor(LiveStoryPipOverlay r0) {
        return r0.minScaleFactor;
    }

    /* renamed from: -$$Nest$fgetpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19385$$Nest$fgetpipHeight(LiveStoryPipOverlay r0) {
        return r0.pipHeight;
    }

    /* renamed from: -$$Nest$fgetpipSource, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PipSource m19386$$Nest$fgetpipSource(LiveStoryPipOverlay r0) {
        return r0.pipSource;
    }

    /* renamed from: -$$Nest$fgetpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19387$$Nest$fgetpipWidth(LiveStoryPipOverlay r0) {
        return r0.pipWidth;
    }

    /* renamed from: -$$Nest$fgetpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19388$$Nest$fgetpipX(LiveStoryPipOverlay r0) {
        return r0.pipX;
    }

    /* renamed from: -$$Nest$fgetpipXSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m19389$$Nest$fgetpipXSpring(LiveStoryPipOverlay r0) {
        return r0.pipXSpring;
    }

    /* renamed from: -$$Nest$fgetpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19390$$Nest$fgetpipY(LiveStoryPipOverlay r0) {
        return r0.pipY;
    }

    /* renamed from: -$$Nest$fgetpipYSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m19391$$Nest$fgetpipYSpring(LiveStoryPipOverlay r0) {
        return r0.pipYSpring;
    }

    /* renamed from: -$$Nest$fgetpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19392$$Nest$fgetpostedDismissControls(LiveStoryPipOverlay r0) {
        return r0.postedDismissControls;
    }

    /* renamed from: -$$Nest$fgetscaleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m19393$$Nest$fgetscaleAnimator(LiveStoryPipOverlay r0) {
        return r0.scaleAnimator;
    }

    /* renamed from: -$$Nest$fgetscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19394$$Nest$fgetscaleFactor(LiveStoryPipOverlay r0) {
        return r0.scaleFactor;
    }

    /* renamed from: -$$Nest$fgetscaleGestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScaleGestureDetector m19395$$Nest$fgetscaleGestureDetector(LiveStoryPipOverlay r0) {
        return r0.scaleGestureDetector;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LivePlayerView m19396$$Nest$fgettextureView(LiveStoryPipOverlay r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetwindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m19397$$Nest$fgetwindowLayoutParams(LiveStoryPipOverlay r0) {
        return r0.windowLayoutParams;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m19398$$Nest$fgetwindowManager(LiveStoryPipOverlay r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fgetwindowViewSkipRender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19399$$Nest$fgetwindowViewSkipRender(LiveStoryPipOverlay r0) {
        return r0.windowViewSkipRender;
    }

    /* renamed from: -$$Nest$fputconsumingChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19400$$Nest$fputconsumingChild(LiveStoryPipOverlay r0, View r1) {
        r0.consumingChild = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameRendered, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19401$$Nest$fputfirstFrameRendered(LiveStoryPipOverlay r0, boolean r1) {
        r0.firstFrameRendered = r1;
    }

    /* renamed from: -$$Nest$fputisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19402$$Nest$fputisScrollDisallowed(LiveStoryPipOverlay r0, boolean r1) {
        r0.isScrollDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19403$$Nest$fputisScrolling(LiveStoryPipOverlay r0, boolean r1) {
        r0.isScrolling = r1;
    }

    /* renamed from: -$$Nest$fputisShowingControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19404$$Nest$fputisShowingControls(LiveStoryPipOverlay r0, boolean r1) {
        r0.isShowingControls = r1;
    }

    /* renamed from: -$$Nest$fputlivePlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19405$$Nest$fputlivePlayer(LiveStoryPipOverlay r0, LivePlayer r1) {
        r0.livePlayer = r1;
    }

    /* renamed from: -$$Nest$fputpipHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19406$$Nest$fputpipHeight(LiveStoryPipOverlay r0, int r1) {
        r0.pipHeight = r1;
    }

    /* renamed from: -$$Nest$fputpipWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19407$$Nest$fputpipWidth(LiveStoryPipOverlay r0, int r1) {
        r0.pipWidth = r1;
    }

    /* renamed from: -$$Nest$fputpipX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19408$$Nest$fputpipX(LiveStoryPipOverlay r0, float r1) {
        r0.pipX = r1;
    }

    /* renamed from: -$$Nest$fputpipY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19409$$Nest$fputpipY(LiveStoryPipOverlay r0, float r1) {
        r0.pipY = r1;
    }

    /* renamed from: -$$Nest$fputplaceholderShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19410$$Nest$fputplaceholderShown(LiveStoryPipOverlay r0, boolean r1) {
        r0.placeholderShown = r1;
    }

    /* renamed from: -$$Nest$fputpostedDismissControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19411$$Nest$fputpostedDismissControls(LiveStoryPipOverlay r0, boolean r1) {
        r0.postedDismissControls = r1;
    }

    /* renamed from: -$$Nest$fputscaleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19412$$Nest$fputscaleAnimator(LiveStoryPipOverlay r0, ValueAnimator r1) {
        r0.scaleAnimator = r1;
    }

    /* renamed from: -$$Nest$fputscaleFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19413$$Nest$fputscaleFactor(LiveStoryPipOverlay r0, float r1) {
        r0.scaleFactor = r1;
    }

    /* renamed from: -$$Nest$mbindTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19414$$Nest$mbindTextureView(LiveStoryPipOverlay r0) {
        r0.bindTextureView();
    }

    /* renamed from: -$$Nest$mgetSuggestedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19415$$Nest$mgetSuggestedHeight(LiveStoryPipOverlay r0) {
        return r0.getSuggestedHeight();
    }

    /* renamed from: -$$Nest$mgetSuggestedWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19416$$Nest$mgetSuggestedWidth(LiveStoryPipOverlay r0) {
        return r0.getSuggestedWidth();
    }

    /* renamed from: -$$Nest$mtoggleControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19417$$Nest$mtoggleControls(LiveStoryPipOverlay r0, boolean r1) {
        r0.toggleControls(r1);
    }

    static {
        PIP_X_PROPERTY = new SimpleFloatPropertyCompat("pipX", new LiveStoryPipOverlay$$ExternalSyntheticLambda0(), new LiveStoryPipOverlay$$ExternalSyntheticLambda1());
        PIP_Y_PROPERTY = new SimpleFloatPropertyCompat("pipY", new LiveStoryPipOverlay$$ExternalSyntheticLambda2(), new LiveStoryPipOverlay$$ExternalSyntheticLambda3());
        instance = new LiveStoryPipOverlay();
    }

    public LiveStoryPipOverlay() {
        this.minScaleFactor = 0.6f;
        this.maxScaleFactor = 1.4f;
        this.placeholderShown = true;
        this.scaleFactor = 1.0f;
        this.dismissControlsCallback = new LiveStoryPipOverlay$$ExternalSyntheticLambda5(this);
    }

    private void bindTextureView() {
        bindTextureView(false);
    }

    private void bindTextureView(boolean r7) {
        LivePlayer r72 = this.livePlayer;
        if (r72 == null) goto L9;
        r72.setVolume(1.0f);
        LivePlayerView r73 = this.pipTextureView;
        LivePlayer r1 = this.livePlayer;
        if (r73 == null) goto L7;
        r1.setDisplaySink(r73.getSink());
        goto L9
    L7:
        r1.setDisplaySink(this.textureView.getSink());
    L9:
        if (this.placeholderShown == false) goto L12;
        this.flickerView.animate().cancel();
        ViewPropertyAnimator r74 = this.flickerView.animate().alpha(0.0f).setDuration(150);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.DEFAULT;
        r74.setInterpolator(r4).start();
        this.avatarImageView.animate().cancel();
        this.avatarImageView.animate().alpha(0.0f).setDuration(150).setInterpolator(r4).start();
        this.textureView.animate().cancel();
        this.textureView.animate().alpha(1.0f).setDuration(150).setInterpolator(r4).start();
        this.placeholderShown = false;
    L12:
        if (this.pipWidth == (getSuggestedWidth() * this.scaleFactor)) goto L14;
    L17:
        WindowManager.LayoutParams r75 = this.windowLayoutParams;
        int r0 = (int) (getSuggestedWidth() * this.scaleFactor);
        this.pipWidth = r0;
        r75.width = r0;
        WindowManager.LayoutParams r76 = this.windowLayoutParams;
        int r02 = (int) (getSuggestedHeight() * this.scaleFactor);
        this.pipHeight = r02;
        r76.height = r02;
        AndroidUtilities.updateViewLayout(this.windowManager, this.contentView, this.windowLayoutParams);
        SpringForce r77 = this.pipXSpring.setStartValue(this.pipX).getSpring();
        float r03 = this.pipX + ((getSuggestedWidth() * this.scaleFactor) / 2.0f);
        int r12 = AndroidUtilities.displaySize.x;
        if (r03 < (r12 / 2.0f)) goto L20;
        float r04 = (r12 - (getSuggestedWidth() * this.scaleFactor)) - AndroidUtilities.dp(16.0f);
    L21:
        r77.setFinalPosition(r04);
        this.pipXSpring.start();
        this.pipYSpring.setStartValue(this.pipY).getSpring().setFinalPosition(MathUtils.clamp(this.pipY, AndroidUtilities.dp(16.0f), (AndroidUtilities.displaySize.y - (getSuggestedHeight() * this.scaleFactor)) - AndroidUtilities.dp(16.0f)));
        this.pipYSpring.start();
        return;
    L20:
        r04 = AndroidUtilities.dp(16.0f);
        goto L21
    L14:
        if (this.pipHeight != (getSuggestedHeight() * this.scaleFactor)) goto L17;
    }

    public static void dismiss() {
        instance.dismissInternal(true);
    }

    public static void dismiss(boolean r1) {
        instance.dismissInternal(r1);
    }

    private void dismissInternal(boolean r10) {
        if (this.isVisible == false) goto L16;
        this.isVisible = false;
        AndroidUtilities.runOnUIThread(new LiveStoryPipOverlay$$ExternalSyntheticLambda4(), 100);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.liveStoryUpdated);
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
        r12.addListener(new AnonymousClass2(this, r10));
        r12.start();
        PipSource r102 = this.pipSource;
        if (r102 == null) goto L15;
        r102.destroy();
        this.pipSource = null;
        return;
    L15:
        return;
    }

    public static LivePlayer getLivePlayer() {
        return instance.livePlayer;
    }

    private float getRatio() {
        if (this.aspectRatio != null) goto L6;
        this.aspectRatio = Float.valueOf(1.7777778f);
        Point r0 = AndroidUtilities.displaySize;
        this.maxScaleFactor = (Math.min(r0.x, r0.y) - AndroidUtilities.dp(32.0f)) / getSuggestedWidth();
    L6:
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

    public static boolean isVisible(LivePlayer r2) {
        LiveStoryPipOverlay r0 = instance;
        if (r0.isVisible == true) goto L5;
        return false;
    L5:
        if (r0.livePlayer != r2) goto L10;
        return true;
    L10:
        return false;
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

    public static void show(Activity r1, LivePlayer r2) {
        instance.showInternal(r1, r2);
    }

    private void showInternal(Activity r31, LivePlayer r32) {
        if (r32 != null) goto L5;
        return;
    L5:
        if (this.isVisible == true) goto L22;
        this.isVisible = true;
        this.livePlayer = r32;
        int r4 = r32.currentAccount;
        this.currentAccount = r4;
        NotificationCenter.getInstance(r4).addObserver(this, NotificationCenter.liveStoryUpdated);
        this.pipWidth = getSuggestedWidth();
        this.pipHeight = getSuggestedHeight();
        this.scaleFactor = 1.0f;
        this.isShowingControls = false;
        this.pipXSpring = new SpringAnimation(this, PIP_X_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f));
        this.pipYSpring = new SpringAnimation(this, PIP_Y_PROPERTY).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(650.0f));
        if (r31 == null) goto L10;
        Context r6 = r31;
    L11:
        int r7 = ViewConfiguration.get(r6).getScaledTouchSlop();
        ScaleGestureDetector r8 = new ScaleGestureDetector(r6, new AnonymousClass3(this));
        this.scaleGestureDetector = r8;
        r8.setQuickScaleEnabled(false);
        this.scaleGestureDetector.setStylusScaleEnabled(false);
        this.gestureDetector = new GestureDetectorCompat(r6, new AnonymousClass4(this, r7));
        this.contentFrameLayout = new AnonymousClass5(this, r6);
        AnonymousClass6 r72 = new AnonymousClass6(this, r6);
        this.contentView = r72;
        r72.addView(this.contentFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
        this.contentFrameLayout.setOutlineProvider(new AnonymousClass7(this));
        this.contentFrameLayout.setClipToOutline(true);
        this.contentFrameLayout.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        BackupImageView r73 = new BackupImageView(r6);
        this.avatarImageView = r73;
        this.contentFrameLayout.addView(r73, LayoutHelper.createFrame(-1, -1.0f));
        LivePlayerView r74 = new LivePlayerView(r6, this.currentAccount, false);
        this.textureView = r74;
        r74.setAlpha(0.0f);
        this.contentFrameLayout.addView(this.textureView, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass8 r75 = new AnonymousClass8(this, r6);
        this.flickerView = r75;
        this.contentFrameLayout.addView(r75, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r76 = new FrameLayout(r6);
        this.controlsView = r76;
        r76.setAlpha(0.0f);
        View r77 = new View(r6);
        GradientDrawable r11 = new GradientDrawable();
        r11.setColors(new int[]{1140850688, 0});
        r11.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        r77.setBackground(r11);
        this.controlsView.addView(r77, LayoutHelper.createFrame(-1, -1.0f));
        int r78 = AndroidUtilities.dp(8.0f);
        ImageView r112 = new ImageView(r6);
        r112.setImageResource(R.drawable.pip_video_close);
        int r12 = Theme.key_voipgroup_actionBarItems;
        r112.setColorFilter(Theme.getColor(r12));
        int r13 = Theme.key_listSelector;
        r112.setBackground(Theme.createSelectorDrawable(Theme.getColor(r13)));
        r112.setPadding(r78, r78, r78, r78);
        r112.setOnClickListener(new LiveStoryPipOverlay$$ExternalSyntheticLambda7());
        this.controlsView.addView(r112, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 4.0f, 0.0f));
        ImageView r42 = new ImageView(r6);
        r42.setImageResource(R.drawable.pip_video_expand);
        r42.setColorFilter(Theme.getColor(r12));
        r42.setBackground(Theme.createSelectorDrawable(Theme.getColor(r13)));
        r42.setPadding(r78, r78, r78, r78);
        r42.setOnClickListener(new LiveStoryPipOverlay$$ExternalSyntheticLambda8(r32, r6));
        this.controlsView.addView(r42, LayoutHelper.createFrame(38, 38.0f, 5, 0.0f, 4.0f, 48.0f, 0.0f));
        this.contentFrameLayout.addView(this.controlsView, LayoutHelper.createFrame(-1, -1.0f));
        this.windowManager = (WindowManager) r6.getSystemService("window");
        WindowManager.LayoutParams r2 = PipUtils.createWindowLayoutParams(r6, false);
        this.windowLayoutParams = r2;
        int r43 = this.pipWidth;
        r2.width = r43;
        r2.height = this.pipHeight;
        float r62 = (AndroidUtilities.displaySize.x - r43) - AndroidUtilities.dp(16.0f);
        this.pipX = r62;
        r2.x = (int) r62;
        WindowManager.LayoutParams r22 = this.windowLayoutParams;
        float r44 = (AndroidUtilities.displaySize.y - this.pipHeight) - AndroidUtilities.dp(16.0f);
        this.pipY = r44;
        r22.y = (int) r44;
        WindowManager.LayoutParams r23 = this.windowLayoutParams;
        r23.dimAmount = 0.0f;
        r23.flags = 520;
        this.contentView.setAlpha(0.0f);
        this.contentView.setScaleX(0.1f);
        this.contentView.setScaleY(0.1f);
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.contentView, this.windowLayoutParams);
        this.windowManager.addView(this.contentView, this.windowLayoutParams);
        AnimatorSet r24 = new AnimatorSet();
        r24.setDuration(250);
        r24.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r24.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.contentView, View.ALPHA, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.contentView, View.SCALE_Y, new float[]{1.0f})});
        r24.addListener(new AnonymousClass9(this));
        r24.start();
        bindTextureView();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
        PipSource r25 = this.pipSource;
        if (r25 == null) goto L14;
        r25.destroy();
        this.pipSource = null;
    L14:
        if (r31 != null) goto L16;
        return;
    L16:
        if (PipUtils.checkPermissions(r31) != 1) goto L21;
        this.pipSource = new PipSource.Builder(r31, this).setTagPrefix("pip-live-story").setPriority(1).setCornerRadius(AndroidUtilities.dp(10.0f)).setContentView(this.contentView).setPlaceholderView(this.textureView.getPlaceholderView()).build();
        return;
    L21:
        return;
    L10:
        r6 = ApplicationLoader.applicationContext;
        goto L11
    }

    public static LivePlayer takeLivePlayer() {
        LiveStoryPipOverlay r0 = instance;
        LivePlayer r1 = r0.livePlayer;
        r0.livePlayer = null;
        return r1;
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
        this.scaleAnimator.addUpdateListener(new LiveStoryPipOverlay$$ExternalSyntheticLambda6(this));
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
        LivePlayerView r0 = new LivePlayerView(this.textureView.getContext(), this.currentAccount, false);
        this.pipTextureView = r0;
        return r0;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePictureInPictureViewBitmap() {
        LivePlayerView r0 = this.pipTextureView;
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
        LivePlayerView r0 = this.textureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.textureView.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipHidePrimaryWindowView(Runnable r2) {
        this.firstFrameCallback = r2;
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
        LivePlayerView r33 = this.pipTextureView;
        if (r33 == null) goto L10;
        r33.release();
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
