package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda130 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TL_update.TL_updateNewChannelMessage f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda130(SendMessagesHelper r1, TL_update.TL_updateNewChannelMessage r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$Cm55TOsTwG250aJg7z1T67z8jS4(this.f$0, this.f$1, this.f$2);
    }
}
