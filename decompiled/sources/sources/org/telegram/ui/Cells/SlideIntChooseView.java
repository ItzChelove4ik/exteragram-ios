package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public class SlideIntChooseView extends FrameLayout {
    private CharSequence label;
    private final AnimatedTextView maxText;
    private float maxTextEmojiSaturation;
    private ValueAnimator maxTextEmojiSaturationAnimator;
    private final AnimatedTextView minText;
    private int minValueAllowed;
    private boolean needDivider;
    private Options options;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SeekBarView seekBarView;
    private float toMaxTextEmojiSaturation;
    private int value;
    private final AnimatedTextView valueText;
    private Utilities.Callback<Integer> whenChanged;

    /* renamed from: org.telegram.ui.Cells.SlideIntChooseView$1, reason: invalid class name */
    public class AnonymousClass1 extends SeekBarView {
        final /* synthetic */ SlideIntChooseView this$0;

        public AnonymousClass1(SlideIntChooseView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.SeekBarView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Cells.SlideIntChooseView$2, reason: invalid class name */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ SlideIntChooseView this$0;

        public AnonymousClass2(SlideIntChooseView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public CharSequence getContentDescription() {
            return SlideIntChooseView.m7045$$Nest$mbuildAccessibilityDescription(this.this$0);
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public int getStepsCount() {
            if (SlideIntChooseView.m7039$$Nest$fgetoptions(this.this$0) != null) goto L7;
            return 0;
        L7:
            return SlideIntChooseView.m7039$$Nest$fgetoptions(this.this$0).getStepsCount();
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public boolean needVisuallyDivideSteps() {
            return false;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r2, float r3) {
            if (SlideIntChooseView.m7039$$Nest$fgetoptions(this.this$0) != null) goto L5;
            return;
        L5:
            if (SlideIntChooseView.m7042$$Nest$fgetwhenChanged(this.this$0) == null) goto L22;
            int r22 = this.this$0.getValue(r3);
            if (SlideIntChooseView.m7038$$Nest$fgetminValueAllowed(this.this$0) == Integer.MIN_VALUE) goto L11;
            r22 = Math.max(r22, SlideIntChooseView.m7038$$Nest$fgetminValueAllowed(this.this$0));
        L11:
            if (SlideIntChooseView.m7041$$Nest$fgetvalue(this.this$0) == r22) goto L20;
            SlideIntChooseView r32 = this.this$0;
            if (r32.getStep(SlideIntChooseView.m7041$$Nest$fgetvalue(r32)) == this.this$0.getStep(r22)) goto L15;
            AndroidUtilities.vibrateCursor(SlideIntChooseView.m7040$$Nest$fgetseekBarView(this.this$0));
        L15:
            SlideIntChooseView.m7044$$Nest$fputvalue(this.this$0, r22);
            SlideIntChooseView r23 = this.this$0;
            r23.updateTexts(SlideIntChooseView.m7041$$Nest$fgetvalue(r23), true);
            if (SlideIntChooseView.m7042$$Nest$fgetwhenChanged(this.this$0) == null) goto L21;
            SlideIntChooseView.m7042$$Nest$fgetwhenChanged(this.this$0).run(Integer.valueOf(SlideIntChooseView.m7041$$Nest$fgetvalue(this.this$0)));
            return;
        L21:
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.SlideIntChooseView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ SlideIntChooseView this$0;
        final /* synthetic */ float val$value;

        public AnonymousClass3(SlideIntChooseView r1, float r2) {
            this.this$0 = r1;
            this.val$value = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ColorMatrix r32 = new ColorMatrix();
            SlideIntChooseView r0 = this.this$0;
            float r1 = this.val$value;
            SlideIntChooseView.m7043$$Nest$fputmaxTextEmojiSaturation(r0, r1);
            r32.setSaturation(r1);
            if (Theme.isCurrentThemeDark() == false) goto L5;
            AndroidUtilities.adjustBrightnessColorMatrix(r32, (1.0f - SlideIntChooseView.m7037$$Nest$fgetmaxTextEmojiSaturation(this.this$0)) * (-0.3f));
        L5:
            SlideIntChooseView.m7036$$Nest$fgetmaxText(this.this$0).setEmojiColorFilter(new ColorMatrixColorFilter(r32));
        }
    }

    public static class Options {
        public int betweenSteps;
        private int max;
        private int min;
        public int[] steps;
        public int style;
        public Utilities.Callback2Return<Integer, Integer, CharSequence> toString;

        public static /* synthetic */ CharSequence $r8$lambda$j64mmNixyX9paZV0J1XrEUu83XA(Utilities.CallbackReturn r0, Integer r1, Integer r2) {
            return (CharSequence) r0.run(r2);
        }

        public static /* synthetic */ CharSequence $r8$lambda$ych8fJhbMOkiRMSNRZEpMuMJEcs(String r0, Integer r1, Integer r2) {
            if (r1.intValue() != 0) goto L7;
            return LocaleController.formatPluralString(r0, r2.intValue(), new Object[0]);
        L7:
            return _UrlKt.FRAGMENT_ENCODE_SET + r2;
        }

        public Options() {
            this.steps = null;
            this.betweenSteps = 1;
        }

        public static Options make(int r1, int r2, int r3, Utilities.CallbackReturn<Integer, CharSequence> r4) {
            Options r0 = new Options();
            r0.style = r1;
            r0.min = r2;
            r0.max = r3;
            r0.toString = new SlideIntChooseView$Options$$ExternalSyntheticLambda0(r4);
            return r0;
        }

        public static Options make(int r1, String r2, int r3, int r4) {
            Options r0 = new Options();
            r0.style = r1;
            r0.min = r3;
            r0.max = r4;
            r0.toString = new SlideIntChooseView$Options$$ExternalSyntheticLambda1(r2);
            return r0;
        }

        public static Options make(int r1, int[] r2, int r3, Utilities.Callback2Return<Integer, Integer, CharSequence> r4) {
            Options r0 = new Options();
            r0.style = r1;
            r0.steps = r2;
            r0.betweenSteps = r3;
            r0.toString = r4;
            return r0;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Options) == false) goto L18;
            Options r52 = (Options) r5;
            if (this.style != r52.style) goto L18;
            if (this.min != r52.min) goto L18;
            if (this.max != r52.max) goto L18;
            if (this.betweenSteps != r52.betweenSteps) goto L18;
            if (Arrays.equals(this.steps, r52.steps) == false) goto L18;
            return true;
        L18:
            return false;
        }

        public int getMax() {
            int[] r0 = this.steps;
            if (r0 == null) goto L7;
            return r0[r0.length - 1];
        L7:
            return this.max;
        }

        public int getMin() {
            int[] r0 = this.steps;
            if (r0 == null) goto L7;
            return r0[0];
        L7:
            return this.min;
        }

        public int getStepsCount() {
            if (this.steps == null) goto L7;
            return (r0.length - 1) * this.betweenSteps;
        L7:
            return getMax() - getMin();
        }

        public int hashCode() {
            return (((((((this.style * 31) + this.min) * 31) + this.max) * 31) + this.betweenSteps) * 31) + Arrays.hashCode(this.steps);
        }
    }

    /* renamed from: $r8$lambda$akI_gAFovTIwG-J3XFzqPqpcHSY, reason: not valid java name */
    public static /* synthetic */ void m7035$r8$lambda$akI_gAFovTIwGJ3XFzqPqpcHSY(SlideIntChooseView r0, ValueAnimator r1) {
        r0.lambda$setMaxTextEmojiSaturation$0(r1);
    }

    /* renamed from: -$$Nest$fgetmaxText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m7036$$Nest$fgetmaxText(SlideIntChooseView r0) {
        return r0.maxText;
    }

    /* renamed from: -$$Nest$fgetmaxTextEmojiSaturation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7037$$Nest$fgetmaxTextEmojiSaturation(SlideIntChooseView r0) {
        return r0.maxTextEmojiSaturation;
    }

    /* renamed from: -$$Nest$fgetminValueAllowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7038$$Nest$fgetminValueAllowed(SlideIntChooseView r0) {
        return r0.minValueAllowed;
    }

    /* renamed from: -$$Nest$fgetoptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Options m7039$$Nest$fgetoptions(SlideIntChooseView r0) {
        return r0.options;
    }

    /* renamed from: -$$Nest$fgetseekBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SeekBarView m7040$$Nest$fgetseekBarView(SlideIntChooseView r0) {
        return r0.seekBarView;
    }

    /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7041$$Nest$fgetvalue(SlideIntChooseView r0) {
        return r0.value;
    }

    /* renamed from: -$$Nest$fgetwhenChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m7042$$Nest$fgetwhenChanged(SlideIntChooseView r0) {
        return r0.whenChanged;
    }

    /* renamed from: -$$Nest$fputmaxTextEmojiSaturation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7043$$Nest$fputmaxTextEmojiSaturation(SlideIntChooseView r0, float r1) {
        r0.maxTextEmojiSaturation = r1;
    }

    /* renamed from: -$$Nest$fputvalue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7044$$Nest$fputvalue(SlideIntChooseView r0, int r1) {
        r0.value = r1;
    }

    /* renamed from: -$$Nest$mbuildAccessibilityDescription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m7045$$Nest$mbuildAccessibilityDescription(SlideIntChooseView r0) {
        return r0.buildAccessibilityDescription();
    }

    public SlideIntChooseView(Context r24, Theme.ResourcesProvider r25) {
        super(r24);
        this.minValueAllowed = Integer.MIN_VALUE;
        this.toMaxTextEmojiSaturation = -1.0f;
        this.resourcesProvider = r25;
        AnimatedTextView r4 = new AnimatedTextView(r24, true, true, true);
        this.minText = r4;
        CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r4.setAnimationProperties(0.3f, 0, 220, r11);
        r4.setTextSize(AndroidUtilities.dp(13.0f));
        int r13 = Theme.key_windowBackgroundWhiteGrayText;
        r4.setTextColor(Theme.getColor(r13, r25));
        r4.setGravity(3);
        r4.setEmojiCacheType(19);
        r4.setEmojiColor(-1);
        r4.setImportantForAccessibility(2);
        addView(r4, LayoutHelper.createFrame(-1, 25.0f, 48, 22.0f, 13.0f, 22.0f, 0.0f));
        AnimatedTextView r5 = new AnimatedTextView(r24, false, true, true);
        this.valueText = r5;
        r5.setAnimationProperties(0.3f, 0, 220, r11);
        r5.setTextSize(AndroidUtilities.dp(13.0f));
        r5.setGravity(17);
        r5.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText, r25));
        r5.setEmojiColor(-1);
        r5.setEmojiCacheType(19);
        r5.setImportantForAccessibility(2);
        addView(r5, LayoutHelper.createFrame(-1, 25.0f, 48, 22.0f, 13.0f, 22.0f, 0.0f));
        AnimatedTextView r52 = new AnimatedTextView(r24, true, true, true);
        this.maxText = r52;
        r52.setAnimationProperties(0.3f, 0, 220, r11);
        r52.setTextSize(AndroidUtilities.dp(13.0f));
        r52.setGravity(5);
        r52.setTextColor(Theme.getColor(r13, r25));
        r52.setEmojiColor(-1);
        r52.setEmojiCacheType(19);
        r52.setImportantForAccessibility(2);
        addView(r52, LayoutHelper.createFrame(-1, 25.0f, 48, 22.0f, 13.0f, 22.0f, 0.0f));
        AnonymousClass1 r42 = new AnonymousClass1(this, r24, r25);
        this.seekBarView = r42;
        r42.setReportChanges(true);
        r42.setDelegate(new AnonymousClass2(this));
        addView(r42, LayoutHelper.createFrame(-1, 38.0f, 55, 6.0f, 30.0f, 6.0f, 0.0f));
    }

    private CharSequence buildAccessibilityDescription() {
        StringBuilder r0 = new StringBuilder();     // Catch: Throwable -> L5
        if (TextUtils.isEmpty(this.label) == true) goto L7;
        r0.append(this.label);     // Catch: Throwable -> L5
    L7:
        Options r1 = this.options;     // Catch: Throwable -> L5
        if (r1 == null) goto L27;
        Utilities.Callback2Return<Integer, Integer, CharSequence> r12 = r1.toString;     // Catch: Throwable -> L5
        if (r12 == null) goto L27;
        CharSequence r13 = r12.run(0, Integer.valueOf(this.value));     // Catch: Throwable -> L5
        if (TextUtils.isEmpty(r13) == false) goto L15;
    L18:
        CharSequence r14 = this.options.toString.run(-1, Integer.valueOf(this.options.getMin()));     // Catch: Throwable -> L5
        CharSequence r2 = this.options.toString.run(1, Integer.valueOf(this.options.getMax()));     // Catch: Throwable -> L5
        if (TextUtils.isEmpty(r14) == true) goto L27;
        if (TextUtils.isEmpty(r2) == true) goto L27;
        if (r0.length() <= 0) goto L25;
        r0.append(", ");     // Catch: Throwable -> L5
    L25:
        r0.append(r14);     // Catch: Throwable -> L5
        r0.append(" – ");     // Catch: Throwable -> L5
        r0.append(r2);     // Catch: Throwable -> L5
        goto L27
    L15:
        if (r0.length() <= 0) goto L17;
        r0.append(", ");     // Catch: Throwable -> L5
    L17:
        r0.append(r13);     // Catch: Throwable -> L5
    L27:
        if (r0.length() <= 0) goto L30;
        return r0.toString();
    L30:
        return null;
    L5:
        th = move-exception;
        FileLog.e(th);
        return this.label;
    }

    public static int[] cut(int[] r6, int r7) {
        int r0 = 0;
        int r1 = 0;
        boolean r2 = false;
        int r3 = 0;
    L4:
        if (r1 >= r6.length) goto L11;
        int r4 = r6[r1];
        if (r4 > r7) goto L10;
        r3 = r3 + 1;
        if (r4 != r7) goto L10;
        r2 = true;
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        if (r2 == true) goto L14;
        r3 = r3 + 1;
    L14:
        if (r3 != r6.length) goto L16;
        return r6;
    L16:
        int[] r12 = new int[r3];
        int r32 = 0;
    L18:
        if (r0 >= r6.length) goto L23;
        int r42 = r6[r0];
        if (r42 > r7) goto L22;
        r12[r32] = r42;
        r32 = r32 + 1;
    L22:
        r0 = r0 + 1;
        goto L18
    L23:
        if (r2 == true) goto L25;
        r12[r32] = r7;
    L25:
        return r12;
    }

    private /* synthetic */ void lambda$setMaxTextEmojiSaturation$0(ValueAnimator r3) {
        ColorMatrix r0 = new ColorMatrix();
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.maxTextEmojiSaturation = r32;
        r0.setSaturation(r32);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        AndroidUtilities.adjustBrightnessColorMatrix(r0, (1.0f - this.maxTextEmojiSaturation) * (-0.3f));
    L5:
        this.maxText.setEmojiColorFilter(new ColorMatrixColorFilter(r0));
    }

    private void setMaxTextEmojiSaturation(float r3, boolean r4) {
        if (Math.abs(this.toMaxTextEmojiSaturation - r3) >= 0.01f) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.maxTextEmojiSaturationAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.maxTextEmojiSaturationAnimator = null;
    L8:
        this.toMaxTextEmojiSaturation = r3;
        if (r4 == false) goto L12;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.maxTextEmojiSaturation, r3});
        this.maxTextEmojiSaturationAnimator = r42;
        r42.addUpdateListener(new SlideIntChooseView$$ExternalSyntheticLambda0(this));
        this.maxTextEmojiSaturationAnimator.addListener(new AnonymousClass3(this, r3));
        this.maxTextEmojiSaturationAnimator.setDuration(240);
        this.maxTextEmojiSaturationAnimator.start();
        return;
    L12:
        ColorMatrix r43 = new ColorMatrix();
        this.maxTextEmojiSaturation = r3;
        r43.setSaturation(r3);
        if (Theme.isCurrentThemeDark() == false) goto L15;
        AndroidUtilities.adjustBrightnessColorMatrix(r43, (1.0f - this.maxTextEmojiSaturation) * (-0.3f));
    L15:
        this.maxText.setEmojiColorFilter(new ColorMatrixColorFilter(r43));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.needDivider == false) goto L6;
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    }

    public float getProgress(int r8) {
        if (this.options.steps == null) goto L14;
        int r1 = 1;
    L5:
        int[] r3 = this.options.steps;
        if (r1 >= r3.length) goto L14;
        int r5 = r3[r1 - 1];
        int r6 = r3[r1];
        if (r8 < r5) goto L12;
        if (r8 > r6) goto L12;
        return (1.0f / (r3.length - 1)) * (r4 + (Math.round(((r8 - r5) / (r6 - r5)) * r2.betweenSteps) / this.options.betweenSteps));
    L12:
        r1 = r1 + 1;
    L14:
        return Utilities.clamp01((r8 - this.options.getMin()) / (this.options.getMax() - this.options.getMin()));
    }

    public int getStep(int r5) {
        if (this.options.steps == null) goto L12;
        int r0 = 1;
    L5:
        int[] r1 = this.options.steps;
        if (r0 >= r1.length) goto L12;
        int r2 = r0 - 1;
        int r3 = r1[r2];
        int r12 = r1[r0];
        if (r5 < r3) goto L11;
        if (r5 > r12) goto L11;
        return r2;
    L11:
        r0 = r0 + 1;
    L12:
        return r5;
    }

    public int getValue(float r7) {
        if (this.options.steps == null) goto L7;
        double r0 = r7 * (r1.length - 1);
        int r72 = Utilities.clamp((int) Math.floor(r0), this.options.steps.length - 1, 0);
        int r2 = Utilities.clamp((int) Math.ceil(r0), this.options.steps.length - 1, 0);
        int[] r3 = this.options.steps;
        return Math.round(AndroidUtilities.lerp(r3[r72], r3[r2], Math.round(((float) (r0 - Math.floor(r0))) * this.options.betweenSteps) / this.options.betweenSteps));
    L7:
        return Math.round(r0.getMin() + ((this.options.getMax() - this.options.getMin()) * r7));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(75.0f), TLObject.FLAG_30));
        if (Build.VERSION.SDK_INT < 29) goto L6;
        setSystemGestureExclusionRects(Arrays.asList(new Rect[]{new Rect(0, 0, AndroidUtilities.dp(80.0f), getMeasuredHeight()), new Rect(getMeasuredWidth() - AndroidUtilities.dp(80.0f), 0, getMeasuredWidth(), getMeasuredHeight())}));
        return;
    }

    public void set(int r2, Options r3, Utilities.Callback<Integer> r4) {
        this.value = r2;
        this.options = r3;
        this.whenChanged = r4;
        this.seekBarView.setProgress(getProgress(r2), false);
        updateTexts(r2, false);
    }

    public void setLabel(CharSequence r1) {
        this.label = r1;
    }

    public void setMinValueAllowed(int r2) {
        this.minValueAllowed = r2;
        if (this.value >= r2) goto L6;
        this.value = r2;
    L6:
        if (this.options != null) goto L8;
        return;
    L8:
        this.seekBarView.setMinProgress(getProgress(r2));
        updateTexts(this.value, false);
        invalidate();
    }

    public void setNeedDivider(boolean r2) {
        if (this.needDivider == r2) goto L6;
        this.needDivider = r2;
        invalidate();
        return;
    }

    public void updateTexts(int r5, boolean r6) {
        Options r0 = this.options;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.toString == null) goto L22;
        this.minText.cancelAnimation();
        this.maxText.cancelAnimation();
        this.valueText.cancelAnimation();
        this.valueText.setText(this.options.toString.run(0, Integer.valueOf(r5)), r6);
        this.minText.setText(this.options.toString.run(-1, Integer.valueOf(this.options.getMin())), r6);
        this.maxText.setText(this.options.toString.run(1, Integer.valueOf(this.options.getMax())), r6);
        AnimatedTextView r02 = this.minText;
        if (r5 > this.options.getMin()) goto L10;
        int r1 = Theme.key_windowBackgroundWhiteValueText;
    L11:
        r02.setTextColor(Theme.getColor(r1, this.resourcesProvider), r6);
        AnimatedTextView r03 = this.maxText;
        if (r5 < this.options.getMax()) goto L14;
        int r12 = Theme.key_windowBackgroundWhiteValueText;
    L15:
        r03.setTextColor(Theme.getColor(r12, this.resourcesProvider), r6);
        if (r5 < this.options.getMax()) goto L18;
        float r52 = 1.0f;
    L19:
        setMaxTextEmojiSaturation(r52, r6);
        return;
    L18:
        r52 = 0.0f;
        goto L19
    L14:
        r12 = Theme.key_windowBackgroundWhiteGrayText;
        goto L15
    L10:
        r1 = Theme.key_windowBackgroundWhiteGrayText;
        goto L11
    }
}
