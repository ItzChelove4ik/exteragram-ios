package org.telegram.messenger;

import android.animation.ValueAnimator;
import android.view.Window;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda15 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AndroidUtilities.IntColorCallback f$0;
    public final /* synthetic */ Window f$1;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda15(AndroidUtilities.IntColorCallback r1, Window r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        AndroidUtilities.$r8$lambda$i21hdvLSrVyXTv5vepryqyfSj6U(this.f$0, this.f$1, r2);
    }
}
