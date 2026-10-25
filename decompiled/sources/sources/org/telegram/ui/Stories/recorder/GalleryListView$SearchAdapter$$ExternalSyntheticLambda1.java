package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.GalleryListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class GalleryListView$SearchAdapter$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ GalleryListView.SearchAdapter f$0;
    public final /* synthetic */ MessagesController f$1;

    public /* synthetic */ GalleryListView$SearchAdapter$$ExternalSyntheticLambda1(GalleryListView.SearchAdapter r1, MessagesController r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        GalleryListView.SearchAdapter.m20037$r8$lambda$yN2H3qfaF3cL8XkRosIsILXPcs(this.f$0, this.f$1, r2, r3);
    }
}
