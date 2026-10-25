package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ WallpapersListActivity.SearchAdapter f$0;

    public /* synthetic */ WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda3(WallpapersListActivity.SearchAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        WallpapersListActivity.SearchAdapter.$r8$lambda$lsSoGABYArClyqOPMsjofX2XNJo(this.f$0, r1, r2);
    }
}
