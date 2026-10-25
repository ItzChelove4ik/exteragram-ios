package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda112 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TL_phone.PhoneCall f$1;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda112(VoIPService r1, TL_phone.PhoneCall r2, TL_phone.exportGroupCallInvite r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        VoIPService.$r8$lambda$DAE0FNhUor_NoQQBBvxJ9pXxnzk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
