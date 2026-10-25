package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$StoriesCollections$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ StoriesController.StoriesCollections f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ StoriesController$StoriesCollections$$ExternalSyntheticLambda5(StoriesController.StoriesCollections r1, TLObject r2, Utilities.Callback r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.StoriesCollections.$r8$lambda$dNWDaulBCDVU9GkGHWt5mlqV1Is(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
