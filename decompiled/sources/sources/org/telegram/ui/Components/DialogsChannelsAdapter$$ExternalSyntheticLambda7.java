package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsChannelsAdapter$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ DialogsChannelsAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_searchGlobal f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLObject f$4;

    public /* synthetic */ DialogsChannelsAdapter$$ExternalSyntheticLambda7(DialogsChannelsAdapter r1, int r2, TLRPC.TL_messages_searchGlobal r3, boolean r4, TLObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsChannelsAdapter.m10125$r8$lambda$Qz2l3Q0X0nra50sksANFAX4j1A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
