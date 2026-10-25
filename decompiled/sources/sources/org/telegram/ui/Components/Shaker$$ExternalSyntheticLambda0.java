package org.telegram.ui.Components;

import android.view.View;

/* loaded from: classes7.dex */
public final /* synthetic */ class Shaker$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ View f$0;

    public /* synthetic */ Shaker$$ExternalSyntheticLambda0(View r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
