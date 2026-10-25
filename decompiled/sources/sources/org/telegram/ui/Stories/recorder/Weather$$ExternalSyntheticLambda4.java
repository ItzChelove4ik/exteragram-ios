package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ TLRPC.User[] f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ Weather$$ExternalSyntheticLambda4(int[] r1, MessagesController r2, TLRPC.User[] r3, Runnable r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        Weather.$r8$lambda$8smBNWt3aHzArTPuFKHaKWBOLSI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
