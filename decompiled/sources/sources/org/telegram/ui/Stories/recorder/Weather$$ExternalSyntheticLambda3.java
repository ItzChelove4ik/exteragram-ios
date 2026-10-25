package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.User[] f$1;
    public final /* synthetic */ double f$2;
    public final /* synthetic */ double f$3;
    public final /* synthetic */ int[] f$4;
    public final /* synthetic */ ConnectionsManager f$5;
    public final /* synthetic */ Utilities.Callback f$6;
    public final /* synthetic */ String f$7;

    public /* synthetic */ Weather$$ExternalSyntheticLambda3(MessagesController r1, TLRPC.User[] r2, double r3, double r5, int[] r7, ConnectionsManager r8, Utilities.Callback r9, String r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
        this.f$5 = r8;
        this.f$6 = r9;
        this.f$7 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Weather.$r8$lambda$giPJVZXgDvfv1Gjcv1pBtZl327Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
