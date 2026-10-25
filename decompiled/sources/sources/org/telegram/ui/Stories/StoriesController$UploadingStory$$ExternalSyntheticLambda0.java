package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$UploadingStory$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StoriesController.UploadingStory f$0;

    public /* synthetic */ StoriesController$UploadingStory$$ExternalSyntheticLambda0(StoriesController.UploadingStory r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StoriesController.UploadingStory.$r8$lambda$MmeK65JXoyPyfRpYhp8rjfY3ikA(this.f$0, r1, r2);
    }
}
