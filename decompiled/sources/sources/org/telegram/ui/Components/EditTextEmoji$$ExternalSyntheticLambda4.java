package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class EditTextEmoji$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ EditTextCaption f$0;

    public /* synthetic */ EditTextEmoji$$ExternalSyntheticLambda4(EditTextCaption r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.performMenuAction(((Integer) r1).intValue());
    }
}
