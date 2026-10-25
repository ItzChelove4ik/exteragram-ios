package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranslateAlert2$$ExternalSyntheticLambda6 implements Utilities.Callback {
    public final /* synthetic */ TranslateAlert2 f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ TranslateAlert2$$ExternalSyntheticLambda6(TranslateAlert2 r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        TranslateAlert2.$r8$lambda$kbWC1iBRRTDdn7x8ZVp2WlDoAqQ(this.f$0, this.f$1, (TLRPC.TL_textWithEntities) r2);
    }
}
