package org.telegram.ui;

import android.animation.ValueAnimator;
import org.telegram.ui.Charts.view_data.TransitionParams;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$BaseChartCell$$ExternalSyntheticLambda5 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ StatisticActivity.BaseChartCell f$0;
    public final /* synthetic */ TransitionParams f$1;
    public final /* synthetic */ float f$2;

    public /* synthetic */ StatisticActivity$BaseChartCell$$ExternalSyntheticLambda5(StatisticActivity.BaseChartCell r1, TransitionParams r2, float r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        StatisticActivity.BaseChartCell.m19283$r8$lambda$UyxITjH46P9Xf9qly_OzlsBqmg(this.f$0, this.f$1, this.f$2, r3);
    }
}
