package org.telegram.messenger;

import androidx.core.util.Consumer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda527 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Consumer f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda527(MessagesController r1, TLRPC.Chat r2, boolean r3, int r4, Consumer r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$h17qzoC1iyYlGeJSC9ZZGqQYBGQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
