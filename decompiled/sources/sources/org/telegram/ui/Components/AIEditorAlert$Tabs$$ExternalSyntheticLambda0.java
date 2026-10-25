package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.ui.Components.AIEditorAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$Tabs$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TL_aicompose.AiComposeTone f$1;

    public /* synthetic */ AIEditorAlert$Tabs$$ExternalSyntheticLambda0(Utilities.Callback r1, TL_aicompose.AiComposeTone r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r2) {
        AIEditorAlert.Tabs.$r8$lambda$6uA4f0ywxY2HiAjUewcvRpB2dCE(this.f$0, this.f$1, r2);
    }
}
