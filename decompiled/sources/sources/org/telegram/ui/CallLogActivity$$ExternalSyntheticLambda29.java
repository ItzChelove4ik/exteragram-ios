package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda29 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda29(int r1, AlertDialog r2, Context r3, Theme.ResourcesProvider r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        CallLogActivity.m6528$r8$lambda$bZZLZrcnfKLwN6U_NV8Mm2huYw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
