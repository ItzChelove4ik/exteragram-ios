package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$ListAdapter$13$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ProfileActivity.ListAdapter.AnonymousClass13 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_username f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ ProfileActivity$ListAdapter$13$$ExternalSyntheticLambda1(ProfileActivity.ListAdapter.AnonymousClass13 r1, TLObject r2, TLRPC.TL_username r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.ListAdapter.AnonymousClass13.$r8$lambda$TDopbDYnY6TSBYpB2kA5xERh9Zs(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
