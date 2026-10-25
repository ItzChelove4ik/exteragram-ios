package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda90 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda90(ProfileActivity r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ProfileActivity.m17802$r8$lambda$AM0nJilDGtt5Pcs9XreRBYCMjE(this.f$0, this.f$1, r2, r3);
    }
}
