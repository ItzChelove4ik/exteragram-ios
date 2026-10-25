package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.QuoteSpan;

/* loaded from: classes7.dex */
public class QuoteCollapseButton {
    private final Paint backgroundPaint;
    private final ButtonBounce bounce;
    private final QuoteSpan.ExpandDrawable drawable;
    private boolean pressed;
    private final AnimatedFloat scale;
    private final AnimatedTextView.AnimatedTextDrawable text;
    private boolean textCollapsed;
    private final int textWidth;

    public QuoteCollapseButton(View r6) {
        this.backgroundPaint = new Paint(1);
        this.scale = new AnimatedFloat(r6, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.drawable = new QuoteSpan.ExpandDrawable(r6);
        this.bounce = new ButtonBounce(r6);
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable();
        this.text = r0;
        r0.setTextSize(AndroidUtilities.dp(11.0f));
        r0.setHacks(true, true, true);
        r0.setCallback(r6);
        r0.setOverrideFullWidth((int) (AndroidUtilities.displaySize.x * 0.3f));
        this.textCollapsed = false;
        r0.setText(LocaleController.getString(R.string.QuoteCollapse), false);
        this.textWidth = (int) Math.ceil(Math.max(r0.getPaint().measureText(LocaleController.getString(R.string.QuoteExpand)), r0.getPaint().measureText(LocaleController.getString(R.string.QuoteCollapse))));
    }

    public float draw(Canvas r7, RectF r8, float r9, float r10, int r11, boolean r12, boolean r13) {
        if (r12 == this.textCollapsed) goto L9;
        AnimatedTextView.AnimatedTextDrawable r0 = this.text;
        this.textCollapsed = r12;
        if (r12 == false) goto L7;
        int r2 = R.string.QuoteExpand;
    L8:
        r0.setText(LocaleController.getString(r2), true);
        goto L9
    L7:
        r2 = R.string.QuoteCollapse;
    L9:
        int r02 = (int) (AndroidUtilities.dp(23.66f) + this.text.getCurrentWidth());
        float r3 = AndroidUtilities.dp(17.66f);
        r8.set(r9 - r02, r10 - r3, r9, r10);
        float r132 = this.scale.set(r13) * this.bounce.getScale(0.02f);
        if (r132 <= 0.0f) goto L12;
        this.backgroundPaint.setColor(ColorUtils.setAlphaComponent(r11, 30));
        r7.save();
        r7.scale(r132, r132, r9, r10);
        float r32 = r3 / 2.0f;
        r7.drawRoundRect(r8, r32, r32, this.backgroundPaint);
        this.text.setBounds((int) (r8.left + AndroidUtilities.dp(6.0f)), (int) r8.top, (int) (r8.right - AndroidUtilities.dp(17.66f)), (int) r8.bottom);
        this.text.setTextColor(r11);
        this.text.draw(r7);
        int r102 = AndroidUtilities.dp(14.0f);
        QuoteSpan.ExpandDrawable r03 = this.drawable;
        float r103 = r102;
        int r22 = (int) ((r8.right - AndroidUtilities.dp(3.33f)) - r103);
        float r104 = r103 / 2.0f;
        r03.setBounds(r22, (int) ((r8.centerY() - r104) + AndroidUtilities.dp(0.33f)), (int) (r8.right - AndroidUtilities.dp(3.33f)), (int) ((r8.centerY() + r104) + AndroidUtilities.dp(0.33f)));
        this.drawable.setColor(r11);
        this.drawable.setState(!r12);
        this.drawable.draw(r7);
        r7.restore();
    L12:
        return r132;
    }

    public int height() {
        return AndroidUtilities.dp(17.66f);
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public void setPressed(boolean r1) {
        this.pressed = r1;
        this.bounce.setPressed(r1);
    }

    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.text) goto L5;
        return true;
    L5:
        if (r2 == this.drawable) goto L11;
        return false;
    L11:
        return true;
    }

    public int width() {
        return (AndroidUtilities.dp(23.66f) + this.textWidth) + (AndroidUtilities.dp(3.333f) * 2);
    }
}
