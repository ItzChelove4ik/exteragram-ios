package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatLinkActivity$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ ChatLinkActivity f$0;
    public final /* synthetic */ AlertDialog[] f$1;

    public /* synthetic */ ChatLinkActivity$$ExternalSyntheticLambda11(ChatLinkActivity r1, AlertDialog[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatLinkActivity.$r8$lambda$9KjzbGCfiKudkDr6LSfSxwM7nH4(this.f$0, this.f$1, r2, r3);
    }
}
