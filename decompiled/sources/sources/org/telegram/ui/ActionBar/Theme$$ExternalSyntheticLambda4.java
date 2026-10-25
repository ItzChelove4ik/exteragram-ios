package org.telegram.ui.ActionBar;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ int f$0;

    public /* synthetic */ Theme$$ExternalSyntheticLambda4(int r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        Theme.m5792$r8$lambda$HzQiQ7SzWbHkRbqdF1shtwbXbs(this.f$0, r1, r2);
    }
}
