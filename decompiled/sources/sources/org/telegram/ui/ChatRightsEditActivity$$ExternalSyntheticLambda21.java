package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatRightsEditActivity$$ExternalSyntheticLambda21 implements RequestDelegate {
    public final /* synthetic */ ChatRightsEditActivity f$0;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$1;
    public final /* synthetic */ TwoStepVerificationActivity f$2;
    public final /* synthetic */ TLRPC.TL_channels_editCreator f$3;

    public /* synthetic */ ChatRightsEditActivity$$ExternalSyntheticLambda21(ChatRightsEditActivity r1, TLRPC.InputCheckPasswordSRP r2, TwoStepVerificationActivity r3, TLRPC.TL_channels_editCreator r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChatRightsEditActivity.$r8$lambda$OblO2tIdaIpVLcgPlrNbn5i3CUs(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
