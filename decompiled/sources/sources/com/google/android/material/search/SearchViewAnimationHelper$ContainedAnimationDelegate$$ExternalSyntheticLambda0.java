package com.google.android.material.search;

import androidx.appcompat.widget.Toolbar;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.google.android.material.search.SearchViewAnimationHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda0 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ SearchViewAnimationHelper.ContainedAnimationDelegate f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Toolbar f$2;

    public /* synthetic */ SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda0(SearchViewAnimationHelper.ContainedAnimationDelegate r1, boolean r2, Toolbar r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r8, boolean r9, float r10, float r11) {
        SearchViewAnimationHelper.ContainedAnimationDelegate.$r8$lambda$hqbvop16BkV8NlN9PzC9mY8yPMk(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
