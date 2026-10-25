package org.telegram.ui.Charts;

import android.animation.ValueAnimator;

/* loaded from: classes6.dex */
public final /* synthetic */ class PieChartView$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PieChartView f$0;
    public final /* synthetic */ PieChartViewData f$1;

    public /* synthetic */ PieChartView$$ExternalSyntheticLambda0(PieChartView r1, PieChartViewData r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        PieChartView.m7374$r8$lambda$0UlhreTqkyaMbOGQtgvdr0b_2Y(this.f$0, this.f$1, r2);
    }
}
