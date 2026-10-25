package org.telegram.ui.Components;

import android.widget.FrameLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class PipVideoOverlay$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ FrameLayout f$0;

    public /* synthetic */ PipVideoOverlay$$ExternalSyntheticLambda10(FrameLayout r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
