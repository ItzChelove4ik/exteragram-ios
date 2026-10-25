package org.telegram.ui.Components.Premium.boosts;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda31 implements Utilities.Callback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Browser.Progress f$1;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda31(AtomicBoolean r1, Browser.Progress r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        BoostDialogs.$r8$lambda$uiKy_Sm31gbPIi8Kcd7_Wd7f12Y(this.f$0, this.f$1, (TLRPC.TL_error) r2);
    }
}
