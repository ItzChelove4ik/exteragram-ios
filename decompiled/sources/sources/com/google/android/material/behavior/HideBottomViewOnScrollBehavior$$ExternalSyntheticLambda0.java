package com.google.android.material.behavior;

import android.view.View;
import android.view.accessibility.AccessibilityManager;

/* loaded from: classes5.dex */
public final /* synthetic */ class HideBottomViewOnScrollBehavior$$ExternalSyntheticLambda0 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ HideBottomViewOnScrollBehavior f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ HideBottomViewOnScrollBehavior$$ExternalSyntheticLambda0(HideBottomViewOnScrollBehavior r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean r2) {
        HideBottomViewOnScrollBehavior.m1820$r8$lambda$u8hW3V09BoGAavrLZRTR540LS0(this.f$0, this.f$1, r2);
    }
}
