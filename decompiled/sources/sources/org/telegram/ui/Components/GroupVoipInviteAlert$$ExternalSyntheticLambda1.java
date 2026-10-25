package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class GroupVoipInviteAlert$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ GroupVoipInviteAlert f$0;
    public final /* synthetic */ TLRPC.TL_channels_getParticipants f$1;

    public /* synthetic */ GroupVoipInviteAlert$$ExternalSyntheticLambda1(GroupVoipInviteAlert r1, TLRPC.TL_channels_getParticipants r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        GroupVoipInviteAlert.$r8$lambda$0VTEIly3Y1tSwf95PHnIa2NtlCI(this.f$0, this.f$1, r2, r3);
    }
}
