package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ArticleViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda67 implements Runnable {
    public final /* synthetic */ ArticleViewer.BlockChannelCell f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLRPC.TL_channels_joinChannel f$3;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda67(ArticleViewer.BlockChannelCell r1, int r2, TLRPC.TL_error r3, TLRPC.TL_channels_joinChannel r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArticleViewer.$r8$lambda$uh92QrckDDOQZkeElt5yQvemKDg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
