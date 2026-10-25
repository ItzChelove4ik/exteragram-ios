package org.telegram.ui;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda118 implements Runnable {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ TLRPC.TL_help_appUpdate f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Browser.Progress f$4;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda118(LaunchActivity r1, TLRPC.TL_help_appUpdate r2, boolean r3, int r4, Browser.Progress r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LaunchActivity.$r8$lambda$IrJY4p9XhkaBDzX1XaQnLHUl39E(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
