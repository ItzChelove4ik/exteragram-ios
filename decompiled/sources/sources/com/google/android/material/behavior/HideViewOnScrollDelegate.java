package com.google.android.material.behavior;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

/* loaded from: classes5.dex */
abstract class HideViewOnScrollDelegate {
    public HideViewOnScrollDelegate() {
    }

    public abstract <V extends View> int getSize(V r1, ViewGroup.MarginLayoutParams r2);

    public abstract int getTargetTranslation();

    public abstract int getViewEdge();

    public abstract <V extends View> ViewPropertyAnimator getViewTranslationAnimator(V r1, int r2);

    public abstract <V extends View> void setAdditionalHiddenOffset(V r1, int r2, int r3);

    public abstract <V extends View> void setViewTranslation(V r1, int r2);
}
