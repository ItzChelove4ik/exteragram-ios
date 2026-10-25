package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.CrossOutDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.GroupCallFullscreenAdapter;
import org.telegram.ui.Components.GroupCallPip;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.GroupCallActivity;
import org.webrtc.TextureViewRenderer;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class GroupCallRenderersContainer extends FrameLayout {
    private boolean animateSpeakingOnNextDraw;
    private LongSparseIntArray attachedPeerIds;
    private final ArrayList<GroupCallMiniTextureView> attachedRenderers;
    private final ImageView backButton;
    ChatObject.Call call;
    private boolean canZoomGesture;
    private boolean drawFirst;
    private boolean drawRenderesOnly;
    ValueAnimator fullscreenAnimator;
    private final RecyclerView fullscreenListView;
    public ChatObject.VideoParticipant fullscreenParticipant;
    public long fullscreenPeerId;
    public GroupCallMiniTextureView fullscreenTextureView;
    GroupCallActivity groupCallActivity;
    public boolean hasPinnedVideo;
    Runnable hideUiRunnable;
    boolean hideUiRunnableIsScheduled;
    public boolean inFullscreenMode;
    public boolean inLayout;
    private boolean isInPinchToZoomTouchMode;
    private boolean isTablet;
    public long lastUpdateTime;
    long lastUpdateTooltipTime;
    private final RecyclerView listView;
    public int listWidth;
    boolean maybeSwipeToBackGesture;
    private boolean notDrawRenderes;
    AnimationNotificationsLocker notificationsLocker;
    private GroupCallMiniTextureView outFullscreenTextureView;
    private final ImageView pinButton;
    View pinContainer;
    CrossOutDrawable pinDrawable;
    TextView pinTextView;
    private float pinchCenterX;
    private float pinchCenterY;
    float pinchScale;
    private float pinchStartCenterX;
    private float pinchStartCenterY;
    private float pinchStartDistance;
    private float pinchTranslationX;
    private float pinchTranslationY;
    public ImageView pipView;
    private int pointerId1;
    private int pointerId2;
    public float progressToFullscreenMode;
    float progressToHideUi;
    public float progressToScrimView;
    ValueAnimator replaceFullscreenViewAnimator;
    Drawable rightShadowDrawable;
    private final View rightShadowView;
    private boolean showSpeakingMembersToast;
    private float showSpeakingMembersToastProgress;
    private final AvatarsImageView speakingMembersAvatars;
    private final TextView speakingMembersText;
    private final FrameLayout speakingMembersToast;
    private float speakingMembersToastChangeProgress;
    private float speakingMembersToastFromLeft;
    private float speakingMembersToastFromRight;
    private float speakingMembersToastFromTextLeft;
    private long speakingToastPeerId;
    ValueAnimator swipeToBackAnimator;
    float swipeToBackDy;
    boolean swipeToBackGesture;
    public boolean swipedBack;
    boolean tapGesture;
    long tapTime;
    float tapX;
    float tapY;
    Drawable topShadowDrawable;
    private final View topShadowView;
    private final int touchSlop;
    boolean uiVisible;
    public UndoView[] undoView;
    TextView unpinTextView;
    Runnable updateTooltipRunnbale;
    ValueAnimator zoomBackAnimator;
    private boolean zoomStarted;

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass1(GroupCallRenderersContainer r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean r0 = this.this$0.canHideUI();
            GroupCallRenderersContainer r2 = this.this$0;
            if (r0 == true) goto L6;
            AndroidUtilities.runOnUIThread(r2.hideUiRunnable, 3000);
            return;
        L6:
            r2.hideUiRunnableIsScheduled = false;
            GroupCallRenderersContainer.m13853$$Nest$msetUiVisible(r2, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$finalNewSmallTextureView;

        public AnonymousClass10(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$finalNewSmallTextureView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$finalNewSmallTextureView.animateEnter = false;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$newSmallTextureView;

        public AnonymousClass11(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$newSmallTextureView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$newSmallTextureView.animateEnter = false;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$12, reason: invalid class name */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$newFullscreenTextureView;

        public AnonymousClass12(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$newFullscreenTextureView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            GroupCallRenderersContainer r42 = this.this$0;
            r42.replaceFullscreenViewAnimator = null;
            this.val$newFullscreenTextureView.animateEnter = false;
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r42) != null) goto L5;
            return;
        L5:
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(this.this$0).getParent() == null) goto L7;
            GroupCallRenderersContainer r43 = this.this$0;
            r43.removeView(GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r43));
            GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(this.this$0).release();
        L7:
            GroupCallRenderersContainer.m13849$$Nest$fputoutFullscreenTextureView(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass13(GroupCallRenderersContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            GroupCallRenderersContainer r42 = this.this$0;
            r42.replaceFullscreenViewAnimator = null;
            r42.fullscreenTextureView.animateEnter = false;
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r42) != null) goto L5;
            return;
        L5:
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(this.this$0).getParent() == null) goto L7;
            GroupCallRenderersContainer r43 = this.this$0;
            r43.removeView(GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r43));
            GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(this.this$0).release();
        L7:
            GroupCallRenderersContainer.m13849$$Nest$fputoutFullscreenTextureView(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$textureViewFinal;

        public AnonymousClass14(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$textureViewFinal = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            this.this$0.notificationsLocker.unlock();
            GroupCallRenderersContainer r42 = this.this$0;
            r42.fullscreenAnimator = null;
            this.val$textureViewFinal.animateToFullscreen = false;
            if (r42.inFullscreenMode == true) goto L5;
            GroupCallRenderersContainer.m13852$$Nest$mclearCurrentFullscreenTextureView(r42);
            GroupCallRenderersContainer r43 = this.this$0;
            r43.fullscreenTextureView = null;
            r43.fullscreenPeerId = 0;
        L5:
            GroupCallRenderersContainer r44 = this.this$0;
            if (r44.inFullscreenMode == false) goto L8;
            float r0 = 1.0f;
        L9:
            r44.progressToFullscreenMode = r0;
            r44.update();
            this.this$0.onFullScreenModeChanged(false);
            GroupCallRenderersContainer r45 = this.this$0;
            if (r45.inFullscreenMode == true) goto L13;
            GroupCallRenderersContainer.m13840$$Nest$fgetbackButton(r45).setVisibility(8);
            GroupCallRenderersContainer.m13842$$Nest$fgetpinButton(this.this$0).setVisibility(8);
            this.this$0.unpinTextView.setVisibility(8);
            this.this$0.pinContainer.setVisibility(8);
            return;
        L13:
            return;
        L8:
            r0 = 0.0f;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass15(GroupCallRenderersContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            GroupCallRenderersContainer r0 = this.this$0;
            r0.swipeToBackAnimator = null;
            r0.swipeToBackDy = 0.0f;
            r0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass16(GroupCallRenderersContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GroupCallRenderersContainer r22 = this.this$0;
            r22.zoomBackAnimator = null;
            r22.pinchScale = 1.0f;
            GroupCallRenderersContainer.m13850$$Nest$fputpinchTranslationX(r22, 0.0f);
            GroupCallRenderersContainer.m13851$$Nest$fputpinchTranslationY(this.this$0, 0.0f);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$2, reason: invalid class name */
    public class AnonymousClass2 extends ImageView {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass2(GroupCallRenderersContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(ActionBar.getCurrentActionBarHeight(), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$3, reason: invalid class name */
    public class AnonymousClass3 extends ImageView {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass3(GroupCallRenderersContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.pinContainer.invalidate();
            this.this$0.invalidate();
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(ActionBar.getCurrentActionBarHeight(), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$4, reason: invalid class name */
    public class AnonymousClass4 extends View {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ Drawable val$pinRippleDrawable;

        public AnonymousClass4(GroupCallRenderersContainer r1, Context r2, Drawable r3) {
            this.this$0 = r1;
            this.val$pinRippleDrawable = r3;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            float r0 = (this.this$0.pinTextView.getMeasuredWidth() * (1.0f - this.this$0.pinDrawable.getProgress())) + (this.this$0.unpinTextView.getMeasuredWidth() * this.this$0.pinDrawable.getProgress());
            r5.save();
            this.val$pinRippleDrawable.setBounds(0, 0, AndroidUtilities.dp(50.0f) + ((int) r0), getMeasuredHeight());
            this.val$pinRippleDrawable.draw(r5);
            super.dispatchDraw(r5);
        }

        @Override // android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            this.val$pinRippleDrawable.setState(getDrawableState());
        }

        @Override // android.view.View
        public void jumpDrawablesToCurrentState() {
            super.jumpDrawablesToCurrentState();
            this.val$pinRippleDrawable.jumpToCurrentState();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.val$pinRippleDrawable != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ Drawable val$toastBackgroundDrawable;

        public AnonymousClass5(GroupCallRenderersContainer r1, Context r2, Drawable r3) {
            this.this$0 = r1;
            this.val$toastBackgroundDrawable = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            if (GroupCallRenderersContainer.m13845$$Nest$fgetspeakingMembersToastChangeProgress(this.this$0) != 1.0f) goto L5;
            this.val$toastBackgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            GroupCallRenderersContainer.m13843$$Nest$fgetspeakingMembersAvatars(this.this$0).setTranslationX(0.0f);
            GroupCallRenderersContainer.m13844$$Nest$fgetspeakingMembersText(this.this$0).setTranslationX(0.0f);
        L6:
            this.val$toastBackgroundDrawable.draw(r10);
            super.dispatchDraw(r10);
            return;
        L5:
            float r1 = 1.0f - CubicBezierInterpolator.DEFAULT.getInterpolation(GroupCallRenderersContainer.m13845$$Nest$fgetspeakingMembersToastChangeProgress(this.this$0));
            float r3 = (GroupCallRenderersContainer.m13846$$Nest$fgetspeakingMembersToastFromLeft(this.this$0) - getLeft()) * r1;
            float r0 = (GroupCallRenderersContainer.m13848$$Nest$fgetspeakingMembersToastFromTextLeft(this.this$0) - GroupCallRenderersContainer.m13844$$Nest$fgetspeakingMembersText(this.this$0).getLeft()) * r1;
            this.val$toastBackgroundDrawable.setBounds((int) r3, 0, getMeasuredWidth() + ((int) ((GroupCallRenderersContainer.m13847$$Nest$fgetspeakingMembersToastFromRight(this.this$0) - getRight()) * r1)), getMeasuredHeight());
            GroupCallRenderersContainer.m13843$$Nest$fgetspeakingMembersAvatars(this.this$0).setTranslationX(r3);
            GroupCallRenderersContainer.m13844$$Nest$fgetspeakingMembersText(this.this$0).setTranslationX(-r0);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$6, reason: invalid class name */
    public class AnonymousClass6 extends UndoView {
        final /* synthetic */ GroupCallRenderersContainer this$0;

        public AnonymousClass6(GroupCallRenderersContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.UndoView, android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$removingMiniView;

        public AnonymousClass7(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$removingMiniView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$removingMiniView.getParent() == null) goto L6;
            this.this$0.removeView(this.val$removingMiniView);
            this.val$removingMiniView.release();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$newFullscreenTextureView;
        final /* synthetic */ GroupCallMiniTextureView val$removingMiniView;

        public AnonymousClass8(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2, GroupCallMiniTextureView r3) {
            this.this$0 = r1;
            this.val$newFullscreenTextureView = r2;
            this.val$removingMiniView = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            GroupCallRenderersContainer r42 = this.this$0;
            r42.replaceFullscreenViewAnimator = null;
            this.val$newFullscreenTextureView.animateEnter = false;
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r42) != null) goto L5;
            return;
        L5:
            if (GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(this.this$0).getParent() == null) goto L7;
            GroupCallRenderersContainer r43 = this.this$0;
            r43.removeView(GroupCallRenderersContainer.m13841$$Nest$fgetoutFullscreenTextureView(r43));
            this.val$removingMiniView.release();
        L7:
            GroupCallRenderersContainer.m13849$$Nest$fputoutFullscreenTextureView(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.GroupCallRenderersContainer$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallRenderersContainer this$0;
        final /* synthetic */ GroupCallMiniTextureView val$removingMiniView;

        public AnonymousClass9(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$removingMiniView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$removingMiniView.getParent() == null) goto L6;
            this.this$0.removeView(this.val$removingMiniView);
            this.val$removingMiniView.release();
            return;
        }
    }

    /* renamed from: $r8$lambda$7xfNKLuhSBNnm7-fFk9QCAfUM4E, reason: not valid java name */
    public static /* synthetic */ void m13837$r8$lambda$7xfNKLuhSBNnm7fFk9QCAfUM4E(GroupCallRenderersContainer r0, ValueAnimator r1) {
        r0.lambda$requestFullscreen$6(r1);
    }

    /* renamed from: $r8$lambda$DRuEHTsriyxxxJ8f1xrjlB-adbE, reason: not valid java name */
    public static /* synthetic */ void m13838$r8$lambda$DRuEHTsriyxxxJ8f1xrjlBadbE(GroupCallRenderersContainer r0) {
        r0.lambda$setVisibleParticipant$9();
    }

    public static /* synthetic */ void $r8$lambda$E_W11qJnAucHQrmwVbF1guFBhgM(GroupCallRenderersContainer r0, GroupCallMiniTextureView r1, ValueAnimator r2) {
        r0.lambda$requestFullscreen$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FhaJrGkeEPHC1cxUwkzVmZGpddU(GroupCallRenderersContainer r0, GroupCallMiniTextureView r1, GroupCallMiniTextureView r2) {
        r0.lambda$requestFullscreen$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$QYXRjo35YQToqL0j2yfAgpjzbKM(GroupCallRenderersContainer r0, GroupCallActivity r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XhwWDLzCQ2pd7LPxCq0R3XK09Qk(GroupCallRenderersContainer r0, float r1, float r2, float r3, ValueAnimator r4) {
        r0.lambda$finishZoom$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$blggpgrcMaICbN2xA0U_FRAvz_U(GroupCallRenderersContainer r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$cDjgfaV-BBQgMCFQFD8-oIAGRuU, reason: not valid java name */
    public static /* synthetic */ void m13839$r8$lambda$cDjgfaVBBQgMCFQFD8oIAGRuU(GroupCallRenderersContainer r0, GroupCallMiniTextureView r1) {
        r0.lambda$requestFullscreen$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$jK8gnM5LUj4YzV_oG8mCVXVS2aQ(GroupCallRenderersContainer r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$xqEpmWMq_RuZ1faw5Fz9r6hHJnU(GroupCallRenderersContainer r0, ValueAnimator r1) {
        r0.lambda$animateSwipeToBack$7(r1);
    }

    /* renamed from: -$$Nest$fgetbackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m13840$$Nest$fgetbackButton(GroupCallRenderersContainer r0) {
        return r0.backButton;
    }

    /* renamed from: -$$Nest$fgetoutFullscreenTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMiniTextureView m13841$$Nest$fgetoutFullscreenTextureView(GroupCallRenderersContainer r0) {
        return r0.outFullscreenTextureView;
    }

    /* renamed from: -$$Nest$fgetpinButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m13842$$Nest$fgetpinButton(GroupCallRenderersContainer r0) {
        return r0.pinButton;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersAvatars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AvatarsImageView m13843$$Nest$fgetspeakingMembersAvatars(GroupCallRenderersContainer r0) {
        return r0.speakingMembersAvatars;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13844$$Nest$fgetspeakingMembersText(GroupCallRenderersContainer r0) {
        return r0.speakingMembersText;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersToastChangeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13845$$Nest$fgetspeakingMembersToastChangeProgress(GroupCallRenderersContainer r0) {
        return r0.speakingMembersToastChangeProgress;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersToastFromLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13846$$Nest$fgetspeakingMembersToastFromLeft(GroupCallRenderersContainer r0) {
        return r0.speakingMembersToastFromLeft;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersToastFromRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13847$$Nest$fgetspeakingMembersToastFromRight(GroupCallRenderersContainer r0) {
        return r0.speakingMembersToastFromRight;
    }

    /* renamed from: -$$Nest$fgetspeakingMembersToastFromTextLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13848$$Nest$fgetspeakingMembersToastFromTextLeft(GroupCallRenderersContainer r0) {
        return r0.speakingMembersToastFromTextLeft;
    }

    /* renamed from: -$$Nest$fputoutFullscreenTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13849$$Nest$fputoutFullscreenTextureView(GroupCallRenderersContainer r0, GroupCallMiniTextureView r1) {
        r0.outFullscreenTextureView = r1;
    }

    /* renamed from: -$$Nest$fputpinchTranslationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13850$$Nest$fputpinchTranslationX(GroupCallRenderersContainer r0, float r1) {
        r0.pinchTranslationX = r1;
    }

    /* renamed from: -$$Nest$fputpinchTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13851$$Nest$fputpinchTranslationY(GroupCallRenderersContainer r0, float r1) {
        r0.pinchTranslationY = r1;
    }

    /* renamed from: -$$Nest$mclearCurrentFullscreenTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13852$$Nest$mclearCurrentFullscreenTextureView(GroupCallRenderersContainer r0) {
        r0.clearCurrentFullscreenTextureView();
    }

    /* renamed from: -$$Nest$msetUiVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13853$$Nest$msetUiVisible(GroupCallRenderersContainer r0, boolean r1) {
        r0.setUiVisible(r1);
    }

    public GroupCallRenderersContainer(Context r19, RecyclerView r20, RecyclerView r21, ArrayList<GroupCallMiniTextureView> r22, ChatObject.Call r23, GroupCallActivity r24) {
        super(r19);
        this.attachedPeerIds = new LongSparseIntArray();
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.speakingMembersToastChangeProgress = 1.0f;
        this.animateSpeakingOnNextDraw = true;
        this.uiVisible = true;
        this.hideUiRunnable = new AnonymousClass1(this);
        this.pinchScale = 1.0f;
        this.undoView = new UndoView[2];
        this.listView = r20;
        this.fullscreenListView = r21;
        this.attachedRenderers = r22;
        this.call = r23;
        this.groupCallActivity = r24;
        AnonymousClass2 r7 = new AnonymousClass2(this, r19);
        this.backButton = r7;
        int r9 = 0;
        BackDrawable r8 = new BackDrawable(false);
        r8.setColor(-1);
        r7.setImageDrawable(r8);
        r7.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r7.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r7.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(-1, 55)));
        View r12 = new View(r19);
        this.topShadowView = r12;
        Drawable r13 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0, ColorUtils.setAlphaComponent(-16777216, 114)});
        this.topShadowDrawable = r13;
        r12.setBackground(r13);
        addView(r12, LayoutHelper.createFrame(-1, 120.0f));
        View r82 = new View(r19);
        this.rightShadowView = r82;
        Drawable r122 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0, ColorUtils.setAlphaComponent(-16777216, 114)});
        this.rightShadowDrawable = r122;
        r82.setBackground(r122);
        if (r23 != null) goto L5;
    L7:
        int r2 = 8;
    L8:
        r82.setVisibility(r2);
        addView(r82, LayoutHelper.createFrame(160, -1, 5));
        addView(r7, LayoutHelper.createFrame(56, -1, 51));
        r7.setOnClickListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda0(this));
        AnonymousClass3 r72 = new AnonymousClass3(this, r19);
        this.pinButton = r72;
        Drawable r123 = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100));
        AnonymousClass4 r132 = new AnonymousClass4(this, r19, r123);
        this.pinContainer = r132;
        r132.setOnClickListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda1(this));
        r123.setCallback(this.pinContainer);
        addView(this.pinContainer);
        CrossOutDrawable r124 = new CrossOutDrawable(r19, R.drawable.msg_pin_filled, -1);
        this.pinDrawable = r124;
        r124.setOffsets(-AndroidUtilities.dp(1.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f));
        r72.setImageDrawable(this.pinDrawable);
        r72.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        addView(r72, LayoutHelper.createFrame(56, -1, 51));
        TextView r25 = new TextView(r19);
        this.pinTextView = r25;
        r25.setTextColor(-1);
        this.pinTextView.setTextSize(1, 15.0f);
        this.pinTextView.setTypeface(AndroidUtilities.bold());
        this.pinTextView.setText(LocaleController.getString(R.string.CallVideoPin));
        TextView r26 = new TextView(r19);
        this.unpinTextView = r26;
        r26.setTextColor(-1);
        this.unpinTextView.setTextSize(1, 15.0f);
        this.unpinTextView.setTypeface(AndroidUtilities.bold());
        this.unpinTextView.setText(LocaleController.getString(R.string.CallVideoUnpin));
        addView(this.pinTextView, LayoutHelper.createFrame(-2, -2, 51));
        addView(this.unpinTextView, LayoutHelper.createFrame(-2, -2, 51));
        ImageView r27 = new ImageView(r19);
        this.pipView = r27;
        r27.setVisibility(4);
        this.pipView.setAlpha(0.0f);
        this.pipView.setImageResource(R.drawable.ic_goinline);
        this.pipView.setContentDescription(LocaleController.getString(R.string.AccDescrPipMode));
        int r28 = AndroidUtilities.dp(4.0f);
        this.pipView.setPadding(r28, r28, r28, r28);
        this.pipView.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(-1, 55)));
        this.pipView.setOnClickListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda2(this, r24));
        addView(this.pipView, LayoutHelper.createFrame(32, 32.0f, 53, 12.0f, 12.0f, 12.0f, 12.0f));
        AnonymousClass5 r3 = new AnonymousClass5(this, r19, Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_listViewBackground), Opcodes.SUB_DOUBLE_2ADDR)));
        this.speakingMembersToast = r3;
        AvatarsImageView r29 = new AvatarsImageView(r19, true);
        this.speakingMembersAvatars = r29;
        r29.setStyle(10);
        r3.setClipChildren(false);
        r3.setClipToPadding(false);
        r3.addView(r29, LayoutHelper.createFrame(100, 32.0f, 16, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r210 = new TextView(r19);
        this.speakingMembersText = r210;
        r210.setTextSize(1, 14.0f);
        r210.setTextColor(-1);
        r210.setLines(1);
        r210.setEllipsize(TextUtils.TruncateAt.END);
        r3.addView(r210, LayoutHelper.createFrame(-2, -2, 16));
        addView(r3, LayoutHelper.createFrame(-2, 36.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    L9:
        if (r9 >= 2) goto L11;
        this.undoView[r9] = new AnonymousClass6(this, r19);
        this.undoView[r9].setHideAnimationType(2);
        this.undoView[r9].setAdditionalTranslationY(AndroidUtilities.dp(10.0f));
        addView(this.undoView[r9], LayoutHelper.createFrame(-1, -2.0f, 80, 16.0f, 0.0f, 0.0f, 8.0f));
        r9 = r9 + 1;
        goto L9
    L11:
        this.pinContainer.setVisibility(8);
        setIsTablet(GroupCallActivity.isTabletMode);
        return;
    L5:
        if (isRtmpStream() == false) goto L7;
        r2 = 0;
        goto L8
    }

    private void animateSwipeToBack(boolean r6) {
        if (this.swipeToBackGesture == false) goto L17;
        this.swipeToBackGesture = false;
        float r0 = this.swipeToBackDy;
        float[] r3 = new float[2];
        if (r6 == false) goto L7;
        r3[0] = r0;
        r3[1] = 0.0f;
        ValueAnimator r02 = ValueAnimator.ofFloat(r3);
    L8:
        this.swipeToBackAnimator = r02;
        r02.addUpdateListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda3(this));
        this.swipeToBackAnimator.addListener(new AnonymousClass15(this));
        ValueAnimator r03 = this.swipeToBackAnimator;
        CubicBezierInterpolator r2 = CubicBezierInterpolator.DEFAULT;
        r03.setInterpolator(r2);
        ValueAnimator r04 = this.swipeToBackAnimator;
        if (r6 == false) goto L11;
        long r32 = 350;
    L12:
        r04.setDuration(r32);
        this.swipeToBackAnimator.setInterpolator(r2);
        GroupCallMiniTextureView r62 = this.fullscreenTextureView;
        if (r62 == null) goto L15;
        r62.textureView.synchOrRunAnimation(this.swipeToBackAnimator);
    L16:
        this.lastUpdateTime = System.currentTimeMillis();
        goto L17
    L15:
        this.swipeToBackAnimator.start();
        goto L16
    L11:
        r32 = 200;
        goto L12
    L7:
        r3[0] = r0;
        r3[1] = 0.0f;
        r02 = ValueAnimator.ofFloat(r3);
    L17:
        this.maybeSwipeToBackGesture = false;
    }

    private boolean checkPointerIds(MotionEvent r5) {
        if (r5.getPointerCount() >= 2) goto L6;
        return false;
    L6:
        if (this.pointerId1 != r5.getPointerId(0)) goto L11;
        if (this.pointerId2 != r5.getPointerId(1)) goto L11;
        return true;
    L11:
        if (this.pointerId1 == r5.getPointerId(1)) goto L13;
    L15:
        return false;
    L13:
        if (this.pointerId2 != r5.getPointerId(0)) goto L15;
        return true;
    }

    private void clearCurrentFullscreenTextureView() {
        GroupCallMiniTextureView r0 = this.fullscreenTextureView;
        if (r0 == null) goto L6;
        r0.setSwipeToBack(false, 0.0f);
        this.fullscreenTextureView.setZoom(false, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        return;
    }

    private void finishZoom() {
        if (this.zoomStarted == false) goto L5;
        this.zoomStarted = false;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.zoomBackAnimator = r0;
        r0.addUpdateListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda4(this, this.pinchScale, this.pinchTranslationX, this.pinchTranslationY));
        this.zoomBackAnimator.addListener(new AnonymousClass16(this));
        this.zoomBackAnimator.setDuration(350);
        this.zoomBackAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.zoomBackAnimator.start();
        this.lastUpdateTime = System.currentTimeMillis();
    L5:
        this.canZoomGesture = false;
        this.isInPinchToZoomTouchMode = false;
    }

    private boolean isRtmpStream() {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.call.rtmp_stream == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$animateSwipeToBack$7(ValueAnimator r1) {
        this.swipeToBackDy = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$finishZoom$8(float r3, float r4, float r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        this.pinchScale = (r3 * r62) + ((1.0f - r62) * 1.0f);
        this.pinchTranslationX = r4 * r62;
        this.pinchTranslationY = r5 * r62;
        invalidate();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        if (this.inFullscreenMode == false) goto L6;
        boolean r32 = !this.hasPinnedVideo;
        this.hasPinnedVideo = r32;
        this.pinDrawable.setCrossOut(r32, true);
        requestLayout();
        return;
    }

    private /* synthetic */ void lambda$new$2(GroupCallActivity r1, View r2) {
        if (isRtmpStream() == false) goto L11;
        if (PipUtils.checkAnyPipPermissions(r1.getParentActivity()) == false) goto L8;
        RTMPStreamPipOverlay.show(r1.getParentActivity());
        r1.dismiss();
        return;
    L8:
        AlertsCreator.createDrawOverlayPermissionDialog(r1.getParentActivity(), null, true).show();
        return;
    L11:
        if (AndroidUtilities.checkInlinePermissions(r1.getParentActivity()) == false) goto L14;
        GroupCallPip.clearForce();
        r1.dismiss();
        return;
    L14:
        AlertsCreator.createDrawOverlayGroupCallPermissionDialog(getContext()).show();
    }

    private /* synthetic */ void lambda$requestFullscreen$3(GroupCallMiniTextureView r4, GroupCallMiniTextureView r5) {
        ValueAnimator r0 = this.replaceFullscreenViewAnimator;
        if (r0 == null) goto L5;
        r0.start();
    L5:
        r4.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.0f).setListener(new AnonymousClass9(this, r4)).setDuration(100).start();
        if (r5 == null) goto L9;
        r5.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(100).setListener(new AnonymousClass10(this, r5)).start();
        return;
    }

    private /* synthetic */ void lambda$requestFullscreen$4(GroupCallMiniTextureView r3) {
        r3.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setListener(new AnonymousClass11(this, r3)).setDuration(150).start();
    }

    private /* synthetic */ void lambda$requestFullscreen$5(GroupCallMiniTextureView r1, ValueAnimator r2) {
        r1.setAlpha(((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    private /* synthetic */ void lambda$requestFullscreen$6(ValueAnimator r3) {
        this.progressToFullscreenMode = ((Float) r3.getAnimatedValue()).floatValue();
        this.groupCallActivity.getMenuItemsContainer().setAlpha(1.0f - this.progressToFullscreenMode);
        this.groupCallActivity.invalidateActionBarAlpha();
        this.groupCallActivity.invalidateScrollOffsetY();
        update();
    }

    private /* synthetic */ void lambda$setVisibleParticipant$9() {
        this.updateTooltipRunnbale = null;
        setVisibleParticipant(true);
    }

    private void setUiVisible(boolean r3) {
        if (this.uiVisible == r3) goto L16;
        this.uiVisible = r3;
        onUiVisibilityChanged();
        if (r3 == true) goto L7;
    L11:
        this.hideUiRunnableIsScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(this.hideUiRunnable);
    L12:
        GroupCallMiniTextureView r2 = this.fullscreenTextureView;
        if (r2 == null) goto L17;
        r2.requestLayout();
        return;
    L17:
        return;
    L7:
        if (this.inFullscreenMode == false) goto L11;
        if (this.hideUiRunnableIsScheduled == true) goto L12;
        this.hideUiRunnableIsScheduled = true;
        AndroidUtilities.runOnUIThread(this.hideUiRunnable, 3000);
        goto L12
    }

    public void attach(GroupCallMiniTextureView r3) {
        this.attachedRenderers.add(r3);
        long r0 = MessageObject.getPeerId(r3.participant.participant.peer);
        LongSparseIntArray r2 = this.attachedPeerIds;
        r2.put(r0, r2.get(r0, 0) + 1);
    }

    public boolean autoPinEnabled() {
        if (this.hasPinnedVideo == false) goto L5;
        return false;
    L5:
        if ((System.currentTimeMillis() - this.lastUpdateTime) > 2000) goto L7;
        return false;
    L7:
        if (this.swipeToBackGesture == false) goto L9;
        return false;
    L9:
        if (this.isInPinchToZoomTouchMode == true) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean canHideUI() {
        return this.inFullscreenMode;
    }

    public void delayHideUi() {
        if (this.hideUiRunnableIsScheduled == false) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.hideUiRunnable);
    L5:
        AndroidUtilities.runOnUIThread(this.hideUiRunnable, 3000);
        this.hideUiRunnableIsScheduled = true;
    }

    public void detach(GroupCallMiniTextureView r3) {
        this.attachedRenderers.remove(r3);
        long r0 = MessageObject.getPeerId(r3.participant.participant.peer);
        this.attachedPeerIds.put(r0, r2.get(r0, 0) - 1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r22) {
        int r9 = 0;
        if (GroupCallActivity.isTabletMode == false) goto L5;
        this.drawRenderesOnly = true;
        super.dispatchDraw(r22);
        this.drawRenderesOnly = false;
    L5:
        this.drawFirst = true;
        super.dispatchDraw(r22);
        this.drawFirst = false;
        if (this.outFullscreenTextureView == null) goto L8;
    L9:
        float r2 = this.listView.getY() - getTop();
        float r3 = (this.listView.getMeasuredHeight() + r2) - this.listView.getTranslationY();
        float r4 = this.progressToFullscreenMode;
        r22.save();
        boolean r5 = GroupCallActivity.isTabletMode;
        if (r5 == true) goto L18;
        GroupCallMiniTextureView r6 = this.fullscreenTextureView;
        if (r6 == null) goto L18;
        if (r6.forceDetached == true) goto L18;
        if (r6.primaryView == null) goto L18;
        float r52 = 1.0f - r4;
        r22.clipRect(0.0f, r2 * r52, getMeasuredWidth(), (r3 * r52) + (getMeasuredHeight() * r4));
    L20:
        GroupCallMiniTextureView r23 = this.outFullscreenTextureView;
        if (r23 != null) goto L23;
    L25:
        GroupCallMiniTextureView r24 = this.fullscreenTextureView;
        if (r24 != null) goto L28;
    L59:
        r22.restore();
    L60:
        int r14 = 0;
    L62:
        if (r14 >= 2) goto L87;
        if (this.undoView[r14].getVisibility() != 0) goto L86;
        r22.save();
        if (GroupCallActivity.isLandscapeMode == false) goto L68;
        float r25 = 0.0f;
    L69:
        float r42 = getMeasuredWidth();
        int r53 = getMeasuredHeight();
        if (GroupCallActivity.isLandscapeMode == false) goto L72;
        int r62 = 0;
    L73:
        r22.clipRect(0.0f, 0.0f, r42, ((r53 - r62) + r25) - AndroidUtilities.dp(18.0f));
        boolean r43 = this.isTablet;
        UndoView[] r54 = this.undoView;
        if (r43 == false) goto L76;
        r22.translate(r54[r14].getX() - AndroidUtilities.dp(8.0f), this.undoView[r14].getY() - AndroidUtilities.dp(8.0f));
    L82:
        if (this.undoView[r14].getAlpha() == 1.0f) goto L84;
        r22.saveLayerAlpha(0.0f, 0.0f, this.undoView[r14].getMeasuredWidth(), this.undoView[r14].getMeasuredHeight(), (int) (this.undoView[r14].getAlpha() * 255.0f), 31);
    L85:
        r22.scale(this.undoView[r14].getScaleX(), this.undoView[r14].getScaleY(), this.undoView[r14].getMeasuredWidth() / 2.0f, this.undoView[r14].getMeasuredHeight() / 2.0f);
        this.undoView[r14].draw(r22);
        r22.restore();
        r22.restore();
        goto L86
    L84:
        r22.save();
        goto L85
    L76:
        float r44 = r54[r14].getX() - AndroidUtilities.dp(8.0f);
        float r32 = this.undoView[r14].getY();
        if (GroupCallActivity.isLandscapeMode == false) goto L79;
        int r55 = 0;
    L80:
        r22.translate(r44, ((r32 - r55) + r25) - AndroidUtilities.dp(26.0f));
        goto L82
    L79:
        r55 = AndroidUtilities.dp(90.0f);
        goto L80
    L72:
        r62 = AndroidUtilities.dp(90.0f);
        goto L73
    L68:
        r25 = (-AndroidUtilities.dp(90.0f)) * (1.0f - this.progressToHideUi);
    L86:
        r14 = r14 + 1;
        goto L62
    L87:
        float r26 = this.progressToFullscreenMode * (1.0f - this.progressToHideUi);
        if (this.replaceFullscreenViewAnimator == null) goto L103;
        GroupCallMiniTextureView r45 = this.outFullscreenTextureView;
        if (r45 == null) goto L103;
        GroupCallMiniTextureView r56 = this.fullscreenTextureView;
        if (r56 == null) goto L103;
        boolean r46 = r45.hasVideo;
        boolean r63 = r56.hasVideo;
        if (r46 == r63) goto L99;
        if (r63 == true) goto L98;
        float r47 = 1.0f - r56.getAlpha();
    L97:
        float r48 = r47 * r26;
    L102:
        int r49 = (int) (r48 * 255.0f);
        this.topShadowDrawable.setAlpha(r49);
        this.rightShadowDrawable.setAlpha(r49);
    L107:
        this.backButton.setAlpha(r26);
        boolean r410 = isRtmpStream();
        ImageView r57 = this.pinButton;
        if (r410 == false) goto L113;
        r57.setAlpha(0.0f);
        this.pinButton.setVisibility(4);
        this.pipView.setAlpha(r26);
        this.pipView.setVisibility(0);
        boolean r411 = GroupCallActivity.isLandscapeMode;
        ImageView r58 = this.pipView;
        if (r411 == false) goto L112;
        r58.setTranslationX((-AndroidUtilities.dp(72.0f)) * (1.0f - this.progressToHideUi));
    L114:
        float r412 = getMeasuredWidth() - this.pinTextView.getMeasuredWidth();
        float r64 = ((ActionBar.getCurrentActionBarHeight() - this.pinTextView.getMeasuredHeight()) / 2.0f) - AndroidUtilities.dp(1.0f);
        float r59 = (((getMeasuredWidth() - this.unpinTextView.getMeasuredWidth()) * this.pinDrawable.getProgress()) + (r412 * (1.0f - this.pinDrawable.getProgress()))) - AndroidUtilities.dp(21.0f);
        if (GroupCallActivity.isTabletMode == false) goto L119;
        int r413 = AndroidUtilities.dp(328.0f);
    L117:
        float r510 = r59 - r413;
        this.pinTextView.setTranslationX(r510);
        this.unpinTextView.setTranslationX(r510);
        this.pinTextView.setTranslationY(r64);
        this.unpinTextView.setTranslationY(r64);
        this.pinContainer.setTranslationX(r510 - AndroidUtilities.dp(36.0f));
        this.pinContainer.setTranslationY((ActionBar.getCurrentActionBarHeight() - this.pinContainer.getMeasuredHeight()) / 2.0f);
        this.pinButton.setTranslationX(r510 - AndroidUtilities.dp(44.0f));
        boolean r414 = isRtmpStream();
        TextView r511 = this.pinTextView;
        if (r414 == false) goto L125;
        r511.setAlpha(0.0f);
        this.unpinTextView.setAlpha(0.0f);
        this.pinContainer.setAlpha(0.0f);
    L126:
        float r27 = this.speakingMembersToastChangeProgress;
        if (r27 == 1.0f) goto L133;
        float r28 = r27 + 0.07272727f;
        this.speakingMembersToastChangeProgress = r28;
        if (r28 <= 1.0f) goto L131;
        this.speakingMembersToastChangeProgress = 1.0f;
    L132:
        this.speakingMembersToast.invalidate();
        goto L133
    L131:
        invalidate();
    L133:
        boolean r29 = this.showSpeakingMembersToast;
        if (r29 == false) goto L141;
        float r512 = this.showSpeakingMembersToastProgress;
        if (r512 == 1.0f) goto L141;
        float r513 = r512 + 0.10666667f;
        this.showSpeakingMembersToastProgress = r513;
        if (r513 <= 1.0f) goto L140;
        this.showSpeakingMembersToastProgress = 1.0f;
    L148:
        boolean r210 = GroupCallActivity.isLandscapeMode;
        FrameLayout r415 = this.speakingMembersToast;
        if (r210 == false) goto L151;
        r415.setTranslationY(AndroidUtilities.dp(16.0f));
    L152:
        this.speakingMembersToast.setAlpha(this.showSpeakingMembersToastProgress * this.progressToFullscreenMode);
        this.speakingMembersToast.setScaleX((this.showSpeakingMembersToastProgress * 0.5f) + 0.5f);
        this.speakingMembersToast.setScaleY((this.showSpeakingMembersToastProgress * 0.5f) + 0.5f);
        if (GroupCallActivity.isTabletMode == false) goto L155;
        this.notDrawRenderes = true;
        super.dispatchDraw(r22);
        this.notDrawRenderes = false;
    L157:
        if (this.fullscreenListView.getVisibility() == 0) goto L159;
        return;
    L159:
        if (r9 >= this.fullscreenListView.getChildCount()) goto L174;
        GroupCallFullscreenAdapter.GroupCallUserCell r211 = (GroupCallFullscreenAdapter.GroupCallUserCell) this.fullscreenListView.getChildAt(r9);
        if (r211.getVisibility() != 0) goto L165;
        if (r211.getAlpha() == 0.0f) goto L165;
        r22.save();
        r22.translate(r211.getX() + this.fullscreenListView.getX(), r211.getY() + this.fullscreenListView.getY());
        r22.scale(r211.getScaleX(), r211.getScaleY(), r211.getMeasuredWidth() / 2.0f, r211.getMeasuredHeight() / 2.0f);
        r211.drawOverlays(r22);
        r22.restore();
    L165:
        r9 = r9 + 1;
        goto L159
    L174:
        return;
    L155:
        super.dispatchDraw(r22);
        goto L157
    L151:
        r415.setTranslationY(((ActionBar.getCurrentActionBarHeight() * (1.0f - this.progressToHideUi)) + AndroidUtilities.dp(8.0f)) + (AndroidUtilities.dp(8.0f) * this.progressToHideUi));
        goto L152
    L140:
        invalidate();
    L141:
        if (r29 == true) goto L148;
        float r212 = this.showSpeakingMembersToastProgress;
        if (r212 == 0.0f) goto L148;
        float r213 = r212 - 0.10666667f;
        this.showSpeakingMembersToastProgress = r213;
        if (r213 >= 0.0f) goto L147;
        this.showSpeakingMembersToastProgress = 0.0f;
        goto L148
    L147:
        invalidate();
        goto L148
    L125:
        r511.setAlpha((1.0f - this.pinDrawable.getProgress()) * r26);
        this.unpinTextView.setAlpha(this.pinDrawable.getProgress() * r26);
        this.pinContainer.setAlpha(r26);
        goto L126
    L119:
        if (GroupCallActivity.isLandscapeMode == false) goto L121;
        r413 = AndroidUtilities.dp(180.0f);
        goto L117
    L121:
        r413 = 0;
        goto L117
    L112:
        r58.setTranslationX(0.0f);
        goto L114
    L113:
        r57.setAlpha(r26);
        this.pinButton.setVisibility(0);
        this.pipView.setAlpha(0.0f);
        this.pipView.setVisibility(4);
        goto L114
    L98:
        r47 = r56.getAlpha();
        goto L97
    L99:
        if (r63 == true) goto L101;
        r48 = 0.0f;
        goto L102
    L101:
        r48 = r26;
    L103:
        GroupCallMiniTextureView r416 = this.fullscreenTextureView;
        Drawable r514 = this.topShadowDrawable;
        if (r416 == null) goto L106;
        float r11 = 255.0f * r26;
        r514.setAlpha((int) ((1.0f - r416.progressToNoVideoStub) * r11));
        this.rightShadowDrawable.setAlpha((int) (r11 * (1.0f - this.fullscreenTextureView.progressToNoVideoStub)));
        goto L107
    L106:
        int r417 = (int) (255.0f * r26);
        r514.setAlpha(r417);
        this.rightShadowDrawable.setAlpha(r417);
        goto L107
    L28:
        if (r24.getParent() == null) goto L59;
        if (this.fullscreenTextureView.getAlpha() == 1.0f) goto L32;
        RectF r214 = AndroidUtilities.rectTmp;
        r214.set(this.fullscreenTextureView.getX(), this.fullscreenTextureView.getY(), this.fullscreenTextureView.getX() + this.fullscreenTextureView.getMeasuredWidth(), this.fullscreenTextureView.getY() + this.fullscreenTextureView.getMeasuredHeight());
        r22.saveLayerAlpha(r214, (int) (this.fullscreenTextureView.getAlpha() * 255.0f), 31);
    L34:
        if (this.swipeToBackGesture == false) goto L36;
    L39:
        boolean r215 = true;
    L40:
        if (r215 == true) goto L42;
    L51:
        r22.translate(this.fullscreenTextureView.getX(), this.fullscreenTextureView.getY());
        this.fullscreenTextureView.setSwipeToBack(r215, this.swipeToBackDy);
        GroupCallMiniTextureView r142 = this.fullscreenTextureView;
        if (this.zoomStarted == false) goto L54;
    L57:
        boolean r15 = true;
    L58:
        r142.setZoom(r15, this.pinchScale, this.pinchCenterX, this.pinchCenterY, this.pinchTranslationX, this.pinchTranslationY);
        this.fullscreenTextureView.draw(r22);
        r22.restore();
        goto L59
    L54:
        if (this.zoomBackAnimator != null) goto L57;
        r15 = false;
        goto L58
    L42:
        if (isRtmpStream() == true) goto L51;
        int r33 = getMeasuredWidth();
        int r418 = getMeasuredHeight();
        if (GroupCallActivity.isLandscapeMode == false) goto L46;
    L49:
        int r515 = 0;
    L50:
        r22.clipRect(0, 0, r33, r418 - r515);
        goto L51
    L46:
        if (GroupCallActivity.isTabletMode == true) goto L49;
        r515 = AndroidUtilities.dp(90.0f);
        goto L50
    L36:
        if (this.swipeToBackAnimator != null) goto L39;
        r215 = false;
        goto L40
    L32:
        r22.save();
        goto L34
    L23:
        if (r23.getParent() == null) goto L25;
        r22.save();
        r22.translate(this.outFullscreenTextureView.getX(), this.outFullscreenTextureView.getY());
        this.outFullscreenTextureView.draw(r22);
        r22.restore();
    L18:
        if (r5 == false) goto L20;
        r22.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        goto L20
    L8:
        if (this.fullscreenTextureView == null) goto L60;
        goto L9
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        if (this.drawFirst == true) goto L5;
        UndoView[] r0 = this.undoView;
        if (r8 != r0[0]) goto L14;
    L46:
        return true;
    L14:
        if (r8 == r0[1]) goto L46;
        if ((r8 instanceof GroupCallMiniTextureView) == false) goto L42;
        GroupCallMiniTextureView r02 = (GroupCallMiniTextureView) r8;
        if (r02 != this.fullscreenTextureView) goto L21;
    L40:
        return true;
    L21:
        if (r02 == this.outFullscreenTextureView) goto L40;
        if (this.notDrawRenderes == true) goto L40;
        if (r02.drawFirst == true) goto L40;
        if (r02.primaryView == null) goto L35;
        float r2 = this.listView.getY() - getTop();
        float r3 = (this.listView.getMeasuredHeight() + r2) - this.listView.getTranslationY();
        float r4 = this.progressToFullscreenMode;
        if (r02.secondaryView != null) goto L32;
        r4 = 0.0f;
    L32:
        r7.save();
        float r03 = 1.0f - r4;
        r7.clipRect(0.0f, r2 * r03, getMeasuredWidth(), (r3 * r03) + (getMeasuredHeight() * r4));
        boolean r6 = super.drawChild(r7, r8, r9);
        r7.restore();
        return r6;
    L35:
        if (GroupCallActivity.isTabletMode == false) goto L39;
        r7.save();
        r7.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        boolean r62 = super.drawChild(r7, r8, r9);
        r7.restore();
        return r62;
    L39:
        return super.drawChild(r7, r8, r9);
    L42:
        if (this.drawRenderesOnly == false) goto L45;
        return true;
    L45:
        return super.drawChild(r7, r8, r9);
    L5:
        if ((r8 instanceof GroupCallMiniTextureView) == true) goto L7;
    L10:
        return true;
    L7:
        if (((GroupCallMiniTextureView) r8).drawFirst == false) goto L10;
        float r04 = this.listView.getY() - getTop();
        float r22 = (this.listView.getMeasuredHeight() + r04) - this.listView.getTranslationY();
        r7.save();
        r7.clipRect(0.0f, r04, getMeasuredWidth(), r22);
        boolean r63 = super.drawChild(r7, r8, r9);
        r7.restore();
        return r63;
    }

    public UndoView getUndoView() {
        if (this.undoView[0].getVisibility() != 0) goto L6;
        UndoView[] r0 = this.undoView;
        UndoView r2 = r0[0];
        r0[0] = r0[1];
        r0[1] = r2;
        r2.hide(true, 2);
        removeView(this.undoView[0]);
        addView(this.undoView[0]);
    L6:
        return this.undoView[0];
    }

    public boolean isAnimating() {
        if (this.fullscreenAnimator == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isUiVisible() {
        return this.uiVisible;
    }

    public boolean isVisible(TLRPC.GroupCallParticipant r3) {
        long r0 = MessageObject.getPeerId(r3.peer);
        if (this.attachedPeerIds.get(r0) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public abstract void onBackPressed();

    public abstract void onFullScreenModeChanged(boolean r1);

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return onTouchEvent(r1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if (GroupCallActivity.isTabletMode == false) goto L5;
        ((ViewGroup.MarginLayoutParams) this.topShadowView.getLayoutParams()).rightMargin = AndroidUtilities.dp(328.0f);
    L13:
        View r0 = this.rightShadowView;
        if (GroupCallActivity.isLandscapeMode == true) goto L16;
    L18:
        int r2 = 8;
    L19:
        r0.setVisibility(r2);
        this.pinContainer.getLayoutParams().height = AndroidUtilities.dp(40.0f);
        this.pinTextView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), 0), r6);
        this.unpinTextView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), 0), r6);
        ViewGroup.LayoutParams r02 = this.pinContainer.getLayoutParams();
        int r22 = AndroidUtilities.dp(46.0f);
        if (this.hasPinnedVideo == true) goto L23;
        TextView r3 = this.pinTextView;
    L24:
        r02.width = r22 + r3.getMeasuredWidth();
        ViewGroup.MarginLayoutParams r03 = (ViewGroup.MarginLayoutParams) this.speakingMembersToast.getLayoutParams();
        if (GroupCallActivity.isLandscapeMode == false) goto L27;
        int r23 = AndroidUtilities.dp(45.0f);
    L28:
        r03.rightMargin = r23;
        int r04 = 0;
    L30:
        if (r04 >= 2) goto L40;
        ViewGroup.MarginLayoutParams r24 = (ViewGroup.MarginLayoutParams) this.undoView[r04].getLayoutParams();
        if (this.isTablet == false) goto L35;
        r24.rightMargin = AndroidUtilities.dp(344.0f);
    L39:
        r04 = r04 + 1;
        goto L30
    L35:
        if (GroupCallActivity.isLandscapeMode == false) goto L37;
        int r32 = AndroidUtilities.dp(180.0f);
    L38:
        r24.rightMargin = r32;
        goto L39
    L37:
        r32 = 0;
        goto L38
    L40:
        super.onMeasure(r5, r6);
        return;
    L27:
        r23 = 0;
        goto L28
    L23:
        r3 = this.unpinTextView;
        goto L24
    L16:
        if (GroupCallActivity.isTabletMode == true) goto L18;
        r2 = 0;
        goto L19
    L5:
        boolean r05 = GroupCallActivity.isLandscapeMode;
        View r25 = this.topShadowView;
        if (r05 == false) goto L12;
        ViewGroup.MarginLayoutParams r06 = (ViewGroup.MarginLayoutParams) r25.getLayoutParams();
        if (isRtmpStream() == false) goto L10;
        int r26 = 0;
    L11:
        r06.rightMargin = r26;
        goto L13
    L10:
        r26 = AndroidUtilities.dp(90.0f);
        goto L11
    L12:
        ((ViewGroup.MarginLayoutParams) r25.getLayoutParams()).rightMargin = 0;
        goto L13
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        if (this.maybeSwipeToBackGesture == true) goto L7;
        if (this.swipeToBackGesture == true) goto L7;
    L20:
        if (this.inFullscreenMode == true) goto L22;
    L202:
        finishZoom();
        return false;
    L22:
        if (this.maybeSwipeToBackGesture == true) goto L36;
        if (this.swipeToBackGesture == true) goto L36;
        if (this.tapGesture == true) goto L36;
        if (this.canZoomGesture == true) goto L36;
        if (this.isInPinchToZoomTouchMode == true) goto L36;
        if (this.zoomStarted == true) goto L36;
        if (r14.getActionMasked() != 0) goto L202;
    L36:
        if (this.fullscreenTextureView == null) goto L202;
        if (r14.getActionMasked() != 0) goto L42;
        this.maybeSwipeToBackGesture = false;
        this.swipeToBackGesture = false;
        this.canZoomGesture = false;
        this.isInPinchToZoomTouchMode = false;
        this.zoomStarted = false;
    L42:
        if (r14.getActionMasked() != 0) goto L47;
        if (this.swipeToBackAnimator == null) goto L47;
        this.maybeSwipeToBackGesture = false;
        this.swipeToBackGesture = true;
        this.tapY = r14.getY() - this.swipeToBackDy;
        this.swipeToBackAnimator.removeAllListeners();
        this.swipeToBackAnimator.cancel();
        this.swipeToBackAnimator = null;
    L51:
        if (this.fullscreenTextureView.isInsideStopScreenButton(r14.getX(), r14.getY()) == false) goto L54;
        return false;
    L54:
        if (r14.getActionMasked() != 0) goto L73;
        if (this.swipeToBackGesture == true) goto L73;
        RectF r0 = AndroidUtilities.rectTmp;
        float r7 = ActionBar.getCurrentActionBarHeight();
        int r8 = this.fullscreenTextureView.getMeasuredWidth();
        if (GroupCallActivity.isLandscapeMode == true) goto L60;
    L62:
        int r9 = 0;
    L63:
        float r82 = r8 + r9;
        int r92 = this.fullscreenTextureView.getMeasuredHeight();
        if (GroupCallActivity.isLandscapeMode == false) goto L66;
    L68:
        int r10 = 0;
    L69:
        r0.set(0.0f, r7, r82, r92 + r10);
        if (r0.contains(r14.getX(), r14.getY()) == false) goto L101;
        this.tapTime = System.currentTimeMillis();
        this.tapGesture = true;
        this.maybeSwipeToBackGesture = true;
        this.tapX = r14.getX();
        this.tapY = r14.getY();
    L101:
        if (this.tapGesture == false) goto L126;
        if (r14.getActionMasked() != 1) goto L126;
        if ((System.currentTimeMillis() - this.tapTime) >= 200) goto L126;
        this.tapGesture = false;
        if (this.showSpeakingMembersToast == false) goto L121;
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(this.speakingMembersToast.getX(), this.speakingMembersToast.getY(), this.speakingMembersToast.getX() + this.speakingMembersToast.getWidth(), this.speakingMembersToast.getY() + this.speakingMembersToast.getHeight());
        if (this.call == null) goto L121;
        if (r02.contains(r14.getX(), r14.getY()) == false) goto L121;
        int r03 = 0;
        boolean r72 = false;
        boolean r83 = false;
    L114:
        if (r03 >= this.call.visibleVideoParticipants.size()) goto L119;
        if (this.speakingToastPeerId != MessageObject.getPeerId(this.call.visibleVideoParticipants.get(r03).participant.peer)) goto L118;
        this.groupCallActivity.fullscreenFor(this.call.visibleVideoParticipants.get(r03));
        r72 = true;
        r83 = true;
    L118:
        r03 = r03 + 1;
        goto L114
    L119:
        if (r72 == true) goto L122;
        this.groupCallActivity.fullscreenFor(new ChatObject.VideoParticipant(this.call.participants.get(this.speakingToastPeerId), false, false));
        r83 = true;
    L122:
        if (r83 == true) goto L124;
        setUiVisible(!this.uiVisible);
    L124:
        this.swipeToBackDy = 0.0f;
        invalidate();
    L121:
        r83 = false;
    L126:
        if (this.fullscreenTextureView.hasVideo == true) goto L128;
    L193:
        finishZoom();
        if (this.tapGesture == false) goto L196;
    L201:
        return true;
    L196:
        if (this.swipeToBackGesture == true) goto L201;
        if (this.maybeSwipeToBackGesture == true) goto L201;
        return false;
    L128:
        if (this.swipeToBackGesture == true) goto L193;
        if (r14.getActionMasked() == 0) goto L170;
        if (r14.getActionMasked() == 5) goto L170;
        if (r14.getActionMasked() != 2) goto L161;
        if (this.isInPinchToZoomTouchMode == false) goto L161;
        int r2 = -1;
        int r5 = -1;
        int r1 = 0;
    L141:
        if (r1 >= r14.getPointerCount()) goto L149;
        if (this.pointerId1 != r14.getPointerId(r1)) goto L146;
        r2 = r1;
    L146:
        if (this.pointerId2 != r14.getPointerId(r1)) goto L148;
        r5 = r1;
    L148:
        r1 = r1 + 1;
        goto L141
    L149:
        if (r2 == (-1)) goto L158;
        if (r5 == (-1)) goto L158;
        float r04 = ((float) Math.hypot(r14.getX(r5) - r14.getX(r2), r14.getY(r5) - r14.getY(r2))) / this.pinchStartDistance;
        this.pinchScale = r04;
        if (r04 > 1.005f) goto L155;
    L157:
        float r05 = (r14.getX(r2) + r14.getX(r5)) / 2.0f;
        float r12 = (r14.getY(r2) + r14.getY(r5)) / 2.0f;
        float r142 = this.pinchStartCenterX - r05;
        float r06 = this.pinchStartCenterY - r12;
        float r13 = this.pinchScale;
        this.pinchTranslationX = (-r142) / r13;
        this.pinchTranslationY = (-r06) / r13;
        invalidate();
    L185:
        if (this.canZoomGesture == false) goto L187;
    L192:
        return true;
    L187:
        if (this.tapGesture == true) goto L192;
        if (this.maybeSwipeToBackGesture == true) goto L192;
        return false;
    L155:
        if (this.zoomStarted == true) goto L157;
        this.pinchStartDistance = (float) Math.hypot(r14.getX(r5) - r14.getX(r2), r14.getY(r5) - r14.getY(r2));
        float r07 = (r14.getX(r2) + r14.getX(r5)) / 2.0f;
        this.pinchCenterX = r07;
        this.pinchStartCenterX = r07;
        float r08 = (r14.getY(r2) + r14.getY(r5)) / 2.0f;
        this.pinchCenterY = r08;
        this.pinchStartCenterY = r08;
        this.pinchScale = 1.0f;
        this.pinchTranslationX = 0.0f;
        this.pinchTranslationY = 0.0f;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.zoomStarted = true;
        this.isInPinchToZoomTouchMode = true;
    L158:
        getParent().requestDisallowInterceptTouchEvent(false);
        finishZoom();
        return this.maybeSwipeToBackGesture;
    L161:
        if (r14.getActionMasked() != 1) goto L163;
    L168:
        getParent().requestDisallowInterceptTouchEvent(false);
        finishZoom();
        goto L185
    L163:
        if (r14.getActionMasked() != 6) goto L167;
        if (checkPointerIds(r14) == true) goto L168;
    L167:
        if (r14.getActionMasked() != 3) goto L185;
    L170:
        if (r14.getActionMasked() != 0) goto L180;
        TextureViewRenderer r09 = this.fullscreenTextureView.textureView.renderer;
        RectF r22 = AndroidUtilities.rectTmp;
        r22.set(r09.getX(), r09.getY(), r09.getX() + r09.getMeasuredWidth(), r09.getY() + r09.getMeasuredHeight());
        r22.inset(((r09.getMeasuredHeight() * this.fullscreenTextureView.textureView.scaleTextureToFill) - r09.getMeasuredHeight()) / 2.0f, ((r09.getMeasuredWidth() * this.fullscreenTextureView.textureView.scaleTextureToFill) - r09.getMeasuredWidth()) / 2.0f);
        if (GroupCallActivity.isLandscapeMode == true) goto L174;
        r22.top = Math.max(r22.top, ActionBar.getCurrentActionBarHeight());
        r22.bottom = Math.min(r22.bottom, this.fullscreenTextureView.getMeasuredHeight() - AndroidUtilities.dp(90.0f));
    L175:
        boolean r010 = r22.contains(r14.getX(), r14.getY());
        this.canZoomGesture = r010;
        if (r010 == true) goto L180;
        finishZoom();
        return this.maybeSwipeToBackGesture;
    L174:
        r22.top = Math.max(r22.top, ActionBar.getCurrentActionBarHeight());
        r22.right = Math.min(r22.right, this.fullscreenTextureView.getMeasuredWidth() - AndroidUtilities.dp(90.0f));
    L180:
        if (this.isInPinchToZoomTouchMode == true) goto L185;
        if (r14.getPointerCount() != 2) goto L185;
        this.pinchStartDistance = (float) Math.hypot(r14.getX(1) - r14.getX(0), r14.getY(1) - r14.getY(0));
        float r011 = (r14.getX(0) + r14.getX(1)) / 2.0f;
        this.pinchCenterX = r011;
        this.pinchStartCenterX = r011;
        float r012 = (r14.getY(0) + r14.getY(1)) / 2.0f;
        this.pinchCenterY = r012;
        this.pinchStartCenterY = r012;
        this.pinchScale = 1.0f;
        this.pointerId1 = r14.getPointerId(0);
        this.pointerId2 = r14.getPointerId(1);
        this.isInPinchToZoomTouchMode = true;
        goto L185
    L66:
        if (this.uiVisible == false) goto L68;
        r10 = -AndroidUtilities.dp(90.0f);
        goto L69
    L60:
        if (this.uiVisible == false) goto L62;
        r9 = -AndroidUtilities.dp(90.0f);
    L73:
        if (this.maybeSwipeToBackGesture == true) goto L79;
        if (this.swipeToBackGesture == true) goto L79;
        if (this.tapGesture == false) goto L101;
    L79:
        if (r14.getActionMasked() != 2) goto L101;
        if (Math.abs(this.tapX - r14.getX()) <= this.touchSlop) goto L83;
    L84:
        this.tapGesture = false;
    L86:
        if (this.maybeSwipeToBackGesture == false) goto L93;
        if (this.zoomStarted == true) goto L93;
        if (Math.abs(this.tapY - r14.getY()) <= (this.touchSlop * 2)) goto L93;
        this.tapY = r14.getY();
        this.maybeSwipeToBackGesture = false;
        this.swipeToBackGesture = true;
    L96:
        if (this.maybeSwipeToBackGesture == false) goto L101;
        if (Math.abs(this.tapX - r14.getX()) <= (this.touchSlop * 4)) goto L101;
        this.maybeSwipeToBackGesture = false;
    L93:
        if (this.swipeToBackGesture == false) goto L96;
        this.swipeToBackDy = r14.getY() - this.tapY;
        invalidate();
        goto L96
    L83:
        if (Math.abs(this.tapY - r14.getY()) <= this.touchSlop) goto L86;
    L47:
        if (this.swipeToBackAnimator == null) goto L51;
        finishZoom();
        return false;
    L7:
        if (r14.getActionMasked() != 1) goto L9;
    L10:
        this.maybeSwipeToBackGesture = false;
        if (this.swipeToBackGesture == true) goto L13;
    L18:
        invalidate();
        goto L20
    L13:
        if (r14.getActionMasked() == 1) goto L15;
    L17:
        animateSwipeToBack(false);
        goto L18
    L15:
        if (Math.abs(this.swipeToBackDy) <= AndroidUtilities.dp(120.0f)) goto L17;
        this.groupCallActivity.fullscreenFor(null);
        goto L18
    L9:
        if (r14.getActionMasked() != 3) goto L20;
        goto L10
    }

    public abstract void onUiVisibilityChanged();

    public void requestFullscreen(ChatObject.VideoParticipant r17) {
        if (r17 == null) goto L5;
    L6:
        if (r17 != null) goto L8;
    L10:
        if (r17 != null) goto L12;
        long r2 = 0;
    L13:
        GroupCallMiniTextureView r4 = this.fullscreenTextureView;
        if (r4 == null) goto L16;
        r4.runDelayedAnimations();
    L16:
        ValueAnimator r42 = this.replaceFullscreenViewAnimator;
        if (r42 == null) goto L19;
        r42.cancel();
    L19:
        VoIPService r43 = VoIPService.getSharedInstance();
        boolean r5 = false;
        if (r43 == null) goto L24;
        ChatObject.VideoParticipant r6 = this.fullscreenParticipant;
        if (r6 == null) goto L24;
        r43.requestFullScreen(r6.participant, false, r6.presentation);
    L24:
        this.fullscreenParticipant = r17;
        if (r43 == null) goto L28;
        if (r17 == null) goto L28;
        r43.requestFullScreen(r17.participant, true, r17.presentation);
    L28:
        this.fullscreenPeerId = r2;
        boolean r22 = this.inFullscreenMode;
        this.lastUpdateTime = System.currentTimeMillis();
        GroupCallMiniTextureView r44 = null;
        float r9 = 1.0f;
        if (r17 == null) goto L31;
        int r11 = 0;
    L57:
        if (r11 >= this.attachedRenderers.size()) goto L62;
        if (this.attachedRenderers.get(r11).participant.equals(r17) == true) goto L60;
        r11 = r11 + 1;
        goto L57
    L60:
        GroupCallMiniTextureView r112 = this.attachedRenderers.get(r11);
    L63:
        Property r12 = View.ALPHA;
        if (r112 == null) goto L102;
        ValueAnimator r1 = this.fullscreenAnimator;
        if (r1 == null) goto L69;
        r1.cancel();
    L69:
        if (this.inFullscreenMode == true) goto L71;
        this.inFullscreenMode = true;
        clearCurrentFullscreenTextureView();
        this.fullscreenTextureView = r112;
        r112.setShowingInFullscreen(true, true);
        invalidate();
        this.pinDrawable.setCrossOut(this.hasPinnedVideo, false);
    L129:
        this.backButton.setEnabled(true);
    L130:
        boolean r13 = this.inFullscreenMode;
        if (r22 == r13) goto L143;
        if (r13 == true) goto L136;
        setUiVisible(true);
        if (this.hideUiRunnableIsScheduled == false) goto L137;
        this.hideUiRunnableIsScheduled = false;
        AndroidUtilities.cancelRunOnUIThread(this.hideUiRunnable);
    L137:
        onFullScreenModeChanged(true);
        float r14 = this.progressToFullscreenMode;
        if (this.inFullscreenMode == true) goto L141;
        r9 = 0.0f;
    L141:
        ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{r14, r9});
        this.fullscreenAnimator = r15;
        r15.addUpdateListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda8(this));
        GroupCallMiniTextureView r16 = this.fullscreenTextureView;
        r16.animateToFullscreen = true;
        this.groupCallActivity.getCurrentAccount();
        this.swipedBack = this.swipeToBackGesture;
        this.notificationsLocker.lock();
        this.fullscreenAnimator.addListener(new AnonymousClass14(this, r16));
        this.fullscreenAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.fullscreenAnimator.setDuration(350);
        this.fullscreenTextureView.textureView.synchOrRunAnimation(this.fullscreenAnimator);
        goto L143
    L136:
        this.backButton.setVisibility(0);
        this.pinButton.setVisibility(0);
        this.unpinTextView.setVisibility(0);
        this.pinContainer.setVisibility(0);
    L143:
        if (this.fullscreenParticipant != null) goto L145;
        r5 = true;
    L145:
        animateSwipeToBack(r5);
        return;
    L71:
        this.hasPinnedVideo = false;
        this.pinDrawable.setCrossOut(false, false);
        this.fullscreenTextureView.forceDetach(false);
        r112.forceDetach(false);
        if (this.isTablet == true) goto L88;
        GroupCallMiniTextureView r18 = this.fullscreenTextureView;
        if (r18.primaryView == null) goto L76;
    L79:
        r44 = new GroupCallMiniTextureView(this, this.attachedRenderers, this.call, this.groupCallActivity);
        GroupCallMiniTextureView r19 = this.fullscreenTextureView;
        r44.setViews(r19.primaryView, r19.secondaryView, r19.tabletGridView);
        r44.setFullscreenMode(this.inFullscreenMode, false);
        r44.updateAttachState(false);
        GroupCallGridCell r110 = this.fullscreenTextureView.primaryView;
        if (r110 == null) goto L82;
        r110.setRenderer(r44);
    L82:
        GroupCallFullscreenAdapter.GroupCallUserCell r111 = this.fullscreenTextureView.secondaryView;
        if (r111 == null) goto L85;
        r111.setRenderer(r44);
    L85:
        GroupCallGridCell r113 = this.fullscreenTextureView.tabletGridView;
        if (r113 == null) goto L88;
        r113.setRenderer(r44);
        goto L88
    L76:
        if (r18.secondaryView != null) goto L79;
        if (r18.tabletGridView != null) goto L79;
    L88:
        GroupCallMiniTextureView r114 = new GroupCallMiniTextureView(this, this.attachedRenderers, this.call, this.groupCallActivity);
        r114.participant = r112.participant;
        r114.setViews(r112.primaryView, r112.secondaryView, r112.tabletGridView);
        r114.setFullscreenMode(this.inFullscreenMode, false);
        r114.updateAttachState(false);
        r114.textureView.renderer.setAlpha(1.0f);
        r114.textureView.blurRenderer.setAlpha(1.0f);
        GroupCallGridCell r7 = r112.primaryView;
        if (r7 == null) goto L91;
        r7.setRenderer(r114);
    L91:
        GroupCallFullscreenAdapter.GroupCallUserCell r72 = r112.secondaryView;
        if (r72 == null) goto L94;
        r72.setRenderer(r114);
    L94:
        GroupCallGridCell r73 = r112.tabletGridView;
        if (r73 == null) goto L97;
        r73.setRenderer(r114);
    L97:
        r114.animateEnter = true;
        r114.setAlpha(0.0f);
        this.outFullscreenTextureView = this.fullscreenTextureView;
        ObjectAnimator r74 = ObjectAnimator.ofFloat(r114, r12, new float[]{0.0f, 1.0f});
        this.replaceFullscreenViewAnimator = r74;
        r74.addListener(new AnonymousClass8(this, r114, r112));
        if (r44 == null) goto L100;
        r44.setAlpha(0.0f);
        r44.setScaleX(0.5f);
        r44.setScaleY(0.5f);
        r44.animateEnter = true;
    L100:
        r114.runOnFrameRendered(new GroupCallRenderersContainer$$ExternalSyntheticLambda5(this, r112, r44));
        clearCurrentFullscreenTextureView();
        this.fullscreenTextureView = r114;
        r114.setShowingInFullscreen(true, false);
        update();
        goto L129
    L102:
        if (this.inFullscreenMode == false) goto L128;
        GroupCallMiniTextureView r45 = this.fullscreenTextureView;
        if (r45.primaryView == null) goto L106;
    L117:
        r45.forceDetach(false);
        GroupCallMiniTextureView r46 = new GroupCallMiniTextureView(this, this.attachedRenderers, this.call, this.groupCallActivity);
        GroupCallMiniTextureView r75 = this.fullscreenTextureView;
        r46.setViews(r75.primaryView, r75.secondaryView, r75.tabletGridView);
        r46.setFullscreenMode(this.inFullscreenMode, false);
        r46.updateAttachState(false);
        GroupCallGridCell r76 = this.fullscreenTextureView.primaryView;
        if (r76 == null) goto L120;
        r76.setRenderer(r46);
    L120:
        GroupCallFullscreenAdapter.GroupCallUserCell r77 = this.fullscreenTextureView.secondaryView;
        if (r77 == null) goto L123;
        r77.setRenderer(r46);
    L123:
        GroupCallGridCell r78 = this.fullscreenTextureView.tabletGridView;
        if (r78 == null) goto L126;
        r78.setRenderer(r46);
    L126:
        r46.setAlpha(0.0f);
        r46.setScaleX(0.5f);
        r46.setScaleY(0.5f);
        r46.animateEnter = true;
        r46.runOnFrameRendered(new GroupCallRenderersContainer$$ExternalSyntheticLambda6(this, r46));
    L127:
        GroupCallMiniTextureView r47 = new GroupCallMiniTextureView(this, this.attachedRenderers, this.call, this.groupCallActivity);
        r47.participant = r17;
        r47.setFullscreenMode(this.inFullscreenMode, false);
        r47.setShowingInFullscreen(true, false);
        r47.animateEnter = true;
        r47.setAlpha(0.0f);
        this.outFullscreenTextureView = this.fullscreenTextureView;
        ValueAnimator r115 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.replaceFullscreenViewAnimator = r115;
        r115.addUpdateListener(new GroupCallRenderersContainer$$ExternalSyntheticLambda7(this, r47));
        this.replaceFullscreenViewAnimator.addListener(new AnonymousClass12(this, r47));
        this.replaceFullscreenViewAnimator.start();
        clearCurrentFullscreenTextureView();
        this.fullscreenTextureView = r47;
        r47.setShowingInFullscreen(true, false);
        this.fullscreenTextureView.updateAttachState(false);
        update();
        goto L129
    L106:
        if (r45.secondaryView == null) goto L108;
        boolean r79 = true;
    L110:
        if (r45.tabletGridView == null) goto L112;
        boolean r8 = true;
    L114:
        if ((r79 | r8) == true) goto L117;
        r45.forceDetach(true);
        goto L127
    L112:
        r8 = false;
        goto L114
    L108:
        r79 = false;
        goto L110
    L128:
        this.inFullscreenMode = true;
        clearCurrentFullscreenTextureView();
        GroupCallMiniTextureView r48 = new GroupCallMiniTextureView(this, this.attachedRenderers, this.call, this.groupCallActivity);
        this.fullscreenTextureView = r48;
        r48.participant = r17;
        r48.setFullscreenMode(this.inFullscreenMode, false);
        this.fullscreenTextureView.setShowingInFullscreen(true, false);
        this.fullscreenTextureView.setShowingInFullscreen(true, false);
        ObjectAnimator r116 = ObjectAnimator.ofFloat(this.fullscreenTextureView, r12, new float[]{0.0f, 1.0f});
        this.replaceFullscreenViewAnimator = r116;
        r116.addListener(new AnonymousClass13(this));
        this.replaceFullscreenViewAnimator.start();
        invalidate();
        this.pinDrawable.setCrossOut(this.hasPinnedVideo, false);
        goto L129
    L62:
        r112 = null;
        goto L63
    L31:
        if (this.inFullscreenMode == false) goto L54;
        ValueAnimator r117 = this.fullscreenAnimator;
        if (r117 == null) goto L35;
        r117.cancel();
    L35:
        this.inFullscreenMode = false;
        GroupCallMiniTextureView r118 = this.fullscreenTextureView;
        if (r118.primaryView == null) goto L38;
    L41:
        ChatObject.VideoParticipant r119 = r118.participant;
        if (ChatObject.Call.videoIsActive(r119.participant, r119.presentation, this.call) == true) goto L53;
    L43:
        this.fullscreenTextureView.forceDetach(true);
        GroupCallGridCell r120 = this.fullscreenTextureView.primaryView;
        if (r120 == null) goto L46;
        r120.setRenderer(null);
    L46:
        GroupCallFullscreenAdapter.GroupCallUserCell r121 = this.fullscreenTextureView.secondaryView;
        if (r121 == null) goto L49;
        r121.setRenderer(null);
    L49:
        GroupCallGridCell r122 = this.fullscreenTextureView.tabletGridView;
        if (r122 == null) goto L52;
        r122.setRenderer(null);
    L52:
        GroupCallMiniTextureView r123 = this.fullscreenTextureView;
        r123.animate().alpha(0.0f).setListener(new AnonymousClass7(this, r123)).setDuration(350).start();
        goto L54
    L53:
        this.fullscreenTextureView.setShowingInFullscreen(false, true);
        goto L54
    L38:
        if (r118.secondaryView != null) goto L41;
        if (r118.tabletGridView == null) goto L43;
    L54:
        this.backButton.setEnabled(false);
        this.hasPinnedVideo = false;
        goto L130
    L12:
        r2 = MessageObject.getPeerId(r17.participant.peer);
        goto L13
    L8:
        if (r17.equals(this.fullscreenParticipant) == false) goto L10;
        return;
    L5:
        if (this.fullscreenParticipant != null) goto L6;
    }

    public void setAmplitude(TLRPC.GroupCallParticipant r6, float r7) {
        int r0 = 0;
    L4:
        if (r0 >= this.attachedRenderers.size()) goto L9;
        if (MessageObject.getPeerId(this.attachedRenderers.get(r0).participant.participant.peer) != MessageObject.getPeerId(r6.peer)) goto L8;
        this.attachedRenderers.get(r0).setAmplitude(r7);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void setGroupCall(ChatObject.Call r1) {
        this.call = r1;
    }

    public void setIsTablet(boolean r4) {
        if (this.isTablet == r4) goto L22;
        this.isTablet = r4;
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.backButton.getLayoutParams();
        if (r4 == false) goto L7;
        int r1 = 85;
    L8:
        r0.gravity = r1;
        if (r4 == false) goto L11;
        int r2 = AndroidUtilities.dp(328.0f);
    L12:
        r0.rightMargin = r2;
        if (r4 == false) goto L15;
        int r42 = -AndroidUtilities.dp(8.0f);
    L16:
        r0.bottomMargin = r42;
        if (this.isTablet == false) goto L20;
        this.backButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.msg_calls_minimize));
        return;
    L20:
        BackDrawable r43 = new BackDrawable(false);
        r43.setColor(-1);
        this.backButton.setImageDrawable(r43);
        return;
    L15:
        r42 = 0;
        goto L16
    L11:
        r2 = 0;
        goto L12
    L7:
        r1 = 51;
        goto L8
    }

    public void setProgressToHideUi(float r2) {
        if (this.progressToHideUi == r2) goto L8;
        this.progressToHideUi = r2;
        invalidate();
        GroupCallMiniTextureView r1 = this.fullscreenTextureView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void setVisibleParticipant(boolean r19) {
        int r3 = 0;
        if (this.inFullscreenMode == false) goto L91;
        if (this.isTablet == true) goto L91;
        if (this.fullscreenParticipant == null) goto L91;
        if (this.fullscreenAnimator != null) goto L91;
        if (this.call == null) goto L91;
        int r1 = this.groupCallActivity.getCurrentAccount();
        long r6 = 500;
        if ((System.currentTimeMillis() - this.lastUpdateTooltipTime) < 500) goto L16;
        this.lastUpdateTooltipTime = System.currentTimeMillis();
        int r5 = 0;
        int r9 = 0;
        SpannableStringBuilder r8 = null;
    L21:
        if (r5 >= this.call.currentSpeakingPeers.size()) goto L57;
        TLRPC.GroupCallParticipant r10 = this.call.currentSpeakingPeers.get(this.call.currentSpeakingPeers.keyAt(r5));
        if (r10.self == false) goto L25;
    L28:
        long r16 = r6;
    L55:
        r5 = r5 + 1;
        r6 = r16;
        goto L21
    L25:
        if (r10.muted_by_you == true) goto L28;
        if (MessageObject.getPeerId(this.fullscreenParticipant.participant.peer) == MessageObject.getPeerId(r10.peer)) goto L28;
        long r12 = MessageObject.getPeerId(r10.peer);
        r16 = r6;
        if ((SystemClock.uptimeMillis() - r10.lastSpeakTime) >= r16) goto L55;
        if (r8 != null) goto L33;
        r8 = new SpannableStringBuilder();
    L33:
        if (r9 != 0) goto L35;
        this.speakingToastPeerId = MessageObject.getPeerId(r10.peer);
    L35:
        if (r9 < 3) goto L37;
    L52:
        r9 = r9 + 1;
        if (r9 != 3) goto L55;
    L37:
        if (r12 <= 0) goto L39;
        TLRPC.User r7 = MessagesController.getInstance(r1).getUser(Long.valueOf(r12));
    L40:
        if (r12 > 0) goto L42;
        TLRPC.Chat r62 = MessagesController.getInstance(r1).getChat(Long.valueOf(r12));
    L43:
        if (r7 != null) goto L46;
        if (r62 == null) goto L55;
    L46:
        this.speakingMembersAvatars.setObject(r9, r1, r10);
        if (r9 == 0) goto L49;
        r8.append(", ");
    L49:
        if (r7 == null) goto L51;
        r8.append(UserObject.getFirstName(r7), new TypefaceSpan(AndroidUtilities.bold()), 0);
        goto L52
    L51:
        r8.append(r62.title, new TypefaceSpan(AndroidUtilities.bold()), 0);
        goto L52
    L42:
        r62 = null;
        goto L43
    L39:
        r7 = null;
    L57:
        if (r9 != 0) goto L59;
        boolean r63 = false;
    L60:
        boolean r72 = this.showSpeakingMembersToast;
        if (r72 == true) goto L64;
        if (r63 == false) goto L64;
        boolean r2 = false;
    L72:
        if (r63 == true) goto L75;
        this.showSpeakingMembersToast = r63;
        invalidate();
        return;
    L75:
        String r73 = LocaleController.getPluralString("MembersAreSpeakingToast", r9);
        int r102 = r73.indexOf("un1");
        SpannableStringBuilder r122 = new SpannableStringBuilder(r73);
        r122.replace(r102, r102 + 3, r8);
        this.speakingMembersText.setText(r122);
        if (r9 == 0) goto L84;
        if (r9 != 1) goto L81;
        r3 = AndroidUtilities.dp(40.0f);
        goto L84
    L81:
        if (r9 != 2) goto L83;
        r3 = AndroidUtilities.dp(64.0f);
        goto L84
    L83:
        r3 = AndroidUtilities.dp(88.0f);
    L84:
        ((FrameLayout.LayoutParams) this.speakingMembersText.getLayoutParams()).leftMargin = r3;
        ((FrameLayout.LayoutParams) this.speakingMembersText.getLayoutParams()).rightMargin = AndroidUtilities.dp(16.0f);
        this.showSpeakingMembersToast = r63;
        invalidate();
    L85:
        AvatarsImageView r32 = this.speakingMembersAvatars;
        if (r9 >= 3) goto L88;
        r32.setObject(r9, r1, null);
        r9 = r9 + 1;
        goto L85
    L88:
        r32.commitTransition(r2);
        return;
    L64:
        if (r63 == true) goto L68;
        if (r72 == false) goto L68;
        this.showSpeakingMembersToast = r63;
        invalidate();
        return;
    L68:
        if (r72 == false) goto L71;
        if (r63 == false) goto L71;
        this.speakingMembersToastFromLeft = this.speakingMembersToast.getLeft();
        this.speakingMembersToastFromRight = this.speakingMembersToast.getRight();
        this.speakingMembersToastFromTextLeft = this.speakingMembersText.getLeft();
        this.speakingMembersToastChangeProgress = 0.0f;
    L71:
        r2 = r19;
        goto L72
    L59:
        r63 = true;
        goto L60
    L16:
        if (this.updateTooltipRunnbale != null) goto L101;
        GroupCallRenderersContainer$$ExternalSyntheticLambda9 r13 = new GroupCallRenderersContainer$$ExternalSyntheticLambda9(this);
        this.updateTooltipRunnbale = r13;
        AndroidUtilities.runOnUIThread(r13, (System.currentTimeMillis() - this.lastUpdateTooltipTime) + 50);
        return;
    L101:
        return;
    L91:
        if (this.showSpeakingMembersToast == false) goto L102;
        this.showSpeakingMembersToast = false;
        this.showSpeakingMembersToastProgress = 0.0f;
        return;
    }

    public void update() {
        invalidate();
    }
}
