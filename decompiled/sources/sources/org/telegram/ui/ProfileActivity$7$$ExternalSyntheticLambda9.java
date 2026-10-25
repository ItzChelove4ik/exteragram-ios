package org.telegram.ui;

import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ UserConfig f$2;
    public final /* synthetic */ TLRPC.Photo f$3;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda9(ProfileActivity.AnonymousClass7 r1, TLObject r2, UserConfig r3, TLRPC.Photo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.AnonymousClass7.$r8$lambda$GnmTnZ_P5RH0lFKTVgtcXa8iO6o(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
