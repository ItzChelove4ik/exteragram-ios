package org.telegram.ui.Components.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.TypingDotsDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SendButtonBlockedByTypingView extends View {
    private final BoolAnimator animatorStopAllowed;
    private final Paint paint;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TypingDotsDrawable typingDotsDrawable;

    public SendButtonBlockedByTypingView(Context r4, Theme.ResourcesProvider r5) {
        super(r4);
        this.animatorStopAllowed = new BoolAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.paint = new Paint(1);
        this.resourcesProvider = r5;
        TypingDotsDrawable r42 = new TypingDotsDrawable(true);
        this.typingDotsDrawable = r42;
        r42.setCallback(this);
        r42.setColor(-1);
        r42.setIgnoreAnimationLocks();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.typingDotsDrawable.start();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.typingDotsDrawable.stop();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        float r0 = getWidth() / 2.0f;
        float r2 = getHeight() / 2.0f;
        super.onDraw(r13);
        this.paint.setColor(Theme.getColor(Theme.key_chat_messagePanelSend, this.resourcesProvider));
        r13.drawCircle(r0, r2, AndroidUtilities.dp(20.0f), this.paint);
        float r1 = this.animatorStopAllowed.getFloatValue();
        float r3 = 1.0f - r1;
        if (r3 <= 0.0f) goto L6;
        DrawableUtils.drawWithScale(r13, this.typingDotsDrawable, r3 * 1.35f);
        invalidate();
    L6:
        if (r1 <= 0.0f) goto L9;
        float r12 = AndroidUtilities.dp(6.666f) * r1;
        float r9 = AndroidUtilities.dp(2.666f) * r1;
        r13.drawRoundRect(r0 - r12, r2 - r12, r0 + r12, r2 + r12, r9, r9, Theme.fillingPaint(-1));
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        DrawableUtils.setBounds(this.typingDotsDrawable, r1 - AndroidUtilities.dp(20.0f), r2 / 2.0f, 17);
    }

    public void setStopAllowed(boolean r1, boolean r2) {
        this.animatorStopAllowed.setValue(r1, r2);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.typingDotsDrawable) goto L7;
        return false;
    L7:
        if (this.animatorStopAllowed.getValue() == false) goto L14;
        return false;
    L14:
        return true;
    }
}
