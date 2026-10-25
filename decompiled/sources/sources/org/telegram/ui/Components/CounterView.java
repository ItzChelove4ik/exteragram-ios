package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CounterView extends View {
    public CounterDrawable counterDrawable;
    private final Theme.ResourcesProvider resourcesProvider;

    public static class CounterDrawable {
        public boolean addServiceGradient;
        int animationType;
        private int circleColor;
        private int circleColorKey;
        public Paint circlePaint;
        public float circleScale;
        private StaticLayout countAnimationInLayout;
        private boolean countAnimationIncrement;
        private StaticLayout countAnimationStableLayout;
        private ValueAnimator countAnimator;
        public float countChangeProgress;
        private StaticLayout countLayout;
        private float countLayoutWidth;
        float countLeft;
        private StaticLayout countOldLayout;
        private int countWidth;
        private int countWidthOld;
        int currentCount;
        CharSequence currentText;
        private boolean drawBackground;
        public int gravity;
        public float horizontalPadding;
        int lastH;
        private View parent;
        public float radius;
        public RectF rectF;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean reverseAnimation;
        public boolean shortFormat;
        private int textColor;
        private int textColorKey;
        public TextPaint textPaint;
        int type;
        public boolean updateVisibility;
        int width;
        float x;

        /* renamed from: org.telegram.ui.Components.CounterView$CounterDrawable$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ CounterDrawable this$0;

            public AnonymousClass1(CounterDrawable r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                CounterDrawable r22 = this.this$0;
                r22.countChangeProgress = 1.0f;
                CounterDrawable.m10043$$Nest$fputcountOldLayout(r22, null);
                CounterDrawable.m10042$$Nest$fputcountAnimationStableLayout(this.this$0, null);
                CounterDrawable.m10041$$Nest$fputcountAnimationInLayout(this.this$0, null);
                if (CounterDrawable.m10039$$Nest$fgetparent(this.this$0) == null) goto L10;
                CounterDrawable r23 = this.this$0;
                if (r23.currentCount == 0) goto L7;
            L9:
                CounterDrawable.m10039$$Nest$fgetparent(this.this$0).invalidate();
                goto L10
            L7:
                if (r23.updateVisibility == false) goto L9;
                CounterDrawable.m10039$$Nest$fgetparent(r23).setVisibility(8);
            L10:
                this.this$0.animationType = -1;
            }
        }

        public static /* synthetic */ void $r8$lambda$q1k11ht5LSbUcfK8f6ut3vtkZvo(CounterDrawable r0, ValueAnimator r1) {
            r0.lambda$setText$0(r1);
        }

        /* renamed from: -$$Nest$fgetparent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10039$$Nest$fgetparent(CounterDrawable r0) {
            return r0.parent;
        }

        /* renamed from: -$$Nest$fputcircleColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10040$$Nest$fputcircleColorKey(CounterDrawable r0, int r1) {
            r0.circleColorKey = r1;
        }

        /* renamed from: -$$Nest$fputcountAnimationInLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10041$$Nest$fputcountAnimationInLayout(CounterDrawable r0, StaticLayout r1) {
            r0.countAnimationInLayout = r1;
        }

        /* renamed from: -$$Nest$fputcountAnimationStableLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10042$$Nest$fputcountAnimationStableLayout(CounterDrawable r0, StaticLayout r1) {
            r0.countAnimationStableLayout = r1;
        }

        /* renamed from: -$$Nest$fputcountOldLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10043$$Nest$fputcountOldLayout(CounterDrawable r0, StaticLayout r1) {
            r0.countOldLayout = r1;
        }

        /* renamed from: -$$Nest$fputreverseAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10044$$Nest$fputreverseAnimation(CounterDrawable r0, boolean r1) {
            r0.reverseAnimation = r1;
        }

        /* renamed from: -$$Nest$fputtextColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10045$$Nest$fputtextColorKey(CounterDrawable r0, int r1) {
            r0.textColorKey = r1;
        }

        public CounterDrawable(View r4, boolean r5, Theme.ResourcesProvider r6) {
            this.circleScale = 1.0f;
            this.animationType = -1;
            this.textPaint = new TextPaint(1);
            this.rectF = new RectF();
            this.countChangeProgress = 1.0f;
            this.textColorKey = Theme.key_chat_goDownButtonCounter;
            this.circleColorKey = Theme.key_chat_goDownButtonCounterBackground;
            this.gravity = 17;
            this.radius = 11.5f;
            this.type = 0;
            this.parent = r4;
            this.resourcesProvider = r6;
            this.drawBackground = r5;
            if (r5 == false) goto L5;
            Paint r42 = new Paint(1);
            this.circlePaint = r42;
            r42.setColor(-16777216);
        L5:
            this.textPaint.setTypeface(AndroidUtilities.bold());
            this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
        }

        private void drawInternal(Canvas r8) {
            float r0 = this.radius * 2.0f;
            float r2 = (this.lastH - AndroidUtilities.dp(r0)) / 2.0f;
            updateX(this.countWidth);
            RectF r1 = this.rectF;
            float r3 = this.x;
            r1.set(r3, r2, (this.countWidth + r3) + AndroidUtilities.dp(this.radius - 0.5f), AndroidUtilities.dp(r0) + r2);
            if (this.circlePaint == null) goto L18;
            if (this.drawBackground == false) goto L18;
            if (this.circleScale == 1.0f) goto L9;
            r8.save();
            float r02 = this.circleScale;
            r8.scale(r02, r02, this.rectF.centerX(), this.rectF.centerY());
            boolean r03 = true;
        L10:
            RectF r12 = this.rectF;
            float r32 = this.radius;
            float r4 = AndroidUtilities.density;
            r8.drawRoundRect(r12, r32 * r4, r32 * r4, this.circlePaint);
            if (this.addServiceGradient == true) goto L13;
        L15:
            if (r03 == false) goto L18;
            r8.restore();
            goto L18
        L13:
            if (Theme.hasGradientService() == false) goto L15;
            RectF r13 = this.rectF;
            float r33 = this.radius;
            float r42 = AndroidUtilities.density;
            r8.drawRoundRect(r13, r33 * r42, r33 * r42, Theme.chat_actionBackgroundGradientDarkenPaint);
            goto L15
        L9:
            r03 = false;
        L18:
            if (this.countLayout == null) goto L21;
            r8.save();
            r8.translate(this.countLeft, r2 + AndroidUtilities.dp(4.0f));
            this.countLayout.draw(r8);
            r8.restore();
            return;
        }

        private String getStringOfCCount(int r1) {
            if (this.shortFormat == false) goto L7;
            return AndroidUtilities.formatWholeNumber(r1, 0);
        L7:
            return String.valueOf(r1);
        }

        private /* synthetic */ void lambda$setText$0(ValueAnimator r1) {
            this.countChangeProgress = ((Float) r1.getAnimatedValue()).floatValue();
            View r0 = this.parent;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        private void updateX(float r6) {
            if (this.drawBackground == false) goto L5;
            float r0 = AndroidUtilities.dp(5.5f);
        L6:
            int r2 = this.gravity;
            if (r2 != 5) goto L13;
            float r22 = this.width - r0;
            this.countLeft = r22;
            float r3 = this.horizontalPadding;
            if (r3 == 0.0f) goto L11;
            this.countLeft = r22 - Math.max(r3 + (r6 / 2.0f), r6);
        L16:
            this.x = this.countLeft - r0;
            return;
        L11:
            this.countLeft = r22 - r6;
            goto L16
        L13:
            if (r2 != 3) goto L15;
            this.countLeft = r0;
            goto L16
        L15:
            this.countLeft = (int) ((this.width - r6) / 2.0f);
            goto L16
        L5:
            r0 = 0.0f;
            goto L6
        }

        public void draw(Canvas r12) {
            int r0 = this.type;
            boolean r1 = true;
            if (r0 != 1) goto L5;
        L14:
            float r02 = this.countChangeProgress;
            if (r02 == 1.0f) goto L86;
            int r3 = this.animationType;
            if (r3 == 0) goto L80;
            if (r3 == 1) goto L80;
            float r03 = r02 * 2.0f;
            if (r03 <= 1.0f) goto L23;
            r03 = 1.0f;
        L23:
            float r32 = (this.lastH - AndroidUtilities.dp(this.radius * 2.0f)) / 2.0f;
            int r5 = this.countWidth;
            int r6 = this.countWidthOld;
            if (r5 != r6) goto L26;
            float r52 = r5;
        L27:
            updateX(r52);
            if (this.countAnimationIncrement == false) goto L34;
            float r62 = this.countChangeProgress;
            if (r62 > 0.5f) goto L33;
            float r63 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r62 * 2.0f);
        L32:
            float r64 = (r63 * 0.1f) + 1.0f;
        L35:
            RectF r8 = this.rectF;
            float r9 = this.x;
            r8.set(r9, r32, (r52 + r9) + AndroidUtilities.dp(this.radius - 0.5f), AndroidUtilities.dp(this.radius * 2.0f) + r32);
            r12.save();
            r12.scale(r64, r64, this.rectF.centerX(), this.rectF.centerY());
            if (this.circleScale == 1.0f) goto L38;
            r12.save();
            float r4 = this.circleScale;
            r12.scale(r4, r4, this.rectF.centerX(), this.rectF.centerY());
            boolean r42 = true;
        L40:
            if (this.drawBackground == false) goto L48;
            Paint r65 = this.circlePaint;
            if (r65 == null) goto L48;
            RectF r7 = this.rectF;
            float r82 = this.radius;
            float r92 = AndroidUtilities.density;
            r12.drawRoundRect(r7, r82 * r92, r82 * r92, r65);
            if (this.addServiceGradient == false) goto L48;
            if (Theme.hasGradientService() == false) goto L48;
            RectF r66 = this.rectF;
            float r72 = this.radius;
            float r83 = AndroidUtilities.density;
            r12.drawRoundRect(r66, r72 * r83, r72 * r83, Theme.chat_actionBackgroundGradientDarkenPaint);
        L48:
            if (r42 == false) goto L50;
            r12.restore();
        L50:
            r12.clipRect(this.rectF);
            if (this.reverseAnimation != this.countAnimationIncrement) goto L55;
            r1 = false;
        L55:
            if (this.countAnimationInLayout == null) goto L62;
            r12.save();
            float r43 = this.countLeft;
            float r84 = AndroidUtilities.dp(4.0f) + r32;
            int r93 = AndroidUtilities.dp(13.0f);
            if (r1 == true) goto L60;
            r93 = -r93;
        L60:
            r12.translate(r43, r84 + (r93 * (1.0f - r03)));
            this.textPaint.setAlpha((int) (r03 * 255.0f));
            this.countAnimationInLayout.draw(r12);
            r12.restore();
        L69:
            if (this.countOldLayout == null) goto L76;
            r12.save();
            float r44 = this.countLeft;
            float r85 = AndroidUtilities.dp(4.0f) + r32;
            if (r1 == false) goto L73;
            int r13 = -AndroidUtilities.dp(13.0f);
        L74:
            r12.translate(r44, r85 + (r13 * r03));
            this.textPaint.setAlpha((int) ((1.0f - r03) * 255.0f));
            this.countOldLayout.draw(r12);
            r12.restore();
            goto L76
        L73:
            r13 = AndroidUtilities.dp(13.0f);
        L76:
            if (this.countAnimationStableLayout == null) goto L78;
            r12.save();
            r12.translate(this.countLeft, r32 + AndroidUtilities.dp(4.0f));
            this.textPaint.setAlpha(255);
            this.countAnimationStableLayout.draw(r12);
            r12.restore();
        L78:
            this.textPaint.setAlpha(255);
            r12.restore();
            return;
        L62:
            if (this.countLayout == null) goto L69;
            r12.save();
            float r45 = this.countLeft;
            float r86 = AndroidUtilities.dp(4.0f) + r32;
            int r94 = AndroidUtilities.dp(13.0f);
            if (r1 == true) goto L67;
            r94 = -r94;
        L67:
            r12.translate(r45, r86 + (r94 * (1.0f - r03)));
            this.textPaint.setAlpha((int) (r03 * 255.0f));
            this.countLayout.draw(r12);
            r12.restore();
            goto L69
        L38:
            r42 = false;
            goto L40
        L33:
            r63 = CubicBezierInterpolator.EASE_IN.getInterpolation(1.0f - ((r62 - 0.5f) * 2.0f));
            goto L32
        L34:
            r64 = 1.0f;
            goto L35
        L26:
            r52 = (r5 * r03) + (r6 * (1.0f - r03));
        L80:
            updateX(this.countWidth);
            float r04 = this.countLeft + (this.countWidth / 2.0f);
            float r14 = this.lastH / 2.0f;
            r12.save();
            int r33 = this.animationType;
            float r46 = this.countChangeProgress;
            if (r33 == 0) goto L84;
            r46 = 1.0f - r46;
        L84:
            r12.scale(r46, r46, r04, r14);
            drawInternal(r12);
            r12.restore();
            return;
        L86:
            drawInternal(r12);
            return;
        L5:
            if (r0 == 2) goto L14;
            int r05 = getThemedColor(this.textColorKey);
            int r2 = getThemedColor(this.circleColorKey);
            if (this.textColor == r05) goto L9;
            this.textColor = r05;
            this.textPaint.setColor(r05);
        L9:
            Paint r06 = this.circlePaint;
            if (r06 == null) goto L14;
            if (this.circleColor == r2) goto L14;
            this.circleColor = r2;
            r06.setColor(r2);
            goto L14
        }

        public float getCenterX() {
            updateX(this.countWidth);
            return this.countLeft + (this.countWidth / 2.0f);
        }

        public int getCurrentWidth() {
            return (int) Math.ceil(this.countLayoutWidth);
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public int getWidth() {
            if (this.currentCount != 0) goto L7;
            return 0;
        L7:
            return this.countWidth + AndroidUtilities.dp(this.radius - 0.5f);
        }

        public void setCount(int r3, boolean r4) {
            setText(getStringOfCCount(r3), r4, r3, false);
        }

        public void setParent(View r1) {
            this.parent = r1;
        }

        public void setSize(int r3, int r4) {
            if (r3 == this.lastH) goto L9;
            int r0 = this.currentCount;
            this.currentCount = -1;
            if (this.animationType != 0) goto L7;
            boolean r1 = true;
        L8:
            setCount(r0, r1);
            this.lastH = r3;
            goto L9
        L7:
            r1 = false;
        L9:
            this.width = r4;
        }

        public void setText(CharSequence r24, boolean r25, int r26, boolean r27) {
            if (TextUtils.equals(r24, this.currentText) == true) goto L82;
            ValueAnimator r1 = this.countAnimator;
            if (r1 == null) goto L9;
            r1.cancel();
        L9:
            if (r26 <= 0) goto L16;
            if (this.updateVisibility == false) goto L16;
            View r12 = this.parent;
            if (r12 == null) goto L16;
            r12.setVisibility(0);
        L16:
            if (Math.abs(r26 - this.currentCount) <= 99) goto L18;
            boolean r13 = false;
        L19:
            float r11 = 0.0f;
            if (r13 == true) goto L36;
            this.currentCount = r26;
            this.currentText = r24;
            if (r26 == 0) goto L24;
            this.countWidth = Math.max(AndroidUtilities.dp(12.0f), (int) Math.ceil(this.textPaint.measureText(r24.toString())));
            StaticLayout r14 = new StaticLayout(r24, this.textPaint, this.countWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.countLayout = r14;
            if (r14.getLineCount() < 1) goto L32;
            r11 = this.countLayout.getLineWidth(0);
        L32:
            this.countLayoutWidth = r11;
            View r0 = this.parent;
            if (r0 == null) goto L80;
            r0.invalidate();
            return;
        L80:
            return;
        L24:
            if (this.updateVisibility == false) goto L78;
            View r02 = this.parent;
            if (r02 == null) goto L79;
            r02.setVisibility(8);
            return;
        L79:
            return;
        L78:
            return;
        L36:
            if (r13 == false) goto L66;
            ValueAnimator r15 = this.countAnimator;
            if (r15 == null) goto L40;
            r15.cancel();
        L40:
            this.countChangeProgress = 0.0f;
            ValueAnimator r4 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.countAnimator = r4;
            r4.addUpdateListener(new CounterView$CounterDrawable$$ExternalSyntheticLambda0(this));
            this.countAnimator.addListener(new AnonymousClass1(this));
            if (this.currentCount > 0) goto L43;
            this.animationType = 0;
            this.countAnimator.setDuration(220);
            this.countAnimator.setInterpolator(new OvershootInterpolator());
        L47:
            if (this.countLayout == null) goto L61;
            CharSequence r16 = this.currentText;
            if (r16.length() != r24.length()) goto L60;
            if (r27 == true) goto L60;
            SpannableStringBuilder r142 = new SpannableStringBuilder(r16);
            SpannableStringBuilder r42 = new SpannableStringBuilder(r24);
            SpannableStringBuilder r5 = new SpannableStringBuilder(r24);
            int r6 = 0;
        L53:
            if (r6 >= r16.length()) goto L59;
            if (r16.charAt(r6) != r24.charAt(r6)) goto L57;
            int r8 = r6 + 1;
            r142.setSpan(new EmptyStubSpan(), r6, r8, 0);
            r42.setSpan(new EmptyStubSpan(), r6, r8, 0);
        L58:
            r6 = r6 + 1;
            goto L53
        L57:
            r5.setSpan(new EmptyStubSpan(), r6, r6 + 1, 0);
            goto L58
        L59:
            int r18 = Math.max(AndroidUtilities.dp(12.0f), (int) Math.ceil(this.textPaint.measureText(r16.toString())));
            TextPaint r152 = this.textPaint;
            Layout.Alignment r19 = Layout.Alignment.ALIGN_CENTER;
            this.countOldLayout = new StaticLayout(r142, r152, r18, r19, 1.0f, 0.0f, false);
            this.countAnimationStableLayout = new StaticLayout(r5, this.textPaint, r18, r19, 1.0f, 0.0f, false);
            this.countAnimationInLayout = new StaticLayout(r42, this.textPaint, r18, r19, 1.0f, 0.0f, false);
        L60:
            this.countOldLayout = this.countLayout;
        L61:
            this.countWidthOld = this.countWidth;
            if (r26 <= this.currentCount) goto L64;
            boolean r17 = true;
        L65:
            this.countAnimationIncrement = r17;
            this.countAnimator.start();
            goto L66
        L64:
            r17 = false;
            goto L65
        L43:
            if (r26 != 0) goto L45;
            this.animationType = 1;
            this.countAnimator.setDuration(150);
            this.countAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            goto L47
        L45:
            this.animationType = 2;
            this.countAnimator.setDuration(430);
            this.countAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        L66:
            if (r26 <= 0) goto L71;
            this.countWidth = Math.max(AndroidUtilities.dp(12.0f), (int) Math.ceil(this.textPaint.measureText(r24.toString())));
            StaticLayout r110 = new StaticLayout(r24, this.textPaint, this.countWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.countLayout = r110;
            if (r110.getLineCount() < 1) goto L70;
            r11 = this.countLayout.getLineWidth(0);
        L70:
            this.countLayoutWidth = r11;
        L71:
            this.currentCount = r26;
            this.currentText = r24;
            View r03 = this.parent;
            if (r03 == null) goto L81;
            r03.invalidate();
            return;
        L81:
            return;
        L18:
            r13 = r25;
            goto L19
        }

        public void setType(int r1) {
            this.type = r1;
        }

        public void updateBackgroundRect() {
            float r0 = this.countChangeProgress;
            if (r0 == 1.0f) goto L20;
            int r2 = this.animationType;
            if (r2 != 0) goto L7;
        L18:
            updateX(this.countWidth);
            float r02 = (this.lastH - AndroidUtilities.dp(this.radius * 2.0f)) / 2.0f;
            RectF r1 = this.rectF;
            float r22 = this.x;
            r1.set(r22, r02, (this.countWidth + r22) + AndroidUtilities.dp(11.0f), AndroidUtilities.dp(23.0f) + r02);
            return;
        L7:
            if (r2 == 1) goto L18;
            float r03 = r0 * 2.0f;
            if (r03 <= 1.0f) goto L12;
            r03 = 1.0f;
        L12:
            float r23 = (this.lastH - AndroidUtilities.dp(this.radius * 2.0f)) / 2.0f;
            int r5 = this.countWidth;
            int r6 = this.countWidthOld;
            if (r5 != r6) goto L15;
            float r04 = r5;
        L16:
            updateX(r04);
            RectF r12 = this.rectF;
            float r7 = this.x;
            r12.set(r7, r23, (r04 + r7) + AndroidUtilities.dp(11.0f), AndroidUtilities.dp(23.0f) + r23);
            return;
        L15:
            r04 = (r5 * r03) + (r6 * (1.0f - r03));
            goto L16
        L20:
            updateX(this.countWidth);
            float r05 = (this.lastH - AndroidUtilities.dp(this.radius * 2.0f)) / 2.0f;
            RectF r13 = this.rectF;
            float r24 = this.x;
            r13.set(r24, r05, (this.countWidth + r24) + AndroidUtilities.dp(11.0f), AndroidUtilities.dp(23.0f) + r05);
        }
    }

    public CounterView(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.resourcesProvider = r3;
        setVisibility(8);
        CounterDrawable r22 = new CounterDrawable(this, true, r3);
        this.counterDrawable = r22;
        r22.updateVisibility = true;
    }

    public float getEnterProgress() {
        CounterDrawable r4 = this.counterDrawable;
        float r0 = r4.countChangeProgress;
        if (r0 == 1.0f) goto L13;
        int r2 = r4.animationType;
        if (r2 != 0) goto L7;
    L8:
        if (r2 != 0) goto L11;
        return r0;
    L11:
        return 1.0f - r0;
    L7:
        if (r2 == 1) goto L8;
    L13:
        if (r4.currentCount != 0) goto L16;
        return 0.0f;
    L16:
        return 1.0f;
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
        this.counterDrawable.draw(r1);
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        this.counterDrawable.setSize(getMeasuredHeight(), getMeasuredWidth());
    }

    public void setColors(int r2, int r3) {
        CounterDrawable.m10045$$Nest$fputtextColorKey(this.counterDrawable, r2);
        CounterDrawable.m10040$$Nest$fputcircleColorKey(this.counterDrawable, r3);
    }

    public void setCount(int r1, boolean r2) {
        this.counterDrawable.setCount(r1, r2);
    }

    public void setGravity(int r1) {
        this.counterDrawable.gravity = r1;
    }

    public void setReverse(boolean r1) {
        CounterDrawable.m10044$$Nest$fputreverseAnimation(this.counterDrawable, r1);
    }
}
