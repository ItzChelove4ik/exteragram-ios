package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda25 implements Utilities.Callback {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda25(TranslateController r1, int r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        TranslateController.$r8$lambda$YAuTAODGqjjt86_doVzdA_AyU7s(this.f$0, this.f$1, this.f$2, (TLRPC.TL_textWithEntities) r3);
    }
}
