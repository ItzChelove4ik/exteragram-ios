package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsBotsAdapter$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ DialogsBotsAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_searchGlobal f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ DialogsBotsAdapter$$ExternalSyntheticLambda10(DialogsBotsAdapter r1, int r2, TLRPC.TL_messages_searchGlobal r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        DialogsBotsAdapter.$r8$lambda$_mdNbZ4ghTD50U_EeELTaMQ0iwA(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
