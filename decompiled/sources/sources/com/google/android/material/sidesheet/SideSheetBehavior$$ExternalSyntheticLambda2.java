package com.google.android.material.sidesheet;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public final /* synthetic */ class SideSheetBehavior$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SideSheetBehavior f$0;
    public final /* synthetic */ ViewGroup.MarginLayoutParams f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ View f$3;

    public /* synthetic */ SideSheetBehavior$$ExternalSyntheticLambda2(SideSheetBehavior r1, ViewGroup.MarginLayoutParams r2, int r3, View r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r4) {
        SideSheetBehavior.m1899$r8$lambda$vg37JzyumMkESCn5KJfiWrmBw(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
