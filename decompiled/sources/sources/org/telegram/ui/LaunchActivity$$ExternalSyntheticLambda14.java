package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda14 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Theme.ThemeInfo f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda14(LaunchActivity r1, Theme.ThemeInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LaunchActivity.m15634$r8$lambda$lBsBDL8Y9FmnHUMliKqyQoxMI(this.f$0, this.f$1, r2, r3);
    }
}
