package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.os.SystemClock;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.CastStatusCodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.voip.RTMPStreamPipOverlay;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes3.dex */
public class GroupCallPip implements NotificationCenter.NotificationCenterDelegate {
    private static boolean forceRemoved = true;
    private static GroupCallPip instance;
    FrameLayout alertContainer;
    boolean animateToPinnedToCenter;
    boolean animateToPrepareRemove;
    boolean animateToShowRemoveTooltip;
    AvatarsImageView avatarsImageView;
    private final GroupCallPipButton button;
    boolean buttonInAlpha;
    int currentAccount;
    RLottieDrawable deleteIcon;
    private final RLottieImageView iconView;
    int lastScreenX;
    int lastScreenY;
    int[] location;
    boolean moving;
    ValueAnimator pinAnimator;
    float pinnedProgress;
    GroupCallPipAlertView pipAlertView;
    float[] point;
    float prepareToRemoveProgress;
    boolean pressedState;
    View removeTooltipView;
    boolean removed;
    boolean showAlert;
    AnimatorSet showRemoveAnimator;
    private ValueAnimator.AnimatorUpdateListener updateXlistener;
    private ValueAnimator.AnimatorUpdateListener updateYlistener;
    WindowManager.LayoutParams windowLayoutParams;
    int windowLeft;
    WindowManager windowManager;
    float windowOffsetLeft;
    float windowOffsetTop;
    FrameLayout windowRemoveTooltipOverlayView;
    FrameLayout windowRemoveTooltipView;
    int windowTop;
    FrameLayout windowView;
    float windowX;
    float windowY;
    float xRelative;
    float yRelative;

    /* renamed from: org.telegram.ui.Components.GroupCallPip$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass1(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            GroupCallPip r0 = this.this$0;
            int r33 = (int) r32;
            r0.windowLayoutParams.x = r33;
            GroupCallPip.m10847$$Nest$mupdateAvatarsPosition(r0);
            if (this.this$0.windowView.getParent() == null) goto L6;
            GroupCallPip r2 = this.this$0;
            r2.windowManager.updateViewLayout(r2.windowView, r2.windowLayoutParams);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ View val$alert;
        final /* synthetic */ WindowManager val$windowManager;
        final /* synthetic */ View val$windowRemoveTooltipOverlayView;
        final /* synthetic */ View val$windowRemoveTooltipView;
        final /* synthetic */ View val$windowView;

