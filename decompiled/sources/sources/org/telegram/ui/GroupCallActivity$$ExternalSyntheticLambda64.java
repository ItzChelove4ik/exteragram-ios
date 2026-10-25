package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda64 implements Utilities.Callback2 {
    public final /* synthetic */ GroupCallActivity f$0;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda64(GroupCallActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        GroupCallActivity.$r8$lambda$tEZPeunWaLtv6BuQXLJYU5KiS7I(this.f$0, (TLRPC.Updates) r1, (TLRPC.TL_error) r2);
    }
}
