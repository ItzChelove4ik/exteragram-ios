package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.Path;
import androidx.core.util.Consumer;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$BackgroundView$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ PremiumPreviewFragment.BackgroundView f$0;
    public final /* synthetic */ Path f$1;
    public final /* synthetic */ float[] f$2;

    public /* synthetic */ PremiumPreviewFragment$BackgroundView$$ExternalSyntheticLambda1(PremiumPreviewFragment.BackgroundView r1, Path r2, float[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r3) {
        PremiumPreviewFragment.BackgroundView.$r8$lambda$_EL8VF1IqGedQlHc64RgLkCvawA(this.f$0, this.f$1, this.f$2, (Canvas) r3);
    }
}
