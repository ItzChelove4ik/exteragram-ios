package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class GroupVoipInviteAlert$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ GroupVoipInviteAlert f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_channels_getParticipants f$3;

    public /* synthetic */ GroupVoipInviteAlert$$ExternalSyntheticLambda3(GroupVoipInviteAlert r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_channels_getParticipants r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupVoipInviteAlert.$r8$lambda$qQLYxm0fGhXt8FIkHbu7CB7XB2A(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
