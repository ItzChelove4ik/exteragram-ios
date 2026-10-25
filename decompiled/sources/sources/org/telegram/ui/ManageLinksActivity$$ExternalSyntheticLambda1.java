package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ManageLinksActivity$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ ManageLinksActivity f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ManageLinksActivity$$ExternalSyntheticLambda1(ManageLinksActivity r1, TLRPC.TL_chatInviteExported r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ManageLinksActivity.$r8$lambda$g8LOZXpU4tFNheHHMQrTesoKfl8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
