package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda9 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert.CreateAiStyleAlert f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda9(AIEditorAlert.CreateAiStyleAlert r1, Browser.Progress r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        AIEditorAlert.CreateAiStyleAlert.m8687$r8$lambda$F88XIvFLvGLS6OIISlq4ypi5Q8(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r3, (TLRPC.TL_error) r4);
    }
}
