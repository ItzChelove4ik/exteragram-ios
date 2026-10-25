package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Property;
import android.view.View;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Locale;
import org.telegram.ui.Components.AnimationProperties;

/* loaded from: classes7.dex */
public class AnimatedNumberLayout {
    public static final Property<AnimatedNumberLayout, Float> PROGRESS = null;
    private ObjectAnimator animator;
    private int currentNumber;
    private ArrayList<StaticLayout> letters;
    private ArrayList<StaticLayout> oldLetters;
    private final View parentView;
    private float progress;
    private final TextPaint textPaint;

    /* renamed from: org.telegram.ui.Components.AnimatedNumberLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<AnimatedNumberLayout> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(AnimatedNumberLayout r1) {
            return Float.valueOf(AnimatedNumberLayout.m8818$$Nest$fgetprogress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((AnimatedNumberLayout) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(AnimatedNumberLayout r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(AnimatedNumberLayout r1, float r2) {
            AnimatedNumberLayout.m8820$$Nest$msetProgress(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimatedNumberLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedNumberLayout this$0;

        public AnonymousClass2(AnimatedNumberLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AnimatedNumberLayout.m8819$$Nest$fputanimator(this.this$0, null);
            AnimatedNumberLayout.m8817$$Nest$fgetoldLetters(this.this$0).clear();
        }
    }

    /* renamed from: -$$Nest$fgetoldLetters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8817$$Nest$fgetoldLetters(AnimatedNumberLayout r0) {
        return r0.oldLetters;
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m8818$$Nest$fgetprogress(AnimatedNumberLayout r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8819$$Nest$fputanimator(AnimatedNumberLayout r0, ObjectAnimator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$msetProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8820$$Nest$msetProgress(AnimatedNumberLayout r0, float r1) {
        r0.setProgress(r1);
    }

    static {
        PROGRESS = new AnonymousClass1("progress");
    }

    public AnimatedNumberLayout(View r2, TextPaint r3) {
        this.letters = new ArrayList();
        this.oldLetters = new ArrayList();
        this.progress = 0.0f;
        this.currentNumber = 1;
        this.textPaint = r3;
        this.parentView = r2;
    }

    private void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        this.parentView.invalidate();
    }

    public void draw(Canvas r13) {
        if (this.letters.isEmpty() == false) goto L5;
        return;
    L5:
        float r0 = this.letters.get(0).getHeight();
        int r2 = Math.max(this.letters.size(), this.oldLetters.size());
        r13.save();
        int r3 = this.textPaint.getAlpha();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L42;
        r13.save();
        StaticLayout r6 = null;
        if (r4 >= this.oldLetters.size()) goto L10;
        StaticLayout r5 = this.oldLetters.get(r4);
    L12:
        if (r4 >= this.letters.size()) goto L14;
        r6 = this.letters.get(r4);
    L14:
        float r7 = this.progress;
        if (r7 <= 0.0f) goto L23;
        TextPaint r9 = this.textPaint;
        if (r5 == null) goto L21;
        float r11 = r3;
        r9.setAlpha((int) (r7 * r11));
        r13.save();
        r13.translate(0.0f, (this.progress - 1.0f) * r0);
        r5.draw(r13);
        r13.restore();
        if (r6 == null) goto L35;
        this.textPaint.setAlpha((int) (r11 * (1.0f - this.progress)));
        r13.translate(0.0f, this.progress * r0);
    L35:
        if (r6 == null) goto L37;
        r6.draw(r13);
    L37:
        r13.restore();
        if (r6 == null) goto L40;
        float r52 = r6.getLineWidth(0);
    L41:
        r13.translate(r52, 0.0f);
        r4 = r4 + 1;
        goto L6
    L40:
        r52 = r5.getLineWidth(0);
        goto L41
    L21:
        r9.setAlpha(r3);
        goto L35
    L23:
        if (r7 >= 0.0f) goto L33;
        if (r5 == null) goto L26;
        this.textPaint.setAlpha((int) (r3 * (-r7)));
        r13.save();
        r13.translate(0.0f, (this.progress + 1.0f) * r0);
        r5.draw(r13);
        r13.restore();
    L26:
        if (r6 == null) goto L35;
        if (r4 == (r2 - 1)) goto L32;
        if (r5 != null) goto L32;
        this.textPaint.setAlpha(r3);
    L32:
        this.textPaint.setAlpha((int) (r3 * (this.progress + 1.0f)));
        r13.translate(0.0f, this.progress * r0);
        goto L35
    L33:
        if (r6 == null) goto L35;
        this.textPaint.setAlpha(r3);
        goto L35
    L10:
        r5 = null;
        goto L12
    L42:
        r13.restore();
    }

    public int getWidth() {
        int r0 = this.letters.size();
        float r1 = 0.0f;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L6;
        r1 = r1 + this.letters.get(r3).getLineWidth(0);
        r3 = r3 + 1;
        goto L3
    L6:
        return (int) Math.ceil(r1);
    }

    public void setNumber(int r19, boolean r20) {
        if (this.currentNumber == r19) goto L5;
    L7:
        ObjectAnimator r2 = this.animator;
        if (r2 == null) goto L10;
        r2.cancel();
        this.animator = null;
    L10:
        this.oldLetters.clear();
        this.oldLetters.addAll(this.letters);
        this.letters.clear();
        Locale r22 = Locale.US;
        String r4 = String.format(r22, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.currentNumber)});
        String r23 = String.format(r22, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r19)});
        if (r19 <= this.currentNumber) goto L13;
        boolean r5 = true;
    L14:
        this.currentNumber = r19;
        this.progress = 0.0f;
        int r8 = 0;
    L16:
        if (r8 >= r23.length()) goto L29;
        int r9 = r8 + 1;
        String r11 = r23.substring(r8, r9);
        if (this.oldLetters.isEmpty() == false) goto L20;
    L22:
        String r10 = null;
    L23:
        if (r10 != null) goto L25;
    L27:
        this.letters.add(new StaticLayout(r11, this.textPaint, (int) Math.ceil(r12.measureText(r11)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
    L28:
        r8 = r9;
        goto L16
    L25:
        if (r10.equals(r11) == false) goto L27;
        this.letters.add(this.oldLetters.get(r8));
        this.oldLetters.set(r8, null);
        goto L28
    L20:
        if (r8 >= r4.length()) goto L22;
        r10 = r4.substring(r8, r9);
        goto L23
    L29:
        if (r20 == true) goto L31;
    L37:
        this.parentView.invalidate();
        return;
    L31:
        if (this.oldLetters.isEmpty() == true) goto L37;
        Property<AnimatedNumberLayout, Float> r24 = PROGRESS;
        if (r5 == false) goto L35;
        float r3 = -1.0f;
    L36:
        ObjectAnimator r1 = ObjectAnimator.ofFloat(this, r24, new float[]{r3, 0.0f});
        this.animator = r1;
        r1.setDuration(150);
        this.animator.addListener(new AnonymousClass2(this));
        this.animator.start();
        goto L37
    L35:
        r3 = 1.0f;
        goto L36
    L13:
        r5 = false;
        goto L14
    L5:
        if (this.letters.isEmpty() == true) goto L7;
    }
}