        public AnonymousClass10(View r1, View r2, View r3, WindowManager r4, View r5) {
            this.val$windowView = r1;
            this.val$windowRemoveTooltipView = r2;
            this.val$windowRemoveTooltipOverlayView = r3;
            this.val$windowManager = r4;
            this.val$alert = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$windowView.getParent() == null) goto L6;
            this.val$windowView.setVisibility(8);
            this.val$windowRemoveTooltipView.setVisibility(8);
            this.val$windowRemoveTooltipOverlayView.setVisibility(8);
            this.val$windowManager.removeView(this.val$windowView);
            this.val$windowManager.removeView(this.val$windowRemoveTooltipView);
            this.val$windowManager.removeView(this.val$windowRemoveTooltipOverlayView);
            this.val$windowManager.removeView(this.val$alert);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass11(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.windowRemoveTooltipView.setVisibility(8);
            GroupCallPip r1 = this.this$0;
            r1.animateToPrepareRemove = false;
            r1.prepareToRemoveProgress = 0.0f;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallPip this$0;
        final /* synthetic */ boolean val$pinned;

        public AnonymousClass12(GroupCallPip r1, boolean r2) {
            this.this$0 = r1;
            this.val$pinned = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            GroupCallPip r42 = this.this$0;
            if (r42.removed == false) goto L6;
            return;
        L6:
            if (this.val$pinned == false) goto L8;
            float r0 = 1.0f;
        L9:
            r42.pinnedProgress = r0;
            GroupCallPip.m10839$$Nest$fgetbutton(r42).setPinnedProgress(this.this$0.pinnedProgress);
            GroupCallPip r43 = this.this$0;
            r43.windowView.setScaleX(1.0f - (r43.pinnedProgress * 0.6f));
            GroupCallPip r44 = this.this$0;
            r44.windowView.setScaleY(1.0f - (r44.pinnedProgress * 0.6f));
            GroupCallPip r3 = this.this$0;
            if (r3.moving == false) goto L13;
            GroupCallPip.m10848$$Nest$mupdateButtonPosition(r3);
            return;
        L13:
            return;
        L8:
            r0 = 0.0f;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass13(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.avatarsImageView.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass2(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            GroupCallPip r0 = this.this$0;
            int r33 = (int) r32;
            r0.windowLayoutParams.y = r33;
            if (r0.windowView.getParent() == null) goto L6;
            GroupCallPip r2 = this.this$0;
            r2.windowManager.updateViewLayout(r2.windowView, r2.windowLayoutParams);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends FrameLayout {
        Runnable micRunnable;
        AnimatorSet moveToBoundsAnimator;
        boolean pressed;
        Runnable pressedRunnable;
        long startTime;
        float startX;
        float startY;
        final /* synthetic */ GroupCallPip this$0;
        final /* synthetic */ float val$touchSlop;

        /* renamed from: org.telegram.ui.Components.GroupCallPip$3$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                VoIPService r0 = VoIPService.getSharedInstance();
                if (r0 != null) goto L5;
                return;
            L5:
                if (r0.isMicMute() == false) goto L23;
                TLRPC.GroupCallParticipant r1 = r0.groupCall.participants.get(r0.getSelfId());
                if (r1 != null) goto L9;
            L15:
                AndroidUtilities.runOnUIThread(this.this$1.micRunnable, 90);
                this.this$1.performHapticFeedback(3, 2);     // Catch: Exception -> L19
            L17:
                this.this$1.pressed = true;
                return;
            L9:
                if (r1.can_self_unmute == true) goto L15;
                if (r1.muted == false) goto L15;
                if (ChatObject.canManageCalls(r0.getChat()) == true) goto L15;
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$mg6HLiI7KXGgamRg3qp8i90PRYE() {
            if (VoIPService.getSharedInstance() != null) goto L5;
            return;
        L5:
            if (VoIPService.getSharedInstance().isMicMute() == false) goto L9;
            VoIPService.getSharedInstance().setMicMute(false, true, false);
            return;
        }

        public AnonymousClass3(GroupCallPip r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$touchSlop = r3;
            super(r2);
            this.pressedRunnable = new AnonymousClass1(this);
            this.micRunnable = new GroupCallPip$3$$ExternalSyntheticLambda0();
        }

        private void onTap() {
            if (VoIPService.getSharedInstance() == null) goto L6;
            GroupCallPip.m10846$$Nest$mshowAlert(this.this$0, !r0.showAlert);
            performHapticFeedback(3, 2);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            Point r42 = AndroidUtilities.displaySize;
            int r52 = r42.x;
            GroupCallPip r0 = this.this$0;
            if (r52 == r0.lastScreenX) goto L5;
        L6:
            r0.lastScreenX = r52;
            r0.lastScreenY = r42.y;
            if (r0.xRelative >= 0.0f) goto L10;
            SharedPreferences r43 = ApplicationLoader.applicationContext.getSharedPreferences("groupcallpipconfig", 0);
            GroupCallPip r53 = this.this$0;
            r53.xRelative = r43.getFloat("relativeX", 1.0f);
            GroupCallPip r54 = this.this$0;
            r54.yRelative = r43.getFloat("relativeY", 0.4f);
        L10:
            if (GroupCallPip.m10849$$Nest$sfgetinstance() == null) goto L14;
            GroupCallPip r44 = GroupCallPip.m10849$$Nest$sfgetinstance();
            GroupCallPip r3 = this.this$0;
            GroupCallPip.m10845$$Nest$msetPosition(r44, r3.xRelative, r3.yRelative);
            return;
        L14:
            return;
        L5:
            if (r0.lastScreenY != r42.y) goto L6;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r13) {
            boolean r1 = false;
            if (GroupCallPip.m10849$$Nest$sfgetinstance() != null) goto L5;
            return false;
        L5:
            float r0 = r13.getRawX();
            float r2 = r13.getRawY();
            ViewParent r3 = getParent();
            int r4 = r13.getAction();
            if (r4 == 0) goto L82;
            float r8 = 0.0f;
            if (r4 == 1) goto L41;
            if (r4 == 2) goto L12;
            if (r4 == 3) goto L41;
        L83:
            return true;
        L12:
            float r132 = r0 - this.startX;
            float r42 = r2 - this.startY;
            if (this.this$0.moving == true) goto L19;
            float r6 = (r132 * r132) + (r42 * r42);
            float r7 = this.val$touchSlop;
            if (r6 <= (r7 * r7)) goto L19;
            if (r3 == null) goto L18;
            r3.requestDisallowInterceptTouchEvent(true);
        L18:
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            GroupCallPip r133 = this.this$0;
            r133.moving = true;
            r133.showRemoveTooltip(true);
            GroupCallPip.m10846$$Nest$mshowAlert(this.this$0, false);
            this.startX = r0;
            this.startY = r2;
            r42 = 0.0f;
        L20:
            GroupCallPip r134 = this.this$0;
            if (r134.moving == false) goto L83;
            r134.windowX += r8;
            r134.windowY += r42;
            this.startX = r0;
            this.startY = r2;
            GroupCallPip.m10848$$Nest$mupdateButtonPosition(r134);
            float r135 = this.this$0.windowX + (getMeasuredWidth() / 2.0f);
            float r02 = this.this$0.windowY + (getMeasuredHeight() / 2.0f);
            float r43 = (r3.windowLeft - this.this$0.windowOffsetLeft) + (r3.windowRemoveTooltipView.getMeasuredWidth() / 2.0f);
            float r62 = (r3.windowTop - this.this$0.windowOffsetTop) + (r3.windowRemoveTooltipView.getMeasuredHeight() / 2.0f);
            float r22 = r135 - r43;
            float r72 = r02 - r62;
            float r32 = (r22 * r22) + (r72 * r72);
            if (r32 >= (AndroidUtilities.dp(80.0f) * AndroidUtilities.dp(80.0f))) goto L39;
            double r73 = Math.toDegrees(Math.atan(r22 / r72));
            if (r135 <= r43) goto L29;
            if (r02 >= r62) goto L29;
        L32:
            double r9 = 270.0d;
        L35:
            GroupCallPip.m10839$$Nest$fgetbutton(this.this$0).setRemoveAngle(r9 - r73);
            if (r32 >= (AndroidUtilities.dp(50.0f) * AndroidUtilities.dp(50.0f))) goto L38;
            boolean r136 = true;
            r1 = true;
        L40:
            this.this$0.pinnedToCenter(r1);
            this.this$0.prepareToRemove(r136);
            goto L83
        L38:
            r136 = true;
        L29:
            if (r135 < r43) goto L31;
        L34:
            r9 = 90.0d;
            goto L35
        L31:
            if (r02 >= r62) goto L34;
        L39:
            r136 = false;
        L19:
            r8 = r132;
        L41:
            AndroidUtilities.cancelRunOnUIThread(this.micRunnable);
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            GroupCallPip r03 = this.this$0;
            if (r03.animateToPrepareRemove == true) goto L44;
            r03.pressedState = false;
            GroupCallPip.m10842$$Nest$mcheckButtonAlpha(r03);
            if (this.pressed == false) goto L58;
            if (VoIPService.getSharedInstance() == null) goto L56;
            VoIPService.getSharedInstance().setMicMute(true, false, false);
            performHapticFeedback(3, 2);     // Catch: Exception -> L84
        L56:
            this.pressed = false;
        L63:
            if (r3 != null) goto L65;
        L81:
            GroupCallPip r12 = this.this$0;
            r12.moving = false;
            r12.showRemoveTooltip(false);
            goto L83
        L65:
            if (this.this$0.moving == false) goto L81;
            r3.requestDisallowInterceptTouchEvent(false);
            Point r137 = AndroidUtilities.displaySize;
            int r04 = r137.x;
            int r138 = r137.y;
            float r23 = this.this$0.windowLayoutParams.x;
            float r33 = getMeasuredWidth() + r23;
            float r44 = this.this$0.windowLayoutParams.y;
            float r63 = getMeasuredHeight() + r44;
            this.moveToBoundsAnimator = new AnimatorSet();
            float r10 = -AndroidUtilities.dp(36.0f);
            if (r23 >= r10) goto L70;
            ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{this.this$0.windowLayoutParams.x, r10});
            r05.addUpdateListener(GroupCallPip.m10840$$Nest$fgetupdateXlistener(this.this$0));
            this.moveToBoundsAnimator.playTogether(new Animator[]{r05});
            r23 = r10;
        L72:
            int r139 = r138 + AndroidUtilities.dp(36.0f);
            if (r44 >= (AndroidUtilities.statusBarHeight - AndroidUtilities.dp(36.0f))) goto L76;
            float r1310 = this.this$0.windowLayoutParams.y;
            r44 = AndroidUtilities.statusBarHeight - AndroidUtilities.dp(36.0f);
            ValueAnimator r1311 = ValueAnimator.ofFloat(new float[]{r1310, r44});
            r1311.addUpdateListener(GroupCallPip.m10841$$Nest$fgetupdateYlistener(this.this$0));
            this.moveToBoundsAnimator.playTogether(new Animator[]{r1311});
        L78:
            this.moveToBoundsAnimator.setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.moveToBoundsAnimator.start();
            GroupCallPip r1312 = this.this$0;
            if (r1312.xRelative < 0.0f) goto L81;
            GroupCallPip.m10843$$Nest$mgetRelativePosition(r1312, r23, r44, r1312.point);
            SharedPreferences.Editor r1313 = ApplicationLoader.applicationContext.getSharedPreferences("groupcallpipconfig", 0).edit();
            GroupCallPip r06 = this.this$0;
            float r24 = r06.point[0];
            r06.xRelative = r24;
            SharedPreferences.Editor r1314 = r1313.putFloat("relativeX", r24);
            GroupCallPip r07 = this.this$0;
            float r25 = r07.point[1];
            r07.yRelative = r25;
            r1314.putFloat("relativeY", r25).apply();
            goto L81
        L76:
            if (r63 <= r139) goto L78;
            float r08 = this.this$0.windowLayoutParams.y;
            r44 = r139 - getMeasuredHeight();
            ValueAnimator r1315 = ValueAnimator.ofFloat(new float[]{r08, r44});
            r1315.addUpdateListener(GroupCallPip.m10841$$Nest$fgetupdateYlistener(this.this$0));
            this.moveToBoundsAnimator.playTogether(new Animator[]{r1315});
            goto L78
        L70:
            if (r33 <= (r04 - r10)) goto L72;
            float r09 = (r04 - getMeasuredWidth()) - r10;
            ValueAnimator r26 = ValueAnimator.ofFloat(new float[]{this.this$0.windowLayoutParams.x, r09});
            r26.addUpdateListener(GroupCallPip.m10840$$Nest$fgetupdateXlistener(this.this$0));
            this.moveToBoundsAnimator.playTogether(new Animator[]{r26});
            r23 = r09;
            goto L72
        L58:
            if (r13.getAction() != 1) goto L63;
            if (this.this$0.moving == true) goto L63;
            onTap();
            return false;
        L44:
            if (this.pressed == true) goto L46;
        L48:
            this.pressed = false;
            GroupCallPip.m10844$$Nest$mremove(this.this$0);
            return false;
        L46:
            if (VoIPService.getSharedInstance() == null) goto L48;
            VoIPService.getSharedInstance().setMicMute(true, false, false);
            goto L48
        L82:
            getLocationOnScreen(this.this$0.location);
            GroupCallPip r1316 = this.this$0;
            int r14 = r1316.location[0];
            WindowManager.LayoutParams r45 = r1316.windowLayoutParams;
            r1316.windowOffsetLeft = r14 - r45.x;
            r1316.windowOffsetTop = r3[1] - r45.y;
            this.startX = r0;
            this.startY = r2;
            this.startTime = System.currentTimeMillis();
            AndroidUtilities.runOnUIThread(this.pressedRunnable, 300);
            GroupCallPip r122 = this.this$0;
            WindowManager.LayoutParams r1317 = r122.windowLayoutParams;
            r122.windowX = r1317.x;
            r122.windowY = r1317.y;
            r122.pressedState = true;
            GroupCallPip.m10842$$Nest$mcheckButtonAlpha(r122);
            goto L83
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass4(GroupCallPip r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            GroupCallPip r12 = this.this$0;
            r12.windowRemoveTooltipView.getLocationOnScreen(r12.location);
            GroupCallPip r0 = this.this$0;
            int[] r13 = r0.location;
            r0.windowLeft = r13[0];
            r0.windowTop = r13[1] - AndroidUtilities.dp(25.0f);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            this.this$0.windowRemoveTooltipOverlayView.setVisibility(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends View {
        Paint paint;
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass5(GroupCallPip r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            GroupCallPip r0 = this.this$0;
            boolean r1 = r0.animateToPrepareRemove;
            if (r1 == false) goto L10;
            float r3 = r0.prepareToRemoveProgress;
            if (r3 == 1.0f) goto L10;
            float r32 = r3 + 0.064f;
            r0.prepareToRemoveProgress = r32;
            if (r32 <= 1.0f) goto L9;
            r0.prepareToRemoveProgress = 1.0f;
        L9:
            invalidate();
        L17:
            this.paint.setColor(ColorUtils.blendARGB(1711607061, 1714752530, this.this$0.prepareToRemoveProgress));
            r7.drawCircle(getMeasuredWidth() / 2.0f, (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(25.0f), AndroidUtilities.dp(35.0f) + (AndroidUtilities.dp(5.0f) * this.this$0.prepareToRemoveProgress), this.paint);
            return;
        L10:
            if (r1 == true) goto L17;
            float r12 = r0.prepareToRemoveProgress;
            if (r12 == 0.0f) goto L17;
            float r13 = r12 - 0.064f;
            r0.prepareToRemoveProgress = r13;
            if (r13 >= 0.0f) goto L16;
            r0.prepareToRemoveProgress = 0.0f;
        L16:
            invalidate();
            goto L17
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            this.this$0.windowRemoveTooltipOverlayView.setAlpha(r1);
        }

        @Override // android.view.View
        public void setScaleX(float r1) {
            super.setScaleX(r1);
            this.this$0.windowRemoveTooltipOverlayView.setScaleX(r1);
        }

        @Override // android.view.View
        public void setScaleY(float r1) {
            super.setScaleY(r1);
            this.this$0.windowRemoveTooltipOverlayView.setScaleY(r1);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            this.this$0.windowRemoveTooltipOverlayView.setTranslationY(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends FrameLayout {
        int lastSize;
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass6(GroupCallPip r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.lastSize = -1;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            Point r12 = AndroidUtilities.displaySize;
            int r22 = r12.x + r12.y;
            int r13 = this.lastSize;
            if (r13 <= 0) goto L6;
            if (r13 == r22) goto L6;
            setVisibility(8);
            GroupCallPip r14 = this.this$0;
            r14.showAlert = false;
            GroupCallPip.m10842$$Nest$mcheckButtonAlpha(r14);
        L6:
            this.lastSize = r22;
        }

        @Override // android.view.View
        public void setVisibility(int r2) {
            super.setVisibility(r2);
            if (r2 != 8) goto L6;
            this.lastSize = -1;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass7(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.alertContainer.getViewTreeObserver().removeOnPreDrawListener(this);
            GroupCallPip r0 = this.this$0;
            r0.alertContainer.getLocationOnScreen(r0.location);
            float r1 = (r0.windowLayoutParams.x + this.this$0.windowOffsetLeft) + (GroupCallPip.m10839$$Nest$fgetbutton(r0).getMeasuredWidth() / 2.0f);
            float r12 = r1 - r0.location[0];
            float r3 = ((r0.windowLayoutParams.y + this.this$0.windowOffsetTop) + (GroupCallPip.m10839$$Nest$fgetbutton(r0).getMeasuredWidth() / 2.0f)) - this.this$0.location[1];
            if ((r3 - AndroidUtilities.dp(61.0f)) > 0.0f) goto L5;
        L7:
            boolean r5 = false;
        L9:
            if (((AndroidUtilities.dp(61.0f) + r12) + this.this$0.pipAlertView.getMeasuredWidth()) >= (this.this$0.alertContainer.getMeasuredWidth() - AndroidUtilities.dp(16.0f))) goto L13;
            if (r5 == false) goto L13;
            this.this$0.pipAlertView.setTranslationX(AndroidUtilities.dp(61.0f) + r12);
            float r2 = AndroidUtilities.dp(40.0f) / this.this$0.pipAlertView.getMeasuredHeight();
            float r02 = Math.max(r2, Math.min(r3 / this.this$0.alertContainer.getMeasuredHeight(), 1.0f - r2));
            this.this$0.pipAlertView.setTranslationY((int) (r3 - (r2.getMeasuredHeight() * r02)));
            this.this$0.pipAlertView.setPosition(0, r12, r3);
        L20:
            return false;
        L13:
            if (((r12 - AndroidUtilities.dp(61.0f)) - this.this$0.pipAlertView.getMeasuredWidth()) <= AndroidUtilities.dp(16.0f)) goto L16;
            if (r5 == false) goto L16;
            float r6 = AndroidUtilities.dp(40.0f) / this.this$0.pipAlertView.getMeasuredHeight();
            float r52 = Math.max(r6, Math.min(r3 / this.this$0.alertContainer.getMeasuredHeight(), 1.0f - r6));
            this.this$0.pipAlertView.setTranslationX((int) ((r12 - AndroidUtilities.dp(61.0f)) - this.this$0.pipAlertView.getMeasuredWidth()));
            this.this$0.pipAlertView.setTranslationY((int) (r3 - (r0.getMeasuredHeight() * r52)));
            this.this$0.pipAlertView.setPosition(1, r12, r3);
        L16:
            float r22 = this.this$0.alertContainer.getMeasuredHeight() * 0.3f;
            GroupCallPip r53 = this.this$0;
            if (r3 <= r22) goto L19;
            float r23 = r12 / r53.alertContainer.getMeasuredWidth();
            float r54 = AndroidUtilities.dp(40.0f) / this.this$0.pipAlertView.getMeasuredWidth();
            float r24 = Math.max(r54, Math.min(r23, 1.0f - r54));
            this.this$0.pipAlertView.setTranslationX((int) (r12 - (r5.getMeasuredWidth() * r24)));
            this.this$0.pipAlertView.setTranslationY((int) ((r3 - r2.getMeasuredHeight()) - AndroidUtilities.dp(61.0f)));
            this.this$0.pipAlertView.setPosition(3, r12, r3);
            goto L20
        L19:
            float r25 = r12 / r53.alertContainer.getMeasuredWidth();
            float r55 = AndroidUtilities.dp(40.0f) / this.this$0.pipAlertView.getMeasuredWidth();
            float r26 = Math.max(r55, Math.min(r25, 1.0f - r55));
            this.this$0.pipAlertView.setTranslationX((int) (r12 - (r5.getMeasuredWidth() * r26)));
            this.this$0.pipAlertView.setTranslationY((int) (AndroidUtilities.dp(61.0f) + r3));
            this.this$0.pipAlertView.setPosition(2, r12, r3);
            goto L20
        L5:
            if ((AndroidUtilities.dp(61.0f) + r3) >= this.this$0.alertContainer.getMeasuredHeight()) goto L7;
            r5 = true;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallPip this$0;

        public AnonymousClass8(GroupCallPip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.alertContainer.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallPip$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallPip this$0;
        final /* synthetic */ View val$alert;
        final /* synthetic */ WindowManager val$windowManager;
        final /* synthetic */ View val$windowRemoveTooltipOverlayView;
        final /* synthetic */ View val$windowRemoveTooltipView;
        final /* synthetic */ View val$windowView;

        public static /* synthetic */ void $r8$lambda$ReVuHfSmyrmXgr5wuhGaW32XCbI(View r1, View r2, WindowManager r3, View r4, View r5) {
            r1.setVisibility(8);
            r2.setVisibility(8);
            r3.removeView(r1);
            r3.removeView(r2);
            r3.removeView(r4);
            r3.removeView(r5);
        }

        public AnonymousClass9(GroupCallPip r1, View r2, View r3, WindowManager r4, View r5, View r6) {
            this.this$0 = r1;
            this.val$windowView = r2;
            this.val$windowRemoveTooltipView = r3;
            this.val$windowManager = r4;
            this.val$windowRemoveTooltipOverlayView = r5;
            this.val$alert = r6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r7) {
            NotificationCenter.getInstance(this.this$0.currentAccount).doOnIdle(new GroupCallPip$9$$ExternalSyntheticLambda0(this.val$windowView, this.val$windowRemoveTooltipView, this.val$windowManager, this.val$windowRemoveTooltipOverlayView, this.val$alert));
        }
    }

    public static /* synthetic */ void $r8$lambda$9Kss3kn5oewQztpL03ZAnRR1cds(GroupCallPip r0, ValueAnimator r1) {
        r0.lambda$pinnedToCenter$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$MDrfEVvjXcz9YNHQyOzvao65hV0(GroupCallPip r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$dJt6bSYIV7qJbxNJoWuZK5bs62c() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$zKc9ceW1i3osKuN72GYE2WoORP0(GroupCallPip r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallPipButton m10839$$Nest$fgetbutton(GroupCallPip r0) {
        return r0.button;
    }

    /* renamed from: -$$Nest$fgetupdateXlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m10840$$Nest$fgetupdateXlistener(GroupCallPip r0) {
        return r0.updateXlistener;
    }

    /* renamed from: -$$Nest$fgetupdateYlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m10841$$Nest$fgetupdateYlistener(GroupCallPip r0) {
        return r0.updateYlistener;
    }

    /* renamed from: -$$Nest$mcheckButtonAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10842$$Nest$mcheckButtonAlpha(GroupCallPip r0) {
        r0.checkButtonAlpha();
    }

    /* renamed from: -$$Nest$mgetRelativePosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10843$$Nest$mgetRelativePosition(GroupCallPip r0, float r1, float r2, float[] r3) {
        r0.getRelativePosition(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mremove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10844$$Nest$mremove(GroupCallPip r0) {
        r0.remove();
    }

    /* renamed from: -$$Nest$msetPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10845$$Nest$msetPosition(GroupCallPip r0, float r1, float r2) {
        r0.setPosition(r1, r2);
    }

    /* renamed from: -$$Nest$mshowAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10846$$Nest$mshowAlert(GroupCallPip r0, boolean r1) {
        r0.showAlert(r1);
    }

    /* renamed from: -$$Nest$mupdateAvatarsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10847$$Nest$mupdateAvatarsPosition(GroupCallPip r0) {
        r0.updateAvatarsPosition();
    }

    /* renamed from: -$$Nest$mupdateButtonPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10848$$Nest$mupdateButtonPosition(GroupCallPip r0) {
        r0.updateButtonPosition();
    }

    /* renamed from: -$$Nest$sfgetinstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallPip m10849$$Nest$sfgetinstance() {
        return instance;
    }

    static {
    }

    public GroupCallPip(Context r11, int r12) {
        this.prepareToRemoveProgress = 0.0f;
        this.location = new int[2];
        this.point = new float[2];
        this.xRelative = -1.0f;
        this.yRelative = -1.0f;
        this.updateXlistener = new AnonymousClass1(this);
        this.updateYlistener = new AnonymousClass2(this);
        this.animateToPinnedToCenter = false;
        this.pinnedProgress = 0.0f;
        this.currentAccount = r12;
        AnonymousClass3 r0 = new AnonymousClass3(this, r11, ViewConfiguration.get(r11).getScaledTouchSlop());
        this.windowView = r0;
        r0.setAlpha(0.7f);
        GroupCallPipButton r122 = new GroupCallPipButton(r11, this.currentAccount, false);
        this.button = r122;
        this.windowView.addView(r122, LayoutHelper.createFrame(-1, -1, 17));
        AvatarsImageView r123 = new AvatarsImageView(r11, true);
        this.avatarsImageView = r123;
        r123.setStyle(5);
        this.avatarsImageView.setCentered(true);
        this.avatarsImageView.setVisibility(8);
        this.avatarsImageView.setDelegate(new GroupCallPip$$ExternalSyntheticLambda0(this));
        updateAvatars(false);
        this.windowView.addView(this.avatarsImageView, LayoutHelper.createFrame(108, 36, 49));
        this.windowRemoveTooltipView = new AnonymousClass4(this, r11);
        AnonymousClass5 r124 = new AnonymousClass5(this, r11);
        this.removeTooltipView = r124;
        this.windowRemoveTooltipView.addView(r124);
        this.windowRemoveTooltipOverlayView = new FrameLayout(r11);
        RLottieImageView r125 = new RLottieImageView(r11);
        this.iconView = r125;
        r125.setScaleType(ImageView.ScaleType.CENTER);
        RLottieDrawable r4 = new RLottieDrawable(R.raw.group_pip_delete_icon, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f), true, null);
        this.deleteIcon = r4;
        r4.setPlayInDirectionOfCustomEndFrame(true);
        r125.setAnimation(this.deleteIcon);
        r125.setColorFilter(-1);
        this.windowRemoveTooltipOverlayView.addView(r125, LayoutHelper.createFrame(40, 40.0f, 17, 0.0f, 0.0f, 0.0f, 25.0f));
        AnonymousClass6 r126 = new AnonymousClass6(this, r11);
        this.alertContainer = r126;
        r126.setOnClickListener(new GroupCallPip$$ExternalSyntheticLambda1(this));
        this.alertContainer.setClipChildren(false);
        FrameLayout r127 = this.alertContainer;
        GroupCallPipAlertView r02 = new GroupCallPipAlertView(r11, this.currentAccount);
        this.pipAlertView = r02;
        r127.addView(r02, LayoutHelper.createFrame(-2, -2.0f));
    }

    private void checkButtonAlpha() {
        if (this.pressedState == false) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (this.buttonInAlpha == r0) goto L17;
        this.buttonInAlpha = r0;
        FrameLayout r1 = this.windowView;
        if (r0 == false) goto L14;
        r1.animate().alpha(1.0f).start();
    L15:
        this.button.setPressedState(r0);
        return;
    L14:
        r1.animate().alpha(0.7f).start();
        goto L15
    L17:
        return;
    L5:
        if (this.showAlert == true) goto L8;
        r0 = false;
        goto L10
    }

    public static boolean checkInlinePermissions() {
        if (ApplicationLoader.canDrawOverlays == false) goto L6;
        return true;
    L6:
        return false;
    }

    public static void clearForce() {
        forceRemoved = false;
    }

    private static WindowManager.LayoutParams createWindowLayoutParams(Context r3) {
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams();
        r0.height = AndroidUtilities.dp(105.0f);
        r0.width = AndroidUtilities.dp(105.0f);
        r0.gravity = 51;
        r0.format = -3;
        if (AndroidUtilities.checkInlinePermissions(r3) == true) goto L5;
        r0.type = 99;
    L9:
        r0.flags = 520;
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
        GroupCallPip r0 = instance;
        if (r0 == null) goto L6;
        r0.showAlert(false);
        GroupCallPip r02 = instance;
        WindowManager r6 = r02.windowManager;
        FrameLayout r3 = r02.windowView;
        FrameLayout r4 = r02.windowRemoveTooltipView;
        FrameLayout r5 = r02.windowRemoveTooltipOverlayView;
        FrameLayout r7 = r02.alertContainer;
        r3.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.0f).setListener(new AnonymousClass10(r3, r4, r5, r6, r7)).start();
        instance.onDestroy();
        instance = null;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
        return;
    }

    public static GroupCallPip getInstance() {
        return instance;
    }

    private void getRelativePosition(float r4, float r5, float[] r6) {
        Point r3 = AndroidUtilities.displaySize;
        float r0 = r3.x;
        float r32 = r3.y;
        float r1 = -AndroidUtilities.dp(36.0f);
        r6[0] = (r4 - r1) / ((r0 - (r1 * 2.0f)) - AndroidUtilities.dp(105.0f));
        r6[1] = r5 / (r32 - AndroidUtilities.dp(105.0f));
        r6[0] = Math.min(1.0f, Math.max(0.0f, r6[0]));
        r6[1] = Math.min(1.0f, Math.max(0.0f, r6[1]));
    }

    public static boolean isShowing() {
        if (RTMPStreamPipOverlay.isVisible() == false) goto L6;
        return true;
    L6:
        if (instance == null) goto L9;
        return true;
    L9:
        if (checkInlinePermissions() == true) goto L11;
        return false;
    L11:
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L14;
    L24:
        return false;
    L14:
        if (r0.groupCall == null) goto L24;
        if (r0.isHangingUp() == true) goto L24;
        if (forceRemoved == true) goto L24;
        if (ApplicationLoader.mainInterfaceStopped == false) goto L22;
    L23:
        return true;
    L22:
        if (GroupCallActivity.groupCallUiVisible == true) goto L24;
        goto L23
    }

    private /* synthetic */ void lambda$new$0() {
        updateAvatars(true);
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        showAlert(false);
    }

    private /* synthetic */ void lambda$pinnedToCenter$3(ValueAnimator r4) {
        if (this.removed == true) goto L10;
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.pinnedProgress = r42;
        this.button.setPinnedProgress(r42);
        this.windowView.setScaleX(1.0f - (this.pinnedProgress * 0.6f));
        this.windowView.setScaleY(1.0f - (this.pinnedProgress * 0.6f));
        if (this.moving == false) goto L9;
        updateButtonPosition();
        return;
    L9:
        return;
    }

    public static boolean onBackPressed() {
        GroupCallPip r0 = instance;
        if (r0 != null) goto L5;
    L8:
        return false;
    L5:
        if (r0.showAlert == false) goto L8;
        r0.showAlert(false);
        return true;
    }

    private void onDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupCallUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.webRtcSpeakerAmplitudeEvent);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.groupCallVisibilityChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didEndCall);
    }

    private void remove() {
        GroupCallPip r0 = instance;
        if (r0 != null) goto L5;
        return;
    L5:
        this.removed = true;
        forceRemoved = true;
        this.button.removed = true;
        r0.showAlert(false);
        float r6 = ((this.windowLeft - this.windowOffsetLeft) + (this.windowRemoveTooltipView.getMeasuredWidth() / 2.0f)) - (this.windowLayoutParams.x + (this.windowView.getMeasuredWidth() / 2.0f));
        float r7 = ((this.windowTop - this.windowOffsetTop) + (this.windowRemoveTooltipView.getMeasuredHeight() / 2.0f)) - (this.windowLayoutParams.y + (this.windowView.getMeasuredHeight() / 2.0f));
        GroupCallPip r02 = instance;
        WindowManager r4 = r02.windowManager;
        FrameLayout r8 = r02.windowView;
        FrameLayout r3 = r02.windowRemoveTooltipView;
        FrameLayout r5 = r02.windowRemoveTooltipOverlayView;
        FrameLayout r03 = r02.alertContainer;
        onDestroy();
        instance = null;
        AnimatorSet r11 = new AnimatorSet();
        if (this.deleteIcon.getCurrentFrame() >= 33) goto L8;
        long r12 = (long) (((1.0f - (this.deleteIcon.getCurrentFrame() / 33.0f)) * this.deleteIcon.getDuration()) / 2.0f);
    L9:
        int r10 = this.windowLayoutParams.x;
        ValueAnimator r102 = ValueAnimator.ofFloat(new float[]{r10, r10 + r6});
        r102.addUpdateListener(this.updateXlistener);
        ValueAnimator r9 = r102.setDuration(250);
        CubicBezierInterpolator r62 = CubicBezierInterpolator.DEFAULT;
        r9.setInterpolator(r62);
        r11.playTogether(new Animator[]{r102});
        int r92 = this.windowLayoutParams.y;
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{r92, (r92 + r7) - AndroidUtilities.dp(30.0f), this.windowLayoutParams.y + r7});
        r2.addUpdateListener(this.updateYlistener);
        r2.setDuration(250).setInterpolator(r62);
        r11.playTogether(new Animator[]{r2});
        Property r22 = View.SCALE_X;
        r11.playTogether(new Animator[]{ObjectAnimator.ofFloat(r8, r22, new float[]{r8.getScaleX(), 0.1f}).setDuration(180)});
        Property r93 = View.SCALE_Y;
        r11.playTogether(new Animator[]{ObjectAnimator.ofFloat(r8, r93, new float[]{r8.getScaleY(), 0.1f}).setDuration(180)});
        Property r63 = View.ALPHA;
        ObjectAnimator r72 = ObjectAnimator.ofFloat(r8, r63, new float[]{1.0f, 0.0f});
        r72.setStartDelay(245);
        r72.setDuration(105);
        r11.playTogether(new Animator[]{r72});
        AndroidUtilities.runOnUIThread(new GroupCallPip$$ExternalSyntheticLambda3(), 370);
        long r122 = r12 + 530;
        ObjectAnimator r73 = ObjectAnimator.ofFloat(this.removeTooltipView, r22, new float[]{1.0f, 1.05f});
        r73.setDuration(r122);
        CubicBezierInterpolator r14 = CubicBezierInterpolator.EASE_BOTH;
        r73.setInterpolator(r14);
        r11.playTogether(new Animator[]{r73});
        ObjectAnimator r74 = ObjectAnimator.ofFloat(this.removeTooltipView, r93, new float[]{1.0f, 1.05f});
        r74.setDuration(r122);
        r74.setInterpolator(r14);
        r11.playTogether(new Animator[]{r74});
        ObjectAnimator r23 = ObjectAnimator.ofFloat(this.removeTooltipView, r22, new float[]{1.0f, 0.3f});
        r23.setStartDelay(r122);
        r23.setDuration(350);
        CubicBezierInterpolator r75 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r23.setInterpolator(r75);
        r11.playTogether(new Animator[]{r23});
        ObjectAnimator r24 = ObjectAnimator.ofFloat(this.removeTooltipView, r93, new float[]{1.0f, 0.3f});
        r24.setStartDelay(r122);
        r24.setDuration(350);
        r24.setInterpolator(r75);
        r11.playTogether(new Animator[]{r24});
        ObjectAnimator r25 = ObjectAnimator.ofFloat(this.removeTooltipView, View.TRANSLATION_Y, new float[]{0.0f, AndroidUtilities.dp(60.0f)});
        r25.setStartDelay(r122);
        r25.setDuration(350);
        r25.setInterpolator(r75);
        r11.playTogether(new Animator[]{r25});
        ObjectAnimator r26 = ObjectAnimator.ofFloat(this.removeTooltipView, r63, new float[]{1.0f, 0.0f});
        r26.setStartDelay(r122);
        r26.setDuration(350);
        r26.setInterpolator(r75);
        r11.playTogether(new Animator[]{r26});
        r11.addListener(new AnonymousClass9(this, r8, r3, r4, r5, r03));
        r11.start();
        this.deleteIcon.setCustomEndFrame(66);
        this.iconView.stopAnimation();
        this.iconView.playAnimation();
        return;
    L8:
        r12 = 0;
        goto L9
    }

    private void setPosition(float r6, float r7) {
        float r0 = -AndroidUtilities.dp(36.0f);
        WindowManager.LayoutParams r2 = this.windowLayoutParams;
        r2.x = (int) (r0 + (((AndroidUtilities.displaySize.x - (2.0f * r0)) - AndroidUtilities.dp(105.0f)) * r6));
        this.windowLayoutParams.y = (int) ((AndroidUtilities.displaySize.y - AndroidUtilities.dp(105.0f)) * r7);
        updateAvatarsPosition();
        if (this.windowView.getParent() == null) goto L6;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
        return;
    }

    public static void show(Context r6, int r7) {
        if (instance == null) goto L5;
        return;
    L5:
        instance = new GroupCallPip(r6, r7);
        WindowManager r72 = (WindowManager) ApplicationLoader.applicationContext.getSystemService("window");
        instance.windowManager = r72;
        WindowManager.LayoutParams r0 = createWindowLayoutParams(r6);
        r0.width = -1;
        r0.height = -1;
        r0.dimAmount = 0.25f;
        r0.flags = 522;
        r72.addView(instance.alertContainer, r0);
        instance.alertContainer.setVisibility(8);
        WindowManager.LayoutParams r02 = createWindowLayoutParams(r6);
        r02.gravity = 81;
        r02.width = AndroidUtilities.dp(100.0f);
        r02.height = AndroidUtilities.dp(150.0f);
        r72.addView(instance.windowRemoveTooltipView, r02);
        WindowManager.LayoutParams r03 = createWindowLayoutParams(r6);
        GroupCallPip r5 = instance;
        r5.windowLayoutParams = r03;
        r72.addView(r5.windowView, r03);
        WindowManager.LayoutParams r62 = createWindowLayoutParams(r6);
        r62.gravity = 81;
        r62.width = AndroidUtilities.dp(100.0f);
        r62.height = AndroidUtilities.dp(150.0f);
        r72.addView(instance.windowRemoveTooltipOverlayView, r62);
        instance.windowRemoveTooltipView.setVisibility(8);
        instance.windowView.setScaleX(0.5f);
        instance.windowView.setScaleY(0.5f);
        instance.windowView.setAlpha(0.0f);
        instance.windowView.animate().alpha(0.7f).scaleY(1.0f).scaleX(1.0f).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        NotificationCenter.getInstance(instance.currentAccount).addObserver(instance, NotificationCenter.groupCallUpdated);
        NotificationCenter.getGlobalInstance().addObserver(instance, NotificationCenter.webRtcSpeakerAmplitudeEvent);
        NotificationCenter.getGlobalInstance().addObserver(instance, NotificationCenter.didEndCall);
    }

    private void showAlert(boolean r6) {
        if (r6 == this.showAlert) goto L11;
        this.showAlert = r6;
        this.alertContainer.animate().setListener(null).cancel();
        if (this.showAlert == true) goto L7;
        this.pipAlertView.animate().scaleX(0.7f).scaleY(0.7f).setDuration(150).start();
        this.alertContainer.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass8(this)).start();
        goto L11
    L7:
        if (this.alertContainer.getVisibility() == 0) goto L9;
        this.alertContainer.setVisibility(0);
        this.alertContainer.setAlpha(0.0f);
        this.pipAlertView.setScaleX(0.7f);
        this.pipAlertView.setScaleY(0.7f);
    L9:
        this.alertContainer.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass7(this));
        this.alertContainer.animate().alpha(1.0f).setDuration(150).start();
        this.pipAlertView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
    L11:
        checkButtonAlpha();
    }

    private void showAvatars(boolean r9) {
        if (this.avatarsImageView.getTag() == null) goto L5;
        boolean r0 = true;
    L6:
        if (r9 == r0) goto L19;
        Integer r3 = null;
        this.avatarsImageView.animate().setListener(null).cancel();
        AvatarsImageView r02 = this.avatarsImageView;
        if (r9 == true) goto L10;
        r02.animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(150).setListener(new AnonymousClass13(this)).start();
    L14:
        AvatarsImageView r8 = this.avatarsImageView;
        if (r9 == false) goto L17;
        r3 = 1;
    L17:
        r8.setTag(r3);
        return;
    L10:
        if (r02.getVisibility() == 0) goto L12;
        this.avatarsImageView.setVisibility(0);
        this.avatarsImageView.setAlpha(0.0f);
        this.avatarsImageView.setScaleX(0.5f);
        this.avatarsImageView.setScaleY(0.5f);
    L12:
        this.avatarsImageView.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
        goto L14
    L19:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void updateAvatars(boolean r13) {
        AvatarsImageView r0 = this.avatarsImageView;
        if (r0.avatarsDrawable.transitionProgressAnimator != null) goto L30;
        VoIPService r02 = VoIPService.getSharedInstance();
        if (r02 == null) goto L7;
        ChatObject.Call r2 = r02.groupCall;
    L8:
        int r3 = 0;
        if (r2 == null) goto L25;
        long r4 = r02.getSelfId();
        int r03 = r2.sortedParticipants.size();
        int r6 = 0;
    L12:
        if (r3 >= 2) goto L23;
        if (r6 >= r03) goto L21;
        TLRPC.GroupCallParticipant r7 = r2.sortedParticipants.get(r6);
        if (MessageObject.getPeerId(r7.peer) == r4) goto L22;
        if ((SystemClock.uptimeMillis() - r7.lastSpeakTime) > 500) goto L22;
        this.avatarsImageView.setObject(r3, this.currentAccount, r7);
    L20:
        r3 = r3 + 1;
    L22:
        r6 = r6 + 1;
        goto L12
    L21:
        this.avatarsImageView.setObject(r3, this.currentAccount, null);
        goto L20
    L23:
        this.avatarsImageView.setObject(2, this.currentAccount, null);
        this.avatarsImageView.commitTransition(r13);
        return;
    L25:
        AvatarsImageView r04 = this.avatarsImageView;
        if (r3 >= 3) goto L28;
        r04.setObject(r3, this.currentAccount, null);
        r3 = r3 + 1;
        goto L25
    L28:
        r04.commitTransition(r13);
        return;
    L7:
        r2 = null;
        goto L8
    L30:
        r0.updateAfterTransitionEnd();
    }

    private void updateAvatarsPosition() {
        float r0 = Math.max(this.windowLayoutParams.x, -AndroidUtilities.dp(36.0f));
        int r2 = AndroidUtilities.displaySize.x;
        float r02 = Math.min(r0, (r2 - this.windowView.getMeasuredWidth()) + AndroidUtilities.dp(36.0f));
        if (r02 >= 0.0f) goto L6;
        this.avatarsImageView.setTranslationX(Math.abs(r02) / 3.0f);
        return;
    L6:
        float r3 = r2 - this.windowView.getMeasuredWidth();
        AvatarsImageView r5 = this.avatarsImageView;
        if (r02 <= r3) goto L10;
        r5.setTranslationX((-Math.abs(r02 - (r2 - this.windowView.getMeasuredWidth()))) / 3.0f);
        return;
    L10:
        r5.setTranslationX(0.0f);
    }

    private void updateButtonPosition() {
        float r0 = ((this.windowLeft - this.windowOffsetLeft) + (this.windowRemoveTooltipView.getMeasuredWidth() / 2.0f)) - (this.windowView.getMeasuredWidth() / 2.0f);
        float r1 = (((this.windowTop - this.windowOffsetTop) + (this.windowRemoveTooltipView.getMeasuredHeight() / 2.0f)) - (this.windowView.getMeasuredHeight() / 2.0f)) - AndroidUtilities.dp(25.0f);
        WindowManager.LayoutParams r2 = this.windowLayoutParams;
        float r3 = this.windowX;
        float r4 = this.pinnedProgress;
        r2.x = (int) ((r3 * (1.0f - r4)) + (r0 * r4));
        r2.y = (int) ((this.windowY * (1.0f - r4)) + (r1 * r4));
        updateAvatarsPosition();
        if (this.windowView.getParent() == null) goto L6;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
        return;
    }

    public static void updateVisibility(Context r4) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
    L9:
        boolean r2 = false;
    L11:
        if (AndroidUtilities.checkInlinePermissions(ApplicationLoader.applicationContext) == false) goto L22;
        if (r2 == false) goto L22;
        if (forceRemoved == true) goto L22;
        if (ApplicationLoader.mainInterfaceStopped == false) goto L19;
    L20:
        show(r4, r0.getAccount());
        instance.showAvatars(true);
        return;
    L19:
        if (GroupCallActivity.groupCallUiVisible == false) goto L20;
    L22:
        finish();
        return;
    L5:
        if (r0.groupCall == null) goto L9;
        if (r0.isHangingUp() == true) goto L9;
        r2 = true;
        goto L11
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.groupCallUpdated) goto L5;
    L11:
        updateAvatars(true);
        return;
    L5:
        if (r1 == NotificationCenter.webRtcSpeakerAmplitudeEvent) goto L11;
        if (r1 != NotificationCenter.didEndCall) goto L13;
        updateVisibility(ApplicationLoader.applicationContext);
        return;
    }

