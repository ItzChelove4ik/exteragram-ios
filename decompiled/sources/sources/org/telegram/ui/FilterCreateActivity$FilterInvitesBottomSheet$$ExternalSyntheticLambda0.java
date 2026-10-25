package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.FilterCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ MessagesController.DialogFilter f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda0(BaseFragment r1, MessagesController.DialogFilter r2, Runnable r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        FilterCreateActivity.FilterInvitesBottomSheet.$r8$lambda$xTpNyZuWJQLrAhKG15CixJcejUg(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
