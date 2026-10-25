package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes6.dex */
public class ChooseQualityLayout$QualityIcon extends Drawable {
    public final AnimatedFloat animatedCast;
    private final Drawable base;
    private final Paint bgLinePaint;
    private final Paint bgPaint;
    public final AnimatedTextView.AnimatedTextDrawable bottomText;
    private final Drawable.Callback callback;
    public boolean cast;
    private final Paint castCutPaint;
    private final Path castCutPath;
    private final Drawable castFill;
    private int castFillColor;
    private final RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private float rotation;
    public final AnimatedTextView.AnimatedTextDrawable topText;

    /* renamed from: org.telegram.ui.ChooseQualityLayout$QualityIcon$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ ChooseQualityLayout$QualityIcon this$0;

        public AnonymousClass1(ChooseQualityLayout$QualityIcon r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            this.this$0.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            this.this$0.scheduleSelf(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            this.this$0.unscheduleSelf(r2);
        }
    }

    public ChooseQualityLayout$QualityIcon(Context r13, int r14, Theme.ResourcesProvider r15) {
        this.bgPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.bgLinePaint = r0;
        this.rect = new RectF();
        AnimatedTextView.AnimatedTextDrawable r2 = new AnimatedTextView.AnimatedTextDrawable();
        this.topText = r2;
        AnimatedTextView.AnimatedTextDrawable r3 = new AnimatedTextView.AnimatedTextDrawable();
        this.bottomText = r3;
        Paint r4 = new Paint(1);
        this.castCutPaint = r4;
        Path r1 = new Path();
        this.castCutPath = r1;
        this.animatedCast = new AnimatedFloat(new ChooseQualityLayout$QualityIcon$$ExternalSyntheticLambda0(this), 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        AnonymousClass1 r5 = new AnonymousClass1(this);
        this.callback = r5;
        this.resourcesProvider = r15;
        this.base = r13.getResources().getDrawable(r14).mutate();
        this.castFill = r13.getResources().getDrawable(R.drawable.mini_casting_fill).mutate();
        r0.setColor(-1);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r2.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r2.setTextColor(-16777216);
        r2.setTextSize(AndroidUtilities.dp(7.0f));
        r2.setCallback(r5);
        r2.setGravity(17);
        r2.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r3.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r3.setTextColor(-16777216);
        r3.setTextSize(AndroidUtilities.dp(7.0f));
        r3.setCallback(r5);
        r3.setGravity(17);
        r3.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        RectF r12 = AndroidUtilities.rectTmp;
        r12.set(AndroidUtilities.dp(0.66f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.33f));
        r1.addRoundRect(r12, AndroidUtilities.dp(2.66f), AndroidUtilities.dp(2.66f), Path.Direction.CW);
        r4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r21) {
        float r8 = this.animatedCast.set(this.cast);
        float r9 = (AndroidUtilities.dp(5.0f) * this.topText.isNotEmpty()) + this.topText.getCurrentWidth();
        float r10 = (AndroidUtilities.dp(5.0f) * this.bottomText.isNotEmpty()) + this.bottomText.getCurrentWidth();
        int r11 = r21.getSaveCount();
        Rect r12 = getBounds();
        if (r9 <= 0.0f) goto L5;
    L10:
        Canvas r1 = r21;
        r1.saveLayerAlpha(r12.left, r12.top, r12.right, r12.bottom, 255, 31);
    L11:
        Rect r2 = AndroidUtilities.rectTmp2;
        r2.set(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), (AndroidUtilities.dp(6.0f) + r12.width()) - AndroidUtilities.dp(12.0f), (AndroidUtilities.dp(6.0f) + r12.height()) - AndroidUtilities.dp(12.0f));
        r2.offset(r12.left, r12.top);
        this.base.setBounds(r2);
        r1.save();
        r1.rotate(this.rotation * (-180.0f), r12.centerX(), r12.centerY());
        this.base.draw(r1);
        r1.restore();
        this.bgPaint.setColor(-1);
        float r22 = r12.left + (r12.width() * 0.98f);
        float r3 = r12.top + (r12.height() * 0.18f);
        float r4 = r12.top + (r12.height() * 0.78f);
        float r5 = AndroidUtilities.dp(10.0f);
        if (r9 <= 0.0f) goto L14;
        float r16 = r5 / 2.0f;
        float r17 = 2.0f;
        float r18 = 3.0f;
        this.rect.set(r22 - r9, r3 - r16, r22, r3 + r16);
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.bgLinePaint);
    L16:
        if (r10 <= 0.0f) goto L18;
        float r15 = r5 / r17;
        float r162 = 0.0f;
        this.rect.set(r22 - r10, r4 - r15, r22, r15 + r4);
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(r18), AndroidUtilities.dp(r18), this.bgLinePaint);
    L19:
        float r13 = 1.0f - r8;
        if ((r9 * r13) <= r162) goto L22;
        float r19 = 255.0f;
        this.bgPaint.setAlpha((int) ((this.topText.isNotEmpty() * 255.0f) * r13));
        AnimatedTextView.AnimatedTextDrawable r14 = this.topText;
        r14.setAlpha((int) ((r14.isNotEmpty() * 255.0f) * r13));
        float r142 = r5 / r17;
        this.rect.set(r22 - r9, r3 - r142, r22, r3 + r142);
        this.rect.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(r18), AndroidUtilities.dp(r18), this.bgPaint);
        this.rect.inset(-AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(1.0f));
        this.topText.setBounds(this.rect);
        this.topText.draw(r1);
    L24:
        if (r8 <= r162) goto L32;
        r1.save();
        int r32 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        if (this.castFillColor == r32) goto L28;
        Drawable r92 = this.castFill;
        this.castFillColor = r32;
        r92.setColorFilter(new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN));
    L28:
        Drawable r33 = this.castFill;
        r33.setBounds((r12.right - r33.getIntrinsicWidth()) - AndroidUtilities.dp(r18), r12.top + AndroidUtilities.dp(0.66f), r12.right - AndroidUtilities.dp(r18), (r12.top + AndroidUtilities.dp(0.66f)) + this.castFill.getIntrinsicHeight());
        this.castFill.setAlpha((int) (r8 * r19));
        float r34 = AndroidUtilities.lerp(0.8f, 1.0f, r8);
        r1.scale(r34, r34, this.castFill.getBounds().centerX(), this.castFill.getBounds().centerY());
        if (r8 <= 0.5f) goto L31;
        r1.save();
        r1.translate(this.castFill.getBounds().left, this.castFill.getBounds().top);
        r1.drawPath(this.castCutPath, this.castCutPaint);
        r1.restore();
    L31:
        this.castFill.draw(r1);
        r1.restore();
    L32:
        if (r10 <= 0.0f) goto L34;
        this.bgPaint.setAlpha((int) (this.bottomText.isNotEmpty() * r19));
        AnimatedTextView.AnimatedTextDrawable r35 = this.bottomText;
        r35.setAlpha((int) (r35.isNotEmpty() * r19));
        float r52 = r5 / r17;
        this.rect.set(r22 - r10, r4 - r52, r22, r4 + r52);
        this.rect.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        r1.drawRoundRect(this.rect, AndroidUtilities.dp(r18), AndroidUtilities.dp(r18), this.bgPaint);
        this.rect.inset(-AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(1.0f));
        this.bottomText.setBounds(this.rect);
        this.bottomText.draw(r1);
    L34:
        r1.restoreToCount(r11);
        return;
    L22:
        r19 = 255.0f;
        goto L24
    L18:
        r162 = 0.0f;
        goto L19
    L14:
        r17 = 2.0f;
        r18 = 3.0f;
        goto L16
    L5:
        if (r10 > 0.0f) goto L10;
        if (r8 > 0.0f) goto L10;
        r1 = r21;
        goto L11
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.base.getIntrinsicHeight() + AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.base.getIntrinsicWidth() + AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.base.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.base.setAlpha(r1);
    }

    public void setCasting(boolean r2, boolean r3) {
        if (this.cast != r2) goto L5;
        return;
    L5:
        this.cast = r2;
        if (r3 == true) goto L8;
        this.animatedCast.force(r2);
    L8:
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.base.setColorFilter(r1);
    }
}
