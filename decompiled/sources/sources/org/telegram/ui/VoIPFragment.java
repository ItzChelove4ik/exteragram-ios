package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.PowerManager;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.voip.EncryptionKeyEmojifier;
import org.telegram.messenger.voip.Instance;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.messenger.voip.VoIPServiceState;
import org.telegram.messenger.voip.VoipAudioManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.DarkAlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.AvatarsDrawable;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.voip.AcceptDeclineView;
import org.telegram.ui.Components.voip.EmojiRationalLayout;
import org.telegram.ui.Components.voip.EndCloseLayout;
import org.telegram.ui.Components.voip.HideEmojiTextView;
import org.telegram.ui.Components.voip.ImageWithWavesView;
import org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew;
import org.telegram.ui.Components.voip.RateCallLayout;
import org.telegram.ui.Components.voip.VoIPBackgroundProvider;
import org.telegram.ui.Components.voip.VoIPButtonsLayout;
import org.telegram.ui.Components.voip.VoIPFloatingLayout;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Components.voip.VoIPNotificationsLayout;
import org.telegram.ui.Components.voip.VoIPPiPView;
import org.telegram.ui.Components.voip.VoIPStatusTextView;
import org.telegram.ui.Components.voip.VoIPTextureView;
import org.telegram.ui.Components.voip.VoIPToggleButton;
import org.telegram.ui.Components.voip.VoIPWindowView;
import org.telegram.ui.Components.voip.VoIpCoverView;
import org.telegram.ui.Components.voip.VoIpGradientLayout;
import org.telegram.ui.Components.voip.VoIpHintView;
import org.telegram.ui.Components.voip.VoIpSnowView;
import org.telegram.ui.Components.voip.VoIpSwitchLayout;
import org.telegram.ui.Stories.recorder.HintView2;
import org.webrtc.EglBase;
import org.webrtc.GlRectDrawer;
import org.webrtc.MediaStreamTrack;
import org.webrtc.RendererCommon;
import org.webrtc.TextureViewRenderer;

/* loaded from: classes3.dex */
public class VoIPFragment implements VoIPService.StateListener, NotificationCenter.NotificationCenterDelegate, IPipSourceDelegate {
    private static VoIPFragment instance;
    private AcceptDeclineView acceptDeclineView;
    private AccessibilityManager accessibilityManager;
    Activity activity;
    private ImageView addIcon;
    private UserSelectorBottomSheet addPeopleSheet;
    private ImageView backIcon;
    private final VoIPBackgroundProvider backgroundProvider;
    private VoIPToggleButton bottomEndCallBtn;
    private VoIpSwitchLayout bottomMuteBtn;
    View bottomShadow;
    private VoIpSwitchLayout bottomSpeakerBtn;
    private VoIpSwitchLayout bottomVideoBtn;
    private VoIPButtonsLayout buttonsLayout;
    TLRPC.User callingUser;
    boolean callingUserIsVideo;
    private VoIPFloatingLayout callingUserMiniFloatingLayout;
    private TextureViewRenderer callingUserMiniTextureRenderer;
    private ImageWithWavesView callingUserPhotoViewMini;
    private VoIPTextureView callingUserTextureView;
    private TextView callingUserTitle;
    boolean cameraForceExpanded;
    private Animator cameraShowingAnimator;
    private boolean canHideUI;
    private boolean canSwitchToPip;
    private boolean canZoomGesture;
    private final int currentAccount;
    private int currentState;
    TLRPC.User currentUser;
    private VoIPFloatingLayout currentUserCameraFloatingLayout;
    private boolean currentUserCameraIsFullscreen;
    boolean currentUserIsVideo;
    private VoIPTextureView currentUserTextureView;
    private boolean deviceIsLocked;
    Drawable[] emojiDrawables;
    private boolean emojiExpanded;
    LinearLayout emojiLayout;
    private boolean emojiLoaded;
    LinearLayout emojiRationalLayout;
    TextView emojiRationalTextView;
    TextView emojiRationalTopTextView;
    BackupImageView[] emojiViews;
    HintView2 encryptionTooltip;
    EndCloseLayout endCloseLayout;
    boolean enterFromPiP;
    private float enterTransitionProgress;
    float fillNaviagtionBarValue;
    private Runnable firstFrameCallback;
    private ViewGroup fragmentView;
    private VoIpGradientLayout gradientLayout;
    FrameLayout hideEmojiLayout;
    TextView hideEmojiTextView;
    Runnable hideUIRunnable;
    boolean hideUiRunnableWaiting;
    private boolean isFinished;
    private boolean isInPinchToZoomTouchMode;
    private boolean isNearEar;
    boolean isOutgoing;
    private boolean isVideoCall;
    long lastContentTapTime;
    private WindowInsets lastInsets;
    private boolean lockOnScreen;
    VoIPNotificationsLayout notificationsLayout;
    AnimationNotificationsLocker notificationsLocker;
    Paint overlayBottomPaint;
    Paint overlayPaint;
    private ConferenceParticipantsView participantsView;
    private float pinchCenterX;
    private float pinchCenterY;
    float pinchScale;
    private float pinchStartCenterX;
    private float pinchStartCenterY;
    private float pinchStartDistance;
    private float pinchTranslationX;
    private float pinchTranslationY;
    private PipSource pipSource;
    private VoIPTextureView pipTextureView;
    private int pointerId1;
    private int pointerId2;
    private PrivateVideoPreviewDialogNew previewDialog;
    private int previousState;
    RateCallLayout rateCallLayout;
    private boolean screenWasWakeup;
    private int selectedRating;
    private boolean signalBarWasReceived;
    private ImageView speakerPhoneIcon;
    private int speakerPhoneIconResId;
    LinearLayout statusLayout;
    private int statusLayoutAnimateToOffset;
    private VoIPStatusTextView statusTextView;
    ValueAnimator.AnimatorUpdateListener statusbarAnimatorListener;
    Runnable stopAnimatingBgRunnable;
    private boolean switchingToPip;
    HintView2 tapToVideoTooltip;
    View topShadow;
    float touchSlop;
    float uiVisibilityAlpha;
    ValueAnimator uiVisibilityAnimator;
    private boolean uiVisible;
    private VoIpCoverView voIpCoverView;
    private VoIpSnowView voIpSnowView;
    private boolean wasEstablished;
    private VoIPWindowView windowView;
    private boolean windowViewSkipRender;
    ValueAnimator zoomBackAnimator;
    private boolean zoomStarted;

    /* renamed from: org.telegram.ui.VoIPFragment$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends VoIPWindowView {
        private final Path clipPath;
        private final RectF rectF;
        final /* synthetic */ VoIPFragment val$fragment;

