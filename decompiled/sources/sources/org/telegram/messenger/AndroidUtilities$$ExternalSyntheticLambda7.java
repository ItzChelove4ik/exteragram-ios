package org.telegram.messenger;

import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda7 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ float f$2;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda7(Runnable r1, View r2, float r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r8, boolean r9, float r10, float r11) {
        AndroidUtilities.$r8$lambda$AxkaJ2J73z4mS3JZ7ehTEo5XrWI(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
