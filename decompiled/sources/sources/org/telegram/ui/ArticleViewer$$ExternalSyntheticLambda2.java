package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ArticleViewer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.WebPage f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda2(ArticleViewer r1, int r2, TLRPC.WebPage r3, MessageObject r4, boolean r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ArticleViewer.$r8$lambda$ZBx4VumPMIvQtsuvWEYwtGMUvBc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
