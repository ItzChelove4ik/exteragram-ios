package org.telegram.ui.Components.Premium.boosts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class GradientButtonWithCounterView extends ButtonWithCounterView {
    private final CellFlickerDrawable flickerDrawable;
    private boolean incGradient;
    private float progress;
    private final RectF rect;

    public GradientButtonWithCounterView(Context r1, boolean r2, Theme.ResourcesProvider r3) {
        super(r1, r2, r3);
        this.rect = new RectF();
        CellFlickerDrawable r12 = new CellFlickerDrawable();
        this.flickerDrawable = r12;
        r12.animationSpeedScale = 1.2f;
        r12.drawFrame = false;
        r12.repeatProgress = 4.0f;
    }

    @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView, android.view.View
    public void onDraw(Canvas r12) {
        boolean r0 = this.incGradient;
        float r1 = this.progress;
        if (r0 == false) goto L7;
        float r13 = r1 + 0.016f;
        this.progress = r13;
        if (r13 <= 3.0f) goto L10;
        this.incGradient = false;
    L10:
        this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), ((-getMeasuredWidth()) * 0.1f) * this.progress, 0.0f);
        r12.drawRoundRect(this.rect, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), PremiumGradient.getInstance().getMainGradientPaint());
        this.flickerDrawable.setParentWidth(getMeasuredWidth());
        this.flickerDrawable.draw(r12, this.rect, AndroidUtilities.dp(8.0f), null);
        super.onDraw(r12);
        invalidate();
        return;
    L7:
        float r14 = r1 - 0.016f;
        this.progress = r14;
        if (r14 >= 1.0f) goto L10;
        this.incGradient = true;
        goto L10
    }
}
