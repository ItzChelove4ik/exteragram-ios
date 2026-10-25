package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.UserFull f$2;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda11(boolean[] r1, Utilities.Callback2 r2, TLRPC.UserFull r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SetupEmojiStatusSheet.$r8$lambda$ax83ennwCnpbPD0KwgVfUXmrEqI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
