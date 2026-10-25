package org.telegram.messenger.postdrawcompat;

import android.view.ViewTreeObserver;

/* loaded from: classes3.dex */
public final /* synthetic */ class ViewOnPostDraw$$ExternalSyntheticLambda0 implements ViewTreeObserver.OnDrawListener {
    public final /* synthetic */ ViewOnPostDraw f$0;

    public /* synthetic */ ViewOnPostDraw$$ExternalSyntheticLambda0(ViewOnPostDraw r1) {
        this.f$0 = r1;
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        this.f$0.forceLayout();
    }
}
