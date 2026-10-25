package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$UploadingStory$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ StoriesController.UploadingStory f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ StoriesController$UploadingStory$$ExternalSyntheticLambda6(StoriesController.UploadingStory r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.UploadingStory.m19717$r8$lambda$2etscVXSVVVWTpPyax8taFgfO8(this.f$0, this.f$1);
    }
}
