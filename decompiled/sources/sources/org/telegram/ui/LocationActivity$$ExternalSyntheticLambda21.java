package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda21 implements MessagesStorage.IntCallback {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda21(LocationActivity r1, boolean r2, TLRPC.User r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r4) {
        LocationActivity.$r8$lambda$JwTss6qTf_szkRi8z4WlKIHHZp0(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
