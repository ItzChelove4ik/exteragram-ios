package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda88 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda88(ProfileActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r2) {
        ProfileActivity.$r8$lambda$Lgous4FPtZaBXZo1s3t0UnTRZms(this.f$0, this.f$1, r2);
    }
}
