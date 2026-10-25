package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Loadable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes3.dex */
public class ButtonWithCounterView extends FrameLayout implements Loadable {
    private int backgroundColor;
    private float countAlpha;
    private final AnimatedFloat countAlphaAnimated;
    private ValueAnimator countAnimator;
    private boolean countFilled;
    private float countScale;
    private final AnimatedTextView.AnimatedTextDrawable countText;
    private Drawable counterDrawable;
    private boolean customBackgroundColor;
    private boolean enabled;
    private ValueAnimator enabledAnimator;
    private float enabledT;
    private boolean filled;
    private boolean flickeringLoading;
    private LoadingDrawable flickeringLoadingDrawable;
    private int globalAlpha;
    private int lastCount;
    private int lastWrapWidth;
    private boolean loading;
    private ValueAnimator loadingAnimator;
    private CircularProgressDrawable loadingDrawable;
    private float loadingT;
    private int minWidth;
    private boolean neutral;
    private final Paint paint;
    protected int radiusDp;
    private Theme.ResourcesProvider resourcesProvider;
    public final View rippleView;
    private boolean showZero;
    public final AnimatedTextView.AnimatedTextDrawable subText;
    private final int subTextAlpha;
    private float subTextT;
    private boolean subTextVisible;
    private ValueAnimator subTextVisibleAnimator;
    public final AnimatedTextView.AnimatedTextDrawable text;
    private Runnable tick;
    private int timerSeconds;
    public boolean useWrapContent;
    private boolean withCounterIcon;
    public boolean wrapContentDynamic;
    private boolean wrapWidth;

    /* renamed from: org.telegram.ui.Stories.recorder.ButtonWithCounterView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ButtonWithCounterView this$0;

        public AnonymousClass1(ButtonWithCounterView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ButtonWithCounterView.m19881$$Nest$fputsubTextVisible(this.this$0, false);
            this.this$0.subText.setText(null, false);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.ButtonWithCounterView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ButtonWithCounterView this$0;
        final /* synthetic */ boolean val$loading;