    public void pinnedToCenter(boolean r5) {
        if (this.removed == false) goto L6;
        return;
    L6:
        if (this.animateToPinnedToCenter == r5) goto L16;
        this.animateToPinnedToCenter = r5;
        ValueAnimator r0 = this.pinAnimator;
        if (r0 == null) goto L10;
        r0.removeAllListeners();
        this.pinAnimator.cancel();
    L10:
        float r02 = this.pinnedProgress;
        if (r5 == false) goto L13;
        float r1 = 1.0f;
    L14:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.pinAnimator = r03;
        r03.addUpdateListener(new GroupCallPip$$ExternalSyntheticLambda2(this));
        this.pinAnimator.addListener(new AnonymousClass12(this, r5));
        this.pinAnimator.setDuration(250);
        this.pinAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.pinAnimator.start();
        return;
    L13:
        r1 = 0.0f;
        goto L14
    }

    public void prepareToRemove(boolean r4) {
        if (this.animateToPrepareRemove == r4) goto L13;
        this.animateToPrepareRemove = r4;
        this.removeTooltipView.invalidate();
        if (this.removed == true) goto L11;
        RLottieDrawable r0 = this.deleteIcon;
        if (r4 == false) goto L9;
        int r1 = 33;
    L10:
        r0.setCustomEndFrame(r1);
        this.iconView.playAnimation();
        goto L11
    L9:
        r1 = 0;
    L11:
        if (r4 == false) goto L13;
        this.button.performHapticFeedback(3, 2);     // Catch: Exception -> L15
    L13:
        this.button.prepareToRemove(r4);
    }

