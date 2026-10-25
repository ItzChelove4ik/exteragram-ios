package org.telegram.ui.Components.voip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import org.telegram.ui.Stories.recorder.HintView2;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIpHintView extends HintView2 {
    private final VoIPBackgroundProvider backgroundProvider;
    private final Paint mainPaint;

    public VoIpHintView(Context r2, int r3, VoIPBackgroundProvider r4, boolean r5) {
        super(r2, r3);
        Paint r22 = new Paint(1);
        this.mainPaint = r22;
        this.backgroundProvider = r4;
        r4.attach(this);
        r22.setPathEffect(new CornerPathEffect(this.rounding));
        if (r5 == false) goto L6;
        setCloseButton(true);
        return;
    }

    @Override // org.telegram.ui.Stories.recorder.HintView2, android.view.View
    public void dispatchDraw(Canvas r4) {
        this.backgroundProvider.setDarkTranslation(getX(), getY());
        super.dispatchDraw(r4);
    }

    @Override // org.telegram.ui.Stories.recorder.HintView2
    public void drawBgPath(Canvas r9, float r10) {
        this.mainPaint.setShader(this.backgroundProvider.getDarkPaint().getShader());
        r9.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) (Math.min(this.backgroundPaint.getAlpha(), this.backgroundProvider.getDarkPaint().getAlpha()) * r10), 31);
        r9.drawPath(this.path, this.mainPaint);
        if (this.backgroundProvider.isReveal() == false) goto L5;
        this.mainPaint.setShader(this.backgroundProvider.getRevealDarkPaint().getShader());
        r9.drawPath(this.path, this.mainPaint);
    L5:
        r9.restore();
    }
}
