package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ManageLinksActivity$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ ManageLinksActivity f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ ManageLinksActivity$$ExternalSyntheticLambda5(ManageLinksActivity r1, TLRPC.TL_chatInviteExported r2, TLRPC.TL_error r3, TLObject r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ManageLinksActivity.$r8$lambda$uBxnQ7MKelnveuFqFA_x9k6_jN8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
