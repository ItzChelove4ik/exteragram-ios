package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda66 implements Utilities.Callback2 {
    public final /* synthetic */ GroupCallActivity f$0;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda66(GroupCallActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        GroupCallActivity.$r8$lambda$PmWCw3YzSbkNLdUhSS1YvRxTxSQ(this.f$0, (TLRPC.Updates) r1, (TLRPC.TL_error) r2);
    }
}
