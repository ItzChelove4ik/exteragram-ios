package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$19$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.Chat f$2;

    public /* synthetic */ ProfileActivity$19$$ExternalSyntheticLambda0(BaseFragment r1, TLRPC.User r2, TLRPC.Chat r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.AnonymousClass19.$r8$lambda$EvCmV7eYRMBpsE1hkgs7Z0_99mA(this.f$0, this.f$1, this.f$2);
    }
}
