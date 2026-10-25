package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.FilterCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessagesController.DialogFilter f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda1(BaseFragment r1, TLObject r2, MessagesController.DialogFilter r3, TLRPC.TL_error r4, Runnable r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FilterCreateActivity.FilterInvitesBottomSheet.$r8$lambda$PtxB57vVtG7SpSeyo74XZjhcgzQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