    public void showRemoveTooltip(boolean r14) {
        if (this.animateToShowRemoveTooltip == r14) goto L16;
        this.animateToShowRemoveTooltip = r14;
        AnimatorSet r0 = this.showRemoveAnimator;
        if (r0 == null) goto L8;
        r0.removeAllListeners();
        this.showRemoveAnimator.cancel();
    L8:
        if (r14 == true) goto L10;
        AnimatorSet r142 = new AnimatorSet();
        this.showRemoveAnimator = r142;
        View r8 = this.removeTooltipView;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{r8.getAlpha(), 0.0f});
        View r82 = this.removeTooltipView;
        ObjectAnimator r83 = ObjectAnimator.ofFloat(r82, View.SCALE_X, new float[]{r82.getScaleX(), 0.5f});
        View r9 = this.removeTooltipView;
        r142.playTogether(new Animator[]{r5, r83, ObjectAnimator.ofFloat(r9, View.SCALE_Y, new float[]{r9.getScaleY(), 0.5f})});
        this.showRemoveAnimator.addListener(new AnonymousClass11(this));
        this.showRemoveAnimator.setDuration(150);
        this.showRemoveAnimator.start();
        return;
    L10:
        if (this.windowRemoveTooltipView.getVisibility() == 0) goto L12;
        this.windowRemoveTooltipView.setVisibility(0);
        this.removeTooltipView.setAlpha(0.0f);
        this.removeTooltipView.setScaleX(0.5f);
        this.removeTooltipView.setScaleY(0.5f);
        this.deleteIcon.setCurrentFrame(0);
    L12:
        AnimatorSet r143 = new AnimatorSet();
        this.showRemoveAnimator = r143;
        View r52 = this.removeTooltipView;
        ObjectAnimator r53 = ObjectAnimator.ofFloat(r52, View.ALPHA, new float[]{r52.getAlpha(), 1.0f});
        View r7 = this.removeTooltipView;
        ObjectAnimator r72 = ObjectAnimator.ofFloat(r7, View.SCALE_X, new float[]{r7.getScaleX(), 1.0f});
        View r92 = this.removeTooltipView;
        r143.playTogether(new Animator[]{r53, r72, ObjectAnimator.ofFloat(r92, View.SCALE_Y, new float[]{r92.getScaleY(), 1.0f})});
        this.showRemoveAnimator.setDuration(150).start();
        return;
    }
}
