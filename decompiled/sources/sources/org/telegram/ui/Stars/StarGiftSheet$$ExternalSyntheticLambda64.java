package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda64 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda64(StarGiftSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.dismiss(((Boolean) r1).booleanValue());
    }
}
