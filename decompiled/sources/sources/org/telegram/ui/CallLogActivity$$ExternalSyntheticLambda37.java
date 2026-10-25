package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda37 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda37(AlertDialog r1, Context r2, int r3, TL_phone.exportGroupCallInvite r4, Theme.ResourcesProvider r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        CallLogActivity.$r8$lambda$eYcPO4NWtGlnlCJLYMBFQqvdDNY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
