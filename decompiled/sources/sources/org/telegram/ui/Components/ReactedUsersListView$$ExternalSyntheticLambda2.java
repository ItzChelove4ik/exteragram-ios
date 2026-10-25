package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReactedUsersListView$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ReactedUsersListView f$0;

    public /* synthetic */ ReactedUsersListView$$ExternalSyntheticLambda2(ReactedUsersListView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ReactedUsersListView.$r8$lambda$Fv78N3DZdD9zLKOt03aIMXaG2Wc(this.f$0, r1, r2);
    }
}
