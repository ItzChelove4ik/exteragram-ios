package com.google.android.material.navigation;

import com.google.android.material.motion.MaterialBackOrchestrator;

/* loaded from: classes5.dex */
public final /* synthetic */ class NavigationView$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MaterialBackOrchestrator f$0;

    public /* synthetic */ NavigationView$1$$ExternalSyntheticLambda0(MaterialBackOrchestrator r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.startListeningForBackCallbacksWithPriorityOverlay();
    }
}
