package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda27 implements RequestDelegate {
    public final /* synthetic */ DialogsSearchAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda27(DialogsSearchAdapter r1, int r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        DialogsSearchAdapter.m5880$r8$lambda$NoOY0Cz1simcTuM9zv8W7KLmqI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
