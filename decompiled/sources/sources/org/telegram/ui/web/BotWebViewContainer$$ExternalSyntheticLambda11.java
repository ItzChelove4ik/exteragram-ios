package org.telegram.ui.web;

import java.io.File;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda11(BotWebViewContainer r1, AlertDialog r2, String r3, String r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        BotWebViewContainer.m21960$r8$lambda$SWTVE8uKPxMUjyKC90X2Zgo6JM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (File) r7);
    }
}
