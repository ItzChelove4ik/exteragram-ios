package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Premium.PremiumGradient;

/* loaded from: classes3.dex */
public class AvatarsImageView extends View {
    public final AvatarsDrawable avatarsDrawable;
    private Paint plusBgPaint;
    private Text plusText;
    private PremiumGradient.PremiumGradientTools premiumGradient;

    public AvatarsImageView(Context r1, boolean r2) {
        super(r1);
        this.avatarsDrawable = new AvatarsDrawable(this, r2);
    }

    public void commitTransition(boolean r1) {
        this.avatarsDrawable.commitTransition(r1);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarsDrawable.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarsDrawable.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        super.onDraw(r13);
        this.avatarsDrawable.onDraw(r13);
        if (this.plusText == null) goto L6;
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(getWidth() - AndroidUtilities.dp(22.0f), getHeight() - AndroidUtilities.dp(22.0f), getWidth() - AndroidUtilities.dp(0.0f), getHeight() - AndroidUtilities.dp(0.0f));
        this.premiumGradient.gradientMatrix(r0);
        r13.drawCircle(r0.centerX(), r0.centerY(), (r0.width() / 2.0f) + AndroidUtilities.dp(1.33f), this.plusBgPaint);
        r13.drawCircle(r0.centerX(), r0.centerY(), r0.width() / 2.0f, this.premiumGradient.paint);
        this.plusText.draw(r13, r0.centerX() - (this.plusText.getCurrentWidth() / 2.0f), r0.centerY(), -1, 1.0f);
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        AvatarsDrawable r12 = this.avatarsDrawable;
        r12.width = getMeasuredWidth();
        AvatarsDrawable r13 = this.avatarsDrawable;
        r13.height = getMeasuredHeight();
    }

    public void reset() {
        this.avatarsDrawable.reset();
    }

    public void setAvatarsTextSize(int r1) {
        this.avatarsDrawable.setAvatarsTextSize(r1);
    }

    public void setCentered(boolean r1) {
        this.avatarsDrawable.setCentered(r1);
    }

    public void setCount(int r1) {
        this.avatarsDrawable.setCount(r1);
    }

    public void setDelegate(Runnable r1) {
        this.avatarsDrawable.setDelegate(r1);
    }

    public void setObject(int r1, int r2, TLObject r3) {
        this.avatarsDrawable.setObject(r1, r2, r3);
    }

    public void setPlus(int r8, int r9) {
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, null);
        this.plusText = new Text("+" + r8, 12.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
        Paint r82 = new Paint(1);
        this.plusBgPaint = r82;
        r82.setColor(r9);
    }

    public void setSize(int r1) {
        this.avatarsDrawable.setSize(r1);
    }

    public void setStepFactor(float r1) {
        this.avatarsDrawable.setStepFactor(r1);
    }

    public void setStyle(int r1) {
        this.avatarsDrawable.setStyle(r1);
    }

    public void updateAfterTransitionEnd() {
        this.avatarsDrawable.updateAfterTransitionEnd();
    }
}
