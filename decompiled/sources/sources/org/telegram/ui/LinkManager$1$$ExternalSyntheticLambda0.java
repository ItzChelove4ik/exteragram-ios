package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LinkManager;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ LinkManager.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ LinkManager$1$$ExternalSyntheticLambda0(LinkManager.AnonymousClass1 r1, TLRPC.User r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LinkManager.AnonymousClass1.$r8$lambda$_ZUnJ9ezAJY0sSljJFHofmzHk4c(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
