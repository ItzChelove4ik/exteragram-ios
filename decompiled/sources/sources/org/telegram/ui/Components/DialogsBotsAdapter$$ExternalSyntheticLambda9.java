package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsBotsAdapter$$ExternalSyntheticLambda9 implements Utilities.Callback2 {
    public final /* synthetic */ DialogsBotsAdapter f$0;
    public final /* synthetic */ TLRPC.TL_contacts_search f$1;

    public /* synthetic */ DialogsBotsAdapter$$ExternalSyntheticLambda9(DialogsBotsAdapter r1, TLRPC.TL_contacts_search r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        DialogsBotsAdapter.m10120$r8$lambda$KUUL8gCV03CB31hPoZ5CGsobpo(this.f$0, this.f$1, (TLRPC.TL_contacts_found) r2, (TLRPC.TL_error) r3);
    }
}
