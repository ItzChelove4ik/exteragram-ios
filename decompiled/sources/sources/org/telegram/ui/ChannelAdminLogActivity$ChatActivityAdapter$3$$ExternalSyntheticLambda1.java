package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChannelAdminLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3 f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ AlertDialog f$3;

    public /* synthetic */ ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda1(ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3 r1, TLRPC.TL_chatInviteExported r2, boolean[] r3, AlertDialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3.$r8$lambda$iAWY9uUdcb63Tl7wSWObbtHydwM(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
