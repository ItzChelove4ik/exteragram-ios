package org.telegram.messenger;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda25 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ImageView f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ Drawable f$2;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda25(ImageView r1, AtomicBoolean r2, Drawable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        AndroidUtilities.$r8$lambda$ZBP_rGQSuvWa9xzbu82YSSoGfZY(this.f$0, this.f$1, this.f$2, r3);
    }
}
