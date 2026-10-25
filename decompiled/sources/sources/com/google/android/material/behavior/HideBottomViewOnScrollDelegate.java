package com.google.android.material.behavior;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/* loaded from: classes5.dex */
final class HideBottomViewOnScrollDelegate extends HideViewOnScrollDelegate {
    public HideBottomViewOnScrollDelegate() {
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public <V extends View> int getSize(V r1, ViewGroup.MarginLayoutParams r2) {
        return r1.getMeasuredHeight() + r2.bottomMargin;
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public int getTargetTranslation() {
        return 0;
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public int getViewEdge() {
        return 1;
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public <V extends View> ViewPropertyAnimator getViewTranslationAnimator(V r1, int r2) {
        return r1.animate().translationY(r2);
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public <V extends View> void setAdditionalHiddenOffset(V r1, int r2, int r3) {
        r1.setTranslationY(r2 + r3);
    }

    @Override // com.google.android.material.behavior.HideViewOnScrollDelegate
    public <V extends View> void setViewTranslation(V r1, int r2) {
        r1.setTranslationY(r2);
    }
}
