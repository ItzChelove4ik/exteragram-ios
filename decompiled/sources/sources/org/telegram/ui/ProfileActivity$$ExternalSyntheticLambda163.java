package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda163 implements Runnable {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_username f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda163(ProfileActivity r1, TLObject r2, TLRPC.TL_username r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.$r8$lambda$XKBBwZqaSNjRRdibk3kK5Di1d_g(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
