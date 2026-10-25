package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda146 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;
    public final /* synthetic */ ShareAlert f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda146(ProfileActivity r1, TLRPC.TL_username r2, ShareAlert r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ProfileActivity.m17808$r8$lambda$LgQvOv0xmFcvrXSsvUp6h9S63s(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
