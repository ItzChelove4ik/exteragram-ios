package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$UploadingStory$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ StoriesController.UploadingStory f$0;

    public /* synthetic */ StoriesController$UploadingStory$$ExternalSyntheticLambda5(StoriesController.UploadingStory r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        StoriesController.UploadingStory.m19718$r8$lambda$aHICgBUDOSbEk3nKlwBrdigyWI(this.f$0, (TLRPC.Document) r1);
    }
}
