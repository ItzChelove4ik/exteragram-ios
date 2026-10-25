package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$16$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ ProfileActivity.AnonymousClass16 f$0;

    public /* synthetic */ ProfileActivity$16$$ExternalSyntheticLambda6(ProfileActivity.AnonymousClass16 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ProfileActivity.AnonymousClass16.m18222$r8$lambda$J_6VEIa46hBSeutS7Bm11fz58(this.f$0, r1, r2);
    }
}
