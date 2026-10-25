package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.FiltersSetupActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ FiltersSetupActivity.ListAdapter f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ MessagesController.DialogFilter f$2;

    public /* synthetic */ FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda8(FiltersSetupActivity.ListAdapter r1, AlertDialog r2, MessagesController.DialogFilter r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        FiltersSetupActivity.ListAdapter.$r8$lambda$LIgIKwvacfGv0QXo2WmoHNt96mU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
