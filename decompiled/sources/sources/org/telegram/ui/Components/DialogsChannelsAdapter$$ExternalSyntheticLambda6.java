package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsChannelsAdapter$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ DialogsChannelsAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_searchGlobal f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ DialogsChannelsAdapter$$ExternalSyntheticLambda6(DialogsChannelsAdapter r1, int r2, TLRPC.TL_messages_searchGlobal r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        DialogsChannelsAdapter.$r8$lambda$0MSiIE4gprn9orq0q_QzeuyjwfQ(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
