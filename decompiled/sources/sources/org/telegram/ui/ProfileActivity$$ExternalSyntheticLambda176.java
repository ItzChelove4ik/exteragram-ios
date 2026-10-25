package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda176 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ TL_account.TL_birthday f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda176(ProfileActivity r1, TLRPC.UserFull r2, TL_account.TL_birthday r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ProfileActivity.$r8$lambda$Gctt69tTeDomVfhB3PCkJrbwYMg(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
