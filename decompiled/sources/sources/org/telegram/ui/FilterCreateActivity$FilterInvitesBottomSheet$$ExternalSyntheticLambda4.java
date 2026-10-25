package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.FilterCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ FilterCreateActivity.FilterInvitesBottomSheet f$0;

    public /* synthetic */ FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda4(FilterCreateActivity.FilterInvitesBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        FilterCreateActivity.FilterInvitesBottomSheet.$r8$lambda$iUBLOzQJb9_5WX6WsFGsm3MDFvg(this.f$0, r1, r2);
    }
}