        public AnonymousClass2(ButtonWithCounterView r1, boolean r2) {
            this.this$0 = r1;
            this.val$loading = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ButtonWithCounterView r22 = this.this$0;
            if (this.val$loading == false) goto L5;
            float r0 = 1.0f;
        L6:
            ButtonWithCounterView.m19880$$Nest$fputloadingT(r22, r0);
            this.this$0.invalidate();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.ButtonWithCounterView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ButtonWithCounterView this$0;

        public AnonymousClass3(ButtonWithCounterView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ButtonWithCounterView.m19879$$Nest$fputcountScale(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    /* renamed from: $r8$lambda$6d6oVl8OJa_-gK1zV2kXvR2fZBM, reason: not valid java name */
    public static /* synthetic */ void m19876$r8$lambda$6d6oVl8OJa_gK1zV2kXvR2fZBM(ButtonWithCounterView r0, ValueAnimator r1) {
        r0.lambda$animateCount$4(r1);
    }

    /* renamed from: $r8$lambda$CxKuDipdlkWgag-L6BfN0c5Jf_Q, reason: not valid java name */
    public static /* synthetic */ void m19877$r8$lambda$CxKuDipdlkWgagL6BfN0c5Jf_Q(ButtonWithCounterView r0, ValueAnimator r1) {
        r0.lambda$setLoading$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$JL4UGvxh7W08XhAxZPgqFfhRrR8(ButtonWithCounterView r0, ValueAnimator r1) {
        r0.lambda$setSubText$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$SQktRbzgLeVFF6UNt1yBFSDTM2Q(ButtonWithCounterView r0, ValueAnimator r1) {
        r0.lambda$setSubText$2(r1);
    }

    /* renamed from: $r8$lambda$ZYbR7_8OT-bj-NerIf5mmDxF1bY, reason: not valid java name */
    public static /* synthetic */ void m19878$r8$lambda$ZYbR7_8OTbjNerIf5mmDxF1bY(ButtonWithCounterView r0, Runnable r1) {
        r0.lambda$setTimer$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$frKAEcolhcJk17GTCIFKTAQUOfk(ButtonWithCounterView r0, ValueAnimator r1) {
        r0.lambda$setEnabled$5(r1);
    }

    /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19879$$Nest$fputcountScale(ButtonWithCounterView r0, float r1) {
        r0.countScale = r1;
    }

    /* renamed from: -$$Nest$fputloadingT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19880$$Nest$fputloadingT(ButtonWithCounterView r0, float r1) {
        r0.loadingT = r1;
    }

    /* renamed from: -$$Nest$fputsubTextVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19881$$Nest$fputsubTextVisible(ButtonWithCounterView r0, boolean r1) {
        r0.subTextVisible = r1;
    }

    public ButtonWithCounterView(Context r2, Theme.ResourcesProvider r3) {
        this(r2, true, r3);
    }

    public ButtonWithCounterView(Context r10, boolean r11, Theme.ResourcesProvider r12) {
        super(r10);
        this.radiusDp = 8;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.countAlphaAnimated = new AnimatedFloat(350, r7);
        this.countFilled = true;
        this.timerSeconds = 0;
        this.subTextT = 0.0f;
        this.loadingT = 0.0f;
        this.countScale = 1.0f;
        this.enabledT = 1.0f;
        this.enabled = true;
        this.globalAlpha = 255;
        this.subTextAlpha = 200;
        this.filled = r11;
        this.resourcesProvider = r12;
        ScaleStateListAnimator.apply(this, 0.02f, 1.2f);
        View r1 = new View(r10);
        this.rippleView = r1;
        addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        if (r11 == false) goto L5;
        int r102 = AndroidUtilities.dp(this.radiusDp);
        int r13 = Theme.getColor(Theme.key_featuredStickers_addButton, r12);
        this.backgroundColor = r13;
        setBackground(Theme.createRoundRectDrawable(r102, r13));
    L5:
        Paint r103 = new Paint(1);
        this.paint = r103;
        r103.setColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r12));
        AnimatedTextView.AnimatedTextDrawable r14 = new AnimatedTextView.AnimatedTextDrawable(true, true, false);
        this.text = r14;
        r14.setAnimationProperties(0.3f, 0, 250, r7);
        r14.setCallback(this);
        r14.setTextSize(AndroidUtilities.dp(14.0f));
        if (r11 == false) goto L8;
        r14.setTypeface(AndroidUtilities.bold());
    L9:
        r14.setGravity(1);
        AnimatedTextView.AnimatedTextDrawable r15 = new AnimatedTextView.AnimatedTextDrawable(subTextSplitToWords(), true, false);
        this.subText = r15;
        r15.setAnimationProperties(0.3f, 0, 250, r7);
        r15.setCallback(this);
        r15.setTextSize(AndroidUtilities.dp(12.0f));
        r15.setGravity(1);
        AnimatedTextView.AnimatedTextDrawable r16 = new AnimatedTextView.AnimatedTextDrawable(false, false, true);
        this.countText = r16;
        r16.setAnimationProperties(0.3f, 0, 250, r7);
        r16.setCallback(this);
        r16.setTextSize(AndroidUtilities.dp(12.0f));
        r16.setTypeface(AndroidUtilities.bold());
        r16.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r16.setGravity(1);
        setWillNotDraw(false);
        updateColors();
        return;
    L8:
        r14.setTypeface(AndroidUtilities.regular());
        goto L9
    }

    private void animateCount() {
        ValueAnimator r0 = this.countAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.countAnimator = null;
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.countAnimator = r02;
        r02.addUpdateListener(new ButtonWithCounterView$$ExternalSyntheticLambda0(this));
        this.countAnimator.addListener(new AnonymousClass3(this));
        this.countAnimator.setInterpolator(new OvershootInterpolator(2.0f));
        this.countAnimator.setDuration(200);
        this.countAnimator.start();
    }

    private void cleanSubTextVisibleAnimator() {
        ValueAnimator r0 = this.subTextVisibleAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.subTextVisibleAnimator = null;
        return;
    }

    private int getWrapWidth() {
        float r0 = this.countAlphaAnimated.set(this.countAlpha);
        if (this.withCounterIcon == false) goto L5;
        float r1 = AndroidUtilities.dp(12.0f);
    L7:
        return (getPaddingLeft() + ((int) ((this.text.getCurrentWidth() + r1) + calculateCounterWidth(AndroidUtilities.dp(15.66f) + this.countText.getCurrentWidth(), r0)))) + getPaddingRight();
    L5:
        r1 = 0.0f;
        goto L7
    }

    private /* synthetic */ void lambda$animateCount$4(ValueAnimator r2) {
        this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    private /* synthetic */ void lambda$setEnabled$5(ValueAnimator r1) {
        this.enabledT = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setLoading$3(ValueAnimator r1) {
        this.loadingT = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setSubText$1(ValueAnimator r1) {
        this.subTextT = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setSubText$2(ValueAnimator r1) {
        this.subTextT = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setTimer$0(Runnable r3) {
        int r0 = this.timerSeconds - 1;
        this.timerSeconds = r0;
        setCount(r0, true);
        if (this.timerSeconds <= 0) goto L6;
        AndroidUtilities.runOnUIThread(this.tick, 1000);
        return;
    L6:
        setClickable(true);
        if (r3 == null) goto L10;
        r3.run();
        return;
    }

    public float calculateCounterWidth(float r1, float r2) {
        return r1 * r2;
    }

    public void disableRippleView() {
        removeView(this.rippleView);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r1, View r2, long r3) {
        return false;
    }

    public TextPaint getTextPaint() {
        return this.text.getPaint();
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override // org.telegram.ui.Components.Loadable
    public boolean isLoading() {
        return this.loading;
    }

    public boolean isTimerActive() {
        if (this.timerSeconds <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        this.rippleView.draw(r18);
        int r4 = 0;
        if (this.flickeringLoading == false) goto L14;
        boolean r2 = this.loading;
        LoadingDrawable r7 = this.flickeringLoadingDrawable;
        if (r2 == false) goto L9;
        if (r7 != null) goto L8;
        LoadingDrawable r22 = new LoadingDrawable(this.resourcesProvider);
        this.flickeringLoadingDrawable = r22;
        r22.setCallback(this);
        this.flickeringLoadingDrawable.setGradientScale(2.0f);
        this.flickeringLoadingDrawable.setAppearByGradient(true);
        this.flickeringLoadingDrawable.strokePaint.setStrokeWidth(0.0f);
        this.flickeringLoadingDrawable.setColors(Theme.multAlpha(-1, 0.02f), Theme.multAlpha(-1, 0.375f));
    L8:
        this.flickeringLoadingDrawable.resetDisappear();
        this.flickeringLoadingDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.flickeringLoadingDrawable.setRadiiDp(this.radiusDp);
        this.flickeringLoadingDrawable.draw(r18);
        goto L14
    L9:
        if (r7 == null) goto L14;
        r7.disappear();
        this.flickeringLoadingDrawable.draw(r18);
        if (this.flickeringLoadingDrawable.isDisappeared() == false) goto L14;
        this.flickeringLoadingDrawable.reset();
    L14:
        if (this.loadingT > 0.0f) goto L16;
    L19:
        float r23 = this.loadingT;
        if (r23 >= 1.0f) goto L72;
        if (r23 == 0.0f) goto L24;
        r18.save();
        r18.translate(0.0f, (int) (this.loadingT * AndroidUtilities.dp(-24.0f)));
        r18.scale(1.0f, 1.0f - (this.loadingT * 0.4f));
        boolean r24 = true;
    L25:
        float r8 = this.text.getCurrentWidth();
        float r9 = this.countAlphaAnimated.set(this.countAlpha);
        if (this.withCounterIcon == false) goto L28;
        float r10 = AndroidUtilities.dp(12.0f);
    L29:
        float r11 = (r8 + r10) + calculateCounterWidth(AndroidUtilities.dp(17.66f) + this.countText.getCurrentWidth(), r9);
        Rect r12 = AndroidUtilities.rectTmp2;
        r12.set((int) (((getMeasuredWidth() - r11) - getWidth()) / 2.0f), (int) ((getMeasuredHeight() - this.text.getHeight()) / 2.0f), (int) ((((getMeasuredWidth() - r11) + getWidth()) / 2.0f) + r8), (int) ((getMeasuredHeight() + this.text.getHeight()) / 2.0f));
        r12.offset(0, (int) ((-AndroidUtilities.dp(7.0f)) * this.subTextT));
        float r14 = 0.5f;
        this.text.setAlpha((int) ((this.globalAlpha * (1.0f - this.loadingT)) * AndroidUtilities.lerp(0.5f, 1.0f, this.enabledT)));
        this.text.setBounds(r12);
        this.text.draw(r18);
        if (this.subTextVisible == false) goto L32;
        r11 = this.subText.getCurrentWidth();
        r12.set((int) (((getMeasuredWidth() - r11) - getWidth()) / 2.0f), (int) (((getMeasuredHeight() - this.subText.getHeight()) / 2.0f) - AndroidUtilities.dp(1.0f)), (int) ((((getMeasuredWidth() - r11) + getWidth()) / 2.0f) + r11), (int) (((getMeasuredHeight() + this.subText.getHeight()) / 2.0f) - AndroidUtilities.dp(1.0f)));
        r12.offset(0, AndroidUtilities.dp(11.0f));
        r18.save();
        float r3 = AndroidUtilities.lerp(0.1f, 1.0f, this.subTextT);
        r18.scale(r3, r3, r12.centerX(), r12.bottom);
        this.subText.setAlpha((int) ((((1.0f - this.loadingT) * 200.0f) * this.subTextT) * AndroidUtilities.lerp(0.5f, 1.0f, this.enabledT)));
        this.subText.setBounds(r12);
        this.subText.draw(r18);
        r18.restore();
    L32:
        float r32 = ((getMeasuredWidth() - r11) / 2.0f) + r8;
        if (this.countFilled == false) goto L35;
        float r5 = 5.0f;
    L36:
        int r33 = (int) (r32 + AndroidUtilities.dp(r5));
        int r52 = (int) ((getMeasuredHeight() - AndroidUtilities.dp(18.0f)) / 2.0f);
        float r13 = ((getMeasuredWidth() - r11) / 2.0f) + r8;
        if (this.countFilled == false) goto L39;
        int r82 = 5;
    L40:
        r12.set(r33, r52, (int) ((r13 + AndroidUtilities.dp(r82 + 10)) + Math.max(AndroidUtilities.dp(9.0f), this.countText.getCurrentWidth() + r10)), (int) ((getMeasuredHeight() + AndroidUtilities.dp(18.0f)) / 2.0f));
        RectF r34 = AndroidUtilities.rectTmp;
        r34.set(r12);
        if (this.countScale == 1.0f) goto L44;
        r18.save();
        float r53 = this.countScale;
        r18.scale(r53, r53, r12.centerX(), r12.centerY());
    L44:
        if (this.countFilled == false) goto L52;
        this.paint.setAlpha((int) ((((this.globalAlpha * (1.0f - this.loadingT)) * r9) * r9) * AndroidUtilities.lerp(0.5f, 1.0f, this.enabledT)));
        if (this.withCounterIcon == false) goto L49;
        float r54 = 4.0f;
    L50:
        float r55 = AndroidUtilities.dp(r54);
        r18.drawRoundRect(r34, r55, r55, this.paint);
        goto L52
    L49:
        r54 = 10.0f;
    L52:
        if (this.countText.getText() == null) goto L55;
        r4 = this.countText.getText().length();
    L55:
        if (r4 <= 1) goto L57;
        float r35 = 0.3f;
    L58:
        r12.offset(-AndroidUtilities.dp(r35), -AndroidUtilities.dp(0.2f));
        AnimatedTextView.AnimatedTextDrawable r36 = this.countText;
        float r42 = (this.globalAlpha * (1.0f - this.loadingT)) * r9;
        if (this.countFilled == false) goto L61;
        r14 = 1.0f;
    L61:
        r36.setAlpha((int) (r42 * r14));
        this.countText.setBounds(r12);
        r18.save();
        if (this.countFilled == true) goto L64;
    L66:
        this.countText.draw(r18);
        r18.restore();
        if (this.countScale == 1.0f) goto L69;
        r18.restore();
    L69:
        if (r24 == false) goto L72;
        r18.restore();
        goto L72
    L64:
        if (this.withCounterIcon == false) goto L66;
        this.counterDrawable.setAlpha((int) (((this.globalAlpha * (1.0f - this.loadingT)) * r9) * 1.0f));
        this.counterDrawable.setBounds(AndroidUtilities.dp(1.0f) + r12.left, AndroidUtilities.dp(2.0f) + r12.top, (AndroidUtilities.dp(1.0f) + r12.left) + this.counterDrawable.getIntrinsicWidth(), (AndroidUtilities.dp(2.0f) + r12.top) + this.counterDrawable.getIntrinsicHeight());
        this.counterDrawable.draw(r18);
        r18.translate(r10 / 2.0f, 0.0f);
        goto L66
    L57:
        r35 = 0.0f;
        goto L58
    L39:
        r82 = 2;
        goto L40
    L35:
        r5 = 2.0f;
        goto L36
    L28:
        r10 = 0.0f;
        goto L29
    L24:
        r24 = false;
    L72:
        if (this.useWrapContent == false) goto L77;
        int r1 = getWrapWidth();
        if (this.lastWrapWidth == r1) goto L78;
        this.lastWrapWidth = r1;
        requestLayout();
        return;
    L78:
        return;
    L77:
        return;
    L16:
        if (this.loadingDrawable != null) goto L18;
        this.loadingDrawable = new CircularProgressDrawable(this.text.getTextColor());
    L18:
        int r25 = (int) ((1.0f - this.loadingT) * AndroidUtilities.dp(24.0f));
        this.loadingDrawable.setBounds(0, r25, getWidth(), getHeight() + r25);
        this.loadingDrawable.setAlpha((int) (this.loadingT * 255.0f));
        this.loadingDrawable.draw(r18);
        invalidate();
        goto L19
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        r1.setClassName("android.widget.Button");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.filled == false) goto L10;
        if (isClickable() == false) goto L10;
        if (getParent() == null) goto L10;
        getParent().requestDisallowInterceptTouchEvent(true);
    L10:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.useWrapContent == false) goto L9;
        int r0 = getWrapWidth();
        this.lastWrapWidth = r0;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(r0, View.MeasureSpec.getSize(r4)), TLObject.FLAG_30), r5);
        View r42 = this.rippleView;
        if (r42 == null) goto L14;
        r42.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        return;
    L14:
        return;
    L9:
        if (this.wrapWidth == false) goto L12;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) Math.min(Math.max((getPaddingLeft() + this.text.getCurrentWidth()) + getPaddingRight(), this.minWidth), View.MeasureSpec.getSize(r4)), TLObject.FLAG_30), r5);
        return;
    L12:
        super.onMeasure(r4, r5);
    }

    public void setColor(int r3) {
        if (this.filled == false) goto L6;
        this.customBackgroundColor = true;
        int r0 = AndroidUtilities.dp(this.radiusDp);
        this.backgroundColor = r3;
        setBackground(Theme.createRoundRectDrawable(r0, r3));
        return;
    L6:
        this.text.setTextColor(r3);
        View r02 = this.rippleView;
        int r32 = Theme.multAlpha(r3, 0.1f);
        int r2 = this.radiusDp;
        r02.setBackground(Theme.createRadSelectorDrawable(r32, r2, r2));
    }

    public void setColor(int r2, int r3) {
        if (this.filled == false) goto L6;
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(this.radiusDp), r2));
        View r22 = this.rippleView;
        int r1 = this.radiusDp;
        r22.setBackground(Theme.createRadSelectorDrawable(r3, r1, r1));
        return;
    L6:
        this.text.setTextColor(r2);
        View r23 = this.rippleView;
        int r12 = this.radiusDp;
        r23.setBackground(Theme.createRadSelectorDrawable(r3, r12, r12));
    }

    public void setCount(int r4, boolean r5) {
        if (r5 == false) goto L4;
        this.countText.cancelAnimation();
    L4:
        if (r5 == false) goto L10;
        int r0 = this.lastCount;
        if (r4 == r0) goto L10;
        if (r4 <= 0) goto L10;
        if (r0 <= 0) goto L10;
        animateCount();
    L10:
        this.lastCount = r4;
        if (r4 == 0) goto L13;
    L16:
        float r02 = 1.0f;
    L17:
        this.countAlpha = r02;
        this.countText.setText(LocaleController.formatNumber(r4, ' '), r5);
        invalidate();
        return;
    L13:
        if (this.showZero == true) goto L16;
        r02 = 0.0f;
        goto L17
    }

    public void setCount(String r2, boolean r3) {
        if (r3 == false) goto L4;
        this.countText.cancelAnimation();
        animateCount();
    L4:
        this.lastCount = -1;
        if (TextUtils.isEmpty(r2) == true) goto L7;
    L10:
        float r0 = 1.0f;
    L11:
        this.countAlpha = r0;
        this.countText.setText(r2, r3);
        invalidate();
        return;
    L7:
        if (this.showZero == true) goto L10;
        r0 = 0.0f;
        goto L11
    }

    public void setCountFilled(boolean r2) {
        this.countFilled = r2;
        AnimatedTextView.AnimatedTextDrawable r0 = this.countText;
        if (r2 == false) goto L5;
        float r22 = 12.0f;
    L6:
        r0.setTextSize(AndroidUtilities.dp(r22));
        updateColors();
        return;
    L5:
        r22 = 14.0f;
        goto L6
    }

    public void setCounterColor(int r3) {
        this.countText.setTextColor(r3);
        this.counterDrawable.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
    }

    @Override // android.view.View
    public void setEnabled(boolean r5) {
        if (this.enabled == r5) goto L12;
        ValueAnimator r0 = this.enabledAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
        this.enabledAnimator = null;
    L7:
        float r02 = this.enabledT;
        this.enabled = r5;
        if (r5 == false) goto L10;
        float r1 = 1.0f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.enabledAnimator = r03;
        r03.addUpdateListener(new ButtonWithCounterView$$ExternalSyntheticLambda1(this));
        this.enabledAnimator.start();
        goto L12
    L10:
        r1 = 0.0f;
    L12:
        super.setEnabled(r5);
    }

    public void setFilled(boolean r2) {
        if (this.filled != r2) goto L5;
        return;
    L5:
        this.filled = r2;
        if (r2 == false) goto L8;
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(this.radiusDp), this.backgroundColor));
        this.text.setTypeface(AndroidUtilities.bold());
    L9:
        updateColors();
        return;
    L8:
        setBackground(null);
        this.text.setTypeface(null);
        goto L9
    }

    public void setFlickeringLoading(boolean r1) {
        this.flickeringLoading = r1;
    }

    public void setGlobalAlpha(float r2) {
        this.globalAlpha = (int) (r2 * 255.0f);
    }

    @Override // org.telegram.ui.Components.Loadable
    public void setLoading(boolean r5) {
        if (this.loading != r5) goto L5;
        return;
    L5:
        if (this.flickeringLoading == false) goto L8;
        this.loading = r5;
        invalidate();
        return;
    L8:
        ValueAnimator r0 = this.loadingAnimator;
        if (r0 == null) goto L11;
        r0.cancel();
        this.loadingAnimator = null;
    L11:
        float r02 = this.loadingT;
        this.loading = r5;
        if (r5 == false) goto L14;
        float r1 = 1.0f;
    L15:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.loadingAnimator = r03;
        r03.addUpdateListener(new ButtonWithCounterView$$ExternalSyntheticLambda4(this));
        this.loadingAnimator.addListener(new AnonymousClass2(this, r5));
        this.loadingAnimator.setDuration(320);
        this.loadingAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadingAnimator.start();
        return;
    L14:
        r1 = 0.0f;
        goto L15
    }

    public void setMinWidth(int r2) {
        this.wrapWidth = true;
        this.minWidth = r2;
    }

    public ButtonWithCounterView setNeutral() {
        this.neutral = true;
        setFilled(true);
        setColor(Theme.getColor(Theme.key_buttonNeutral, this.resourcesProvider));
        updateColors();
        return this;
    }

    public ButtonWithCounterView setRound() {
        setRoundRadius(24);
        return this;
    }

    public void setRoundRadius(int r2) {
        this.radiusDp = r2;
        if (this.filled == false) goto L5;
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(r2), this.backgroundColor));
    L6:
        updateColors();
        return;
    L5:
        setBackground(null);
        goto L6
    }

