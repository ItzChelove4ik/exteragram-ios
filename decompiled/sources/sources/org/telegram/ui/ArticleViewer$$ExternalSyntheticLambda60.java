package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda60 implements RequestDelegate {
    public final /* synthetic */ ArticleViewer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Utilities.Callback0Return f$4;
    public final /* synthetic */ TLRPC.TL_messages_getWebPage f$5;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda60(ArticleViewer r1, int r2, Browser.Progress r3, String r4, Utilities.Callback0Return r5, TLRPC.TL_messages_getWebPage r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ArticleViewer.m5990$r8$lambda$6RceP80ceRWcdOvUlvuC0xFT8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
