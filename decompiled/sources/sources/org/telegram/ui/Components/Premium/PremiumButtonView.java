package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BadWayToMakeButtonRound;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Loadable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes3.dex */
public class PremiumButtonView extends FrameLayout implements Loadable {
    public FrameLayout buttonLayout;
    public AnimatedTextView buttonTextView;
    AnimatedFloat counterOffset;
    AnimatedFloat counterOffset2;
    CounterView counterView;
    public boolean drawGradient;
    private boolean drawOverlayColor;
    CellFlickerDrawable flickerDrawable;
    RLottieImageView iconView;
    private boolean inc;
    private boolean isButtonTextSet;
    private boolean isFlickerDisabled;
    private boolean loading;
    private ValueAnimator loadingAnimator;
    private CircularProgressDrawable loadingDrawable;
    private float loadingT;
    private boolean nonClickable;
    ValueAnimator overlayAnimator;
    private float overlayProgress;
    public AnimatedTextView overlayTextView;
    private Paint paintOverlayPaint;
    Path path;
    private float progress;
    private int radius;
    private boolean showOverlay;

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatedTextView {
        final /* synthetic */ PremiumButtonView this$0;

        public AnonymousClass1(PremiumButtonView r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r8) {
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) <= 0.0f) goto L9;
            if (PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0) != null) goto L7;
            PremiumButtonView.m12026$$Nest$fputloadingDrawable(this.this$0, new CircularProgressDrawable(this.this$0.buttonTextView.getTextColor()));
        L7:
            int r0 = (int) ((1.0f - PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0)) * AndroidUtilities.dp(24.0f));
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).setBounds(0, r0, getWidth(), getHeight() + r0);
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).setAlpha((int) (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * 255.0f));
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).draw(r8);
            invalidate();
        L9:
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) < 1.0f) goto L11;
            return;
        L11:
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) == 0.0f) goto L14;
            r8.save();
            r8.translate(0.0f, (int) (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * AndroidUtilities.dp(-24.0f)));
            r8.scale(1.0f, 1.0f - (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * 0.4f));
            super.onDraw(r8);
            r8.restore();
            return;
        L14:
            super.onDraw(r8);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ PremiumButtonView this$0;

        public AnonymousClass2(PremiumButtonView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setClassName("android.widget.Button");
            if (PremiumButtonView.m12025$$Nest$fgetshowOverlay(this.this$0) == false) goto L7;
            AnimatedTextView r0 = this.this$0.overlayTextView;
            if (r0 == null) goto L7;
            CharSequence r02 = r0.getText();
        L8:
            if (r02 != null) goto L12;
            AnimatedTextView r1 = this.this$0.buttonTextView;
            if (r1 == null) goto L12;
            r02 = r1.getText();
        L12:
            if (r02 == null) goto L17;
            r3.setText(r02);
            if (getContentDescription() != null) goto L18;
            r3.setContentDescription(r02);
            return;
        L18:
            return;
        L17:
            return;
        L7:
            r02 = null;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends AnimatedTextView {
        final /* synthetic */ PremiumButtonView this$0;

        public AnonymousClass3(PremiumButtonView r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r8) {
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) <= 0.0f) goto L9;
            if (PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0) != null) goto L7;
            PremiumButtonView.m12026$$Nest$fputloadingDrawable(this.this$0, new CircularProgressDrawable(this.this$0.buttonTextView.getTextColor()));
        L7:
            int r0 = (int) ((1.0f - PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0)) * AndroidUtilities.dp(24.0f));
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).setBounds(0, r0, getWidth(), getHeight() + r0);
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).setAlpha((int) (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * 255.0f));
            PremiumButtonView.m12023$$Nest$fgetloadingDrawable(this.this$0).draw(r8);
            invalidate();
        L9:
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) < 1.0f) goto L11;
            return;
        L11:
            if (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) == 0.0f) goto L14;
            r8.save();
            r8.translate(0.0f, (int) (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * AndroidUtilities.dp(-24.0f)));
            r8.scale(1.0f, 1.0f - (PremiumButtonView.m12024$$Nest$fgetloadingT(this.this$0) * 0.4f));
            super.onDraw(r8);
            r8.restore();
            return;
        L14:
            super.onDraw(r8);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ PremiumButtonView this$0;
        final /* synthetic */ boolean val$loading;

        public AnonymousClass4(PremiumButtonView r1, boolean r2) {
            this.this$0 = r1;
            this.val$loading = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PremiumButtonView r22 = this.this$0;
            if (this.val$loading == false) goto L5;
            float r0 = 1.0f;
        L6:
            PremiumButtonView.m12027$$Nest$fputloadingT(r22, r0);
            this.this$0.buttonTextView.invalidate();
            AnimatedTextView r1 = this.this$0.overlayTextView;
            if (r1 == null) goto L10;
            r1.invalidate();
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ PremiumButtonView this$0;

        public AnonymousClass5(PremiumButtonView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            PremiumButtonView.m12028$$Nest$fputoverlayProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            PremiumButtonView.m12029$$Nest$mupdateOverlayProgress(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumButtonView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ PremiumButtonView this$0;

        public AnonymousClass6(PremiumButtonView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PremiumButtonView r22 = this.this$0;
            if (PremiumButtonView.m12025$$Nest$fgetshowOverlay(r22) == false) goto L5;
            float r0 = 1.0f;
        L6:
            PremiumButtonView.m12028$$Nest$fputoverlayProgress(r22, r0);
            PremiumButtonView.m12029$$Nest$mupdateOverlayProgress(this.this$0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$8pOnu0jeuhVBvEfTdRDjy0q7g_c(PremiumButtonView r0) {
        r0.lambda$setIcon$1();
    }

    public static /* synthetic */ void $r8$lambda$915eW1Bo7KgezQrdtvutfUIrrCQ(PremiumButtonView r0, ValueAnimator r1) {
        r0.lambda$setLoading$0(r1);
    }

    /* renamed from: -$$Nest$fgetloadingDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CircularProgressDrawable m12023$$Nest$fgetloadingDrawable(PremiumButtonView r0) {
        return r0.loadingDrawable;
    }

    /* renamed from: -$$Nest$fgetloadingT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12024$$Nest$fgetloadingT(PremiumButtonView r0) {
        return r0.loadingT;
    }

    /* renamed from: -$$Nest$fgetshowOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12025$$Nest$fgetshowOverlay(PremiumButtonView r0) {
        return r0.showOverlay;
    }

    /* renamed from: -$$Nest$fputloadingDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12026$$Nest$fputloadingDrawable(PremiumButtonView r0, CircularProgressDrawable r1) {
        r0.loadingDrawable = r1;
    }

    /* renamed from: -$$Nest$fputloadingT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12027$$Nest$fputloadingT(PremiumButtonView r0, float r1) {
        r0.loadingT = r1;
    }

    /* renamed from: -$$Nest$fputoverlayProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12028$$Nest$fputoverlayProgress(PremiumButtonView r0, float r1) {
        r0.overlayProgress = r1;
    }

    /* renamed from: -$$Nest$mupdateOverlayProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12029$$Nest$mupdateOverlayProgress(PremiumButtonView r0) {
        r0.updateOverlayProgress();
    }

    public PremiumButtonView(Context r25, int r26, boolean r27, Theme.ResourcesProvider r28) {
        super(r25);
        this.paintOverlayPaint = new Paint(1);
        this.path = new Path();
        this.drawGradient = true;
        this.counterOffset = new AnimatedFloat(this);
        this.counterOffset2 = new AnimatedFloat(this);
        this.loadingT = 0.0f;
        this.radius = r26;
        CellFlickerDrawable r0 = new CellFlickerDrawable();
        this.flickerDrawable = r0;
        r0.animationSpeedScale = 1.2f;
        r0.drawFrame = false;
        r0.repeatProgress = 4.0f;
        LinearLayout r11 = new LinearLayout(r25);
        r11.setOrientation(0);
        AnonymousClass1 r12 = new AnonymousClass1(this, r25, true, true, true);
        this.buttonTextView = r12;
        r12.setAnimationProperties(0.35f, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.buttonTextView.setGravity(17);
        AnimatedTextView r02 = this.buttonTextView;
        int r13 = Theme.key_featuredStickers_buttonText;
        r02.setTextColor(Theme.getColor(r13));
        this.buttonTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        RLottieImageView r03 = new RLottieImageView(r25);
        this.iconView = r03;
        r03.setColorFilter(Theme.getColor(r13));
        this.iconView.setVisibility(8);
        AnonymousClass2 r04 = new AnonymousClass2(this, r25);
        this.buttonLayout = r04;
        r04.addView(r11, LayoutHelper.createFrame(-2, -2, 17));
        this.buttonLayout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r26, 0, ColorUtils.setAlphaComponent(Theme.getColor(r13), 120)));
        r11.addView(this.buttonTextView, LayoutHelper.createLinear(-2, -2, 16));
        r11.addView(this.iconView, LayoutHelper.createLinear(24, 24, 0.0f, 16, 4, 0, 0, 0));
        addView(this.buttonLayout);
        BadWayToMakeButtonRound.round(this);
        ScaleStateListAnimator.apply(this, 0.02f, 1.2f);
        if (r27 == false) goto L6;
        AnonymousClass3 r05 = new AnonymousClass3(this, r25, true, true, true);
        this.overlayTextView = r05;
        r05.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.overlayTextView.setGravity(17);
        this.overlayTextView.setTextColor(Theme.getColor(r13, r28));
        this.overlayTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.overlayTextView.setTypeface(AndroidUtilities.bold());
        this.overlayTextView.getDrawable().setAllowCancel(true);
        this.overlayTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r13), 120)));
        addView(this.overlayTextView);
        this.paintOverlayPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, r28));
        updateOverlayProgress();
        return;
    }

    public PremiumButtonView(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        this(r2, AndroidUtilities.dp(8.0f), r3, r4);
    }

    private /* synthetic */ void lambda$setIcon$1() {
        this.iconView.getAnimatedDrawable().setCurrentFrame(0, true);
        this.iconView.playAnimation();
    }

    private /* synthetic */ void lambda$setLoading$0(ValueAnimator r1) {
        this.loadingT = ((Float) r1.getAnimatedValue()).floatValue();
        this.buttonTextView.invalidate();
        AnimatedTextView r0 = this.overlayTextView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private void updateOverlay(boolean r4) {
        ValueAnimator r0 = this.overlayAnimator;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.overlayAnimator.cancel();
    L5:
        float r02 = 0.0f;
        if (r4 == false) goto L8;
        float r42 = this.overlayProgress;
        if (this.showOverlay == false) goto L15;
        r02 = 1.0f;
    L15:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, r02});
        this.overlayAnimator = r43;
        r43.addUpdateListener(new AnonymousClass5(this));
        this.overlayAnimator.addListener(new AnonymousClass6(this));
        this.overlayAnimator.setDuration(250);
        this.overlayAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.overlayAnimator.start();
        return;
    L8:
        if (this.showOverlay == false) goto L10;
        r02 = 1.0f;
    L10:
        this.overlayProgress = r02;
        updateOverlayProgress();
    }

    private void updateOverlayProgress() {
        this.overlayTextView.setAlpha(this.overlayProgress);
        this.overlayTextView.setTranslationY(AndroidUtilities.dp(12.0f) * (1.0f - this.overlayProgress));
        this.buttonLayout.setAlpha(1.0f - this.overlayProgress);
        this.buttonLayout.setTranslationY((-AndroidUtilities.dp(12.0f)) * this.overlayProgress);
        FrameLayout r0 = this.buttonLayout;
        int r2 = 0;
        if (this.overlayProgress != 1.0f) goto L5;
        int r1 = 4;
    L6:
        r0.setVisibility(r1);
        AnimatedTextView r02 = this.overlayTextView;
        if (this.overlayProgress != 0.0f) goto L9;
        r2 = 4;
    L9:
        r02.setVisibility(r2);
        invalidate();
        return;
    L5:
        r1 = 0;
        goto L6
    }

    public void checkCounterView() {
        if (this.counterView != null) goto L6;
        CounterView r0 = new CounterView(getContext(), null);
        this.counterView = r0;
        r0.setGravity(3);
        this.counterView.setColors(Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_buttonText);
        this.counterView.counterDrawable.circleScale = 0.8f;
        setClipChildren(false);
        addView(this.counterView, LayoutHelper.createFrame(-1, 24, 16));
        return;
    }

    public void clearOverlayText() {
        this.showOverlay = false;
        updateOverlay(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r14) {
        if (this.counterView == null) goto L5;
        this.counterOffset.set(((r0.counterDrawable.getWidth() * 0.85f) + AndroidUtilities.dp(3.0f)) / 2.0f);
        this.counterOffset2.set(((getMeasuredWidth() / 2.0f) + (this.overlayTextView.getDrawable().getWidth() / 2.0f)) + AndroidUtilities.dp(3.0f));
        this.overlayTextView.setTranslationX(-this.counterOffset.get());
        this.counterView.setTranslationX(this.counterOffset2.get() - this.counterOffset.get());
    L8:
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        if (this.overlayProgress == 1.0f) goto L11;
    L12:
        boolean r4 = this.inc;
        float r6 = this.progress;
        if (r4 == false) goto L17;
        float r62 = r6 + 0.016f;
        this.progress = r62;
        if (r62 <= 3.0f) goto L21;
        this.inc = false;
    L21:
        if (this.drawGradient == false) goto L23;
        PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), ((-getMeasuredWidth()) * 0.1f) * this.progress, 0.0f);
        int r1 = this.radius;
        r14.drawRoundRect(r0, r1, r1, PremiumGradient.getInstance().getMainGradientPaint());
    L24:
        invalidate();
    L26:
        if (BuildVars.IS_BILLING_UNAVAILABLE == false) goto L28;
    L30:
        float r12 = this.overlayProgress;
        if (r12 != 0.0f) goto L33;
    L38:
        super.dispatchDraw(r14);
        return;
    L33:
        if (this.drawOverlayColor == false) goto L38;
        this.paintOverlayPaint.setAlpha((int) (r12 * 255.0f));
        if (this.overlayProgress == 1.0f) goto L37;
        this.path.rewind();
        this.path.addCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, (Math.max(getMeasuredWidth(), getMeasuredHeight()) * 1.4f) * this.overlayProgress, Path.Direction.CW);
        r14.save();
        r14.clipPath(this.path);
        int r13 = this.radius;
        r14.drawRoundRect(r0, r13, r13, this.paintOverlayPaint);
        r14.restore();
        goto L38
    L37:
        int r15 = this.radius;
        r14.drawRoundRect(r0, r15, r15, this.paintOverlayPaint);
        goto L38
    L28:
        if (this.isFlickerDisabled == true) goto L30;
        this.flickerDrawable.setParentWidth(getMeasuredWidth());
        this.flickerDrawable.draw(r14, r0, this.radius, null);
        goto L30
    L23:
        this.paintOverlayPaint.setAlpha(255);
        int r16 = this.radius;
        r14.drawRoundRect(r0, r16, r16, this.paintOverlayPaint);
        goto L24
    L17:
        float r63 = r6 - 0.016f;
        this.progress = r63;
        if (r63 >= 1.0f) goto L21;
        this.inc = true;
        goto L21
    L11:
        if (this.drawOverlayColor == true) goto L26;
    L5:
        AnimatedTextView r02 = this.overlayTextView;
        if (r02 == null) goto L8;
        r02.setTranslationX(0.0f);
        goto L8
    }

    public RLottieImageView getIconView() {
        return this.iconView;
    }

    public AnimatedTextView getTextView() {
        return this.buttonTextView;
    }

    public void hideIcon() {
        this.flickerDrawable.setOnRestartCallback(null);
        this.iconView.setVisibility(8);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.buttonLayout.isEnabled();
    }

    @Override // org.telegram.ui.Components.Loadable
    public boolean isLoading() {
        return this.loading;
    }

    public boolean isShowOverlay() {
        return this.showOverlay;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
    }

    public void setButton(String r2, View.OnClickListener r3) {
        setButton(r2, r3, false);
    }

    public void setButton(String r3, View.OnClickListener r4, boolean r5) {
        if (this.isButtonTextSet == true) goto L6;
        if (r5 == false) goto L6;
        r5 = true;
    L6:
        this.isButtonTextSet = true;
        if (r5 == true) goto L9;
    L11:
        this.buttonTextView.setText(r3, r5);
        this.buttonLayout.setContentDescription(r3);
        if (this.nonClickable == true) goto L15;
        this.buttonLayout.setOnClickListener(r4);
        return;
    L15:
        return;
    L9:
        if (this.buttonTextView.isAnimating() == false) goto L11;
        this.buttonTextView.cancelAnimation();
        goto L11
    }

    @Override // android.view.View
    public void setEnabled(boolean r1) {
        super.setEnabled(r1);
        this.buttonLayout.setEnabled(r1);
    }

    public void setFlickerDisabled(boolean r1) {
        this.isFlickerDisabled = r1;
        invalidate();
    }

    public void setIcon(int r3) {
        this.iconView.setAnimation(r3, 24, 24);
        CellFlickerDrawable r32 = this.flickerDrawable;
        r32.progress = 2.0f;
        r32.setOnRestartCallback(new PremiumButtonView$$ExternalSyntheticLambda1(this));
        invalidate();
        this.iconView.setVisibility(0);
    }

    @Override // org.telegram.ui.Components.Loadable
    public void setLoading(boolean r5) {
        if (this.loading == r5) goto L13;
        ValueAnimator r0 = this.loadingAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
        this.loadingAnimator = null;
    L7:
        float r02 = this.loadingT;
        this.loading = r5;
        if (r5 == false) goto L10;
        float r1 = 1.0f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.loadingAnimator = r03;
        r03.addUpdateListener(new PremiumButtonView$$ExternalSyntheticLambda0(this));
        this.loadingAnimator.addListener(new AnonymousClass4(this, r5));
        this.loadingAnimator.setDuration(320);
        this.loadingAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadingAnimator.start();
        return;
    L10:
        r1 = 0.0f;
        goto L11
    }

    public void setNonClickable() {
        this.nonClickable = true;
        setClickable(false);
        this.buttonLayout.setClickable(false);
        setStateListAnimator(null);
    }

    public void setOverlayText(CharSequence r2, boolean r3, boolean r4) {
        this.showOverlay = true;
        this.drawOverlayColor = r3;
        this.overlayTextView.setText(r2, r4);
        this.overlayTextView.setContentDescription(r2);
        updateOverlay(r4);
    }
}
