package org.telegram.ui.Charts;

import android.animation.ValueAnimator;
import org.telegram.ui.Charts.view_data.ChartBottomSignatureData;

/* loaded from: classes6.dex */
public final /* synthetic */ class BaseChartView$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BaseChartView f$0;
    public final /* synthetic */ ChartBottomSignatureData f$1;

    public /* synthetic */ BaseChartView$$ExternalSyntheticLambda3(BaseChartView r1, ChartBottomSignatureData r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        BaseChartView.m7370$r8$lambda$AXptsAnIXPu2xR4hWPRhkGvjRQ(this.f$0, this.f$1, r2);
    }
}
