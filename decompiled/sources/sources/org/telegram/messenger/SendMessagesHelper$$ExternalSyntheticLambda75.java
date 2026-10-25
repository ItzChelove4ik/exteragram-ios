package org.telegram.messenger;

import java.io.File;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda75 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_photo f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ File f$3;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda75(SendMessagesHelper r1, TLRPC.TL_photo r2, MessageObject r3, File r4, SendMessagesHelper.DelayedMessage r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4945$r8$lambda$yh2ItvMZWbsbV7fhHfWl2WEuPI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
