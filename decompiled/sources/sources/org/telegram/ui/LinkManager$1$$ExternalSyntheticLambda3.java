package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LinkManager;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$1$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ LinkManager.AnonymousClass1 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ LinkManager$1$$ExternalSyntheticLambda3(LinkManager.AnonymousClass1 r1, TLObject r2, TLRPC.User r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinkManager.AnonymousClass1.m15690$r8$lambda$MshUPh8amEooRQYD5ffuuICO9Y(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
