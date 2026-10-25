package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TL_account.updateEmojiStatus f$3;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda9(boolean[] r1, Utilities.Callback r2, int r3, TL_account.updateEmojiStatus r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        SetupEmojiStatusSheet.$r8$lambda$OQ0ytzBiGXpvsWY9v4dtAOtj2ws(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
