package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda90 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda90(LaunchActivity r1, int r2, AlertDialog r3, Runnable r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        LaunchActivity.m15630$r8$lambda$juTkcuiFl6vQ820245peQTrOWc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
