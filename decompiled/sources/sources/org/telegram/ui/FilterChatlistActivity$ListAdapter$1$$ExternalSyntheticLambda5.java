package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.FilterChatlistActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ FilterChatlistActivity.ListAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda5(FilterChatlistActivity.ListAdapter.AnonymousClass1 r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        FilterChatlistActivity.ListAdapter.AnonymousClass1.$r8$lambda$wmj4UTP4egC9fE98nDlSzTUkmjI(this.f$0, this.f$1, r2, r3);
    }
}
