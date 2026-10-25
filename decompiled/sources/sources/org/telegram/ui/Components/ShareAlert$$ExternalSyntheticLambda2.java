package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ShareAlert$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ShareAlert f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ ShareAlert$$ExternalSyntheticLambda2(ShareAlert r1, Context r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ShareAlert.m12649$r8$lambda$afmCE9Pb9L9rx1QIMJCy2kiVaE(this.f$0, this.f$1, r2, r3);
    }
}
