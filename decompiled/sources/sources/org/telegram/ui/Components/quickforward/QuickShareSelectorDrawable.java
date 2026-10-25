package org.telegram.ui.Components.quickforward;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.content.ContextCompat;
import androidx.core.math.MathUtils;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class QuickShareSelectorDrawable extends Drawable implements Animator.AnimatorListener {
    private static final Property<QuickShareSelectorDrawable, Float> CLOSE_FACTOR = null;
    private static final Property<QuickShareSelectorDrawable, Float> OPEN_FACTOR = null;
    private static final int[] tmpCords = null;
    private static final Rect tmpRect = null;
    private static final RectF tmpRectF = null;
    private final QuickShareAvatarCell[] avatarCells;
    private final RectF ballLeft;
    private final RectF ballRight;
    private boolean ballsAllowed;
    private Matrix bitmapMatrix;
    private BitmapShader bitmapShader;
    private final RectF bubbleCurrent;
    private float bubbleOffset;
    private final RectF bubbleStart;
    private float bulletinImageCx;
    private float bulletinImageCy;
    private Bulletin.LottieLayout bulletinLayout;
    private final RectF buttonCurrent;
    public final ChatMessageCell cell;
    private final ObjectAnimator closeAnimation;
    private boolean closeAnimationCompleted;
    private BlurVisibilityDrawable closeAnimationDrawable;
    private boolean closeAnimationStarted;
    private float closeProgress;
    private Bitmap globalBlurBitmap;
    private Paint globalBlurBitmapPaint;
    private boolean isDestroyed;
    private boolean isReady;
    public final String key;
    private LinearGradient linearGradient;
    public final MessageObject messageObject;
    private int offsetX;
    private int offsetY;
    private final Runnable onFinish;
    private final ObjectAnimator openAnimation;
    private boolean openAnimationCompleted;
    private float openProgress;
    private final Paint paintBubbleBg;
    public final QuickShareSelectorOverlayLayout parent;
    private final Path path;
    private int selectedIndex;
    private final Matrix shaderMatrix;
    private final Drawable shadowDrawable;

    /* renamed from: org.telegram.ui.Components.quickforward.QuickShareSelectorDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ QuickShareSelectorDrawable this$0;
        final /* synthetic */ ViewTreeObserver val$observer;

        public AnonymousClass1(QuickShareSelectorDrawable r1, ViewTreeObserver r2) {
            this.this$0 = r1;
            this.val$observer = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.val$observer.removeOnPreDrawListener(this);
            QuickShareSelectorDrawable.m13765$$Nest$minitBulletin(this.this$0);
            QuickShareSelectorDrawable.m13764$$Nest$mcloseImpl(this.this$0);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.quickforward.QuickShareSelectorDrawable$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimationProperties.FloatProperty<QuickShareSelectorDrawable> {
        public AnonymousClass2(String r1) {
            super(r1);
        }

        public Float get(QuickShareSelectorDrawable r1) {
            return Float.valueOf(QuickShareSelectorDrawable.m13760$$Nest$fgetopenProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((QuickShareSelectorDrawable) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(QuickShareSelectorDrawable r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(QuickShareSelectorDrawable r1, float r2) {
            QuickShareSelectorDrawable.m13762$$Nest$fputopenProgress(r1, r2);
            QuickShareSelectorDrawable.m13763$$Nest$mcalculateOpeningAnimationPositions(r1);
            r1.invalidateSelf();
        }
    }

    /* renamed from: org.telegram.ui.Components.quickforward.QuickShareSelectorDrawable$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimationProperties.FloatProperty<QuickShareSelectorDrawable> {
        public AnonymousClass3(String r1) {
            super(r1);
        }

        public Float get(QuickShareSelectorDrawable r1) {
            return Float.valueOf(QuickShareSelectorDrawable.m13759$$Nest$fgetcloseProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((QuickShareSelectorDrawable) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(QuickShareSelectorDrawable r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(QuickShareSelectorDrawable r1, float r2) {
            QuickShareSelectorDrawable.m13761$$Nest$fputcloseProgress(r1, r2);
            r1.invalidateSelf();
        }
    }

    public static class Interpolators {
        public static final Interpolator DECELERATE_INTERPOLATOR = null;
        public static final Interpolator LINEAR_INTERPOLATOR = null;
        public static final Interpolator avatar1 = null;
        public static final Interpolator avatar2 = null;
        public static final Interpolator avatar3 = null;
        public static final Interpolator avatarOvershootCancel = null;
        public static final Interpolator ballsRadius = null;
        public static final Interpolator bgOpacity = null;
        public static final Interpolator bgScale = null;
        public static final Interpolator bubbleY = null;
        public static final Interpolator buttonJumpDown = null;
        public static final Interpolator buttonJumpUp = null;
        public static final Interpolator buttonRotationDown = null;
        public static final Interpolator buttonRotationUp = null;
        public static final Interpolator closeAlpha = null;
        public static final Interpolator closeAvatarAlpha = null;
        public static final Interpolator closeAvatarPosition = null;
        public static final Interpolator heightExpansion = null;
        public static final Interpolator overshootCancel = null;
        public static final Interpolator widthExpansion = null;

        static {
            DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
            LinearInterpolator r0 = new LinearInterpolator();
            LINEAR_INTERPOLATOR = r0;
            closeAlpha = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 0, 240, 240);
            closeAvatarPosition = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r0, 0, 240, 240);
            closeAvatarAlpha = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), Opcodes.REM_INT_LIT8, 240, 240);
            buttonRotationUp = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new CubicBezierInterpolator(0.7f, -0.6f, 0.4f, 1.0f), 0, 200, 560);
            buttonRotationDown = QuickShareSelectorDrawable.m13767$$Nest$sminterpolator(new CubicBezierInterpolator(0.7f, -0.6f, 0.4f, 1.0f), 200, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 560, true);
            buttonJumpUp = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 0, 150, 560);
            buttonJumpDown = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), Opcodes.MUL_INT_LIT16, 425, 560);
            CubicBezierInterpolator r02 = CubicBezierInterpolator.EASE_OUT_QUINT;
            bgOpacity = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 0, 320, 560);
            bgScale = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 40, 320, 560);
            heightExpansion = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 0, 250, 560);
            widthExpansion = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 0, 460, 560);
            bubbleY = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 0, 325, 560);
            ballsRadius = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 150, 250, 560);
            overshootCancel = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 200, 480, 560);
            avatar1 = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 60, 320, 560);
            avatar2 = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 90, 380, 560);
            avatar3 = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(r02, 110, 440, 560);
            avatarOvershootCancel = QuickShareSelectorDrawable.m13766$$Nest$sminterpolator(new DecelerateInterpolator(), 200, 460, 560);
        }
    }

    public static class Sizes {
        public static int AVATAR = 0;
        public static int AVATAR_RADIUS = 21;
        public static int BLUR_RADIUS = 10;
        public static int BUBBLE_HEIGHT = 0;
        public static int CLOSE_AVATAR_JUMP_HEIGHT = 15;
        public static int GAP = 11;
        public static int PADDING_H = 9;
        public static int TEXT_BLUR_RADIUS = 4;
        public static int TEXT_PADDING_EXTERNAL = 16;
        public static int TEXT_PADDING_INTERNAL = 8;

        static {
            int r0 = 21 * 2;
            AVATAR = r0;
            BUBBLE_HEIGHT = r0 + (7 * 2);
        }
    }

    /* renamed from: $r8$lambda$TFdbEg5B8-rng8gyNxj41ZbRLAs, reason: not valid java name */
    public static /* synthetic */ float m13756$r8$lambda$TFdbEg5B8rng8gyNxj41ZbRLAs(boolean r2, float r3, float r4, Interpolator r5, float r6) {
        if (r2 == false) goto L7;
        return 1.0f - r5.getInterpolation(1.0f - MathUtils.clamp((r6 - r3) / (r4 - r3), 0.0f, 1.0f));
    L7:
        return r5.getInterpolation(MathUtils.clamp((r6 - r3) / (r4 - r3), 0.0f, 1.0f));
    }

    /* renamed from: $r8$lambda$X-0bppOuoAIyxc0PyJo4PACfVAk, reason: not valid java name */
    public static /* synthetic */ void m13757$r8$lambda$X0bppOuoAIyxc0PyJo4PACfVAk(QuickShareSelectorDrawable r0, Canvas r1, int r2) {
        r0.drawBubble(r1, r2);
    }

    /* renamed from: $r8$lambda$p8F7bTzct9FfwKI-ByiZC9cmEs0, reason: not valid java name */
    public static /* synthetic */ void m13758$r8$lambda$p8F7bTzct9FfwKIByiZC9cmEs0(QuickShareSelectorDrawable r0, Bitmap r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetcloseProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13759$$Nest$fgetcloseProgress(QuickShareSelectorDrawable r0) {
        return r0.closeProgress;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13760$$Nest$fgetopenProgress(QuickShareSelectorDrawable r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fputcloseProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13761$$Nest$fputcloseProgress(QuickShareSelectorDrawable r0, float r1) {
        r0.closeProgress = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13762$$Nest$fputopenProgress(QuickShareSelectorDrawable r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$mcalculateOpeningAnimationPositions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13763$$Nest$mcalculateOpeningAnimationPositions(QuickShareSelectorDrawable r0) {
        r0.calculateOpeningAnimationPositions();
    }

    /* renamed from: -$$Nest$mcloseImpl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13764$$Nest$mcloseImpl(QuickShareSelectorDrawable r0) {
        r0.closeImpl();
    }

    /* renamed from: -$$Nest$minitBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13765$$Nest$minitBulletin(QuickShareSelectorDrawable r0) {
        r0.initBulletin();
    }

    /* renamed from: -$$Nest$sminterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Interpolator m13766$$Nest$sminterpolator(Interpolator r0, int r1, int r2, int r3) {
        return interpolator(r0, r1, r2, r3);
    }

    /* renamed from: -$$Nest$sminterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Interpolator m13767$$Nest$sminterpolator(Interpolator r0, int r1, int r2, int r3, boolean r4) {
        return interpolator(r0, r1, r2, r3, r4);
    }

    static {
        tmpRectF = new RectF();
        tmpRect = new Rect();
        tmpCords = new int[2];
        OPEN_FACTOR = new AnonymousClass2("openFactor");
        CLOSE_FACTOR = new AnonymousClass3("openFactor");
    }

    public QuickShareSelectorDrawable(QuickShareSelectorOverlayLayout r8, ChatMessageCell r9, List<Long> r10, String r11, Runnable r12) {
        this.paintBubbleBg = new Paint(1);
        this.shaderMatrix = new Matrix();
        this.path = new Path();
        this.bubbleStart = new RectF();
        this.buttonCurrent = new RectF();
        this.bubbleCurrent = new RectF();
        this.ballLeft = new RectF();
        this.ballRight = new RectF();
        this.openProgress = 0.0f;
        this.closeProgress = 0.0f;
        this.bitmapMatrix = new Matrix();
        int r0 = 0;
        this.openAnimationCompleted = false;
        this.closeAnimationCompleted = false;
        this.closeAnimationStarted = false;
        this.ballsAllowed = true;
        this.selectedIndex = -1;
        this.openAnimation = ObjectAnimator.ofFloat(this, OPEN_FACTOR, new float[]{1.0f}).setDuration(560);
        this.closeAnimation = ObjectAnimator.ofFloat(this, CLOSE_FACTOR, new float[]{1.0f}).setDuration(240);
        this.onFinish = r12;
        this.parent = r8;
        this.cell = r9;
        this.messageObject = r9.getMessageObject();
        this.key = r11;
        this.avatarCells = new QuickShareAvatarCell[Math.min(5, r10.size())];
    L3:
        QuickShareAvatarCell[] r112 = this.avatarCells;
        if (r0 >= r112.length) goto L6;
        r112[r0] = new QuickShareAvatarCell(this, r10.get(r0).longValue());
        r0 = r0 + 1;
        goto L3
    L6:
        this.paintBubbleBg.setStyle(Paint.Style.FILL);
        this.shadowDrawable = ContextCompat.getDrawable(r8.getContext(), R.drawable.reactions_bubble_shadow).mutate();
        r9.setHideSideButtonByQuickShare(true);
        r8.performHapticFeedback(3, 1);
        updateColors();
        ObjectAnimator r82 = this.openAnimation;
        Interpolator r92 = Interpolators.LINEAR_INTERPOLATOR;
        r82.setInterpolator(r92);
        this.openAnimation.addListener(this);
        this.closeAnimation.setInterpolator(r92);
        this.closeAnimation.addListener(this);
        AndroidUtilities.makeGlobalBlurBitmap(new QuickShareSelectorDrawable$$ExternalSyntheticLambda1(this), 15.0f);
    }

    private void arcTo(Path r8, RectF r9, float r10, float r11, boolean r12) {
        arcTo(r8, r9, r10, r11, r12, false);
    }

    private void arcTo(Path r3, RectF r4, float r5, float r6, boolean r7, boolean r8) {
        float r62 = r6 - r5;
        if (r7 == false) goto L8;
        if (r62 <= 0.0f) goto L11;
        r62 = r62 - 360.0f;
    L11:
        if (Math.abs(r62) <= 270.0f) goto L14;
        if (r8 == false) goto L14;
        this.ballsAllowed = false;
    L14:
        r3.arcTo(r4, r5, r62);
        return;
    L8:
        if (r62 >= 0.0f) goto L11;
        r62 = r62 + 360.0f;
        goto L11
    }

    private void buildPath(Path r11, RectF r12, RectF r13, RectF r14, RectF r15, boolean r16, boolean r17) {
        r11.reset();
        float r7 = calculateAngle(r12.centerX(), r12.centerY(), r15.centerX(), r15.centerY());
        float r8 = calculateAngle(r12.centerX(), r12.centerY(), r14.centerX(), r14.centerY());
        arcTo(r11, r12, r7, r8, false);
        float r122 = -90.0f;
        if (r16 == false) goto L5;
        float r4 = calculateAngle(r14.centerX(), r14.centerY(), r13.left + (r13.height() / 2.0f), r13.centerY());
    L6:
        arcTo(r11, r14, reverseAngle(r8), r4, true, true);
        if (r16 == true) goto L9;
        r11.lineTo(r13.left + (r13.height() / 2.0f), r13.bottom);
    L9:
        RectF r2 = tmpRectF;
        float r142 = r13.left;
        r2.set(r142, r13.top, r13.height() + r142, r13.bottom);
        arcTo(r11, r2, reverseAngle(r4), -90.0f, false);
        r11.lineTo(r13.right - (r13.height() / 2.0f), r13.top);
        if (r17 == false) goto L12;
        r122 = calculateAngle(r15.centerX(), r15.centerY(), r13.right - (r13.height() / 2.0f), r13.centerY());
    L12:
        r2.set(r13.right - r13.height(), r13.top, r13.right, r13.bottom);
        arcTo(r11, r2, -90.0f, reverseAngle(r122), false);
        if (r17 == true) goto L15;
        r11.lineTo(r15.centerX(), r13.bottom);
    L15:
        arcTo(r11, r15, r122, reverseAngle(r7), true, true);
        r11.close();
        return;
    L5:
        r4 = -90.0f;
        goto L6
    }

    private static float calculateAngle(float r0, float r1, float r2, float r3) {
        float r22 = r2 - r0;
        return (float) Math.toDegrees((float) Math.atan2(r3 - r1, r22));
    }

    private static double calculateC0(double r2, double r4, double r6, double r8) {
        return (r4 - (r8 * (r2 * r2))) - (r6 * r2);
    }

    private static double calculateC1(double r4, double r6, double r8, double r10, double r12) {
        double r102 = (r10 - (r12 * ((r8 * r8) - (r4 * r4)))) - r6;
        double r82 = r8 - r4;
        if (r82 != 0.0d) goto L6;
        return 0.0d;
    L6:
        return r102 / r82;
    }

    private static double calculateC2(double r2, double r4, double r6, double r8, double r10, double r12) {
        double r122 = (r12 - (((r10 - r2) * (r8 - r4)) / (r6 - r2))) - r4;
        double r42 = (((r10 * r10) - (r6 * r10)) + (r6 * r2)) - (r2 * r10);
        if (r42 != 0.0d) goto L6;
        return 0.0d;
    L6:
        return r122 / r42;
    }

    private void calculateCords() {
        ChatMessageCell r0 = this.cell;
        int[] r1 = tmpCords;
        r0.getLocationInWindow(r1);
        int r2 = r1[0];
        int r4 = r1[1];
        this.parent.getLocationInWindow(r1);
        int r02 = r1[0];
        int r12 = r1[1];
        this.offsetX = r2 - r02;
        this.offsetY = r4 - r12;
        float r13 = AndroidUtilities.dp(16.0f);
        float r22 = (this.offsetX + this.cell.getSideButtonStartX()) + r13;
        float r3 = (this.offsetY + this.cell.getSideButtonStartY()) + r13;
        this.bubbleStart.set(r22 - r13, r3 - r13, r22 + r13, r3 + r13);
        float r03 = AndroidUtilities.dp(16.0f);
        if (((this.bubbleStart.right + AndroidUtilities.dp(48.0f)) + r03) <= this.parent.getMeasuredWidth()) goto L7;
        this.bubbleOffset = Math.max(0.0f, (this.parent.getMeasuredWidth() - r03) - this.bubbleStart.right);
        return;
    L7:
        if ((((this.bubbleStart.right + AndroidUtilities.dp(48.0f)) - getBubbleWidth()) - r03) >= 0.0f) goto L10;
        this.bubbleOffset = Math.max(0.0f, (r03 + getBubbleWidth()) - this.bubbleStart.right);
        return;
    L10:
        this.bubbleOffset = AndroidUtilities.dp(48.0f);
    }

    private void calculateOpeningAnimationPositions() {
        float r1 = 1.0f - Interpolators.overshootCancel.getInterpolation(this.openProgress);
        float r3 = (Interpolators.buttonJumpUp.getInterpolation(this.openProgress) - Interpolators.buttonJumpDown.getInterpolation(this.openProgress)) * AndroidUtilities.dp(13.0f);
        this.buttonCurrent.set(this.bubbleStart);
        this.buttonCurrent.offset(0.0f, -r3);
        float r32 = fromTo(this.buttonCurrent.height(), AndroidUtilities.dp(Sizes.BUBBLE_HEIGHT) + (AndroidUtilities.dp(2.0f) * r1), Interpolators.heightExpansion.getInterpolation(this.openProgress)) / 2.0f;
        Interpolator r7 = Interpolators.widthExpansion;
        float r4 = fromTo(this.buttonCurrent.width(), getBubbleWidth() + (AndroidUtilities.dp(10.0f) * r1), r7.getInterpolation(this.openProgress));
        float r6 = r32 * 2.0f;
        float r42 = Math.max(r4, r6);
        float r8 = (this.bubbleStart.centerX() + r32) + (Math.min(AndroidUtilities.dp(-12.0f) + this.bubbleOffset, (r42 - Math.max(this.buttonCurrent.width(), r6)) / 2.0f) * r7.getInterpolation(this.openProgress));
        float r62 = ((this.bubbleStart.bottom - r32) - 1.0f) - ((AndroidUtilities.dp(38.0f) + (AndroidUtilities.dp(6.0f) * r1)) * Interpolators.bubbleY.getInterpolation(this.openProgress));
        RectF r12 = this.bubbleCurrent;
        r12.left = r8 - r42;
        r12.top = r62 - r32;
        r12.right = r8;
        r12.bottom = r62 + r32;
        if (this.ballsAllowed == true) goto L5;
        return;
    L5:
        if (this.openAnimationCompleted == true) goto L34;
        float r13 = fromTo(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(3.0f), Interpolators.ballsRadius.getInterpolation(this.openProgress));
        float r2 = this.bubbleCurrent.bottom + r13;
        float r33 = (float) findOtherLeg((this.buttonCurrent.width() / 2.0f) + r13, Math.abs(r2 - this.buttonCurrent.centerY()));
        float r43 = this.buttonCurrent.centerX() - r33;
        RectF r63 = this.bubbleCurrent;
        boolean r72 = true;
        if (r43 >= (r63.left + (r63.height() / 2.0f))) goto L9;
        boolean r64 = true;
    L10:
        if (r64 == false) goto L15;
        float r10 = this.buttonCurrent.centerX();
        float r11 = this.buttonCurrent.centerY();
        float r122 = (this.buttonCurrent.height() / 2.0f) + r13;
        RectF r9 = this.bubbleCurrent;
        PointF r92 = findIntersectionWithGravity(r10, r11, r122, r9.left + (r9.height() / 2.0f), this.bubbleCurrent.centerY(), (this.bubbleCurrent.height() / 2.0f) + r13, true);
        if (r92 == null) goto L14;
        r43 = r92.x;
        r2 = r92.y;
        goto L15
    L14:
        this.ballsAllowed = false;
    L15:
        this.ballLeft.set(r43 - r13, r2 - r13, r43 + r13, r2 + r13);
        float r22 = this.bubbleCurrent.bottom + r13;
        float r44 = this.buttonCurrent.centerX() + r33;
        RectF r34 = this.bubbleCurrent;
        if (r44 > (r34.right - (r34.height() / 2.0f))) goto L19;
        r72 = false;
    L19:
        if (r72 == false) goto L24;
        float r93 = this.buttonCurrent.centerX();
        float r102 = this.buttonCurrent.centerY();
        float r112 = (this.buttonCurrent.height() / 2.0f) + r13;
        RectF r35 = this.bubbleCurrent;
        PointF r36 = findIntersectionWithGravity(r93, r102, r112, r35.right - (r35.height() / 2.0f), this.bubbleCurrent.centerY(), (this.bubbleCurrent.height() / 2.0f) + r13, false);
        if (r36 == null) goto L23;
        r44 = r36.x;
        r22 = r36.y;
        goto L24
    L23:
        this.ballsAllowed = false;
    L24:
        this.ballRight.set(r44 - r13, r22 - r13, r44 + r13, r22 + r13);
        float r14 = Math.abs(this.ballLeft.centerX() - this.ballRight.centerX());
        float r23 = Math.abs(this.ballLeft.centerY() - this.ballRight.centerY());
        if (Math.sqrt((r14 * r14) + (r23 * r23)) > ((this.ballLeft.width() + this.ballRight.width()) / 2.0f)) goto L30;
        if (this.ballsAllowed == false) goto L30;
        this.ballsAllowed = false;
    L30:
        if (this.ballsAllowed == false) goto L35;
        buildPath(this.path, this.buttonCurrent, this.bubbleCurrent, this.ballLeft, this.ballRight, r64, r72);
        return;
    L35:
        return;
    L9:
        r64 = false;
        goto L10
    }

    private void closeImpl() {
        this.closeAnimation.start();
        this.closeAnimationStarted = true;
        if (this.openAnimationCompleted == true) goto L5;
    L7:
        invalidateSelf();
        return;
    L5:
        if (this.isDestroyed == true) goto L7;
        BlurVisibilityDrawable r0 = new BlurVisibilityDrawable(new QuickShareSelectorDrawable$$ExternalSyntheticLambda0(this));
        this.closeAnimationDrawable = r0;
        r0.render((int) this.bubbleCurrent.width(), (int) (this.bubbleCurrent.height() + AndroidUtilities.dp(30.0f)), AndroidUtilities.dp(Sizes.BLUR_RADIUS), 4.0f);
        goto L7
    }

    private void drawBubble(Canvas r4, int r5) {
        r4.save();
        RectF r0 = this.bubbleCurrent;
        r4.translate(-r0.left, (-r0.top) + AndroidUtilities.dp(30.0f));
        draw(r4, r5, true);
        r4.restore();
    }

    public static PointF findIntersectionWithGravity(float r6, float r7, float r8, float r9, float r10, float r11, boolean r12) {
        float r92 = r9 - r6;
        float r102 = r10 - r7;
        float r0 = (float) Math.sqrt(Math.pow(r92, 2.0d) + Math.pow(r102, 2.0d));
        if (r0 <= (r8 + r11)) goto L5;
        return null;
    L5:
        if (r0 < Math.abs(r8 - r11)) goto L24;
        float r112 = (((r8 * r8) - (r11 * r11)) + (r0 * r0)) / (2.0f * r0);
        float r82 = (float) Math.sqrt(r8 - (r112 * r112));
        float r62 = r6 + ((r112 * r92) / r0);
        float r72 = r7 + ((r112 * r102) / r0);
        float r103 = (r102 * r82) / r0;
        float r113 = r62 + r103;
        float r83 = (r82 * r92) / r0;
        float r93 = r72 - r83;
        float r63 = r62 - r103;
        float r73 = r72 + r83;
        if (r113 == r63) goto L17;
        if (r113 >= r63) goto L17;
        if (r12 == false) goto L15;
        return new PointF(r113, r93);
    L15:
        return new PointF(r63, r73);
    L17:
        if (r93 <= r73) goto L21;
        return new PointF(r113, r93);
    L21:
        return new PointF(r63, r73);
    L24:
        return null;
    }

    private static double findOtherLeg(double r1, double r3) {
        if (r1 > r3) goto L7;
        return 0.0d;
    L7:
        return Math.sqrt((r1 * r1) - (r3 * r3));
    }

    private static float findParabola(float r12, float r13, float r14, float r15, float r16, float r17, float r18) {
        double r0 = r12;
        double r2 = r13;
        double r4 = r14;
        double r6 = r15;
        double r8 = calculateC2(r0, r2, r4, r6, r16, r17);
        double r42 = calculateC1(r0, r2, r4, r6, r8);
        double r02 = calculateC0(r0, r2, r42, r8);
        double r122 = fromTo(r12, r14, r18);
        return (float) ((((r8 * r122) * r122) + (r42 * r122)) + r02);
    }

    private static float fromTo(float r0, float r1, float r2) {
        return r0 + ((r1 - r0) * r2);
    }

    private void initBulletin() {
        Bulletin.LottieLayout r0 = this.bulletinLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        int[] r1 = tmpCords;
        r0.getLocationInWindow(r1);
        float r2 = r1[0];
        float r4 = r1[1] - this.bulletinLayout.getTranslationY();
        Bulletin.LottieLayout r5 = this.bulletinLayout;
        if (r5.top == false) goto L8;
        float r52 = r5.getTopOffset();
    L9:
        this.parent.getLocationInWindow(r1);
        float r02 = r1[0];
        float r12 = r1[1];
        this.bulletinImageCx = ((r2 - r02) + this.bulletinLayout.imageView.getLeft()) + (this.bulletinLayout.imageView.getMeasuredWidth() / 2.0f);
        this.bulletinImageCy = (((r4 + r52) - r12) + this.bulletinLayout.imageView.getTop()) + (this.bulletinLayout.imageView.getMeasuredHeight() / 2.0f);
        return;
    L8:
        r52 = -r5.getBottomOffset();
        goto L9
    }

    private static Interpolator interpolator(Interpolator r1, int r2, int r3, int r4) {
        return interpolator(r1, r2, r3, r4, false);
    }

    private static Interpolator interpolator(Interpolator r0, int r1, int r2, int r3, boolean r4) {
        float r32 = r3;
        return new QuickShareSelectorDrawable$$ExternalSyntheticLambda2(r4, r1 / r32, r2 / r32, r0);
    }

    private /* synthetic */ void lambda$new$0(Bitmap r3) {
        if (this.isDestroyed == false) goto L6;
        this.globalBlurBitmap.recycle();
        return;
    L6:
        this.globalBlurBitmap = r3;
        Bitmap r0 = this.globalBlurBitmap;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        this.bitmapShader = new BitmapShader(r0, r1, r1);
        Paint r32 = new Paint(2);
        this.globalBlurBitmapPaint = r32;
        r32.setShader(this.bitmapShader);
        ColorMatrix r33 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L9;
        float r02 = 0.08f;
    L10:
        AndroidUtilities.adjustSaturationColorMatrix(r33, r02);
        if (Theme.isCurrentThemeDark() == false) goto L13;
        float r03 = 0.02f;
    L14:
        AndroidUtilities.adjustBrightnessColorMatrix(r33, r03);
        this.globalBlurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        this.bitmapMatrix.reset();
        this.bitmapMatrix.setScale(15.0f, 15.0f);
        this.bitmapShader.setLocalMatrix(this.bitmapMatrix);
        return;
    L13:
        r03 = -0.15f;
        goto L14
    L9:
        r02 = 1.25f;
        goto L10
    }

    private void onCloseAnimationEnd() {
        this.closeAnimationCompleted = true;
        invalidateSelf();
        Bulletin.LottieLayout r0 = this.bulletinLayout;
        if (r0 == null) goto L6;
        r0.imageView.setVisibility(0);
    L6:
        if (this.openAnimationCompleted == false) goto L9;
        this.onFinish.run();
        return;
    }

    private void onOpenAnimationEnd() {
        this.cell.setHideSideButtonByQuickShare(false);
        this.openAnimationCompleted = true;
        invalidateSelf();
        if (this.closeAnimationCompleted == false) goto L6;
        this.onFinish.run();
        return;
    }

    private void prepare() {
        calculateCords();
        this.openAnimation.start();
        this.isReady = true;
    }

    private static float reverseAngle(float r2) {
        if (r2 > 0.0f) goto L7;
        return r2 + 180.0f;
    L7:
        return r2 - 180.0f;
    }

    private void setIndex(int r6) {
        if (this.selectedIndex == r6) goto L20;
        this.parent.performHapticFeedback(3, 1);
        this.selectedIndex = r6;
        int r1 = 0;
    L6:
        QuickShareAvatarCell[] r3 = this.avatarCells;
        if (r1 >= r3.length) goto L24;
        QuickShareAvatarCell r32 = r3[r1];
        if (r6 != r1) goto L11;
        boolean r4 = true;
    L12:
        r32.setSelected(r4, true);
        QuickShareAvatarCell r33 = this.avatarCells[r1];
        if (r6 != r1) goto L15;
    L18:
        boolean r42 = true;
    L19:
        r33.setFullVisible(r42, true);
        r1 = r1 + 1;
        goto L6
    L15:
        if (r6 == (-1)) goto L18;
        r42 = false;
        goto L19
    L11:
        r4 = false;
        goto L12
    L24:
        return;
    }

    private void updateColors() {
        int r0 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.cell.getResourcesProvider());
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelShadow), PorterDuff.Mode.MULTIPLY));
        LinearGradient r5 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(100.0f), new int[]{r0, 16777215 & r0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.linearGradient = r5;
        this.paintBubbleBg.setShader(r5);
    }

    public void close(Bulletin r2) {
        if (r2 != null) goto L5;
        this.selectedIndex = -1;
        closeImpl();
        return;
    L5:
        Bulletin.Layout r22 = r2.getLayout();
        if ((r22 instanceof Bulletin.LottieLayout) == true) goto L9;
        closeImpl();
        return;
    L9:
        Bulletin.LottieLayout r23 = (Bulletin.LottieLayout) r22;
        this.bulletinLayout = r23;
        r23.imageView.setVisibility(4);
        ViewTreeObserver r24 = this.bulletinLayout.getViewTreeObserver();
        r24.addOnPreDrawListener(new AnonymousClass1(this, r24));
    }

    public void destroy() {
        int r1 = 0;
        this.cell.setHideSideButtonByQuickShare(false);
        if (this.isDestroyed == true) goto L13;
        this.isDestroyed = true;
        Bitmap r0 = this.globalBlurBitmap;
        if (r0 == null) goto L7;
        r0.recycle();
    L7:
        BlurVisibilityDrawable r02 = this.closeAnimationDrawable;
        if (r02 == null) goto L10;
        r02.recycle();
    L10:
        QuickShareAvatarCell[] r3 = this.avatarCells;
        int r03 = r3.length;
    L11:
        if (r1 >= r03) goto L15;
        r3[r1].recycle();
        r1 = r1 + 1;
        goto L11
    L15:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        draw(r3, 255, false);
    }

    public void draw(Canvas r22, int r23, boolean r24) {
        Canvas r1 = r22;
        if (this.isReady == true) goto L5;
        prepare();
    L5:
        BlurVisibilityDrawable r2 = this.closeAnimationDrawable;
        int r12 = 2;
        if (r2 == null) goto L19;
        if (r24 == true) goto L19;
        RectF r5 = this.bubbleCurrent;
        int r6 = (int) r5.left;
        int r52 = (int) (r5.top - AndroidUtilities.dp(30.0f));
        RectF r7 = this.bubbleCurrent;
        r2.setBounds(r6, r52, (int) r7.right, (int) r7.bottom);
        this.closeAnimationDrawable.setAlpha((int) ((1.0f - Interpolators.closeAlpha.getInterpolation(this.closeProgress)) * 255.0f));
        this.closeAnimationDrawable.draw(r1);
        if (this.selectedIndex == (-1)) goto L66;
        float r53 = 1.0f - Interpolators.closeAvatarAlpha.getInterpolation(this.closeProgress);
        float r25 = Interpolators.closeAvatarPosition.getInterpolation(this.closeProgress);
        int r3 = this.selectedIndex - 2;
        float r14 = this.bubbleCurrent.centerX() + (AndroidUtilities.dp(Sizes.AVATAR + Sizes.GAP) * r3);
        float r15 = this.bubbleCurrent.centerY();
        float r32 = this.bulletinImageCx;
        float r4 = this.bulletinImageCy;
        float r18 = (r14 + r32) / 2.0f;
        Bulletin.LottieLayout r62 = this.bulletinLayout;
        if (r62 != null) goto L13;
    L16:
        float r63 = Math.min(r15, r4) - AndroidUtilities.dp(Sizes.CLOSE_AVATAR_JUMP_HEIGHT);
    L15:
        float r19 = r63;
        this.avatarCells[this.selectedIndex].drawBlurredAvatar(r1, fromTo(r14, r32, r25), findParabola(r14, r15, r32, r4, r18, r19, r25), fromTo((AndroidUtilities.dp(Sizes.AVATAR) / 2.0f) + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(12.0f), r25), r53);
        return;
    L13:
        if (r62.top == false) goto L16;
        r63 = Math.max(r15, r4) + AndroidUtilities.dp(Sizes.CLOSE_AVATAR_JUMP_HEIGHT);
        goto L15
    L66:
        return;
    L19:
        if (r24 == true) goto L22;
        float r33 = 1.0f - this.closeProgress;
    L21:
        float r10 = r33;
        float r26 = fromTo(0.3f, 0.075f, Interpolators.bgScale.getInterpolation(this.openProgress));
        this.shaderMatrix.reset();
        this.shaderMatrix.setScale(r26, r26);
        this.shaderMatrix.postTranslate(0.0f, this.bubbleCurrent.bottom);
        this.linearGradient.setLocalMatrix(this.shaderMatrix);
        this.paintBubbleBg.setAlpha((int) ((Interpolators.bgOpacity.getInterpolation(this.openProgress) * 255.0f) * r10));
        RectF r27 = tmpRectF;
        r27.set(this.bubbleCurrent);
        r27.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
        Rect r34 = tmpRect;
        r27.round(r34);
        this.shadowDrawable.setAlpha((int) (255.0f * r10));
        this.shadowDrawable.setBounds(r34);
        this.shadowDrawable.draw(r1);
        if (this.openAnimationCompleted == true) goto L27;
        float r28 = (Interpolators.buttonRotationUp.getInterpolation(this.openProgress) - Interpolators.buttonRotationDown.getInterpolation(this.openProgress)) * (-40.0f);
        r1.save();
        RectF r35 = this.buttonCurrent;
        r1.translate(r35.left, r35.top);
        r1.rotate(r28, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r1.translate(-this.cell.getSideButtonStartX(), -this.cell.getSideButtonStartY());
        this.cell.drawSideButton(r1, true);
        r1.restore();
    L27:
        if (this.ballsAllowed == true) goto L29;
    L31:
        float r29 = Math.min(this.bubbleCurrent.width(), this.bubbleCurrent.height()) / 2.0f;
        float r36 = Math.min(this.buttonCurrent.width(), this.buttonCurrent.height()) / 2.0f;
        r1.drawRoundRect(this.bubbleCurrent, r29, r29, this.paintBubbleBg);
        if (this.openAnimationCompleted == true) goto L34;
        r1.drawRoundRect(this.buttonCurrent, r36, r36, this.paintBubbleBg);
    L34:
        float r210 = AndroidUtilities.dp(2.0f) * Interpolators.avatarOvershootCancel.getInterpolation(this.openProgress);
        float r152 = ((AndroidUtilities.dp(Sizes.AVATAR + 2) * Interpolators.avatar1.getInterpolation(this.openProgress)) / 2.0f) - r210;
        float r16 = ((AndroidUtilities.dp(Sizes.AVATAR + 2) * Interpolators.avatar2.getInterpolation(this.openProgress)) / 2.0f) - r210;
        float r17 = ((AndroidUtilities.dp(Sizes.AVATAR + 2) * Interpolators.avatar3.getInterpolation(this.openProgress)) / 2.0f) - r210;
        int r211 = 0;
    L35:
        if (r211 >= r12) goto L71;
        int r37 = 0;
    L38:
        if (r37 >= this.avatarCells.length) goto L65;
        if (r211 == 0) goto L41;
    L44:
        if (r211 == 1) goto L46;
    L48:
        float r54 = r37 - ((r4.length / 2.0f) - 0.5f);
        if (r37 != r12) goto L51;
        float r9 = r152;
    L57:
        float r72 = this.bubbleCurrent.centerX() + (AndroidUtilities.dp(Sizes.AVATAR + Sizes.GAP) * r54);
        float r8 = this.bubbleCurrent.centerY();
        QuickShareAvatarCell r42 = this.avatarCells[r37];
        float r55 = AndroidUtilities.dp(Sizes.TEXT_PADDING_EXTERNAL);
        float r64 = this.parent.getMeasuredWidth() - AndroidUtilities.dp(Sizes.TEXT_PADDING_EXTERNAL);
        RectF r11 = this.bubbleCurrent;
        float r56 = r11.left;
        float r112 = r11.right;
        if (r37 == this.selectedIndex) goto L60;
    L62:
        int r192 = r37;
        float r38 = r55;
        int r122 = r211;
        Canvas r212 = r1;
        QuickShareAvatarCell r13 = r42;
        float r43 = r64;
        float r65 = r112;
        boolean r113 = false;
    L63:
        r13.draw(r212, r38, r43, r56, r65, r72, r8, r9, r10, r113);
    L64:
        r37 = r192 + 1;
        r1 = r22;
        r211 = r122;
        r12 = 2;
        goto L38
    L60:
        if (this.closeAnimationStarted == false) goto L62;
        r192 = r37;
        r38 = r55;
        r122 = r211;
        r212 = r1;
        r13 = r42;
        r43 = r64;
        r65 = r112;
        r113 = true;
        goto L63
    L51:
        if (r37 != 1) goto L53;
    L56:
        r9 = r16;
        goto L57
    L53:
        if (r37 == 3) goto L56;
        r9 = r17;
        goto L57
    L46:
        if (r37 == this.selectedIndex) goto L48;
    L43:
        r122 = r211;
        r192 = r37;
        goto L64
    L41:
        if (r37 == this.selectedIndex) goto L43;
    L65:
        r211 = r211 + 1;
        r1 = r22;
        r12 = 2;
        goto L35
    L71:
        return;
    L29:
        if (this.openAnimationCompleted == true) goto L31;
        r1.drawPath(this.path, this.paintBubbleBg);
        goto L34
    L22:
        r33 = r23 / 255.0f;
        goto L21
    }

    public Paint getBlurBitmapPaint() {
        return this.globalBlurBitmapPaint;
    }

    public int getBubbleWidth() {
        int r0 = Sizes.AVATAR;
        int r1 = Sizes.GAP;
        return AndroidUtilities.dp((((r0 + r1) * this.avatarCells.length) - r1) + (Sizes.PADDING_H * 2));
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public long getSelectedDialogId() {
        int r0 = this.selectedIndex;
        if (r0 != (-1)) goto L5;
        return 0;
    L5:
        return this.avatarCells[r0].dialogId;
    }

    public boolean isActive() {
        return !this.closeAnimationStarted;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator r1) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator r2) {
        if (r2 != this.openAnimation) goto L7;
        onOpenAnimationEnd();
        return;
    L7:
        if (r2 != this.closeAnimation) goto L10;
        onCloseAnimationEnd();
        return;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator r1) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator r1) {
    }

    public void onTouchMoveEvent(float r4, float r5) {
        if (this.openAnimationCompleted == true) goto L5;
        return;
    L5:
        RectF r0 = this.bubbleCurrent;
        float r42 = (r4 - r0.left) + this.offsetX;
        float r52 = (r5 - r0.top) + this.offsetY;
        int r43 = (int) Math.floor((r42 - (AndroidUtilities.dp(Sizes.PADDING_H) - (AndroidUtilities.dp(Sizes.GAP) / 2.0f))) / AndroidUtilities.dp(Sizes.AVATAR + Sizes.GAP));
        if ((-AndroidUtilities.dp(Sizes.TEXT_PADDING_EXTERNAL + 21)) < r52) goto L8;
    L10:
        int r44 = -1;
    L11:
        setIndex(r44);
        return;
    L8:
        if (r52 >= this.bubbleCurrent.height()) goto L10;
        r44 = MathUtils.clamp(r43, 0, this.avatarCells.length - 1);
        goto L11
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
