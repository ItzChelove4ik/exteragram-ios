package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ TLRPC.User[] f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ Utilities.Callback f$5;

    public /* synthetic */ Weather$$ExternalSyntheticLambda13(int[] r1, TLObject r2, MessagesController r3, TLRPC.User[] r4, Runnable r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Weather.$r8$lambda$hk9az5DrJFjlueUS6FNBCLlJ_t4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
