package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda93 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TLRPC.InputInvoice f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ BulletinFactory f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda93(StarsController r1, MessageObject r2, TLRPC.InputInvoice r3, Utilities.Callback r4, BulletinFactory r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarsController.$r8$lambda$e83p9TWjyjvsRKqy9X3iTHDnbLk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
