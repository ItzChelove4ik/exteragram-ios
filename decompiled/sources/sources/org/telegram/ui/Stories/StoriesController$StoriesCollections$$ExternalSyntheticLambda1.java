package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$StoriesCollections$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StoriesController.StoriesCollections f$0;

    public /* synthetic */ StoriesController$StoriesCollections$$ExternalSyntheticLambda1(StoriesController.StoriesCollections r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StoriesController.StoriesCollections.$r8$lambda$VCeUGGr2GZ3ceowPtrSEpZdH3AU(this.f$0, r1, r2);
    }
}