    public void setShowZero(boolean r1) {
        this.showZero = r1;
    }

    public void setSubText(CharSequence r8, boolean r9) {
        if (r8 == null) goto L5;
        boolean r2 = true;
    L6:
        if (r9 == false) goto L8;
        this.subText.cancelAnimation();
    L8:
        setContentDescription(r8);
        invalidate();
        if (this.subTextVisible == false) goto L12;
        if (r2 == true) goto L12;
        cleanSubTextVisibleAnimator();
        ValueAnimator r82 = ValueAnimator.ofFloat(new float[]{this.subTextT, 0.0f});
        this.subTextVisibleAnimator = r82;
        r82.addUpdateListener(new ButtonWithCounterView$$ExternalSyntheticLambda2(this));
        this.subTextVisibleAnimator.addListener(new AnonymousClass1(this));
        this.subTextVisibleAnimator.setDuration(200);
        this.subTextVisibleAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.subTextVisibleAnimator.start();
    L14:
        if (this.subTextVisible == true) goto L18;
        if (r2 == false) goto L19;
        this.subTextVisible = true;
        cleanSubTextVisibleAnimator();
        ValueAnimator r83 = ValueAnimator.ofFloat(new float[]{this.subTextT, 1.0f});
        this.subTextVisibleAnimator = r83;
        r83.addUpdateListener(new ButtonWithCounterView$$ExternalSyntheticLambda3(this));
        this.subTextVisibleAnimator.setDuration(200);
        this.subTextVisibleAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.subTextVisibleAnimator.start();
        return;
    L19:
        return;
    L18:
        return;
    L12:
        this.subText.setText(r8, r9);
        goto L14
    L5:
        r2 = false;
        goto L6
    }

