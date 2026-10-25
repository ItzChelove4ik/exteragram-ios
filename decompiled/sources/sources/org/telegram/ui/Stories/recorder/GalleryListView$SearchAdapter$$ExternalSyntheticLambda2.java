package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.GalleryListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class GalleryListView$SearchAdapter$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ GalleryListView.SearchAdapter f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ GalleryListView$SearchAdapter$$ExternalSyntheticLambda2(GalleryListView.SearchAdapter r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        GalleryListView.SearchAdapter.$r8$lambda$wA3YHlUxKvKsP_Y1rqPwSJOXVjA(this.f$0, this.f$1, r2, r3);
    }
}
