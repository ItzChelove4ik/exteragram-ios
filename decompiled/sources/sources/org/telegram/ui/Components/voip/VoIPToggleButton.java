package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIPToggleButton extends FrameLayout {
    private boolean animateBackground;
    int animateToBackgroundColor;
    public int animationDelay;
    private int backgroundCheck1;
    private int backgroundCheck2;
    int backgroundColor;
    private ValueAnimator checkAnimator;
    private boolean checkable;
    private boolean checked;
    private float checkedProgress;
    Paint circlePaint;
    private float crossOffset;
    private float crossOffsetY;
    private final Paint crossPaint;
    private float crossProgress;
    int currentBackgroundColor;
    int currentIconColor;
    int currentIconRes;
    String currentText;
    private final float diameter;
    private boolean drawBackground;
    private boolean drawCross;
    private boolean drawRipple;
    Drawable[] icon;
    private boolean iconChangeColor;
    private float pressedScale;
    private ValueAnimator pressedScaleAnimator;
    ValueAnimator replaceAnimator;
    private int replaceColorFrom;
    float replaceProgress;
    Drawable rippleDrawable;
    FrameLayout textLayoutContainer;
    TextView[] textView;
    private final Paint xRefPaint;

    /* renamed from: org.telegram.ui.Components.voip.VoIPToggleButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPToggleButton this$0;
        final /* synthetic */ boolean val$animateText;

        public AnonymousClass1(VoIPToggleButton r1, boolean r2) {
            this.this$0 = r1;
            this.val$animateText = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            VoIPToggleButton r62 = this.this$0;
            r62.replaceAnimator = null;
            if (this.val$animateText == false) goto L6;
            TextView[] r63 = r62.textView;
            TextView r1 = r63[0];
            r63[0] = r63[1];
            r63[1] = r1;
            r1.setVisibility(8);
        L6:
            if (VoIPToggleButton.m13994$$Nest$fgeticonChangeColor(this.this$0) == true) goto L10;
            Drawable[] r64 = this.this$0.icon;
            Drawable r12 = r64[1];
            if (r12 == null) goto L10;
            r64[0] = r12;
            r64[1] = null;
        L10:
            VoIPToggleButton.m13996$$Nest$fputiconChangeColor(this.this$0, false);
            if (VoIPToggleButton.m13992$$Nest$fgetcheckable(this.this$0) == true) goto L13;
            VoIPToggleButton r65 = this.this$0;
            r65.backgroundColor = r65.animateToBackgroundColor;
        L13:
            VoIPToggleButton r5 = this.this$0;
            r5.replaceProgress = 0.0f;
            r5.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPToggleButton$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPToggleButton this$0;

        public AnonymousClass2(VoIPToggleButton r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIPToggleButton r22 = this.this$0;
            if (VoIPToggleButton.m13993$$Nest$fgetchecked(r22) == false) goto L5;
            float r0 = 1.0f;
        L6:
            VoIPToggleButton.m13995$$Nest$fputcheckedProgress(r22, r0);
            VoIPToggleButton r23 = this.this$0;
            r23.setBackgroundColor(VoIPToggleButton.m13990$$Nest$fgetbackgroundCheck1(r23), VoIPToggleButton.m13991$$Nest$fgetbackgroundCheck2(this.this$0));
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$64NEjtoayAmAlyewCQQvZ92EAUM(VoIPToggleButton r0, boolean r1, ValueAnimator r2) {
        r0.lambda$setData$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FKrg0BKrQhaVFPTLE3SNAfkVsU0(VoIPToggleButton r0, ValueAnimator r1) {
        r0.lambda$setPressedBtn$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ODuK6ABAmi06V4El3FEtQnM_X5g(VoIPToggleButton r0, ValueAnimator r1) {
        r0.lambda$setChecked$2(r1);
    }

    /* renamed from: -$$Nest$fgetbackgroundCheck1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13990$$Nest$fgetbackgroundCheck1(VoIPToggleButton r0) {
        return r0.backgroundCheck1;
    }

    /* renamed from: -$$Nest$fgetbackgroundCheck2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13991$$Nest$fgetbackgroundCheck2(VoIPToggleButton r0) {
        return r0.backgroundCheck2;
    }

    /* renamed from: -$$Nest$fgetcheckable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13992$$Nest$fgetcheckable(VoIPToggleButton r0) {
        return r0.checkable;
    }

    /* renamed from: -$$Nest$fgetchecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13993$$Nest$fgetchecked(VoIPToggleButton r0) {
        return r0.checked;
    }

    /* renamed from: -$$Nest$fgeticonChangeColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13994$$Nest$fgeticonChangeColor(VoIPToggleButton r0) {
        return r0.iconChangeColor;
    }

    /* renamed from: -$$Nest$fputcheckedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13995$$Nest$fputcheckedProgress(VoIPToggleButton r0, float r1) {
        r0.checkedProgress = r1;
    }

    /* renamed from: -$$Nest$fputiconChangeColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13996$$Nest$fputiconChangeColor(VoIPToggleButton r0, boolean r1) {
        r0.iconChangeColor = r1;
    }

    public VoIPToggleButton(Context r14, float r15) {
        super(r14);
        this.circlePaint = new Paint(1);
        this.drawBackground = true;
        this.drawRipple = true;
        this.icon = new Drawable[2];
        this.textView = new TextView[2];
        this.crossPaint = new Paint(1);
        this.xRefPaint = new Paint(1);
        this.pressedScale = 1.0f;
        this.diameter = r15;
        int r2 = 0;
        setWillNotDraw(false);
        FrameLayout r3 = new FrameLayout(r14);
        this.textLayoutContainer = r3;
        addView(r3);
    L3:
        if (r2 >= 2) goto L5;
        TextView r32 = new TextView(r14);
        r32.setGravity(1);
        r32.setTextSize(1, 11.0f);
        r32.setTextColor(-1);
        r32.setImportantForAccessibility(2);
        this.textLayoutContainer.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, r15 + 6.0f, 0.0f, 0.0f));
        this.textView[r2] = r32;
        r2 = r2 + 1;
        goto L3
    L5:
        this.textView[1].setVisibility(8);
        this.xRefPaint.setColor(-16777216);
        this.xRefPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.xRefPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.crossPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.crossPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private /* synthetic */ void lambda$setChecked$2(ValueAnimator r2) {
        this.checkedProgress = ((Float) r2.getAnimatedValue()).floatValue();
        setBackgroundColor(this.backgroundCheck1, this.backgroundCheck2);
    }

    private /* synthetic */ void lambda$setData$1(boolean r3, ValueAnimator r4) {
        this.replaceProgress = ((Float) r4.getAnimatedValue()).floatValue();
        invalidate();
        if (r3 == false) goto L6;
        this.textView[0].setAlpha(1.0f - this.replaceProgress);
        this.textView[0].setScaleX(1.0f - this.replaceProgress);
        this.textView[0].setScaleY(1.0f - this.replaceProgress);
        this.textView[1].setAlpha(this.replaceProgress);
        this.textView[1].setScaleX(this.replaceProgress);
        this.textView[1].setScaleY(this.replaceProgress);
        return;
    }

    private /* synthetic */ void lambda$setPressedBtn$0(ValueAnimator r1) {
        this.pressedScale = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.setState(getDrawableState());
        return;
    }

    public View getTextView() {
        return this.textView[0];
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r17) {
        Canvas r1 = r17;
        r1.save();
        float r2 = this.pressedScale;
        r1.scale(r2, r2, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
        if (this.animateBackground == false) goto L7;
        float r22 = this.replaceProgress;
        if (r22 == 0.0f) goto L7;
        this.circlePaint.setColor(ColorUtils.blendARGB(this.backgroundColor, this.animateToBackgroundColor, r22));
    L8:
        float r9 = getWidth() / 2.0f;
        float r10 = AndroidUtilities.dp(this.diameter) / 2.0f;
        float r23 = AndroidUtilities.dp(this.diameter) / 2.0f;
        if (this.drawBackground == false) goto L12;
        r1.drawCircle(r9, r10, r23, this.circlePaint);
    L12:
        if (this.rippleDrawable != null) goto L15;
        Drawable r4 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(this.diameter), 0, -16777216);
        this.rippleDrawable = r4;
        r4.setCallback(this);
    L15:
        if (this.drawRipple == false) goto L18;
        this.rippleDrawable.setBounds((int) (r9 - r23), (int) (r10 - r23), (int) (r9 + r23), (int) (r23 + r10));
        this.rippleDrawable.draw(r1);
    L18:
        if (this.currentIconRes != 0) goto L20;
    L77:
        r1.restore();
        return;
    L20:
        if (this.drawCross == true) goto L53;
        if (this.crossProgress != 0.0f) goto L53;
        int r24 = 0;
    L26:
        if (this.replaceProgress != 0.0f) goto L28;
    L31:
        int r6 = 1;
    L32:
        if (r24 >= r6) goto L77;
        if (this.icon[r24] == null) goto L51;
        r1.save();
        float r62 = this.replaceProgress;
        if (r62 == 0.0f) goto L47;
        if (this.iconChangeColor == true) goto L47;
        Drawable[] r12 = this.icon;
        if (r12[0] == null) goto L47;
        if (r12[1] == null) goto L47;
        if (r24 != 0) goto L45;
        r62 = 1.0f - r62;
    L45:
        r1.scale(r62, r62, r9, r10);
        this.icon[r24].setAlpha((int) (r62 * 255.0f));
    L50:
        this.icon[r24].setBounds((int) (r9 - (r6.getIntrinsicWidth() / 2.0f)), (int) (r10 - (this.icon[r24].getIntrinsicHeight() / 2.0f)), (int) ((this.icon[r24].getIntrinsicWidth() / 2.0f) + r9), (int) ((this.icon[r24].getIntrinsicHeight() / 2.0f) + r10));
        this.icon[r24].draw(r1);
        r1.restore();
    L47:
        if (this.iconChangeColor == false) goto L49;
        int r63 = ColorUtils.blendARGB(this.replaceColorFrom, this.currentIconColor, r62);
        this.icon[r24].setColorFilter(new PorterDuffColorFilter(r63, PorterDuff.Mode.MULTIPLY));
        this.crossPaint.setColor(r63);
    L49:
        this.icon[r24].setAlpha(255);
    L51:
        r24 = r24 + 1;
        goto L26
    L28:
        if (this.iconChangeColor == true) goto L31;
        r6 = 2;
    L53:
        if (this.iconChangeColor == false) goto L55;
        int r25 = ColorUtils.blendARGB(this.replaceColorFrom, this.currentIconColor, this.replaceProgress);
        this.icon[0].setColorFilter(new PorterDuffColorFilter(r25, PorterDuff.Mode.MULTIPLY));
        this.crossPaint.setColor(r25);
    L55:
        this.icon[0].setAlpha(255);
        float r26 = this.replaceProgress;
        if (r26 != 0.0f) goto L58;
    L60:
        this.icon[0].setAlpha(255);
        boolean r27 = this.drawCross;
        if (r27 == false) goto L68;
        float r64 = this.crossProgress;
        if (r64 >= 1.0f) goto L68;
        float r65 = r64 + 0.08f;
        this.crossProgress = r65;
        if (r65 <= 1.0f) goto L67;
        this.crossProgress = 1.0f;
    L73:
        float r28 = this.crossProgress;
        Drawable[] r3 = this.icon;
        if (r28 <= 0.0f) goto L76;
        int r29 = (int) (r9 - (r3[0].getIntrinsicWidth() / 2.0f));
        float r122 = (r29 + AndroidUtilities.dpf2(8.0f)) + this.crossOffset;
        float r13 = (((int) (r10 - (this.icon[0].getIntrinsicHeight() / 2.0f))) + AndroidUtilities.dpf2(8.0f)) + this.crossOffsetY;
        float r42 = AndroidUtilities.dp(17.0f);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.DEFAULT;
        float r14 = (r122 - AndroidUtilities.dp(1.0f)) + (r42 * r5.getInterpolation(this.crossProgress));
        float r15 = r13 + (AndroidUtilities.dp(17.0f) * r5.getInterpolation(this.crossProgress));
        r1.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
        this.icon[0].setBounds((int) (r9 - (r2.getIntrinsicWidth() / 2.0f)), (int) (r10 - (this.icon[0].getIntrinsicHeight() / 2.0f)), (int) (r9 + (this.icon[0].getIntrinsicWidth() / 2.0f)), (int) (r10 + (this.icon[0].getIntrinsicHeight() / 2.0f)));
        this.icon[0].draw(r1);
        r1.drawLine(r122, r13 - AndroidUtilities.dp(2.0f), r14, r15 - AndroidUtilities.dp(2.0f), this.xRefPaint);
        r17.drawLine(r122, r13, r14, r15, this.crossPaint);
        r1 = r17;
        r1.restore();
        goto L77
    L76:
        r3[0].setBounds((int) (r9 - (r2.getIntrinsicWidth() / 2.0f)), (int) (r10 - (this.icon[0].getIntrinsicHeight() / 2.0f)), (int) (r9 + (this.icon[0].getIntrinsicWidth() / 2.0f)), (int) (r10 + (this.icon[0].getIntrinsicHeight() / 2.0f)));
        this.icon[0].draw(r1);
        goto L77
    L67:
        invalidate();
    L68:
        if (r27 == true) goto L73;
        float r210 = this.crossProgress - 0.08f;
        this.crossProgress = r210;
        if (r210 >= 0.0f) goto L72;
        this.crossProgress = 0.0f;
        goto L73
    L72:
        invalidate();
        goto L73
    L58:
        if (this.iconChangeColor == false) goto L60;
        int r211 = ColorUtils.blendARGB(this.replaceColorFrom, this.currentIconColor, r26);
        this.icon[0].setColorFilter(new PorterDuffColorFilter(r211, PorterDuff.Mode.MULTIPLY));
        this.crossPaint.setColor(r211);
    L7:
        this.circlePaint.setColor(this.backgroundColor);
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setText(this.currentText);
        if (this.checkable == false) goto L6;
        r2.setClassName(ToggleButton.class.getName());
        r2.setCheckable(true);
        r2.setChecked(this.checked);
        return;
    L6:
        r2.setClassName(Button.class.getName());
    }

    public void setBackgroundColor(int r2, int r3) {
        this.backgroundCheck1 = r2;
        this.backgroundCheck2 = r3;
        this.backgroundColor = ColorUtils.blendARGB(r2, r3, this.checkedProgress);
        invalidate();
    }

    public void setCheckable(boolean r1) {
        this.checkable = r1;
    }

    public void setChecked(boolean r3, boolean r4) {
        if (this.checked == r3) goto L22;
        this.checked = r3;
        if (this.checkable == false) goto L21;
        float r0 = 0.0f;
        if (r4 == false) goto L17;
        ValueAnimator r32 = this.checkAnimator;
        if (r32 == null) goto L12;
        r32.removeAllListeners();
        this.checkAnimator.cancel();
    L12:
        float r33 = this.checkedProgress;
        if (this.checked == false) goto L15;
        r0 = 1.0f;
    L15:
        ValueAnimator r34 = ValueAnimator.ofFloat(new float[]{r33, r0});
        this.checkAnimator = r34;
        r34.addUpdateListener(new VoIPToggleButton$$ExternalSyntheticLambda0(this));
        this.checkAnimator.addListener(new AnonymousClass2(this));
        this.checkAnimator.setDuration(150);
        this.checkAnimator.start();
        return;
    L17:
        if (r3 == false) goto L19;
        r0 = 1.0f;
    L19:
        this.checkedProgress = r0;
        setBackgroundColor(this.backgroundCheck1, this.backgroundCheck2);
        return;
    L21:
        return;
    }

    public void setCrossOffset(float r1) {
        this.crossOffset = r1;
    }

    public void setCrossOffsetY(float r1) {
        this.crossOffsetY = r1;
    }

    public void setData(int r7, int r8, int r9, float r10, boolean r11, String r12, boolean r13, boolean r14) {
        if (getVisibility() == 0) goto L6;
        setVisibility(0);
        r14 = false;
    L6:
        if (this.currentIconRes != r7) goto L21;
        if (this.currentIconColor != r8) goto L21;
        if (this.checkable == false) goto L12;
    L13:
        String r0 = this.currentText;
        if (r0 == null) goto L21;
        if (r0.equals(r12) == false) goto L21;
        if (r13 != this.drawCross) goto L21;
        return;
    L12:
        if (this.currentBackgroundColor == r9) goto L13;
    L21:
        if (this.rippleDrawable == null) goto L24;
        if (r11 == true) goto L24;
    L29:
        ValueAnimator r102 = this.replaceAnimator;
        if (r102 == null) goto L33;
        r102.cancel();
    L33:
        if (this.currentBackgroundColor == r9) goto L35;
        boolean r103 = true;
    L36:
        this.animateBackground = r103;
        if (this.currentIconRes != r7) goto L39;
        boolean r104 = true;
    L40:
        this.iconChangeColor = r104;
        if (r104 == false) goto L43;
        this.replaceColorFrom = this.currentIconColor;
    L43:
        this.currentIconRes = r7;
        this.currentIconColor = r8;
        this.currentBackgroundColor = r9;
        this.currentText = r12;
        this.drawCross = r13;
        if (r14 == true) goto L56;
        if (r7 == 0) goto L47;
        this.icon[0] = ContextCompat.getDrawable(getContext(), r7).mutate();
        this.icon[0].setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.MULTIPLY));
    L47:
        this.crossPaint.setColor(r8);
        if (this.checkable == true) goto L50;
        this.backgroundColor = r9;
    L50:
        this.textView[0].setText(r12);
        if (this.drawCross == false) goto L53;
        float r72 = 1.0f;
    L54:
        this.crossProgress = r72;
        this.iconChangeColor = false;
        this.replaceProgress = 0.0f;
        invalidate();
        return;
    L53:
        r72 = 0.0f;
        goto L54
    L56:
        if (r104 == true) goto L60;
        if (r7 == 0) goto L60;
        this.icon[1] = ContextCompat.getDrawable(getContext(), r7).mutate();
        this.icon[1].setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.MULTIPLY));
    L60:
        if (this.checkable == true) goto L62;
        this.animateToBackgroundColor = r9;
    L62:
        boolean r73 = this.textView[0].getText().toString().equals(r12);
        boolean r82 = !r73;
        TextView[] r92 = this.textView;
        if (r73 == false) goto L65;
        r92[0].setText(r12);
    L66:
        ValueAnimator r74 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.replaceAnimator = r74;
        r74.addUpdateListener(new VoIPToggleButton$$ExternalSyntheticLambda1(this, r82));
        this.replaceAnimator.addListener(new AnonymousClass1(this, r82));
        this.replaceAnimator.setDuration(150).start();
        invalidate();
        return;
    L65:
        r92[1].setText(r12);
        this.textView[1].setVisibility(0);
        this.textView[1].setAlpha(0.0f);
        this.textView[1].setScaleX(0.0f);
        this.textView[1].setScaleY(0.0f);
        goto L66
    L39:
        r104 = false;
        goto L40
    L35:
        r103 = false;
    L24:
        if (Color.alpha(r9) == 255) goto L26;
    L28:
        Drawable r105 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(this.diameter), 0, ColorUtils.setAlphaComponent(-1, (int) (r10 * 76.5f)));
        this.rippleDrawable = r105;
        r105.setCallback(this);
        goto L29
    L26:
        if (AndroidUtilities.computePerceivedBrightness(r9) <= 0.5d) goto L28;
        Drawable r106 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(this.diameter), 0, ColorUtils.setAlphaComponent(-16777216, (int) (r10 * 25.5f)));
        this.rippleDrawable = r106;
        r106.setCallback(this);
        goto L29
    }

    public void setData(int r10, int r11, int r12, String r13, boolean r14, boolean r15) {
        setData(r10, r11, r12, 1.0f, true, r13, r14, r15);
    }

    public void setDrawBackground(boolean r1) {
        this.drawBackground = r1;
    }

    public void setDrawRipple(boolean r1) {
        this.drawRipple = r1;
    }

    public void setPressedBtn(boolean r4) {
        ValueAnimator r0 = this.pressedScaleAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r02 = this.pressedScale;
        if (r4 == false) goto L8;
        float r42 = 0.8f;
    L9:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r02, r42});
        this.pressedScaleAnimator = r43;
        r43.addUpdateListener(new VoIPToggleButton$$ExternalSyntheticLambda2(this));
        this.pressedScaleAnimator.setDuration(150);
        this.pressedScaleAnimator.start();
        return;
    L8:
        r42 = 1.0f;
        goto L9
    }

    public void setTextSize(int r5) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L6;
        this.textView[r0].setTextSize(1, r5);
        r0 = r0 + 1;
        goto L4
    }

    public void shakeView() {
        AndroidUtilities.shakeView(this.textView[0]);
        AndroidUtilities.shakeView(this.textView[1]);
    }

    public void showText(boolean r3, boolean r4) {
        float r0 = 0.0f;
        if (r4 == false) goto L10;
        if (r3 == false) goto L7;
        r0 = 1.0f;
    L7:
        if (this.textLayoutContainer.getAlpha() == r0) goto L15;
        this.textLayoutContainer.animate().alpha(r0).start();
        return;
    L15:
        return;
    L10:
        this.textLayoutContainer.animate().cancel();
        FrameLayout r2 = this.textLayoutContainer;
        if (r3 == false) goto L13;
        r0 = 1.0f;
    L13:
        r2.setAlpha(r0);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.rippleDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
