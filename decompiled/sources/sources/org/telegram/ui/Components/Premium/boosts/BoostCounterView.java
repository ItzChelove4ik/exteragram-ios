package org.telegram.ui.Components.Premium.boosts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class BoostCounterView extends View {
    private final Paint bgPaint;
    private ValueAnimator countAnimator;
    private float countScale;
    private final AnimatedTextView.AnimatedTextDrawable countText;
    private int lastCount;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostCounterView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BoostCounterView this$0;

        public AnonymousClass1(BoostCounterView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            BoostCounterView.m12068$$Nest$fputcountScale(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$HXzhlCSg7936OGz_FkdbWpZvrt4(BoostCounterView r0, ValueAnimator r1) {
        r0.lambda$animateCount$0(r1);
    }

    /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12068$$Nest$fputcountScale(BoostCounterView r0, float r1) {
        r0.countScale = r1;
    }

    public BoostCounterView(Context r8, Theme.ResourcesProvider r9) {
        super(r8);
        this.countScale = 1.0f;
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, false, true);
        this.countText = r0;
        r0.setAnimationProperties(0.3f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setCallback(this);
        r0.setTextSize(AndroidUtilities.dp(11.5f));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextColor(-1);
        r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r0.setGravity(17);
        Paint r82 = new Paint(1);
        this.bgPaint = r82;
        r82.setColor(-6915073);
        setVisibility(8);
    }

    private void animateCount() {
        ValueAnimator r0 = this.countAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.countAnimator = null;
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.countAnimator = r02;
        r02.addUpdateListener(new BoostCounterView$$ExternalSyntheticLambda0(this));
        this.countAnimator.addListener(new AnonymousClass1(this));
        this.countAnimator.setInterpolator(new OvershootInterpolator(2.0f));
        this.countAnimator.setDuration(200);
        this.countAnimator.start();
    }

    private /* synthetic */ void lambda$animateCount$0(ValueAnimator r2) {
        this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.save();
        r8.translate(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        Rect r0 = AndroidUtilities.rectTmp2;
        r0.set(0, 0, AndroidUtilities.dp(8.0f) + ((int) this.countText.getCurrentWidth()), AndroidUtilities.dp(20.0f));
        RectF r1 = AndroidUtilities.rectTmp;
        r1.set(r0);
        if (this.countScale == 1.0f) goto L5;
        r8.save();
        float r2 = this.countScale;
        r8.scale(r2, r2, r0.centerX(), r0.centerY());
    L5:
        r8.drawRoundRect(r1, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.bgPaint);
        r0.set(0, 0, (int) r1.width(), AndroidUtilities.dp(19.0f));
        this.countText.setBounds(r0);
        this.countText.draw(r8);
        if (this.countScale == 1.0f) goto L8;
        r8.restore();
    L8:
        r8.restore();
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.dp(15.0f) + this.countText.getWidth()), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(26.0f), TLObject.FLAG_30));
    }

    public void setCount(int r5, boolean r6) {
        if (BoostRepository.isMultiBoostsAvailable() == true) goto L5;
        r5 = 0;
    L5:
        if (r5 <= 0) goto L7;
        setVisibility(0);
    L7:
        if (r6 == false) goto L9;
        this.countText.cancelAnimation();
    L9:
        if (r6 == true) goto L11;
    L14:
        this.lastCount = r5;
        int r0 = this.countText.getText().length();
        this.countText.setText("x" + r5, r6);
        int r52 = this.countText.getText().length();
        invalidate();
        if (r0 == r52) goto L18;
        requestLayout();
        return;
    L18:
        return;
    L11:
        if (r5 == this.lastCount) goto L14;
        if (r5 <= 0) goto L14;
        animateCount();
        goto L14
    }
}
