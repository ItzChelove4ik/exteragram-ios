package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda41 implements Utilities.Callback2 {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Browser.Progress f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda41(LaunchActivity r1, boolean r2, int r3, Browser.Progress r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        LaunchActivity.$r8$lambda$7VBDVa0K1b7vZfz8SMRrGH1dD_M(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_help_appUpdate) r7, (TLRPC.TL_error) r8);
    }
}
