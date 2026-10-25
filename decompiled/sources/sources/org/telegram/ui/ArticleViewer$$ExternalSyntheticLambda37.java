package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda37 implements RequestDelegate {
    public final /* synthetic */ ArticleViewer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda37(ArticleViewer r1, int r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ArticleViewer.m6001$r8$lambda$qfmxxSOzhF3WMAzium3L4VZ28(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
