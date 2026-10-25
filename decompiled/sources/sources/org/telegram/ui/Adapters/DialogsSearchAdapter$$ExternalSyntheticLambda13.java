package org.telegram.ui.Adapters;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ProfileSearchCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda13 implements Utilities.Callback2 {
    public final /* synthetic */ DialogsSearchAdapter f$0;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda13(DialogsSearchAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.openSponsoredOptions((ProfileSearchCell) r1, (TLRPC.TL_sponsoredPeer) r2);
    }
}
