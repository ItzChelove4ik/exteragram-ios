package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda32 implements RequestDelegate {
    public final /* synthetic */ ChatUsersActivity f$0;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda32(ChatUsersActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ChatUsersActivity.m8510$r8$lambda$hHV4EA8yN6ZOz6jLWbg7aDZO0w(this.f$0, r1, r2);
    }
}
