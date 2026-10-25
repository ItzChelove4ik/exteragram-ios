package org.telegram.ui.web;

import org.telegram.ui.web.WebActionBar;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebActionBar$ForwardDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WebActionBar.ForwardDrawable f$0;

    public /* synthetic */ WebActionBar$ForwardDrawable$$ExternalSyntheticLambda0(WebActionBar.ForwardDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
