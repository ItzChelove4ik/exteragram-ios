package org.telegram.ui.Charts;

import android.animation.ValueAnimator;
import org.telegram.ui.Charts.view_data.LineViewData;

/* loaded from: classes6.dex */
public final /* synthetic */ class BaseChartView$$ExternalSyntheticLambda4 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BaseChartView f$0;
    public final /* synthetic */ LineViewData f$1;

    public /* synthetic */ BaseChartView$$ExternalSyntheticLambda4(BaseChartView r1, LineViewData r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        BaseChartView.$r8$lambda$A2JflOeeZkfiHMl9EHO5_5WdebU(this.f$0, this.f$1, r2);
    }
}
