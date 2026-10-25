package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda165 implements Runnable {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ ActionIntroActivity f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda165(AlertDialog r1, TLObject r2, ActionIntroActivity r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LaunchActivity.m15642$r8$lambda$swXf3dSvh8HHFZzxTC8rZQJfnc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
