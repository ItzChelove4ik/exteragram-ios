package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$5$$ExternalSyntheticLambda17 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ SharedMediaLayout.AnonymousClass5 f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ SharedMediaLayout$5$$ExternalSyntheticLambda17(SharedMediaLayout.AnonymousClass5 r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r2) {
        SharedMediaLayout.AnonymousClass5.$r8$lambda$jvRbsf1ymogqqNYxnuMM6jNLHGI(this.f$0, this.f$1, r2);
    }
}
