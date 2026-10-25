package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda82 implements Utilities.Callback2 {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda82(ProfileActivity r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ProfileActivity.$r8$lambda$sKX0Xl1TsTWGhZ7YFvpouf1GlPc(this.f$0, this.f$1, (Integer) r2, (TLRPC.TL_error) r3);
    }
}
