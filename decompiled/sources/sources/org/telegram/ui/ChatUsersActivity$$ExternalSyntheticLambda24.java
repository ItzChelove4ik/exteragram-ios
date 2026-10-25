package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.TextCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda24 implements RequestDelegate {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ TextCell f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda24(ChatUsersActivity r1, TextCell r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatUsersActivity.m8504$r8$lambda$IJTbhRnRE6RyeeB2Oavu5OEX9g(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
