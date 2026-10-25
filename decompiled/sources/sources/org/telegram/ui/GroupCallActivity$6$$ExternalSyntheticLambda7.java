package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$6$$ExternalSyntheticLambda7 implements JoinCallAlert.JoinCallAlertDelegate {
    public final /* synthetic */ GroupCallActivity.AnonymousClass6 f$0;

    public /* synthetic */ GroupCallActivity$6$$ExternalSyntheticLambda7(GroupCallActivity.AnonymousClass6 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.JoinCallAlert.JoinCallAlertDelegate
    public final void didSelectChat(TLRPC.InputPeer r1, boolean r2, boolean r3, boolean r4) {
        GroupCallActivity.AnonymousClass6.m15297$r8$lambda$UEQm4r2e1jrobNKwh773unbzE(this.f$0, r1, r2, r3, r4);
    }
}
