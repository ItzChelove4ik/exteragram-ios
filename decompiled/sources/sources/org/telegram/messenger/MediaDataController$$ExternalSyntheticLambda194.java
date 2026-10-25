package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda194 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda194(MediaDataController r1, int r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MediaDataController.m4377$r8$lambda$6jTmVaC3gpaV5w2O01mBuyAkg(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
