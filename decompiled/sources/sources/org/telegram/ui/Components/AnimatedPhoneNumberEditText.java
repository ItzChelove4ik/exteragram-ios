package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.annotation.Keep;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes7.dex */
public class AnimatedPhoneNumberEditText extends HintEditText {
    private ObjectAnimator animator;
    private Runnable hintAnimationCallback;
    private List<Float> hintAnimationValues;
    private List<SpringAnimation> hintAnimations;
    private HintFadeProperty hintFadeProperty;
    private ArrayList<StaticLayout> letters;
    private ArrayList<StaticLayout> oldLetters;
    private String oldText;
    private float progress;
    private TextPaint textPaint;
    private String wasHint;
    private Boolean wasHintVisible;

    /* renamed from: org.telegram.ui.Components.AnimatedPhoneNumberEditText$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedPhoneNumberEditText this$0;

        public AnonymousClass1(AnimatedPhoneNumberEditText r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AnimatedPhoneNumberEditText.m8824$$Nest$fputanimator(this.this$0, null);
            AnimatedPhoneNumberEditText.m8823$$Nest$fgetoldLetters(this.this$0).clear();
        }
    }

    public final class HintFadeProperty extends FloatPropertyCompat<Integer> {
        final /* synthetic */ AnimatedPhoneNumberEditText this$0;

