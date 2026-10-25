package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class VideoTimeView extends View {
    private final Paint backgroundPaint;
    private boolean shown;
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;

    public VideoTimeView(Context r10) {
        super(r10);
        Paint r102 = new Paint(1);
        this.backgroundPaint = r102;
        this.shown = true;
        r102.setColor(Integer.MIN_VALUE);
        AnimatedTextView.AnimatedTextDrawable r2 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.textDrawable = r2;
        r2.setAnimationProperties(0.2f, 0, 200, CubicBezierInterpolator.EASE_OUT_QUINT);
        r2.setTextSize(AndroidUtilities.dp(13.0f));
        r2.setTextColor(-1);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setCallback(this);
        r2.setGravity(1);
        setTime(0, false);
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        float r0 = this.textDrawable.getCurrentWidth();
        RectF r1 = AndroidUtilities.rectTmp;
        r1.set(((getWidth() - r0) / 2.0f) - AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f), ((getWidth() + r0) / 2.0f) + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(23.0f));
        r8.drawRoundRect(r1, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), this.backgroundPaint);
        this.textDrawable.setBounds((int) r1.left, ((int) r1.top) - AndroidUtilities.dp(1.0f), (int) r1.right, (int) r1.bottom);
        this.textDrawable.draw(r8);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(25.0f), TLObject.FLAG_30));
    }

    public void setTime(long r10, boolean r12) {
        long r102 = r10 / 1000;
        long r2 = r102 % 60;
        long r103 = r102 - r2;
        long r4 = r103 / 60;
        long r104 = (r103 - (r4 * 60)) / 60;
        StringBuilder r0 = new StringBuilder(8);
        if (r104 >= 10) goto L5;
        r0.append('0');
    L5:
        r0.append(r104);
        r0.append(':');
        if (r4 >= 10) goto L8;
        r0.append('0');
    L8:
        r0.append(r4);
        r0.append(':');
        if (r2 >= 10) goto L11;
        r0.append('0');
    L11:
        r0.append(r2);
        if (TextUtils.equals(r0, this.textDrawable.getText()) == true) goto L21;
        this.textDrawable.cancelAnimation();
        AnimatedTextView.AnimatedTextDrawable r9 = this.textDrawable;
        if (r12 == true) goto L16;
    L18:
        boolean r105 = false;
    L19:
        r9.setText(r0, r105);
        return;
    L16:
        if (LocaleController.isRTL == true) goto L18;
        r105 = true;
        goto L19
    }

    public void show(boolean r5, boolean r6) {
        if (r5 != this.shown) goto L6;
        if (r6 == false) goto L6;
        return;
    L6:
        this.shown = r5;
        animate().cancel();
        float r1 = 0.8f;
        float r2 = 0.0f;
        if (r6 == false) goto L24;
        ViewPropertyAnimator r4 = animate();
        if (r5 == false) goto L11;
        float r62 = 0.0f;
    L12:
        ViewPropertyAnimator r42 = r4.translationY(r62);
        if (r5 == false) goto L15;
        r2 = 1.0f;
    L15:
        ViewPropertyAnimator r43 = r42.alpha(r2);
        if (r5 == false) goto L18;
        float r63 = 1.0f;
    L19:
        ViewPropertyAnimator r44 = r43.scaleX(r63);
        if (r5 == false) goto L22;
        r1 = 1.0f;
    L22:
        r44.scaleY(r1).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(220).start();
        return;
    L18:
        r63 = 0.8f;
        goto L19
    L11:
        r62 = AndroidUtilities.dp(6.0f);
        goto L12
    L24:
        if (r5 == false) goto L26;
        float r64 = 0.0f;
    L27:
        setTranslationY(r64);
        if (r5 == false) goto L30;
        float r65 = 1.0f;
    L31:
        setScaleX(r65);
        if (r5 == false) goto L34;
        r1 = 1.0f;
    L34:
        setScaleY(r1);
        if (r5 == false) goto L37;
        r2 = 1.0f;
    L37:
        setAlpha(r2);
        return;
    L30:
        r65 = 0.8f;
        goto L31
    L26:
        r64 = AndroidUtilities.dp(6.0f);
        goto L27
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.textDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
