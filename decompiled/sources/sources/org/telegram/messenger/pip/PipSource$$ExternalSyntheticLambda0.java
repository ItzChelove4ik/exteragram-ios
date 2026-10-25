package org.telegram.messenger.pip;

import android.view.ViewTreeObserver;

/* loaded from: classes5.dex */
public final /* synthetic */ class PipSource$$ExternalSyntheticLambda0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ PipSource f$0;

    public /* synthetic */ PipSource$$ExternalSyntheticLambda0(PipSource r1) {
        this.f$0 = r1;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.f$0.invalidatePosition();
    }
}