    public void setSubTextHacks(boolean r1, boolean r2, boolean r3, boolean r4) {
        this.subText.setHacks(r1, r2, r3, r4);
    }

    public void setText(CharSequence r2) {
        setText(r2, false);
    }

    public void setText(CharSequence r2, boolean r3) {
        setText(r2, r3, true);
    }

    public void setText(CharSequence r2, boolean r3, boolean r4) {
        if (r3 == false) goto L4;
        this.text.cancelAnimation();
    L4:
        this.text.setText(r2, r3, r4);
        setContentDescription(r2);
        invalidate();
    }

    public void setTextAlpha(float r2) {
        this.text.setAlpha((int) (r2 * 255.0f));
    }

    public void setTextColor(int r3) {
        this.text.setTextColor(r3);
        if (this.filled == true) goto L6;
        View r32 = this.rippleView;
        int r0 = Theme.multAlpha(this.text.getTextColor(), 0.1f);
        int r2 = this.radiusDp;
        r32.setBackground(Theme.createRadSelectorDrawable(r0, r2, r2));
        return;
    }

    public void setTextHacks(boolean r1, boolean r2, boolean r3, boolean r4) {
        this.text.setHacks(r1, r2, r3, r4);
    }

    public void setTimer(int r3, Runnable r4) {
        AndroidUtilities.cancelRunOnUIThread(this.tick);
        setCountFilled(false);
        this.timerSeconds = r3;
        setCount(r3, false);
        setShowZero(false);
        ButtonWithCounterView$$ExternalSyntheticLambda5 r32 = new ButtonWithCounterView$$ExternalSyntheticLambda5(this, r4);
        this.tick = r32;
        AndroidUtilities.runOnUIThread(r32, 1000);
    }

