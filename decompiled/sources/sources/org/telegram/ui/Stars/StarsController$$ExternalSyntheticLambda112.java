package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda112 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TLRPC.InputInvoice f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ BulletinFactory f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda112(StarsController r1, TLObject r2, MessageObject r3, TLRPC.InputInvoice r4, Utilities.Callback r5, BulletinFactory r6, TLRPC.TL_error r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19131$r8$lambda$kdsBADDRHT0H4MsqYCEBJkPNro(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
