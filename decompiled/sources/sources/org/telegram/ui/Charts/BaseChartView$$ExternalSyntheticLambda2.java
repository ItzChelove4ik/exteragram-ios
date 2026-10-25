package org.telegram.ui.Charts;

import android.animation.ValueAnimator;
import org.telegram.ui.Charts.view_data.ChartHorizontalLinesData;

/* loaded from: classes6.dex */
public final /* synthetic */ class BaseChartView$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BaseChartView f$0;
    public final /* synthetic */ ChartHorizontalLinesData f$1;

    public /* synthetic */ BaseChartView$$ExternalSyntheticLambda2(BaseChartView r1, ChartHorizontalLinesData r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        BaseChartView.$r8$lambda$TDUFRa8WhBI7mm8d8Me5RDEfOkY(this.f$0, this.f$1, r2);
    }
}
