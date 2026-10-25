package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import java.util.ArrayList;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;

/* loaded from: classes7.dex */
public final /* synthetic */ class RecyclerAnimationScrollHelper$1$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ RecyclerAnimationScrollHelper.AnonymousClass1 f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ ArrayList f$5;

    public /* synthetic */ RecyclerAnimationScrollHelper$1$$ExternalSyntheticLambda0(RecyclerAnimationScrollHelper.AnonymousClass1 r1, ArrayList r2, boolean r3, int r4, int r5, ArrayList r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r8) {
        RecyclerAnimationScrollHelper.AnonymousClass1.$r8$lambda$9T1ZXwceH73WmQ9KucH0H5e2P98(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
