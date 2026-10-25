package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.ui.web.BrowserHistory;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebBrowserSettings$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ WebBrowserSettings f$0;
    public final /* synthetic */ HistoryFragment[] f$1;

    public /* synthetic */ WebBrowserSettings$$ExternalSyntheticLambda3(WebBrowserSettings r1, HistoryFragment[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        WebBrowserSettings.$r8$lambda$GepSwmr4kQ43AzK81LMyTi6DmxE(this.f$0, this.f$1, (BrowserHistory.Entry) r2);
    }
}
