package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda151 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda151(ProfileActivity r1, String r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ProfileActivity.$r8$lambda$Sd2G9W0XvpFAhg6y5W9TAEbXtpk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
