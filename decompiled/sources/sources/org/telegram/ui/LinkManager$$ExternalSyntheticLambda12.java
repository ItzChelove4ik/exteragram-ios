package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda12 implements Utilities.Callback {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ TLRPC.User[] f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda12(LinkManager r1, TLRPC.User[] r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        LinkManager.$r8$lambda$Rx0vQsu1qGItd7IWcoO776ixb9U(this.f$0, this.f$1, this.f$2, (Long) r3);
    }
}
