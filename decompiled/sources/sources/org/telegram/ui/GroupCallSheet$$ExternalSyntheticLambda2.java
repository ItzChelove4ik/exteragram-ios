package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallSheet$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ TLRPC.InputGroupCall f$6;
    public final /* synthetic */ TLRPC.TL_error f$7;

    public /* synthetic */ GroupCallSheet$$ExternalSyntheticLambda2(AlertDialog r1, Browser.Progress r2, TLObject r3, int r4, Context r5, long r6, TLRPC.InputGroupCall r8, TLRPC.TL_error r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupCallSheet.$r8$lambda$Akf10XNgC6J0t1JRJKCuBgnc3cI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
