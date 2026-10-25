package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsChannelsAdapter$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ DialogsChannelsAdapter f$0;
    public final /* synthetic */ TLRPC.TL_contacts_search f$1;

    public /* synthetic */ DialogsChannelsAdapter$$ExternalSyntheticLambda1(DialogsChannelsAdapter r1, TLRPC.TL_contacts_search r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        DialogsChannelsAdapter.$r8$lambda$0G0haSjFAol8ostpHiorw_k91rM(this.f$0, this.f$1, (TLRPC.TL_contacts_found) r2, (TLRPC.TL_error) r3);
    }
}
