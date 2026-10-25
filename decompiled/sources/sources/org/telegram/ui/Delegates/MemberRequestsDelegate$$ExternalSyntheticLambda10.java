package org.telegram.ui.Delegates;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class MemberRequestsDelegate$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ MemberRequestsDelegate f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ TLRPC.User f$5;
    public final /* synthetic */ TLRPC.TL_messages_hideChatJoinRequest f$6;

    public /* synthetic */ MemberRequestsDelegate$$ExternalSyntheticLambda10(MemberRequestsDelegate r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_chatInviteImporter r4, boolean r5, TLRPC.User r6, TLRPC.TL_messages_hideChatJoinRequest r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MemberRequestsDelegate.m14278$r8$lambda$2n2gxPHM92617CJ0tNI1VG3qaE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
