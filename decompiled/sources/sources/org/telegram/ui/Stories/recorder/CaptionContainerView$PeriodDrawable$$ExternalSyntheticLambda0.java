package org.telegram.ui.Stories.recorder;

import org.telegram.ui.Stories.recorder.CaptionContainerView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CaptionContainerView$PeriodDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CaptionContainerView.PeriodDrawable f$0;

    public /* synthetic */ CaptionContainerView$PeriodDrawable$$ExternalSyntheticLambda0(CaptionContainerView.PeriodDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
