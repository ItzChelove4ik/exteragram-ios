package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda170 implements Utilities.Callback {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda170(ProfileActivity r1, TLRPC.UserFull r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ProfileActivity.m17844$r8$lambda$rIEMiFIme1OW_EVvNySZK0u6O4(this.f$0, this.f$1, (TL_account.TL_birthday) r2);
    }
}
