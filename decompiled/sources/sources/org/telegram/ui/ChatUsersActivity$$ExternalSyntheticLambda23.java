package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.TextCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ TextCell f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda23(ChatUsersActivity r1, TextCell r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatUsersActivity.$r8$lambda$N16Gx7YQXDGgiAF37HORjRRoces(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
