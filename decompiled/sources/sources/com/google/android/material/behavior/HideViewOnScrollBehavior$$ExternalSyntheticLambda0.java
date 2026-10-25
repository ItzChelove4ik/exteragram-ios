package com.google.android.material.behavior;

import android.view.View;
import android.view.accessibility.AccessibilityManager;

/* loaded from: classes5.dex */
public final /* synthetic */ class HideViewOnScrollBehavior$$ExternalSyntheticLambda0 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ HideViewOnScrollBehavior f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ HideViewOnScrollBehavior$$ExternalSyntheticLambda0(HideViewOnScrollBehavior r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean r2) {
        HideViewOnScrollBehavior.$r8$lambda$O4yxcwWX0oqhtqJeVevJwrB8hK4(this.f$0, this.f$1, r2);
    }
}
