package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda6 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert.AiStyleAlert f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ TL_aicompose.AiComposeTone f$2;

    public /* synthetic */ AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda6(AIEditorAlert.AiStyleAlert r1, Theme.ResourcesProvider r2, TL_aicompose.AiComposeTone r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        AIEditorAlert.AiStyleAlert.m8684$r8$lambda$7mmTr52PLLQyv3vq2pBUA5EwKg(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r3, (TLRPC.TL_error) r4);
    }
}