        public HintFadeProperty(AnimatedPhoneNumberEditText r1) {
            this.this$0 = r1;
            super("hint_fade");
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(Integer r3) {
            if (r3.intValue() < AnimatedPhoneNumberEditText.m8822$$Nest$fgethintAnimationValues(this.this$0).size()) goto L5;
            return 0.0f;
        L5:
            return ((Float) AnimatedPhoneNumberEditText.m8822$$Nest$fgethintAnimationValues(this.this$0).get(r3.intValue())).floatValue() * 100.0f;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(Integer r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(Integer r3, float r4) {
            if (r3.intValue() >= AnimatedPhoneNumberEditText.m8822$$Nest$fgethintAnimationValues(this.this$0).size()) goto L6;
            AnimatedPhoneNumberEditText.m8822$$Nest$fgethintAnimationValues(this.this$0).set(r3.intValue(), Float.valueOf(r4 / 100.0f));
            this.this$0.invalidate();
            return;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(Integer r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: $r8$lambda$ohAvjVTP9j05qd7ZoLO-IH5XshA, reason: not valid java name */
    public static /* synthetic */ void m8821$r8$lambda$ohAvjVTP9j05qd7ZoLOIH5XshA(AnimatedPhoneNumberEditText r0, boolean r1, String r2) {
        r0.lambda$setHintText$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgethintAnimationValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m8822$$Nest$fgethintAnimationValues(AnimatedPhoneNumberEditText r0) {
        return r0.hintAnimationValues;
    }

    /* renamed from: -$$Nest$fgetoldLetters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8823$$Nest$fgetoldLetters(AnimatedPhoneNumberEditText r0) {
        return r0.oldLetters;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8824$$Nest$fputanimator(AnimatedPhoneNumberEditText r0, ObjectAnimator r1) {
        r0.animator = r1;
    }

    public AnimatedPhoneNumberEditText(Context r2) {
        super(r2);
        this.letters = new ArrayList();
        this.oldLetters = new ArrayList();
        this.textPaint = new TextPaint(1);
        this.oldText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.hintFadeProperty = new HintFadeProperty(this);
        this.hintAnimationValues = new ArrayList();
        this.hintAnimations = new ArrayList();
    }

    private /* synthetic */ void lambda$setHintText$0(boolean r3, String r4) {
        this.hintAnimationValues.clear();
        Iterator<SpringAnimation> r0 = this.hintAnimations.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().cancel();
        goto L4
    L6:
        if (r3 == true) goto L10;
        super.setHintText(r4);
        return;
    }

    private void runHintAnimation(int r11, boolean r12, Runnable r13) {
        Runnable r0 = this.hintAnimationCallback;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        int r02 = 0;
    L7:
        if (r02 >= r11) goto L15;
        float r3 = 0.0f;
        if (r12 == false) goto L11;
        float r5 = 0.0f;
    L12:
        if (r12 == false) goto L14;
        r3 = 1.0f;
    L14:
        float r32 = r3 * 100.0f;
        SpringAnimation r33 = new SpringAnimation(Integer.valueOf(r02), this.hintFadeProperty).setSpring(new SpringForce(r32).setStiffness(500.0f).setDampingRatio(1.0f).setFinalPosition(r32)).setStartValue(100.0f * r5);
        this.hintAnimations.add(r33);
        this.hintAnimationValues.add(Float.valueOf(r5));
        Objects.requireNonNull(r33);
        postDelayed(new AnimatedPhoneNumberEditText$$ExternalSyntheticLambda1(r33), r02 * 5);
        r02 = r02 + 1;
        goto L7
    L11:
        r5 = 1.0f;
        goto L12
    L15:
        this.hintAnimationCallback = r13;
        postDelayed(r13, (r11 * 5) + 150);
    }

    @Override // org.telegram.ui.Components.HintEditText
    public String getHintText() {
        return this.wasHint;
    }

    @Keep
    public float getProgress() {
        return this.progress;
    }

    @Override // org.telegram.ui.Components.HintEditText, org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onDraw(Canvas r1) {
        super.onDraw(r1);
    }

    @Override // org.telegram.ui.Components.HintEditText
    public void onPreDrawHintCharacter(int r1, Canvas r2, float r3, float r4) {
        if (r1 >= this.hintAnimationValues.size()) goto L6;
        this.hintPaint.setAlpha((int) (this.hintAnimationValues.get(r1).floatValue() * 255.0f));
        return;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
    }

    @Override // org.telegram.ui.Components.HintEditText
    public void setHintText(String r5) {
        boolean r0 = TextUtils.isEmpty(r5);
        boolean r1 = !r0;
        Boolean r2 = this.wasHintVisible;
        if (r2 != null) goto L5;
    L8:
        this.hintAnimationValues.clear();
        Iterator<SpringAnimation> r22 = this.hintAnimations.iterator();
    L10:
        if (r22.hasNext() == false) goto L12;
        r22.next().cancel();
        goto L10
    L12:
        this.hintAnimations.clear();
        this.wasHintVisible = Boolean.valueOf(r1);
        boolean r23 = TextUtils.isEmpty(getText());
    L13:
        if (r0 == true) goto L15;
        String r3 = r5;
    L16:
        if (r3 != null) goto L18;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        this.wasHint = r5;
        if (r0 == false) goto L21;
        if (r23 == false) goto L21;
    L22:
        if (r23 == false) goto L26;
        runHintAnimation(r3.length(), r1, new AnimatedPhoneNumberEditText$$ExternalSyntheticLambda0(this, r1, r5));
        return;
    L26:
        return;
    L21:
        super.setHintText(r5);
        goto L22
    L15:
        r3 = this.wasHint;
        goto L16
    L5:
        if (r2.booleanValue() != r1) goto L8;
        r23 = false;
        goto L13
    }

    public void setNewText(String r15) {
        if (this.oldLetters != null) goto L5;
        return;
    L5:
        if (this.letters != null) goto L7;
        return;
    L7:
        if (Objects.equals(this.oldText, r15) == true) goto L47;
        ObjectAnimator r0 = this.animator;
        if (r0 == null) goto L12;
        r0.cancel();
        this.animator = null;
    L12:
        this.oldLetters.clear();
        this.oldLetters.addAll(this.letters);
        this.letters.clear();
        int r2 = 0;
        if (TextUtils.isEmpty(this.oldText) == true) goto L15;
    L17:
        boolean r02 = false;
    L18:
        this.progress = 0.0f;
    L20:
        if (r2 >= r15.length()) goto L38;
        int r3 = r2 + 1;
        String r5 = r15.substring(r2, r3);
        if (this.oldLetters.isEmpty() == false) goto L24;
    L26:
        String r4 = null;
    L27:
        if (r02 == true) goto L32;
        if (r4 == null) goto L32;
        if (r4.equals(r5) == false) goto L32;
        this.letters.add(this.oldLetters.get(r2));
        this.oldLetters.set(r2, null);
    L36:
        r2 = r3;
    L32:
        if (r02 == false) goto L35;
        if (r4 != null) goto L35;
        this.oldLetters.add(new StaticLayout(_UrlKt.FRAGMENT_ENCODE_SET, this.textPaint, 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
    L35:
        this.letters.add(new StaticLayout(r5, this.textPaint, (int) Math.ceil(r6.measureText(r5)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
        goto L36
    L24:
        if (r2 >= this.oldText.length()) goto L26;
        r4 = this.oldText.substring(r2, r3);
        goto L27
    L38:
        if (this.oldLetters.isEmpty() == true) goto L40;
        ObjectAnimator r03 = ObjectAnimator.ofFloat(this, "progress", new float[]{-1.0f, 0.0f});
        this.animator = r03;
        r03.setDuration(150);
        this.animator.addListener(new AnonymousClass1(this));
        this.animator.start();
    L40:
        this.oldText = r15;
        invalidate();
        return;
    L15:
        if (TextUtils.isEmpty(r15) == true) goto L17;
        r02 = true;
        goto L18
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.widget.TextView
    public void setTextColor(int r1) {
        super.setTextColor(r1);
        this.textPaint.setColor(r1);
    }

    @Override // org.telegram.ui.Components.HintEditText, org.telegram.ui.Components.EditTextBoldCursor, android.widget.TextView
    public void setTextSize(int r2, float r3) {
        super.setTextSize(r2, r3);
        this.textPaint.setTextSize(TypedValue.applyDimension(r2, r3, getResources().getDisplayMetrics()));
    }
}
