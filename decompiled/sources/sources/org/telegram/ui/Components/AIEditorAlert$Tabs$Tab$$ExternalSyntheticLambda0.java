package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$Tabs$Tab$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ AIEditorAlert.Tabs.Tab f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ AIEditorAlert$Tabs$Tab$$ExternalSyntheticLambda0(AIEditorAlert.Tabs.Tab r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        AIEditorAlert.Tabs.Tab.$r8$lambda$VUYK_0QLJxz7vC050ddoBsJdB9E(this.f$0, this.f$1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
