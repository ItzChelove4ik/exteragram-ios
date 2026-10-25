package org.telegram.ui;

import android.net.Uri;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda51 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Uri f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ AlertDialog f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda51(LaunchActivity r1, Uri r2, int r3, AlertDialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LaunchActivity.m15607$r8$lambda$Q3aTtC4dXdYPeHJkLJ9MBdxVgA(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
