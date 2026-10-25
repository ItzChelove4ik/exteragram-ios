package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda185 implements Runnable {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.UserFull f$2;
    public final /* synthetic */ TL_account.TL_birthday f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda185(ProfileActivity r1, TLObject r2, TLRPC.UserFull r3, TL_account.TL_birthday r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.m17809$r8$lambda$N65mP4byop7WZoA9yvOiqAowg4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
