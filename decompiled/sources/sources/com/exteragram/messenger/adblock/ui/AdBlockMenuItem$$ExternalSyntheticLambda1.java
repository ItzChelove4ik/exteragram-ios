package com.exteragram.messenger.adblock.ui;

import com.exteragram.messenger.adblock.WebAdBlocker;

/* loaded from: classes4.dex */
public final /* synthetic */ class AdBlockMenuItem$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ WebAdBlocker f$0;

    public /* synthetic */ AdBlockMenuItem$$ExternalSyntheticLambda1(WebAdBlocker r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.reload();
    }
}
