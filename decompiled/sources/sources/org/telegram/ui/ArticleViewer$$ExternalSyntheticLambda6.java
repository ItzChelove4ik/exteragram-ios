package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ ArticleViewer f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.WebPage f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda6(ArticleViewer r1, TLObject r2, int r3, TLRPC.WebPage r4, MessageObject r5, boolean r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArticleViewer.m5999$r8$lambda$fGSPV9IHSMvckYiwtkwkhU7WA4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
