package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.Button;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ProfileActivity;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ProfileActionsView extends View {
    private AccessibilityNodeProvider accessibilityNodeProvider;
    private final List<Action> actions;
    private int activeCount;
    private final Set<Integer> allAvailableActions;
    private ProfileActivity.AvatarImageView avatarView;
    private Action callAction;
    private boolean callAnimationStateLoaded;
    private float callBackwardAnimateFromX;
    private float callBackwardAnimateFromY;
    private final Path clipAvatarPath;
    public float clipHeight;
    private final Path clipPath;
    private int color;
    private float currentHeight;
    private long downTime;
    private float downX;
    private float downY;
    private Action firstAction;
    private boolean hasColorById;
    private Action hit;
    private boolean ignoreRect;
    public boolean isAnimatingCallAction;
    private boolean isApplying;
    private boolean isNotificationsEnabled;
    public boolean isOpeningLayout;
    private Action lastAction;
    private ColorFilter lastColorFilter;
    private int lastColorFilterColor;
    private final Matrix matrix;
    public int mode;
    private OnActionClickListener onActionClickListener;
    private final Paint paint;
    private float parentExpanded;
    private final Path pathTmp;
    private RadialGradient radialGradient;
    private final float[] radii;
    private RenderNode renderNode;
    private float renderNodeScale;
    private float renderNodeTranslateY;
    private final Paint shaderPaint;
    private final int targetHeight;
    private int textColor;
    final float textPadding;
    final float top;
    final float xpadding;
    final float ypadding;

    /* renamed from: org.telegram.ui.Components.ProfileActionsView$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityNodeProvider {
        final /* synthetic */ ProfileActionsView this$0;

        public AnonymousClass1(ProfileActionsView r1) {
            this.this$0 = r1;
        }

        private void sendAccessibilityEventForVirtualView(int r2, int r3) {
            sendAccessibilityEventForVirtualView(r2, r3, null);
        }

        private void sendAccessibilityEventForVirtualView(int r3, int r4, String r5) {
            if (((AccessibilityManager) this.this$0.getContext().getSystemService("accessibility")).isTouchExplorationEnabled() == false) goto L11;
            AccessibilityEvent r42 = AccessibilityEvent.obtain(r4);
            r42.setPackageName(this.this$0.getContext().getPackageName());
            r42.setSource(this.this$0, r3);
            if (r5 == null) goto L8;
            r42.getText().add(r5);
        L8:
            if (this.this$0.getParent() == null) goto L12;
            this.this$0.getParent().requestSendAccessibilityEvent(this.this$0, r42);
            return;
        L12:
            return;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int r8) {
            int r0 = 0;
            int[] r1 = {0, 0};
            this.this$0.getLocationOnScreen(r1);
            if (r8 != (-1)) goto L9;
            AccessibilityNodeInfo r82 = AccessibilityNodeInfo.obtain(this.this$0);
            this.this$0.onInitializeAccessibilityNodeInfo(r82);
            r82.setEnabled(true);
        L6:
            if (r0 >= ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).size()) goto L8;
            ProfileActionsView r12 = this.this$0;
            r82.addChild(r12, ((Action) ProfileActionsView.m12195$$Nest$fgetactions(r12).get(r0)).key);
            r0 = r0 + 1;
            goto L6
        L8:
            return r82;
        L9:
            int r2 = 0;
        L11:
            if (r2 >= ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).size()) goto L16;
            if (((Action) ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).get(r2)).key == r8) goto L14;
            r2 = r2 + 1;
            goto L11
        L14:
            Action r22 = (Action) ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).get(r2);
        L17:
            if (r22 != null) goto L20;
            return null;
        L20:
            if (r22.rect.isEmpty() == false) goto L22;
            return null;
        L22:
            AccessibilityNodeInfo r4 = AccessibilityNodeInfo.obtain();
            r4.setSource(this.this$0, r8);
            r4.setParent(this.this$0);
            r4.setPackageName(this.this$0.getContext().getPackageName());
            r4.addAction(16);
            r4.addAction(64);
            r4.setClickable(true);
            r4.setFocusable(true);
            r4.setEnabled(true);
            r4.setVisibleToUser(true);
            r4.setClassName(Button.class.getName());
            r4.setText(Action.m12206$$Nest$fgettext(r22).getText());
            RectF r83 = r22.rect;
            Rect r7 = new Rect((int) r83.left, (int) r83.top, (int) r83.right, (int) r83.bottom);
            r4.setBoundsInParent(r7);
            r7.offset(r1[0], r1[1]);
            r4.setBoundsInScreen(r7);
            return r4;
        L16:
            r22 = null;
            goto L17
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int r3, int r4, Bundle r5) {
            if (r3 == (-1)) goto L5;
            int r0 = 0;
        L8:
            if (r0 >= ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).size()) goto L13;
            if (((Action) ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).get(r0)).key == r3) goto L11;
            r0 = r0 + 1;
            goto L8
        L11:
            Action r02 = (Action) ProfileActionsView.m12195$$Nest$fgetactions(this.this$0).get(r0);
        L14:
            if (r02 != null) goto L17;
            return false;
        L17:
            if (r4 != 64) goto L21;
            sendAccessibilityEventForVirtualView(r3, 32768);
            return true;
        L21:
            if (r4 == 16) goto L23;
            return false;
        L23:
            if (ProfileActionsView.m12198$$Nest$fgetonActionClickListener(this.this$0) == null) goto L25;
            ProfileActionsView.m12198$$Nest$fgetonActionClickListener(this.this$0).onClick(r3, 0.0f, 0.0f);
        L25:
            return true;
        L13:
            r02 = null;
            goto L14
        L5:
            return this.this$0.performAccessibilityAction(r4, r5);
        }
    }

    public class Action {
        private final ButtonBounce bounce;
        private final Rect bounds;
        int callDelay;
        private RLottieDrawable drawableAnimated;
        private Drawable drawableFilled;
        private Drawable drawableOutline;
        private final RectF from;
        float iconScale;
        int iconTranslationY;
        boolean isDeleted;
        boolean isDeleting;
        boolean isLoading;
        boolean isOpening;
        int key;
        LoadingDrawable loadingDrawable;
        private final AnimatedFloat positionFraction;
        final RectF prevRect;
        final RectF rect;
        private final Drawable rippleDrawable;
        long startTime;
        int stopDelay;
        int supportsAnimate;
        boolean supportsLoading;
        private Text text;
        private float textScale;
        final /* synthetic */ ProfileActionsView this$0;
        private final RectF to;
        boolean wasFirst;
        boolean wasLast;

        /* renamed from: -$$Nest$fgetbounce, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonBounce m12200$$Nest$fgetbounce(Action r0) {
            return r0.bounce;
        }

        /* renamed from: -$$Nest$fgetbounds, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Rect m12201$$Nest$fgetbounds(Action r0) {
            return r0.bounds;
        }

        /* renamed from: -$$Nest$fgetdrawableAnimated, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m12202$$Nest$fgetdrawableAnimated(Action r0) {
            return r0.drawableAnimated;
        }

        /* renamed from: -$$Nest$fgetdrawableFilled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m12203$$Nest$fgetdrawableFilled(Action r0) {
            return r0.drawableFilled;
        }

        /* renamed from: -$$Nest$fgetdrawableOutline, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m12204$$Nest$fgetdrawableOutline(Action r0) {
            return r0.drawableOutline;
        }

        /* renamed from: -$$Nest$fgetrippleDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m12205$$Nest$fgetrippleDrawable(Action r0) {
            return r0.rippleDrawable;
        }

        /* renamed from: -$$Nest$fgettext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Text m12206$$Nest$fgettext(Action r0) {
            return r0.text;
        }

        /* renamed from: -$$Nest$fgettextScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12207$$Nest$fgettextScale(Action r0) {
            return r0.textScale;
        }

        /* renamed from: -$$Nest$fputtextScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12208$$Nest$fputtextScale(Action r0, float r1) {
            r0.textScale = r1;
        }

        @Deprecated
        public Action(ProfileActionsView r9) {
            this.this$0 = r9;
            this.bounce = new ButtonBounce(r9);
            this.prevRect = new RectF();
            this.rect = new RectF();
            this.positionFraction = new AnimatedFloat(r9, 0, 250, CubicBezierInterpolator.DEFAULT);
            this.to = new RectF();
            this.from = new RectF();
            this.bounds = new Rect();
            this.textScale = 1.0f;
            this.isOpening = false;
            this.isDeleting = false;
            this.isDeleted = false;
            this.iconTranslationY = 0;
            this.iconScale = 1.0f;
            this.callDelay = 0;
            this.rippleDrawable = createRippleDrawable();
        }

        public Action(ProfileActionsView r9, ActionButton r10) {
            this.this$0 = r9;
            this.bounce = new ButtonBounce(r9);
            this.prevRect = new RectF();
            this.rect = new RectF();
            this.positionFraction = new AnimatedFloat(r9, 0, 250, CubicBezierInterpolator.DEFAULT);
            this.to = new RectF();
            this.from = new RectF();
            this.bounds = new Rect();
            this.textScale = 1.0f;
            this.isOpening = false;
            this.isDeleting = false;
            this.isDeleted = false;
            this.iconTranslationY = 0;
            this.iconScale = 1.0f;
            this.callDelay = 0;
            this.rippleDrawable = createRippleDrawable();
            update(r10);
        }

        private void animatePosition() {
            float r0 = this.positionFraction.set(1.0f);
            if (r0 == 1.0f) goto L6;
            RectF r1 = this.rect;
            r1.left = AndroidUtilities.lerp(this.from.left, this.to.left, r0);
            RectF r12 = this.rect;
            r12.right = AndroidUtilities.lerp(this.from.right, this.to.right, r0);
            return;
        L6:
            this.isOpening = false;
            if (this.isDeleting == false) goto L10;
            this.isDeleted = true;
            return;
        }

        private void checkBounds() {
            RLottieDrawable r0 = this.drawableAnimated;
            if (r0 == null) goto L5;
            r0.setBounds(this.bounds);
        L5:
            Drawable r02 = this.drawableFilled;
            if (r02 == null) goto L8;
            r02.setBounds(this.bounds);
        L8:
            Drawable r03 = this.drawableOutline;
            if (r03 == null) goto L12;
            r03.setBounds(this.bounds);
            return;
        }

        private Drawable createRippleDrawable() {
            if (ProfileActionsView.m12199$$Nest$misSegmentedMode(this.this$0) == false) goto L5;
            int r0 = 0;
        L6:
            Drawable r02 = Theme.createRadSelectorDrawable(0, 285212671, r0, r0);
            r02.setCallback(this.this$0);
            return r02;
        L5:
            r0 = ExteraConfig.getSectionRadiusDp();
            goto L6
        }

        public void delete() {
            LoadingDrawable r0 = this.loadingDrawable;
            boolean r1 = false;
            if (r0 == null) goto L5;
            r0.disappear();
            this.supportsLoading = false;
            this.isLoading = false;
        L5:
            this.isDeleting = true;
            if (this != ProfileActionsView.m12196$$Nest$fgetfirstAction(this.this$0)) goto L8;
            boolean r2 = true;
        L9:
            this.wasFirst = r2;
            if (this != ProfileActionsView.m12197$$Nest$fgetlastAction(this.this$0)) goto L12;
            boolean r22 = true;
        L13:
            this.wasLast = r22;
            RectF r23 = this.prevRect;
            if ((r23.left - 1.0f) > this.this$0.xpadding) goto L16;
            boolean r3 = true;
        L18:
            if ((r23.right + 1.0f) < (r5.getMeasuredWidth() - this.this$0.xpadding)) goto L20;
            boolean r24 = true;
        L21:
            if (r3 == false) goto L24;
            if (r24 == false) goto L24;
            r24 = false;
        L25:
            this.from.set(this.prevRect);
            this.to.set(this.prevRect);
            if (r1 == false) goto L28;
            RectF r12 = this.to;
            r12.right = r12.left;
        L38:
            this.positionFraction.set(0.0f, true);
            return;
        L28:
            if (r24 == false) goto L30;
            RectF r13 = this.to;
            r13.left = r13.right;
            goto L38
        L30:
            int r14 = this.key;
            if (r14 == 3) goto L35;
            if (r14 == 2) goto L35;
        L37:
            RectF r15 = this.to;
            float r25 = r15.centerX();
            r15.right = r25;
            r15.left = r25;
        L35:
            if (this.this$0.mode != 1) goto L37;
            RectF r16 = this.to;
            r16.left = r16.right;
        L24:
            r1 = r3;
            goto L25
        L20:
            r24 = false;
            goto L21
        L16:
            r3 = false;
            goto L18
        L12:
            r22 = false;
            goto L13
        L8:
            r2 = false;
            goto L9
        }

        public float getAlpha() {
            if (this.isDeleting == false) goto L7;
            return 1.0f - this.positionFraction.set(1.0f);
        L7:
            if (this.isOpening == true) goto L9;
            return 1.0f;
        L9:
            return this.positionFraction.set(1.0f);
        }

        public float getScale() {
            return this.bounce.getScale(0.04f);
        }

        public void setBounds(int r2, int r3, int r4, int r5) {
            this.bounds.set(r2, r3, r4, r5);
            checkBounds();
        }

        public void setText(CharSequence r4) {
            this.text = new Text(r4, 11.0f, AndroidUtilities.bold()).multiline(3).align(Layout.Alignment.ALIGN_CENTER);
        }

        public void update(ActionButton r4) {
            updateDrawable(0, r4.filledIcon, r4.outlineIcon);
            setText(LocaleController.getString(r4.title));
        }

        @SuppressLint({"UseCompatLoadingForDrawables"})
        public void updateDrawable(int r8, int r9, int r10) {
            Drawable r0 = null;
            if (r8 == 0) goto L5;
            RLottieDrawable r1 = new RLottieDrawable(r8, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f), false, null);
            r1.setMasterParent(this.this$0);
            r1.start();
            this.drawableAnimated = r1;
        L6:
            if (r9 == 0) goto L8;
            Drawable r82 = this.this$0.getResources().getDrawable(r9).mutate();
        L9:
            this.drawableFilled = r82;
            if (r10 == 0) goto L12;
            r0 = this.this$0.getResources().getDrawable(r10).mutate();
        L12:
            this.drawableOutline = r0;
            checkBounds();
            return;
        L8:
            r82 = null;
            goto L9
        L5:
            this.drawableAnimated = null;
            goto L6
        }

        @Deprecated
        public void updateDrawable(boolean r2, int r3) {
            if (r2 == false) goto L6;
            updateDrawable(r3, 0, 0);
            return;
        L6:
            updateDrawable(0, r3, r3);
        }

        public void updatePosition() {
            if (this.isDeleting == false) goto L6;
            animatePosition();
            return;
        L6:
            boolean r2 = false;
            if (this.this$0.isOpeningLayout == false) goto L11;
            this.isOpening = false;
            this.prevRect.set(this.rect);
            this.from.set(this.rect);
            this.to.set(this.rect);
            this.positionFraction.set(1.0f, true);
            return;
        L11:
            if (this.to.isEmpty() == false) goto L66;
            this.isOpening = true;
            this.to.set(this.rect);
            this.from.set(this.rect);
            RectF r0 = this.rect;
            float r5 = r0.left - 1.0f;
            ProfileActionsView r6 = this.this$0;
            if (r5 > r6.xpadding) goto L15;
            boolean r52 = true;
        L16:
            float r02 = r0.right + 1.0f;
            float r1 = r6.getMeasuredWidth();
            ProfileActionsView r62 = this.this$0;
            if (r02 < (r1 - r62.xpadding)) goto L19;
            boolean r03 = true;
        L20:
            if (r52 == false) goto L23;
            if (r03 == false) goto L23;
        L30:
            r03 = false;
            r52 = false;
        L31:
            int r12 = this.key;
            if (r12 == 5) goto L36;
            if (r12 == 6) goto L36;
        L39:
            if (r12 == 3) goto L43;
            if (r12 == 2) goto L43;
        L45:
            if (r52 == true) goto L47;
        L51:
            if (r03 == true) goto L53;
        L57:
            r2 = r52;
        L58:
            if (r2 == false) goto L60;
            RectF r04 = this.from;
            r04.left = r04.right;
        L64:
            this.positionFraction.set(0.0f, true);
            goto L66
        L60:
            RectF r13 = this.from;
            if (r03 == false) goto L63;
            r13.right = r13.left;
            goto L64
        L63:
            float r05 = this.to.centerX();
            r13.right = r05;
            r13.left = r05;
            goto L64
        L53:
            if (ProfileActionsView.m12197$$Nest$fgetlastAction(this.this$0) == null) goto L57;
            if (ProfileActionsView.m12197$$Nest$fgetlastAction(this.this$0).isDeleting == true) goto L57;
        L37:
            r03 = false;
            r2 = true;
            goto L58
        L47:
            if (ProfileActionsView.m12196$$Nest$fgetfirstAction(this.this$0) == null) goto L51;
            if (ProfileActionsView.m12196$$Nest$fgetfirstAction(this.this$0).isDeleting == true) goto L51;
            r03 = true;
        L43:
            if (this.this$0.mode != 1) goto L45;
        L36:
            if (this.this$0.mode != 0) goto L39;
        L23:
            if (ProfileActionsView.m12196$$Nest$fgetfirstAction(r62) == null) goto L27;
            if (ProfileActionsView.m12196$$Nest$fgetfirstAction(this.this$0).key == this.key) goto L30;
        L27:
            if (ProfileActionsView.m12197$$Nest$fgetlastAction(this.this$0) == null) goto L31;
            if (ProfileActionsView.m12197$$Nest$fgetlastAction(this.this$0).key != this.key) goto L31;
        L19:
            r03 = false;
            goto L20
        L15:
            r52 = false;
        L66:
            if (this.rect.equals(this.to) == true) goto L68;
            this.from.set(this.prevRect);
            this.to.set(this.rect);
            this.positionFraction.set(0.0f, true);
        L68:
            animatePosition();
            this.prevRect.set(this.rect);
        }
    }

    public enum ActionButton extends Enum<ActionButton> {
        private static final /* synthetic */ ActionButton[] $VALUES = null;
        public static final ActionButton CALL = null;
        public static final ActionButton DISCUSS = null;
        public static final ActionButton EDIT_INFO = null;
        public static final ActionButton EDIT_USERNAME = null;
        public static final ActionButton GIFT = null;
        public static final ActionButton JOIN = null;
        public static final ActionButton LEAVE = null;
        public static final ActionButton MESSAGE = null;
        public static final ActionButton NOTIFICATION_MUTE = null;
        public static final ActionButton NOTIFICATION_UNMUTE = null;
        public static final ActionButton OPEN_CHANNEL = null;
        public static final ActionButton REPORT = null;
        public static final ActionButton SETTINGS = null;
        public static final ActionButton SET_PHOTO = null;
        public static final ActionButton SHARE = null;
        public static final ActionButton STOP = null;
        public static final ActionButton STORY = null;
        public static final ActionButton STREAM = null;
        public static final ActionButton VIDEO = null;
        public static final ActionButton VOICE_CHAT = null;
        final int filledIcon;
        final int outlineIcon;
        final int title;

        private static /* synthetic */ ActionButton[] $values() {
            return new ActionButton[]{MESSAGE, NOTIFICATION_MUTE, NOTIFICATION_UNMUTE, DISCUSS, GIFT, SHARE, CALL, VIDEO, JOIN, REPORT, LEAVE, VOICE_CHAT, STREAM, STORY, OPEN_CHANNEL, STOP, SET_PHOTO, EDIT_USERNAME, EDIT_INFO, SETTINGS};
        }

        static {
            MESSAGE = new ActionButton("MESSAGE", 0, R.string.ProfileActionsMessage, R.drawable.filled_profile_message_24, R.drawable.outline_profile_message_24);
            NOTIFICATION_MUTE = new ActionButton("NOTIFICATION_MUTE", 1, R.string.ProfileButtonMute, R.drawable.filled_profile_mute_24, R.drawable.outline_profile_mute_24);
            NOTIFICATION_UNMUTE = new ActionButton("NOTIFICATION_UNMUTE", 2, R.string.ProfileButtonUnmute, R.drawable.filled_profile_unmute_24, R.drawable.outline_profile_unmute_24);
            DISCUSS = new ActionButton("DISCUSS", 3, R.string.ProfileActionsDiscuss, R.drawable.filled_profile_message_24, R.drawable.outline_profile_message_24);
            GIFT = new ActionButton("GIFT", 4, R.string.ProfileActionsGift, R.drawable.gift, R.drawable.input_gift_s);
            SHARE = new ActionButton("SHARE", 5, R.string.ProfileActionsShare, R.drawable.action_share, R.drawable.msg_share);
            CALL = new ActionButton("CALL", 6, R.string.ProfileActionsCall, R.drawable.filled_profile_call_24, R.drawable.outline_profile_call_24);
            VIDEO = new ActionButton("VIDEO", 7, R.string.ProfileActionsVideo, R.drawable.filled_profile_video_24, R.drawable.outline_profile_video_24);
            JOIN = new ActionButton("JOIN", 8, R.string.ProfileActionsJoin, R.drawable.filled_profile_member_24, R.drawable.outline_profile_member_24);
            REPORT = new ActionButton("REPORT", 9, R.string.ProfileActionsReport, R.drawable.report, R.drawable.msg_report);
            int r6 = R.string.ProfileActionsLeave;
            int r7 = R.drawable.leave;
            LEAVE = new ActionButton("LEAVE", 10, r6, r7, r7);
            int r72 = R.string.ProfileActionsVoiceChat;
            int r8 = R.drawable.live_stream;
            VOICE_CHAT = new ActionButton("VOICE_CHAT", 11, r72, r8, r8);
            int r82 = R.string.ProfileActionsLiveStream;
            int r9 = R.drawable.live_stream;
            STREAM = new ActionButton("STREAM", 12, r82, r9, r9);
            STORY = new ActionButton("STORY", 13, R.string.Story, R.drawable.filled_profile_story, R.drawable.outline_profile_story);
            OPEN_CHANNEL = new ActionButton("OPEN_CHANNEL", 14, R.string.ProfileChannel, R.drawable.msg_channel_filled, R.drawable.msg_channel);
            STOP = new ActionButton("STOP", 15, R.string.ProfileActionsStop, R.drawable.filled_profile_stop_24, R.drawable.outline_profile_stop_24);
            SET_PHOTO = new ActionButton("SET_PHOTO", 16, R.string.ProfileActionsEditPhoto2, R.drawable.filled_profile_photo, R.drawable.outline_profile_photo);
            EDIT_USERNAME = new ActionButton("EDIT_USERNAME", 17, R.string.ProfileActionsEditUsername, R.drawable.filled_profile_edit_24, R.drawable.outline_profile_edit_24);
            EDIT_INFO = new ActionButton("EDIT_INFO", 18, R.string.ProfileActionsEditInfo, R.drawable.filled_profile_edit_24, R.drawable.outline_profile_edit_24);
            SETTINGS = new ActionButton("SETTINGS", 19, R.string.Settings, R.drawable.filled_profile_settings, R.drawable.outline_profile_settings);
            $VALUES = $values();
        }

        ActionButton(String r1, int r2, int r3, int r4, int r5) {
            this.title = r3;
            this.filledIcon = r4;
            this.outlineIcon = r5;
        }

        public static ActionButton valueOf(String r1) {
            return (ActionButton) Enum.valueOf(ActionButton.class, r1);
        }

        public static ActionButton[] values() {
            return (ActionButton[]) $VALUES.clone();
        }
    }

    public interface OnActionClickListener {
        void onClick(int r1, float r2, float r3);
    }

    public static /* synthetic */ boolean $r8$lambda$6l_EqQDYfg9tJMBcXflockeZmGs(Drawable r0, Action r1) {
        if (Action.m12205$$Nest$fgetrippleDrawable(r1) != r0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$8ngVJAgRu6XSMWkZKDLMOiF7er0(ProfileActionsView r0, Action r1) {
        r0.lambda$onTouchEvent$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$cu0RL9KbDHK6dgpS5gypSCcn7bA(ProfileActionsView r0, List r1) {
        r0.lambda$applyVisibleActions$2(r1);
    }

    /* renamed from: -$$Nest$fgetactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12195$$Nest$fgetactions(ProfileActionsView r0) {
        return r0.actions;
    }

    /* renamed from: -$$Nest$fgetfirstAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Action m12196$$Nest$fgetfirstAction(ProfileActionsView r0) {
        return r0.firstAction;
    }

    /* renamed from: -$$Nest$fgetlastAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Action m12197$$Nest$fgetlastAction(ProfileActionsView r0) {
        return r0.lastAction;
    }

    /* renamed from: -$$Nest$fgetonActionClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnActionClickListener m12198$$Nest$fgetonActionClickListener(ProfileActionsView r0) {
        return r0.onActionClickListener;
    }

    /* renamed from: -$$Nest$misSegmentedMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12199$$Nest$misSegmentedMode(ProfileActionsView r0) {
        return r0.isSegmentedMode();
    }

    public ProfileActionsView(Context r6, int r7) {
        super(r6);
        this.actions = new ArrayList();
        Paint r62 = new Paint();
        this.paint = r62;
        this.shaderPaint = new Paint();
        this.isAnimatingCallAction = false;
        this.isOpeningLayout = true;
        this.clipHeight = -1.0f;
        this.clipAvatarPath = new Path();
        this.clipPath = new Path();
        this.pathTmp = new Path();
        this.radii = new float[8];
        this.activeCount = 0;
        this.ignoreRect = false;
        this.currentHeight = 0.0f;
        this.onActionClickListener = null;
        this.allAvailableActions = new HashSet();
        this.mode = 6;
        this.callAction = null;
        this.color = 0;
        this.matrix = new Matrix();
        this.textColor = -1;
        this.hit = null;
        this.callAnimationStateLoaded = false;
        this.callBackwardAnimateFromX = -1.0f;
        this.callBackwardAnimateFromY = -1.0f;
        r62.setColor(-16777216);
        r62.setAlpha(40);
        float r63 = AndroidUtilities.dpf2(12.0f);
        this.ypadding = r63;
        this.xpadding = r63;
        float r2 = AndroidUtilities.dpf2(8.0f);
        this.top = r2;
        this.textPadding = AndroidUtilities.dpf2(4.0f);
        this.targetHeight = (int) ((r7 - r63) - r2);
        setBackgroundColor(0);
        setImportantForAccessibility(1);
    }

    private void applyVisibleActions() {
        if (this.isApplying == false) goto L6;
        return;
    L6:
        if (this.mode != 6) goto L9;
        this.activeCount = this.actions.size();
        invalidate();
        return;
    L9:
        ArrayList r0 = new ArrayList();
        boolean r2 = hasJoin();
        int r3 = this.mode;
        if (r3 != 0) goto L12;
        insertIfAvailable(r0, 0);
        insertIfAvailable(r0, 5);
        insertIfAvailable(r0, 6);
        insertIfAvailable(r0, 1);
        insertIfNotAvailable(r0, 3, 6);
    L38:
        AndroidUtilities.runOnUIThread(new ProfileActionsView$$ExternalSyntheticLambda1(this, r0));
        return;
    L12:
        if (r3 == 1) goto L27;
        if (r3 == 2) goto L26;
        if (r3 == 3) goto L19;
        if (r3 == 4) goto L19;
        if (r3 != 5) goto L38;
        insertIfAvailable(r0, 0);
        insertIfAvailable(r0, 1);
    L19:
        if (r2 == false) goto L21;
        insertIfAvailable(r0, 7);
    L22:
        insertIfAvailable(r0, 1);
        if (r2 == false) goto L25;
        r0.add(getOrCreate(8));
        goto L38
    L25:
        insertIfAvailable(r0, 18);
        insertIfAvailable(r0, 10);
        insertIfNotAvailable(r0, 11, 10);
        insertIfAvailable(r0, 12);
        insertIfNotAvailable(r0, 9, 12);
        goto L38
    L21:
        insertIfAvailable(r0, 0);
        goto L22
    L26:
        insertIfAvailable(r0, 0);
        insertIfAvailable(r0, 1);
        insertIfAvailable(r0, 4);
        r0.add(getOrCreate(13));
        goto L38
    L27:
        if (r2 == false) goto L29;
        insertIfAvailable(r0, 7);
    L30:
        insertIfAvailable(r0, 1);
        if (r2 == true) goto L33;
        insertIfAvailable(r0, 2);
        insertIfNotAvailable2(r0, 3, 2, 12);
    L33:
        insertIfNotAvailable(r0, 4, 12);
        if (r2 == false) goto L36;
        r0.add(getOrCreate(8));
        goto L38
    L36:
        insertIfAvailable(r0, 12);
        insertIfNotAvailable(r0, 9, 12);
        goto L38
    L29:
        insertIfAvailable(r0, 10);
        insertIfNotAvailable(r0, 11, 10);
        goto L30
    }

    private void checkPaints() {
    }

    private void createColorShader() {
        int r0 = this.color;
        if (r0 != 0) goto L6;
        return;
    L6:
        if (this.hasColorById == true) goto L9;
        this.paint.setColor(r0);
        return;
    L9:
        int r02 = getMeasuredWidth();
        if (r02 > 0) goto L12;
        return;
    L12:
        float r03 = ((r02 - (getGap() * Math.max(0, this.activeCount - 1))) - (this.xpadding * 2.0f)) / Math.max(1, this.activeCount);
        float r4 = r03 / 2.0f;
        float r5 = this.targetHeight / 2.0f;
        if (this.hasColorById == false) goto L16;
        float r04 = r03 * 0.65f;
    L17:
        RadialGradient r3 = new RadialGradient(r4, r5, r04, Theme.multAlpha(this.color, 0.8f), this.color, Shader.TileMode.CLAMP);
        this.radialGradient = r3;
        this.shaderPaint.setShader(r3);
        return;
    L16:
        r04 = 1.0f;
        goto L17
    }

    private void drawAction(Canvas r12, Action r13, float r14, float r15) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r13.isDeleted == true) goto L46;
        boolean r0 = isButtonColorLight();
        float r1 = 0.0f;
        if (r0 == true) goto L9;
        float r3 = 1.0f;
    L10:
        if (r0 == true) goto L12;
    L14:
        int r9 = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText), -1, r3);
        if (this.lastColorFilter != null) goto L17;
    L18:
        this.lastColorFilterColor = r9;
        this.lastColorFilter = new PorterDuffColorFilter(r9, PorterDuff.Mode.SRC_IN);
    L19:
        r12.save();
        float r10 = r15 * r13.getAlpha();
        float r152 = r13.rect.centerX();
        float r02 = r13.rect.centerY();
        float r142 = r14 * r13.getScale();
        r12.scale(r142, r142, r152, r02);
        r12.clipRect(r13.rect);
        updateBounds(r13);
        float r8 = ((Action.m12201$$Nest$fgetbounds(r13).bottom + Action.m12201$$Nest$fgetbounds(r13).top) - ((Action.m12206$$Nest$fgettext(r13).getHeight() * Action.m12207$$Nest$fgettextScale(r13)) / 2.0f)) - AndroidUtilities.dp(6.0f);
        r12.save();
        r12.scale(Action.m12207$$Nest$fgettextScale(r13), Action.m12207$$Nest$fgettextScale(r13), r152, ((Action.m12206$$Nest$fgettext(r13).getHeight() * Action.m12207$$Nest$fgettextScale(r13)) / 2.0f) + r8);
        Action.m12206$$Nest$fgettext(r13).draw(r12, r152 - (Action.m12206$$Nest$fgettext(r13).getWidth() / 2.0f), r8, r9, r10);
        r12.restore();
        int r122 = r13.iconTranslationY;
        if (r122 == 0) goto L22;
        r12.translate(0.0f, r122);
    L22:
        float r123 = r13.iconScale;
        if (r123 == 1.0f) goto L26;
        r12.scale(r123, r123, Action.m12201$$Nest$fgetbounds(r13).centerX(), Action.m12201$$Nest$fgetbounds(r13).centerY());
    L26:
        if (this.isAnimatingCallAction == true) goto L28;
    L29:
        boolean r124 = IconManager.INSTANCE.isBasePackOnly(IconPackType.DEFAULT);
        if (r124 == false) goto L33;
        r1 = (1.0f - r3) * r10;
    L33:
        if (r124 == true) goto L35;
        float r125 = r10;
    L37:
        if (Action.m12202$$Nest$fgetdrawableAnimated(r13) != null) goto L39;
        drawActionDrawable(r12, Action.m12204$$Nest$fgetdrawableOutline(r13), r1);
        drawActionDrawable(r12, Action.m12203$$Nest$fgetdrawableFilled(r13), r125);
    L43:
        r12.restore();
        drawLoading(r12, r13, r10);
        return;
    L39:
        if (r13.key != 1) goto L41;
        drawActionDrawable(r12, Action.m12204$$Nest$fgetdrawableOutline(r13), r1);
        drawActionDrawable(r12, Action.m12202$$Nest$fgetdrawableAnimated(r13), r125);
        goto L43
    L41:
        drawActionDrawable(r12, Action.m12202$$Nest$fgetdrawableAnimated(r13), r10);
        goto L43
    L35:
        r125 = r3 * r10;
        goto L37
    L28:
        if (r13.key == 5) goto L43;
    L17:
        if (this.lastColorFilterColor == r9) goto L19;
    L12:
        if (Build.VERSION.SDK_INT >= 31) goto L14;
        r3 = 0.0f;
        goto L14
    L9:
        r3 = MathUtils.clamp((this.parentExpanded - 0.75f) / 0.25f, 0.0f, 1.0f);
        goto L10
    }

    private void drawActionDrawable(Canvas r2, Drawable r3, float r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.setColorFilter(this.lastColorFilter);
        r3.setAlpha((int) (r4 * 255.0f));
        r3.draw(r2);
    }

    private void drawGradient(Canvas r4, Action r5, float r6) {
        if (this.radialGradient == null) goto L10;
        int r0 = this.shaderPaint.getAlpha();
        this.shaderPaint.setAlpha((int) ((r5.getAlpha() * r6) * r0));
        Matrix r52 = this.matrix;
        RectF r62 = AndroidUtilities.rectTmp;
        r52.setTranslate(r62.left, r62.top);
        this.radialGradient.setLocalMatrix(this.matrix);
        if (isSegmentedMode() == false) goto L7;
        r4.drawRect(r62, this.shaderPaint);
    L8:
        this.shaderPaint.setAlpha(r0);
        return;
    L7:
        float r53 = getRoundRadius();
        r4.drawRoundRect(r62, r53, r53, this.shaderPaint);
        goto L8
    }

    private void drawLoading(Canvas r9, Action r10, float r11) {
        boolean r1 = false;
        if (r10.stopDelay > 0) goto L5;
    L7:
        boolean r0 = r10.isLoading;
        LoadingDrawable r2 = r10.loadingDrawable;
        if (r0 == false) goto L16;
        if (r2 != null) goto L12;
        LoadingDrawable r02 = new LoadingDrawable();
        r10.loadingDrawable = r02;
        r02.setCallback(this);
        r10.loadingDrawable.setColors(Theme.multAlpha(-1, 0.1f), Theme.multAlpha(-1, 0.3f), Theme.multAlpha(-1, 0.35f), Theme.multAlpha(-1, 0.8f));
        r10.loadingDrawable.setAppearByGradient(true);
        r10.loadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
    L22:
        LoadingDrawable r03 = r10.loadingDrawable;
        if (r03 == null) goto L55;
        r03.setBounds(r10.rect);
        if (isSegmentedMode() == false) goto L52;
        float r04 = getRoundRadius();
        float r22 = getInnerRoundRadius();
        boolean r4 = r10.isDeleting;
        if (r4 == false) goto L30;
        boolean r5 = r10.wasFirst;
    L33:
        if (r4 == false) goto L36;
        r1 = r10.wasLast;
    L38:
        LoadingDrawable r8 = r10.loadingDrawable;
        if (r5 == false) goto L41;
        float r3 = r04;
    L42:
        if (r1 == false) goto L44;
        float r42 = r04;
    L45:
        if (r1 == false) goto L47;
        float r12 = r04;
    L48:
        if (r5 == true) goto L51;
        r04 = r22;
    L51:
        r8.setRadii(r3, r42, r12, r04);
    L53:
        r10.loadingDrawable.setAlpha((int) (r11 * 255.0f));
        r10.loadingDrawable.draw(r9);
        return;
    L47:
        r12 = r22;
        goto L48
    L44:
        r42 = r22;
        goto L45
    L41:
        r3 = r22;
        goto L42
    L36:
        if (r10 != this.lastAction) goto L38;
        r1 = true;
        goto L38
    L30:
        if (r10 != this.firstAction) goto L32;
        r5 = true;
        goto L33
    L32:
        r5 = false;
        goto L33
    L52:
        r10.loadingDrawable.setRadii(getRoundRadius());
        goto L53
    L55:
        return;
    L12:
        if (r2.isDisappeared() == false) goto L14;
    L15:
        r10.loadingDrawable.reset();
        r10.loadingDrawable.resetDisappear();
        goto L22
    L14:
        if (r10.loadingDrawable.isDisappearing() == false) goto L22;
    L16:
        if (r2 == null) goto L22;
        if (r2.isDisappearing() == true) goto L22;
        if (r10.loadingDrawable.isDisappeared() == true) goto L22;
        r10.loadingDrawable.disappear();
        goto L22
    L5:
        if (System.currentTimeMillis() <= (r10.stopDelay + r10.startTime)) goto L7;
        r10.isLoading = false;
        goto L7
    }

    private void drawRenderNode(Canvas r11) {
        RenderNode r0 = this.renderNode;
        if (r0 != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        return;
    L7:
        if (r0.hasDisplayList() == true) goto L9;
        return;
    L9:
        if (r11.isHardwareAccelerated() == false) goto L19;
        r11.save();
        ProfileActivity.AvatarImageView r02 = this.avatarView;
        if (r02 == null) goto L14;
        View r03 = (View) r02.getParent();
        float r2 = r03.getX();
        float r3 = r03.getY() - getTranslationY();
        float r1 = r03.getWidth() * r03.getScaleX();
        float r4 = r03.getHeight() * r03.getScaleY();
        this.clipAvatarPath.rewind();
        this.clipAvatarPath.addRoundRect(r2, r3, r1 + r2, r4 + r3, this.avatarView.getRoundRadiusForExpand() * r03.getScaleX(), this.avatarView.getRoundRadiusForExpand() * r03.getScaleY(), Path.Direction.CCW);
        r11.clipPath(this.clipAvatarPath);
    L14:
        r11.clipPath(this.clipPath);
        r11.translate(0.0f, this.renderNodeTranslateY);
        float r04 = this.renderNodeScale;
        r11.scale(r04, r04);
        r11.drawRenderNode(this.renderNode);
        r11.restore();
        return;
    }

    private Action find(int r2) {
        return find(this.actions, r2);
    }

    private Action find(List<Action> r4, int r5) {
        int r3 = r4.size();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L10;
        Action r1 = r4.get(r0);
        if (r1.isDeleting == true) goto L9;
        if (r1.key != r5) goto L9;
        return r1;
    L9:
        r0 = r0 + 1;
        goto L3
    L10:
        return null;
    }

    private float getGap() {
        if (isSegmentedMode() == false) goto L7;
        return AndroidUtilities.dp(2.0f);
    L7:
        return this.xpadding / 2.0f;
    }

    private float getItemWidth() {
        int r0 = getMeasuredWidth();
        float r1 = getGap();
        return ((r0 - (r1 * (r2 - 1))) - (this.xpadding * 2.0f)) / this.activeCount;
    }

    private Action getOrCreate(int r6) {
        Action r0 = find(r6);
        if (r0 == null) goto L8;
        if (r6 != 1) goto L6;
        updateNotification(r0, false);
    L6:
        return r0;
    L8:
        if (r6 != 18) goto L10;
        r0 = new Action(this, ActionButton.OPEN_CHANNEL);
    L27:
        if (r0 == null) goto L29;
        r0.key = r6;
    L29:
        return r0;
    L10:
        switch(r6) {
            case 0: goto L25;
            case 1: goto L24;
            case 2: goto L23;
            case 3: goto L22;
            case 4: goto L21;
            case 5: goto L20;
            case 6: goto L19;
            case 7: goto L18;
            case 8: goto L17;
            case 9: goto L16;
            case 10: goto L15;
            case 11: goto L14;
            case 12: goto L13;
            case 13: goto L12;
            default: goto L27;
        };
    L12:
        r0 = new Action(this, ActionButton.STOP);
        r0.supportsLoading = true;
        r0.stopDelay = 300;
        goto L27
    L13:
        r0 = new Action(this, ActionButton.STORY);
        goto L27
    L14:
        r0 = new Action(this, ActionButton.STREAM);
        r0.supportsLoading = true;
        r0.supportsAnimate = R.raw.profile_voicechat;
        r0.stopDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L27
    L15:
        r0 = new Action(this, ActionButton.VOICE_CHAT);
        r0.supportsLoading = true;
        r0.supportsAnimate = R.raw.profile_voicechat;
        r0.stopDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L27
    L16:
        r0 = new Action(this, ActionButton.LEAVE);
        r0.supportsLoading = true;
        r0.supportsAnimate = R.raw.profile_leave;
        r0.stopDelay = 300;
        goto L27
    L17:
        r0 = new Action(this, ActionButton.REPORT);
        r0.supportsLoading = true;
        r0.stopDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L27
    L18:
        r0 = new Action(this, ActionButton.JOIN);
        r0.supportsLoading = true;
        r0.callDelay = 300;
        goto L27
    L19:
        r0 = new Action(this, ActionButton.VIDEO);
        r0.supportsLoading = true;
        r0.stopDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L27
    L20:
        r0 = new Action(this, ActionButton.CALL);
        this.callAction = r0;
        r0.supportsLoading = true;
        r0.stopDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        goto L27
    L21:
        r0 = new Action(this, ActionButton.SHARE);
        goto L27
    L22:
        r0 = new Action(this, ActionButton.GIFT);
        r0.supportsLoading = true;
        r0.stopDelay = 200;
        goto L27
    L23:
        r0 = new Action(this, ActionButton.DISCUSS);
        goto L27
    L24:
        r0 = new Action(this);
        updateNotification(r0, false);
        goto L27
    L25:
        r0 = new Action(this, ActionButton.MESSAGE);
        goto L27
    }

    private boolean hasJoin() {
        if (this.allAvailableActions.contains(7) == true) goto L5;
        return false;
    L5:
        if (this.allAvailableActions.contains(9) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private void insertIfAvailable(List<Action> r3, int r4) {
        if (this.allAvailableActions.contains(Integer.valueOf(r4)) == false) goto L6;
        r3.add(getOrCreate(r4));
        return;
    }

    private void insertIfNotAvailable(List<Action> r3, int r4, int r5) {
        if (this.allAvailableActions.contains(Integer.valueOf(r4)) == true) goto L5;
        return;
    L5:
        if (this.allAvailableActions.contains(Integer.valueOf(r5)) == true) goto L9;
        r3.add(getOrCreate(r4));
        return;
    }

    private void insertIfNotAvailable2(List<Action> r3, int r4, int r5, int r6) {
        if (this.allAvailableActions.contains(Integer.valueOf(r4)) == true) goto L5;
        return;
    L5:
        if (this.allAvailableActions.contains(Integer.valueOf(r5)) == false) goto L7;
        return;
    L7:
        if (this.allAvailableActions.contains(Integer.valueOf(r6)) == true) goto L12;
        r3.add(getOrCreate(r4));
        return;
    }

    private boolean isButtonColorLight() {
        if (AndroidUtilities.computePerceivedBrightness(this.color) <= 0.72f) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isSegmentedMode() {
        if (ExteraConfig.getDividerStyle() != DividerStyle.SEGMENTS) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$applyVisibleActions$2(List r5) {
        int r0 = this.activeCount;
        int r1 = r5.size();
        this.activeCount = r1;
        if (r0 != r1) goto L5;
    L7:
        int r02 = this.actions.size();
        int r12 = 0;
    L8:
        List<Action> r2 = this.actions;
        if (r12 >= r02) goto L19;
        Action r22 = r2.get(r12);
        if (r22.isDeleting == false) goto L16;
        if (r22.isDeleted == true) goto L16;
        r5.add(r22);
    L18:
        r12 = r12 + 1;
    L16:
        if (find(r5, r22.key) != null) goto L18;
        r22.delete();
        r5.add(r22);
        goto L18
    L19:
        r2.clear();
        this.actions.addAll(r5);
        invalidate();
        return;
    L5:
        if (this.radialGradient == null) goto L7;
        createColorShader();
        goto L7
    }

    private /* synthetic */ void lambda$onTouchEvent$0(Action r3) {
        OnActionClickListener r2 = this.onActionClickListener;
        int r0 = r3.key;
        RectF r32 = r3.rect;
        r2.onClick(r0, r32.left, r32.top);
    }

    private void measureActions() {
        if (getMeasuredWidth() > 0) goto L5;
        return;
    L5:
        if (this.activeCount <= 0) goto L25;
        float r0 = getItemWidth();
        int r1 = 0;
    L9:
        if (r1 >= this.actions.size()) goto L26;
        Action r2 = this.actions.get(r1);
        if (Action.m12206$$Nest$fgettext(r2) == null) goto L20;
        Action.m12206$$Nest$fgettext(r2).setMaxWidth(r0 - AndroidUtilities.dp(2.0f));
        if (Action.m12206$$Nest$fgettext(r2).getLineCount() < 3) goto L16;
        float r3 = 0.75f;
    L19:
        Action.m12208$$Nest$fputtextScale(r2, r3);
        goto L20
    L16:
        if (Action.m12206$$Nest$fgettext(r2).getLineCount() < 2) goto L18;
        r3 = 0.85f;
        goto L19
    L18:
        r3 = 1.0f;
    L20:
        r1 = r1 + 1;
        goto L9
    L26:
        return;
    }

    private void setRadii(boolean r3, boolean r4, float r5, float r6) {
        float[] r2 = this.radii;
        if (r3 == false) goto L5;
        float r0 = r5;
    L6:
        r2[1] = r0;
        r2[0] = r0;
        if (r4 == false) goto L9;
        float r02 = r5;
    L10:
        r2[3] = r02;
        r2[2] = r02;
        if (r4 == false) goto L13;
        float r42 = r5;
    L14:
        r2[5] = r42;
        r2[4] = r42;
        if (r3 == true) goto L18;
        r5 = r6;
    L18:
        r2[7] = r5;
        r2[6] = r5;
        return;
    L13:
        r42 = r6;
        goto L14
    L9:
        r02 = r6;
        goto L10
    L5:
        r0 = r6;
        goto L6
    }

    private void stopLoading(Action r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isLoading == false) goto L8;
        r2.isLoading = false;
        invalidate();
        return;
    }

    private void updateBounds(Action r6) {
        float r0 = r6.rect.centerX();
        r6.rect.centerY();
        float r1 = AndroidUtilities.dp(24.0f);
        float r2 = 0.5f * r1;
        float r5 = Math.max(0.0f, ((this.targetHeight - (Action.m12206$$Nest$fgettext(r6).getHeight() * Action.m12207$$Nest$fgettextScale(r6))) / 3.0f) + AndroidUtilities.dpf2(2.0f));
        r6.setBounds((int) (r0 - r2), (int) r5, (int) (r0 + r2), (int) (r5 + r1));
    }

    private void updateClipPath(Action r6, float r7, float r8, Path r9) {
        r9.rewind();
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r6.rect);
        r0.inset((r6.rect.width() / 2.0f) * (1.0f - r6.getScale()), (r6.rect.height() / 2.0f) * (1.0f - r6.getScale()));
        float r82 = -r8;
        r0.inset(r82, r82);
        if (isSegmentedMode() == false) goto L18;
        float r83 = getInnerRoundRadius();
        boolean r1 = r6.isDeleting;
        boolean r2 = false;
        if (r1 == false) goto L8;
        boolean r4 = r6.wasFirst;
    L11:
        if (r1 == false) goto L14;
        r2 = r6.wasLast;
    L16:
        setRadii(r4, r2, r7, r83);
        r9.addRoundRect(r0, this.radii, Path.Direction.CCW);
        return;
    L14:
        if (r6 != this.lastAction) goto L16;
        r2 = true;
        goto L16
    L8:
        if (r6 != this.firstAction) goto L10;
        r4 = true;
        goto L11
    L10:
        r4 = false;
        goto L11
    L18:
        r9.addRoundRect(r0, r7, r7, Path.Direction.CCW);
    }

    private void updateNotification(Action r2, boolean r3) {
        if (r3 == false) goto L12;
        if (IconManager.INSTANCE.isBasePackOnly(IconPackType.DEFAULT) == false) goto L12;
        if (this.isNotificationsEnabled == false) goto L9;
        ActionButton r1 = ActionButton.NOTIFICATION_MUTE;
        r2.setText(LocaleController.getString(r1.title));
        r2.updateDrawable(R.raw.profile_unmuting, r1.filledIcon, r1.outlineIcon);
        return;
    L9:
        ActionButton r12 = ActionButton.NOTIFICATION_UNMUTE;
        r2.setText(LocaleController.getString(r12.title));
        r2.updateDrawable(R.raw.profile_muting, r12.filledIcon, r12.outlineIcon);
        return;
    L12:
        if (this.isNotificationsEnabled == false) goto L14;
        ActionButton r13 = ActionButton.NOTIFICATION_MUTE;
    L15:
        r2.update(r13);
        return;
    L14:
        r13 = ActionButton.NOTIFICATION_UNMUTE;
        goto L15
    }

    public void addCameraAction() {
        Action r0 = new Action(this, ActionButton.SET_PHOTO);
        r0.key = 14;
        this.actions.add(r0);
    }

    public void addEditInfo() {
        Action r0 = new Action(this, ActionButton.EDIT_INFO);
        r0.key = 16;
        this.actions.add(r0);
    }

    public void addSettings() {
        Action r0 = new Action(this, ActionButton.SETTINGS);
        r0.key = 17;
        this.actions.add(r0);
    }

    public void beginApplyingActions() {
        this.isApplying = true;
    }

    public boolean canHaveJoinAction() {
        int r2 = this.mode;
        if (r2 != 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 3) goto L9;
        return false;
    }

    public void commitActions() {
        if (this.isApplying == false) goto L6;
        this.isApplying = false;
        applyVisibleActions();
        return;
    }

    public void drawingBlur(RenderNode r2, ProfileActivity.AvatarImageView r3, float r4, float r5) {
        this.ignoreRect = false;
        this.renderNode = r2;
        this.avatarView = r3;
        this.renderNodeScale = r4;
        this.renderNodeTranslateY = r5;
        invalidate();
    }

    public void drawingBlur(boolean r2) {
        if (this.ignoreRect == r2) goto L5;
    L8:
        this.ignoreRect = r2;
        this.renderNode = null;
        this.avatarView = null;
        invalidate();
        return;
    L5:
        if (this.renderNode != null) goto L8;
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.accessibilityNodeProvider != null) goto L6;
        this.accessibilityNodeProvider = new AnonymousClass1(this);
    L6:
        return this.accessibilityNodeProvider;
    }

    public float getInnerRoundRadius() {
        return Math.min(AndroidUtilities.dp(4.0f), ExteraConfig.getSectionRadiusDp());
    }

    public float getRoundRadius() {
        return AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp());
    }

    public boolean hasCall() {
        if (this.allAvailableActions.contains(5) == true) goto L5;
        return false;
    L5:
        if (this.callAction == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r17) {
        float r2 = this.clipHeight;
        if (r2 < 0.0f) goto L8;
        float r22 = r2 - getY();
        if (r22 <= 0.0f) goto L75;
        r17.clipRect(0.0f, 0.0f, getMeasuredWidth(), r22);
        goto L8
    L75:
        return;
    L8:
        float r23 = Math.max(0.0f, (this.currentHeight - this.ypadding) - this.top);
        if (r23 <= 0.0f) goto L76;
        float r4 = getGap();
        float r5 = getItemWidth();
        float r6 = this.xpadding;
        float r7 = getRoundRadius();
        if (this.renderNode == null) goto L14;
        this.clipPath.rewind();
    L14:
        int r8 = this.actions.size();
        Action r9 = null;
        Action r11 = null;
        int r12 = 0;
    L15:
        if (r12 >= r8) goto L27;
        Action r13 = this.actions.get(r12);
        if (r13.isDeleted == true) goto L26;
        if (r13.isDeleting == true) goto L25;
        RectF r112 = r13.rect;
        float r14 = this.top;
        r112.set(r6, r14, r6 + r5, r14 + r23);
        r6 = r6 + (r5 + r4);
        if (r9 != null) goto L24;
        r9 = r13;
    L24:
        r11 = r13;
    L25:
        r13.updatePosition();
    L26:
        r12 = r12 + 1;
        goto L15
    L27:
        this.firstAction = r9;
        this.lastAction = r11;
        if (this.renderNode == null) goto L36;
        int r42 = 0;
    L30:
        if (r42 >= r8) goto L36;
        Action r62 = this.actions.get(r42);
        if (r62.isDeleted == true) goto L35;
        updateClipPath(r62, r7, 1.0f, this.pathTmp);
        this.clipPath.addPath(this.pathTmp);
    L35:
        r42 = r42 + 1;
    L36:
        float r24 = Utilities.clamp01(r23 / this.targetHeight);
        float r43 = Utilities.clamp01((r24 - 0.2f) / 0.8f);
        if (r43 > 0.0f) goto L40;
        return;
    L40:
        if (this.ignoreRect == true) goto L58;
        int r63 = 0;
    L42:
        if (r63 >= r8) goto L58;
        Action r92 = this.actions.get(r63);
        if (r92.isDeleted == true) goto L57;
        int r10 = this.paint.getAlpha();
        int r113 = (int) ((r92.getAlpha() * r43) * r10);
        Paint r122 = this.paint;
        float r114 = r113;
        if (this.radialGradient == null) goto L49;
        float r132 = 0.1f;
    L50:
        r122.setAlpha((int) (r114 * r132));
        updateClipPath(r92, r7, 0.0f, this.pathTmp);
        if (isSegmentedMode() == false) goto L53;
        r17.save();
        r17.clipPath(this.pathTmp);
        RectF r115 = AndroidUtilities.rectTmp;
        r17.drawRect(r115, this.paint);
        drawGradient(r17, r92, r43);
        this.paint.setAlpha(r10);
        Action.m12205$$Nest$fgetrippleDrawable(r92).setBounds((int) r115.left, (int) r115.top, (int) r115.right, (int) r115.bottom);
        Action.m12205$$Nest$fgetrippleDrawable(r92).draw(r17);
        r17.restore();
        goto L57
    L53:
        RectF r116 = AndroidUtilities.rectTmp;
        r17.drawRoundRect(r116, r7, r7, this.paint);
        if (this.radialGradient == null) goto L56;
        drawGradient(r17, r92, r43);
    L56:
        this.paint.setAlpha(r10);
        Action.m12205$$Nest$fgetrippleDrawable(r92).setBounds((int) r116.left, (int) r116.top, (int) r116.right, (int) r116.bottom);
        Action.m12205$$Nest$fgetrippleDrawable(r92).draw(r17);
        goto L57
    L49:
        r132 = 1.0f;
    L57:
        r63 = r63 + 1;
    L58:
        drawRenderNode(r17);
        float r44 = Utilities.clamp01((r24 - 0.4f) / 0.6f);
        if (r44 <= 0.0f) goto L63;
        int r102 = 0;
    L61:
        if (r102 >= r8) goto L78;
        drawAction(r17, this.actions.get(r102), r24, r44);
        r102 = r102 + 1;
        goto L61
    L78:
        return;
    L63:
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), View.MeasureSpec.makeMeasureSpec((int) ((this.targetHeight + this.top) + this.ypadding), TLObject.FLAG_30));
        measureActions();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        if (this.currentHeight >= AndroidUtilities.dp(8.0f)) goto L5;
        return false;
    L5:
        float r0 = r10.getX();
        float r2 = r10.getY();
        int r102 = r10.getAction();
        if (r102 != 0) goto L16;
        this.hit = null;
        int r103 = this.actions.size();
        int r3 = 0;
    L8:
        if (r3 >= r103) goto L55;
        Action r5 = this.actions.get(r3);
        if (r5.isDeleting == true) goto L14;
        if (r5.rect.contains(r0, r2) == false) goto L14;
        this.hit = r5;
        this.downX = r0;
        this.downY = r2;
        this.downTime = System.currentTimeMillis();
        Action.m12200$$Nest$fgetbounce(this.hit).setPressed(true);
        Action.m12205$$Nest$fgetrippleDrawable(this.hit).setHotspot(r0, r2);
        Action.m12205$$Nest$fgetrippleDrawable(this.hit).setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
    L14:
        r3 = r3 + 1;
    L55:
        if (this.hit == null) goto L57;
        return true;
    L57:
        return false;
    L16:
        if (r102 == 2) goto L18;
        if (r102 != 1) goto L26;
    L27:
        Action r52 = this.hit;
        if (r52 == null) goto L55;
        Action.m12200$$Nest$fgetbounce(r52).setPressed(false);
        Action.m12205$$Nest$fgetrippleDrawable(this.hit).setState(new int[0]);
        if (r102 == 1) goto L32;
    L52:
        this.hit = null;
        return true;
    L32:
        if (this.hit.rect.contains(r0, r2) == false) goto L52;
        if ((System.currentTimeMillis() - this.downTime) > 250) goto L59;
    L36:
        Action r104 = this.hit;
        if (r104.supportsLoading == false) goto L42;
        if (r104.isLoading == true) goto L42;
        r104.isLoading = true;
        invalidate();
    L42:
        if (this.hit.supportsAnimate != 0) goto L44;
    L46:
        this.hit.startTime = System.currentTimeMillis();
        Action r105 = this.hit;
        OnActionClickListener r02 = this.onActionClickListener;
        if (r02 == null) goto L52;
        if (r105.callDelay != 0) goto L51;
        int r1 = r105.key;
        RectF r106 = r105.rect;
        r02.onClick(r1, r106.left, r106.top);
        goto L52
    L51:
        postDelayed(new ProfileActionsView$$ExternalSyntheticLambda2(this, r105), r105.callDelay);
        goto L52
    L44:
        if (IconManager.INSTANCE.isBasePackOnly(IconPackType.DEFAULT) == false) goto L46;
        Action r107 = this.hit;
        r107.updateDrawable(true, r107.supportsAnimate);
        goto L46
    L59:
        performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L58
        goto L36
    L26:
        if (r102 != 3) goto L55;
    L18:
        if (this.hit == null) goto L55;
        if (Math.abs(r0 - this.downX) <= 20.0f) goto L22;
    L23:
        Action.m12200$$Nest$fgetbounce(this.hit).setPressed(false);
        Action.m12205$$Nest$fgetrippleDrawable(this.hit).setState(new int[0]);
        this.hit = null;
        goto L55
    L22:
        if (Math.abs(r2 - this.downY) <= 20.0f) goto L55;
        goto L23
    }

    public void set(int r2, boolean r3) {
        Set<Integer> r0 = this.allAvailableActions;
        if (r3 == false) goto L5;
        boolean r22 = r0.add(Integer.valueOf(r2));
    L6:
        if (r22 == false) goto L9;
        applyVisibleActions();
        return;
    L9:
        return;
    L5:
        r22 = r0.remove(Integer.valueOf(r2));
        goto L6
    }

    public void setActionsColor(int r2, int r3, boolean r4) {
        if (this.radialGradient != null) goto L5;
    L12:
        this.color = r2;
        this.textColor = r3;
        this.hasColorById = r4;
        createColorShader();
        checkPaints();
        return;
    L5:
        if (this.color != r2) goto L12;
        if (this.textColor != r3) goto L12;
        if (this.hasColorById != r4) goto L12;
    }

    public void setNotifications(boolean r3) {
        if (this.isNotificationsEnabled == r3) goto L5;
        boolean r0 = true;
    L6:
        this.isNotificationsEnabled = r3;
        Action r32 = find(1);
        if (r32 == null) goto L10;
        updateNotification(r32, r0);
        invalidate();
        return;
    L10:
        this.allAvailableActions.add(1);
        applyVisibleActions();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setOnActionClickListener(OnActionClickListener r1) {
        this.onActionClickListener = r1;
    }

    public void setParentExpanded(float r2) {
        if (this.parentExpanded == r2) goto L6;
        this.parentExpanded = r2;
        checkPaints();
        invalidate();
        return;
    }

    public void startAnimatedActions() {
        if (this.mode != 6) goto L14;
        int r0 = this.actions.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L18;
        Action r3 = this.actions.get(r2);
        if (Action.m12202$$Nest$fgetdrawableAnimated(r3) == null) goto L13;
        if (r3.key != 15) goto L11;
        Action.m12202$$Nest$fgetdrawableAnimated(r3).setCurrentFrame(14);
    L12:
        Action.m12202$$Nest$fgetdrawableAnimated(r3).start();
        goto L13
    L11:
        Action.m12202$$Nest$fgetdrawableAnimated(r3).setCurrentFrame(0);
    L13:
        r2 = r2 + 1;
        goto L5
    L18:
        return;
    }

    public void startCameraAnimation() {
        Action r1 = find(14);
        if (r1 != null) goto L5;
        return;
    L5:
        if (Action.m12202$$Nest$fgetdrawableAnimated(r1) == null) goto L9;
        Action.m12202$$Nest$fgetdrawableAnimated(r1).start();
        return;
    }

    public void stopLoading(int r1) {
        stopLoading(find(r1));
    }

    public boolean supportsEditInfo() {
        if (this.mode != 6) goto L6;
        return true;
    L6:
        return false;
    }

    public void updatePosition(float r1, float r2) {
        this.currentHeight = r2;
        setTranslationY(r1);
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r3) {
        if (this.actions.stream().anyMatch(new ProfileActionsView$$ExternalSyntheticLambda0(r3)) == false) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r3) == false) goto L7;
        return true;
    L7:
        if ((r3 instanceof LoadingDrawable) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
