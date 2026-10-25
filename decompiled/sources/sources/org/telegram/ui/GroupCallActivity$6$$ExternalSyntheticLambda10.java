package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$6$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity.AnonymousClass6 f$0;

    public /* synthetic */ GroupCallActivity$6$$ExternalSyntheticLambda10(GroupCallActivity.AnonymousClass6 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        GroupCallActivity.AnonymousClass6.$r8$lambda$OaLFMPzyOZNBsAE0XwP8XBrQCiI(this.f$0, r1, r2);
    }
}
