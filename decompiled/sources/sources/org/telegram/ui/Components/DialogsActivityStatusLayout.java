package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class DialogsActivityStatusLayout extends View {
    private final RectF animatingRectF;
    private final BoolAnimator animatorStatusBarVisible;
    private final Paint fillingPaint;
    private final Runnable justForTestR;
    private final RectF statusBarRectF;
    private final RectF telegramLogoRectF;

    public static /* synthetic */ void $r8$lambda$KFz3bTPRVZcnbk7him0WIDOckdE(DialogsActivityStatusLayout r0) {
        r0.justForTest();
    }

    public DialogsActivityStatusLayout(Context r4) {
        super(r4);
        this.animatorStatusBarVisible = new BoolAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.fillingPaint = new Paint(1);
        this.statusBarRectF = new RectF();
        this.telegramLogoRectF = new RectF();
        this.animatingRectF = new RectF();
        this.justForTestR = new DialogsActivityStatusLayout$$ExternalSyntheticLambda0(this);
        updateColors();
    }

    private void justForTest() {
        this.animatorStatusBarVisible.setValue(!r0.getValue(), true);
        AndroidUtilities.runOnUIThread(this.justForTestR, 3000);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AndroidUtilities.runOnUIThread(this.justForTestR, 3000);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.justForTestR);
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        float r0 = this.animatorStatusBarVisible.getFloatValue();
        AndroidUtilities.lerp(this.telegramLogoRectF, this.statusBarRectF, r0, this.animatingRectF);
        float r02 = AndroidUtilities.lerp(AndroidUtilities.dp(15.0f), 0, r0);
        r5.drawRoundRect(this.animatingRectF, r02, r02, this.fillingPaint);
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        int r62 = ActionBar.getCurrentActionBarHeight();
        int r0 = getPaddingTop();
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r0 + r62, TLObject.FLAG_30));
        this.statusBarRectF.set(0.0f, 0.0f, getMeasuredWidth(), r0);
        this.telegramLogoRectF.set(AndroidUtilities.dp(12.0f), (r0 + (r62 / 2)) - AndroidUtilities.dp(15.0f), AndroidUtilities.dp(12.0f) + AndroidUtilities.dp(30.0f), r0 + AndroidUtilities.dp(30.0f));
    }

    public void updateColors() {
        this.fillingPaint.setColor(Theme.getColor(Theme.key_telegram_color));
        invalidate();
    }
}
