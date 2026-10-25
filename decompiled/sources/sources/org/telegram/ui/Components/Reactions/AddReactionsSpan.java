package org.telegram.ui.Components.Reactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AddReactionsSpan extends ReplacementSpan {
    private int alpha;
    private float height;
    private StaticLayout layout;
    private final RectF rectF;
    private final TextPaint textPaint;
    private float width;

    /* renamed from: org.telegram.ui.Components.Reactions.AddReactionsSpan$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ AddReactionsSpan this$0;
        final /* synthetic */ Runnable val$after;

        public AnonymousClass1(AddReactionsSpan r1, Runnable r2) {
            this.this$0 = r1;
            this.val$after = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$after.run();
        }
    }

    public static /* synthetic */ void $r8$lambda$04MiLjzcFHwht8fJtHvULW8oDP0(AddReactionsSpan r0, View r1, ValueAnimator r2) {
        r0.lambda$show$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6WxN_ShN_bb94mCFL5AOoibHfm0(AddReactionsSpan r0, View r1, ValueAnimator r2) {
        r0.lambda$hide$1(r1, r2);
    }

    public AddReactionsSpan(float r3, Theme.ResourcesProvider r4) {
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        this.rectF = new RectF();
        r0.setTextSize(AndroidUtilities.dp(r3));
        r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText5, r4));
    }

    private /* synthetic */ void lambda$hide$1(View r1, ValueAnimator r2) {
        this.alpha = ((Integer) r2.getAnimatedValue()).intValue();
        r1.invalidate();
    }

    private /* synthetic */ void lambda$show$0(View r1, ValueAnimator r2) {
        this.alpha = ((Integer) r2.getAnimatedValue()).intValue();
        r1.invalidate();
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        makeLayout();
        this.rectF.set(r1.getClipBounds());
        r1.saveLayerAlpha(this.rectF, this.alpha, 31);
        r1.translate(r5 + AndroidUtilities.dp(4.0f), (r6 + ((r8 - r6) / 2.0f)) - (this.height / 2.0f));
        this.layout.draw(r1);
        r1.restore();
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        makeLayout();
        return (int) (AndroidUtilities.dp(8.0f) + this.width);
    }

    public void hide(View r3, Runnable r4) {
        ValueAnimator r0 = ValueAnimator.ofInt(new int[]{this.alpha, 0});
        r0.addUpdateListener(new AddReactionsSpan$$ExternalSyntheticLambda1(this, r3));
        r0.addListener(new AnonymousClass1(this, r4));
        r0.setDuration(200);
        r0.start();
    }

    public void makeLayout() {
        if (this.layout != null) goto L11;
        String r2 = LocaleController.getString(R.string.ReactionAddReactionsHint);
        TextPaint r3 = this.textPaint;
        int r4 = AndroidUtilities.displaySize.x;
        if (LocaleController.isRTL == false) goto L8;
        Layout.Alignment r0 = Layout.Alignment.ALIGN_OPPOSITE;
    L9:
        StaticLayout r1 = new StaticLayout(r2, r3, r4, r0, 1.0f, 0.0f, false);
        this.layout = r1;
        this.width = r1.getLineWidth(0);
        this.height = this.layout.getHeight();
        return;
    L8:
        r0 = Layout.Alignment.ALIGN_NORMAL;
        goto L9
    }

    public void show(View r3) {
        ValueAnimator r0 = ValueAnimator.ofInt(new int[]{this.alpha, 255});
        r0.addUpdateListener(new AddReactionsSpan$$ExternalSyntheticLambda0(this, r3));
        r0.setDuration(200);
        r0.start();
    }
}
