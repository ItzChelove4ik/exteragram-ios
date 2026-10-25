package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda25 implements RequestDelegate {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ byte[] f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda25(Context r1, Theme.ResourcesProvider r2, byte[] r3, BaseFragment r4, Runnable r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ReportBottomSheet.$r8$lambda$M33PR0JD7PwlA2Pa4KZy6QZ0tv8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
