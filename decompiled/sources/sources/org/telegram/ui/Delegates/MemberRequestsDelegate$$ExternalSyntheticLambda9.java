package org.telegram.ui.Delegates;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class MemberRequestsDelegate$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ MemberRequestsDelegate f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ TLRPC.TL_messages_hideChatJoinRequest f$4;

    public /* synthetic */ MemberRequestsDelegate$$ExternalSyntheticLambda9(MemberRequestsDelegate r1, TLRPC.TL_chatInviteImporter r2, boolean r3, TLRPC.User r4, TLRPC.TL_messages_hideChatJoinRequest r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MemberRequestsDelegate.m14280$r8$lambda$mM1XisvfpzBZKpnOJFlBuyd67o(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
