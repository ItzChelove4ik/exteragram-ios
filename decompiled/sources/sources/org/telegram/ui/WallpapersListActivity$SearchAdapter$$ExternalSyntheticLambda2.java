package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ WallpapersListActivity.SearchAdapter f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda2(WallpapersListActivity.SearchAdapter r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        WallpapersListActivity.SearchAdapter.m21276$r8$lambda$t8zW4MxoxMYmZgVqBKpIhRYBs(this.f$0, this.f$1, r2, r3);
    }
}
