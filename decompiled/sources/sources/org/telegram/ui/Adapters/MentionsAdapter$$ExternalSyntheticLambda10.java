package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ MentionsAdapter f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ MentionsAdapter$$ExternalSyntheticLambda10(MentionsAdapter r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        MentionsAdapter.m5903$r8$lambda$2zMFKPtuv5vS7Vm8_mqKTsfT0A(this.f$0, this.f$1, r2, r3);
    }
}
