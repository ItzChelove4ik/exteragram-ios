package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda153 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TLRPC.TL_channels_channelParticipant f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda153(MessagesController r1, long r2, ArrayList r4, TLRPC.TL_channels_channelParticipant r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$TWxp24pmkDjB1ooLf4ZNFQicelg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
