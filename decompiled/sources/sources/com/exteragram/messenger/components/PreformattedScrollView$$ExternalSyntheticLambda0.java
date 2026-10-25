package com.exteragram.messenger.components;

import android.view.View;

/* loaded from: classes4.dex */
public final /* synthetic */ class PreformattedScrollView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ View f$0;

    public /* synthetic */ PreformattedScrollView$$ExternalSyntheticLambda0(View r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.requestLayout();
    }
}
