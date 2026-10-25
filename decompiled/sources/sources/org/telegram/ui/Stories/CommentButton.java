package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.blur3.StrokeDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;

/* loaded from: classes7.dex */
public class CommentButton extends FrameLayout {
    private final ImageView arrowImage;
    private final Paint backgroundPaint;
    private final Paint clearPaint;
    private boolean collapsed;
    private final ImageView commentImage;
    private ValueAnimator countAnimator;
    private float countScale;
    private final AnimatedTextView.AnimatedTextDrawable countText;
    private int lastCount;
    private final FrameLayout layout;

    /* renamed from: org.telegram.ui.Stories.CommentButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CommentButton this$0;

        public AnonymousClass1(CommentButton r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            CommentButton.m19296$$Nest$fputcountScale(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$dyHBtoX9bz5pULsGljI4MP_MXqU(CommentButton r0, ValueAnimator r1) {
        r0.lambda$animateBounce$0(r1);
    }

    /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19296$$Nest$fputcountScale(CommentButton r0, float r1) {
        r0.countScale = r1;
    }

    public CommentButton(Context r7, BlurredBackgroundColorProvider r8) {
        super(r7);
        Paint r0 = new Paint(1);
        this.backgroundPaint = r0;
        Paint r2 = new Paint(1);
        this.clearPaint = r2;
        this.countScale = 1.0f;
        ScaleStateListAnimator.apply(this);
        AnimatedTextView.AnimatedTextDrawable r4 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.countText = r4;
        r4.setTextColor(-9866632);
        r4.setTextSize(AndroidUtilities.dp(9.0f));
        r4.setCallback(this);
        r4.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r4.setAllowCancel(true);
        r0.setColor(-14670806);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        FrameLayout r02 = new FrameLayout(r7);
        this.layout = r02;
        StrokeDrawable r22 = new StrokeDrawable();
        r22.setColorProvider(r8);
        r22.setBackgroundColor(-14670806);
        r22.setPadding(AndroidUtilities.dp(1.0f));
        r02.setBackground(r22);
        addView(r02, LayoutHelper.createFrame(40, 40, 17));
        ImageView r82 = new ImageView(r7);
        this.commentImage = r82;
        r82.setImageResource(R.drawable.menu_comments);
        PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
        r82.setColorFilter(new PorterDuffColorFilter(-2960428, r3));
        r02.addView(r82, LayoutHelper.createFrame(20, 20, 17));
        ImageView r83 = new ImageView(r7);
        this.arrowImage = r83;
        r83.setImageResource(R.drawable.menu_comments_arrow);
        r83.setColorFilter(new PorterDuffColorFilter(-2960428, r3));
        r02.addView(r83, LayoutHelper.createFrame(20, 20, 17));
        r83.setPivotX(AndroidUtilities.dp(10.27f));
        r83.setPivotY(AndroidUtilities.dp(9.58f));
    }

    private void animateBounce() {
        ValueAnimator r0 = this.countAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.countAnimator = null;
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.countAnimator = r02;
        r02.addUpdateListener(new CommentButton$$ExternalSyntheticLambda0(this));
        this.countAnimator.addListener(new AnonymousClass1(this));
        this.countAnimator.setInterpolator(new OvershootInterpolator(2.5f));
        this.countAnimator.setDuration(200);
        this.countAnimator.start();
    }

    private /* synthetic */ void lambda$animateBounce$0(ValueAnimator r2) {
        this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        r9.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        super.dispatchDraw(r9);
        float r92 = this.countScale * this.countText.isNotEmpty();
        float r0 = Math.max(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f) + this.countText.getCurrentWidth());
        r9.save();
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(getWidth() - r0, 0.0f, getWidth(), AndroidUtilities.dp(13.0f));
        r9.scale(r92, r92, r2.centerX(), r2.centerY());
        r2.inset(-AndroidUtilities.dp(2.0f), -AndroidUtilities.dp(2.0f));
        r9.drawRoundRect(r2, r2.height() / 2.0f, r2.height() / 2.0f, this.clearPaint);
        r2.set(getWidth() - r0, 0.0f, getWidth(), AndroidUtilities.dp(13.0f));
        r9.drawRoundRect(r2, r2.height() / 2.0f, r2.height() / 2.0f, this.backgroundPaint);
        r9.translate(r2.left + ((r0 - this.countText.getCurrentWidth()) / 2.0f), AndroidUtilities.dp(7.0f));
        this.countText.draw(r9);
        r9.restore();
        r9.restore();
    }

    public void setCollapsed(boolean r3, boolean r4) {
        if (r4 == true) goto L4;
    L6:
        this.collapsed = r3;
        ImageView r2 = this.arrowImage;
        float r0 = 180.0f;
        if (r4 == false) goto L13;
        ViewPropertyAnimator r22 = r2.animate();
        if (r3 == false) goto L11;
        r0 = 0.0f;
    L11:
        r22.rotation(r0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).start();
        return;
    L13:
        if (r3 == false) goto L15;
        r0 = 0.0f;
    L15:
        r2.setRotation(r0);
        return;
    L4:
        if (this.collapsed != r3) goto L6;
    }

    public void setCount(int r5) {
        AnimatedTextView.AnimatedTextDrawable r0 = this.countText;
        if (r5 > 0) goto L5;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.setText(r1);
        if (this.lastCount == r5) goto L9;
        animateBounce();
        this.lastCount = r5;
    L9:
        invalidate();
        return;
    L5:
        r1 = LocaleController.formatNumber(r5, ',');
        goto L6
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.countText) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