    public void setUseWrapContent(boolean r1) {
        this.useWrapContent = r1;
    }

    public boolean subTextSplitToWords() {
        return true;
    }

    public void updateColors() {
        if (this.customBackgroundColor == true) goto L10;
        if (this.neutral == false) goto L7;
        int r0 = Theme.key_buttonNeutral;
    L8:
        this.backgroundColor = Theme.getColor(r0, this.resourcesProvider);
        goto L10
    L7:
        r0 = Theme.key_featuredStickers_addButton;
    L10:
        if (this.filled == true) goto L12;
        int r02 = Theme.key_featuredStickers_addButton;
    L16:
        int r03 = Theme.getColor(r02, this.resourcesProvider);
        this.text.setTextColor(r03);
        boolean r1 = this.filled;
        View r2 = this.rippleView;
        if (r1 == false) goto L19;
        int r12 = Theme.getColor(Theme.key_listSelector, this.resourcesProvider);
        int r3 = this.radiusDp;
        r2.setBackground(Theme.createRadSelectorDrawable(r12, r3, r3));
    L20:
        this.subText.setTextColor(r03);
        if (this.neutral == false) goto L23;
        int r13 = Theme.key_buttonNeutralText;
    L24:
        int r14 = Theme.getColor(r13, this.resourcesProvider);
        boolean r22 = this.filled;
        Paint r32 = this.paint;
        if (r22 == true) goto L27;
        r32.setColor(Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider));
        AnimatedTextView.AnimatedTextDrawable r23 = this.countText;
        if (this.countFilled == false) goto L38;
        r03 = r14;
    L38:
        r23.setTextColor(r03);
        return;
    L27:
        if (this.countFilled == false) goto L29;
        int r15 = r03;
    L30:
        r32.setColor(r15);
        AnimatedTextView.AnimatedTextDrawable r16 = this.countText;
        if (this.countFilled == false) goto L33;
        r03 = this.backgroundColor;
    L33:
        r16.setTextColor(r03);
        return;
    L29:
        r15 = this.backgroundColor;
        goto L30
    L23:
        r13 = Theme.key_featuredStickers_buttonText;
        goto L24
    L19:
        int r17 = Theme.multAlpha(this.text.getTextColor(), 0.1f);
        int r33 = this.radiusDp;
        r2.setBackground(Theme.createRadSelectorDrawable(r17, r33, r33));
        goto L20
    L12:
        if (this.neutral == false) goto L14;
        r02 = Theme.key_buttonNeutralText;
        goto L16
    L14:
        r02 = Theme.key_featuredStickers_buttonText;
        goto L16
    }

    public void updateColors(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
        updateColors();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.flickeringLoadingDrawable != r2) goto L5;
        return true;
    L5:
        if (this.text != r2) goto L7;
        return true;
    L7:
        if (this.subText != r2) goto L9;
        return true;
    L9:
        if (this.countText != r2) goto L11;
        return true;
    L11:
        if (super.verifyDrawable(r2) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public void withCounterIcon() {
        this.withCounterIcon = true;
        Drawable r0 = ContextCompat.getDrawable(getContext(), R.drawable.mini_boost_button).mutate();
        this.counterDrawable = r0;
        r0.setColorFilter(new PorterDuffColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_IN));
    }

    public void wrapContentDynamic() {
        this.wrapContentDynamic = true;
    }
}
