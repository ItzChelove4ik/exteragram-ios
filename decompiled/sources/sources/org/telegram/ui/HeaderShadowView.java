package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class HeaderShadowView extends View implements FactorAnimator.Target {
    private final INavigationLayout iNavigationLayout;
    private final BoolAnimator shadowVisible;

    public HeaderShadowView(Context r8, INavigationLayout r9) {
        super(r8);
        this.shadowVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380, true);
        this.iNavigationLayout = r9;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r2) {
        super.dispatchDraw(r2);
        this.iNavigationLayout.drawHeaderShadow(r2, 0);
    }

    public boolean isShadowVisible() {
        return this.shadowVisible.getValue();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r2 <= 0.0f) goto L5;
        int r12 = 0;
    L6:
        setVisibility(r12);
        setAlpha(r2);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    public void setShadowVisible(boolean r1, boolean r2) {
        this.shadowVisible.setValue(r1, r2);
    }
}
