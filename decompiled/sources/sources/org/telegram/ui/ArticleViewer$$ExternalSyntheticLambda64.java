package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda64 implements Runnable {
    public final /* synthetic */ ArticleViewer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ Utilities.Callback0Return f$5;
    public final /* synthetic */ TLRPC.TL_messages_getWebPage f$6;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda64(ArticleViewer r1, int r2, Browser.Progress r3, TLObject r4, String r5, Utilities.Callback0Return r6, TLRPC.TL_messages_getWebPage r7) {
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
        ArticleViewer.$r8$lambda$tm6Nu892jcSAwWeLEyMROp56UWo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
