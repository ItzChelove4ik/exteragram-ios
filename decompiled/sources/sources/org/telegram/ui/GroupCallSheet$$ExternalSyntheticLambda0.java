package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallSheet$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TLRPC.InputGroupCall f$5;

    public /* synthetic */ GroupCallSheet$$ExternalSyntheticLambda0(AlertDialog r1, Browser.Progress r2, int r3, Context r4, long r5, TLRPC.InputGroupCall r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        GroupCallSheet.$r8$lambda$Foiu6CG_1ucS_wThAXsc32eF6_k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
