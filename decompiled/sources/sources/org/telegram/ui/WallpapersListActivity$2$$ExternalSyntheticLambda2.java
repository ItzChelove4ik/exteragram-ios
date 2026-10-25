package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class WallpapersListActivity$2$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ WallpapersListActivity.AnonymousClass2 f$0;
    public final /* synthetic */ int[] f$1;

    public /* synthetic */ WallpapersListActivity$2$$ExternalSyntheticLambda2(WallpapersListActivity.AnonymousClass2 r1, int[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        WallpapersListActivity.AnonymousClass2.$r8$lambda$mhZrOjukErnPTsuJk32bz2lUgg8(this.f$0, this.f$1, r2, r3);
    }
}
