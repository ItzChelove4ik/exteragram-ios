package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda18 implements Utilities.Callback {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ TLRPC.User[] f$1;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda18(LinkManager r1, TLRPC.User[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        LinkManager.$r8$lambda$M83DG4eOPEKDIndItiLYBXjJulY(this.f$0, this.f$1, (TLRPC.User) r2);
    }
}
