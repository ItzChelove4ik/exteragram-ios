package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$BotPreviewsList$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StoriesController.BotPreviewsList f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ StoriesController$BotPreviewsList$$ExternalSyntheticLambda1(StoriesController.BotPreviewsList r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StoriesController.BotPreviewsList.$r8$lambda$mDs5_1SbFLxyKpSZQJh8_9apNA4(this.f$0, this.f$1, r2, r3);
    }
}
