package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda68 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteJoinResultWebView f$1;
    public final /* synthetic */ TLRPC.Chat f$2;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda68(int r1, TLRPC.TL_chatInviteJoinResultWebView r2, TLRPC.Chat r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArticleViewer.m5991$r8$lambda$8C1j81eT1F90rB_oMzEI4lk8(this.f$0, this.f$1, this.f$2);
    }
}
