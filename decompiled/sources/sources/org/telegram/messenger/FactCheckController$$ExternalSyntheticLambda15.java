package org.telegram.messenger;

import org.telegram.messenger.FactCheckController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda15 implements Utilities.Callback {
    public final /* synthetic */ FactCheckController f$0;
    public final /* synthetic */ FactCheckController.Key f$1;
    public final /* synthetic */ MessageObject f$2;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda15(FactCheckController r1, FactCheckController.Key r2, MessageObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        FactCheckController.m3999$r8$lambda$fIkaxRl8CN3xq7q4SzIvYtzEA0(this.f$0, this.f$1, this.f$2, (TLRPC.TL_factCheck) r3);
    }
}
