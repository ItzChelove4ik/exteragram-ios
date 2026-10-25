package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda142 implements MessagesController.IsInChatCheckedCallback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ DialogsActivity f$5;
    public final /* synthetic */ TLRPC.User f$6;
    public final /* synthetic */ long f$7;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda142(LaunchActivity r1, String r2, String r3, int r4, TLRPC.Chat r5, DialogsActivity r6, TLRPC.User r7, long r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.messenger.MessagesController.IsInChatCheckedCallback
    public final void run(boolean r13, TLRPC.TL_chatAdminRights r14, String r15) {
        LaunchActivity.$r8$lambda$2poBaxj0Ut_Z3tFnLDr6NcWnDtE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r13, r14, r15);
    }
}