        public AnonymousClass1(Activity r1, boolean r2, VoIPFragment r3) {
            this.val$fragment = r3;
            super(r1, r2);
            this.clipPath = new Path();
            this.rectF = new RectF();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (VoIPFragment.m21191$$Nest$fgetswitchingToPip(this.val$fragment) == true) goto L5;
        L8:
            super.dispatchDraw(r6);
            return;
        L5:
            if (getAlpha() == 0.0f) goto L8;
            float r0 = VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getWidth() * VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getScaleX();
            float r1 = VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getHeight() * VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getScaleY();
            float r3 = VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getX() + ((VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getWidth() - r0) / 2.0f);
            float r2 = VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getY() + ((VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.val$fragment).getHeight() - r1) / 2.0f);
            r6.save();
            this.clipPath.rewind();
            this.rectF.set(r3, r2, r0 + r3, r1 + r2);
            float r02 = AndroidUtilities.dp(4.0f);
            this.clipPath.addRoundRect(this.rectF, r02, r02, Path.Direction.CW);
            this.clipPath.close();
            r6.clipPath(this.clipPath);
            super.dispatchDraw(r6);
            r6.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r4) {
            if (VoIPFragment.m21178$$Nest$fgetisFinished(this.val$fragment) == false) goto L5;
            return false;
        L5:
            if (VoIPFragment.m21191$$Nest$fgetswitchingToPip(this.val$fragment) == true) goto L29;
            int r0 = r4.getKeyCode();
            if (r0 != 4) goto L16;
            if (r4.getAction() != 1) goto L16;
            if (VoIPFragment.m21181$$Nest$fgetlockOnScreen(this.val$fragment) == true) goto L16;
            VoIPFragment.m21217$$Nest$monBackPressed(this.val$fragment);
            return true;
        L16:
            if (r0 == 25) goto L20;
            if (r0 == 24) goto L20;
        L26:
            return super.dispatchKeyEvent(r4);
        L20:
            if (VoIPFragment.m21169$$Nest$fgetcurrentState(this.val$fragment) != 15) goto L26;
            VoIPServiceState r02 = VoIPService.getSharedState();
            if (r02 == null) goto L26;
            r02.stopRinging();
            return true;
        L29:
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            if (VoIPFragment.m21194$$Nest$fgetwindowViewSkipRender(this.val$fragment) == false) goto L5;
            return;
        L5:
            super.draw(r2);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;
        final /* synthetic */ Runnable val$after;

        public AnonymousClass10(VoIPFragment r1, Runnable r2) {
            this.this$0 = r1;
            this.val$after = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$after.run();
            VoIPFragment.m21159$$Nest$fgetacceptDeclineView(this.this$0).setScaleX(1.15f);
            VoIPFragment.m21159$$Nest$fgetacceptDeclineView(this.this$0).setScaleY(1.15f);
            ViewGroup.MarginLayoutParams r32 = (ViewGroup.MarginLayoutParams) VoIPFragment.m21159$$Nest$fgetacceptDeclineView(this.this$0).getLayoutParams();
            r32.leftMargin = AndroidUtilities.dp(10.0f);
            r32.rightMargin = AndroidUtilities.dp(10.0f);
            VoIPFragment.m21159$$Nest$fgetacceptDeclineView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass11(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIPFragment r22 = this.this$0;
            r22.zoomBackAnimator = null;
            r22.pinchScale = 1.0f;
            VoIPFragment.m21204$$Nest$fputpinchTranslationX(r22, 0.0f);
            VoIPFragment.m21205$$Nest$fputpinchTranslationY(this.this$0, 0.0f);
            VoIPFragment.m21176$$Nest$fgetfragmentView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 implements RendererCommon.RendererEvents {
        final /* synthetic */ VoIPFragment this$0;

        public static /* synthetic */ void $r8$lambda$FVbtLzmi6eckhp2IjlABezE5XMM(AnonymousClass12 r0) {
            r0.lambda$onFirstFrameRendered$0();
        }

        public AnonymousClass12(VoIPFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onFirstFrameRendered$0() {
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            AndroidUtilities.runOnUIThread(new VoIPFragment$12$$ExternalSyntheticLambda0(this));
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 implements RendererCommon.RendererEvents {
        final /* synthetic */ VoIPFragment this$0;

        public static /* synthetic */ void $r8$lambda$BLweqnYjemTMe97Ll7XZvDT7ZFs(AnonymousClass13 r0) {
            r0.lambda$onFirstFrameRendered$0();
        }

        public AnonymousClass13(VoIPFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onFirstFrameRendered$0() {
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            if (VoIPFragment.m21175$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L5;
            VoIPFragment.m21175$$Nest$fgetfirstFrameCallback(this.this$0).run();
            VoIPFragment.m21197$$Nest$fputfirstFrameCallback(this.this$0, null);
        L5:
            AndroidUtilities.runOnUIThread(new VoIPFragment$13$$ExternalSyntheticLambda0(this));
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public static /* synthetic */ void $r8$lambda$1nMXi60JRbj0B6XD_MddK_8oIbY(AnonymousClass14 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass14(VoIPFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            this.this$0.notificationsLocker.unlock();
            VoIPPiPView.getInstance().onTransitionEnd();
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setCornerRadius(-1.0f);
            VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.this$0).renderer.release();
            VoIPFragment.m21172$$Nest$fgetcurrentUserTextureView(this.this$0).renderer.release();
            VoIPFragment.m21162$$Nest$fgetcallingUserMiniTextureRenderer(this.this$0).release();
            VoIPFragment.m21213$$Nest$mdestroy(this.this$0);
            VoIPFragment.m21193$$Nest$fgetwindowView(this.this$0).finishImmediate();
            VoIPPiPView.switchingToPip = false;
            VoIPFragment.m21210$$Nest$fputswitchingToPip(this.this$0, false);
            VoIPFragment.m21223$$Nest$sfputinstance(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            VoIPPiPView.getInstance().windowView.setAlpha(1.0f);
            AndroidUtilities.runOnUIThread(new VoIPFragment$14$$ExternalSyntheticLambda0(this), 200);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass15(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.notificationsLocker.unlock();
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setCornerRadius(-1.0f);
            VoIPFragment.m21210$$Nest$fputswitchingToPip(this.this$0, false);
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).switchingToPip = false;
            VoIPFragment r22 = this.this$0;
            VoIPFragment.m21209$$Nest$fputpreviousState(r22, VoIPFragment.m21169$$Nest$fgetcurrentState(r22));
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass16(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            BackupImageView[] r4 = this.this$0.emojiViews;
            int r52 = r4.length;
            int r0 = 0;
        L3:
            if (r0 >= r52) goto L10;
            BackupImageView r1 = r4[r0];
            AnimatedEmojiDrawable r2 = r1.animatedEmojiDrawable;
            if (r2 == null) goto L9;
            if (r2.getImageReceiver() == null) goto L9;
            r1.animatedEmojiDrawable.getImageReceiver().setAllowStartAnimation(true);
            r1.animatedEmojiDrawable.getImageReceiver().startAnimation();
        L9:
            r0 = r0 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$17, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass17(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            VoIPFragment.m21221$$Nest$mstartWaitingFoHideUi(this.this$0);
            BackupImageView[] r62 = this.this$0.emojiViews;
            int r0 = r62.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L10;
            BackupImageView r3 = r62[r2];
            AnimatedEmojiDrawable r4 = r3.animatedEmojiDrawable;
            if (r4 == null) goto L9;
            if (r4.getImageReceiver() == null) goto L9;
            r3.animatedEmojiDrawable.getImageReceiver().setAllowStartAnimation(false);
            r3.animatedEmojiDrawable.getImageReceiver().stopAnimation();
        L9:
            r2 = r2 + 1;
            goto L3
        L10:
            this.this$0.emojiRationalLayout.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$18, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass18 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass18(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            VoIPFragment.m21165$$Nest$fgetcallingUserTitle(this.this$0).setText(LocaleController.getString(R.string.VoipCallEnded));
            VoIPFragment.m21165$$Nest$fgetcallingUserTitle(this.this$0).animate().alpha(1.0f).setDuration(70).setListener(null).start();
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$19, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass19(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (VoIPFragment.m21161$$Nest$fgetcallingUserMiniFloatingLayout(this.this$0).getTag() != null) goto L6;
            VoIPFragment.m21161$$Nest$fgetcallingUserMiniFloatingLayout(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        boolean check;
        long pressedTime;
        float pressedX;
        float pressedY;
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass2(VoIPFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            if (r5 != VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0)) goto L11;
            VoIPFragment r0 = this.this$0;
            if (r0.currentUserIsVideo == false) goto L7;
            return false;
        L7:
            if (r0.callingUserIsVideo == false) goto L11;
            return false;
        L11:
            if (r5 == VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0)) goto L19;
            if (r5 == VoIPFragment.m21164$$Nest$fgetcallingUserTextureView(this.this$0)) goto L19;
            if (r5 != VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0)) goto L24;
            if (VoIPFragment.m21171$$Nest$fgetcurrentUserCameraIsFullscreen(this.this$0) == true) goto L19;
        L24:
            return super.drawChild(r4, r5, r6);
        L19:
            if (VoIPFragment.m21195$$Nest$fgetzoomStarted(this.this$0) == false) goto L21;
        L25:
            r4.save();
            VoIPFragment r02 = this.this$0;
            float r1 = r02.pinchScale;
            r4.scale(r1, r1, VoIPFragment.m21182$$Nest$fgetpinchCenterX(r02), VoIPFragment.m21183$$Nest$fgetpinchCenterY(this.this$0));
            r4.translate(VoIPFragment.m21187$$Nest$fgetpinchTranslationX(this.this$0), VoIPFragment.m21188$$Nest$fgetpinchTranslationY(this.this$0));
            boolean r3 = super.drawChild(r4, r5, r6);
            r4.restore();
            return r3;
        L21:
            if (this.this$0.zoomBackAnimator == null) goto L24;
            goto L25
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (r4.getActionMasked() != 1) goto L8;
            VoIPFragment.m21163$$Nest$fgetcallingUserPhotoViewMini(this.this$0).setMute(false, false);
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).resume();
            AndroidUtilities.cancelRunOnUIThread(this.this$0.stopAnimatingBgRunnable);
            if (VoIPFragment.m21169$$Nest$fgetcurrentState(this.this$0) != 3) goto L8;
            AndroidUtilities.runOnUIThread(this.this$0.stopAnimatingBgRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        L8:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r14) {
            if (r14.getActionMasked() != 1) goto L8;
            VoIPFragment.m21163$$Nest$fgetcallingUserPhotoViewMini(this.this$0).setMute(false, false);
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).resume();
            AndroidUtilities.cancelRunOnUIThread(this.this$0.stopAnimatingBgRunnable);
            if (VoIPFragment.m21169$$Nest$fgetcurrentState(this.this$0) != 3) goto L8;
            AndroidUtilities.runOnUIThread(this.this$0.stopAnimatingBgRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        L8:
            if (VoIPFragment.m21167$$Nest$fgetcanZoomGesture(this.this$0) == true) goto L18;
            if (VoIPFragment.m21179$$Nest$fgetisInPinchToZoomTouchMode(this.this$0) == true) goto L18;
            if (VoIPFragment.m21195$$Nest$fgetzoomStarted(this.this$0) == true) goto L18;
            if (r14.getActionMasked() == 0) goto L18;
            VoIPFragment.m21215$$Nest$mfinishZoom(this.this$0);
            return false;
        L18:
            if (r14.getActionMasked() != 0) goto L20;
            VoIPFragment.m21196$$Nest$fputcanZoomGesture(this.this$0, false);
            VoIPFragment.m21198$$Nest$fputisInPinchToZoomTouchMode(this.this$0, false);
            VoIPFragment.m21211$$Nest$fputzoomStarted(this.this$0, false);
        L20:
            VoIPTextureView r0 = VoIPFragment.m21216$$Nest$mgetFullscreenTextureView(this.this$0);
            if (r14.getActionMasked() == 0) goto L59;
            if (r14.getActionMasked() == 5) goto L59;
            if (r14.getActionMasked() != 2) goto L50;
            if (VoIPFragment.m21179$$Nest$fgetisInPinchToZoomTouchMode(this.this$0) == false) goto L50;
            int r6 = -1;
            int r8 = -1;
            int r4 = 0;
        L31:
            if (r4 >= r14.getPointerCount()) goto L39;
            if (VoIPFragment.m21189$$Nest$fgetpointerId1(this.this$0) != r14.getPointerId(r4)) goto L36;
            r6 = r4;
        L36:
            if (VoIPFragment.m21190$$Nest$fgetpointerId2(this.this$0) != r14.getPointerId(r4)) goto L38;
            r8 = r4;
        L38:
            r4 = r4 + 1;
            goto L31
        L39:
            if (r6 == (-1)) goto L48;
            if (r8 == (-1)) goto L48;
            this.this$0.pinchScale = ((float) Math.hypot(r14.getX(r8) - r14.getX(r6), r14.getY(r8) - r14.getY(r6))) / VoIPFragment.m21186$$Nest$fgetpinchStartDistance(this.this$0);
            VoIPFragment r02 = this.this$0;
            if (r02.pinchScale > 1.005f) goto L45;
        L47:
            float r03 = (r14.getX(r6) + r14.getX(r8)) / 2.0f;
            float r42 = (r14.getY(r6) + r14.getY(r8)) / 2.0f;
            float r5 = VoIPFragment.m21184$$Nest$fgetpinchStartCenterX(this.this$0) - r03;
            float r04 = VoIPFragment.m21185$$Nest$fgetpinchStartCenterY(this.this$0) - r42;
            VoIPFragment r43 = this.this$0;
            VoIPFragment.m21204$$Nest$fputpinchTranslationX(r43, (-r5) / r43.pinchScale);
            VoIPFragment r44 = this.this$0;
            VoIPFragment.m21205$$Nest$fputpinchTranslationY(r44, (-r04) / r44.pinchScale);
            invalidate();
        L74:
            VoIPFragment.m21176$$Nest$fgetfragmentView(this.this$0).invalidate();
            int r05 = r14.getAction();
            if (r05 == 0) goto L102;
            if (r05 == 1) goto L81;
            if (r05 != 3) goto L104;
            this.check = false;
        L104:
            if (VoIPFragment.m21167$$Nest$fgetcanZoomGesture(this.this$0) == false) goto L106;
        L109:
            return true;
        L106:
            if (this.check == true) goto L109;
            return false;
        L81:
            if (this.check == false) goto L104;
            float r06 = r14.getX() - this.pressedX;
            float r142 = r14.getY() - this.pressedY;
            long r45 = System.currentTimeMillis();
            float r07 = (r06 * r06) + (r142 * r142);
            VoIPFragment r143 = this.this$0;
            float r1 = r143.touchSlop;
            if (r07 < (r1 * r1)) goto L85;
        L101:
            this.check = false;
            goto L104
        L85:
            if ((r45 - this.pressedTime) >= 300) goto L101;
            if ((r45 - r143.lastContentTapTime) <= 300) goto L101;
            r143.lastContentTapTime = System.currentTimeMillis();
            boolean r144 = VoIPFragment.m21173$$Nest$fgetemojiExpanded(this.this$0);
            VoIPFragment r08 = this.this$0;
            if (r144 == false) goto L92;
            VoIPFragment.m21214$$Nest$mexpandEmoji(r08, false);
            goto L101
        L92:
            if (VoIPFragment.m21166$$Nest$fgetcanHideUI(r08) == false) goto L101;
            VoIPFragment.m21220$$Nest$mshowUi(this.this$0, !VoIPFragment.m21192$$Nest$fgetuiVisible(r14));
            VoIPFragment r145 = this.this$0;
            VoIPFragment.m21209$$Nest$fputpreviousState(r145, VoIPFragment.m21169$$Nest$fgetcurrentState(r145));
            if (VoIPFragment.m21192$$Nest$fgetuiVisible(this.this$0) == true) goto L100;
            HintView2 r146 = this.this$0.tapToVideoTooltip;
            if (r146 == null) goto L100;
            if (r146.shown() == false) goto L100;
            this.this$0.tapToVideoTooltip.hide();
        L100:
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
            goto L101
        L102:
            this.pressedX = r14.getX();
            this.pressedY = r14.getY();
            this.check = true;
            this.pressedTime = System.currentTimeMillis();
            goto L104
        L45:
            if (VoIPFragment.m21195$$Nest$fgetzoomStarted(r02) == true) goto L47;
            VoIPFragment.m21203$$Nest$fputpinchStartDistance(this.this$0, (float) Math.hypot(r14.getX(r8) - r14.getX(r6), r14.getY(r8) - r14.getY(r6)));
            VoIPFragment r09 = this.this$0;
            float r46 = (r14.getX(r6) + r14.getX(r8)) / 2.0f;
            VoIPFragment.m21199$$Nest$fputpinchCenterX(r09, r46);
            VoIPFragment.m21201$$Nest$fputpinchStartCenterX(r09, r46);
            VoIPFragment r010 = this.this$0;
            float r47 = (r14.getY(r6) + r14.getY(r8)) / 2.0f;
            VoIPFragment.m21200$$Nest$fputpinchCenterY(r010, r47);
            VoIPFragment.m21202$$Nest$fputpinchStartCenterY(r010, r47);
            VoIPFragment r011 = this.this$0;
            r011.pinchScale = 1.0f;
            VoIPFragment.m21204$$Nest$fputpinchTranslationX(r011, 0.0f);
            VoIPFragment.m21205$$Nest$fputpinchTranslationY(this.this$0, 0.0f);
            getParent().requestDisallowInterceptTouchEvent(true);
            VoIPFragment.m21211$$Nest$fputzoomStarted(this.this$0, true);
            VoIPFragment.m21198$$Nest$fputisInPinchToZoomTouchMode(this.this$0, true);
        L48:
            getParent().requestDisallowInterceptTouchEvent(false);
            VoIPFragment.m21215$$Nest$mfinishZoom(this.this$0);
        L50:
            if (r14.getActionMasked() != 1) goto L52;
        L57:
            getParent().requestDisallowInterceptTouchEvent(false);
            VoIPFragment.m21215$$Nest$mfinishZoom(this.this$0);
            goto L74
        L52:
            if (r14.getActionMasked() != 6) goto L56;
            if (VoIPFragment.m21212$$Nest$mcheckPointerIds(this.this$0, r14) == true) goto L57;
        L56:
            if (r14.getActionMasked() != 3) goto L74;
        L59:
            if (r14.getActionMasked() != 0) goto L68;
            RectF r48 = AndroidUtilities.rectTmp;
            r48.set(r0.getX(), r0.getY(), r0.getX() + r0.getMeasuredWidth(), r0.getY() + r0.getMeasuredHeight());
            r48.inset(((r0.getMeasuredHeight() * r0.scaleTextureToFill) - r0.getMeasuredHeight()) / 2.0f, ((r0.getMeasuredWidth() * r0.scaleTextureToFill) - r0.getMeasuredWidth()) / 2.0f);
            if (GroupCallActivity.isLandscapeMode == true) goto L63;
            r48.top = Math.max(r48.top, ActionBar.getCurrentActionBarHeight());
            r48.bottom = Math.min(r48.bottom, r0.getMeasuredHeight() - AndroidUtilities.dp(90.0f));
        L64:
            VoIPFragment.m21196$$Nest$fputcanZoomGesture(this.this$0, r48.contains(r14.getX(), r14.getY()));
            if (VoIPFragment.m21167$$Nest$fgetcanZoomGesture(this.this$0) == true) goto L68;
            VoIPFragment.m21215$$Nest$mfinishZoom(this.this$0);
            goto L68
        L63:
            r48.top = Math.max(r48.top, ActionBar.getCurrentActionBarHeight());
            r48.right = Math.min(r48.right, r0.getMeasuredWidth() - AndroidUtilities.dp(90.0f));
        L68:
            if (VoIPFragment.m21167$$Nest$fgetcanZoomGesture(this.this$0) == false) goto L74;
            if (VoIPFragment.m21179$$Nest$fgetisInPinchToZoomTouchMode(this.this$0) == true) goto L74;
            if (r14.getPointerCount() != 2) goto L74;
            VoIPFragment.m21203$$Nest$fputpinchStartDistance(this.this$0, (float) Math.hypot(r14.getX(1) - r14.getX(0), r14.getY(1) - r14.getY(0)));
            VoIPFragment r012 = this.this$0;
            float r49 = (r14.getX(0) + r14.getX(1)) / 2.0f;
            VoIPFragment.m21199$$Nest$fputpinchCenterX(r012, r49);
            VoIPFragment.m21201$$Nest$fputpinchStartCenterX(r012, r49);
            VoIPFragment r013 = this.this$0;
            float r410 = (r14.getY(0) + r14.getY(1)) / 2.0f;
            VoIPFragment.m21200$$Nest$fputpinchCenterY(r013, r410);
            VoIPFragment.m21202$$Nest$fputpinchStartCenterY(r013, r410);
            VoIPFragment r014 = this.this$0;
            r014.pinchScale = 1.0f;
            VoIPFragment.m21206$$Nest$fputpointerId1(r014, r14.getPointerId(0));
            VoIPFragment.m21207$$Nest$fputpointerId2(this.this$0, r14.getPointerId(1));
            VoIPFragment.m21198$$Nest$fputisInPinchToZoomTouchMode(this.this$0, true);
            goto L74
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$20, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass20(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setTranslationX(0.0f);
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setTranslationY(0.0f);
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setScaleY(1.0f);
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setScaleX(1.0f);
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$21, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass21 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass21(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            VoIPFragment.m21163$$Nest$fgetcallingUserPhotoViewMini(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$22, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass22(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            VoIPFragment.m21159$$Nest$fgetacceptDeclineView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$23, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass23 extends Visibility {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass23(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r3, View r4, TransitionValues r5, TransitionValues r6) {
            ObjectAnimator r2 = ObjectAnimator.ofPropertyValuesHolder(r4, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(100.0f), 0.0f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.0f, 1.0f})});
            if ((r4 instanceof VoIPToggleButton) == false) goto L6;
            r4.setTranslationY(AndroidUtilities.dp(100.0f));
            r4.setScaleX(0.0f);
            r4.setScaleY(0.0f);
            r2.setStartDelay(((VoIPToggleButton) r4).animationDelay);
        L6:
            if ((r4 instanceof VoIpSwitchLayout) == false) goto L8;
            r4.setTranslationY(AndroidUtilities.dp(100.0f));
            r4.setScaleX(0.0f);
            r4.setScaleY(0.0f);
            r2.setStartDelay(((VoIpSwitchLayout) r4).animationDelay);
        L8:
            return r2;
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r5, View r6, TransitionValues r7, TransitionValues r8) {
            return ObjectAnimator.ofPropertyValuesHolder(r6, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{r6.getTranslationY(), AndroidUtilities.dp(100.0f)}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{r6.getScaleY(), 0.0f}), PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{r6.getScaleX(), 0.0f})});
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$24, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass24 extends PrivateVideoPreviewDialogNew {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass24(VoIPFragment r1, Context r2, float r3, float r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew
        public void afterOpened() {
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).lockDrawing = true;
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew
        public void beforeClosed() {
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).lockDrawing = false;
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew
        public int[] getFloatingViewLocation() {
            int[] r0 = new int[2];
            VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).getLocationOnScreen(r0);
            return new int[]{r0[0], r0[1], VoIPFragment.m21170$$Nest$fgetcurrentUserCameraFloatingLayout(this.this$0).getMeasuredWidth()};
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew
        public boolean isHasVideoOnMainScreen() {
            return this.this$0.callingUserIsVideo;
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew
        public void onDismiss(boolean r4, boolean r5) {
            VoIPFragment.m21208$$Nest$fputpreviewDialog(this.this$0, null);
            VoIPService r0 = VoIPService.getSharedInstance();
            VoIPFragment.m21193$$Nest$fgetwindowView(this.this$0).setLockOnScreen(false);
            if (r5 == false) goto L10;
            this.this$0.currentUserIsVideo = true;
            if (r0 == null) goto L8;
            if (r4 == true) goto L8;
            r0.requestVideoCall(false);
            r0.setVideoState(false, 2);
            r0.switchToSpeaker();
        L8:
            if (r0 == null) goto L12;
            VoIPFragment r42 = this.this$0;
            VoIPFragment.m21219$$Nest$msetVideoAction(r42, VoIPFragment.m21160$$Nest$fgetbottomVideoBtn(r42), r0, true);
        L12:
            VoIPFragment r43 = this.this$0;
            VoIPFragment.m21209$$Nest$fputpreviousState(r43, VoIPFragment.m21169$$Nest$fgetcurrentState(r43));
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
            return;
        L10:
            if (r0 == null) goto L12;
            r0.setVideoState(false, 0);
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$25, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass25 implements RendererCommon.RendererEvents {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass25(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            if (VoIPFragment.m21175$$Nest$fgetfirstFrameCallback(this.this$0) == null) goto L6;
            VoIPFragment.m21175$$Nest$fgetfirstFrameCallback(this.this$0).run();
            VoIPFragment.m21197$$Nest$fputfirstFrameCallback(this.this$0, null);
            return;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends BackgroundGradientDrawable.ListenerAdapter {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass3(VoIPFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onAllSizesReady() {
            VoIPFragment.m21177$$Nest$fgetgradientLayout(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends LinearLayout {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass4(VoIPFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setVisibleToUser(VoIPFragment.m21174$$Nest$fgetemojiLoaded(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends TextView {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass5(VoIPFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (r1 == false) goto L6;
            VoIPFragment.m21222$$Nest$mupdateViewState(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends LinearLayout {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass6(VoIPFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r7) {
            super.onInitializeAccessibilityNodeInfo(r7);
            VoIPServiceState r0 = VoIPService.getSharedState();
            CharSequence r6 = VoIPFragment.m21165$$Nest$fgetcallingUserTitle(this.this$0).getText();
            if (r0 != null) goto L5;
            return;
        L5:
            if (TextUtils.isEmpty(r6) == true) goto L18;
            StringBuilder r1 = new StringBuilder(r6);
            r1.append(", ");
            if (r0.getPrivateCall() != null) goto L9;
        L11:
            r1.append(LocaleController.getString(R.string.VoipInCallBranding));
        L12:
            long r2 = r0.getCallDuration();
            if (r2 <= 0) goto L15;
            r1.append(", ");
            r1.append(LocaleController.formatDuration((int) (r2 / 1000)));
        L15:
            r7.setText(r1);
            return;
        L9:
            if (r0.getPrivateCall().video == false) goto L11;
            r1.append(LocaleController.getString(R.string.VoipInVideoCallBranding));
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends VoIPToggleButton {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass7(VoIPFragment r1, Context r2, float r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchSetPressed(boolean r1) {
            super.dispatchSetPressed(r1);
            setPressedBtn(r1);
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements AcceptDeclineView.Listener {
        final /* synthetic */ VoIPFragment this$0;

        public static /* synthetic */ void $r8$lambda$nzwZljtnktEk7s8u91LjR_ISj6s(AnonymousClass8 r0) {
            r0.lambda$onAccept$0();
        }

        public AnonymousClass8(VoIPFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAccept$0() {
            if (VoIPService.getSharedState() == null) goto L10;
            VoIPService.getSharedState().acceptIncomingCall();
            if (this.this$0.currentUserIsVideo == true) goto L7;
            return;
        L7:
            if (VoIPService.getSharedInstance() == null) goto L12;
            VoIPService.getSharedInstance().requestVideoCall(false);
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.Listener
        public void onAccept() {
            if (VoIPFragment.m21169$$Nest$fgetcurrentState(this.this$0) != 17) goto L10;
            Intent r0 = new Intent(this.this$0.activity, VoIPService.class);
            r0.putExtra("user_id", this.this$0.callingUser.id);
            r0.putExtra("is_outgoing", true);
            r0.putExtra("start_incall_activity", false);
            r0.putExtra("video_call", VoIPFragment.m21180$$Nest$fgetisVideoCall(this.this$0));
            r0.putExtra("can_video_call", VoIPFragment.m21180$$Nest$fgetisVideoCall(this.this$0));
            r0.putExtra("account", VoIPFragment.m21168$$Nest$fgetcurrentAccount(this.this$0));
            this.this$0.activity.startService(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            FileLog.e(th);
            return;
        L10:
            if (this.this$0.activity.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L14;
            this.this$0.activity.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 101);
            return;
        L14:
            if (VoIPService.getSharedState() == null) goto L20;
            VoIPFragment.m21218$$Nest$mrunAcceptCallAnimation(this.this$0, new VoIPFragment$8$$ExternalSyntheticLambda0(this));
            return;
        }

        @Override // org.telegram.ui.Components.voip.AcceptDeclineView.Listener
        public void onDecline() {
            if (VoIPFragment.m21169$$Nest$fgetcurrentState(this.this$0) != 17) goto L7;
            VoIPFragment.m21193$$Nest$fgetwindowView(this.this$0).finish();
            return;
        L7:
            if (VoIPService.getSharedState() == null) goto L10;
            VoIPService.getSharedState().declineIncomingCall();
            return;
        L10:
            VoIPFragment.m21193$$Nest$fgetwindowView(this.this$0).finish();
        }
    }

    /* renamed from: org.telegram.ui.VoIPFragment$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends ImageView {
        final /* synthetic */ VoIPFragment this$0;

        public AnonymousClass9(VoIPFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName(ToggleButton.class.getName());
            r1.setCheckable(true);
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 == null) goto L6;
            r1.setChecked(r0.isSpeakerphoneOn());
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class ConferenceParticipantsView extends View {
        private final AvatarsDrawable avatarsDrawable;
        private final Paint backgroundPaint;
        private Text text;

        public ConferenceParticipantsView(Context r2) {
            super(r2);
            Paint r22 = new Paint(1);
            this.backgroundPaint = r22;
            r22.setColor(-14538189);
            AvatarsDrawable r23 = new AvatarsDrawable(this, false);
            this.avatarsDrawable = r23;
            r23.width = AndroidUtilities.dp(100.0f);
            r23.height = AndroidUtilities.dp(30.0f);
            r23.drawStoriesCircle = false;
            r23.setSize(AndroidUtilities.dp(24.0f));
            r23.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
            r23.setStepFactor(0.58f);
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            if (this.text != null) goto L5;
            return;
        L5:
            float r3 = (((AndroidUtilities.dp(4.0f) + this.avatarsDrawable.getUsedWidth()) + AndroidUtilities.dp(7.0f)) + this.text.getCurrentWidth()) + AndroidUtilities.dp(13.0f);
            float r5 = AndroidUtilities.dp(30.0f);
            RectF r6 = AndroidUtilities.rectTmp;
            r6.set((getWidth() - r3) / 2.0f, 0.0f, (getWidth() + r3) / 2.0f, getHeight());
            float r32 = r5 / 2.0f;
            r12.drawRoundRect(r6, r32, r32, this.backgroundPaint);
            r12.save();
            r12.translate(r6.left + AndroidUtilities.dp(4.0f), 0.0f);
            this.avatarsDrawable.onDraw(r12);
            r12.translate(this.avatarsDrawable.getMaxX() + AndroidUtilities.dp(7.0f), 0.0f);
            this.text.draw(r12, 0.0f, r32, -1, 1.0f);
            r12.restore();
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(30.0f));
        }

        public void set(int r8, long r9, ArrayList<TLRPC.GroupCallParticipant> r11, int r12) {
            if (r11 != null) goto L8;
            if (r12 > 0) goto L8;
            setVisibility(8);
            return;
        L8:
            if (r11 != null) goto L10;
            int r2 = 0;
        L11:
            int r122 = Math.max(r12, r2);
            if (r11 != null) goto L14;
            int r22 = 0;
        L15:
            int r23 = Math.min(3, r22);
            this.avatarsDrawable.setCount(r23);
            int r3 = 0;
        L16:
            if (r3 >= r23) goto L18;
            this.avatarsDrawable.setObject(r3, r8, MessagesController.getInstance(r8).getUserOrChat(DialogObject.getPeerDialogId(r11.get(r3).peer)));
            r3 = r3 + 1;
            goto L16
        L18:
            this.avatarsDrawable.commitTransition(false);
            if (r122 != 1) goto L29;
            if (r11 != null) goto L22;
        L27:
            setVisibility(8);
            return;
        L22:
            if (r11.size() == 0) goto L27;
            if (r11.size() != 1) goto L29;
            if (DialogObject.getPeerDialogId(r11.get(0).peer) == r9) goto L27;
        L29:
            this.text = new Text(LocaleController.formatPluralStringComma("Participants", r122), 14.0f, AndroidUtilities.bold());
            setVisibility(0);
            invalidate();
            return;
        L14:
            r22 = r11.size();
            goto L15
        L10:
            r2 = r11.size();
            goto L11
        }
    }

    public static /* synthetic */ void $r8$lambda$26UgQxF11Wb0wnxuH1wrDVcux_4(VoIPFragment r0, boolean r1, float r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9, float r10, float r11, float r12, float r13, float r14, float r15, float r16, float r17, ValueAnimator r18) {
        r0.lambda$createPiPTransition$20(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public static /* synthetic */ void $r8$lambda$3HVrNwfN65JoBr3D53vpBsFv9vA(VoIPFragment r0, float r1, float r2, float r3, ValueAnimator r4) {
        r0.lambda$finishZoom$17(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$3Vk7ik85Np9Lx7A8xukZyp22rwk(VoIPFragment r0, VoIPService r1, View r2) {
        r0.lambda$updateViewState$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6ZXnOOdNLHXbJen81yXDC0h8Xwo(VoIPFragment r0, Context r1, View r2) {
        r0.lambda$createView$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$78f7mgXYiRGqVH4NbzpYJrPs4CI(VoIPFragment r0, VoIPService r1, VoIpSwitchLayout r2, View r3) {
        r0.lambda$setFrontalCameraAction$39(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7hQjCE9wTVvSOmNGCVgR_Z4a2gQ(VoIPFragment r0) {
        r0.lambda$updateViewState$31();
    }

    public static /* synthetic */ void $r8$lambda$8Kqhw28hIlUs9LmGxvR6F6Xv7i4(VoIPFragment r0) {
        r0.lambda$runAcceptCallAnimation$16();
    }

    /* renamed from: $r8$lambda$ADa60CQ0ZR_-aSgBGpe_5i6VFc8, reason: not valid java name */
    public static /* synthetic */ void m21149$r8$lambda$ADa60CQ0ZR_aSgBGpe_5i6VFc8(VoIPFragment r0, View r1) {
        r0.lambda$setVideoAction$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$BrL2KK_EfMSKdYYUf51yg28CTlk(VoIPFragment r0, View r1) {
        r0.lambda$updateButtons$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$DhuytnEUORABk7dQ_mwIM261yCI(VoIPFragment r0) {
        r0.lambda$updateViewState$27();
    }

    public static /* synthetic */ void $r8$lambda$E812p1EC8Xz1D3AYOnMPWBfbzvs(VoIPFragment r0, Animator r1) {
        r0.lambda$startTransitionFromPiP$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$FwyUpE4nCla9PlLaGy629GsXD1E(VoIPFragment r0, View r1) {
        r0.lambda$createView$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$FzX6YaMC8JRQK6ZFLx1pQ_HB2Sk(VoIPFragment r0) {
        r0.lambda$checkEmojiLoaded$34();
    }

    public static /* synthetic */ void $r8$lambda$H10Ifwoghk6cnoPBh2QT6h6gljI(VoIPFragment r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$IRtdx08_kW4Q3okRYwraol8kvc0(VoIPFragment r0, int r1, VoIpSwitchLayout r2, VoIPService r3, View r4) {
        r0.lambda$setSpeakerPhoneAction$38(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$KbbFFT0ZU_e-LUrymc0nL2uzQjk, reason: not valid java name */
    public static /* synthetic */ void m21150$r8$lambda$KbbFFT0ZU_eLUrymc0nL2uzQjk(VoIPFragment r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.lambda$updateViewState$29(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MjosVzJyMoINzJh5GOo7HMxa2_k(Boolean r1, HashSet r2) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L6;
        r0.convertToConferenceCall(r1.booleanValue(), r2);
        return;
    }

    public static /* synthetic */ void $r8$lambda$OUeQmDMrfS4S7kmLRa4EK6VaSdo(VoIPFragment r0) {
        r0.lambda$updateViewState$32();
    }

    public static /* synthetic */ void $r8$lambda$R6WkJTy6a6TYJ5jiOgVYOwxp7NA(VoIPFragment r0) {
        r0.lambda$onRequestPermissionsResultInternal$41();
    }

    /* renamed from: $r8$lambda$S03yk9JGbn-igTPpQK6WCgq-Mvw, reason: not valid java name */
    public static /* synthetic */ void m21151$r8$lambda$S03yk9JGbnigTPpQK6WCgqMvw(VoIPFragment r0) {
        r0.lambda$createView$14();
    }

    public static /* synthetic */ void $r8$lambda$VxJHC3IyNrckuQmOdzaKUBxL4Fo(VoIPFragment r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$WE7K5BHJaihRt-4lh9v2_L0Zm9I, reason: not valid java name */
    public static /* synthetic */ void m21152$r8$lambda$WE7K5BHJaihRt4lh9v2_L0Zm9I(VoIPFragment r0, int r1) {
        r0.lambda$onSignalBarsCountChanged$4(r1);
    }

    /* renamed from: $r8$lambda$WOq9184HVe3mIsIz1N-rliDuvnI, reason: not valid java name */
    public static /* synthetic */ void m21153$r8$lambda$WOq9184HVe3mIsIz1NrliDuvnI(VoIPFragment r0, AlertDialog r1, int r2) {
        r0.lambda$requestInlinePermissions$43(r1, r2);
    }

    /* renamed from: $r8$lambda$XTcOvxMlC-7nTDK1Qy-xiflax2k, reason: not valid java name */
    public static /* synthetic */ void m21154$r8$lambda$XTcOvxMlC7nTDK1Qyxiflax2k(VoIPFragment r0, int r1) {
        r0.lambda$updateViewState$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$_SZMR5fQcPhPm1qINYno3BqzNV4(VoIPFragment r0, VoIPService r1, View r2) {
        r0.lambda$updateViewState$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bWTEtx4vn8A5wOhVYcW50prfNbM(VoIPFragment r0) {
        r0.lambda$updateViewState$33();
    }

    public static /* synthetic */ void $r8$lambda$cA8tJ9pdLH_WrR0PUitJR8pnAKI() {
        if (VoIPService.getSharedState() == null) goto L6;
        VoIPService.getSharedState().acceptIncomingCall();
        return;
    }

    public static /* synthetic */ void $r8$lambda$cSc2ka2xbVHlEh7upbNdFRYoIjI(VoIPFragment r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$dQo0IPLolFb1Zqcg8mBLg480H1g(VoIPFragment r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$dc2UA12AtvkLwuvNggls1hVpIvY(VoIPFragment r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$eG35quzz0EcQolDirR0hUJQPkPM(VoIPFragment r0, View r1) {
        r0.lambda$setMicrohoneAction$36(r1);
    }

    public static /* synthetic */ void $r8$lambda$fRfyAco2WhjU9tZI26jTDkpqxOI(VoIPFragment r0, AlertDialog r1, int r2) {
        r0.lambda$updateViewState$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gMQFC27LxWZNPgp3xugJjRRFhHg(VoIPFragment r0, float r1, boolean r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$h4Vsz7PaGNg0Fa1Skednb7HLwZY(VoIPFragment r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$jzLcl_XUrG07oCyspPEf1cfOUvw(VoIPFragment r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$kAv2KvHQF8gDIrukR8X3nxQZEjY(VoIPFragment r0, int r1, int r2, int r3, ViewGroup.MarginLayoutParams r4, ValueAnimator r5) {
        r0.lambda$runAcceptCallAnimation$15(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$lBvaJAvhmUGlpQo8AfDacpcvewE(VoIPFragment r0, View r1, WindowInsetsCompat r2) {
        r0.setInsets(r2.toWindowInsets());
        return WindowInsetsCompat.CONSUMED;
    }

    public static /* synthetic */ void $r8$lambda$oX5v0FFuMMwOQNxEXgReGRZTvkM(VoIPFragment r0, DialogInterface r1) {
        r0.lambda$showErrorDialog$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$qddhzaBYVwfeewe9zvzfSQTq03w(VoIPFragment r0, boolean[] r1, DialogInterface r2) {
        r0.lambda$updateViewState$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qy7pltytxqsRs3p8a8yYewHeFGw(VoIPFragment r0) {
        r0.lambda$startTransitionFromPiP$19();
    }

    /* renamed from: $r8$lambda$uZp_JcMZwl7CyazvmRevn-QO3BA, reason: not valid java name */
    public static /* synthetic */ void m21155$r8$lambda$uZp_JcMZwl7CyazvmRevnQO3BA(VoIPFragment r0, VoIPService r1) {
        r0.lambda$updateViewState$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$vHJTpm_yg43Hd4HHGerBrzgMVMI(VoIPFragment r0) {
        r0.startWaitingFoHideUi();
    }

    /* renamed from: $r8$lambda$vYWOK9azQW7_ci0dVhvWQq-5cSQ, reason: not valid java name */
    public static /* synthetic */ void m21156$r8$lambda$vYWOK9azQW7_ci0dVhvWQq5cSQ(VoIPFragment r0) {
        r0.lambda$updateViewState$21();
    }

    /* renamed from: $r8$lambda$y9-sJwLOQOBP06on8Nkmo7Ftws8, reason: not valid java name */
    public static /* synthetic */ void m21157$r8$lambda$y9sJwLOQOBP06on8Nkmo7Ftws8(VoIPFragment r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$replaceEmojiToLottieFrame$44(r1);
    }

    /* renamed from: $r8$lambda$yUjZjkvLBGofet0wk0-imaZtC4E, reason: not valid java name */
    public static /* synthetic */ void m21158$r8$lambda$yUjZjkvLBGofet0wk0imaZtC4E(VoIPFragment r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$zmklAMRa7N5Xmt8YZlZTPQsfWJM(VoIPFragment r0) {
        r0.lambda$updateViewState$24();
    }

    /* renamed from: -$$Nest$fgetacceptDeclineView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AcceptDeclineView m21159$$Nest$fgetacceptDeclineView(VoIPFragment r0) {
        return r0.acceptDeclineView;
    }

    /* renamed from: -$$Nest$fgetbottomVideoBtn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIpSwitchLayout m21160$$Nest$fgetbottomVideoBtn(VoIPFragment r0) {
        return r0.bottomVideoBtn;
    }

    /* renamed from: -$$Nest$fgetcallingUserMiniFloatingLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPFloatingLayout m21161$$Nest$fgetcallingUserMiniFloatingLayout(VoIPFragment r0) {
        return r0.callingUserMiniFloatingLayout;
    }

    /* renamed from: -$$Nest$fgetcallingUserMiniTextureRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureViewRenderer m21162$$Nest$fgetcallingUserMiniTextureRenderer(VoIPFragment r0) {
        return r0.callingUserMiniTextureRenderer;
    }

    /* renamed from: -$$Nest$fgetcallingUserPhotoViewMini, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageWithWavesView m21163$$Nest$fgetcallingUserPhotoViewMini(VoIPFragment r0) {
        return r0.callingUserPhotoViewMini;
    }

    /* renamed from: -$$Nest$fgetcallingUserTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPTextureView m21164$$Nest$fgetcallingUserTextureView(VoIPFragment r0) {
        return r0.callingUserTextureView;
    }

    /* renamed from: -$$Nest$fgetcallingUserTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m21165$$Nest$fgetcallingUserTitle(VoIPFragment r0) {
        return r0.callingUserTitle;
    }

    /* renamed from: -$$Nest$fgetcanHideUI, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21166$$Nest$fgetcanHideUI(VoIPFragment r0) {
        return r0.canHideUI;
    }

    /* renamed from: -$$Nest$fgetcanZoomGesture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21167$$Nest$fgetcanZoomGesture(VoIPFragment r0) {
        return r0.canZoomGesture;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21168$$Nest$fgetcurrentAccount(VoIPFragment r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21169$$Nest$fgetcurrentState(VoIPFragment r0) {
        return r0.currentState;
    }

    /* renamed from: -$$Nest$fgetcurrentUserCameraFloatingLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPFloatingLayout m21170$$Nest$fgetcurrentUserCameraFloatingLayout(VoIPFragment r0) {
        return r0.currentUserCameraFloatingLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentUserCameraIsFullscreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21171$$Nest$fgetcurrentUserCameraIsFullscreen(VoIPFragment r0) {
        return r0.currentUserCameraIsFullscreen;
    }

    /* renamed from: -$$Nest$fgetcurrentUserTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPTextureView m21172$$Nest$fgetcurrentUserTextureView(VoIPFragment r0) {
        return r0.currentUserTextureView;
    }

    /* renamed from: -$$Nest$fgetemojiExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21173$$Nest$fgetemojiExpanded(VoIPFragment r0) {
        return r0.emojiExpanded;
    }

    /* renamed from: -$$Nest$fgetemojiLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21174$$Nest$fgetemojiLoaded(VoIPFragment r0) {
        return r0.emojiLoaded;
    }

    /* renamed from: -$$Nest$fgetfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21175$$Nest$fgetfirstFrameCallback(VoIPFragment r0) {
        return r0.firstFrameCallback;
    }

    /* renamed from: -$$Nest$fgetfragmentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m21176$$Nest$fgetfragmentView(VoIPFragment r0) {
        return r0.fragmentView;
    }

    /* renamed from: -$$Nest$fgetgradientLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIpGradientLayout m21177$$Nest$fgetgradientLayout(VoIPFragment r0) {
        return r0.gradientLayout;
    }

    /* renamed from: -$$Nest$fgetisFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21178$$Nest$fgetisFinished(VoIPFragment r0) {
        return r0.isFinished;
    }

    /* renamed from: -$$Nest$fgetisInPinchToZoomTouchMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21179$$Nest$fgetisInPinchToZoomTouchMode(VoIPFragment r0) {
        return r0.isInPinchToZoomTouchMode;
    }

    /* renamed from: -$$Nest$fgetisVideoCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21180$$Nest$fgetisVideoCall(VoIPFragment r0) {
        return r0.isVideoCall;
    }

    /* renamed from: -$$Nest$fgetlockOnScreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21181$$Nest$fgetlockOnScreen(VoIPFragment r0) {
        return r0.lockOnScreen;
    }

    /* renamed from: -$$Nest$fgetpinchCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21182$$Nest$fgetpinchCenterX(VoIPFragment r0) {
        return r0.pinchCenterX;
    }

    /* renamed from: -$$Nest$fgetpinchCenterY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21183$$Nest$fgetpinchCenterY(VoIPFragment r0) {
        return r0.pinchCenterY;
    }

    /* renamed from: -$$Nest$fgetpinchStartCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21184$$Nest$fgetpinchStartCenterX(VoIPFragment r0) {
        return r0.pinchStartCenterX;
    }

    /* renamed from: -$$Nest$fgetpinchStartCenterY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21185$$Nest$fgetpinchStartCenterY(VoIPFragment r0) {
        return r0.pinchStartCenterY;
    }

    /* renamed from: -$$Nest$fgetpinchStartDistance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21186$$Nest$fgetpinchStartDistance(VoIPFragment r0) {
        return r0.pinchStartDistance;
    }

    /* renamed from: -$$Nest$fgetpinchTranslationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21187$$Nest$fgetpinchTranslationX(VoIPFragment r0) {
        return r0.pinchTranslationX;
    }

    /* renamed from: -$$Nest$fgetpinchTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21188$$Nest$fgetpinchTranslationY(VoIPFragment r0) {
        return r0.pinchTranslationY;
    }

    /* renamed from: -$$Nest$fgetpointerId1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21189$$Nest$fgetpointerId1(VoIPFragment r0) {
        return r0.pointerId1;
    }

    /* renamed from: -$$Nest$fgetpointerId2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21190$$Nest$fgetpointerId2(VoIPFragment r0) {
        return r0.pointerId2;
    }

    /* renamed from: -$$Nest$fgetswitchingToPip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21191$$Nest$fgetswitchingToPip(VoIPFragment r0) {
        return r0.switchingToPip;
    }

    /* renamed from: -$$Nest$fgetuiVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21192$$Nest$fgetuiVisible(VoIPFragment r0) {
        return r0.uiVisible;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPWindowView m21193$$Nest$fgetwindowView(VoIPFragment r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fgetwindowViewSkipRender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21194$$Nest$fgetwindowViewSkipRender(VoIPFragment r0) {
        return r0.windowViewSkipRender;
    }

    /* renamed from: -$$Nest$fgetzoomStarted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21195$$Nest$fgetzoomStarted(VoIPFragment r0) {
        return r0.zoomStarted;
    }

    /* renamed from: -$$Nest$fputcanZoomGesture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21196$$Nest$fputcanZoomGesture(VoIPFragment r0, boolean r1) {
        r0.canZoomGesture = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21197$$Nest$fputfirstFrameCallback(VoIPFragment r0, Runnable r1) {
        r0.firstFrameCallback = r1;
    }

    /* renamed from: -$$Nest$fputisInPinchToZoomTouchMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21198$$Nest$fputisInPinchToZoomTouchMode(VoIPFragment r0, boolean r1) {
        r0.isInPinchToZoomTouchMode = r1;
    }

    /* renamed from: -$$Nest$fputpinchCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21199$$Nest$fputpinchCenterX(VoIPFragment r0, float r1) {
        r0.pinchCenterX = r1;
    }

    /* renamed from: -$$Nest$fputpinchCenterY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21200$$Nest$fputpinchCenterY(VoIPFragment r0, float r1) {
        r0.pinchCenterY = r1;
    }

    /* renamed from: -$$Nest$fputpinchStartCenterX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21201$$Nest$fputpinchStartCenterX(VoIPFragment r0, float r1) {
        r0.pinchStartCenterX = r1;
    }

    /* renamed from: -$$Nest$fputpinchStartCenterY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21202$$Nest$fputpinchStartCenterY(VoIPFragment r0, float r1) {
        r0.pinchStartCenterY = r1;
    }

    /* renamed from: -$$Nest$fputpinchStartDistance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21203$$Nest$fputpinchStartDistance(VoIPFragment r0, float r1) {
        r0.pinchStartDistance = r1;
    }

    /* renamed from: -$$Nest$fputpinchTranslationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21204$$Nest$fputpinchTranslationX(VoIPFragment r0, float r1) {
        r0.pinchTranslationX = r1;
    }

    /* renamed from: -$$Nest$fputpinchTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21205$$Nest$fputpinchTranslationY(VoIPFragment r0, float r1) {
        r0.pinchTranslationY = r1;
    }

    /* renamed from: -$$Nest$fputpointerId1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21206$$Nest$fputpointerId1(VoIPFragment r0, int r1) {
        r0.pointerId1 = r1;
    }

    /* renamed from: -$$Nest$fputpointerId2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21207$$Nest$fputpointerId2(VoIPFragment r0, int r1) {
        r0.pointerId2 = r1;
    }

    /* renamed from: -$$Nest$fputpreviewDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21208$$Nest$fputpreviewDialog(VoIPFragment r0, PrivateVideoPreviewDialogNew r1) {
        r0.previewDialog = r1;
    }

    /* renamed from: -$$Nest$fputpreviousState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21209$$Nest$fputpreviousState(VoIPFragment r0, int r1) {
        r0.previousState = r1;
    }

    /* renamed from: -$$Nest$fputswitchingToPip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21210$$Nest$fputswitchingToPip(VoIPFragment r0, boolean r1) {
        r0.switchingToPip = r1;
    }

    /* renamed from: -$$Nest$fputzoomStarted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21211$$Nest$fputzoomStarted(VoIPFragment r0, boolean r1) {
        r0.zoomStarted = r1;
    }

    /* renamed from: -$$Nest$mcheckPointerIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21212$$Nest$mcheckPointerIds(VoIPFragment r0, MotionEvent r1) {
        return r0.checkPointerIds(r1);
    }

    /* renamed from: -$$Nest$mdestroy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21213$$Nest$mdestroy(VoIPFragment r0) {
        r0.destroy();
    }

    /* renamed from: -$$Nest$mexpandEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21214$$Nest$mexpandEmoji(VoIPFragment r0, boolean r1) {
        r0.expandEmoji(r1);
    }

    /* renamed from: -$$Nest$mfinishZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21215$$Nest$mfinishZoom(VoIPFragment r0) {
        r0.finishZoom();
    }

    /* renamed from: -$$Nest$mgetFullscreenTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPTextureView m21216$$Nest$mgetFullscreenTextureView(VoIPFragment r0) {
        return r0.getFullscreenTextureView();
    }

    /* renamed from: -$$Nest$monBackPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21217$$Nest$monBackPressed(VoIPFragment r0) {
        r0.onBackPressed();
    }

    /* renamed from: -$$Nest$mrunAcceptCallAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21218$$Nest$mrunAcceptCallAnimation(VoIPFragment r0, Runnable r1) {
        r0.runAcceptCallAnimation(r1);
    }

    /* renamed from: -$$Nest$msetVideoAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21219$$Nest$msetVideoAction(VoIPFragment r0, VoIpSwitchLayout r1, VoIPService r2, boolean r3) {
        r0.setVideoAction(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mshowUi, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21220$$Nest$mshowUi(VoIPFragment r0, boolean r1) {
        r0.showUi(r1);
    }

    /* renamed from: -$$Nest$mstartWaitingFoHideUi, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21221$$Nest$mstartWaitingFoHideUi(VoIPFragment r0) {
        r0.startWaitingFoHideUi();
    }

    /* renamed from: -$$Nest$mupdateViewState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21222$$Nest$mupdateViewState(VoIPFragment r0) {
        r0.updateViewState();
    }

    /* renamed from: -$$Nest$sfputinstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21223$$Nest$sfputinstance(VoIPFragment r0) {
        instance = r0;
    }

    public VoIPFragment(int r4) {
        this.backgroundProvider = new VoIPBackgroundProvider();
        this.emojiViews = new BackupImageView[4];
        this.emojiDrawables = new Drawable[4];
        this.overlayPaint = new Paint();
        this.overlayBottomPaint = new Paint();
        this.uiVisible = true;
        this.uiVisibilityAlpha = 1.0f;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.statusbarAnimatorListener = new VoIPFragment$$ExternalSyntheticLambda5(this);
        this.hideUIRunnable = new VoIPFragment$$ExternalSyntheticLambda6(this);
        this.stopAnimatingBgRunnable = new VoIPFragment$$ExternalSyntheticLambda7(this);
        this.pinchScale = 1.0f;
        this.currentAccount = r4;
        this.currentUser = MessagesController.getInstance(r4).getUser(Long.valueOf(UserConfig.getInstance(r4).getClientUserId()));
        VoIPServiceState r0 = VoIPService.getSharedState();
        if (r0 != null) goto L5;
        return;
    L5:
        this.callingUser = r0.getUser();
        if (VoIPService.getSharedInstance() == null) goto L8;
        VoIPService.getSharedInstance().registerStateListener(this);
    L8:
        this.isOutgoing = r0.isOutgoing();
        this.previousState = -1;
        this.currentState = r0.getCallState();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.webRtcSpeakerAmplitudeEvent);
        NotificationCenter.getInstance(r4).addObserver(this, NotificationCenter.voipServiceCreated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.closeInCallActivity);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.nearEarEvent);
    }

    private void checkEmojiLoaded(boolean r7) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 4) goto L9;
        if (isLoaded(this.emojiDrawables[r1]) == false) goto L8;
        r2 = r2 + 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        if (r2 != 4) goto L27;
        this.emojiLoaded = true;
        int r12 = 0;
    L11:
        if (r12 >= 4) goto L18;
        if (this.emojiViews[r12].getVisibility() == 0) goto L17;
        this.emojiViews[r12].setVisibility(0);
        if (r7 == false) goto L17;
        this.emojiViews[r12].setAlpha(0.0f);
        this.emojiViews[r12].setScaleX(0.0f);
        this.emojiViews[r12].setScaleY(0.0f);
        this.emojiViews[r12].animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK).setDuration(250).start();
    L17:
        r12 = r12 + 1;
        goto L11
    L18:
        this.encryptionTooltip.postDelayed(new VoIPFragment$$ExternalSyntheticLambda3(this), 1000);
        return;
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

    public static void clearInstance() {
        if (instance != null) goto L5;
    L14:
        instance = null;
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L13;
        int r0 = instance.windowView.getMeasuredHeight();
        if (instance.canSwitchToPip == false) goto L13;
        if (VoIPService.getSharedInstance().isConverting() == true) goto L13;
        VoIPFragment r1 = instance;
        VoIPPiPView.show(r1.activity, r1.currentAccount, r1.windowView.getMeasuredWidth(), r0, 0);
        WindowInsets r02 = instance.lastInsets;
        if (r02 == null) goto L13;
        VoIPPiPView.topInset = r02.getSystemWindowInsetTop();
        VoIPPiPView.bottomInset = instance.lastInsets.getSystemWindowInsetBottom();
    L13:
        instance.callingUserTextureView.renderer.release();
        instance.currentUserTextureView.renderer.release();
        instance.callingUserMiniTextureRenderer.release();
        instance.destroy();
        goto L14
    }

    private void destroy() {
        if (VoIPService.getSharedInstance() == null) goto L5;
        VoIPService.getSharedInstance().unregisterStateListener(this);
    L5:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.webRtcSpeakerAmplitudeEvent);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.voipServiceCreated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.closeInCallActivity);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.nearEarEvent);
        PipSource r0 = this.pipSource;
        if (r0 == null) goto L8;
        r0.destroy();
        this.pipSource = null;
    L8:
        UserSelectorBottomSheet r02 = this.addPeopleSheet;
        if (r02 == null) goto L12;
        r02.dismiss();
        this.addPeopleSheet = null;
        return;
    }

    private void expandEmoji(boolean r11) {
        if (this.emojiLoaded == true) goto L5;
        return;
    L5:
        if (this.emojiExpanded != r11) goto L7;
        return;
    L7:
        if (this.uiVisible == false) goto L26;
        this.emojiExpanded = r11;
        this.voIpCoverView.onEmojiExpanded(r11);
        if (r11 == false) goto L19;
        if (SharedConfig.callEncryptionHintDisplayedCount >= 2) goto L14;
        SharedConfig.incrementCallEncryptionHintDisplayed(2);
    L14:
        this.encryptionTooltip.hide();
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        if (this.callingUserPhotoViewMini.getVisibility() != 0) goto L17;
        this.callingUserPhotoViewMini.animate().setStartDelay(0).translationY(AndroidUtilities.dp(48.0f)).scaleY(0.1f).scaleX(0.1f).alpha(0.0f).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L17:
        this.hideEmojiLayout.animate().setListener(null).cancel();
        this.hideEmojiLayout.setVisibility(0);
        this.hideEmojiLayout.setAlpha(0.0f);
        this.hideEmojiLayout.setScaleX(0.3f);
        this.hideEmojiLayout.setScaleY(0.3f);
        this.hideEmojiLayout.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT).start();
        ViewPropertyAnimator r2 = this.emojiLayout.animate().scaleX(1.72f).scaleY(1.72f).translationY(AndroidUtilities.dp(140.0f));
        CubicBezierInterpolator r6 = CubicBezierInterpolator.DEFAULT;
        r2.setInterpolator(r6).setDuration(400).start();
        this.emojiRationalLayout.animate().setListener(null).cancel();
        this.emojiRationalLayout.setVisibility(0);
        this.emojiRationalLayout.setTranslationY(-AndroidUtilities.dp(120.0f));
        this.emojiRationalLayout.setScaleX(0.7f);
        this.emojiRationalLayout.setScaleY(0.7f);
        this.emojiRationalLayout.setAlpha(0.0f);
        this.emojiRationalLayout.animate().alpha(1.0f).translationY(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(400).setListener(new AnonymousClass16(this)).setInterpolator(r6).start();
    L22:
        this.previousState = this.currentState;
        updateViewState();
        return;
    L19:
        if (this.callingUserPhotoViewMini.getVisibility() != 0) goto L21;
        this.callingUserPhotoViewMini.animate().setStartDelay(50).translationY(0.0f).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(250).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L21:
        this.hideEmojiLayout.animate().setListener(null).cancel();
        ViewPropertyAnimator r112 = this.hideEmojiLayout.animate().alpha(0.0f).scaleY(0.3f).scaleX(0.3f).setDuration(230);
        CubicBezierInterpolator r22 = CubicBezierInterpolator.DEFAULT;
        r112.setInterpolator(r22).setListener(new HideViewAfterAnimation(this.hideEmojiLayout)).start();
        this.emojiLayout.animate().scaleX(1.0f).scaleY(1.0f).translationY(0.0f).setInterpolator(r22).setDuration(280).start();
        this.emojiRationalLayout.animate().setListener(null).cancel();
        this.emojiRationalLayout.animate().alpha(0.0f).scaleY(0.7f).scaleX(0.7f).translationY(-AndroidUtilities.dp(120.0f)).setListener(new AnonymousClass17(this)).setDuration(250).setInterpolator(r22).start();
        goto L22
    }

    private void finishZoom() {
        if (this.zoomStarted == false) goto L5;
        this.zoomStarted = false;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.zoomBackAnimator = r0;
        r0.addUpdateListener(new VoIPFragment$$ExternalSyntheticLambda45(this, this.pinchScale, this.pinchTranslationX, this.pinchTranslationY));
        this.zoomBackAnimator.addListener(new AnonymousClass11(this));
        this.zoomBackAnimator.setDuration(350);
        this.zoomBackAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.zoomBackAnimator.start();
    L5:
        this.canZoomGesture = false;
        this.isInPinchToZoomTouchMode = false;
    }

    private VoIPTextureView getFullscreenTextureView() {
        if (this.callingUserIsVideo == false) goto L7;
        return this.callingUserTextureView;
    L7:
        return this.currentUserTextureView;
    }

    public static VoIPFragment getInstance() {
        return instance;
    }

    private void initRenderers() {
        this.currentUserTextureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass12(this));
        this.callingUserTextureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass13(this), EglBase.CONFIG_PLAIN, new GlRectDrawer());
        this.callingUserMiniTextureRenderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), null);
    }

    private boolean isLoaded(Drawable r2) {
        if ((r2 instanceof Emoji.EmojiDrawable) == false) goto L7;
        return ((Emoji.EmojiDrawable) r2).isLoaded();
    L7:
        if ((r2 instanceof AnimatedEmojiDrawable) == false) goto L19;
        ImageReceiver r1 = ((AnimatedEmojiDrawable) r2).getImageReceiver();
        if (r1 != null) goto L11;
        return false;
    L11:
        Drawable r12 = r1.getThumb();
        if ((r12 instanceof Emoji.EmojiDrawable) == true) goto L14;
        return true;
    L14:
        if (((Emoji.EmojiDrawable) r12).isLoaded() == true) goto L20;
        return false;
    L20:
        return true;
    L19:
        return false;
    }

    private /* synthetic */ void lambda$checkEmojiLoaded$34() {
        if (SharedConfig.callEncryptionHintDisplayedCount >= 2) goto L6;
        SharedConfig.incrementCallEncryptionHintDisplayed(1);
        this.encryptionTooltip.setTranslationY(this.emojiLayout.getY() + AndroidUtilities.dp(36.0f));
        this.encryptionTooltip.show();
        return;
    }

    private /* synthetic */ void lambda$createPiPTransition$20(boolean r4, float r5, float r6, float r7, float r8, float r9, float r10, float r11, float r12, float r13, float r14, float r15, float r16, float r17, float r18, float r19, float r20, ValueAnimator r21) {
        float r0 = ((Float) r21.getAnimatedValue()).floatValue();
        float r2 = 1.0f - r0;
        this.enterTransitionProgress = r2;
        updateSystemBarColors();
        if (r4 == false) goto L5;
        float r52 = (r5 * r2) + (r6 * r0);
        this.currentUserCameraFloatingLayout.setScaleX(r52);
        this.currentUserCameraFloatingLayout.setScaleY(r52);
        this.currentUserCameraFloatingLayout.setTranslationX((r7 * r2) + (r8 * r0));
        this.currentUserCameraFloatingLayout.setTranslationY((r9 * r2) + (r10 * r0));
        this.currentUserCameraFloatingLayout.setCornerRadius((r11 * r2) + (r12 * r0));
        this.currentUserCameraFloatingLayout.setAlpha((r13 * r2) + (r14 * r0));
    L5:
        float r42 = (r15 * r2) + (r16 * r0);
        this.callingUserTextureView.setScaleX(r42);
        this.callingUserTextureView.setScaleY(r42);
        this.callingUserTextureView.setTranslationX((r17 * r2) + (r18 * r0));
        this.callingUserTextureView.setTranslationY((r19 * r2) + (r20 * r0));
        this.callingUserTextureView.setRoundCorners(((AndroidUtilities.dp(4.0f) * r0) * 1.0f) / r42);
        if (this.currentUserCameraFloatingLayout.measuredAsFloatingMode == true) goto L8;
        this.currentUserTextureView.setScreenshareMiniProgress(r0, false);
    L8:
        this.windowView.invalidate();
    }

    private /* synthetic */ void lambda$createView$10(View r3) {
        if (this.speakerPhoneIcon.getTag() == null) goto L17;
        VoIPService r32 = VoIPService.getSharedInstance();
        if (r32 == null) goto L16;
        startWaitingFoHideUi();
        if (r32.isBluetoothOn() == false) goto L11;
        int r0 = 2;
    L14:
        r32.toggleSpeakerphoneOrShowRouteSheet(this.activity, false, Integer.valueOf(r0));
        return;
    L11:
        if (r32.isSpeakerphoneOn() == false) goto L13;
        r0 = 0;
        goto L14
    L13:
        r0 = 1;
        goto L14
    L16:
        return;
    }

    private /* synthetic */ void lambda$createView$11(View r1) {
        if (this.lockOnScreen == false) goto L5;
        return;
    L5:
        onBackPressed();
    }

    private /* synthetic */ void lambda$createView$13(Context r10, View r11) {
        if (this.lockOnScreen == false) goto L5;
        return;
    L5:
        UserSelectorBottomSheet r112 = this.addPeopleSheet;
        if (r112 == null) goto L8;
        r112.dismiss();
        this.addPeopleSheet = null;
    L8:
        UserSelectorBottomSheet r0 = new UserSelectorBottomSheet(r10, this.currentAccount, 0, null, 4, true, new DarkBlueThemeResourcesProvider());
        TLRPC.User r102 = this.currentUser;
        long r1 = 0;
        if (r102 == null) goto L11;
        long r103 = r102.id;
    L12:
        TLRPC.User r3 = this.callingUser;
        if (r3 == null) goto L15;
        r1 = r3.id;
    L15:
        UserSelectorBottomSheet r104 = r0.exceptUsers(new long[]{r103, r1}).setOnUsersSelector(new VoIPFragment$$ExternalSyntheticLambda36());
        this.addPeopleSheet = r104;
        r104.show();
        return;
    L11:
        r103 = 0;
        goto L12
    }

    private /* synthetic */ void lambda$createView$14() {
        this.previousState = this.currentState;
        updateViewState();
    }

    private /* synthetic */ void lambda$createView$5(float r1, boolean r2) {
        this.currentUserTextureView.setScreenshareMiniProgress(r1, r2);
    }

    private /* synthetic */ void lambda$createView$6(View r5) {
        if (this.currentUserIsVideo == true) goto L5;
        return;
    L5:
        if (this.callingUserIsVideo == true) goto L7;
        return;
    L7:
        if ((System.currentTimeMillis() - this.lastContentTapTime) <= 500) goto L12;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        this.lastContentTapTime = System.currentTimeMillis();
        this.callingUserMiniFloatingLayout.setRelativePosition(this.currentUserCameraFloatingLayout);
        this.currentUserCameraIsFullscreen = true;
        this.cameraForceExpanded = true;
        this.previousState = this.currentState;
        updateViewState();
        return;
    }

    private /* synthetic */ void lambda$createView$7(View r5) {
        if (this.cameraForceExpanded == true) goto L5;
        return;
    L5:
        if ((System.currentTimeMillis() - this.lastContentTapTime) <= 500) goto L9;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        this.lastContentTapTime = System.currentTimeMillis();
        this.currentUserCameraFloatingLayout.setRelativePosition(this.callingUserMiniFloatingLayout);
        this.currentUserCameraIsFullscreen = false;
        this.cameraForceExpanded = false;
        this.previousState = this.currentState;
        updateViewState();
        return;
    }

    private /* synthetic */ void lambda$createView$8(View r5) {
        if ((System.currentTimeMillis() - this.lastContentTapTime) < 500) goto L13;
        this.lastContentTapTime = System.currentTimeMillis();
        boolean r52 = this.emojiExpanded;
        if (r52 == false) goto L9;
        return;
    L9:
        if (this.emojiLoaded == false) goto L12;
        expandEmoji(!r52);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$createView$9(View r5) {
        if ((System.currentTimeMillis() - this.lastContentTapTime) < 500) goto L10;
        this.lastContentTapTime = System.currentTimeMillis();
        if (this.emojiLoaded == false) goto L9;
        expandEmoji(!this.emojiExpanded);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$finishZoom$17(float r3, float r4, float r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        this.pinchScale = (r3 * r62) + ((1.0f - r62) * 1.0f);
        this.pinchTranslationX = r4 * r62;
        this.pinchTranslationY = r5 * r62;
        this.fragmentView.invalidate();
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r1) {
        this.uiVisibilityAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        updateSystemBarColors();
    }

    private /* synthetic */ void lambda$new$1() {
        this.hideUiRunnableWaiting = false;
        HintView2 r1 = this.tapToVideoTooltip;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L9:
        if (this.canHideUI == true) goto L11;
        return;
    L11:
        if (this.uiVisible == true) goto L13;
        return;
    L13:
        if (this.emojiExpanded == true) goto L19;
        if (r12 == true) goto L20;
        this.lastContentTapTime = System.currentTimeMillis();
        showUi(false);
        this.previousState = this.currentState;
        updateViewState();
        return;
    L20:
        return;
    L19:
        return;
    L5:
        if (r1.shown() == false) goto L7;
        r12 = true;
        goto L9
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.currentState != 3) goto L6;
        this.callingUserPhotoViewMini.setMute(true, false);
        this.gradientLayout.pause();
        return;
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultInternal$41() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$onSignalBarsCountChanged$4(int r3) {
        this.statusTextView.setSignalBarCount(r3);
        VoIpGradientLayout r0 = this.gradientLayout;
        if (r3 > 1) goto L6;
        r0.showToBadConnection();
        this.statusTextView.showBadConnection(true, true);
        return;
    L6:
        r0.hideBadConnection();
        this.statusTextView.showBadConnection(false, true);
    }

    private /* synthetic */ void lambda$replaceEmojiToLottieFrame$44(TLRPC.TL_messages_stickerSet r1) {
        updateKeyView(true);
    }

    private /* synthetic */ void lambda$requestInlinePermissions$43(AlertDialog r1, int r2) {
        VoIPWindowView r0 = this.windowView;
        if (r0 == null) goto L6;
        r0.finish();
        return;
    }

    private /* synthetic */ void lambda$runAcceptCallAnimation$15(int r2, int r3, int r4, ViewGroup.MarginLayoutParams r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        this.acceptDeclineView.setTranslationY(r2 * r62);
        int r22 = (int) (r3 - ((r3 + r4) * r62));
        r5.leftMargin = r22;
        r5.rightMargin = r22;
        this.acceptDeclineView.requestLayout();
    }

    private /* synthetic */ void lambda$runAcceptCallAnimation$16() {
        int[] r0 = new int[2];
        this.acceptDeclineView.getLocationOnScreen(r0);
        int r2 = r0[0];
        int r4 = r0[1];
        this.bottomSpeakerBtn.getLocationOnScreen(r0);
        this.bottomSpeakerBtn.setTranslationX((r2 - r0[0]) + AndroidUtilities.dp(42.0f));
        this.bottomSpeakerBtn.setTranslationY((r4 - r0[1]) + AndroidUtilities.dp(44.0f));
        this.bottomMuteBtn.getLocationOnScreen(r0);
        this.bottomMuteBtn.setTranslationX((r2 - r0[0]) + AndroidUtilities.dp(42.0f));
        this.bottomMuteBtn.setTranslationY((r4 - r0[1]) + AndroidUtilities.dp(44.0f));
        this.bottomVideoBtn.getLocationOnScreen(r0);
        this.bottomVideoBtn.setTranslationX((r2 - r0[0]) + AndroidUtilities.dp(42.0f));
        this.bottomVideoBtn.setTranslationY((r4 - r0[1]) + AndroidUtilities.dp(44.0f));
        this.bottomEndCallBtn.getLocationOnScreen(r0);
        this.bottomEndCallBtn.setTranslationX((((r2 + this.acceptDeclineView.getWidth()) - r0[0]) - AndroidUtilities.dp(49.0f)) - AndroidUtilities.dp(60.0f));
        this.bottomEndCallBtn.setTranslationY((r4 - r0[1]) + AndroidUtilities.dp(44.0f));
        this.bottomEndCallBtn.setAlpha(1.0f);
        this.bottomSpeakerBtn.setAlpha(1.0f);
        this.bottomMuteBtn.setAlpha(1.0f);
        this.bottomVideoBtn.setAlpha(1.0f);
        this.bottomEndCallBtn.animate().setStartDelay(0).translationY(0.0f).setInterpolator(new LinearInterpolator()).translationX(0.0f).setDuration(200).start();
        this.bottomSpeakerBtn.animate().setStartDelay(0).translationY(0.0f).setInterpolator(new LinearInterpolator()).translationX(0.0f).setDuration(200).start();
        this.bottomMuteBtn.animate().setStartDelay(0).translationY(0.0f).setInterpolator(new LinearInterpolator()).translationX(0.0f).setDuration(200).start();
        this.bottomVideoBtn.animate().setStartDelay(0).translationY(0.0f).setInterpolator(new LinearInterpolator()).translationX(0.0f).setDuration(200).start();
    }

    private /* synthetic */ void lambda$setFrontalCameraAction$39(VoIPService r3, VoIpSwitchLayout r4, View r5) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        if (this.accessibilityManager.isTouchExplorationEnabled() == true) goto L7;
    L11:
        r4.setType(VoIpSwitchLayout.Type.CAMERA, !r3.isFrontFaceCamera());
        r0.switchCamera();
        return;
    L7:
        if (r3.isFrontFaceCamera() == false) goto L9;
        String r2 = LocaleController.getString(R.string.AccDescrVoipCamSwitchedToBack);
    L10:
        r5.announceForAccessibility(r2);
        goto L11
    L9:
        r2 = LocaleController.getString(R.string.AccDescrVoipCamSwitchedToFront);
        goto L10
    }

    private /* synthetic */ void lambda$setMicrohoneAction$36(View r6) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        boolean r2 = r0.isMicMute();
        boolean r3 = !r2;
        if (this.accessibilityManager.isTouchExplorationEnabled() == false) goto L10;
        if (r2 == true) goto L8;
        int r22 = R.string.AccDescrVoipMicOff;
    L9:
        r6.announceForAccessibility(LocaleController.getString(r22));
        goto L10
    L8:
        r22 = R.string.AccDescrVoipMicOn;
    L10:
        r0.setMicMute(r3, false, true);
        this.previousState = this.currentState;
        updateViewState();
        return;
    }

    private /* synthetic */ void lambda$setSpeakerPhoneAction$38(int r3, VoIpSwitchLayout r4, VoIPService r5, View r6) {
        if (VoIPService.getSharedInstance() == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        VoIPService.getSharedInstance().toggleSpeakerphoneOrShowRouteSheet(this.activity, false, Integer.valueOf(r3));
        setSpeakerPhoneAction(r4, r5, true);
        return;
    }

    private /* synthetic */ void lambda$setVideoAction$37(View r2) {
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        if (this.activity.checkSelfPermission("android.permission.CAMERA") == 0) goto L6;
        this.activity.requestPermissions(new String[]{"android.permission.CAMERA"}, 102);
        return;
    L6:
        toggleCameraInput();
    }

    private /* synthetic */ void lambda$showErrorDialog$42(DialogInterface r1) {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$startTransitionFromPiP$18(Animator r6) {
        VoIPPiPView.switchingToPip = false;
        VoIPPiPView.finish();
        ViewPropertyAnimator r0 = this.speakerPhoneIcon.animate().setDuration(150);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.DEFAULT;
        r0.setInterpolator(r3).start();
        this.backIcon.animate().alpha(1.0f).setDuration(150).setInterpolator(r3).start();
        this.addIcon.animate().alpha(1.0f).setDuration(150).setInterpolator(r3).start();
        this.emojiLayout.animate().alpha(1.0f).setDuration(150).setInterpolator(r3).start();
        this.statusLayout.animate().alpha(1.0f).setDuration(150).setInterpolator(r3).start();
        this.buttonsLayout.animate().alpha(1.0f).setDuration(150).setInterpolator(r3).start();
        this.bottomShadow.animate().alpha(1.0f).setDuration(350).setInterpolator(r3).start();
        this.topShadow.animate().alpha(1.0f).setDuration(350).setInterpolator(r3).start();
        this.notificationsLayout.animate().alpha(1.0f).setDuration(350).setInterpolator(r3).start();
        r6.addListener(new AnonymousClass15(this));
        r6.setDuration(350);
        r6.setInterpolator(r3);
        r6.start();
    }

    private /* synthetic */ void lambda$startTransitionFromPiP$19() {
        this.windowView.setAlpha(1.0f);
        this.windowView.invalidate();
        Animator r1 = createPiPTransition(true);
        this.backIcon.setAlpha(0.0f);
        this.addIcon.setAlpha(0.0f);
        this.emojiLayout.setAlpha(0.0f);
        this.statusLayout.setAlpha(0.0f);
        this.buttonsLayout.setAlpha(0.0f);
        this.bottomShadow.setAlpha(0.0f);
        this.topShadow.setAlpha(0.0f);
        this.speakerPhoneIcon.setAlpha(0.0f);
        this.notificationsLayout.setAlpha(0.0f);
        this.currentUserCameraFloatingLayout.switchingToPip = true;
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda40(this, r1), 32);
    }

    private /* synthetic */ void lambda$updateButtons$35(View r1) {
        if (VoIPService.getSharedInstance() == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        VoIPService.getSharedInstance().hangUp();
        return;
    }

    private /* synthetic */ void lambda$updateViewState$21() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$22(VoIPService r1, View r2) {
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda44(this));
        int r0 = this.selectedRating;
        if (r0 <= 0) goto L6;
        r1.sendCallRating(r0);
        return;
    }

    private /* synthetic */ void lambda$updateViewState$23(VoIPService r3) {
        this.endCloseLayout.switchToClose(new VoIPFragment$$ExternalSyntheticLambda41(this, r3), true);
    }

    private /* synthetic */ void lambda$updateViewState$24() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$25(VoIPService r1, View r2) {
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda33(this));
        int r0 = this.selectedRating;
        if (r0 <= 0) goto L6;
        r1.sendCallRating(r0);
        return;
    }

    private /* synthetic */ void lambda$updateViewState$26(int r1) {
        this.selectedRating = r1;
    }

    private /* synthetic */ void lambda$updateViewState$27() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$28(AlertDialog r1, int r2) {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$29(boolean[] r4, AlertDialog r5, int r6) {
        r4[0] = true;
        this.currentState = 17;
        Intent r42 = new Intent(this.activity, VoIPService.class);
        r42.putExtra("user_id", this.callingUser.id);
        r42.putExtra("is_outgoing", true);
        r42.putExtra("start_incall_activity", false);
        r42.putExtra("video_call", false);
        r42.putExtra("can_video_call", false);
        r42.putExtra("account", this.currentAccount);
        this.activity.startService(r42);     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$updateViewState$30(boolean[] r1, DialogInterface r2) {
        if (r1[0] == true) goto L6;
        this.windowView.finish();
        return;
    }

    private /* synthetic */ void lambda$updateViewState$31() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$32() {
        this.windowView.finish();
    }

    private /* synthetic */ void lambda$updateViewState$33() {
        VoIPFloatingLayout r1 = this.callingUserMiniFloatingLayout;
        r1.isAppearing = false;
        r1.invalidate();
    }

    private void onBackPressed() {
        if (this.isFinished == false) goto L5;
        return;
    L5:
        if (this.switchingToPip == true) goto L43;
        PrivateVideoPreviewDialogNew r0 = this.previewDialog;
        if (r0 == null) goto L12;
        r0.dismiss(false, false);
        return;
    L12:
        if (this.callingUserIsVideo == false) goto L20;
        if (this.currentUserIsVideo == false) goto L20;
        if (this.cameraForceExpanded == false) goto L20;
        this.cameraForceExpanded = false;
        this.currentUserCameraFloatingLayout.setRelativePosition(this.callingUserMiniFloatingLayout);
        this.currentUserCameraIsFullscreen = false;
        this.previousState = this.currentState;
        updateViewState();
        return;
    L20:
        if (this.emojiExpanded == false) goto L24;
        expandEmoji(false);
        return;
    L24:
        if (this.emojiRationalLayout.getVisibility() == 8) goto L27;
        return;
    L27:
        if (this.canSwitchToPip == true) goto L29;
    L40:
        this.windowView.finish();
        return;
    L29:
        if (VoIPService.getSharedInstance() == null) goto L40;
        if (VoIPService.getSharedInstance().isConverting() == true) goto L40;
        if (this.lockOnScreen == true) goto L40;
        if (PipUtils.checkAnyPipPermissions(this.activity) == false) goto L38;
        switchToPip();
        return;
    L38:
        requestInlinePermissions();
        return;
    }

    public static void onPause() {
        VoIPFragment r0 = instance;
        if (r0 == null) goto L6;
        r0.onPauseInternal();
    L6:
        if (VoIPPiPView.getInstance() == null) goto L9;
        VoIPPiPView.getInstance().onPause();
        return;
    }

    public static void onRequestPermissionsResult(int r1, String[] r2, int[] r3) {
        VoIPFragment r0 = instance;
        if (r0 == null) goto L6;
        r0.onRequestPermissionsResultInternal(r1, r2, r3);
        return;
    }

    @TargetApi(23)
    private void onRequestPermissionsResultInternal(int r3, String[] r4, int[] r5) {
        if (r3 != 101) goto L21;
        if (VoIPService.getSharedState() != null) goto L9;
        this.windowView.finish();
        return;
    L9:
        if (r5.length <= 0) goto L14;
        if (r5[0] != 0) goto L14;
        runAcceptCallAnimation(new VoIPFragment$$ExternalSyntheticLambda0());
    L14:
        if (this.activity.shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") == true) goto L21;
        if (VoIPService.getSharedState() == null) goto L18;
        VoIPService.getSharedState().declineIncomingCall();
    L18:
        VoIPHelper.permissionDenied(this.activity, new VoIPFragment$$ExternalSyntheticLambda1(this), r3);
        return;
    L21:
        if (r3 == 102) goto L23;
        return;
    L23:
        if (VoIPService.getSharedState() != null) goto L27;
        this.windowView.finish();
        return;
    L27:
        if (r5.length > 0) goto L29;
        return;
    L29:
        if (r5[0] != 0) goto L34;
        toggleCameraInput();
        return;
    }

    public static void onResume() {
        VoIPFragment r0 = instance;
        if (r0 == null) goto L6;
        r0.onResumeInternal();
    L6:
        if (VoIPPiPView.getInstance() == null) goto L9;
        VoIPPiPView.getInstance().onResume();
        return;
    }

    @SuppressLint({"InlinedApi"})
    private void requestInlinePermissions() {
        AlertsCreator.createDrawOverlayPermissionDialog(this.activity, new VoIPFragment$$ExternalSyntheticLambda37(this), true).show();
    }

    private void runAcceptCallAnimation(Runnable r17) {
        if (this.bottomVideoBtn.getVisibility() != 0) goto L6;
        this.acceptDeclineView.getLocationOnScreen(new int[2]);
        this.acceptDeclineView.stopAnimations();
        r17.run();
        return;
    L6:
        this.bottomEndCallBtn.animate().cancel();
        this.bottomSpeakerBtn.animate().cancel();
        this.bottomMuteBtn.animate().cancel();
        this.bottomVideoBtn.animate().cancel();
        this.acceptDeclineView.getLocationOnScreen(new int[2]);
        this.acceptDeclineView.stopAnimations();
        this.bottomEndCallBtn.setData(R.drawable.calls_decline, -1, -1041108, LocaleController.getString(R.string.VoipEndCall2), false, false);
        this.bottomSpeakerBtn.setType(VoIpSwitchLayout.Type.SPEAKER, false);
        this.bottomMuteBtn.setType(VoIpSwitchLayout.Type.MICRO, false);
        this.bottomVideoBtn.setType(VoIpSwitchLayout.Type.VIDEO, true);
        this.bottomEndCallBtn.setVisibility(0);
        this.bottomSpeakerBtn.setVisibility(0);
        this.bottomMuteBtn.setVisibility(0);
        this.bottomVideoBtn.setVisibility(0);
        this.bottomEndCallBtn.setAlpha(0.0f);
        this.bottomSpeakerBtn.setAlpha(0.0f);
        this.bottomMuteBtn.setAlpha(0.0f);
        this.bottomVideoBtn.setAlpha(0.0f);
        ViewGroup.MarginLayoutParams r5 = (ViewGroup.MarginLayoutParams) this.acceptDeclineView.getLayoutParams();
        int r3 = r5.getMarginEnd();
        AndroidUtilities.dp(52.0f);
        int r4 = AndroidUtilities.dp(24.0f);
        int r2 = AndroidUtilities.dp(62.0f);
        AnimatorSet r10 = new AnimatorSet();
        ValueAnimator r11 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r11.addUpdateListener(new VoIPFragment$$ExternalSyntheticLambda31(this, r2, r3, r4, r5));
        AcceptDeclineView r0 = this.acceptDeclineView;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r0, View.SCALE_X, new float[]{r0.getScaleX(), 1.0f, 1.0f, 1.0f});
        AcceptDeclineView r22 = this.acceptDeclineView;
        ObjectAnimator r23 = ObjectAnimator.ofFloat(r22, View.SCALE_Y, new float[]{r22.getScaleY(), 1.0f, 1.0f, 1.0f});
        AcceptDeclineView r32 = this.acceptDeclineView;
        r10.playTogether(new Animator[]{r11, r02, r23, ObjectAnimator.ofFloat(r32, View.ALPHA, new float[]{r32.getAlpha(), this.acceptDeclineView.getAlpha(), 0.0f, 0.0f})});
        r10.setDuration(400);
        r10.setInterpolator(new LinearInterpolator());
        r10.addListener(new AnonymousClass10(this, r17));
        r10.start();
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda32(this), 133);
    }

    private void setFrontalCameraAction(VoIpSwitchLayout r3, VoIPService r4, boolean r5) {
        if (this.currentUserIsVideo == true) goto L6;
        r3.setType(VoIpSwitchLayout.Type.CAMERA, false);
        r3.setOnBtnClickedListener(null);
        r3.setEnabled(false);
        return;
    L6:
        r3.setEnabled(true);
        if (r4.isFrontFaceCamera() == false) goto L9;
        r3.setType(VoIpSwitchLayout.Type.CAMERA, true ^ r4.isSwitchingCamera());
    L10:
        r3.setOnBtnClickedListener(new VoIPFragment$$ExternalSyntheticLambda42(this, r4, r3));
        return;
    L9:
        r3.setType(VoIpSwitchLayout.Type.CAMERA, r4.isSwitchingCamera());
        goto L10
    }

    private void setInsets(WindowInsets r3) {
        this.lastInsets = r3;
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) this.buttonsLayout.getLayoutParams();
        r32.bottomMargin = this.lastInsets.getSystemWindowInsetBottom();
        FrameLayout.LayoutParams r33 = (FrameLayout.LayoutParams) this.acceptDeclineView.getLayoutParams();
        r33.bottomMargin = this.lastInsets.getSystemWindowInsetBottom();
        FrameLayout.LayoutParams r34 = (FrameLayout.LayoutParams) this.backIcon.getLayoutParams();
        r34.topMargin = this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r35 = (FrameLayout.LayoutParams) this.addIcon.getLayoutParams();
        r35.topMargin = this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r36 = (FrameLayout.LayoutParams) this.speakerPhoneIcon.getLayoutParams();
        r36.topMargin = AndroidUtilities.dp(56.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r37 = (FrameLayout.LayoutParams) this.statusLayout.getLayoutParams();
        r37.topMargin = AndroidUtilities.dp(135.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r38 = (FrameLayout.LayoutParams) this.emojiLayout.getLayoutParams();
        r38.topMargin = AndroidUtilities.dp(17.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r39 = (FrameLayout.LayoutParams) this.callingUserPhotoViewMini.getLayoutParams();
        r39.topMargin = AndroidUtilities.dp(93.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r310 = (FrameLayout.LayoutParams) this.hideEmojiLayout.getLayoutParams();
        r310.topMargin = this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r311 = (FrameLayout.LayoutParams) this.emojiRationalLayout.getLayoutParams();
        r311.topMargin = AndroidUtilities.dp(118.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r312 = (FrameLayout.LayoutParams) this.rateCallLayout.getLayoutParams();
        r312.topMargin = AndroidUtilities.dp(380.0f) + this.lastInsets.getSystemWindowInsetTop();
        FrameLayout.LayoutParams r313 = (FrameLayout.LayoutParams) this.callingUserMiniFloatingLayout.getLayoutParams();
        r313.bottomMargin = this.lastInsets.getSystemWindowInsetBottom();
        FrameLayout.LayoutParams r314 = (FrameLayout.LayoutParams) this.notificationsLayout.getLayoutParams();
        r314.bottomMargin = this.lastInsets.getSystemWindowInsetBottom();
        this.currentUserCameraFloatingLayout.setInsets(this.lastInsets);
        this.callingUserMiniFloatingLayout.setInsets(this.lastInsets);
        this.fragmentView.requestLayout();
        PrivateVideoPreviewDialogNew r315 = this.previewDialog;
        if (r315 == null) goto L6;
        r315.setBottomPadding(this.lastInsets.getSystemWindowInsetBottom());
        return;
    }

    private void setMicrohoneAction(VoIpSwitchLayout r3, VoIPService r4, boolean r5) {
        r3.setType(VoIpSwitchLayout.Type.MICRO, r4.isMicMute());
        this.currentUserCameraFloatingLayout.setMuted(r4.isMicMute(), r5);
        r3.setOnBtnClickedListener(new VoIPFragment$$ExternalSyntheticLambda38(this));
    }

    private void setSpeakerPhoneAction(VoIpSwitchLayout r4, VoIPService r5, boolean r6) {
        VoipAudioManager r62 = VoipAudioManager.get();
        int r2 = 0;
        if (r5.isBluetoothOn() == false) goto L6;
        r4.setType(VoIpSwitchLayout.Type.BLUETOOTH, false);
        r2 = 2;
    L9:
        r4.setEnabled(true);
        r4.setOnBtnClickedListener(new VoIPFragment$$ExternalSyntheticLambda34(this, r2, r4, r5));
        return;
    L6:
        if (r62.isSpeakerphoneOn() == false) goto L8;
        r4.setType(VoIpSwitchLayout.Type.SPEAKER, true);
        goto L9
    L8:
        r4.setType(VoIpSwitchLayout.Type.SPEAKER, false);
        r2 = 1;
        goto L9
    }

    private void setVideoAction(VoIpSwitchLayout r4, VoIPService r5, boolean r6) {
        if (this.currentUserIsVideo == false) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (r0 == true) goto L12;
        r4.setType(VoIpSwitchLayout.Type.VIDEO, true);
        r4.setOnClickListener(null);
        r4.setEnabled(false);
        return;
    L12:
        if (this.currentUserIsVideo == true) goto L14;
        r4.setType(VoIpSwitchLayout.Type.VIDEO, true, r6);
    L18:
        r4.setOnBtnClickedListener(new VoIPFragment$$ExternalSyntheticLambda43(this));
        r4.setEnabled(true);
        return;
    L14:
        if (r5.isScreencast() == false) goto L16;
        r4.setType(VoIpSwitchLayout.Type.VIDEO, false, r6);
        goto L18
    L16:
        r4.setType(VoIpSwitchLayout.Type.VIDEO, false, r6);
        goto L18
    L5:
        if (this.callingUserIsVideo == true) goto L8;
        r0 = r5.isVideoAvailable();
        goto L10
    }

    public static void show(Activity r5, int r6) {
        VoIPFragment r0 = instance;
        if (r0 == null) goto L14;
        if (r0.windowView.getParent() != null) goto L14;
        VoIPFragment r02 = instance;
        if (r02 == null) goto L12;
        r02.callingUserTextureView.renderer.release();
        instance.currentUserTextureView.renderer.release();
        instance.callingUserMiniTextureRenderer.release();
        VoIPWindowView r03 = instance.windowView;
        if (r03 == null) goto L11;
        r03.finishImmediate();
    L11:
        instance.destroy();
    L12:
        instance = null;
    L14:
        if (instance == null) goto L16;
        return;
    L16:
        if (r5.isFinishing() == false) goto L19;
        return;
    L19:
        if (VoIPPiPView.getInstance() == null) goto L21;
        boolean r04 = true;
    L23:
        if (VoIPService.getSharedState() != null) goto L26;
        return;
    L26:
        if (VoIPService.getSharedState().getUser() == null) goto L46;
        VoIPFragment r2 = new VoIPFragment(r6);
        r2.activity = r5;
        instance = r2;
        AnonymousClass1 r62 = new AnonymousClass1(r5, !r04, r2);
        instance.deviceIsLocked = ((KeyguardManager) r5.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        boolean r3 = ((PowerManager) r5.getSystemService("power")).isInteractive();
        VoIPFragment r4 = instance;
        r4.screenWasWakeup = !r3;
        r62.setLockOnScreen(r4.deviceIsLocked);
        r2.windowView = r62;
        ViewCompat.setOnApplyWindowInsetsListener(r62, new VoIPFragment$$ExternalSyntheticLambda2(r2));
        ((WindowManager) r5.getSystemService("window")).addView(r62, r62.createWindowLayoutParams());
        r62.addView(r2.createView(r5));
        if (r04 == false) goto L31;
        r2.enterTransitionProgress = 0.0f;
        r2.startTransitionFromPiP();
    L32:
        VoIPService r63 = VoIPService.getSharedInstance();
        if (r63 != null) goto L35;
        return;
    L35:
        if (r63.getRemoteVideoState() == 2) goto L37;
        return;
    L37:
        if (PipUtils.checkPermissions(r5) != 1) goto L43;
        VoIPFragment r64 = instance;
        r64.pipSource = new PipSource.Builder(r5, r64).setTagPrefix("voip-fragment-pip").setContentView(instance.callingUserTextureView.renderer).setPlaceholderView(instance.callingUserTextureView.getPlaceholderView()).build();
        return;
    L43:
        return;
    L31:
        r2.enterTransitionProgress = 1.0f;
        r2.updateSystemBarColors();
        goto L32
    L46:
        return;
    L21:
        r04 = false;
        goto L23
    }

    private void showAcceptDeclineView(boolean r5, boolean r6) {
        int r0 = 8;
        Integer r2 = null;
        if (r6 == true) goto L9;
        AcceptDeclineView r62 = this.acceptDeclineView;
        if (r5 == false) goto L7;
        r0 = 0;
    L7:
        r62.setVisibility(r0);
    L20:
        this.acceptDeclineView.setEnabled(r5);
        AcceptDeclineView r4 = this.acceptDeclineView;
        if (r5 == false) goto L23;
        r2 = 1;
    L23:
        r4.setTag(r2);
        return;
    L9:
        if (r5 == true) goto L11;
    L16:
        if (r5 == true) goto L20;
        if (this.acceptDeclineView.getTag() == null) goto L20;
        this.acceptDeclineView.animate().setListener(null).cancel();
        this.acceptDeclineView.animate().setListener(new AnonymousClass22(this)).alpha(0.0f);
        goto L20
    L11:
        if (this.acceptDeclineView.getTag() != null) goto L16;
        this.acceptDeclineView.animate().setListener(null).cancel();
        if (this.acceptDeclineView.getVisibility() != 8) goto L15;
        this.acceptDeclineView.setVisibility(0);
        this.acceptDeclineView.setAlpha(0.0f);
    L15:
        this.acceptDeclineView.animate().alpha(1.0f);
        goto L16
    }

    private void showCallingUserAvatarMini(boolean r10, boolean r11) {
        int r2 = 0;
        if (this.currentUserIsVideo == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Integer r3 = null;
        if (r10 == true) goto L11;
        this.callingUserPhotoViewMini.animate().setListener(null).cancel();
        this.callingUserPhotoViewMini.setTranslationY(0.0f);
        this.callingUserPhotoViewMini.setAlpha(1.0f);
        this.callingUserPhotoViewMini.setScaleX(1.0f);
        this.callingUserPhotoViewMini.setScaleY(1.0f);
        ImageWithWavesView r102 = this.callingUserPhotoViewMini;
        if (r0 == true) goto L30;
        r2 = 8;
    L30:
        r102.setVisibility(r2);
    L31:
        ImageWithWavesView r9 = this.callingUserPhotoViewMini;
        if (r0 == false) goto L34;
        r3 = 1;
    L34:
        r9.setTag(r3);
        return;
    L11:
        if (r0 == true) goto L13;
    L22:
        if (r0 == true) goto L31;
        if (this.callingUserPhotoViewMini.getTag() == null) goto L31;
        this.callingUserPhotoViewMini.animate().setListener(null).cancel();
        this.callingUserPhotoViewMini.setTranslationY(0.0f);
        this.callingUserPhotoViewMini.animate().alpha(0.0f).setDuration(150).scaleX(0.1f).scaleY(0.1f).setInterpolator(CubicBezierInterpolator.DEFAULT).setListener(new AnonymousClass21(this)).start();
        goto L31
    L13:
        if (this.callingUserPhotoViewMini.getTag() != null) goto L22;
        this.callingUserPhotoViewMini.animate().setListener(null).cancel();
        this.callingUserPhotoViewMini.setVisibility(0);
        if (this.emojiExpanded == true) goto L20;
        ImageWithWavesView r103 = this.callingUserPhotoViewMini;
        if (r11 == false) goto L19;
        r103.setAlpha(0.0f);
        this.callingUserPhotoViewMini.animate().alpha(1.0f).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        goto L31
    L19:
        r103.setAlpha(0.0f);
        this.callingUserPhotoViewMini.setTranslationY(-AndroidUtilities.dp(135.0f));
        this.callingUserPhotoViewMini.animate().alpha(1.0f).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        goto L31
    L20:
        if (r11 == false) goto L31;
        this.callingUserPhotoViewMini.setAlpha(0.0f);
        this.callingUserPhotoViewMini.setTranslationY(AndroidUtilities.dp(48.0f));
        this.callingUserPhotoViewMini.setScaleX(0.1f);
        this.callingUserPhotoViewMini.setScaleY(0.1f);
        goto L31
    L5:
        if (this.callingUserIsVideo == true) goto L7;
        r0 = true;
        goto L8
    }

    private void showErrorDialog(CharSequence r3) {
        if (this.activity.isFinishing() == false) goto L5;
        return;
    L5:
        AlertDialog r32 = new DarkAlertDialog.Builder(this.activity).setTitle(LocaleController.getString(R.string.VoipFailed)).setMessage(r3).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        r32.setCanceledOnTouchOutside(true);
        r32.setOnDismissListener(new VoIPFragment$$ExternalSyntheticLambda39(this));
    }

    private void showFloatingLayout(int r11, boolean r12) {
        if (this.currentUserCameraFloatingLayout.getTag() != null) goto L5;
    L6:
        this.currentUserCameraFloatingLayout.setUiVisible(this.uiVisible);
    L7:
        if (r12 == true) goto L11;
        Animator r0 = this.cameraShowingAnimator;
        if (r0 == null) goto L11;
        r0.removeAllListeners();
        this.cameraShowingAnimator.cancel();
    L11:
        boolean r2 = true;
        if (r11 != 0) goto L30;
        VoIPFloatingLayout r6 = this.currentUserCameraFloatingLayout;
        if (r12 == true) goto L16;
        r6.setVisibility(8);
    L66:
        this.currentUserCameraFloatingLayout.setTag(Integer.valueOf(r11));
        return;
    L16:
        if (r6.getTag() == null) goto L66;
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() == 0) goto L66;
        Animator r122 = this.cameraShowingAnimator;
        if (r122 == null) goto L22;
        r122.removeAllListeners();
        this.cameraShowingAnimator.cancel();
    L22:
        AnimatorSet r123 = new AnimatorSet();
        VoIPFloatingLayout r02 = this.currentUserCameraFloatingLayout;
        r123.playTogether(new Animator[]{ObjectAnimator.ofFloat(r02, View.ALPHA, new float[]{r02.getAlpha(), 0.0f})});
        if (this.currentUserCameraFloatingLayout.getTag() != null) goto L25;
    L27:
        this.cameraShowingAnimator = r123;
        r123.addListener(new AnonymousClass20(this));
        this.cameraShowingAnimator.setDuration(250).setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.cameraShowingAnimator.setStartDelay(50);
        this.cameraShowingAnimator.start();
        goto L66
    L25:
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() != 2) goto L27;
        VoIPFloatingLayout r03 = this.currentUserCameraFloatingLayout;
        ObjectAnimator r04 = ObjectAnimator.ofFloat(r03, View.SCALE_X, new float[]{r03.getScaleX(), 0.7f});
        VoIPFloatingLayout r3 = this.currentUserCameraFloatingLayout;
        r123.playTogether(new Animator[]{r04, ObjectAnimator.ofFloat(r3, View.SCALE_Y, new float[]{r3.getScaleX(), 0.7f})});
        goto L27
    L30:
        if (this.currentUserCameraFloatingLayout.getTag() != null) goto L32;
    L35:
        boolean r62 = false;
    L36:
        VoIPFloatingLayout r7 = this.currentUserCameraFloatingLayout;
        if (r12 == true) goto L39;
        r7.setVisibility(0);
    L51:
        if (this.currentUserCameraFloatingLayout.getTag() != null) goto L53;
    L54:
        VoIPFloatingLayout r124 = this.currentUserCameraFloatingLayout;
        if (r124.relativePositionToSetX >= 0.0f) goto L57;
        r124.setRelativePosition(1.0f, 1.0f);
        this.currentUserCameraIsFullscreen = true;
    L57:
        VoIPFloatingLayout r125 = this.currentUserCameraFloatingLayout;
        if (r11 != 2) goto L60;
        boolean r05 = true;
    L61:
        r125.setFloatingMode(r05, r62);
        if (r11 != 2) goto L65;
        r2 = false;
    L65:
        this.currentUserCameraIsFullscreen = r2;
        goto L66
    L60:
        r05 = false;
        goto L61
    L53:
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() == 2) goto L57;
    L39:
        if (r7.getTag() == null) goto L51;
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() != 0) goto L51;
        if (this.currentUserCameraFloatingLayout.getVisibility() != 8) goto L45;
        this.currentUserCameraFloatingLayout.setAlpha(0.0f);
        this.currentUserCameraFloatingLayout.setScaleX(0.7f);
        this.currentUserCameraFloatingLayout.setScaleY(0.7f);
        this.currentUserCameraFloatingLayout.setVisibility(0);
    L45:
        Animator r126 = this.cameraShowingAnimator;
        if (r126 == null) goto L48;
        r126.removeAllListeners();
        this.cameraShowingAnimator.cancel();
    L48:
        AnimatorSet r127 = new AnimatorSet();
        r127.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.currentUserCameraFloatingLayout, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.currentUserCameraFloatingLayout, View.SCALE_X, new float[]{0.7f, 1.0f}), ObjectAnimator.ofFloat(this.currentUserCameraFloatingLayout, View.SCALE_Y, new float[]{0.7f, 1.0f})});
        this.cameraShowingAnimator = r127;
        r127.setDuration(150).start();
        goto L51
    L32:
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() == 0) goto L35;
        r62 = r12;
        goto L36
    L5:
        if (((Integer) this.currentUserCameraFloatingLayout.getTag()).intValue() == 2) goto L7;
        goto L6
    }

    private void showUi(boolean r11) {
        ValueAnimator r0 = this.uiVisibilityAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        int r2 = 0;
        if (r11 == false) goto L8;
    L10:
        if (r11 == true) goto L12;
    L14:
        int r02 = 0;
    L15:
        this.uiVisible = r11;
        this.windowView.requestFullscreen(!r11);
        ViewPropertyAnimator r112 = this.notificationsLayout.animate();
        int r1 = -AndroidUtilities.dp(16.0f);
        if (this.uiVisible == false) goto L18;
        r2 = AndroidUtilities.dp(80.0f);
    L18:
        r112.translationY(r1 - r2).setDuration(150).setStartDelay(r02).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L12:
        if (this.uiVisible == true) goto L14;
        this.tapToVideoTooltip.hide();
        this.encryptionTooltip.hide();
        ViewPropertyAnimator r6 = this.callingUserTitle.animate().alpha(1.0f).setDuration(150).translationY(0.0f);
        CubicBezierInterpolator r8 = CubicBezierInterpolator.DEFAULT;
        r6.setInterpolator(r8).start();
        this.statusTextView.animate().alpha(1.0f).setDuration(150).translationY(0.0f).setInterpolator(r8).start();
        this.speakerPhoneIcon.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setInterpolator(r8).start();
        this.backIcon.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setInterpolator(r8).start();
        this.addIcon.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setInterpolator(r8).start();
        this.emojiLayout.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setInterpolator(r8).start();
        this.buttonsLayout.animate().alpha(1.0f).translationY(0.0f).setDuration(150).setInterpolator(r8).start();
        this.bottomShadow.animate().alpha(1.0f).setDuration(150).setInterpolator(r8).start();
        this.topShadow.animate().alpha(1.0f).setDuration(150).setInterpolator(r8).start();
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.uiVisibilityAlpha, 1.0f});
        this.uiVisibilityAnimator = r03;
        r03.addUpdateListener(this.statusbarAnimatorListener);
        this.uiVisibilityAnimator.setDuration(150).setInterpolator(r8);
        this.uiVisibilityAnimator.start();
        this.buttonsLayout.setEnabled(true);
        goto L14
    L8:
        if (this.uiVisible == false) goto L10;
        ViewPropertyAnimator r62 = this.speakerPhoneIcon.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(10.0f)).setDuration(150);
        CubicBezierInterpolator r82 = CubicBezierInterpolator.DEFAULT;
        r62.setInterpolator(r82).start();
        this.backIcon.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(10.0f)).setDuration(150).setInterpolator(r82).start();
        this.addIcon.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(10.0f)).setDuration(150).setInterpolator(r82).start();
        this.emojiLayout.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(10.0f)).setDuration(150).setInterpolator(r82).start();
        this.callingUserTitle.animate().alpha(0.0f).setDuration(150).translationY(-AndroidUtilities.dp(10.0f)).setInterpolator(r82).start();
        this.statusTextView.animate().alpha(0.0f).setDuration(150).translationY(-AndroidUtilities.dp(10.0f)).setInterpolator(r82).start();
        this.buttonsLayout.animate().alpha(0.0f).translationY(AndroidUtilities.dp(10.0f)).setDuration(150).setInterpolator(r82).start();
        this.bottomShadow.animate().alpha(0.0f).setDuration(150).setInterpolator(r82).start();
        this.topShadow.animate().alpha(0.0f).setDuration(150).setInterpolator(r82).start();
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{this.uiVisibilityAlpha, 0.0f});
        this.uiVisibilityAnimator = r04;
        r04.addUpdateListener(this.statusbarAnimatorListener);
        this.uiVisibilityAnimator.setDuration(150).setInterpolator(r82);
        this.uiVisibilityAnimator.start();
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        this.buttonsLayout.setEnabled(false);
        this.encryptionTooltip.hide();
        r02 = 150;
        goto L15
    }

    private void startWaitingFoHideUi() {
        if (VoIPService.getSharedInstance() == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(this.hideUIRunnable);
        this.hideUiRunnableWaiting = false;
        if (this.canHideUI == true) goto L7;
        return;
    L7:
        if (this.uiVisible == false) goto L12;
        AndroidUtilities.runOnUIThread(this.hideUIRunnable, 3000);
        this.hideUiRunnableWaiting = true;
        return;
    L12:
        return;
    }

    private void toggleCameraInput() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.accessibilityManager.isTouchExplorationEnabled() == false) goto L12;
        if (this.currentUserIsVideo == true) goto L9;
        String r1 = LocaleController.getString(R.string.AccDescrVoipCamOn);
    L10:
        this.fragmentView.announceForAccessibility(r1);
        goto L12
    L9:
        r1 = LocaleController.getString(R.string.AccDescrVoipCamOff);
    L12:
        if (this.currentUserIsVideo == false) goto L14;
        this.currentUserTextureView.saveCameraLastBitmap();
        r0.setVideoState(false, 0);
        r0.clearCamera();
        this.previousState = this.currentState;
        updateViewState();
        return;
    L14:
        if (this.previewDialog != null) goto L26;
        r0.createCaptureDevice(false);
        if (r0.isFrontFaceCamera() == true) goto L18;
        r0.switchCamera();
    L18:
        this.windowView.setLockOnScreen(true);
        this.bottomVideoBtn.getLocationOnScreen(new int[2]);
        AnonymousClass24 r3 = new AnonymousClass24(this, this.fragmentView.getContext(), r0[0], r0[1]);
        this.previewDialog = r3;
        WindowInsets r02 = this.lastInsets;
        if (r02 == null) goto L21;
        r3.setBottomPadding(r02.getSystemWindowInsetBottom());
    L21:
        this.fragmentView.addView(this.previewDialog);
        return;
    }

    private void updateButtons(boolean r12) {
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 != null) goto L6;
        return;
    L6:
        if (r12 == false) goto L8;
        TransitionSet r3 = new TransitionSet();
        Transition r4 = new AnonymousClass23(this).setDuration(250);
        CubicBezierInterpolator r8 = CubicBezierInterpolator.DEFAULT;
        r3.addTransition(r4.setInterpolator(r8)).addTransition(new ChangeBounds().setDuration(250).setInterpolator(r8));
        r3.excludeChildren(VoIPToggleButton.class, true);
        r3.excludeChildren(VoIpSwitchLayout.class, true);
        TransitionManager.beginDelayedTransition(this.buttonsLayout, r3);
    L8:
        int r32 = this.currentState;
        if (r32 != 11) goto L12;
        this.bottomSpeakerBtn.setVisibility(8);
        this.bottomVideoBtn.setVisibility(8);
        this.bottomMuteBtn.setVisibility(8);
        this.bottomEndCallBtn.setVisibility(8);
        return;
    L12:
        int r9 = 0;
        if (r32 != 15) goto L15;
    L31:
        TL_phone.PhoneCall r2 = r1.privateCall;
        if (r2 != null) goto L34;
    L47:
        this.bottomSpeakerBtn.setVisibility(8);
        this.bottomVideoBtn.setVisibility(8);
        this.bottomMuteBtn.setVisibility(8);
    L48:
        this.bottomEndCallBtn.setVisibility(8);
    L50:
        if (this.bottomSpeakerBtn.getVisibility() != 0) goto L53;
        this.bottomSpeakerBtn.animationDelay = 0;
        r9 = 16;
    L53:
        if (this.bottomVideoBtn.getVisibility() != 0) goto L56;
        this.bottomVideoBtn.animationDelay = r9;
        r9 = r9 + 16;
    L56:
        if (this.bottomMuteBtn.getVisibility() != 0) goto L59;
        this.bottomMuteBtn.animationDelay = r9;
        r9 = r9 + 16;
    L59:
        if (this.bottomEndCallBtn.getVisibility() != 0) goto L61;
        this.bottomEndCallBtn.animationDelay = r9;
    L61:
        updateSpeakerPhoneIcon();
        return;
    L34:
        if (r2.video == false) goto L47;
        if (r32 != 15) goto L47;
        if (r1.isScreencast() == false) goto L39;
    L45:
        setSpeakerPhoneAction(this.bottomSpeakerBtn, r1, r12);
        this.speakerPhoneIcon.animate().alpha(0.0f).start();
    L46:
        setVideoAction(this.bottomVideoBtn, r1, false);
        setMicrohoneAction(this.bottomMuteBtn, r1, r12);
        goto L48
    L39:
        if (this.currentUserIsVideo == false) goto L41;
    L42:
        setFrontalCameraAction(this.bottomSpeakerBtn, r1, r12);
        if (this.uiVisible == false) goto L46;
        this.speakerPhoneIcon.animate().alpha(1.0f).start();
        goto L46
    L41:
        if (this.callingUserIsVideo == false) goto L45;
    L15:
        if (r32 == 17) goto L31;
        if (instance != null) goto L21;
        return;
    L21:
        if (r1.isScreencast() == false) goto L23;
    L29:
        setSpeakerPhoneAction(this.bottomSpeakerBtn, r1, r12);
        this.speakerPhoneIcon.setTag(null);
        this.speakerPhoneIcon.animate().alpha(0.0f).start();
    L30:
        setVideoAction(this.bottomVideoBtn, r1, false);
        setMicrohoneAction(this.bottomMuteBtn, r1, r12);
        this.bottomEndCallBtn.setData(R.drawable.calls_decline, -1, -1041108, LocaleController.getString(R.string.VoipEndCall2), false, r12);
        this.bottomEndCallBtn.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda20(this));
        goto L50
    L23:
        if (this.currentUserIsVideo == false) goto L25;
    L26:
        setFrontalCameraAction(this.bottomSpeakerBtn, r1, r12);
        if (this.uiVisible == false) goto L30;
        this.speakerPhoneIcon.setTag(1);
        this.speakerPhoneIcon.animate().alpha(1.0f).start();
        goto L30
    L25:
        if (this.callingUserIsVideo == false) goto L29;
        goto L26
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [org.telegram.ui.Components.AnimatedEmojiDrawable] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v15, types: [org.telegram.ui.Components.BackupImageView[]] */
    /* JADX WARN: Type inference failed for: r4v16, types: [org.telegram.ui.Components.BackupImageView] */
    private void updateKeyView(boolean r10) {
        if (this.emojiLoaded == true) goto L38;
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L39;
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();     // Catch: Exception -> L10
        r1.write(r0.getEncryptionKey());     // Catch: Exception -> L10
        r1.write(r0.getGA());     // Catch: Exception -> L10
        byte[] r02 = r1.toByteArray();     // Catch: Exception -> L10
    L12:
        if (r02 != null) goto L14;
        return;
    L14:
        String[] r03 = EncryptionKeyEmojifier.emojifyForCall(Utilities.computeSHA256(r02, 0, r02.length));
        int r12 = 0;
    L16:
        if (r12 >= 4) goto L31;
        Emoji.preloadEmoji(r03[r12]);
        Emoji.EmojiDrawable r2 = Emoji.getEmojiDrawable(r03[r12]);
        if (r2 == null) goto L30;
        r2.setBounds(0, 0, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        r2.preload();
        int[] r5 = new int[1];
        TextPaint r6 = new TextPaint(1);
        r6.setTextSize(AndroidUtilities.dp(28.0f));
        TLRPC.Document r4 = replaceEmojiToLottieFrame(Emoji.replaceEmoji(r03[r12], r6.getFontMetricsInt(), false, r5), r5);
        if (r4 == null) goto L28;
        Drawable r22 = this.emojiDrawables[r12];
        if ((r22 instanceof AnimatedEmojiDrawable) == true) goto L24;
    L26:
        Drawable[] r23 = this.emojiDrawables;
        AnimatedEmojiDrawable r52 = new AnimatedEmojiDrawable(21, this.currentAccount, r4);
        r23[r12] = r52;
        ?? r24 = r52;
    L27:
        r24.setupEmojiThumb(r03[r12]);
        this.emojiViews[r12].setAnimatedEmojiDrawable(r24);
        this.emojiViews[r12].getImageReceiver().clearImage();
        r2 = r24;
    L29:
        this.emojiViews[r12].setVisibility(8);
        goto L30
    L24:
        if (((AnimatedEmojiDrawable) r22).getDocumentId() != r4.id) goto L26;
        r24 = (AnimatedEmojiDrawable) this.emojiDrawables[r12];
        goto L27
    L28:
        this.emojiViews[r12].setImageDrawable(r2);
        r2 = r2;
    L30:
        this.emojiDrawables[r12] = r2;
        r12 = r12 + 1;
        goto L16
    L31:
        checkEmojiLoaded(r10);
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        r02 = null;
        goto L12
    L39:
        return;
    }

    private void updateSpeakerPhoneIcon() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isBluetoothOn() == false) goto L9;
        int r02 = R.drawable.calls_bluetooth;
    L15:
        int r1 = this.speakerPhoneIconResId;
        ImageView r2 = this.speakerPhoneIcon;
        if (r1 == r02) goto L18;
        AndroidUtilities.updateImageViewImageAnimated(r2, r02);
    L19:
        this.speakerPhoneIconResId = r02;
        return;
    L18:
        r2.setImageResource(r02);
        goto L19
    L9:
        if (VoipAudioManager.get().isSpeakerphoneOn() == false) goto L12;
        r02 = R.drawable.calls_speaker;
        goto L15
    L12:
        if (r0.isHeadsetPlugged() == false) goto L14;
        r02 = R.drawable.calls_menu_headset;
        goto L15
    L14:
        r02 = R.drawable.calls_menu_phone;
        goto L15
    }

    private void updateSystemBarColors() {
        this.overlayPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((this.uiVisibilityAlpha * 102.0f) * this.enterTransitionProgress)));
        this.overlayBottomPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((((this.fillNaviagtionBarValue * 0.5f) + 0.5f) * 255.0f) * this.enterTransitionProgress)));
        ViewGroup r4 = this.fragmentView;
        if (r4 == null) goto L6;
        r4.invalidate();
        return;
    }

    private void updateViewState() {
        if (this.isFinished == false) goto L5;
        return;
    L5:
        if (this.switchingToPip == true) goto L468;
        int r1 = 0;
        this.lockOnScreen = false;
        if (this.previousState == (-1)) goto L10;
        boolean r2 = true;
    L11:
        VoIPServiceState r5 = VoIPService.getSharedState();
        VoIPService r6 = VoIPService.getSharedInstance();
        int r7 = this.currentState;
        if (r7 == 1) goto L105;
        if (r7 == 2) goto L105;
        if (r7 == 3) goto L95;
        if (r7 == 4) goto L66;
        if (r7 == 5) goto L95;
        switch(r7) {
            case 11: goto L39;
            case 12: goto L37;
            case 13: goto L35;
            case 14: goto L34;
            case 15: goto L24;
            case 16: goto L22;
            case 17: goto L19;
            default: goto L106;
        };
    L19:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipBusy), false, r2);
        this.acceptDeclineView.setRetryMod(true);
        this.currentUserIsVideo = false;
        this.callingUserIsVideo = false;
    L20:
        boolean r52 = false;
        boolean r14 = false;
        boolean r12 = true;
    L108:
        if (this.previewDialog == null) goto L111;
        return;
    L111:
        if (this.callingUserIsVideo == false) goto L113;
    L116:
        boolean r13 = true;
    L117:
        if (r6 != null) goto L119;
    L134:
        if (r2 == false) goto L137;
        this.currentUserCameraFloatingLayout.saveRelativePosition();
        this.callingUserMiniFloatingLayout.saveRelativePosition();
    L137:
        if (this.callingUserIsVideo == false) goto L151;
        if (this.switchingToPip == true) goto L141;
        this.gradientLayout.setAlpha(1.0f);
    L141:
        VoIPTextureView r72 = this.callingUserTextureView;
        if (r2 == false) goto L144;
        r72.animate().alpha(1.0f).setDuration(250).start();
    L146:
        if (this.callingUserTextureView.renderer.isFirstFrameRendered() == true) goto L151;
        if (this.enterFromPiP == true) goto L151;
        this.callingUserIsVideo = false;
        goto L151
    L144:
        r72.animate().cancel();
        this.callingUserTextureView.setAlpha(1.0f);
    L151:
        if (this.currentUserIsVideo == false) goto L153;
    L159:
        this.gradientLayout.setVisibility(4);
    L160:
        boolean r73 = this.currentUserIsVideo;
        if (r73 == true) goto L163;
    L164:
        this.cameraForceExpanded = false;
    L165:
        if (r73 == true) goto L167;
    L171:
        boolean r74 = false;
    L172:
        showCallingUserAvatarMini(r2, r13);
        if (this.callingUserPhotoViewMini.getTag() != null) goto L175;
        int r11 = 0;
    L176:
        showAcceptDeclineView(r12, r2);
        VoIPWindowView r10 = this.windowView;
        if (this.lockOnScreen == false) goto L179;
    L182:
        boolean r15 = true;
    L183:
        r10.setLockOnScreen(r15);
        if (this.currentState != 3) goto L190;
        boolean r152 = r13;
        if (this.currentUserIsVideo == false) goto L188;
    L189:
        boolean r102 = true;
    L192:
        this.canHideUI = r102;
        if (r102 == true) goto L198;
        if (this.uiVisible == true) goto L198;
        showUi(true);
    L198:
        if (this.uiVisible == true) goto L200;
    L205:
        boolean r25 = r14;
    L206:
        int r103 = this.currentState;
        if (r103 != 11) goto L209;
    L213:
        boolean r142 = false;
    L214:
        if (r12 == false) goto L216;
    L234:
        boolean r104 = false;
    L236:
        if (r2 == false) goto L256;
        ImageView r122 = this.backIcon;
        if (r142 == false) goto L241;
        r122.animate().alpha(1.0f).start();
    L244:
        ImageView r123 = this.addIcon;
        if (r104 == false) goto L248;
        r123.animate().alpha(1.0f).start();
    L251:
        ViewPropertyAnimator r105 = this.notificationsLayout.animate();
        int r124 = -AndroidUtilities.dp(16.0f);
        if (this.uiVisible == false) goto L254;
        int r143 = AndroidUtilities.dp(80.0f);
    L255:
        r105.translationY(r124 - r143).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L277:
        int r16 = this.currentState;
        if (r16 == 10) goto L281;
        if (r16 == 11) goto L281;
        updateButtons(r2);
    L281:
        if (r25 == false) goto L283;
        this.statusTextView.showTimer(r2);
    L283:
        this.statusTextView.showReconnect(r52, r2);
        if (this.callingUserPhotoViewMini.getVisibility() != 0) goto L293;
        if (this.emojiExpanded == false) goto L293;
        r11 = r11 + AndroidUtilities.dp(24.0f);
        Layout r17 = this.emojiRationalTextView.getLayout();
        if (r17 == null) goto L293;
        int r18 = r17.getLineCount();
        if (r18 <= 2) goto L293;
        r11 = r11 + (AndroidUtilities.dp(20.0f) * (r18 - 2));
    L293:
        if (this.currentState != 11) goto L300;
        if (this.currentUserIsVideo == true) goto L300;
        if (this.callingUserIsVideo == true) goto L300;
        r11 = r11 - AndroidUtilities.dp(24.0f);
    L300:
        if (this.currentUserIsVideo == false) goto L302;
    L303:
        r11 = r11 - AndroidUtilities.dp(60.0f);
    L304:
        if (r2 == true) goto L306;
        this.statusLayout.setTranslationY(r11);
    L321:
        this.statusLayoutAnimateToOffset = r11;
        if (r6 != null) goto L324;
    L326:
        boolean r19 = false;
    L327:
        int r112 = this.currentState;
        if (r112 != 11) goto L330;
    L337:
        boolean r110 = false;
    L338:
        this.canSwitchToPip = r110;
        if (r6 != null) goto L341;
    L403:
        int r111 = this.notificationsLayout.getChildsHight();
        this.callingUserMiniFloatingLayout.setBottomOffset(r111, r2);
        this.currentUserCameraFloatingLayout.setBottomOffset(r111, r2);
        this.currentUserCameraFloatingLayout.setUiVisible(this.uiVisible);
        this.callingUserMiniFloatingLayout.setUiVisible(this.uiVisible);
        if (this.currentUserIsVideo == true) goto L406;
        boolean r53 = true;
        showFloatingLayout(0, r2);
    L414:
        if (r74 == true) goto L416;
    L421:
        if (r74 == false) goto L423;
    L425:
        this.currentUserCameraFloatingLayout.restoreRelativePosition();
        this.callingUserMiniFloatingLayout.restoreRelativePosition();
        updateSpeakerPhoneIcon();
        if (this.currentState != 3) goto L434;
        this.voIpCoverView.onConnected();
        this.callingUserPhotoViewMini.onConnected();
        if (this.gradientLayout.isConnectedCalled() == true) goto L434;
        int[] r113 = new int[2];
        this.callingUserPhotoViewMini.getLocationOnScreen(r113);
        if (this.previousState == (-1)) goto L432;
        boolean r22 = true;
    L433:
        boolean r222 = true;
        this.gradientLayout.switchToCallConnected(r113[0] + AndroidUtilities.dp(106.0f), r113[1] + AndroidUtilities.dp(106.0f), r22);
    L436:
        if (this.currentUserIsVideo == false) goto L438;
    L441:
        boolean r4 = r222;
    L442:
        this.voIpSnowView.setState(r4);
        this.voIpCoverView.setState(r4);
        this.backgroundProvider.setHasVideo(r4);
        if (this.callingUserIsVideo == false) goto L450;
        if (r152 == true) goto L450;
        if (this.isNearEar == false) goto L450;
        this.isNearEar = false;
        if (r6 == null) goto L450;
        r6.playStartRecordSound();
    L450:
        View r114 = this.topShadow;
        if (r4 == true) goto L456;
        if (r114.getVisibility() == 4) goto L458;
        this.topShadow.setVisibility(4);
        this.bottomShadow.setVisibility(4);
    L458:
        AndroidUtilities.cancelRunOnUIThread(this.stopAnimatingBgRunnable);
        if (this.currentState != 3) goto L461;
        AndroidUtilities.runOnUIThread(this.stopAnimatingBgRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    L461:
        PipSource r0 = this.pipSource;
        if (r0 == null) goto L467;
        r0.invalidateActions();
        return;
    L467:
        return;
    L456:
        if (r114.getVisibility() == 0) goto L458;
        this.topShadow.setVisibility(0);
        this.bottomShadow.setVisibility(0);
        goto L458
    L438:
        if (this.callingUserIsVideo == true) goto L441;
        r4 = false;
        goto L442
    L432:
        r22 = false;
    L434:
        r222 = true;
        goto L436
    L423:
        if (this.callingUserMiniFloatingLayout.getTag() == null) goto L425;
        this.callingUserMiniFloatingLayout.setIsActive(false);
        this.callingUserMiniFloatingLayout.animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setListener(new AnonymousClass19(this)).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        this.callingUserMiniFloatingLayout.setTag(null);
        goto L425
    L416:
        if (this.callingUserMiniFloatingLayout.getTag() != null) goto L421;
        this.callingUserMiniFloatingLayout.setIsActive(r53);
        if (this.callingUserMiniFloatingLayout.getVisibility() == 0) goto L420;
        this.callingUserMiniFloatingLayout.setVisibility(0);
        this.callingUserMiniFloatingLayout.setAlpha(0.0f);
        this.callingUserMiniFloatingLayout.setScaleX(0.5f);
        this.callingUserMiniFloatingLayout.setScaleY(0.5f);
    L420:
        this.callingUserMiniFloatingLayout.animate().setListener(null).cancel();
        VoIPFloatingLayout r115 = this.callingUserMiniFloatingLayout;
        r115.isAppearing = true;
        r115.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).setStartDelay(150).withEndAction(new VoIPFragment$$ExternalSyntheticLambda19(this)).start();
        this.callingUserMiniFloatingLayout.setTag(1);
        goto L425
    L406:
        if (this.callingUserIsVideo == true) goto L408;
    L409:
        r53 = true;
        showFloatingLayout(1, r2);
        goto L414
    L408:
        if (this.cameraForceExpanded == true) goto L409;
        showFloatingLayout(2, r2);
        r53 = true;
        goto L414
    L341:
        if (this.currentUserIsVideo == false) goto L343;
        r6.sharedUIParams.tapToVideoTooltipWasShowed = true;
    L343:
        this.currentUserTextureView.setIsScreencast(r6.isScreencast());
        this.currentUserTextureView.renderer.setMirror(r6.isFrontFaceCamera());
        if (this.currentUserIsVideo == true) goto L346;
    L348:
        TextureViewRenderer r116 = null;
    L350:
        if (this.windowViewSkipRender == false) goto L354;
        VoIPTextureView r54 = this.pipTextureView;
        if (r54 == null) goto L354;
    L353:
        TextureViewRenderer r55 = r54.renderer;
    L357:
        r6.setSinks(r116, r55);
        if (r2 == false) goto L360;
        this.notificationsLayout.beforeLayoutChanges();
    L360:
        boolean r117 = r6.isMicMute();
        VoIPNotificationsLayout r56 = this.notificationsLayout;
        if (r117 == false) goto L363;
        r56.addNotification(R.drawable.calls_mute_mini, LocaleController.getString(R.string.VoipMyMicrophoneState), "self-muted", r2);
    L365:
        if (this.currentUserIsVideo == false) goto L367;
    L368:
        int r118 = this.currentState;
        if (r118 == 3) goto L373;
        if (r118 == 5) goto L373;
    L382:
        int r119 = r6.getRemoteAudioState();
        VoIPNotificationsLayout r106 = this.notificationsLayout;
        if (r119 != 0) goto L385;
        r106.addNotification(R.drawable.calls_mute_mini, LocaleController.formatString("VoipUserMicrophoneIsOff", R.string.VoipUserMicrophoneIsOff, new Object[]{r106.ellipsize(UserObject.getFirstName(this.callingUser))}), "muted", r2);
    L386:
        this.notificationsLayout.removeNotification(MediaStreamTrack.VIDEO_TRACK_KIND);
    L388:
        if (this.notificationsLayout.getChildCount() != 0) goto L399;
        if (this.callingUserIsVideo == false) goto L399;
        TL_phone.PhoneCall r120 = r6.privateCall;
        if (r120 == null) goto L399;
        if (r120.video == true) goto L399;
        VoIPService.SharedUIParams r121 = r6.sharedUIParams;
        if (r121.tapToVideoTooltipWasShowed == true) goto L399;
        r121.tapToVideoTooltipWasShowed = true;
        this.tapToVideoTooltip.setTranslationY(-((this.fragmentView.getMeasuredHeight() - this.buttonsLayout.getY()) + AndroidUtilities.dp(6.0f)));
        this.tapToVideoTooltip.setJointPx(0.0f, (this.buttonsLayout.getX() + this.bottomVideoBtn.getX()) + AndroidUtilities.dp(14.0f));
        this.tapToVideoTooltip.show();
    L401:
        if (r2 == false) goto L403;
        this.notificationsLayout.animateLayoutChanges();
    L399:
        if (this.notificationsLayout.getChildCount() == 0) goto L401;
        this.tapToVideoTooltip.hide();
        goto L401
    L385:
        r106.removeNotification("muted");
    L373:
        if (r6.getCallDuration() <= 500) goto L382;
        int r125 = r6.getRemoteAudioState();
        VoIPNotificationsLayout r144 = this.notificationsLayout;
        if (r125 != 0) goto L377;
        r144.addNotification(R.drawable.calls_mute_mini, LocaleController.formatString("VoipUserMicrophoneIsOff", R.string.VoipUserMicrophoneIsOff, new Object[]{r144.ellipsize(UserObject.getFirstName(this.callingUser))}), "muted", r2);
    L378:
        int r126 = r6.getRemoteVideoState();
        VoIPNotificationsLayout r57 = this.notificationsLayout;
        if (r126 != 0) goto L381;
        r57.addNotification(R.drawable.calls_camera_mini, LocaleController.formatString("VoipUserCameraIsOff", R.string.VoipUserCameraIsOff, new Object[]{r57.ellipsize(UserObject.getFirstName(this.callingUser))}), MediaStreamTrack.VIDEO_TRACK_KIND, r2);
        goto L388
    L381:
        r57.removeNotification(MediaStreamTrack.VIDEO_TRACK_KIND);
        goto L388
    L377:
        r144.removeNotification("muted");
        goto L378
    L367:
        if (this.callingUserIsVideo == false) goto L382;
    L363:
        r56.removeNotification("self-muted");
    L354:
        if (r74 == false) goto L356;
        r55 = this.callingUserMiniTextureRenderer;
        goto L357
    L356:
        r54 = this.callingUserTextureView;
        goto L353
    L346:
        if (r6.isScreencast() == true) goto L348;
        r116 = this.currentUserTextureView.renderer;
        goto L350
    L330:
        if (r112 == 17) goto L337;
        if (this.currentUserIsVideo == false) goto L335;
        if (r19 == true) goto L335;
    L336:
        r110 = true;
    L335:
        if (this.callingUserIsVideo == false) goto L337;
    L324:
        if (r6.isScreencast() == false) goto L326;
        r19 = true;
        goto L327
    L306:
        if (this.emojiExpanded == true) goto L308;
    L312:
        long r132 = 250;
        this.statusLayout.animate().setStartDelay(250).alpha(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L314:
        if (r11 == this.statusLayoutAnimateToOffset) goto L321;
        ViewPropertyAnimator r127 = this.statusLayout.animate();
        if (this.currentState == 11) goto L319;
        r132 = 0;
    L319:
        r127.setStartDelay(r132).translationY(r11).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        goto L321
    L308:
        if (this.currentUserIsVideo == false) goto L310;
    L311:
        this.statusLayout.animate().setStartDelay(0).alpha(0.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        r132 = 250;
        goto L314
    L310:
        if (this.callingUserIsVideo == false) goto L312;
    L302:
        if (this.callingUserIsVideo == false) goto L304;
    L254:
        r143 = 0;
        goto L255
    L248:
        if (r123.getVisibility() == 0) goto L250;
        this.addIcon.setVisibility(0);
        this.addIcon.setAlpha(0.0f);
    L250:
        this.addIcon.animate().alpha(0.0f).start();
        goto L251
    L241:
        if (r122.getVisibility() == 0) goto L243;
        this.backIcon.setVisibility(0);
        this.backIcon.setAlpha(0.0f);
    L243:
        this.backIcon.animate().alpha(0.0f).start();
        goto L244
    L256:
        ImageView r128 = this.backIcon;
        if (r142 == true) goto L260;
        r1 = 8;
    L260:
        r128.setVisibility(r1);
        ImageView r129 = this.backIcon;
        if (r142 == false) goto L263;
        float r1210 = 1.0f;
    L264:
        r129.setAlpha(r1210);
        ImageView r130 = this.addIcon;
        if (r104 == false) goto L267;
        int r1211 = 0;
    L268:
        r130.setVisibility(r1211);
        ImageView r131 = this.addIcon;
        if (r104 == false) goto L271;
        float r107 = 1.0f;
    L272:
        r131.setAlpha(r107);
        VoIPNotificationsLayout r133 = this.notificationsLayout;
        int r108 = -AndroidUtilities.dp(16.0f);
        if (this.uiVisible == false) goto L275;
        int r1212 = AndroidUtilities.dp(80.0f);
    L276:
        r133.setTranslationY(r108 - r1212);
        goto L277
    L275:
        r1212 = 0;
        goto L276
    L271:
        r107 = 0.0f;
        goto L272
    L267:
        r1211 = 8;
        goto L268
    L263:
        r1210 = 0.0f;
        goto L264
    L216:
        if (r103 == 16) goto L234;
        if (r103 == 11) goto L234;
        if (r103 == 12) goto L234;
        if (r103 == 14) goto L234;
        if (r103 == 6) goto L234;
        if (this.lockOnScreen == true) goto L234;
        if (this.uiVisible == false) goto L234;
        if (r6 == null) goto L234;
        TL_phone.PhoneCall r109 = r6.privateCall;
        if (r109 == null) goto L234;
        if (r109.conference_supported == false) goto L234;
        r104 = true;
        goto L236
    L209:
        if (this.lockOnScreen == true) goto L213;
        if (this.uiVisible == false) goto L213;
        r142 = true;
        goto L214
    L200:
        if (this.canHideUI == false) goto L205;
        if (this.hideUiRunnableWaiting == true) goto L205;
        if (r6 == null) goto L205;
        r25 = r14;
        AndroidUtilities.runOnUIThread(this.hideUIRunnable, 3000);
        this.hideUiRunnableWaiting = true;
        goto L206
    L188:
        if (this.callingUserIsVideo == true) goto L189;
    L191:
        r102 = false;
        goto L192
    L190:
        r152 = r13;
        goto L191
    L179:
        if (this.deviceIsLocked == true) goto L182;
        r15 = false;
        goto L183
    L175:
        r11 = AndroidUtilities.dp(135.0f) + AndroidUtilities.dp(12.0f);
        goto L176
    L167:
        if (this.cameraForceExpanded == false) goto L171;
        if (AndroidUtilities.isInPictureInPictureMode(this.activity) == true) goto L171;
        r74 = true;
        goto L172
    L163:
        if (this.callingUserIsVideo == true) goto L165;
    L153:
        if (this.callingUserIsVideo == true) goto L159;
        this.gradientLayout.setVisibility(0);
        VoIPTextureView r75 = this.callingUserTextureView;
        if (r2 == false) goto L158;
        r75.animate().alpha(0.0f).setDuration(250).start();
        goto L160
    L158:
        r75.animate().cancel();
        this.callingUserTextureView.setAlpha(0.0f);
        goto L160
    L119:
        if (r6.getRemoteVideoState() != 2) goto L121;
        boolean r76 = true;
    L122:
        this.callingUserIsVideo = r76;
        if (r6.getVideoState(false) != 2) goto L125;
    L128:
        boolean r77 = true;
    L129:
        this.currentUserIsVideo = r77;
        if (r77 == false) goto L134;
        if (this.isVideoCall == true) goto L134;
        this.isVideoCall = true;
        goto L134
    L125:
        if (r6.getVideoState(false) == 1) goto L128;
        r77 = false;
        goto L129
    L121:
        r76 = false;
        goto L122
    L113:
        if (this.currentUserIsVideo == true) goto L116;
        r13 = false;
        goto L117
    L24:
        this.lockOnScreen = false;
        this.acceptDeclineView.setRetryMod(false);
        if (r5 != null) goto L27;
    L29:
        if (r5 != null) goto L31;
    L33:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipInCallBranding), false, r2);
        this.acceptDeclineView.setTranslationY(0.0f);
        goto L20
    L31:
        if (r5.isCallingVideo() == false) goto L33;
        this.statusTextView.setText(LocaleController.getString(R.string.VoipInVideoCallBranding), false, r2);
        this.acceptDeclineView.setTranslationY(-AndroidUtilities.dp(60.0f));
        goto L20
    L27:
        if (r5.isConference() == false) goto L29;
        this.statusTextView.setText(LocaleController.getString(R.string.VoipInConferenceCallBranding), false, r2);
        this.acceptDeclineView.setTranslationY(0.0f);
        goto L20
    L34:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipRequesting), true, r2);
        goto L106
    L35:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipWaiting), true, r2);
        goto L106
    L39:
        if (r6 != null) goto L41;
    L43:
        boolean r58 = false;
    L44:
        this.currentUserTextureView.saveCameraLastBitmap();
        if (r58 == true) goto L47;
    L65:
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda13(this), 200);
        goto L106
    L47:
        if (this.isFinished == true) goto L65;
        if (this.uiVisible == false) goto L51;
        int[] r59 = new int[2];
        int r78 = AndroidUtilities.displaySize.x;
        this.bottomEndCallBtn.getLocationOnScreen(r59);
        int r79 = ((r78 - r59[0]) - ((this.bottomEndCallBtn.getMeasuredWidth() - AndroidUtilities.dp(52.0f)) / 2)) - AndroidUtilities.dp(52.0f);
        ViewGroup.MarginLayoutParams r1010 = (ViewGroup.MarginLayoutParams) this.endCloseLayout.getLayoutParams();
        r1010.rightMargin = r79;
        r1010.leftMargin = r79;
        this.endCloseLayout.setTranslationY(r59[1]);
        this.endCloseLayout.setAlpha(1.0f);
        this.endCloseLayout.setLayoutParams(r1010);
        this.buttonsLayout.animate().alpha(0.0f).setDuration(80).start();
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda10(this, r6), 2);
    L55:
        this.rateCallLayout.setVisibility(0);
        this.rateCallLayout.show(new VoIPFragment$$ExternalSyntheticLambda12(this));
        if (this.emojiExpanded == false) goto L58;
        this.emojiExpanded = false;
        ViewPropertyAnimator r510 = this.hideEmojiLayout.animate().alpha(0.0f).scaleY(0.3f).scaleX(0.3f).setDuration(250);
        CubicBezierInterpolator r1213 = CubicBezierInterpolator.DEFAULT;
        r510.setInterpolator(r1213).setListener(new HideViewAfterAnimation(this.hideEmojiLayout)).start();
        this.emojiLayout.animate().scaleX(1.0f).scaleY(1.0f).translationY(0.0f).setInterpolator(r1213).setDuration(250).start();
        this.emojiRationalLayout.animate().alpha(0.0f).scaleY(0.7f).scaleX(0.7f).translationY(-AndroidUtilities.dp(120.0f)).setListener(new HideViewAfterAnimation(this.hideEmojiLayout)).setDuration(250).setInterpolator(r1213).start();
    L58:
        BackupImageView[] r511 = this.emojiViews;
        int r1214 = r511.length;
        int r145 = 0;
    L59:
        if (r145 >= r1214) goto L61;
        r511[r145].animate().alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(250).start();
        r145 = r145 + 1;
        goto L59
    L61:
        this.callingUserTitle.animate().alpha(0.0f).setDuration(70).setListener(new AnonymousClass18(this)).start();
        this.speakerPhoneIcon.animate().alpha(0.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(250).start();
        this.speakerPhoneIcon.setVisibility(8);
        this.statusTextView.showReconnect(false, true);
        this.statusTextView.showBadConnection(false, true);
        this.statusTextView.setDrawCallIcon();
        this.callingUserPhotoViewMini.onNeedRating();
        updateButtons(true);
        this.bottomEndCallBtn.setVisibility(4);
        this.callingUserMiniFloatingLayout.setAlpha(0.0f);
        this.callingUserMiniFloatingLayout.setVisibility(8);
        this.currentUserCameraFloatingLayout.setAlpha(0.0f);
        this.currentUserCameraFloatingLayout.setVisibility(8);
        PrivateVideoPreviewDialogNew r512 = this.previewDialog;
        if (r512 == null) goto L64;
        r512.dismiss(false, false);
    L64:
        this.notificationsLayout.animate().alpha(0.0f).setDuration(250).start();
        goto L106
    L51:
        this.buttonsLayout.setVisibility(8);
        FrameLayout.LayoutParams r513 = (FrameLayout.LayoutParams) this.endCloseLayout.getLayoutParams();
        r513.rightMargin = AndroidUtilities.dp(18.0f);
        r513.leftMargin = AndroidUtilities.dp(18.0f);
        int r1215 = AndroidUtilities.dp(36.0f);
        r513.bottomMargin = r1215;
        WindowInsets r134 = this.lastInsets;
        if (r134 == null) goto L54;
        r513.bottomMargin = r1215 + r134.getSystemWindowInsetBottom();
    L54:
        r513.gravity = 80;
        this.endCloseLayout.setLayoutParams(r513);
        this.endCloseLayout.animate().alpha(1.0f).setDuration(250).start();
        this.endCloseLayout.switchToClose(new VoIPFragment$$ExternalSyntheticLambda11(this, r6), false);
        goto L55
    L41:
        if (r6.hasRate() == false) goto L43;
        r58 = true;
    L106:
        r52 = false;
        r12 = false;
    L103:
        r14 = r12;
        goto L108
    L22:
        if (this.previousState == 16) goto L106;
        this.statusTextView.setText(LocaleController.getString(R.string.VoipRinging), true, r2);
        goto L106
    L37:
        if (this.previousState == 12) goto L106;
        this.statusTextView.setText(LocaleController.getString(R.string.VoipExchangingKeys), true, r2);
        goto L106
    L66:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipFailed), false, r2);
        VoIPService r514 = VoIPService.getSharedInstance();
        if (r514 == null) goto L69;
        String r515 = r514.getLastError();
    L71:
        if (TextUtils.equals(r515, Instance.ERROR_UNKNOWN) == false) goto L73;
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda18(this), 1000);
        goto L106
    L73:
        if (TextUtils.equals(r515, Instance.ERROR_INCOMPATIBLE) == false) goto L76;
        TLRPC.User r516 = this.callingUser;
        showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString("VoipPeerIncompatible", R.string.VoipPeerIncompatible, new Object[]{ContactsController.formatName(r516.first_name, r516.last_name)})));
        goto L106
    L76:
        if (TextUtils.equals(r515, Instance.ERROR_PEER_OUTDATED) == false) goto L82;
        boolean r517 = this.isVideoCall;
        TLRPC.User r710 = this.callingUser;
        if (r517 == false) goto L80;
        String r518 = LocaleController.formatString("VoipPeerVideoOutdated", R.string.VoipPeerVideoOutdated, new Object[]{UserObject.getFirstName(r710)});
        boolean[] r711 = new boolean[1];
        AlertDialog r519 = new DarkAlertDialog.Builder(this.activity).setTitle(LocaleController.getString(R.string.VoipFailed)).setMessage(AndroidUtilities.replaceTags(r518)).setNegativeButton(LocaleController.getString(R.string.Cancel), new VoIPFragment$$ExternalSyntheticLambda14(this)).setPositiveButton(LocaleController.getString(R.string.VoipPeerVideoOutdatedMakeVoice), new VoIPFragment$$ExternalSyntheticLambda15(this, r711)).show();
        r519.setCanceledOnTouchOutside(true);
        r519.setOnDismissListener(new VoIPFragment$$ExternalSyntheticLambda16(this, r711));
        goto L106
    L80:
        showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString("VoipPeerOutdated", R.string.VoipPeerOutdated, new Object[]{UserObject.getFirstName(r710)})));
        goto L106
    L82:
        if (TextUtils.equals(r515, Instance.ERROR_PRIVACY) == false) goto L85;
        TLRPC.User r520 = this.callingUser;
        showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.CallNotAvailable, new Object[]{ContactsController.formatName(r520.first_name, r520.last_name)})));
        AlertsCreator.showCallsForbidden(this.activity, this.currentAccount, this.callingUser.id, null);
        goto L106
    L85:
        if (TextUtils.equals(r515, Instance.ERROR_AUDIO_IO) == false) goto L88;
        showErrorDialog("Error initializing audio hardware");
        goto L106
    L88:
        if (TextUtils.equals(r515, Instance.ERROR_LOCALIZED) == false) goto L91;
        this.windowView.finish();
        goto L106
    L91:
        if (TextUtils.equals(r515, Instance.ERROR_CONNECTION_SERVICE) == false) goto L93;
        showErrorDialog(LocaleController.getString(R.string.VoipErrorUnknown));
        goto L106
    L93:
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda17(this), 1000);
        goto L106
    L69:
        r515 = Instance.ERROR_UNKNOWN;
    L95:
        updateKeyView(r2);
        if (this.currentState != 5) goto L104;
        r52 = this.wasEstablished;
        if (r52 == false) goto L100;
    L102:
        r12 = false;
        goto L103
    L100:
        if (this.previousState == 5) goto L102;
        this.statusTextView.setText(LocaleController.getString(R.string.VoipConnecting), true, r2);
        goto L102
    L104:
        this.wasEstablished = true;
        r52 = false;
        r12 = false;
        r14 = true;
    L105:
        this.statusTextView.setText(LocaleController.getString(R.string.VoipConnecting), true, r2);
        goto L106
    L10:
        r2 = false;
        goto L11
    }

    public Animator createPiPTransition(boolean r22) {
        this.currentUserCameraFloatingLayout.animate().cancel();
        float r0 = VoIPPiPView.getInstance().windowLayoutParams.x + VoIPPiPView.getInstance().xOffset;
        float r2 = VoIPPiPView.getInstance().windowLayoutParams.y + VoIPPiPView.getInstance().yOffset;
        float r5 = this.currentUserCameraFloatingLayout.getX();
        float r7 = this.currentUserCameraFloatingLayout.getY();
        float r3 = this.currentUserCameraFloatingLayout.getScaleX();
        if (VoIPPiPView.isExpanding() == false) goto L5;
        float r14 = 0.4f;
    L6:
        float r4 = r0 - ((this.callingUserTextureView.getMeasuredWidth() - (this.callingUserTextureView.getMeasuredWidth() * r14)) / 2.0f);
        float r9 = r2 - ((this.callingUserTextureView.getMeasuredHeight() - (this.callingUserTextureView.getMeasuredHeight() * r14)) / 2.0f);
        boolean r10 = this.callingUserIsVideo;
        VoIPFloatingLayout r11 = this.currentUserCameraFloatingLayout;
        if (r10 == false) goto L15;
        int r102 = r11.getMeasuredWidth();
        if (this.currentUserIsVideo == false) goto L12;
        if (r102 == 0) goto L12;
        float r112 = ((this.windowView.getMeasuredWidth() / r102) * r14) * 0.4f;
        float r02 = (((r0 - ((this.currentUserCameraFloatingLayout.getMeasuredWidth() - (this.currentUserCameraFloatingLayout.getMeasuredWidth() * r112)) / 2.0f)) + (VoIPPiPView.getInstance().parentWidth * r14)) - ((VoIPPiPView.getInstance().parentWidth * r14) * 0.4f)) - AndroidUtilities.dp(4.0f);
        float r23 = (((r2 - ((this.currentUserCameraFloatingLayout.getMeasuredHeight() - (this.currentUserCameraFloatingLayout.getMeasuredHeight() * r112)) / 2.0f)) + (VoIPPiPView.getInstance().parentHeight * r14)) - ((VoIPPiPView.getInstance().parentHeight * r14) * 0.4f)) - AndroidUtilities.dp(4.0f);
        boolean r6 = true;
    L13:
        float r8 = r23;
        boolean r24 = r6;
    L14:
        float r62 = r02;
        if (this.callingUserIsVideo == false) goto L19;
        float r03 = AndroidUtilities.dp(4.0f);
    L20:
        float r103 = (AndroidUtilities.dp(4.0f) * 1.0f) / r112;
        if (this.callingUserIsVideo == true) goto L23;
    L26:
        float r13 = 1.0f;
    L28:
        if (r22 == false) goto L32;
        if (r24 == false) goto L31;
        this.currentUserCameraFloatingLayout.setScaleX(r112);
        this.currentUserCameraFloatingLayout.setScaleY(r112);
        this.currentUserCameraFloatingLayout.setTranslationX(r62);
        this.currentUserCameraFloatingLayout.setTranslationY(r8);
        this.currentUserCameraFloatingLayout.setCornerRadius(r103);
        this.currentUserCameraFloatingLayout.setAlpha(r13);
    L31:
        this.callingUserTextureView.setScaleX(r14);
        this.callingUserTextureView.setScaleY(r14);
        this.callingUserTextureView.setTranslationX(r4);
        this.callingUserTextureView.setTranslationY(r9);
        this.callingUserTextureView.setRoundCorners((AndroidUtilities.dp(6.0f) * 1.0f) / r14);
    L32:
        if (r22 == false) goto L34;
        float r12 = 1.0f;
    L35:
        if (r22 == false) goto L38;
        float r15 = 0.0f;
    L37:
        float r19 = r03;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{r12, r15});
        if (r22 == false) goto L42;
        float r152 = 0.0f;
    L43:
        this.enterTransitionProgress = r152;
        updateSystemBarColors();
        r04.addUpdateListener(new VoIPFragment$$ExternalSyntheticLambda35(this, r24, r3, r112, r5, r62, r7, r8, r19, r103, 1.0f, r13, 1.0f, r14, 0.0f, r4, 0.0f, r9));
        return r04;
    L42:
        r152 = 1.0f;
        goto L43
    L38:
        r15 = 1.0f;
        goto L37
    L34:
        r12 = 0.0f;
        goto L35
    L23:
        if (VoIPPiPView.isExpanding() == true) goto L26;
        r13 = 0.0f;
        goto L28
    L19:
        r03 = 0.0f;
    L12:
        r02 = 1.0f;
        r23 = 1.0f;
        r6 = false;
        r112 = 0.0f;
        goto L13
    L15:
        r02 = r0 - ((r11.getMeasuredWidth() - (this.currentUserCameraFloatingLayout.getMeasuredWidth() * r14)) / 2.0f);
        r8 = r2 - ((this.currentUserCameraFloatingLayout.getMeasuredHeight() - (this.currentUserCameraFloatingLayout.getMeasuredHeight() * r14)) / 2.0f);
        r112 = r14;
        r24 = true;
        goto L14
    L5:
        r14 = 0.25f;
        goto L6
    }

    public View createView(Context r28) {
        this.touchSlop = ViewConfiguration.get(r28).getScaledTouchSlop();
        this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(r28, AccessibilityManager.class);
        AnonymousClass2 r7 = new AnonymousClass2(this, r28);
        boolean r8 = false;
        r7.setClipToPadding(false);
        r7.setClipChildren(false);
        r7.setBackgroundColor(-16777216);
        updateSystemBarColors();
        this.fragmentView = r7;
        VoIPServiceState r10 = VoIPService.getSharedState();
        if (r10 != null) goto L5;
    L7:
        boolean r3 = false;
    L8:
        this.gradientLayout = new VoIpGradientLayout(r28, r3, this.backgroundProvider);
        VoIPTextureView r1 = new VoIPTextureView(r28, false, true, false, false);
        this.callingUserTextureView = r1;
        TextureViewRenderer r12 = r1.renderer;
        RendererCommon.ScalingType r32 = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
        r12.setScalingType(r32);
        this.callingUserTextureView.renderer.setEnableHardwareScaler(true);
        this.callingUserTextureView.renderer.setRotateTextureWithScreen(true);
        this.callingUserTextureView.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        r7.addView(this.gradientLayout, LayoutHelper.createFrame(-1, -1.0f));
        VoIpCoverView r13 = new VoIpCoverView(r28, this.callingUser, this.backgroundProvider);
        this.voIpCoverView = r13;
        r7.addView(r13, LayoutHelper.createFrame(-1, -1.0f));
        VoIpSnowView r14 = new VoIpSnowView(r28);
        this.voIpSnowView = r14;
        r7.addView(r14, LayoutHelper.createFrame(-1, 220.0f));
        r7.addView(this.callingUserTextureView);
        GradientDrawable.Orientation r5 = GradientDrawable.Orientation.TOP_BOTTOM;
        new BackgroundGradientDrawable(r5, new int[]{-14994098, -14328963}).startDithering(BackgroundGradientDrawable.Sizes.ofDeviceScreen(BackgroundGradientDrawable.Sizes.Orientation.PORTRAIT), new AnonymousClass3(this));
        VoIPFloatingLayout r15 = new VoIPFloatingLayout(r28);
        this.currentUserCameraFloatingLayout = r15;
        r15.setDelegate(new VoIPFragment$$ExternalSyntheticLambda21(this));
        this.currentUserCameraFloatingLayout.setRelativePosition(1.0f, 1.0f);
        this.currentUserCameraIsFullscreen = true;
        VoIPTextureView r16 = new VoIPTextureView(r28, true, false);
        this.currentUserTextureView = r16;
        r16.renderer.setIsCamera(true);
        this.currentUserTextureView.renderer.setUseCameraRotation(true);
        this.currentUserCameraFloatingLayout.setOnTapListener(new VoIPFragment$$ExternalSyntheticLambda22(this));
        this.currentUserTextureView.renderer.setMirror(true);
        this.currentUserCameraFloatingLayout.addView(this.currentUserTextureView);
        VoIPFloatingLayout r17 = new VoIPFloatingLayout(r28);
        this.callingUserMiniFloatingLayout = r17;
        r17.alwaysFloating = true;
        r17.setFloatingMode(true, false);
        TextureViewRenderer r18 = new TextureViewRenderer(r28);
        this.callingUserMiniTextureRenderer = r18;
        r18.setEnableHardwareScaler(true);
        this.callingUserMiniTextureRenderer.setIsCamera(false);
        this.callingUserMiniTextureRenderer.setFpsReduction(30.0f);
        this.callingUserMiniTextureRenderer.setScalingType(r32);
        this.callingUserMiniFloatingLayout.addView(this.callingUserMiniTextureRenderer, LayoutHelper.createFrame(-1, -2, 17));
        this.callingUserMiniFloatingLayout.setOnTapListener(new VoIPFragment$$ExternalSyntheticLambda23(this));
        this.callingUserMiniFloatingLayout.setVisibility(8);
        r7.addView(this.currentUserCameraFloatingLayout, LayoutHelper.createFrame(-2, -2.0f));
        r7.addView(this.callingUserMiniFloatingLayout);
        View r19 = new View(r28);
        this.bottomShadow = r19;
        r19.setBackground(new GradientDrawable(r5, new int[]{0, ColorUtils.setAlphaComponent(-16777216, 127)}));
        r7.addView(this.bottomShadow, LayoutHelper.createFrame(-1, 160, 80));
        View r110 = new View(r28);
        this.topShadow = r110;
        r110.setBackground(new GradientDrawable(r5, new int[]{ColorUtils.setAlphaComponent(-16777216, 102), 0}));
        r7.addView(this.topShadow, LayoutHelper.createFrame(-1, 160, 48));
        AnonymousClass4 r111 = new AnonymousClass4(this, r28);
        this.emojiLayout = r111;
        r111.setOrientation(0);
        this.emojiLayout.setPadding(0, 0, 0, AndroidUtilities.dp(30.0f));
        this.emojiLayout.setClipToPadding(false);
        this.emojiLayout.setContentDescription(LocaleController.getString(R.string.VoipHintEncryptionKey));
        this.emojiLayout.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda24(this));
        this.hideEmojiTextView = new HideEmojiTextView(r28, this.backgroundProvider);
        FrameLayout r112 = new FrameLayout(r28);
        this.hideEmojiLayout = r112;
        r112.addView(this.hideEmojiTextView, LayoutHelper.createFrame(-2, -2.0f, 3, 0.0f, 16.0f, 0.0f, 0.0f));
        this.hideEmojiLayout.setVisibility(8);
        this.hideEmojiLayout.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda25(this));
        EmojiRationalLayout r113 = new EmojiRationalLayout(r28, this.backgroundProvider);
        this.emojiRationalLayout = r113;
        r113.setOrientation(1);
        TextView r114 = new TextView(r28);
        this.emojiRationalTopTextView = r114;
        r114.setText(LocaleController.getString(R.string.VoipCallEncryptionEndToEnd));
        this.emojiRationalTopTextView.setTextSize(1, 15.0f);
        this.emojiRationalTopTextView.setTypeface(AndroidUtilities.bold());
        this.emojiRationalTopTextView.setTextColor(-1);
        this.emojiRationalTopTextView.setGravity(17);
        AnonymousClass5 r115 = new AnonymousClass5(this, r28);
        this.emojiRationalTextView = r115;
        r115.setTextSize(1, 15.0f);
        this.emojiRationalTextView.setTextColor(-1);
        this.emojiRationalTextView.setGravity(17);
        this.emojiRationalTextView.setText(LocaleController.formatString("CallEmojiKeyTooltip", R.string.CallEmojiKeyTooltip, new Object[]{TextUtils.ellipsize(UserObject.getFirstName(this.callingUser), this.emojiRationalTextView.getPaint(), AndroidUtilities.dp(300.0f), TextUtils.TruncateAt.END)}));
        this.emojiRationalLayout.setVisibility(8);
        this.emojiRationalLayout.addView(this.emojiRationalTopTextView);
        this.emojiRationalLayout.addView(this.emojiRationalTextView, LayoutHelper.createLinear(-2, -2, 0.0f, 8.0f, 0.0f, 0.0f));
        this.emojiRationalLayout.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(80.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
        int r116 = 0;
    L10:
        if (r116 >= 4) goto L16;
        this.emojiViews[r116] = new BackupImageView(r28);
        this.emojiViews[r116].getImageReceiver().setAspectFit(true);
        LinearLayout r9 = this.emojiLayout;
        BackupImageView r142 = this.emojiViews[r116];
        if (r116 != 0) goto L14;
        float r21 = 0.0f;
    L15:
        r9.addView(r142, LayoutHelper.createLinear(25, 25, r21, 0.0f, 0.0f, 0.0f));
        r116 = r116 + 1;
        goto L10
    L14:
        r21 = 6.0f;
        goto L15
    L16:
        AnonymousClass6 r117 = new AnonymousClass6(this, r28);
        this.statusLayout = r117;
        r117.setOrientation(1);
        this.statusLayout.setFocusable(true);
        this.statusLayout.setFocusableInTouchMode(true);
        this.callingUserPhotoViewMini = new ImageWithWavesView(r28);
        AvatarDrawable r118 = new AvatarDrawable();
        r118.setInfo(this.callingUser);
        this.callingUserPhotoViewMini.setImage(ImageLocation.getForUserOrChat(this.currentAccount, this.callingUser, 0), null, r118, this.callingUser);
        this.callingUserPhotoViewMini.setRoundRadius(AndroidUtilities.dp(135.0f) / 2);
        TextView r119 = new TextView(r28);
        this.callingUserTitle = r119;
        r119.setTextSize(1, 28.0f);
        TLRPC.User r120 = this.callingUser;
        this.callingUserTitle.setText(Emoji.replaceEmoji(ContactsController.formatName(r120.first_name, r120.last_name), this.callingUserTitle.getPaint().getFontMetricsInt(), false));
        this.callingUserTitle.setMaxLines(2);
        this.callingUserTitle.setEllipsize(TextUtils.TruncateAt.END);
        this.callingUserTitle.setTextColor(-1);
        this.callingUserTitle.setGravity(1);
        this.callingUserTitle.setImportantForAccessibility(2);
        this.statusLayout.addView(this.callingUserTitle, LayoutHelper.createLinear(-2, -2, 1, 8, 0, 8, 6));
        VoIPStatusTextView r121 = new VoIPStatusTextView(r28, this.backgroundProvider);
        this.statusTextView = r121;
        ViewCompat.setImportantForAccessibility(r121, 4);
        this.statusLayout.addView(this.statusTextView, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 6));
        if (r10 != null) goto L19;
    L25:
        this.statusLayout.setClipChildren(false);
        this.statusLayout.setClipToPadding(false);
        this.statusLayout.setPadding(0, 0, 0, AndroidUtilities.dp(15.0f));
        this.endCloseLayout = new EndCloseLayout(r28);
        this.rateCallLayout = new RateCallLayout(r28, this.backgroundProvider);
        this.endCloseLayout.setAlpha(0.0f);
        this.rateCallLayout.setVisibility(8);
        r7.addView(this.callingUserPhotoViewMini, LayoutHelper.createFrame(Opcodes.SUB_DOUBLE_2ADDR, 204.0f, 1, 0.0f, 93.0f, 0.0f, 0.0f));
        r7.addView(this.statusLayout, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 135.0f, 0.0f, 0.0f));
        r7.addView(this.hideEmojiLayout, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        r7.addView(this.emojiRationalLayout, LayoutHelper.createFrame(304, -2.0f, 49, 0.0f, 118.0f, 0.0f, 0.0f));
        r7.addView(this.emojiLayout, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        r7.addView(this.endCloseLayout, LayoutHelper.createFrame(-1, 52.0f, 5, 0.0f, 0.0f, 0.0f, 0.0f));
        r7.addView(this.rateCallLayout, LayoutHelper.createFrame(-1, -2.0f, 3, 0.0f, 380.0f, 0.0f, 0.0f));
        this.buttonsLayout = new VoIPButtonsLayout(r28);
        this.bottomSpeakerBtn = new VoIpSwitchLayout(r28, this.backgroundProvider);
        this.bottomVideoBtn = new VoIpSwitchLayout(r28, this.backgroundProvider);
        this.bottomMuteBtn = new VoIpSwitchLayout(r28, this.backgroundProvider);
        this.bottomEndCallBtn = new AnonymousClass7(this, r28, 52.0f);
        this.bottomSpeakerBtn.setTranslationY(AndroidUtilities.dp(100.0f));
        this.bottomSpeakerBtn.setScaleX(0.0f);
        this.bottomSpeakerBtn.setScaleY(0.0f);
        this.bottomSpeakerBtn.animate().setStartDelay(150).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(250).start();
        this.bottomVideoBtn.setTranslationY(AndroidUtilities.dp(100.0f));
        this.bottomVideoBtn.setScaleX(0.0f);
        this.bottomVideoBtn.setScaleY(0.0f);
        this.bottomVideoBtn.animate().setStartDelay(166).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(250).start();
        this.bottomMuteBtn.setTranslationY(AndroidUtilities.dp(100.0f));
        this.bottomMuteBtn.setScaleX(0.0f);
        this.bottomMuteBtn.setScaleY(0.0f);
        this.bottomMuteBtn.animate().setStartDelay(182).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(250).start();
        this.bottomEndCallBtn.setTranslationY(AndroidUtilities.dp(100.0f));
        this.bottomEndCallBtn.setScaleX(0.0f);
        this.bottomEndCallBtn.setScaleY(0.0f);
        this.bottomEndCallBtn.animate().setStartDelay(198).translationY(0.0f).scaleY(1.0f).scaleX(1.0f).setDuration(250).start();
        this.buttonsLayout.addView(this.bottomSpeakerBtn);
        this.buttonsLayout.addView(this.bottomVideoBtn);
        this.buttonsLayout.addView(this.bottomMuteBtn);
        this.buttonsLayout.addView(this.bottomEndCallBtn);
        AcceptDeclineView r122 = new AcceptDeclineView(r28);
        this.acceptDeclineView = r122;
        r122.setListener(new AnonymousClass8(this));
        this.acceptDeclineView.setScaleX(1.15f);
        this.acceptDeclineView.setScaleY(1.15f);
        r7.addView(this.buttonsLayout, LayoutHelper.createFrame(-1, -2, 80));
        if (AndroidUtilities.isTablet() == false) goto L28;
        int r123 = 100;
    L29:
        float r124 = r123;
        r7.addView(this.acceptDeclineView, LayoutHelper.createFrame(-1, 186.0f, 80, r124, 0.0f, r124, 0.0f));
        ImageView r125 = new ImageView(r28);
        this.addIcon = r125;
        r125.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(-1, 76)));
        this.addIcon.setImageResource(R.drawable.msg_addcontact);
        this.addIcon.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r7.addView(this.addIcon, LayoutHelper.createFrame(56, 56, 53));
        ScaleStateListAnimator.apply(this.addIcon);
        ImageView r126 = new ImageView(r28);
        this.backIcon = r126;
        r126.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(-1, 76)));
        this.backIcon.setImageResource(R.drawable.msg_call_minimize_shadow);
        this.backIcon.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.backIcon.setContentDescription(LocaleController.getString(R.string.Back));
        r7.addView(this.backIcon, LayoutHelper.createFrame(56, 56, 51));
        ScaleStateListAnimator.apply(this.backIcon);
        AnonymousClass9 r127 = new AnonymousClass9(this, r28);
        this.speakerPhoneIcon = r127;
        r127.setContentDescription(LocaleController.getString(R.string.VoipSpeaker));
        this.speakerPhoneIcon.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(-1, 76)));
        this.speakerPhoneIcon.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r7.addView(this.speakerPhoneIcon, LayoutHelper.createFrame(56, 56.0f, 53, 0.0f, 56.0f, 0.0f, 0.0f));
        this.speakerPhoneIcon.setAlpha(0.0f);
        this.speakerPhoneIcon.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda26(this));
        this.backIcon.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda27(this));
        this.addIcon.setOnClickListener(new VoIPFragment$$ExternalSyntheticLambda28(this, r28));
        if (this.windowView.isLockOnScreen() == false) goto L32;
        this.backIcon.setVisibility(8);
        this.addIcon.setVisibility(8);
    L32:
        VoIPNotificationsLayout r128 = new VoIPNotificationsLayout(r28, this.backgroundProvider);
        this.notificationsLayout = r128;
        r128.setGravity(80);
        this.notificationsLayout.setOnViewsUpdated(new VoIPFragment$$ExternalSyntheticLambda29(this));
        r7.addView(this.notificationsLayout, LayoutHelper.createFrame(-1, 200.0f, 80, 16.0f, 0.0f, 16.0f, 0.0f));
        HintView2 r129 = new VoIpHintView(r28, 3, this.backgroundProvider, true).setMultilineText(true);
        Layout.Alignment r33 = Layout.Alignment.ALIGN_CENTER;
        HintView2 r130 = r129.setTextAlign(r33).setDuration(-1).setOnHiddenListener(new VoIPFragment$$ExternalSyntheticLambda30(this)).setHideByTouch(true).setMaxWidth(320.0f).useScale(true).setInnerPadding(10.0f, 6.0f, 10.0f, 6.0f).setRounding(8.0f);
        this.tapToVideoTooltip = r130;
        r130.setText(LocaleController.getString(R.string.TapToTurnCamera));
        r7.addView(this.tapToVideoTooltip, LayoutHelper.createFrame(-2, -2.0f, 80, 19.0f, 0.0f, 19.0f, 0.0f));
        HintView2 r131 = new VoIpHintView(r28, 1, this.backgroundProvider, false).setMultilineText(true).setTextAlign(r33).setDuration(4000).setHideByTouch(true).setMaxWidth(320.0f).useScale(true).setInnerPadding(10.0f, 6.0f, 10.0f, 6.0f).setRounding(8.0f);
        this.encryptionTooltip = r131;
        r131.setText(LocaleController.getString(R.string.VoipHintEncryptionKey));
        r7.addView(this.encryptionTooltip, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        updateViewState();
        VoIPService r132 = VoIPService.getSharedInstance();
        if (r132 != null) goto L35;
    L43:
        return r7;
    L35:
        if (this.isVideoCall == true) goto L42;
        TL_phone.PhoneCall r133 = r132.privateCall;
        if (r133 != null) goto L39;
    L41:
        this.isVideoCall = r8;
        goto L42
    L39:
        if (r133.video == false) goto L41;
        r8 = true;
    L42:
        initRenderers();
        goto L43
    L28:
        r123 = 27;
        goto L29
    L19:
        if (r10.getUser() == null) goto L25;
        if (r10.isConference() == false) goto L25;
        if (r10.getGroupCall() == null) goto L25;
        ConferenceParticipantsView r134 = new ConferenceParticipantsView(r28);
        this.participantsView = r134;
        r134.set(this.currentAccount, r10.getUser().id, r10.getGroupParticipants(), r10.getGroupCall().participants_count);
        this.statusLayout.addView(this.participantsView, LayoutHelper.createLinear(-1, 30, 0.0f, 22.0f, 0.0f, 0.0f));
        goto L25
    L5:
        if (r10.isConference() == false) goto L7;
        r3 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.voipServiceCreated) goto L11;
        if (this.currentState == 17) goto L7;
        return;
    L7:
        if (VoIPService.getSharedInstance() == null) goto L29;
        this.currentUserTextureView.renderer.release();
        this.callingUserTextureView.renderer.release();
        this.callingUserMiniTextureRenderer.release();
        initRenderers();
        VoIPService.getSharedInstance().registerStateListener(this);
        return;
    L29:
        return;
    L11:
        if (r3 != NotificationCenter.emojiLoaded) goto L15;
        updateKeyView(true);
        return;
    L15:
        if (r3 != NotificationCenter.closeInCallActivity) goto L19;
        this.windowView.finish();
        return;
    L19:
        if (r3 != NotificationCenter.webRtcSpeakerAmplitudeEvent) goto L23;
        this.callingUserPhotoViewMini.setAmplitude(((Float) r5[0]).floatValue() * 15.0f);
        return;
    L23:
        if (r3 != NotificationCenter.nearEarEvent) goto L30;
        boolean r32 = ((Boolean) r5[0]).booleanValue();
        this.isNearEar = r32;
        if (r32 == false) goto L31;
        this.callingUserPhotoViewMini.setMute(true, true);
        return;
    L31:
        return;
    }

    public void finish() {
        this.windowView.finish();
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
        updateButtons(true);
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraSwitch(boolean r1) {
        this.previousState = this.currentState;
        updateViewState();
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onMediaStateUpdated(int r2, int r3) {
        this.previousState = this.currentState;
        if (r3 == 2) goto L5;
    L7:
        VoIPService r32 = VoIPService.getSharedInstance();
        if (r32 != null) goto L10;
    L16:
        PipSource r22 = this.pipSource;
        if (r22 == null) goto L19;
        r22.destroy();
        this.pipSource = null;
    L19:
        updateViewState();
        return;
    L10:
        if (r32.getRemoteVideoState() != 2) goto L16;
        if (this.pipSource != null) goto L19;
        if (PipUtils.checkPermissions(this.activity) != 1) goto L19;
        this.pipSource = new PipSource.Builder(this.activity, instance).setTagPrefix("voip-fragment-pip").setContentView(this.callingUserTextureView.renderer).setPlaceholderView(this.callingUserTextureView.getPlaceholderView()).build();
        goto L19
    L5:
        if (this.isVideoCall == true) goto L7;
        this.isVideoCall = true;
        goto L7
    }

    public void onPauseInternal() {
        boolean r0 = ((PowerManager) this.activity.getSystemService("power")).isInteractive();
        int r1 = PipUtils.checkPermissions(this.activity);
        if (r1 <= 0) goto L5;
        boolean r4 = true;
    L7:
        if (this.canSwitchToPip == false) goto L18;
        if (VoIPService.getSharedInstance() == null) goto L18;
        if (VoIPService.getSharedInstance().isConverting() == true) goto L18;
        if (r1 != 2) goto L18;
        int r12 = instance.windowView.getMeasuredHeight();
        VoIPFragment r5 = instance;
        VoIPPiPView.show(r5.activity, r5.currentAccount, r5.windowView.getMeasuredWidth(), r12, 0);
        WindowInsets r13 = instance.lastInsets;
        if (r13 == null) goto L18;
        VoIPPiPView.topInset = r13.getSystemWindowInsetTop();
        VoIPPiPView.bottomInset = instance.lastInsets.getSystemWindowInsetBottom();
    L18:
        if (this.currentUserIsVideo == false) goto L25;
        if (r4 == false) goto L21;
        if (r0 == false) goto L21;
        return;
    L21:
        VoIPService r8 = VoIPService.getSharedInstance();
        if (r8 == null) goto L27;
        r8.setVideoState(false, 1);
        return;
    L27:
        return;
    L25:
        return;
    L5:
        r4 = false;
        goto L7
    }

    public void onResumeInternal() {
        if (VoIPPiPView.getInstance() == null) goto L5;
        VoIPPiPView.finish();
    L5:
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L12;
        if (r0.getVideoState(false) != 1) goto L10;
        r0.setVideoState(false, 2);
    L10:
        updateViewState();
    L14:
        this.deviceIsLocked = ((KeyguardManager) this.activity.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        return;
    L12:
        if (VoIPService.getSharedState() != null) goto L14;
        this.windowView.finish();
        goto L14
    }

    public void onScreenCastStart() {
        PrivateVideoPreviewDialogNew r1 = this.previewDialog;
        if (r1 != null) goto L5;
        return;
    L5:
        r1.dismiss(true, true);
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onScreenOnChange(boolean r1) {
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onSignalBarsCountChanged(int r2) {
        if (r2 <= 0) goto L5;
        this.signalBarWasReceived = true;
    L5:
        if (this.statusTextView == null) goto L14;
        VoIpGradientLayout r0 = this.gradientLayout;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.isConnectedCalled() == true) goto L11;
        return;
    L11:
        if (this.signalBarWasReceived == false) goto L17;
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda9(this, r2), 400);
        return;
    L17:
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r2) {
        int r0 = this.currentState;
        if (r0 == r2) goto L8;
        this.previousState = r0;
        this.currentState = r2;
        if (this.windowView == null) goto L9;
        updateViewState();
        return;
    L9:
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onVideoAvailableChange(boolean r2) {
        this.previousState = this.currentState;
        if (r2 == true) goto L5;
    L7:
        updateViewState();
        return;
    L5:
        if (this.isVideoCall == true) goto L7;
        this.isVideoCall = true;
        goto L7
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public View pipCreatePictureInPictureView() {
        VoIPTextureView r0 = new VoIPTextureView(this.activity, false, true, false, false);
        this.pipTextureView = r0;
        r0.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        this.pipTextureView.renderer.setEnableHardwareScaler(true);
        this.pipTextureView.renderer.setRotateTextureWithScreen(true);
        VoIPTextureView r02 = this.pipTextureView;
        r02.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        r02.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass25(this));
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
    public void pipHidePrimaryWindowView(Runnable r2) {
        this.firstFrameCallback = r2;
        VoIPTextureView r22 = this.callingUserTextureView;
        if (r22 == null) goto L5;
        r22.renderer.clearFirstFrame();
    L5:
        this.windowViewSkipRender = true;
        updateViewState();
        ((WindowManager) this.activity.getSystemService("window")).removeView(this.windowView);
        this.windowView.invalidate();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipRenderBackground(Canvas r1) {
        r1.drawColor(-14999773);
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipShowPrimaryWindowView(Runnable r3) {
        this.firstFrameCallback = r3;
        WindowManager r32 = (WindowManager) this.activity.getSystemService("window");
        VoIPWindowView r0 = this.windowView;
        r32.addView(r0, r0.createWindowLayoutParams());
        this.windowViewSkipRender = false;
        updateViewState();
        this.windowView.invalidate();
        VoIPTextureView r33 = this.pipTextureView;
        if (r33 == null) goto L6;
        r33.renderer.release();
        this.pipTextureView = null;
        return;
    }

    public TLRPC.Document replaceEmojiToLottieFrame(CharSequence r12, int[] r13) {
        if ((r12 instanceof Spannable) == true) goto L5;
        return null;
    L5:
        Spannable r122 = (Spannable) r12;
        Emoji.EmojiSpan[] r0 = (Emoji.EmojiSpan[]) r122.getSpans(0, r122.length(), Emoji.EmojiSpan.class);
        AnimatedEmojiSpan[] r123 = (AnimatedEmojiSpan[]) r122.getSpans(0, r122.length(), AnimatedEmojiSpan.class);
        if (r0 == null) goto L31;
        if (r13 != null) goto L9;
        int r132 = 0;
    L10:
        int r133 = r132 - r0.length;
        if (r123 != null) goto L13;
        int r124 = 0;
    L15:
        if ((r133 - r124) > 0) goto L31;
        int r125 = r0.length;
        int r134 = 0;
    L18:
        if (r134 >= r125) goto L31;
        Emoji.EmojiSpan r2 = r0[r134];
        TLRPC.TL_inputStickerSetShortName r5 = new TLRPC.TL_inputStickerSetShortName();
        r5.short_name = "StaticEmoji";
        TLRPC.TL_messages_stickerSet r4 = MediaDataController.getInstance(this.currentAccount).getStickerSet(r5, 0, false, true, new VoIPFragment$$ExternalSyntheticLambda8(this));
        if (r4 == null) goto L21;
        String r22 = r2.emoji.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
        ArrayList<TLRPC.Document> r42 = r4.documents;
        int r7 = r42.size();
        int r8 = 0;
    L23:
        if (r8 >= r7) goto L27;
        TLRPC.Document r9 = r42.get(r8);
        r8 = r8 + 1;
        TLRPC.Document r92 = r9;
        if (TextUtils.equals(MessageObject.findAnimatedEmojiEmoticon(r92, null).replace("️", _UrlKt.FRAGMENT_ENCODE_SET), r22) == false) goto L23;
    L28:
        if (r92 != null) goto L29;
        r134 = r134 + 1;
        goto L18
    L29:
        return r92;
    L27:
        r92 = null;
        goto L28
    L21:
        return null;
    L13:
        r124 = r123.length;
        goto L15
    L9:
        r132 = r13[0];
    L31:
        return null;
    }

    public void startTransitionFromPiP() {
        this.enterFromPiP = true;
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 != null) goto L5;
    L7:
        this.windowView.setAlpha(0.0f);
        updateViewState();
        this.switchingToPip = true;
        VoIPPiPView.switchingToPip = true;
        VoIPPiPView.prepareForTransition();
        this.notificationsLocker.lock();
        AndroidUtilities.runOnUIThread(new VoIPFragment$$ExternalSyntheticLambda4(this), 32);
        return;
    L5:
        if (r1.getVideoState(false) != 2) goto L7;
        this.callingUserTextureView.setStub(VoIPPiPView.getInstance().callingUserTextureView);
        this.currentUserTextureView.setStub(VoIPPiPView.getInstance().currentUserTextureView);
        goto L7
    }

    public void switchToPip() {
        if (this.isFinished == false) goto L5;
        return;
    L5:
        if (instance == null) goto L18;
        this.isFinished = true;
        if (VoIPService.getSharedInstance() == null) goto L13;
        int r1 = instance.windowView.getMeasuredHeight();
        VoIPFragment r2 = instance;
        VoIPPiPView.show(r2.activity, r2.currentAccount, r2.windowView.getMeasuredWidth(), r1, 1);
        WindowInsets r12 = instance.lastInsets;
        if (r12 == null) goto L13;
        VoIPPiPView.topInset = r12.getSystemWindowInsetTop();
        VoIPPiPView.bottomInset = instance.lastInsets.getSystemWindowInsetBottom();
    L13:
        if (VoIPPiPView.getInstance() == null) goto L19;
        ViewPropertyAnimator r13 = this.speakerPhoneIcon.animate().alpha(0.0f).setDuration(150);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.DEFAULT;
        r13.setInterpolator(r5).start();
        this.backIcon.animate().alpha(0.0f).setDuration(150).setInterpolator(r5).start();
        this.addIcon.animate().alpha(0.0f).setDuration(150).setInterpolator(r5).start();
        this.emojiLayout.animate().alpha(0.0f).setDuration(150).setInterpolator(r5).start();
        this.statusLayout.animate().alpha(0.0f).setDuration(150).setInterpolator(r5).start();
        this.buttonsLayout.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).start();
        this.bottomShadow.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).start();
        this.topShadow.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).start();
        this.callingUserMiniFloatingLayout.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).start();
        this.notificationsLayout.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).start();
        VoIPPiPView.switchingToPip = true;
        this.switchingToPip = true;
        Animator r0 = createPiPTransition(false);
        this.notificationsLocker.lock();
        r0.addListener(new AnonymousClass14(this));
        r0.setDuration(350);
        r0.setInterpolator(r5);
        r0.start();
        return;
    L19:
        return;
    }
}
