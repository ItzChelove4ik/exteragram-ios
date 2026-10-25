package org.telegram.ui.Cells;

import android.animation.ValueAnimator;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.ui.Cells.AboutLinkCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class AboutLinkCell$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AboutLinkCell f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ AboutLinkCell.SpringInterpolator f$4;

    public /* synthetic */ AboutLinkCell$$ExternalSyntheticLambda0(AboutLinkCell r1, AtomicReference r2, float r3, float r4, AboutLinkCell.SpringInterpolator r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r7) {
        AboutLinkCell.$r8$lambda$Pt22f2MUtmcmKf_HHfwHegxdhSk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
