package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ArticleViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda56 implements Runnable {
    public final /* synthetic */ IArticleViewer f$0;
    public final /* synthetic */ ArticleViewer.WebpageAdapter f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ ArticleViewer.BlockChannelCell f$5;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda56(IArticleViewer r1, ArticleViewer.WebpageAdapter r2, TLRPC.TL_error r3, TLObject r4, int r5, ArticleViewer.BlockChannelCell r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArticleViewer.m5988$r8$lambda$2rhW2LfNfDa61aBbL2Y3O1tacw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
