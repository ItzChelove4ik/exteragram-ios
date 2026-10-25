package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$StoriesCollections$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ StoriesController.StoriesCollections f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ StoriesController$StoriesCollections$$ExternalSyntheticLambda4(StoriesController.StoriesCollections r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StoriesController.StoriesCollections.$r8$lambda$Pqh_viu1ivnpOd51WOAEMNnwbw8(this.f$0, this.f$1, r2, r3);
    }
}
