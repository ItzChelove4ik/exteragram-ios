package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ArticleViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda52 implements RequestDelegate {
    public final /* synthetic */ IArticleViewer f$0;
    public final /* synthetic */ ArticleViewer.WebpageAdapter f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ ArticleViewer.BlockChannelCell f$3;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda52(IArticleViewer r1, ArticleViewer.WebpageAdapter r2, int r3, ArticleViewer.BlockChannelCell r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ArticleViewer.$r8$lambda$7Ow6upENl44LN33FFQBmMRW5X0I(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
