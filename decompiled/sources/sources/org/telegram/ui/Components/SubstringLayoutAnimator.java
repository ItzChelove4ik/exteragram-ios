package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class SubstringLayoutAnimator {
    private StaticLayout animateInLayout;
    private StaticLayout animateOutLayout;
    private StaticLayout animateStableLayout;
    public boolean animateTextChange;
    private boolean animateTextChangeOut;
    private float hintProgress;
    private final View parentView;
    private boolean replaceAnimation;
    ValueAnimator valueAnimator;
    private float xOffset;

    /* renamed from: org.telegram.ui.Components.SubstringLayoutAnimator$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SubstringLayoutAnimator this$0;

        public AnonymousClass1(SubstringLayoutAnimator r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.animateTextChange = false;
        }
    }

    public static /* synthetic */ void $r8$lambda$qDMgCLMWVQ441JAjnKJP60iXIgE(SubstringLayoutAnimator r0, ValueAnimator r1) {
        r0.lambda$create$0(r1);
    }

    public SubstringLayoutAnimator(View r1) {
        this.parentView = r1;
    }

    private /* synthetic */ void lambda$create$0(ValueAnimator r1) {
        this.hintProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.parentView.invalidate();
    }

    public void cancel() {
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.animateTextChange = false;
    }

    public void create(StaticLayout r27, CharSequence r28, CharSequence r29, TextPaint r30) {
        if (r27 != null) goto L5;
        return;
    L5:
        if (r28.equals(r29) == true) goto L30;
        ValueAnimator r1 = this.valueAnimator;
        if (r1 == null) goto L10;
        r1.cancel();
    L10:
        if (r28.length() <= r29.length()) goto L12;
        String r12 = r28.toString();
        String r2 = r29.toString();
        boolean r5 = true;
    L13:
        int r6 = r12.indexOf(r2);
        if (r6 < 0) goto L26;
        SpannableStringBuilder r11 = new SpannableStringBuilder(r12);
        SpannableStringBuilder r10 = new SpannableStringBuilder(r12);
        if (r6 == 0) goto L19;
        r10.setSpan(new EmptyStubSpan(), 0, r6, 0);
    L19:
        if ((r2.length() + r6) == r12.length()) goto L21;
        r10.setSpan(new EmptyStubSpan(), r2.length() + r6, r12.length(), 0);
    L21:
        r11.setSpan(new EmptyStubSpan(), r6, r2.length() + r6, 0);
        int r13 = AndroidUtilities.dp(400.0f);
        Layout.Alignment r14 = Layout.Alignment.ALIGN_NORMAL;
        this.animateInLayout = new StaticLayout(r11, r30, r13, r14, 1.0f, 0.0f, false);
        StaticLayout r122 = new StaticLayout(r10, r30, AndroidUtilities.dp(400.0f), r14, 1.0f, 0.0f, false);
        this.animateStableLayout = r122;
        this.animateTextChange = true;
        this.animateTextChangeOut = r5;
        if (r6 != 0) goto L24;
        float r15 = 0.0f;
    L25:
        this.xOffset = r15;
        this.animateOutLayout = null;
        this.replaceAnimation = false;
    L27:
        this.hintProgress = 0.0f;
        ValueAnimator r16 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.valueAnimator = r16;
        r16.addUpdateListener(new SubstringLayoutAnimator$$ExternalSyntheticLambda0(this));
        this.valueAnimator.addListener(new AnonymousClass1(this));
        this.valueAnimator.setDuration(150);
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.valueAnimator.start();
        return;
    L24:
        r15 = -r122.getPrimaryHorizontal(r6);
        goto L25
    L26:
        int r21 = AndroidUtilities.dp(400.0f);
        Layout.Alignment r22 = Layout.Alignment.ALIGN_NORMAL;
        this.animateInLayout = new StaticLayout(r29, r30, r21, r22, 1.0f, 0.0f, false);
        this.animateOutLayout = new StaticLayout(r28, r30, AndroidUtilities.dp(400.0f), r22, 1.0f, 0.0f, false);
        this.animateStableLayout = null;
        this.animateTextChange = true;
        this.replaceAnimation = true;
        this.xOffset = 0.0f;
        goto L27
    L12:
        r12 = r29.toString();
        r2 = r28.toString();
        r5 = false;
        goto L13
    }

    public void draw(Canvas r12, TextPaint r13) {
        if (this.animateTextChange == false) goto L35;
        float r0 = this.xOffset;
        boolean r1 = this.animateTextChangeOut;
        float r2 = this.hintProgress;
        if (r1 == true) goto L8;
        r2 = 1.0f - r2;
    L8:
        float r02 = r0 * r2;
        int r14 = r13.getAlpha();
        if (this.animateStableLayout == null) goto L12;
        r12.save();
        r12.translate(r02, 0.0f);
        this.animateStableLayout.draw(r12);
        r12.restore();
    L12:
        if (this.animateInLayout == null) goto L21;
        boolean r22 = this.animateTextChangeOut;
        float r8 = this.hintProgress;
        if (r22 == false) goto L16;
        r8 = 1.0f - r8;
    L16:
        r12.save();
        r13.setAlpha((int) (r14 * r8));
        r12.translate(r02, 0.0f);
        if (this.replaceAnimation == false) goto L19;
        float r82 = (r8 * 0.1f) + 0.9f;
        r12.scale(r82, r82, r02, this.parentView.getMeasuredHeight() / 2.0f);
    L19:
        this.animateInLayout.draw(r12);
        r12.restore();
        r13.setAlpha(r14);
    L21:
        if (this.animateOutLayout == null) goto L36;
        boolean r23 = this.animateTextChangeOut;
        float r83 = this.hintProgress;
        if (r23 == true) goto L26;
        r83 = 1.0f - r83;
    L26:
        r12.save();
        float r24 = r14;
        boolean r9 = this.animateTextChangeOut;
        float r10 = this.hintProgress;
        if (r9 == true) goto L30;
        r10 = 1.0f - r10;
    L30:
        r13.setAlpha((int) (r24 * r10));
        r12.translate(r02, 0.0f);
        if (this.replaceAnimation == false) goto L33;
        float r84 = (r83 * 0.1f) + 0.9f;
        r12.scale(r84, r84, r02, this.parentView.getMeasuredHeight() / 2.0f);
    L33:
        this.animateOutLayout.draw(r12);
        r12.restore();
        r13.setAlpha(r14);
        return;
    L36:
        return;
    }
}
