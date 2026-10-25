package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda143 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ ArrayList f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda143(MessagesStorage r1, TLRPC.Message r2, boolean r3, ArrayList r4, ArrayList r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$jsGx9B3iMM0dG9plpC6YnWmdl4Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
