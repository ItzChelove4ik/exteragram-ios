package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda9 implements Utilities.Callback2 {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda9(LinkManager r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        LinkManager.$r8$lambda$27BjbDTWzQucmElTGG6AGUbtea4(this.f$0, this.f$1, (TL_account.Passkeys) r2, (TLRPC.TL_error) r3);
    }
}
