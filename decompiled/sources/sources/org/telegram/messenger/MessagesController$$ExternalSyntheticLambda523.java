package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda523 implements Utilities.Callback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_messages_invitedUsers f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda523(MessagesController r1, TLRPC.TL_messages_invitedUsers r2, int[] r3, int r4, TLRPC.Chat r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        MessagesController.$r8$lambda$gsLIb3Wn77eKlczW3dz7Be2Myao(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLRPC.TL_messages_invitedUsers) r8);
    }
}
