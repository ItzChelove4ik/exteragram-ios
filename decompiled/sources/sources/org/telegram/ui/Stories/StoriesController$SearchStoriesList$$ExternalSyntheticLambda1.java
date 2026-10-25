package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$SearchStoriesList$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StoriesController.SearchStoriesList f$0;

    public /* synthetic */ StoriesController$SearchStoriesList$$ExternalSyntheticLambda1(StoriesController.SearchStoriesList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StoriesController.SearchStoriesList.$r8$lambda$U03vayNwVsEQYqjtQFhi_86W5MQ(this.f$0, r1, r2);
    }
}
