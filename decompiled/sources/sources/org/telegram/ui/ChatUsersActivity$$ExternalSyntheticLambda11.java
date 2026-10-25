package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda11(ChatUsersActivity r1, TLObject r2, TLRPC.User r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatUsersActivity.$r8$lambda$YMon1SkEXQS98W4osJRO6zZbJK8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
