package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda39 implements RequestDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda39(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StoriesController.$r8$lambda$Owir6VpucWpW_OEQO03M6UcNEes(this.f$0, r1, r2);
    }
}
