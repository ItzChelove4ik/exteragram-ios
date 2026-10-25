package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda125 implements Runnable {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.ChannelParticipant f$1;
    public final /* synthetic */ TLRPC.ChatParticipant f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda125(ProfileActivity r1, TLRPC.ChannelParticipant r2, TLRPC.ChatParticipant r3, TLRPC.User r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.$r8$lambda$8KUPiZr1dAWUO66BjIJ4N9ypl0Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
