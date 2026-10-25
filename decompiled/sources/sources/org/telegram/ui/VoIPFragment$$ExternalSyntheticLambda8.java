package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPFragment$$ExternalSyntheticLambda8 implements Utilities.Callback {
    public final /* synthetic */ VoIPFragment f$0;

    public /* synthetic */ VoIPFragment$$ExternalSyntheticLambda8(VoIPFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        VoIPFragment.m21157$r8$lambda$y9sJwLOQOBP06on8Nkmo7Ftws8(this.f$0, (TLRPC.TL_messages_stickerSet) r1);
    }
}
