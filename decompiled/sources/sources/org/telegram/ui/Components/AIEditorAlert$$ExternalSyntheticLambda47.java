package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$$ExternalSyntheticLambda47 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TL_aicompose.TL_aiComposeTone f$2;

    public /* synthetic */ AIEditorAlert$$ExternalSyntheticLambda47(AIEditorAlert r1, Browser.Progress r2, TL_aicompose.TL_aiComposeTone r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        AIEditorAlert.$r8$lambda$FumP5kjUbbNwGzf4fkQqmQw2BzI(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r3, (TLRPC.TL_error) r4);
    }
}
