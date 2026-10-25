package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ TLRPC.User[] f$2;
    public final /* synthetic */ TLRPC.TL_requestPeerTypeCreateBot f$3;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda11(LinkManager r1, BaseFragment r2, TLRPC.User[] r3, TLRPC.TL_requestPeerTypeCreateBot r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinkManager.$r8$lambda$lUbPYZohusLcD1MGHe1LvMEk9kE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
