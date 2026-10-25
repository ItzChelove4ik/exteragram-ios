package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ AIEditorAlert.CreateAiStyleAlert f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;

    public /* synthetic */ AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda8(AIEditorAlert.CreateAiStyleAlert r1, Theme.ResourcesProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        AIEditorAlert.CreateAiStyleAlert.$r8$lambda$feLLmp5f3Uo4ZZW9I0MF1VRtk3E(this.f$0, this.f$1, (TL_aicompose.AiComposeTone) r2, (TLRPC.TL_error) r3);
    }
}
