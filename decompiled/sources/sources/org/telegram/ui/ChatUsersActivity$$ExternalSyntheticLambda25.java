package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.TextCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda25 implements Utilities.Callback2 {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ TextCell f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda25(ChatUsersActivity r1, TextCell r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ChatUsersActivity.m8517$r8$lambda$zaYC3Cjux3O4VmQ86L454dQuAo(this.f$0, this.f$1, this.f$2, (TLRPC.Updates) r3, (TLRPC.TL_error) r4);
    }
}
