package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.ui.web.BrowserHistory;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda40 implements Utilities.Callback {
    public final /* synthetic */ ArticleViewer f$0;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda40(ArticleViewer r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.openHistoryEntry((BrowserHistory.Entry) r1);
    }
}
