package org.telegram.ui.Components.poll.buttons;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

@SuppressLint({"UseCompatLoadingForDrawables"})
/* loaded from: classes7.dex */
public class PollAddButtonDrawable extends PollButtonDrawableBase implements FactorAnimator.Target {
    private int addAnOptionLastWidth;
    private StaticLayout addAnOptionText;
    private final TextPaint addAnOptionTextPaint;
    private final Drawable addDrawable;
    private final BoolAnimator animatorIsEnabled;
    private final int[] pressedState;
    private int textLastColor;

    public PollAddButtonDrawable(Context r7, Theme.ResourcesProvider r8) {
        super(r8);
        this.animatorIsEnabled = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
        this.pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        this.addDrawable = r7.getResources().getDrawable(org.telegram.messenger.R.drawable.outline_poll_add_24).mutate();
        this.addAnOptionTextPaint = new TextPaint(Theme.chat_audioPerformerPaint);
        setSelectorsColor(Theme.getColor(Theme.key_listSelector, r8));
        checkIconsAlpha();
        checkTextAlpha();
    }

    private void checkIconsAlpha() {
        float r0 = this.animatorIsEnabled.getFloatValue();
        this.addDrawable.setAlpha((int) (getAlpha() * (1.0f - r0)));
    }

    private void checkTextAlpha() {
        float r0 = this.animatorIsEnabled.getFloatValue();
        this.addAnOptionTextPaint.setAlpha((int) (getAlpha() * (1.0f - r0)));
    }

    public int checkMotionPressed(int r2, int r3) {
        if (getBounds().contains(r2, r3) == false) goto L6;
        getSelectorDrawable().setHotspot(r2, r3);
        getSelectorDrawable().setState(this.pressedState);
        return 0;
    L6:
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        Rect r0 = getBounds();
        getSelectorDrawable().draw(r5);
        DrawableUtils.drawWithScale(r5, this.addDrawable, 1.0f - this.animatorIsEnabled.getFloatValue());
        if (this.addAnOptionText == null) goto L6;
        r5.save();
        r5.translate(r0.left + AndroidUtilities.dp(44.0f), r0.top + AndroidUtilities.dp(13.66f));
        this.addAnOptionText.draw(r5);
        r5.restore();
        return;
    }

    @Override // org.telegram.ui.Components.poll.buttons.PollButtonDrawableBase
    public void onAlphaChanged(int r1) {
        super.onAlphaChanged(r1);
        checkIconsAlpha();
        checkTextAlpha();
    }

    @Override // org.telegram.ui.Components.poll.buttons.PollButtonDrawableBase, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r10) {
        super.onBoundsChange(r10);
        float r0 = r10.exactCenterY();
        float r1 = r10.left + AndroidUtilities.dp(22.33f);
        AndroidUtilities.dp(27.0f);
        AndroidUtilities.dp(44.0f);
        DrawableUtils.setBounds(this.addDrawable, r1, r0, 17);
        int r4 = r10.width() - AndroidUtilities.dp(56.0f);
        if (this.addAnOptionText != null) goto L5;
    L8:
        this.addAnOptionLastWidth = r4;
        this.addAnOptionText = new StaticLayout(LocaleController.getString(org.telegram.messenger.R.string.PollAddAnOption), this.addAnOptionTextPaint, r4, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        return;
    L5:
        if (this.addAnOptionLastWidth != r4) goto L8;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        checkIconsAlpha();
        checkTextAlpha();
        invalidateSelf();
    }

    public void setIsEditEnabled(boolean r1, boolean r2) {
        this.animatorIsEnabled.setValue(r1, r2);
    }

    public void setTextColor(int r3) {
        if (this.textLastColor == r3) goto L6;
        this.textLastColor = r3;
        this.addAnOptionTextPaint.setColor(r3);
        PorterDuffColorFilter r0 = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
        this.addDrawable.setColorFilter(r0);
        checkTextAlpha();
        return;
    }
}
