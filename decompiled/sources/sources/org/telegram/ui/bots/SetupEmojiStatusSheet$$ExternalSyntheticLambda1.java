package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ TLRPC.Document f$1;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda1(Utilities.Callback2 r1, TLRPC.Document r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        SetupEmojiStatusSheet.$r8$lambda$qDwyHc5fpxxeLe5vW_dVAWCeWXs(this.f$0, this.f$1, (String) r2);
    }
}
