package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$SearchAdapter$$ExternalSyntheticLambda139 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity.SearchAdapter f$0;

    public /* synthetic */ ProfileActivity$SearchAdapter$$ExternalSyntheticLambda139(ProfileActivity.SearchAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ProfileActivity.SearchAdapter.$r8$lambda$3ms37uvIpv3XQIbRMLUxeFtdp4I(this.f$0, r1, r2);
    }
}
