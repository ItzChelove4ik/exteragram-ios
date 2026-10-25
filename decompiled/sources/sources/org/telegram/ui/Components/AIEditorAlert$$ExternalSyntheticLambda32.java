package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AIEditorAlert$$ExternalSyntheticLambda32 implements Runnable {
    public final /* synthetic */ AIEditorAlert f$0;
    public final /* synthetic */ TL_aicompose.TL_aiComposeTone f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ AIEditorAlert$$ExternalSyntheticLambda32(AIEditorAlert r1, TL_aicompose.TL_aiComposeTone r2, Context r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AIEditorAlert.$r8$lambda$3ALjk6pwRcPoEnSva4DUwvNw1Rc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
