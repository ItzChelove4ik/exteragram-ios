package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda525 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.ChannelParticipant[] f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda525(ChatActivity r1, TLRPC.ChannelParticipant[] r2, ArrayList r3, ArrayList r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        ChatActivity.$r8$lambda$w1nGx9u3GjwNyYaGzNian64UfXk(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_channels_channelParticipant) r7, (TLRPC.TL_error) r8);
    }
}
